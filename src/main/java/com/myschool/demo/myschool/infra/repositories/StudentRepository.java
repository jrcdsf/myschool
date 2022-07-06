package com.myschool.demo.myschool.infra.repositories;

import com.myschool.demo.myschool.infra.entities.StudentDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDto, Long> {
  Optional<List<StudentDto>> findByName(String name);
}
