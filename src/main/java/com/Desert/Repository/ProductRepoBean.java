package com.Desert.Repository;

import com.Desert.Entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepoBean implements ProductRepo {

    private final SessionFactory sessionFactory;

    public ProductRepoBean(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product getProduct(String barcode) {
        Session session = sessionFactory.getCurrentSession();

        Query<Product> query = session.createQuery("FROM Product WHERE barcode = :barcode", Product.class);
        query.setParameter("barcode", barcode);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product getProduct(long productID) {
        Session session = sessionFactory.getCurrentSession();

        try {
            return session.get(Product.class, productID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getProductList() {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product", Product.class);
        return query.getResultList();
    }
}
