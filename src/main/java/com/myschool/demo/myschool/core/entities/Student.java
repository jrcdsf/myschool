package com.myschool.demo.myschool.core.entities;

import java.util.HashSet;
import java.util.Set;

public class Student {

  private long id;
  private String name;
  private String birth;
  private String gender;

  private Set<Course> courses = new HashSet<>();

  public Student() {
  }

  public Student(String name, String birth, String gender) {
    this.name = name;
    this.birth = birth;
    this.gender = gender;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Set<Course> getCourses() {
    return courses;
  }

  public void setCourses(Set<Course> courses) {
    this.courses = courses;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", birth='" + birth + '\'' +
        ", gender='" + gender + '\'' +
        ", courses=" + courses +
        '}';
  }
}
