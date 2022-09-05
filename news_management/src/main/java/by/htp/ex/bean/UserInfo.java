package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String loginUser = "";
	private String passwordUser = "";
	private int userId = 0;

	public UserInfo() {
	}

	public UserInfo(String loginUser, String passwordUser) {
		super();

		this.loginUser = loginUser;
		this.passwordUser = passwordUser;
	}

	public UserInfo(int userId) {
		super();
		this.setUserId(userId);
	}

	public String getLogin() {
		return loginUser;
	}

	public void setLogin(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getPassword() {
		return passwordUser;
	}

	public void setPassword(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserInfo{" + "loginUser='" + loginUser + '\'' + ", passwordUser='" + passwordUser + '\'' + ", userId="
				+ userId + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserInfo userInfo = (UserInfo) o;
		return userId == userInfo.userId && Objects.equals(loginUser, userInfo.loginUser)
				&& Objects.equals(passwordUser, userInfo.passwordUser);
	}

	@Override
	public int hashCode() {
		return Objects.hash(loginUser, passwordUser, userId);
	}
}
