package com.example.mini_project_be.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDtoForEdit {

  @NotEmpty
  private String email;

  @NotEmpty
  private String password;

}
