<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link
	href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css"
	rel="stylesheet">
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p>
				<%-- <spring:message code="schedule.jsp.title" text="Schedule..." /> --%>
			</p>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-lg-8 col-md-8 col-sm-12">
						<form modelAttribute="commonResultSetMapper" class="form-inline"
							role="form"
							action="${pageContext.request.contextPath }/invsetup/searchchargemaster.htm"
							method="post">
							<div class="form-group">

								<input type="text" class="form-control"
									placeholder='<spring:message code="chargemaster.jsp.name" text="Name" />'
									name="name" value="${commonResultSetMapper.name}">
							</div>
							<div class="form-group">
								<select class="form-control" name="qryCondition">
									<option value="like">LIKE</option>
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
							<button class="btn btn-primary" type="button"
								onclick="openAddEditModal(0,'')">
								<i class="fa fa-plus"></i>
								<spring:message code="cmn.jsp.btn.add" text="Add" />
							</button>
						</c:if>
					</div>
				</div>
			</div>


			<%-- <div class="pull-right">
					<c:if test="${menuByUserDTO.isAll==1}">
						<button class="btn btn-primary" type="button" onclick="openAddEditModal(0,'')">
						<i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
					</c:if>
				</div> --%>

			<!-- Add/Edit Schedule Modal Starts -->

			<div class="modal fade" id="marketAddEditModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				data-backdrop="static" data-keyboard="false">
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
									<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="chargemaster.jsp.name" text="Name"></spring:message><span class="required_star"> *</span>
										
									</label>
									<div class="col-sm-8">
										<input type="text" id="chargemaster_name" class="form-control-trx"
											placeholder="Name" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="chargemaster.jsp.code" text="Code"></spring:message>
										
									</label>
									<div class="col-sm-8">
										<input type="text" id="chargemaster_code" class="form-control-trx"
											placeholder="Code" />
									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label" id="rate_label"><spring:message code="chargemaster.jsp.rate" text="Rate"></spring:message><span class="required_star"> *</span></label>
									<div class="col-sm-8">
										<input type="text" id="rate" class="form-control-trx"
											placeholder="rate" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label"><spring:message code="chargemaster.jsp.factor" text="Factor"></spring:message></label>
									<div class="col-sm-8">
										<select class="form-control-trx" name="factor"
											id="factor">
											<option value="1">+ Ve</option>
											<option value="-1">- Ve</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label"><spring:message code="chargemaster.jsp.istaxable" text="IsTaxable"></spring:message></label>
									<div class="col-sm-8">
										<select class="form-control-trx" name="IsTaxable"
											id="istaxable">
											<option value="0">NO</option>
											<option value="1">YES</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label" ><spring:message code="chargemaster.jsp.wef" text="W.E.F"></spring:message></label>
									<div class="col-sm-8">
										<input type="text" class="form-control-trx" id="date"
											value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"
											placeholder="" />
									</div>
								</div>
							</div>


							<input type="hidden" id="chargemaster_id" value="0">
							<div
								style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;"
								id="alertMsg"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<spring:message code="cmn.jsp.btn.close" text="Close" />
							</button>
							<button type="button" onclick="javascript:addEditChMaster()"
								class="btn btn-theme">
								<spring:message code="cmn.jsp.btn.save" text="SAVE" />
							</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Add/Edit Schedule Modal Ends -->



			<table id="example"
				class="table table-bordered table-striped table-condensed table-hover display"
				cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Name</th>
						<th>Code</th>
						<th>Rate</th>
						<th><spring:message code="cmn.jsp.tblhdr.action"
								text="Action" /></th>
					</tr>
				</thead>

				<tbody>
					<c:if test="${!empty chargeMasters}">
						<c:forEach items="${chargeMasters}" var="chargeMaster">
							<tr>
								<td>${chargeMaster.name}</td>
								<td>${chargeMaster.code}</td>
								<td>${chargeMaster.rate}</td>
								<td><c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button"
											onclick="openAddEditModal(${chargeMaster.id},&quot;${chargeMaster.name}&quot;)">
											<i class="fa fa-pencil"></i>&nbsp;
											<spring:message code="cmn.jsp.btn.edit" text="Edit" />
										</button>
									</c:if> <c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" type="button"
											onclick="showScheduleDelModal(${chargeMaster.id})">
											<i class="fa fa-trash-o "></i>&nbsp;
											<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
										</button>
									</c:if></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</section>
<!--/wrapper -->
<script
	src="${pageContext.request.contextPath }/assets/js/inventory/chargemaster/chargemaster.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script
		src="${pageContext.request.contextPath}/assets/js/inventory/chargemaster/chargemaster_en.js"></script>
	<script
		src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
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
	var currentDate = new Date();
	$('#date').datepicker({
		format : 'yyyy-mm-dd',
		//endDate : currentDate,
		autoclose : true,
		orientation: "auto",
	});
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getMarketerText.dataTablePlaceHolder);
} );
</script>