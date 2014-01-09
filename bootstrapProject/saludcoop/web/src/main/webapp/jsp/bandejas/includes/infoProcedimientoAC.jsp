<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:message key='label.codigoCups' var="CODIGO_CUPS" />
<fmt:message key='label.name' var="NOMBRE" />
<fmt:message key='label.cantidadSolicitada' var="CANT_SOL_LABEL" />
<fmt:message key='label.lateralidad' var="LATERALIDAD_LABEL" />

<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
    <strong><fmt:message key="label.bandeja.procedimientoSolicitado" /></strong>
</blockquote>
<div class="collapsible-content">
    <div class="form-group form-group-sm">
        <label for="codigoProcedimiento" class="control-label control-label-sm text-right col-lg-2">${CODIGO_CUPS}</label>
        <div class="col-lg-2">
            <input class="form-control input-sm" id="codigoProcedimiento" name="codigoProcedimiento" placeholder="${CODIGO_CUPS}"
                value="${bandejaAC.procedimiento.codigo}" />
        </div>
    </div>

    <div class="form-group form-group-sm">
        <label for="nombreProcedimiento" class="control-label control-label-sm text-right col-lg-2">${NOMBRE}</label>
        <div class="col-lg-9">
            <input class="form-control input-sm" id="nombreProcedimiento" name="nombreProcedimiento" placeholder="${NOMBRE}"
                value="${bandejaAC.procedimiento.nombre}" />
        </div>
    </div>
    <div class="form-group form-group-sm">
        <label for="cantidadSolicitada" class="control-label control-label-sm text-right col-lg-2">${CANT_SOL_LABEL}</label>
        <div class="col-lg-2">
            <input class="form-control input-sm" id="cantidadSolicitada" name="cantidadSolicitada" placeholder="${CANT_SOL_LABEL}"
                maxlength="4" value="${bandejaAC.procedimiento.cantidadSolicitada}" />
        </div>
        <label for="lateralidadProcAC" class="control-label control-label-sm text-right col-lg-3">${LATERALIDAD_LABEL}</label>
        <div class="col-lg-2">
            <input class="form-control input-sm" id="lateralidadProcAC" name="lateralidadProcAC" placeholder="${LATERALIDAD_LABEL}"
                value="${bandejaAC.procedimiento.lateralidad}" />
        </div>
    </div>
    <br />
</div>