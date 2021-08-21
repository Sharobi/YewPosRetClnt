<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll;max-height: 250px; width: 300px; word-break: break-all;
}
-->
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
			
				<div class="panel-body-trx">
					<input type="hidden" id="expId" value="${expiryId}" />
					
					
					 <c:if test="${expiryId==0}"> 
					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/manualexpiry/searchmanualexpiry.htm" method="post" id="searchForm">
						<table>
							<tr align="center" style="font-weight: bold;">
							    <td><spring:message code="stckentry.jsp.entrydt" text="Entry Date" /></td>
							    <c:if test="${expiryId!=0}"> 
							     <td>Invoice Number</td>
							     </c:if>
							    <td width="25%" id="vendor_label"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
								<td><spring:message code="chargemaster.jsp.itename" text="Item Name" /></td>
								<td><spring:message code="chargemaster.jsp.itemcode" text="Item Code" /></td>
								
							</tr>
							<tr>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" readonly="readonly" id="invDt" class="form-control-trx" name="asOnDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
									</div>
								</td>
								<c:if test="${expiryId!=0}"> 
								<td width="15%" style="padding: 0 1px;"><input class="hide" name="invoiceNo" value=""/><input class="form-control-trx" type="text" id="expiryInvoiceNo" value="${expiryHeader.invNo}" readonly="readonly"></td>
								</c:if>
								<td style="padding: 0 1px;"><select class="form-control-trx" name="distributorId" id="seldistributor">
											<option value="0">All</option>
											<c:if test="${!empty allVendors}">
												<c:forEach items="${allVendors}" var="allVendor">
														<option value="${allVendor.id}">${allVendor.name}</option>
												</c:forEach>
											</c:if>
									</select>
								</td>
								<td width="25%" style="padding: 0 5px;"><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"> <input type="hidden" id="item_id" value="0" name="itemId"></td>
								
								<td width="16%"><input class="form-control-trx" type="text" id="item_code" placeholder="Please type atleast 2 characters"></td>
								
								
								
								<td style="padding: 0 2px;"><button type="submit" id="search_btn" class="btn btn-theme">Search</button></td>
								
							</tr>
							<tr>
								<td colspan="6"><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
							</tr>
							<tr>
							<td colspan="6">
							 <c:if test="${error==1}">
							 <span>Please Select Item For Details</span>
							 </c:if>
							</td>
							</tr>
							</table>
						</form>
					</c:if>
					 <c:if test="${expiryId!=0}"> 
					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/manualexpiry/searchmanualexpiry.htm" method="post" id="searchForm">
						<table>
							<tr align="center" style="font-weight: bold;">
							    <td><spring:message code="stckentry.jsp.entrydt" text="Entry Date" /></td>
							     <td>Invoice Number</td>
							    <td width="25%" id="vendor_label"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
								<td><spring:message code="chargemaster.jsp.itename" text="Item Name" /></td>
								<td><spring:message code="chargemaster.jsp.itemcode" text="Item Code" /></td>
								
							</tr>
							<tr>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" readonly="readonly" id="invDt" class="form-control-trx" name="asOnDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
									</div>
								</td>
								<td width="15%" style="padding: 0 1px;"><input class="hide" name="invoiceNo" value=""/><input class="form-control-trx" type="text" id="expiryInvoiceNo" value="${expiryHeader.invNo}" readonly="readonly"></td>
								
								<td style="padding: 0 1px;"><select class="form-control-trx" name="distributorId" id="seldistributor">
											<option value="0">All</option>
											<c:if test="${!empty allVendors}">
												<c:forEach items="${allVendors}" var="allVendor">
														<option value="${allVendor.id}">${allVendor.name}</option>
												</c:forEach>
											</c:if>
									</select>
								</td>
								<td width="25%" style="padding: 0 5px;"><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"> <input type="hidden" id="item_id" value="0" name="itemId"></td>
								
								<td width="16%"><input class="form-control-trx" type="text" id="item_code" placeholder="Please type atleast 2 characters"></td>
								
								
								
								<td style="padding: 0 2px;"><button type="button" id="search_btn" class="btn btn-theme">Search</button></td>
								
							</tr>
							<tr>
								<td colspan="6"><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
							</tr>
							<tr>
							<td colspan="6">
							 <c:if test="${error==1}">
							 <span>Please Select Item For Details</span>
							 </c:if>
							</td>
							</tr>
							</table>
						</form>
					</c:if>
				</div>
			</div>
		</div>
		
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div style="max-height: 440px; height: 440px; overflow-y: scroll; overflow-x:hidden;">
			<%-- <label style="display:inline;"><b><spring:message code="purinvdet.jsp.selectall" text="Check it to select all:" /></b></label> --%>
			<table id="expitem" class="table table-bordered table-striped table-condensed-trx table-hover">
				<thead>
					<tr>
							<th><input name="select_all" value="1" id="example-select-all" type="checkbox" style="zoom: 2;vertical-align:sub;"/></th>
							<th><spring:message code="chargemaster.jsp.istaxable" text="IsTaxable" /></th>
							<th style="text-align: center;"><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
							<th style="text-align: center;"><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
                            <th style="text-align: center;"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
                            <th style="text-align: center;"><spring:message code="expiry.jsp.unit" text="Unit" /></th>
                        	<th style="text-align: center;"><spring:message code="itemmstr.jsp.rack" text="Rack" /></th>
                           	<th style="text-align: center;"><spring:message code="reprintcash.jsp.totqty" text="Tot.Qty" /></th>
                           	<th style="text-align: center;"><spring:message code="expissuemanual.jsp.expqty" text="Expiry Qty" /></th>
                            <th style="text-align: center;" class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
                            <th style="text-align: center;" class="numeric"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
                            <th style="text-align: center;" width="25%"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th>
                            <th class="hide"></th> 
                          
					</tr>
				</thead>
				<tbody>
					 <c:if test="${!empty expiryDetailsDTOs}">
						<c:forEach items="${expiryDetailsDTOs}" var="expiryDetailsDTO">
							 <tr id="${expiryDetailsDTO.itemUniqueKey}">
							     <td>
									<c:choose>
										<c:when test="${expiryId==0}">
											<input id='${expiryDetailsDTO.itemUniqueKey}_modretcheck' class='chkboxcheked' type='checkbox' onclick="call_vendor('${expiryDetailsDTO.itemUniqueKey}')" >
										</c:when>
										<c:otherwise>
											<input id='${expiryDetailsDTO.itemUniqueKey}_modretcheck' class='chkboxcheked' type='checkbox' checked="checked" onclick="call_vendor('${expiryDetailsDTO.itemUniqueKey}')">
										</c:otherwise>
									</c:choose>
								</td>
								
								<td style="text-align: center;">
								
									<c:choose>
										<c:when test="${expiryDetailsDTO.taxAmount==0}">
											<input name="taxable" value="1" id="${expiryDetailsDTO.itemUniqueKey}_istaxable" type="checkbox" />
										</c:when>
										<c:otherwise>
											<input name="taxable" value="1" id="${expiryDetailsDTO.itemUniqueKey}_istaxable" type="checkbox" checked="checked" />
										</c:otherwise>
									</c:choose>
								</td>
								<td id="tbl_item_name">${expiryDetailsDTO.itemName}</td>
								<td id="tbl_batch_no">${expiryDetailsDTO.batchNo}</td>
								<td id="tbl_exp">${expiryDetailsDTO.expiryDateFormat}</td>
								<td id="tbl_punit_name">${expiryDetailsDTO.packUnitName}</td>
								<td id="tbl_rack_name">${expiryDetailsDTO.rackName}</td>
								<td id="tbl_stock_qty">${expiryDetailsDTO.stockQty}</td>
								<td id="tbl_exp_qty"><input id="exp_qty" value="${expiryDetailsDTO.calculateLooseQty}" onkeyup = "CheckExpQty('${expiryDetailsDTO.itemUniqueKey}',${expiryDetailsDTO.calculateLooseQty});"/></td>
								<td id="tbl_mrp">${expiryDetailsDTO.mrp}</td>
								<td id="tbl_rate">${expiryDetailsDTO.rate}</td>
							    <td width="25%"><select class="form-control-trx" name="distributorId" id="expdistributor" onchange="checkSelection();">
															<option value="0">Select</option>
											
											<c:if test="${!empty allVendors}">
												<c:forEach items="${allVendors}" var="allVendor">
													<c:choose>
														<c:when test="${allVendor.id==expiryDetailsDTO.distributorId}">
															<option value="${expiryDetailsDTO.distributorId}" selected="selected">${expiryDetailsDTO.distributorName}</option>
														</c:when>
														<c:otherwise>
															<option value="${allVendor.id}">${allVendor.name}</option>
														</c:otherwise>
													</c:choose>														
												</c:forEach>
											</c:if>
									</select>
								</td> 
								
								<td class="hide" id="tbl_item_id">${expiryDetailsDTO.itemId}</td>
								<td class="hide" id="tbl_punit_id">${expiryDetailsDTO.packUnitId}</td>
								<td class="hide" id="tbl_rack_id">${expiryDetailsDTO.rackId}</td>
							    <fmt:parseDate value="${expiryDetailsDTO.expiryDate}" var="expDate" pattern="MMM dd, yyyy" />
								<td class="hide" id="tbl_expdt"><fmt:formatDate pattern="yyyy-MM-dd" value="${expDate}" /></td>
								<td class="hide" id="tbl_pqty">${expiryDetailsDTO.packQty}</td>
								<td class="hide" id="tbl_cnvrsn">${expiryDetailsDTO.conversion}</td>
								<td class="hide" id="tbl_lqty">${expiryDetailsDTO.looseQty}</td>
								<td class="hide" id="tbl_free">${expiryDetailsDTO.freeQty}</td>
								<td class="hide" id="tbl_amount">${expiryDetailsDTO.amount}</td>
								<td class="hide" id="tbl_netcontent">${expiryDetailsDTO.netContent}</td>
								<td class="hide" id="tbl_calclqty">${expiryDetailsDTO.calculateLooseQty}</td>
								
								<td class="hide" id="tbl_taxperc">${expiryDetailsDTO.taxPercentage}</td>
								<td class="hide" id="tbl_discperc">${expiryDetailsDTO.discPer}</td>
								<td class="hide" id="tbl_disc">${expiryDetailsDTO.disc}</td>
								<td class="hide" id="tbl_taxamt">${expiryDetailsDTO.taxAmount}</td>
								<td class="hide" id="tbl_totamt">${expiryDetailsDTO.totAmount}</td>
								<td class="hide" id="tbl_netamt">${expiryDetailsDTO.netAmount}</td>
								<td class="hide" id="tbl_taxid">${expiryDetailsDTO.taxId}</td>
								<td class="hide" id="tbl_taxtypeid">${expiryDetailsDTO.taxTypeId}</td>  
							   
							   
							</tr> 
						</c:forEach>
					</c:if> 
				</tbody>
			</table>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<p></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			 <input type="hidden" id="creditor_code1" value="<spring:message code="accgroup.jsp.creditor_code" text="SCR" />">
				  <input type="hidden" id="exp_code1" value="<spring:message code="accgroup.jsp.expire_code" text="EXP" />">
		
		<fieldset class="col-lg-12 col-md-12 col-sm-12"> 
		<c:if test="${sesloggedinUser.is_account == 1}">	
		<legend>Account</legend>
			<table width="100%">
			   <tr>
					<td class="font-bold"><spring:message code="accgroup.jsp.drby" text="Dr/By" />:</td>
				    <td class="font-bold">
				       <input type="hidden" id="creditor_legder_id" value="0">
						<input type="text" class="form-control" style="margin-bottom: 2px;" id="creditor_legder_id_val" name="creditor_legder_id_val"  readonly >
				    </td>
				 	<td class="font-bold"><spring:message code="accgroup.jsp.debit_amount" text="Debit Amount" />:</td>
				    <td class="font-bold">
				    <input type="text" class="form-control" style="margin-bottom: 2px;"  onkeydown="numcheck(event)" id="dr_amount" name="dr_amount" readonly placeholder="<spring:message code="accgroup.jsp.debit_amount" text="Debit Amount" />"></td>
				</tr>
			   <tr>
					<td class="font-bold"><spring:message code="accgroup.jsp.crby" text="Cr/By" />:</td>
				    <td class="font-bold">  
				    <input type="hidden"  id="exp_ledger_id" value="0">
				  
					<input type="text" class="form-control-trx" style="margin-bottom: 2px;" id="exp_ledger_id_val" name="exp_ledger_id_val"   readonly></td>
				 	<td class="font-bold"><spring:message code="accgroup.jsp.credit_amount" text="Credit Amount" />:</td>
				    <td class="font-bold"><input type="text" class="form-control-trx" style="margin-bottom: 2px;"  onkeydown="numcheck(event)" readonly  id="cr_amount" name="cr_amount" placeholder="<spring:message code="accgroup.jsp.credit_amount" text="Credit Amount" />"></td>
				</tr>
			
				<tr>
					<td class="font-bold"><spring:message code="purinvdet.jsp.remarks" text="Remarks" />:</td>
					<td colspan="2" style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="remarks" value="${expiryHeader.remarks}"></td>
					<c:if test="${expiryHeader.isPosted !=1}">
					<td colspan="4" style="padding-top: 4px;">
						<button style="padding: 5px 8px;" class="btn btn-info btn-sm" type="button" onclick="newExpiryInv()"><spring:message code="cmn.jsp.new" text="NEW" /></button>
						<c:if test="${expiryId==0}">
							<button style="padding: 5px 8px;" class="btn btn-success btn-sm" type="button" id="save_btn" onclick="getcheckedexpiryitemlist()"><spring:message code="cmn.jsp.btn.savecaps" text="SAVE" /></button>
						</c:if>
						<c:if test="${expiryId!=0}">
							<button style="padding: 5px 8px;" class="btn btn-info btn-sm" type="button" onclick="postExpiryInv()"><spring:message code="cmn.jsp.btn.post" text="POST" /></button>
							<button style="padding: 5px 8px;text-transform: uppercase;" class="btn btn-success btn-sm" type="button" id="save_btn" onclick="getcheckedexpiryitemlist()"><spring:message code="cmn.jsp.btn.update" text="UPDATE" /></button>
							<button style="padding: 5px 8px;" class="btn btn-danger btn-sm" type="button" id="delButtId" onclick="deleteExpiryInv()">
								<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
							</button>
						</c:if>						
					</td>
					</c:if>
				</tr>
			</table>
    </c:if>
	<c:if test="${sesloggedinUser.is_account == 0}">	
		<table width="100%">
			   <tr style="display:none;">
					<td class="font-bold"><spring:message code="accgroup.jsp.drby" text="Dr/By" />:</td>
				    <td class="font-bold">
				       <input type="hidden" id="creditor_legder_id" value="0">
						<input type="text" class="form-control" style="margin-bottom: 2px;" id="creditor_legder_id_val" name="creditor_legder_id_val"  readonly >
				    </td>
				 	<td class="font-bold"><spring:message code="accgroup.jsp.debit_amount" text="Debit Amount" />:</td>
				    <td class="font-bold">
				    <input type="text" class="form-control" style="margin-bottom: 2px;"  onkeydown="numcheck(event)" id="dr_amount" name="dr_amount" readonly placeholder="<spring:message code="accgroup.jsp.debit_amount" text="Debit Amount" />"></td>
				</tr>
			   <tr style="display:none;">
					<td class="font-bold"><spring:message code="accgroup.jsp.crby" text="Cr/By" />:</td>
				    <td class="font-bold">  
				    <input type="hidden"  id="exp_ledger_id" value="0">
				  
					<input type="text" class="form-control-trx" style="margin-bottom: 2px;" id="exp_ledger_id_val" name="exp_ledger_id_val"   readonly></td>
				 	<td class="font-bold"><spring:message code="accgroup.jsp.credit_amount" text="Credit Amount" />:</td>
				    <td class="font-bold"><input type="text" class="form-control-trx" style="margin-bottom: 2px;"  onkeydown="numcheck(event)" readonly  id="cr_amount" name="cr_amount" placeholder="<spring:message code="accgroup.jsp.credit_amount" text="Credit Amount" />"></td>
				</tr>
			
				<tr>
					<td class="font-bold" style="display:none;"><spring:message code="purinvdet.jsp.remarks" text="Remarks" />:</td>
					<td colspan="2" style="padding: 2px 5px 0 0 ; display:none;"><input class="form-control-trx" type="text" id="remarks" value="${expiryHeader.remarks}"></td>
					<c:if test="${expiryHeader.isPosted !=1}">
					<td colspan="4" style="padding-top: 4px;text-align: right">
						<button style="padding: 5px 8px;" class="btn btn-info btn-sm" type="button" onclick="newExpiryInv()"><spring:message code="cmn.jsp.new" text="NEW" /></button>
						<c:if test="${expiryId==0}">
							<button style="padding: 5px 8px;" class="btn btn-success btn-sm" type="button" id="save_btn" onclick="getcheckedexpiryitemlist()"><spring:message code="cmn.jsp.btn.savecaps" text="SAVE" /></button>
						</c:if>
						<c:if test="${expiryId!=0}">
							<button style="padding: 5px 8px;" class="btn btn-info btn-sm" type="button" onclick="postExpiryInv()"><spring:message code="cmn.jsp.btn.post" text="POST" /></button>
							<button style="padding: 5px 8px;text-transform: uppercase;" class="btn btn-success btn-sm" type="button" id="save_btn" onclick="getcheckedexpiryitemlist()"><spring:message code="cmn.jsp.btn.update" text="UPDATE" /></button>
							<button style="padding: 5px 8px;" class="btn btn-danger btn-sm" type="button" id="delButtId" onclick="deleteExpiryInv()">
								<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
							</button>
						</c:if>						
					</td>
					</c:if>
				</tr>
			</table>
	</c:if>
	</fieldset>
		</div>
	</div>
