<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="./../includes/header.jsp">
	<jsp:param name="includeMenu" value="true" />
</jsp:include>

	<div class="row">
		<div class="col-lg-offset-3 col-lg-6">
			<div class="well">
				<h3>Ticket</h3>
				<div class="content">
					${passwordMessage}
				</div>
			</div>
		</div>
	</div>
<%@include file="./../includes/footer.jsp"%>
