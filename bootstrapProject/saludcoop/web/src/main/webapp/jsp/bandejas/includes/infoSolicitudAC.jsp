<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="itemProcedimiento" value="<%= SystemConstants.ITEM_PROCEDIMIENTO_STRING %>" />
<c:set var="itemMedicamento" value="<%= SystemConstants.ITEM_MEDICAMENTO_STRING %>" />
<c:set var="itemInsumo" value="<%= SystemConstants.ITEM_INSUMO_STRING %>" />
<c:set var="tipoBandejaRegional" value="<%= SystemConstants.BANDEJA_REGIONAL %>" />

<c:set var="webContext" value="${pageContext.request.contextPath}" />

<c:if test="${bandejasParam.tipoBandeja == tipoBandejaRegional}">
    <c:set var="disabledInput" value="disabled=disabled" />
    <c:set var="disabledSelect" value="true" />
</c:if>

<fmt:message key='label.direccionamientoIps' var="DIR_IPS_LABEL" />
<fmt:message key='label.observacionesLineaFrente' var="OBSERVACIONES_LF_LABEL" />
<fmt:message key='label.docComplementaria' var="DOC_COMP_LABEL" />
<fmt:message key='label.listadoDocAdjuntos' var="DOC_ADJ_LABEL" />
<fmt:message key='label.button.adjuntarDocumento' var="ADJ_DOC_LABEL" />
<fmt:message key='label.hagaClick' var="HAGA_CLICK_LABEL" />
<fmt:message key='label.numeroEntregas' var="NUMEROS_ENTREGA" />

<c:if test="${bandejasParam.editable}">
    <c:set var="disabledInput" value="" />
</c:if>
<c:if test="${!bandejasParam.editable}">
    <c:set var="disabledInput" value="disabled=disabled" />
</c:if>

<div class="row">
    <div class=" col-lg-12">
        <div id="messages"></div>
        <div class="form-horizontal">
            <form class="form-horizontal" id="formInfoSolicitudAC" method="post" enctype="multipart/form-data">
                <input type="hidden" value="${itemId}" id="itemId" name="itemId" />
                <jsp:include page="./informacionSolicitud.jsp">
                    <jsp:param value="${disabledInput}" name="disableInput" />
                    <jsp:param value="true" name="esAC" />
                </jsp:include>
                <fieldset id="fieldSetInfoDiagnosticoAC" class="well">
                    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                        <strong><fmt:message key="label.bandeja.infoDiagnostico" /></strong>
                    </blockquote>
                    <div class="collapsible-content">
                        <jsp:include page="./diagnostico.jsp">
                            <jsp:param value="${bandejasParam.editable}" name="editable" />
                        </jsp:include>
                    </div>
                </fieldset>

                <fieldset disabled="disabled" class="well">
                    <c:if test="${bandejaAC.tipoItem == itemMedicamento}">
                        <jsp:include page="./infoMedicamentoAC.jsp" />
                    </c:if>

                    <c:if test="${bandejaAC.tipoItem == itemProcedimiento}">
                        <jsp:include page="./infoProcedimientoAC.jsp" />
                    </c:if>
                    <c:if test="${bandejaAC.tipoItem == itemInsumo}">
                        <jsp:include page="./infoInsumoAC.jsp" />
                    </c:if>
                </fieldset>

                <jsp:include page="./direccionamientoIps.jsp">
                    <jsp:param value="${bandejasParam.editableRedireccion}" name="editable" />
                </jsp:include>

                <fieldset class="well">
                    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                        <strong><fmt:message key="label.infoComplementaria" /></strong>
                    </blockquote>
                    <div class="collapsible-content" style="padding-left: 20px;">
                        <div class="form-group form-group-sm">
                            <label class="control-label control-label-sm text-right col-lg-6" style="text-align: left">${DOC_ADJ_LABEL}</label>
                        </div>
                        <div class="form-group form-group-sm">
                            <div class="col-lg-6">
                                <jsp:include page="./docAdjuntos.jsp">
                                    <jsp:param name="docAdjuntosName" value="docAdjuntos" />
                                </jsp:include>
                            </div>
                            <div class="col-lg-6">
                                <jsp:include page="./docSoporte.jsp">
                                    <jsp:param value="archivosAC" name="tipoArchivo" />
                                    <jsp:param value="docAdjuntos" name="divId" />
                                    <jsp:param value="${DOC_COMP_LABEL}-${HAGA_CLICK_LABEL}" name="headerMessage" />
                                    <jsp:param value="${!bandejasParam.editable}" name="disabled" />
                                </jsp:include>
                            </div>
                        </div>

                        <div class="form-group form-group-sm">
                            <c:if test="${bandejaAC.devueltaLF}">
                                <label for="observacionesLF" class="control-label control-label-sm text-right col-lg-6"
                                    style="text-align: left">${OBSERVACIONES_LF_LABEL}</label>
                            </c:if>
                        </div>
                        <div class="form-group form-group-sm">
                            <div class="col-lg-6">
                                <c:if test="${bandejaAC.devueltaLF}">
                                    <textarea class="form-control input-sm" rows="3" id="observacionesLF" name="observacionesLF"
                                        disabled="disabled">${bandejaAC.observacionesLineaFrente}</textarea>
                                    <br />
                                </c:if>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <jsp:include page="./infoDevolucionesAC.jsp">
                    <jsp:param value="${bandejasParam.caseDevoluciones}" name="casoDevolucion" />
                </jsp:include>
                <jsp:include page="./conceptoAC.jsp">
                    <jsp:param value="${disabledInput}" name="disableInput" />
                    <jsp:param value="${bandejasParam.caseDevoluciones}" name="casoDevolucion" />
                </jsp:include>

            </form>
        </div>
    </div>
</div>

<!-- Modal historial de devoluciones -->
<div class="modal fade" id="historialDev" tabindex="-1" role="dialog" aria-labelledby="fechasEntregaLabel" aria-hidden="true">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">
                <fmt:message key="label.historialDevoluciones" />
            </h4>
        </div>
        <div class="modal-body">...</div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">
                <fmt:message key="label.button.cerrar" />
            </button>
        </div>
    </div>
</div>