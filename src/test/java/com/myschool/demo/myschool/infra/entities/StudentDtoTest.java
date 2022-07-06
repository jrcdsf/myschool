package com.myschool.demo.myschool.infra.entities;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsStringIgnoringCase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class StudentDtoTest {

  @Test
  void givenBidirectionRelation_whenUsingJsonIdentityInfo_thenCorrect()
      throws JsonProcessingException {

    HashSet<CourseDto> list = new HashSet<>();
    StudentDto studentDto = new StudentDto();
    studentDto.setId(1L);
    studentDto.setName("JR");
    studentDto.setBirth("25/03/1975");
    studentDto.setGender("M");

    CourseDto courseDto = new CourseDto();
    courseDto.setId(1L);
    courseDto.setName("course 001");
    courseDto.setDescription("long description");

    list.add(courseDto);
    studentDto.setCourses(list);

    String result = new ObjectMapper().writeValueAsString(studentDto);
    assertThat(result, containsStringIgnoringCase("JR"));
    assertThat(result, containsStringIgnoringCase("course 001"));
    System.out.println(result);
    }
  }
