<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; max-height: 250px; width: 300px; word-break: break-all;
}
-->
.form-group {
    margin-bottom: 5px;
}
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<p>
			<%-- <spring:message code="itemmstr.jsp.title" text="Item Master..." /> --%>
		<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
		</p>

		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<form modelAttribute="itemMaster" role="form" action="${pageContext.request.contextPath}/item/addorupdateitem.htm" method="POST" id="item_form">
					<!--  this section for add -->

						<c:choose>
							<c:when test="${item_id==0}">
							<input type="hidden" value="" id="itemid" name="id">
							<!-- start first col  -->
							<div class="col-md-4 col-sm-12">
							<div class="form-horizontal">
							<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												 <c:if test="${retailTypeControlDTO.controlName=='name' && retailTypeControlDTO.isVisible=='1'}">
												<div class="form-group ">
											<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="itemmstr.jsp.name" text="Item Name" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="hidden" value="" id="itemidparchase" name="id"> <input type="text" value="" id="itemName" class="form-control-trx" onblur="checkSameItem(document.getElementById('itemName').value,0)" name="name" placeholder="<spring:message code="itemmstr.jsp.name" text="Item Name" />">
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='name' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group hide">
											  <label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="itemmstr.jsp.name" text="Item Name" /> <span class="required_star">*</span></label>
											 <div class="col-sm-8">
												<input type="hidden" value="" id="itemidparchase" name="id"> <input type="text" value="" id="itemName" class="form-control-trx" onblur="checkSameItem(document.getElementById('itemName').value,0)" name="name" placeholder="<spring:message code="itemmstr.jsp.name" text="Item Name" />">
											 </div>
									   	</div>
												 </c:if>
												</c:forEach>

										<div class="form-group" id="itmnameexistdiv" style="display: none;">
											<div class="alert alert-danger">
												<strong><spring:message code="footer.jsp.alert" text="Alert!" /></strong>
												<spring:message code="itemmstr.jsp.nameexist.error" text="Item name already exist, please try other." />
											</div>
										</div>
							<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 													<c:if test="${retailTypeControlDTO.controlName=='barcode' && retailTypeControlDTO.isVisible=='1'}">
                                                  <div class="form-group">
                                                  <label class="col-sm-4 col-sm-4 control-label" id="barcode_label">Barcode</label>
											   <div class="col-sm-8">
											 <input type="text" value="" id="sku" class="form-control-trx" name="sku" placeholder="Barcode">
											</div>
											</div>
 													</c:if>
 													<c:if test="${retailTypeControlDTO.controlName=='barcode' && retailTypeControlDTO.isVisible=='0'}">
													<div class="form-group hide">
													<label class="col-sm-4 col-sm-4 control-label hide" id="barcode_label">Barcode</label>
													<div class="col-sm-8">
												 <input type="text" value="" id="sku" class="form-control-trx" name="sku" placeholder="Barcode">
											</div>
											</div>
												 	</c:if>
											</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='group' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="grp_label"><spring:message code="itemmstr.jsp.group" text="Group" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="groupId" id="grpSelect">
														<c:if test="${!empty allGroups}">
															<c:forEach items="${allGroups}" var="allGroup">
																<option value="${allGroup.id}">${allGroup.name}</option>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openGroupMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='group' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="grp_label"><spring:message code="itemmstr.jsp.group" text="Group" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="groupId" id="grpSelect">
														<c:if test="${!empty allGroups}">
															<c:forEach items="${allGroups}" var="allGroup">
																<option value="${allGroup.id}">${allGroup.name}</option>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openGroupMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
 											</c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='schedule' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="schdle_label"><spring:message code="itemmstr.jsp.schedule" text="Schedule" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="scheduleId" id="scheSelect">
														<c:if test="${!empty allSchedules}">
															<c:forEach items="${allSchedules}" var="allSchedule">
																<option value="${allSchedule.id}">${allSchedule.name}</option>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='schedule' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="schdle_label"><spring:message code="itemmstr.jsp.schedule" text="Schedule" /></label>
											<div class="col-sm-8 hide">
												<div class="input-group">
													<select class="form-control-trx" name="scheduleId" id="scheSelect">
														<c:if test="${!empty allSchedules}">
															<c:forEach items="${allSchedules}" var="allSchedule">
																<option value="${allSchedule.id}">${allSchedule.name}</option>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='category' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="cat_label"><spring:message code="itemmstr.jsp.category" text="Category" /></label>
											<div class="col-sm-8">
											 <div class="input-group">
												<select class="form-control-trx" name="categoryId" id="catSelect" onchange="getSelectedCat()">
													<c:if test="${!empty allCat}">
															<c:forEach items="${allCat}" var="allcat">
																	<option value="${allcat.id}">${allcat.name}</option>
															</c:forEach>
														</c:if>
												</select>
												<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openAddCatModal()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='category' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cat_label"><spring:message code="itemmstr.jsp.category" text="Category" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="categoryId" id="catSelect">
													<c:if test="${!empty allCat}">
															<c:forEach items="${allCat}" var="allcat">
																	<option value="${allcat.id}">${allcat.name}</option>
															</c:forEach>
														</c:if>
												</select>
											</div>
										</div>
										 </c:if>
									</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='subCategory' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="subCat_label"><spring:message code="itemmstr.jsp.subcat" text="Sub-Category" /></label>
											<div class="col-sm-8">
											<div class="input-group">
												<select class="form-control-trx" name="subCategoryId" id="subCatSelect">

												</select>
												<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSubCatModal()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='subCategory' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="subCat_label"><spring:message code="itemmstr.jsp.subcat" text="Sub-Category" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="subCategoryId" id="subCatSelect">

												</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='brand' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="brand_label"><spring:message code="itemmstr.jsp.brand" text="Brand" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="brandId" value="0" class="form-control-trx" name="brandId"><input type="text" value="${itemMaster.brandMaster.name}" id="itemBrand" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.brand" text="Brand" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openBrandMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
											 </c:if>
 											 <c:if test="${retailTypeControlDTO.controlName=='brand' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="brand_label"><spring:message code="itemmstr.jsp.brand" text="Brand" /></label>
											<div class="col-sm-8 hide">
												<div class="input-group">
													<input type="hidden" id="brandId" value="0" class="form-control-trx" name="brandId"><input type="text" value="${itemMaster.brandMaster.name}" id="itemBrand" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.brand" text="Brand" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openBrandMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
 											 </c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
                                             <c:if test="${retailTypeControlDTO.controlName=='vat' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="vat_label"><spring:message code="itemmstr.jsp.vat" text="Vat" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemvat" class="form-control-trx" value="0" name="vat" placeholder="<spring:message code="itemmstr.jsp.vat" text="Vat" />">
											</div>
											</div>
                                             </c:if>
                                             <c:if test="${retailTypeControlDTO.controlName=='vat' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label hide" id="vat_label"><spring:message code="itemmstr.jsp.vat" text="Vat" /></label>
											<div class="col-sm-8 hide">
												<input type="text" id="itemvat" class="form-control-trx" value="0" name="vat" placeholder="<spring:message code="itemmstr.jsp.vat" text="Vat" />">
											</div>
											</div>
                                             </c:if>
                                         </c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='packingunit' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="pckUnit_label"><spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="packUnitId" value="" class="form-control-trx" name="packUnitId"><input type="text" id="itemPackingUnit" value="${itemMaster.packUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(2)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='packingunit' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="pckUnit_label"><spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="packUnitId" value="" class="form-control-trx" name="packUnitId"><input type="text" id="itemPackingUnit" value="${itemMaster.packUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(2)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
	 										</c:if>
										</c:forEach>

											<!--  for TAX TYPE by  -->

											<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 												<c:if test="${retailTypeControlDTO.controlName=='taxtype' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.taxtype" text="Tax type " /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="taxTypeId" id="alltaxtypeexclusive">
													<c:forEach  var="tt" items="${alltaxtypelist}">
													<option value="${tt.taxTypeId}">${tt.taxTypeName}</option>
													</c:forEach>
												</select>
											</div>
											</div>
 												</c:if>
 												<c:if test="${retailTypeControlDTO.controlName=='taxtype' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.taxtype" text="Tax type " /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="taxTypeId" id="alltaxtypeexclusive">
													<c:forEach  var="tt" items="${alltaxtypelist}">
													<option value="${tt.taxTypeId}">${tt.taxTypeName}</option>
													</c:forEach>
												</select>
											</div>
											</div>
 												</c:if>
											</c:forEach>

											<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='purchasetax' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"
												id="purchasetax_label">Purchase Tax <span
												class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="purchaseTaxId" value=""
														class="form-control-trx" name="purchaseTaxId"><input type="hidden" id="purchaseTaxPerc" value=""
														class="form-control-trx" name="purchaseTaxPercentage"><input
														type="text" id="purchaseTax" value=""
														class="form-control-trx" name="purchaseTax"
														placeholder="Purchase Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
 											</c:if>
											<c:if test="${retailTypeControlDTO.controlName=='purchasetax' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"
												id="purchasetax_label">Purchase Tax <span
												class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="purchaseTaxId" value=""
														class="form-control-trx" name="purchaseTaxId"><input type="hidden" id="purchaseTaxPerc" value=""
														class="form-control-trx" name="purchaseTaxPercentage"><input
														type="text" id="purchaseTax" value=""
														class="form-control-trx" name="purchaseTax"
														placeholder="Purchase Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
 											</c:if>
											</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='saletax' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"
												id="saletax_label">Sale Tax <span
												class="required_star">*</span> </label>
											<div class="col-sm-8">
											 	<div class="input-group">
													<input type="hidden" id="saleTaxId" value=""
														class="form-control-trx" name="saleTaxId">
														<input type="hidden" id="saleTaxPerc" value=""
														class="form-control-trx" name="saleTaxPercentage"><input
														type="text" id="saleTax" value="" class="form-control-trx"
														name="saleTax"
														placeholder="Sale Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='saletax' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group  hide">
												<label class="col-sm-4 col-sm-4 control-label"
												id="saletax_label">Sale Tax <span
												class="required_star">*</span> </label>
											<div class="col-sm-8">
											 	<div class="input-group">
													<input type="hidden" id="saleTaxId" value=""
														class="form-control-trx" name="saleTaxId">
														<input type="hidden" id="saleTaxPerc" value=""
														class="form-control-trx" name="saleTaxPercentage"><input
														type="text" id="saleTax" value="" class="form-control-trx"
														name="saleTax"
														placeholder="Sale Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
											</div>
 											</c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='mrp' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="price_label"><spring:message code="itemmstr.jsp.price" text="Price" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemPrice" value="" class="form-control-trx" name="mrp" placeholder="<spring:message code="itemmstr.jsp.price" text="Price" />" onkeyup="getMrp()">
											</div>
										   </div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='mrp' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="price_label"><spring:message code="itemmstr.jsp.price" text="Price" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemPrice" value="" class="form-control-trx" name="mrp" placeholder="<spring:message code="itemmstr.jsp.price" text="Price" />">
											</div>
										   </div>
 											</c:if>
										</c:forEach>
												<!-- for sale rate   -->
												<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 													<c:if test="${retailTypeControlDTO.controlName=='salerate' && retailTypeControlDTO.isVisible=='1'}">
												<div class="form-group">
													<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.salerate" text="Sale Rate " /></label>
													<div class="col-sm-8">
													<input type="text" id="sale_rate" value="0.0" class="form-control-trx" name="saleRate" placeholder="<spring:message code="itemmstr.jsp.salerate" text="Sale Rate" />">
													</div>
												</div>
 													</c:if>
 													<c:if test="${retailTypeControlDTO.controlName=='salerate' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group hide">
													<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.salerate" text="Sale Rate " /></label>
													<div class="col-sm-8">
													<input type="text" id="sale_rate" value="0.0" class="form-control-trx" name="saleRate" placeholder="<spring:message code="itemmstr.jsp.salerate" text="Sale Rate" />">
													</div>
												</div>
 													</c:if>
												</c:forEach>

										<!--  for purchase rate  -->
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='purchaserate' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.purchaserate" text="Purchase rate " /></label>
											<div class="col-sm-8">
												<input type="text" id="purchaserate" value="0.0" class="form-control-trx" name="purchaseRate" placeholder="<spring:message code="itemmstr.jsp.purchaserate" text="Purchase rate" />">
											</div>
											</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='purchaserate' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.purchaserate" text="Purchase rate " /></label>
											<div class="col-sm-8">
												<input type="text" id="purchaserate" value="0.0" class="form-control-trx" name="purchaseRate" placeholder="<spring:message code="itemmstr.jsp.purchaserate" text="Purchase rate" />">
											</div>
											</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='isActive' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isactive" text="Is Active" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="isActive" id="isactive">
													<option value="1">YES</option>
													<option value="0">NO</option>
												</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='isActive' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isactive" text="Is Active" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="isActive" id="isactive">
													<option value="1">YES</option>
													<option value="0">NO</option>
												</select>
											</div>
											</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='itemManufacturer' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="mnfctr_label"><spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="manufacturerId" value="" class="form-control-trx" name="manufacturerId"><input type="text" id="itemManufacturer" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openManufactMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='itemManufacturer' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="mnfctr_label"><spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="manufacturerId" value="" class="form-control-trx" name="manufacturerId"><input type="text" id="itemManufacturer" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openManufactMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
										</c:forEach>

											<!--  for marketed by  -->
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='marketer' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="marketed_by"><spring:message code="itemmstr.jsp.marketedby" text="Marketed By " /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="marketed_by_id" name="marketerId" value="" class="form-control-trx" >
													<input type="text" id="marketed_by_name" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.marketedby" text="Marketed By" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openAddEditMarketer();">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='marketer' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="marketed_by"><spring:message code="itemmstr.jsp.marketedby" text="Marketed By " /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="marketed_by_id" name="marketerId" value="" class="form-control-trx" >
													<input type="text" id="marketed_by_name" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.marketedby" text="Marketed By" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openAddEditMarketer();">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='authorname' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.authorname" text="Author Name" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="authorName" class="form-control-trx" name="authorName" placeholder="Author Name">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='authorname' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.authorname" text="Author Name" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="authorName" class="form-control-trx" name="authorName" placeholder="Author Name">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='edition' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.edition" text="Edition" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="edition" class="form-control-trx" name="edition" placeholder="Edition">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='edition' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.edition" text="Edition" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="edition" class="form-control-trx" name="edition" placeholder="Edition">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='hsncode' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.hsncode" text="HSN Code" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="hsnCode" class="form-control-trx" name="hsnCode" placeholder="HSN Code">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='hsncode' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.hsncode" text="HSN Code" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="hsnCode" class="form-control-trx" name="hsnCode" placeholder="HSN Code">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='printname' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.printname" text="Print Name" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="printname" class="form-control-trx" name="printName" placeholder="Print Name">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='printname' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.printname" text="Print Name" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="printname" class="form-control-trx" name="printName" placeholder="Print Name">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='code' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="itemmstr.jsp.code" text="Item Code" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemCode" value="" class="form-control-trx" name="code" placeholder="<spring:message code="itemmstr.jsp.code" text="Item Code" />">
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='code' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="itemmstr.jsp.code" text="Item Code" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemCode" value="" class="form-control-trx" name="code" placeholder="<spring:message code="itemmstr.jsp.code" text="Item Code" />">
											</div>
										</div>
										 </c:if>
									</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='imgurl' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.imgurl" text="Image URL" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="imgurl" class="form-control-trx" name="imgUrl" placeholder="Image URL">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='imgurl' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.imgurl" text="Image URL" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="imgurl" class="form-control-trx" name="imgUrl" placeholder="Image URL">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												 <c:if test="${retailTypeControlDTO.controlName=='netcontent' && retailTypeControlDTO.isVisible=='1'}">
												<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="netcntnt_label"><spring:message code="itemmstr.jsp.netcontent" text="Net Content" /></label>
											<div class="col-sm-8">
												<input type="text" id="netcontent" value="" class="form-control-trx" name="netContent" placeholder="<spring:message code="itemmstr.jsp.netcontent" text="Net Content" />">
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='netcontent' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="netcntnt_label"><spring:message code="itemmstr.jsp.netcontent" text="Net Content" /></label>
											<div class="col-sm-8">
												<input type="text" id="netcontent" value="" class="form-control-trx" name="netContent" placeholder="<spring:message code="itemmstr.jsp.netcontent" text="Net Content" />">
											</div>
										</div>
												 </c:if>
										</c:forEach>
							</div>
							</div>
							<!-- // end first col  -->
							<div class="col-md-4 col-sm-12">
							<div class="form-horizontal">
							<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='reorderlevel' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrder_label"><spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemreorderlevel" value="0" class="form-control-trx" name="reorderLevel" placeholder="<spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" />">
											</div>
											</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='reorderlevel' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrder_label"><spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemreorderlevel" value="0" class="form-control-trx" name="reorderLevel" placeholder="<spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" />">
											</div>
											</div>
 											</c:if>
										</c:forEach>
							<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='maxlevel' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maxlevel" text="Max Level" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="maxlevel" class="form-control-trx" name="maxLevel" placeholder="Max Level">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='maxlevel' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maxlevel" text="Max Level" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="maxlevel" class="form-control-trx" name="maxLevel" placeholder="Max Level">
											</div>
										</div>
 											</c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='minlevel' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.minlevel" text="Min Level" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="minlevel" class="form-control-trx" name="minLevel" placeholder="Min Level">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='minlevel' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.minlevel" text="Min Level" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="minlevel" class="form-control-trx" name="minLevel" placeholder="Min Level">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<!-- for order quantity -->
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='reorderLevelQty' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.orderquanity" text="Order Quantity " /></label>
											<div class="col-sm-8">
												<input type="text" id="orderquanity" value="0.0" class="form-control-trx" name="reorderLevelQty" placeholder="<spring:message code="itemmstr.jsp.orderquanity" text="Order Quantity" />">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='reorderLevelQty' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.orderquanity" text="Order Quantity " /></label>
											<div class="col-sm-8">
												<input type="text" id="orderquanity" value="0.0" class="form-control-trx" name="reorderLevelQty" placeholder="<spring:message code="itemmstr.jsp.orderquanity" text="Order Quantity" />">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												 <c:if test="${retailTypeControlDTO.controlName=='maximumQty' && retailTypeControlDTO.isVisible=='1'}">
												<!--  for maximum stock -->

											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maximumstock" text="Maximum stock" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="maximumstock" class="form-control-trx" name="maximumQty" placeholder="<spring:message code="itemmstr.jsp.maximumstock" text="Maximum stock" />">
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='maximumQty' && retailTypeControlDTO.isVisible=='0'}">
												<!--  for maximum stock -->

											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maximumstock" text="Maximum stock" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="maximumstock" class="form-control-trx" name="maximumQty" placeholder="<spring:message code="itemmstr.jsp.maximumstock" text="Maximum stock" />">
											</div>
										</div>
												 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='rack' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="rack_label"><spring:message code="itemmstr.jsp.rack" text="Rack" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="rackId" id="rackSelect">
													<option value="0">Select...</option>
													<c:if test="${!empty allRacks}">
														<c:forEach items="${allRacks}" var="allRack">
															<option value="${allRack.id}">${allRack.name}</option>
														</c:forEach>
													</c:if>
												</select>
											</div>
										</div>
											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='rack' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="rack_label"><spring:message code="itemmstr.jsp.rack" text="Rack" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="rackId" id="rackSelect">
													<option value="0">Select...</option>
													<c:if test="${!empty allRacks}">
														<c:forEach items="${allRacks}" var="allRack">
															<option value="${allRack.id}">${allRack.name}</option>
														</c:forEach>
													</c:if>
												</select>
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='isonmrp' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="isonmrp_label"><spring:message code="itemmstr.jsp.isonmrp" text="IsOnMRP" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="isOnMrp" id="isonmrp">
													<option value="0">NO</option>
													<option value="1">YES</option>
												</select>
											</div>
											</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='isonmrp' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="isonmrp_label"><spring:message code="itemmstr.jsp.isonmrp" text="IsOnMRP" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="isOnMrp" id="isonmrp">
													<option value="0">NO</option>
													<option value="1">YES</option>
												</select>
											</div>
											</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='colour' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="color_label"><spring:message code="itemmstr.jsp.colour" text="Colour" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="colour" class="form-control-trx" name="colour" placeholder="colour">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='colour' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.colour" text="Colour" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="colour" class="form-control-trx" name="colour" placeholder="colour">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='sizeType' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.sizetype" text="Size Type" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="sizeType" id="sizetype">
													<option value="1">USA</option>
													<option value="2">INDIA/UK</option>
													<option value="3">EUR</option>
											</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='sizeType' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.sizetype" text="Size Type" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="sizeType" id="sizetype">
													<option value="1">USA</option>
													<option value="2">INDIA/UK</option>
													<option value="3">EUR</option>
											</select>
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='size' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="size_label"><spring:message code="itemmstr.jsp.size" text="Size" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="size" class="form-control-trx" name="size" placeholder="Size">
											</div>
										</div>
	 										</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='size' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.size" text="Size" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="size" class="form-control-trx" name="size" placeholder="Size">
											</div>
										</div>
 											</c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='weight' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.weight" text="Weight" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="weight" class="form-control-trx" name="weight" placeholder="Weight">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='weight' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.weight" text="Weight" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="weight" class="form-control-trx" name="weight" placeholder="Weight">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='partnumber' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.partnumber" text="Part Number" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="partNumber" class="form-control-trx" name="partNumber" placeholder="Part Number">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='partnumber' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.partnumber" text="Part Number" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="partNumber" class="form-control-trx" name="partNumber" placeholder="Part Number">
											</div>
										</div>
 											</c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='listedMrp' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.listedmrp" text="Listed Mrp" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="listedMrp" class="form-control-trx" name="listedMrp" placeholder="Listed Mrp">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='listedMrp' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.listedmrp" text="Listed Mrp" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="listedMrp" class="form-control-trx" name="listedMrp" placeholder="Listed Mrp">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='wsp' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.wsp" text="WSP" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="wsp" class="form-control-trx" name="wsp" placeholder="wsp">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='wsp' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.wsp" text="WSP" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="wsp" class="form-control-trx" name="wsp" placeholder="wsp">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='markupperc' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" /></label>
											<div class="col-sm-8">
												<input type="number" id="itemMarkup" value="" class="form-control-trx" name="markup" placeholder="<spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" />">
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='markupperc' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" /></label>
											<div class="col-sm-8">
												<input type="number" id="itemMarkup" value="" class="form-control-trx" name="markup" placeholder="<spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" />">
											</div>
										</div>
										 </c:if>
									</c:forEach>
									<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												 <c:if test="${retailTypeControlDTO.controlName=='isdiscount' && retailTypeControlDTO.isVisible=='1'}">
												<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isdiscount" text="Is Discount" /></label>
											<div class="col-sm-8">
											<input type="hidden" id="isDiscounthid" value="1">
												<select class="form-control-trx" name="isDiscount" id="isDiscount">
											 	<option value="1">YES</option>
												<option value="0">NO</option>
												</select>
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='isdiscount' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isdiscount" text="Is Discount" /></label>
											<div class="col-sm-8">
											<input type="hidden" id="isDiscounthid" value="1">
												<select class="form-control-trx" name="isDiscount" id="isDiscount">
											 	<option value="1">YES</option>
												<option value="0">NO</option>
												</select>
											</div>
										</div>
												 </c:if>
											</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='maxdiscperc' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group" id="max_discount_hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maxdiscperc" text="Max Discount%" /></label>
											<div class="col-sm-8">
												<input type="text" id="maxDiscountLimit" value="0.0" class="form-control-trx" name="maxDiscountLimit" placeholder="Max Discount%">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='maxdiscperc' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide" id="max_discount_hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maxdiscperc" text="Max Discount%" /></label>
											<div class="col-sm-8">
												<input type="text" id="maxDiscountLimit" value="0.0" class="form-control-trx" name="maxDiscountLimit" placeholder="Max Discount%">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='discperc' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group" id="discount_hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.discperc" text="Discount%" /></label>
											<div class="col-sm-8">
												<input type="text" id="discount" value="0.0" class="form-control-trx" name="discount" placeholder="Discount%">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='discperc' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group" id="discount_hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.discperc" text="Discount%" /></label>
											<div class="col-sm-8">
												<input type="text" id="discount" value="0.0" class="form-control-trx" name="discount" placeholder="Discount%">
											</div>
										</div>
											 </c:if>
										</c:forEach>

										<!-- new extra field add here  -->

											<!--  for launch date -->
											<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='launchDate' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.launchdate" text="Launch date" /></label>
											<div class="col-sm-8">
											<input type="text" class="form-control-trx" id="launchdate" name="launchDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
											 <%-- <input type="text" value="" id="launchdate" class="form-control-trx" name="launchdate" placeholder="<spring:message code="itemmstr.jsp.launchdate" text="Launch date" />"> --%>
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='launchDate' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.launchdate" text="Launch date" /></label>
											<div class="col-sm-8">
											<input type="text" class="form-control-trx" id="launchdate" name="launchDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
											 <%-- <input type="text" value="" id="launchdate" class="form-control-trx" name="launchdate" placeholder="<spring:message code="itemmstr.jsp.launchdate" text="Launch date" />"> --%>
											</div>
										</div>
										 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='discontinuedate' && retailTypeControlDTO.isVisible=='1'}">
											<!--  for discontinue date -->
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.discontinuedate" text="Discontinue date" /></label>
											<div class="col-sm-8">

											<input type="text" class="form-control-trx" id="discontinuedate" name="discontinueDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
											<!--   <input type="text" value="" id="discontinuedate" class="form-control-trx" name="discontinuedate" placeholder="<spring:message code="itemmstr.jsp.discontinuedate" text="Discontinue date" />">-->
											</div>
										 </div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='discontinuedate' && retailTypeControlDTO.isVisible=='0'}">
											<!--  for discontinue date -->
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.discontinuedate" text="Discontinue date" /></label>
											<div class="col-sm-8">

											<input type="text" class="form-control-trx" id="discontinuedate" name="discontinueDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
											<!--   <input type="text" value="" id="discontinuedate" class="form-control-trx" name="discontinuedate" placeholder="<spring:message code="itemmstr.jsp.discontinuedate" text="Discontinue date" />">-->
											</div>
										    </div>
											 </c:if>
										</c:forEach>

									<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 										<c:if test="${retailTypeControlDTO.controlName=='itemContent' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cntnt_label"><spring:message code="pos.jsp.generic" text="Generic" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="content_id" value=""></input> <input type="hidden" id="content_Dets" value=""></input> <input type="hidden" id="contentId" value="" class="form-control-trx" name="contentId"><input type="text" id="itemContent" value="" class="form-control-trx" placeholder="<spring:message code="pos.jsp.generic" text="Generic" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button type="button" class="btn btn-default" id="contentdetails" onclick="contentDetailsMod()" style="padding: 6px;">
															<i class="fa fa-info-circle" aria-hidden="true"></i>
														</button>
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openContentMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='itemContent' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cntnt_label"><spring:message code="pos.jsp.generic" text="Generic" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="content_id" value=""></input> <input type="hidden" id="content_Dets" value=""></input> <input type="hidden" id="contentId" value="" class="form-control-trx" name="contentId"><input type="text" id="itemContent" value="" class="form-control-trx" placeholder="<spring:message code="pos.jsp.generic" text="Generic" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button type="button" class="btn btn-default" id="contentdetails" onclick="contentDetailsMod()" style="padding: 6px;">
															<i class="fa fa-info-circle" aria-hidden="true"></i>
														</button>
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openContentMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
									</c:forEach>
									<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='description' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.description" text="Description" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="description" class="form-control-trx" name="description" placeholder="description">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='description' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.description" text="Description" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="description" class="form-control-trx" name="description" placeholder="description">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='note' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.remarks" text="Remarks" /></label>
											<div class="col-sm-8">

											<textarea  class="form-control-trx" style="width: 218px; height: 47px;" id="itemRemarks" name="note" placeholder="<spring:message code="itemmstr.jsp.remarks" text="Remarks" />"></textarea>
												<!--<input type="text" id="itemRemarks" value="" class="form-control-trx" name="note" placeholder="<spring:message code="reprintcash.jsp.note" text="Note" />">-->
											</div>
										    </div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='note' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.remarks" text="Remarks" /></label>
											<div class="col-sm-8">

											<textarea  class="form-control-trx" style="width: 218px; height: 47px;" id="itemRemarks" name="note" placeholder="<spring:message code="itemmstr.jsp.remarks" text="Remarks" />"></textarea>
												<!--<input type="text" id="itemRemarks" value="" class="form-control-trx" name="note" placeholder="<spring:message code="reprintcash.jsp.note" text="Note" />">-->
											</div>
											</div>
											 </c:if>
									</c:forEach>
							</div>
							</div>
							<div class="col-md-4 col-sm-12">
							<div class="form-horizontal">
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='stockRequired' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.stockrequired" text="Stock Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="stockRequired" id="stockRequired">
													<option value="0">NO</option>
													<option value="1">YES</option>
												</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='stockRequired' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.stockrequired" text="Stock Required" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="stockRequired" class="form-control-trx" name="stockRequired" placeholder="Stock Required">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='mrpRequired' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.mrprequired" text="MRP Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="mrpRequired" id="mrpRequired">
													<option value="0">NO</option>
													<option value="1">YES</option>
												</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='mrpRequired' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.mrprequired" text="MRP Required" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="mrpRequired" class="form-control-trx" name="mrpRequired" placeholder="MRP Required">
											</div>
										</div>
	 										</c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='priceListRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.pricelistreq" text="PriceList Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="priceListRequired" id="priceListRequired">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										    </div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='priceListRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.pricelistreq" text="PriceList Required" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="priceListRequired" class="form-control-trx" name="priceListRequired" placeholder="PriceList Required">
											</div>
										   </div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='locationRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.locationreq" text="Location Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="locationRequired" id="locationRequired">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='locationRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.locationreq" text="Location Required" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="locationRequired" class="form-control-trx" name="locationRequired" placeholder="Location Required">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='sizeWiseStockRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.sizewisestkreq" text="SizeWise Stock Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="sizeWiseStockRequired" id="sizeWiseStockRequired">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='sizeWiseStockRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.sizewisestkreq" text="SizeWise Stock Required" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="sizeWiseStockRequired" class="form-control-trx" name="sizeWiseStockRequired" placeholder="SizeWise Stock Required">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												<c:if test="${retailTypeControlDTO.controlName=='colourWiseStockRequired' && retailTypeControlDTO.isVisible=='1'}">
														<div class="form-group">
														<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.colorwisestkreq" text="ColorWise Stock Required" /></label>
														<div class="col-sm-8">
														<select class="form-control-trx" name="colourWiseStockRequired" id="colourWiseStockRequired">
															<option value="0">NO</option>
															<option value="1">YES</option>
														</select>
														</div>
				  										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='colourWiseStockRequired' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group hide">
													<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.colorwisestkreq" text="ColorWise Stock Required" /></label>
													<div class="col-sm-8">
													 <select class="form-control-trx" name="colourWiseStockRequired" id="colourWiseStockRequired">
													        <option value="0">NO</option>
															<option value="1">YES</option>
														</select>
													</div>
										       </div>
												 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												 <c:if test="${retailTypeControlDTO.controlName=='batchWiseStock' && retailTypeControlDTO.isVisible=='1'}">
												<!-- extra field  end here  -->
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.batwisestk" text="BatchWise Stock" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="batchWiseStock" id="batchWiseStock" onchange="getBatWiseStk()">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='batchWiseStock' && retailTypeControlDTO.isVisible=='0'}">
												<!-- extra field  end here  -->
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.batwisestk" text="BatchWise Stock" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="batchWiseStock" id="batchWiseStock">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										</div>
												 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='expiryDateRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div id="expreqdiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.expdatereq" text="ExpiryDate Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="expiryDateRequired" id="expiryDateRequired">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='expiryDateRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.expdatereq" text="ExpiryDate Required" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="expiryDateRequired" class="form-control-trx" name="expiryDateRequired" placeholder="ExpiryDate Required">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='expiryMonthRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div id="expmonthreqdiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.expmonthreq" text="ExpiryMonth Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="expiryMonthRequired" id="expiryMonthRequired">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
											</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='expiryMonthRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.expmonthreq" text="ExpiryMonth Required" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="expiryMonthRequired" class="form-control-trx" name="expiryMonthRequired" placeholder="ExpiryMonth Required">
											</div>
											</div>
										 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='dualStockRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.dualstkreq" text="DualStock Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="dualStockRequired" id="dualStockRequired" onchange="dualStkReq()">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
											</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='dualStockRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.dualstkreq" text="DualStock Required" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="dualStockRequired" class="form-control-trx" name="dualStockRequired" placeholder="DualStock Required">
											</div>
										    </div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='conversion' && retailTypeControlDTO.isVisible=='1'}">
											<div id="ratiodiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cnvrsn_label"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemconversion" value="1" class="form-control-trx" name="conversion" placeholder="<spring:message code="purinvdet.jsp.ratio" text="Ratio" />">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='conversion' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cnvrsn_label"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemconversion" value="1" class="form-control-trx" name="conversion" placeholder="<spring:message code="purinvdet.jsp.ratio" text="Ratio" />">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='looseunit' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide" id="lseUnitDiv">
											<label class="col-sm-4 col-sm-4 control-label" id="lseUnit_label"><spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="looseUnitId" value="" class="form-control-trx" name="looseUnitId"><input type="text" id="itemLooseUnit" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(1)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='looseunit' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide" id="lseUnitDiv">
											<label class="col-sm-4 col-sm-4 control-label" id="lseUnit_label"><spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="looseUnitId" value="" class="form-control-trx" name="looseUnitId"><input type="text" id="itemLooseUnit" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(1)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='salerateon' && retailTypeControlDTO.isVisible=='1'}">
											<div id="rateondiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.salerateon" text="SaleRate On" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="saleRateOn" id="saleRateOn">
													<!-- <option value="Qty1">Qty1</option> -->
													<option value="Qty2">Qty2</option>
											</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='salerateon' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.salerateon" text="SaleRate On" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="saleRateOn" id="saleRateOn">
													<!-- <option value="Qty1">Qty1</option> -->
													<option value="Qty2">Qty2</option>
											</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='serialNoRequired' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.serialnorequired" text="SerialNo Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="serialNoRequired" id="serialNoRequired" onchange="getSelSerialNo()">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='serialNoRequired' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.serialnorequired" text="SerialNo Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="serialNoRequired" id="serialNoRequired" onchange="getSelSerialNo()">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='serialNoSuffRequired' && retailTypeControlDTO.isVisible=='1'}">
										<div id="slnosuffreq" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.serialnosuffrequired" text="SerialNo SuffRequired" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="serialNoSuffRequired" id="serialNoSuffRequired">
													<option value="0">NO</option>
													<option value="1">YES</option>
												</select>
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='serialNoSuffRequired' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.serialnosuffrequired" text="SerialNo SuffRequired" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="serialNoSuffRequired" class="form-control-trx" name="serialNoSuffRequired" placeholder="SerialNoSuff Required">
											</div>
										</div>
										 	</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='serialNoPrefRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div id="slNoPrefReq" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.slnoprefreq" text="SerialNo PrefRequired" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="serialNoPrefRequired" id="serialNoPrefRequired" onchange="getSlNoPrefReq()">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='serialNoPrefRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.slnoprefreq" text="SerialNo PrefRequired" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="serialNoPrefRequired" class="form-control-trx" name="serialNoPrefRequired" placeholder="SerialNo PrefRequired">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='serialNoType' && retailTypeControlDTO.isVisible=='1'}">
											<div id="slnotypeDiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.slnotype" text="SerialNo Type" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="serialNoType" id="serialNoType">
													<option value="1">MANUAL</option>
													<option value="2">AUTO</option>
													<option value="3">COMMON</option>
											</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='serialNoType' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.slnotype" text="SerialNo Type" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="serialNoType" id="serialNoType">
													<option value="1">MANUAL</option>
													<option value="2">AUTO</option>
													<option value="3">COMMON</option>
											</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												 <c:if test="${retailTypeControlDTO.controlName=='warrantyRequired' && retailTypeControlDTO.isVisible=='1'}">
												<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantyreq" text="Warranty Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="warrantyRequired" id="warrantyRequired" onchange="getWarrantyReq()">
													<option value="0">NO</option>
													<option value="1">YES</option>
											</select>
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='warrantyRequired' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantyreq" text="Warranty Required" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="warrantyRequired" class="form-control-trx" name="warrantyRequired" placeholder="Warranty Required">
											</div>
										</div>
												 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='warrantyTypeOn' && retailTypeControlDTO.isVisible=='1'}">
											<div id="warrantytypeondiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantytypeon" text="WarrantyType On" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="warrantyTypeOn" id="warrantyTypeOn">
													<option value="1">ISSUE</option>
													<option value="2">RECEIPT</option>
													<option value="3">BOTH</option>
												</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='warrantyTypeOn' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantytypeon" text="WarrantyType On" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="warrantyTypeOn" id="warrantyTypeOn">
													<option value="1">ISSUE</option>
													<option value="2">RECEIPT</option>
													<option value="3">BOTH</option>
												</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='warrantyMonth' && retailTypeControlDTO.isVisible=='1'}">
											<div id="warrantymonthondiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantymonthon" text="WarrantyMonth On" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="warrantyMonth" class="form-control-trx" name="warrantyMonth" placeholder="Warranty Month">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='warrantyMonth' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantymonthon" text="WarrantyMonth On" /></label>
											<div class="col-sm-8">
											 <input type="text" value="" id="warrantyMonth" class="form-control-trx" name="warrantyMonth" placeholder="Warranty Month">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<!---hide  on 11_02_2017-->
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='isLseSale' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isLseSale" text="Is Loose Sale" /></label>
											<div class="col-sm-8">
												<input type="checkbox" id="lseSaleChk" name="lseSaleChk" style="zoom: 1.5; vertical-align: middle; margin: 0px;" onchange="lseSaleChkBox();"><input type="hidden" name="isLooseSale" id="isLooseSale" />
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='isLseSale' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isLseSale" text="Is Loose Sale" /></label>
											<div class="col-sm-8">
												<input type="checkbox" id="lseSaleChk" name="lseSaleChk" style="zoom: 1.5; vertical-align: middle; margin: 0px;" onchange="lseSaleChkBox();"><input type="hidden" name="isLooseSale" id="isLooseSale" />
											</div>
										</div>
											 </c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='Reorderunit' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrderUnit_label"><spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" /></label>
											<div class="col-sm-8">
												<input type="hidden" value="" id="reorderLevelUnitId" class="form-control-trx" name="reorderLevelUnitId"> <input type="text" id="itemreorderUnitId" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" />(Please type atleast 2 characters)">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='Reorderunit' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrderUnit_label"><spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" /></label>
											<div class="col-sm-8">
												<input type="hidden" value="" id="reorderLevelUnitId" class="form-control-trx" name="reorderLevelUnitId"> <input type="text" id="itemreorderUnitId" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" />(Please type atleast 2 characters)">
											</div>
										</div>
											 </c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='strength' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.strength" text="Strength" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemstrength" value="" class="form-control-trx" name="strength" placeholder="<spring:message code="itemmstr.jsp.strength" text="Strength" />">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='strength' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.strength" text="Strength" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemstrength" value="" class="form-control-trx" name="strength" placeholder="<spring:message code="itemmstr.jsp.strength" text="Strength" />">
											</div>
										</div>
											 </c:if>
										</c:forEach>

							</div>
							</div>
								<div class="col-md-6 col-sm-12">
									<div class="form-horizontal">

										<!-- extra field here  -->
									</div><!--  Horizontal form end here  -->
								</div><!-- first column end here  -->

								<!--  2nd column -->
								<div class="col-md-6 col-sm-12">
									<div class="form-horizontal">

									</div>
								</div>

								<!-- 2nd column  end here  -->
							</c:when>
							<c:otherwise>
							<div class="col-md-4 col-sm-12">
							<div class="form-horizontal">
								<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
								 <c:if test="${retailTypeControlDTO.controlName=='name' && retailTypeControlDTO.isVisible=='1'}">
								<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="itemmstr.jsp.name" text="Item Name" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="hidden" value="${itemMaster.id}" id="itemid" name="id"> <input type="text" value="${itemMaster.name}" id="itemName" class="form-control-trx" onblur="checkSameItem(document.getElementById('itemName').value,${itemMaster.id})" name="name" placeholder="<spring:message code="itemmstr.jsp.name" text="Item Name" />">
											</div>
										</div>
								 </c:if>
								 <c:if test="${retailTypeControlDTO.controlName=='name' && retailTypeControlDTO.isVisible=='0'}">
								<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="itemmstr.jsp.name" text="Item Name" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="hidden" value="${itemMaster.id}" id="itemid" name="id"> <input type="text" value="${itemMaster.name}" id="itemName" class="form-control-trx" onblur="checkSameItem(document.getElementById('itemName').value,${itemMaster.id})" name="name" placeholder="<spring:message code="itemmstr.jsp.name" text="Item Name" />">
											</div>
										</div>
									 </c:if>
									</c:forEach>

										<div class="form-group" id="itmnameexistdiv" style="display: none;">
											<div class="alert alert-danger">
												<strong><spring:message code="footer.jsp.alert" text="Alert!" /></strong>
												<spring:message code="itemmstr.jsp.exist.error" text="Item name already exist, please try other." />
											</div>
										</div>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='barcode' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="barcode_label">Barcode</label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.sku}" id="sku" class="form-control-trx" name="sku" placeholder="Barcode">
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='barcode' && retailTypeControlDTO.isVisible=='0'}">

										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="barcode_label">Barcode</label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.sku}" id="sku" class="form-control-trx" name="sku" placeholder="Barcode">
											</div>
										</div>
										 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='group' && retailTypeControlDTO.isVisible=='1'}">
											 <div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="grp_label"><spring:message code="itemmstr.jsp.group" text="Group" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="groupId" id="grpSelect">
														<c:if test="${!empty allGroups}">
															<c:forEach items="${allGroups}" var="allGroup">
																<c:if test="${allGroup.id==itemMaster.groupMaster.id}">
																	<option value="${itemMaster.groupMaster.id}" selected="selected">${itemMaster.groupMaster.name}</option>
																</c:if>
																<c:if test="${allGroup.id!=itemMaster.groupMaster.id}">
																	<option value="${allGroup.id}">${allGroup.name}</option>
																</c:if>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openGroupMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='group' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="grp_label"><spring:message code="itemmstr.jsp.group" text="Group" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="groupId" id="grpSelect">
														<c:if test="${!empty allGroups}">
															<c:forEach items="${allGroups}" var="allGroup">
																<c:if test="${allGroup.id==itemMaster.groupMaster.id}">
																	<option value="${itemMaster.groupMaster.id}" selected="selected">${itemMaster.groupMaster.name}</option>
																</c:if>
																<c:if test="${allGroup.id!=itemMaster.groupMaster.id}">
																	<option value="${allGroup.id}">${allGroup.name}</option>
																</c:if>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openGroupMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='schedule' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="schdle_label"><spring:message code="itemmstr.jsp.schedule" text="Schedule" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="scheduleId" id="scheSelect">
														<c:if test="${!empty allSchedules}">
															<c:forEach items="${allSchedules}" var="allSchedule">
																<c:if test="${allSchedule.id==itemMaster.scheduleMaster.id}">
																	<option value="${itemMaster.scheduleMaster.id}" selected="selected">${itemMaster.scheduleMaster.name}</option>
																</c:if>
																<c:if test="${allSchedule.id!=itemMaster.scheduleMaster.id}">
																	<option value="${allSchedule.id}">${allSchedule.name}</option>
																</c:if>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='schedule' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="schdle_label"><spring:message code="itemmstr.jsp.schedule" text="Schedule" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="scheduleId" id="scheSelect">
														<c:if test="${!empty allSchedules}">
															<c:forEach items="${allSchedules}" var="allSchedule">
																<c:if test="${allSchedule.id==itemMaster.scheduleMaster.id}">
																	<option value="${itemMaster.scheduleMaster.id}" selected="selected">${itemMaster.scheduleMaster.name}</option>
																</c:if>
																<c:if test="${allSchedule.id!=itemMaster.scheduleMaster.id}">
																	<option value="${allSchedule.id}">${allSchedule.name}</option>
																</c:if>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
									 <c:if test="${retailTypeControlDTO.controlName=='category' && retailTypeControlDTO.isVisible=='1'}">
									<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="cat_label"><spring:message code="itemmstr.jsp.category" text="Category" /></label>
											<div class="col-sm-8">
											<div class="input-group">
                                                 <select class="form-control-trx" name="categoryId" id="catSelect" onchange="getSubCatEdit()">
													<c:if test="${!empty allCat}">
															<c:forEach items="${allCat}" var="allcat">
															<c:choose>
															<c:when test="${allcat.id==itemMaster.categoryId}">
															<option value="${allcat.id}" selected="selected">${allcat.name}</option>
															</c:when>
															<c:otherwise>
															<option value="${allcat.id}">${allcat.name}</option>
															</c:otherwise>
															</c:choose>

															</c:forEach>
														</c:if>
												</select>
												<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openAddCatModal()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>

												<input type="text" value="${itemMaster.categoryMaster.name}" id="catSelect" class="form-control-trx hide"  />
												<input type="hidden" name="categoryId" value="${itemMaster.categoryId}" />
											</div>
										</div>
									 </c:if>
									 <c:if test="${retailTypeControlDTO.controlName=='category' && retailTypeControlDTO.isVisible=='0'}">
									<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cat_label"><spring:message code="itemmstr.jsp.category" text="Category" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="categoryId" id="catSelect" onchange="getSubCatEdit()">
													<c:if test="${!empty allCat}">
															<c:forEach items="${allCat}" var="allcat">
															<c:choose>
															<c:when test="${allcat.id==itemMaster.categoryId}">
															<option value="${allcat.id}" selected="selected">${allcat.name}</option>
															</c:when>
															<c:otherwise>
															<option value="${allcat.id}">${allcat.name}</option>
															</c:otherwise>
															</c:choose>

															</c:forEach>
														</c:if>
												</select>
												<input type="text" value="${itemMaster.categoryMaster.name}" id="catSelect" class="form-control-trx" />
												<input type="hidden" name="categoryId" id="categoryIdEdit" value="${itemMaster.categoryId}" />
											</div>
										</div>
									 </c:if>
									</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='subCategory' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="subCat_label"><spring:message code="itemmstr.jsp.subcat" text="Sub-Category" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="subCategoryId" id="selsubCategoryIdEdit" >

												</select>

												<input type="text" value="${itemMaster.subCategoryMaster.name}" id="subCatSelect" class="form-control-trx hide"  />
												<input type="hidden" name="subCategoryId" id="subCategoryIdEdit" value="${itemMaster.subCategoryId}" />
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='subCategory' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="subCat_label"><spring:message code="itemmstr.jsp.subcat" text="Sub-Category" /></label>
											<div class="col-sm-8">
												<input type="text" value="${itemMaster.subCategoryMaster.name}" id="subCatSelect" class="form-control-trx" />
												<input type="hidden" name="subCategoryId" value="${itemMaster.subCategoryId}" />
											</div>
										</div>
										 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='brand' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="brand_label"><spring:message code="itemmstr.jsp.brand" text="Brand" /></label>
											<div class="col-sm-8">
												<input type="hidden" id="brandId" value="${itemMaster.brandId}" class="form-control-trx" name="brandId"><input type="text" value="${itemMaster.brandMaster.name}" id="itemBrand" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.brand" text="Brand" />(Please type atleast 2 characters)">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='brand' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="brand_label"><spring:message code="itemmstr.jsp.brand" text="Brand" /></label>
											<div class="col-sm-8">
												<input type="hidden" id="brandId" value="${itemMaster.brandId}" class="form-control-trx" name="brandId"><input type="text" value="${itemMaster.brandMaster.name}" id="itemBrand" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.brand" text="Brand" />(Please type atleast 2 characters)">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='vat' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="vat_label"><spring:message code="itemmstr.jsp.vat" text="Vat" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemvat" class="form-control-trx" value="${itemMaster.vat}" name="vat" placeholder="<spring:message code="itemmstr.jsp.vat" text="Vat" />">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='vat' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="vat_label"><spring:message code="itemmstr.jsp.vat" text="Vat" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemvat" class="form-control-trx" value="${itemMaster.vat}" name="vat" placeholder="<spring:message code="itemmstr.jsp.vat" text="Vat" />">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='packingunit' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="pckUnit_label"><spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="packUnitId" value="${itemMaster.packUnitId}" class="form-control-trx" name="packUnitId"><input type="text" id="itemPackingUnit" value="${itemMaster.packUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(2)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='packingunit' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="pckUnit_label"><spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="packUnitId" value="${itemMaster.packUnitId}" class="form-control-trx" name="packUnitId"><input type="text" id="itemPackingUnit" value="${itemMaster.packUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(2)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
										</c:forEach>


												<!--  for TAX TYPE by  -->
												<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												 <c:if test="${retailTypeControlDTO.controlName=='taxtype' && retailTypeControlDTO.isVisible=='1'}">
												<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.taxtype" text="Tax type " /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="taxTypeId" id="alltaxtypeexclusive">
													<c:forEach  var="tt" items="${alltaxtypelist}">
													 <c:if test = "${tt.taxTypeId == itemMaster.taxTypeId}">
													<option value="${tt.taxTypeId}" selected >${tt.taxTypeName}</option>
													</c:if>
													 <c:if test = "${tt.taxTypeId != itemMaster.taxTypeId}">
													<option value="${tt.taxTypeId}"  >${tt.taxTypeName}</option>
													</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='taxtype' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.taxtype" text="Tax type " /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="taxTypeId" id="alltaxtypeexclusive">
													<c:forEach  var="tt" items="${alltaxtypelist}">
													 <c:if test = "${tt.taxTypeId == itemMaster.taxTypeId}">
													<option value="${tt.taxTypeId}" selected >${tt.taxTypeName}</option>
													</c:if>
													 <c:if test = "${tt.taxTypeId != itemMaster.taxTypeId}">
													<option value="${tt.taxTypeId}"  >${tt.taxTypeName}</option>
													</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
												 </c:if>
												</c:forEach>

											<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='purchasetax' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"
												id="purchasetax_label">Purchase Tax<span
												class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="purchaseTaxId" value="${itemMaster.purchaseTax.id}"
														class="form-control-trx" name="purchaseTaxId"><input type="hidden" id="purchaseTaxPerc" value="${itemMaster.purchaseTax.percentage}"
														class="form-control-trx" name="purchaseTaxPercentage"><input
														type="text" id="purchaseTax" value="${itemMaster.purchaseTax.name}"
														class="form-control-trx" name="purchaseTax"
														placeholder="Purchase Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='purchasetax' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"
												id="purchasetax_label">Purchase Tax<span
												class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="purchaseTaxId" value="${itemMaster.purchaseTax.id}"
														class="form-control-trx" name="purchaseTaxId"><input type="hidden" id="purchaseTaxPerc" value="${itemMaster.purchaseTax.percentage}"
														class="form-control-trx" name="purchaseTaxPercentage"><input
														type="text" id="purchaseTax" value="${itemMaster.purchaseTax.name}"
														class="form-control-trx" name="purchaseTax"
														placeholder="Purchase Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
											 </c:if>
											</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='saletax' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"
												id="saletax_label">Sale Tax<span
												class="required_star">*</span> </label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="saleTaxId" value="${itemMaster.saleTax.id}"
														class="form-controltrx" name="saleTaxId"><input type="hidden" id="saleTaxPerc" value="${itemMaster.saleTax.percentage}"
														class="form-control-trx" name="saleTaxPercentage"><input
														type="text" id="saleTax" value="${itemMaster.saleTax.name}" class="form-control-trx"
														name="saleTax"
														placeholder="Sale Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='saletax' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"
												id="saletax_label">Sale Tax<span
												class="required_star">*</span> </label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="saleTaxId" value="${itemMaster.saleTax.id}"
														class="form-controltrx" name="saleTaxId"><input type="hidden" id="saleTaxPerc" value="${itemMaster.saleTax.percentage}"
														class="form-control-trx" name="saleTaxPercentage"><input
														type="text" id="saleTax" value="${itemMaster.saleTax.name}" class="form-control-trx"
														name="saleTax"
														placeholder="Sale Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='mrp' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="price_label"><spring:message code="itemmstr.jsp.price" text="Price" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemPrice" value="${itemMaster.mrp}" class="form-control-trx" name="mrp" placeholder="<spring:message code="itemmstr.jsp.price" text="Price" />" onkeyup="getMrp()">
											</div>
										   </div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='mrp' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="price_label"><spring:message code="itemmstr.jsp.price" text="Price" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemPrice" value="${itemMaster.mrp}" class="form-control-trx" name="mrp" placeholder="<spring:message code="itemmstr.jsp.price" text="Price" />">
											</div>
										   </div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='salerate' && retailTypeControlDTO.isVisible=='1'}">
										<!-- for sale rate   -->
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.salerate" text="Sale Rate " /></label>
											<div class="col-sm-8">
												<input type="text" id="sale_rate" value="${itemMaster.saleRate}" class="form-control-trx" name="saleRate" placeholder="<spring:message code="itemmstr.jsp.salerate" text="Sale Rate" />">
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='salerate' && retailTypeControlDTO.isVisible=='0'}">
										<!-- for sale rate   -->
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.salerate" text="Sale Rate " /></label>
											<div class="col-sm-8">
												<input type="text" id="sale_rate" value="${itemMaster.saleRate}" class="form-control-trx" name="saleRate" placeholder="<spring:message code="itemmstr.jsp.salerate" text="Sale Rate" />">
											</div>
										</div>
										 </c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='purchaserate' && retailTypeControlDTO.isVisible=='1'}">
										<!--  for purchase rate  -->
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.purchaserate" text="Purchase rate " /></label>
											<div class="col-sm-8">
												<input type="text" id="purchaserate" value="${itemMaster.purchaseRate}" class="form-control-trx" name="purchaseRate" placeholder="<spring:message code="itemmstr.jsp.purchaserate" text="Purchase rate" />">
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='purchaserate' && retailTypeControlDTO.isVisible=='0'}">
										<!--  for purchase rate  -->
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.purchaserate" text="Purchase rate " /></label>
											<div class="col-sm-8">
												<input type="text" id="purchaserate" value="${itemMaster.purchaseRate}" class="form-control-trx" name="purchaseRate" placeholder="<spring:message code="itemmstr.jsp.purchaserate" text="Purchase rate" />">
											</div>
										</div>
										 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='isActive' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isactive" text="Is Active" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="isActive" id="isactive">
													<c:if test="${itemMaster.isActive=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.isActive=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
												</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='isActive' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isactive" text="Is Active" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="isActive" id="isactive">
													<c:if test="${itemMaster.isActive=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.isActive=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
												</select>
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='itemManufacturer' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="mnfctr_label"><spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="manufacturerId" value="${itemMaster.manufacturerId}" class="form-control-trx" name="manufacturerId"><input type="text" id="itemManufacturer" value="${itemMaster.manufacturerMaster.name}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openManufactMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='itemManufacturer' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="mnfctr_label"><spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="manufacturerId" value="${itemMaster.manufacturerId}" class="form-control-trx" name="manufacturerId"><input type="text" id="itemManufacturer" value="${itemMaster.manufacturerMaster.name}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openManufactMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='marketer' && retailTypeControlDTO.isVisible=='1'}">
										<!--  for marketed by  -->
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="marketed_by"><spring:message code="itemmstr.jsp.marketedby" text="Marketed By " /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="marketed_by_id" value="${itemMaster.marketerMaster.id}" class="form-control-trx" name="marketerId">
													<input type="text" id="marketed_by_name" value="${itemMaster.marketerMaster.name}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.marketedby" text="Marketed By" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openAddEditMarketer();">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='marketer' && retailTypeControlDTO.isVisible=='0'}">
										<!--  for marketed by  -->
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="marketed_by"><spring:message code="itemmstr.jsp.marketedby" text="Marketed By " /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="marketed_by_id" value="${itemMaster.marketerMaster.id}" class="form-control-trx" name="marketerId">
													<input type="text" id="marketed_by_name" value="${itemMaster.marketerMaster.name}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.marketedby" text="Marketed By" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openAddEditMarketer();">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='authorname' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.authorname" text="Author Name" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.authorName}" id="authorName" class="form-control-trx" name="authorName" placeholder="Author Name">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='authorname' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.authorname" text="Author Name" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.authorName}" id="authorName" class="form-control-trx" name="authorName" placeholder="Author Name">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='edition' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.edition" text="Edition" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.edition}" id="edition" class="form-control-trx" name="edition" placeholder="Edition">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='edition' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.edition" text="Edition" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.edition}" id="edition" class="form-control-trx" name="edition" placeholder="Edition">
											</div>
										</div>
 											</c:if>
										</c:forEach>

											<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='hsncode' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">HSN Code</label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.hsnCode}" id="hsnCode" class="form-control-trx" name="hsnCode" placeholder="HSN Code">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='hsncode' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label">HSN Code</label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.hsnCode}" id="hsnCode" class="form-control-trx" name="hsnCode" placeholder="HSN Code">
											</div>
										</div>
											 </c:if>
											</c:forEach>


										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='printname' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.printname" text="Print Name" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.printName}" id="printname" class="form-control-trx" name="printName" placeholder="Print Name">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='printname' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.printname" text="Print Name" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.printName}" id="printname" class="form-control-trx" name="printName" placeholder="Print Name">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
									 <c:if test="${retailTypeControlDTO.controlName=='code' && retailTypeControlDTO.isVisible=='1'}">
									<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="itemmstr.jsp.code" text="Item Code" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemCode" value="${itemMaster.code}" class="form-control-trx" name="code" placeholder="<spring:message code="itemmstr.jsp.code" text="Item Code" />">
											</div>
										</div>
									 </c:if>
									 <c:if test="${retailTypeControlDTO.controlName=='code' && retailTypeControlDTO.isVisible=='0'}">
									<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="itemmstr.jsp.code" text="Item Code" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemCode" value="${itemMaster.code}" class="form-control-trx" name="code" placeholder="<spring:message code="itemmstr.jsp.code" text="Item Code" />">
											</div>
										</div>
									 </c:if>
									</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='imgurl' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.imgurl" text="Image URL" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.imgUrl}" id="imgurl" class="form-control-trx" name="imgUrl" placeholder="Image URL">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='imgurl' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.imgurl" text="Image URL" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.imgUrl}" id="imgurl" class="form-control-trx" name="imgUrl" placeholder="Image URL">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='netcontent' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="netcntnt_label"><spring:message code="itemmstr.jsp.netcontent" text="Net Content" /></label>
											<div class="col-sm-8">
												<input type="text" id="netcontent" value="${itemMaster.netContent}" class="form-control-trx" name="netContent" placeholder="<spring:message code="itemmstr.jsp.netcontent" text="Net Content" />">
											</div>
											</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='netcontent' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="netcntnt_label"><spring:message code="itemmstr.jsp.netcontent" text="Net Content" /></label>
											<div class="col-sm-8">
												<input type="text" id="netcontent" value="${itemMaster.netContent}" class="form-control-trx" name="netContent" placeholder="<spring:message code="itemmstr.jsp.netcontent" text="Net Content" />">
											</div>
										    </div>
											 </c:if>
										</c:forEach>
							</div>
							</div>
							<div class="col-md-4 col-sm-12">
							<div class="form-horizontal">
							<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='reorderlevel' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrder_label"><spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemreorderlevel" value="${itemMaster.reorderLevel}" class="form-control-trx" name="reorderLevel" placeholder="<spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" />">
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='reorderlevel' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrder_label"><spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemreorderlevel" value="${itemMaster.reorderLevel}" class="form-control-trx" name="reorderLevel" placeholder="<spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" />">
											</div>
										</div>
										 </c:if>
										</c:forEach>
							<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='maxlevel' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maxlevel" text="Max Level" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.maxLevel}" id="maxlevel" class="form-control-trx" name="maxLevel" placeholder="Max Level">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='maxlevel' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maxlevel" text="Max Level" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.maxLevel}" id="maxlevel" class="form-control-trx" name="maxLevel" placeholder="Max Level">
											</div>
										</div>
 											</c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='minlevel' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.minlevel" text="Min Level" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.minLevel}" id="minlevel" class="form-control-trx" name="minLevel" placeholder="Min Level">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='minlevel' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.minlevel" text="Min Level" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.minLevel}" id="minlevel" class="form-control-trx" name="minLevel" placeholder="Min Level">
											</div>
										</div>
 											</c:if>
										</c:forEach>
											<!-- for order quantity -->
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='reorderLevelQty' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.orderquanity" text="Order Quantity " /></label>
											<div class="col-sm-8">
												<input type="text" id="orderquanity" value="${itemMaster.reorderLevelQty}" class="form-control-trx" name="reorderLevelQty" placeholder="<spring:message code="itemmstr.jsp.orderquanity" text="Order Quantity" />">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='reorderLevelQty' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.orderquanity" text="Order Quantity " /></label>
											<div class="col-sm-8">
												<input type="text" id="orderquanity" value="${itemMaster.reorderLevelQty}" class="form-control-trx" name="reorderLevelQty" placeholder="<spring:message code="itemmstr.jsp.orderquanity" text="Order Quantity" />">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='maximumQty' && retailTypeControlDTO.isVisible=='1'}">
											<!--  for maximum stock -->
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maximumstock" text="Maximum stock" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.maximumQty}" id="maximumstock"  class="form-control-trx" name="maximumQty" placeholder="<spring:message code="itemmstr.jsp.maximumstock" text="Maximum stock" />">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='maximumQty' && retailTypeControlDTO.isVisible=='0'}">
											<!--  for maximum stock -->
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.maximumstock" text="Maximum stock" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.maximumQty}" id="maximumstock"  class="form-control-trx" name="maximumQty" placeholder="<spring:message code="itemmstr.jsp.maximumstock" text="Maximum stock" />">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='rack' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="rack_label"><spring:message code="itemmstr.jsp.rack" text="Rack" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="rackId" id="rackSelect">
													<c:if test="${!empty allRacks}">
														<option value="0">Select...</option>
														<c:forEach items="${allRacks}" var="allRack">
															<option value="${allRack.id}">${allRack.name}</option>
														</c:forEach>
													</c:if>
												</select> <input type="hidden" id="edited_rackId" value="${itemMaster.rackId}" />
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='rack' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="rack_label"><spring:message code="itemmstr.jsp.rack" text="Rack" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="rackId" id="rackSelect">
													<c:if test="${!empty allRacks}">
														<option value="0">Select...</option>
														<c:forEach items="${allRacks}" var="allRack">
															<option value="${allRack.id}">${allRack.name}</option>
														</c:forEach>
													</c:if>
												</select> <input type="hidden" id="edited_rackId" value="${itemMaster.rackId}" />
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='isonmrp' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="isonmrp_label"><spring:message code="itemmstr.jsp.isonmrp" text="IsOnMRP" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="isOnMrp" id="edited_isonmrpId">
													<c:if test="${itemMaster.isOnMrp=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.isOnMrp=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
												</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='isonmrp' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="isonmrp_label"><spring:message code="itemmstr.jsp.isonmrp" text="IsOnMRP" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="isOnMrp" id="edited_isonmrpId">
													<c:if test="${itemMaster.isOnMrp=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.isOnMrp=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
												</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='colour' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.colour" text="Colour" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.colour}" id="colour" class="form-control-trx" name="colour" placeholder="colour">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='colour' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.colour" text="Colour" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.colour}" id="colour" class="form-control-trx" name="colour" placeholder="colour">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='sizeType' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.sizetype" text="Size Type" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="sizeType" id="sizetype">
											       <c:if test="${itemMaster.sizeType=='1'}">
													<option value="1" selected="selected">USA</option>
													<option value="2">INDIA/UK</option>
													<option value="3">EUR</option>
													</c:if>
													<c:if test="${itemMaster.sizeType=='2'}">
													<option value="1">USA</option>
													<option value="2" selected="selected">INDIA/UK</option>
													<option value="3">EUR</option>
													</c:if>
													<c:if test="${itemMaster.sizeType=='3'}">
													<option value="1">USA</option>
													<option value="2">INDIA/UK</option>
													<option value="3" selected="selected">EUR</option>
													</c:if>
											</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='sizeType' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.sizetype" text="Size Type" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="sizeType" id="sizetype">
													<option value="1">USA</option>
													<option value="2">INDIA/UK</option>
													<option value="3">EUR</option>
											</select>
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='size' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.size" text="Size" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.size}" id="size" class="form-control-trx" name="size" placeholder="Size">
											</div>
										</div>
	 										</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='size' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.size" text="Size" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.size}" id="size" class="form-control-trx" name="size" placeholder="Size">
											</div>
										</div>
 											</c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='weight' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.weight" text="Weight" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.weight}" id="weight" class="form-control-trx" name="weight" placeholder="Weight">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='weight' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.weight" text="Weight" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.weight}" id="weight" class="form-control-trx" name="weight" placeholder="Weight">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='partnumber' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.partnumber" text="Part Number" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.partNumber}" id="partNumber" class="form-control-trx" name="partNumber" placeholder="Part Number">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='partnumber' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.partnumber" text="Part Number" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.partNumber}" id="partNumber" class="form-control-trx" name="partNumber" placeholder="Part Number">
											</div>
										</div>
 											</c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='listedMrp' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.listedmrp" text="Listed Mrp" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.listedMrp}" id="listedMrp" class="form-control-trx" name="listedMrp" placeholder="Listed Mrp">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='listedMrp' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.listedmrp" text="Listed Mrp" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.listedMrp}" id="listedMrp" class="form-control-trx" name="listedMrp" placeholder="Listed Mrp">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='wsp' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.wsp" text="WSP" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.wsp}" id="wsp" class="form-control-trx" name="wsp" placeholder="wsp">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='wsp' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.wsp" text="WSP" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.wsp}" id="wsp" class="form-control-trx" name="wsp" placeholder="wsp">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='markup' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" /></label>
											<div class="col-sm-8">
												<input type="number" id="itemMarkup" value="${itemMaster.markup}" class="form-control-trx" name="markup" placeholder="<spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" />">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='markup' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" /></label>
											<div class="col-sm-8">
												<input type="number" id="itemMarkup" value="${itemMaster.markup}" class="form-control-trx" name="markup" placeholder="<spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" />">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='isdiscount' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">Is Discount</label>
											<div class="col-sm-8">
											<input type="hidden" id="isDiscounthid" value="1">
												<select class="form-control-trx" name="isDiscount" id="isDiscount">
													<option value="1" ${itemMaster.isDiscount == '1' ? 'selected' : ''}>YES</option>
													 <option value="0" ${itemMaster.isDiscount == '0' ? 'selected' : ''}>NO</option>
												</select>
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='isdiscount' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label">Is Discount</label>
											<div class="col-sm-8">
											<input type="hidden" id="isDiscounthid" value="1">
												<select class="form-control-trx" name="isDiscount" id="isDiscount">
													<option value="1" ${itemMaster.isDiscount == '1' ? 'selected' : ''}>YES</option>
													 <option value="0" ${itemMaster.isDiscount == '0' ? 'selected' : ''}>NO</option>
												</select>
											</div>
										</div>
										 </c:if>
										</c:forEach>
											<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='maxDiscountLimit' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group" id="max_discount_hide">
											<label class="col-sm-4 col-sm-4 control-label">Max Discount%</label>
											<div class="col-sm-8">
												<input type="text" id="maxDiscountLimit" value="${itemMaster.maxDiscountLimit}" class="form-control-trx" name="maxDiscountLimit" placeholder="Max Discount%">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='maxDiscountLimit' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide" id="max_discount_hide">
											<label class="col-sm-4 col-sm-4 control-label">Max Discount%</label>
											<div class="col-sm-8">
												<input type="text" id="maxDiscountLimit" value="${itemMaster.maxDiscountLimit}" class="form-control-trx" name="maxDiscountLimit" placeholder="Max Discount%">
											</div>
										</div>
											 </c:if>
											</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='discperc' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group" id="discount_hide">
											<label class="col-sm-4 col-sm-4 control-label">Discount%</label>
											<div class="col-sm-8">
												<input type="text" id="discount" value="${itemMaster.discount}" class="form-control-trx" name="discount" placeholder="Discount%">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='discperc' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide" id="discount_hide">
											<label class="col-sm-4 col-sm-4 control-label">Discount%</label>
											<div class="col-sm-8">
												<input type="text" id="discount" value="${itemMaster.discount}" class="form-control-trx" name="discount" placeholder="Discount%">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='launchDate' && retailTypeControlDTO.isVisible=='1'}">
										<!--  for launch date -->
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.launchdate" text="Launch date" /></label>
											<div class="col-sm-8" >

										 <fmt:parseDate value="${itemMaster.launchDate}" type="BOTH" pattern="MMM dd, yyyy hh:mm:ss a" var="launchDate"/>
										  <input type="text" class="form-control-trx" id="launchdate" name="launchDate" value="<fmt:formatDate pattern="yyyy-MM-dd" type="DATE" value="${launchDate}" />">
											 <%-- <input type="text" value="" id="launchdate" class="form-control-trx" name="launchdate" placeholder="<spring:message code="itemmstr.jsp.launchdate" text="Launch date" />"> --%>
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='launchDate' && retailTypeControlDTO.isVisible=='0'}">
										<!--  for launch date -->
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.launchdate" text="Launch date" /></label>
											<div class="col-sm-8" >

										 <fmt:parseDate value="${itemMaster.launchDate}" type="BOTH" pattern="MMM dd, yyyy hh:mm:ss a" var="launchDate"/>
										  <input type="text" class="form-control-trx" id="launchdate" name="launchDate" value="<fmt:formatDate pattern="yyyy-MM-dd" type="DATE" value="${launchDate}" />">
											 <%-- <input type="text" value="" id="launchdate" class="form-control-trx" name="launchdate" placeholder="<spring:message code="itemmstr.jsp.launchdate" text="Launch date" />"> --%>
											</div>
										</div>
										 </c:if>
										</c:forEach>
										<!-- new extra field add here  -->
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='discontinuedate' && retailTypeControlDTO.isVisible=='1'}">
										<!--  for discontinue date -->
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.discontinuedate" text="Discontinue date" /></label>
											<div class="col-sm-8">
											<fmt:parseDate value="${itemMaster.discontinueDate}" type="BOTH" pattern="MMM dd, yyyy hh:mm:ss a" var="discontinueDate"/>
											<input type="text" class="form-control-trx" id="discontinuedate" name="discontinueDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${discontinueDate}" />">
											<!--   <input type="text" value="" id="discontinuedate" class="form-control-trx" name="discontinuedate" placeholder="<spring:message code="itemmstr.jsp.discontinuedate" text="Discontinue date" />">-->
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='discontinuedate' && retailTypeControlDTO.isVisible=='0'}">
										<!--  for discontinue date -->
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.discontinuedate" text="Discontinue date" /></label>
											<div class="col-sm-8">
											<fmt:parseDate value="${itemMaster.discontinueDate}" type="BOTH" pattern="MMM dd, yyyy hh:mm:ss a" var="discontinueDate"/>
											<input type="text" class="form-control-trx" id="discontinuedate" name="discontinueDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${discontinueDate}" />">
											<!--   <input type="text" value="" id="discontinuedate" class="form-control-trx" name="discontinuedate" placeholder="<spring:message code="itemmstr.jsp.discontinuedate" text="Discontinue date" />">-->
											</div>
										</div>
										 </c:if>
										</c:forEach>


									<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
									 <c:if test="${retailTypeControlDTO.controlName=='itemContent' && retailTypeControlDTO.isVisible=='1'}">
									<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cntnt_label"><spring:message code="itemmstr.jsp.content" text="Content" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="content_id" value="${itemMaster.contentId}"></input> <input type="hidden" id="content_Dets" value="${itemMaster.contentMaster.name}"></input> <input type="hidden" id="contentId" value="${itemMaster.contentId}" class="form-control-trx" name="contentId"><input type="text" id="itemContent" value="${itemMaster.contentMaster.name}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.content" text="Content" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button type="button" class="btn btn-default" id="contentdetails" onclick="contentDetailsMod()" style="height: 140%;">
															<i class="fa fa-info-circle" aria-hidden="true"></i>
														</button>
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openContentMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
									 </c:if>
									 <c:if test="${retailTypeControlDTO.controlName=='itemContent' && retailTypeControlDTO.isVisible=='0'}">
									<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cntnt_label"><spring:message code="itemmstr.jsp.content" text="Content" /></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="content_id" value="${itemMaster.contentId}"></input> <input type="hidden" id="content_Dets" value="${itemMaster.contentMaster.name}"></input> <input type="hidden" id="contentId" value="${itemMaster.contentId}" class="form-control-trx" name="contentId"><input type="text" id="itemContent" value="${itemMaster.contentMaster.name}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.content" text="Content" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button type="button" class="btn btn-default" id="contentdetails" onclick="contentDetailsMod()" style="height: 140%;">
															<i class="fa fa-info-circle" aria-hidden="true"></i>
														</button>
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openContentMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
									 </c:if>
									</c:forEach>
									<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='description' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.description" text="Description" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.description}" id="description" class="form-control-trx" name="description" placeholder="description">
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='description' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.description" text="Description" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.description}" id="description" class="form-control-trx" name="description" placeholder="description">
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='note' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.remarks" text="Remarks" /></label>
											<div class="col-sm-8">

											<textarea  class="form-control-trx" style="width: 218px; height: 47px;" id="itemRemarks" name="note" placeholder="<spring:message code="itemmstr.jsp.remarks" text="Remarks" />">${itemMaster.note}</textarea>
												<!--<input type="text" id="itemRemarks" value="" class="form-control-trx" name="note" placeholder="<spring:message code="reprintcash.jsp.note" text="Note" />">-->
											</div>
										    </div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='note' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.remarks" text="Remarks" /></label>
											<div class="col-sm-8">

											<textarea  class="form-control-trx" style="width: 218px; height: 47px;" id="itemRemarks" name="note" placeholder="<spring:message code="itemmstr.jsp.remarks" text="Remarks" />">${itemMaster.note}</textarea>
												<!--<input type="text" id="itemRemarks" value="" class="form-control-trx" name="note" placeholder="<spring:message code="reprintcash.jsp.note" text="Note" />">-->
											</div>
											</div>
											 </c:if>
									</c:forEach>
							</div>
							</div>
							<div class="col-md-4 col-sm-12">
							<div class="form-horizontal">
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='stockRequired' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.stockrequired" text="Stock Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="stockRequired" id="stockRequired">
													<c:if test="${itemMaster.stockRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.stockRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='stockRequired' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.stockrequired" text="Stock Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="stockRequired" id="stockRequired">
													<c:if test="${itemMaster.stockRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.stockRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='mrpRequired' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.mrprequired" text="MRP Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="mrpRequired" id="mrpRequired">
													<c:if test="${itemMaster.mrpRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.mrpRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='mrpRequired' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.mrprequired" text="MRP Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="mrpRequired" id="mrpRequired">
													<c:if test="${itemMaster.mrpRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.mrpRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										</div>
	 										</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='priceListRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.pricelistreq" text="PriceList Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="priceListRequired" id="priceListRequired">
											<c:if test="${itemMaster.priceListRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.priceListRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										    </div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='priceListRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.pricelistreq" text="PriceList Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="priceListRequired" id="priceListRequired">
											<c:if test="${itemMaster.priceListRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.priceListRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										   </div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='locationRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.locationreq" text="Location Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="locationRequired" id="locationRequired">
													<c:if test="${itemMaster.locationRequired=='0'}">
											 <option value="0" selected="selected">NO</option>
											 <option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.locationRequired=='1'}">
											  <option value="0">NO</option>
											  <option value="1" selected="selected">YES</option>
											</c:if>
												</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='locationRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.locationreq" text="Location Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="locationRequired" id="locationRequired">
											<c:if test="${itemMaster.locationRequired=='0'}">
											 <option value="0" selected="selected">NO</option>
											 <option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.locationRequired=='1'}">
											  <option value="0">NO</option>
											  <option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='sizeWiseStockRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.sizewisestkreq" text="SizeWise Stock Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="sizeWiseStockRequired" id="sizeWiseStockRequired">
													<c:if test="${itemMaster.sizeWiseStockRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.sizeWiseStockRequired=='1'}">
													        <option value="0">NO</option>
															<option value="1" selected="selected">YES</option>
													</c:if>
											</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='sizeWiseStockRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.sizewisestkreq" text="SizeWise Stock Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="sizeWiseStockRequired" id="sizeWiseStockRequired">
													<c:if test="${itemMaster.sizeWiseStockRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.sizeWiseStockRequired=='1'}">
													        <option value="0">NO</option>
															<option value="1" selected="selected">YES</option>
													</c:if>
											</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											<c:if test="${retailTypeControlDTO.controlName=='colourWiseStockRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.colorwisestkreq" text="ColorWise Stock Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="colourWiseStockRequired" id="colourWiseStockRequired">
													<c:if test="${itemMaster.colourWiseStockRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.colourWiseStockRequired=='1'}">
													        <option value="0">NO</option>
															<option value="1" selected="selected">YES</option>
													</c:if>
												</select>
											</div>
	  									 </div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='colourWiseStockRequired' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.colorwisestkreq" text="ColorWise Stock Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="colourWiseStockRequired" id="colourWiseStockRequired">
													<c:if test="${itemMaster.colourWiseStockRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.colourWiseStockRequired=='1'}">
													        <option value="0">NO</option>
															<option value="1" selected="selected">YES</option>
													</c:if>
												</select>
											</div>
										</div>
												 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												 <c:if test="${retailTypeControlDTO.controlName=='batchWiseStock' && retailTypeControlDTO.isVisible=='1'}">
												<!-- extra field  end here  -->
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.batwisestk" text="BatchWise Stock" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="batchWiseStock" id="batchWiseStock" onchange="getBatWiseStk()">
											<c:if test="${itemMaster.batchWiseStock=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.batchWiseStock=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='batchWiseStock' && retailTypeControlDTO.isVisible=='0'}">
												<!-- extra field  end here  -->
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.batwisestk" text="BatchWise Stock" /></label>
											<div class="col-sm-8">
											<c:if test="${itemMaster.batchWiseStock=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.batchWiseStock=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</div>
										</div>
												 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='expiryDateRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div id="expreqdiv" class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.expdatereq" text="ExpiryDate Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="expiryDateRequired" id="expiryDateRequired">
											<c:if test="${itemMaster.expiryDateRequired=='0'}">
											 <option value="0" selected="selected">NO</option>
											 <option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.expiryDateRequired=='1'}">
											  <option value="0">NO</option>
											  <option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='expiryDateRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.expdatereq" text="ExpiryDate Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="expiryDateRequired" id="expiryDateRequired">
											<c:if test="${itemMaster.expiryDateRequired=='0'}">
											 <option value="0" selected="selected">NO</option>
											 <option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.expiryDateRequired=='1'}">
											  <option value="0">NO</option>
											  <option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='expiryMonthRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div id="expmonthreqdiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.expmonthreq" text="ExpiryMonth Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="expiryMonthRequired" id="expiryMonthRequired">
													<c:if test="${itemMaster.expiryMonthRequired=='0'}">
											 			<option value="0" selected="selected">NO</option>
											 			<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.expiryMonthRequired=='1'}">
											  			<option value="0">NO</option>
											  			<option value="1" selected="selected">YES</option>
													</c:if>
											 </select>
											</div>
											</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='expiryMonthRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.expmonthreq" text="ExpiryMonth Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="expiryMonthRequired" id="expiryMonthRequired">
													<c:if test="${itemMaster.expiryMonthRequired=='0'}">
											 			<option value="0" selected="selected">NO</option>
											 			<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.expiryMonthRequired=='1'}">
											  			<option value="0">NO</option>
											  			<option value="1" selected="selected">YES</option>
													</c:if>
											 </select>
											</div>
											</div>
										 </c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='dualStockRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.dualstkreq" text="DualStock Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="dualStockRequired" id="dualStockRequired" onchange="dualStkReq()">
											<c:if test="${itemMaster.dualStockRequired=='0'}">
											 <option value="0" selected="selected">NO</option>
											 <option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.dualStockRequired=='1'}">
											  <option value="0">NO</option>
											  <option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
											</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='dualStockRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.dualstkreq" text="DualStock Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="dualStockRequired" id="dualStockRequired" >
											<c:if test="${itemMaster.dualStockRequired=='0'}">
											 <option value="0" selected="selected">NO</option>
											 <option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.dualStockRequired=='1'}">
											  <option value="0">NO</option>
											  <option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										    </div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='conversion' && retailTypeControlDTO.isVisible=='1'}">
											<div id="ratiodiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cnvrsn_label"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemconversion" value="${itemMaster.conversion}" class="form-control-trx" name="conversion" placeholder="<spring:message code="purinvdet.jsp.ratio" text="Ratio" />">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='conversion' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cnvrsn_label"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemconversion" value="${itemMaster.conversion}" class="form-control-trx" name="conversion" placeholder="<spring:message code="purinvdet.jsp.ratio" text="Ratio" />">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='looseunit' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group hide" id="lseUnitDiv">
											<label class="col-sm-4 col-sm-4 control-label" id="lseUnit_label"><spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="looseUnitId" value="${itemMaster.looseUnitId}" class="form-control-trx" name="looseUnitId"><input type="text" id="itemLooseUnit" value="${itemMaster.looseUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(1)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='looseunit' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide" id="lseUnitDiv">
											<label class="col-sm-4 col-sm-4 control-label" id="lseUnit_label"><spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="looseUnitId" value="${itemMaster.looseUnitId}" class="form-control-trx" name="looseUnitId"><input type="text" id="itemLooseUnit" value="${itemMaster.looseUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(1)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='salerateon' && retailTypeControlDTO.isVisible=='1'}">
											<div id="rateondiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.salerateon" text="SaleRate On" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="saleRateOn" id="saleRateOn">
											<%-- <c:if test="${itemMaster.saleRateOn=='Qty1'}">
											        <option value="Qty1" selected="selected">Qty1</option>
													<option value="Qty2">Qty2</option>
											</c:if> --%>
											<c:if test="${itemMaster.saleRateOn=='Qty2'}">
											      <!--   <option value="Qty1">Qty1</option> -->
													<option value="Qty2" selected="selected">Qty2</option>
											</c:if>
												</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='salerateon' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.salerateon" text="SaleRate On" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.saleRateOn}" id="saleRateOn" class="form-control-trx" name="saleRateOn" placeholder="SaleRate On">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='serialNoRequired' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.serialnorequired" text="SerialNo Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="serialNoRequired" id="serialNoRequired" onchange="getSelSerialNo()">
													<c:if test="${itemMaster.serialNoRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.serialNoRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</select>
											</div>
										</div>
 											</c:if>
 											<c:if test="${retailTypeControlDTO.controlName=='serialNoRequired' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.serialnorequired" text="SerialNo Required" /></label>
											<div class="col-sm-8">
											<c:if test="${itemMaster.serialNoRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.serialNoRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</div>
										</div>
 											</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
 											<c:if test="${retailTypeControlDTO.controlName=='serialNoSuffRequired' && retailTypeControlDTO.isVisible=='1'}">
										<div id="slnosuffreq" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.serialnosuffrequired" text="SerialNo SuffRequired" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="serialNoSuffRequired" id="serialNoSuffRequired">
													<c:if test="${itemMaster.serialNoSuffRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.serialNoSuffRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
												</select>
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='serialNoSuffRequired' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.serialnosuffrequired" text="SerialNo SuffRequired" /></label>
											<div class="col-sm-8">
												<c:if test="${itemMaster.serialNoSuffRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
											</c:if>
											<c:if test="${itemMaster.serialNoSuffRequired=='1'}">
											        <option value="0">NO</option>
													<option value="1" selected="selected">YES</option>
											</c:if>
											</div>
										</div>
										 	</c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='serialNoPrefRequired' && retailTypeControlDTO.isVisible=='1'}">
											<div id="slNoPrefReq" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.slnoprefreq" text="SerialNo PrefRequired" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="serialNoPrefRequired" id="serialNoPrefRequired">
													<c:if test="${itemMaster.serialNoPrefRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.serialNoPrefRequired=='1'}">
													        <option value="0">NO</option>
															<option value="1" selected="selected">YES</option>
													</c:if>
												</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='serialNoPrefRequired' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.slnoprefreq" text="SerialNo PrefRequired" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="serialNoPrefRequired" id="serialNoPrefRequired">
													<c:if test="${itemMaster.serialNoPrefRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.serialNoPrefRequired=='1'}">
													        <option value="0">NO</option>
															<option value="1" selected="selected">YES</option>
													</c:if>
												</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='serialNoType' && retailTypeControlDTO.isVisible=='1'}">
											<div id="slnotypeDiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.slnotype" text="SerialNo Type" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="serialNoType" id="serialNoType">
											<c:if test="${itemMaster.serialNoType=='1'}">
											        <option value="1" selected="selected">MANUAL</option>
													<option value="2">AUTO</option>
													<option value="3">COMMON</option>
											</c:if>
											<c:if test="${itemMaster.serialNoType=='2'}">
											        <option value="1">MANUAL</option>
													<option value="2" selected="selected">AUTO</option>
													<option value="3">COMMON</option>
											</c:if>
											<c:if test="${itemMaster.serialNoType=='3'}">
											        <option value="1">MANUAL</option>
													<option value="2">AUTO</option>
													<option value="3" selected="selected">COMMON</option>
											</c:if>
											</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='serialNoType' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.slnotype" text="SerialNo Type" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="serialNoType" id="serialNoType">
											<c:if test="${itemMaster.serialNoType=='1'}">
											        <option value="1" selected="selected">MANUAL</option>
													<option value="2">AUTO</option>
													<option value="3">COMMON</option>
											</c:if>
											<c:if test="${itemMaster.serialNoType=='2'}">
											        <option value="1">MANUAL</option>
													<option value="2" selected="selected">AUTO</option>
													<option value="3">COMMON</option>
											</c:if>
											<c:if test="${itemMaster.serialNoType=='3'}">
											        <option value="1">MANUAL</option>
													<option value="2">AUTO</option>
													<option value="3" selected="selected">COMMON</option>
											</c:if>
											</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
												 <c:if test="${retailTypeControlDTO.controlName=='warrantyRequired' && retailTypeControlDTO.isVisible=='1'}">
												<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantyreq" text="Warranty Required" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="warrantyRequired" id="warrantyRequired" onchange="getWarrantyReq()">
													<c:if test="${itemMaster.warrantyRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.warrantyRequired=='1'}">
													        <option value="0">NO</option>
															<option value="1" selected="selected">YES</option>
													</c:if>
												</select>
											</div>
										</div>
												 </c:if>
												 <c:if test="${retailTypeControlDTO.controlName=='warrantyRequired' && retailTypeControlDTO.isVisible=='0'}">
												<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantyreq" text="Warranty Required" /></label>
											<div class="col-sm-8">
											 <select class="form-control-trx" name="warrantyRequired" id="warrantyRequired" onchange="getWarrantyReq()">
													<c:if test="${itemMaster.warrantyRequired=='0'}">
											        <option value="0" selected="selected">NO</option>
													<option value="1">YES</option>
													</c:if>
													<c:if test="${itemMaster.warrantyRequired=='1'}">
													        <option value="0">NO</option>
															<option value="1" selected="selected">YES</option>
													</c:if>
												</select>
											</div>
										</div>
												 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='warrantyTypeOn' && retailTypeControlDTO.isVisible=='1'}">
											<div id="warrantytypeondiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantytypeon" text="WarrantyType On" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="warrantyTypeOn" id="warrantyTypeOn" >
											<c:if test="${itemMaster.warrantyTypeOn=='1'}">
											        <option value="1" selected="selected">ISSUE</option>
													<option value="2">RECEIPT</option>
													<option value="3">BOTH</option>
											</c:if>
											<c:if test="${itemMaster.warrantyTypeOn=='2'}">
													<option value="1">ISSUE</option>
													<option value="2" selected="selected">RECEIPT</option>
													<option value="3">BOTH</option>
											</c:if>
											<c:if test="${itemMaster.warrantyTypeOn=='3'}">
													<option value="1">ISSUE</option>
													<option value="2">RECEIPT</option>
													<option value="3" selected="selected">BOTH</option>
											</c:if>
												</select>
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='warrantyTypeOn' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantytypeon" text="WarrantyType On" /></label>
											<div class="col-sm-8">
											<select class="form-control-trx" name="warrantyTypeOn" id="warrantyTypeOn" >
											<c:if test="${itemMaster.warrantyTypeOn=='1'}">
											        <option value="1" selected="selected">ISSUE</option>
													<option value="2">RECEIPT</option>
													<option value="3">BOTH</option>
											</c:if>
											<c:if test="${itemMaster.warrantyTypeOn=='2'}">
													<option value="1">ISSUE</option>
													<option value="2" selected="selected">RECEIPT</option>
													<option value="3">BOTH</option>
											</c:if>
											<c:if test="${itemMaster.warrantyTypeOn=='3'}">
													<option value="1">ISSUE</option>
													<option value="2">RECEIPT</option>
													<option value="3" selected="selected">BOTH</option>
											</c:if>
											</select>
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='warrantyMonth' && retailTypeControlDTO.isVisible=='1'}">
											<div id="warrantymonthondiv" class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantymonthon" text="WarrantyMonth On" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.warrantyMonth}" id="warrantyMonth" class="form-control-trx" name="warrantyMonth" placeholder="Warranty Month">
											</div>
											</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='warrantyMonth' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.warrantymonthon" text="WarrantyMonth On" /></label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.warrantyMonth}" id="warrantyMonth" class="form-control-trx" name="warrantyMonth" placeholder="Warranty Month">
											</div>
										</div>
											 </c:if>
										</c:forEach>


										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='isLseSale' && retailTypeControlDTO.isVisible=='1'}">
											<!-- hide on 11_02_2017-->
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isLseSale" text="Sale Unit" /></label>
											<div class="col-sm-8">
												<input type="checkbox" id="lseSaleChk" name="lseSaleChk" style="zoom: 1.5; vertical-align: middle; margin: 0px;" onchange="lseSaleChkBox();"><input type="hidden" name="isLooseSale" id="isLooseSale" value="${itemMaster.isLooseSale}" />
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='isLseSale' && retailTypeControlDTO.isVisible=='0'}">
											<!-- hide on 11_02_2017-->
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isLseSale" text="Sale Unit" /></label>
											<div class="col-sm-8">
												<input type="checkbox" id="lseSaleChk" name="lseSaleChk" style="zoom: 1.5; vertical-align: middle; margin: 0px;" onchange="lseSaleChkBox();"><input type="hidden" name="isLooseSale" id="isLooseSale" value="${itemMaster.isLooseSale}" />
											</div>
										</div>
											 </c:if>
										</c:forEach>

										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
											 <c:if test="${retailTypeControlDTO.controlName=='Reorderunit' && retailTypeControlDTO.isVisible=='1'}">
											<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrderUnit_label"><spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" /></label>
											<div class="col-sm-8">
												<input type="hidden" value="${itemMaster.reorderLevelUnitId}" id="reorderLevelUnitId" class="form-control-trx" name="reorderLevelUnitId"> <input type="text" id="itemreorderUnitId"  value="${itemMaster.packUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" />(Please type atleast 2 characters)">
											</div>
										</div>
											 </c:if>
											 <c:if test="${retailTypeControlDTO.controlName=='Reorderunit' && retailTypeControlDTO.isVisible=='0'}">
											<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrderUnit_label"><spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" /></label>
											<div class="col-sm-8">
												<input type="hidden" value="${itemMaster.reorderLevelUnitId}" id="reorderLevelUnitId" class="form-control-trx" name="reorderLevelUnitId"> <input type="text" id="itemreorderUnitId"  value="${itemMaster.packUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" />(Please type atleast 2 characters)">
											</div>
										</div>
											 </c:if>
										</c:forEach>
										<c:forEach  var="retailTypeControlDTO" items="${retailTypeControlDTOs}">
										 <c:if test="${retailTypeControlDTO.controlName=='strength' && retailTypeControlDTO.isVisible=='1'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.strength" text="Strength" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemstrength" value="${itemMaster.strength}" class="form-control-trx" name="strength" placeholder="<spring:message code="itemmstr.jsp.strength" text="Strength" />">
											</div>
										</div>
										 </c:if>
										 <c:if test="${retailTypeControlDTO.controlName=='strength' && retailTypeControlDTO.isVisible=='0'}">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.strength" text="Strength" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemstrength" value="${itemMaster.strength}" class="form-control-trx" name="strength" placeholder="<spring:message code="itemmstr.jsp.strength" text="Strength" />">
											</div>
										</div>
										 </c:if>
										</c:forEach>
							</div>
							</div>

							</c:otherwise>
						</c:choose>
						<div class="pull-right">
							<button type="button" class="btn btn-default" data-dismiss="modal" onclick="window.location.href='${pageContext.request.contextPath}/item/loaditem.htm'">
								<spring:message code="cmn.jsp.btn.close" text="Close" />
							</button>
							<c:if test="${menuByUserDTO.isAll==1}">
								<c:if test="${item_id==0}">
									<button class="btn btn-primary" type="button" id="itemaddbut" onclick="javascript:clicksub()">
										<i class="fa fa-plus"></i>
										<spring:message code="cmn.jsp.btn.add" text="Add" />
									</button>
								</c:if>
								<c:if test="${item_id!=0}">
									<button class="btn btn-info" type="button" id="itemupdatebut" onclick="javascript:clicksub()">
										<i class="fa fa-pencil"></i>
										<spring:message code="cmn.jsp.btn.update" text="Update" />
									</button>
							  	<button class="btn btn-theme04" type="button" onclick="showItemDelModal(${item_id})">
										<i class="fa fa-trash-o "></i>
										<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
									</button>
								</c:if>
								<!-- 								<input type="submit" value="Submit" /> -->
							</c:if>
						</div>
					</form>

					<!-- ============================================this is for edit start ============================================-->
				</div>
			</div>
		</div>
	</div>
