package com.Desert.Repository;

import com.Desert.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoBean implements UserRepo {

    private final SessionFactory sessionFactory;

    public UserRepoBean(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUser(String username, String password) {
        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("FROM User WHERE username = :username AND password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUser(String username) {
        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
