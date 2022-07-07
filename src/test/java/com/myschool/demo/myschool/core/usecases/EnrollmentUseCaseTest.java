package com.myschool.demo.myschool.core.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.myschool.demo.myschool.core.boundaries.CourseServiceInterface;
import com.myschool.demo.myschool.core.boundaries.StudentServiceInterface;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.core.exceptions.BusinessException;
import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnrollmentUseCaseTest {

  @Mock
  private  CourseServiceInterface courseServiceMock;

  @Mock
  private StudentServiceInterface studentServiceMock;

  private EnrollmentUseCase useCaseMock;

  private final Course course1 = new Course("C1", "Description C1");
  private final Student student1 = new Student("Name1","Birth1","Gender1");

  private HashSet<Course> courses = new HashSet<>();

  @BeforeEach
  void setUp() {
    useCaseMock = new EnrollmentUseCase(courseServiceMock, studentServiceMock);

    courses.add(course1);
    student1.setCourses(courses);
  }

  @Test
  void enrollToCourseTest() throws BusinessException {
    when(studentServiceMock.findById(anyLong())).thenReturn(Optional.of(student1));
    when(courseServiceMock.findById(anyLong())).thenReturn(Optional.of(course1));
    when(studentServiceMock.save(any())).thenReturn(student1);

    Optional<Student> actual = useCaseMock.enrollToCourse(student1.getId(), course1.getId());

    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(actual.get().getCourses(), courses);
  }
}