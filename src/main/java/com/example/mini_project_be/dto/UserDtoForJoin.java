package com.example.mini_project_be.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDtoForJoin {

  @NotBlank // 공백도 안댐
  private String name;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Size(min=4)
  private String password;
}
