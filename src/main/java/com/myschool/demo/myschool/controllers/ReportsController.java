package com.myschool.demo.myschool.controllers;

import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.core.usecases.ReportsUseCase;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportsController {

  @Autowired
  private ReportsUseCase useCase;

  @GetMapping("/students")
  public ResponseEntity<List<Student>> studentReport(@RequestParam(required = true) String course){
    Optional<List<Student>> response = useCase.findStudentsByCourse(course);
    return response.map(students -> new ResponseEntity<>(students, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }



}
