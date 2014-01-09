package com.conexia.saludcoop.common.entity.maestro;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.transaccional.RoleEstado;

@Entity
@Table(name = "estado_autorizacion", schema = "maestros")
@Mappeable(mappedTo = DescriptivoDto.class)
public class EstadoAutorizacion extends Descriptivo {



	public static final Integer RADICADA = 1;
	public static final Integer EN_ESTUDIO = 2;
	public static final Integer DEVUELTA_IPS = 3;
	public static final Integer RESPUESTA_IPS = 4;
	public static final Integer APROBADA_REGIONAL = 5;
	public static final Integer NEGADA_REGIONAL = 6;
	public static final Integer ANULADA = 7;
	public static final Integer DEVUELTA_REGIONAL = 8;
	public static final Integer RESPUESTA_REGIONAL = 9;
	public static final Integer NEGADA_NACIONAL = 10;
	public static final Integer PENDIENTE_ACTA = 11;
	public static final Integer AUTORIZADO = 12;
	public static final Integer NEGADA_POR_NO_ENCONTRAR_IPS_EN_REDIRECCION = 13;
	public static final Integer NO_AUTORIZADA = 15;
	public static final Integer PENDIENTE_CONTACT_SERVICE = 16;
	public static final Integer PENDIENTE_ALTO_COSTO = 17;
	public static final Integer PENDIENTE_CTC = 18;
	public static final Integer CONSUMIDA = 19;
	public static final Integer PENDIENTE_REDIRECCION_AUDITOR = 20;
	public static final Integer VENCIDA = 21;
	public static final Integer PENDIENTE_ANULACION_AUDITOR = 22;
	public static final Integer PENDIENTE_TUTELA = 23;
	public static final Integer PENDIENTE_AUDITORIA_ESPECIALIZADA = 25;

	@ManyToMany(mappedBy = "estadosAutorizacion")
	private Set<RoleEstado> roleEstados;

	public Set<RoleEstado> getRoleEstados() {
		return roleEstados;
	}

	public void setRoleEstados(Set<RoleEstado> roleEstados) {
		this.roleEstados = roleEstados;
	}

}