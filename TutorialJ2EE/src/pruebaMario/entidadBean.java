package pruebaMario;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateful
public class entidadBean implements claseRemota {
    @PersistenceContext(unitName="testUNIT")
	private EntityManager entityManager;
	@Override
	public void addEntidad(Test2 test2) {
		entityManager.persist(test2);
		
	}

}
