package com.myschool.demo.myschool.infra.models;

import lombok.Data;

@Data
public class CreateCourseRequest {

  private String name;
  private String description;

}
