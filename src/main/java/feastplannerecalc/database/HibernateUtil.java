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
 * Classe responsável por gerenciar as sessões do Hibernate. Essa classe 
 * lida com a configuração e inicialização da conexão com o banco de dados,
 * além de garantir que as tabelas sejam criadas e atualizadas conforme necessário.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * Este método é responsável por configurar e fornecer uma fábrica de sessões 
     * (SessionFactory) que será usada em transações externas. Ele utiliza o banco 
     * de dados H2 e configura o Hibernate para gerar ou atualizar as tabelas 
     * automaticamente.
     * 
     * @return {@code sessionFactory} A fábrica de sessões configurada, 
     * pronta para uso.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

             // Configurações do Hibernate
                Properties settings = new Properties();
                settings.put(Environment.SHOW_SQL, "true"); // Enable SQL logging
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect"); // Use H2 dialect
                settings.put(Environment.URL, "jdbc:h2:./database/feast_planner");
                settings.put(Environment.HBM2DDL_AUTO, "update"); // Automatically create/update tables
                settings.put(Environment.GENERATE_STATISTICS, "false");

                configuration.setProperties(settings);

             // Adiciona as classes anotadas usadas pelo Hibernate
                configuration.addAnnotatedClass(Bebida.class);
                configuration.addAnnotatedClass(BebidaQuantidadePadrao.class);
                configuration.addAnnotatedClass(BebidaTipo.class);
                configuration.addAnnotatedClass(Comida.class);
                configuration.addAnnotatedClass(ComidaCategoriaCarne.class);
                configuration.addAnnotatedClass(ComidaCategoriaSalgado.class);
                configuration.addAnnotatedClass(ComidaQuantidadePadrao.class);
                configuration.addAnnotatedClass(ComidaTipo.class);
               // configuration.addAnnotatedClass(Pessoa.class);
                configuration.addAnnotatedClass(PessoaCategoria.class);

             // Adiciona as classes que armazenam os resultados das simulações
                configuration.addAnnotatedClass(ResultadoSimulacao.class);
                configuration.addAnnotatedClass(ResultadoChurrasco.class);
                configuration.addAnnotatedClass(ResultadoSalgado.class);


             // Criação do registro de serviço
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
     * Método responsável por inicializar a primeira sessão do Hibernate.
     * Ele garante que o banco de dados esteja devidamente criado ao abrir 
     * e fechar uma sessão de teste.
     */
    public static void Initialize() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
    }
}
