<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/glitter/jquery.gritter.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/keyboard.css" />
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
margin-left: -13px;
}
#myTabContent{
margin-left: -13px;
}
/* for sale */

#toppanel{
margin-top: -9px;
margin-left: -2px;
 margin-right: -11px;
}
#header_div_panel{
margin-top: -10px;
margin-bottom: 2px;
margin-right: -11px;
margin-left: -2px;
}

#detail_table8{
 margin-right: -11px;
}
#final_table8{
 margin-right: -11px;
}
/* for sale return  */

#toppanel2{
margin-top: -9px;
margin-left: -2px;
 margin-right: -11px;
}
#header_div_panel2{
margin-top: -10px;
margin-bottom: 2px;
margin-right: -11px;
margin-left: -2px;
}

#detail_table1{
 margin-right: -11px;
}
#final_table82{
 margin-right: -11px;
}

.notallowed {cursor: not-allowed;}
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div id="rowmarg" class="row">
		<div class="col-lg-12 hide">
			<p></p>${sesloggedinStore}
		<input type="hidden" id="isexclusive" value="${sesloggedinStore.isExclusive}" />
		 <input type="hidden" id="isesi" value="${sesloggedinStore.isEsi}" />
		<input type="hidden" id="isexclusiversp" value="${sesloggedinStore.isExclusive}"/>
		<input type="hidden" id="retamtsamepage" value="0"/>
		<input type="hidden" id="retamtsamepagesaleid" value="${saleId}"/>
		<input type="hidden" id="confirmvalrsp" value="${saleHeaderDTO.allReturnIds}">
		<input type="hidden" id="seslocationid" value="${sessionScope.sesloggedinUser.locationId}" />
		<input type="hidden" id="retailtypeinfo" value="${sessionScope.sesloggedinUser.retailTypeId}"/>
		<input type="hidden" id="item_batchWiseStock_req_ret" value="0" />
		<input type="hidden" id="item_expiryDateRequired_req_ret" value="0" />
		<input type="hidden" id="item_expiryMonthRequired_req_ret" value="0" />
		<input type="hidden" id="item_dualStockRequired_req_ret" value="0" />
		<input type="hidden" id="item_mrpRequired_req_ret" value="0" />
		<input type="hidden" id="item_locationRequired_req_ret" value="0" />
		<input type="hidden" id="item_priceListRequired_req_ret" value="0" />
		<input type="hidden" id="item_serialNo_req_ret" value="0" />
		<input type="hidden" id="item_serialNo_list_ret" value="0" />
		<input type="hidden" id="isMrpEnableFlag" value="${sesloggedinStore.isMrpEnable}" />
		<c:set var="mrpEnableFlag" value="${sesloggedinStore.isMrpEnable}" />
		<input type="hidden" id="isConversionFlag" value="${sesloggedinStore.isConversion}" />
		<input type="hidden" id="isManufacturerFlag" value="${sesloggedinStore.isManufacturer}" />
		<input type="hidden" id="isFreeFlag" value="${sesloggedinStore.isFree}" />
		<!-- for onbill return  -->
		<input  type="hidden" name="is_free_rsp" id="is_free_rsp" value="${userinfo.isFree}">
		<input type="hidden" id="qs" value="${menuByUserDTO.qs}" />
		<input type="hidden" id="numerickeyboard" value="${sesloggedinStore.numericKeyBoard}"/>
		<c:set var="numerickeyboardon" value="${sesloggedinStore.numericKeyBoard}" />
		<input type="hidden" id="setprinter" value="${setprinter}" />
		</div>
		<div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
			<ul class="nav nav-tabs" id="myTabs" role="tablist">
				<li role="presentation" class="active "><a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true" style="font-weight: bolder;">Cash Memo</a></li>
				<c:if test="${saleHeaderDTO.holdFlag == 0 && sesloggedinStore.isOnBillSale == 1}">
				<li role="presentation" ><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false" style="font-weight: bolder;">Return Memo</a></li>
				</c:if>

			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade active in" role="tabpanel" id="home" aria-labelledby="home-tab">
					<p><!--  first filter div  --></p>
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter5">
						<div id="toppanel" class="panel-trx panel-default">
							<div class="panel-body-trx">

							<!-- 	${userinfo }  -->
								<table>
									<!--   when sale id is zero  -->
									<c:if test="${saleId==0}">
										<input type="hidden" id="esiTypeId" value="0" />
										<input type="hidden" id="esiCodeId" value="0" />
										<tr align="center">
											<td><spring:message code="pos.jsp.date" text="Date" /></td>
											<td>Series</td>
											<td class="hide"><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></td>
											<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
											<td class= "${sesloggedinStore.isCustomerDisplay==1?' ': 'hide'}"><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
											<td class="add_td hide"></td>
											<td id="prebilltext" class="hide"></td>
											<td class="hide"><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></td>
										    <td></td>
										</tr>

										<tr>
											<td width="10%" style="padding: 0 1px;">
<!-- 												<div class="input-group"> -->
													<input type="text" class="form-control-trx" id="date" style="text-align: center;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
