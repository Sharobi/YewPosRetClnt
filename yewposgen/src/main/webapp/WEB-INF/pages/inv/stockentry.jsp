<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; width: 300px; max-height: 250px; word-break: break-all;
}
-->
</style>
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p>
				<%-- <spring:message code="stckentry.jsp.title" text="Stock Entry..." /> --%>
			</p>
			<div class="col-md-5 col-sm-5"></div>
			<div class="col-md-2 col-sm-2">
				<select class="form-control-trx" name="qryCondition" id="entrySelect">
					<option value="0"><spring:message code="stckentry.jsp.entrytype" text="Select Entry Type" /></option>
					<option value="1"><spring:message code="stckentry.jsp.manual" text="Manual" /></option>
					<option value="2" class=""><spring:message code="stckentry.jsp.excel" text="Excel Upload" /></option>
				</select>
			</div>
			<div class="col-md-5 col-sm-5 hide" id="entryDtDiv">
			<input type="hidden" id="retailerProfitPrcnt" value="${retailerProfitPrcnt}" />
			<input type="hidden" id="wholesalerProfitPrcnt" value="${wholesaleProfitPrcnt}" />
			<input type="hidden" id="isMrpEnableFlag" value="${sessionScope.sesloggedinUser.isMrpEnable}" />
				<table>
					<tr align="center" >
						<td><spring:message code="stckentry.jsp.entrydt" text="Entry Date" /></td>
						<td style="padding: 0 1px;">
							<div class="input-group">
								<fmt:parseDate value="${sessionScope.sesloggedinUser.startDate}" var="parsedStrtDate" pattern="yyyy-MM-dd" />
								<input type="hidden" id="sessionStrtdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedStrtDate}" />" /> <input type="hidden" id="todate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />" /> <input type="text" readonly="readonly" class="form-control-trx" id="entrydate" name="entryDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div>
						</td>
						<td style="padding-left: 9%;">
							<button class="btn btn-primary btn-sm" type="button" onclick="clearHeaderDiv()">
											<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
										</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="col-lg-12 col-md-12 col-sm-12">
			<input type="hidden" id="seslocationid" value="${sessionScope.sesloggedinUser.locationId}" />
				<p></p>
			</div>
			<div class="hide" id="manual_div">
				<!-- Manual Entry -->

				<div>
					<div class="panel-trx panel-default">
						<div class="panel-body-trx" id="header_div">
							<table>
								<tr align="center" >
									<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" />

								<input type="hidden" id="item_taxTypeId" value="0">
									</td>
									<td colspan="2"><spring:message code="cmn.jsp.barcode" text="Barcode" /></td>
									<td>
										<span id="newItemLabel"><spring:message code="cmn.jsp.capitalize.new" text="New" /></span>
										<span id="editItemLabel" class="hide"><spring:message code="cmn.jsp.btn.edit" text="Edit" /></span>
									</td>
									<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
									<td id="batch_label" class="hide"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
									<td id="mfd_label" class="hide"><spring:message code="purinvdet.jsp.mfd" text="MFD" /></td>
									<td id="exp_month_label" class="hide"><spring:message code="purinvdet.jsp.expmonth" text="Exp Month" /></td>
									<td id="exp_label" class="hide"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>

									<td id="lqty_label" class="hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
									<td id="ratio_label" class="hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
									<td id="mrp_label" class="hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
									<td id="rate_label"><spring:message code="purinvdet.jsp.rate" text="Rate" /></td>
									<td id="salerate_label" class="hide"><spring:message code="purinvdet.jsp.salerate" text="Sale Rate" /></td>
									<td class="hide"><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></td>
									<td class="hide"><spring:message code="purinvdet.jsp.tax" text="Tax" /></td>
									<td class="hide"><spring:message code="purinvdet.jsp.vatprcnt" text="Vat%" /></td>
									<td class="hide"><spring:message code="purinvdet.jsp.vat" text="Vat" /></td>
									<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>
								</tr>
								<tr>
									<td width="15%" style="padding: 0 1px;"><input type="hidden" id="itemid" /><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"></td>
									<td style="padding: 0 1px;" colspan="2"><input class="form-control-trx" type="text" id="purbarcode" placeholder="Scan Barcode"><input type="hidden" id="purHsnCode" /></td>
									<td style="padding: 0 1px;">
										<button class="btn btn-primary btn-sm" type="button" id="addNewItemBtn" style="padding: 4px 10px;" onclick="addNewItem()">
											<i class="fa fa-plus"></i> ITEM
										</button>
										<button class="btn btn-info btn-sm hide" id="editNewItemBtn" type="button" style="padding: 4px 10px;" onclick="addNewItem()">
											<i class="fa fa-pencil"></i> ITEM
										</button>
									</td>

									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="pqty" onblur="javascript:openSerialModal($(this).val())"  onkeydown="numcheck(event)" ><input type="hidden" id="punitid" value="0"><input type="hidden" id="serialnumberreq" value="0" /></td>
									<td style="padding: 0 1px;" id="batch_no_td" class="hide"><input class="form-control-trx" type="text" id="batch_no"><input type="hidden" id="item_batchWiseStock_req" value="0" /><input type="hidden" id="item_expiryDateRequired_req" value="0" /><input type="hidden" id="item_expiryMonthRequired_req" value="0" /><input type="hidden" id="item_dualStockRequired_req" value="0" /><input type="hidden" id="item_mrpRequired_req" value="0" /><input type="hidden" id="item_locationRequired_req" value="0" /><input type="hidden" id="item_priceListRequired_req" value="0" /></td>
									<td style="padding: 0 1px;width: 8%;" id="mfd_val" class="hide"><input type="text" class="form-control-trx" id="mfd" name="mfd" onblur="calculateExpiry()" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"></td>
									<td style="padding: 0 1px;" id="expmonth_val" class="hide"><input class="form-control-trx" type="text" id="expmonth" value="0" onblur="calculateExpiry()"></td>

									<td style="padding: 0 1px; width: 8%;" id="exp_td" class="hide">
									<c:choose>
										<c:when test="${isWholesale==1}">
											<div class="input-group">
											<input type="text" class="form-control-trx" placeholder="MM/YY" id="exp" name="expDate">
											</div>
										</c:when>
										<c:otherwise>
											<div class="input-group">
												<input type="text" class="form-control-trx hide" id="exppre" class="hide" name="expDate" placeholder="MM/YY" maxlength="5" onchange="expiryCalculation($(this).val(),$('#purorderid').val(),$('#itemid').val());">
												<input type="text" class="form-control-trx" id="exp" onblur="calExpMonth()" name="expDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
											</div>
										</c:otherwise>
									</c:choose>
									
								</td>

									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="lqty"></td>
									<td style="padding: 0 1px;" id="ratio_val" class="hide"><input class="form-control-trx" type="text" id="ratio"></td>
									<td style="padding: 0 1px;" id="mrp_td" class="hide">
					<input class="form-control-trx" type="text" id="mrp" onkeydown="numcheck(event)"></td>
									<td style="padding: 0 1px;">
							<input class="form-control-trx" type="text" id="rate" onkeydown="numcheck(event)"></td>
									<td style="padding: 0 1px;" id="sale_rate_val" class="hide"><input class="form-control-trx" type="text" id="sale_rate"  onkeydown="numcheck(event)"></td>

									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vatprcnt" value="${sessionScope.sesloggedinUser.vatPer}"></td>
									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>

									<%-- <c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==0}">
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt" value="${sessionScope.sesloggedinUser.taxPer}"></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vatprcnt" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
									</c:if>
									<c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==1}">
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vatprcnt" value="${sessionScope.sesloggedinUser.vatPer}"></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
									</c:if> --%>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="total" tabindex="-1" readonly></td>
									<td>
										<button class="btn btn-success btn-sm" type="button" id="stock_add_btn">
											<spring:message code="cmn.jsp.addcaps" text="ADD" />
										</button>
										<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" id="edit_btn">
											<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
										</button>
									</td>
								</tr>
								<tr align="center" >
								<td><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
								<td id="location_label" class="hide"><spring:message code="purinvdet.jsp.loc" text="Location" /></td>
								<td><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></td>
								<td><spring:message code="purinvdet.jsp.tax" text="Tax" /></td>


								</tr>
								<tr>
								<td width="9%" style="padding: 0 1px;"><select class="form-control-trx" name="distributorId" id="seldistributor">
											<option value="0">Select</option>
											<c:if test="${!empty allVendors}">
												<c:forEach items="${allVendors}" var="allVendor">
													<option value="${allVendor.id}">${allVendor.name}</option>
												</c:forEach>
											</c:if>
											<option value="-1">Add Vendor</option>
									</select></td>
								<td width="9%" style="padding: 0 1px;" id="locationval_td" class="hide">
							<select class="form-control-trx" name="location" id="location">
									<option value="0">Select</option>
									<c:if test="${!empty locationDTOs}">
										<c:forEach items="${locationDTOs}" var="loc">
											<option value="${loc.id}">${loc.name}</option>
										</c:forEach>
									</c:if>
							</select>
							</td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt" value="${sessionScope.sesloggedinUser.taxPer}" tabindex="-1" readonly><input type="hidden" id="purTaxId" /></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly><input type="hidden" id="purtaxmode" /><input type="hidden" id="purisgrptax" /></td>

							<td class="hide"><input type="hidden" id="id" /></td>
									<td class="hide"><input type="hidden" id="tblrow_id" /><input type="hidden" id="disc" value="0.0" /><input type="hidden" id="free" value="0" /></td>
