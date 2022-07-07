package com.myschool.demo.myschool.infra.repositories;

import com.myschool.demo.myschool.infra.entities.CourseDto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseDto, Long> {
  Optional<CourseDto> findByName(String name);

  @Query(value = "select * from course c where c.id in (select sc.course_id from student_course sc where sc.student_id = ?1)", nativeQuery = true)
  Optional<List<CourseDto>> findByStudentId(long id);

  @Query(value = "select c.* from course c left join student_course sc on sc.course_id = c.id where sc.course_id is null", nativeQuery = true)
  Optional<List<CourseDto>> findCoursesWithoutEnrollments();

  @Query(value = "delete from student_course sc where sc.course_id = ?1", nativeQuery = true)
  @Modifying
  @Transactional
  void deleteStudentCourseByCourseId(long courseId);

  @Query(value = "select count(*) from student_course sc where sc.course_id = ?1", nativeQuery = true)
  int countStudentCourseByCourseId(long courseId);
}
