<script>
	function applyEpicrisis() {
		$container.attr("data-form-epicrisis", $("#epicrisisManualForm").serialize());
	}

	function resetFieldsEpicrisis() {
		$("#epicrisisManualForm input, #epicrisisManualForm textarea").val("");
	}

	$(document).ready(function() {
		$("#idAceptarEpicrisis").click(function() {
			applyEpicrisis();
		});

		$("#idCancelarEpicrisis").click(function() {
			resetFieldsEpicrisis();
		});

	});
</script>
<div class="modal fade" id="epicrisisManual" data-width="900" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Epicrisis</h3>
	</div>
	<div class="modal-body">
		<form id="epicrisisManualForm" class="form-horizontal">
			<fieldset>
				<div class="form-group form-group-sm">
					<label for="motivoConsulta" class="col-lg-3 control-label control-label-sm">Motivo de la consulta</label>
					<div class="col-lg-9">
						<textarea rows="3" class="form-control input-sm" name="epicrisis.motivoConsulta"></textarea>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="enfermedadActual" class="col-lg-3 control-label control-label-sm">Enfermedad Actual</label>
					<div class="col-lg-9">
						<textarea rows="3" class="form-control input-sm" name="epicrisis.enfermedadActual"></textarea>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="antecedentes" class="col-lg-3 control-label control-label-sm">Antecedentes</label>
					<div class="col-lg-9">
						<span class="help-block">Breve resumen de historia clínica y antecedentes médicos, sociales y psicológicos importantes en relación al motivo de consulta.</span>
						<textarea rows="6" class="form-control input-sm" name="epicrisis.antecedentes"></textarea>
					</div>
				</div>
				
				<div class="col-lg-12">
					<p class="text-info">Revisión Por Sistemas</p> 
					<table class="table table-hover">
						<thead>
							<tr class="row">
								<th class="data col-lg-2" name="sistema">Sistema</th>
								<th class="data col-lg-10" name="observaciones">Observaciones</th>
							</tr>
						</thead>
						<tbody>
							<tr class="row">
								<td>Síntomas generales</td>
								<td><textarea class="input-flat form-control input-sm" name="epicrisis.sintomasGenerales"></textarea></td>
							</tr>
							<tr class="row">
								<td>Sistema respiratorio</td>
								<td><textarea class="input-flat form-control input-sm" name="epicrisis.sistemaRespiratorio"></textarea></td>
							</tr>
							<tr class="row">
								<td>Sistema cardiovascular</td>
								<td><textarea class="input-flat form-control input-sm" name="epicrisis.sistemaCardiovascular"></textarea></td>
							</tr>
							<tr class="row">
								<td>Sistema gastrointestinal o digestivo</td>
								<td><textarea class="input-flat form-control input-sm" name="epicrisis.sistemaGastroDigestivo"></textarea></td>
							</tr>
							<tr class="row">
								<td>Sistema genitourinario</td>
								<td><textarea class="input-flat form-control input-sm" name="epicrisis.sistemaGenitourinario"></textarea></td>
							</tr>
							<tr class="row">
								<td>Sistema endocrino</td>
								<td><textarea class="input-flat form-control input-sm" name="epicrisis.sistemaEndocrino"></textarea></td>
							</tr>
							<tr class="row">
								<td>Sistema neurológico</td>
								<td><textarea class="input-flat form-control input-sm" name="epicrisis.sistemaNeurologico"></textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
						
		</fieldset>
	</form>
</div>
<div class="modal-footer">
	<button type="button" id="idAceptarEpicrisis" class="btn btn-primary btn-success" data-dismiss="modal">Aceptar</button>
	<button type="button" id="idCancelarEpicrisis" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
</div>
</div>