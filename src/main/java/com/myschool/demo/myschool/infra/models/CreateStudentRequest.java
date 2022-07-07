package com.myschool.demo.myschool.infra.models;

import lombok.Data;

@Data
public class CreateStudentRequest {

  private String name;
  private String birth;
  private String gender;
}
