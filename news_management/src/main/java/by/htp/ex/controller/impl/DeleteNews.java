package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.AttributeCommand;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.NewsParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteNews implements Command {
	
	private final static Logger log = LogManager.getRootLogger();
	
	private final INewsService serviceNews = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DaoException {
		
		HttpSession getSession = request.getSession(true);
				
		try {
			String[] idNews = request.getParameterValues(NewsParameter.ID_NEWS);			
			if(serviceNews.delete(idNews)) {
				getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_ROLE_ADMIN);
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_NEWS_LIST);			
			}
		} catch (ServiceException e) {
			log.log(Level.ERROR, e);
		} catch (IOException e) {
			log.log(Level.ERROR, e);
		}

	}

}
