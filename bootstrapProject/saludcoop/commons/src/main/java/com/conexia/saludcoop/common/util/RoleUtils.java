package com.conexia.saludcoop.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class RoleUtils {
    /*
     * LISTA DE CONSTANTES CON LAS ESPECIALIDADES SEGUN TIPO PARA
     */
    public static Map<String, List<String>> getEspecialidadesNivel5() {
        Map<String, List<String>> hm = new HashMap<String, List<String>>();
        hm.put("espOdontologo", ConverterUtil.getListValuesFromSC_ByPrefixName("ESPECIALIDAD_ODONTOLOGO", new String()));
        hm.put("espOtorrino", ConverterUtil.getListValuesFromSC_ByPrefixName("ESPECIALIDAD_OTORRINO", new String()));
        hm.put("espOrtopedista", ConverterUtil.getListValuesFromSC_ByPrefixName("ESPECIALIDAD_ORTOPEDISTA", new String()));
        return hm;
    }
    
    public static List<Integer> getListID_ROLE_EspecialidadesNivel5(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(ConstantesTarget.ROLE_AUDITOR_ORTOPEDISTA);
        list.add(ConstantesTarget.ROLE_AUDITOR_ODONTOLOGO);
        list.add(ConstantesTarget.ROLE_AUDITOR_OTORRINO);
        return list;
    } 
    
    public static List<String> getListStr_Roles_EspecialidadesNivel5(){
        List<String> list = new ArrayList<String>();
        list.add(SystemConstants.ROLE_AUDITOR_ODONTOLOGO);
        list.add(SystemConstants.ROLE_AUDITOR_OTORRINO);
        list.add(SystemConstants.ROLE_AUDITOR_ORTOPEDISTA);
        return list;
    } 

    public static List<Integer> getRolesExcepcion() {
        List<Integer> roles = new Vector<>();
        roles.add(ConstantesTarget.ROLE_MEDICO);
        roles.add(ConstantesTarget.ROLE_BACKOFFICE_IPS);
        roles.add(ConstantesTarget.ROLE_BACKOFFICE_LDF);
        roles.add(ConstantesTarget.ROLE_RECEPCION_IPS);
        roles.add(ConstantesTarget.ROLE_LINEA_FRENTE);
        roles.add(ConstantesTarget.ROLE_CONTACT_CENTER);
        return roles;
    }
    
    public static boolean esRoleLDF(List<Integer> roleIds) {
        List<Integer> roles = new Vector<>();
        roles.add(ConstantesTarget.ROLE_BACKOFFICE_LDF);
        roles.add(ConstantesTarget.ROLE_LINEA_FRENTE);
        roles.add(ConstantesTarget.ROLE_CONTACT_CENTER);
        
        for (Integer roleId : roleIds) {
            if(roles.contains(roleId)) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> getRolesLDF() {
        List<Integer> roles = new Vector<>();
        roles.add(ConstantesTarget.ROLE_BACKOFFICE_LDF);
        roles.add(ConstantesTarget.ROLE_LINEA_FRENTE);
        roles.add(ConstantesTarget.ROLE_CONTACT_CENTER);
        
        return roles;
    }
    
    public static boolean esRoleIPS(List<Integer> roleIds) {
        List<Integer> roles = new Vector<>();
        roles.add(ConstantesTarget.ROLE_MEDICO);
        roles.add(ConstantesTarget.ROLE_BACKOFFICE_IPS);
        roles.add(ConstantesTarget.ROLE_RECEPCION_IPS);
        
        for (Integer roleId : roleIds) {
            if(roles.contains(roleId)) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> getRolesIPS() {
        List<Integer> roles = new Vector<>();
        roles.add(ConstantesTarget.ROLE_MEDICO);
        roles.add(ConstantesTarget.ROLE_BACKOFFICE_IPS);
        roles.add(ConstantesTarget.ROLE_RECEPCION_IPS);
        
        return roles;
    }
    
    /**
     * Lista de Roles Permitidos para ver autorizaciones de Redireccionamiento
     * @return
     */
    
    public static List<String> getRolesAllowedSeeRedireccion(){
    	List<String> list = new Vector<>();
    	list.add(SystemConstants.ROLE_AUDITOR_CTC_REG);
    	list.addAll(getListStr_Roles_EspecialidadesNivel5());
    	return list;
    }
    
    /**
     * Lista de Roles Permitidos para ver autorizaciones de Anulamiento
     * @return
     */
    public static List<String> getRolesAllowedSeeAnulacion(){
    	List<String> list = new Vector<>();
    	list.add(SystemConstants.ROLE_AUDITOR_CTC_REG);
    	list.addAll(getListStr_Roles_EspecialidadesNivel5());
    	list.add(SystemConstants.ROLE_CONTACT_SERVICE_NAC);
    	list.add(SystemConstants.ROLE_AUDITOR_AC_NAC);
    	return list;
    }
}
