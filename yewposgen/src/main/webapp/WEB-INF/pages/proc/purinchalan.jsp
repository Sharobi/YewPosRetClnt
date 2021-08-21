<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; max-height: 250px; width: 300px; word-break: break-all; z-index: 2150000000 !important;
}
-->
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />

<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter1">
			<div class="panel-trx panel-default">


				<!-- Confirm Print Purchase Modal Starts -->

				<div class="modal fade" id="confirmPrintPurchaseModal" style="text-align: center;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
								<h4 class="modal-title" id="myModalLabel">
									<spring:message code="footer.jsp.alert" text="Alert!" />
								</h4>
							</div>
							<div class="modal-body">
								<div id="confirprintmmessagecont">
									<span id="purAddEditMsg"> <%-- <spring:message code="purinv.jsp.addsucmsg" text="Purchase Invoice successfully added!" /> --%>
									</span> <br>
									<%-- <label class="col-sm-4 col-sm-4 control-label"><spring:message code="purinv.jsp.printpur" text="Print Purchase invoice" /> : </label> --%>
									<div class="col-sm-12 hide" style="text-align: center; font-weight: 700;">
										<label class="checkbox-inline"> <spring:message code="purinv.jsp.printpur" text="Print Purchase invoice" /> : <input type="checkbox" id="printPurchase" name="printPurchase" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
										</label>

									</div>
								</div>
								<input type="hidden" id="confirmvalfrsaveupdate" value="">
							</div>
							<div class="modal-footer" style="border-top: 0px;">
								<button type="button" onclick="targetActionPur()" data-dismiss="modal" class="btn btn-theme">
									<spring:message code="footer.jsp.btn.ok" text="OK" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Confirm Print Purchase Modal ends -->


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



				<!-- Footer error modal start -->

				<div class="modal fade" id="footerErrorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
								<h4 class="modal-title" id="myModalLabel">
									<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
								</h4>
							</div>
							<div class="modal-body">
								<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgforSave"></div>
							</div>
							<div class="modal-footer">
								<button type="button" onclick="errSpclDiscOk()" data-dismiss="modal" class="btn btn-theme">
									<spring:message code="footer.jsp.btn.ok" text="OK" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Footer error modal end -->


				<div class="panel-body-trx">
				<input type="hidden" id="retailerProfitPrcnt" value="${retailerProfitPrcnt}" />
				<input type="hidden" id="wholesalerProfitPrcnt" value="${wholesaleProfitPrcnt}" />

				<input type="hidden" id="expalertrequiremnt" value="${expalertrequiremnt}" />
				<input type="hidden" id="seslocationid" value="${sessionScope.sesloggedinUser.locationId}" />
				<input type="hidden" id="purqs" value="${menuByUserDTO.qs}" />
				<input type="hidden" id="isMrpEnableFlag" value="${sessionScope.sesloggedinUser.isMrpEnable}" />
					<table>
						<tr align="center" >
							<td><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></td>
							<td id="duedate_label"><spring:message code="purorder.jsp.duedt" text="Due Date" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
							<td colspan="4"><spring:message code="purinvdet.jsp.ordrno" text="Ord No" /></td>
							<td id="bill_label"><spring:message code="purinvdet.jsp.billno" text="Bill No" /></td>
							<td><spring:message code="purinvdet.jsp.billdate" text="Bill Date" /></td>
							<td width="20%" id="vendor_label"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
							<td><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></td>
							<td width="9%" class="hide"><spring:message code="purinvdet.jsp.mode" text="Mode" /></td>
						</tr>
						<tr>
							<td style="padding: 0 1px;">
								<div class="input-group">
									<input type="text" class="form-control-trx" id="invdt" name="invDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
								</div>
							</td>
							<td style="padding: 0 1px;">
								<div class="input-group">
									<input type="text" class="form-control-trx" id="duedt" name="dueDate" >
									<!--value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"-->
									<input type="hidden" id="noOfDue" value="${noOfDue}" />
								</div>
							</td>
							<td width="8%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" value="PI/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="purOrderDoc" value="${poDefaultPrefix}/" size="4" readonly></td>
									<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="purOrderFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
									<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="purOrderSlash" value="/" readonly></td>
									<td style="padding: 0 1px;" width="16%">
										<div class="input-group">
										<input class="form-control-trx" type="text" id="purorderno" value="" size="14">
										<div class="input-group-addon" onclick="veiwPurOrderDet(document.getElementById('purorderno').value,document.getElementById('purOrderFinyr').value)">
											<span class="fa fa-search" ></span>
										</div>
									</div>
									</td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="billno" placeholder="Enter Bill number" readonly="readonly"></td>
							<td style="padding: 0 1px;">
								<div class="input-group">
									<input type="text" class="form-control-trx" id="billDate" name="billDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
								</div>
							</td>
							<td style="padding: 0 1px;"><select class="form-control-trx" name="distributorId" id="seldistributor" onchange="getvendordisval()">
									<option value="0">Select</option>
									<c:if test="${!empty allVendors}">
										<c:forEach items="${allVendors}" var="allVendor">
											<option value="${allVendor.id}_${allVendor.discount}_${allVendor.dueDays}">${allVendor.name}</option>
										</c:forEach>
									</c:if>
									<option value="-1">Add Vendor</option>
							</select></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" readonly="readonly" id="vendordis"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text"></td>
							<td class="hide"><input class="form-control-trx" type="hidden" id="vendorchnge1"></td>
							<td style="padding: 0 1px;">
								<select class="form-control-trx" id="slctMode" onchange="chngeMode();">
									<option value="P">Purchase</option>
									<!-- <option value="R">Return</option> -->
								</select>
							</td>
							<td style="padding: 0 1px;" class="hide"><button class="btn btn-info" id="" type="button" style="padding: 4px 10px;" onclick="purInvUsingExcel()">
									<i class="fa fa-file-excel-o"></i> <spring:message code="purinv.jsp.importexcel" text="Import" />
								</button></td>
							<td colspan="2">
								<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
							</td>

						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter2">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx" id="header_div">
					<table id="header_pur_tbl">
						<tr align="center" >
							<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
							<td colspan="2"><spring:message code="cmn.jsp.barcode" text="Barcode" /></td>
							<td><span id="newItemLabel"><spring:message code="cmn.jsp.capitalize.new" text="New" /></span> <span id="editItemLabel" class="hide"><spring:message code="cmn.jsp.btn.edit" text="Edit" /></span></td>
							<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
							<td id="batch_label" class="hide"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
							<td id="mfd_label" class="hide"><spring:message code="purinvdet.jsp.mfd" text="MFD" /></td>
							<td id="exp_month_label" class="hide"><spring:message code="purinvdet.jsp.expmonth" text="Exp Month" /></td>
							<td id="exp_label" class="hide"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>
							<td id="ratio_label" class="hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
							<td id="mrp_label" class="hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
							<td id="rate_label"><spring:message code="purinvdet.jsp.rate" text="Rate" /></td>
							<td id="salerate_label" class="hide"><spring:message code="purinvdet.jsp.salerate" text="Sale Rate" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.ma" text="Ma%" /></td>

							<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>
						</tr>
						<tr>
							<td width="20%" style="padding: 0 1px;"><input type="hidden" id="itemid" /><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"></td>
							<td style="padding: 0 1px;" colspan="2"><input class="form-control-trx" type="text" id="purbarcode" placeholder="Scan Barcode"><input type="hidden" id="purHsnCode" /></td>
							<td style="padding: 0 1px;">
								<button class="btn btn-primary btn-sm" id="addNewItemBtn" type="button" style="padding: 4px 10px;" onclick="addNewItem()">
									<i class="fa fa-plus"></i> ITEM
								</button>
								<button class="btn btn-info btn-sm hide" id="editNewItemBtn" type="button" style="padding: 4px 10px;" onclick="addNewItem()">
									<i class="fa fa-pencil"></i> ITEM
								</button>
							</td>
							<td style="padding: 0 1px;"><input class="form-control-trx" onkeydown="numcheck(event)" placeholder="Enter Quantity "  type="text" id="pqty" onclick="" onblur="javascript:openSerialModal(Number($(this).val())+Number(Math.floor($('#free').val())))"><input type="hidden" id="punitid" /> <input type="hidden" id="serialnumberreq" value="0" /> <input type="hidden" id="oldpqty" value="0" /></td>
							<td style="padding: 0 1px;" id="batch_no_td" class="hide"><input class="form-control-trx" type="text" placeholder="Enter Batch" id="batch_no"><input type="hidden" id="purorderid" value="0" /><input type="hidden" id="item_batchWiseStock_req" value="0" /><input type="hidden" id="item_expiryDateRequired_req" value="0" /><input type="hidden" id="item_expiryMonthRequired_req" value="0" /><input type="hidden" id="item_dualStockRequired_req" value="0" /><input type="hidden" id="item_mrpRequired_req" value="0" /><input type="hidden" id="item_locationRequired_req" value="0" /><input type="hidden" id="item_priceListRequired_req" value="0" /></td>
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
							<td style="padding: 0 1px;" id="ratio_val" class="hide"><input class="form-control-trx" type="text" id="ratio"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="lqty" tabindex="-1" readonly></td>


							<td style="padding: 0 1px;" id="mrp_td" class="hide"><input class="form-control-trx" type="text" id="mrp" onkeydown="numcheck(event)" ></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="rate" onkeydown="numcheck(event)" ><input type="hidden" id="rateNonFree" /></td>
							<td style="padding: 0 1px;" id="sale_rate_val" class="hide"><input class="form-control-trx" onkeydown="numcheck(event)"  type="text" id="sale_rate"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ma" value="0"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="total" tabindex="-1" readonly></td>
						</tr>
						<tr align="center" >
							<td id="location_label" class="hide"><spring:message code="purinvdet.jsp.loc" text="Location" /></td>
							<td id="free_label"><spring:message code="purinvdet.jsp.free" text="Free" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></td>
							<td class="hide"><spring:message code="itemmstr.jsp.group" text="Grp" /></td>
							<td class="hide"><spring:message code="itemmstr.jsp.schedule" text="Sch" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.edprcnt" text="Ed%" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></td>
							<td id="wtypelabel_td" class="hide"><spring:message code="purinvdet.jsp.warrantytype" text="Warranty Type" /></td>
							<td id="wmonthlabel_td" class="hide"><spring:message code="purinvdet.jsp.wmonth" text="W. Month" /></td>
							<td id="serialNoPreflabel_td" class="hide"><spring:message code="purinvdet.jsp.slprev" text="SL prev" /></td>
							<td id="serialNoSufflabel_td" class="hide"><spring:message code="purinvdet.jsp.slsuff" text="SL suff" /></td>
							<td id="serialNoTypelabel_td" class="hide"><spring:message code="purinvdet.jsp.sltype" text="SL Type" /></td>
							<td id="Sizelabel_td" class="hide"><spring:message code="purinvdet.jsp.size" text="Size" /></td>
							<td id="Colorlabel_td" class="hide"><spring:message code="purinvdet.jsp.color" text="Color" /></td>
							<td><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></td>
							<td><spring:message code="purinvdet.jsp.tax" text="Tax" /></td>
							<td class="hide">TAX%</td>
							<td class="hide">TAX</td>
							<td id="dis_label"><spring:message code="purinvdet.jsp.discprcnt" text="D%" /></td>
							<td id="disamt_label"><spring:message code="purinvdet.jsp.disc" text="Disc" /></td>
							<td class=""><spring:message code="purinvdet.jsp.checkfreeitem" text="Check if free item" /></td>
						</tr>
						<tr>
						<td style="padding: 0 1px;" id="locationval_td" class="hide">
							<select class="form-control-trx" name="location" id="location">
									<option value="0">Select</option>
									<c:if test="${!empty locationDTOs}">
										<c:forEach items="${locationDTOs}" var="loc">
											<option value="${loc.id}">${loc.name}</option>
										</c:forEach>
									</c:if>
							</select>
							</td>
							<td style="padding: 0 1px;"><input onkeydown="numcheck(event)"  class="form-control-trx" type="text" id="free" onblur="openFreeSlNo($(this).val())"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="mfg" tabindex="-1" readonly><input type="hidden" id="mfgid" /></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="grp" tabindex="-1" readonly><input type="hidden" id="grpid" /></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="sch" tabindex="-1" readonly><input type="hidden" id="schid" /></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="edpercnt"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ed"></td>
							<td style="padding: 0 1px;" id="wtypeval_td" class="hide">
							<select class="form-control-trx" name="warrantyTypeOn" id="warrantyTypeOn">
									<option value="1">ISSUE</option>
									<option value="2">RECEIPT</option>
									<option value="3">BOTH</option>
							</select>
							</td>
							<td style="padding: 0 1px;" id="wmonthval_td" class="hide"><input class="form-control-trx" type="text" value="0" id="warrantymonth"></td>
							<td style="padding: 0 1px;" id="serialNoPrefRequired_td" class="hide"><input class="form-control-trx" type="text" value="0" id="serialNoPrefRequired"></td>
							<td style="padding: 0 1px;" id="serialNoSuffRequired_td" class="hide"><input class="form-control-trx" type="text" value="0" id="serialNoSuffRequired"></td>
							<td style="padding: 0 1px;" id="serialNoType_td" class="hide">
							<select class="form-control-trx" name="serialNoType" id="serialNoType">
													<option value="1">MANUAL</option>
													<option value="2">AUTO</option>
													<option value="3">COMMON</option>
							</select>
							</td>
							<td style="padding: 0 1px;" id="size_td" class="hide"><select class="form-control-trx" name="size" id="size">
									<option value="0">Select</option>
									<c:if test="${!empty sizeVariantDTOs}">
										<c:forEach items="${sizeVariantDTOs}" var="size">
											<option value="${size.value}">${size.value}</option>
										</c:forEach>
									</c:if>
											</select></td>
							<td style="padding: 0 1px;" id="color_td" class="hide"><select class="form-control-trx" name="colour" id="colour">
													<option value="0">Select</option>
									<c:if test="${!empty colorVariantDTOs}">
										<c:forEach items="${colorVariantDTOs}" var="color">
											<option value="${color.value}">${color.value}</option>
										</c:forEach>
									</c:if>
											</select></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt" value="${sessionScope.sesloggedinUser.taxPer}" tabindex="-1" readonly><input type="hidden" id="purTaxId" />
							<input type="hidden" id="item_taxTypeId" value="0" />

							</td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly><input type="hidden" id="purtaxmode" /><input type="hidden" id="purisgrptax" /></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vatprcnt" value="${sessionScope.sesloggedinUser.vatPer}"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly> </td>

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
							<td style="padding: 0 1px;" id="discprcnt_td" width="3%"><input class="form-control-trx" onkeydown="numcheck(event)"  type="text" id="dprcnt" value="0"></td>
							<td style="padding: 0 1px;" id="discamt_td"><input class="form-control-trx" type="text" id="disc" tabindex="-1" readonly></td>
							<td class="hide"><input type="hidden" id="id" /></td>
							<td class="hide"><input type="hidden" id="tblrow_id" /></td>
							<td style="padding: 0 1px; text-align: center;" class=""><input type="checkbox" id="freeCheck" name="freeCheck" style="zoom: 1.5"></td>
							<td style="padding: 0 1px;">
								<button class="btn btn-success btn-sm" type="button" id="add_btn">
									<spring:message code="cmn.jsp.addcaps" text="ADD" />
								</button>
								<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" id="edit_btn">
									<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
								</button>
							</td>
							<td><button class="btn btn-primary btn-sm" id="clear_btn" type="button" onclick="clearHeaderDiv();">
									<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
								</button></td>
						</tr>
					</table>

					<table id="header_ret_tbl" class="hide">
						<tr align="center" >
								<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
								<td><spring:message code="cmn.jsp.barcode" text="Barcode" /></td>
								<td id="batch_ret_label" class="hide"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
								<td id="exp_ret_label" class="hide"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>
								<td id="pqty_ret_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
								<td class="hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
								<td class="hide" id="ratio_ret_label"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
								<td class="hide"><spring:message code="purinvdet.jsp.free" text="Free" /></td>
								<td class="hide" id="mrp_ret_label"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
								<td id="rate_ret_label"><spring:message code="purinvdet.jsp.rate" text="Rate" /></td>
								<td class="hide"><spring:message code="purinvdet.jsp.ma" text="Ma%" /></td>

								<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>
							</tr>
							<tr>
								<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_name_ret" placeholder="Please type atleast 2 characters"> <input type="hidden" id="itemid_ret" value="0"></td>
								<td width="12%"><input class="form-control-trx" type="text" id="barcode_ret" placeholder="Scan Barcode"><input type="hidden" id="purHsnCode_ret" /></td>
								<td style="padding: 0 1px;" class="hide" id="batch_no_ret_td"><input class="form-control-trx" type="text" id="batch_no_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;" class="hide" id="exp_ret_td"><div class="input-group">
										<input type="text" class="form-control-trx" id="exp_ret" name="expDate" placeholder="MM/YY" maxlength="5" tabindex="-1" readonly>
									</div></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="pqty_ret"><input type="hidden" id="punitid_ret" /></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="lqty_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;" class="hide" id="ratio_ret_td"><input class="form-control-trx" type="text" id="ratio_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="free_ret" tabindex="-1" readonly><input type="hidden" id="prevfree_ret"></td>
								<td style="padding: 0 1px;" class="hide" id="mrp_ret_td"><input class="form-control-trx" type="text" id="mrp_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="rate_ret" ></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ma" value="0" tabindex="-1" readonly></td>

								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="total_ret" tabindex="-1" readonly></td>
							</tr>
							<tr align="center" >
								<td class="hide"><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></td>
								<td class="hide"><spring:message code="itemmstr.jsp.group" text="Grp" /></td>
								<td class="hide"><spring:message code="itemmstr.jsp.schedule" text="Sch" /></td>
								<td class="hide"><spring:message code="purinvdet.jsp.edprcnt" text="Ed%" /></td>
								<td class="hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></td>
								<td><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></td>
								<td><spring:message code="purinvdet.jsp.tax" text="Tax" /></td>
								<td class="hide">TAX%</td>
								<td class="hide">TAX</td>
								<td id="dis_label"><spring:message code="purinvdet.jsp.discprcnt" text="D%" /></td>
								<td id="disamt_label"><spring:message code="purinvdet.jsp.disc" text="Disc" /></td>
								<td><spring:message code="purretrn.jsp.bpqty" text="B.P.Qty" /></td>
								<td><spring:message code="" text="Return Reason" /></td>
							</tr>
							<tr>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="mfg_ret" tabindex="-1" readonly><input type="hidden" id="mfgid_ret" /></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="grp_ret" tabindex="-1" readonly><input type="hidden" id="grpid_ret" /></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="sch_ret" tabindex="-1" readonly><input type="hidden" id="schid_ret" /></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="edpercnt" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ed" tabindex="-1" readonly> </td>
								<%-- <c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==0}">
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="taxprcnt" value="${sessionScope.sesloggedinUser.taxPer}" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vatprcnt" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
								</c:if>
								<c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==1}">--%>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt_ret" tabindex="-1" readonly><input type="hidden" id="purTaxId_ret" /></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax_ret" tabindex="-1" readonly><input type="hidden" id="purtaxmode_ret" /><input type="hidden" id="purisgrptax_ret" /></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vatprcnt" value="${sessionScope.sesloggedinUser.vatPer}"></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>

								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="dprcnt_ret" value="0" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="disc_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="billpqty_ret" tabindex="-1" value="0" readonly></td>
								<td class="hide"><input type="hidden" id="tblrow_id_ret" /><input type="hidden" id="allChkdSlNos_ret" /><input type="hidden" id="allSlNos_ret" /></td>
								<td style="padding: 0 1px;">
									<select class="form-control-trx" id="slctRetReason">
					          			<c:if test="${!empty allReturnReasonTypes}">
											<c:forEach items="${allReturnReasonTypes}" var="allReturnReasonType">
													<option value="${allReturnReasonType.id}">${allReturnReasonType.typeName}</option>
											</c:forEach>
										</c:if>
									</select>
								</td>

								<td>
									<button class="btn btn-success btn-sm" type="button" id="add_btn_ret" onclick="addForRet();">
										<spring:message code="cmn.jsp.return" text="Return" />
									</button>
									<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" id="edit_btn_ret" onclick="editForRet();">
										<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
									</button>
								</td>
								<td><button class="btn btn-primary btn-sm" type="button" onclick="clearHeaderDiv();">
										<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
									</button></td>
							</tr>
					</table>

				</div>
			</div>
		</div>

		<div class="col-lg-12 col-md-12 col-sm-12">
			<div id="purHistoryDiv" class="custom-alert custom-alert-info alert-dismissable hide">
				Purchase History Of <strong><span id="itemnameofpurhis"></span></strong><a onclick="closePurHisDet()" aria-label="close" class="pull-right" style="cursor: pointer;"><i class="fa fa-times" aria-hidden="true"></i></a>
				<table id="" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<td>Inv.No</td>
							<td>Inv.Date</td>
							<td>Vendor</td>
							<td>Batch</td>
							<td>Exp.Date</td>
							<td>Pur.Qty</td>
							<td>Free.Qty</td>
							<td>UOM</td>
							<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
								<td>MRP</td>
							</c:if>
							<td>Rate</td>
							<td>Disc(%)</td>
							<td>Gross Amt</td>
						</tr>
					</thead>
					<tbody id="itmpurhistboby"></tbody>
				</table>
			</div>
		</div>

		<div class="col-lg-12 col-md-12 col-sm-12">
			<div style="overflow: auto;" id="detail_table">
				<table id="peitem" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
							<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
							<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
							<th class="numeric hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
							<th class="numeric hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.free" text="Free" /></th>
							<th class="numeric hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.salerate" text="Sale Rate" /></th>
							<!-- <th class="numeric">Ed%</th> -->
							<th class="numeric hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></th>
							<!-- <th class="numeric">Tax%</th> -->
							<th class="numeric"><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.tax" text="Tax" /></th>
							<!-- <th class="numeric">Vat%</th> -->
							<th class="numeric hide"><spring:message code="purinvdet.jsp.vat" text="Vat" /></th>
							<!-- <th class="numeric">D%</th> -->
							<th class="numeric"><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.disc" text="Disc" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.amnt" text="Amt" /></th>
							<th class="numeric hide"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.netamt" text="Net Amt" /></th>
							<!-- <th class="numeric">Ma%</th> -->
							<th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>
						</tr>
					</thead>
					<tbody id="peitemtbody">

					</tbody>
				</table>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12"></div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail">

		                                     <input type="hidden" id="creditor_code1" value="<spring:message code="accgroup.jsp.creditor_code" text="SCR" />">
											  <input type="hidden" id="purchase_code1" value="<spring:message code="accgroup.jsp.purchase_code1" text="PA" />">
											 <input type="hidden" id="dutiesandtax_code1" value="<spring:message code="accgroup.jsp.duties_code" text="DT" />">
											 <input type="hidden" id="dicount_code1" value="<spring:message code="accgroup.jsp.disc_code" text="DIS" />">
											 <input type="hidden" id="roundof_code1" value="<spring:message code="accgroup.jsp.roun_code" text="ROD" />">

											  <input type="hidden" id="lot_adjasment_code" value="<spring:message code="accgroup.jsp.lotadjs_group_code" text="LOT" />">
											<%--   <input type="hidden" id="oth_adjasment_code" value="<spring:message code="accgroup.jsp.othadjs_group_code" text="LOT" />"> --%>
											   <input type="hidden" id="cash_code" value="<spring:message code="accgroup.jsp.cash_code" text="CIH" />">


					                             <!-- for ledger id list -->
					                            <input type="hidden"  id="purchase_ledger_id1" value="0">
												<input type="hidden"  id="duties_ledger_id1" value="0">
												<input type="hidden"  id="round_ledger_id1" value="0">
												<input type="hidden"  id="discount_ledger_id1" value="0">
												<input type="hidden"  id="lotadj_ledger_id1" value="0">
												<input type="hidden"  id="creditor_ledger_id1" value="0">



			<table>
				<tr>
					<td ><spring:message code="purinvdet.jsp.itemcount" text="ItemCount" />:&nbsp;</td>
					<td width="3%" ><span id="itemcount">0</span></td>
					<td ><spring:message code="purinvdet.jsp.total" text="Total" />:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" value="0.0" id="totgrosamnt" tabindex="-1" readonly><input type="hidden" id="totgrosamnt_ret"></td>
					<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
						<td><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" />:</td>
						<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" value="0.0" id="totgrosmrp" tabindex="-1" readonly><input type="hidden" id="totgrosmrp_ret"></td>
					</c:if>
					<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==0}">
						<td class="hide"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" />:</td>
						<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" value="0.0" id="totgrosmrp" tabindex="-1" readonly><input type="hidden" id="totgrosmrp_ret"></td>
					</c:if>
					<td class="hide"><spring:message code="purinvdet.jsp.toted" text="Tot.ED" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="toted" tabindex="-1" readonly></td>
					<td class="hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamnt" tabindex="-1" readonly></td>
					<td class=""><spring:message code="purinvdet.jsp.tottax" text="Tot.Tax" />:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" value="0.0" id="tottaxamnt" tabindex="-1" readonly><input type="hidden" id="tottaxamnt_ret"></td>
					<td class="f"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" value="0.0" id="totdiscamnt" tabindex="-1" readonly><input type="hidden" id="totdiscamnt_ret"></td>
					<td class=""><spring:message code="purinvdet.jsp.roff" text="R.Off" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" value="0.0" id="roundoff" tabindex="-1" readonly="readonly"><input type="hidden" id="roundoff_ret"></td>


					<td><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal" /></span>:</td>
					<td width="8%"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" value="0.0" id="totnetamnt" tabindex="-1" readonly></td>
				</tr>

				<tr>

					<td colspan="2" class=""><spring:message code="purinvdet.jsp.cashpurchase" text="CashPurchase" />: <input type="checkbox" id="invmode" onclick="call_cash_ledger();"></td>
					<td class="" id="cr_noteLabel"><spring:message code="purinvdet.jsp.crnote" text="Adv.Amt." />:</td>
					<td style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="cr_note" onkeydown="numcheck(event)"  value="0"></td>
					<td class=""><spring:message code="purinvdet.jsp.spcldisc" text="Spl.Disc%" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" id="spldisc" onkeydown="numcheck(event)"  value="0"><input type="hidden" value="0.0" id="spldiscamt"></td>
					<!-- <td style="padding: 3px 5px 0 0;"><input class="form-control-trx" type="text" readonly></td> -->
					<td class=""><spring:message code="purinvdet.jsp.ltadj" text="Lt.Adj" />:</td>
					<td style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="totltadj" value="0.0" tabindex="-1" readonly="readonly"><input type="hidden" id="totltadj_ret" /></td>

					<%-- <td class="font-bold"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" id="totdiscamnt" tabindex="-1" readonly></td> --%>
					<td class="" id="suppbillamt_label"><spring:message code="purinvdet.jsp.suppBillAmt" text="Sup.Bill" />:</td>
					<td style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="suppbillamt" onkeydown="numcheck(event)"  value="0.0"></td>
					<td class=""><spring:message code="purinvdet.jsp.othadj" text="Oth.Adj" />:</td>
					<td style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="othadjamt" value="0.0" tabindex="-1" readonly="readonly"></td>
					<td class=""><spring:message code="purinvdet.jsp.billamnt" text="Bill.Amt." />:</td>
					<td style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="billamt" value="0.0" tabindex="-1" readonly="readonly"></td>
				</tr>
				<tr>
					<td class=""><spring:message code="purinvdet.jsp.remarks" text="Remarks" />:</td>
					<td colspan="3" style="padding: 1px 5px 0 0"><input class="form-control-trx" type="text" id="remarks"></td>


					<td class="hide"><spring:message code="purinvdet.jsp.expadj" text="Exp.Adj" />:</td>
					<td style="padding: 1px 5px 0 0" class="hide"><input class="form-control-trx" type="text" id="expadj" value="0.0" tabindex="-1" readonly="readonly"></td>
					<td style="padding-top: 1px; padding-right: 4px;" class="hide"><button class="btn btn-primary btn-sm" type="button" onclick="openexpadjmod()" title="<spring:message code="purinv.jsp.retrnexptitle" text="Return Expiry" />">
							<i class="fa fa-reply"></i>
							<spring:message code="purinv.jsp.retbtn" text="RET" />
						</button></td>
					<td class="hide"></td>
					<td><spring:message code="purinvdet.jsp.retadj" text="Ret.Adj" />:</td>
					<td style="padding: 1px 5px 0 0"><input class="form-control-trx" type="text" id="retadj" value="0.0" tabindex="-1" readonly="readonly"></td>
					<td style="padding-top: 1px; padding-right: 4px;"><button class="btn btn-primary btn-sm" type="button" id="retAdj_btn" onclick="openretadjmod()" title="<spring:message code="purinv.jsp.retrnpurtitle" text="Return Purchase" />">
							<i class="fa fa-reply"></i>
							<spring:message code="purinv.jsp.retbtn" text="RET" />
						</button></td>

					<!-- </tr>
					<tr> -->
					<td style="padding-top: 1px; padding-right: 4px;"><button class="btn btn-primary btn-sm hide" type="button">
							<spring:message code="cmn.jsp.btn.post" text="POST" />
						</button></td>
					<td colspan="4" style="padding-top: 1px;">
						<button style="padding: 5px 8px;" class="btn btn-info btn-sm" type="button" onclick="newPurInv()">
							<spring:message code="cmn.jsp.new" text="NEW" />
						</button>
						<button style="padding: 5px 8px;" class="btn btn-danger btn-sm hide" type="button">
							<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
						</button>
						<button style="padding: 5px 8px;" class="btn btn-success btn-sm" type="button" id="save_btn">
							<spring:message code="cmn.jsp.btn.savecaps" text="SAVE" />
						</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
