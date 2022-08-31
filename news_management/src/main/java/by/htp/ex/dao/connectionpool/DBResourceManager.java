package by.htp.ex.dao.connectionpool;

import java.util.ResourceBundle;

public final class DBResourceManager {

	private final static DBResourceManager instance = new DBResourceManager();

	private ResourceBundle bundle = ResourceBundle.getBundle("db");

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}
