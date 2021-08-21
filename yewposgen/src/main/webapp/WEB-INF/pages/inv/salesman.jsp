<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
		<section class="wrapper">
			<div class="row">
          		<div class="col-lg-12">
   				<p><%-- <spring:message code="schedule.jsp.title" text="Schedule..." /> --%></p>
				<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-lg-8 col-md-8 col-sm-12">
						<form modelAttribute="commonResultSetMapper" class="form-inline" role="form" action="${pageContext.request.contextPath }/salesman/searchsalesman.htm" method="post">
							<div class="form-group">

								<input type="text" class="form-control" placeholder='<spring:message code="salesman.jsp.name" text="salesman Name" />' name="name" value="${salesmanMaster.name}">
							</div>
							<div class="form-group">
								<select class="form-control" name="qryCondition" >
									<option value="like" >LIKE</option>
									<option value="equals">=</option>
								</select>
							</div>

							<button type="submit" class="btn btn-theme">
								<spring:message code="cmn.jsp.search" text="Search" />
							</button>
						</form>
					</div>
					<div class="pull-right">
						<c:if test="${menuByUserDTO.isAll==1}">
							<button class="btn btn-primary" type="button" onclick="openAddEditModal(0,'')">
								<i class="fa fa-plus"></i>
								<spring:message code="cmn.jsp.btn.add" text="Add" />
							</button>
						</c:if>
					</div>
				</div>
			</div>
				<!-- Add/Edit Schedule Modal Starts -->

					<div class="modal fade" id="salesmanAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
							<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="salesman.jsp.code" text="Salesman Id" /></label>
							<div class="col-sm-8">
								<input type="text" id="salesman_code" class="form-control" placeholder="<spring:message code="salesman.jsp.code" text="salesman Code" />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="salesman.jsp.name" text="Salesman Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="salesman_name" class="form-control" placeholder="<spring:message code="salesman.jsp.name" text="salesman Name" />" />
							</div>
						</div>

						<div class="form-group">
                   			<label class="col-sm-4 col-sm-4 control-label" id="country_label"><spring:message code="city.jsp.countryname" text="Country Name" /> <!-- <span class="required_star">*</span> --></label>
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
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="city.jsp.statename" text="State Name" />
								<!-- <span class="required_star">*</span> -->
							</label>
							<div class="col-sm-8">
								<select class="form-control-trx" name="statelist" id="stateList" onchange="getCityByState()">
								</select>
							</div>
						</div>
			        	<div class="form-group">
                   			<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="city.jsp.cityname" text="City Name" />
                   			 <!-- <span class="required_star">*</span> -->
                   			</label>
                   			<div class="col-sm-8">
                   				<select id="cityList" name="cityList" class="form-control">
                   				</select>
                   			</div>
               			</div>

						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="manufacturer.jsp.addrs" text="Address" /></label>
							<div class="col-sm-8">
								<textarea  id="addrs" class="form-control" rows="4" placeholder="<spring:message code="manufacturer.jsp.addrs" text="Enter Address" />" ></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.phn" text="Contact No." /></label>
							<div class="col-sm-8">
								<input type="text" id="phn" class="form-control"  placeholder="<spring:message code="manufacturer.jsp.phn" text="Enter Contact No." />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="salesman.jsp.pan" text="PAN" /></label>
							<div class="col-sm-8">
								<input type="text" id="pan" class="form-control"  placeholder="<spring:message code="salesman.jsp.pan" text="Enter PAN No." />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="salesman.jsp.aadhar" text="Aadhar No." /></label>
							<div class="col-sm-8">
								<input type="text" id="aadhar" class="form-control"  placeholder="<spring:message code="salesman.jsp.aadhar" text="Aadhar No." />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="salesman.jsp.commition" text="Commition" /></label>
							<div class="col-sm-8">
								<input type="text" id="commition" class="form-control" placeholder="<spring:message code="salesman.jsp.commition" text="Enter Commition %." />" />
							</div>
						</div>
					</div>
						        <input type="hidden" id="salesman_id" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditsalesman()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div>

				<!-- Add/Edit Schedule Modal Ends -->

				<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="salesman.jsp.name" text="Salesman Name" /></th>
						<th><spring:message code="manufacturer.jsp.addrs" text="Address" /></th>
						<th><spring:message code="manufacturer.jsp.phn" text="Phone no" /></th>
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>

				<tbody>
				<c:if test="${!empty allSalesmanMasterlist}">
					<c:forEach items="${allSalesmanMasterlist}" var="salesmanmaster">
						<tr>
						<td>${salesmanmaster.name}</td>
						<td>${salesmanmaster.address}</td>
						<td>${salesmanmaster.phoneNo}</td>
						<td>
							<c:if test="${menuByUserDTO.isAll==1}">
								<button class="btn btn-info btn-xs" type="button" onclick="openAddEditModal(${salesmanmaster.id},&quot;${salesmanmaster.name}&quot;)">
								    <i class="fa fa-pencil"></i>&nbsp;<spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
							</c:if>
							<c:if test="${menuByUserDTO.isView==1}">
								<button class="btn btn-theme04 btn-xs" type="button" onclick="showScheduleDelModal(${salesmanmaster.id})"><i class="fa fa-trash-o "></i>&nbsp;<spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button>
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
<script src="${pageContext.request.contextPath }/assets/js/inventory/salesman/salesman.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/salesman/salesman_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
var countryId = "${sessionScope.sesloggedinUser.countryId}";
var stateId = "${sessionScope.sesloggedinUser.stateId}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showScheduleDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
}

$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getSalesmanText.dataTablePlaceHolder);
} );
</script>