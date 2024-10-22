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
     * The starting point of the program. This method is responsible for the 
     * control of the functions at runtime.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Database Handler
        HibernateUtil.Initialize();

        // Disable Logging
        LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
        Map<String, LoggerConfig> map = logContext.getConfiguration().getLoggers();
        for (String key : map.keySet()) {
            LoggerConfig logger = map.get(key);
            logger.setLevel(Level.OFF);
        }
        
        // Open a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Call DataInitializer to populate initial data in the database
        DataInitializer.initializeData(session);

        // Inicializa a interface gráfica (Swing)
    	SwingUtilities.invokeLater(() -> {
    		MainWindow frame = new MainWindow();
    		frame.setVisible(true);
    	});
        // Print a message to confirm initialization
        System.out.println("Database initialized successfully.");
    }
}
