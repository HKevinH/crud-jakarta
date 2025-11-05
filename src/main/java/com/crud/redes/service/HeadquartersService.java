package com.crud.redes.service;

import com.crud.redes.config.HibernateUtil;
import com.crud.redes.models.Headquarters;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Transaction;

import java.util.List;

@ApplicationScoped
public class HeadquartersService {


    public boolean create(Headquarters headquarters) {
        Transaction transaction = null;
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(headquarters);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }

    }

    public boolean update (Headquarters headquarters) {
        Transaction transaction = null;
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(headquarters);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete (Headquarters headquarters) {
        Transaction transaction = null;
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(headquarters);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public List<Headquarters> getAll() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Headquarters", Headquarters.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
