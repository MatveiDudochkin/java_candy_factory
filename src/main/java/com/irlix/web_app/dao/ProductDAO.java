package com.irlix.web_app.dao;

import com.irlix.web_app.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Product showProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }

    @Transactional
    public void saveNewProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Transactional
    public void updateProduct(int id, Product updatedProduct) {
        Session session = sessionFactory.getCurrentSession();
        Product personToBeUpdated = session.get(Product.class, id);

        personToBeUpdated.setName(updatedProduct.getName());
        personToBeUpdated.setCalories(updatedProduct.getCalories());
        personToBeUpdated.setPrice(updatedProduct.getPrice());
        personToBeUpdated.setCompound(updatedProduct.getCompound());
    }

    @Transactional
    public void deleteProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Product.class, id));
    }
}
