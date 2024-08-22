package com.users.app.repository;

import com.users.app.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user); //save(user);
    }

    @Transactional
    public User findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }
}
