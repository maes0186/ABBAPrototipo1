<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<c:set var="bandejaACReg" value="<%=SystemConstants.BANDEJA_AC_REG%>" />
<c:set var="bandejaACNac" value="<%=SystemConstants.BANDEJA_AC_NAC%>" />
<c:set var="bandejaCSReg" value="<%=SystemConstants.BANDEJA_CS_REG%>" />
<c:set var="bandejaCSNac" value="<%=SystemConstants.BANDEJA_CS_NAC%>" />
<c:set var="bandejaCTCReg" value="<%=SystemConstants.BANDEJA_CTC_REG%>" />
<c:set var="bandejaCTCNac" value="<%=SystemConstants.BANDEJA_CTC_NAC%>" />
<c:set var="bandejaTutelas" value="<%=SystemConstants.BANDEJA_TUTELAS%>" />
<c:set var="bandejaContactCenter" value="<%=SystemConstants.BANDEJA_CONTACT_CENTER%>" />
<c:set var="bandejaAnulacionAuditor" value="<%=SystemConstants.BANDEJA_ANULACION_AUDITOR%>" />
<c:set var="bandejaAutorizaciones" value="<%=SystemConstants.BANDEJA_AUTORIZACIONES%>" />
<c:set var="bandejaIpsMedico" value="<%=SystemConstants.BANDEJA_IPS_MEDICO%>" />
<c:set var="bandejaRedireccionAuditor" value="<%=SystemConstants.BANDEJA_REDIRECCION_AUDITOR%>" />
<c:set var="bandejaProveeduria" value="<%=SystemConstants.BANDEJA_PROVEEDURIA%>" />
<c:set var="bandejaEspecializada" value="<%=SystemConstants.BANDEJA_ESPECIALIZADA%>" />
<c:set var="bandejaSolicitudParcial" value="<%=SystemConstants.BANDEJA_SOLICITUD_PARCIAL%>" />

<c:if test="${nombreBandeja == bandejaSolicitudParcial}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
       <jsp:param name="esIps" value="true" />
        <jsp:param name="esAutorizacion" value="true" />
        <jsp:param name="esRedireccion" value="false" />
        <jsp:param name="esAnulacion" value="false" />
        <jsp:param name="esSolicitudParcial" value="true"/>
        <jsp:param name="titulo" value="Solicitud Parcial"/>
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaACNac || nombreBandeja == bandejaACReg}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esIps" value="false" />
        <jsp:param name="esSolicitudParcial" value="false"/>
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaCSNac || nombreBandeja == bandejaCSReg}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esIps" value="false" />
        <jsp:param name="esSolicitudParcial" value="false"/>
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaCTCNac || nombreBandeja == bandejaCTCReg}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esIps" value="false" />
        <jsp:param name="esSolicitudParcial" value="false"/>
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaTutelas}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="tutelas" value="true" />
        <jsp:param name="esSolicitudParcial" value="false"/>
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaContactCenter}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esIps" value="false" />
        <jsp:param name="esSolicitudParcial" value="false"/>
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaAnulacionAuditor}">
    <fmt:message key="label.bandeja.consultaSolicitudesAnulacion" var="TITULO" />
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esAutorizacion" value="true" />
        <jsp:param name="esSolicitudParcial" value="false"/>
        <jsp:param name="titulo" value="${TITULO}" />
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaRedireccionAuditor}">
    <fmt:message key="label.bandeja.consultaSolicitudesRedireccion" var="TITULO" />
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esAutorizacion" value="true" />
        <jsp:param name="titulo" value="${TITULO}" />
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaProveeduria}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esAutorizacion" value="true" />
        <jsp:param name="titulo" value="Consulta de Autorizaciones" />
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaAutorizaciones}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esIps" value="true" />
        <jsp:param name="esAutorizacion" value="true" />
        <jsp:param name="titulo" value="Consulta de Autorizaciones" />
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaIpsMedico}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esIps" value="true" />
        <jsp:param name="esSolicitudParcial" value="false"/>
    </jsp:include>
</c:if>
<c:if test="${nombreBandeja == bandejaEspecializada}">
    <jsp:include page="./../bandejas/includes/bandejaFiltros.jsp">
        <jsp:param name="esIps" value="false" />
        <jsp:param name="esSolicitudParcial" value="false"/>
    </jsp:include>
</c:if>
<script>
    function gestionar(numItem) {
        location.href = "${webContext}/bandejas/gestionarItem/" + numItem;
    }
</script>