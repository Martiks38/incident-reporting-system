package com.tp.infrastructure.domain.service;

import java.util.List;

import com.tp.infrastructure.domain.client.Client;
import com.tp.infrastructure.domain.type_problem.TypeProblem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service", schema = "service")
@Entity
public class Service {
  @Id
  @Getter
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long service_id;

  @Getter
  @Setter
  @Column(nullable = false, unique = true, length = 45)
  private String service_name;

  @Getter
  @Setter
  @ManyToMany(mappedBy = "client_services")
  private List<Client> clients;

  @Getter
  @Setter
  @ManyToMany(mappedBy = "type_problem_services")
  private List<TypeProblem> typesProblem;
}
