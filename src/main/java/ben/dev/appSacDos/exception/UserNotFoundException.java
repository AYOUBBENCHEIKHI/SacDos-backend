package ben.dev.appSacDos.exception;

public class UserNotFoundException extends RuntimeException{
	
	public  UserNotFoundException(String message) {
		super(message);
	}
}
