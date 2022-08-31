package by.htp.ex.service;

import by.htp.ex.bean.NewUserInfo;

public interface IUserService {
	
	String signIn(String login, String password) throws ServiceException;
	String registration(NewUserInfo user) throws ServiceException;
	int takeUserId(String login) throws ServiceException;
}
