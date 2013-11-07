package br.com.achoufestas.ejb.expection;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class DALException extends Exception {

	private static final long serialVersionUID = 5441155010716319649L;

	public DALException(String string) {
		super(string);
	}
	
	public DALException(String string, Throwable cause) {
		super(string, cause);
	}

	public DALException(Exception e) {
		super(e);
	}
}
