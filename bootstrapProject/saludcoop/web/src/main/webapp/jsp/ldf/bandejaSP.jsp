
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%-- <fmt:message key='label.select' var="SELECCIONE_LABEL" /> --%>
<%-- <jsp:include page="../includes/header.jsp"> --%>
<%-- 	<jsp:param name="includeMenu" value="true" /> --%>
<%-- </jsp:include> --%>
<jsp:include page="./..//bandejas/includes/bandejaFiltros.jsp">
    <jsp:param name="esIps" value="true" />
    <jsp:param name="esAutorizacion" value="true" />
    <jsp:param name="esRedireccion" value="false" />
    <jsp:param name="esAnulacion" value="false" />
    <jsp:param name="esAnulacion" value="false" />
    <jsp:param name="esSolicitudParcial" value="true"/>
    <jsp:param name="titulo" value="Solicitud Parcial"/>
</jsp:include>

<jsp:include page="../includes/components.jsp" />	
<c:set var="webContext" value="${pageContext.request.contextPath}" />

<jsp:include page="../includes/footer.jsp" />

<script>

$(document).ready(function(){
	configABMComponents();	 
});


function verSolicitud(numItem){
	location.href="${webContext}/prestador/cargarSolicitud/"+numItem;
}
var urlAbsoluta = '${webContext}';

</script>