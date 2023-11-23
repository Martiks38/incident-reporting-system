package com.tp.infrastructure.domain.incident;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.tp.infrastructure.domain.client.Client;
import com.tp.infrastructure.domain.technical.Technical;
import com.tp.infrastructure.domain.type_problem.TypeProblem;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "incident")
@Entity
public class Incident {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column
  private UUID incident_id;

  @Column
  private String considerations;

  @Column(nullable = false, columnDefinition = "TINYINT(1)")
  private String resolved;

  @Column
  @Temporal(TemporalType.DATE)
  private Date create_time;

  @Column
  private String description;

  @ManyToOne
  @JoinColumn(name = "technical_id")
  private Technical technical;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "type_problem")
  private TypeProblem typeProblem;
}