<!-- 									<td colspan="2"></td> -->
									<td colspan="8">
										<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
									</td>
								</tr>
								<tr>
								<%--	<td class="hide"><input type="hidden" id="id" /></td>
									<td class="hide"><input type="hidden" id="tblrow_id" /><input type="hidden" id="disc" value="0.0" /><input type="hidden" id="free" value="0" /></td>
									<td colspan="2"></td>
									<td colspan="8">
										<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
									</td>
									 <td>
										<button class="btn btn-success btn-sm" type="button" id="add_btn">
											<spring:message code="cmn.jsp.addcaps" text="ADD" />
										</button>
									</td> --%>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<div>
					<div style="max-height: 350px; height: 350px; overflow: auto;">
						<table id="stockitems" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
							<thead>
								<tr>
									<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
									<th><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></th>
									<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
									<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
									<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
									<th class="numeric hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
									<th class="numeric hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
									<%-- <th class="numeric hide"><spring:message code="purinvdet.jsp.free" text="Free" /></th> --%>
									<th class="numeric hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
									<th class="numeric"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
									<th class="numeric"><spring:message code="purinvdet.jsp.salerate" text="Sale Rate" /></th>
									<th class="numeric"><spring:message code="purinvdet.jsp.tax" text="Tax" /></th>
									<th class="numeric hide"><spring:message code="purinvdet.jsp.vat" text="Vat" /></th>
									<th class="numeric"><spring:message code="purinvdet.jsp.grossamnt" text="GrossAmt" /></th>
									<th class="numeric"><spring:message code="purinvdet.jsp.netamnt" text="NetAmt" /></th>
									<!-- <th class="numeric">Ed%</th> -->
									<%-- <th class="numeric hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></th>
			                            <!-- <th class="numeric">Tax%</th> -->
			                            <!-- <th class="numeric">Vat%</th> -->
			                            <!-- <th class="numeric">D%</th> -->
			                            <th class="numeric hide"><spring:message code="purinvdet.jsp.disc" text="Disc" /></th>
			                            <th class="numeric hide" ><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" /></th> --%>
									<!-- <th class="numeric">Ma%</th> -->
									<th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					<p></p>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					<table style="float: right;">
						<tr>
							<td style="padding-top: 4px; padding-right: 4px;">
								<button style="padding: 5px 8px;" class="btn btn-success btn-sm" type="button" onclick="saveStockManual();">
									<spring:message code="cmn.jsp.btn.savecaps" text="SAVE" />
								</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-md-12 col-sm-12 hide" id="excel_div">
				<!-- Excel Upload -->
				<form:form action="${pageContext.request.contextPath }/stock/uploadstockexcel.htm" id="stock_upload_form" modelAttribute="commonResultSetMapper" method="post" enctype="multipart/form-data">
					<table style="margin-top: 4%;">
						<tr>
							<td colspan="2" width="20%" class="font-bold"><spring:message code="cmn.jsp.upload" text="Upload" /></td>
							<td colspan="3"><input class="form-control" type="file" name="fileUpload" id="fileUpload" accept=".xls,.xlsx" /><input type="hidden" id="excelEntryDate" /></td>
							<!-- <td colspan="3"><input class="form-control" type="file" name="fileUpload" id="fileUpload" accept=".xls" /><input type="hidden" id="excelEntryDate" /></td> -->
							<td width="7%"></td>
							<td colspan="2"><input type="button" id="stock_upload" onclick="uploadStock();" style="padding: 5px 8px; text-transform: uppercase;" class="btn btn-success btn-sm" value="<spring:message code="cmn.jsp.btn.submit" text="Submit" />" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</section>
