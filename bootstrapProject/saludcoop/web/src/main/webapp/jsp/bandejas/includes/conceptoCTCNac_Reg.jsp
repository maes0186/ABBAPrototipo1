<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="tipoBandejaNacional" value="<%=SystemConstants.BANDEJA_NACIONAL%>" />
<c:set var="tipoBandejaRegional" value="<%=SystemConstants.BANDEJA_REGIONAL%>" />
<c:set var="itemMedicamento" value="<%=SystemConstants.ITEM_MEDICAMENTO_STRING%>" />
<c:set var="itemProcedimiento" value="<%= SystemConstants.ITEM_PROCEDIMIENTO_STRING %>" />
<c:set var="itemInsumo" value="<%= SystemConstants.ITEM_INSUMO_STRING %>" />

<fmt:message key='label.button.verFechasEntrega' var="VER_FECHAS_LABEL" />
<fmt:message key='label.fechasEntrega' var="FECHAS_ENTREGA" />
<fmt:message key='label.numeroEntrega' var="NUMEROS_ENTREGA" />
<fmt:message key='label.cantidadAprobada' var="CANT_APR_LABEL" />
<fmt:message key='label.duracionTratamiento'  var="UNIDADES_LABEL" />

<c:if
    test="${(bandejasParam.tipoBandeja == tipoBandejaNacional && (bandejasParam.caseDevoluciones == 1 || bandejasParam.caseDevoluciones == 0 || bandejasParam.caseDevoluciones == 3 )) 
