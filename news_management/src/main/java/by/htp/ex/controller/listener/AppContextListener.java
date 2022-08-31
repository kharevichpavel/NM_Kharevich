package by.htp.ex.controller.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
	
	private static final Logger log = LogManager.getRootLogger();
	
	public void contextInitialized(ServletContextEvent event) {
		try {
			ConnectionPool.getInstance().initPoolData();
		} catch (ConnectionPoolException e) {
			log.log(Level.ERROR, e);
		}		
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		ConnectionPool.getInstance().dispose();
	}
}
