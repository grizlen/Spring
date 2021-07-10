package ru.geekbrains.HiberSpringApp.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.HiberSpringApp.entities.Client;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class ClientRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ClientRepository(EntityManagerFactory emf) {
        sessionFactory = emf.unwrap(SessionFactory.class);
    }

    public List<Client> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Client> list = session.createNamedQuery("client.all").getResultList();
            session.getTransaction().commit();
            return list;
        }
    }

    public void saveOrUpdate(Client client) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(client);
            session.getTransaction().commit();
        }
    }

    public Client get(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            session.getTransaction().commit();
            return client;
        }
    }
}
