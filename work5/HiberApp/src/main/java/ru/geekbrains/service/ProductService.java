package ru.geekbrains.service;

import org.hibernate.Session;
import ru.geekbrains.main.HiberUtil;
import ru.geekbrains.model.Product;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public class ProductService implements Closeable {

    public ProductService() {
        initTable();
    }

    private static void initTable() {
        StringBuilder sql = new StringBuilder("INSERT INTO products (title, cost) VALUES")
                .append("('product 1', 10),")
                .append("('product 2', 10.5),")
                .append("('product 3', 25),")
                .append("('product 4', 250),")
                .append("('product 5', 100)")
                .append(";");
        System.out.println("Init:");
        HiberUtil.execSQL(sql.toString());
    }
    public Product create(String title, Float cost) {
        try (Session session = HiberUtil.getSession()) {
            Product product = new Product(title, cost);
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll() {
        try (Session session = HiberUtil.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createNamedQuery("Product.findAll").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    public Product findById(long id) {
        try (Session session = HiberUtil.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public Product setTitle(long id, String title) {
        try (Session session = HiberUtil.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setTitle(title);
                session.saveOrUpdate(product);
            }
            session.getTransaction().commit();
            return product;
        }
    }

    public Product setCost(long id, float cost) {
        try (Session session = HiberUtil.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setCost(cost);
                session.saveOrUpdate(product);
            }
            session.getTransaction().commit();
            return product;
        }
    }

    public boolean delete(long id) {
        try (Session session = HiberUtil.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            session.getTransaction().commit();
            return product != null;
        }
    }

    @Override
    public void close() throws IOException {
        System.out.println("Close:");
        HiberUtil.close();
    }
}
