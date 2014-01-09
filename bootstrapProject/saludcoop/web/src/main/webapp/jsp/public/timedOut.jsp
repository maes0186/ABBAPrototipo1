<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-offset-3 col-lg-6 col-sm-offset-3 col-sm-6 col-xs-12">
		<div class="well">
			<h3>Salida del Sistema</h3>
			<div class="row">
				<div class="col-lg-12">
					<strong>Debido a un período de inactividad, su sesión ha expirado.</strong><br /> 
					Por favor, reingrese al sistema para seguir	operando.
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<button id="aceptarBtn" class="btn btn-default">Aceptar</button>
				</div>
				<div class="row"><br/></div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function() {

		$("#aceptarBtn").click(function(event) {
			event.preventDefault();
			window.location = "${pageContext.request.contextPath}";
		});
	});
	$("#aceptarBtn").focus();
</script>
	<%@include file="./../includes/footer.jsp"%>