<!-- 												</div> -->
											</td>

											<td width="8%" style="padding: 0 5px;"><select class="form-control-trx" name="mulSeriesPrefix" id="mulslid" onchange="getesitype()">

														<c:if test="${!empty serialnumberlist}">
															<c:forEach items="${serialnumberlist}" var="slno">
																<option value="${slno.mulSeriesPrefix}">${slno.mulSeriesPrefix}</option>
															</c:forEach>
														</c:if>
												</select>
											 <!-- <button style="margin-right: 2px;" class="btn btn-primary" type="button" onclick="changeSeries()">
													Change Series
												</button> -->
												</td>
											<td width="8%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" value="/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>

											<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="saleinvno" readonly="readonly" value=""><input type="hidden" id="saleId" value="0"></td>
											<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" id="salediscount" value="${sessionScope.sesloggedinUser.vatPer}" type="text"></td>
											<td  width="17%" style="padding: 0 1px;"><input class="form-control-trx" id="salecustph" placeholder="Search by phone no " type="text"><input type="hidden" id="salecustid" value="0"><input type="hidden" id="custCreditLimit" /></td>
										    <td  class= "${sesloggedinStore.isCustomerDisplay==1?' ': 'hide'}" width="25%" style="padding: 0 1px;"><input class="form-control-trx" placeholder="Search by name " id="salecustname" type="text"></td>
											<td id="add_cust_td" class="hide">
												<button style="margin-right: 2px;" class="btn btn-primary" type="button" onclick="openAddModal('cust')">
													<i class="fa fa-plus"></i>
												</button>
											</td>
											<td id="prebilltd" class="hide"><button class="btn btn-primary btn-sm" type="button" onclick="getCustPreviousBill(document.getElementById('salecustph').value)">
													<i class="fa fa-search" aria-hidden="true"></i>
													<spring:message code="pos.jsp.prevbill" text="Prev Bill" />
												</button></td>
											<td width="22%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" id="saledocname" type="text"><input type="hidden" id="saledocid" value="0"></td>
											<td id="add_doc_td" class="hide">
												<button class="btn btn-primary" type="button" onclick="openAddModal('doc')">
													<i class="fa fa-plus"></i>
												</button>
											</td>

											<td></td>
										</tr>
										<c:if test="${sesloggedinStore.isEsi==1}">
											<tr align="center" >
												<td><spring:message code="pos.jsp.type" text="Type" /></td>
												<td colspan="2"><spring:message code="pos.jsp.pissdt" text="Pres. Issue Dt" /></td>
												<td><spring:message code="pos.jsp.inscardNo" text="Ins/Card No." /></td>
												<td><spring:message code="pos.jsp.rslno" text="Req. Serial No." /></td>
												<td id="blacktext_td" class="hide"></td>
												<td id="codetextid"><spring:message code="pos.jsp.code" text="Code" /></td>
												<td id="pregtextid"><spring:message code="pos.jsp.pregno" text="Pres. Reg. No" /></td>
											</tr>
											<tr align="center" >
												<td><select class="form-control-trx" name="esitype" id="esitypeid" onchange="getesitype()">
														<option value="0">Select</option>
														<c:if test="${!empty typeMasters}">
															<c:forEach items="${typeMasters}" var="typeMaster">
																<option value="${typeMaster.id}">${typeMaster.typeName}</option>
															</c:forEach>
														</c:if>
												</select>
												</td>
												<td></td>
												<td><input type="text" style="margin-left: -71%;" class="form-control-trx" id="pissuedt" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"></td>
												<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ecardno" value="" readonly></td>
												<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="reqslno" value=""></td>
												<td id="black_td" class="hide"></td>
												<td style="padding: 0 1px;" id="codevalid"><select class="form-control-trx" name="pcode" id="pcodeid" onchange="getpode()">
														<option value="0">Select</option>
														<c:if test="${!empty esiCodeMasters}">
															<c:forEach items="${esiCodeMasters}" var="esiCodeMaster">
																<option value="${esiCodeMaster.id}">${esiCodeMaster.code}</option>
															</c:forEach>
														</c:if>
												</select></td>
												<td style="padding: 0 1px;" id="pregvalid"><input class="form-control-trx" type="text" id="prregno" value=""></td>

											</tr>
										</c:if>
									</c:if>
									<!-- //  when sale id is zero  -->
									<!-- when sale id is not  zero  -->
									<c:if test="${saleId!=0}">
										<tr align="center" >
											<td><spring:message code="pos.jsp.date" text="Date" /></td>
											<td><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
											<td class="hide"><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></td>
											<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
											<c:if test="${sesloggedinStore.isCustomerDisplay==1}">
											<td><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
											</c:if>
											<c:if test="${sesloggedinStore.isCustomerDisplay==0}">
											<td class="hide"><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
											</c:if>
											<td class="hide"><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></td>
											<td></td>
										</tr>
										<tr>
											<td style="padding: 0 1px;">
												<div class="input-group">
													<fmt:parseDate value="${saleHeaderDTO.invDate}" var="salesInvDate" pattern="MMM dd, yyyy" />
													<input type="text" class="form-control-trx" id="date"  value="<fmt:formatDate pattern="yyyy-MM-dd" value="${salesInvDate}" />" readonly>
												</div>
											</td>
											<td width="8%" style="padding: 0 1px;" class="hide"><input class="form-control-trx " type="text" id="saleId" value="${saleHeaderDTO.saleId}" readonly></td>
											<td style="padding: 0 5px;"><input class="form-control-trx" type="text" id="saleinvno" value="${saleHeaderDTO.invNo}" readonly></td>
											<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="salediscount" value="${saleHeaderDTO.customerDiscPer}" readonly></td>
											<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="salecustph" value="${saleHeaderDTO.customerPhone}"> <input type="hidden" id="salecustid" value="${saleHeaderDTO.customerId}"><input type="hidden" id="custCreditLimit" value="${saleHeaderDTO.creditLimit}" /></td>
											<c:if test="${sesloggedinStore.isCustomerDisplay==1}">
											<td width="25%" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="salecustname" value="${saleHeaderDTO.customerName}"></td>
											</c:if>
											<c:if test="${sesloggedinStore.isCustomerDisplay==0}">
											<td width="25%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="salecustname" value="${saleHeaderDTO.customerName}"></td>
											</c:if>
											<td width="25%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="saledocname" value="${saleHeaderDTO.doctorName}"> <input type="hidden" id="saledocid" value="${saleHeaderDTO.doctorId}"></td>
											<td></td>
											<td id="add_doc_td" class="hide">
												<button class="btn btn-primary" type="button" onclick="openAddModal('doc')">
													<i class="fa fa-plus"></i>
												</button>
											</td>
										</tr>
										<c:if test="${sesloggedinStore.isEsi==1}">
											<input type="hidden" id="esiTypeId" value="${saleHeaderDTO.esiType}" />
											<input type="hidden" id="esiCodeId" value="${saleHeaderDTO.esiCode}" />
											<tr align="center" >
												<td><spring:message code="pos.jsp.type" text="Type" /></td>
												<td><spring:message code="pos.jsp.pissdt" text="Pres. Issue Dt" /></td>
												<td><spring:message code="pos.jsp.inscardNo" text="Ins/Card No." /></td>
												<td><spring:message code="pos.jsp.rslno" text="Req. Serial No." /></td>
												<td id="codetextid"><spring:message code="pos.jsp.code" text="Code" /></td>
												<td id="pregtextid"><spring:message code="pos.jsp.pregno" text="Pres. Reg. No" /></td>
											</tr>
											<tr align="center" >
												<td><select class="form-control-trx" name="esitype" id="esitypeid" onchange="getesitype()">
														<option value="0">Select</option>
														<c:if test="${!empty typeMasters}">
															<c:forEach items="${typeMasters}" var="typeMaster">
																<option value="${typeMaster.id}">${typeMaster.typeName}</option>
															</c:forEach>
														</c:if>
												</select></td>
												<fmt:parseDate value="${saleHeaderDTO.prescriptionIssueDate}" var="pDate" pattern="MMM dd, yyyy" />
												<td style="padding: 0 1px;"><input type="text" readonly="readonly" class="form-control-trx" id="pissuedt" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${pDate}" />"></td>
												<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ecardno" value="${saleHeaderDTO.customerCode}" readonly></td>
												<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="reqslno" value="${saleHeaderDTO.slipNo}"></td>
												<td style="padding: 0 1px;" id="codevalid"><select class="form-control-trx" name="pcode" id="pcodeid" onchange="getpode()">
														<option value="0">Select</option>
														<c:if test="${!empty esiCodeMasters}">
															<c:forEach items="${esiCodeMasters}" var="esiCodeMaster">
																<option value="${esiCodeMaster.id}">${esiCodeMaster.code}</option>
															</c:forEach>
														</c:if>
												</select></td>
												<td style="padding: 0 1px;" id="pregvalid"><input class="form-control-trx" type="text" id="prregno" value="${saleHeaderDTO.prescriptionRegNo}"></td>

											</tr>
										</c:if>
									</c:if>
										<!--// when sale id is not  zero  -->
								</table>
							</div>
						</div>

					</div>
					<!-- // first filter div  -->


					<!--  item search div    -->
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter6">
						<div id="header_div_panel" class="panel-trx panel-default">
							<div class="panel-body-trx" id="header_div">

								<table  >
									<tr align="center" >
										<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
										<td>Barcode</td>

										<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
										<c:choose>
										  <c:when test="${sesloggedinStore.defaultPort=='--'}">
										  <td id="wmc_label" class="hide" >W.M/C</td>
										  </c:when>
										  <c:otherwise>
										  <td id="wmc_label" >W.M/C</td>
										  </c:otherwise>
										</c:choose>

										<td id="lqty_label" class="hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
										<td    id="free_label" class="${userinfo.isFree==1 ?'':'hide'}"><spring:message code="purinvdet.jsp.free" text="Free" /></td>
										<td id="sale_rate_label" class="hide">Rate</td>
										<td id="s_dis_label" class="hide">Sc Disc%</td>
										<td id="t_dis_label" class="hide">Tr Disc%</td>
										<td><spring:message code="pos.jsp.rateLs" text="Rate/Ls" /></td>
										<td id="dis_label"><spring:message code="purinvdet.jsp.discprcnt" text="D%" /></td>
										<td id="batch_label" class="hide"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
										<td id="exp_label" class="hide"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>
										<td id="expdate_label" class="hide"><spring:message code="purinvdet.jsp.expddate" text="ExpDate" /></td>

										<td class="hide"><spring:message code="purinvdet.jsp.vatprcnt" text="Vat%" /></td>
										<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>

									</tr>
									<tr>
										<td width="25%" style="padding: 0 5px;"><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"> <input type="hidden" id="item_id" value="0"></td>
										<td width="16%"><input class="form-control-trx" type="text" id="item_barcode" placeholder="Scan Barcode"></td>

										<td style="padding: 0 5px;" width="7%"><input class="form-control-trx" type="text" id="item_pqty" value="0" onkeydown="numcheck(event)" > <input type="hidden" id="item_packunitid" value="0"><input type="hidden" id="item_is_slno" value="0"><input type="hidden" id="item_taxtype" value="0"></td>
										<c:choose>
										  <c:when test="${sesloggedinStore.defaultPort=='--'}">
										  <td style="padding: 0 1px;" class="hide"><input type="checkbox" style="zoom:2.0;vertical-align: middle; " id="weival" name="weival" data-reverse></td>
										  </c:when>
										  <c:otherwise>
										  <td style="padding: 0 1px;" ><input type="checkbox" style="zoom:2.0;vertical-align: middle; " id="weival" name="weival" data-reverse></td>
										  </c:otherwise>
										</c:choose>

										<td  width="7%" style="padding: 0 5px;" class="${userinfo.isFree==1 ?'':'hide'}" ><input class="form-control-trx "  	onkeydown="numcheck(event)" type="text" id="item_free" value="0"><input class="form-control-trx hide" type="text" id="item_lqty" value="0"><input type="hidden" id="item_looseunitid" value="0"> <input type="hidden" id="item_stockedqty" value="0"><input type="hidden" id="item_ltadj" value="0"></td>
										<td style="padding: 0 5px;" class="hide"><input class="form-control-trx" type="text" id="item_sale_rate"><input class="form-control-trx" type="hidden" id="item_purChase_rate"><input type="hidden" id="item_dualStockRequired_req" value="0" /></td>
										<td style="padding: 0 5px;" class="hide"><input class="form-control-trx" type="text" id="item_sc_dis" value="0.0000" onkeyup="" ><input type="hidden" id="item_sc_dis_amt" value="0"> </td>
										<td style="padding: 0 5px;" class="hide"><input class="form-control-trx" type="text" id="item_tr_dis" value="0.0000" onkeyup="" ><input type="hidden" id="item_tr_dis_amt" value="0"></td>
										<td style="padding: 0 5px;"><input class="form-control-trx" type="text" id="item_rate_ls" value="0" onkeydown="numcheck(event)"  ><input type="hidden" id="item_rate_ls_hid" value="0"></td>
										<td  width="9%" style="padding: 0 5px;" id="item_dis_td"><input class="form-control-trx" type="text" id="item_dis" onkeydown="numcheck(event)" value="0.0000"><input type="hidden" id="item_discamt" value="0"><input type="hidden" id="item_mrpRequired_req" value="0"><input type="hidden" id="item_priceListRequired_req" value="0"><input type="hidden" id="item_expDate" tabindex="-1" readonly></td>
										<td style="padding: 0 5px;" id="batch_no_td" class="hide"><input class="form-control-trx" type="text" id="item_batch" tabindex="-1" readonly></td>
										<td style="padding: 0 5px;" id="exp_td" class="hide"><input class="form-control-trx" type="text" id="item_exp" tabindex="-1" readonly></td>

										<td style="padding: 0 5px;" class="hide"><input class="form-control-trx " type="text" id="item_vat" value="${sessionScope.sesloggedinUser.vatPer}" readonly><input type="hidden" id="item_vatamt" value="0"><input class="form-control-trx " type="text" id="item_tax" value="${sessionScope.sesloggedinUser.taxPer}" readonly><input type="hidden" id="item_taxamt" value="0"></td>
										<td style="padding: 0 5px;"><input class="form-control-trx" type="text" id="item_tot" value="0" tabindex="-1" readonly></td>

									</tr>
									<tr align="center" >
								    <td class="${userinfo.isManufacturerSearch==1? '' :'hide'}"><spring:message code="pos.jsp.manu" text="Manufacturer" /></td>
										<td colspan="2" class="hide"><spring:message code="itemmstr.jsp.content" text="Content" /></td>

										<td id="mrp_label" class="hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
										<td class="hide"><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></td>
										<td class="hide"><spring:message code="itemmstr.jsp.schedule" text="Schedule" /></td>
										<td id="location_label" class="hide"><spring:message code="purinvdet.jsp.loc" text="Location" /></td>
										<td id="Sizelabel_td" class="hide"><spring:message code="purinvdet.jsp.size" text="Size" /></td>
							            <td id="Colorlabel_td" class="hide"><spring:message code="purinvdet.jsp.color" text="Color" /></td>
							            <td id="wtypelabel_td" class="hide"><spring:message code="purinvdet.jsp.warrantytype" text="Warranty Type" /></td>
							            <td id="wmonthlabel_td" class="hide"><spring:message code="purinvdet.jsp.wmonth" text="W. Month" /></td>
							            <!-- for sale man  -->
							            <td id="wmonthlabel_td" class="${userinfo.isSaleman==1? '' :'hide'}" ><spring:message code="salesman.jsp.name" text="Salesman Name" /></td>
							        	<td id="ratio_label" class="hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
							             <td id="isfreeiai_td"></td>
							            <td colspan="2" ></td>
							            <td colspan="2" >.</td>



									</tr>
									<tr>
										<td class="${userinfo.isManufacturerSearch==1? '' :'hide'}" style="padding: 0 1px;" class="hide">
										<input type="hidden" id="manu_id" value="0"/>
										<input class="form-control-trx" type="text"  id="item_mfg" tabindex="-1" value="" placeholder="Please type atleast 2 characters">
										</td>
										<td colspan="2" style="padding: 0 1px;" class="hide"><input class="form-control-trx" id="item_content" title="sdfgsdfg" type="text" tabindex="-1" readonly></td>

										<td style="padding: 0 1px;" id="mrp_td" class="hide"><input class="form-control-trx" type="text" id="item_mrp_pack" tabindex="-1" readonly></td>
										<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_mrp" tabindex="-1" readonly></td>
										<!--  for salesman -->
										<td style="padding: 0 1px;" class="${userinfo.isSaleman==1? '' :'hide'}" >

										<input class="form-control-trx" type="hidden" id="salesman_id" value="0">
										<input class="form-control-trx" type="hidden" id="salesman_per" value="0">
										<input class="form-control-trx" type="hidden" id="salesman_amt" value="0">

										<select class="form-control-trx " name="salemanslist" id="salemanslist" onChange="salemansCommissionCal()">
												<option value="0">Select</option>

											</select>

										</td>
											<td style="padding: 0 1px;" id="ratio_val" class="hide"><input class="form-control-trx" type="text" id="item_conv" tabindex="-1" readonly></td>
											<!--  free item aginst  item  -->
										<td style="padding-left: 10px;"><input type="checkbox" id="isfreeiai" onclick="javascript:itemAgainstItem()"> <spring:message code="pos.jsp.isfreeiai" text="Checked it free" /> </td>

										<td class="hide">
										<input type="hidden" id="item_sche">
										<input type="hidden" id="item_taxId">
										<input type="hidden" id="item_taxPercentage">
										<input type="hidden" id="item_isGroupTax">
										<input type="hidden" id="item_CalcTaxAmt" value="0">
										<input type="hidden" id="item_discount">
										<input type="hidden" id="item_isDiscount">
										<input type="hidden" id="item_maxDiscountLimit" value="0">
										<input type="hidden" id="item_taxMode" value="0">
										<input type="hidden" id="item_hsnCode" value="0">
										<input type="hidden" id="item_purCost" value="0">
										<input type="hidden" id="item_purCostperUnit" value="0">
										<input type="hidden" id="item_dualstock" value="0">
										</td>
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
											<td style="padding: 0 1px;" id="wtypeval_td" class="hide">
							<select class="form-control-trx" name="warrantyTypeOn" id="warrantyTypeOn">
									<option value="1">ISSUE</option>
									<option value="2">RECEIPT</option>
									<option value="3">BOTH</option>
							</select>
							</td>
							<td style="padding: 0 1px;" id="wmonthval_td" class="hide"><input class="form-control-trx" type="text" value="0" id="warrantymonth"></td>
										<td colspan="2">
											<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
										</td>
										<c:if test="${saleHeaderDTO.isPosted !=1}">
											<c:if test="${saleHeaderDTO.holdFlag ==0}">
												<td colspan="3" align="middle"><button class="btn btn-success btn-sm" title='Add into table ' type="button" onclick="addOrUpdateItemToDetailsTable(0)" id="add_btn">
														<spring:message code="cmn.jsp.addcaps" text="ADD" />
													</button>
													<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" title='Update into table 'type="button" onclick="addOrUpdateItemToDetailsTable(1)" id="edit_btn">
														<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
													</button>
													<button class="btn btn-primary btn-sm" type="button" title='Clear content' onclick="clearHeaderDiv()">
														<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
													</button></td>
											</c:if>
										</c:if>

									</tr>
								</table>
							</div>
						</div>
					</div>
					<!--//  item search div    -->

					<!-- Purchase History -->
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
									<td>Lot.Adj.</td>
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
				<!-- Purchase History -->


					<!-- item table for after add for sale -->
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div style="overflow: auto; height:240px;" id="detail_table8">
							<table id="selitem" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
								<thead>
									<tr>
										<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
										<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
										<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
										<th class="hide"><spring:message code="purinvdet.jsp.expdate" text="ExpDate" /></th>
										<th class= "${sesloggedinStore.isManufacturer==1?' ': 'hide'}"><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></th>
										<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
										<th class="${sesloggedinStore.isFree==1?' ': 'hide'}"><spring:message code="purinvdet.jsp.free" text="Free" /></th>
										<th class="${sesloggedinStore.isConversion==1?' ': 'hide'}"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
										<c:if test="${mrpEnableFlag==1}">
										<th class="numeric"><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></th>
										<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
										</c:if>
										<c:if test="${mrpEnableFlag==0}">
										<th class="numeric hide"><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></th>
										<th class="numeric hide"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
										</c:if>
										<th class="numeric">Amt</th>
										<th class="numeric"><spring:message code="pos.jsp.rateLs" text="Rate/Ls" /></th>
										<th class="numeric hide"><spring:message code="purinvdet.jsp.vatprcnt" text="VAT%" /></th>
										<th class="numeric"><spring:message code="purinvdet.jsp.taxprcnt" text="TAX%" /></th>
										<th class="numeric"><spring:message code="purinvdet.jsp.disc" text="Disc" />%</th>
										<!--saleman com-->
										<%-- <th class="numeric"><spring:message code="pos.jsp.salesmancomper" text="Com.Per" /></th> --%>
										<th class="numeric"><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
										<th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>

									</tr>
								</thead>
								<tbody id="saletabitemdetails">

									<%-- 						<c:if test="${saleId!=0}"> value="${saleDetail.totAmount}"--%>
									<c:if test="${!empty saleDetailsDTOs }">

										<c:forEach items="${saleDetailsDTOs}" var="saleDetail">
											<c:choose>
												<c:when test="${saleDetail.scheduleName=='H1' || saleDetail.scheduleName == 'X'}">
													<tr id="${saleDetail.itemUniqueKey}" class='schx ${saleDetail.itemFreeAgainstItem==1?'freeiai':''}' style='cursor: pointer;' onclick="javascript:itemHeaderDivView(this.id);">
												</c:when>
												<c:otherwise>
													<tr id="${saleDetail.itemUniqueKey}" class="${saleDetail.itemFreeAgainstItem==1?'freeiai':''}" style='cursor: pointer;' onclick="javascript:itemHeaderDivView(this.id);">
												</c:otherwise>
											</c:choose>
                                            <c:set var="tblslnosedit" value=""/>
											<td id="saletabname">${saleDetail.itemName}</td>
											<td id="saletabbat">${saleDetail.batchNo}</td>
											<td id="saletabexpdt">${saleDetail.expiryDateFormat}</td>
											<fmt:parseDate value="${saleDetail.expiryDate}" var="formattedExpDate" pattern="MMM dd, yyyy" />
											<td id="saletabexpdate" class="hide"><fmt:formatDate pattern = "yyyy-MM-dd" value = "${formattedExpDate}" /></td>
											<td class="${sesloggedinStore.isManufacturer==1?' ': 'hide'}" id="saletabmanname">${saleDetail.manufacturerName}</td>
											<td class="numeric" id="saletabpqty">${saleDetail.packQty}</td>
										     <td class="numeric ${userinfo.isFree==1 ?'':'hide'}" id="saletabfree">${saleDetail.freeQty}</td>

									     <td class="hide" id="saletablqty">${saleDetail.looseQty}</td>
											<%-- <td class="${sesloggedinStore.isFree==1?' ': 'hide'}" id="saletablqty">${saleDetail.looseQty}</td> --%>

											<td class="${sesloggedinStore.isConversion==1?' ': 'hide'}" id="saletabconv">${saleDetail.conversion}</td>

											<c:if test="${mrpEnableFlag==1}">
											<td class="numeric" id="saletabmrppack"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.mrp}" /></td>
											<td class="numeric" id="saletabmrp"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${((saleDetail.packQty*saleDetail.conversion)+saleDetail.looseQty)*saleDetail.mrpPerUnit}" /></td>
											</c:if>
											<c:if test="${mrpEnableFlag==0}">
											<td class="numeric hide" id="saletabmrppack"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.mrp}" /></td>
											<td class="numeric hide" id="saletabmrp"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${((saleDetail.packQty*saleDetail.conversion)+saleDetail.looseQty)*saleDetail.mrpPerUnit}" /></td>
											</c:if>
											<%-- <td class="numeric" id="saletabamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${((saleDetail.packQty*saleDetail.conversion)+saleDetail.looseQty)*saleDetail.ratePerUnit}" /></td> --%>


                                         <c:if test="${saleDetail.itemdualstock==1}">
                                         <td class="numeric" id="saletabamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${(saleDetail.packQty*saleDetail.ratePerUnit)}" /></td>
                                         </c:if>

  										<c:if test="${saleDetail.itemdualstock==0}">
  										<td class="numeric" id="saletabamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${((saleDetail.packQty*saleDetail.conversion)+saleDetail.looseQty)*saleDetail.ratePerUnit}" /></td>
                                         </c:if>
											<td class="numeric" id="saletabrateperunit"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.ratePerUnit}" /></td>
											<td class="numeric hide" id="saletabvatperc"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.vatPer}" /></td>
											<td id="saletabtaxPercentage" class="numeric"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.taxPercentage}" /></td>
											<td class="numeric" id="saletabdiscperc"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.discPer}" /></td>
											<td class="numeric" id="saletabtotamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.totAmount}" /></td>
											<td><c:if test="${saleHeaderDTO.isPosted !=1}">
													<c:if test="${saleHeaderDTO.holdFlag ==0}">
														<button class="btn btn-theme04 btn-xs" title='Delete item' id="${saleDetail.itemUniqueKey}" onclick='javascript:showSelTabItemDelModal(this.id);'>
															<i class="fa fa-trash-o "></i>
														</button>
													</c:if>
												</c:if></td>
											<td id="saletabitembarcode" class="hide">${saleDetail.sku}</td>
											<td id="saletabpunitid" class="hide">${saleDetail.packUnitId}</td>
											<td id="saletablunitid" class="hide">${saleDetail.looseUnitId}</td>
											<td id="saletabvat" class="hide">${saleDetail.vat}</td>
											<td id="saletabdisc" class="hide">${saleDetail.disc}</td>
											<td id="saletabcontent" class="hide">${saleDetail.contentName}</td>
											<td id="saletabitemstkqty" class="hide">${saleDetail.itemId}</td>
											<td id="saletabmrpperunit" class="hide">${saleDetail.mrpPerUnit}</td>
											<td id="saletabrate" class="hide">${saleDetail.rate}</td>
											<td id="saletabschename" class="hide">${saleDetail.scheduleName}</td>
											<td id="saletabtaxperc" class="hide">${saleDetail.taxPer}</td>
											<td id="saletabtax" class="hide">${saleDetail.tax}</td>
											<td id="saletabcontentid" class="hide">${saleDetail.contentId}</td>
											<td id="saletabtaxId" class="hide">${saleDetail.taxId}</td>

											<td id="saletabisGroupTax" class="hide">${saleDetail.isGroupTax}</td>
											<td id="saletabdiscount" class="hide">${saleDetail.discount}</td>
											<td id="saletabisDiscount" class="hide">${saleDetail.isDiscount}</td>
											<td id="saletabmaxDiscountLimit" class="hide">${saleDetail.maxDiscountLimit}</td>
											<td id="saletabitemcalcgstamt" class="hide">${saleDetail.taxAmount}</td>
											<td id="saletabitemtaxmode" class="hide">${saleDetail.taxMode}</td>
											<td id="saletabitemhsncode" class="hide">${saleDetail.hsnCode}</td>
											<td id='saletabpurcost' class='hide'><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${((saleDetail.packQty*saleDetail.conversion)+saleDetail.looseQty)*saleDetail.purchaseCostPerUnit}" /></td>
											<td id='saletabpurcostperunit' class='hide'>${saleDetail.purchaseCostPerUnit}</td>
											<td id='saletabitemsalerate' class='hide'>${saleDetail.saleRate}</td>
											<td id='saletabitempurrate' class='hide'>${saleDetail.purchaseRate}</td>
											<td id='saletabdualstock' class='hide'>${saleDetail.itemdualstock}</td>
											<td id='saletabitemltadj' class='hide'>${saleDetail.itemLotAdjAmount}</td>
											<td id='saletabsalesmanIdComPer' class='hide'>${saleDetail.salesmanId}_${saleDetail.salesmanFactor}_${saleDetail.salesmanAmount}</td>
											<td id='saletabisfreeitemaginstitem' class='hide'>${saleDetail.itemFreeAgainstItem}</td>


											<c:if test="${!empty saleDetail.salesDetailsSerialMapper}">
											<c:forEach items="${saleDetail.salesDetailsSerialMapper}" var="saleDetailsSerialMapper" varStatus="salesDetSerialMapper">
											<c:set var="tblslnosedit" value="${tblslnosedit}${saleDetailsSerialMapper.uniqueIdentifierNo}," />
											</c:forEach>
											<td id="saletabitemslnos" class="numeric hide">${tblslnosedit}</td>
											</c:if>
											<td id="saletabitemisslno" class="numeric hide">${saleDetail.serialNoRequired}</td>
											<td id="saletabitemchkslnosid" class="numeric hide">${saleDetail.purchaseRate}</td>
											<td id="saletabitemtaxtype" class="numeric hide">${saleDetail.taxTypeId}</td>
											</tr>
										</c:forEach>
									</c:if>

									<%-- 						</c:if> --%>
								</tbody>
							</table>
						</div>
					</div>
									<!--// item table for after add for sale -->
					<div class="col-lg-12 col-md-12 col-sm-12">
						<p></p>
					</div>
					<!-- all final calculation from sale table   -->
					<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail7">
				<input type="hidden" id="dueties_and_tax_accf" value="<spring:message code="accgroup.jsp.duties_code" text="DT"/>">
				<input type="hidden" id="saleaccunt_group_codef" value="<spring:message code="accgroup.jsp.saleac_code" text="SA"/>">
				<input type="hidden" id="roundoff_group_codef" value="<spring:message code="accgroup.jsp.roun_code" text="ROD"/>">
				<input type="hidden" id="debitor_group_codef" value="<spring:message code="accgroup.jsp.deb_code" text="SDE"/>">
				 <input type="hidden" id="dicount_codef" value="<spring:message code="accgroup.jsp.disc_code" text="DIS" />">
				 <input type="hidden" id="cash_codef" value="<spring:message code="accgroup.jsp.cash_code" text="CIH" />">
				<input type="hidden" id="card_codef" value="<spring:message code="accgroup.jsp.card_code" text="CAB" />">
				   <input type="hidden"  id="duties_ledger_idf" value="0">
				 	<input type="hidden"  id="round_ledger_idf" value="0">
				     <input type="hidden"  id="sales_ledger_idf" value="0">
				     <input type="hidden"  id="debitor_ledger_idf" value="0">
				     <input type="hidden"  id="discount_ledger_idf" value="0">
				     <input type="hidden"  id="debitor_cahs_ledger_idf" value="0">
				      <input type="hidden" id="card_ledger_idf" value="0">


						<table  id="final_table8">
							<tr>
								<td ><spring:message code="purinvdet.jsp.itemcount" text="ItemCount" />:</td>
								<td width="2%" ><span id="totitmcount"></span></td>
								<%-- <td class="font-bold"><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></td>
					           <td style="padding-right: 5px;"><input class="form-control-trx" id="remarks" type="text" value="${saleHeaderDTO.remarks}"></td> --%>
								<td ><spring:message code="purinvdet.jsp.total" text="Total:" />:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrossamt" value="${saleHeaderDTO.grossAmount}" tabindex="-1" readonly></td>
								<c:if test="${mrpEnableFlag==1}">
								<td >Tot.MRP:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totmrpamt" value="${saleHeaderDTO.totalMrp}" tabindex="-1" readonly></td>
								</c:if>
								<c:if test="${mrpEnableFlag==0}">
								<td class="hide">Tot.MRP:</td>
								<td class="hide" style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totmrpamt" value="${saleHeaderDTO.totalMrp}" tabindex="-1" readonly></td>
								</c:if>
								<td class="font-bold hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat:" /></td>
								<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamt" value="${saleHeaderDTO.vatAmount}" tabindex="-1" readonly></td>
								<td class="font-bold hide"><spring:message code="pos.jsp.stax" text="Tot.Tax" /></td>
								<td  style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="tottaxamt" value="${saleHeaderDTO.taxAmount}" tabindex="-1" readonly></td>
								<!--  for total tax -->
								<td  width="6%" ><spring:message code="pos.jsp.stax" text="Tot.Tax" /></td>
								<td width="10%" style="padding-right: 5px;"><input class="form-control-trx" type="text" id="totgstamt" value="${saleHeaderDTO.taxAmount}" tabindex="-1" readonly></td>
								<!-- for discount  -->
								<td width="7%" ><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc:" /></td>
								<td width="7%" style="padding-right: 5px;"><input class="form-control-trx" type="text" id="totdiscamt" value="${saleHeaderDTO.discAmount}" tabindex="-1" readonly></td>
								<td class="hide">ProfitAmt:</td>
								<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="profitperc" value="" readonly="readonly"></td>
								<!--  for round off  -->
								<td width="3%" ><spring:message code="purinvdet.jsp.roff" text="R.Off:" /></td>
								<td width="7%" style="padding-right: 5px;"><input class="form-control-trx" type="text" id="roundoff" value="${saleHeaderDTO.roundoff}" tabindex="-1" readonly="readonly"></td>
								   <!-- for other adjasment -->
					            <td  id="otherAdjasment_td"><spring:message code="purinvdet.jsp.othadj" text="Oth.Adj" />:</td>
							   <td  width="7%" style="padding: 2px 5px 0 0" ><input  onkeydown="numcheck(event)" class="form-control-trx" type="text" id="otheradjasment" value="${saleHeaderDTO.othAdjAmount}" tabindex="-1" >
								<!--  for net total  -->
								<td width="7%"><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal:" /></span></td>
								<td width="15%"  ><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="nettot" value="${saleHeaderDTO.netAmount}" tabindex="-1" readonly></td>
							</tr>
							<tr>

								<td  id="remarkstext"><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></td>
								<td colspan="3" style="padding: 3px 5px 0 0"><input class="form-control-trx" id="remarks" type="text" value="${saleHeaderDTO.remarks}"></td>
								<td colspan="4" class="font-bold hide"><c:if test="${saleId!=0}">
										<c:if test="${saleHeaderDTO.invMode==1}">
											<input type="radio" id="payOption" name="payOption" value="1" checked="checked">&nbsp; <spring:message code="pos.jsp.cash" text="Cash" />
										</c:if>
										<c:if test="${saleHeaderDTO.invMode==2}">
											<input type="radio" id="payOption" name="payOption" value="2" checked="checked">&nbsp; <spring:message code="pos.jsp.card" text="Card" />
										</c:if>
										<c:if test="${saleHeaderDTO.invMode==3}">
											<input type="radio" id="payOption" name="payOption" value="3" checked="checked">&nbsp; <spring:message code="pos.jsp.credit" text="Credit" />
										</c:if>
									</c:if> <c:if test="${saleId==0}">
										<input type="radio" id="payOption" name="payOption" value="1" checked="checked">&nbsp; <spring:message code="pos.jsp.cash" text="Cash" />
										<input type="radio" id="payOption" name="payOption" value="2">&nbsp; <spring:message code="pos.jsp.card" text="Card" />
										<input type="radio" id="payOption" name="payOption" value="3">&nbsp; <spring:message code="pos.jsp.credit" text="Credit" />
									</c:if></td>

								<td ><spring:message code="pos.jsp.spldscprcnt" text="SplDisc(%)" />:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="spcldiscperc" onkeydown="numcheck(event)"  value="${saleHeaderDTO.specialDiscPer}" onkeyup="calculateSpclDisc()"></td>
								<td ><spring:message code="pos.jsp.spldscamnt" text="Spl.Disc.Amt" />:</td>

								<td style="padding-right: 5px;"><input class="form-control-trx" readonly="readonly" tabindex="-1" type="text" id="spcldisc" value="${saleHeaderDTO.specialDiscAmount}" onkeyup="calculateSpclDiscPrcnt()"></td>
								 <c:if test="${saleId==0 && sesloggedinStore.isOnBillSale == 1}">
								<td  >Ret Amt:</td>
								<td style="padding-right: 5px;" ><input class="form-control-trx" readonly="readonly" tabindex="-1" type="text" id="retamtvalsamepage" value="0"></td>
								</c:if>
								 <c:if test="${saleId!=0 &&saleHeaderDTO.holdFlag ==0 && sesloggedinStore.isOnBillSale == 1}">
								<td >Ret Amt:</td>
								<td style="padding-right: 5px;" ><input class="form-control-trx" readonly="readonly" tabindex="-1" type="text" id="retamtvalsamepage" value="${saleHeaderDTO.totalReturnAmount}"></td>
								</c:if>
								<!-- for lot adjasmment   -->
								<td class="hide" id="flotid"><spring:message code="purinvdet.jsp.ltadj" text="Lt.Adj" />:</td>
					            <td width="6%" style="padding: 2px 5px 0 0" ><input class="form-control-trx hide" type="text" id="totltadj" value="${saleHeaderDTO.lotAdjAmount}" tabindex="-1" readonly="readonly">

								<%-- <td class="font-bold">RefundAmt:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="refundAmt" value="" readonly="readonly"></td>
					<td class="font-bold">CashAmt:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="cashAmt" value="${saleHeaderDTO.cashAmount}"></td>
					<td class="font-bold">CreditAmt:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly"></td>
					<td class="font-bold">CardAmt:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="cardAmt" value="${saleHeaderDTO.cardAmount}" readonly="readonly"></td>
					<td style="padding-top: 4px; padding-right: 4px;"><button class="btn btn-primary btn-sm" style="padding: 5px 2px;" type="button" onclick="openCardModal()">CARD</button></td>--%>
								<c:if test="${saleHeaderDTO.isPosted !=1}">

									<td colspan="5" style="padding-top: 4px; padding-right: 4px;">
										<button style="padding: 5px 8px;" class="btn btn-info btn-sm" title="New Sale Button " type="button" onclick="openNewCashMemo()">
											<spring:message code="cmn.jsp.new" text="NEW" />
										</button>
										 <c:if test="${saleHeaderDTO.holdFlag ==0}">
											<c:if test="${saleId!=0}">
												<button style="padding: 5px 8px;" class="btn btn-danger btn-sm" title="Sales Delete Button" type="button" id="delButtId" onclick="deleteSalesInv()">
													<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
												</button>
											</c:if>
											<button style="/*padding: 5px 30px;*/ text-transform: uppercase;" id="holdButtId"  title="Sales hold Button" class="btn btn-success btn-sm" type="button" onclick="saveCashmemo()">
												<c:if test="${saleId==0}">
													<spring:message code="cmn.jsp.btn.hold" text="HOLD" />
												</c:if>
												<c:if test="${saleId!=0}">
													<spring:message code="cmn.jsp.btn.hold" text="HOLD" />
												</c:if>
											</button>
										</c:if>
										 <c:if test="${saleHeaderDTO.holdFlag ==0}">
											<button class="btn btn-primary btn-sm" style="padding: 5px 30px;" title="Sales Payment Button " type="button" id="payButtId" onclick="paySaleInvModal()">
												<spring:message code="pos.jsp.payBtn" text="PAY" />
											</button>
										</c:if> <c:if test="${saleHeaderDTO.holdFlag ==1}">
											<button class="btn btn-primary btn-sm" style="padding: 5px 30px;" title="Sales Payment Detials Button "  type="button" onclick="paySaleInvModal1()">
												<spring:message code="pos.jsp.payBtnDet" text="PAY DET." />
											</button>
										</c:if>
									</td>
								</c:if>
								<c:if test="${saleHeaderDTO.isPosted ==1}">
									<c:if test="${saleHeaderDTO.holdFlag ==1}">
										<td colspan="4" style="padding-top: 4px; padding-right: 4px;">
											<button class="btn btn-primary btn-sm" style="padding: 5px 30px;" type="button" onclick="paySaleInvModal1()">
												<spring:message code="pos.jsp.payBtnDet" text="PAY DET." />
											</button>
										</td>
									</c:if>
								</c:if>
							</tr>
						</table>
					</div>
					<!--// all final  calculation from sale table   -->

				</div>
				<div class="tab-pane fade" role="tabpanel" id="profile" aria-labelledby="profile-tab">
					<p>
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter11">
			<input type="hidden" id="useridrsp" value="${sessionScope.sesloggedinUser.id}"> <input type="hidden" id="companyIdrsp" value="${sessionScope.sesloggedinUser.companyId}"> <input type="hidden" id="storeIdrsp" value="${sessionScope.sesloggedinUser.storeId}"> <input type="hidden" id="finyrIdrsp" value="${sessionScope.sesloggedinUser.finyrId}">
			<div id="toppanel2" class="panel-trx panel-default">

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
									<div class="col-sm-12 hide" style="text-align: center; font-weight: 700;">
										<spring:message code="rtrnmemo.jsp.printsalereturn" text="Print Sale Return" />
										: <input type="checkbox" id="printSaleReturn" name="printSaleReturn" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
									</div>
								</div>

								<input type="hidden" id="confirmvalrspsamepage" value="0">
							</div>
							<div class="modal-footer" style="border-top: 0px;">
								<button type="button" onclick="targetURLrspsamepage()" data-dismiss="modal" class="btn btn-theme">
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
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="pos.jsp.date" text="Date" /></td>
								<td colspan="3"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.billno" text="Bill No" /></td>
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
								<td width="9%" style="padding: 0 1px;"><input class="form-control-trx" type="text" value="SRM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="" readonly="readonly" value=""><input type="hidden" id="saleretId" value="0"></td>
								<%-- <td width="8%" style="padding: 0 1px;"><input
									class="form-control-trx" type="text"
									value="SIM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td> --%>

								<td style="padding: 0 1px;">

								<!-- <input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="SIM" size="4" > -->
								<select class="form-control-trx" name="retmemoDoc" id="retmemoDoc" onchange="getesitype()">

														<c:if test="${!empty serialnumberlist}">
															<c:forEach items="${serialnumberlist}" var="slno">
																<option value="${slno.mulSeriesPrefix}">${slno.mulSeriesPrefix}</option>
															</c:forEach>
														</c:if>
												</select>

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
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="pos.jsp.date" text="Date" /></td>
								<td colspan="1"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.billno" text="Bill No" /></td>
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

								<!-- <input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="SIM" size="4"> -->
									<select class="form-control-trx" name="retmemoDoc" id="retmemoDoc" onchange="getesitype()">

														<c:if test="${!empty serialnumberlist}">
															<c:forEach items="${serialnumberlist}" var="slno">
																<option value="${slno.mulSeriesPrefix}">${slno.mulSeriesPrefix}</option>
															</c:forEach>
														</c:if>
												</select>
								</td>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoDocSlash" value="/" readonly></td>
								<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoSlash" value="/" readonly></td>

								<td style="padding: 0 1px;">
									<div class="input-group">
										<input class="form-control-trx" type="text" id="invnorsp" value="">
										<div class="input-group-addon">
											<span class="fa fa-search" onclick="getRetSaleDetbyInvId(document.getElementById('invnorsp').value,document.getElementById('retmemoFinyr').value,document.getElementById('retmemoDoc').value)"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustph" type="text" value="${saleReturnDTO.customerPhone}" readonly><input id="saleretcustid" type="hidden" value="${saleReturnDTO.customerId}"></td>

								</td>
								<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustname" type="text" value="${saleReturnDTO.customerName}" readonly></td>
								<td width="20%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" id="saleretdocname" type="text" value="${saleReturnDTO.doctorName}" readonly><input id="saleretdocid" type="hidden" value="${saleReturnDTO.doctorId}"></td>

								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter22">
			<div id="header_div_panel2" class="panel-trx panel-default">
				<div class="panel-body-trx" id="header_div_rsp">
					<table>
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
							<td>Barcode</td>
							<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
							<td id="lqty_label" class="hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>

							<td class="hide"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.expdate" text="ExpDate" /></td>

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
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_rpqty_rsp" value="0"></td>
							<td style="padding: 0 1px;" id="free_qty_rsp"  class="${userinfo.isFree==1 ?'':'hide'}">
							<input class="form-control-trx"  type="text" onkeydown="numcheck(event)"  id="item_rfqty_rsp" value="0">
							<input class="form-control-trx" type="hidden" id="item_lotadjasment_rsp" value="0">
							</td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="item_rlqty_rsp" value="0"></td>

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
								<td><button class="btn btn-success btn-sm" title="add into table" type="button" onclick="addOrUpdateItemToDetailsTablersp(0)" id="add_btn_rsp">
										<spring:message code="cmn.jsp.addcaps" text="ADD" />
									</button>
									<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" title="add into table" onclick="addOrUpdateItemToDetailsTablersp(1)" id="edit_btn_rsp">
										<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
									</button></td>
								<td><button class="btn btn-primary btn-sm" type="button" onclick="clearHeaderDivrsp()">
										<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
									</button></td>
							</c:if>

						</tr>
						<tr align="center" style="font-weight: bold;">
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
							<td style="padding: 0 1px;" class="hide" id="ratio_val_rsp"><input class="form-control-trx" type="text" id="item_conv_rsp" readonly></td>
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
							<input type="hidden" id="item_hsnCode_rsp" value="0"></td>
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
			<div style="overflow: auto;height:250px;" id="detail_table1">
				<table id="salretitem" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
							<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
							<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
							<th class="hide"><spring:message code="purinvdet.jsp.expdate" text="ExpDate" /></th>
							<th class="hide"><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
							<th class="${userinfo.isFree==1 ?'':'hide'}" ><spring:message code="purinvdet.jsp.free" text="Free" /></th>
							<th class="numeric hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
