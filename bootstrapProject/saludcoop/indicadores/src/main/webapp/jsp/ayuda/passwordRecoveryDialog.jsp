<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />


<fmt:message key="label.user" var="usuarioLabel" />
<fmt:message key="label.mail" var="mailLabel" />
<fmt:message key="label.jcaptcha" var="captchaLabel" />
<script type="text/javascript">
	$(function() {

		$('#passwordRecoveryForm')
				.validate(
						{
							onfocusout : false,
							focusInvalid : false,
							focusCleanup : false,
							onkeyup : false,
							onclick : false,
							rules : {
								nombre : "required",
								mail : {
									required : true,
									email : true
								},
								jcaptchaText : "required"
							},
							messages : {
								nombre : '<fmt:message key="validation.required"><fmt:param value="${usuarioLabel}"/></fmt:message>',
								mail : {
									required : '<fmt:message key="validation.required"><fmt:param value="${mailLabel}"/></fmt:message>',
									email : '<fmt:message key="validation.email.valid"></fmt:message>',
								},
								jcaptchaText : '<fmt:message key="validation.required"><fmt:param value="${captchaLabel}"/></fmt:message>'
							},
						});

		$("#idCancelar").click(function(event) {
			event.preventDefault();
			$("#passwordRecoveryDialog").dialog("close");
			document.getElementById("passwordRecoveryForm").reset();
			//Regenero el captcha para que cuando vuelva a abrir no sea el mismo
			//$('#imgCaptcha').attr('src','${pageContext.request.contextPath}/jcaptcha');
		});

		$("#idAceptar")
				.click(
						function(event) {
							event.preventDefault();
							if ($('#passwordRecoveryForm').valid()) {
								$('#passwordRecoveryDialog').modal('loading');
								$("#idAceptar").attr('disabled', 'disabled');
								var formData = $('#passwordRecoveryForm').serializeArray();
								$.ajax({
									url : "${pageContext.request.contextPath}/ayuda/passwordRecovery",
									data : formData,
									type : 'POST',
									dataType : "json",
									complete : function(data) {
										// llamo a la funcion que parsea la response con los eventuales 
										// errores de validacion y los resultados para llenar la grilla
										parseResultsAddBeneficiario($.parseJSON(data.responseText));
									}
								});
							}
						});

		$("#idCerrar").click(function(event) {
			event.preventDefault();
			$("#passwordRecoveryDialog").dialog("close");
		});

		function parseResultsAddBeneficiario(searchResults) {
			if (searchResults.content.status == 'NOTOK') {
				var infoMessage = $("div#errors");
				//toma los errores y los ubica en sus divs
				//errores "generales"
				if (searchResults.generalErrors.length > 0) {
					$.each(searchResults.generalErrors, function(i, value) {
						infoMessage.append($("<p class='error'></p>").html( value ));
					});
				}
				if (!$.isEmptyObject(searchResults.validationResult)) {
					$.each(searchResults.validationResult,
							function(key, value) {
								infoMessage.append($("<p class='error'></p>").html( value ));
							});
				}
				$('#passwordRecoveryDialog').modal('loading');
				infoMessage.show();
				$("#idAceptar").removeAttr('disabled');

			} else {
				$("#mensajes").show();
				$("#contenido").hide();
			}
			$("#loaderPasswordRecovery").hide();
		}

	});
</script>

<div class="modal fade" id="passwordRecoveryDialog"  style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Recuperación de Password</h3>
	</div>
	<div class="modal-body">
	<form class="form-horizontal" action="${pageContext.request.contextPath}/j_spring_security_check"
		id="passwordRecoveryForm" method="post">
		<fieldset>
			<div class="form-group">
				<label for="idNombreUsuario" class="col-lg-4 control-label"><fmt:message key="label.user"/></label>
				<div class="col-lg-8">
					<input type="text" class="form-control" name="nombre"
					id="idNombreUsuario" />
				</div>
			</div>
			<div class="form-group">
				<label for="idMailUsuario" class="col-lg-4 control-label"><fmt:message key="label.mail"/></label>
				<div class="col-lg-8">
					<input type="text" class="form-control" name="mail"
					id="idMailUsuario" />
				</div>
			</div>
			<div class="form-group">
				<label for="jcaptchaText" class="col-lg-4 control-label"><fmt:message key="label.jcaptcha"/></label>
				<div class="col-lg-8">
					<input type="text" class="form-control" name="jcaptchaText"
					id="jcaptchaText" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-4 col-lg-8">
					<img id="imgCaptcha" src="${webContext}/jcaptcha"/>
					<span class="help-block"><a href="#" onclick="$('#imgCaptcha').attr('src','${pageContext.request.contextPath}/jcaptcha');$('#jcaptchaText').val('');">Cambiar captcha</a></span>
				</div>
			</div>
		</fieldset>
		</form>
	</div>
	<div class="modal-footer">
		<a id="idAceptar" href="#" class="btn btn-primary btn-success">Aceptar</a><a id="idCancelar" href="#" class="btn btn-danger" data-dismiss="modal">Cerrar</a>
	</div>
</div>