</section>

<!-- Content Details modal start -->
<div class="modal fade" id="contentDetailsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
						<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="content.jsp.name" text="Content" /> </label>
						<div class="col-sm-8">
							<textarea id="contentVal" class="form-control-trx" rows="4"></textarea>
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>

			</div>
		</div>
	</div>
</div>
<!-- Content Details modal end -->

<div class="modal fade" id="confirmMessageModalNewItem" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<input type="hidden" id="rqstType" />
				<input type="hidden" id="objctId" />
				<input type="hidden" id="objctName" />
				<input type="hidden" id="objctType" />
				<input type="hidden" id="taxPrcnt" />
				<div id="confirmmessagecontNewItem"></div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme" onclick="targetAction();">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Add/Edit tax modal start -->
<div class="modal fade" id="taxAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
	        <h4 class="modal-title" id="myModalLabel">
	        	<span id="taxheadertext"></span>
	        </h4>
	      </div>
	      <div class="modal-body">
	        <div class="form-horizontal">
	        	<div class="form-group">
	        	<input type="hidden" id="taxId" value=""></input><input type="hidden" id="taxType" />
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxCat_label">Tax Category<span class="required_star">*</span></label>

                         			<div class="col-sm-8">
                         				<select id="taxMode" class="form-control">
                         					<option value="NOTAX">NOTAX</option>
                         					<option value="VAT">VAT</option>
                         					<option value="GST">GST</option>
                         					<option value="CGST">CGST</option>
                         					<option value="SGST">SGST</option>
                         					<option value="IGST">IGST</option>
                         					<option value="OTHER">OTHER</option>
                         				</select>
                         			</div>
                     			</div>
	        	<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxName_label">Name <span class="required_star">*</span></label>
                         			<div class="col-sm-8">
                             			<input type="text" id="taxName" class="form-control">
                         			</div>
                     			</div>
                     			<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxPer_label">Percentage <span class="required_star">*</span></label>
                         			<div class="col-sm-8">
                             			<input type="text" id="taxPer" class="form-control">
                         			</div>
                     			</div>
                     			<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxDesc_label">Description <span class="required_star">*</span></label>
                         			<div class="col-sm-8">
                             			<input type="text" id="taxDesc" class="form-control">
                         			</div>
                     			</div>
                     			<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxIsgrp_label">Is Group<span class="required_star">*</span></label>

                         			<div class="col-sm-8">
                         				<select id="taxIsGrp" onchange="makeGroupStat();" class="form-control">
                         					<option value="0">No</option>
                         					<option value="1">Yes</option>
                         				</select>
                         			</div>
                     			</div>
                     			<div id="snglTaxDiv" class="form-group hide">
                         			<table id="singleTaxListTbl" class="table table-bordered table-striped table-condensed-trx table-hover">
                         				<thead>
                         					<tr>
                         						<th></th>
                         						<th>Name</th>
                         						<th>Percentage</th>
                         					</tr>
                         				</thead>
                         				<tbody id="singleTaxList"></tbody>
                         			</table>
                     			</div>
	        </div>
	        <input type="hidden" id="tax_id" value=""></input>
	        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgTax"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
	        <button type="button" id="saveTaxBtn" onclick="javascript:addEditTax()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
	        <button type="button" id="updateTaxBtn" onclick="javascript:addEditTax()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.update" text="Update" /></button>
	      </div>
	    </div>
	  </div>