</section>

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
								<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="vendor.jsp.addrs" text="Address" /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<textarea id="addrs" class="form-control" rows="2"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="vendor.jsp.pin" text="Pin" /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<input type="text" id="pin" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.city" text="City" /></label>
								<div class="col-sm-8">
									<input type="text" id="city" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.state" text="State" /></label>
								<div class="col-sm-8">
									<input type="text" id="state" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.cntry" text="Country" /></label>
								<div class="col-sm-8">
									<input type="text" id="cntry" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label" id="phn1_label"><spring:message code="vendor.jsp.phn1" text="Contact No. 1" /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<input type="text" id="phn1" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label" id="discount_label"><spring:message code="vendor.jsp.discount" text="Discount(%)" /></label>
								<div class="col-sm-8">
									<input type="text" id="discount" class="form-control" />
								</div>
							</div>
						</div>


						<div class="col-sm-6 col-sm-6 ">
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
								<label class="col-sm-4 col-sm-4 control-label" id="rgstratn_label"><spring:message code="vendor.jsp.rgstratn" text="Registration No." /> <span class="required_star">*</span></label>
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
								<label class="col-sm-4 col-sm-4 control-label" id="licenceNo_label"><spring:message code="vendor.jsp.licenceno" text="Licence No" /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<input type="text" id="licenceNo" class="form-control"  placeholder="<spring:message code="cmn.jsp.egdlno" text="E.g. D.L No." />"/>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" id="vendor_id" value=""></input>
					<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="vendorAddAlertMsg"></div>
				</div>
				<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
					<button type="button" class="btn btn-default" data-dismiss="modal">
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


