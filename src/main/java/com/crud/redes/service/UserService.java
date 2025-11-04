package com.crud.redes.service;

import com.crud.redes.config.HibernateUtil;
import com.crud.redes.models.Users;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Transaction;

@ApplicationScoped
public class UserService {
    public boolean registerUser(Users user) {

        Transaction transaction = null;
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Users loginUser(String email, String password) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery("FROM Users WHERE email = :email AND password = :password", Users.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            Users user = query.uniqueResult();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
