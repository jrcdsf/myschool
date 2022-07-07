package com.myschool.demo.myschool.infra.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "student")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StudentDto implements Serializable {

  private static final long serialVersionUID = 4697620279185292652L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "student_id_seq")
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "birth", nullable = false)
  private String birth;

  @Column(name = "gender", nullable = false)
  private String gender;

  @ManyToMany(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST})
  @JoinTable(
      name = "student_course",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private Set<CourseDto> courses = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    StudentDto that = (StudentDto) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
