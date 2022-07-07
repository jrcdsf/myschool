package com.myschool.demo.myschool.core.boundaries;

import com.myschool.demo.myschool.core.entities.Student;
import java.util.List;
import java.util.Optional;

public interface StudentServiceInterface {
  Student save(Student student);

  boolean delete(long id);

  List<Student> findAll();

  Optional<Student> findById(long id);

  Optional<Student> findByName(String name);
}
