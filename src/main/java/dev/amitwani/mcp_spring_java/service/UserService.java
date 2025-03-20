package dev.amitwani.mcp_spring_java.service;

import dev.amitwani.mcp_spring_java.model.User;
import dev.amitwani.mcp_spring_java.model.UsersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class UserService {

  private final RestTemplate restTemplate;
  private final String BASE_URL = "https://dummyjson.com";

  public UserService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  /**
   * Get all users with pagination
   *
   * @param limit Maximum number of users to return
   * @param skip  Number of users to skip for pagination
   * @return List of users wrapped in a response object
   */
  @Tool(name = "getAllUsers", description = "Get all users")
  public UsersResponse getAllUsers(int limit, int skip) {
    String url = BASE_URL + "/users?limit=" + limit + "&skip=" + skip;
    return restTemplate.getForObject(url, UsersResponse.class);
  }

  /**
   * Get all users (default pagination)
   *
   * @return List of users wrapped in a response object
   */
  @Tool(name = "getAllUsersDefault", description = "Get all users with default pagination")
  public UsersResponse getAllUsers() {
    String url = BASE_URL + "/users";
    return restTemplate.getForObject(url, UsersResponse.class);
  }

  /**
   * Get a single user by ID
   *
   * @param id The user ID
   * @return User object
   */
  @Tool(name = "getUserById", description = "Get a single user by ID")
  public User getUserById(int id) {
    String url = BASE_URL + "/users/" + id;
    return restTemplate.getForObject(url, User.class);
  }

  /**
   * Search for users by query
   *
   * @param query The search query
   * @return List of users that match the query
   */
  @Tool(name = "searchUsers", description = "Search for users by query")
  public UsersResponse searchUsers(String query) {
    String url = BASE_URL + "/users/search?q=" + query;
    return restTemplate.getForObject(url, UsersResponse.class);
  }

  /**
   * Add a new user
   *
   * @param user The user to add
   * @return The added user with ID
   */
  @Tool(name = "addUser", description = "Add a new user")
  public User addUser(User user) {
    String url = BASE_URL + "/users/add";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<User> request = new HttpEntity<>(user, headers);

    return restTemplate.postForObject(url, request, User.class);
  }

  /**
   * Update a user
   *
   * @param id      The ID of the user to update
   * @param updates A map of fields to update
   * @return The updated user
   */
  @Tool(name = "updateUser", description = "Update a user")
  public User updateUser(int id, Map<String, Object> updates) {
    String url = BASE_URL + "/users/" + id;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Map<String, Object>> request = new HttpEntity<>(updates, headers);

    return restTemplate.exchange(url, HttpMethod.PUT, request, User.class).getBody();
  }

  /**
   * Delete a user
   *
   * @param id The ID of the user to delete
   * @return The deleted user with isDeleted flag
   */
  @Tool(name = "deleteUser", description = "Delete a user")
  public User deleteUser(int id) {
    String url = BASE_URL + "/users/" + id;

    return restTemplate.exchange(url, HttpMethod.DELETE, null, User.class).getBody();
  }


}