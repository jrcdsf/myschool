package com.myschool.demo.myschool.core.usecases;

import com.myschool.demo.myschool.core.boundaries.CourseServiceInterface;
import com.myschool.demo.myschool.core.boundaries.StudentServiceInterface;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.core.exceptions.BusinessException;
import com.myschool.demo.myschool.core.exceptions.MaxNumberOfCourseEnrollmentsException;
import com.myschool.demo.myschool.core.exceptions.MaxNumberOfStudentEnrollmentsException;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentUseCase {

  @Value("${limits.course.students.max}")
  private int courseMaxLimitEnrollments = 50;

  @Value("${limits.student.courses.max}")
  private int studentMaxLimitEnrollments = 5;

  private final CourseServiceInterface courseService;
  private final StudentServiceInterface studentService;

  public EnrollmentUseCase(
      CourseServiceInterface courseService, StudentServiceInterface studentService) {
    this.courseService = courseService;
    this.studentService = studentService;
  }

  public Optional<Student> enrollToCourse(long studentId, long courseId)
      throws BusinessException {
    Optional<Student> student = studentService.findById(studentId);
    if (student.isPresent()) {
      Optional<Course> course = courseService.findById(courseId);
      if (course.isPresent()) {
        if (courseService.countStudentsByCourse(courseId) == courseMaxLimitEnrollments)
          throw new MaxNumberOfStudentEnrollmentsException("Max number of students enrolled reached " + courseMaxLimitEnrollments);
        Set<Course> currentCourses = student.get().getCourses();
        if (currentCourses.size() == studentMaxLimitEnrollments)
          throw new MaxNumberOfCourseEnrollmentsException("Max number of course enrollments reached " + studentMaxLimitEnrollments);
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