</section>

<!-- Return Adj Modal Start -->
<div class="modal fade" id="retAdjModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 666px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.retrnmemotoadjst" text="Return Purchase to Adjust for Bill Amt" />
					. <span id="billamtheaderAdj"></span>
				</h4>
			</div>
			<div class="modal-body">

				<table>
					<tr>
						<td><spring:message code="pos.jsp.memono" text="Memo.No" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjmemoDoc" value="${purReturnDefaultPrefix}/" size="4" readonly></td>
						<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjmemoFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
						<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retadjmemoSlash" value="/" readonly></td>
						<td><input class="form-control-trx" type="text" id="retadjmemono" value="" size="10"></td>
						<td style="padding: 0 1px;"><spring:message code="purinvdet.jsp.vendor" text="Vendor" />:</td>
						<td style="padding: 0 1px;" id="adjVendor"></td>
						<td><input type="hidden" id="vendorIdforAdj" /></td>
						<td><button class="btn btn-theme btn-sm" onclick="getMemoDetForAdj()">
								<spring:message code="cmn.jsp.search" text="Search" />
							</button></td>
					</tr>
				</table>
				<div>
					<p>
						<span id="nocashmemofound" style="font-weight: bold; color: red;"></span>
					</p>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="showretadjDetails">
					<table id="" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
								<th><spring:message code="pos.jsp.preadjamt" text="Pre Adj Amt" /></th>
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
							</tr>
						</thead>
						<tbody id="showretadjtbody">

						</tbody>
					</table>
				</div>
				<div>
					<p>
						<spring:message code="pos.jsp.addedmemos" text="Added Memos" />
					</p>
					<span id="greaterbillamt" style="font-weight: bold; color: red;"></span>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="retadjDetails">
					<table id="retadjtable" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
								<th><spring:message code="pos.jsp.preadjamt" text="Pre Adj Amt" /></th>
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
							</tr>
						</thead>
						<tbody id="retadjtbody">

						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td><span class="font-bold"><spring:message code="purinvdet.jsp.total" text="Total" /></span></td>
								<td><span class="font-bold" id="totretnetamt">0.00</span></td>
								<td><span class="font-bold" id="totretpreadjamt">0.00</span></td>
								<td><span class="font-bold" id="totretadjamt">0.00</span></td>
								<td></td>
							</tr>
						</tfoot>

					</table>
				</div>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-default" onclick="closeRetAdjMod()" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="okRetAdjMod()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Return Adj Modal End -->

