package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.NewsParameter;

public class NewsServiceImpl implements INewsService{

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
		
	@Override
	public boolean save(News news) throws ServiceException, ConnectionPoolException {
		try {
			if(newsDAO.addNews(news)!=1) {
				return false;
			}
			return true;
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}				
	}

	@Override
	public boolean update(News news) throws ServiceException {
		try {
			if(!newsDAO.updateNews(news)) {
				return false;
			}
			return true;
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}				
	}

	@Override
	public List<News> latestList(int count) throws ServiceException {
		
		try {
			return newsDAO.getLatestsList(NewsParameter.COUNT_OF_LAST_NEWS);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list(int paginationId, int paginationSizeFromUser) throws ServiceException {
		try {
			return newsDAO.getList(paginationId, paginationSizeFromUser);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException, ConnectionPoolException {
		try {			
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(String[] idNews) throws ServiceException {
		try {
			if(!newsDAO.deleteNews(idNews)) {
				return false;
			}
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
		return true;		
	}

	@Override
	public int getDbSize() throws ServiceException {
		try {			
			return newsDAO.getDbSize();
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}		
}
