package com.example.mini_project_be.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name, thumbnail, detail;

  @Column(name = "start_date")
  private String startDate;

  @Column(name = "end_date")
  private String endDate;

}
