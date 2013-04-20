package org.ejb.beans.tutorial;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
public class RemoteBeanDatos implements RemoteIntDatos {
	@PersistenceContext(unitName="frameUNIT")
	  private EntityManager entityManager;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int cantidad(){
		Usuario us= new Usuario();
		us.setApellido1("algo");
		us.setApellido2("algo2");
		us.setNombre1("algo2");
		us.setNombre2("algo2");
		entityManager.persist(us);
		return 5;
	}
}
