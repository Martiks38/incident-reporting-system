package com.tp.infrastructure.domain.technical;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.tp.infrastructure.domain.incident.Incident;
import com.tp.infrastructure.domain.specialty.Specialty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "technical", schema = "technical")
@Entity
public class Technical {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID technical_id;

  @Column(nullable = false)
  private String technical_name;

  @Column(nullable = false)
  private String means_notification;

  @Column(nullable = false)
  private int number_incidents_resolved;

  @Column(nullable = false)
  private Long incident_resolution_speed;

  @OneToMany(mappedBy = "technical")
  private List<Incident> incidents;

  @ManyToMany
  @JoinTable(name = "technical__specialty", joinColumns = @JoinColumn(name = "fk_ts_technical"), inverseJoinColumns = @JoinColumn(name = "fk_ts_specialty"))
  private List<Specialty> specialties;
}
