package com.myschool.demo.myschool.controllers;

import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.core.usecases.StudentUseCase;
import com.myschool.demo.myschool.infra.models.CreateStudentRequest;
import com.myschool.demo.myschool.infra.models.UpdateStudentRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private StudentUseCase useCase;

  @PostMapping
  @ResponseBody
  public Student createStudent(@RequestBody CreateStudentRequest request) {
    return useCase.addStudent(
        new Student(request.getName(), request.getBirth(), request.getGender()));
  }

  @PatchMapping
  @ResponseBody
  public ResponseEntity<Student> updateStudent(@RequestBody UpdateStudentRequest request){
    Optional<Student> response = useCase.updateStudent(
        new Student(request.getId(), request.getName(), request.getBirth(), request.getGender()));
    return response.map(student -> new ResponseEntity<>(student, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<Student>> listStudents(@RequestParam(required = false, defaultValue = "") String name) {
    if (name.isEmpty()) {
      List<Student> response = useCase.listStudents();
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    else {
      Optional<List<Student>> response = useCase.findStudent(name);
      return response
          .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  public ResponseEntity<Student> findStudentById(@PathVariable long id) {

    Optional<Student> response = useCase.findStudent(id);
    return response
        .map(student  -> new ResponseEntity<>(student, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Long> deleteStudent(@PathVariable long id) {
    if (useCase.deleteStudent(id))
      return new ResponseEntity<>(id, HttpStatus.OK);
    else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
