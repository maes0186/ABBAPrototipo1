<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="respSI" value="<%=SystemConstants.SI%>" />
<c:set var="tipoBandejaNacional" value="<%=SystemConstants.BANDEJA_NACIONAL%>" />
<c:set var="tipoBandejaRegional" value="<%=SystemConstants.BANDEJA_REGIONAL%>" />

<c:if test="${bandejasParam.editable}">
    <c:set var="disableInput" value="" />
</c:if>
<c:if test="${!bandejasParam.editable}">
    <c:set var="disableInput" value="disabled=disabled" />
</c:if>
<fieldset class="well">
    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
        <strong><fmt:message key="label.alt.pos.prev.utili" /></strong>
    </blockquote>
    <div class="collapsible-content">
        <c:if test="${infoProcedimiento.sinAlternativaPOSPrev}">
            <label for="justifNOexisteAlternPOSPrev" class="control-label control-label-sm col-lg-12" style="text-align: left;"><fmt:message
                    key="label.procedimiento.sinAlternativaPosPrev" /></label>
            <div class="form-group">
                <label for="justiNOexistefAlternPOSPrev" class="control-label control-label-sm col-lg-2"><fmt:message
                        key="label.nopos.justificacion" /></label>
                <div class="col-lg-10">
                    <textarea class="form-control" id="justifNOexisteAlternPOSPrev" disabled="disabled">${infoProcedimiento.justificacionSinPOSPrev}</textarea>
                </div>
            </div>
        </c:if>
        <c:if test="${!infoProcedimiento.sinAlternativaPOSPrev}">
            <p>
                <fmt:message key="label.procedimiento.posPreviosUtilizados" />
            </p>
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th style="width: 20%" class="text-center"><small><fmt:message key="label.code" /></small></th>
                        <th class="text-center"><small><fmt:message key="label.description" /></small></th>
                        <th class="text-center"><small><fmt:message key="label.respuestaClinicaObservada" /></small></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${infoProcedimiento.procedimientosAnteriores}" var="procedimientosPrevios">
                        <tr>
                            <td><c:out value="${procedimientosPrevios.procedimiento.codigo}" /></td>
                            <td><c:out value="${procedimientosPrevios.procedimiento.descripcion}" /></td>
                            <td><c:out value="${procedimientosPrevios.respuestaClinicaObservada.descripcion}" /></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
    </div>
</fieldset>
<fieldset class="well" <c:out value="${disableInput}" />>
    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
        <strong><fmt:message key="label.procedimiento.nopos.posHomologo" /></strong>
    </blockquote>
    <div class="collapsible-content">
        <div id="procedHomologo-messages"></div>
        <jsp:include page="./procedimientoHomologo.jsp">
            <jsp:param value="${bandejasParam.editable}" name="editable" />
        </jsp:include>
        <div class="form-group form-group-sm">
            <label for="cantidadPeridoHomologo" class="col-lg-3 control-label control-label-sm"> <fmt:message
                    key="label.cantidad.periodo" />
            </label>
            <div class="col-lg-3">
                <input type="text" name="cantidadPeridoHomologo" value="${infoProcedimiento.procedimientoPOSHomologo.cantidadPeriodo}"
                    id="cantidadPeridoHomologo" class="form-control input-sm" />
            </div>
            <label for="objProcedHom" class="col-lg-3 control-label control-label-sm"><fmt:message key="label.objetivo" /></label>
            <div class="col-lg-3">
                <form:select path="objetivos" name="objProcedHom" id="objProcedHom" class="form-control input-sm">
                    <form:options items="${objetivos}" itemLabel="descripcion" itemValue="id" />
                </form:select>
            </div>
        </div>
        <div class="form-group form-group-sm">
            <label for="tiempoPeriodoHomologo" class="col-lg-3 control-label control-label-sm"> <fmt:message
                    key="label.tiempo.uso.periodo" />
            </label>
            <div class="col-lg-3">
                <input type="text" name="tiempoPeriodoHomologo" value="${infoProcedimiento.procedimientoPOSHomologo.diasDeUso}"
                    id="tiempoPeriodoHomologo" class="form-control input-sm" />
            </div>
            <label for="frecuenciaUsoHomologo" class="col-lg-3 control-label control-label-sm"><fmt:message
                    key="label.frecuencia.uso.periodo" /> </label>
            <div class="col-lg-3">
                <input type="text" name="frecuenciaUsoHomologo" value="${infoProcedimiento.procedimientoPOSHomologo.frecuenciaDeUso}"
                    id="frecuenciaUsoLabel" class="form-control input-sm" />
            </div>
        </div>
        <div class="form-group form-group-sm">
            <label for="justificacionNoHomolLabel" class="col-lg-3 control-label control-label-sm"><fmt:message
                    key="label.justificacion.noHomologo" /></label>

            <div class="col-lg-9">
                <textarea name="justificacionNoHomoTextArea" disabled="disabled" id="justificacionNoHomolLabel" class="form-control">${infoProcedimiento.justificacionMedicoNoHomologo}</textarea>
            </div>
        </div>
    </div>
