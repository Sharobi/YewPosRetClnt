<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; max-height: 250px; width: 300px; word-break: break-all;
}
-->

table.table.table-striped tr.freeiai td {
    background-color:#ffb3b3;
}

#rowmarg{
margin-left: -28px;
}
 #toppanel{
      margin-top: -9px;
      margin-right:-11px;
 }
 #twondpanel{
margin-top: -10px;
margin-bottom:  1px;
margin-right:-11px;
}

#detail_table{

margin-right:-11px;
}
#finaltable{

margin-right:-11px;
margin-left: 0px;
}
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div id="rowmarg" class="row">
		<div class="col-lg-12">
			<p></p>
			<input type="hidden" id="isexclusiversp" value="${sesloggedinStore.isExclusive}"/>
			<input type="hidden" id="item_batchWiseStock_req_ret" value="0" /><input type="hidden" id="item_expiryDateRequired_req_ret" value="0" /><input type="hidden" id="item_expiryMonthRequired_req_ret" value="0" /><input type="hidden" id="item_dualStockRequired_req_ret" value="0" /><input type="hidden" id="item_mrpRequired_req_ret" value="0" /><input type="hidden" id="item_locationRequired_req_ret" value="0" /><input type="hidden" id="item_priceListRequired_req_ret" value="0" /><input type="hidden" id="item_serialNo_req_ret" value="0" /><input type="hidden" id="item_serialNo_list_ret" value="0" /><input type="hidden" id="item_expdate_rsp" value="0" />
			<input type="hidden" id="isMrpEnableFlag" value="${sessionScope.sesloggedinUser.isMrpEnable}" />
			<c:set var="mrpEnableFlag" value="${sessionScope.sesloggedinUser.isMrpEnable}" />
			<input  type="hidden" name="is_free_rsp" id="is_free_rsp" value="${userinfo.isFree}">
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter1">
			<input type="hidden" id="useridrsp" value="${sessionScope.sesloggedinUser.id}"> <input type="hidden" id="companyIdrsp" value="${sessionScope.sesloggedinUser.companyId}"> <input type="hidden" id="storeIdrsp" value="${sessionScope.sesloggedinUser.storeId}"> <input type="hidden" id="finyrIdrsp" value="${sessionScope.sesloggedinUser.finyrId}">
			<div id="toppanel" class="panel-trx panel-default">

				<!-- Confirm Print Sale Return Modal Starts -->

				<div class="modal fade" id="confirmPrintSaleReturnModalrsp" style="text-align: center;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
									<spring:message code="rtrnmemo.jsp.addsuccmsg" text="Sales Return Invoice successfully added!" />
									<br>
									<div class="col-sm-12" style="text-align: center; font-weight: 700;">
										<spring:message code="rtrnmemo.jsp.printsalereturn" text="Print Sale Return" />
										: <input type="checkbox" id="printSaleReturn" name="printSaleReturn" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
									</div>
								</div>
								<input type="hidden" id="confirmvalrsp" value="">
							</div>
							<div class="modal-footer" style="border-top: 0px;">
								<button type="button" onclick="targetURLrsp()" data-dismiss="modal" class="btn btn-theme">
									<spring:message code="footer.jsp.btn.ok" text="OK" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Confirm Print Purchase Modal ends -->


				<div class="panel-body-trx">
					<table>
						<c:if test="${saleRetId==0}">
							<tr align="center">
								<td><spring:message code="pos.jsp.date" text="Date" /></td>
								<td colspan="2"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.billno" text="Bill No" /></td>
								<td></td>
								<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
								<td><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
								<td class="hide"><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></td>
							</tr>

							<tr>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" class="form-control-trx" id="datersp" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
									</div>
								</td>
								<td width="9%" style="padding: 0 1px;"><input class="form-control-trx" type="text" value="${retDefaultPrefix}/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>
								<td style="padding: 0 1px;><input class="form-control-trx" type="text" id="" readonly="readonly" value=""><input type="hidden" id="saleretId" value="0"></td>
								<%-- <td width="8%" style="padding: 0 1px;"><input
									class="form-control-trx" type="text"
									value="SIM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>   onchange="getesitype()"--%>

								<td style="padding: 0 1px;"><select class="form-control-trx" name="mulSeriesPrefix" id="retmemoDoc">

														<c:if test="${!empty serialnumberlist}">
															<c:forEach items="${serialnumberlist}" var="slno">
																<option value="${slno.mulSeriesPrefix}">${slno.mulSeriesPrefix}</option>
															</c:forEach>
														</c:if>
												</select>
<!-- 												<input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="SIM" size="4" > -->
												</td>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoDocSlash" value="/" readonly></td>
								<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoSlash" value="/" readonly></td>

								<td style="padding: 0 1px;">
									<div class="input-group">
										<input class="form-control-trx" type="text" id="invnorsp" placeholder="Enter invoice no " value="">
										<div class="input-group-addon" title="Press enter /click for Search " onclick="getRetSaleDetbyInvId(document.getElementById('invnorsp').value,document.getElementById('retmemoFinyr').value,document.getElementById('retmemoDoc').value)">
											<span class="fa fa-search"></span>
										</div>
									</div>

								</td>
								<td style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustph" type="text"></td>
								<input id="saleretcustid" type="hidden" value="0">
								</td>
								<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustname" type="text"></td>
								<td width="20%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" id="saleretdocname" type="text"></td>
								<input id="saleretdocid" type="hidden">
								</td>
							</tr>
						</c:if>
						<c:if test="${saleRetId!=0}">
							<tr align="center">
								<td><spring:message code="pos.jsp.date" text="Date" /></td>
								<td colspan="1"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.billno" text="Bill No" /></td>
								<td></td>
								<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
								<td><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
								<td class="hide"><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></td>
							</tr>

							<tr>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<fmt:parseDate value="${saleReturnDTO.invDate}" var="salesRetInvDate" pattern="MMM dd, yyyy" />
										<input type="text" class="form-control-trx" id="datersp" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${salesRetInvDate}" />">
										<%-- 										<input type="text" class="form-control-trx" id="date" value="${saleReturnDTO.invDate}" /> --%>
									</div>
								</td>
								<td width="8%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" value="SRM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" readonly="readonly" id="saleretinvno" value="${saleReturnDTO.invNo}"><input type="hidden" id="saleretId" value="${saleReturnDTO.saleReturnId}"></td>
								<%-- <td width="8%" style="padding: 0 1px;"><input
									class="form-control-trx" type="text"
									value="SIM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td> --%>

								<td style="padding: 0 1px;">
								<select class="form-control-trx" name="mulSeriesPrefix" id="retmemoDoc" onchange="getesitype()">

														<c:if test="${!empty serialnumberlist}">
															<c:forEach items="${serialnumberlist}" var="slno">
																<option value="${slno.mulSeriesPrefix}">${slno.mulSeriesPrefix}</option>
															</c:forEach>
														</c:if>
												</select>
