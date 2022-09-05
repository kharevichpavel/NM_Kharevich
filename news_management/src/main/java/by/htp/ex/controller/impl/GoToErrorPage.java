package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.ErrorParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToErrorPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DaoException {
		
		HttpSession getSession = request.getSession();
		
		
		getSession.setAttribute(ErrorParameter.ERROR_STATE, ErrorParameter.ERROR_STATE_ACTIVE);						
		request.getRequestDispatcher(AttributeForAll.URL_TO_BASE_LAYOUT).forward(request, response);
		getSession.removeAttribute(ErrorParameter.ERROR_STATE);

	}

}
