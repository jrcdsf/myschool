package com.myschool.demo.myschool.core.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.myschool.demo.myschool.core.entities.Student;
import com.myschool.demo.myschool.infra.services.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentUseCaseTest {

  @Mock
  private StudentService serviceMock;

  private StudentUseCase useCaseMock;

  private final Student student1 = new Student("Name1","Birth1","Gender1");
  private final Student student2 = new Student("Name2","Birth2","Gender2");

  private List<Student> studentList = new ArrayList<>();

  @BeforeEach
  void setUp() {
    useCaseMock = new StudentUseCase(serviceMock);
    studentList.add(student1);
    studentList.add(student2);
  }

  @Test
  void addStudentTest() {

    when(serviceMock.save(any())).thenReturn(student1);

    Student actual = useCaseMock.addStudent(student1);

    Assertions.assertEquals(student1, actual);
  }

  @Test
  void updateStudent() {
    when(serviceMock.findById(anyLong())).thenReturn(Optional.of(student1));
    when(serviceMock.save(any())).thenReturn(student1);

    Optional<Student> actual = useCaseMock.updateStudent(student1);

    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(student1, actual.get());
  }

  @Test
  void listStudents() {
    when(serviceMock.findAll()).thenReturn(studentList);

    List<Student> actual = useCaseMock.listStudents();

    Assertions.assertIterableEquals(studentList, actual);
  }

  @Test
  void deleteStudent() {
    when(serviceMock.delete(anyLong())).thenReturn(true);

    boolean response = useCaseMock.deleteStudent(anyLong());

    Assertions.assertTrue(response);
  }

  @Test
  void findStudentById() {
    when(serviceMock.findById(anyLong())).thenReturn(Optional.of(student1));

    Optional<Student> actual = useCaseMock.findStudent(student1.getId());
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(student1, actual.get());

  }

  @Test
  void findStudentByName() {
    when(serviceMock.findByName(anyString())).thenReturn(Optional.of(student1));

    Optional<Student> actual = useCaseMock.findStudent(student1.getName());
    Assertions.assertTrue(actual.isPresent());
    Assertions.assertEquals(student1, actual.get());

  }
}