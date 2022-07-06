package com.myschool.demo.myschool.infra.services;

import com.myschool.demo.myschool.core.boundaries.StudentCourseServiceInterface;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.infra.entities.StudentDto;
import com.myschool.demo.myschool.infra.mappers.CycleAvoidingMappingContext;
import com.myschool.demo.myschool.infra.mappers.StudentMapper;
import com.myschool.demo.myschool.infra.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService implements StudentCourseServiceInterface {

  private final StudentRepository repository;
  private final StudentMapper mapper;

  public StudentCourseService(StudentRepository repository, StudentMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Optional<List<Student>> findStudentsByCourseId(long id) {
    Optional<List<StudentDto>> students = repository.findByCourseId(
        id);
    return students.map(studentDtos -> studentDtos.stream()
        .map(s -> mapper.toStudent(s, new CycleAvoidingMappingContext())).collect(
            Collectors.toList()));
  }

  @Override
  public Optional<List<Course>> findCoursesByStudentId(long id) {
    return Optional.empty();
  }

  @Override
  public Optional<List<Student>> findStudentsWithoutEnrollments() {
    Optional<List<StudentDto>> students = repository.findStudentsWithoutEnrollments();
    return students.map(studentDtos -> studentDtos.stream()
        .map(s -> mapper.toStudent(s, new CycleAvoidingMappingContext())).collect(
            Collectors.toList()));
  }
}
