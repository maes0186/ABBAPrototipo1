<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

<fmt:message key="label.password" var="idPassword"/>
<fmt:message key="label.repeatedPassword" var="idRepeatedPassword"/>
<jsp:include page="../includes/header.jsp">
	<jsp:param name="includeMenu" value="false" />
</jsp:include>

<div class="col-lg-6 col-lg-offset-3">
	<div class="row well">
		<form class="form form-horizontal" id="updatePasswordForm" action="${pageContext.request.contextPath}/updatePassword" method="post">
			
			<fieldset>
				<blockquote class="col-lg-12"
					style="padding: 2px; font-size: 20px;">
					<strong>Cambiar Contraseña</strong>
				</blockquote>
				<div id="messages" class="col-lg-12"></div>
				<div class="form-group">
					<label for="password" class="control-label col-lg-3 col-lg-offset-2"> Nueva Contraseña </label>
					<div class="col-lg-3">
						<input type="password" class="form-control input-sm" id="password" name="password"/>
					</div>
				</div>
				<div class="form-group">
					<label for="repeatedPassword" class="control-label col-lg-3 col-lg-offset-2"> Repetir Contraseña </label>
					<div class="col-lg-3">
						<input type="password" class="form-control input-sm" id="repeatedPassword" name="repeatedPassword"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-2 col-lg-offset-4">
						<button type="button" class="btn btn-sm btn-warning btn-block" id="goBack">Volver</button>	
					</div>
					<div class="col-lg-2 col-lg-offset-1">
						<button id="accept" class="btn btn-sm btn-success btn-block">Aceptar</button>	
					</div>
				</div>
		</form>
	</div>
</div>

<script>
$(function() {
	$("[name=password]").focus();   

	$("#accept").click(
		function(event){
			event.preventDefault();
			if ($("#updatePasswordForm").valid())
				$("#updatePasswordForm").submit();
		}	
	);
	$("#goBack").click(
		function(event){
			event.preventDefault();
			location.href="${pageContext.request.contextPath}/main";
		}	
	);
	
	//Validator
    $('#updatePasswordForm').validate({
        onfocusout: false,
        focusInvalid: false,
        focusCleanup: false,
        onkeyup: false,
        onclick: false,
        rules: {
            password: {required:true,rangelength: [3, 20]},
            repeatedPassword: {required:true,rangelength: [3, 20]}
        },
        messages: {
            password:{	
            	required: '<fmt:message key="validation.required"><fmt:param value="${idPassword}"/></fmt:message>',
            	rangelength:'<fmt:message key="validation.restriction.betweenNandMChars"><fmt:param value="${idPassword}"/><fmt:param value="3"/><fmt:param value="20"/></fmt:message>'
            },
            repeatedPassword:{ 
            	required: '<fmt:message key="validation.required"><fmt:param value="${idRepeatedPassword}"/></fmt:message>',
            	rangelength:'<fmt:message key="validation.restriction.betweenNandMChars"><fmt:param value="${idRepeatedPassword}"/><fmt:param value="3"/><fmt:param value="20"/></fmt:message>'
            }
        
        },  
	    errorPlacement: function (error, element){
	    	appendErrorMessage($("#messages"), error.text());
	    }
    });
});
</script>
<jsp:include page="../includes/footer.jsp" />