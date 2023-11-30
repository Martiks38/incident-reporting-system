package com.tp.infrastructure.domain.incident;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "incident", schema = "incident")
@Entity
public class Incident {
  @Id
  @Getter
  @Column(name = "id", nullable = false, length = 36, insertable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String incident_id;

  @Getter
  @Setter
  @Column(nullable = false, columnDefinition = "TINYINT(1)")
  private String resolved;

  @Getter
  @Setter
  @Column(nullable = false)
  private String description;

  @Getter
  @Setter
  @Column(nullable = false)
  private String considerations;

  @Getter
  @Setter
  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date create_time;

  @Getter
  @Setter
  @Temporal(TemporalType.DATE)
  private Date time_us_up;

  @Getter
  @Setter
  @ManyToOne
  @JoinColumn(name = "fk_incident_technical")
  private Technical technical;

  @Getter
  @Setter
  @ManyToOne
  @JoinColumn(name = "fk_incident_client")
  private Client client;

  @Getter
  @Setter
  @ManyToMany
  @JoinTable(name = "incident__type_problem", joinColumns = @JoinColumn(name = "fk_itp_problem", nullable = false), inverseJoinColumns = @JoinColumn(name = "fk_itp_incident", nullable = false))
  private List<TypeProblem> incident_type_problem;
}
