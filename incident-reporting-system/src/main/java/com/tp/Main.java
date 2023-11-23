package com.tp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

// import org.hibernate.SessionFactory;

// import com.tp.infrastructure.configuration.HibernateUtil;

public class Main {
    // private static final String mySqldbUrl = "jdbc:mysql://localhost:3306/port";
    // private static final String mySqldbUrl =
    // "jdbc:mysql://localhost:3306/incident-reporting-system";
    // private static final String mySqldbUsername = "root";
    // private static final String mySqldbPassword = "1234";

    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("user_test");
        EntityManager manager = factory.createEntityManager();

        return manager;
    }

    public static void main(String[] args) {
        // SessionFactory mysqlSessionFactory =
        // HibernateUtil.getSessionFactory(mySqldbUrl, mySqldbUsername,
        // mySqldbPassword);

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // User u = new User("Martiks", "pako@outlok.com", "1234", "jigaipojgaapdg
        // aijgpadg");
        // name = user.getEmail();
        // User user = new User();

        // em.persist(u);
        tx.commit();

        // System.out.println(user);

        // mysqlSessionFactory.close();
    }
}