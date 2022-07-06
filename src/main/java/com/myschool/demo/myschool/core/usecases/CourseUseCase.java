package com.myschool.demo.myschool.core.usecases;

import com.myschool.demo.myschool.core.boundaries.CourseServiceInterface;
import com.myschool.demo.myschool.core.entities.Course;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CourseUseCase {

  private final CourseServiceInterface service;

  public CourseUseCase(CourseServiceInterface service) {
    this.service = service;
  }

  public Course addCourse(Course course) {
    return service.save(course);
  }

  public Optional<Course> updateCourse(Course course) {
    Optional<Course> retrieved = service.findById(course.getId());
    if (retrieved.isPresent()) {
      return Optional.of(service.save(course));
    }
    return Optional.empty();
  }

  public List<Course> listCourses(){
    return service.findAll();
  }

  public boolean deleteCourse(long id) {
    return service.delete(id);
  }

  public Optional<Course> findCourse(long id) {
    return service.findById(id);
  }

  public Optional<List<Course>> findCourse(String name) {
    return service.findByName(name);
  }

}