<!-- 								<input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="SIM" size="4" > -->
								</td>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoDocSlash" value="/" readonly></td>
								<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoSlash" value="/" readonly></td>

								<td style="padding: 0 1px;">
									<div class="input-group">
										<input class="form-control-trx" type="text" id="invnorsp"  placeholder="Enter  last invoice no " value="">
										<div class="input-group-addon">
											<span class="fa fa-search" onclick="getRetSaleDetbyInvId(document.getElementById('invnorsp').value,document.getElementById('retmemoFinyr').value,document.getElementById('retmemoDoc').value)"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustph" type="text" value="${saleReturnDTO.customerPhone}" readonly></td>
								<input id="saleretcustid" type="hidden" value="${saleReturnDTO.customerId}">
								</td>
								<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustname" type="text" value="${saleReturnDTO.customerName}" readonly></td>
								<td width="20%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" id="saleretdocname" type="text" value="${saleReturnDTO.doctorName}" readonly></td>
								<input id="saleretdocid" type="hidden" value="${saleReturnDTO.doctorId}">
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter2">
			<div id="twondpanel" class="panel-trx panel-default">
				<div  class="panel-body-trx" id="header_div_rsp">
					<table>
						<tr align="center" >
							<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
							<td>Barcode</td>
							<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
							<th id="free_label_rsp" class="${userinfo.isFree==1 ?'':'hide'}" ><spring:message code="purinvdet.jsp.free" text="Free" /></th>
							<td id="lqty_label" class="hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>

							<td class="hide"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>

							<td class="hide"><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
							<td class="hide"><spring:message code="rtrnmemo.jsp.srate" text="S.Rate" /></td>
							<td class="hide"><spring:message code="rtrnmemo.jsp.srate/ls" text="S.Rate/Ls" /></td>


							<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>
						</tr>
						<tr>
							<!-- 						data-toggle="modal" data-target="#itemsaledetailModal" -->
							<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_name_rsp" placeholder="Please type atleast 2 characters">
							<input type="hidden" id="item_id_rsp" value="0"></td>
							<td width="12%"><input class="form-control-trx" type="text"  placeholder="Scan Barcode" id="item_barcode_rsp"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" onkeydown="numcheck(event)"  type="text" id="item_rpqty_rsp" value="0"></td>
							<td style="padding: 0 1px;" id="free_qty_rsp"  class="${userinfo.isFree==1 ?'':'hide'}">
							<input class="form-control-trx"  type="text" onkeydown="numcheck(event)"  id="item_rfqty_rsp" value="0">
							<input class="form-control-trx" type="hidden" id="item_lotadjasment_rsp" value="0">
							</td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx"  type="text" id="item_rlqty_rsp" value="0"></td>

							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_bat_rsp" readonly></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_exp_rsp" readonly></td>

							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_mrpperpack_rsp" readonly></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_mrp_rsp" readonly></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_rate_rsp" readonly></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_rateperloose_rsp" readonly></td>

							<td style="padding: 0 1px;" ><input class="form-control-trx" type="text" id="item_netamt_rsp" value="0" readonly></td>
						<td colspan="2">
								<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg_rsp"></div>
							</td>
							<c:if test="${saleReturnDTO.isPosted ==0}">
								<td><button class="btn btn-success btn-sm" type="button" onclick="addOrUpdateItemToDetailsTablersp(0)" id="add_btn_rsp">
										<spring:message code="cmn.jsp.addcaps" text="ADD" />
									</button>
									<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" onclick="addOrUpdateItemToDetailsTablersp(1)" id="edit_btn_rsp">
										<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
									</button></td>
								<td><button class="btn btn-primary btn-sm" type="button" onclick="clearHeaderDivrsp()">
										<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
									</button></td>
							</c:if>
						</tr>
						<tr align="center" >
							<td class="hide"><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></td>
							<td colspan="3" class="hide"><spring:message code="itemmstr.jsp.content" text="Content" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.discprcnt" text="D%" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
							<td class="hide"><spring:message code="purretrn.jsp.bpqty" text="B.P.Qty" /></td>
							<td class="hide"><spring:message code="purretrn.jsp.blqty" text="B.L.Qty" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.pqty" text="P.Qty Hide" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty Hide" /></td>
						</tr>
						<tr>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_mfg_rsp" readonly></td>
							<td colspan="3" style="padding: 0 1px;" class="hide"><input class="form-control-trx" id="item_content_rsp" title="" type="text" readonly></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_disper_rsp" value="0" readonly></td>
							<td style="padding: 0 1px;" id="ratio_val_rsp" class="hide"><input class="form-control-trx" type="text" id="item_conv_rsp" readonly></td>

							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_pqty_rsp" value="0" readonly></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_lqty_rsp" value="0" readonly></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="hidden" id="item_rpqty_hide_rsp" value="0"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="hidden" id="item_rlqty_hide_rsp" value="0">
							<input class="form-control-trx" type="hidden" id="item_rfqty_hide_rsp" value="0">
							<input type="hidden" id="item_taxId_rsp">
							<input type="hidden" id="item_taxPercentage_rsp">
							<input type="hidden" id="item_isGroupTax_rsp">
							<input type="hidden" id="item_CalcTaxAmt_rsp" value="0">
							<input type="hidden" id="item_discount_rsp">
							<input type="hidden" id="item_isDiscount_rsp">
							<input type="hidden" id="item_maxDiscountLimit_rsp" value="0">
							<input type="hidden" id="item_taxMode_rsp" value="0">
							<input type="hidden" id="item_hsnCode_rsp" value="0">
							<input type="hidden" id="item_taxTypeId_rsp">
							</td>
							<%-- <td colspan="2">
								<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg_rsp"></div>
							</td>
							<c:if test="${saleReturnDTO.isPosted ==0}">
								<td><button class="btn btn-success btn-sm" type="button" onclick="addOrUpdateItemToDetailsTablersp(0)" id="add_btn_rsp">
										<spring:message code="cmn.jsp.addcaps" text="ADD" />
									</button>
									<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" onclick="addOrUpdateItemToDetailsTablersp(1)" id="edit_btn_rsp">
										<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
									</button></td>
								<td><button class="btn btn-primary btn-sm" type="button" onclick="clearHeaderDivrsp()">
										<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
									</button></td>
							</c:if> --%>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div style="overflow: auto;" id="detail_table">
				<table id="salretitem" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
							<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
							<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
							<th class="hide"><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
							<th class="${userinfo.isFree==1 ?'':'hide'}" ><spring:message code="purinvdet.jsp.free" text="Free" /></th>

							<th class="numeric hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
