package com.crud.redes.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;

@WebListener
public class HibernateContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Fuerza la inicialización de Hibernate
        SessionFactory sf = HibernateUtil.getSessionFactory();
        System.out.println("✅ SessionFactory inicializada correctamente: " + (sf != null));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cierra la fábrica de sesiones al apagar la aplicación
        HibernateUtil.getSessionFactory().close();
        System.out.println("🛑 SessionFactory cerrada correctamente");
    }
}
