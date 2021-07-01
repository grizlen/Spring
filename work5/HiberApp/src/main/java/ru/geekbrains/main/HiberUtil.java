package ru.geekbrains.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Product;

public class HiberUtil {

    private static SessionFactory sessionFactory;

    public static Session getSession() {
        return getSessionFactory().getCurrentSession();
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("configs/hibernate.cfg.xml").addAnnotatedClass(Product.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void execSQL(String sql) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
