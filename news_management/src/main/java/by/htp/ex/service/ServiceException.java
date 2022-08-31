package by.htp.ex.service;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = -1291668902432628598L;

	public ServiceException(String e) {
		super(e);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
