package by.htp.ex.util.validation;

import java.util.regex.Pattern;
import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.util.PatternParameter;

public class UserDataValidationImpl implements UserDataValidation {

	@Override
	public boolean checkAUthData(String login, String password) {		
		if(login.isEmpty() & password.isEmpty()) {			
			return false;
		} else if (Pattern.matches(PatternParameter.PATTERN_LOGIN, login)) {
			return true;
		}
		return false;		
	}

	@Override
	public boolean checkRegistrationData(NewUserInfo user) {
		String login = user.getLogin();
		String password = user.getPassword();
		String email = user.getEmail();
		String telephone = user.getTelephone();
		if (login.isEmpty() || password.isEmpty() || email.isEmpty() || telephone.isEmpty()) {
			return false;
		}
		if (Pattern.matches(PatternParameter.PATTERN_LOGIN, login) 
				&& Pattern.matches(PatternParameter.PATTERN_EMAIL, email)
				&& Pattern.matches(PatternParameter.PATTERN_TEL, telephone)) {
			return true;
		}
		return false;
	}
}
