package ru.geekbrains.HiberSpringApp.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.HiberSpringApp.entities.Product;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class ProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(EntityManagerFactory emf) {
        sessionFactory = emf.unwrap(SessionFactory.class);
    }

    public List<Product> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> list = session.createNamedQuery("product.all").getResultList();
            session.getTransaction().commit();
            return list;
        }
    }

    public void saveOrUpdate(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    public Product get(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }
}
