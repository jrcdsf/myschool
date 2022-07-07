package com.myschool.demo.myschool.infra.models;

import lombok.Data;

@Data
public class UpdateStudentRequest {

  private long id;
  private String name;
  private String birth;
  private String gender;
}
