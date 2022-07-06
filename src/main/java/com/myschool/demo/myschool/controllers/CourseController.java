package com.myschool.demo.myschool.controllers;

import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.usecases.CourseUseCase;
import com.myschool.demo.myschool.infra.models.CreateCourseRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

  @Autowired
  private CourseUseCase useCase;

  @PostMapping
  @ResponseBody
  public Course createCourse(@RequestBody CreateCourseRequest request) {
    return useCase.addCourse(new Course(request.getName(),request.getDescription()));
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<Course>> listCourses(@RequestParam(required = false, defaultValue = "") String name) {
    if (name.isEmpty()) {
      List<Course> response = useCase.listCourses();
      return new ResponseEntity<>(response, HttpStatus.OK);
      }
    else {
      Optional<List<Course>> response = useCase.findCourse(name);
      return response
          .map(course -> new ResponseEntity<>(course, HttpStatus.OK))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  public ResponseEntity<Course> findCourseById(@PathVariable long id) {

      Optional<Course> response = useCase.findCourse(id);
      return response
          .map(course -> new ResponseEntity<>(course, HttpStatus.OK))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Long> deleteCourse(@PathVariable long id) {
    if (useCase.deleteCourse(id))
      return new ResponseEntity<>(id, HttpStatus.OK);
    else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
