package com.myschool.demo.myschool.infra.services;

import com.myschool.demo.myschool.core.boundaries.StudentCourseServiceInterface;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.infra.entities.CourseDto;
import com.myschool.demo.myschool.infra.entities.StudentDto;
import com.myschool.demo.myschool.infra.mappers.CourseMapper;
import com.myschool.demo.myschool.infra.mappers.CycleAvoidingMappingContext;
import com.myschool.demo.myschool.infra.mappers.StudentMapper;
import com.myschool.demo.myschool.infra.repositories.CourseRepository;
import com.myschool.demo.myschool.infra.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService implements StudentCourseServiceInterface {

  private final StudentRepository studentRepository;

  private final CourseRepository courseRepository;
  private final StudentMapper studentMapper;

  private final CourseMapper courseMapper;

  public StudentCourseService(StudentRepository studentRepository,
      CourseRepository courseRepository,
      StudentMapper studentMapper, CourseMapper courseMapper) {
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
    this.studentMapper = studentMapper;
    this.courseMapper = courseMapper;
  }

  @Override
  public Optional<List<Student>> findStudentsByCourseId(long id) {
    Optional<List<StudentDto>> students = studentRepository.findByCourseId(
        id);
    return students.map(studentDtos -> studentDtos.stream()
        .map(s -> studentMapper.toStudent(s, new CycleAvoidingMappingContext())).collect(
            Collectors.toList()));
  }

  @Override
  public Optional<List<Course>> findCoursesByStudentId(long id) {
    Optional<List<CourseDto>> courses = courseRepository.findByStudentId(
        id);
    return courses.map(courseDtos -> courseDtos.stream().map(c -> courseMapper.toCourse(c, new CycleAvoidingMappingContext())).collect(
        Collectors.toList()));
  }

  @Override
  public Optional<List<Student>> findStudentsWithoutEnrollments() {
    Optional<List<StudentDto>> students = studentRepository.findStudentsWithoutEnrollments();
    return students.map(studentDtos -> studentDtos.stream()
        .map(s -> studentMapper.toStudent(s, new CycleAvoidingMappingContext())).collect(
            Collectors.toList()));
  }

  @Override
  public Optional<List<Course>> findCoursesWithoutEnrollments() {
    Optional<List<CourseDto>> students = courseRepository.findCoursesWithoutEnrollments();
    return students.map(courseDtos -> courseDtos.stream()
        .map(c -> courseMapper.toCourse(c, new CycleAvoidingMappingContext())).collect(
            Collectors.toList()));
  }
}
