package com.Desert.Repository;

import com.Desert.Entity.BillDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDetailRepoBean implements BillDetailRepo {

    private final SessionFactory sessionFactory;

    public BillDetailRepoBean(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<BillDetail> getBillDetails(long billID) {
        Session session = sessionFactory.getCurrentSession();

        Query<BillDetail> query = session.createQuery("FROM BillDetail WHERE bill_id = :billID", BillDetail.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