|| (bandejasParam.tipoBandeja == tipoBandejaRegional && bandejasParam.caseDevoluciones < 5)}">
    <script>
                    var lastTab = new Array();
                    function showDivConcepto(index, tab) {
                        $(lastTab[index]).hide();
                        $(tab).show();
                        lastTab[index] = $(tab);
                        addRules();
                    }
                </script>
    <fieldset class="well">
        <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
            <c:if test="${bandejasParam.tipoBandeja == tipoBandejaRegional}">
                <strong> <fmt:message key="label.solicitud.conceptoCTCRegional" />
                </strong>
            </c:if>
            <c:if test="${bandejasParam.tipoBandeja == tipoBandejaNacional}">
                <strong> <fmt:message key="label.solicitud.conceptoCTCNacional" />
                </strong>
            </c:if>
        </blockquote>
        <div class="collapsible-content">
            <div class="col-lg-12">
                <div id="contAprobadoDept" style="margin-top: 15px">
                    <fieldset id="fieldSetConceptoCTC" <c:out value="${param.disableInput}"/>>
                    
                        <c:if test="${param.tipoItem == itemMedicamento}">
                            <div class="form-group form-group-sm">
                                <label for="periodoAprobDiasInput" class="col-lg-4 control-label" style="text-align: left;"> <fmt:message
                                        key="label.periodo.aprobadoDias" />
                                </label>
                                <div class="col-lg-2">
                                    <input type="text" name="periodoAprobado" id="periodoAprobDiasInput" class="form-control input-sm" readonly="readonly"
                                        value="${conceptoCTC.periodoAprobado}" />
                                </div>
                                <label for="diasXPeriodoInput" class="col-lg-3 control-label"><fmt:message key="label.dias.periodo" /></label>
                                <div class="col-lg-2">
                                    <input type="text" name="diasPeriodo" id="diasXPeriodoInput" class="form-control input-sm"
                                        value="${conceptoCTC.diasPeriodo}" onchange="calcularPeriodoAprobado()" />
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="unidsAprobPeriodosInput" class="col-lg-4 control-label" style="text-align: left;"> <fmt:message
                                        key="label.unidades.aprobadasPeriodo" />
                                </label>
                                <div class="col-lg-2">
                                    <input type="text" name="unidadesAprobadas" id="unidsAprobPeriodoInput" class="form-control input-sm"
                                        value="${conceptoCTC.unidadesAprobadas}" />
                                </div>
                                <label for="dosisAprobadaCTC" class="control-label control-label-sm text-right col-lg-3"><fmt:message
                                        key="label.dosisAprobada" /></label>
                                <div class="col-lg-2">
                                    <input class="form-control input-sm" id="dosisAprobadaCTC" name="dosisAprobada"
                                        value="${conceptoCTC.dosisAprobada}" />
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="numEntregasInput" class="col-lg-4 control-label" style="text-align: left;"> <fmt:message
                                        key="label.numeroEntregas" />
                                </label>
                                <div class="col-lg-2">
                                    <input type="text" name="numeroEntregas" id="numEntregasInput" class="form-control input-sm"
                                        value="${conceptoCTC.numeroEntregas}" onchange="calcularPeriodoAprobado()" />
                                </div>
                                <div class="col-lg-4">
                                    <button type="button" id="procesarFechas" class="btn btn-info btn-sm" data-toggle="modal">${VER_FECHAS_LABEL}</button>
                                </div>
                            </div>
                        </c:if>
                        
                        <c:if test="${param.tipoItem == itemProcedimiento}">
                            <div class="form-group form-group-sm">
                                <label for="unidadesAprobadasCTC" class="control-label control-label-sm text-right col-lg-2">${CANT_APR_LABEL}</label>
                                <div class="col-lg-2">
                                    <input class="form-control input-sm digits-only" id="unidadesAprobadasCTC" name="unidadesAprobadas"
                                        placeholder="${CANT_APR_LABEL}" maxlength="4" value="${conceptoCTC.unidadesAprobadas}" />
                                </div>
                            </div>
                        </c:if>
                        
                        <c:if test="${param.tipoItem == itemInsumo}">
                           <div class="form-group form-group-sm">
                                <label for="diasXPeriodoInput" class="col-lg-3 control-label">${UNIDADES_LABEL}</label>
                                <div class="col-lg-2">
                                    <input type="text" name="diasPeriodo" id="diasXPeriodoInput" class="form-control input-sm"
                                        value="${conceptoCTC.diasPeriodo}"  placeholder=${UNIDADES_LABEL} />
                                </div>
                                <label for="unidadesAprobadasCTC" class="control-label control-label-sm text-right col-lg-2">${CANT_APR_LABEL}</label>
                                <div class="col-lg-2">
                                    <input class="form-control input-sm digits-only" id="unidadesAprobadasCTC" name="unidadesAprobadas"
                                        placeholder="${CANT_APR_LABEL}" maxlength="4" value="${conceptoCTC.unidadesAprobadas}" />
                                </div>
                            </div>
                          </c:if>
                        
                    </fieldset>
                    <div class="form-group form-group-sm">
                        <label for="justifiConceptTextArea" class="col-lg-12 control-label" style="text-align: left;"> <fmt:message
                                key="label.justificacion" />
                        </label>
                        <div class="col-lg-12">
                            <textarea name="justificacion" id="justificacionCTC" class="form-control input-sm"
                                <c:if test="${!bandejasParam.editable && !bandejasParam.editableRespuesta}">
                        disabled="disabled"   
                    </c:if>>
	                ${conceptoCTC.justificacion}
                </textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-12">
                <div style="text-align: center;">
                    <c:if test="${bandejasParam.mostrarAcciones[0]}">
                        <label class="radio-inline" style="font-weight: bold;"> <input type="radio" id="aprobarSolicitud" value="1"
                            name="concepto" onchange="showDivConcepto(0,'#tabDeptAprobar')"> <fmt:message key="label.aprobado" />
                        </label>
                    </c:if>
                    <c:if test="${bandejasParam.mostrarAcciones[1]}">
                        <label class="radio-inline" style="font-weight: bold;"> <input type="radio" id="noAprobarSolicitud"
                            value="2" name="concepto" onchange="showDivConcepto(0,'#tabDeptNoAprobar')"> <fmt:message
                                key="label.noAprobado" />
                        </label>
                    </c:if>
                    <c:if test="${bandejasParam.mostrarAcciones[2]}">
                        <label class="radio-inline" style="font-weight: bold;"> <input type="radio" id="anularSolicitud" value="3"
                            name="concepto" onchange="showDivConcepto(0,'#tabDeptAnular')"> <fmt:message key="label.anular" />
                        </label>
                    </c:if>
                    <c:if test="${bandejasParam.mostrarAcciones[3]}">
                        <label class="radio-inline" style="font-weight: bold;"> <input type="radio" id="devolverSolicitud" value="4"
                            name="concepto" onchange="showDivConcepto(0,'#tabDeptDevolver')"> <fmt:message key="label.devolver" />
                        </label>
                    </c:if>
                    <c:if test="${bandejasParam.mostrarAcciones[4]}">
                        <label class="radio-inline" style="font-weight: bold;"> <input type="radio" id="responderSolicitud"
                            value="5" name="concepto" onchange="showDivConcepto(0,'#tabDeptResponder')"> <fmt:message
                                key="label.responder" />
                        </label>
                    </c:if>
                </div>
                <div id="conceptoValidation" style="text-align: center;"></div>
                <div id="ConceptoDeptTabContent" style="margin-top: 15px">
                    <div id="tabDeptAprobar" style="display: none;"></div>
                    <div id="tabDeptNoAprobar" style="display: none;">
                        <span class="text-success"> <strong><fmt:message key="label.infoSolicitud.criterioNegociacion1" /></strong></span>
                        <br /> <br />
                        <div class="form-group">
                            <c:forEach var="listadoCausalesNegacion" items="${criteriosNegacion}">
                                <input type="checkbox" name="criteriosNegacion" id="criterioNegacion${listadoCausalesNegacion.id}"
                                    class="col-lg-1" value="${listadoCausalesNegacion.id}" />
                                <label class="col-lg-11" style="font-weight: bold;"> ${listadoCausalesNegacion.descripcion} </label>
                            </c:forEach>
                        </div>
                    </div>
                    <div id="tabDeptAnular" style="display: none;">
                        <span class="text-success"> <strong><fmt:message key="label.infoSolicitud.causalAnulacion" /></strong></span> <br />
                        <br />
                        <div class="form-group">
                            <c:forEach var="listadoCausalesAnulacion" items="${causalesAnulacion}">
                                <label class="col-lg-12" style="font-weight: bold;"> <input type="radio"
                                    id="criterioAnulacion${listadoCausalesAnulacion.id}" value="${listadoCausalesAnulacion.id}"
                                    name="causalAnulacion"> ${listadoCausalesAnulacion.descripcion}
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                    <div id="tabDeptDevolver" style="display: none;">
                        <span class="text-success"> <strong><fmt:message key="label.infoSolicitud.causalDevolucion" /></strong></span> <br />
                        <br />
                        <div class="form-group">
                            <c:forEach var="listadoCausalesDevolucion" items="${causalesDevolucion}">
                                <label class="col-lg-12" style="font-weight: bold;"> <input type="radio"
                                    id="criterioAnulacion${listadoCausalesDevolucion.id}" value="${listadoCausalesDevolucion.id}"
                                    name="causalDevolucion"> ${listadoCausalesDevolucion.descripcion}
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                    <div id="tabDeptResponder" style="display: none;"></div>
                </div>
            </div>
            <br />
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
