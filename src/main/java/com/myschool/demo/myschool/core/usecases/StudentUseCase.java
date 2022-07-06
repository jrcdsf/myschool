package com.myschool.demo.myschool.core.usecases;

import com.myschool.demo.myschool.core.boundaries.StudentServiceInterface;
import com.myschool.demo.myschool.core.entities.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class StudentUseCase {

  private final StudentServiceInterface service;

  public StudentUseCase(StudentServiceInterface service) {
    this.service = service;
  }

  public Student addStudent(Student student) {
    return service.save(student);
  }

  public Optional<Student> updateStudent(Student student) {
    Optional<Student> retrieved = service.findById(student.getId());
    if (retrieved.isPresent()) {
      student.setCourses(retrieved.get().getCourses());
      return Optional.of(service.save(student));
    }
    return Optional.empty();
  }

  public List<Student> listStudents(){
    return service.findAll();
  }

  public boolean deleteStudent(long id) {
    return service.delete(id);
  }

  public Optional<Student> findStudent(long id) {
    return service.findById(id);
  }

  public Optional<List<Student>> findStudent(String name) {
    return service.findByName(name);
  }
}
