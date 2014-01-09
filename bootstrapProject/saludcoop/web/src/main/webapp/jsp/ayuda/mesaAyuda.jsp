<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./../includes/header.jsp"%>
<%@include file="./../includes/menu.jsp"%>

<fmt:message key="label.EPS" var="eps"/>
<fmt:message key="label.IPS" var="ips"/>
<fmt:message key="label.prestador" var="prestador"/>
<fmt:message key="label.comentario" var="comentario"/>
<fmt:message key="validation.email.required" var="email"/>
<fmt:message key="validation.email.valid" var="emailValido"/>
 
<script>
$(function() {
	
	

	
	$('#mesaAyudaForm').validate({
		onfocusout: false,
		focusInvalid: false,
		focusCleanup: false,
		onkeyup: false,
		onclick: false,
		ignore: ".ignore", //se ignoran los campos que tengan esta clase
		rules: { //Reglas para cada campo
			eps: "required",
			ips: "required",
			prestador: "required",
			comentario: {requiredAndTextoGuia:true,minlength:10},
			email: {required: true, email: true}
		},
		messages: {//Mensajes de error formateados para cada uno de los campos
	   		eps: '<fmt:message key="validation.required"><fmt:param value="${eps}"/></fmt:message>',
	   		ips: '<fmt:message key="validation.required"><fmt:param value="${ips}"/></fmt:message>',
	   		prestador: '<fmt:message key="validation.required"><fmt:param value="${prestador}"/></fmt:message>',
	   		comentario:{ 
	   			requiredAndTextoGuia:'<fmt:message key="validation.required"><fmt:param value="${comentario}"/></fmt:message>',
	   			minlength:'<fmt:message key="validation.restriction.atLeastNumeric"><fmt:param value="${comentario}"/><fmt:param value="10"/></fmt:message>'},
	   		email: {required: '${email}',
	   				email: '${emailValido}'}
		},
		errorContainer: "#generalErrorsMesaAyudaValidation",
	   	errorLabelContainer: "#generalErrorsMesaAyudaValidation ul",
	   	wrapper: "li",
	   	invalidHandler: function(form, validator) {
	   		$('#content').animate({scrollTop: '0'}, 300);      
   	  	}
	});
	jQuery.validator.addMethod("requiredAndTextoGuia", function(value) {
		
		if(value == "" || value == '<fmt:message key="message.guia.comentarioMail"></fmt:message>') {
			return false;
		}
		return true;
	});
	
	$("#accept").click(
		function(event){

			if($('#mesaAyudaForm').valid()){
				$("#loader").show();
	            $("#accept").attr('disabled', 'disabled');
				$("#mesaAyudaForm").submit();
			}	
		});
	
	

	
	$("#cancel").click(
			function(event){
				event.preventDefault();
				window.location = "${webContext}/main"
			});
	
	
	//Agrego el mensaje guia.
	$('#comentario').attr('title','<fmt:message key="message.guia.comentarioMail"></fmt:message>');

	//controla el maxlength del textarea de observaciones.
	$('#comentario').maxlength({max: 500,overflowText:'{o} caracteres de mas ({m} m\u00E1ximo)',feedbackText:'{r} caracteres restantes ({m} m\u00E1ximo)',truncate:false});

	
	//Para poner el texto guia.
	$('.default').each(function(){
	    var defaultVal = $(this).attr('title');
	    $(this).focus(function(){
	      if ($(this).val() == defaultVal){
	        $(this).removeClass('active').val('');
	      }
	    })
	    .blur(function(){
	      if ($(this).val() == ''){
	        $(this).addClass('active').val(defaultVal);
	      }
	    })
	    .blur().addClass('active');
	  });
	
	});
</script>
<style media="screen" type="text/css">
    .default { width: 300px; }
    .active {
    		 font-style: italic; }
</style>
<div id="wrapper" class="container_12">
	
		<h2>Cont&aacute;ctenos</h2>
		<h3>Usted podr&aacute; enviarnos sus
sugerencias, solicitudes o inquietudes. Recuerde que contar&aacute; con
personal t&eacute;cnico capacitado que le dar&aacute; respuesta r&aacute;pidamente. Sus
observaciones son muy importantes para nosotros. 
</h3>
		<div id="generalErrorsMesaAyuda" class="errorLabel">
			<c:forEach items="${form_errors}" var="formErrorsEntry">
				<ul>${formErrorsEntry.value.errorMessage}</ul>
			</c:forEach>
		</div>
		<div id="formErrorsMesaAyuda" class="errorLabel">
			<c:forEach items="${general_errors}" var="formErrorsItem">
				<ul>${formErrorsItem}</ul>
			</c:forEach>
		</div>
			<div class="genericContainer" style="margin-top:30px;">
				<h3>Datos Personales</h3>
				<div style="margin-left:40px;" id="generalErrorsMesaAyudaValidation" class="errorLabel">  
                	<ul></ul>
            	</div>
				<div class="content" style="height: 550px; width: 250px;">
				<form id="mesaAyudaForm" style="width: 1000px" class="form" action="${webContext}/ayuda/mesaAyuda" method="post">
					<table class="resultTable" style="margin-left:12px;">
						<tbody>
							<tr>
								<td class="labelForm" width="100px;" align="right">Usuario:&nbsp;</td>
								<td>	
									<input class="dataLabel disabled" type="text" name="prestador" id="prestador" readonly="readonly" value="${usuario.usuario}(${usuario.nombre})" style="width: 880px;"/>
								</td>
							</tr>
							<tr>									
								<td class="labelForm" align="right">IPS:&nbsp;</td>
								<td>	
									<input class="dataLabel disabled" type="text" name="ips" id="ips" readonly="readonly" value="${usuario.ips}" style="width: 880px;"/>
								</td>
							</tr>

							<tr>
							<td class="labelForm" align="right">EPS:&nbsp;</td>
							<td><select name="eps" id="eps" style="width: 150px;">
									<option value="">Seleccione...</option>
									<c:forEach items="${listaEps}" var="eps">
										<option value="${eps.razonSocial}">${eps.razonSocial}</option>
									</c:forEach>
								</select>
							</td>
								</tr>
							<tr>
								<td class="labelForm" align="right">Email:&nbsp;</td>
								<td>
									<input class="dataLabel" type="text" name="email" id="email" style="width: 880px;"/>
								</td>
							</tr>
						</tbody>
					</table>
					<table class="resultTable" style="margin-left:50px; width: 950px;">
						<tr>	
							<td class="labelForm">Comentario:&nbsp;</td>
						</tr>
						<tr>
							<td>	
								<textarea class="default" name="comentario" id="comentario" title="" style="width: 950px; height: 300px;"></textarea>
							</td>
						</tr>
					</table>	
					<table id="buttonsBoxUser" >
						<tr>
							<td align="right" class="formActions">
								<span id="loader" style="display:none; vertical-align: middle;">
								<span>Aguarde un momento, su solicitud está siendo procesada</span><img src="${webContext}/resources/images/standard/loader.gif"/>
								</span>
								<button id="accept">
									Aceptar
								</button>
								&nbsp;
								<button id="cancel">
									Cancelar
								</button>
							</td>
						</tr>
					</table>
				</form>
				</div>
			</div>
	
</div>
<%@include file="./../includes/footer.jsp"%>