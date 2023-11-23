package com.tp.infrastructure.domain.client;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tp.infrastructure.domain.incident.Incident;

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
@Table(name = "client")
@Entity
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column
  private UUID client_id;

  @Column
  private String cuit;

  @Column
  private String service;

  @Column
  private String bussiness_name;

  @OneToMany(mappedBy = "client")
  private List<Incident> incidents;
}
