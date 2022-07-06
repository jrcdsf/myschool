package com.myschool.demo.myschool.core.usecases;

import com.myschool.demo.myschool.core.boundaries.CourseServiceInterface;
import com.myschool.demo.myschool.core.boundaries.StudentCourseServiceInterface;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ReportsUseCase {

  private final CourseServiceInterface courseService;
  private final StudentCourseServiceInterface studentCourseService;

  public ReportsUseCase(CourseServiceInterface courseService,
      StudentCourseServiceInterface studentCourseService) {
    this.courseService = courseService;
    this.studentCourseService = studentCourseService;
  }

  public Optional<List<Student>> findStudentsByCourse(String course) {
    if (course.equalsIgnoreCase("none")) {
      return studentCourseService.findStudentsWithoutEnrollments();
    }
    Optional<Course> retrievedCourse = courseService.findByName(course);
    if (retrievedCourse.isPresent()){
      return studentCourseService.findStudentsByCourseId(retrievedCourse.get().getId());
    }
    return Optional.empty();
  }
}
