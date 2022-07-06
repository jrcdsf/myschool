package com.myschool.demo.myschool.infra.repositories;

import com.myschool.demo.myschool.infra.entities.StudentDto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDto, Long> {
  Optional<List<StudentDto>> findByName(String name);

  @Query(value = "SELECT s from StudentDto s inner join s.courses c where c.id =:id")
  Optional<List<StudentDto>> findByCourseId(Long id);

  @Query(value = "select s.* from student s left join student_course sc on sc.student_id = s.id where sc.student_id is null", nativeQuery = true)
  Optional<List<StudentDto>> findStudentsWithoutEnrollments();

  @Query(value = "delete from student_course sc where sc.student_id = ?1", nativeQuery = true)
  @Modifying
  @Transactional
  void deleteStudentCourseByStudentId(long studentId);

}