<!-- Expiry Adj Modal Start -->
<div class="modal fade" id="expAdjModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 666px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.retrnexptoadjst" text="Return Expiry to Adjust for Bill Amt" />
					. <span id="billamtheaderExpAdj"></span>
				</h4>
			</div>
			<div class="modal-body">

				<table>
					<tr>
						<td><spring:message code="pos.jsp.memono" text="Memo.No" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjexpDoc" value="EXP/" size="4" readonly></td>
						<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjexpFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
						<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retadjexpSlash" value="/" readonly></td>
						<td><input class="form-control-trx" type="text" id="retadjexpno" value="" size="10"></td>
						<td style="padding: 0 1px;"><spring:message code="purinvdet.jsp.vendor" text="Vendor" />:</td>
						<td style="padding: 0 1px;" id="expAdjVendor"></td>
						<td><input type="hidden" id="expVendorIdforAdj" /></td>
						<td><button class="btn btn-theme btn-sm" onclick="getExpDetForAdj()">
								<spring:message code="cmn.jsp.search" text="Search" />
							</button></td>
					</tr>
					<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
					<tr>
						<td><input type="radio" name="rateOrmrp" value="rate" checked onchange="adjOnMrpRate();"> <spring:message code="purinvdet.jsp.rate" text="Rate" /></td>
						<td><input type="radio" name="rateOrmrp" value="mrp" onchange="adjOnMrpRate();"> <spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
					</tr>
					</c:if>
				</table>
				<div>
					<p>
						<span id="noexpfound" style="font-weight: bold; color: red;"></span>
					</p>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="showretadjDetails">
					<table id="showretadjDetailsTbl" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="reprintcash.jsp.totqty" text="Tot.Qty" /></th>
								<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
								<th><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								</c:if>
								<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==0}">
								<th class="hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								</c:if>
								<th><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
							</tr>
						</thead>
						<tbody id="showretadjexptbody">

						</tbody>
					</table>
				</div>
				<div>
					<p>
						<spring:message code="pos.jsp.addedmemos" text="Added Memos" />
					</p>
					<span id="expgreaterbillamt" style="font-weight: bold; color: red;"></span>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="retadjDetails">
					<table id="retexpadjtable" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="reprintcash.jsp.totqty" text="Tot.Qty" /></th>
								<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
								<th><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								</c:if>
								<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==0}">
								<th class="hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								</c:if>
								<th><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
							</tr>
						</thead>
						<tbody id="retadjexptbody">

						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
								<td></td>
								</c:if>
								<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
								<td class="hide"></td>
								</c:if>
								<td><span class="font-bold"><spring:message code="purinvdet.jsp.total" text="Total" /></span></td>
								<td><span class="font-bold" id="totretexpadjamt">0.00</span></td>
								<td></td>
							</tr>
						</tfoot>

					</table>
				</div>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-default" onclick="closeRetExpAdjMod()" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="okRetExpAdjMod()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Expiry Adj Modal End -->

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
				<div class="form-horizontal" id="vendorModalBody">
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
							<label class="col-sm-4 col-sm-4 control-label" id="phn1_label"><spring:message code="vendor.jsp.phn1" text="Contact No. 1" /><!--  <span class="required_star">*</span> --></label>
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
							<label class="col-sm-4 col-sm-4 control-label" id="rgstratn_label"><spring:message code="vendor.jsp.rgstratn" text="Registration No." /> <!-- <span class="required_star">*</span> --></label>
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
							<label class="col-sm-4 col-sm-4 control-label" id="licenceNo_label"><spring:message code="vendor.jsp.licenceno" text="Licence No" /> <!-- <span class="required_star">*</span> --></label>
							<div class="col-sm-8">
								<input type="text" id="licenceNo" class="form-control" placeholder="<spring:message code="cmn.jsp.egdlno" text="E.g. D.L No." />"/>
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
											<option value="${userinformation.countryId}"></option>
											<!-- <option value="0">Select...</option> -->
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
<!-- Item exists start -->
<div class="modal fade" id="purInvExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!--  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel"><spring:message code="purinv.jsp.purimportfromexcel" text="Purchase Import From Excel" /></h4>
			</div>

			<div class="modal-body">
			<form:form action="${pageContext.request.contextPath}/purinv/uploadpurchaseinvexcel.htm" id="purinv_upload_excel" modelAttribute="purchaseInvoiceExcelDTO" method="post" enctype="multipart/form-data">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id=""><spring:message code="purinv.jsp.uploadexcel" text="Upload" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input class="form-control" type="file" name="fileUpload" id="fileUpload" accept=".xls,.xlsx" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id=""><spring:message code="purinv.jsp.vendor" text="Vendor" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<select class="form-control-trx" name="excelUploadVendorId" id="excelUploadVendorId">
									<option value="0">Select</option>
									<c:if test="${!empty allVendors}">
										<c:forEach items="${allVendors}" var="allVendor">
											<option value="${allVendor.id}">${allVendor.name}</option>
										</c:forEach>
									</c:if>

								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id=""><spring:message code="purinv.jsp.billno" text="Bill No." /> <span class="required_star">*</span></label>
							<div class="col-sm-5">
								<input type="text" name="excelUploadBillNo" id="excelUploadBillNo" class="form-control-trx"  placeholder="e.g.- ww12345" readonly="readonly"/>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-primary btn-sm" type="button" id="advbut" style="text-transform: uppercase;">
									<i class="fa fa-hand-o-down" aria-hidden="true"></i>
									<spring:message code="purinv.jsp.Advance" text="Advance" />
								</button>
							</div>
						</div>
						<div id="advancediv" style="display: none;">
						<div class="form-group">
						<label class="col-sm-2 col-sm-2 control-label" id=""><spring:message code="purinv.jsp.StartRow" text="Start Row" /></label>
							<div class="col-sm-4">
								<input type="number" name="excelUploadStartRow" id="excelUploadStartRow" class="form-control-trx" placeholder="e.g.- 5"/>
							</div>
							<label class="col-sm-2 col-sm-2 control-label" id=""><spring:message code="purinv.jsp.EndRow" text="End Row" /></label>
							<div class="col-sm-4">
								<input type="number" name="excelUploadEndRow" id="excelUploadEndRow" class="form-control-trx"  placeholder="e.g.- 100"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.Name" text="Name" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadItemName" id="excelUploadItemName" style="text-transform:uppercase;" class="form-control-trx" placeholder="e.g.- B" maxlength="1" />
							</div>
							<label class="col-sm-1 col-sm-1 control-label"  id=""><spring:message code="purinv.jsp.Pack" text="Pack" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadPack" id="excelUploadPack" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- C" maxlength="1"/>
							</div>
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.Batch" text="Batch" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadBatch" id="excelUploadBatch" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- D" maxlength="1"/>
							</div>
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.MFG" text="MFG" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadMfg" id="excelUploadMfg" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- E" maxlength="1"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.ExpDate" text="ExpDate" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadExpdate" id="excelUploadExpdate" class="form-control-trx" style="margin-left: 8%;text-transform:uppercase;" placeholder="e.g.- F" maxlength="1"/>
							</div>
							<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
								<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.MRP" text="MRP" /></label>
								<div class="col-sm-2">
									<input type="text" name="excelUploadMrp" id="excelUploadMrp" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- G" maxlength="1"/>
								</div>
							</c:if>
							<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==0}">
								<label class="col-sm-1 col-sm-1 control-label hide" id=""><spring:message code="purinv.jsp.MRP" text="MRP" /></label>
								<div class="col-sm-2 hide">
									<input type="text" name="excelUploadMrp" id="excelUploadMrp" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- G" maxlength="1"/>
								</div>
							</c:if>
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.Rate" text="Rate" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadRate" id="excelUploadRate" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- H" maxlength="1"/>
							</div>
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.Qty" text="Qty" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadQty" id="excelUploadQty" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- I" maxlength="1"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.Free" text="Free" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadFree" id="excelUploadFree" class="form-control-trx"  style="text-transform:uppercase;" placeholder="e.g.- J" maxlength="1"/>
							</div>
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.Disper" text="Dis(%)" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadDiscPerc" id="excelUploadDiscPerc" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- K" maxlength="1"/>
							</div>
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.Taxper" text="Tax(%)" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadTaxPerc" id="excelUploadTaxPerc" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- L" maxlength="1"/>
							</div>
							<label class="col-sm-1 col-sm-1 control-label" id=""><spring:message code="purinv.jsp.Amt" text="Amt" /></label>
							<div class="col-sm-2">
								<input type="text" name="excelUploadAmt" id="excelUploadAmt" class="form-control-trx" style="text-transform:uppercase;" placeholder="e.g.- M" maxlength="1"/>
							</div>
						</div>
						</div>
					</div>

				</form:form>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="exceluploadalertmsg"></div>
			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
			<input type="button"  onclick="uploadPurInv()" style="text-transform: uppercase;" class="btn btn-success btn-sm" value="Import" />

			</div>

		</div>
	</div>