<!--/wrapper -->

<!-- Item exists start -->

<div class="modal fade" id="itemExistsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="itemmstr.jsp.exist.error" text="Item already exist, please try other." />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="ExistsOk()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Item exists end -->


<!-- Add vendor modal start -->
<div class="modal fade" id="vendorAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
							<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="vendor.jsp.name" text="Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="name" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="vendor.jsp.addrs" text="Address" /> <!-- <span class="required_star">*</span> --></label>
							<div class="col-sm-8">
								<textarea id="addrs" class="form-control" rows="2"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="vendor.jsp.pin" text="Pin" /> <!-- <span class="required_star">*</span> --></label>
							<div class="col-sm-8">
								<input type="text" id="pin" class="form-control" />
							</div>
						</div>
						
				 	<!-- this for country name  -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="dctr.jsp.country" text="Country" /> </label>
							<div class="col-sm-8">
						<!-- 		<div class="input-group"> -->
									<select class="form-control-trx" name="country" id="cntry"  onchange="getStateByCountry();">
										<!-- <option value="0">Select...</option> -->
										<option value="${userinformation.countryId}"></option>
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
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="accsetup.jsp.state" text="State" /> </label>
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
							<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="accsetup.jsp.city" text="City" /> </label>
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
							<label class="col-sm-4 col-sm-4 control-label" id="discount_label"><spring:message code="vendor.jsp.discount" text="Discount(%)" /></label>
							<div class="col-sm-8">
								<input type="text" id="discount" class="form-control" />
							</div>
						</div>
							 <div class="form-group ">
								<label class="col-sm-4 col-sm-4 control-label" id="dueDays_label"><spring:message code="vendor.jsp.duedays" text="Due days" /></label>
								<div class="col-sm-8">
									<input type="text" id="dueDays" class="form-control" placeholder="<spring:message code="vendor.jsp.duedays" text="Due days" />" />
								</div>
							</div>
								<div class="form-group ">
								<label class="col-sm-4 col-sm-4 control-label" id="duePer_label"><spring:message code="vendor.jsp.dueperc" text="Due(%)" /></label>
								<div class="col-sm-8">
									<input type="text" id="duePer" class="form-control" placeholder="Due Percentage" />
								</div>
							</div>
					</div>


					<div class="col-sm-6 col-sm-6 ">
							<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="phn1_label"><spring:message code="vendor.jsp.phn1" text="Contact No. 1" /> <!-- <span class="required_star">*</span> --></label>
							<div class="col-sm-8">
								<input type="text" id="phn1" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.phn2" text="Contact No. 2" /></label>
							<div class="col-sm-8">
								<input type="text" id="phn2" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.fax" text="FAX" /></label>
							<div class="col-sm-8">
								<input type="text" id="fax" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.email" text="Email" /></label>
							<div class="col-sm-8">
								<input type="text" id="email" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.cntctPerson" text="Contact Person" /></label>
							<div class="col-sm-8">
								<input type="text" id="cntct_person" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="rgstratn_label"><spring:message code="vendor.jsp.rgstratn" text="Registration No." /><!--  <span class="required_star">*</span> --></label>
							<div class="col-sm-8">
								<input type="text" id="rgstratn" class="form-control" rows="4" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.opbalance" text="Opening Balance" /></label>
							<div class="col-sm-8">
								<input type="text" id="opbalance" class="form-control" rows="4" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.credtlimit" text="Credit Limit" /></label>
							<div class="col-sm-8">
								<input type="text" id="credt_limit" class="form-control" rows="4" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="licenceNo_label"><spring:message code="vendor.jsp.licenceno" text="Licence No" /><!--  <span class="required_star">*</span> --></label>
							<div class="col-sm-8">
								<input type="text" id="licenceNo" class="form-control"  placeholder="<spring:message code="cmn.jsp.egdlno" text="E.g. D.L No." />" />
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="vendor_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="vendorAddAlertMsg"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" id="vendorCloseBtn" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditVendor()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add vendor modal end -->
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

