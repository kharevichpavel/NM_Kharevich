package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.util.AttributeCommand;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.ErrorParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToAddNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession getSession = request.getSession();

		if (getSession.getAttribute(AttributeForAll.USER_ROLE).equals(AttributeForAll.USER_ROLE_ADMIN)) {

			request.setAttribute(AttributeForAll.PRESENTATION, AttributeForAll.PRESENTATION_ADD_NEWS);
			request.getRequestDispatcher(AttributeForAll.URL_TO_BASE_LAYOUT).forward(request, response);

		} else {
			getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);
			getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_1);
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);
		}
	}
}
