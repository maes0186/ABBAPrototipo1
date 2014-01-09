<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<c:set var="webContext" value="${pageContext.request.contextPath}"  />
			<!--BODY-->
			</div>
		</div>
        <style type="text/css">

            div.blockOverlay {
                z-index: 9900 !important;
            }
            
        </style>
		<script>var urlAbsoluta = '${webContext}';</script>
		<script type="text/javascript" src="${webContext}/resources/js/utils.js?${cacheControl}"></script>
		<script type="text/javascript" src="${webContext}/resources/js/tooltip.js?${cacheControl}"></script>
		<script type="text/javascript" src="${webContext}/resources/js/bootstrap.min.js?${cacheControl}"></script>
		<script type="text/javascript" src="${webContext}/resources/js/twitter-bootstrap/bootstrap-datepicker.js?${cacheControl}"></script>
 		<script type="text/javascript" src="${webContext}/resources/js/twitter-bootstrap/bootstrap-modal.js?${cacheControl}"></script>
		<script type="text/javascript" src="${webContext}/resources/js/twitter-bootstrap/bootstrap-modalmanager.js?${cacheControl}"></script> 
		<script type="text/javascript" src="${webContext}/resources/js/collapsible.js?${cacheControl}"></script>
		<script type="text/javascript" src="${webContext}/resources/js/jquery.blockUI.js?${cacheControl}"></script>
		<script type="text/javascript" src="${webContext}/resources/js/alertUtils.js?${cacheControl}"></script>
		<script type="text/javascript" src="${webContext}/resources/js/tipoIdentificacion.js?${cacheControl}"></script>
		<script>
		$.fn.reset = function(){$(this).val('');};
		
    	$(document).ajaxStart(function(){
    	    $.blockUI({message: "Procesando...",  css: { 
                    border: 'none', 
                    padding: '15px', 
                    backgroundColor: '#111', 
                    opacity: .7, 
                    color: '#fff', 
                    "z-index": 10000
        		} 
    	    });
    	});
		$(document).ajaxStop(function(){$.unblockUI()});
		
			$.browser={ msie: ( navigator.appName == 'Microsoft Internet Explorer') ? true : false };
			$.fn.modal.defaults.spinner = $.fn.modalmanager.defaults.spinner = 
			    '<div class="loading-spinner" style="width: 200px; margin-left: -100px;">' +
			        '<div class="progress progress-striped active">' +
			            '<div class="progress-bar" style="width: 100%;"></div>' +
			        '</div>' +
			    '</div>';

		    $(document).ready(function() {

			  	$(".control-label-sm").each(function() {
				    $(this).css("padding-top", calcularPaddingTop($(this).height(), $(this).css("padding-top")));
			  	});
			  	
			  	// Selecciona el menú activo automáticamente
			  	$(".nav.bs-sidenav").find("li").click(function() {
			  	    
			  	  	var datos = new Array();
			  	    datos.push({name : "menuId", value : $(this).attr("id") });
			  	  	$.ajax({
			            type : 'POST',
			            async : false,
			            url : "${webContext}/main/setMenuId",
			            data : datos
			        });
			  	});

			  	$($(".nav.bs-sidenav").find("li")[0]).removeClass("active");
			  	$(".nav.bs-sidenav").find("li[id=${menuActivo}]").addClass("active");
			  	
			  	setDefaults();
			  	<%-- Se implementa la sgte validación debido a JIRA#225 --%>
			    $(this).keydown(function(e) {
			    	var element = e.target.nodeName.toLowerCase();
			    	var $target = $(event.target);
			    	if ((element == 'input' || element == 'textarea') && e.keyCode === 8) {
			    	    var readonly = $target.attr("readonly");
			    	    var disabled = $target.is(':disabled') ;
			    	    if((readonly != null && readonly != undefined?true:false) || (disabled != null && disabled?true:false)){
			    	        return false;
			    	    }
			    	}
		    	});
			    
            });

            function collapseContent(button) {

                if (!$(button).hasClass("collapsed")) {
                    $(".collapsible-header").each(function(i, e) {
                        if ($(e).is(":visible")) {
                            if (!$(e).hasClass("collapsed")) {
                                $(e).click();
                            }
                        }
                    });
                    $(button).attr("title", "<fmt:message key='label.desplegarTodos' />");
                } else {
                    $(".collapsible-header").each(function(i, e) {
                        if ($(e).is(":visible")) {
                            if ($(e).hasClass("collapsed")) {
                                $(e).click();
                            }
                        }
                    });
                    $(button).attr("title", "<fmt:message key='label.colapsarTodos' />");
                }
                $(button).toggleClass("collapsed");
            }
            function setDefaults(contextId) {
                //Asigno el valor default a todos los select que lo tengan programado
                if (contextId && typeof contextId == "string")
                    $("#" + contextId + " select[default-value]").each(function(i, e) {
                        var $select = $(e);
                        $select.val($select.attr("default-value"));
                    });
                else
                    $("select[default-value]").each(function(i, e) {
                        var $select = $(e);
                        $select.val($select.attr("default-value"));
                    });
            }
            alert = function(param) {
                console.log(param);
            };
            jQuery.fn.replaceAll = function(replaceMe, newGuy) {
                if (typeof replaceMe == "string") {
                    $(this).split(replaceMe).join(newGuy);
                }
            };
        </script>  
		    
		
	</body>
</html>