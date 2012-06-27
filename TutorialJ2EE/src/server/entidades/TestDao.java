package server.entidades;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateful
public class TestDao implements TestInterface {
	@PersistenceContext(unitName = "testUNIT")
	private EntityManager manager;
	@Override
	public EntityManager getManager() throws Exception {
		try {
		return manager;
		}
		catch(Exception e){
			throw e;
		}
	}
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addTest(Test test) throws TestException {
		manager.persist(test);
		observar(test);
	}
	
	private void imprimir(Object objeto){
	System.out.println(objeto);
	}

	@Override
	public void observar(Object object) {
		imprimir(manager.contains(object));
		
	}

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addTest2(Test test) throws TestException {
		manager.persist(test);
		observar(test);
		addTest(test);
		observar(test);
		
	}

	@Override
	public void revisarTransaccion() throws TestException {
		Test test = new Test();
		test.setA(2);
		addTest(test);
		observar(test);
		addTest2(test);
		observar(test);
		
	}
	
	
	

}
