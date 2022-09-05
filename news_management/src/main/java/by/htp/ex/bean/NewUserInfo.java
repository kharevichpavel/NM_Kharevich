package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class NewUserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login = "";
	private String password = "";
	private String registrationDate = "";
	private String email = "";
	private String telephone = "";

	public NewUserInfo() {
	}

	public NewUserInfo(String login, String password, String registrationDate, String email, String telephone) {
		super();

		this.login = login;
		this.password = password;
		this.registrationDate = registrationDate;
		this.email = email;
		this.telephone = telephone;
	}

	public NewUserInfo(String login) {
		super();

		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "NewUserInfo{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", registrationDate='"
				+ registrationDate + '\'' + ", email='" + email + '\'' + ", telephone='" + telephone + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NewUserInfo that = (NewUserInfo) o;
		return Objects.equals(login, that.login) && Objects.equals(password, that.password)
				&& Objects.equals(registrationDate, that.registrationDate) && Objects.equals(email, that.email)
				&& Objects.equals(telephone, that.telephone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, password, registrationDate, email, telephone);
	}
}
