package me.learnjava.exception;


/**
 * 自定义异常
 * @author liusha
 *
 */
public class UserExistException extends Exception{
	
	private static final long serialVersionUID = 5003432555287095883L;

	public UserExistException() {
		super();
	}

	public UserExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExistException(String message) {
		super(message);
	}

	public UserExistException(Throwable cause) {
		super(cause);
	}

}
