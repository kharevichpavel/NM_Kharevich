package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
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

public class GoToBasePage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	private final static Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession getSession = request.getSession();

		List<News> latestNews;

		try {

			latestNews = newsService.latestList(NewsParameter.COUNT_OF_LAST_NEWS);
			request.setAttribute(AttributeForAll.NEWS, latestNews);
			request.getRequestDispatcher(AttributeForAll.URL_TO_BASE_LAYOUT).forward(request, response);

		} catch (ServiceException e) {
			log.log(Level.ERROR, e);
			getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);
			getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_4);
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);
		}
	}
}
