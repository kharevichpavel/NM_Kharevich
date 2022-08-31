package by.htp.ex.controller;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.dao.DaoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = LogManager.getRootLogger();

	private final CommandProvider provider = new CommandProvider();

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commandName = request.getParameter("command");

		Command command = provider.getCommand(commandName);
		try {
			command.execute(request, response);

		} catch (ServletException e) {
			log.log(Level.ERROR, e);
		} catch (IOException e) {
			log.log(Level.ERROR, e);
		} catch (DaoException e) {			
			log.log(Level.ERROR, e);
		}
	}
}
