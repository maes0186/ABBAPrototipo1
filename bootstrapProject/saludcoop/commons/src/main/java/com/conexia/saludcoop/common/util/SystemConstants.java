package com.conexia.saludcoop.common.util;


/**
 * Clase de constantes de la aplicación
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 10/10/2013
 * 
 */
public interface SystemConstants {

    public static final Short SHORT_TRUE = Short.valueOf("1");
    public static final Short SHORT_FALSE = Short.valueOf("0");

    /*
     * CONSTANTES CON LOS NOMBRES DE ROLES DE LA APLICACIÓN
     */

    public static final String ROLE_MEDICO = "ROLE_MEDICO";
    public static final String ROLE_IPS = "ROLE_BACKOFFICE_IPS";
    public static final String ROLE_RECEPCION_IPS = "ROLE_RECEPCION_IPS";
    public static final String ROLE_LINEA_FRENTE = "ROLE_LINEA_FRENTE";
    public static final String ROLE_BACKOFFICE_LDF = "ROLE_BACKOFFICE_LDF";
    public static final String ROLE_AUDITOR_CTC_REG = "ROLE_AUDITOR_CTC_REG";
    public static final String ROLE_AUDITOR_CTC_NAC = "ROLE_AUDITOR_CTC_NAC";
    public static final String ROLE_AUDITOR_AC_REG = "ROLE_AUDITOR_AC_REG";
    public static final String ROLE_AUDITOR_AC_NAC = "ROLE_AUDITOR_AC_NAC";
    public static final String ROLE_CONTACT_SERVICE_REG = "ROLE_CONTACT_SERVICE_REG";
    public static final String ROLE_CONTACT_SERVICE_NAC = "ROLE_CONTACT_SERVICE_NAC";
    public static final String ROLE_AUDITOR_TUTELAS = "ROLE_AUDITOR_TUTELAS";
    public static final String ROLE_CONTACT_CENTER = "ROLE_CONTACT_CENTER";
    public static final String ROLE_AUDITOR_ODONTOLOGO = "ROLE_AUDITOR_ODONTOLOGO";
    public static final String ROLE_AUDITOR_ORTOPEDISTA = "ROLE_AUDITOR_ORTOPEDISTA";
    public static final String ROLE_AUDITOR_OTORRINO = "ROLE_AUDITOR_OTORRINO";
    public static final String ROLE_PROVEEDURIA = "ROLE_PROVEEDURIA";

    /*
     * CONSTANTES PARA EL TIPO PPM
     */
    public static final String PPM_POS = "POS";
    public static final String PPM_NOP = "NOP";
    public static final String PPM_ACO = "ACO";

    /*
     * CONSTANTES PARA TIPO DOCUMENTO SOPORTE
     */
    public static final String DOC_RESUMEN = "RESUMEN";
    public static final String DOC_OTROS = "OTROS";
    public static final String DOC_FORMULARIO_CTC_MEDICAMENTOS = "FORM_CTC_MEDICAMENTO";
    public static final String DOC_FORMULARIO_CTC_PROCEDIMIENTO = "FORM_CTC_PROCEDIMIENTO";
    public static final String DOC_FORMULARIO_CTC_INSUMO = "FORM_CTC_INSUMO";
    public static final String DOC_FORMULARIO_NEGACION_SERVICIO = "FORM_NEGACION_SERV";

    /*
     * CONSTANTES GENERALES
     */
    public static final String DATE_PATTERN_SIMPLE = "dd-MM-yyyy";

    /*
     * CONSTANTES PARA DEFINIR LA ACCIÓN REALIZADA SOBRE LA SOLICITUD, TANTO A NIVEL NACIONAL COMO DEPARTAMENTAL
     */
    public static final int APROBAR = 1;
    public static final int APROBAR_REG = 7;
    public static final int NO_APROBAR = 2;
    public static final int NO_APROBAR_REG = 6;
    public static final int ANULAR = 3;
    public static final int DEVOLVER = 4;
    public static final int RESPONDER = 5;
    public static final int ESCALAR = 8;
    public static final int ENVIAR_TUTELA = 9;

    /*
     * CONSTANTES PARA DIFERENCIAR LOS TIPOS DE ITEM
     */
    public static final int ITEM_MEDICAMENTO = 1;
    public static final int ITEM_PROCEDIMIENTO = 2;
    public static final int ITEM_INSUMO = 3;
    public static final String ITEM_MEDICAMENTO_STRING = "medicamento";
    public static final String ITEM_PROCEDIMIENTO_STRING = "procedimiento";
    public static final String ITEM_INSUMO_STRING = "insumo";

    /*
     * CONSTANTES PARA DIFERENCIAR LOS TIPOS DE BANDEJA
     */
    public static final String BANDEJA_NACIONAL = "nacional";
    public static final String BANDEJA_REGIONAL = "regional";
    public static final String BANDEJA_REDIRECCION_AUTORIZACION = "redireccionAutorizacion";
    public static final String BANDEJA_ANULACION_AUTORIZACION = "anulacionAutorizacion";

