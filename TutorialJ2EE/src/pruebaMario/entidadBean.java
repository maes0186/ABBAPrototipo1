package pruebaMario;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import relationsEntities.Hijo;
import relationsEntities.Papa;

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
		Hijo hijoAux=entityManager.find(Hijo.class, hijo.getIdHijo());
		Hijo hijoOut= new Hijo();
		hijoOut.setIdPapaFk(new Papa());
		hijoOut.getIdPapaFk().setDescPapa(hijoAux.getIdPapaFk().getDescPapa());
		return hijoOut;
		
		}
		catch(Exception e){
			
			e.printStackTrace();
			throw e;
		}
	}
	

}
