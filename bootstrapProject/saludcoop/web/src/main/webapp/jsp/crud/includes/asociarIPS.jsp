<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />

<fmt:message key='label.asociacionIPS' var="ASOCIACION" />
<fmt:message key='label.button.aceptar' var="ACEPTAR" />
<fmt:message key='label.button.cancelar' var="CANCELAR" />



<div class="modal fade" id="asociarIPSModal" data-width="900"
	style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h3 class="modal-title">${ASOCIACION}</h3>
	</div>
	<div class="modal-body">
		<div id="asociarIPS-messages"></div>
		<form id="asociarIPSModalForm" class="form-horizontal">
			<fieldset>
				<blockquote class="col-lg-12 collapsible-header"
					style="padding: 2px; font-size: 16px;">
					<strong>Información Ips</strong>
				</blockquote>
				<div class="form-group form-group-sm">
					<h4 class="col-lg-10 col-lg-offset-2" id="item-description"></h4>
				</div>
				<div
					class="row form-group form-group-sm searchForm  collapsible-content">

					<input role="identificacion" tipoDatos="ips" nameLeft="tipoIdentificacion"
						nameRight="numeroIdentificacion" class="template-hidden col-lg-12"
						avoidDescriptionLabel="true" /> <input placeholder="Razón Social"
						name="razonSocial" id="razonSocial" row="2"
						class="col-lg-4 template-hidden"> <input
						placeholder="Código Municipio" id="municipioCodigo" row="3"
						class="col-lg-2 template-hidden"> <input
						placeholder="Nombre Municipio" id="municipioNombre" row="3"
						class="col-lg-2 template-hidden"> <input
						placeholder="Dirección" id="direccion" row="4"
						class="col-lg-4 template-hidden">
				</div>

				<div class="row collapsible-content">
					<div class="col-lg-12">
						<table class="table  table-hover">
							<thead>
								<tr class="row">
									<th class="data col-lg-2" name="razonSocial">Razón Social</th>
									<th class="data col-lg-2" name="municipioNombre">Municipio</th>
									<th class="data col-lg-2" name="direccion">Dirección</th>


								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" id="idAceptarAsociacion"
			class="btn btn-primary btn-success">${ACEPTAR}</button>
		<button type="button" id="idCancelarAsociacion" class="btn btn-danger"
			data-dismiss="modal">${CANCELAR}</button>
	</div>
</div>

<script>


$(document).ready(function() {
	
});

function fillasociarIPSModal($container) {
	
	$("#asociarIPSModalForm #item-description").text($container.parents("tr").find("[id='descripcion']").val());

}

$("#idAceptarAsociacion").click(function() {
	$("#prescripcion-procedimiento-messages").empty();
	if ($("#asociarIPSModalForm").valid()){
		applyIPS();
		resetFieldsIPS();
		$("#asociarIPSModal").modal('hide');
		$container.removeClass("btn-warning");
		$container.addClass("btn-success");	
	}
});

$("#idCancelarPrescripcionProcedimiento").click(function() {
	resetFieldsIPS();
});

function applyIPS() {
	$container.data($("#asociarIPSModalForm").serializeArray());
	$container.attr("data-form-prescr", $("#asociarIPSModalForm").serialize());
}

function resetFieldsIPS() {
	genericResetFields("asociarIPSModalForm");
	setDefaults("asociarIPSModalForm");
}




</script>