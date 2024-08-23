package com.users.repository;

import com.users.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public Long saveUser(UserEntity user) {

        try {
            Session session = sessionFactory.getCurrentSession();
            NativeQuery<Long> query = session.createNativeQuery(
                    "INSERT INTO users (firstname, lastname, emailaddress)" +
                            "VALUES (:firstname, :lastname, :emailaddress) " +
                            "RETURNING userid", Long.class);
            //query.setParameter("userid", user.getUserId());
            query.setParameter("firstname", user.getFirstName());
            query.setParameter("lastname", user.getLastName());
            query.setParameter("emailaddress", user.getEmailAddress());
            //query.executeUpdate();
            Long generatedId = ((Number) query.getSingleResult()).longValue();
            return  generatedId;
        } catch (Exception e) {
            System.err.println("Exception occurred during creation query. " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public Boolean updateUser(UserEntity user, Long userId) {

        try{
            Session session = sessionFactory.getCurrentSession();

            NativeQuery<UserEntity> query = session.createNativeQuery(
                    "UPDATE users SET firstname = :firstname, lastname = :lastname, emailaddress = :emailaddress " +
                            "WHERE userid = :userid", UserEntity.class);
            query.setParameter("firstname",user.getFirstName());
            query.setParameter("lastname",user.getLastName());
            query.setParameter("emailaddress",user.getEmailAddress());
            query.setParameter("userid",userId);
            query.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println("Exception occurred during update query. " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public UserEntity findByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();

        NativeQuery<UserEntity> query = session.createNativeQuery(
                "SELECT * FROM users WHERE userid = :userid", UserEntity.class);
        query.setParameter("userid",userId);
        List<UserEntity> userEntityList = query.getResultList();

        try{
            return userEntityList.get(0);
        } catch (Exception e) {
            System.err.println("Exception ocurred during finding. " + e.getMessage());
            return null;
        }
    }


    // Not specified on requirements
    @Transactional
    public UserEntity findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserEntity.class, id);
    }

    @Transactional
    public List<UserEntity> findAll() {
        Session session = sessionFactory.getCurrentSession();

        NativeQuery<UserEntity> query = session.createNativeQuery("SELECT * FROM users", UserEntity.class);
        List<UserEntity> userEntityList = query.getResultList();

        for(UserEntity user : userEntityList) {
            System.out.println(
                    "userId: " + user.getUserId() +
                    " firstName: " + user.getFirstName() +
                    " lastName: " + user.getLastName() +
                    " emailaddress: " + user.getEmailAddress());
        }


        /*
        NativeQuery<Object[]> query1 = session.
                createNativeQuery("SELECT userid, firstname, lastname, emailaddress FROM users WHERE userid = :userid");
        query1.setParameter("userid",userId);
        List<Object[]> results = query1.getResultList();

        for(Object[] obj : results) {
            System.out.println("userId: " + obj[0] + " firstName: " + obj[1] + " lastName: " + obj[2] + " emailaddress: " + obj[3]);
        }
        */

        return userEntityList;
    }

    @Transactional
    public void save(UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }
}
