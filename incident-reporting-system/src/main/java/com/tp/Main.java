package com.tp;

// import java.util.List;
// import java.util.UUID;

import com.tp.application.GetEntityManager;
// import com.tp.infrastructure.domain.client.Client;
import com.tp.infrastructure.domain.notificationMedium.NotificationMediumRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Main {

    public static void main(String[] args) {
        EntityManager manager = GetEntityManager.getManager();
        
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            // Client c = new Client(UUID.randomUUID().toString(), "11991343226", "Nuka Cola", null, null);
        
            // System.out.println(manager.createQuery("FROM Client"));
            
            // manager.persist(c);

            // System.out.println("\n");


            // List<Client> clients = manager.createQuery("FROM Client", Client.class).getResultList();

            // for (Client client : clients) {
            //     System.out.println(client);
            // }    

            // Client c = new Client(UUID.randomUUID().toString(), "12345678901", "Nuka Cola S.A", "contact@nukacola.com", null, null);
            
            // Usa merge para fusionar la entidad o persistirla si es nueva
            // c = manager.merge(c);

            // System.out.println(manager.createQuery("FROM Client"));

            System.out.println(new NotificationMediumRepository(manager).findAll());

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace(); // o manejar de otra manera
        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
    }
}