</div>
<!-- Item exists end -->

<!-- Purchase Order Detail by Order No Modal Starts -->
			<div class="modal fade" id="purorderdetailModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="purorder.jsp.purorderdetfor" text="Purchase Order Details for" />
					<spring:message code="purinvdet.jsp.invno" text="Invoice No" />. ${poDefaultPrefix}/${sessionScope.sesloggedinUser.finyrCode}/
					<span id="searchmodorderno"> </span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="purorderdetnotfounddiv" class="hide">
					<span class="font-bold"><spring:message code="purorder.jsp.ordernotfoundfornumbermsg" text="No Purchase Order found for this order number" />.</span>
				</div>
				<div id="purorderdetfounddiv">
					<%-- <div>
						<span class="font-bold"><spring:message code="rtrnmemo.jsp.salereturnmodal.invdt" text="Invoice Date" />
							: </span> <span id="searchmodorderdate">2017-02-09</span>
					</div>
					<div>
						<span class="font-bold"><spring:message code="rtrnmemo.jsp.salereturnmodal.invamnt" text="Invoice Amount" /> : </span> <span id="searchordermodtotamt">100.00</span>
					</div> --%>

					<div>
						<span class="font-bold"><spring:message code="printpur.jsp.vendorname" text="Vendor Name" /> :</span> <span
							id="searchordermodvendorname">Med Plus</span>
					</div>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;"
					id="purorderdetmodtable">
					<table id="searchordermodtable"
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
									<th><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								</c:if>
								<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
									<th class="hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								</c:if>
								<th><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></th>
								<th><spring:message code="purretrn.jsp.discper" text="Dis%" /></th>
							</tr>
						</thead>
						<tbody id="searchordermodtbody">

						</tbody>
						<div>
							<span id="alertmessagecont"
								style="font-weight: bold; color: red;"> </span>
						</div>

					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" class="btn btn-primary" id="purorderdetailModal_okbtn"
					onclick="getmodordercheckeditemlist()">
					<spring:message code="footer.jsp.btn.ok" text="Ok" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Purchase Order Detail by Order No Modal ends -->

