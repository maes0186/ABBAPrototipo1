package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.transaccional.RoleEstado;
import com.conexia.saludcoop.common.repository.RoleEstadoRepository;

@Repository
@Transactional
public class RoleEstadoManager {

	@Autowired
	public RoleEstadoRepository re;
	
	public RoleEstadoManager() {
		// TODO Auto-generated constructor stub
	}
	
	public List<DescriptivoDto> getEstadosByRole(Integer id){
		List<RoleEstado> entidades = re.findByRoleId(id);
		List<DescriptivoDto> ret = new ArrayList<>();
		for (RoleEstado estado : entidades) {
			DescriptivoDto dto = new DescriptivoDto();
			dto.setCodigo(estado.getEstadoVisible().getCodigo());
			dto.setDescripcion(estado.getEstadoVisible().getDescripcion());
			dto.setId(estado.getEstadoVisible().getId());
			ret.add(dto);
		}
		return ret;
	}
}
