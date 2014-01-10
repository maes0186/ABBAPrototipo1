package com.conexia.saludcoop.web.xls;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.conexia.saludcoop.common.entity.maestro.NivelAutorizacion;
import com.conexia.saludcoop.common.enumerator.OrigenSolicitud;

import static com.conexia.saludcoop.common.util.SystemConstants.SHORT_TRUE;
import static com.conexia.saludcoop.common.util.SystemConstants.SHORT_FALSE;

public class BandejaXls {
	
	private final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	private Integer solId;
	private Long solItemId;
	private String regional;
	private Date fecha;
	private String tipoDocumento;
	private String documento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String medicamento;
	private String procedimiento;
	private String insumo;
	private String diagnostico;
	private String conceptoAnulacion;
	private String conceptoDevolucion;
	private String estado;
	private String ambito;
	private Short mVisibleCtc;
	private Short mAltoCosto;
	private Short mIsInsumo;
	private Short iVisibleCtc;
	private Short iAltoCosto;
	private Short iIsInsumo;
	private Integer nivel;
	private String entidad;
	private String eps;
	private String origino;
	private String tipoCTC; 
	private String prioridad;
	private String tipoPrioridad;
	
	
	
	public BandejaXls() {
		// TODO Auto-generated constructor stub
	}

	public Integer getSolId() {
		return solId;
	}

	public void setSolId(Integer solId) {
		this.solId = solId;
	}

	public Long getSolItemId() {
		return solItemId;
	}
	public void setSolItemId(Long solItemId) {
		this.solItemId = solItemId;
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	public String getFecha() {
		return BandejaXls.SDF.format(this.fecha) ;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getUsuario() {
		return (this.primerNombre + " " + this.segundoNombre + " " + this.primerApellido + " " + this.segundoApellido);
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}
	public void setInsumo(String insumo) {
		this.insumo = insumo;
	}
	public String getTecnologia() {
		if(this.medicamento != null){
			return this.medicamento;
		}else if(this.procedimiento != null){
			return this.procedimiento;
		}else if( this.insumo != null){
			return this.insumo;
		}else{
			return null;	
		}
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public void setConceptoAnulacion(String conceptoAnulacion) {
		this.conceptoAnulacion = conceptoAnulacion;
	}
	public void setConceptoDevolucion(String conceptoDevolucion) {
		this.conceptoDevolucion = conceptoDevolucion;
	}
	public String getConceptoCTC() {
		if(this.conceptoAnulacion != null){
			return "ANULADO";
		}else if(this.conceptoDevolucion != null){
			return "DEVUELTO";
		}else{
			return "APROBADO";	
		}
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	
	public Short getmVisibleCtc() {
		return mVisibleCtc;
	}

	public void setmVisibleCtc(Short mVisibleCtc) {
		this.mVisibleCtc = mVisibleCtc;
	}

	public Short getmAltoCosto() {
		return mAltoCosto;
	}

	public void setmAltoCosto(Short mAltoCosto) {
		this.mAltoCosto = mAltoCosto;
	}

	public Short getmIsInsumo() {
		return mIsInsumo;
	}

	public void setmIsInsumo(Short mIsInsumo) {
		this.mIsInsumo = mIsInsumo;
	}

	public Short getiVisibleCtc() {
		return iVisibleCtc;
	}

	public void setiVisibleCtc(Short iVisibleCtc) {
		this.iVisibleCtc = iVisibleCtc;
	}

	public Short getiAltoCosto() {
		return iAltoCosto;
	}

	public void setiAltoCosto(Short iAltoCosto) {
		this.iAltoCosto = iAltoCosto;
	}

	public Short getiIsInsumo() {
		return iIsInsumo;
	}

	public void setiIsInsumo(Short iIsInsumo) {
		this.iIsInsumo = iIsInsumo;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getOrigen() {
		String origen = "";
		if (medicamento != null) {
			if (SHORT_FALSE.equals(mVisibleCtc) && SHORT_FALSE.equals(mAltoCosto)) {
				origen = OrigenSolicitud.POS.getCode();
			} else if (SHORT_TRUE.equals(mVisibleCtc) && SHORT_FALSE.equals(mAltoCosto)) {
				origen = OrigenSolicitud.CTC.getCode();
			} else if (SHORT_TRUE.equals(mAltoCosto) && SHORT_TRUE.equals(mIsInsumo)) {
				origen = OrigenSolicitud.INSUMOS_ALTO_COSTO.getCode();
			}
		} else if (procedimiento != null) {
			if (nivel == NivelAutorizacion.NIVEL_1 || nivel == NivelAutorizacion.NIVEL_2) {
				origen = OrigenSolicitud.POS.getCode();
			} else if (nivel == 5) {
				origen = OrigenSolicitud.CTC.getCode();
			}

		} else if (insumo != null) {
			if (SHORT_FALSE.equals(iVisibleCtc) && SHORT_FALSE.equals(iAltoCosto)) {
				origen = OrigenSolicitud.POS.getCode();
			} else if (SHORT_TRUE.equals(iVisibleCtc) && SHORT_FALSE.equals(iAltoCosto)) {
				origen = OrigenSolicitud.CTC.getCode();
			} else if (SHORT_TRUE.equals(iAltoCosto) && SHORT_TRUE.equals(iIsInsumo)) {
				origen = OrigenSolicitud.INSUMOS_ALTO_COSTO.getCode();
			}
		}

		return origen;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getEps() {
		return eps;
	}
	public void setEps(String eps) {
		this.eps = eps;
	}
	public String getOrigino() {
		return origino;
	}
	public void setOrigino(String origino) {
		this.origino = origino;
	}
	public String getTipoCTC() {
		return tipoCTC;
	}
	public void setTipoCTC(String tipoCTC) {
		this.tipoCTC = tipoCTC;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getTipoPrioridad() {
		return tipoPrioridad;
	}
	public void setTipoPrioridad(String tipoPrioridad) {
		this.tipoPrioridad = tipoPrioridad;
	}
	
	

}
