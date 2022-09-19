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

public class GoToNewsList implements Command {

	private final static Logger log = LogManager.getRootLogger();

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession getSession = request.getSession();
		List<News> newsList;
		
		int paginationId;
		int numberOfTableRows;		
		int paginationSizeFromUser;
		
		String resultPaginationFromRequest = request.getParameter(NewsParameter.PAGINATION_SIZE_FROM_USER);	
		String resultPageIdFromRequest = request.getParameter(NewsParameter.PAGE_ID);
				
		try {	
			
			if(resultPaginationFromRequest == null || !resultPaginationFromRequest.matches("[0-9]+")) {
				paginationSizeFromUser = NewsParameter.PAGINATION_SIZE_FIXED;
			}else {			
				paginationSizeFromUser = Integer.parseInt(resultPaginationFromRequest);
			}	
			if(resultPageIdFromRequest == null || !resultPageIdFromRequest.matches("[0-9]+")) {
				paginationId = 1;
			}else {
				paginationId = Integer.parseInt(resultPageIdFromRequest);				
			}
			
			newsList = newsService.list(paginationId, paginationSizeFromUser);
			numberOfTableRows = newsService.getDbSize();			
			
			int paginationSizeForPage = (int) Math.ceil((double)numberOfTableRows/paginationSizeFromUser);	
			
			request.setAttribute(AttributeForAll.NEWS, newsList);
			request.setAttribute(NewsParameter.PAGINATION_SIZE_FROM_USER, paginationSizeFromUser);
			request.setAttribute(NewsParameter.PAGINATION_SIZE_FOR_PAGE, paginationSizeForPage);			
			request.setAttribute(AttributeForAll.PRESENTATION, AttributeForAll.PRESENTATION_NEWS_LIST);
			request.getRequestDispatcher(AttributeForAll.URL_TO_BASE_LAYOUT).forward(request, response);
			
		} catch (ServiceException e) {
			log.log(Level.ERROR, e);
			getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);
			getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_4);
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);
		}
	}
}
