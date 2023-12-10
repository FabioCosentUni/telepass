package utils;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateConfiguration {
    private static SessionFactory sessionFactory;
    private HibernateConfiguration(){}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");//oracle.jdbc.OracleDriver
                settings.put(Environment.URL, "jdbc:oracle:thin:@localhost:1521:xe");
                settings.put(Environment.USER, "TELEPASS");
                settings.put(Environment.PASS, "telepass");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.OracleDialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Casello.class);
                configuration.addAnnotatedClass(Veicolo.class);
                configuration.addAnnotatedClass(Utente.class);
                configuration.addAnnotatedClass(Transponder.class);
                configuration.addAnnotatedClass(Viaggio.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}