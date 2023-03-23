package com.example.mini_project_be.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER) // 카트가 Many
  @JoinColumn(name="user_id") // FK
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="product_id")
  private Product product;
}
