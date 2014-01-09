<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="itemProcedimiento" value="<%=SystemConstants.ITEM_PROCEDIMIENTO%>" />
<c:set var="itemMedicamento" value="<%=SystemConstants.ITEM_MEDICAMENTO%>" />
<c:set var="itemInsumo" value="<%=SystemConstants.ITEM_INSUMO%>" />

<jsp:include page="./../includes/header.jsp">
    <jsp:param name="includeMenu" value="true" />
</jsp:include>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<div class="row">
    <div class=" col-lg-12">
        <div id="messages"></div>
        <div class="well">
            <form class="form-horizontal" id="informacionForm">
                <button class="btn btn-link btn-sm collapse-button" onclick="collapseContent(this)"
                    title="<fmt:message key='label.colapsarTodos' />"></button>
                <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
                    <strong> <c:if test="${tipoItem == itemMedicamento}">
                            <fmt:message key='label.autorizacionMedicamento' />
                        </c:if> <c:if test="${tipoItem == itemProcedimiento}">
                            <fmt:message key='label.autorizacionProcedimiento' />
                        </c:if>
                        
                        <c:if test="${tipoItem == itemInsumo}">
                            <fmt:message key='label.autorizacionInsumo' />
                        </c:if>
                    </strong>
                </blockquote>
                <div class="collapsible-content">
                    <jsp:include page="./../bandejas/includes/informacionGeneral_Usuario.jsp" />
                </div>
            </form>
        </div>
    </div>
</div>
<div id="alert-area" class="alert-messages col-lg-9-5 row" style="display: none;"></div>

<jsp:include page="./../bandejas/includes/infoSolicitudCTC.jsp" />

<%@include file="./../bandejas/includes/auditorCTCJS.jsp"%>
<%@include file="./../includes/footer.jsp"%>