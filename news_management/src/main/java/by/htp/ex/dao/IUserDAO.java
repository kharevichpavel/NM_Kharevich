package by.htp.ex.dao;

import by.htp.ex.bean.NewUserInfo;

public interface IUserDAO {
	
	boolean logination(String login, String password) throws DaoException;
	boolean registration(NewUserInfo user) throws DaoException;
	String getRole(String login) throws DaoException;
	String getRole(NewUserInfo user) throws DaoException;
	int takeUserId(String login) throws DaoException;
}
