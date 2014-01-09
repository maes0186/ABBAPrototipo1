<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="tipoBandejaNacional" value="<%= SystemConstants.BANDEJA_NACIONAL %>" />
<c:set var="itemProcedimiento" value="<%= SystemConstants.ITEM_PROCEDIMIENTO_STRING %>" />
<c:set var="itemMedicamento" value="<%= SystemConstants.ITEM_MEDICAMENTO_STRING %>" />

<fmt:message key='label.periodo.aprobadoDias' var="PERIODO_LABEL" />
<fmt:message key='label.cantidadAprobada' var="CANT_APR_LABEL" />
<fmt:message key='label.unidadesAprobadasPeriodo' var="UNID_APR_PER_LABEL" />
<fmt:message key='label.diasPeriodo' var="DIAS_PER_LABEL" />
<fmt:message key='label.numeroEntregas' var="NUM_ENTREGAS_LABEL" />
<fmt:message key='label.lateralidad' var="LATERALIDAD_LABEL" />
<fmt:message key='label.button.verFechasEntrega' var="VER_FECHAS_LABEL" />
<fmt:message key='label.fechasEntrega' var="FECHAS_ENTREGA" />
<fmt:message key='label.numeroEntrega' var="NUMEROS_ENTREGA" />

<script>
    var lastTab = new Array();
    function showDivConcepto(index, tab) {
        $(lastTab[index]).hide();
        $(tab).show();
        lastTab[index] = $(tab);
        addRules();
    }
