package com.example.mini_project_be.dto;

import lombok.Data;

@Data
public class UserDtoForLogin {

  private String email;
  private String password;
}
