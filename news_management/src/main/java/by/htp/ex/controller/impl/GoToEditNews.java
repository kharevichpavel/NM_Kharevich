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
import by.htp.ex.util.AttributeCommand;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.ErrorParameter;
import by.htp.ex.util.NewsParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToEditNews implements Command {

	private final static Logger log = LogManager.getRootLogger();

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DaoException {

		HttpSession getSession = request.getSession();

		News news;
		String id = request.getParameter(NewsParameter.ID_NEWS);

		if (getSession.getAttribute(AttributeForAll.USER_ROLE).equals(AttributeForAll.USER_ROLE_ADMIN)) {
			try {
				news = newsService.findById(Integer.parseInt(id));
				request.setAttribute(AttributeForAll.NEWS, news);
				request.setAttribute(AttributeForAll.PRESENTATION, AttributeForAll.PRESENTATION_EDIT_NEWS);
				getSession.setAttribute(AttributeForAll.USER_STATE, AttributeForAll.USER_STATE_ACTIVE);
				getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_ROLE_ADMIN);

				request.getRequestDispatcher(AttributeForAll.URL_TO_BASE_LAYOUT).forward(request, response);

			} catch (ServiceException | NumberFormatException | ConnectionPoolException e) {
				log.log(Level.ERROR, e);
				getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);
				getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_6);
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);
			}
		} else {
			getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);
			getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_1);
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);
		}
	}

}
