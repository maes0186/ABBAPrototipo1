package pruebaMario;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import relationsEntities.Hijo;

@Stateful
public class entidadBean implements claseRemota {
    @PersistenceContext(unitName="testUNIT")
	private EntityManager entityManager;
	
	@Override
	public void addEntidad(Object object) {
		entityManager.persist(object);
	}

	@Override
	public Hijo loadHijo(Hijo hijo) throws Exception {
		try{
		return entityManager.find(Hijo.class, hijo.getIdHijo());
		
		}
		catch(Exception e){
			
			e.printStackTrace();
			throw e;
		}
	}
	

}
