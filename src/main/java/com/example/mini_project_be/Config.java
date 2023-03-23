package com.example.mini_project_be;

import com.example.mini_project_be.repository.JpaUserRepository;
import com.example.mini_project_be.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class Config {

  private final EntityManager entityManager;

  public Config(EntityManager entityManager) { // 생성자 주입
    this.entityManager = entityManager;
  }

  // 레포지토리
  @Bean
  public UserRepository userRepository() {
    return new JpaUserRepository(entityManager);
  }

}
