<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="./../includes/header.jsp">
    <jsp:param name="includeMenu" value="true" />
</jsp:include>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<div class="row">
    <div class=" col-lg-12">
        <div id="messages"></div>
        <div class="well">
            <form class="form-horizontal" id="informacionForm">
                <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
                    <strong>
                    	Detalle Solicitud
                    </strong>
                </blockquote>
                <div class="collapsible-content">
	                <jsp:include page="./../bandejas/includes/informacionGeneral_Usuario.jsp">
	                	<jsp:param name="bandejaIps" value="true"/>
	                </jsp:include>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="row">
	<div class=" col-lg-12">
		<div id="messages"></div>
		<div class="well form-horizontal">
			<fieldset>
				<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
					<strong>Diagnostico</strong>
				</blockquote>
				<div class="form-group form-group-sm collapsible-content">
					<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="diagnosticoCodigo"><fmt:message
							key="label.code" /></label>
					<div class=" col-lg-2 col-sm-2 col-xs-6">
						<input class="form-control input-sm" disabled="disabled" id="diagnosticoCodigo"	value="${bandejaCC.diagnostico.codigo}">
				    </div>
				</div>
				<div class="form-group form-group-sm collapsible-content">
					<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="diagnosticoDescripcion"><fmt:message
							key="label.description" /></label>
					<div class=" col-lg-6 col-sm-2 col-xs-6">
						<input class="form-control input-sm" disabled="disabled" id="diagnosticoDescripcion" value="${bandejaCC.diagnostico.descripcion}">
				    </div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
<c:choose>
	<c:when test="${bandejaCC.tipoItem == '1'}">
		<div class="row">
			<div class=" col-lg-12">
				<div id="messages"></div>
				<div class="well form-horizontal">
					<fieldset>
						<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
							<strong>Medicamento Solicitado</strong>
						</blockquote>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoCodigo"><fmt:message
									key="label.code" /></label>
							<div class=" col-lg-2 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="medicamentoCodigo" value="${bandejaCC.medicamento.codigo}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoNombre"><fmt:message
									key="label.name" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="medicamentoNombre" value="${bandejaCC.medicamento.nombre}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoCantidadSolicitada"><fmt:message
									key="label.infoSolicitud.cantidadSolicitada" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="medicamentoCantidadSolicitada" value="${bandejaCC.medicamento.cantidadSolicitada}">
						    </div>
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoDosis"><fmt:message
									key="label.infoSolicitud.dosis" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="medicamentoDosis" value="${bandejaCC.medicamento.dosis}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoViaAdministracion"><fmt:message
									key="label.viaAdministracion" /></label>
							<div class=" col-lg-2 col-sm-2 col-xs-6">
								<input class="form-control input-sm" id="medicamentoViaAdministracion" value="${bandejaCC.medicamento.viaAdministracion}" disabled="disabled">
						    </div>
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoDuracion"><fmt:message
									key="label.infoSolicitud.duracionTratamiento" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="medicamentoDuracion" value="${bandejaCC.medicamento.duracionTratamiento}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="medicamentoPosologia"><fmt:message
									key="label.posologia" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<textarea class="form-control textarea" rows="2" cols="40" disabled="disabled" id="medicamentoPosologia">${bandejaCC.medicamento.posologia}</textarea>
						    </div>
						</div>
						<div class="form-group form-group-sm text-right collapsible-content" style="padding-right: 20px">
							<button type="button" id="search" class="btn btn-info btn-sm">
								<fmt:message key="label.historial.devoluciones" />
							</button>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${bandejaCC.tipoItem == '2'}">
		<div class="row">
			<div class=" col-lg-12">
				<div id="messages"></div>
				<div class="well form-horizontal">
					<fieldset>
						<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
							<strong>Procedimiento Solicitado</strong>
						</blockquote>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoCodigo"><fmt:message
									key="label.code" /></label>
							<div class=" col-lg-2 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoCodigo" value="${bandejaCC.procedimiento.codigo}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoNombre"><fmt:message
									key="label.name" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoNombre" value="${bandejaCC.procedimiento.nombre}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoCantidadSolicitada"><fmt:message
									key="label.infoSolicitud.cantidadSolicitada" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoCantidadSolicitada" value="${bandejaCC.procedimiento.cantidadSolicitada}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoLateralidad"><fmt:message
									key="label.lateralidad" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoLateralidad" value="${bandejaCC.procedimiento.lateralidad}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="procedimientoIpsRemitente"><fmt:message
									key="label.solicitud.ipsRemitente" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="procedimientoIpsRemitente" value="${bandejaCC.procedimiento.ipsRemitente}">
						    </div>
						</div>
						<div class="form-group form-group-sm text-right collapsible-content" style="padding-right: 20px">
							<button type="button" id="search" class="btn btn-info btn-sm">
								<fmt:message key="label.historial.devoluciones" />
							</button>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${bandejaCC.tipoItem == '3'}">
		<div class="row">
			<div class=" col-lg-12">
				<div id="messages"></div>
				<div class="well form-horizontal">
					<fieldset>
						<blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
							<strong>Insumo Solicitado</strong>
						</blockquote>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="insumoCodigo"><fmt:message
									key="label.code" /></label>
							<div class=" col-lg-2 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="insumoCodigo" value="${bandejaCC.insumo.codigo}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="insumoNombre"><fmt:message
									key="label.name" /></label>
							<div class=" col-lg-6 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="insumoNombre" value="${bandejaCC.insumo.nombre}">
						    </div>
						</div>
						<div class="form-group form-group-sm collapsible-content">
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="insumoCantidadSolicitada"><fmt:message
									key="label.infoSolicitud.cantidadSolicitada" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="insumoCantidad" value="${bandejaCC.insumo.cantidad}">
						    </div>
							<label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="insumoDuracion"><fmt:message
									key="label.infoSolicitud.duracionTratamiento" /></label>
							<div class=" col-lg-1 col-sm-2 col-xs-6">
								<input class="form-control input-sm" disabled="disabled" id="insumoDuracion" value="${bandejaCC.insumo.duracionTratamiento}">
						    </div>
						</div>
						<div class="form-group form-group-sm text-right collapsible-content" style="padding-right: 20px">
							<button type="button" id="search" class="btn btn-info btn-sm">
								<fmt:message key="label.historial.devoluciones" />
							</button>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</c:when>
</c:choose>

<div class="well-sm text-right clearfix" style="padding: 20px">
	<div class="row">
			<div class="col-lg-offset-10 col-lg-2">
				<button type="button" id="cancelar" class="btn btn-danger btn-block">Volver</button>
			</div>
	</div>
</div>
<script>

    $(document).ready(function() {
    	
    	$("#cancelar").click(function(){
    		window.history.back();
		});

		
    });
</script>
<jsp:include page="./../includes/footer.jsp"/>