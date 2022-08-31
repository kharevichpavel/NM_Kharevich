package by.htp.ex.util.validation;

import by.htp.ex.bean.NewUserInfo;

public interface UserDataValidation {
	
       boolean checkAUthData(String login, String password);
       boolean checkRegistrationData(NewUserInfo user);
}
