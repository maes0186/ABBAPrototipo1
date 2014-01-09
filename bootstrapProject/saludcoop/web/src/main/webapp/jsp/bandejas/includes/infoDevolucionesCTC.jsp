<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="itemProcedimiento" value="<%=SystemConstants.ITEM_PROCEDIMIENTO_STRING%>" />
<c:set var="itemMedicamento" value="<%=SystemConstants.ITEM_MEDICAMENTO_STRING%>" />
<c:set var="itemInsumo" value="<%=SystemConstants.ITEM_INSUMO_STRING%>" />

<c:if test="${param.casoDevolucion != 0}">
    <!-- El caso 1 se trata cuando El auditor nacional ve los datos de la solicitud ingresados por el auditor regional -->
    <c:if test="${param.casoDevolucion == 1 || param.casoDevolucion == 6}">
        <fieldset disabled="disabled" style="padding-left: 20px;" class="well">
            <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                <strong><fmt:message key="label.solicitud.conceptoCTCRegional" /></strong>
            </blockquote>
            <div class="collapsible-content">
                <div class="col-lg-12">
                    <div class="form-group form-group-sm">
                        <c:if test="${infoDevoluciones.concepto == 7}">
                            <label class="radio-inline" style="font-weight: bold;"><fmt:message key="label.aprobada" /></label>
                        </c:if>
                        <c:if test="${infoDevoluciones.concepto == 6}">
                            <div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-3 control-label control-label-sm"
                                        style="text-align: left;"><fmt:message key="label.negada" /> </label>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-3 control-label control-label-sm"
                                        style="text-align: left;"><fmt:message key="label.criteriosNegacion" /> </label>
                                    <div class="col-lg-9">
                                        <ul>
                                            <c:forEach items="${infoDevoluciones.criteriosNegacion}" var="_criteriosNeg">
                                                <li>${_criteriosNeg}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${infoDevoluciones.concepto == 3}">
                            <div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-3 control-label control-label-sm"
                                        style="text-align: left;"><fmt:message key="label.anulada" /></label>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-2 control-label control-label-sm"
                                        style="text-align: left;"><fmt:message key="label.causalAnulacion" /> </label>
                                    <div class="col-lg-10">${infoDevoluciones.causalAnulacion}</div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${infoDevoluciones.concepto == 5}">
                            <label class="radio-inline" style="font-weight: bold;"><fmt:message key="label.respuesta" /></label>
                        </c:if>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div id="contAprobadoDept">
                        <c:if test="${param.casoDevolucion != 6}">
                            <c:if test="${param.tipoItem == itemMedicamento}">
                                <div class="form-group form-group-sm">
                                    <label for="periodoAprobDiasDeptInput" class="col-lg-4 control-label control-label-sm"
                                        style="text-align: left;"> <fmt:message key="label.periodo.aprobadoDias" />
                                    </label>
                                    <div class="col-lg-2">
                                        <input type="text" name="periodoAprobadoDev" id="periodoAprobDiasDeptInput"
                                            class="form-control input-sm" value="${infoDevoluciones.periodoAprobado}" />
                                    </div>
                                    <label for="diasXPeriodoDeptInput" class="col-lg-3 control-label control-label-sm"><fmt:message
                                            key="label.dias.periodo" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="diasPeriodoDev" id="diasXPeriodoDeptInput" class="form-control input-sm"
                                            value="${infoDevoluciones.diasPeriodo}" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="unidsAprobPeriodosDeptInput" class="col-lg-4 control-label control-label-sm"
                                        style="text-align: left;"> <fmt:message key="label.unidades.aprobadasPeriodo" />
                                    </label>
                                    <div class="col-lg-2">
                                        <input type="text" name="unidadesAprobadasDev" id="unidsAprobPeriodosDeptInput"
                                            class="form-control input-sm" value="${infoDevoluciones.unidadesAprobadas}" />
                                    </div>

                                    <label for="dosisAprobadaCTCDev" class="control-label control-label-sm text-right col-lg-3"><fmt:message
                                            key="label.dosisAprobada" /></label>
                                    <div class="col-lg-2">
                                        <input class="form-control input-sm" id="dosisAprobadaCTCDev" name="dosisAprobadaDev"
                                            value="${infoDevoluciones.dosisAprobada}" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-4 control-label control-label-sm"
                                        style="text-align: left;"> <fmt:message key="label.numeroEntregas" />
                                    </label>
                                    <div class="col-lg-2">
                                        <input type="text" name="numeroEntregasDev" id="numEntregasDeptInput" class="form-control input-sm"
                                            value="${infoDevoluciones.numeroEntregas}" />
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${param.tipoItem == itemProcedimiento}">
                                <div class="form-group form-group-sm">
                                    <label for="unidsAprobPeriodosDeptInput" class="col-lg-2 control-label control-label-sm"
                                        style="text-align: left;"> <fmt:message key="label.cantidadAprobada" />
                                    </label>
                                    <div class="col-lg-2">
                                        <input type="text" name="unidadesAprobadasDev" id="unidsAprobPeriodosDeptInput"
                                            class="form-control input-sm" value="${infoDevoluciones.unidadesAprobadas}" />
                                    </div>
                                </div>
                            </c:if>
                            
                            <c:if test="${param.tipoItem == itemInsumo}">
                               <div class="form-group form-group-sm">
                                    <label for="diasXPeriodoDeptInput" class="col-lg-3 control-label control-label-sm"><fmt:message
                                            key="label.dias.periodo" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="diasPeriodoDev" id="diasXPeriodoDeptInput" class="form-control input-sm"
                                            value="${infoDevoluciones.diasPeriodo}" />
                                    </div>
                                    <label for="unidsAprobPeriodosDeptInput" class="col-lg-2 control-label control-label-sm"
                                        style="text-align: left;"> <fmt:message key="label.cantidadAprobada" />
                                    </label>
                                    <div class="col-lg-2">
                                        <input type="text" name="unidadesAprobadasDev" id="unidsAprobPeriodosDeptInput"
                                            class="form-control input-sm" value="${infoDevoluciones.unidadesAprobadas}" />
                                    </div>
                                </div>
                             </c:if>
                            
                        </c:if>
                        <div class="form-group form-group-sm">
                            <label for="justifiConceptDeptTextArea" class="col-lg-12 control-label control-label-sm"
                                style="text-align: left;"> <fmt:message key="label.justificacion.regional" />
                            </label>
                            <div class="col-lg-12">
                                <textarea name="justificacionDev" id="justificacionDev" class="form-control input-sm">${infoDevoluciones.justificacion}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <br />
            </div>
        </fieldset>
    </c:if>

    <!-- El caso 2 se trata cuando el auditor regional ve el motivo por el cual el auditor nacional devolvió la solicitud, 
    también en los casos que se autorizó o negó la solicitud por parte del auditor nacional -->
    <c:if test="${param.casoDevolucion == 2 || param.casoDevolucion == 5}">
        <fieldset disabled="disabled" style="" class="well">
            <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                <c:if test="${infoDevoluciones.concepto == 3}">
                    <strong><fmt:message key="label.solicitud.conceptoCTCRegional" /></strong>
                </c:if>
                <c:if test="${infoDevoluciones.concepto != 3}">
                    <strong><fmt:message key="label.solicitud.conceptoCTCNacional" /></strong>
                </c:if>
            </blockquote>
            <div class="collapsible-content">
                <c:if test="${infoDevoluciones.concepto == 3}">
                    <span class="text-success"><strong><fmt:message key="label.solicitud.conceptoCTCRegional" /></strong></span>
                </c:if>
                <c:if test="${infoDevoluciones.concepto != 3}">
                    <span class="text-success"><strong><fmt:message key="label.solicitud.conceptoCTCNacional" /></strong></span>
                </c:if>
                <div class="col-lg-12">
                    <div class="form-group form-group-sm" style="padding-left: 20px;">
                        <c:if test="${infoDevoluciones.concepto == 1}">
                            <label class="radio-inline" style="font-weight: bold; padding-left: 0px;"><fmt:message
                                    key="label.aprobada" /></label>
                            <br />
                            <fieldset id="fieldSetConceptoCTC" disabled="disabled">
                                <c:if test="${param.tipoItem == itemMedicamento}">
                                    <div class="form-group form-group-sm">
                                        <label for="periodoAprobDiasInputDev" class="col-lg-4 control-label" style="text-align: left;">
                                            <fmt:message key="label.periodo.aprobadoDias" />
                                        </label>
                                        <div class="col-lg-2">
                                            <input type="text" name="periodoAprobadoDev" id="periodoAprobDiasInputDev"
                                                class="form-control input-sm" value="${infoDevoluciones.periodoAprobado}" />
                                        </div>
                                        <label for="diasXPeriodoInput" class="col-lg-3 control-label"><fmt:message
                                                key="label.dias.periodo" /></label>
                                        <div class="col-lg-2">
                                            <input type="text" name="diasPeriodo" id="diasXPeriodoInput" class="form-control input-sm"
                                                value="${infoDevoluciones.diasPeriodo}" />
                                        </div>
                                    </div>
                                    <div class="form-group form-group-sm">
                                        <label for="unidsAprobPeriodosInputDev" class="col-lg-4 control-label" style="text-align: left;">
                                            <fmt:message key="label.unidades.aprobadasPeriodo" />
                                        </label>
                                        <div class="col-lg-2">
                                            <input type="text" name="unidadesAprobadasDev" id="unidsAprobPeriodoInputDev"
                                                class="form-control input-sm" value="${infoDevoluciones.unidadesAprobadas}" />
                                        </div>
                                        <label for="dosisAprobadaCTCDev" class="control-label control-label-sm text-right col-lg-3"><fmt:message
                                                key="label.dosisAprobada" /></label>
                                        <div class="col-lg-2">
                                            <input class="form-control input-sm" id="dosisAprobadaCTCDev" name="dosisAprobadaDev"
                                                value="${infoDevoluciones.dosisAprobada}" />
                                        </div>
                                    </div>
                                    <div class="form-group form-group-sm">
                                        <label for="numEntregasInputDev" class="col-lg-4 control-label" style="text-align: left;"> <fmt:message
                                                key="label.numeroEntregas" />
                                        </label>
                                        <div class="col-lg-2">
                                            <input type="text" name="numeroEntregasDev" id="numEntregasInputDev"
                                                class="form-control input-sm" value="${infoDevoluciones.numeroEntregas}" />
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${param.tipoItem == itemProcedimiento}">
                                    <div class="form-group form-group-sm">
                                        <label for="unidsAprobPeriodosDev" class="col-lg-2 control-label control-label-sm"
                                            style="text-align: left;"> <fmt:message key="label.cantidadAprobada" />
                                        </label>
                                        <div class="col-lg-2">
                                            <input type="text" name="unidadesAprobadasDev" id="unidsAprobPeriodosDev"
                                                class="form-control input-sm" value="${infoDevoluciones.unidadesAprobadas}" />
                                        </div>
                                    </div>
                                </c:if>
                            </fieldset>
                        </c:if>
                        <c:if test="${infoDevoluciones.concepto == 2}">
                            <div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-3 control-label control-label-sm"
                                        style="text-align: left; padding-left: 0px;"><fmt:message key="label.negada" /> </label>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-3 control-label control-label-sm"
                                        style="text-align: left; padding-left: 0px;"><fmt:message key="label.criteriosNegacion" />
                                    </label>
                                    <div class="col-lg-9">
                                        <ul>
                                            <c:forEach items="${infoDevoluciones.criteriosNegacion}" var="_criteriosNeg">
                                                <li>${_criteriosNeg}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${infoDevoluciones.concepto == 3}">
                            <div>
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-3 control-label control-label-sm" style="text-align: left;"><fmt:message
                                            key="label.anulada" /></label>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-2 control-label control-label-sm" style="text-align: left;"><fmt:message
                                            key="label.causalAnulacion" /> </label>
                                    <div class="col-lg-10">${infoDevoluciones.causalAnulacion}</div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${infoDevoluciones.concepto == 4}">
                            <div>
                                <div class="form-group form-group-sm">
                                    <label for="numEntregasDeptInput" class="col-lg-3 control-label control-label-sm"
                                        style="text-align: left; padding-left: 0px;"><fmt:message key="label.devuelta" /></label>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-3" style="text-align: left;"><fmt:message key="label.causalDevolucion" />
                                    </label>
                                    <div class="col-lg-9">${infoDevoluciones.causalDevolucion}</div>
                                </div>
                            </div>
                        </c:if>
                        <div class="form-group form-group-sm" style="">
                            <c:if test="${infoDevoluciones.concepto != 3}">
                                <label for="justificacionNacional" class="col-lg-12 control-label control-label-sm"
                                    style="text-align: left;"> <fmt:message key="label.justificacion.nacional" />
                                </label>
                            </c:if>
                            <c:if test="${infoDevoluciones.concepto == 3}">
                                <label for="justificacionNacional" class="col-lg-12 control-label control-label-sm"
                                    style="text-align: left;"> <fmt:message key="label.justificacion.regional" />
                                </label>
                            </c:if>
                            <div class="col-lg-12" style="">
                                <textarea name="justificacionDev" id="justificacionDev" class="form-control input-sm">${infoDevoluciones.justificacion}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <br />
            </div>
        </fieldset>
    </c:if>

    <!-- El caso 3 se trata cuando el auditor regional ve la respuesta de la IPS a una solicitud devuelta previamente -->
    <c:if test="${param.casoDevolucion == 3}">
        <fieldset disabled="disabled" class="well">
            <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                <strong><fmt:message key="label.respuestaIPS" /> </strong>
            </blockquote>
            <div class="collapsible-content" style="padding-left: 20px;">
                <div class="col-lg-12">
                    <div>
                        <div class="form-group form-group-sm">
                            <label for="justifiConceptDeptTextArea" class="col-lg-12 control-label control-label-sm"
                                style="text-align: left;"> <fmt:message key="label.respuestaIPS" />
                            </label>
                            <div class="col-lg-12">
                                <textarea name="justificacionDev" id="justificacionDev" class="form-control input-sm">${infoDevoluciones.justificacion}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <br />
            </div>
        </fieldset>
    </c:if>
</c:if>