<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:message key='label.cantidadSolicitada' var="CANT_SOL_LABEL" />
<fmt:message key='label.dosis' var="DOSIS_LABEL" />
<fmt:message key='label.frecuencia' var="FRECUENCIA_LABEL" />
<fmt:message key='label.duracionTratamiento' var="DUR_TRAT_LABEL" />
<fmt:message key='label.posologia' var="POSOLOGIA_LABEL" />
<fmt:message key='label.code' var="CODIGO_CUM" />
<fmt:message key='label.name' var="NOMBRE" />

<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
    <strong><fmt:message key="label.bandeja.insumoSolicitado" /></strong>
</blockquote>
<div class="collapsible-content">
    <div class="form-group form-group-sm">
        <label for="codigoInsumo" class="control-label control-label-sm text-right col-lg-2">${CODIGO_CUM}</label>
        <div class="col-lg-2">
            <input class="form-control input-sm" id="codigoInsumo" name="codigoInsumo" placeholder="${CODIGO_CUM}"
                value="${bandejaAC.insumo.codigo}" />
        </div>
    </div>

    <div class="form-group form-group-sm">
        <label for="nombreInsumo" class="control-label control-label-sm text-right col-lg-2">${NOMBRE}</label>
        <div class="col-lg-9">
            <input class="form-control input-sm" id="nombreInsumo" name="nombreInsumo" placeholder="${NOMBRE}"
                value="${bandejaAC.insumo.nombre}" />
        </div>
    </div>
    <div class="form-group form-group-sm">
        <label for="cantidadSolicitada" class="control-label control-label-sm text-right col-lg-2">${CANT_SOL_LABEL}</label>
        <div class="col-lg-2">
            <input class="form-control input-sm" id="cantidadSolicitada" name="cantidadSolicitada" placeholder="${CANT_SOL_LABEL}"
                maxlength="4" value="${bandejaAC.insumo.cantidadSolicitada}" />
        </div>
        <label for="duracionTratamiento" class="control-label control-label-sm text-right col-lg-3">${DUR_TRAT_LABEL}</label>
        <div class="col-lg-3">
            <input class="form-control input-sm" id="duracionTratamiento" name="duracionTratamiento" placeholder="${DUR_TRAT_LABEL}"
                value="${bandejaAC.insumo.duracionTratamiento}" />
        </div>
    </div>
    <br />
</div>