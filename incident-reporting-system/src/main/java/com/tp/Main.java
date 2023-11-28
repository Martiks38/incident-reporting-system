package com.tp;

import com.tp.application.GetEntityManager;

import jakarta.persistence.EntityManager;
// import jakarta.persistence.EntityTransaction;

public class Main {

    public static void main(String[] args) {
        EntityManager manager = GetEntityManager.getManager();
        
        // Funcionar funciona me molesta el ruido en consola

        // EntityTransaction tx = manager.getTransaction();

        // tx.begin();

        // name = user.getEmail();
        // User user = new User();

        // em.persist(u);
        // tx.commit();
        System.out.println("null");
    }
}