<div class="modal fade" id="openSerialModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
<%-- 					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message> --%>
                      Add Serial No
				</h4>
			</div>
			<div class="modal-body">
			<div id='TextBoxesGroup'>
			<div id="TextBoxDiv_1">
		      <label>SL No #1 : </label><input type='textbox' id='textbox1' onblur="getSLValue($(this).val())">
		      <input type="hidden" id="allVal" />
		      <input type="hidden" id="clk" value="0"/>
		      <input type="hidden" id="qtysl" value="0"/>
	        </div>
	        <div id="TextBoxDiv_2" class="hide">
		      <label>SL No #2 : </label><input type='textbox' id='textbox2' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_3" class="hide">
		      <label>SL No #3 : </label><input type='textbox' id='textbox3' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_4" class="hide">
		      <label>SL No #4 : </label><input type='textbox' id='textbox4' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_5" class="hide">
		      <label>SL No #5 : </label><input type='textbox' id='textbox5' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_6" class="hide">
		      <label>SL No #6 : </label><input type='textbox' id='textbox6' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_7" class="hide">
		      <label>SL No #7 : </label><input type='textbox' id='textbox7' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_8" class="hide">
		      <label>SL No #8 : </label><input type='textbox' id='textbox8' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_9" class="hide">
		      <label>SL No #9 : </label><input type='textbox' id='textbox9' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_10" class="hide">
		      <label>SL No #10 : </label><input type='textbox' id='textbox10' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_11" class="hide">
		      <label>SL No #11 : </label><input type='textbox' id='textbox11' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_12" class="hide">
		      <label>SL No #12 : </label><input type='textbox' id='textbox12' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_13" class="hide">
		      <label>SL No #13 : </label><input type='textbox' id='textbox13' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_14" class="hide">
		      <label>SL No #14 : </label><input type='textbox' id='textbox14' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_15" class="hide">
		      <label>SL No #15 : </label><input type='textbox' id='textbox15' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_16" class="hide">
		      <label>SL No #16 : </label><input type='textbox' id='textbox16' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_17" class="hide">
		      <label>SL No #17 : </label><input type='textbox' id='textbox17' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_18" class="hide">
		      <label>SL No #18 : </label><input type='textbox' id='textbox18' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_19" class="hide">
		      <label>SL No #19 : </label><input type='textbox' id='textbox19' onblur="getSLValue($(this).val())">
	        </div>
	         <div id="TextBoxDiv_20" class="hide">
		      <label>SL No #20 : </label><input type='textbox' id='textbox20' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_21" class="hide">
		      <label>SL No #21 : </label><input type='textbox' id='textbox21' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_22" class="hide">
		      <label>SL No #22 : </label><input type='textbox' id='textbox22' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_23" class="hide">
		      <label>SL No #23 : </label><input type='textbox' id='textbox23' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_24" class="hide">
		      <label>SL No #24 : </label><input type='textbox' id='textbox24' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_25" class="hide">
		      <label>SL No #25 : </label><input type='textbox' id='textbox25' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_26" class="hide">
		      <label>SL No #26 : </label><input type='textbox' id='textbox26' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_27" class="hide">
		      <label>SL No #27 : </label><input type='textbox' id='textbox27' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_28" class="hide">
		      <label>SL No #28 : </label><input type='textbox' id='textbox28' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_29" class="hide">
		      <label>SL No #29 : </label><input type='textbox' id='textbox29' onblur="getSLValue($(this).val())">
	        </div>
	        <div id="TextBoxDiv_30" class="hide">
		      <label>SL No #30 : </label><input type='textbox' id='textbox30' onblur="getSLValue($(this).val())">
	        </div>
			</div>
			<div id="slnoduptext" class="hide">Duplicate serial numbers not allow : <span id="slnodupval"></span></div>
			<div id="blankslno"></div>
			</div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-default" onclick="closeSlNo()">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="openSlNo()" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>

			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/pages/common/commonnewitemadd.jsp" />
