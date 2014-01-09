<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="itemProcedimiento" value="<%= SystemConstants.ITEM_PROCEDIMIENTO_STRING %>" />
<c:set var="itemMedicamento" value="<%= SystemConstants.ITEM_MEDICAMENTO_STRING %>" />

<fmt:message key='label.button.verHistoriaDevoluciones' var="VER_HIST_DEV_LABEL" />
<fmt:message key='label.periodo.aprobadoDias' var="PERIODO_LABEL" />
<fmt:message key='label.cantidadAprobada' var="CANT_APR_LABEL" />
<fmt:message key='label.dosisAprobada' var="DOSIS_APR_LABEL" />
<fmt:message key='label.unidadesAprobadasPeriodo' var="UNID_APR_PER_LABEL" />
<fmt:message key='label.diasPeriodo' var="DIAS_PER_LABEL" />
<fmt:message key='label.numeroEntregas' var="NUM_ENTREGAS_LABEL" />

<c:if test="${param.casoDevolucion != 0}">

    <!-- El caso 1 se trata cuando El auditor nacional ve los datos de la solicitud ingresados por el auditor regional -->
    <c:if test="${param.casoDevolucion == 3}">
        <fieldset disabled="disabled" style="padding-left: 0px;" class="well">
            <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                <strong><fmt:message key="label.respuestaRegional" /></strong>
            </blockquote>
            <div class="collapsible-content">
                <div class="col-lg-12">
                    <div class="form-group form-group-sm">
                        <label for="justifiConceptDeptTextArea" class="col-lg-12 control-label control-label-sm" style="text-align: left;">
                            <fmt:message key="label.justificacion.regional" />
                        </label>
                        <div class="col-lg-12">
                            <textarea name="devjustificacionDev" id="justificacionDev" class="form-control input-sm">${infoDevoluciones.justificacion}</textarea>
                        </div>
                    </div>
                </div>
                <br />
            </div>
        </fieldset>
    </c:if>

    <!-- El caso 1 se trata cuando El auditor nacional ve los datos de la solicitud ingresados por la ips -->
    <c:if test="${param.casoDevolucion == 4}">
        <fieldset disabled="disabled" style="padding-left: 0px;" class="well">
            <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                <strong><fmt:message key="label.respuestaIPS" /> </strong>
            </blockquote>
            <div class="collapsible-content">
                <div class="col-lg-12">
                    <div class="form-group form-group-sm">
                        <label for="justifiConceptDeptTextArea" class="col-lg-12 control-label control-label-sm" style="text-align: left;">
                            <fmt:message key="label.respuestaIPS" />
                        </label>
                        <div class="col-lg-12">
                            <textarea name="devjustificacionDev" id="justificacionDev" class="form-control input-sm">${infoDevoluciones.justificacion}</textarea>
                        </div>
                    </div>
                </div>
                <br />
            </div>
        </fieldset>
    </c:if>

    <!-- El caso 2 se trata cuando el auditor regional o la ips ve el motivo por el cual el auditor nacional devolvió la solicitud -->
    <c:if test="${param.casoDevolucion == 1 || param.casoDevolucion == 2 || param.casoDevolucion == 5}">
        <fieldset disabled="disabled" class="well">
            <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                <strong><fmt:message key="label.conceptoCNA" /></strong>
            </blockquote>
            <div class="collapsible-content" style="padding-left: 20px;">
                <div class="col-lg-12">
                    <div class="form-group form-group-sm">
                        <c:if test="${infoDevoluciones.concepto == 1}">
                            <label class="radio-inline" style="font-weight: bold; text-align: left; padding-left: 0px;"><fmt:message
                                    key="label.autorizada" /></label>

                            <div id="contentItemSolicitado" class="tab-content">

                                <c:if test="${bandejaCS.tipoItem == itemMedicamento}">
                                    <div class="form-group form-group-sm">
                                        <label for="cantidadAprobadaCSDev" class="control-label control-label-sm col-lg-3"
                                            style="text-align: left;">${PERIODO_LABEL}</label>
                                        <div class="col-lg-2">
                                            <input class="form-control input-sm" id="periodoAprobadoCSDev" name="devperiodoAprobado"
                                                placeholder="${PERIODO_LABEL}" maxlength="4" value="${infoDevoluciones.periodoAprobado}" />
                                        </div>
                                        <label for="diasPeriodoCSDev" class="control-label control-label-sm col-lg-2">${DIAS_PER_LABEL}</label>
                                        <div class="col-lg-2">
                                            <input class="form-control input-sm" id="diasPeriodoCSDev" name="devdiasPeriodo"
                                                placeholder="${DIAS_PER_LABEL}" maxlength="4" value="${infoDevoluciones.diasPeriodo}" />
                                        </div>
                                    </div>
                                    <div class="form-group form-group-sm">
                                        <label for="unidadesAprobadasCSDev" class="control-label control-label-sm col-lg-3"
                                            style="text-align: left;">${UNID_APR_PER_LABEL}</label>
                                        <div class="col-lg-2">
                                            <input class="form-control input-sm" id="unidadesAprobadasCSDev" name="devunidadesAprobadas"
                                                placeholder="${UNID_APR_PER_LABEL}" maxlength="4"
                                                value="${infoDevoluciones.unidadesAprobadas}" />
                                        </div>
                                        <label for="dosisAprobadaCSDev" class="control-label control-label-sm col-lg-2">${DOSIS_APR_LABEL}</label>
                                        <div class="col-lg-2">
                                            <input class="form-control input-sm" id="dosisAprobadaCSDev" name="devdosisAprobada"
                                                placeholder="${DOSIS_APR_LABEL}" value="${infoDevoluciones.dosisAprobada}" />
                                        </div>
                                    </div>
                                    <div class="form-group form-group-sm">
                                        <label for="numeroEntregasCSDev" class="control-label control-label-sm col-lg-3"
                                            style="padding-right: 0px; text-align: left;">${NUM_ENTREGAS_LABEL}</label>
                                        <div class="col-lg-2">
                                            <input class="form-control input-sm" id="numeroEntregasCSDev" name="devnumeroEntregas"
                                                placeholder="${NUM_ENTREGAS_LABEL}" maxlength="4" value="${infoDevoluciones.numeroEntregas}" />
                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${bandejaCS.tipoItem == itemProcedimiento}">
                                    <div class="form-group form-group-sm">
                                        <label for="unidadesAprobadasCSDev" class="control-label control-label-sm col-lg-2"
                                            style="text-align: left;">${CANT_APR_LABEL}</label>
                                        <div class="col-lg-2">
                                            <input class="form-control input-sm" id="unidadesAprobadasCSDev" name="devunidadesAprobadas"
                                                placeholder="${CANT_APR_LABEL}" maxlength="4" value="${infoDevoluciones.unidadesAprobadas}" />
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </c:if>
                        <c:if test="${infoDevoluciones.concepto == 3}">
                            <div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-3 control-label control-label-sm"
                                        style="text-align: left; padding-left: 0px;"><fmt:message key="label.anulada" /></label>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-2 control-label control-label-sm"
                                        style="text-align: left;"><fmt:message key="label.causalAnulacion" /> </label>
                                    <div class="col-lg-10">${infoDevoluciones.causalAnulacion}</div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${infoDevoluciones.concepto == 4 || infoDevoluciones.concepto == 5}">
                            <div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-3 control-label control-label-sm"
                                        style="text-align: left;"><fmt:message key="label.devuelta" /></label>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-3" style="text-align: left;"><fmt:message key="label.causalDevolucion" />
                                    </label>
                                    <div class="col-lg-9">${infoDevoluciones.causalDevolucion}</div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="form-group form-group-sm">
                    <label for="justifiConceptTextArea" class="col-lg-12 control-label" style="text-align: left;"> <fmt:message
                            key="label.justificacion" />
                    </label>
                    <div class="col-lg-12">
                        <textarea name="devjustificacion" id="justificacionCSDev" class="form-control input-sm"
                            <c:if test="${!bandejasParam.editable && !bandejasParam.editableRespuesta}">
                        disabled="disabled"   
                    </c:if>>
                    ${infoDevoluciones.justificacion}
                </textarea>
                        <script type="text/javascript">
                                                                                                    $("#justificacionCSDev").val(
                                                                                                            $.trim($("#justificacionCSDev")
                                                                                                                    .val()));
                                                                                                </script>
                    </div>
                </div>
                <br />
            </div>
        </fieldset>
    </c:if>
    <!--     TODO: Verificar como manejar el historial de devoluciones y manejarlo -->
    <!--     <div class="form-group form-group-sm" style="padding-left: 20px;"> -->
    <!--         <div class="col-lg-10"> -->
    <%--             <a data-toggle="modal" id="verHistorialDev" href="#historialDev" class="btn btn-info btn-sm">${VER_HIST_DEV_LABEL}</a> --%>
    <!--         </div> -->
    <!--     </div> -->
</c:if>