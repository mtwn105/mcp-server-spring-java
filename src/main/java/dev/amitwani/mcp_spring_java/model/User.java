package dev.amitwani.mcp_spring_java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private int id;
  private String firstName;
  private String lastName;
  private String maidenName;
  private int age;
  private String gender;
  private String email;
  private String phone;
  private String username;
  private String password;
  private String birthDate;
  private String image;
  private String bloodGroup;
  private double height;
  private double weight;
  private String eyeColor;
  private Map<String, String> hair;
  private String ip;
  private Map<String, Object> address;
  private String macAddress;
  private String university;
  private Map<String, Object> bank;
  private Map<String, Object> company;
  private String ein;
  private String ssn;
  private String userAgent;
  private Map<String, String> crypto;
  private String role;
}