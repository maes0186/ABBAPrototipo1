package com.conexia.saludcoop.common.dto.transaccional;
/**
 * Clase encargada del redireccionamiento
 * @author mortega
 *
 */
public class GestionItemRedir_AnulaDto {
	
	private Long idAutorizacion;
	private Long idSolicitudItem;
	private Long idSedeIps;
	private Boolean esGrupo;
	private Boolean esParaAuditor;
	private String justificacion;
	private Integer roleId;
	
	public Long getIdSolicitudItem() {
		return idSolicitudItem;
	}

	public void setIdSolicitudItem(Long idSolicitudItem) {
		this.idSolicitudItem = idSolicitudItem;
	}

	public Long getIdSedeIps() {
		return idSedeIps;
	}

	public void setIdSedeIps(Long idSedeIps) {
		this.idSedeIps = idSedeIps;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public Boolean getEsGrupo() {
		return esGrupo;
	}

	public void setEsGrupo(Boolean esGrupo) {
		this.esGrupo = esGrupo;
	}

	public Long getIdAutorizacion() {
		return idAutorizacion;
	}

	public void setIdAutorizacion(Long idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}

	public Boolean getEsParaAuditor() {
		return esParaAuditor;
	}

	public void setEsParaAuditor(Boolean esParaAuditor) {
		this.esParaAuditor = esParaAuditor;
	}

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
