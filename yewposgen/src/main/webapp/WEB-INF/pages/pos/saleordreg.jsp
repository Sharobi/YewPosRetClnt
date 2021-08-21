<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<style>
td {
    border-right: none !important;
}

</style>

<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><%-- <spring:message code="purinvreg.jsp.title" text="Purchase Invoice Register..." /> --%></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx">
					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/saleorder/searchsaleorderregister.htm" method="post" id="searchForm">
						<table>
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></td>
								<td><spring:message code="purinvreg.jsp.todt" text="To Date" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td class="hide" width="25%"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
								<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
								<td class= "${sesloggedinStore.isCustomerDisplay==1?' ': 'hide'}"><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
								<!-- <td class="add_td hide"></td> -->
							</tr>
							<tr>
								<td width="197px;" style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" readonly="readonly" class="form-control-trx" id="stdate" name="startDate" value="${commonResultSetMapper.startDate}">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td width="197px;" style="padding: 0 1px;">
									<!-- <div class="input-group date" data-provide="datepicker"> -->
									<div class="input-group">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="${commonResultSetMapper.endDate}">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<%-- <td style="padding: 0 1px;">
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon3">PIM/${sessionScope.sesloggedinUser.finyrCode}/</span> <input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}">
									</div>
								</td> --%>
								<td width="45px;" style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="${soDefaultPrefix}/" size="4" readonly></td>
								<c:if test="${empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								</c:if>
								<c:if test="${!empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${commonResultSetMapper.finyrCode}" size="5"></td>
								</c:if>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoSlash" value="/" readonly></td>
								<td width="185px;"><div class="input-group"><input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}"></div></td>
								
								<td class="hide" style="padding: 0 1px;"><select class="form-control-trx" name="distributorId" id="seldistributor">
										<c:if test="${!empty allVendors}">
										<option value="0">All</option>
											<c:forEach items="${allVendors}" var="allVendor">
												<c:if test="${commonResultSetMapper.distributorId==allVendor.id}">
													<option value="${allVendor.id}" selected="selected">${allVendor.name}</option>
												</c:if>
												<c:if test="${commonResultSetMapper.distributorId!=allVendor.id}">
													<option value="${allVendor.id}">${allVendor.name}</option>
												</c:if>
											</c:forEach>
										</c:if>
								</select></td>
								<td  width="17%" style="padding: 0 1px;"><input class="form-control-trx" id="salecustph" placeholder="Search by phone no " type="text"><input type="hidden" id="salecustid" name="custId" value="0"><input type="hidden" id="custCreditLimit" /></td>
							    <td  class= "${sesloggedinStore.isCustomerDisplay==1?' ': 'hide'}" width="25%" style="padding: 0 1px;"><input class="form-control-trx" placeholder="Search by name " id="salecustname" name="custName" type="text"></td>
								<!-- <td id="add_cust_td" class="hide">
									<button style="margin-right: 2px;" class="btn btn-primary" type="button" onclick="openAddModal('cust')">
										<i class="fa fa-plus"></i>
									</button>
								</td> -->
								<td style="padding: 0 2px;"><button type="button" id="search_btn" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button></td>
							</tr>
							<tr>
								<td colspan="6"><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<table id="purinvtable" class="table table-bordered table-striped table-condensed table-hover">
				<thead>
					<tr>
						<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></th>
						<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
						<th><spring:message code="purinvdet.jsp.cutomer" text="Customer" /></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.total" text="Total"/></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP:" /></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal:" /></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.advamt" text="Adv. Amt.:" /></th>
						<th><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></th>
						<th><spring:message code="purinvreg.jsp.status" text="Status" /></th>
						<th><spring:message code="purorder.jsp.ispostedstat" text="Is Posted" /></th>
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty saleOrderList }">
						<c:forEach items="${saleOrderList}" var="SaleOrder">
							<tr>
								<td>${SaleOrder.invNo}</td>
								<td><fmt:parseDate value="${SaleOrder.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" /></td>
								<td>${SaleOrder.customerName}</td>
								<td>${SaleOrder.grossAmount}</td>
								<td>${SaleOrder.totalMrp}</td>
								<td>${SaleOrder.netAmount}</td>
								<td>${SaleOrder.advAmount}</td>
								<td>${SaleOrder.remarks}</td>
								<td><c:if test="${SaleOrder.status==1}">
								Partially Completed
								</c:if>
								<c:if test="${SaleOrder.status==0}">
								Pending
								</c:if>
								<c:if test="${SaleOrder.status==2}">
								Completed
								</c:if>
								<c:if test="${SaleOrder.status==3}">
								Closed
								</c:if>
								</td>
								<td><c:if test="${SaleOrder.isPosted==1}">
								Posted
								</c:if>
									<c:if test="${SaleOrder.isPosted==0}">
								Unposted
								</c:if></td>
								<td>
									<button class="btn btn-success btn-xs" onclick="window.location.href ='${pageContext.request.contextPath}/reprintmemo/saleorder.htm?reprint=N&backUrl=saleorder/loadsaleordregister&saleId=3465fg-trw73sxz-${SaleOrder.saleOrderId}-utew09-qdd55-4320jhhgrt'">
									<i class="fa fa-print"></i>Print</button>
									<c:if test="${SaleOrder.isPosted==1}">
										<button class="btn btn-success btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/saleorder/loadsaleorderdet/${SaleOrder.saleOrderId}.htm'"><spring:message code="cmn.jsp.btn.view" text="View" /></button>
										<c:if test="${SaleOrder.status!=3}">
											<button class="btn btn-warning btn-xs" onclick="closePurchaseOrder(${SaleOrder.saleOrderId})"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
										</c:if>
									</c:if>
									<c:if test="${SaleOrder.isPosted==0}">
										<c:if test="${menuByUserDTO.isAll==1}">
											<button class="btn btn-theme btn-xs" type="button" onclick="postPurchaseInv(${SaleOrder.saleOrderId})">Post</button>
											<button class="btn btn-info btn-xs" type="button" onclick="window.location.href='${pageContext.request.contextPath}/saleorder/loadsaleorderdet/${SaleOrder.saleOrderId}.htm'">
												<i class="fa fa-pencil"></i>
												<spring:message code="cmn.jsp.btn.edit" text="Edit" />
											</button>
										</c:if>
										<c:if test="${menuByUserDTO.isView==1}">
											<button class="btn btn-theme04 btn-xs" type="button" onclick="deletePurchaseOrder(${SaleOrder.saleOrderId})">
												<i class="fa fa-trash-o "></i>
												<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
											</button>
										</c:if>
									</c:if>
									 <button class="btn btn-theme btn-xs complaint_btn hide" data-orderno="${SaleOrder.invNo}"  type="button">
										<i class="fa"></i>Complaint
									</button> 
									<c:if test="${sesloggedinStore.isMailEnable==1}">
										<button class="btn btn-theme btn-xs"  id="mail_btn" type="button" onclick="sendMail(${SaleOrder.saleOrderId},&quot;${SaleOrder.customerEmail}&quot;);">
											<spring:message code="cmn.jsp.btn.mail" text="MAIL" />
										</button>
									</c:if>
									<%-- <button class="btn btn-theme btn-xs"  id="bill_btn" type="button" onclick="getBill(${SaleOrder.saleOrderId});">
											<spring:message code="bill.jsp.show" text="Show" />
										</button> --%>
									<c:if test="${sessionScope.sesloggedinUser.storeId !=23}">
									<button class="btn btn-theme btn-xs"  id="bill_pdf_btn" type="button" onclick="saveBill(${SaleOrder.saleOrderId},1)">
											<spring:message code="bill.jsp.pdf" text="PDF" />
									</button>
									<button class="btn btn-theme btn-xs"  id="bill_excel_btn" type="button" onclick="saveBill(${SaleOrder.saleOrderId},2)">
											<spring:message code="bill.jsp.excel" text="EXCEL" />
									</button>  	
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
<div class="hide" id="printcontent"></div>
<!--/wrapper -->