</script>
<c:if
    test="${(bandejasParam.tipoBandeja == tipoBandejaNacional && param.casoDevolucion != 5) || param.casoDevolucion == 1 || param.casoDevolucion == 2}">

    <fieldset class="well">
        <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
            <c:if test="${param.casoDevolucion == 1}">
                <strong><fmt:message key="label.respuestaRegional" /></strong>
            </c:if>
            <c:if test="${param.casoDevolucion == 2}">
                <strong><fmt:message key="label.respuestaIPS" /></strong>
            </c:if>
            <c:if test="${param.casoDevolucion != 1 && param.casoDevolucion != 2}">
                <strong><fmt:message key="label.conceptoCNA" /></strong>
            </c:if>
        </blockquote>
        <div class="collapsible-content">
            <div class="col-lg-12">
                <c:if test="${param.casoDevolucion != 1 && param.casoDevolucion != 2}">
                    <fieldset id="fieldSetConceptoAC" <c:out value="${param.disableInput}"/>>
                        <div id="contentItemSolicitado" class="tab-content">
                            <c:if test="${bandejaAC.tipoItem == itemMedicamento}">
                                <div class="form-group form-group-sm">
                                    <label for="cantidadAprobadaAC" class="control-label control-label-sm text-right col-lg-3">${PERIODO_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="periodoAprobadoAC" name="periodoAprobado" readonly="readonly"
                                            placeholder="${PERIODO_LABEL}" maxlength="4" value="${conceptoAC.periodoAprobado}" />
                                    </div>
                                    <label for="diasPeriodoAC" class="control-label control-label-sm text-right col-lg-2">${DIAS_PER_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="diasPeriodoAC" name="diasPeriodo"
                                            placeholder="${DIAS_PER_LABEL}" maxlength="4" value="${conceptoAC.diasPeriodo}"
                                            onchange="calcularPeriodoAprobado()" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="unidadesAprobadasAC" class="control-label control-label-sm text-right col-lg-3">${UNID_APR_PER_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="unidadesAprobadasAC" name="unidadesAprobadas"
                                            placeholder="${UNID_APR_PER_LABEL}" maxlength="4" value="${conceptoAC.unidadesAprobadas}" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="numeroEntregasAC" class="control-label control-label-sm text-right col-lg-3"
                                        style="padding-right: 0px;">${NUM_ENTREGAS_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="numeroEntregasAC" name="numeroEntregas"
                                            placeholder="${NUM_ENTREGAS_LABEL}" maxlength="4" value="${conceptoAC.numeroEntregas}"
                                            onchange="calcularPeriodoAprobado()" />
                                    </div>
                                    <div class="col-lg-4">
                                        <button type="button" id="procesarFechas" class="btn btn-info btn-sm" data-toggle="modal">${VER_FECHAS_LABEL}</button>
                                    </div>
                                </div>
                            </c:if>
                            
                             <c:if test="${bandejaAC.tipoItem == itemInsumo}">
                                <div class="form-group form-group-sm">
                                    <label for="unidadesAprobadasAC" class="control-label control-label-sm text-right col-lg-2">${CANT_APR_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="unidadesAprobadasAC" name="unidadesAprobadas"
                                            placeholder="${CANT_APR_LABEL}" maxlength="4" value="${conceptoAC.unidadesAprobadas}" />
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${bandejaAC.tipoItem == itemProcedimiento}">
                                <div class="form-group form-group-sm">
                                    <label for="unidadesAprobadasAC" class="control-label control-label-sm text-right col-lg-2">${CANT_APR_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="unidadesAprobadasAC" name="unidadesAprobadas"
                                            placeholder="${CANT_APR_LABEL}" maxlength="4" value="${conceptoAC.unidadesAprobadas}" />
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </fieldset>
                </c:if>
                <div class="form-group form-group-sm">
                    <label for="justifiConceptTextArea" class="col-lg-12 control-label" style="text-align: left;"> <fmt:message
                            key="label.justificacion" />
                    </label>
                    <div class="col-lg-12">
                        <textarea name="justificacion" id="justificacionAC" class="form-control input-sm"
                            <c:if test="${!bandejasParam.editable && !bandejasParam.editableRespuesta}">
                        disabled="disabled"   
                    </c:if>>
                    ${conceptoAC.justificacion}
                </textarea>
                    </div>
                </div>
                <fieldset>
                    <div class="tab-content">
                        <br />
                        <div class="col-lg-12">
                            <div style="text-align: center;">
                                <c:if test="${bandejasParam.mostrarAcciones[0]}">
                                    <label class="radio-inline" style="font-weight: bold;"> <input type="radio"
                                        id="aprobarSolicitud" value="1" name="concepto" onchange="showDivConcepto(0,'#tabDeptAprobar')">
                                        <fmt:message key="label.concepto.autorizado" />
                                    </label>
                                </c:if>
                                <c:if test="${bandejasParam.mostrarAcciones[2]}">
                                    <label class="radio-inline" style="font-weight: bold;"> <input type="radio" id="anularSolicitud"
                                        value="3" name="concepto" onchange="showDivConcepto(0,'#tabDeptAnular')"> <fmt:message
                                            key="label.anular" />
                                    </label>
                                </c:if>
                                <c:if test="${bandejasParam.mostrarAcciones[3]}">
                                    <label class="radio-inline" style="font-weight: bold;"> <input type="radio"
                                        id="devolverSolicitud" value="4" name="concepto" onchange="showDivConcepto(0,'#tabDeptDevolver')">
                                        <fmt:message key="label.devolver" />
                                    </label>
                                </c:if>
                                <c:if test="${bandejasParam.mostrarAcciones[4]}">
                                    <label class="radio-inline" style="font-weight: bold;"> <input type="radio"
                                        id="responderSolicitud" value="5" name="concepto" onchange="showDivConcepto(0,'#tabDeptResponder')">
                                        <fmt:message key="label.responder" />
                                    </label>
                                </c:if>
                            </div>
                            <div id="conceptoValidation" style="text-align: center;"></div>
                            <div id="ConceptoDeptTabContent" style="margin-top: 15px">
                                <div id="tabDeptAprobar" style="display: none;"></div>
                                <div id="tabEnviarCTC" style="display: none;"></div>
                                <div id="tabDeptAnular" style="display: none;">
                                    <span class="text-success"> <strong><fmt:message
                                                key="label.infoSolicitud.causalAnulacion" /></strong></span> <br /> <br />
                                    <div class="form-group">
                                        <c:forEach var="listadoCausalesAnulacion" items="${causalesAnulacion}">
                                            <input type="radio" id="criterioAnulacion${listadoCausalesAnulacion.id}"
                                                value="${listadoCausalesAnulacion.id}" name="causalAnulacion" class="col-lg-1" />
                                            <label class="col-lg-11" for="criterioAnulacion${listadoCausalesAnulacion.id}"
                                                style="font-weight: bold;"> ${listadoCausalesAnulacion.descripcion} </label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div id="tabDeptDevolver" style="display: none;">
                                    <span class="text-success"> <strong><fmt:message
                                                key="label.infoSolicitud.causalDevolucion" /></strong></span> <br /> <br />
                                    <div class="form-group">
                                        <c:forEach var="listadoCausalesDevolucion" items="${causalesDevolucion}">
                                            <input type="radio" id="criterioDevolucion${listadoCausalesDevolucion.id}"
                                                value="${listadoCausalesDevolucion.id}" name="causalDevolucion" class="col-lg-1" />
                                            <label class="col-lg-11" for="criterioDevolucion${listadoCausalesDevolucion.id}"
                                                style="font-weight: bold;">${listadoCausalesDevolucion.descripcion} </label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div id="tabDeptResponder" style="display: none;"></div>
                            </div>
                        </div>
                        <br />
                    </div>
                </fieldset>
            </div>
        </div>
    </fieldset>
</c:if>
<c:if test="${anulacionAutorizAuditor}">
    <fieldset class="well">
        <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
            <strong><fmt:message key="label.justificacion.anulacion" /></strong>
        </blockquote>
        <div class="collapsible-content">
            <div class="col-lg-12">
                <div class="form-group">
                    <textarea class="form-control input-sm" id="justificacionAnulacion" name="justificacionAnulacion"></textarea>
                </div>
            </div>
        </div>
    </fieldset>
</c:if>
<div class="col-lg-12">
    <div class="form-group text-center">
        <c:if test="${redireccionAutorizAuditor}">
            <button type="button" id="btnRedireccionar" class="btn btn-success">
                <fmt:message key="label.button.redireccionar" />
            </button>
        </c:if>
        <c:if test="${anulacionAutorizAuditor}">
            <button type="button" id="btnAnular" class="btn btn-success">
                <fmt:message key="label.button.anular" />
            </button>        
        </c:if>
        <c:if test="${bandejasParam.editable || bandejasParam.editableRespuesta}">
            <button type="submit" id="btnAceptar" class="btn btn-success">
                <fmt:message key="label.button.aceptar" />
            </button>
        </c:if>
        <button type="button" id="btnCancelar" class="btn btn-danger">
            <fmt:message key="label.button.cancelar" />
        </button>
    </div>
</div>
<br />

<!-- Modal fechas de entrega -->
<div class="modal fade" id="fechasEntrega" tabindex="-1" role="dialog" aria-labelledby="fechasEntregaLabel" aria-hidden="true">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">
                <fmt:message key="label.entregasFuturas" />
            </h4>
        </div>
        <div class="modal-body">
            <table id="tablaFechas" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th>${NUMEROS_ENTREGA}</th>
                        <th>${FECHAS_ENTREGA}</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
                <tfoot>
                </tfoot>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">
                <fmt:message key="label.button.cerrar" />
            </button>
        </div>
    </div>
</div>
