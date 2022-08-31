package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.util.AttributeCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoLocal implements Command {
	
	private final static Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DaoException {
		
		HttpSession getSession = request.getSession(true);
		
		getSession.setAttribute("local", request.getParameter("local"));			
		
		String referer = request.getHeader("Referer");
		getSession.setAttribute("referer", referer);
				
		if(referer == null || referer.isEmpty()) {
			log.info("URL not found.");			
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_BASE_PAGE);			
		}
		response.sendRedirect(referer);		
	}
}