<%-- 							<th class="numeric"><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></th> --%>
							<c:if test="${mrpEnableFlag==1}">
							<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
							</c:if>
							<c:if test="${mrpEnableFlag==0}">
							<th class="numeric hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
							</c:if>
<%-- 							<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th> --%>
							<th class="numeric"><spring:message code="rtrnmemo.jsp.srate/ls" text="Rate/Ls" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.disc" text="Disc" />%</th>
							<th class="numeric"><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>
						</tr>
					</thead>
					<tbody id="salerettabitemdetails">
						<c:if test="${!empty saleReturnDetailsDTOs }">
							<c:forEach items="${saleReturnDetailsDTOs}" var="saleRetDetail">
								<tr class="${saleRetDetail.itemFreeAgainstItem==1?'freeiai':''}" id="${saleRetDetail.itemUniqueKey}" style='cursor: pointer;' onclick="javascript:itemHeaderDivViewrsp(this.id);">
									<c:set var="tblslnoseditret" value=""/>
									<c:set var="checkedtblslnoseditret" value=""/>
									<td id="salerettabname">${saleRetDetail.itemName}</td>
						    	    <td id="salerettabsalesmanIdComPer" class="numeric hide">${saleRetDetail.salesmanId}_${saleRetDetail.salesmanFactor}_${saleRetDetail.salesmanAmount}</td>

									<td id="salerettabbat">${saleRetDetail.batchNo}</td>
									<td id="salerettabexpdt">${saleRetDetail.expiryDateFormat}</td>
									<td id="salerettabmanname" class="hide">${saleRetDetail.manufacturerName}</td>
									<td class="numeric" id="salerettabpqty">${saleRetDetail.packQty}</td>
									<td class="numeric ${userinfo.isFree==1 ?'':'hide'}" id="salerettabfqty">${saleRetDetail.freeQty}</td>


									<td class="numeric hide" id="salerettablqty">${saleRetDetail.looseQty}</td>
									<td class="numeric" id="salerettabconv">${saleRetDetail.conversion}</td>
									<c:if test="${mrpEnableFlag==1}">
									<td class="numeric" id="salerettabmrppack"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.mrp}" /></td>
									</c:if>
									<c:if test="${mrpEnableFlag==0}">
									<td class="numeric hide" id="salerettabmrppack"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.mrp}" /></td>
									</c:if>
