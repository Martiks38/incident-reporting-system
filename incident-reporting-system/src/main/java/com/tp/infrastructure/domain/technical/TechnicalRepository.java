package com.tp.infrastructure.domain.technical;

import java.util.List;

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

      String t_name = technical.getTechnical_name();
      String t_media = technical.getMeans_notification();
      int number_incidents_resolved = technical.getNumber_incidents_resolved();
      Long incident_resolution_speed = technical.getIncident_resolution_speed();
      
      if(t_name != null){
        technical.setTechnical_name(t_name);
      }

      if(t_media == null){
        technical.setMeans_notification(t_media);
      }

      if(number_incidents_resolved < 0){
        technical.setNumber_incidents_resolved(number_incidents_resolved);
      }

      if(incident_resolution_speed < 0){
        technical.setIncident_resolution_speed(incident_resolution_speed);
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
