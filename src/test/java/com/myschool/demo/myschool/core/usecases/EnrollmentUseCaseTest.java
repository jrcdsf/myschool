package com.myschool.demo.myschool.core.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.myschool.demo.myschool.common.BaseTest;
import com.myschool.demo.myschool.core.boundaries.CourseServiceInterface;
import com.myschool.demo.myschool.core.boundaries.StudentServiceInterface;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.core.exceptions.BusinessException;
import com.myschool.demo.myschool.core.exceptions.MaxNumberOfCourseEnrollmentsException;
import com.myschool.demo.myschool.core.exceptions.MaxNumberOfStudentEnrollmentsException;
import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class EnrollmentUseCaseTest extends BaseTest {

  @Mock private CourseServiceInterface courseServiceMock;

  @Mock private StudentServiceInterface studentServiceMock;

  private EnrollmentUseCase useCaseMock;

  private final Course course = new Course("C", "Description C");
  private final Student student1 = new Student("Name1", "Birth1", "Gender1");

  private HashSet<Course> courses = new HashSet<>();

  @BeforeEach
  void setUp() {
    useCaseMock = new EnrollmentUseCase(courseServiceMock, studentServiceMock);
    courses.add(course);
    student1.setCourses(courses);
  }

  private void setupForMaxNumberOfCourseEnrollmentsExceptionTest() {
    for (int i = 0; i < 5; i++) {
      courses.add(new Course(String.format("C%s", i), String.format("Description C%s", i)));
    }
  }

  @Test
  void enrollToCourseTest() throws BusinessException {
    when(studentServiceMock.findById(anyLong())).thenReturn(Optional.of(student1));
    when(courseServiceMock.findById(anyLong())).thenReturn(Optional.of(course));
    when(studentServiceMock.save(any())).thenReturn(student1);

    Optional<Student> actual = useCaseMock.enrollToCourse(student1.getId(), course.getId());

    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(actual.get().getCourses(), courses);
  }

  @Test
  void enrollToCourseThrowsMaxNumberOfCourseEnrollmentsExceptionTest() {
    setupForMaxNumberOfCourseEnrollmentsExceptionTest();
    Course course6 = new Course("C6", "Description C6");
    when(studentServiceMock.findById(anyLong())).thenReturn(Optional.of(student1));
    when(courseServiceMock.findById(anyLong())).thenReturn(Optional.of(course6));

    assertThrows(
        MaxNumberOfCourseEnrollmentsException.class,
        () -> useCaseMock.enrollToCourse(student1.getId(), course6.getId()));
  }

  @Test
  void enrollToCourseMaxNumberOfStudentEnrollmentsExceptionTest() {

    Course course6 = new Course("C6", "Description C6");
    when(studentServiceMock.findById(anyLong())).thenReturn(Optional.of(student1));
    when(courseServiceMock.findById(anyLong())).thenReturn(Optional.of(course6));
    when(courseServiceMock.countStudentsByCourse(anyLong())).thenReturn(50);

    assertThrows(
        MaxNumberOfStudentEnrollmentsException.class,
        () -> useCaseMock.enrollToCourse(student1.getId(), course6.getId()));
  }
}