<%-- 									<td class="numeric" id="salerettabmrp"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.mrpPerUnit}" /></td> --%>
									<td class="numeric" id="salerettabrateperunit"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.ratePerUnit}" /></td>
									<td class="numeric" id="salerettabdiscperc"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.discPer}" /></td>
									<td class="numeric" id="salerettabtotamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.totAmount}" /></td>
									<td><c:if test="${saleReturnDTO.isPosted ==0}">
											<button class="btn btn-theme04 btn-xs" id="${saleRetDetail.itemUniqueKey}" title="Delete item" onclick='javascript:showSelRetItemDelModal(this.id);'>
												<i class="fa fa-trash-o "></i>
											</button>
										</c:if></td>
									<td class="hide" id="salepqty_rsp">${saleRetDetail.remainingPackQty}</td>
									<td class="hide" id="salelqty_rsp">${saleRetDetail.remainingLooseQty}</td>
									<td id="salerettabpunitid" class="hide">${saleRetDetail.packUnitId}</td>
									<td id="salerettablunitid" class="hide">${saleRetDetail.looseUnitId}</td>
									<td id="salerettabcontent" class="hide">${saleRetDetail.contentName}</td>
									<td id="salerettabrate" class="hide">${saleRetDetail.rate}</td>
									<td id="salerettabdisamt" class="hide">${saleRetDetail.disc}</td>
									<td id="salerettabed" class="hide">${saleRetDetail.edPer}</td>
									<td id="salerettabedamt" class="hide">${saleRetDetail.ed}</td>
									<td id="salerettabtax" class="hide">${saleRetDetail.taxPer}</td>
									<td id="salerettabtaxamt" class="hide">${saleRetDetail.tax}</td>
									<td id="salerettabvat" class="hide">${saleRetDetail.vatPer}</td>
									<td id="salerettabvatamt" class="hide">${saleRetDetail.vat}</td>
									<td id="salerettabsaleid" class="hide">${saleRetDetail.saleId}</td>
									<td id="salerettabsaleinvno" class="hide">${saleRetDetail.saleInvNo}</td>
									<td class="hide" id="salerettabpqtyhide">${saleRetDetail.hidePackQty}</td>
									<td class="hide" id="salerettablqtyhide">${saleRetDetail.hideLooseQty}</td>
									<td id='salerettabsku' class='hide'>${saleRetDetail.sku}</td>
									<td id='salerettabtaxId' class='hide'>${saleRetDetail.taxId}</td>
									<td id='salerettabtaxPercentage' class='hide'>${saleRetDetail.taxPercentage}</td>
									<td id='salerettabitemTaxAmount' class='hide'>${saleRetDetail.taxAmount}</td>
									<td id='salerettabisGroupTax' class='hide'>${saleRetDetail.isGroupTax}</td>
									<td id='salerettabtaxMode' class='hide'>${saleRetDetail.taxMode}</td>
									<td id='salerettabhsnCode' class='hide'>${saleRetDetail.hsnCode}</td>
									<td class="numeric hide" id="salerettabmrp" ><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.mrpPerUnit}" /></td>
									<!-- 									<td class="hide" id="salerettabpqtyhide">0</td> -->
									<!-- 									<td class="hide" id="salerettablqtyhide">0</td> -->
									<td id='salerettabslnoreq' class='hide'>${saleRetDetail.serialNoRequired}</td>
									<td id='salerettabitembatchWiseStockreq' class='hide'>${saleRetDetail.batchWiseStock}</td>
									<td id='salerettabitemexpiryDateRequiredreq' class='hide'>${saleRetDetail.expiryDateRequired}</td>
									<td id='salerettabitemexpiryMonthRequiredreq' class='hide'>${saleRetDetail.expiryMonthRequired}</td>
									<td id='salerettabitemdualStockRequiredreq' class='hide'>${saleRetDetail.dualStockRequired}</td>
									<td id='salerettabitemmrpRequiredreq' class='hide'>${saleRetDetail.mrpRequired}</td>
									<td id='salerettabitemlocationRequiredreq' class='hide'>${saleRetDetail.locationRequired}</td>
									<td id='salerettabitempriceListRequiredreq' class='hide'>${saleRetDetail.priceListRequired}</td>
									<td id='salerettabitemlotadjasment' class='hide'>${saleRetDetail.itemLotAdjAmount}</td>
									<td id='salerettabitemagainstitem' class='hide'>${saleRetDetail.itemFreeAgainstItem}</td>
									<td id='salerettabtaxTypeId' class='hide'>${saleRetDetail.taxTypeId}</td>

									<c:if test="${!empty saleRetDetail.saleReturnDetailsSerialMapper}">
											<c:forEach items="${saleRetDetail.saleReturnDetailsSerialMapper}" var="saleretDetailsSerialMapper" varStatus="salesDetSerialMapper">
											<c:set var="tblslnoseditret" value="${tblslnoseditret}${saleretDetailsSerialMapper.uniqueIdentifierNo}," />
											<c:if test="${saleretDetailsSerialMapper.checkStatus=='1'}">
											<c:set var="checkedtblslnoseditret" value="${checkedtblslnoseditret}${saleretDetailsSerialMapper.uniqueIdentifierNo}," />
											</c:if>
											</c:forEach>
											<td id="salerettabitemslnolist" class="numeric hide">${tblslnoseditret}</td>
											<td id="checkedsalerettabitemslnolist" class="numeric hide">${checkedtblslnoseditret}</td>
									</c:if>

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
		<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail">


			<input type="hidden" id="dueties_and_tax_acc" value="<spring:message code="accgroup.jsp.duties_code" text="DT"/>">
		<input type="hidden" id="saleaccunt_group_code" value="<spring:message code="accgroup.jsp.saleac_code" text="SA"/>">
		<input type="hidden" id="roundoff_group_code" value="<spring:message code="accgroup.jsp.roun_code" text="ROD"/>">
		<input type="hidden" id="debitor_group_code" value="<spring:message code="accgroup.jsp.deb_code" text="SDE"/>">
		<input type="hidden" id="cash_in_hand_group_code" value="<spring:message code="accgroup.jsp.cash_code" text="CIH"/>">
         <input type="hidden" id="lot_adjas_group_code" value="<spring:message code="accgroup.jsp.lotadjs_group_code" text="LOT" />">
		<input type="hidden" id="duties_ledger_idf" value="">
		<input type="hidden" id="round_ledger_idf" value="">
		<input type="hidden" id="sales_ledger_idf" value="">
		<input type="hidden" id="debitor_ledger_idf" value="">
		<input type="hidden" id="lotAdjasment_ledger_idf" value="0">

	 	<input class="form-control-trx" type="hidden" id="sale_account_rsp"  readonly value="0">
	 		<input class="form-control-trx" type="hidden" id="debitor_amt_rsp" readonly value="0">


		<table   id="finaltable">
				<tr>


				</tr>
				<tr>
					<td ><spring:message code="purinvdet.jsp.itemcount" text="ItemCount" />:</td>
					<td width="8%" ><span id="totitmcount_rsp">1</span> </td>
					<td class="hide"><spring:message code="purinvdet.jsp.total" text="Total:" />:</td>

					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrossamt_rsp" value="${saleReturnDTO.grossAmount}" readonly></td>
					<td class="hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat:" /></td>
					<td style="padding-right: 5px;" class="hide" ><input class="form-control-trx" type="text" id="totvatamt_rsp" value="${saleReturnDTO.vatAmount}" tabindex="-1" readonly></td>
					<td class=""><spring:message code="pos.jsp.stax" text="Tot.Tax" /></td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="tottaxamt_rsp" value="${saleReturnDTO.taxAmount}" tabindex="-1" readonly></td>
					<td class="hide"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc:" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="" value="" readonly></td>
					<td  class=""><spring:message code="purinvdet.jsp.roff" text="R.Off:" />:</td>
					<td style="padding-right: 5px;" width="8%"><input class="form-control-trx" type="text" id="roundoff_rsp" value="<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${saleReturnDTO.roundoff}" />" readonly="readonly"></td>
					<td style=" width: 10%;"><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="rtrnmemo.jsp.retamt" text="Ret.Amt" />:</span></td>
					<td width="16%" style="padding-right: 5px;"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="totretamt_rsp" value="<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${saleReturnDTO.netAmount}" />" readonly></td>
				</tr>
				<tr>
					<td><spring:message code="rtrnmemo.jsp.reason" text="Reason"></spring:message>:</td>
					<td colspan="4" style="padding: 3px 5px 0 0"><input class="form-control-trx" id="remarks_rsp" type="text" value="${saleReturnDTO.remarks}"></td>

					<!--  for lot  adjasment -->
	                <td class="hide" id="flotid_rsp"><spring:message code="purinvdet.jsp.ltadj" text="Lt.Adj" /></td>
					<td style="padding-right: 5px;" class="hide" id="totltadj_rsp"><input class="form-control-trx" type="text" id="totlotadjasment_rsp" value="${saleReturnDTO.lotAdjAmount}" tabindex="-1" readonly></td>


					<td class="hide"><spring:message code="pos.jsp.spldscprcnt" text="Spl.Disc(%)" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="spcldiscperc_rsp" value="${saleReturnDTO.specialDiscPer}" onkeyup="calculateSpclDiscrsp()"></td>
					<td class="hide"><spring:message code="pos.jsp.spldscamnt" text="Spl.Disc.Amt" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="spcldisc_rsp" value="${saleReturnDTO.specialDiscAmount}" readonly></td>

					<td colspan="3" style="padding-top: 4px; padding-right: 4px;"><c:if test="${saleReturnDTO.isPosted ==0}">
							<c:if test="${menuByUserDTO.isAll==1}">
								<button style="padding: 5px 8px;" class="btn btn-primary btn-sm" type="button" onclick="saveOrUpdateSaleReturnInv()">
									<spring:message code="cmn.jsp.return" text="RETURN" />
								</button>
							</c:if>
							<button class="btn btn-success btn-sm" type="button" id="" onclick="openRetMemo()">
								<spring:message code="cmn.jsp.new" text="NEW" />
							</button>
							<c:if test="${menuByUserDTO.isView==1}">
								<button style="padding: 5px 8px;" class="btn btn-danger btn-sm" type="button" id="" onclick="deleteRetSalesInv()">
									<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
								</button>
							</c:if>
						</c:if></td>

				</tr>
			</table>
		<!-- <fieldset class="col-lg-12 col-md-12 col-sm-12">

		<input type="hidden" id="dueties_and_tax_acc" value="DT">
		<input type="hidden" id="saleaccunt_group_code" value="SA">
		<input type="hidden" id="roundoff_group_code" value="ROD">
		<input type="hidden" id="debitor_group_code" value="SDE">

		<input type="hidden" id="cash_in_hand_group_code" value="CIH">
		<legend class="hide">Account</legend>
			<table>
				<tr>


				</tr>

						 for account start
				<tbody><tr class="hide">
					<th colspan="2" width="8%"> </th>




					<th colspan="2" class="font-bold" width="40%">
					<input type="hidden" id="duties_ledger_id" value="132">
					<span id="duties_html">DUTIES &amp; TAXES - Duties and Taxes (Debit) </span>
					Duties & Taxes Account Debit
					<input type="text" id="dueties_and_tax" value="">
					</th>


					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="" value="" readonly></td>


					<th colspan="2" class="font-bold">
				     	<input type="hidden" id="round_ledger_id" value="133">
						<span id="round_html">ROUND OFF A/C - Round Off A/C</span>
					Round of Account (-)Credit/(+)Debit</th>

					<th class="font-bold ">
					<input type="hidden" id="sales_ledger_id" value="39">
					<span id="sales_html">SALES ACCOUNTS  - Sales Accounts (Debit) </span>


					</th>
					<th width="20%" class="font-bold ">

				   <input type="hidden" id="debitor_ledger_id" value="137">
				   	<span id="debitor_html">CASH IN HAND - Cash in Hand (Credit) </span>

					</th>



				</tr>
				 for  account  end

					<tr>
					<td width="8%" class="font-bold">ItemCount:</td>
					<td width="2%" class="font-bold"><span id="totitmcount_rsp">1</span></td>

					<td class="font-bold hide">Total:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrossamt_rsp" value="0.0" readonly=""></td>
					<td class="font-bold hide">Tot.VAT</td>
					<td style="padding-right: 5px;" class="hide">
					<input class="form-control-trx" type="text" id="totvatamt_rsp" value="0.0" tabindex="-1" readonly="">



					</td>
					<td class="font-bold ">Tot.TAX</td>
					<td style="padding-right: 5px;">


					<input class="form-control-trx" type="text" id="tottaxamt_rsp" value="0.0" tabindex="-1" readonly="">



					</td>
					<td class="font-bold hide">Tot.Disc:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="" value="" readonly=""></td>


					<td class="font-bold">R.Off:</td>
					<td style="padding-right: 5px;" width="8%">
					<input class="form-control-trx" type="text" id="roundoff_rsp" value="0.00" readonly="readonly"></td>
			 	<td class="hide"><span style="font-size: 16px; font-weight: bold; color: #d43f3a;">
			 	<input class="form-control-trx" type="text" id="sale_account_rsp" readonly="" value="0">

			 	</span></td >
					<td width="8%" style="padding-right: 5px;" class="hide">
					<input class="form-control-trx" type="text" id="debitor_amt_rsp" readonly="" value="0">



					</td>



				</tr>


				<tr>
					<td class="font-bold">Reason:</td>
					<td colspan="3" style="padding: 3px 5px 0 0"><input class="form-control-trx" id="remarks_rsp" type="text" value=""></td>

					<td><span style="font-size: 16px; font-weight: bold; color: #d43f3a;">Ret.Amt:</span></td>
					<td width="8%" style="padding-right: 5px;"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="totretamt_rsp" value="0.00" readonly=""></td>

					<td class="font-bold hide">Bill.Disc(%):</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="spcldiscperc_rsp" value="0.0" onkeyup="calculateSpclDiscrsp()"></td>
					<td class="font-bold hide">Bill.Disc.Amt:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="spcldisc_rsp" value="0.0" readonly=""></td>

					<td colspan="4" style="padding-top: 4px; padding-right: 4px;">

								<button style="padding: 5px 8px;" class="btn btn-primary btn-sm" type="button" onclick="saveOrUpdateSaleReturnInv()">
									RETURN
								</button>

							<button class="btn btn-success btn-sm" type="button" id="" onclick="openRetMemo()">
								NEW
							</button>

								<button style="padding: 5px 8px;" class="btn btn-danger btn-sm" type="button" id="" onclick="deleteRetSalesInv()">
									DEL
								</button>

						</td>

				</tr>
			</tbody></table>
			</fieldset> -->

		</div>

	</div>
