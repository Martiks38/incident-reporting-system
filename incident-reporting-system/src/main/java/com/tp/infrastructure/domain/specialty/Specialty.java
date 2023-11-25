package com.tp.infrastructure.domain.specialty;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

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
@Table(name = "specialty", schema = "specialty")
@Entity
public class Specialty {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long specialty_id;

  @Column(nullable = false)
  private String specialty_name;

  @ManyToMany(mappedBy = "specialties")
  private List<Technical> technicals;

  @ManyToMany(mappedBy = "specialties")
  private List<TypeProblem> typesProblem;
}
