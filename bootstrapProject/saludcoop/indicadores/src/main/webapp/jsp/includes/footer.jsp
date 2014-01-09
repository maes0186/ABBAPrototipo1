<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<c:set var="webContext" value="${pageContext.request.contextPath}"  />
			<!--BODY-->
			</div>
		</div>
		
		<script type="text/javascript" src="${webContext}/resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="http://jschr.github.io/bootstrap-modal/js/bootstrap-modal.js"></script>
		<script type="text/javascript" src="http://jschr.github.io/bootstrap-modal/js/bootstrap-modalmanager.js"></script> 
		<script type="text/javascript" src="${webContext}/resources/js/utils.js"></script>
		<script type="text/javascript" src="${webContext}/resources/js/alertUtils.js"></script>
		<script type="text/javascript" src="${webContext}/resources/js/jquery/jquery.i18n.properties-min-1.0.9.js"></script>
		
		
		<script>

			//Le tuve que agregar el codigo del menu a mano, debería funcionar solo. Y a la vez, este código es sólo si se quiere un menu collapsible
			$(".nav.bs-sidenav > li").click(function(){$.each($(this).parent().find("li"),function(i,e){$(e).removeClass("active");});$(this).addClass("active")});
			$.browser={ msie: ( navigator.appName == 'Microsoft Internet Explorer') ? true : false };
			$.fn.modal.defaults.spinner = $.fn.modalmanager.defaults.spinner = 
			    '<div class="loading-spinner" style="width: 200px; margin-left: -100px;">' +
			        '<div class="progress progress-striped active">' +
			            '<div class="progress-bar" style="width: 100%;"></div>' +
			        '</div>' +
			    '</div>';
			    
		  

		</script>
	</body>
</html>