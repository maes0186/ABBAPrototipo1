package server.entidades;

import javax.ejb.ApplicationException;



@ApplicationException(rollback = true)
public class TestException extends Exception {
	private static final long serialVersionUID = 1L;

	public TestException(String msg) {
		super(msg);
	}
}