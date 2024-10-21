package feastplannerecalc.database;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import feastplannerecalc.model.*;
/**
 * This class is responsible for managing {@link org.hibernate.Hibernate}
 * sessions.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * This function is responsible for creating sessions to be used in 
     * transactions externally.
     * 
     * @return the sessionFactory static variable.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate Settings
                Properties settings = new Properties();
                settings.put(Environment.SHOW_SQL, "true"); // Enable SQL logging
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect"); // Use H2 dialect
                settings.put(Environment.URL, "jdbc:h2:./database/feast_planner");
                settings.put(Environment.HBM2DDL_AUTO, "update"); // Automatically create/update tables
                settings.put(Environment.GENERATE_STATISTICS, "false");

                configuration.setProperties(settings);

                // Add your test entity class here
                configuration.addAnnotatedClass(Bebida.class);
                configuration.addAnnotatedClass(BebidaQuantidadePadrao.class);
                configuration.addAnnotatedClass(BebidaTipo.class);
                configuration.addAnnotatedClass(Comida.class);
                configuration.addAnnotatedClass(ComidaCategoriaCarne.class);
                configuration.addAnnotatedClass(ComidaCategoriaSalgado.class);
                configuration.addAnnotatedClass(ComidaQuantidadePadrao.class);
                configuration.addAnnotatedClass(ComidaTipo.class);
                configuration.addAnnotatedClass(Pessoa.class);
                configuration.addAnnotatedClass(PessoaCategoria.class);

                // Registry
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

    /**
     * This method is responsible for opening the very first session, making
     * sure the database is properly created.
     */
    public static void Initialize() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
    }
}
