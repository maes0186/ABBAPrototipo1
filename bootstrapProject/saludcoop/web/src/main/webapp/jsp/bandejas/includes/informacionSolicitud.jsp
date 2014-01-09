<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<fmt:message key='label.causaExterna' var="CAUSA_EXTERNA_LABEL" />
<fmt:message key='label.finalidad' var="FINALIDAD_LABEL" />
<fmt:message key='label.tipoCatastrofico' var="TIPO_CATASTROFICO_LABEL" />
<fmt:message key='label.entidadRecobro' var="ENTIDAD_RECOBRO_LABEL" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />


<fieldset class="well" <c:out value="${param.disableInput}"/>>
    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
        <strong><fmt:message key="label.info.solicitud" /></strong>
    </blockquote>
    <div class="collapsible-content">
        <div class="form-group form-group-sm">
            <label for="causaExternaSelect" class="col-lg-2 control-label control-label-sm"><fmt:message key="label.externalcause" /></label>
            <div class="col-lg-3">
                <form:select path="causasExternas" name="causaExterna" id="causaExternaSelect" class="form-control input-sm">
                    <form:option value="" label="${SELECCIONE_LABEL}" />
                    <form:options items="${causasExternas}" itemLabel="descripcion" itemValue="id" />
                </form:select>
            </div>
            <label for="finalidadSelect" class="col-lg-3 control-label control-label-sm"><fmt:message key="label.finalidad" /></label>
            <div class="col-lg-3">
                <form:select path="finalidades" name="finalidad" id="finalidadSelect" class="form-control input-sm">
                    <form:option value="" label="${SELECCIONE_LABEL}" />
                    <form:options items="${finalidades}" itemLabel="descripcion" itemValue="id" />
                </form:select>
            </div>
        </div>
        <div class="form-group form-group-sm">
            <label for="tipoCatastroficoSelect" class="col-lg-2 control-label control-label-sm"><fmt:message
                    key="label.tipoCatastrofico" /></label>
            <div class="col-lg-3">
                <form:select path="tiposCatastroficos" name="tipoCatastrofico" id="tipoCatastroficoSelect" class="form-control input-sm">
                    <form:option value="" label="${SELECCIONE_LABEL}" />
                    <form:options items="${tiposCatastroficos}" itemLabel="descripcion" itemValue="id" />
                </form:select>
            </div>
            <label for="entidadRecobroSelect" class="col-lg-3 control-label control-label-sm"><fmt:message
                    key="label.infoSolicitud.entidadRecobro" /></label>
            <div class="col-lg-3">
                <form:select path="entidadesRecobro" name="entidadRecobro" id="entidadRecobroSelect" class="form-control input-sm">
                    <form:option value="" label="${SELECCIONE_LABEL}" />
                    <form:options items="${entidadesRecobro}" itemLabel="descripcion" itemValue="id" />
                </form:select>
            </div>
        </div>
        <c:if test="${not empty infoGeneral.observaciones}">
            <div class="form-group form-group-sm">
                <label class="col-lg-2 control-label control-label-sm" for="observaciones"><fmt:message
                        key="label.observations" /></label>
                <div class="col-lg-9">
                <textarea id="observaciones" name="observaciones"class="form-control" rows="2" disabled="disabled">${infoGeneral.observaciones}</textarea>
                </div>
            </div>
        </c:if>
        <c:if test="${!param.esAC}">
            <div class="form-group form-group-sm">
                <label for="resHistClinica" class="col-lg-2 control-label control-label-sm"><fmt:message
                        key="label.infoSolicitud.resHistClin" /></label>
                <div class="col-lg-9">
                    <textarea id="resHistClinica" disabled="disabled" class="form-control">${infoSolicitud.descHistClinica}</textarea>
                </div>
            </div>
        </c:if>
        <br />
    </div>
</fieldset>
<script>
    $(document).ready(function() {    
        selectDefaultValues();
    });

    /**
    * Selecciona por defecto los valores por defecto para los selects de la información 
    * de la solicitud 
    */
    function selectDefaultValues() {
        try {
            selectDefault("causaExternaSelect", ${infoSolicitud.causaExternaSeleccionada != null ? infoSolicitud.causaExternaSeleccionada : 'null'});
            selectDefault("finalidadSelect", ${infoSolicitud.finalidadSeleccionada != null ? infoSolicitud.finalidadSeleccionada : 'null'});
            selectDefault("tipoCatastroficoSelect", ${infoSolicitud.tipoCatastroficoSeleccionado != null ? infoSolicitud.tipoCatastroficoSeleccionado : 'null'});            
            selectDefault("entidadRecobroSelect", ${infoSolicitud.entidadSeleccionada != null ? infoSolicitud.entidadSeleccionada : 'null'});            
        } catch(e) {                
        }
    }
</script>