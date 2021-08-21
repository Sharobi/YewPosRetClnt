
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--sidebar start-->
<aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">

			<li class="mt"><c:if test="${home=='Y'}">
					<a class="active" href="${pageContext.request.contextPath }/home/welcome.htm"> <i class="custom-home"></i> <span>HOME</span>
					</a>
				</c:if> <c:if test="${home!='Y'}">
					<a href="${pageContext.request.contextPath }/home/welcome.htm"> <i class="custom-home"></i> <span>HOME</span>
					</a>
				</c:if></li>

			<c:if test="${! empty sesappMenuList}">
				<c:forEach items="${sesappMenuList}" var="appList">
					<li class="sub-menu">
					  <c:choose>
							<c:when test="${menuselect.menu==appList.menuCode}">
							<c:if test="${appList.isNone==0 }">
								<a class="active" href="${appList.menuUrl}">
								   <c:if test="${appList.menuCode=='100'}">
										<i class="custom-pos"></i>
									</c:if> 
									<c:if test="${appList.menuCode=='200'}">
										<i class="custom-inv"></i>
									</c:if> 
									<c:if test="${appList.menuCode=='300'}">
										<i class="custom-pro"></i>
									</c:if>
									<c:if test="${appList.menuCode=='400'}">
										<i class="fa fa-suitcase" ></i>
									</c:if> 
									<c:if test="${appList.menuCode=='500'}">
										<i class="fa fa-wrench"></i>
									</c:if>
									<c:if test="${appList.menuCode=='600'}">
									 <i class="fa fa-users"></i> 
									<!--  <i class="fa fa-address-book"></i>  -->
									</c:if> 
									<c:if test="${appList.menuCode=='700'}">
										<i class="fa fa-cogs"></i>
									</c:if> 
									<c:if test="${appList.menuCode=='800'}">
										<i class="custom-rep"></i>
									</c:if>
									 <c:if test="${appList.menuCode=='900'}">
										<i class="fa fa-user"></i>
									</c:if> 
									<span>${appList.menuName}</span>
								</a>
								</c:if>
							</c:when>
							<c:otherwise>
							<c:if test="${appList.isNone==0 }">
								<a href="${appList.menuUrl}"> 
								   <c:if test="${appList.menuCode=='100'}">
										<i class="custom-pos"></i>
									</c:if> 
									<c:if test="${appList.menuCode=='200'}">
										<i class="custom-inv"></i>
									</c:if> 
									<c:if test="${appList.menuCode=='300'}">
										<i class="custom-pro"></i>
									</c:if> 
									<c:if test="${appList.menuCode=='400'}">
										<i class="fa fa-suitcase"></i>
									</c:if>
									<c:if test="${appList.menuCode=='500'}">
										<i class="fa fa-wrench"></i>
									</c:if> 
									<c:if test="${appList.menuCode=='600'}">
									 <i class="fa fa-users"></i> 
									<!--  <i class="fa fa-address-book"></i>  -->
									</c:if> 
									<c:if test="${appList.menuCode=='700'}">
										<i class="fa fa-cogs"></i>
									</c:if> 
									<c:if test="${appList.menuCode=='800'}">
										<i class="custom-rep"></i>
									</c:if> 
									<c:if test="${appList.menuCode=='900'}">
										<i class="fa fa-user"></i>
									</c:if> 
									
									<span>${appList.menuName}</span>
								</a>
								</c:if>
							</c:otherwise>
						</c:choose>
						<ul class="sub">
							<c:forEach items="${appList.subMenuList}" var="subMenu">
								<c:set var="submenuUrl" value="#"></c:set>
								<c:if test="${subMenu.menuUrl!='#'}">
									<c:set var="submenuUrl" value="${pageContext.request.contextPath}/${subMenu.menuUrl}.htm"></c:set>
								</c:if>
								<c:choose>
									<c:when test="${! empty subMenu.subMenuList}">
										<c:choose>
											<c:when test="${menuselect.subMenu==subMenu.menuCode}">
												<c:if test="${subMenu.isNone==0 }">
													<li><a class="active" href="${submenuUrl}">${subMenu.menuName}</a>
														<ul class="sub">
															<c:forEach items="${subMenu.subMenuList}" var="childsubMenu">
																<c:set var="childsubmenuUrl" value="#"></c:set>
																<c:if test="${childsubMenu.menuUrl!='#'}">
																	<c:set var="childsubmenuUrl" value="${pageContext.request.contextPath}/${childsubMenu.menuUrl}.htm"></c:set>
																</c:if>
																<c:choose>
																	<c:when test="${menuselect.childsubMenu==childsubMenu.menuCode}">
																	<c:if test="${childsubMenu.isNone==0 }">
																		<li class="active"><a href="${childsubmenuUrl}">${childsubMenu.menuName}</a></li>
																	</c:if>
																	</c:when>
																	<c:otherwise>
																	<c:if test="${childsubMenu.isNone==0 }">
																		<li ><a  href="${childsubmenuUrl}">${childsubMenu.menuName}</a></li>
																	</c:if>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</ul>
													</li>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${subMenu.isNone==0 }">
													<li><a  href="${submenuUrl}">${subMenu.menuName}</a>
														<ul class="sub">
															<c:forEach items="${subMenu.subMenuList}" var="childsubMenu">
																<c:set var="childsubmenuUrl" value="#"></c:set>
																<c:if test="${childsubMenu.menuUrl!='#'}">
																	<c:set var="childsubmenuUrl" value="${pageContext.request.contextPath}/${childsubMenu.menuUrl}.htm"></c:set>
																</c:if>
																<c:if test="${childsubMenu.isNone==0 }">
																<li><a href="${childsubmenuUrl}">${childsubMenu.menuName}</a></li>
																</c:if>
															</c:forEach>
														</ul></li>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${menuselect.subMenu==subMenu.menuCode}">
												<c:if test="${subMenu.isNone==0 }">
													<li class="active"><a href="${submenuUrl}">${subMenu.menuName}</a></li>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${subMenu.isNone==0 }">
													<li><a href="${submenuUrl}">${subMenu.menuName}</a></li>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul></li>
				</c:forEach>
			</c:if>
		</ul>
		<!-- sidebar menu end-->
	</div>
</aside>
<!--sidebar end-->