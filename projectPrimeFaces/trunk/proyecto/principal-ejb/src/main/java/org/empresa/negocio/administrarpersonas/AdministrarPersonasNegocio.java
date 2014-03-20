package org.empresa.negocio.administrarpersonas;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.empresa.adaptadores.AdaptadorPersonas;
import org.empresa.dto.PersonaDTO;
import org.empresa.entidades.proyecto.Persona;

@Stateless
public class AdministrarPersonasNegocio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	EntityManager em;
	
	
	public boolean creacionPersona(PersonaDTO personaDTO) throws Throwable{
		try{
		Persona persona= AdaptadorPersonas.toPersona(personaDTO);
		em.persist(persona);
		return true;
		}
		catch(Throwable tr){
			throw tr;
		}
	}
	public List<PersonaDTO> obtenerTodasPersonas() throws Throwable{
		try{
			String consulta=consultaObtenerTodasPersonas();
			Query query=em.createQuery(consulta);
			List<Persona> personas=query.getResultList();
			return AdaptadorPersonas.toPersonasDTO(personas);
			}
			catch(Throwable tr){
				throw tr;
			}
	}
	
	
	private String consultaObtenerTodasPersonas(){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT p FROM Persona p");
		return sql.toString();
	}

}
