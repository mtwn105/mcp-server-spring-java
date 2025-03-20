package dev.amitwani.mcp_spring_java.config;

import dev.amitwani.mcp_spring_java.service.UserService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MCPConfig {

    private final UserService userService;

    @Autowired
    public MCPConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    ToolCallbackProvider userTools() {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(userService)
                .build();
    }

}
