package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.util.LocalAndDateParameter;
import by.htp.ex.util.NewsParameter;

public class NewsDAO implements INewsDAO {
	
	private final static ConnectionPool provider = ConnectionPool.getInstance();		
	
	private static final String TAK_LAST_COUNT_NEWS = "SELECT id, newsDate, title ,brief, content FROM news ORDER BY newsDate DESC LIMIT ?";

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {	
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<News> result = new ArrayList<News>();
		 
		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(TAK_LAST_COUNT_NEWS);

			ps.setInt(1, count);
			rs = ps.executeQuery();
			while (rs.next()) {
				String dateToString = convertDateToString(rs);
				News lastListNews = new News(rs.getInt(1), dateToString, rs.getString(3), rs.getString(4),
						rs.getString(5));
				result.add(lastListNews);
			}

			return result;

		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		} finally {
			provider.closeConnection(connection, ps, rs);
		}
	}

	private static final String TAKE_ALL_NEWS = "SELECT id, newsDate, title ,brief, content FROM news";

	@Override
	public List<News> getList(int paginationId, int paginationSizeFromUser) throws NewsDAOException {	
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<News> result = new ArrayList<News>();
		
		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(TAKE_ALL_NEWS);
			rs = ps.executeQuery();			
			int numberOfTableRows = 0;
			while (rs.next()) {
				String dateToString = convertDateToString(rs);
				News news = new News(rs.getInt(NewsParameter.ID_NEWS), dateToString,
						rs.getString(NewsParameter.TITLE_NEWS), rs.getString(NewsParameter.BRIEF_NEWS),
						rs.getString(NewsParameter.CONTENT_NEWS));
				result.add(news);
				numberOfTableRows += 1;
			}
			if(paginationId>(int) Math.ceil((double)numberOfTableRows/paginationSizeFromUser)) {				
				paginationId = 1;
			}			
			int firstIndexForPagination = paginationId*paginationSizeFromUser-paginationSizeFromUser;
			int lastIndexForPagination = paginationId*paginationSizeFromUser;
			if(lastIndexForPagination>numberOfTableRows) {
				lastIndexForPagination = numberOfTableRows;
			}
			return result.subList(firstIndexForPagination, lastIndexForPagination);

		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		} finally {
			provider.closeConnection(connection, ps, rs);
		}
	}
	
	@Override
	public int getDbSize() throws NewsDAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(TAKE_ALL_NEWS);
			rs = ps.executeQuery();			
			int numberOfTableRows = 0;
			while (rs.next()) {				
				numberOfTableRows += 1;
			}
		
			return numberOfTableRows;

		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		} finally {
			provider.closeConnection(connection, ps, rs);
		}
	}

	private static final String SELECT_NEWS_BY_ID = "SELECT id, newsDate, title ,brief, content FROM news WHERE id = ?";

	@Override
	public News fetchById(int id) throws NewsDAOException{
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(SELECT_NEWS_BY_ID);

			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (!rs.next()) {
				throw new NewsDAOException("News is not found!");
			}
			String dateToString = convertDateToString(rs);

			News news = new News(id, dateToString, rs.getString(NewsParameter.TITLE_NEWS),
					rs.getString(NewsParameter.BRIEF_NEWS), rs.getString(NewsParameter.CONTENT_NEWS));

			return news;
			
		} catch (ConnectionPoolException | SQLException e) {
			throw new NewsDAOException(e);
		} finally {
			provider.closeConnection(connection, ps, rs);
		}
	}

	private static final String INSERT_NEW_NEWS = "INSERT INTO news (newsDate,title,brief,content,reporter_id) values (?,?,?,?,?)";

	@Override
	public int addNews(News news) throws NewsDAOException {
		
		Connection connection = null;
		PreparedStatement ps = null;		
		
		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(INSERT_NEW_NEWS);

			ps.setString(1, getDate());
			ps.setString(2, news.getTitle());
			ps.setString(3, news.getBrief());
			ps.setString(4, news.getContent());
			ps.setInt(5, news.getUserId());
			ps.executeUpdate();			
			
			return 1;
		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		} finally {
			provider.closeConnection(connection, ps);
		}
	}

	private static final String UPDATE_NEWS = "UPDATE news SET newsDate = ?, title = ?, brief = ?, content = ? WHERE id = ?";

	@Override
	public boolean updateNews(News news) throws NewsDAOException {
		
		Connection connection = null;
		PreparedStatement ps = null;
				
		try {	
			connection = provider.takeConnection();
			ps = connection.prepareStatement(UPDATE_NEWS);
			
			ps.setString(1, getDate());
			ps.setString(2, news.getTitle());
			ps.setString(3, news.getBrief());
			ps.setString(4, news.getContent());
			ps.setInt(5, news.getIdNews());
			int result = ps.executeUpdate();		
			
			return !(result == 0);

		} catch (SQLException e) {
			throw new NewsDAOException(e);

		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		} finally {
			provider.closeConnection(connection, ps);
		}
	}
	
	private static final String DELETE_NEWS = "DELETE FROM news WHERE id = ?";

	@Override
	public boolean deleteNews(String[] idNews) throws NewsDAOException {
		
		Connection connection = null;
		PreparedStatement ps = null;		
		
		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(DELETE_NEWS);
			
			connection.setAutoCommit(false);
			connection.rollback();
			for (int i = 0; i < idNews.length; i++) {
				ps.setInt(1, Integer.parseInt(idNews[i]));				
				ps.executeUpdate();
			}		
			connection.commit();
			connection.setAutoCommit(true);
			
			return true;

		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		} finally {
			provider.closeConnection(connection, ps);
		}
	}

	private String getDate() {
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(LocalAndDateParameter.ZONE_ID));
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(LocalAndDateParameter.DATE_FORMAT_FOR_GET_DATE);
		String date = dateTimeFormatter.format(zonedDateTime);
		return date;
	}

	private String convertDateToString(ResultSet rs) throws SQLException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LocalAndDateParameter.DATE_FORMAT_FOR_USER,
				LocalAndDateParameter.LOCALE_FOR_USER);
		Timestamp timestamp = rs.getTimestamp(NewsParameter.DATA_NEWS);
		String dateToString = simpleDateFormat.format(timestamp);
		return dateToString;
	}	
}
