package pruebaMario;

import javax.ejb.Remote;

import relationsEntities.Hijo;


@Remote
public interface claseRemota {
	
	public void addEntidad(Object object);
	public Hijo loadHijo (Hijo hijo) throws Exception ;
	
}
