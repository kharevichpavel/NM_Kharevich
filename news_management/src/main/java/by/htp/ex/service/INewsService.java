package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.News;

public interface INewsService {
	
  boolean save(News news) throws ServiceException;  
  boolean update(News news) throws ServiceException;
  boolean delete(String[] idNews) throws ServiceException;
  List<News> latestList(int count)  throws ServiceException;
  List<News> list(int paginationId, int paginationSizeFromUser)  throws ServiceException;
  News findById(int id) throws ServiceException;
  int getDbSize() throws ServiceException;
 
}
