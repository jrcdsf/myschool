package com.myschool.demo.myschool.core.boundaries;

import com.myschool.demo.myschool.core.entities.Course;
import java.util.List;
import java.util.Optional;

public interface CourseServiceInterface {
  Course save(Course course);

  boolean delete(long id);

  List<Course> findAll();

  Optional<Course> findById(long id);

  Optional<Course> findByName(String name);

  int countStudentsByCourse(long id);
}
