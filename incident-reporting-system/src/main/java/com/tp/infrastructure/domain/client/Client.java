package com.tp.infrastructure.domain.client;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.tp.infrastructure.domain.incident.Incident;
import com.tp.infrastructure.domain.service.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client", schema = "client")
@Entity
public class Client {
  @Id
  @Getter
  @Column(name = "id", nullable = false, length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String client_id;

  @Getter
  @Setter
  @Column(nullable = false, length = 11)
  private String cuit;
  
  @Getter
  @Setter
  @Column(nullable = false, length = 80)
  private String business_name;
  
  @Getter
  @Setter
  @Column(nullable = false, length = 45)
  private String mail;
  
  @Getter
  @OneToMany(mappedBy = "client")
  private List<Incident> incidents;
  
  @Getter
  @Setter
  @ManyToMany
  @JoinTable(name = "client__service", joinColumns = @JoinColumn(name = "fk_cs_service", nullable = false), inverseJoinColumns = @JoinColumn(name = "fk_cs_client", nullable = false))
  private List<Service> client_services;
}
