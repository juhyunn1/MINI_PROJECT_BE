package com.example.mini_project_be.repository;

import com.example.mini_project_be.repository.user.JpaUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class JpaUserRepositoryTest {

  EntityManager entityManager;
  private JpaUserRepository jpaUserRepository = new JpaUserRepository(entityManager);

  @Test
  void save() {

  }

  @Test
  void findById() {
  }

  @Test
  void findByEmail() {
  }

  @Test
  void findAll() {
  }

  @Test
  void update() {
  }

  @Test
  void remove() {
  }
}