<!-- Item exists start -->

	<div class="modal fade" id="itemExistsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
							        <h4 class="modal-title" id="myModalLabel"><spring:message code="footer.jsp.alert" text="Alert!"></spring:message></h4>
							      </div>
							      <div class="modal-body">
							        <spring:message code="itemmstr.jsp.exist.error" text="Item already exist, please try other." />
								  </div>
							      <div class="modal-footer">
							        <button type="button" onclick="ExistsOk()" data-dismiss="modal" class="btn btn-theme"><spring:message code="footer.jsp.btn.ok" text="OK" /></button>
							      </div>
							    </div>
							  </div>
	</div>      
<!-- Item exists end -->
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/stock/manualstockexpissue.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/inventory/stock/stock_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	var selectedDistributor = "${selecteddistributor}";
	var expiryId=${expiryId};
	var isAcccount = ${sesloggedinUser.is_account};
	var formDate = "${sesloggedinUser.startDate}";
	function showConfirmModal()
	{
		$('#confirmMessageModal').modal('show');
	}
	var s=0;
	var total_sale_rate=0;
	var myVar;
	$(document).ready(function() {
		setAllCheck();
	     myVar = setInterval("clearMessage()", 30000);
		getvendorledger_expire($('#exp_code1').val(),0,0,1); 
		if (expiryId!=0) {
			$('#expitem > tbody > tr').each(function() {
				
				 var itemid = this.id;
				 console.log(itemid);
					if ($("#" + itemid + "_modretcheck").is(":checked")) {
						s++;
						 console.log("chec l");
							if (s==1) {// for vendor call first
								 
								getvendorledger_expire($('#creditor_code1').val(),0,$("#" + itemid).find('#expdistributor').val(),0);// for expe	
							}
					 
							
							var itemPrice = Number($("#" + itemid).find('#exp_qty').val()) *  parseFloat($("#" + itemid).find('#tbl_rate').text());     
							total_sale_rate= parseFloat(total_sale_rate)+(parseFloat(itemPrice));
					}
			 
			});// end loop
			
			$('#dr_amount').val(total_sale_rate.toFixed(2));
			$('#cr_amount').val(total_sale_rate.toFixed(2));
			
		}
		
        $("#exp").keyup(function(){
            if ($(this).val().length == 2){
            	if($(this).val()<=12 && $(this).val()>0){
            		$(this).val($(this).val() + "/");
            	}else{
            		$(this).val("");
            	}
                
            }
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
			//endDate : currentDate,
			autoclose: true,
		});
		
	
	
		var table =$('#expitem').DataTable({
			"lengthChange" : false,
		    "searching": false,
		    "paging": false,
		    "bInfo" : false,
		     
		
		     });
	
		 
		$("#item_name").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/item/autocompleteitem.htm",
						type : "GET",
						data : {
							tagName : request.term
						},
						dataType : "json",
						success : function(data) {
						      response($.map(data, function(v) {
								return {
									label : v.itemName,
									itemCode : v.itemId,
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
			      console.log(JSON.stringify(ui));
				$("#item_id").val(ui.item.itemCode);
				$("#item_code").val(ui.item.items.itemCode);
				
				},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});


	$("#item_code").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/item/autocompleteitembycode.htm",
					type : "GET",
					data : {
						tagName : request.term
					},
					dataType : "json",
					success : function(data) {
					      response($.map(data, function(v) {
							return {
								label : v.itemCode,
								itemCode : v.itemId,
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
		      console.log(JSON.stringify(ui));
			$("#item_id").val(ui.item.itemCode);
			$("#item_name").val(ui.item.items.itemName);
			
			},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});


	   
	      
	    	  
	});
	function clearMessage(){
		document.getElementById('alertMsg').innerHTML ="";
	}
</script>
