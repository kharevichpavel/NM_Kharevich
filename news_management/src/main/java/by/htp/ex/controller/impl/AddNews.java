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

public class AddNews implements Command {

	private final static Logger log = LogManager.getRootLogger();

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DaoException {

		HttpSession getSession = request.getSession();

		int idNews = 0;
		String newsDate = request.getParameter(NewsParameter.DATA_NEWS);
		String title = request.getParameter(NewsParameter.TITLE_NEWS);
		String brief = request.getParameter(NewsParameter.BRIEF_NEWS);
		String content = request.getParameter(NewsParameter.CONTENT_NEWS);
		int userId = (int) request.getSession(true).getAttribute(AttributeForAll.USER_ID);

		News news = new News(idNews, newsDate, title, brief, content, userId);

		try {
			if (service.save(news)) {
				getSession.setAttribute(AttributeForAll.USER_STATE, AttributeForAll.USER_STATE_ACTIVE);
				getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_ROLE_ADMIN);
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_NEWS_LIST);
			}
		} catch (ServiceException | ConnectionPoolException e) {
			log.log(Level.ERROR, e);
			getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);
			getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_5);
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);
		}
	}
}