<!-- Confirm Modal Start -->

<div class="modal fade" id="confirmModalPur" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
				<input type="hidden" id="confirmid"><input type="hidden" id="confirmtype">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmPurOrder()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->

<!-- Customer Contact modal Start -->

<div class="modal fade" id="complaintModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<!-- <h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.wait" text="Please Wait!" />
				</h4> -->
				<h4 class="modal-title">Complaint for order no. (<span id="saleOrderNum"></span>)</h4>
			</div>
			<div class="modal-body">
				  <h2>Please fill in the complain details:</h2>
				  <form id="complaintForm">
				  	<div class="form-group">
				      <label for="email">Full Name:</label>
				      <input type="text" class="form-control" id="email" placeholder="Enter Full Name" name="fullName">
				    </div>
				    <div class="form-group">
				      <label for="email">Email:</label>
				      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
				    </div>
				    <div class="form-group">
				      <label for="email">Product type:</label>
				      <select class="form-control" id="type" placeholder="Enter Complaint" name="type">
				      	<option selected="selected">Please select Product Type</option>
				      	<option value="Sungalsses">Sungalsses</option>
						<option value="Spectacles">Spectacles</option>
						<option value="Contact Lenses">Contact Lenses</option>
						<option value="Spectacle Lenses">Spectacle Lenses</option>
				      </select>
				    </div>
				    <div class="form-group">
				      <label for="email">Complaint:</label>
				      <textarea class="form-control" id="compliant" placeholder="Enter Complaint" name="compliant"></textarea>
				    </div>
				    
				    <button type="button" class="btn btn-default" data-dismiss="modal">Submit</button>
				  </form>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>
