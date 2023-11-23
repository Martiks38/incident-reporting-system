package com.tp.infrastructure.domain.type_problem;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
