<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<c:set var="webContext" value="${pageContext.request.contextPath}"  />
				<c:if test="${menu == null}">
                    <script>
	                    $(document).ready(function() {
	                        $.ajax({
	                            type : 'GET',
	                            async : false,
	                            url : "${webContext}/main"
	                        });
	                        location.reload();
	                    });
                    </script>
                </c:if>
				<div class="col-lg-2 col-md-2 col-sm-2 menu-collapsible" >
					<div class="bs-sidebar hidden-print affix" style="margin-top:50px">
						<ul class="nav bs-sidenav">
							<div style="color:#2957A3;font-weight:bold;border-bottom:1px solid #DDD;padding:5px;padding-bottom:10px" class="text-center">${userName}</div>
							<c:forEach items="${menu.rootNodes}" var="parentMenu">
								<li id = "menu_${parentMenu.idMenu}"><a href="${webContext}/${parentMenu.link}"><c:if test="${parentMenu.icon != null}"><i class="${parentMenu.icon}"></i></c:if> <span> ${parentMenu.titulo}</span></a>
									<c:if test="${not empty parentMenu.children}">
										<ul>
											<c:forEach items="${parentMenu.children}" var="child">
												<li id = "menu_${child.idMenu}">
												<a href="${webContext}/${child.link}"><c:if test="${child.icon != null}"><i class="${child.icon}"></i></c:if> <span> ${child.titulo}</span></a>
												</li>
											</c:forEach>
										</ul>
									</c:if></li>
							</c:forEach>
							<li><a href="${webContext}/logout"> <i class="icon-off"></i> Salir </a></li>
							<div class="text-center">
								<img src="${webContext}/resources/images/conexia_logo.png"/>
							</div>
						</ul>
						
					</div>
				</div>