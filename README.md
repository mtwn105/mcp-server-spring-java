# MCP Server using Spring Boot Java

A Model Context Protocol (MCP) server implementation built with Spring Boot and Java 21. This project demonstrates how to create an MCP server that provides user management tools for AI assistants.

## Features

- Implements MCP server functionality using Spring AI
- Exposes user management tools via MCP
- Synchronous communication mode
- Support for Standard I/O and Server-Sent Events transports
- Integrates with DummyJSON external API for user data

## Technologies

- Java 21
- Spring Boot 3.4.3
- Spring AI (Model Context Protocol)
- Project Lombok
- Maven

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven

### Installation

1. Clone the repository

```bash
git clone https://github.com/yourusername/mcp-spring-java.git
cd mcp-spring-java
```

2. Build the project

```bash
mvn clean package
```

3. Run the application

```bash
java -jar target/mcp-spring-java-0.0.1-SNAPSHOT.jar
```

## Configuration

The MCP server is configured in `application.yml`:

- Server runs on port 8090
- Server name: my-dummy-users-server
- Synchronous communication mode
- Supports STDIO transport for terminal-based communication
- Exposes `/mcp/message` endpoint for SSE communication

## Available Tools

The server exposes the following user management tools:

- `getAllUsers` - Get all users with pagination
- `getAllUsersDefault` - Get all users with default pagination
- `getUserById` - Get a single user by ID
- `searchUsers` - Search for users by query
- `addUser` - Add a new user
- `updateUser` - Update a user
- `deleteUser` - Delete a user

## Testing with Postman

You can use Postman to test the MCP server endpoints:

1. Start the MCP server
2. Open Postman and create a new request:

   - Method: POST
   - URL: http://localhost:8090/mcp/message
   - Headers:
     - Content-Type: application/json
     - Accept: application/json

3. For the request body, use the following JSON format to invoke any of the available tools:

```json
{
  "message": {
    "toolCalls": [
      {
        "id": "call-123",
        "name": "[TOOL_NAME]",
        "parameters": {
          "[PARAMETER_NAME]": "[PARAMETER_VALUE]"
        }
      }
    ]
  }
}
```

### Example Requests

#### Get All Users (Default Pagination)

```json
{
  "message": {
    "toolCalls": [
      {
        "id": "call-123",
        "name": "getAllUsersDefault",
        "parameters": {}
      }
    ]
  }
}
```

#### Get User by ID

```json
{
  "message": {
    "toolCalls": [
      {
        "id": "call-123",
        "name": "getUserById",
        "parameters": {
          "id": 1
        }
      }
    ]
  }
}
```

#### Search Users

```json
{
  "message": {
    "toolCalls": [
      {
        "id": "call-123",
        "name": "searchUsers",
        "parameters": {
          "query": "John"
        }
      }
    ]
  }
}
```

The response will contain a JSON object with the results from the tool execution.

## Usage with MCP Clients

You can configure this server in MCP client applications using the following configuration:

```json
{
  "mcpServers": {
    "dummy-user-server": {
      "command": "java",
      "args": [
        "-Dspring.ai.mcp.server.stdio=true",
        "-Dspring.main.web-application-type=none",
        "-Dlogging.pattern.console=",
        "-jar",
        "path/to/mcp-spring-java-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```

## License

This project is licensed under the terms provided in the LICENSE file.
