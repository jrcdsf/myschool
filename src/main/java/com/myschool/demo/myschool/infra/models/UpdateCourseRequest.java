package com.myschool.demo.myschool.infra.models;

import lombok.Data;

@Data
public class UpdateCourseRequest {

  private long id;
  private String name;
  private String description;
}
