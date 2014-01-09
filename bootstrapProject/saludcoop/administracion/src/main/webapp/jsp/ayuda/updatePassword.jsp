<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./../includes/header.jsp" %>
<fmt:message key="label.password" var="idPassword"/>
<fmt:message key="label.repeatedPassword" var="idRepeatedPassword"/>
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
        errorContainer: "#generalErrorsUpdatePassword",
        errorLabelContainer: "#generalErrorsUpdatePassword ul",
        wrapper: "li"
    });
});
</script>
<div id="wrapper" class="container_12" style="display:none; margin-top: 100px;">
	<div class="prefix_4 grid_4 suffix_4 alpha omega">
		<div class="genericContainer" style="margin-top:30px;">
			<h3>Cambiar contraseña</h3>
			<div class="content">
				<div id="generalErrorsUpdatePassword" style="color:red">
					<ul></ul>
					${errors}
               	</div>
				<form class="form" id="updatePasswordForm" action="${pageContext.request.contextPath}/updatePassword" method="post">		
					<table class="innerTable">
                        <tr>
                            <td class="formLabel">Nueva contrase&ntilde;a</td>
                            <td><input type="password" name="password" maxlength="20"/></td>
                        </tr>
                        <tr>
                            <td class="formLabel">Repetir Contrase&ntilde;a</td>
                            <td><input type="password" name="repeatedPassword" maxlength="20"/></td>
                        </tr>
                    </table>
                    <table id="buttonsBox">
                        <tr>
                            <td align="right" class="formActions">
                                <span id="loader" style="display:none; vertical-align: middle;">
                                    <img src="${pageContext.request.contextPath}/resources/images/standard/loader.gif"/>
                                </span>
                                 <button id="goBack">
                                    Volver
                                </button>
                                <button id="accept">
                                    Cambiar contraseña
                                </button>
                            </td>
                        </tr>
                    </table>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
