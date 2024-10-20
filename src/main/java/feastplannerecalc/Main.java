package feastplannerecalc;

import feastplannerecalc.database.HibernateUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;

import java.util.Map;

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

        // Print a message to confirm initialization
        System.out.println("Database initialized successfully.");
    }
}
