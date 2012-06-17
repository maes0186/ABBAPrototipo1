package server.entidades;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ArticuloException extends Exception {
	private static final long serialVersionUID = 1L;

	public ArticuloException(String msg) {
		super(msg);
	}
}

