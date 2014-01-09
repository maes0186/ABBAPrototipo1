<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="respSI" value="<%=SystemConstants.SI%>" />

<fmt:message key='label.nopos.justificacion.solicitud' var="justificacionSolicitud" />
<fmt:message key='label.solicitud.conceptoCTCDepartamental' var="conceptoCTCDepartamental" />
<fmt:message key='label.infoSolicitud.insumoHomologado' var="insumoHomologado" />
<fmt:message key='label.infoSolicitud.insumoSolicitado' var="insumoSolicitado" />

<c:if test="${bandejasParam.editable}">
    <c:set var="disableInput" value="" />
</c:if>
<c:if test="${!bandejasParam.editable}">
    <c:set var="disableInput" value="disabled=disabled" />
</c:if>



<!-- <fieldset class="well"> -->
<!--     <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;"> -->
<%--         <strong><fmt:message key="label.alt.pos.prev.utili" /></strong> --%>
<!--     </blockquote> -->
<!--     <div class="collapsible-content"> -->
<%--         <c:if test="${infoInsumo.sinAlternativaPOSPrev}"> --%>
<%--             <label for="justifNOexisteAlternPOSPrev" class="control-label control-label-sm col-lg-12" style="text-align: left;"><fmt:message --%>
<%--                     key="label.insumo.sinAlternativaPosPrev" /></label> --%>
<!--             <div class="form-group"> -->
<%--                 <label for="justiNOexistefAlternPOSPrev" class="control-label control-label-sm col-lg-2"><fmt:message --%>
<%--                         key="label.nopos.justificacion" /></label> --%>
<!--                 <div class="col-lg-10"> -->
<%--                     <textarea class="form-control" id="justifNOexisteAlternPOSPrev" disabled="disabled">${infoInsumo.justificacionSinPOSPrev}</textarea> --%>
<!--                 </div> -->
<!--             </div> -->
<%--         </c:if> --%>
<%--         <c:if test="${!infoInsumo.sinAlternativaPOSPrev}"> --%>
<!--             <p> -->
<%--                 <fmt:message key="label.insumo.posPreviosUtilizados" /> --%>
<!--             </p> -->
<!--             <table class="table table-striped table-bordered table-hover"> -->
<!--                 <thead> -->
<!--                     <tr> -->
<%--                         <th style="width: 20%" class="text-center"><small><fmt:message key="label.code" /></small></th> --%>
<%--                         <th class="text-center"><small><fmt:message key="label.description" /></small></th> --%>
<%--                         <th class="text-center"><small><fmt:message key="label.dosis" /></small></th> --%>
<%--                         <th class="text-center"><small><fmt:message key="label.infoSolicitud.diasTratamiento" /></small></th> --%>
<%--                         <th class="text-center"><small><fmt:message key="label.respuestaClinicaObservada" /></small></th> --%>
<!--                     </tr> -->
<!--                 </thead> -->
<!--                 <tbody> -->
<%--                     <c:forEach items="${infoInsumo.insumosAnteriores}" var="insumosPrevios"> --%>
<!--                         <tr> -->
<%--                             <td><c:out value="${insumosPrevios.insumo.codigo}" /></td> --%>
<%--                             <td><c:out value="${insumosPrevios.insumo.descripcion}" /></td> --%>
<%--                             <td><c:out value="${insumosPrevios.dosis}" /></td> --%>
<%--                             <td><c:out value="${insumosPrevios.diasTratamiento}" /></td> --%>
<%--                             <td><c:out value="${insumosPrevios.respuestaClinicaObservada.descripcion}" /></td> --%>
<!--                         </tr> -->
<%--                     </c:forEach> --%>
<!--                 </tbody> -->
<!--             </table> -->
<%--         </c:if> --%>
<!--     </div> -->
<!-- </fieldset> --> 
<fieldset disabled="disabled" class="well">
    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
        <strong>${justificacionSolicitud}</strong>
    </blockquote>
    <div class="collapsible-content">
        <div class="form-group form-group-sm">
            <label for="listadoDocsAdjunts" class="col-lg-4 control-label control-label-sm"> <fmt:message
                    key="label.infoSolicitud.listadoDocumentos" />
            </label>
            <div class="col-lg-6">
                <jsp:include page="./docAdjuntos.jsp">
                    <jsp:param name="docAdjuntosName" value="docAdjuntos" />
                </jsp:include>
            </div>
        </div>
        <div class="form-group form-group-sm">
            <label class="col-lg-10 control-label control-label-sm" style="text-align: left;">1. <fmt:message
                    key="label.infoSolicitud.riesgoInmminente" />:
            </label> <label class="col-lg-1 control-label control-label-sm text-info" style="text-align: left;">${infoInsumo.riesgoInminente}</label>
        </div>
        <c:if test="${infoInsumo.riesgoInminente == respSI}">
            <div class="form-group form-group-sm">
                <label for="justificRiesgoTextArea" class="col-lg-3 control-label control-label-sm"><fmt:message
                        key="label.justificacionRiesgo" />:</label>

                <div class="col-lg-9">
                    <textarea name="justificRiesgoTextArea" id="justificRiesgoTextArea" class="form-control">${infoInsumo.justificacionRiesgo}</textarea>
                </div>
            </div>
        </c:if>
        <div class="form-group form-group-sm">
            <label class="col-lg-10 control-label control-label-sm" style="text-align: left;">2. <fmt:message
                    key="label.nopos.justificacion.posibilidadesPOSAgotadas" />:
            </label> <label class="col-lg-1 control-label control-label-sm text-info" style="text-align: left;">${infoInsumo.posibilidadesAgotadas}</label>
        </div>
        <label class="col-lg-10 control-label control-label-sm" style="text-align: left;"><fmt:message key="label.insumo.noPos.solicitado" /></label>
        <table class="table table-striped table-bordered table-hover">
            <thead>
                <tr>
                    <th style="width: 20%" class="text-center"><small><fmt:message key="label.code" /></small></th>
                    <th class="text-center"><small><fmt:message key="label.description" /></small></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${infoInsumo.insumoSolicitado.insumo.codigo}</td>
                    <td>${infoInsumo.insumoSolicitado.insumo.descripcion}</td>
                </tr>
            </tbody>
        </table>
        <div class="form-group form-group-sm">
            <label class="col-lg-2 control-label  control-label-sm" for="cantidadMedicamSolic"><fmt:message key="label.cantidad" /></label>
            <div class="col-lg-2">
                <input type="text" name="cantidadMedicamSolic" id="cantidadMedicamSolic" class="form-control input-sm"
                    value="${infoInsumo.cantidadInsumoSolicitado}" />
            </div>
            <label class="col-lg-2 control-label  control-label-sm" for="duracionTratMedicamSolic"><fmt:message
                    key="label.infoSolicitud.duracionTratamiento" /></label>
            <div class="col-lg-2">
                <input type="text" name="duracionTratMedicamSolic" id="duracionTratMedicamSolic" class="form-control input-sm"
                    value="${infoInsumo.insumoSolicitado.duracion}" />
            </div>
        </div>
        <div class="form-group form-group-sm">
            <label for="justificMedico" class="col-lg-2 control-label control-label-sm"><fmt:message
                    key="label.infoSolicitud.justificacionMedico" />:</label>

            <div class="col-lg-9">
                <textarea name="justificMedico" id="justificMedico" class="form-control">${infoInsumo.justificacionMedico}</textarea>
            </div>
        </div>
    </div>
</fieldset>