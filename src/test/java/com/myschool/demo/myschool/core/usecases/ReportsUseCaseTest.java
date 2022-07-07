package com.myschool.demo.myschool.core.usecases;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.myschool.demo.myschool.common.BaseTest;
import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.infra.services.CourseService;
import com.myschool.demo.myschool.infra.services.StudentCourseService;
import com.myschool.demo.myschool.infra.services.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ReportsUseCaseTest extends BaseTest {

  @Mock private StudentService studentServiceMock;

  @Mock private CourseService courseServiceMock;

  @Mock private StudentCourseService studentCourseServiceMock;

  private ReportsUseCase useCaseMock;

  private Optional<List<Student>> optionalStudentList;
  private List<Student> studentList = new ArrayList<>();
  private Optional<Course> optionalCourse;
  private Course course;
  private Optional<List<Course>> optionalCourseList;
  private List<Course> courseList = new ArrayList<>();
  private Optional<Student> optionalStudent;

  private Student student1;
  private Student student2;

  @BeforeEach
  void setUp() {
    useCaseMock =
        new ReportsUseCase(studentServiceMock, courseServiceMock, studentCourseServiceMock);
    course = new Course("C1", "Description C1");
    optionalCourse = Optional.of(course);
    student1 = new Student("Name 1", "Birth 1", "Gender 1");
    student2 = new Student("Name 2", "Birth 2", "Gender 2");
    studentList.add(student1);
    studentList.add(student2);
    optionalStudent = Optional.of(student1);
    optionalStudentList = Optional.of(studentList);
    courseList.add(course);
    optionalCourseList = Optional.of(courseList);
  }

  @Test
  void findStudentsWithoutEnrollmentsTest() {
    when(studentCourseServiceMock.findStudentsWithoutEnrollments()).thenReturn(optionalStudentList);
    Optional<List<Student>> actual = useCaseMock.findStudentsByCourse("none");
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertTrue(optionalStudentList.isPresent());
    Assertions.assertIterableEquals(optionalStudentList.get(), actual.get());
  }

  @Test
  void findStudentsByCourseIdTest() {
    when(studentCourseServiceMock.findStudentsByCourseId(course.getId()))
        .thenReturn(optionalStudentList);
    when(courseServiceMock.findByName(anyString())).thenReturn(optionalCourse);
    Optional<List<Student>> actual = useCaseMock.findStudentsByCourse("COURSE 001");
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertTrue(optionalStudentList.isPresent());
    Assertions.assertIterableEquals(optionalStudentList.get(), actual.get());
  }

  @Test
  void findCoursesWithoutEnrollmentsTest() {
    when(studentCourseServiceMock.findCoursesWithoutEnrollments()).thenReturn(optionalCourseList);

    Optional<List<Course>> actual = useCaseMock.findCoursesByStudent(-1);
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertTrue(optionalCourseList.isPresent());
    Assertions.assertIterableEquals(optionalCourseList.get(), actual.get());
  }

  @Test
  void findCoursesByStudentIdTest() {
    when(studentCourseServiceMock.findCoursesByStudentId(1)).thenReturn(optionalCourseList);
    when(studentServiceMock.findById(1)).thenReturn(optionalStudent);

    Optional<List<Course>> actual = useCaseMock.findCoursesByStudent(1);
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertTrue(optionalCourseList.isPresent());
    Assertions.assertIterableEquals(optionalCourseList.get(), actual.get());
  }
}