</div>
<!-- Add/Edit tax modal end -->

<!-- Add/Edit unit modal start -->
<div class="modal fade" id="unitAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertext">Add Unit</span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="unitcode_label"><spring:message code="unit.jsp.tblhdr.code" text="Unit Code" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="unitCodeId" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="unitdesc_label"><spring:message code="unit.jsp.tblhdr.desc" text="Unit Description" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="unitDescId" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="unittype_label"><spring:message code="unit.jsp.tblhdr.typeId" text="Unit Type Id" /><span class="required_star">*</span></label>

						<div class="col-sm-8">
							<select id="unitTypeId" class="form-control">
								<option value="1">Loose Unit</option>
								<option value="2">Packing Unit</option>
							</select>
						</div>

						<!-- <div class="col-sm-8">
                                  			<input type="text" id="unitTypeId" class="form-control">
                              			</div> -->
					</div>
				</div>
				<input type="hidden" id="unit_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgunit"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditUnit()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit unit modal end -->

<!-- Add/Edit brand modal start -->
<div class="modal fade" id="brandAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertextbrand">Add Brand</span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="brand.jsp.tblhdr.name" text="Brand Name" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="brandName" class="form-control">
						</div>
					</div>
				</div>
				<input type="hidden" id="brandId" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgbrand"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditBrand()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit brand modal end -->