</section>
<!--/wrapper -->

<!-- Modal Starts find the sale details by sale invoice id  in sale return -->
<div class="modal fade" id="saledetailModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="rtrnmemo.jsp.salereturnmodal.invno" text="Sale Details for " />
					<spring:message code="purinvdet.jsp.invno" text="Invoice No" />
					. <span id="searchmodinvno_rsp"> SIM/${sessionScope.sesloggedinUser.finyrCode}/</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="saledetnotfounddiv_rsp" class="hide">
					<span class="font-bold"><spring:message code="rtrnmemo.jsp.memonotfoundfornumbermsg" text="No Cash memo found for this invoice number" />.</span>
				</div>
				<div id="saledetfounddiv_rsp">
					<div>
						<span class="font-bold"><spring:message code="rtrnmemo.jsp.salereturnmodal.invdt" text="Invoice Date" /> : </span> <span id="searchmodinvdate_rsp">2017-02-09</span>
					</div>
					<div>
						<div class="col-md-6 col-sm-6" style="padding: 0px;">
							<span class="font-bold"><spring:message code="rtrnmemo.jsp.salereturnmodal.invamnt" text="Invoice Amount" /> : </span> <span id="searchmodtotamt_rsp">100.00</span>
						</div>
						<div class="col-md-6 col-sm-6">
							<span class="font-bold">Bill Disc(%): </span><span id="searchmodcustspcldisc_rsp" class="font-bold" style="color: #ed7ba3;">0.00</span>
						</div>
					</div>
					<div>
						<div class="col-md-6 col-sm-6" style="padding: 0px;">
							<span class="font-bold"><spring:message code="pos.jsp.custContact" text="Cust.Contact" /> : </span><span id="searchmodcustcont_rsp">9568034213</span>
						</div>
						<div class="col-md-6 col-sm-6">
							<span class="font-bold"><spring:message code="pos.jsp.custName" text="Cust. Name" /> : </span><span id="searchmodcustname_rsp">S Maity</span>
						</div>

					</div>
					<div >
					<span class="font-bold">Remarks :</span> <span id="searchmoddocname1_rsp"></span>
 					</div>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;" id="saledetmodtable_rsp">
					<table id="searchmodtable_rsp" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th class="${userinfo.isSaleman==1? '' :'hide'}"><spring:message code="salesman.jsp.name" text="Salesman Name" /></th>
								<th><spring:message code="rtrnmemo.jsp.salereturnmodal.batch" text="Batch No" /></th>
								<th><spring:message code="rtrnmemo.jsp.salereturnmodal.exp" text="Exp Dt" /></th>
								<th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th   class="${userinfo.isFree==1 ?'':'hide'}" ><spring:message code="purinvdet.jsp.free" text="Free" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.preretpqty" text="Pre Ret P.Qty" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.preretlqty" text="Pre Ret L.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.retpqty" text="Ret P.Qty" /></th>

								<th   class="${userinfo.isFree==1 ?'':'hide'}"  ><spring:message code="purretrn.jsp.freequt" text="Ret F.Qty" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.retlqty" text="Ret L.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.mrpls" text="MRP/Ls" /></th>
								<th><spring:message code="purinvdet.jsp.ratels" text="Rate/Ls" /></th>
								<th><spring:message code="purinvdet.jsp.retamt" text="Ret Amt" /></th>
							</tr>
						</thead>
						<tbody id="searchmodtbody_rsp">

						</tbody>
						<div>
							<span id="alertmessagecont_rsp" style="font-weight: bold; color: red;"> </span>
						</div>

					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" class="btn btn-primary" id="nocashmemofoundokbut_rsp" onclick="getmodretcheckeditemlistrsp()">
					<spring:message code="footer.jsp.btn.ok" text="Ok" />
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="itemsaledetailModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 800px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="rtrnmemo.jsp.salereturnmodal.invno" text="Sale Details for" />
					<span id="itemsaledetailitemname_rsp"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="itemsaledetnotfounddiv_rsp" class="hide">
					<span class="font-bold"><span id=""><spring:message code="rtrnmemo.jsp.memonotfoundmsg" text="No Cashmemo found for this item" /></span>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;" id="itemsaledetmodtable_rsp">
					<table id="" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>

								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th class="${userinfo.isSaleman==1? '' :'hide'}"><spring:message code="salesman.jsp.name" text="Salesman Name" /></th>
								<th><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></th>
								<th><spring:message code="pos.jsp.custName" text="Cust. Name" /></th>
								<th class="hide"><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></th>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th  class="${userinfo.isFree==1 ?'':'hide'}" ><spring:message code="purinvdet.jsp.free" text="Free" /></th>
								<th class="numeric hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th class="numeric"><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
								<th class="numeric" style="background-color: yellow;">Spcl Disc(%)</th>
							</tr>
						</thead>
						<tbody id="itemsearchmodtbody_rsp">

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