<!-- Customer Contact modal end -->
<%-- <div class="modal fade" id="billModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						      <h4 class="modal-title" id="myModalLabel">
						        	<span id="headertext"></span>
						        </h4>
						      </div>
						      <div class="modal-body">
						      <div id="billdata"></div>
						       
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearDiv();"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						       <button class="btn btn-theme"  id="pdf_btn" type="button" onclick="printPdf();">
											<spring:message code="bill.jsp.pdf" text="PDF" />
										</button>
									<button class="btn btn-theme"  id="excel_btn" type="button" >
											<spring:message code="bill.jsp.excel" text="EXCEL" />
								   </button>
						    </div>
						  </div>
				</div>
</div> --%>
<script src="${pageContext.request.contextPath }/assets/js/pos/saleorderreg/saleorderreg.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/pos/saleorderreg/saleorderreg_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

 <!-- <script src="https://kendo.cdn.telerik.com/2017.2.621/js/jquery.min.js"></script> 
 <script src="https://kendo.cdn.telerik.com/2017.2.621/js/jszip.min.js"></script>
 <script src="https://kendo.cdn.telerik.com/2017.2.621/js/kendo.all.min.js"></script> -->
 
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
var save_sale_order_url='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_SALEORDER_QUOTATION_DOWNLOAD)%>';

var companyId= "${sessionScope.sesloggedinUser.companyId}";
var storeId = "${sessionScope.sesloggedinUser.storeId}"; 


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
		$('.dataTables_filter input').attr("placeholder", getPurOrderRegText.dataTablePlaceHolder);
		strtDtGrtEndDt();


		$("#salecustph").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/customer/getcustomerwithcreditlistautocomplete.htm",
					type : "GET",
					data : {
						tagName : request.term
					},

					dataType : "json",

					success : function(data) {
						if (!$.trim(data)) {
							$('#add_cust_td').removeClass("hide");
							$('.add_td').removeClass("hide");
							$('#blacktext_td').removeClass("hide");
							$('#black_td').removeClass("hide");
						} else {
							$('#add_cust_td').addClass("hide");
							$('.add_td').addClass("hide");
							$('#blacktext_td').addClass("hide");
							$('#black_td').addClass("hide");
						}
						response($.map(data, function(v) {
							return {
								label : v.phoneNo,
								itemCode : v.id,
								name : v.name,
								creditLimit : v.creditLimit,
								items : v,
							};

						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});
			}
		},
		select : function(	e,
							ui) {
			//console.log("items=" + ui.item.creditLimit);
			$("#salecustid").val(ui.item.itemCode);
			$("#salecustname").val(ui.item.name);
			$("#custCreditLimit").val(ui.item.creditLimit);
			$("#ecardno").val(ui.item.items.code);
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				//		e.target.value = "";
				$("#salecustid").val(0);
			//$("#salecustname").val("");
		},
	});

	$("#salecustname").autocomplete({
		source : function(	request,
							response) {
			//		if (request.term.length >= 2) {
			$.ajax({
				url : BASE_URL + "/customer/getcustomerwithcreditlistautocompletebyname.htm",
				type : "GET",
				data : {
					tagName : request.term
				},

				dataType : "json",
				success : function(data) {
					if (!$.trim(data)) {
						$('#add_cust_td').removeClass("hide");
						$('.add_td').removeClass("hide");
						$('#blacktext_td').removeClass("hide");
						$('#black_td').removeClass("hide");
					} else {
						$('#add_cust_td').addClass("hide");
						$('.add_td').addClass("hide");
						$('#blacktext_td').addClass("hide");
						$('#black_td').addClass("hide");
					}
					response($.map(data, function(v) {
						return {
							label : v.name,
							itemCode : v.id,
							phno : v.phoneNo,
							creditLimit : v.creditLimit,
							items : v,
						};

					}));
				},
				error : function(error) {
					alert('error: ' + error);
				}
			});
			//		}
		},
		select : function(	e,
							ui) {

			$("#salecustid").val(ui.item.itemCode);
			$("#salecustph").val(ui.item.phno);
			$("#custCreditLimit").val(ui.item.creditLimit);
			$("#ecardno").val(ui.item.items.code);
			//		$("#prebilltd").removeClass("hide");
			//		$("#prebilltext").removeClass("hide");
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				//e.target.value = "";
				$("#salecustid").val(0);
			//$("#salecustph").val(0000000000);
		},
	});

	});

	$(".complaint_btn").on('click',function(){
		console.log("clicked");
		document.getElementById("complaintForm").reset();
		$("#saleOrderNum").text($(this).attr("data-orderno"));
		$("#complaintModal").modal("show");
	});	
</script>

