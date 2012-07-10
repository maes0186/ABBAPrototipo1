package pruebaMario;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public Papa loadPapa(Papa papa) throws Exception {
		try{
		Papa papaAux=entityManager.find(Papa.class, papa.getIdPapa());
		
		return papaAux;
		
		}
		catch(Exception e){
			
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Test2> getAll() {
		Query query=entityManager.createNamedQuery("Test2.findAll");
		@SuppressWarnings("unchecked")
		List<Test2> lstTodos=query.getResultList();
		return lstTodos;
	}

}
