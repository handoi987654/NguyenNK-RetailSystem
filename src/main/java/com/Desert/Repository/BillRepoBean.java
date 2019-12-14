package com.Desert.Repository;

import com.Desert.Entity.Bill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepoBean implements BillRepo {

    private final SessionFactory sessionFactory;

    public BillRepoBean(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Bill> getBills(long userID) {
        Session session = sessionFactory.getCurrentSession();

        Query<Bill> query = session.createQuery("FROM Bill WHERE user_id = :userID", Bill.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
