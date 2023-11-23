package com.tp.infrastructure.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory(String dbUrl, String dbUsername, String dbPassword) {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.connection.url", dbUrl);
        configuration.setProperty("hibernate.connection.username", dbUsername);
        configuration.setProperty("hibernate.connection.password", dbPassword);

        sessionFactory = configuration.buildSessionFactory();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return sessionFactory;
  }

  public static void shutdown() {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }
}
