package com.tp.infrastructure.domain.technical;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "technical")
@Entity
public class Technical {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID technical_id;

  @Column
  private String technical_name;

  @OneToMany(mappedBy = "technical")
  private List<Incident> incidents;

  @ManyToMany
  @JoinTable(name = "technical__specialty", joinColumns = @JoinColumn(name = "technical_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
  private List<Specialty> specialties;
}
