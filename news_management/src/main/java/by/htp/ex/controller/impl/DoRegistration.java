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
import by.htp.ex.util.ErrorParameter;
import by.htp.ex.util.UserParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoRegistration implements Command {	
	
	private final static Logger log = LogManager.getRootLogger();
	
	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
				
		String login = request.getParameter(UserParameter.NEW_LOGIN_PARAM);
		String passwordOriginal = request.getParameter(UserParameter.NEW_PASSWORD_PARAM);		
		String password = BCrypt.hashpw(passwordOriginal, BCrypt.gensalt());		
		String registration_date = request.getParameter(UserParameter.NEW_REGISTRATION_DATE_PARAM);
		String email = request.getParameter(UserParameter.NEW_EMAIL_PARAM);
		String tel = request.getParameter(UserParameter.NEW_TEL_PARAM);	
		
		NewUserInfo user = new NewUserInfo(login, password, registration_date, email, tel);
		
		HttpSession getSession = request.getSession();
		
		try {
			String role = service.registration(user);

			if (!role.equals(AttributeForAll.USER_ROLE_GUEST)) {				
				getSession.setAttribute(AttributeForAll.USER_STATE, AttributeForAll.USER_STATE_ACTIVE);
				getSession.setAttribute(AttributeForAll.USER_ROLE, role);
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_NEWS_LIST);
			} else {
				getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);							
				getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_3);								
				response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);								
			}
			
		} catch (ServiceException e) {
			log.log(Level.ERROR, e);			
			getSession.setAttribute(AttributeForAll.USER_ROLE, AttributeForAll.USER_STATE_NOT_ACTIVE);			
			getSession.setAttribute(ErrorParameter.ERROR_NUMBER, ErrorParameter.ERROR_NUMBER_8);								
			response.sendRedirect(AttributeCommand.COMMAND_GO_TO_ERROR_PAGE);			
			
		}		
	}
}
