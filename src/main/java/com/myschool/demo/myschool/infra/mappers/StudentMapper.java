package com.myschool.demo.myschool.infra.mappers;

import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.infra.entities.StudentDto;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);
  Student toStudent(StudentDto studentDto, @Context CycleAvoidingMappingContext context);

  @InheritInverseConfiguration
  StudentDto toStudentDto(Student student, @Context CycleAvoidingMappingContext context);
}
