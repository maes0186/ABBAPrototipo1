package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.transaccional.RoleEstado;

public interface RoleEstadoRepository extends CrudRepository<RoleEstado, Integer> {

	public List<RoleEstado> findByRoleId(Integer rolId);
	public RoleEstado findOneByEstadosAutorizacionIdAndRoleId(Integer EstadoAutorizacionid, Integer rolId);
	public RoleEstado findOneByEstadoVisibleIdAndRoleId(Integer EstadoVisibleid, Integer rolId);
	
}