<div class="modal fade" id="currStkGraterModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.inputgrtsellmsg" text="Input quantity is greater than sell/remaining quantity." />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="closeCurrStkModalrsp()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="sameItemInvModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.itemalrdyaddedmsg" text="Item already added for this invoice." />
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="sameUserModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.returndfrntmemomsg" text="You are trying to return different user cashmemo." />
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="confirmModalPos_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmPosrsp()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal Ends -->
<div class="modal fade" id="noItemBarcodeModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				No item found against this(<span id="inputbarcode_rsp"></span>) barcode. Please check the input barcode.
			</div>
			<div class="modal-footer">

				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal Start -->

<div class="modal fade" id="confirmModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
				<input type="hidden" id="confirmId_rsp" value="">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmrsp()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->
<div class="modal fade" id="confirmMessageModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div id="confirmmessagecontrsp"></div>
				<input type="hidden" id="confirmvalrsp" value="">
				<input type="hidden" id="cnfrmCustName" />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="targetURLrsp()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="openSerialModalrsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
<%-- 					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message> --%>
                      Serial Number
				</h4>
			</div>
			<div class="modal-body">
			<div id='TextBoxesGrouprsp'>
			<div id="TextBoxDivrsp_1">
		      <label>SL No #1 : </label><input type='textbox' id='rsptextbox1' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate1' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "1" id='rspcheckbox1'></span>
		      <input type="hidden" id="allValrsp" />
		      <input type="hidden" id="allChkIDrsp" />
		      <input type="hidden" id="clkrsp" value="0"/>
		      <input type="hidden" id="qtyslrsp" value="0"/>
		      <input type="hidden" id="maxSlRatersp" value="0"/>
	        </div>
	        <div id="TextBoxDivrsp_2" class="hide">
		      <label>SL No #2 :  </label><input type='textbox' id='rsptextbox2' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate2' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "2" id='rspcheckbox2'></span>
	        </div>
	         <div id="TextBoxDivrsp_3" class="hide">
		      <label>SL No #3 :  </label><input type='textbox' id='rsptextbox3' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate3' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "3" id='rspcheckbox3'></span>
	        </div>
	         <div id="TextBoxDivrsp_4" class="hide">
		      <label>SL No #4 :  </label><input type='textbox' id='rsptextbox4' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate4' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "4" id='rspcheckbox4'></span>
	        </div>
	         <div id="TextBoxDivrsp_5" class="hide">
		      <label>SL No #5 :  </label><input type='textbox' id='rsptextbox5' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate5' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "5" id='rspcheckbox5'></span>
	        </div>
	         <div id="TextBoxDivrsp_6" class="hide">
		      <label>SL No #6 :  </label><input type='textbox' id='rsptextbox6' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate6' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "6" id='rspcheckbox6'></span>
	        </div>
	         <div id="TextBoxDivrsp_7" class="hide">
		      <label>SL No #7 :  </label><input type='textbox' id='rsptextbox7' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate7' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "7" id='rspcheckbox7'></span>
	        </div>
	        <div id="TextBoxDivrsp_8" class="hide">
		      <label>SL No #8 :  </label><input type='textbox' id='rsptextbox8' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate8' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "8" id='rspcheckbox8'></span>
	        </div>
	         <div id="TextBoxDivrsp_9" class="hide">
		      <label>SL No #9 :  </label><input type='textbox' id='rsptextbox9' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate9' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "9" id='rspcheckbox9'></span>
	        </div>
	         <div id="TextBoxDivrsp_10" class="hide">
		      <label>SL No #10 : </label><input type='textbox' id='rsptextbox10' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate10' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' class = "chkrsp" value = "10" style='zoom:1' id='rspcheckbox10'></span>
	        </div>
	         <div id="TextBoxDivrsp_11" class="hide">
		      <label>SL No #11 : </label><input type='textbox' id='rsptextbox11' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate11' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "11" id='rspcheckbox11'></span>
	        </div>
	         <div id="TextBoxDivrsp_12" class="hide">
		      <label>SL No #12 : </label><input type='textbox' id='rsptextbox12' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate12' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "12" id='rspcheckbox12'></span>
	        </div>
	         <div id="TextBoxDivrsp_13" class="hide">
		      <label>SL No #13 : </label><input type='textbox' id='rsptextbox13' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate13' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "13" id='rspcheckbox13'></span>
	        </div>
	         <div id="TextBoxDivrsp_14" class="hide">
		      <label>SL No #14 : </label><input type='textbox' id='rsptextbox14' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate14' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "14" id='rspcheckbox14'></span>
	        </div>
	         <div id="TextBoxDivrsp_15" class="hide">
		      <label>SL No #15 : </label><input type='textbox' id='rsptextbox15' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate15' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "15" id='rspcheckbox15'></span>
	        </div>
	         <div id="TextBoxDivrsp_16" class="hide">
		      <label>SL No #16 : </label><input type='textbox' id='rsptextbox16' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate16' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "16" id='rspcheckbox16'></span>
	        </div>
	         <div id="TextBoxDivrsp_17" class="hide">
		      <label>SL No #17 : </label><input type='textbox' id='rsptextbox17' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate17' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "17" id='rspcheckbox17'></span>
	        </div>
	         <div id="TextBoxDirspv_18" class="hide">
		      <label>SL No #18 : </label><input type='textbox' id='rsptextbox18' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate18' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "18" id='rspcheckbox18'></span>
	        </div>
	         <div id="TextBoxDivrsp_19" class="hide">
		      <label>SL No #19 : </label><input type='textbox' id='rsptextbox19' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate19' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "19" id='rspcheckbox19'></span>
	        </div>
	         <div id="TextBoxDivrsp_20" class="hide">
		      <label>SL No #20 : </label><input type='textbox' id='rsptextbox20' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate20' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "20" id='rspcheckbox20'></span>
	        </div>
	        <div id="TextBoxDivrsp_21" class="hide">
		      <label>SL No #21 : </label><input type='textbox' id='rsptextbox21' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate21' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "21" id='rspcheckbox21'></span>
	        </div>
	        <div id="TextBoxDivrsp_22" class="hide">
		      <label>SL No #22 : </label><input type='textbox' id='rsptextbox22' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate22' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "22" id='rspcheckbox22'></span>
	        </div>
	        <div id="TextBoxDivrsp_23" class="hide">
		      <label>SL No #23 : </label><input type='textbox' id='rsptextbox23' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate23' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "23" id='rspcheckbox23'></span>
	        </div>
	        <div id="TextBoxDivrsp_24" class="hide">
		      <label>SL No #24 : </label><input type='textbox' id='rsptextbox24' readonly="readonly" onblur="getSLValue($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate24' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "24" id='rspcheckbox24'></span>
	        </div>
	        <div id="TextBoxDivrsp_25" class="hide">
		      <label>SL No #25 : </label><input type='textbox' id='rsptextbox25' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate25' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "25" id='rspcheckbox25'></span>
	        </div>
	        <div id="TextBoxDivrsp_26" class="hide">
		      <label>SL No #26 : </label><input type='textbox' id='rsptextbox26' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate26' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "26" id='rspcheckbox26'></span>
	        </div>
	        <div id="TextBoxDivrsp_27" class="hide">
		      <label>SL No #27 : </label><input type='textbox' id='rsptextbox27' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate27' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "27" id='rspcheckbox27'></span>
	        </div>
	        <div id="TextBoxDivrsp_28" class="hide">
		      <label>SL No #28 : </label><input type='textbox' id='rsptextbox28' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate28' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "28" id='rspcheckbox28'></span>
	        </div>
	        <div id="TextBoxDivrsp_29" class="hide">
		      <label>SL No #29 : </label><input type='textbox' id='textbox29' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate29' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "29" id='rspcheckbox29'></span>
	        </div>
	        <div id="TextBoxDivrsp_30" class="hide">
		      <label>SL No #30 : </label><input type='textbox' id='textbox30' readonly="readonly" onblur="getSLValuersp($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate30' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsp" value = "30" id='rspcheckbox30'></span>
	        </div>
			</div>
			<div id="slnoduptextrsp" class="hide">Duplicate serial numbers not allow : <span id="slnodupvalrsp"></span></div>
			</div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-default hide" onclick="closeSlNorsp()">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" id="selslnorsp" class="btn btn-theme" onclick="okSlNorsp()">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>

			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="openSerialModalrsppopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
