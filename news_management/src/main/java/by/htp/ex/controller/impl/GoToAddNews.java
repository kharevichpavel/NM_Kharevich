package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.util.AttributeForAll;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute(AttributeForAll.PRESENTATION, AttributeForAll.PRESENTATION_ADD_NEWS);
		request.getRequestDispatcher(AttributeForAll.URL_TO_BASE_LAYOUT).forward(request, response);
		
	}
}