</fieldset>
<fieldset class="well" disabled="disabled">
    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
        <strong><fmt:message key="label.nopos.justificacion.solicitud" /></strong>
    </blockquote>
    <div class="collapsible-content">
        <div class="form-group form-group-sm">
            <label class="col-lg-10 control-label control-label-sm" style="text-align: left;">1. <fmt:message
                    key="label.infoSolicitud.riesgoInmminente" />:
            </label> <label class="col-lg-1 control-label control-label-sm text-info" style="text-align: left;">${infoProcedimiento.esRiesgoParaVida}</label>
        </div>
        <c:if test="${infoProcedimiento.esRiesgoParaVida == respSI}">
            <div class="form-group form-group-sm">
                <label for="justificRiesgoTextArea" class="col-lg-3 control-label control-label-sm"><fmt:message
                        key="label.justificacionRiesgo" />:</label>
                <div class="col-lg-9">
                    <textarea name="justificRiesgoTextArea" id="justificRiesgoTextArea" class="form-control">${infoProcedimiento.justificacionRiesgo}</textarea>
                </div>
            </div>
        </c:if>
        <div class="form-group form-group-sm">
            <label class="col-lg-10 control-label control-label-sm" style="text-align: left;">2. <fmt:message
                    key="label.nopos.justificacion.posibilidadesPOSAgotadas" />:
            </label> <label class="col-lg-1 control-label control-label-sm text-info" style="text-align: left;">${infoProcedimiento.seAgotoPosibilTerapEnPOS}</label>
        </div>
        <div class="form-group form-group-sm">
            <label class="col-lg-10 control-label control-label-sm" style="text-align: left;">3. <fmt:message
                    key="label.nopos.justificacion.autorizadoINVIMA" />:
            </label> <label class="col-lg-2 control-label control-label-sm text-info" style="text-align: left;">${infoProcedimiento.esAutorizadoInvima}</label>
        </div>
        <div class="form-group form-group-sm">
            <label for="justificMedico" class="col-lg-2 control-label control-label-sm"><fmt:message
                    key="label.infoSolicitud.justificacionMedico" />:</label>
            <div class="col-lg-9">
                <textarea name="justificMedico" id="justificMedico" class="form-control">${infoProcedimiento.justificacionMedicoSolicitud}</textarea>
            </div>
        </div>
    </div>
</fieldset>

<script>
    $(document).ready(function() {    
        try {
        	selectDefault("objProcedHom", ${infoProcedimiento.procedimientoPOSHomologo.objetivo.id != null ? infoProcedimiento.procedimientoPOSHomologo.objetivo.id : 'null'});           
        } catch(e) {
        	
        }
    });
</script>