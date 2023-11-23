package com.tp.infrastructure.domain.specialty;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.tp.infrastructure.domain.technical.Technical;
import com.tp.infrastructure.domain.type_problem.TypeProblem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specialty")
@Entity
public class Specialty {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long specialty_id;

  @Column
  private String specialty_name;

  @ManyToMany(mappedBy = "specialties")
  private List<Technical> technicals;

  @ManyToMany(mappedBy = "specialties")
  private List<TypeProblem> typesProblem;
}
