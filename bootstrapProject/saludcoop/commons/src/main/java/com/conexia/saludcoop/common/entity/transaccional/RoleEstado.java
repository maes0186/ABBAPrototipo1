package com.conexia.saludcoop.common.entity.transaccional;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.EstadoVisible;
import com.conexia.saludcoop.common.entity.security.Role;
@Entity
@Table(name="role_estado_visible", schema="transaccional", 
uniqueConstraints = {
		@UniqueConstraint(columnNames = { "role_id","estado_visible_id"})
		})
public class RoleEstado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
		
	@ManyToOne
	@JoinColumn(name="role_id", nullable=false)
	private Role role;
	@ManyToOne
	@JoinColumn(name="estado_visible_id", nullable=false)
	private EstadoVisible estadoVisible;

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="role_estado_estados",schema="transaccional",
	joinColumns={@JoinColumn(name="role_estado_id",nullable = false, updatable = false)},
	inverseJoinColumns={
			@JoinColumn(name="estado_autorizacion_id",nullable = false, updatable = false)})
	private Set<EstadoAutorizacion> estadosAutorizacion;

	public Set<EstadoAutorizacion> getEstadosAutorizacion() {
		return estadosAutorizacion;
	}

	public void setEstadosAutorizacion(Set<EstadoAutorizacion> estadosAutorizacion) {
		this.estadosAutorizacion = estadosAutorizacion;
	}

	public Integer getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public EstadoVisible getEstadoVisible() {
		return estadoVisible;
	}

	public void setEstadoVisible(EstadoVisible estadoVisible) {
		this.estadoVisible = estadoVisible;
	}

	
	
}