<%-- 							<th class="numeric"><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></th> --%>
							<th class= "${mrpEnableFlag==1?' ': 'hide'}"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
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
								<tr id="${saleRetDetail.itemUniqueKey}" style='cursor: pointer;' onclick="javascript:itemHeaderDivViewrsp(this.id);">
									<td id="salerettabname">${saleRetDetail.itemName}</td>
									<td id="salerettabbat">${saleRetDetail.batchNo}</td>
									<td id="salerettabexpdt">${saleRetDetail.expiryDateFormat}</td>
									<td id="salerettabexpdate" class="hide">${saleRetDetail.expiryDate}</td>
									<td id="salerettabmanname" class="hide">${saleRetDetail.manufacturerName}</td>
									<td class="numeric" id="salerettabpqty">${saleRetDetail.packQty}</td>
									<td class="numeric ${userinfo.isFree==1 ?'':'hide'}" id="salerettabfqty">${saleRetDetail.freeQty}</td>
									<td class="numeric hide" id="salerettablqty">${saleRetDetail.looseQty}</td>
									<td class="numeric" id="salerettabconv">${saleRetDetail.conversion}</td>
									<td class="numeric" id="salerettabmrppack"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.mrp}" /></td>
<%-- 									<td class="numeric" id="salerettabmrp"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.mrpPerUnit}" /></td> --%>
									<td class="numeric" id="salerettabrateperunit"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.ratePerUnit}" /></td>
									<td class="numeric" id="salerettabdiscperc"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.discPer}" /></td>
									<td class="numeric" id="salerettabtotamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.totAmount}" /></td>
									<td><c:if test="${saleReturnDTO.isPosted ==0}">
											<button class="btn btn-theme04 btn-xs" id="${saleRetDetail.itemUniqueKey}" onclick='javascript:showSelRetItemDelModal(this.id);'>
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
									<td id='salerettabtaxTypeId' class='hide'>${saleRetDetail.taxTypeId}</td>
									<c:if test="${!empty saleRetDetail.saleReturnDetailsSerialMapper}">
											<c:forEach items="${saleRetDetail.saleReturnDetailsSerialMapper}" var="saleretDetailsSerialMapper" varStatus="salesDetSerialMapper">
											<c:set var="tblslnoseditret" value="${tblslnoseditret}${saleretDetailsSerialMapper.uniqueIdentifierNo}," />
											</c:forEach>
											<td id="salerettabitemslnolist" class="numeric hide">${tblslnoseditret}</td>
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
		<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail1">

			<fieldset class="col-lg-12 col-md-12 col-sm-12">

		<input type="hidden" id="dueties_and_tax_acc" value="DT">
		<input type="hidden" id="saleaccunt_group_code" value="SA">
		<input type="hidden" id="roundoff_group_code" value="ROD">
		<input type="hidden" id="debitor_group_code" value="SDE">

		<input type="hidden" id="cash_in_hand_group_code" value="CIH">
		<legend class="hide">Account</legend>
			<table id="final_table82" >
						<!--  for account start  -->
				<tbody><tr class="hide">
					<th colspan="2" width="8%"> </th>
					<th colspan="2" class="font-bold" width="40%">
					<input type="hidden" id="duties_ledger_id" value="132">
					<span id="duties_html">DUTIES &amp; TAXES - Duties and Taxes (Debit) </span>
				<!-- 	Duties & Taxes Account Debit -->
					<!-- <input type="text" id="dueties_and_tax" value=""> -->
					</th>
					<th colspan="2" class="font-bold">
				     	<input type="hidden" id="round_ledger_id" value="133">
						<span id="round_html">ROUND OFF A/C - Round Off A/C</span>
					<!-- Round of Account (-)Credit/(+)Debit --></th>
					<th class="font-bold ">
					<input type="hidden" id="sales_ledger_id" value="39">
					<span id="sales_html">SALES ACCOUNTS  - Sales Accounts (Debit) </span>
					</th>
					<th width="20%" class="font-bold ">
				     <input type="hidden" id="debitor_ledger_id" value="137">
				   	<span id="debitor_html">CASH IN HAND - Cash in Hand (Credit) </span>
					</th>

				</tr>
				<!--  for  account  end-->

					<tr>
					<td width="8%" class="font-bold">ItemCount:</td>
					<td width="2%" class="font-bold"><span id="totitmcount_rsp">1</span></td>
					<td class="font-bold hide">Total:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrossamt_rsp" value="0.0" readonly=""></td>
					<td class="font-bold hide">Tot.VAT</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamt_rsp" value="0.0" tabindex="-1" readonly=""></td>
					<td class="font-bold ">Tot.TAX</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="tottaxamt_rsp" value="0.0" tabindex="-1" readonly="">
					</td>
					<td class="font-bold hide">Tot.Disc:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="" value="" readonly=""></td>
					<td class="font-bold">R.Off:</td>
					<td style="padding-right: 5px;" width="8%"><input class="form-control-trx" type="text" id="roundoff_rsp" value="0.00" readonly="readonly"></td>
			 	    <td class="hide"><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><input class="form-control-trx" type="text" id="sale_account_rsp" readonly="" value="0"></span></td >
					<td width="8%" style="padding-right: 5px;" class="hide">
					<input class="form-control-trx" type="text" id="debitor_amt_rsp" readonly="" value="0"></td>
					<td width="7%"><span style="font-size: 16px; font-weight: bold; color: #d43f3a;">Ret.Amt:</span></td>
					<td width="10%" style="padding-right: 5px;"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="totretamt_rsp" value="0.00" readonly=""></td>


				</tr>


				<tr>
					<td class="font-bold">Reason:</td>
					<td colspan="3" style="padding: 3px 5px 0 0"><input class="form-control-trx" id="remarks_rsp" type="text" value=""></td>
					<!--  for lot  adjasment -->
	                <td class="hide" id="flotid_rsp"><spring:message code="purinvdet.jsp.ltadj" text="Lt.Adj" /></td>
					<td style="padding-right: 5px;" class="hide" id="totltadj_rsp"><input class="form-control-trx" type="text" id="totlotadjasment_rsp" value="${saleReturnDTO.lotAdjAmount}" tabindex="-1" readonly></td>



					<td class="font-bold hide">Bill.Disc(%):</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="spcldiscperc_rsp" value="0.0" onkeyup="calculateSpclDiscrsp()"></td>
					<td class="font-bold hide">Bill.Disc.Amt:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="spcldisc_rsp" value="0.0" readonly=""></td>

					<td colspan="3" style="padding-top: 4px; padding-right: 4px;">

								<button style="padding: 5px 8px;" title="For sale return " class="btn btn-primary btn-sm" type="button" onclick="saveOrUpdateSaleReturnInv()">
									RETURN
								</button>

							<button class="btn btn-success btn-sm"  title="For new sale return "  type="button" id="" onclick="openRetMemo()">
								NEW
							</button>

								<button style="padding: 5px 8px;" title="For delete sale return "  class="btn btn-danger btn-sm" type="button" id="" onclick="deleteRetSalesInv()">
									DEL
								</button>

						</td>

				</tr>
			</tbody></table>
			</fieldset>
		</div>
					</p>
				</div>
			</div>
		</div>


	</div>
