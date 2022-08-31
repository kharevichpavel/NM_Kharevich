package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;

public interface INewsService {
	
  boolean save(News news) throws ServiceException, ConnectionPoolException;  
  boolean update(News news) throws ServiceException;
  boolean delete(String[] idNews) throws ServiceException;
  List<News> latestList(int count)  throws ServiceException;
  List<News> list()  throws ServiceException;
  News findById(int id) throws ServiceException, ConnectionPoolException;
 
}
