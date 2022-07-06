package com.myschool.demo.myschool.infra.repositories;

import com.myschool.demo.myschool.infra.entities.CourseDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseDto, Long> {
  Optional<List<CourseDto>> findByName(String name);
}
