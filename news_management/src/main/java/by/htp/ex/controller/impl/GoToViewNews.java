package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.AttributeCommand;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.ErrorParameter;
import by.htp.ex.util.NewsParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToViewNews implements Command {

	private final static Logger log = LogManager.getRootLogger();

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession getSession = request.getSession();

		News news;
		int idNews = 0;

		try {
			Scanner scanner = new Scanner(request.getParameter(NewsParameter.ID_NEWS));
			if (scanner.hasNextInt()) {
				idNews = Integer.parseInt(request.getParameter(NewsParameter.ID_NEWS));

				news = newsService.findById(idNews);
				request.setAttribute(AttributeForAll.NEWS, news);
				request.setAttribute(AttributeForAll.PRESENTATION, AttributeForAll.PRESENTATION_VIEW_NEWS);
				request.getRequestDispatcher(AttributeForAll.URL_TO_BASE_LAYOUT).forward(request, response);
			} else {
				log.log(Level.ERROR, "Invalid news id");				
				getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_4);
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);
			}
			
		} catch (ServiceException | ConnectionPoolException e) {
			log.log(Level.ERROR, e);				
			getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_4);
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);
		}
	}
}
