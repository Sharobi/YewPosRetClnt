<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
		<section class="wrapper">
			<div class="row">
          		<div class="col-lg-12">
   				<p><%-- <spring:message code="subcat.jsp.title" text="SubCategory..." /> --%></p>
   				<div class="panel panel-default">
					<div class="panel-body">
                           
                         <div class="col-lg-10 col-md-10 col-sm-12">
								 <div class="col-lg-2 col-md-2"><b><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></b></div>
								<div class="col-lg-2 col-md-2"><b><spring:message code="purinvreg.jsp.todt" text="To Date" /></b></div>
								<div class="col-lg-3 col-md-3"><b><spring:message code="salesman.jsp.salesman" text="Salesman" /></b></div>
								
						</div>
						<div class="col-lg-10 col-md-10 col-sm-12">
							 <form modelAttribute="commonResultSetMapper" class="form-inline" role="form" action="${pageContext.request.contextPath }/salestour/searchTourPlan.htm" method="post">
								 <div class="col-lg-2 col-md-2 form-group">
                                          <input type="text" class="form-control-trx" id="fromdate" name="startDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
								</div>
								<div class="col-lg-2 col-md-2 form-group">     
									      <input type="text" class="form-control-trx" id="todate" name="endDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
									
								</div>
								<div class="col-lg-3 col-md-3 form-group">
									<select class="form-control" id="saleseman" name="salesmanId" style="height:29px;width:100%;" >
										    <option value="0">All</option>
											<c:if test="${!empty allSalesmanMasterlist}">
												<c:forEach items="${allSalesmanMasterlist}" var="saleseman">
													<option value="${saleseman.id}">${saleseman.name}</option>
												</c:forEach>
											</c:if>
									</select>
									
								</div>
								<div class="col-lg-2 col-md-2 form-group">
								  <button type="submit" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button>
								</div>
							</form> 
						</div>

						<%-- <div class="pull-right">
							<button class="btn btn-primary" type="button" onclick="javascript:openAddEditModal('','','','','','','','','')"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
						</div> --%>
					</div>
				</div>

			


				<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="tourplan.jsp.tourPlan" text="Plan No." /></th>
						<th><spring:message code="Tour Date" text="Tour Date" /></th>
						<th><spring:message code="tourplan.jsp.noOfCustomer" text="No.Of Customer" /></th>
						<th><spring:message code="tourplan.jsp.countryName" text="Country" /></th>
						<th><spring:message code="tourplan.jsp.stateName" text="State" /></th>
						<th><spring:message code="tourplan.jsp.remarks" text="Remarks" /></th>
						<th><spring:message code="tourplan.jsp.status" text="Status" /></th>
						<th><spring:message code="tourplan.jsp.cancelRemarks" text="Cancel Remarks" /></th>
						<th><spring:message code="tourplan.jsp.details" text="Details" /></th>
						
					</tr>
				</thead>

				<tbody>
				 <c:if test="${!empty allTours}">
					<c:forEach items="${allTours}" var="tour" varStatus="index">
						<tr>
					    <td>${tour.tourPlanNo}</td>
					    <td>${tour.tourDate}</td>
						<td>${tour.noOfCustomer}</td>
						<td>${tour.countryName}</td>
						<td>${tour.stateName}</td>
						<td>${tour.remarks}</td>
					    <td>${tour.isCancel == '0' ? 'Active' : 'Canceled'}</td>
						<td>${tour.cancelRemarks}</td>
						<td>
							<button class="btn btn-theme04 btn-xs" type="button" onclick="showTourDetaillModal('${tour.tourPlanNo}',${tour.saleTourPlanId},'${tour.tourDate}','${tour.salesmanName}','${tour.remarks}',${tour.isCancel},${tour.noOfCustomer});"><!-- <i class="fa fa-trash-o "></i>&nbsp; --><spring:message code="tourplan.jsp.details" text="Details" /></button>
							
						</td>
					   </tr>
					</c:forEach>
				</c:if> 
				</tbody>
			</table>
          		</div>
          	</div>
          	
   <div class="modal fade" id="tourPlanDetailModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 850px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="tourplan.jsp.tourplandetails" text="Tour Plan Details" />
				</h4>
			</div>
			<div class="modal-body">
				<div style="max-height: 300px; height: 200px; overflow: auto;"
					id="purordrbytypediv">
					<table id="salesmantbl"
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
							    <th>Plan Id</th>
								<th>Salesman Name</th>
								<th>Tour Date</th>
								<th>No. Of Customer</th>
								<th>Tour Status</th>
								<th>Remarks</th>
								
							</tr>
						</thead>
						<tbody id="tourPlanData">

						</tbody>
					</table>
					<table id="purordrbytypetable"
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th>Plan Id</th>
								<!-- <th>Tour Date</th> -->
								<th>Customer Name</th>
								<th>Customer Address</th>
								<th>City</th>
								<th>Zone</th>
								<th>Area</th>
							</tr>
						</thead>
						<tbody id="tourPlanDetails">

						</tbody>
					</table>
					<div>
						<span id="alertmessagecont"
							style="font-weight: bold; color: red;"> </span>
					</div>
				</div>
				
			</div>
			<div class="modal-footer" id="footerDiv">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<%-- <button type="button" class="btn btn-primary" id="purOrdrByTypeModal_okbtn"
					onclick="getmodcheckedpoitemlist()">
					<spring:message code="footer.jsp.btn.ok" text="Ok" />
				</button> --%>
			</div>
		</div>
	</div>
</div>
		</section><!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/ajax/ajax.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/pos/tourplan/tourplan.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/admin/user/user_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
var activeStoreId = "${sesloggedinUser.storeId}";
$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getUserText.dataTablePlaceHolder);
    
    $('#fromdate').datepicker({
		format : 'yyyy-mm-dd',
		autoclose: true
	});
	$('#todate').datepicker({
		format : 'yyyy-mm-dd',
		autoclose: true
	});
});
function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showUserDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
}


</script>