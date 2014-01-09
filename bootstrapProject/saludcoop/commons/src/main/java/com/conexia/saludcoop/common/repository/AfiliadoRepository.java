package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.repository.custom.ExtendedAfiliadoRepository;

public interface AfiliadoRepository extends CrudRepository<Afiliado, Long>, ExtendedAfiliadoRepository {
	
	public Afiliado findOneByTipoIdentificacionIdAndNumeroIdentificacion(Integer tipoIdentificacionId, String numeroIdentificacion);

	public Afiliado findOneByTipoIdentificacionIdAndNumeroIdentificacionAndEpsId(Integer tipoIdentificacionId, String numeroIdentificacion, Long eps);

	@Query(" select a from Afiliado a where UPPER(CONCAT(a.primerNombre, a.segundoNombre))  like UPPER(:nombres) "
			+ " and UPPER(CONCAT(a.primerApellido,a.segundoApellido)) like UPPER(:apellidos)")
	public List<Afiliado> findByNombreYApellido(@Param(value = "nombres") String nombres, @Param(value = "apellidos") String apellidos);

	@Query(" select a from Afiliado a where UPPER(CONCAT(a.primerNombre, a.segundoNombre))  like UPPER(:nombres) "
			+ " and UPPER(CONCAT(a.primerApellido,a.segundoApellido)) like UPPER(:apellidos) and a.eps.id = :eps")
	public List<Afiliado> findByNombreYApellido(@Param(value = "nombres") String nombres, @Param(value = "apellidos") String apellidos, @Param(value = "eps") Long eps);
	
	public Afiliado findOneById(Long id);
	
//	
//	@Query(" select a from Afiliado a left join fetch a.programas where a.id=:id")
//    public Afiliado findOneByIdFetchProgramas(@Param(value = "id") Long id);
}
