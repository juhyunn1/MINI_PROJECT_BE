package com.example.mini_project_be.repository;

import com.example.mini_project_be.domain.User;
import com.example.mini_project_be.dto.UserDtoForEdit;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  public User save(User member);
  public Optional<User> findById(Long id);
  public Optional<User> findByEmail(String email);
  public List<User> findAll();
  public void update(UserDtoForEdit userDtoForEdit);
  public Long remove(Long id);
}
