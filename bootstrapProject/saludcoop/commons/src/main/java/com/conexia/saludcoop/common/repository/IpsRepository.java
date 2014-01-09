package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.Ips;

public interface IpsRepository extends CrudRepository<Ips, Long> {

	List<Ips> findByTipoIdentificacionIdAndNumeroIdentificacionLikeAndRazonSocialLike(Integer tipoIdentId, String numeroIdentificacion, String razonSocial);

}