<!-- Ret On Pur Bill Modal Starts -->

<div class="modal fade" id="itempurdetailModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="purretrn.jsp.purdetfor" text="Purchase Details for" />
					<span id="itempurdetailitemname"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="itempurdetnotfounddiv" class="hide">
					<span class="font-bold"><span id=""><spring:message code="purretrn.jsp.purmemonotfoundforitemmsg" text="No Purchase memo found for this item or select the vendor" />.</span>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;"
					id="itempurdetmodtable">
					<table id=""
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>

								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></th>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.free" text="Free" /></th>
								<th><spring:message code="purinvdet.jsp.stockqty" text="Stock Qty" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></th>
								<th><spring:message code="purinvdet.jsp.tax" text="Tax" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.vat" text="Vat" /></th>
								<th><spring:message code="purinvdet.jsp.disc" text="Disc" /></th>
								<th class="numeric"><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
							</tr>
						</thead>
						<tbody id="itemsearchmodtbody">

						</tbody>


					</table>
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

<!-- Ret On Pur Bill Modal Ends -->


<div class="modal fade" id="confirmPurexModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
			<div id="Purexconfirmmessagecont"></div>
			<div id="notupldfilelistdiv">
				List of failed items: ${notupldfilelistobj.failedItemList}
			</div>
			</div>
			<div class="modal-footer">
				<button type="button" onclick="PurexDoConfirm()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

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
		      <label>SL No #1 : </label><input type='textbox' id='textbox1' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "1" id='checkbox1'></span>
		      <input type="hidden" id="allVal" />
		      <input type="hidden" id="clk" value="0"/>
		      <input type="hidden" id="qtysl" value="0"/>
		      <input type="hidden" id="modalCltype" value="0"/>
	        </div>
	        <div id="TextBoxDiv_2" class="hide">
		      <label>SL No #2 : </label><input type='textbox' id='textbox2' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "2" id='checkbox2'></span>
	        </div>
	         <div id="TextBoxDiv_3" class="hide">
		      <label>SL No #3 : </label><input type='textbox' id='textbox3' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "3" id='checkbox3'></span>
	        </div>
	         <div id="TextBoxDiv_4" class="hide">
		      <label>SL No #4 : </label><input type='textbox' id='textbox4' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "4" id='checkbox4'></span>
	        </div>
	         <div id="TextBoxDiv_5" class="hide">
		      <label>SL No #5 : </label><input type='textbox' id='textbox5' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "5" id='checkbox5'></span>
	        </div>
	         <div id="TextBoxDiv_6" class="hide">
		      <label>SL No #6 : </label><input type='textbox' id='textbox6' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "6" id='checkbox6'></span>
	        </div>
	         <div id="TextBoxDiv_7" class="hide">
		      <label>SL No #7 : </label><input type='textbox' id='textbox7' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "7" id='checkbox7'></span>
	        </div>
	         <div id="TextBoxDiv_8" class="hide">
		      <label>SL No #8 : </label><input type='textbox' id='textbox8' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "8" id='checkbox8'></span>
	        </div>
	         <div id="TextBoxDiv_9" class="hide">
		      <label>SL No #9 : </label><input type='textbox' id='textbox9' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "9" id='checkbox9'></span>
	        </div>
	         <div id="TextBoxDiv_10" class="hide">
		      <label>SL No #10 : </label><input type='textbox' id='textbox10' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "10" id='checkbox10'></span>
	        </div>
	         <div id="TextBoxDiv_11" class="hide">
		      <label>SL No #11 : </label><input type='textbox' id='textbox11' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "11" id='checkbox11'></span>
	        </div>
	         <div id="TextBoxDiv_12" class="hide">
		      <label>SL No #12 : </label><input type='textbox' id='textbox12' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "12" id='checkbox12'></span>
	        </div>
	         <div id="TextBoxDiv_13" class="hide">
		      <label>SL No #13 : </label><input type='textbox' id='textbox13' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "13" id='checkbox13'></span>
	        </div>
	         <div id="TextBoxDiv_14" class="hide">
		      <label>SL No #14 : </label><input type='textbox' id='textbox14' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "14" id='checkbox14'></span>
	        </div>
	         <div id="TextBoxDiv_15" class="hide">
		      <label>SL No #15 : </label><input type='textbox' id='textbox15' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "15" id='checkbox15'></span>
	        </div>
	         <div id="TextBoxDiv_16" class="hide">
		      <label>SL No #16 : </label><input type='textbox' id='textbox16' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "16" id='checkbox16'></span>
	        </div>
	         <div id="TextBoxDiv_17" class="hide">
		      <label>SL No #17 : </label><input type='textbox' id='textbox17' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "17" id='checkbox17'></span>
	        </div>
	         <div id="TextBoxDiv_18" class="hide">
		      <label>SL No #18 : </label><input type='textbox' id='textbox18' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "18" id='checkbox18'></span>
	        </div>
	         <div id="TextBoxDiv_19" class="hide">
		      <label>SL No #19 : </label><input type='textbox' id='textbox19' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "19" id='checkbox19'></span>
	        </div>
	         <div id="TextBoxDiv_20" class="hide">
		      <label>SL No #20 : </label><input type='textbox' id='textbox20' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "20" id='checkbox20'></span>
	        </div>
	        <div id="TextBoxDiv_21" class="hide">
		      <label>SL No #21 : </label><input type='textbox' id='textbox21' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "21" id='checkbox21'></span>
	        </div>
	        <div id="TextBoxDiv_22" class="hide">
		      <label>SL No #22 : </label><input type='textbox' id='textbox22' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "22" id='checkbox22'></span>
	        </div>
	        <div id="TextBoxDiv_23" class="hide">
		      <label>SL No #23 : </label><input type='textbox' id='textbox23' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "23" id='checkbox23'></span>
	        </div>
	        <div id="TextBoxDiv_24" class="hide">
		      <label>SL No #24 : </label><input type='textbox' id='textbox24' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "24" id='checkbox24'></span>
	        </div>
	        <div id="TextBoxDiv_25" class="hide">
		      <label>SL No #25 : </label><input type='textbox' id='textbox25' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "25" id='checkbox25'></span>
	        </div>
	        <div id="TextBoxDiv_26" class="hide">
		      <label>SL No #26 : </label><input type='textbox' id='textbox26' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "26" id='checkbox26'></span>
	        </div>
	        <div id="TextBoxDiv_27" class="hide">
		      <label>SL No #27 : </label><input type='textbox' id='textbox27' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "27" id='checkbox27'></span>
	        </div>
	        <div id="TextBoxDiv_28" class="hide">
		      <label>SL No #28 : </label><input type='textbox' id='textbox28' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "28" id='checkbox28'></span>
	        </div>
	        <div id="TextBoxDiv_29" class="hide">
		      <label>SL No #29 : </label><input type='textbox' id='textbox29' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "29" id='checkbox29'></span>
	        </div>
	        <div id="TextBoxDiv_30" class="hide">
		      <label>SL No #30 : </label><input type='textbox' id='textbox30' onblur="getSLValue($(this).val())"><span style="margin-left: 1%;" class="retSlnoChk hide"> <input type='checkbox' style='zoom:1' class = "chk" value = "30" id='checkbox30'></span>
	        </div>
			</div>
			<div id="slnoduptext" class="hide">Duplicate serial numbers not allow : <span id="slnodupval"></span></div>
			<div id="blankslno"></div>
			</div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-default" onclick="closeSlNo()">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" id="selslno" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>

			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="ord_openSerialModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title">
