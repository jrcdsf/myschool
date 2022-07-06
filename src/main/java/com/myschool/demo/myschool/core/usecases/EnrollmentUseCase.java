package com.myschool.demo.myschool.core.usecases;

import com.myschool.demo.myschool.core.boundaries.CourseServiceInterface;
import com.myschool.demo.myschool.core.boundaries.StudentServiceInterface;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentUseCase {

  private final CourseServiceInterface courseService;
  private final StudentServiceInterface studentService;

  public EnrollmentUseCase(
      CourseServiceInterface courseService, StudentServiceInterface studentService) {
    this.courseService = courseService;
    this.studentService = studentService;
  }

  public Optional<Student> enrollToCourse(long studentId, long courseId) {
    Optional<Student> student = studentService.findById(studentId);
    if (student.isPresent()) {
      Optional<Course> course = courseService.findById(courseId);
      if (course.isPresent()) {
        Set<Course> currentCourses = student.get().getCourses();
        if (currentCourses.stream().filter(c -> c.getId() == courseId).findFirst().orElse(null)
            == null) {
          currentCourses.add(course.get());
          Student retrieved = student.get();
          retrieved.setCourses(currentCourses);
          studentService.save(retrieved);
          return student;
        }
      }
    }
    return Optional.empty();
  }
}
