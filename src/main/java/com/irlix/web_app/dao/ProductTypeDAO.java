package com.irlix.web_app.dao;

import com.irlix.web_app.model.ProductType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductTypeDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductTypeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<ProductType> findAllProductType() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM ProductType p", ProductType.class).getResultList();
    }

    @Transactional(readOnly = true)
    public ProductType showProductType(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProductType.class, id);
    }

    @Transactional
    public void saveNewProduct(ProductType productType) {
        Session session = sessionFactory.getCurrentSession();
        session.save(productType);
    }

    @Transactional
    public void updateProductType(int id, ProductType updatedProductType) {
        Session session = sessionFactory.getCurrentSession();
        ProductType personToBeUpdated = session.get(ProductType.class, id);

        personToBeUpdated.setType(updatedProductType.getType());
        personToBeUpdated.setDescription(updatedProductType.getDescription());
    }

    @Transactional
    public void deleteProductType(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(ProductType.class, id));
    }
}
