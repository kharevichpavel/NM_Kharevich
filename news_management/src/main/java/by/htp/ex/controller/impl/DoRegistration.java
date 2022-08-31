package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.AttributeCommand;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.UserParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {	
	
	private final static Logger log = LogManager.getRootLogger();
	
	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
				
		String login = request.getParameter(UserParameter.NEW_LOGIN_PARAM);
		String passwordOriginal = request.getParameter(UserParameter.NEW_PASSWORD_PARAM);		
		String password = BCrypt.hashpw(passwordOriginal, BCrypt.gensalt());
		System.out.println(passwordOriginal);
		System.out.println(password);
		String registration_date = request.getParameter(UserParameter.NEW_REGISTRATION_DATE_PARAM);
		String email = request.getParameter(UserParameter.NEW_EMAIL_PARAM);
		String tel = request.getParameter(UserParameter.NEW_TEL_PARAM);	
		
		NewUserInfo user = new NewUserInfo(login, password, registration_date, email, tel);
		
		try {
			String role = service.registration(user);

			if (!role.equals(AttributeForAll.USER_ROLE_GUEST)) {				
				request.getSession(true).setAttribute(AttributeForAll.USER_STATE, AttributeForAll.USER_STATE_ACTIVE);
				request.getSession(true).setAttribute(AttributeForAll.USER_ROLE, role);
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_NEWS_LIST);
			} else {
				request.getSession(true).setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);
				request.getSession(true).setAttribute(AttributeForAll.AUTHENTICATION_ERROR, "user exists, change login");
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_BASE_PAGE);
			}
			
		} catch (ServiceException e) {
			log.log(Level.ERROR, e);
		}		
	}
}
