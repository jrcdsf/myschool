package com.myschool.demo.myschool.core.boundaries;

import com.myschool.demo.myschool.core.entities.Course;
import com.myschool.demo.myschool.core.entities.Student;
import java.util.List;
import java.util.Optional;

public interface StudentCourseServiceInterface {

  Optional<List<Student>> findStudentsByCourseId(long id);
  Optional<List<Course>> findCoursesByStudentId(long id);
  Optional<List<Student>> findStudentsWithoutEnrollments();
  Optional<List<Course>> findCoursesWithoutEnrollments();

}
