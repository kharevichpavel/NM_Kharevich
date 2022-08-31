package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.NewsParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToEditNews implements Command {
	
	private final static Logger log = LogManager.getRootLogger();
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DaoException {
		
				
		News news;		
		String id = request.getParameter(NewsParameter.ID_NEWS);	
		
		try {			
			news  = newsService.findById(Integer.parseInt(id));				
			request.setAttribute(AttributeForAll.NEWS, news);
			request.setAttribute(AttributeForAll.PRESENTATION, AttributeForAll.PRESENTATION_EDIT_NEWS);			
			request.getSession(true).setAttribute(AttributeForAll.USER_STATE, AttributeForAll.USER_STATE_ACTIVE);
			request.getSession(true).setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_ROLE_ADMIN);
			
			request.getRequestDispatcher(AttributeForAll.URL_TO_BASE_LAYOUT).forward(request, response);

		} catch (NumberFormatException e) {
			log.log(Level.ERROR, e);
		} catch (ServiceException e) {
			log.log(Level.ERROR, e);
		} catch (ConnectionPoolException e) {
			log.log(Level.ERROR, e);
		}
		
	}

}
