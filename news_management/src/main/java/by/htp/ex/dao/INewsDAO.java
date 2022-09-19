package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.News;



public interface INewsDAO {
	
	List<News> getList(int paginationId, int paginationSizeFromUser) throws NewsDAOException;
	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int id) throws NewsDAOException;
	int addNews(News news) throws NewsDAOException;
	int getDbSize() throws NewsDAOException;
	boolean updateNews(News news) throws NewsDAOException;
	boolean deleteNews(String[] idNews)throws NewsDAOException;		
}
