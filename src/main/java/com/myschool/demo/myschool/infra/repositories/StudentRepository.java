package com.myschool.demo.myschool.infra.repositories;

import com.myschool.demo.myschool.infra.entities.StudentDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDto, Long> {
  Optional<List<StudentDto>> findByName(String name);

  @Query(value = "SELECT student_id from student_course where course_id = ?1", nativeQuery=true)
  Optional<List<StudentDto>> findByCourseId(Long id);

}
