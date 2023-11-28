package com.tp.infrastructure.domain.technical;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.tp.infrastructure.domain.specialty.Specialty;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TechnicalRepository implements TechnicalDAO{

  static EntityManager manager;

  public TechnicalRepository(EntityManager mg){
    manager = mg;
  }

  @Override
  public Technical find(String id) {
    Technical technical = manager.find(Technical.class, id);

    return technical;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Technical> findAll() {
    List<Technical> technicals = (List<Technical>) manager.createQuery("FROM Technical").getResultList();

    return technicals;
  }

  @Override
  public void save(Technical data) {
    EntityTransaction transaction = manager.getTransaction();

    try {
      transaction.begin();
      
      TechnicalCheckData.check(data);

      manager.persist(data);

      transaction.commit();
    } catch (Exception e) {
      if(transaction != null && transaction.isActive()){
        transaction.rollback();
      }

      // Para ver la traza pero se debería borrar y enviar la traza a un archivo o una base de datos que almacene los errores
      e.printStackTrace();
      System.err.println("Error en la transacción: " + e.getMessage());
    }
  }

  @Override
  public void update(String id, Technical data) {
    EntityTransaction transaction = manager.getTransaction();

    try {
      transaction.begin();

      Technical technical = this.find(id);

      if(technical == null){
        throw new RuntimeException("No se ha encontrado el técnico para actualizar sus datos.");
      }

      String t_name = data.getTechnical_name();
      String t_media = data.getMeans_notification();
      Integer t_number_incidents_resolved = data.getNumber_incidents_resolved();
      Long t_incident_resolution_speed = data.getIncident_resolution_speed();
      List<Specialty> t_specialties = data.getSpecialties();

      if(t_name != null){
        technical.setTechnical_name(t_name);
      }

      if(t_media != null){
        technical.setMeans_notification(t_media);
      }

      if(t_number_incidents_resolved != null){
        technical.setNumber_incidents_resolved(t_number_incidents_resolved);
      }

      if(t_incident_resolution_speed != null){
        technical.setIncident_resolution_speed(t_incident_resolution_speed);
      }

      if(t_specialties != null && t_specialties.size() != 0){
        List<String> specialties_name = technical
          .getSpecialties().stream()
          .map(s -> s.getSpecialty_name())
          .collect(Collectors.toList());
        
        // Set<String> currentSpecialties = new HashMap<>(specialties_name);
        
        // List<String> newSpecialtiesList = 

      }

      manager.persist(technical);

      transaction.commit();
    } catch (Exception e) {
      if(transaction != null && transaction.isActive()){
        transaction.rollback();
      }

      // Para ver la traza pero se debería borrar y enviar la traza a un archivo o una base de datos que almacene los errores
      e.printStackTrace();
      System.err.println("Error en la transacción: " + e.getMessage());
    }
  }

  @Override
  public void delete(String id) {
    EntityTransaction transaction = manager.getTransaction();

    try {
      transaction.begin();

      Technical t = manager.find(Technical.class, id);
      
      manager.remove(t);

      transaction.commit();
    } catch (Exception e) {
      if(transaction != null && transaction.isActive()){
        transaction.rollback();
      }

      // Para ver la traza pero se debería borrar y enviar la traza a un archivo o una base de datos que almacene los errores
      e.printStackTrace();
      System.err.println("Error en la transacción: " + e.getMessage());
    }
  }
}
