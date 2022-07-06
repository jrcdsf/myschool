package com.myschool.demo.myschool.infra.services;

import com.myschool.demo.myschool.core.boundaries.CourseServiceInterface;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.infra.entities.CourseDto;
import com.myschool.demo.myschool.infra.mappers.CourseMapper;
import com.myschool.demo.myschool.infra.mappers.CycleAvoidingMappingContext;
import com.myschool.demo.myschool.infra.repositories.CourseRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements CourseServiceInterface {

  private final CourseRepository repository;
  private final CourseMapper mapper;

  public CourseService(CourseRepository repository, CourseMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Course save(Course course) {
    return mapper.toCourse(repository.save(mapper.toCourseDto(course, new CycleAvoidingMappingContext())),new CycleAvoidingMappingContext());
  }

  @Override
  public boolean delete(long id) {
    if (repository.existsById(id)) {
      repository.deleteStudentCourseByCourseId(id);
      repository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  public List<Course> findAll() {
    return repository.findAll().stream().map(c -> mapper.toCourse(c,new CycleAvoidingMappingContext())).collect(Collectors.toList());
  }

  @Override
  public Optional<Course> findById(long id) {
    return Optional.ofNullable(mapper.toCourse(repository.findById(id).orElse(null),new CycleAvoidingMappingContext()));
  }

  @Override
  public Optional<Course> findByName(String name) {
    Optional<CourseDto> courseDto = repository.findByName(
        name);
    if (courseDto.isPresent()) {
      Course res = mapper.toCourse(courseDto.get(), new CycleAvoidingMappingContext());
      return Optional.of(res);
    }
    return Optional.empty();
  }

  @Override
  public int countStudentsByCourse(long id) {
    return repository.countStudentCourseByCourseId(id);
  }
}
