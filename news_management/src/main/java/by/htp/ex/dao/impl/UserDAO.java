package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.mindrot.jbcrypt.BCrypt;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.util.AttributeForAll;
import by.htp.ex.util.LocalAndDateParameter;

public class UserDAO implements IUserDAO {
	
	private final static ConnectionPool provider = ConnectionPool.getInstance();
	private Connection connection = null;
	private PreparedStatement ps = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	@Override
	public boolean logination(String login, String password) throws DaoException {
		
		final String selectUserId = "SELECT password FROM users WHERE login = " + "\"" + login + "\"";
		
		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(selectUserId);
			rs = ps.executeQuery();
			rs.next();
			
			String passwordFromDb = rs.getString(1);		
			//String hashSalt = getBcryptSalt(passwordFromDb);			
			String passwordUserHash = BCrypt.hashpw(password, getBcryptSalt(passwordFromDb));

			if (passwordFromDb.equals(passwordUserHash)) {
				return true;
			}
			return false;

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			provider.closeConnection(connection, ps, rs);
		}		
	}		
	
	private final String selectForLogination = "SELECT * FROM users";
	private final String insertNewUserInUsers = "INSERT INTO users (login,password,registration_date,email,tel,roles_id) values (?,?,?,?,?,?)";

	@Override
	public boolean registration(NewUserInfo user) throws DaoException {

		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(insertNewUserInUsers);
			st = connection.createStatement();
			rs = st.executeQuery(selectForLogination);
			while (rs.next()) {
				String loginUser = rs.getString(2);
				String email = rs.getString(5);
				String tel = rs.getString(6);
				if (loginUser.equals(user.getLogin()) || email.equals(user.getEmail()) || tel.equals(user.getTel())) {
					return false;
				}
			}
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, getDate());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getTel());
			ps.setInt(6, 2);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			provider.closeConnection(connection, st, rs);
		}
	}

	private final String selectUserRole = "SELECT roles.title FROM users INNER JOIN roles ON users.roles_id=roles.id WHERE users.login=?";

	public String getRole(String login) throws DaoException {
		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(selectUserRole);
			ps.setString(1, login);			
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(AttributeForAll.TITLE_ROLE_FROM_DB);
			}
			return AttributeForAll.USER_ROLE_GUEST;
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			provider.closeConnection(connection, ps, rs);
		}		
	}

	public String getRole(NewUserInfo user) throws DaoException {
		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(selectUserRole);
			ps.setString(1, user.getLogin());			
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(AttributeForAll.TITLE_ROLE_FROM_DB);
			}
			return AttributeForAll.USER_ROLE_GUEST;
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			provider.closeConnection(connection, ps, rs);
		}		
	}

	@Override
	public int takeUserId(String login) throws DaoException {
		
		final String selectUserId = "SELECT id FROM users WHERE login = " + "\"" + login + "\"";
		
		try {
			connection = provider.takeConnection();
			ps = connection.prepareStatement(selectUserId);
			rs = ps.executeQuery();

			rs.next();
			int userId = rs.getInt(1);			

			return userId;

		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			provider.closeConnection(connection, ps, rs);
		}		
	}	

	private String getDate() {
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(LocalAndDateParameter.ZONE_ID));
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(LocalAndDateParameter.DATE_FORMAT_FOR_GET_DATE);
		String date = dateTimeFormatter.format(zonedDateTime);
		return date;
	}	
	
	private String getBcryptSalt(String password) {
		String salt = BCrypt.gensalt();	       
        String[] pass = password.split("");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pass.length; i++) {
            stringBuilder.append(pass[i]);
            if(i==salt.length()-1){
                break;
            }
        }
        String hashSalt = stringBuilder.toString();
                
		return hashSalt;		
	}	
}
