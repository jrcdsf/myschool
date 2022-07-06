package com.myschool.demo.myschool.infra.services;

import com.myschool.demo.myschool.core.boundaries.StudentServiceInterface;
import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.infra.entities.StudentDto;
import com.myschool.demo.myschool.infra.mappers.CycleAvoidingMappingContext;
import com.myschool.demo.myschool.infra.mappers.StudentMapper;
import com.myschool.demo.myschool.infra.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService implements StudentServiceInterface {

  private final StudentRepository repository;
  private final StudentMapper mapper;

  public StudentService(StudentRepository repository, StudentMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Student save(Student student) {
    return mapper.toStudent(repository.save(mapper.toStudentDto(student, new CycleAvoidingMappingContext())), new CycleAvoidingMappingContext());
  }

  @Override
  public boolean delete(long id) {
    if (repository.existsById(id)){
      repository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  public List<Student> findAll() {
    try {
      List<StudentDto> students = repository.findAll();
      return students.stream().map(s -> mapper.toStudent(s, new CycleAvoidingMappingContext())).collect(Collectors.toList());
    } catch (RuntimeException e) {
      log.error("Find all error ", e);
      throw e;
    }
  }

  @Override
  public Optional<Student> findById(long id) {
    return Optional.ofNullable(mapper.toStudent(repository.findById(id).orElse(null),new CycleAvoidingMappingContext()));
  }

  @Override
  public Optional<List<Student>> findByName(String name) {
    Optional<List<StudentDto>> list = repository.findByName(name);
    if (list.isPresent()) {
      List<Student> res = list.get().stream().map(s -> mapper.toStudent(s, new CycleAvoidingMappingContext()))
          .collect(Collectors.toList());
      return Optional.of(res);
    }
    return Optional.empty();
  }
}
