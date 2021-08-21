<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><spring:message code="ccgroup.jsp.dailycollection" text="Daily Collection ..." /></p>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-sm-12 col-sm-12">
					<input type="hidden" id="compid" value="${sesloggedinUser.companyId}">
					<input type="hidden" id="storeid" value="${sesloggedinUser.storeId}">
					<input type="hidden" id="finyrid" value="${sesloggedinUser.finyrId}">
					<!-- <input type="hidden" id="manufid" value="0">
					<input type="hidden" id="contentid" value="0"> -->
					<center>
					<span id="error_rpt" style="color:red;"></span>
					</center>
					<table >
						<tr align="center" style="font-weight: bold;">
					<%-- 	  <td width="10%" ><spring:message code="accgroup.jsp.report_type" text="Report Type" /></td> --%>
								<td id="col_from_date_head"><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></td>
								<td align="center">
								<span id="todata_heading" align="center"><spring:message code="purinvreg.jsp.todt" text="To Date" /></span>
								<span id="ason_heading" align="center"><spring:message code="accgroup.jsp.asondate" text="As on Date" /></span>
							 
								</td>
								
								<%-- <td width="25%" id="ledger_group"><spring:message code="accsetup.jsp.accountgroupname" text="Account Group Name" /></td>
								<td width="23%"><spring:message code="accsetup.jsp.partyaccountname" text="Account Name " /></td> --%>
								<td><spring:message code="accgroup.jsp.reportformat" text="Format" /> </td>
							</tr>
						<tr>
						<fmt:parseDate value="${sessionScope.sesloggedinUser.startDate}" var="parsedStrtDate" pattern="yyyy-MM-dd" />
						<fmt:parseDate value="${sessionScope.sesloggedinUser.endDate}" var="parsedEndDate" pattern="yyyy-MM-dd" />
						
						 	<!-- report  type -->
							<!-- 	<td style="padding: 0 1px;">
									<select class="form-control-trx" name="reprot_type" id="report_type"  onchange="arrange_menu();">
									 
										<option value="0">Select</option>
										<option value="1">Ledger</option>
										<option value="2">Trial Balance</option>
										<option value="3">Balance Sheet</option>
									</select>
								</td>  --> 
								<td style="padding: 0 1px;" id="col_from_date">
									<div class="input-group">
										<input type="hidden" id="sessionstrtdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedStrtDate}" />">
										<input type="text" readonly="readonly" class="form-control-trx" id="strtdate" name="startDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								
								
								<td align="center" style="padding: 0 1px;">
									<!-- <div class="input-group date" data-provide="datepicker"> -->
									<div class="input-group">
										<input type="hidden" id="sessionenddate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedEndDate}" />">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								
							<%-- 
								
								<td style="padding: 0 1px;">
									<select class="form-control-trx" name="accountgroup_id" id="accountgroup_id">
										<option value="0">All</option>
										<c:if test="${!empty accountgrouplist}">
											<c:forEach items="${accountgrouplist}" var="ag">
												<option value="${ag.id}">${ag.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
								<td>
									<input type="hidden" id="accountid" value="0" />
									<input class="form-control-trx" type="text" id="account_name"  onkeyup="auto_com();" placeholder="Account Name(Please type atleast 2 characters)">
									
								</td> --%>
								
								<td>
									<select class="form-control-trx" name="report_format" id="report_format">
										<option value="1">Pdf</option>
									 	<option value="2">XLSX</option>
									</select>
									
								</td>
								
								<td>
									<div class="col-lg-4 col-md-4 col-sm-12">
										<button type="button" class="btn btn-theme" onclick="getCurrPurchaseItems()"><spring:message code="cmn.jsp.btn.submit" text="Submit" /></button>
									</div>
								</td>
								<td><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
					</table>
					</div>
					
				</div>
				<div id="rptpossaleregdiv" style="height: 450px;width: 100%;"></div>
			</div>
		</div>
	</div>
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
$(document).ready(function() {
	$("#ason_heading").addClass('hide');
	
	
});

 

/*
 * for  report type on change function 
 */

 
 
// $(document).ready(function() {
var compid=$("#compid").val();
var storeid=$("#storeid").val();
var finyrid=$("#finyrid").val();

// });
var pdf_url_purchase_itemwise='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ACCOUNT_DAILY_COLLETION)%>';


	

	var currentDate = new Date();
	var sessionstrtdate = $("#sessionstrtdate").val();
	var sessionenddate = $("#sessionenddate").val();
	var endDt = new Date();		
	if( (currentDate.getTime() > new Date(sessionenddate).getTime()))
	{
		endDt = sessionenddate;
		$('#enddate').val(sessionenddate);
	}
	else
	{
		endDt = currentDate;
	}
	
	var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
	$('#strtdate').datepicker({
		format : 'yyyy-mm-dd',
		startDate : sessionstrtdate,
		autoclose: true,
	});
	$('#enddate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : endDt,
		autoclose: true,
	});
	
	function getCurrPurchaseItems(){
		
	
				var strtdate=$("#strtdate").val();
				var enddate=$("#enddate").val();
			 
				var report_format= $('#report_format').val();
 
					 
			  
					var  final_url=pdf_url_purchase_itemwise+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&startDate="+strtdate+"&endDate="+enddate+"&report_format="+report_format+"";
					 
					 
					var pdfline="<iframe src='"+final_url+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
				
					document.getElementById('rptpossaleregdiv').innerHTML=pdfline;
		 
		
		
	}
	
</script>