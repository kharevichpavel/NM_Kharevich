package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;



public interface INewsDAO {
	
	List<News> getList() throws NewsDAOException;
	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int id) throws NewsDAOException, ConnectionPoolException;
	int addNews(News news) throws NewsDAOException, ConnectionPoolException;
	boolean updateNews(News news) throws NewsDAOException;
	boolean deleteNews(String[] idNews)throws NewsDAOException;		
}
