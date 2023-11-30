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
import jakarta.persistence.Table;

import com.tp.infrastructure.domain.incident.Incident;
import com.tp.infrastructure.domain.service.Service;
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
  @Getter
  @Column(nullable = false, name = "id", insertable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long type_problem_id;

  @Getter
  @Setter
  @Column(nullable = false, length = 45)
  private String type_problem_name;

  @Getter
  @Setter
  @Column(nullable = false)
  private Long maximum_resolution_time;

  @Getter
  @Setter
  @Column(nullable = false)
  private Long estimated_resolution_time;

  @Getter
  @Setter
  @ManyToMany
  @JoinTable(name = "type_problem__specialty", joinColumns = @JoinColumn(name = "fk_tps_type_problem"), inverseJoinColumns = @JoinColumn(name = "fk_tps_specialty"))
  private List<Specialty> specialties;

  @Getter
  @Setter
  @ManyToMany(mappedBy = "incident_type_problem")
  private List<Incident> incidents;

  @Getter
  @Setter
  @ManyToMany
  @JoinTable(name = "service__type_problem", joinColumns = @JoinColumn(name = "fk_stp_service", nullable = false), inverseJoinColumns = @JoinColumn(name = "fk_stp_type_problem", nullable = false))
  private List<Service> type_problem_services;
}
