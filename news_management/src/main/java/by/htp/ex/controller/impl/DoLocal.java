package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.util.AttributeCommand;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.ErrorParameter;
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

		getSession.setAttribute(AttributeForAll.LOCAL, request.getParameter(AttributeForAll.LOCAL));

		String referer = request.getHeader(AttributeForAll.REFERER);
		getSession.setAttribute(AttributeForAll.REFERER, referer);

		if (referer == null || referer.isEmpty()) {
			log.info("URL not found.");
			getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);
			getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_7);
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);
		}
		response.sendRedirect(referer);
	}
}