</section>



<div class="modal fade" id="itemsByContentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.itemnameforgeneric" text="Item name for " />
					<span id="modsearchedcontent"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="contentnotmatchdiv" class="hide">
					<input type="hidden" id="contentnotmatchid"> <span class="font-bold" id="itemnotmatchcontent"><spring:message code="pos.jsp.itemnotmatchcontent" text="No Item match with this content " />: <span id="contentnotfoundname"> </span> </span>
				</div>
				<div id="itemsearchtable">
					<table id="itemsbycontentidtbl" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th>Item Name</th>
							</tr>
						</thead>
						<tbody id="itemsbycontentid">

						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
			</div>
		</div>
	</div>
</div>
<!--  for manufacturer  -->



<div class="modal fade" id="itemsByManufactureModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.itemnameforgeneric" text="Item name for " />
					<span id="modsearchedmanufacture"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="manufacturenotmatchdiv" class="hide">
					<input type="hidden" id="manufacturenotmatchid"> <span class="font-bold" id="itemnotmatchmanufature"><spring:message code="pos.jsp.itemnotmatchmanufacturer" text="No Item match with this manufacturer " />: <span id="manufacturernotfoundname"> </span> </span>
				</div>
				<div id="itemsearchtablemanu">
					<table id="itemsbymanufactureidtbl" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th>Item Name</th>
							</tr>
						</thead>
						<tbody id="itemsbymanufactureid">

						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="stkdetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.stckdetailsfor" text="Stock Details for" />
					<span id="moditemname"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div>
					<span class="font-bold"><spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" /> :</span> <span id="modmanufname"></span>
				</div>
				<div class="hide">
					<span class="font-bold"><spring:message code="itemmstr.jsp.content" text="Content" /> :</span> <span id="modcontentname"> </span>
				</div>
				<div>
					<span class="font-bold"><spring:message code="itemmstr.jsp.group" text="Group" /> :</span> <span id="modgroupname"> </span>
				</div>
				<div>
					<span class="font-bold"><spring:message code="itemmstr.jsp.rack" text="Rack" /> :</span> <span id="modrackname">  </span>
				</div>
				<div>
					<span class="font-bold">Note :</span> <span id="moditemnote"> </span>
				</div>
				<div id="snditmtopodiv" class="">
					<input type="hidden" id="senditemtopoid">
					<button type="button" onclick="sendItemToPO(1)" class="btn btn-theme">Send Item To PO</button>
					&nbsp; &nbsp;<span id="snditmpo" class="alert alert-success hide" style="padding: 6px;"></span>
				</div>
				<div id="itemnotfounddiv" class="hide">
					<input type="hidden" id="itemnotfoundid"> <span class="font-bold" id="itemnotfound"><spring:message code="pos.jsp.nostockerror" text="No stock found for" /> <span id="itemnotfoundname"> </span>

						<button type="button" onclick="getGenericMed()" data-dismiss="modal" class="btn btn-theme hide">
							<spring:message code="footer.jsp.btn.getaltmed" text="Get Alternate Medicine" />
						</button></span>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto; margin-top: 1%;" id="modtable">
					<table id="itemstockdetails" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.expdate" text="ExpDate" /></th>
								<th><spring:message code="pos.jsp.currstock" text="Cur Stock" /></th>
								<th><spring:message code="pos.jsp.holdstock" text="Hold Stock" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th class="numeric hide"><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></th>
								<th class="numeric hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
								<th class="hide"><spring:message code="pos.jsp.packing" text="Packing" /></th>
								<th>P. Rate</th>
								<th id="salerateheader">S. Rate</th>
							</tr>
						</thead>
						<tbody id="itemdetails">

						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td class="font-bold" id="totalcurrstkitm">48/0 [480]</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tfoot>

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