<!-- Add/Edit Content modal start -->
<div class="modal fade" id="contentAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertext">Add Content</span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">

					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="content.jsp.name" text="Content Name" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<textarea id="content_name" class="form-control" rows="4"></textarea>
						</div>
					</div>
				</div>
				<input type="hidden" id="content_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgcontent"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditContent()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit Content modal end -->
<!-- Add/Edit Manufacturer modal start -->
<div class="modal fade" id="manufacturerAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertext">Add Manufacturer</span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">

					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="mfcode_label"><spring:message code="manufacturer.jsp.code" text="Manufacturer Code" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="manufctr_code" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="mfname_label"><spring:message code="manufacturer.jsp.name" text="Manufacturer Name" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="manufctr_name" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="mfaddrs_label"><spring:message code="manufacturer.jsp.addrs" text="Address" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<textarea id="addrsmnuf" class="form-control" rows="4"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.phn" text="Contact No." /></label>
						<div class="col-sm-8">
							<input type="text" id="phn" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.fax" text="FAX" /></label>
						<div class="col-sm-8">
							<input type="text" id="fax" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.email" text="Email" /></label>
						<div class="col-sm-8">
							<input type="text" id="email" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.url" text="URL" /></label>
						<div class="col-sm-8">
							<input type="text" id="url" class="form-control" />
						</div>
					</div>
				</div>
				<input type="hidden" id="manufctr_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgmanu"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditManufacturer()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit Manufacturer modal end -->

