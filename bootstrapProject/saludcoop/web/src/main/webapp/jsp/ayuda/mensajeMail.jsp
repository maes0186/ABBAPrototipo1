<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./../includes/header.jsp"%>
<%@include file="./../includes/menu.jsp"%>

<script>
$(function() {	

	$("#aceptarBtn").click(
		function(event){
			event.preventDefault();
			window.location = "${pageContext.request.contextPath}/main";
		}		
	);
});
</script>
	<div class="prefix_2 grid_8 suffix_2 alpha omega">
		<div class="genericContainer" style="margin-top:30px;">
			<h3>Aviso</h3>
			<div class="content">
				
				<form class="form">		
					<table id="buttonsBoxSearch">
					<tr style="margin-left: 30px;">
						<td align="left">
						Su mensaje fue enviado correctamente.
						</td>
					</tr>
					</table>
				</form>
			</div>
		</div>
		<br>
		<table id="buttonsBoxSearch" style="width: 727px;">
			<tr>
				<td align="right" class="formActions">
					<button id="aceptarBtn">
						Aceptar
					</button>
					&nbsp;
				</td>
			</tr>
		</table>
	</div>
<%@include file="./../includes/footer.jsp"%>