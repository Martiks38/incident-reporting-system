package com.tp.infrastructure.domain.incident;

import java.sql.Date;

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
@Table(name = "incident", schema = "incident")
@Entity
public class Incident {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String incident_id;

  @Column(nullable = false, columnDefinition = "TINYINT(1)")
  private String resolved;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String considerations;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date create_time;

  @Temporal(TemporalType.DATE)
  private Date time_us_up;

  @ManyToOne
  @JoinColumn(name = "fk_incident_technical")
  private Technical technical;

  @ManyToOne
  @JoinColumn(name = "fk_incident_type_problem")
  private TypeProblem typeProblem;

  @ManyToOne
  @JoinColumn(name = "fk_incident_client")
  private Client client;
}
