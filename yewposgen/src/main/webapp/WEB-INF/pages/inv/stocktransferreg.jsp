<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link
	href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css"
	rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p> <spring:message code="stocktrns.reg.jsp.title"
					text="Stock Transfer Register..." />
			</p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx">
					<form modelAttribute="commonResultSetMapper"
						action="${pageContext.request.contextPath }/stock/searchstocksransferregister.htm"
						method="post" id="searchForm">
						<table>
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvreg.jsp.frmdt"
										text="From Date" /></td>
								<td><spring:message code="purinvreg.jsp.todt"
										text="To Date" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.invno"
										text="Invoice No" /></td>								
							</tr>
							<tr>
								<td width="197px;" style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" readonly="readonly"
											class="form-control-trx" id="stdate" name="startDate"
											value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td width="197px;" style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" readonly="readonly"
											class="form-control-trx" id="enddate" name="endDate"
											value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon3">STRNF/${sessionScope.sesloggedinUser.finyrCode}/</span>
										<input class="form-control-trx" type="text" name="invoiceNo"
											value="${commonResultSetMapper.invoiceNo}">
									</div>
								</td>

								<td style="padding: 0 2px;"><button type="button"
										id="search_btn" class="btn btn-theme">
										<spring:message code="cmn.jsp.search" text="Search" />
									</button></td>
							</tr>
							<tr>
								<td colspan="6"><div
										style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;"
										id="alertMsg"></div></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<table id="purinvtable"
				class="table table-bordered table-striped table-condensed table-hover">
				<thead>
					<tr>
						<th><spring:message code="pos.jsp.stocktransferjsp.invno"
								text="Invoice No" /></th>
						<th><spring:message code="pos.jsp.stocktransferjsp.invdt"
								text="Inv Date" /></th>
						<th><spring:message code="pos.jsp.stocktransferjsp.from"
								text="From" /></th>
						<th><spring:message code="pos.jsp.stocktransferjsp.to"
								text="To" /></th>
						<th class="numeric"><spring:message
								code="pos.jsp.stocktransferjsp.nettotal" text="NetTotal:" /></th>
						<th><spring:message code="pos.jsp.stocktransferjsp.remarks"
								text="Remarks:" /></th>
						<th><spring:message code="pos.jsp.stocktransferjsp.status"
								text="Status" /></th>
						<th><spring:message code="pos.jsp.stocktransferjsp.received"
								text="Is Received" /></th>
						<th><spring:message code="pos.jsp.stocktransferjsp.action"
								text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty stockTransferDTO }">
						<c:forEach items="${stockTransferDTO}" var="stockTransfer">
							<tr>
								<td>${stockTransfer.stockTransNo}</td>
								<td><fmt:parseDate value="${stockTransfer.stockTransDate}"
										var="parsedInvDate" pattern="MMM dd, yyyy" /> <fmt:formatDate
										pattern="yyyy-MM-dd" value="${parsedInvDate}" /></td>
								<td>${stockTransfer.fromStoreName}</td>
								<td>${stockTransfer.toStoreName}</td>
								<td>${stockTransfer.sendNetAmount}</td>
								<td>${stockTransfer.sendRemarks}</td>
								<td><c:if test="${stockTransfer.dispatchStatus=='N'}">
								Not dispatched
								</c:if> <c:if test="${stockTransfer.dispatchStatus=='Y'}">
								Dispatched
								</c:if></td>
								<td><c:if test="${stockTransfer.receiveStatus=='N'}">
								No
								</c:if> <c:if test="${stockTransfer.receiveStatus=='Y'}">
								Yes
								</c:if></td>
								<td>
									<c:if
										test="${stockTransfer.dispatchStatus=='Y'}">
										<button class="btn btn-success btn-xs"
											onclick="window.location.href='${pageContext.request.contextPath}/stock/stocktransferheader/${stockTransfer.transId}.htm'">
											<spring:message code="cmn.jsp.btn.view" text="View" />
										</button>

									</c:if> <c:if test="${stockTransfer.dispatchStatus=='N'}">
										<c:if test="${menuByUserDTO.isAll==1}">
											<button class="btn btn-theme btn-xs" type="button"
												onclick="dispatchStockTransfer(${stockTransfer.transId})">Dispatch</button>
											<button class="btn btn-info btn-xs" type="button"
												onclick="window.location.href='${pageContext.request.contextPath}/stock/stocktransferheader/${stockTransfer.transId}.htm'">
												<i class="fa fa-pencil"></i>
												<spring:message code="cmn.jsp.btn.edit" text="Edit" />
											</button>
											<button class="btn btn-theme04 btn-xs" type="button"
												onclick="deleteStockTransfer(${stockTransfer.transId})">
												<i class="fa fa-trash-o "></i>
												<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
											</button>
										</c:if>

									</c:if>
								</td>
							</tr>

						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</section>

<div class="modal fade" id="confirmModalStock" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="footer.jsp.cnfrmquestion" text="Are you sure?" />
				<input type="hidden" id="confirmid"><input type="hidden"
					id="confirmtype">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmStockTransfer()"
					data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<!--/wrapper -->

<!-- Confirm Modal Start -->


<!-- Confirm Modal end -->
<script
	src="${pageContext.request.contextPath }/assets/js/inventory/stock/stock_transfer.js"></script>

<script
	src="${pageContext.request.contextPath }/assets/js/glitter/jquery.gritter.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script
		src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script
	src="${pageContext.request.contextPath }/assets/js/inventory/stock/stock_trans_en.js"></script>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
	$(document).ready(function() {
		$('#purinvtable').DataTable({
			"lengthChange" : false,
		/* "searching": false,
		"pageLength": 12 */
		"columnDefs": [
		               { className: "text-right", "targets": [5,6,7] },
		             ]
		});
		var currentDate = new Date();
		var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
		$('#stdate').datepicker({
			format : 'yyyy-mm-dd',
			startDate : startDateFrom,
			autoclose: true,
		});
		$('#enddate').datepicker({
			format : 'yyyy-mm-dd',
			endDate : currentDate,
			autoclose: true,
		});
		/* $('.dataTables_filter input').attr("placeholder", getPurOrderRegText.dataTablePlaceHolder); */
		strtDtGrtEndDt();
	});
	
</script>