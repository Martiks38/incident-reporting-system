package com.tp.infrastructure.domain.type_problem;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.tp.infrastructure.domain.incident.Incident;
import com.tp.infrastructure.domain.specialty.Specialty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type_problem", schema = "type_problem")
@Entity
public class TypeProblem {
  @Id
  @Column(nullable = false, name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long type_problem_id;

  @Column(nullable = false)
  private String type_problem_name;

  @Column(nullable = false)
  private Long maximum_resolution_time;

  @Column(nullable = false)
  private Long stimated_resolution_time;

  @ManyToMany
  @JoinTable(name = "type_problem__specialty", joinColumns = @JoinColumn(name = "fk_tps_type_problem"), inverseJoinColumns = @JoinColumn(name = "fk_tps_specialty"))
  private List<Specialty> specialties;

  @OneToMany(mappedBy = "typeProblem")
  private List<Incident> incidents;
}
