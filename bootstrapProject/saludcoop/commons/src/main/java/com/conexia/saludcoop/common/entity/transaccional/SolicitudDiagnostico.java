package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ar.com.conexia.common.persistence.entity.Identifiable;
import com.conexia.saludcoop.common.entity.maestro.Diagnostico;

/**
 * Entidad que relaciona Diagnostico con la Solicitud
 * @author fcostazini
 *
 */
@Entity
@Table(name = "solicitud_diagnostico", schema = "transaccional")
public class SolicitudDiagnostico implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name="diagnostico_id", nullable=false)
	private Diagnostico diagnostico;
	
//	@OneToMany(mappedBy="diagnostico", fetch=FetchType.EAGER)
//	private Set<SolicitudItem> solicitudItems = new HashSet<>();
	
	@Column(name="es_principal", nullable=false)
	private Boolean esPrincipal = false;

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public boolean isEsPrincipal() {
		return esPrincipal;
	}

	public void setEsPrincipal(boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}

	public Long getId() {
		return id;
	}

//	public Set<SolicitudItem> getSolicitudItems() {
//		return solicitudItems;
//	}
//
//	public void setSolicitudItems(Set<SolicitudItem> solicitudItems) {
//		this.solicitudItems = solicitudItems;
//	}
//
//	public void addSolicitudItem(SolicitudItem solicitudItem) {
//		
//			if(this.solicitudItems == null){
//				this.solicitudItems = new HashSet<SolicitudItem>();
//			}
//			this.solicitudItems.add(solicitudItem);
//			solicitudItem.setDiagnostico(this);
//		
//	}
//	


}