    /*
     * CONSTANTES PARA DETERMINAR EL TIPO DE AUDITOR
     */
    public static final int AUDITOR_AC = 1;
    public static final int AUDITOR_CTC = 2;
    public static final int CONTACT_SERVICE = 3;
    public static final int AUDITOR_TUTELAS = 4;
    public static final int LINEA_FRENTE = 5;
    public static final int AUDITOR_ESPECIALIZADO = 6;
    /*
     * CONSTANTES PARA ENTIDADES DE RECOBRO
     */
    public static final int ENTIDAD_RECOBRO_FOSYGA = 1;

    /*
     * CONSTANTE PARA LA INFORMACIÓN DE LA SOLICITUD EN ALTO COSTO
     */
    public static final int ENTIDAD_RECOBRO_AC = 7;
    public static final int FINALIDAD_PROC_AC = 1;
    public static final int FINALIDAD_MEDI_AC = 2;
    public static final int TIPO_CATASTROFICO_PROC_AC = 1;
    public static final int CAUSA_EXTERNA_AC = 13;

    /*
     * CONSTANTE PARA LA INFORMACIÓN DE LA SOLICITUD EN CONTACT SERVICE
     */
    public static final int ENTIDAD_RECOBRO_CS = 7;
    public static final int FINALIDAD_PROC_CS = 1;
    public static final int FINALIDAD_MEDI_CS = 2;
    public static final int TIPO_CATASTROFICO_PROC_CS = 1;
    public static final int CAUSA_EXTERNA_CS = 13;

    /*
     * CONSTANTES DE RESPUESTAS A PREGUNTAS CERRADAS
     */
    public static final String SI = "SI";
    public static final String NO = "NO";
    public static final String NO_SABE = "NO SABE";

    /*
     * CONSTANTES PARA NOMBRES DE BANDEJAS
     */
    public static final String BANDEJA_AC_REG = "bandejaACReg";
    public static final String BANDEJA_AC_NAC = "bandejaACNac";
    public static final String BANDEJA_CS_REG = "bandejaCSReg";
    public static final String BANDEJA_CS_NAC = "bandejaCSNac";
    public static final String BANDEJA_CTC_REG = "bandejaCTCReg";
    public static final String BANDEJA_CTC_NAC = "bandejaCTCNac";
    public static final String BANDEJA_TUTELAS = "bandejaTutelas";
    public static final String BANDEJA_CONTACT_CENTER = "bandejaContactCenter";
    public static final String BANDEJA_ANULACION_AUDITOR = "bandejaAnulacionAuditor";
    public static final String BANDEJA_AUTORIZACIONES = "bandejaAutorizaciones";
    public static final String BANDEJA_IPS_MEDICO = "bandejaIpsMedico";
    public static final String BANDEJA_REDIRECCION_AUDITOR = "bandejaRedireccionAuditor";
    public static final String BANDEJA_ANULACION = "bandejaAnulacion";
    public static final String BANDEJA_REDIRECCION = "bandejaRedireccion";
    public static final String BANDEJA_ESPECIALIZADA = "bandejaEspecializada";
    public static final String BANDEJA_SOLICITUD_PARCIAL = "bandejaSolicitudParcial";
    public static final String BANDEJA_PROVEEDURIA = "bandejaProveeduria";
    
    /**
     * CODIGOS DE ESPECIALIDADES NIVEL 5 AGRUPADAS POR TIPO 
	 * PARA OBTENER TODAS LAS ESPECIALIDADES EN UN List<String> POR TIPO AUDITOR PUENDE USAR
	 * EL METODO ConverterUtil#getListValuesFromSC_ByPrefixName(String, Object)
	 * 
	 * Un Ejemplo está en NivelAutorizadorManager#getEspecialidades()
     */
    
    public static final String ESPECIALIDAD_ODONTOLOGO_1 = "60";
    public static final String ESPECIALIDAD_ODONTOLOGO_2 = "87";
    public static final String ESPECIALIDAD_OTORRINO_1 = "91";
    public static final String ESPECIALIDAD_OTORRINO_2 = "156";
    public static final String ESPECIALIDAD_ORTOPEDISTA_1 = "144";
    public static final String ESPECIALIDAD_ORTOPEDISTA_2 = "155";
    public static final String ESPECIALIDAD_ORTOPEDISTA_3 = "166";
    public static final String ESPECIALIDAD_ORTOPEDISTA_4 = "174";
    public static final String ESPECIALIDAD_ORTOPEDISTA_5 = "182";
    
    /*
     * ACCIONES DE BANDEJAS PARA LDF 
     */
    public final static String REDIRECCIONAMIENTO = "redireccionamiento";
    public final static String ANULAMIENTO = "anulacion";
    
    /*
     * CRUDS 
     */
    
    public static final String PROFESIONAL_CRUD = "profesionalCrud";
    
    /*
     * CONSTANTES AUTHORITIES
     */
    public static final String AUTHORITY_CONSUMO = "BANDEJA_AUTORIZACIONES_CONSUMIR";
    
    public static final String ESTADO_NO_AUTORIZADA = "No Autorizada";
    public static final String ESTADO_AUTORIZADA = "Autorizada";
}
