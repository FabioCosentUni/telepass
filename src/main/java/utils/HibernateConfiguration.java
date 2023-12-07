package utils;

import dto.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateConfiguration {
    private static SessionFactory sessionFactory;

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

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(CaselloDTO.class);
                configuration.addAnnotatedClass(VeicoloDTO.class);
                configuration.addAnnotatedClass(UtenteDTO.class);
                configuration.addAnnotatedClass(TransponderDTO.class);
                configuration.addAnnotatedClass(ViaggioDTO.class);

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