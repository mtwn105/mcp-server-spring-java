package dev.amitwani.mcp_spring_java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {
  private List<User> users;
  private int total;
  private int skip;
  private int limit;
}