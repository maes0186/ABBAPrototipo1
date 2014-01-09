<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./../includes/header.jsp"%>
	<div class="row">
	<div class="col-lg-offset-3 col-lg-6 col-sm-offset-3 col-sm-6 col-md-offset-3 col-md-6 col-xs-12">
		<div class="well">
			<form class="form-horizontal"
				action="${pageContext.request.contextPath}/logout"
				id="loginForm" method="post">
				<fieldset>
					<legend>Acceso denegado.</legend>
					<div class="form-group text-center">
						<div class="col-lg-8 col-lg-offset-2">
							<button type="submit" id="accept" class="btn btn-success">Aceptar</button>
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
<%@include file="./../includes/footer.jsp"%>
