package com.myschool.demo.myschool.controllers;

import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.core.exceptions.BusinessException;
import com.myschool.demo.myschool.core.usecases.EnrollmentUseCase;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

  @Autowired private EnrollmentUseCase useCase;

  @PatchMapping(value = "/student/{studentId}/course/{courseId}")
  public ResponseEntity<Student> enrollStudentToCourse(
      @PathVariable long studentId, @PathVariable long courseId) throws BusinessException {
    Optional<Student> response = useCase.enrollToCourse(studentId, courseId);
    return response
        .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
