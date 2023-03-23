package com.example.mini_project_be.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

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