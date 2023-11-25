package com.tp.infrastructure.domain.client;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.tp.infrastructure.domain.incident.Incident;
import com.tp.infrastructure.domain.service.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client", schema = "client")
@Entity
public class Client {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID client_id;

  @Column(nullable = false)
  private String cuit;

  @Column(nullable = false)
  private String bussiness_name;

  @OneToMany(mappedBy = "client")
  private List<Incident> incidents;

  @ManyToOne
  @JoinColumn(name = "service_id", referencedColumnName = "id")
  private Service service;
}
