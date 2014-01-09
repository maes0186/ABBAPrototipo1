package com.conexia.saludcoop.validador.businessRules;

import java.util.List;

import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.entity.maestro.EspecialidadInsumo;
import com.conexia.saludcoop.common.entity.maestro.NivelAutorizacion;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.common.util.RoleUtils;
import com.conexia.saludcoop.common.util.SystemConstants;

public class NivelAutorizadorManager {
	public RoleDTO obtenerRolPorMedicamento(Boolean altoCosto, Boolean visibleCtc, Boolean tutela) {
		RoleDTO roleDto = new RoleDTO();

		// Si aplica tutela, significa que la solicitud es creada desde la IPS,
		// por lo que se debe
		// redireccional al rol de línea de frente
		if (Boolean.TRUE.equals(tutela)) {
			roleDto.setId(ConstantesTarget.ROLE_LINEA_FRENTE);
			return roleDto;
		} else {
			if (visibleCtc) {
				roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
				return roleDto;
			} else if (altoCosto) {
				roleDto.setId(ConstantesTarget.ROLE_AUDITOR_AC_NAC);
				return roleDto;
			} else {
				roleDto.setId(ConstantesTarget.SISTEMA);
				return roleDto;
			}
		}

	}

	public RoleDTO obtenerRolPorProcedimiento(Integer nivelAutorizacion, Boolean tutela, String codigoEspecialidad, String codigoTipoPPM) throws Exception {
		RoleDTO roleDto = new RoleDTO();

		// Si aplica tutela, significa que la solicitud es creada desde la IPS,
		// por lo que se debe
		// redireccional al rol de línea de frente
		if (Boolean.TRUE.equals(tutela)) {
			roleDto.setId(ConstantesTarget.ROLE_LINEA_FRENTE);
		} else {
			switch (nivelAutorizacion) {
			case NivelAutorizacion.NIVEL_1:
			case NivelAutorizacion.NIVEL_2:
				roleDto.setId(ConstantesTarget.SISTEMA);
				break;
			case NivelAutorizacion.NIVEL_3:
				roleDto.setId(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC);
				break;
			case NivelAutorizacion.NIVEL_4:
				roleDto.setId(ConstantesTarget.ROLE_AUDITOR_AC_NAC);
				break;
			case NivelAutorizacion.NIVEL_5:
				if (SystemConstants.PPM_POS.equals(codigoTipoPPM) || codigoTipoPPM == null) {
					if (RoleUtils.getEspecialidadesNivel5().get("espOdontologo").contains(codigoEspecialidad)) {
						roleDto.setId(ConstantesTarget.ROLE_AUDITOR_ODONTOLOGO);
					} else if (RoleUtils.getEspecialidadesNivel5().get("espOrtopedista").contains(codigoEspecialidad)) {
						roleDto.setId(ConstantesTarget.ROLE_AUDITOR_ORTOPEDISTA);
					} else if (RoleUtils.getEspecialidadesNivel5().get("espOtorrino").contains(codigoEspecialidad)) {
						roleDto.setId(ConstantesTarget.ROLE_AUDITOR_OTORRINO);
					} else if (codigoTipoPPM != null) {
						roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
					} else {
						roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_NAC);
					}
				} else {
					roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
				}
				break;

			default:
				// TODO: Crear excepecion
				throw new Exception();
			}
		}
		return roleDto;
	}

	public RoleDTO obtenerRolPorInsumo(Integer nivelAutorizacion, Boolean altoCosto, Boolean visibleCtc, Boolean tutela, String codigoTipoPPM, Iterable<EspecialidadInsumo> especialidades, 
	        Boolean superoTope) throws Exception {
		RoleDTO roleDto = new RoleDTO();

		// Si aplica tutela, significa que la solicitud es creada desde la IPS,
		// por lo que se debe
		// redireccional al rol de línea de frente
		if (Boolean.TRUE.equals(tutela)) {
			roleDto.setId(ConstantesTarget.ROLE_LINEA_FRENTE);
			return roleDto;
		} else {
		    if(superoTope != null && Boolean.FALSE.equals(superoTope)){
		        roleDto.setId(ConstantesTarget.SISTEMA);
		        return roleDto;
		    }
			if (nivelAutorizacion != null && !nivelAutorizacion.equals(0) ) {
				switch (nivelAutorizacion) {
				case NivelAutorizacion.NIVEL_1:
				case NivelAutorizacion.NIVEL_2:
					roleDto.setId(ConstantesTarget.SISTEMA);
					break;
				case NivelAutorizacion.NIVEL_3:
					roleDto.setId(ConstantesTarget.ROLE_CONTACT_SERVICE_NAC);
					break;
				case NivelAutorizacion.NIVEL_4:
					roleDto.setId(ConstantesTarget.ROLE_AUDITOR_AC_NAC);
					break;
				case NivelAutorizacion.NIVEL_5:
					if (SystemConstants.PPM_POS.equals(codigoTipoPPM) || codigoTipoPPM == null) {
                        if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOdontologo"), especialidades)) {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_ODONTOLOGO);
                        } else if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOrtopedista"), especialidades)) {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_ORTOPEDISTA);
                        } else if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOtorrino"), especialidades)) {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_OTORRINO);
                        } else if (codigoTipoPPM != null) {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                        } else {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_NAC);
                        }
                    } else {
                        roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                    }
					break;

				default:
					
					throw new Exception();
				}
			} else{
				if (visibleCtc) {
				    if (SystemConstants.PPM_POS.equals(codigoTipoPPM) || codigoTipoPPM == null) {
                        if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOdontologo"), especialidades)) {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_ODONTOLOGO);
                        } else if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOrtopedista"), especialidades)) {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_ORTOPEDISTA);
                        } else if (perteneEspecialidad(RoleUtils.getEspecialidadesNivel5().get("espOtorrino"), especialidades)) {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_OTORRINO);
                        } else if (codigoTipoPPM != null) {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                        } else {
                            roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_NAC);
                        }
                    } else {
                        roleDto.setId(ConstantesTarget.ROLE_AUDITOR_CTC_REG);
                    }
					return roleDto;
				} else if (altoCosto) {
					roleDto.setId(ConstantesTarget.ROLE_AUDITOR_AC_NAC);
					return roleDto;
				} else {
					roleDto.setId(ConstantesTarget.SISTEMA);
					return roleDto;
				}
			}
		}
		return roleDto;
	}
	
    /**
     * Itera las especialidades del insumo sobre las especialidades nivel 5 para comprobar si pertenece.
     * @param especialidades
     * @param especialidadesInsumos
     * @return
     */
    
    private boolean perteneEspecialidad(List<String> especialidades, Iterable<EspecialidadInsumo> especialidadesInsumos) {
        boolean respuesta = false;
        for (EspecialidadInsumo especialidadInsumo : especialidadesInsumos) {
            for (String especialidad : especialidades) {
                if (especialidadInsumo.getEspecialidad().getCodigo().equals(especialidad)) {
                    return true;
                }
            }
        }
        return respuesta;
    }
}
