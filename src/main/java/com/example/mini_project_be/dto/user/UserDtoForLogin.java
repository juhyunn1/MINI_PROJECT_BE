package com.example.mini_project_be.dto.user;

import lombok.Data;

@Data
public class UserDtoForLogin {

  private String email;
  private String password;
}