<!-- Add/Edit group modal start -->
	<div class="modal fade" id="groupAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
		        <h4 class="modal-title" id="myModalLabel">
		        	<span id="headertext">Add Group</span>
		        </h4>
		      </div>
		      <div class="modal-body">
		        <div class="form-horizontal">
		        	<div class="form-group">
                          			<label class="col-sm-4 col-sm-4 control-label" id="grpname_label"><spring:message code="grp.jsp.name" text="Group Name" /> <span class="required_star">*</span></label>
                          			<div class="col-sm-8">
                              			<input type="text" id="grpName" class="form-control">
                          			</div>
                      			</div>
                      			<div class="form-group">
                          			<label class="col-sm-4 col-sm-4 control-label" id="grpdesc_label"><spring:message code="grp.jsp.desc" text="Group Description" /> <span class="required_star">*</span></label>
                          			<div class="col-sm-8">
                              			<input type="text" id="grpDesc" class="form-control">
                          			</div>
                      			</div>
                      		 </div>
		        <input type="hidden" id="grp_id" value=""></input>
		        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgGroup"></div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
		        <button type="button" onclick="javascript:addEditGroup()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
		      </div>
		    </div>
		  </div>
	</div>
<!-- Add/Edit group modal end -->

<!-- Add/Edit Schedule Modal Starts -->

	<div class="modal fade" id="scheduleAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
	        <h4 class="modal-title" id="myModalLabel">
	        	<span id="scheheadertext"></span>
	        </h4>
	      </div>
	      <div class="modal-body">
	        <div class="form-horizontal">
	        	<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="schname_label"><spring:message code="schedule.jsp.scheduleName" text="Schedule Name" /> <span class="required_star">*</span></label>
                         			<div class="col-sm-8">
                             			<input type="text" id="scheduleName" class="form-control">
                         			</div>
                     			</div>
	        </div>
	        <input type="hidden" id="scheduleId" value="">
	        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgSche"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
	        <button type="button" onclick="javascript:addEditSchedule()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
	      </div>
	    </div>
	  </div>
