package by.htp.ex.util.validation;

public class ValidationProvider {
	
	private static final ValidationProvider instance = new ValidationProvider();
	
	private ValidationProvider() {}
	
	private final UserDataValidation userDataValidation = new UserDataValidationImpl();
	
	public UserDataValidation getUserDataValidation() {
		return userDataValidation;
	}	
	
	public static ValidationProvider getInstance() {
		return instance;
	}

}
