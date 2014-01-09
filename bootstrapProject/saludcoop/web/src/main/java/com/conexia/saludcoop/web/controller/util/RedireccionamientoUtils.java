package com.conexia.saludcoop.web.controller.util;

import java.util.LinkedHashMap;

import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.entity.maestro.NivelAutorizacion;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.web.vo.BandejaSubItemProjVO;


public class RedireccionamientoUtils {
	
	private final static String TARGET="target";
	private final static String ID="id";
	private final static String DESCRIPCION="descripcion";
	private final static String ESTADO="estadoAutorizacion";
    public static final int BANDEJA_AC = 1;
    public static final int BANDEJA_CTC = 2;
    public static final int BANDEJA_CS = 4;
    public static final int BANDEJA_IPS_PRESTADOR = 3;
    public static final int BANDEJA_CONTACT_CENTER = 5;
    public static final String REDIRECCION="REDIRECCION";
    public static final String ESCALAR="ESCALAR";
    public static final String REDIRECCION_ESCALAR="REDIRECCION_ESCALAR";
    public static final String ERROR_REGISTRO_MEDICO="message.error.registroMedico";
    public static final String ERROR_DOCUMENTO="message.error.numeroDocumento";
    public static final String NUEVO_ELEMENTO="NUEVO";
	
	
	@SuppressWarnings("unchecked")
	public static RoleDTO obtenerRol(LinkedHashMap<String, Object> mapa){
		RoleDTO roleDTO = new RoleDTO();
		if((LinkedHashMap<String, Object>)mapa.get(TARGET)==null)return null;
		Integer idRole=(Integer) ((LinkedHashMap<String, Object>)mapa.get(TARGET)).get(ID);
		String descripcion=(String) ((LinkedHashMap<String, Object>)mapa.get(TARGET)).get(DESCRIPCION);
		roleDTO.setDescripcion(descripcion);
		roleDTO.setId(idRole);
		return roleDTO;
	}
	@SuppressWarnings("unchecked")
	public static Integer obtenerEstado(LinkedHashMap<String, Object> mapa){
		if((LinkedHashMap<String, Object>)mapa.get(ESTADO)==null)return null;
		Integer idRole=(Integer) ((LinkedHashMap<String, Object>)mapa.get(ESTADO)).get(ID);
		return idRole;
	}
	/**
	 * 
	 * @param insumo
	 * @param medicamento
	 * @param procedimiento
	 * @param subitem
	 * @param tipoBandeja
	 */
	public static void obtenerNivelAutorizador(InsumoDto insumo,
			MedicamentoDto medicamento, ProcedimientoDto procedimiento,
			BandejaSubItemProjVO subitem, String tipoBandeja) {
		switch (tipoBandeja) {
		case SystemConstants.REDIRECCIONAMIENTO:
			if (subitem.getIdMedicamento() != null) {
				if (medicamento.isVisibleCtc()) {
					subitem.setEsNivelAutorizacionAuditor(true);
				} else {
					subitem.setEsNivelAutorizacionAuditor(false);
				}
			} else if (subitem.getIdProcedimiento() != null) {
				switch (procedimiento.getNivelDeAutorizacion()) {
				case NivelAutorizacion.NIVEL_5:
					subitem.setEsNivelAutorizacionAuditor(true);
					break;

				default:
					subitem.setEsNivelAutorizacionAuditor(false);
					break;
				}
			} else if (subitem.getIdInsumo() != null) {
			    if (insumo.getNivelDeAutorizacion() != null && insumo.getNivelDeAutorizacion().intValue() != 0 ) {
	                 switch (insumo.getNivelDeAutorizacion()) {
	                    case 5:
	                        subitem.setEsNivelAutorizacionAuditor(true);
	                        break;

	                    default:
	                        subitem.setEsNivelAutorizacionAuditor(false);
	                        break;
	                    }
			    }else{
			        if(insumo.isVisibleCtc()){
			            subitem.setEsNivelAutorizacionAuditor(true);
			        }else{
			            subitem.setEsNivelAutorizacionAuditor(false);
			        }
			    }
			}
			break;
		case SystemConstants.ANULAMIENTO:
			if (subitem.getIdMedicamento() != null) {
				if (medicamento.isVisibleCtc()) {
					subitem.setEsNivelAutorizacionAuditor(true);
				} else if (medicamento.isAltoCosto()) {
					subitem.setEsNivelAutorizacionAuditor(true);
				} else {
					subitem.setEsNivelAutorizacionAuditor(false);
				}
			} else if (subitem.getIdProcedimiento() != null) {
				switch (procedimiento.getNivelDeAutorizacion()) {
				case NivelAutorizacion.NIVEL_3:
				case NivelAutorizacion.NIVEL_4:
				case NivelAutorizacion.NIVEL_5:
					subitem.setEsNivelAutorizacionAuditor(true);
					break;

				default:
					subitem.setEsNivelAutorizacionAuditor(false);
					break;
				}
			} else if (subitem.getIdInsumo() != null) {
				switch (insumo.getNivelDeAutorizacion()) {
				case NivelAutorizacion.NIVEL_3:
				case NivelAutorizacion.NIVEL_4:
				case NivelAutorizacion.NIVEL_5:
					subitem.setEsNivelAutorizacionAuditor(true);
					break;

				default:
					subitem.setEsNivelAutorizacionAuditor(false);
					break;
				}
			}
			break;

		default:
			break;
		}
	}
	

}
