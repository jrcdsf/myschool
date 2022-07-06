package com.myschool.demo.myschool.infra.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.infra.entities.CourseDto;
import com.myschool.demo.myschool.infra.entities.StudentDto;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class StudentMapperTest {

  @Test
  void testMapDtoToEntity() {

    StudentDto studentDto = new StudentDto();
    studentDto.setId(1L);
    studentDto.setName("JR");
    studentDto.setBirth("25/03/1975");
    studentDto.setGender("M");

    HashSet<CourseDto> list = new HashSet<>();
    CourseDto courseDto = new CourseDto();
    courseDto.setId(1L);
    courseDto.setName("course 001");
    courseDto.setDescription("long description");

    list.add(courseDto);
    studentDto.setCourses(list);

    Student student = StudentMapper.MAPPER.toStudent(studentDto, new CycleAvoidingMappingContext());
    assertThat(student).isNotNull();
    Set<Course> courses = student.getCourses();
    assertThat(courses).isNotNull().hasSize(1);
    System.out.println(student);
  }

  @Test
  void testMapEntityToDto(){
    Student student = new Student();
    student.setId(1L);
    student.setName("JR");
    student.setBirth("25/03/1975");
    student.setGender("M");

    HashSet<Course> list = new HashSet<>();
    Course course = new Course();
    course.setId(1L);
    course.setName("course 001");
    course.setDescription("long description");

    list.add(course);
    student.setCourses(list);

    StudentDto studentDto = StudentMapper.MAPPER.toStudentDto(student, new CycleAvoidingMappingContext());
    assertThat(studentDto).isNotNull();
    Set<CourseDto> coursesDto = studentDto.getCourses();
    assertThat(coursesDto).isNotNull().hasSize(1);
    System.out.println(student);


  }

}