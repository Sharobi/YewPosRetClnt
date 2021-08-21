<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">

			<p>
				<%-- <spring:message code="customer.jsp.title" text="Customer..." /> --%>
			</p>
			<div class="pull-right">
				<c:if test="${menuByUserDTO.isAll==1}">
					<button class="btn btn-primary" type="button" onclick="openAddEditModal(0)">
						<i class="fa fa-plus"></i>
						<spring:message code="cmn.jsp.btn.add" text="Add" />
					</button>
				</c:if>
			</div>

			<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="customer.jsp.name" text="Customer Name" /></th>
						<th><c:if test="${sesloggedinStore.isEsi==1}">
								<spring:message code="customer.jsp.inscardno" text="Ins/Card No" />
							</c:if>
							<c:if test="${sesloggedinStore.isEsi==0}">
								<spring:message code="customer.jsp.code" text="Code" />
							</c:if></th>
						<th><spring:message code="customer.jsp.address" text="Address" /></th>

						<th><spring:message code="customer.jsp.phn" text="Phone No." /></th>
						<th><spring:message code="customer.jsp.opbal" text="Opening Balance" /></th>
						<th><spring:message code="customer.jsp.creditLimit" text="creditLimit" /></th>
						<th><spring:message code="customer.jsp.recamt" text="Receivable Amount" /></th>
						<th><spring:message code="customer.jsp.avlLimit" text="Avl. Limit" /></th>
						<th style="width: 21%"><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty allCustomers }">
						<c:forEach items="${allCustomers}" var="allCustomer">
							<tr>
								<td>${allCustomer.name}</td>
								<td>${allCustomer.code}</td>
								<td>${allCustomer.address}</td>

								<td>${allCustomer.phoneNo}</td>
								<td>${allCustomer.obBal}</td>
								<td>${allCustomer.creditLimit}</td>
								<td>${allCustomer.paybleText}</td>
								<td>${allCustomer.creditLimit-allCustomer.paybleAmount}</td>
								<td><c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button" style="text-transform: uppercase; font-size: 11px;" onclick="openAddEditModal(${allCustomer.id})">
											<i class="fa fa-pencil"></i>
											<spring:message code="cmn.jsp.btn.edit" text="Edit" />
										</button>
									</c:if>
									<button class="btn btn-primary btn-xs" style="font-size: 11px;" type="button" onclick="window.location.href='${pageContext.request.contextPath }/customer/loadcustomerpay/${allCustomer.id}/${allCustomer.outstandingAmount}/${allCustomer.name}.htm'">
										<spring:message code="pos.jsp.receiptBtn" text="RECEIPT" />
									</button>
									<button class="btn btn-success btn-xs" style="font-size: 11px;" type="button" onclick="window.location.href='${pageContext.request.contextPath }/customer/loadcustomerpaydet/${allCustomer.id}/${allCustomer.outstandingAmount}/${allCustomer.name}.htm'">
										<spring:message code="pos.jsp.receiptBtnDet" text="RECEIPT DET." />
									</button> <c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" style="font-size: 11px;" type="button" onclick="showCustomerDelModal(${allCustomer.id})">
											<i class="fa fa-trash-o "></i>
											<spring:message code="cmn.jsp.tblhdr.del" text="Del" />
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

