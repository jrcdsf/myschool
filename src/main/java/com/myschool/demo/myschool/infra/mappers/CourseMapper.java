package com.myschool.demo.myschool.infra.mappers;

import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.infra.entities.CourseDto;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

  CourseMapper MAPPER = Mappers.getMapper(CourseMapper.class);

  Course toCourse(CourseDto courseDto, @Context CycleAvoidingMappingContext context);

  @InheritInverseConfiguration
  CourseDto toCourseDto(Course course, @Context CycleAvoidingMappingContext context);
}