<%-- 					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message> --%>
                      Serial Number
				</h4>
			</div>
			<div class="modal-body">
			<div id='TextBoxesGrouprsppopup'>
			<div id="TextBoxDivrsppopup_1">
		      <label>SL No #1 : </label><input type='textbox' id='rsptextboxpopup1' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup1' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "1" id='rspcheckboxpopup1'></span>
		      <input type="hidden" id="allValrsppopup" />
		      <input type="hidden" id="allChkIDrsppopup" />
		      <input type="hidden" id="clkrsppopup" value="0"/>
		      <input type="hidden" id="qtyslrsppopup" value="0"/>
		      <input type="hidden" id="maxSlRatersppopup" value="0"/>
		      <input type="hidden" id="selchkboxlength" value="0"/>
		      <input type="hidden" id="clickeditem" value="0"/>
	        </div>
	        <div id="TextBoxDivrsppopup_2" class="hide">
		      <label>SL No #2 :  </label><input type='textbox' id='rsptextboxpopup2' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup2' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "2" id='rspcheckboxpopup2'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_3" class="hide">
		      <label>SL No #3 :  </label><input type='textbox' id='rsptextboxpopup3' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup3' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "3" id='rspcheckboxpopup3'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_4" class="hide">
		      <label>SL No #4 :  </label><input type='textbox' id='rsptextboxpopup4' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup4' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "4" id='rspcheckboxpopup4'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_5" class="hide">
		      <label>SL No #5 :  </label><input type='textbox' id='rsptextboxpopup5' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup5' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "5" id='rspcheckboxpopup5'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_6" class="hide">
		      <label>SL No #6 :  </label><input type='textbox' id='rsptextboxpopup6' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup6' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "6" id='rspcheckboxpopup6'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_7" class="hide">
		      <label>SL No #7 :  </label><input type='textbox' id='rsptextboxpopup7' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup7' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "7" id='rspcheckboxpopup7'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_8" class="hide">
		      <label>SL No #8 :  </label><input type='textbox' id='rsptextboxpopup8' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup8' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "8" id='rspcheckboxpopup8'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_9" class="hide">
		      <label>SL No #9 :  </label><input type='textbox' id='rsptextboxpopup9' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup9' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "9" id='rspcheckboxpopup9'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_10" class="hide">
		      <label>SL No #10 : </label><input type='textbox' id='rsptextboxpopup10' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup10' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' class = "chkrsppopup" value = "10" style='zoom:1' id='rspcheckboxpopup10'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_11" class="hide">
		      <label>SL No #11 : </label><input type='textbox' id='rsptextboxpopup11' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup11' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "11" id='rspcheckboxpopup11'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_12" class="hide">
		      <label>SL No #12 : </label><input type='textbox' id='rsptextboxpopup12' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup12' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "12" id='rspcheckboxpopup12'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_13" class="hide">
		      <label>SL No #13 : </label><input type='textbox' id='rsptextboxpopup13' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup13' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "13" id='rspcheckboxpopup13'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_14" class="hide">
		      <label>SL No #14 : </label><input type='textbox' id='rsptextboxpopup14' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup14' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "14" id='rspcheckboxpopup14'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_15" class="hide">
		      <label>SL No #15 : </label><input type='textbox' id='rsptextboxpopup15' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup15' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "15" id='rspcheckboxpopup15'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_16" class="hide">
		      <label>SL No #16 : </label><input type='textbox' id='rsptextboxpopup16' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup16' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "16" id='rspcheckboxpopup16'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_17" class="hide">
		      <label>SL No #17 : </label><input type='textbox' id='rsptextboxpopup17' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup17' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "17" id='rspcheckboxpopup17'></span>
	        </div>
	         <div id="TextBoxDirspvpopup_18" class="hide">
		      <label>SL No #18 : </label><input type='textbox' id='rsptextboxpopup18' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup18' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "18" id='rspcheckboxpopup18'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_19" class="hide">
		      <label>SL No #19 : </label><input type='textbox' id='rsptextboxpopup19' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslrate1popup9' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "19" id='rspcheckboxpopup19'></span>
	        </div>
	         <div id="TextBoxDivrsppopup_20" class="hide">
		      <label>SL No #20 : </label><input type='textbox' id='rsptextboxpopup20' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup20' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "20" id='rspcheckboxpopup20'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_21" class="hide">
		      <label>SL No #21 : </label><input type='textbox' id='rsptextboxpopup21' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup21' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "21" id='rspcheckboxpopup21'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_22" class="hide">
		      <label>SL No #22 : </label><input type='textbox' id='rsptextboxpopup22' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup22' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "22" id='rspcheckboxpopup22'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_23" class="hide">
		      <label>SL No #23 : </label><input type='textbox' id='rsptextboxpopup23' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup23' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "23" id='rspcheckboxpopup23'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_24" class="hide">
		      <label>SL No #24 : </label><input type='textbox' id='rsptextboxpopup24' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup24' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "24" id='rspcheckboxpopup24'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_25" class="hide">
		      <label>SL No #25 : </label><input type='textbox' id='rsptextboxpopup25' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup25' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "25" id='rspcheckboxpopup25'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_26" class="hide">
		      <label>SL No #26 : </label><input type='textbox' id='rsptextboxpopup26' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup26' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "26" id='rspcheckboxpopup26'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_27" class="hide">
		      <label>SL No #27 : </label><input type='textbox' id='rsptextboxpopup27' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup27' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "27" id='rspcheckboxpopup27'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_28" class="hide">
		      <label>SL No #28 : </label><input type='textbox' id='rsptextboxpopup28' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup28' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "28" id='rspcheckboxpopup28'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_29" class="hide">
		      <label>SL No #29 : </label><input type='textbox' id='rsptextboxpopup29' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup29' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "29" id='rspcheckboxpopup29'></span>
	        </div>
	        <div id="TextBoxDivrsppopup_30" class="hide">
		      <label>SL No #30 : </label><input type='textbox' id='rsptextboxpopup30' readonly="readonly" onblur="getSLValuersppopup($(this).val())"> <span class="hide"> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='rspslratepopup30' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chkrsppopup" value = "30" id='rspcheckboxpopup30'></span>
	        </div>
			</div>
			<div id="slnoduptextrsppopup" class="hide">Duplicate serial numbers not allow : <span id="slnodupvalrsppopup"></span></div>
			</div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-default hide" onclick="closeSlNorsppopup()">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" id="selslnorsp" class="btn btn-theme" onclick="okSlNorsppopup()">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>

			</div>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/returnsalememo.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/retcashmemo_en.js"></script>
