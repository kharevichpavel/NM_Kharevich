package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.util.AttributeForAll;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.getSession(true).setAttribute(AttributeForAll.USER_STATE, AttributeForAll.USER_STATE_NOT_ACTIVE);
			response.sendRedirect(AttributeForAll.URL_TO_INDEX);
		
	}

}
