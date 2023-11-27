package com.tp.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class GetEntityManager {
  private final String persistenceUnitName= "user_test";

  private static GetEntityManager instance;
  private static EntityManager manager;

  private GetEntityManager() {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);

    manager = factory.createEntityManager();
  }

  public static EntityManager getManager(){
    if(instance == null){
      instance = new GetEntityManager();
    }

    return manager;
  }
}