<%-- 					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message> --%>
                      Add Serial No
				</h4>
			</div>
			<div class="modal-body">
			<div id="ordsltmplt" class="hide">
				<div class="">
			      <label>SL No #NO
			      </label>
			      <input class="ordtextbox" type='textbox' id='ordtextbox'>
		        </div>
			</div>
			<div>
				<input type="hidden" id="ordtxtno" />
				<input type="hidden" id="ordallVal" />
				<input type="hidden" id="ordclk" value="0"/>
				<input type="hidden" id="ordqtysl" value="0"/>
				<input type="hidden" id="ordmodalCltype" value="0"/>
			</div>
			<div id='ordTextBoxesGroup'>
				
	         
			</div>
			<div id="slnoduptext" class="hide">Duplicate serial numbers not allow : <span id="slnodupval"></span></div>
			<div id="errmsgslno" style="color:red;"></div>
			</div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-default" onclick="closeordSlNo()">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" id="ord_selslno" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>

			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="openExpiryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
<%-- 					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message> --%>
                      Expiry Details
				</h4>
			</div>
			<div class="modal-body">
			<div class="form-horizontal">
			<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id=""><spring:message code="purinv.jsp.uploadexcel" text="Upload" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input class="form-control" type="file" name="fileUpload" id="fileUpload" accept=".xls,.xlsx" />
							</div>
						</div>
			</div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="openSlNo()" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>

			</div>
		</div>
	</div>