</c:if>
<script type="text/javascript">
var is_salemanflag=${userinfo.isSaleman};

var freechequee=${saleReturnDTO.lotAdjAmount};
if (freechequee>0) {

	$("#totltadj_rsp").removeClass("hide");
	$("#flotid_rsp").removeClass("hide");
}else {
	$("#totltadj_rsp").addClass("hide");
	$("#flotid_rsp").addClass("hide");
}
$( document ).ready(function() {

	/*  for sale man
	*/



	/*
	 * for account
	 */
	var sale_return_id=${saleRetId};
	var sale_return_customer_id=${saleReturnDTO.customerId};


	var tem_return_tax_amount=${saleReturnDTO.taxAmount};
	var tem_return_net_amount=${saleReturnDTO.netAmount};
	var tem_return_roundoff=${saleReturnDTO.roundoff};

	if (sale_return_id!=0) {

		if (tem_return_roundoff>0) {

			$('#debitor_amt_rsp').val(tem_return_net_amount.toFixed(2));
			var temp_val=(tem_return_net_amount-tem_return_tax_amount-tem_return_roundoff);
			$('#sale_account_rsp').val(temp_val.toFixed(2));

		}else
			{

			$('#debitor_amt_rsp').val(tem_return_net_amount.toFixed(2));
			var temp_val=(tem_return_net_amount-tem_return_tax_amount+Math.abs(tem_return_roundoff));
			$('#sale_account_rsp').val(temp_val.toFixed(2));
			}

		getvendorledger($('#dueties_and_tax_acc').val(),0,0,0);// for duties and tax
		getvendorledger($('#roundoff_group_code').val(),0,0,1); // for round off
		getvendorledger($('#saleaccunt_group_code').val(),0,0,2); // for sale account
		getvendorledger($('#lot_adjas_group_code').val(),0,0,4); // for lot adjasment account

	if (sale_return_customer_id==0) {

		getvendorledger($('#cash_in_hand_group_code').val(),0,0,3); // for sunndry debitor credit
	}else
		{
		getvendorledger($('#debitor_group_code').val(),0,sale_return_customer_id,3);// for sunndry debitor credit
		}
	}
});

</script>