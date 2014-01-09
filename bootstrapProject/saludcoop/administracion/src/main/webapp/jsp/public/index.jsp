<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<jsp:include page="./../includes/header.jsp">
	<jsp:param name="includeMenu" value="false" />
</jsp:include>

<c:set var="webContext" value="${pageContext.request.contextPath}"  />

<fmt:message key="label.user" var="user" />
<fmt:message key="label.password" var="password" />
<script>
	$(document)
			.ready(
					function() {
						
						
						$.get("${webContext}/loginCheck", 
								{}, 
								function(data){
									if (data.content) 
										location.href="${webContext}/main";
									}
						);
						
						//$('#imgCaptcha').attr('src','${pageContext.request.contextPath}/jcaptcha');

						$("[name=j_username]").focus();

						$.urlParam = function(name) {
							var results = new RegExp('[\\?&]' + name
									+ '=([^&#]*)').exec(window.location.href);
							if (results != null) {
								return results[1] || 0;
							}
						}
						var error = $.urlParam("login_error");
						if (error == "t") {
							$("#errors")
									.append(
											'<p class="text-danger"><fmt:message key="message.login.noUser"/></p>');
						} else if (error == "d") {

							$("#errors")
									.append(
											'<p class="text-danger"><fmt:message key="message.login.disabled"/></p>');
						} else if (error == "l") {
							$("#errors")
									.append(
											'<p class="text-danger"><fmt:message key="message.login.noMoreAttemps"/></p>');
						} else if (error == "noToken") {
							$("#errors")
									.append(
											'<p class="text-danger"><fmt:message key="message.passwordRecovery.noToken"/></p>');
						} else if (error == "tokenExpired") {
							$("#errors")
									.append(
											'<p class="text-danger"><fmt:message key="message.passwordRecovery.tokenExpired"/></p>');
						} else if (error == "u") {
							$("#errors")
									.append(
											'<p class="text-danger"><fmt:message key="message.login.passwordUpdated"/></p>');
						}

						//Validator
						$('#loginForm')
								.validate(
										{
											onfocusout : false,
											focusInvalid : false,
											focusCleanup : false,
											onkeyup : false,
											onclick : false,
											rules : {
												j_username : "required",
												j_password : {
													required : true,
													minlength : 6
												}
											},
											messages : {
												j_username : '<fmt:message key="validation.required"><fmt:param value="${user}"/></fmt:message>',
												j_password : {
													required : '<fmt:message key="validation.required"><fmt:param value="${password}"/></fmt:message>',
													minlength : '<fmt:message key="validation.restriction.atLeastNChars.NSBzero"><fmt:param value="${idPassword}"/><fmt:param value="6"/></fmt:message>'
												}

											}
										});

						$("#passwordRecovery").click(function(event) {
							event.preventDefault();
							openPasswordRecoveryDialog();
						});

						//Metodo para abrir el dialog de beneficiario
						function openPasswordRecoveryDialog() {
							$("#contenido").show();
							$("#idNombreUsuario").val("");
							$("#mensajes").hide();
							$("#generalErrorsPasswordRecovery li").remove();
							$("#idNombreUsuario").attr('class', '');
							$("#generalMessagePasswordRecovery").hide();
							//Abre el dialogo
							$("#passwordRecoveryDialog").dialog({
								title : "Recuperar contraseña",
								width : 370,
								height : 'auto',
								resizable : false,
								modal : true,
								close : function(event, ui) {
									//entonces, en el close, remuevo el <div> del document
									$("#loaderPasswordRecovery").hide();
									$("#idAceptar").removeAttr('disabled');
								}
							});
						}

						$("#accept").click(function(event) {
							$('#userLoginMessage').remove();
							event.preventDefault();
							$("#loginForm").submit();
						});


					});
</script>

<div class="row">
	<div class="col-lg-offset-3 col-lg-6 col-sm-offset-3 col-sm-6 col-md-offset-3 col-md-6 col-xs-12">
		<div class="well">
			<form class="form-horizontal"
				action="${pageContext.request.contextPath}/j_spring_security_check"
				id="loginForm" method="post">
				<fieldset>
					<legend>Login</legend>
					<div class="col-lg-offset-2 col-lg-10" id="errors"></div>
					<div class="form-group">
						<label for="username" class="col-lg-2 control-label">${user}</label>
						<div class="col-lg-8 input-group">
							<span class="input-group-addon" style="background:white"><i class="icon-user"></i></span>
							<input type="text" class="form-control" id="username"
								name="j_username" placeholder="Usuario">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-lg-2 control-label">${password}</label>
						<div class="col-lg-8" input-group">
							<div class="input-group">
								<span class="input-group-addon" style="background:white"><i class="icon-lock"></i></span>
								<input type="password" class="form-control" id="password"
									name="j_password" placeholder="Password">
							</div>
						</div>
					</div>
					<div class="form-group text-center">
						<div class="col-lg-8 col-lg-offset-2">
							<button type="submit" id="accept" class="btn btn-success">Ingresar</button>
							<a data-toggle="modal" href="#passwordRecoveryDialog">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Recuperar contraseña</a></span>
						</div>
					</div>
					<div class="form-group text-center">
						<img src="http://www.conexia.com/img/conexia.png"/>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>

<%@include file="./../ayuda/passwordRecoveryDialog.jsp"%>
<jsp:include page="./../includes/footer.jsp"/>