<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<form:form modelAttribute="user" class="form-login" action="${pageContext.request.contextPath }/authentication/dologin.htm" method="post">
		        <%-- <h2 class="form-login-heading"><spring:message code="login.jsp.please.signin" text="PLEASE SIGN IN" /></h2> --%>
		        <div class="login-header-img"></div>
		        <div class="login-wrap">
		        	<form:errors path="*"  cssStyle="color:#a94442;"></form:errors>
		        	<div class="input-group">
							<span class="input-group-addon" style="background-color: #86b855;"> <i class="fa fa-user" style="color:white;"></i>
							</span>
							<input type="text" name="userName" class="form-control"  placeholder="<spring:message code="login.jsp.email" text="EMAIL-ID" />" autofocus/>
					</div>
		            <br>
		            <div class="input-group">
							<span class="input-group-addon" style="background-color: #86b855;"> <i class="fa fa-lock" style="color:white;"></i>
							</span>
							<input type="password" name="password" class="form-control" placeholder="<spring:message code="login.jsp.password" text="PASSWORD" />"/>
					</div>
					<br>
					<input type="text" name="loginDate" class="form-control hide" readonly="readonly" placeholder="Login Date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"/>
		            
		           <%--  <input type="submit" value="<spring:message code="login.jsp.signin" text="SIGN IN" />" class="btn btn-theme btn-block"/> --%>
		            <button class="btn btn-primary btn-block" type="submit"> <spring:message code="login.jsp.signin" text="SIGN IN" /></button>
		            
		        </div>
</form:form>	  	
