package pruebaMario;

import java.util.List;

import javax.ejb.Remote;

import relationsEntities.Hijo;
import relationsEntities.Papa;


@Remote
public interface claseRemota {
	
	public void addEntidad(Object object);
	public Hijo loadHijo (Hijo hijo) throws Exception ;
	public Papa loadPapa(Papa papa) throws Exception ;
	public List<Test2> getAll();
}
