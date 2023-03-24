package com.example.mini_project_be.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDtoForEdit {

  private String email;

  @NotBlank
  private String currentPassword;

  @NotBlank
  @Size(min=4, max=8)
  private String newPassword;
}
