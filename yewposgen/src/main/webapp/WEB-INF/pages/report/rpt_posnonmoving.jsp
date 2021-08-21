<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- <div class="col-sm-3 col-sm-3"> -->
					<input type="hidden" id="compid" value="${sesloggedinUser.companyId}">
					<input type="hidden" id="storeid" value="${sesloggedinUser.storeId}">
					<input type="hidden" id="finyrid" value="${sesloggedinUser.finyrId}">
					<input type="hidden" id="noofexpmon" value="5">
					<table>
					  <tr align="center" style="font-weight: bold;">
			            <td><spring:message code="pos.jsp.date" text="Date" /></td>
						<td><spring:message code="report.msg.type" text="Report Type" /></td>
						<c:if test="${sesloggedinUser.isAdmin == 1}">
						<td><spring:message code="eport.msg.allstores" text="For All Stores" /></td>
						</c:if>
						<td></td>
					   </tr>
					   <tr>
					     <td style="padding: 0 1px;">
					     <input type="text" class="form-control" id="asondate" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" /> ">
					     </td>
					     <td style="padding: 0 1px;">
					     <select id="rptType" class="form-control">
		                           <option value="1">PDF</option>
		                           <option value="2">Excel</option>
		                 </select>
					     </td>
					     <c:if test="${sesloggedinUser.isAdmin == 1}">
					       <td style="padding: 0 34px;">
					      <input name="all_store"  id="Selected_all_store" type="checkbox" style="zoom: 2;">
					      </td>
					    </c:if>
					    <td>
					    	<button type="submit" class="btn btn-theme" onclick="getNonMovingItem()">Get Non Moving Item</button>
					
					    </td>
					   </tr>
					</table>
					
					
		            
					 
					
					<!-- </div> -->
					
					<!-- <div class="col-lg-4 col-md-4 col-sm-12">
						<button type="submit" class="btn btn-theme" onclick="getNonMovingItem()">Get Non Moving Item</button>
					</div> -->
				</div>
				<div id="rptnonmovitemdiv"></div>
			</div>
		</div>
	</div>
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

var pdf_url_stock_reg='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_REP_INV_NONMOVING_ITEM)%>';
	var currentDate = new Date();
	/* $('#date').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
	}); */
	function getNonMovingItem(){
		var compid=$("#compid").val();
		var storeid=$("#storeid").val();
		var finyrid=$("#finyrid").val();
		var asondate=$("#asondate").val();
		var noofexpmon=$("#noofexpmon").val();
		var rpttype = $("#rptType").val();
		if($("#Selected_all_store").prop("checked") == true){
			storeid=0;
		}
		
		var pdfline="<iframe src='"+pdf_url_stock_reg+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&asOnDate="+asondate+"&noOfMonth="+noofexpmon+"&rptType="+rpttype+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
		document.getElementById('rptnonmovitemdiv').innerHTML=pdfline;
	}

</script>