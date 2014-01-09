<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<c:set var="profesionalCrud" value="<%=SystemConstants.PROFESIONAL_CRUD%>" />
<fmt:message key='label.titulo.profesional' var="TITULO_PROFESIONAL" />


<c:if test="${nombreCrud == profesionalCrud}">
    <jsp:include page="./../crud/includes/crudFiltros.jsp">
        <jsp:param name="esProfesional" value="true" />
        <jsp:param name="TITULO" value="${TITULO_PROFESIONAL}" />
    </jsp:include>
</c:if>
<script>
    function editarElemento(numItem) {
    	if(numItem==null){
    		location.href = "${webContext}/profesionalCrud/gestionarCreacion";
    	}
    	else {
    		location.href = "${webContext}/profesionalCrud/gestionarEdicion/"+ numItem;
    	}
    }
</script>