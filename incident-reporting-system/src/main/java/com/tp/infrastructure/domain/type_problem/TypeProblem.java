package com.tp.infrastructure.domain.type_problem;

import java.sql.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
@Table(name = "type_problem")
@Entity
public class TypeProblem {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long type_problem_id;

  @Column
  private String type_problem_name;

  @Column
  @Temporal(TemporalType.DATE)
  private Date resolution_time;

  @ManyToMany
  @JoinTable(name = "type_problem__specialty", joinColumns = @JoinColumn(name = "type_problem_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
  private List<Specialty> specialties;

  @OneToMany(mappedBy = "type_problem")
  private List<Incident> incidents;
}
