package feastplannerecalc;

import feastplannerecalc.database.HibernateUtil;
import feastplannerecalc.util.DataInitializer;
import feastplannerecalc.views.MainWindow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.hibernate.Session;

import java.util.Map;

import javax.swing.SwingUtilities;

public class Main {
    /**
     * Método principal do programa. Ele é o ponto de entrada da aplicação, responsável por
     * inicializar a conexão com o banco de dados, desativar logs indesejados, carregar dados
     * iniciais e exibir a interface gráfica. O projeto é construído utilizando Maven, 
     * com o banco de dados H2 e Hibernate como ORM para persistência de dados.
     * 
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
    	// Inicializa o banco de dados e as tabelas utilizando Hibernate
        HibernateUtil.Initialize();

        // Desabilita o logging para evitar saídas indesejadas no console
        LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
        Map<String, LoggerConfig> map = logContext.getConfiguration().getLoggers();
        for (String key : map.keySet()) {
            LoggerConfig logger = map.get(key);
            logger.setLevel(Level.OFF);
        }
        
     // Abre uma sessão Hibernate para interações com o banco de dados
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Chama o inicializador de dados para popular o banco com dados iniciais
        DataInitializer.initializeData(session);

        /**
         * Inicializa a interface gráfica utilizando Java Swing.
         * O método {@code invokeLater} garante que a interface será criada
         * na thread de despacho de eventos do Swing, mantendo o comportamento
         * thread-safe.
         */
    	SwingUtilities.invokeLater(() -> {
    		MainWindow frame = new MainWindow();
    		frame.setVisible(true);
    	});
    	// Imprime uma mensagem no console para confirmar a inicialização do banco de dados
        System.out.println("Database initialized successfully.");
    }
}
