package com.crud.redes.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@ApplicationScoped
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            System.out.println("⚙️ Iniciando configuración de Hibernate...");
            return new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("❌ Error creando SessionFactory: " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
