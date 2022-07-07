package com.myschool.demo.myschool.core.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.myschool.demo.myschool.common.BaseTest;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.infra.services.CourseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class CourseUseCaseTest extends BaseTest {

  @Mock private CourseService serviceMock;

  private CourseUseCase useCaseMock;

  private final Course course1 = new Course("C1", "Description C1");

  private final Course course2 = new Course("C2", "Description C2");

  private List<Course> courseList = new ArrayList<>();

  @BeforeEach
  void setUp() {
    useCaseMock = new CourseUseCase(serviceMock);
    courseList.add(course1);
    courseList.add(course1);
  }

  @Test
  void addCourseTest() {
    when(serviceMock.save(any())).thenReturn(course1);

    Course actual = useCaseMock.addCourse(course1);

    Assertions.assertEquals(course1, actual);
  }

  @Test
  void updateCourseTest() {
    when(serviceMock.findById(anyLong())).thenReturn(Optional.of(course1));
    when(serviceMock.save(any())).thenReturn(course1);

    Optional<Course> actual = useCaseMock.updateCourse(course1);

    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(course1, actual.get());
  }

  @Test
  void listCoursesTest() {
    when(serviceMock.findAll()).thenReturn(courseList);

    List<Course> actual = useCaseMock.listCourses();

    Assertions.assertIterableEquals(courseList, actual);
  }

  @Test
  void deleteCourseTest() {
    when(serviceMock.delete(anyLong())).thenReturn(true);

    boolean response = useCaseMock.deleteCourse(anyLong());

    Assertions.assertTrue(response);
  }

  @Test
  void findCourseByIdTest() {
    when(serviceMock.findById(anyLong())).thenReturn(Optional.of(course1));

    Optional<Course> actual = useCaseMock.findCourse(course1.getId());
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(course1, actual.get());
  }

  @Test
  void findCourseByNameTest() {
    when(serviceMock.findByName(anyString())).thenReturn(Optional.of(course1));

    Optional<Course> actual = useCaseMock.findCourse(course1.getName());
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(course1, actual.get());
  }
}
