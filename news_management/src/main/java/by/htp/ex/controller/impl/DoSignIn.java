package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.ex.controller.Command;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.AttributeCommand;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.UserParameter;
import by.htp.ex.service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignIn implements Command {
	
	private final static Logger log = LogManager.getRootLogger();

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String login = request.getParameter(UserParameter.LOGIN_PARAM);		
		String password = request.getParameter(UserParameter.PASSWORD_PARAM);		
		
		if (login.isEmpty() | password.isEmpty()) {
			request.getSession(true).setAttribute(AttributeForAll.USER_STATE, AttributeForAll.USER_STATE_NOT_ACTIVE);
			request.getSession(true).setAttribute(AttributeForAll.AUTHENTICATION_ERROR, "wrong login or password");
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_BASE_PAGE);
			return;
		}

		try {
			String role = service.signIn(login, password);
			int userId = service.takeUserId(login);

			if (!role.equals(AttributeForAll.USER_ROLE_GUEST)) {				
				request.getSession(true).setAttribute(AttributeForAll.USER_STATE, AttributeForAll.USER_STATE_ACTIVE);
				request.getSession(true).setAttribute(AttributeForAll.USER_ROLE, role);
				request.getSession(true).setAttribute(AttributeForAll.USER_ID, userId);
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_NEWS_LIST);
				
			} else {
				request.getSession(true).setAttribute(AttributeForAll.USER_STATE, AttributeForAll.USER_STATE_NOT_ACTIVE);
				request.getSession(true).setAttribute(AttributeForAll.AUTHENTICATION_ERROR, "wrong login or password");
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_BASE_PAGE);
			}
			
		} catch (ServiceException e) {
			log.log(Level.ERROR, e);
		}		
	}
}