<div class="modal fade" id="alternateMedModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.altrntmed" text="Alternate Medicine for" />
					<span id="alternateMeditemname"> Telma AM</span>
				</h4>
			</div>

			<div class="modal-body">
				<div id="altmeditemnotfounddiv" class="hide">
					<span class="font-bold" id="altmeditemnotfound"><spring:message code="pos.jsp.altrntmederror" text="No alternate medicine found for" /></span> <span id="altmeditemnotfoundname"> </span>
				</div>
				<div style="max-height: 350px; height: 300px; overflow: auto;" id="alternateMedtable">
					<table id="alternateMeddetails" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="pos.jsp.name" text="Name" /></th>
								<th style="width: 30%;"><spring:message code="pos.jsp.mfgname" text="Mfg. Name" /></th>
								<th><spring:message code="pos.jsp.netcntnt" text="Net.Cont" /></th>
								<th><spring:message code="itemmstr.jsp.price" text="Price" /></th>
								<th><spring:message code="pos.jsp.stkqty" text="Stk.Qty" /></th>

							</tr>
						</thead>
						<tbody id="alternatemeddetails">

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

<%-- <div class="modal fade" id="cardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">

				<h4 class="modal-title" id="myModalLabel">Card Details</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label">Card Amount :</label>
						<div class="col-sm-8">
							<input class="form-control-trx" type="text" id="cardAmtMod" value="${saleHeaderDTO.cardAmount}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label">Card Expiry :</label>
						<div class="col-sm-8">
							<input class="form-control-trx" type="text" id="cardExpMod" value="${saleHeaderDTO.cardExpDate}" placeholder="MM/YY">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label">Card Last Four Digit :</label>
						<div class="col-sm-8">
							<input class="form-control-trx" type="text" id="cardFourDigitMod" value="${saleHeaderDTO.cardFourDigit}" placeholder="XXXX">
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" onclick="holdCardDetails()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div> --%>
<div class="modal fade" id="payModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<!-- 				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.payBtn" text="Pay" />
				</h4>
			</div>
			<c:if test="${saleHeaderDTO.holdFlag ==0}">
			<div class="modal-body">
			<div class="row">
			<div class="col-sm-12" id="saleInvDiv">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="pos.jsp.billamt" text="Bill Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" style="font-weight: bold; color: #000000; background-color: #ebccd1;" id="paymodnettot" value="${saleHeaderDTO.netAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.retrnadjamt" text="Return Adj. Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="payretadjamt" value="${saleHeaderDTO.adjAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3" style="padding-left: 10px;">
								<button class="btn btn-primary btn-sm" type="button" title="Add Sales Return Memo" onclick="openretadjmod()" id="" style="text-transform: uppercase;">
									<i class="fa fa-reply"></i>
									<spring:message code="pos.jsp.retrnmemo" text="Return Memo" />
								</button>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.tenderamt" text="Tender Amount" /> :</label>
							<div class="col-sm-5">
							<c:if test="${numerickeyboardon==0}">
								<input class="form-control-trx" type="text" id="tenderamt" onkeydown="numcheck(event)"  value="${saleHeaderDTO.tenderAmount}" onblur="cTA()">
							</c:if>
							<c:if test="${numerickeyboardon==1}">
								<input class="form-control-trx tenderamt" type="text"  onkeydown="numcheck(event)"  id="tenderamt" value="${saleHeaderDTO.tenderAmount}" >
							</c:if>
							</div>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.refundamt" text="Refund Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="refundAmt" value="" readonly="readonly">
							</div>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cashamt" text="Cash Amount" /> :</label>
							<div class="col-sm-5">
							<c:if test="${numerickeyboardon==0}">
								<input class="form-control-trx" type="text" onkeydown="numcheck(event)"  id="cashAmt" value="${saleHeaderDTO.cashAmount}">
							</c:if>
							<c:if test="${numerickeyboardon==1}">
								<input class="form-control-trx cashAmt" onkeydown="numcheck(event)"  type="text" id="cashAmt" value="${saleHeaderDTO.cashAmount}">
							</c:if>
							</div>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.creditamt" text="Credit Amount" /> :</label>
							<div id="creditCustDiv" class="col-sm-8 col-sm-8 hide">
								<div class="col-sm-4" style="padding: 0;">
									<input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly">
								</div>
								<div class="col-sm-4">
									<label class="control-label"><spring:message code="pos.jsp.avlamt" text="Avl Limit" /> :</label>
								</div>
								<div class="col-sm-4" style="padding: 0;">
									<input class="form-control-trx" type="text" id="creditLimit" readonly="readonly">
								</div>
							</div>
							<div id="notCreditCustDiv">
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly">
								</div>
							</div>
						</div>
						<div id="carddiv" style="display: none;">
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardamt" text="Card Amount" /> :</label>
								<div class="col-sm-5">
								<c:if test="${numerickeyboardon==0}">
									<input class="form-control-trx" type="text" onkeydown="numcheck(event)"  id="cardAmt" value="${saleHeaderDTO.cardAmount}">
								</c:if>
								<c:if test="${numerickeyboardon==1}">
									<input class="form-control-trx cardAmt" type="text" onkeydown="numcheck(event)"  id="cardAmt" value="${saleHeaderDTO.cardAmount}">
								</c:if>
								</div>
								<div class="col-sm-3">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardexp" text="Card Expiry" /> :</label>
								<div class="col-sm-5">
									<input class="form-control-trx" type="text" id="cardExpMod" value="${saleHeaderDTO.cardExpDate}" placeholder="MM/YY" maxlength="5">
								</div>
								<div class="col-sm-3">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardlstdigit" text="Card Last Four Digit" /> :</label>
								<div class="col-sm-5">
									<input class="form-control-trx" type="text" id="cardFourDigitMod" value="${saleHeaderDTO.cardFourDigit}" placeholder="XXXX" maxlength="4">
								</div>
								<div class="col-sm-3">
								</div>
							</div>
						</div>
						<div class="form-group">
							<c:if test="${saleHeaderDTO.holdFlag ==0}">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.prntinv" text="Print Invoice" /> : </label>

								<div class="col-sm-6">
								<c:if test="${setprinter==1}">
								<input type="checkbox" id="printInv" name="printInv" checked="checked" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
								</c:if>
								<c:if test="${setprinter==2}">
								<input type="checkbox" id="printInv" name="printInv" checked="checked" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
								</c:if>
								</div>
							</c:if>
							<div class="col-sm-2">
								<button class="btn btn-primary btn-sm" type="button" id="cardbut" title='Open/close Card Pay Option' style="text-transform: uppercase;">
									<i class="fa fa-credit-card" aria-hidden="true"></i>
									<spring:message code="pos.jsp.card" text="CARD" />
								</button>
							</div>
						</div>
						<c:if test="${saleHeaderDTO.holdFlag ==0}">
							<strong><spring:message code="pos.jsp.asktopay" text="Are you sure to pay" /> <span id="docorcus"> </span> ? </strong>
						</c:if>
						<div>
							<span id="alertmessagecont" style="font-weight: bold; color: red;"> </span>
						</div>
					</div>
				</div>
				<div class="col-sm-6 hide" id="account">



											<!-- add for account  -->

											 <input type="hidden" id="debitor_code1" value="SDE">
											  <input type="hidden" id="saleac_code1" value="SA">
											 <input type="hidden" id="dutiesandtax_code1" value="DT">
											 <input type="hidden" id="dicount_code1" value="DIS">
											 <input type="hidden" id="roundof_code1" value="ROD">
											  <input type="hidden" id="cash_code1" value="CIH">
											  <input type="hidden" id="card_code1" value="CAB">
			      <div class="form-horizontal" id="account_from">
			      <!-- sale ledger-->
		             <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label">
							<input type="hidden" id="sales_ledger_id1" value="39"><span id="sales_html1">Cr-SALES ACCOUNTS  - Sales Accounts</span>
							</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style="color: #000000;" id="pos_sale" readonly="readonly">
							</div>
						</div>

						<!--  duties ledger -->
						  <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label">

								<input type="hidden" id="duties_ledger_id1" value="132"><span id="duties_html1">Cr-DUTIES &amp; TAXES - Duties and Taxes</span>
							</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style=" color: #000000; " id="pos_duties" readonly="readonly">
							</div>
						</div>
						<!-- discount ledger -->
						  <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label">
						<input type="hidden" id="discount_ledger_id1" value="134">
					     	<span id="discount_html1">Dr-DISCOUNT A/C - Discounts A/C</span>
							</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style=" color: #000000; " id="pos_discount" readonly="readonly">
							</div>
						</div>
						<!--  round of ledger -->
						  <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label">
								<input type="hidden" id="round_ledger_id1" value="133"><span id="round_html1">ROUND OFF A/C - Round Off A/C</span>
							</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style=" color: #000000; " id="pos_round" readonly="readonly">
							</div>
						</div>

						<!--  sunddy debitor  ledger -->
						  <div class="form-group" id="creditor_led">
							<label class="col-sm-4 col-sm-4 control-label">
							 <input type="hidden" id="debitor_ledger_id1" value="137"><span id="debitor_html1">Dr-CASH IN HAND - Cash in Hand</span>
							</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style="color: #000000; " id="pos_debit" readonly="readonly">
							</div>
						</div>
					<!--  cash   ledger -->
						  <div class="form-group hide" id="cash_led">
							<label class="col-sm-4 col-sm-4 control-label">
							 <input type="hidden" id="debitor_cahs_ledger_id1" value="0"><span id="cash_ledger_html1"></span>
							</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style="color: #000000; " id="pos_cash_debit" readonly="readonly">
							</div>
						</div>

					<div id="card_led_div" style="display: none;">

					<input type="hidden">
						<!--  card debitor  ledger -->
						  <div class="form-group" id="card_led">
							<label class="col-sm-4 col-sm-4 control-label">

							   <input type="hidden" id="card_ledger_id_final" value="0">
				         	<input type="text" class="form-control-trx" style="margin-bottom: 2px;" id="card_ledger_id1" name="card_ledger_id_val" onkeyup="searchledger(1)" placeholder="search bank ledger">


							</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style="color: #000000; " id="card_debit_amt" readonly="readonly">

							</div>
						</div>
					</div>




						</div>
						</div>
			</div>
			</div>
				<%-- <div class="modal-body" id="saleInvDiv">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="pos.jsp.billamt" text="Bill Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style="font-weight: bold; color: #000000; background-color: #ebccd1;" id="paymodnettot" value="${saleHeaderDTO.netAmount}" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.retrnadjamt" text="Return Adj. Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="payretadjamt" value="${saleHeaderDTO.adjAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3" style="padding-left: 10px;">
								<button class="btn btn-primary btn-sm" type="button" onclick="openretadjmod()" id="" style="text-transform: uppercase;">
									<i class="fa fa-reply"></i>
									<spring:message code="pos.jsp.retrnmemo" text="Return Memo" />
								</button>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.tenderamt" text="Tender Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="tenderamt" value="${saleHeaderDTO.tenderAmount}" onblur="cTA()">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.refundamt" text="Refund Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="refundAmt" value="" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cashamt" text="Cash Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="cashAmt" value="${saleHeaderDTO.cashAmount}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.creditamt" text="Credit Amount" /> :</label>
							<div id="creditCustDiv" class="col-sm-8 col-sm-8 hide">
								<div class="col-sm-4" style="padding: 0;">
									<input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly">
								</div>
								<div class="col-sm-4">
									<label class="control-label"><spring:message code="pos.jsp.avlamt" text="Avl Limit" /> :</label>
								</div>
								<div class="col-sm-4" style="padding: 0;">
									<input class="form-control-trx" type="text" id="creditLimit" readonly="readonly">
								</div>
							</div>
							<div id="notCreditCustDiv">
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly">
								</div>
							</div>
						</div>
						<div id="carddiv" style="display: none;">
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardamt" text="Card Amount" /> :</label>
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="cardAmt" value="${saleHeaderDTO.cardAmount}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardexp" text="Card Expiry" /> :</label>
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="cardExpMod" value="${saleHeaderDTO.cardExpDate}" placeholder="MM/YY" maxlength="5">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardlstdigit" text="Card Last Four Digit" /> :</label>
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="cardFourDigitMod" value="${saleHeaderDTO.cardFourDigit}" placeholder="XXXX" maxlength="4">
								</div>
							</div>
						</div>
						<div class="form-group">
							<c:if test="${saleHeaderDTO.holdFlag ==0}">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.prntinv" text="Print Invoice" /> : </label>
								<div class="col-sm-3">
								A4	<input type="radio" id="printInv" name="printInv" value="A4" checked="checked" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
								</div>
								<div class="col-sm-3">
								80mm	<input type="radio" id="printInv" name="printInv" value="80" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
								</div>
							</c:if>
							<div class="col-sm-2">
								<button class="btn btn-primary btn-sm" type="button" id="cardbut" style="text-transform: uppercase;">
									<i class="fa fa-credit-card" aria-hidden="true"></i>
									<spring:message code="pos.jsp.card" text="CARD" />
								</button>
							</div>
						</div>
						<c:if test="${saleHeaderDTO.holdFlag ==0}">
							<strong><spring:message code="pos.jsp.asktopay" text="Are you sure to pay" /> <span id="docorcus"> </span> ? </strong>
						</c:if>
						<div>
							<span id="alertmessagecont" style="font-weight: bold; color: red;"> </span>
						</div>
					</div>
				</div> --%>
			</c:if>
			<c:if test="${saleHeaderDTO.holdFlag ==1}">
				<div class="modal-body" id="saleInvDivpaydet">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="pos.jsp.billamt" text="Bill Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" style="font-weight: bold; color: #000000; background-color: #ebccd1;" id="paymodnettot" value="${saleHeaderDTO.netAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.retrnadjamt" text="Return Adj. Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="viewpayretadjamt" value="${saleHeaderDTO.adjAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3" style="padding-left: 6px;">
								<button class="btn btn-primary btn-sm" type="button" onclick="viewretadjamt()" id="" style="text-transform: uppercase;">
									<i class="fa fa-reply"></i>
									<spring:message code="pos.jsp.retrnmemodt" text="Ret Memo Det" />
									.
								</button>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.tenderamt" text="Tender Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="tenderamt" value="${saleHeaderDTO.tenderAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.refundamt" text="Refund Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="refundAmt" value="" readonly="readonly">
							</div>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cashamt" text="Cash Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="cashAmt" value="${saleHeaderDTO.cashAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.creditamt" text="Credit Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3">
							</div>
						</div>
						<div id="carddiv" style="display: none;">
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardamt" text="Card Amount" /> :</label>
								<div class="col-sm-5">
									<input class="form-control-trx" type="text" id="cardAmt" value="${saleHeaderDTO.cardAmount}" readonly="readonly">

								</div>
								<div class="col-sm-3">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardexp" text="Card Expiry" /> :</label>
								<div class="col-sm-5">
									<input class="form-control-trx" type="text" id="cardExpMod" value="${saleHeaderDTO.cardExpDate}" placeholder="MM/YY" maxlength="5" readonly="readonly">
								</div>
								<div class="col-sm-3">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardlstdigit" text="Card Last Four Digit" /> :</label>
								<div class="col-sm-5">
									<input class="form-control-trx" type="text" id="cardFourDigitMod" value="${saleHeaderDTO.cardFourDigit}" placeholder="XXXX" maxlength="4" readonly="readonly">
								</div>
								<div class="col-sm-3">
								</div>
							</div>
						</div>
						<div class="form-group">
							<c:if test="${saleHeaderDTO.holdFlag ==0}">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.prntinv" text="Print Invoice" /> : </label>
								<div class="col-sm-6">
								<c:if test="${setprinter==1}">
								<input type="checkbox" id="printInv" name="printInv" checked="checked" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
								</c:if>
								<c:if test="${setprinter==2}">
								<input type="checkbox" id="printInv" name="printInv" checked="checked" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
								</c:if>
								</div>
							</c:if>
							<div class="col-sm-2">
								<button class="btn btn-primary btn-sm" type="button" id="cardbut" style="text-transform: uppercase;">
									<i class="fa fa-credit-card" aria-hidden="true"></i>
									<spring:message code="pos.jsp.card" text="Card" />
								</button>
							</div>
						</div>
						<c:if test="${saleHeaderDTO.holdFlag ==0}">
							<strong><spring:message code="pos.jsp.asktopay" text="Are you sure to pay" /> ?</strong>
						</c:if>
					</div>
				</div>
			</c:if>
			<div class="modal-footer">
				<c:if test="${saleHeaderDTO.holdFlag ==0}">
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearSaleInv()">
						<spring:message code="cmn.jsp.btn.close" text="Close" />
					</button>
				</c:if>
				<c:if test="${saleHeaderDTO.holdFlag ==1}">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="cmn.jsp.btn.close" text="Close" />
					</button>
				</c:if>
				<c:if test="${saleHeaderDTO.holdFlag ==0}">
					<button type="button" onclick="paySaleInv()" style="padding: 6px 30px;" class="btn btn-theme">
						<spring:message code="footer.jsp.btn.ok" text="OK" />
					</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
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
				<spring:message code="itemmstr.jsp.exist.error" text="Item with same batch and expiry already exist, please try other." />
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
<div class="modal fade" id="saveWithoutCustAndDocNameModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="pos.jsp.asktoholdcashmemo" text="Want to hold cashmemo without Doctor/Customer name" />
				?
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="saveOrUpdateSaleInv()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal Start -->

<div class="modal fade" id="confirmModalPos" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
				<input type="hidden" id="confirmIdret">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmPos()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->
<div class="modal fade" id="scheleXorH1Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold" style="color: red;">
				<spring:message code="pos.jsp.clctprescriptionmsg" text="Please collect the prescription and add Doctor & Customer name." />
				<input type="hidden" id="operationtype">
			</div>
			<div class="modal-footer">
				<button type="button" onclick="addItemtotable(document.getElementById('operationtype').value)" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="currStkGraterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
			<input type="hidden" id="curstkcheck" value="0" />
				<spring:message code="pos.jsp.totqtygrtcrntstckmsg" text="Total quantity is greater than current stock" />
				( <span id="currstkofitm"></span> ).

				<div id="snditmtopodiv" class="">
					<input type="hidden" id="senditemtopoid">
					<button type="button" onclick="sendItemToPO(2)" class="btn btn-theme">Send Item To PO</button>
					&nbsp; &nbsp;<span id="snditmpo2" class="alert alert-success hide" style="padding: 6px;"></span>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" onclick="closeCurrStkModal()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Cust Last Bills Modal -->
<div class="modal fade" id="custLastBillModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.prevbillfor" text="Previous Bills for" />
					<span id="cusprebillphno"> </span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="prevsaledetnotfounddiv" class="hide">
					<span class="font-bold" id="prevsalenotfound"> <spring:message code="pos.jsp.prevcashmemonotprsnterr" text="Previous cashmemo not found for this Customer" />.
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;" id="saledetmodtable">
					<table id="" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="modcancashmemo.jsp.invmode" text="Inv Mode" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.total" text="Total" /></th>
								<th><spring:message code="modcancashmemo.jsp.discount" text="Dis.Amt." /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal" /></th>
								<th><spring:message code="purinvreg.jsp.status" text="Status" /></th>
							</tr>
						</thead>
						<tbody id="saleheaderdet">

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


<div class="modal fade" id="saledetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="rtrnmemo.jsp.salereturnmodal.invno" text="Sale Details for " />
					<spring:message code="purinvdet.jsp.invno" text="Invoice No" />
					. <span id="searchmodinvno"> SIM/${sessionScope.sesloggedinUser.finyrCode}/00001</span>
				</h4>
			</div>
			<div class="modal-body">

				<div style="max-height: 300px; height: 200px; overflow: auto;" id="saledetmodtable">
					<table id="searchmodtable" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="rtrnmemo.jsp.salereturnmodal.batch" text="Batch No" /></th>
								<th><spring:message code="rtrnmemo.jsp.salereturnmodal.exp" text="Exp Dt" /></th>
								<th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>

								<th><spring:message code="purinvdet.jsp.mrpls" text="MRP/Ls" /></th>
								<th><spring:message code="purinvdet.jsp.ratels" text="Rate/Ls" /></th>
							</tr>
						</thead>
						<tbody id="searchmodtbody">

						</tbody>
						<div>
							<span id="alertmessagecont" style="font-weight: bold; color: red;"> </span>
						</div>

					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" class="btn btn-primary" onclick="">
					<spring:message code="footer.jsp.btn.ok" text="Ok" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- End Cust Last Bills MOdal -->
<!-- Return Adj Modal -->
<div class="modal fade" id="retAdjModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 666px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.retrnmemotoadjst" text="Return Memo to Adjust for Bill Amt" />
					. <span id="billamtheaderAdj"></span>
				</h4>
			</div>
			<div class="modal-body">

				<table>
					<tr>
						<td><spring:message code="pos.jsp.memono" text="Memo.No" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjmemoDoc" value="SRM/" size="4" readonly></td>
						<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjmemoFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
						<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retadjmemoSlash" value="/" readonly></td>
						<td><input class="form-control-trx" type="text" id="retadjmemono" value="" size="10"></td>
						<td style="padding: 0 1px;"><spring:message code="pos.jsp.custphn" text="Cust.Ph" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="retadjcustph" value=""></td>
						<td style="padding: 0 1px;"><spring:message code="pos.jsp.custname" text="Cust.Name" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="retadjcustname" readonly="readonly" value=""></td>
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

<div class="modal fade" id="viewretAdjModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 666px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.retrnmemodetails" text="Return Memo Details" />
				</h4>
			</div>
			<div class="modal-body">

				<div>
					<p>
						<spring:message code="pos.jsp.addedmemos" text="Added Memos" />
					</p>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="viewretadjDetails">
					<table id="viewretadjtable" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
								<!-- 								<th>Pre Adj Amt</th> -->
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
							</tr>
						</thead>
						<tbody id="viewretadjtbody">

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
<!-- End Return Adj Modal -->

<div class="modal fade" id="confirmModalRetadj" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
				<button type="button" onclick="DoConfirmRetAdj()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Add customer modal start -->
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
							<label class="col-sm-4 col-sm-4 control-label" id="cust_name_label"><spring:message code="customer.jsp.name" text="Customer Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="customerName" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="phn_label"><spring:message code="customer.jsp.phn" text="Phone No." /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="phn" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="customer.jsp.address" text="Address" /></label>
							<div class="col-sm-8">
								<input type="text" id="addrs" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="customer.jsp.pin" text="Pin" /></label>
							<div class="col-sm-8">
								<input type="text" id="pin" class="form-control">
							</div>
						</div>
					<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="fax_label"><spring:message code="customer.jsp.fax" text="Fax" /></label>
							<div class="col-sm-8">
								<input type="text" id="fax" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.aadharno" text="Aadhar card no." /></label>
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
							<spring:message code="customer.jsp.dlno" text="DL No." />
							</label>

							<div class="col-sm-8">
								<input type="text" id="dlno" class="form-control">
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
				<%-- 		<div class="form-group">
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
						</div>

							<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="customer.jsp.city" text="City" /></label>
							<div class="col-sm-8">
								<input type="text" id="city" class="form-control">
							</div>
						</div>
 --%>
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
							<label class="col-sm-4 col-sm-4 control-label" id="code_label"><c:if test="${sesloggedinStore.isEsi==1}">
									<spring:message code="customer.jsp.inscardno" text="Ins/Card No" />
								</c:if> <c:if test="${sesloggedinStore.isEsi==0}">
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
								<input type="text" id="creditLimitAdd" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label">Type</label>
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
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="custAddAlertMsg"></div>
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
<!-- Add customer modal end -->
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
<!-- Add doctor modal start -->
<div class="modal fade" id="doctorAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 836px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertextDoc"></span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="doc_name_label"><spring:message code="dctr.jsp.name" text="Doctor Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="dctrName" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="dctr.jsp.code" text="Code" /></label>
							<div class="col-sm-8">
								<input type="text" id="code" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="locked_label"><spring:message code="dctr.jsp.locked" text="Is Locked" /></label>
							<div class="col-sm-8">
								<input type="checkbox" id="locked">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="qualification_label"><spring:message code="dctr.jsp.qualification" text="Qualification" /></label>
							<div class="col-sm-8">
								<input type="text" id="qualification" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="speciality_label"><spring:message code="dctr.jsp.speciality" text="Speciality" /></label>
							<div class="col-sm-8">
								<input type="text" id="speciality" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="dctr.jsp.address" text="Address" /></label>
							<div class="col-sm-8">
								<input type="text" id="addrs" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="dctr.jsp.pin" text="Pin" /></label>
							<div class="col-sm-8">
								<input type="text" id="pin" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="dctr.jsp.city" text="City" /></label>
							<div class="col-sm-8">
								<input type="text" id="city" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="dctr.jsp.state" text="State" /></label>
							<div class="col-sm-8">
								<input type="text" id="state" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="country_label"><spring:message code="dctr.jsp.country" text="Country" /></label>
							<div class="col-sm-8">
								<input type="text" id="country" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="phn_label"><spring:message code="dctr.jsp.phn" text="Phone No." /></label>
							<div class="col-sm-8">
								<input type="text" id="phn" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="fax_label"><spring:message code="dctr.jsp.fax" text="Fax" /></label>
							<div class="col-sm-8">
								<input type="text" id="fax" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="opbal_label"><spring:message code="dctr.jsp.opbal" text="Opening Balance" /></label>
							<div class="col-sm-8">
								<input type="text" id="opbal" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="commPer_label"><spring:message code="dctr.jsp.commPer" text="Commision Percentage" /></label>
							<div class="col-sm-8">
								<input type="text" id="commPer" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="dctr_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="docAddAlertMsg"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditDoctor()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add doctor modal end -->

<div class="modal fade" id="saletableItemDelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="footer.jsp.cnfrmquestion" text="Are you sure?" />
				<input type="hidden" id="saletableitemdelid" value="0">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="closeSaletableItemDel(document.getElementById('saletableitemdelid').value)" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="itemMaxDisModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				Maximum discount allowed to this item is <span id="itemmaxdisperspan"> </span> (%)
			</div>
			<div class="modal-footer">

				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="noItemBarcodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				No item found against this(<span id="inputbarcode"></span>) barcode. Please check the input barcode.
			</div>
			<div class="modal-footer">

				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!--/wrapper -->
<!-- Return page Modal Starts -->

<!-- Modal Starts -->
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
					<div>
						<span class="font-bold"><spring:message code="pos.jsp.dctrName" text="Doc. Name" /> :</span> <span id="searchmoddocname_rsp">Dr T Roy</span>
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
	<div class="modal-dialog" style="width: 948px;">
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

								<th width="14%"><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th class="${userinfo.isSaleman==1? '' :'hide'}"><spring:message code="salesman.jsp.name" text="Salesman Name" /></th>
								<th><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></th>
								<th><spring:message code="pos.jsp.custName" text="Cust. Name" /></th>
								<th class="hide"><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></th>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.expdate" text="ExpDate" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th   class="${userinfo.isFree==1 ?'':'hide'}"  ><spring:message code="purretrn.jsp.freequt" text="Ret F.Qty" /></th>
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
				<input type="hidden" id="confirmvalrsp" value="${saleHeaderDTO.allReturnIds}">
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

<div class="modal fade" id="samepageretnotsave_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div> Please save the return memo first.</div>
			</div>
			<div class="modal-footer">
				<button type="button" onclick="" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="openModalForChangeSeries" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				Series Number : <input type="text" id="seriesval" value="">
			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal" id="">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="updateSeries()" data-dismiss="modal" class="btn btn-theme">
					Update
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="samepageretvalgreater_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div> Return amount is greater than or equals Net-Total. Please add sell item for adjust.</div>
			</div>
			<div class="modal-footer">
				<button type="button" onclick="" data-dismiss="modal" class="btn btn-theme">
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
                      Serial Number
				</h4>
			</div>
			<div class="modal-body">
			<div id='TextBoxesGroup'>
			<div id="TextBoxDiv_1">
		      <label>SL No #1 : </label><input type='textbox' id='textbox1' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate1' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "1" id='checkbox1'></span>
		      <input type="hidden" id="allVal" />
		      <input type="hidden" id="allChkID" />
		      <input type="hidden" id="clk" value="0"/>
		      <input type="hidden" id="qtysl" value="0"/>
		      <input type="hidden" id="maxSlRate" value="0"/>
	        </div>
	        <div id="TextBoxDiv_2" class="hide">
		      <label>SL No #2 :  </label><input type='textbox' id='textbox2' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate2' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "2" id='checkbox2'></span>
	        </div>
	         <div id="TextBoxDiv_3" class="hide">
		      <label>SL No #3 :  </label><input type='textbox' id='textbox3' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate3' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "3" id='checkbox3'></span>
	        </div>
	         <div id="TextBoxDiv_4" class="hide">
		      <label>SL No #4 :  </label><input type='textbox' id='textbox4' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate4' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "4" id='checkbox4'></span>
	        </div>
	         <div id="TextBoxDiv_5" class="hide">
		      <label>SL No #5 :  </label><input type='textbox' id='textbox5' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate5' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "5" id='checkbox5'></span>
	        </div>
	         <div id="TextBoxDiv_6" class="hide">
		      <label>SL No #6 :  </label><input type='textbox' id='textbox6' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate6' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "6" id='checkbox6'></span>
	        </div>
	         <div id="TextBoxDiv_7" class="hide">
		      <label>SL No #7 :  </label><input type='textbox' id='textbox7' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate7' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "7" id='checkbox7'></span>
	        </div>
	        <div id="TextBoxDiv_8" class="hide">
		      <label>SL No #8 :  </label><input type='textbox' id='textbox8' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate8' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "8" id='checkbox8'></span>
	        </div>
	         <div id="TextBoxDiv_9" class="hide">
		      <label>SL No #9 :  </label><input type='textbox' id='textbox9' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate9' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "9" id='checkbox9'></span>
	        </div>
	         <div id="TextBoxDiv_10" class="hide">
		      <label>SL No #10 : </label><input type='textbox' id='textbox10' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate10' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' class = "chk" value = "10" style='zoom:1' id='checkbox10'></span>
	        </div>
	         <div id="TextBoxDiv_11" class="hide">
		      <label>SL No #11 : </label><input type='textbox' id='textbox11' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate11' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "11" id='checkbox11'></span>
	        </div>
	         <div id="TextBoxDiv_12" class="hide">
		      <label>SL No #12 : </label><input type='textbox' id='textbox12' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate12' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "12" id='checkbox12'></span>
	        </div>
	         <div id="TextBoxDiv_13" class="hide">
		      <label>SL No #13 : </label><input type='textbox' id='textbox13' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate13' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "13" id='checkbox13'></span>
	        </div>
	         <div id="TextBoxDiv_14" class="hide">
		      <label>SL No #14 : </label><input type='textbox' id='textbox14' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate14' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "14" id='checkbox14'></span>
	        </div>
	         <div id="TextBoxDiv_15" class="hide">
		      <label>SL No #15 : </label><input type='textbox' id='textbox15' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate15' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "15" id='checkbox15'></span>
	        </div>
	         <div id="TextBoxDiv_16" class="hide">
		      <label>SL No #16 : </label><input type='textbox' id='textbox16' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate16' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "16" id='checkbox16'></span>
	        </div>
	         <div id="TextBoxDiv_17" class="hide">
		      <label>SL No #17 : </label><input type='textbox' id='textbox17' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate17' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "17" id='checkbox17'></span>
	        </div>
	         <div id="TextBoxDiv_18" class="hide">
		      <label>SL No #18 : </label><input type='textbox' id='textbox18' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate18' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "18" id='checkbox18'></span>
	        </div>
	         <div id="TextBoxDiv_19" class="hide">
		      <label>SL No #19 : </label><input type='textbox' id='textbox19' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate19' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "19" id='checkbox19'></span>
	        </div>
	         <div id="TextBoxDiv_20" class="hide">
		      <label>SL No #20 : </label><input type='textbox' id='textbox20' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate20' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "20" id='checkbox20'></span>
	        </div>
	        <div id="TextBoxDiv_21" class="hide">
		      <label>SL No #21 : </label><input type='textbox' id='textbox21' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate21' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "21" id='checkbox21'></span>
	        </div>
	        <div id="TextBoxDiv_22" class="hide">
		      <label>SL No #22 : </label><input type='textbox' id='textbox22' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate22' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "22" id='checkbox22'></span>
	        </div>
	        <div id="TextBoxDiv_23" class="hide">
		      <label>SL No #23 : </label><input type='textbox' id='textbox23' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate23' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "23" id='checkbox23'></span>
	        </div>
	        <div id="TextBoxDiv_24" class="hide">
		      <label>SL No #24 : </label><input type='textbox' id='textbox24' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate24' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "24" id='checkbox24'></span>
	        </div>
	        <div id="TextBoxDiv_25" class="hide">
		      <label>SL No #25 : </label><input type='textbox' id='textbox25' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate25' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "25" id='checkbox25'></span>
	        </div>
	        <div id="TextBoxDiv_26" class="hide">
		      <label>SL No #26 : </label><input type='textbox' id='textbox26' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate26' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "26" id='checkbox26'></span>
	        </div>
	        <div id="TextBoxDiv_27" class="hide">
		      <label>SL No #27 : </label><input type='textbox' id='textbox27' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate27' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "27" id='checkbox27'></span>
	        </div>
	        <div id="TextBoxDiv_28" class="hide">
		      <label>SL No #28 : </label><input type='textbox' id='textbox28' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate28' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "28" id='checkbox28'></span>
	        </div>
	        <div id="TextBoxDiv_29" class="hide">
		      <label>SL No #29 : </label><input type='textbox' id='textbox29' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate29' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "29" id='checkbox29'></span>
	        </div>
	        <div id="TextBoxDiv_30" class="hide">
		      <label>SL No #30 : </label><input type='textbox' id='textbox30' readonly="readonly" onblur="getSLValue($(this).val())"> <span> <label>S.Rate : </label><input type='textbox' style="width: 14%;" id='slrate30' readonly="readonly" value=""> </span> <span style="margin-left: 1%;"> <input type='checkbox' style='zoom:1' class = "chk" value = "30" id='checkbox30'></span>
	        </div>
			</div>
			<div id="slnoduptext" class="hide">Duplicate serial numbers not allow : <span id="slnodupval"></span></div>
			</div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-default hide" onclick="closeSlNo()">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" id="selslno" class="btn btn-theme">
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

<style>
.rowActive {
	background-color: #EDF4F9 !important;
}
</style>


<script src="${pageContext.request.contextPath }/assets/js/pos/cashmemo/pos.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/glitter/jquery.gritter.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/pos/customer/customer_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/inventory/doctor/doctor_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/pos/cashmemo/cashmemo_en.js"></script>
</c:if>
<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/returnsalememo.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
<%-- 	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script> --%>
	<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/retcashmemo_en.js"></script>
</c:if>
<script type="text/javascript">
	var isWholesale=${isWholesale};
	var BASE_URL = "${pageContext.request.contextPath}";
	var dotMatrixPrint='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_PRINT)%>';
	var n2='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE)%>';
	var n1='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO)%>';
	var is_salemanflag=${userinfo.isSaleman};
	var defaul_sales_man_per=${defaulSalesManPer};
	var freechequee=${saleHeaderDTO.lotAdjAmount};

	if (freechequee>0) {

		$("#flotid").removeClass("hide");
		$("#totltadj").removeClass("hide");
	}else {
		$("#flotid").addClass("hide");
		$("#totltadj").addClass("hide");
	}

	//$("#add_cust_td").hide();
	function showConfirmModal() {
		$('#confirmMessageModal').modal('show');
	}
	/* $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		  var target = $(e.target).attr("href") // activated tab
		  alert(target);
		}); */
	$(document).ready(function() {


		getvendorledger_sale($('#dueties_and_tax_accf').val(),0,0,0);// for duties and tax
		getvendorledger_sale($('#roundoff_group_codef').val(),0,0,1);// for round off
		getvendorledger_sale($('#saleaccunt_group_codef').val(),0,0,2);// for sale account
		getvendorledger_sale($('#dicount_codef').val(),0,0,4);// for discount acc
	     sale_id_present=${saleId};

		//calculateTotalPurchaseamt();
		var grandtotalPur = 0.00;
		$('#selitem tbody tr').each(function() {
			var itmpur = $(this).find("#saletabpurcost").html();
			grandtotalPur = grandtotalPur + Number(itmpur);
		});
		var nettot = $("#nettot").val();
		$("#profitperc").val(parseFloat(Number(nettot) - Number(grandtotalPur)).toFixed(2));

		showWeight();
	});
</script>