<!-- Add/Edit group modal start -->
<div class="modal fade" id="customerAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 836px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertext"></span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="customer.jsp.name" text="Customer Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="customerName" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="phn_label"><spring:message code="customer.jsp.phn" text="Phone No." /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="phn" class="form-control" onkeydown="numcheck(event)">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="customer.jsp.address" text="Address" /></label>
							<div class="col-sm-8">
								<input type="text" id="addrs" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="customer.jsp.pin" text="Pin" /> <!-- <span class="required_star">*</span> --></label>
							<div class="col-sm-8">
								<input type="text" id="pin" class="form-control" onkeydown="numcheck(event)">
							</div>
						</div>
						<!-- for fax -->
				<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="fax_label"><spring:message code="manufacturer.jsp.email" text="Email" /></label>
							<div class="col-sm-8">
								<input type="text" id="fax" class="form-control">
							</div>
						</div>
						<div class="form-group ${isTRN==1?'':'hide'}">
							<label class="col-sm-4 col-sm-4 control-label ">
							<spring:message code="customer.jsp.aadharno" text="Aadhar card no" />
							</label>

							<div class="col-sm-8">
								<input type="text" id="aadharcard" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label ">
							<spring:message code="customer.jsp.panno" text="PAN No." />
							</label>

							<div class="col-sm-8">
								<input type="text" id="panno" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label ">
							<%-- <spring:message code="customer.jsp.dlno" text="DL No." /> --%>
							<spring:message code="vendor.jsp.licenceno" text="Licence No" />
							</label>

							<div class="col-sm-8">
								<input type="text" id="dlno" class="form-control" placeholder="<spring:message code="cmn.jsp.egdlno" text="E.g. DL No." />"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label ">
							<spring:message code="${isTRN==2?'customer.jsp.trnno':'customer.jsp.gstno'}" text="${isTRN==2?'TRN/REG No.':'GST No.'}" />
							</label>

							<div class="col-sm-8">
								<input type="text" id="trnno_regno" class="form-control">
							</div>
						</div>
					</div>



					<div class="col-sm-6 col-sm-6 ">

						<%-- <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="country_label"><spring:message code="customer.jsp.country" text="Country" /></label>
							<div class="col-sm-8">
								<input type="text" id="country" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="customer.jsp.state" text="State" /></label>
							<div class="col-sm-8">
								<input type="text" id="state" class="form-control">
							</div>
						</div> --%>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label">Gender</label>
							<div class="col-sm-8">
								<select class="form-control" name="gender" id="slgender" onchange="getGender()">
									<c:if test="${!empty genderlist}">
										<c:forEach items="${genderlist}" var="glist">
											<option value="${glist.name}">${glist.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<!-- this for country name  -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="dctr.jsp.country" text="Country" /> </label>
							<div class="col-sm-8">
						<!-- 		<div class="input-group"> -->
									<select class="form-control-trx" name="country" id="country"  onchange="getStateByCountry();">
										<option value="0">Select...</option>
									<!-- 	<option value="101">india...</option> -->
									  <c:if test="${!empty contrylist}">
											<c:forEach items="${contrylist}" var="allcountry">
												<option value="${allcountry.id}">${allcountry.name}</option>
											</c:forEach>
										</c:if>
									</select>
									<!-- <div class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
											<i class="fa fa-plus"></i>
										</button>
									</div> -->
								<!-- </div> -->
							</div>
						</div>
					<!-- this for stateName  -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="states_label"><spring:message code="accsetup.jsp.state" text="State" /> <!-- <span class="required_star">*</span> --></label>
							<div class="col-sm-8">
						 <!--  onChange="getCityByState()"-->
									<select class="form-control-trx" name="state" id="state"  onChange="getCityByState()">


									</select>
									<!-- <div class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
											<i class="fa fa-plus"></i>
										</button>
									</div> -->

							</div>
						</div>

					<%-- 	<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="customer.jsp.city" text="City" /></label>
							<div class="col-sm-8">
								<input type="text" id="city" class="form-control">
							</div>
						</div> --%>
									<!-- this for CityName  -->
				 <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="accsetup.jsp.city" text="City" /> <!-- <span class="required_star">*</span> --></label>
							<div class="col-sm-8">
							<!--onChange="getZoneByCity()"  -->
								<div class="input-group">
									<select class="form-control-trx" name="city" id="city" >

									</select>
									<div class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openCityModel()">
											<i class="fa fa-plus"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="code_label"><c:if test="${sesloggedinStore.isEsi==1}">
									<spring:message code="customer.jsp.inscardno" text="Ins/Card No" />
								</c:if>
								<c:if test="${sesloggedinStore.isEsi==0}">
									<spring:message code="customer.jsp.code" text="Code" />
								</c:if></label>
							<div class="col-sm-8">
								<input type="text" id="code" class="form-control">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="opbal_label"><spring:message code="customer.jsp.opbal" text="Opening Balance" /></label>
							<div class="col-sm-8">
								<input type="text" id="opbal" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.creditLimit" text="Credit Limit" /></label>
							<div class="col-sm-8">
								<input type="text" id="creditLimit" class="form-control">
							</div>
						</div>
						<div class="form-group hide">
							<label class="col-sm-4 col-sm-4 control-label">D.O.B</label>
							<div class="col-sm-8">
								 <input type="text" class="form-control" id="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="type_label">Type</label>
							<div class="col-sm-8">
								<select class="form-control" name="customerTypeId" id="custTypeId" onchange="getCust()">
									<c:if test="${!empty customerTypes}">
										<c:forEach items="${customerTypes}" var="customerType">
											<option value="${customerType.id}">${customerType.typeName}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="cust_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditCustomer()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit group modal end -->

				<!-- add city modal start here  -->


			<div class="modal fade" id="cityAddEditModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
							<h4 class="modal-title" id="myModalLabel">
								<span id="headertextofcity"></span>
							</h4>
						</div>
						<div class="modal-body">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label" id="name_label_countryincity"><spring:message code="city.jsp.countryname" text="Country" />
										<span class="required_star">*</span>
									</label>
									<div class="col-sm-8">
										<select class="form-control-trx" name="countryincity" id="countryidincity" onchange="getStateByCountryinCity()">

											<option value="0">Select...</option>
												<c:if test="${!empty contrylist}">
											<c:forEach items="${contrylist}" var="allcountry">
												<option value="${allcountry.id}">${allcountry.name}</option>
											</c:forEach>
										</c:if>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label" id="name_label_stateincity"><spring:message code="city.jsp.statename" text="State" />
										<span class="required_star">*</span>
									</label>
									<div class="col-sm-8">
										<select class="form-control-trx" name="statelist" id="stateList">

										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label" id="name_label_cityincity"><spring:message code="city.jsp.cityname" text="City" /><span class="required_star"> *</span></label>
									<div class="col-sm-8">
										<input type="text" id="cityName" class="form-control-trx">
									</div>
								</div>
							</div>
							<input type="hidden" id="selcityId" value="">
							<div
								style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;"
								id="alertMsgForcity"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<spring:message code="cmn.jsp.btn.close" text="Close" />
							</button>
							<button type="button" id="savebtm" onclick="javascript:addEditCity()"
								class="btn btn-theme">
								<spring:message code="cmn.jsp.btn.save" text="SAVE" />
							</button>
						</div>
					</div>
				</div>
			</div>


				<!--  add city modal end here -->
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/pos/customer/customer.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/pos/customer/customer_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}"
var countryId = "${sessionScope.sesloggedinUser.countryId}";
var stateId = "${sessionScope.sesloggedinUser.stateId}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function showCustomerDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
}

$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    	"columnDefs": [
		               { className: "text-right", "targets": [4,5,7] },
		             ]
    });
    $('.dataTables_filter input').attr("placeholder", getCustomerText.dataTablePlaceHolder);
} );
</script>