<script src="${pageContext.request.contextPath }/assets/js/inventory/stock/stockentry.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/stock/stock_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	var storeId = "${storeId}";
	var cmpnyId = "${cmpnyId}";
	var createdBy = "${createdBy}";
	var finyrId = "${finyrId}";
	var fileUploadMsg = "${msg}";
	var isWholesale=${isWholesale};
	/* function addNewItem(){
		$('#itemmasterModal').modal('show');
	} */
	$(document).ready(function() {
		var seslocid=$("#seslocationid").val();
		$("#location").val(seslocid);
		/* $("#exp").keyup(function() {
			if ($(this).val().length == 2) {
				if ($(this).val() <= 12 && $(this).val() > 0) {
					$(this).val($(this).val() + "/");
				} else {
					$(this).val("");
				}

			}
		}); */

		if (fileUploadMsg == "1") {
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.fileSucUploaded;
			$("#confirmval").val(0);
			showConfirmModal();
		} else if (fileUploadMsg == "0") {
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.fileNotUploaded;
			$("#confirmval").val(2);
			showConfirmModal();
		} else {
		}

		var currentDate = new Date();
		var startDateFrom = $("#sessionStrtdate").val();
		$('#entrydate').datepicker({
			format : 'yyyy-mm-dd',
			startDate : startDateFrom,
			endDate : currentDate,
			autoclose : true,
		});

	});
	var countryId = "${sessionScope.sesloggedinUser.countryId}";
	var stateId = "${sessionScope.sesloggedinUser.stateId}";
</script>
