package server.entidades;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

@Remote
public interface TestInterface {
	
	public void addTest(Test test)throws TestException;
	public void addTest2(Test test)throws TestException;
	public void observar(Object object);
	public EntityManager getManager()throws Exception;
	public void revisarTransaccion()throws TestException ;

}