</div>
</div>
<!--/wrapper -->
<jsp:include page="/WEB-INF/pages/common/commonnewitemadd.jsp" />
<script src="${pageContext.request.contextPath }/assets/js/proc/purchalan/purchallan.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/proc/purchalan/purchallan_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	var fileUploadMsg = "${msg}";
	var notupldfilelist = "${notupldfilelistobj}";
	var storeId = "${sessionScope.sesloggedinUser.storeId}";
	var cmpnyId = "${sessionScope.sesloggedinUser.companyId}";
	var createdBy = "${sessionScope.sesloggedinUser.id}";
	var finyrId = "${sessionScope.sesloggedinUser.finyrId}";
	var is_acc=${is_acc};
	var isWholesale=${isWholesale};

	function showConfirmModal() {
		$('#confirmMessageModal').modal('show');
	}

	//  function addNewItem() {
	//	$('#itemmasterModal').modal('show');

	//}
	$(document).ready(function() {


		getvendorledger_pur($('#dutiesandtax_code1').val(),0,0,0); // for duties and tax
		getvendorledger_pur($('#roundof_code1').val(),0,0,1); // for round off
		getvendorledger_pur($('#purchase_code1').val(),0,0,2);// for purchase account

		getvendorledger_pur($('#dicount_code1').val(),0,0,4);// for discount account
		getvendorledger_pur($('#lot_adjasment_code').val(),0,0,5);// for lot adjasment account

		var seslocid=$("#seslocationid").val();
		$("#location").val(seslocid);
		if (fileUploadMsg == "-1") {

		}else if (fileUploadMsg == "0") {
			document.getElementById('Purexconfirmmessagecont').innerHTML = "File not uploaded.Please re-check your file.";
			//$("#notupldfilelistdiv").removeClass("hide");
			$('#confirmPurexModal').modal('show');
		} else {
			document.getElementById('Purexconfirmmessagecont').innerHTML = "File uploaded successfully. Invoice no:"+fileUploadMsg;
			//$("#notupldfilelistdiv").addClass("hide");
			$('#confirmPurexModal').modal('show');
		}
		$("input:text").focus(function() {
			$(this).select();
		});
		var today = new Date();
		  var today = new Date();
		today.setDate(today.getDate() + parseInt($("#noOfDue").val()));
		var yyyy = today.getFullYear().toString();
		var mm = (today.getMonth() + 1).toString(); // getMonth() is zero-based
		var dd = today.getDate().toString();
		$("#duedt").val(yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]));

		  $('#duedt').datepicker({
			format : 'yyyy-mm-dd',
			startDate : today,
			autoclose : true,
		});

		/*  $("#exp").keyup(function() {
			if ($(this).val().length == 2) {
				if ($(this).val() <= 12 && $(this).val() > 0) {
					$(this).val($(this).val() + "/");
				} else {
					$(this).val("");
				}

			}
		});  */

	});
	var countryId = "${sessionScope.sesloggedinUser.countryId}";
	var stateId = "${sessionScope.sesloggedinUser.stateId}";
</script>
