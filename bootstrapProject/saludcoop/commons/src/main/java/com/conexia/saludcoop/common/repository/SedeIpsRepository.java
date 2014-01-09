package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.SedeIps;
/**
 * 
 * @author nobregon
 *
 */
public interface SedeIpsRepository extends CrudRepository<SedeIps, Long> {
	@Query(
			"select s from SedeIps s "
			+ " where s.ips.tipoIdentificacion.id = COALESCE(Cast(?1 as integer), s.ips.tipoIdentificacion.id)"
			+ "   and s.ips.numeroIdentificacion = COALESCE(Cast(?2 as string),s.ips.numeroIdentificacion)"
			+ "   and s.ips.razonSocial like ?3"
			+ "	  and s.municipio.codigo = COALESCE(cast(?4 as string),s.municipio.codigo)"
			+ "   and s.municipio.descripcion like ?5"
			+ "   and s.direccion like ?6"
			)
	public List<SedeIps> findByIPS(Integer tipoIdentificacion, String numeroIdentificacion, String razonSocial, 
									String municipioCodigo, String municipioNombre, String direccion);

	@Query(
			"select s from SedeIps s "
			+ " where s.municipio.codigo = COALESCE(cast(?1 as string), s.municipio.codigo)"
			+ "	and s.ips.numeroIdentificacion = COALESCE(cast(?2 as string), s.ips.numeroIdentificacion)"
			)
	public List<SedeIps> findByMunicipioCodigoAndNumeroIdentificacion(String municipioCodigo, String numeroIdentificacion);
	
	@Query(
			"select s from SedeIps s "
			+ " where s.ips.numeroIdentificacion = COALESCE(cast(?1 as string), s.ips.numeroIdentificacion)"
			)
	public List<SedeIps> findByNumeroIdentificacion(String numeroIdentificacion);
    
	@Query(
			"select s from SedeIps s "
			+ " where s.regional.codigo = COALESCE(cast(?1 as string), s.regional.codigo)"
			+ "	and s.ips.numeroIdentificacion = COALESCE(cast(?2 as string), s.ips.numeroIdentificacion)"
			)
    public List<SedeIps> findByRegionalCodigoAndNumeroIdentificacion(String codigo, String numeroIdentificacion);
    
	@Query(
			"select s from SedeIps s where "
//			+ " where s.divisionSeccionales.codigo = COALESCE(cast(?1 as string), s.divisionSeccional.codigo)"
			+ "	 s.ips.numeroIdentificacion = COALESCE(cast(?2 as string), s.ips.numeroIdentificacion)"
			)
    public List<SedeIps> findByDivisionSeccionalCodigoAndNumeroIdentificacion(String codigoRegional, String numeroIdentificacion);
    
    public List<SedeIps> findByNombreLikeIgnoreCase(String nombre);
    
    public SedeIps findOneByCodigoMinisterioSalud(String codigo);
    
    public List<SedeIps> findByCodigoMinisterioSaludAndNombreLikeAllIgnoreCase(String codigo, String nombre);
    
    @Query(
			"select pesi.sedeIps from ProfesionalEspecialidadSedeIps pesi JOIN pesi.profesionalEspecialidad pe JOIN pe.profesional p"
			+ " where p.id = ?")
    public List<SedeIps> findByProfesional(Long idProfesional);
}
