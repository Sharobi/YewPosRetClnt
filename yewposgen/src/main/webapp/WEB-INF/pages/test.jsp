<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keyword" content="">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>.: POS :: <%-- <tiles:getAsString name="title" />  --%>:.</title>
	<link rel="icon" type="image/png" id="myfavicon" href="${pageContext.request.contextPath}/assets/images/favicon/favicon-16x16.png">
	 <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/assets/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/assets/css/bootstrap/datepicker.css" rel="stylesheet">
    <!--external css-->
    <link href="${pageContext.request.contextPath }/assets/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/assets/css/style-responsive.css" rel="stylesheet">
   <link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
        <script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.12.3.js"></script>
       <script src="${pageContext.request.contextPath }/assets/js/common/angular_1_4_8_min.js"></script>
    
</head>
<body ng-app="">
<section id="container" >
<!--  for common modal  -->
    <jsp:include page="common/header.jsp" />  
      <!-- for common menu   --> 
         <jsp:include page="common/leftmenu.jsp" />   
               <!--main content start-->
      		<section id="main-content">
      			<section class="wrapper">
				<div class="row">
          			<div class="col-lg-12">
   					<p><%-- <spring:message code="subcat.jsp.title" text="SubCategory..." /> --%></p>
   					<div class="panel panel-default">
						<div class="panel-body">
					{{23423}}
						<div class="col-lg-8 col-md-8 col-sm-12">
							<form modelAttribute="commonResultSetMapper" class="form-inline" role="form" action="${pageContext.request.contextPath }/invsetup/searcharea.htm" method="post">
								<div class="form-group">
	
									<input type="text" class="form-control" placeholder="Area Name" name="Area Name" value="${commonResultSetMapper.groupName}">
								</div>
								<div class="form-group">
									<select class="form-control" name="qryCondition" >
										<option value="like">LIKE</option>
										<option value="equals">=</option>
									</select>
								</div>
								<button type="submit" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button>
							</form>
						</div>
					
						<div class="pull-right">
							<c:if test="${menuByUserDTO.isAll==1}">
								<button class="btn btn-primary" type="button" onclick="javascript:openAddEditModal(0,0,0,0,0,'')"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
							</c:if>
						</div>
					</div>
				</div>
							
				<!-- Add/Edit areaAddEditModal Starts -->
				
					<div class="modal fade" id="areaAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						        <h4 class="modal-title" id="myModalLabel">
						        	<span id="headertext"></span>
						        </h4>
						      </div>
						      <div class="modal-body">
						        <div class="form-horizontal">
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="country_label"><spring:message code="city.jsp.countryname" text="Country" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="countryList" class="form-control" onchange="getStateByCountry()">
                              					<option value="0">Select</option>
	                              				<c:if test="${!empty countryMasters}">
													<c:forEach items="${countryMasters}" var="countryMaster">
														<option value="${countryMaster.id}">${countryMaster.name}</option>
													</c:forEach>
												</c:if>
                              				</select>
                              			</div>
                          			</div>	
                          			<div class="form-group">
										<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="city.jsp.statename" text="State" />
											<span class="required_star">*</span>
										</label>
										<div class="col-sm-8">
											<select class="form-control-trx" name="statelist" id="stateList" onchange="getCityByState()">
												
											</select>
										</div>
									</div>				        
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="city.jsp.cityname" text="City" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="cityList" name="cityList" class="form-control" onchange="getZoneByCity()">
                              				</select>
                              			</div>
                          			</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="zone_label"><spring:message code="city.jsp.zonename" text="Zone" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="zoneList" name="zoneList" class="form-control">
                              				</select>
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="city.jsp.areaname" text="Area Name" /></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="areaName" class="form-control">
                              			</div>
                          			</div>
						        </div>
						        <input type="hidden" id="areaId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditArea()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div> 
				
				<!-- Add/Edit accGroupAddEditModal Modal Ends -->
				
			
				
				<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="city.jsp.areaname" text="Area" /></th>
						<th><spring:message code="city.jsp.zonename" text="Zone" /></th>
						<th><spring:message code="city.jsp.cityname" text="City" /></th>
						<th><spring:message code="city.jsp.statename" text="State" /></th>
						<th><spring:message code="city.jsp.countryname" text="Country" /></th>
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				
				<tbody>
				<c:if test="${!empty allAreas }">
					<c:forEach items="${allAreas}" var="allArea" varStatus="index">
						<tr>
						<td>${allArea.name}</td>
						<td>${allArea.zoneName}</td>
						<td>${allArea.cityName}</td>
						<td>${allArea.stateName}</td>
						<td>${allArea.countryName}</td>	
						<td>
							<c:if test="${menuByUserDTO.isAll==1}">
								<button class="btn btn-info btn-xs" type="button" onclick="javascript:openAddEditModal(${allArea.id},${allArea.countryId},${allArea.stateId},${allArea.cityId},${allArea.zoneId},&quot;${allArea.name }&quot;)"><i class="fa fa-pencil"></i>&nbsp;<spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
							</c:if>
							<c:if test="${menuByUserDTO.isView==1}">
								<button class="btn btn-theme04 btn-xs" type="button" onclick="showAccGroupDelModal(${allArea.id})"><i class="fa fa-trash-o "></i>&nbsp;<spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button>
							</c:if>
						</td>
					   </tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>		
          		</div>
          	</div>
		</section><!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/area/area.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/area/area_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showAccGroupDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 

$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getAreaText.dataTablePlaceHolder);
});
</script>
          		
      		</section><!-- /MAIN CONTENT -->
		<!--main content end-->
	<%-- 	<tiles:insertAttribute name="footer" /> --%>
	<jsp:include page="common/footer.jsp" />  
	</section>
	<!-- js placed at the end of the document so the pages load faster -->
  
    <%-- <script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script> --%>
    <script class="include" type="text/javascript" src="${pageContext.request.contextPath }/assets/js/common/jquery.dcjqaccordion.2.7.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/common/jquery.scrollTo.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/common/jquery.nicescroll.js" type="text/javascript"></script>
	
	<!--common script for all pages-->
    <script src="${pageContext.request.contextPath }/assets/js/common/common-scripts.js"></script>
</body>
</html>