</div>

<!-- Add/Edit Schedule Modal Ends -->


<!-- add/EDIT MODAL  or marketer  start-->


					<div class="modal fade" id="marketAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						        <h4 class="modal-title" id="myModalLabel">
						        	<span id="headertextmarketer"></span>
						        </h4>
						      </div>
						      <div class="modal-body">

								<div class="form-horizontal">

						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="marketercode_label"><spring:message code="marketer.jsp.code" text="Marketer Code" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="marketer_code" class="form-control" placeholder="<spring:message code="marketer.jsp.p_code" text="Marketer Code" />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="marketername_label"><spring:message code="marketer.jsp.name" text="Marketer Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="marketer_name" class="form-control" placeholder="<spring:message code="marketer.jsp.p_name" text="Marketer Name" />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="marketer.jsp.addrs" text="Address" /></label>
							<div class="col-sm-8">
								<textarea  id="marketer_addrs" class="form-control" rows="4" placeholder="<spring:message code="marketer.jsp.p_addrs" text="Enter Address" />" ></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="marketer.jsp.phn" text="Contact No." /></label>
							<div class="col-sm-8">
								<input type="text" id="marketer_phn" class="form-control"  placeholder="<spring:message code="marketer.jsp.p_phn" text="Enter Contact No." />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="marketer.jsp.fax" text="FAX" /></label>
							<div class="col-sm-8">
								<input type="text" id="marketer_fax" class="form-control" placeholder="<spring:message code="marketer.jsp.p_fax" text="Enter FAX No." />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="marketer.jsp.email" text="Email" /></label>
							<div class="col-sm-8">
								<input type="text" id="marketer_email" class="form-control"  placeholder="<spring:message code="marketer.jsp.p_email" text="Enter Email id" />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="marketer.jsp.url" text="URL" /></label>
							<div class="col-sm-8">
								<input type="text" id="marketer_url" class="form-control" placeholder="<spring:message code="marketer.jsp.p_url" text="Enter URL" />" />
							</div>
						</div>
					</div>


						        <input type="hidden" id="marketer_id" value="">
