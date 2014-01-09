<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="tipoBandejaNacional" value="<%=SystemConstants.BANDEJA_NACIONAL%>" />
<c:set var="itemProcedimiento" value="<%=SystemConstants.ITEM_PROCEDIMIENTO_STRING%>" />
<c:set var="itemMedicamento" value="<%=SystemConstants.ITEM_MEDICAMENTO_STRING%>" />

<fmt:message key='label.justificacionConcepto' var="JUSTIFICACION_CONCEPTO" />
<fmt:message key='label.justificacionConexidad' var="JUSTIFICACION_CONEXIDAD" />
<fmt:message key='label.periodo.aprobadoDias' var="PERIODO_LABEL" />
<fmt:message key='label.cantidadAprobada' var="CANT_APR_LABEL" />
<fmt:message key='label.unidadesAprobadasPeriodo' var="UNID_APR_PER_LABEL" />
<fmt:message key='label.diasPeriodo' var="DIAS_PER_LABEL" />
<fmt:message key='label.numeroEntregas' var="NUM_ENTREGAS_LABEL" />
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
            <strong><fmt:message key="label.header.concepto" /></strong>
        </blockquote>
        <div class="collapsible-content" style="margin-left: 20px">
            <div class="col-lg-12">
                <c:if test="${!esLDF}">
                    <fieldset id="fieldSetConceptoTutelas" disabled="disabled">
                        <div id="contentItemSolicitado" class="tab-content">
                            <c:if test="${bandejaAC.tipoItem == itemMedicamento}">
                                <div class="form-group form-group-sm">
                                    <label for="cantidadAprobadaTutelas" class="control-label control-label-sm col-lg-2-5"
                                        style="text-align: left;">${PERIODO_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="periodoAprobadoTutelas" name="periodoAprobado"
                                            readonly="readonly" placeholder="${PERIODO_LABEL}" maxlength="4"
                                            value="${conceptoTutelas.periodoAprobado}" />
                                    </div>
                                    <label for="diasPeriodoTutelas" class="control-label control-label-sm text-right col-lg-2">${DIAS_PER_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="diasPeriodoTutelas" name="diasPeriodo"
                                            placeholder="${DIAS_PER_LABEL}" maxlength="4" value="${conceptoTutelas.diasPeriodo}"
                                            onchange="calcularPeriodoAprobado()" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="unidadesAprobadasTutelas" class="control-label control-label-sm col-lg-2-5"
                                        style="text-align: left;">${UNID_APR_PER_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="unidadesAprobadasTutelas" name="unidadesAprobadas"
                                            placeholder="${UNID_APR_PER_LABEL}" maxlength="4" value="${conceptoTutelas.unidadesAprobadas}" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="numeroEntregasTutelas" class="control-label control-label-sm col-lg-2-5"
                                        style="padding-right: 0px; text-align: left;">${NUM_ENTREGAS_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="numeroEntregasTutelas" name="numeroEntregas"
                                            placeholder="${NUM_ENTREGAS_LABEL}" maxlength="4" value="${conceptoTutelas.numeroEntregas}"
                                            onchange="calcularPeriodoAprobado()" />
                                    </div>
                                    <div class="col-lg-4">
                                        <button type="button" id="procesarFechas" class="btn btn-info btn-sm" data-toggle="modal">${VER_FECHAS_LABEL}</button>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${bandejaAC.tipoItem == itemProcedimiento}">
                                <div class="form-group form-group-sm">
                                    <label for="unidadesAprobadasTutelas" class="control-label control-label-sm text-right col-lg-2-5" style="text-align: left;">${CANT_APR_LABEL}</label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="unidadesAprobadasTutelas" name="unidadesAprobadas"
                                            placeholder="${CANT_APR_LABEL}" maxlength="4" value="${conceptoTutelas.unidadesAprobadas}" />
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </fieldset>
                </c:if>
                <div class="form-group form-group-sm">
                    <label for="justifiConceptTextArea" class="col-lg-2-5 control-label" style="text-align: left;">
                        ${JUSTIFICACION_CONCEPTO} </label>
                    <div class="col-lg-9-5">
                        <textarea name="justificacionConcepto" id="justificacionConcepto" class="form-control input-sm"
                            placeholder="${JUSTIFICACION_CONCEPTO}"
                            <c:if test="${!bandejasParam.editable && !bandejasParam.editableRespuesta}">
                        disabled="disabled"   
                    </c:if>>
                    ${conceptoTutelas.justificacion}
                </textarea>
                    </div>
                </div>
                <c:if test="${!esLDF}">
                    <div class="form-group form-group-sm">
                        <label for="justifiConceptTextArea" class="col-lg-2-5 control-label" style="text-align: left;">
                            ${JUSTIFICACION_CONEXIDAD} </label>
                        <div class="col-lg-9-5">
                            <textarea name="justificacionConexidad" id="justificacionConexidad" class="form-control input-sm"
                                placeholder="${JUSTIFICACION_CONEXIDAD}"
                                <c:if test="${!bandejasParam.editable && !bandejasParam.editableRespuesta}">
                        disabled="disabled"   
                    </c:if>>
                    ${conceptoTutelas.justificacionConexidad}
                </textarea>
                        </div>
                    </div>
                </c:if>
                <fieldset>
                    <div class="tab-content">
                        <br />
                        <div class="col-lg-12">
                            <div style="text-align: center;">
                                <c:if test="${bandejasParam.mostrarAcciones[0]}">
                                    <label class="radio-inline" style="font-weight: bold;"> <input type="radio"
                                        id="aprobarSolicitud" value="1" name="concepto" onchange="showDivConcepto(0,'#tabAutorizar')">
                                        <fmt:message key="label.concepto.autorizado" />
                                    </label>
                                </c:if>
                                <c:if test="${bandejasParam.mostrarAcciones[1]}">
                                    <label class="radio-inline" style="font-weight: bold;"> <input type="radio" id="enviarTutela"
                                        value="9" name="concepto" onchange="showDivConcepto(0,'#tabEnviarTutela')"> <fmt:message
                                            key="label.concepto.enviarTutela" />
                                    </label>
                                </c:if>
                                <c:if test="${bandejasParam.mostrarAcciones[2]}">
                                    <label class="radio-inline" style="font-weight: bold;"> <input type="radio"
                                        id="escalarSolicitud" value="8" name="concepto" onchange="showDivConcepto(0,'#tabDeptResponder')">
                                        <fmt:message key="label.concepto.escalar" />
                                    </label>
                                </c:if>
                                <c:if test="${bandejasParam.mostrarAcciones[3]}">
                                    <label class="radio-inline" style="font-weight: bold;"> <input type="radio"
                                        id="devolverSolicitud" value="4" name="concepto" onchange="showDivConcepto(0,'#tabDevolver')">
                                        <fmt:message key="label.devolver" />
                                    </label>
                                </c:if>
                                <c:if test="${bandejasParam.mostrarAcciones[4]}">
                                    <label class="radio-inline" style="font-weight: bold;"> <input type="radio" id="anularSolicitud"
                                        value="3" name="concepto" onchange="showDivConcepto(0,'#tabAnular')"> <fmt:message
                                            key="label.anular" />
                                    </label>
                                </c:if>
                            </div>
                            <div id="conceptoValidation" style="text-align: center;"></div>
                            <div id="ConceptoDeptTabContent" style="margin-top: 15px">
                                <div id="tabAutorizar" style="display: none;"></div>
                                <div id="tabEnviarTutela" style="display: none;"></div>
                                <div id="tabAnular" style="display: none;">
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
                                <div id="tabDevolver" style="display: none;">
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
                                <div id="tabEscalar" style="display: none;"></div>
                            </div>
                        </div>
                        <br />
                    </div>
                </fieldset>
            </div>
        </div>
    </fieldset>
</c:if>
<div class="col-lg-12">
    <div class="form-group text-center">
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
