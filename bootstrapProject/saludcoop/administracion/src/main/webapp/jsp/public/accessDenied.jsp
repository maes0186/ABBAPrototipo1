<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./../includes/header.jsp"%>
<%@include file="./../includes/menu.jsp"%>


	<div class="prefix_2 grid_8 suffix_2 alpha omega">
		<div class="genericContainer" style="margin-top:30px;">
			<h3>Aviso</h3>
			<div class="content">
				Acceso denegado.
				<form class="form">		
					<table id="buttonsBoxSearch">
						<tr>
							<td align="right" class="formActions">
								<span id="loader" style="display:none; vertical-align: middle;">
									<img src="${pageContext.request.contextPath}/resources/images/standard/loader.gif"/>
								</span>
								<button id="search">
									Aceptar
								</button>
								&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
<%@include file="./../includes/footer.jsp"%>