<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgMarketer"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditMarketer()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div>
<!-- add/EDIT MODAL  or marketer  end -->

	<!-- Add/Edit Category Modal Starts -->

					<div class="modal fade" id="catAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						        <h4 class="modal-title" id="myModalLabel">
						        	Add Category
						        </h4>
						      </div>
						      <div class="modal-body">
						        <div class="form-horizontal">
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="catname_label"><spring:message code="cat.jsp.catName" text="Category Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="CatName" class="form-control">
                              			</div>
                          			</div>
						        </div>
						        <input type="hidden" id="CatId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgCat"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditCat()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div>

				<!-- Add/Edit Category Modal Ends -->

<!-- Add/Edit SubCategory Modal Starts -->

					<div class="modal fade" id="subcatAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
                              			<label class="col-sm-4 col-sm-4 control-label"><spring:message code="cat.jsp.catName" text="Category Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              			<select class="form-control-trx" name="catNameList" id="catNameList">

										</select>
                              				<%-- <select id="catNameList" class="form-control">
                              					<option value="0">Select</option>
                              					<c:if test="${!empty allCat}">
                              						<c:forEach items="${allCat}" var="allCat">
                              							<option value="${allCat.id}">${allCat.name}</option>
                              						</c:forEach>
                              					</c:if>
                              				</select> --%>
                              			</div>
                          			</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="subcat.jsp.subcatName" text="SubCategory Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="SubCatName" class="form-control">
                              			</div>
                          			</div>
						        </div>
						        <input type="hidden" id="SubCatId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgSubCat"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditSubCat()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div>

				<!-- Add/Edit SubCategory Modal Ends -->


