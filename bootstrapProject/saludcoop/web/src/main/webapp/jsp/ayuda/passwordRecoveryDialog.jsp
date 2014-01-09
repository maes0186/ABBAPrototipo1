<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib tagdir='/WEB-INF/tags' prefix='sc'%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />


<fmt:message key="label.user" var="usuarioLabel" />
<fmt:message key="label.mail" var="mailLabel" />
<fmt:message key="label.jcaptcha" var="captchaLabel" />

<div class="modal fade" id="passwordRecoveryDialog"  style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Recuperación de Password</h3>
	</div>
	<div class="modal-body">
		<div id="contenido">
			<form class="form-horizontal" action="${pageContext.request.contextPath}/j_spring_security_check" id="passwordRecoveryForm" method="post">
				<fieldset>
					<div id="password-recovery-messages" class="col-lg-12"></div>
					<div class="form-group">
						<label for="idNombreUsuario" class="col-lg-4 control-label"><fmt:message key="label.user"/></label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="nombre" id="idNombreUsuario" />
						</div>
					</div>
					<div class="form-group">
						<label for="idMailUsuario" class="col-lg-4 control-label"><fmt:message key="label.mail"/></label>
						<div class="col-lg-8">
							<input type="text" class="form-control" name="mail"	id="idMailUsuario" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-12">
							<sc:captcha/>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div id="mensajes" class=" template-hidden" >
			<div class="col-lg-12">
				<p>Se le ha enviado un correo electrónico con instrucciones para recuperar su contraseña.<br/> Muchas gracias</p>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<a id="idAceptar" href="#" class="btn btn-primary btn-success">Aceptar</a><a id="idCancelar" href="#" class="btn btn-danger" data-dismiss="modal">Cerrar</a>
	</div>
</div>
<script type="text/javascript">
	$(function() {

		$("#recaptcha_response_field").attr("style","");
		$("#recaptcha_response_field").css("border","1px solid #dce4ec");
		$("#recaptcha_response_field").css("padding","10px 15px");
		$("#recaptcha_response_field").addClass("form-control");
		
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
								recaptcha_response_field : "required"
							},
							messages : {
								nombre : '<fmt:message key="validation.required"><fmt:param value="${usuarioLabel}"/></fmt:message>',
								mail : {
									required : '<fmt:message key="validation.required"><fmt:param value="${mailLabel}"/></fmt:message>',
									email : '<fmt:message key="validation.email.valid"></fmt:message>',
								},
								recaptcha_response_field : '<fmt:message key="validation.required"><fmt:param value="${captchaLabel}"/></fmt:message>'
							},  
						    errorPlacement: function (error, element){
						    	appendErrorMessage($("#password-recovery-messages"), error.text());
						    }
						});

		$("#idCancelar").click(function(event) {
			event.preventDefault();
			$("#passwordRecoveryDialog").dialog("close");
			document.getElementById("passwordRecoveryForm").reset();
		});

		$("#idAceptar")
				.click(
						function(event) {
							
							if ($("#contenido").is(":visible")){
								$("#password-recovery-messages").empty();
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
											parseResults($.parseJSON(data.responseText));
										}
									});
								}
							}else{
								event.preventDefault();
								$("#passwordRecoveryDialog").modal('hide');
								$("#mensajes").hide();
								$("#contenido").show();
							}
							
							
						});

		$("#idCerrar").click(function(event) {
			event.preventDefault();
			$("#passwordRecoveryDialog").dialog("close");
		});

		function parseResults(searchResults) {
			if (searchResults.content.status == 'NOTOK') {
				//toma los errores y los ubica en sus divs
				//errores "generales"
				if (searchResults.generalErrors.length > 0) {
					createErrorMessages($("div#password-recovery-messages"), searchResults.generalErrors);
				}
				if (!$.isEmptyObject(searchResults.validationResult)) {
					$.each(searchResults.validationResult, function(i,error){appendErrorMessage($("div#password-recovery-messages"), error);});
				}
				$('#passwordRecoveryDialog').modal('loading');
				$("#idAceptar").removeAttr('disabled');
				$("#passwordRecoveryForm .form-control").val("");
				$("#recaptcha_reload").click();

			} else {
				$('#passwordRecoveryDialog').modal('loading');
				$("#idAceptar").removeAttr('disabled');
				$("#mensajes").show();
				$("#contenido").hide();
			}
		}

	});
</script>
