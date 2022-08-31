package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	private final UserDataValidation userDataValidation = ValidationProvider.getInstance().getUserDataValidation();

	@Override
	public String signIn(String login, String password) throws ServiceException {		
		try {			
			if (!userDataValidation.checkAUthData(login, password)) {
				return AttributeForAll.USER_ROLE_GUEST;
			} else if (userDAO.logination(login, password)) {
				return userDAO.getRole(login);
			}
			return AttributeForAll.USER_ROLE_GUEST;
		} catch (DaoException e) {
			throw new ServiceException(e);			
		}
	}

	@Override
	public String registration(NewUserInfo user) throws ServiceException {
		try {
			if (!userDataValidation.checkRegistrationData(user)) {
				return AttributeForAll.USER_ROLE_GUEST;
			} else if (userDAO.registration(user)) {
				return userDAO.getRole(user.getLogin());
			}
			return AttributeForAll.USER_ROLE_GUEST;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int takeUserId(String login) throws ServiceException {
		try {
			if (userDAO.takeUserId(login)!=0) {
				return userDAO.takeUserId(login);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return 0;
	}	
}