<script src="${pageContext.request.contextPath }/assets/js/inventory/item/item.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/item/item_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/common/commonnewitem_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/inventory/tax/tax_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";

	$(document).ready(function(){
		//$('#isDiscount').val($("#isDiscounthid").val());
		getSelectedCat();
		//dualstk checking
		var dualStockReq=$("#dualStockRequired").val();
		if(dualStockReq==1){
			$("#lseUnitDiv").removeClass("hide");
			$("#rateondiv").removeClass("hide");
			$("#ratiodiv").removeClass("hide");
		}else{
			$("#lseUnitDiv").addClass("hide");
			$("#rateondiv").addClass("hide");
			$("#ratiodiv").addClass("hide");
		}
		//serial no checking
		var slno=$("#serialNoRequired").val();
		if(slno==1){
			$("#slnosuffreq").removeClass("hide");
			$("#slNoPrefReq").removeClass("hide");
			$("#slnotypeDiv").removeClass("hide");
		}else{
			$("#slnosuffreq").addClass("hide");
			$("#slNoPrefReq").addClass("hide");
			$("#slnotypeDiv").addClass("hide");
		}
		//warranty check

		var warrno=$("#warrantyRequired").val();
		if(warrno==1){
			$("#warrantymonthondiv").removeClass("hide");
			$("#warrantytypeondiv").removeClass("hide");
		}else{
			$("#warrantymonthondiv").addClass("hide");
			$("#warrantytypeondiv").addClass("hide");
		}
		//batchWiseStock req

		var batwistk=$("#batchWiseStock").val();
		if(batwistk==1){
			$("#expreqdiv").removeClass("hide");
		}else{
			$("#expreqdiv").addClass("hide");
		}

		if($("#isLooseSale").val()==1)
		{
			$("#lseSaleChk").attr("checked",true);
			$("#lseUnitDiv").removeClass("hide");
		}
		else
		{
			$("#lseSaleChk").attr("checked",false);
			$("#lseUnitDiv").addClass("hide");
		}

		// is discount trigger

			// for is isdiscount
		$("#isDiscount").change(function(){

			if($("#isDiscount").val()==0)
			{
				$("#max_discount_hide").addClass("hide");
				$("#discount_hide").addClass("hide");

			}

			if($("#isDiscount").val()==1)
			{

				$("#max_discount_hide").removeClass("hide");
				$("#discount_hide").removeClass("hide");
			}
		});
		var item_id=${item_id};

		 if(item_id==0)
			 {
				isDiscountWhenEdit(1);
			 }
		 else
			 {
			 isDiscountWhenEdit(${itemMaster.isDiscount});
			 }





	});


	function showConfirmModal()
	{
		$('#confirmMessageModal').modal('show');
	}
	function showConfirmModalNewItem() {
		$('#confirmMessageModalNewItem').modal('show');
	}
	function showItemDelModal(id)
	{
		   document.getElementById('confirmId').value=id;
		   $('#confirmModal').modal('show');
	}

	$(function() {

	//	function getSubCatEdit(){
// 			var categoryIdEdit=$("#categoryIdEdit").val();
			var categoryIdEdit=$("#catSelect").val();
			var commResultsetMapperObj = {};
			commResultsetMapperObj.catId = categoryIdEdit;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getCatBySubcat.htm", commResultsetMapperObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var res = JSON.parse(response);
				var str = "<option value='0'>Select....</option>";
				$.each(res, function(i) {
					str = str + "<option value='" + res[i].subCategoryId + "'>"
							+ res[i].subCategoryName + "</option>";
				});
				$("#selsubCategoryIdEdit").html(str);
				$("#selsubCategoryIdEdit").val($("#subCategoryIdEdit").val());
			});
		//}

		$("#itemBrand").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/brand/autocompleteitembrand.htm",
						type : "GET",
						data : {
							tagName : request.term
						},
						dataType : "json",
						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.name,
									itemCode : v.id,
									//tagCode : v.tagCode
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				$("#brandId").val(ui.item.itemCode);
				// $("#itemCode").val(ui.item.itemCode);
				//$("#autocompleteItemId").val(ui.item.itemCode);
				/*var disc = 0.0;
				if (ui.item.items.promotionFlag == 'Y')
					disc = ui.item.items.promotionValue;
				additemtoOrder(ui.item.items.id, ui.item.items.name, ui.item.items.price, disc, ui.item.items.vat, ui.item.items.serviceTax, ui.item.items.promotionFlag, ui.item.items.promotionValue);*/

			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},

		});

		$("#itemManufacturer").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/manufacturer/autocompleteitemmanufacturer.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.name,
									itemCode : v.id,
									//tagCode : v.tagCode
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				$("#manufacturerId").val(ui.item.itemCode);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#itemContent").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/content/autocompleteitemcontent.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.name,
									itemCode : v.id,
									//tagCode : v.tagCode
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				$("#contentId").val(ui.item.itemCode);
				$("#content_id").val(ui.item.itemCode);
				$("#content_Dets").val(ui.item.label);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#itemLooseUnit").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/invsetup/autocompleteitemlooseunit.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.code,
									itemCode : v.id,
									//tagCode : v.tagCode
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				$("#looseUnitId").val(ui.item.itemCode);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#itemPackingUnit").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/invsetup/autocompleteitempackingunit.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.code,
									itemCode : v.id,
									//tagCode : v.tagCode
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				//$("#itemid").val(ui.item.itemCode);
				$("#packUnitId").val(ui.item.itemCode);
				$("#reorderLevelUnitId").val(ui.item.itemCode);
				$("#itemreorderUnitId").val(ui.item.label);
				$("#looseUnitId").val(ui.item.itemCode);
				$("#itemLooseUnit").val(ui.item.label);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#itemreorderUnitId").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/invsetup/autocompleteitempackingunit.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.code,
									itemCode : v.id,
									//tagCode : v.tagCode
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				$("#reorderLevelUnitId").val(ui.item.itemCode);

			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#purchaseTax").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/tax/gettaxlistforautocomplete.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								console.log("v="+v);
								return {
									label : v.taxName,
									itemCode : v.taxId,
									//tagCode : v.tagCode
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				$("#purchaseTaxId").val(ui.item.itemCode);
				$("#purchaseTaxPerc").val(ui.item.items.percentage);
				$("#saleTax").val(ui.item.label);
				$("#saleTaxId").val(ui.item.itemCode);
				$("#saleTaxPerc").val(ui.item.items.percentage);

			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#saleTax").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/tax/gettaxlistforautocomplete.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.taxName,
									itemCode : v.taxId,
									//tagCode : v.tagCode
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				$("#saleTaxId").val(ui.item.itemCode);
				$("#saleTaxPerc").val(ui.item.items.percentage);

			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});


		/*   add by siddik on 11_02_2017*/


		/* this is for Marketer  autocomplete search
		*/
		$("#marketed_by_name").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/item/autocompletemarketer.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.name,
									itemCode : v.id,
									//tagCode : v.tagCode
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				$("#marketed_by_id").val(ui.item.itemCode);
				//$("#content_id").val(ui.item.itemCode);
				$("#marketed_by_name").val(ui.item.label);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});





		var currentDate = new Date();
		$('#launchdate').datepicker({
			format : 'yyyy-mm-dd',
			endDate : currentDate,
			autoclose : true,
		});

		$('#discontinuedate').datepicker({
			format : 'yyyy-mm-dd',
			endDate : currentDate,
			autoclose : true,
		});



	});


	/*

open marketer modal
	*/
	function openAddEditMarketer()
	{
		$('#marketer_addrs').val("");
		$('#alertMsgMarketer').text("");
		$("#marketAddEditModal").find('input:text').val('');
		$("#marketAddEditModal").find('input:hidden').val('');

		$("#headertextmarketer").text(getItemText.MarketerheaderTextAdd);
		$("#marketAddEditModal").modal("show");


	}


	function isDiscountWhenEdit(a)
	{
		   $("#isDiscount")
	       .val(a)
	       .trigger('change');
	}
	function getSubCatEdit(){
//			var categoryIdEdit=$("#categoryIdEdit").val();
		var categoryIdEdit=$("#catSelect").val();
		var commResultsetMapperObj = {};
		commResultsetMapperObj.catId = categoryIdEdit;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getCatBySubcat.htm", commResultsetMapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			var res = JSON.parse(response);
			var str = "<option value='0'>Select....</option>";
			$.each(res, function(i) {
				str = str + "<option value='" + res[i].subCategoryId + "'>"
						+ res[i].subCategoryName + "</option>";
			});
			$("#selsubCategoryIdEdit").html(str);
// 			$("#selsubCategoryIdEdit").val($("#subCategoryIdEdit").val());
		});
	}

	function getSelectedCat(){
		var selcatid=$("#catSelect").val();
		var commResultsetMapperObj = {};
		commResultsetMapperObj.catId = selcatid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getCatBySubcat.htm", commResultsetMapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			var res = JSON.parse(response);
			var str = "<option value='0'>Select....</option>";
			$.each(res, function(i) {
				str = str + "<option value='" + res[i].subCategoryId + "'>"
						+ res[i].subCategoryName + "</option>";
			});
			$("#subCatSelect").html(str);

		});
	}

</script>