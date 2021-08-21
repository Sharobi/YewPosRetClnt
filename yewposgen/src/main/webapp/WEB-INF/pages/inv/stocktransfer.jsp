<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/css/glitter/jquery.gritter.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/css/keyboard.css" />

<style>
<!--
.ui-autocomplete {
	overflow-y: scroll;
	max-height: 250px;
	width: 150px;
	word-break: break-all;
}

-->
table.table.table-striped tr.freeiai td {
	background-color: #ffb3b3;
}

#rowmarg {
	margin-left: -13px;
}

#myTabContent {
	margin-left: -13px;
}
/* for sale */
#toppanel {
	margin-top: -9px;
	margin-left: -2px;
	margin-right: -11px;
}

#header_div_panel {
	margin-top: -10px;
	margin-bottom: 2px;
	margin-right: -11px;
	margin-left: -2px;
}

#detail_table8 {
	margin-right: -11px;
}

#final_table8 {
	margin-right: -11px;
}
/* for sale return  */
#toppanel2 {
	margin-top: -9px;
	margin-left: -2px;
	margin-right: -11px;
}

#header_div_panel2 {
	margin-top: -10px;
	margin-bottom: 2px;
	margin-right: -11px;
	margin-left: -2px;
}

#detail_table1 {
	margin-right: -11px;
}

#final_table82 {
	margin-right: -11px;
}

.notallowed {
	cursor: not-allowed;
}
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div id="rowmarg" class="row">
		<div class="col-lg-12 hide">
			<p></p>${sesloggedinStore}
			<input type="hidden" id="isexclusive"
				value="${sesloggedinStore.isExclusive}" /> <input type="hidden"
				id="isesi" value="${sesloggedinStore.isEsi}" /> <input
				type="hidden" id="isexclusiversp"
				value="${sesloggedinStore.isExclusive}" /> <input type="hidden"
				id="retamtsamepage" value="0" /> <input type="hidden"
				id="retamtsamepagesaleid" value="${stockTransferHeader.transId}" />
			<input type="hidden" id="confirmvalrsp"
				value="${saleHeaderDTO.allReturnIds}"> <input type="hidden"
				id="seslocationid"
				value="${sessionScope.sesloggedinUser.locationId}" /> <input
				type="hidden" id="retailtypeinfo"
				value="${sessionScope.sesloggedinUser.retailTypeId}" /> <input
				type="hidden" id="isMrpEnableFlag"
				value="${sessionScope.sesloggedinUser.isMrpEnable}" /> <input
				type="hidden" id="item_batchWiseStock_req_ret" value="0" /> <input
				type="hidden" id="item_expiryDateRequired_req_ret" value="0" /> <input
				type="hidden" id="item_expiryMonthRequired_req_ret" value="0" /> <input
				type="hidden" id="item_dualStockRequired_req_ret" value="0" /> <input
				type="hidden" id="item_mrpRequired_req_ret" value="0" /> <input
				type="hidden" id="item_locationRequired_req_ret" value="0" /> <input
				type="hidden" id="item_priceListRequired_req_ret" value="0" /> <input
				type="hidden" id="item_serialNo_req_ret" value="0" /> <input
				type="hidden" id="item_serialNo_list_ret" value="0" />
			<!-- <input type="hidden" id="isMrpEnableFlag" value="${sesloggedinStore.isMrpEnable}" /> -->
			<c:set var="mrpEnableFlag" value="${sesloggedinStore.isMrpEnable}" />
			<input type="hidden" id="isConversionFlag"
				value="${sesloggedinStore.isConversion}" /> <input type="hidden"
				id="isManufacturerFlag" value="${sesloggedinStore.isManufacturer}" />
			<input type="hidden" id="isFreeFlag"
				value="${sesloggedinStore.isFree}" />
			<!-- for onbill return  -->
			<input type="hidden" name="is_free_rsp" id="is_free_rsp"
				value="${userinfo.isFree}"> <input type="hidden" id="qs"
				value="${poDefaultPrefix}" /> <input type="hidden"
				id="numerickeyboard" value="${sesloggedinStore.numericKeyBoard}" />
			<c:set var="numerickeyboardon"
				value="${sesloggedinStore.numericKeyBoard}" />
			<input type="hidden" id="setprinter" value="${setprinter}" />
		</div>
		<div class="bs-example bs-example-tabs"
			data-example-id="togglable-tabs">
			<ul class="nav nav-tabs" id="myTabs" role="tablist">
				<li role="presentation" class="active "><a href="#home"
					id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
					aria-expanded="true" style="font-weight: bolder;">Stock
						transfer</a></li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade active in" role="tabpanel" id="home"
					aria-labelledby="home-tab">
					<p>
						<!--  first filter div  -->
					</p>
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter5">
						<div id="toppanel" class="panel-trx panel-default">
							<div class="panel-body-trx">

								<!-- 	${userinfo }  -->
								<table>
									<!--   when sale id is zero  -->
									<c:if test="${stockTransferHeader.transId==0}">
										<input type="hidden" id="esiTypeId" value="0" />
										<input type="hidden" id="esiCodeId" value="0" />
										<tr align="center">
											<td><spring:message code="pos.jsp.date" text="Date" /></td>
											<td>Series</td>
											<td colspan="4"><spring:message
													code="purinvdet.jsp.ordrno" text="Ord No" /></td>
											<td class="hide"><spring:message
													code="purinvdet.jsp.discprcnt" text="Disc%" /></td>
											<td><spring:message code="pos.jsp.custContact"
													text="Cust.Contact" /></td>
											<td
												class="${sesloggedinStore.isCustomerDisplay==1?' ': 'hide'}"><spring:message
													code="pos.jsp.custName" text="Cust. Name" /></td>
											<td class="add_td hide"></td>
											<td id="prebilltext" class="hide"></td>


											<c:choose>
												<c:when
													test="${sessionScope.sesloggedinUser.companyId == 18}">
													<td></td>
													<td></td>
													<td><spring:message code="pos.jsp.tailor"
															text="Tailor Name" /></td>
													<td></td>
													<%-- <td><spring:message code="pos.jsp.interior" text="Interior Name" /></td> --%>
												</c:when>
												<c:otherwise>
													<td class="hide"><spring:message
															code="pos.jsp.dctrName" text="Doc. Name" /></td>
												</c:otherwise>
											</c:choose>
											<td></td>
										</tr>
										<tr>
											<td width="10%" style="padding: 0 1px;">
												<!-- 												<div class="input-group"> --> <input
												type="text" class="form-control-trx" id="date"
												style="text-align: center;"
												value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
												<!-- 												</div> -->
											</td>

											<td width="8%" style="padding: 0 5px;"><select
												class="form-control-trx" name="mulSeriesPrefix" id="mulslid"
												onchange="getesitype()">

													<c:if test="${!empty serialnumberlist}">
														<c:forEach items="${serialnumberlist}" var="slno">
															<option value="${slno.mulSeriesPrefix}">${slno.mulSeriesPrefix}</option>
														</c:forEach>
													</c:if>
											</select> <!-- <button style="margin-right: 2px;" class="btn btn-primary" type="button" onclick="changeSeries()">
													Change Series
												</button> --></td>
											<td style="padding: 0 1px; width: 4%;"><input
												class="form-control-trx" type="text"
												style="padding: 4px 1px;" id="purOrderDoc"
												value="${soDefaultPrefix}/" size="4" readonly></td>
											<td style="width: 4%;"><input class="form-control-trx"
												type="text" style="padding: 4px 1px;" id="purOrderFinyr"
												value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
											<td style="padding: 0 1px;" width="2.5%"><input
												class="form-control-trx" type="text"
												style="padding: 4px 0px;" id="purOrderSlash" value="/"
												readonly></td>
											<td style="padding: 0 1px;" width="16%">
												<div class="input-group">
													<input class="form-control-trx" type="text" id="purorderno"
														value="" size="14">
													<div class="input-group-addon"
														onclick="veiwPurOrderDet(document.getElementById('purorderno').value,document.getElementById('purOrderFinyr').value)">
														<span class="fa fa-search"></span>
													</div>
												</div>
											</td>

											<td width="8%" style="padding: 0 1px;" class="hide"><input
												class="form-control-trx" type="text"
												value="/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>

											<td style="padding: 0 1px;" class="hide"><input
												class="form-control-trx" type="text" id="saleinvno"
												readonly="readonly" value=""><input type="hidden"
												id="saleId" value="${stockTransferHeader.transId}"></td>
											<td style="padding: 0 1px;" class="hide"><input
												class="form-control-trx" id="salediscount"
												value="${sessionScope.sesloggedinUser.vatPer}" type="text"></td>
											<td
												width="${sessionScope.sesloggedinUser.companyId == 18? '17%' : '17%'}"
												style="padding: 0 1px;"><input class="form-control-trx"
												id="salecustph" placeholder="Search by phone no "
												type="text"><input type="hidden" id="salecustid"
												value="0"><input type="hidden" id="custCreditLimit" /><input
												type="hidden" id="custEmail" /></td>
											<%--  <td  class= "${sesloggedinStore.isCustomerDisplay==1?' ': 'hide'}" width="${sessionScope.sesloggedinUser.companyId == 18? '20%' : '25%'}" style="padding: 0 1px;"><input class="form-control-trx" placeholder="Search by name " id="salecustname" type="text"></td> --%>
											<td
												width="${sessionScope.sesloggedinUser.companyId == 18? '20%' : '25%'}"
												style="padding: 0 1px;"><input class="form-control-trx"
												placeholder="Search by name " id="salecustname" type="text"></td>
											<td id="add_cust_td" class="hide">
												<button style="margin-right: 2px;" class="btn btn-primary"
													type="button" onclick="openAddModal('cust')">
													<i class="fa fa-plus"></i>
												</button>
											</td>
											<td id="prebilltd"><button
													class="btn btn-primary btn-sm" type="button"
													onclick="getCustPreviousBill(document.getElementById('salecustph').value)">
													<i class="fa fa-search" aria-hidden="true"></i>
													<spring:message code="pos.jsp.prevbill" text="Prev Bill" />
												</button></td>

											<c:choose>
												<c:when
													test="${sessionScope.sesloggedinUser.companyId == 18}">
													<td width="15%" style="padding: 0 1px;"><input
														class="form-control-trx" id="saledocname" type="text"><input
														type="hidden" id="saledocid" value="0"></td>
													<td id="add_doc_td">
														<button class="btn btn-primary" type="button"
															onclick="openAddModal('tailor')">
															<i class="fa fa-plus"></i>
														</button>
													</td>
													<!--  <td width="15%" style="padding: 0 1px;"><input class="form-control-trx" id="iteriorname" type="text"></td> -->

												</c:when>
												<c:otherwise>
													<td width="15%" style="padding: 0 1px;" class="hide"><input
														class="form-control-trx" id="saledocname" type="text"><input
														type="hidden" id="saledocid" value="0"></td>
													<td id="add_doc_td" class="hide">
														<button class="btn btn-primary" type="button"
															onclick="openAddModal('doc')">
															<i class="fa fa-plus"></i>
														</button>
													</td>
												</c:otherwise>
											</c:choose>

											<td></td>
										</tr>
										<c:if test="${sesloggedinStore.isEsi==1}">
											<tr align="center">
												<td><spring:message code="pos.jsp.type" text="Type" /></td>
												<td colspan="2"><spring:message code="pos.jsp.pissdt"
														text="Pres. Issue Dt" /></td>
												<td><spring:message code="pos.jsp.inscardNo"
														text="Ins/Card No." /></td>
												<td><spring:message code="pos.jsp.rslno"
														text="Req. Serial No." /></td>
												<td id="blacktext_td" class="hide"></td>
												<td id="codetextid"><spring:message code="pos.jsp.code"
														text="Code" /></td>
												<td id="pregtextid"><spring:message
														code="pos.jsp.pregno" text="Pres. Reg. No" /></td>
											</tr>
											<tr align="center">
												<td><select class="form-control-trx" name="esitype"
													id="esitypeid" onchange="getesitype()">
														<option value="0">Select</option>
														<c:if test="${!empty typeMasters}">
															<c:forEach items="${typeMasters}" var="typeMaster">
																<option value="${typeMaster.id}">${typeMaster.typeName}</option>
															</c:forEach>
														</c:if>
												</select></td>
												<td></td>
												<td><input type="text" style="margin-left: -71%;"
													class="form-control-trx" id="pissuedt"
													value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"></td>
												<td style="padding: 0 1px;"><input
													class="form-control-trx" type="text" id="ecardno" value=""
													readonly></td>
												<td style="padding: 0 1px;"><input
													class="form-control-trx" type="text" id="reqslno" value=""></td>
												<td id="black_td" class="hide"></td>
												<td style="padding: 0 1px;" id="codevalid"><select
													class="form-control-trx" name="pcode" id="pcodeid"
													onchange="getpode()">
														<option value="0">Select</option>
														<c:if test="${!empty esiCodeMasters}">
															<c:forEach items="${esiCodeMasters}" var="esiCodeMaster">
																<option value="${esiCodeMaster.id}">${esiCodeMaster.code}</option>
															</c:forEach>
														</c:if>
												</select></td>
												<td style="padding: 0 1px;" id="pregvalid"><input
													class="form-control-trx" type="text" id="prregno" value=""></td>

											</tr>
										</c:if>
									</c:if>
									<!-- //  when sale id is zero  -->
									<!-- when sale id is not  zero  -->
									<c:if test="${stockTransferHeader.transId !=null}">
										<tr align="center">
											<td><spring:message code="pos.jsp.trno"
													text="Transfer No" /></td>
											<td><spring:message code="stckentry.jsp.date"
													text="Date" /></td>
											<td><spring:message code="stckentry.jsp.duedate"
													text="Due Date" /></td>
											<td><spring:message code="stckentry.jsp.from"
													text="From" /></td>
											<td><spring:message code="stckentry.jsp.to" text="To" /></td>
											<c:choose>
												<c:when
													test="${sessionScope.sesloggedinUser.companyId == 18}">
													<td class=""><spring:message code="pos.jsp.tailor"
															text="Tailor Name" /></td>
												</c:when>
												<c:otherwise>
													<td class="hide"><spring:message
															code="pos.jsp.dctrName" text="Doc. Name" /></td>
												</c:otherwise>
											</c:choose>
											<td></td>
										</tr>
									</c:if>
									<c:if test="${stockTransferHeader.transId==null}">
										<tr align="center">

											<td><spring:message code="stckentry.jsp.date"
													text="Date" /></td>
											<td><spring:message code="stckentry.jsp.duedate"
													text="Due Date" /></td>
											<td><spring:message code="stckentry.jsp.from"
													text="From" /></td>
											<td><spring:message code="stckentry.jsp.to" text="To" /></td>
											<c:choose>
												<c:when
													test="${sessionScope.sesloggedinUser.companyId == 18}">
													<td class=""><spring:message code="pos.jsp.tailor"
															text="Tailor Name" /></td>
												</c:when>
												<c:otherwise>
													<td class="hide"><spring:message
															code="pos.jsp.dctrName" text="Doc. Name" /></td>
												</c:otherwise>
											</c:choose>
											<td></td>
										</tr>
									</c:if>
									<tr>
										<c:if test="${stockTransferHeader.transId==null}">
											<td style="padding: 0 1px;">
												<div class="input-group">

													<input type="text" class="form-control-trx" id="date"
														readonly
														value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
												</div>
											</td>

											<td style="padding: 0 1px;">
												<div class="input-group">

													<input type="text" class="form-control-trx" id="duedate"
														value="" readonly> <input type="hidden"
														id="noOfDue" value="${noOfDue}" />
												</div>
											</td>

											<c:if test="${sesloggedinUser.isAdmin == 1}">
												<td style="padding: 0 1px;">
													<div class="input-group">
														<select class="form-control-trx" id="fromTrans">
															<c:forEach items="${allstoresdata}" var="store">
																<%--  <option value="${stockTransferHeader.fromStoreId}">${stockTransferHeader.fromStoreName}</option> --%>
																<option value="${store.id}"
																	${sesloggedinUser.storeId== store.id ? 'selected' : ''}>${store.name}</option>
															</c:forEach>
														</select>
													</div>
												</td>
											</c:if>

											<c:if test="${sesloggedinUser.isAdmin !=1}">
												<td style="padding: 0 1px;">
													<div class="input-group">
														<select class="form-control-trx" id="fromTrans"
															disabled="true">
															<c:forEach items="${allstoresdata}" var="store">
																<%--  <option value="${stockTransferHeader.fromStoreId}">${stockTransferHeader.fromStoreName}</option> --%>
																<option value="${store.id}">${store.name}</option>
															</c:forEach>
														</select>
													</div>
												</td>
											</c:if>
											<c:if test="${sesloggedinUser.isAdmin == 1}">
												<td style="padding: 0 1px;">
													<div class="input-group">
														<select class="form-control-trx" id="toTrans">
															<c:forEach items="${allstoresdata}" var="store">
																<%-- <option value="${stockTransferHeader.toStoreId}">${stockTransferHeader.toStoreName}</option> --%>
																<option value="${store.id}">${store.name}</option>
															</c:forEach>
														</select>
													</div>
												</td>
											</c:if>
											<c:if test="${sesloggedinUser.isAdmin !=1}">
												<td style="padding: 0 1px;">
													<div class="input-group">
														<select class="form-control-trx" id="toTrans">
															<c:forEach items="${allstoresdata}" var="store">
																<%-- <option value="${stockTransferHeader.toStoreId}">${stockTransferHeader.toStoreName}</option> --%>
																<option value="${store.id}">${store.name}</option>
															</c:forEach>
														</select>
													</div>
												</td>
											</c:if>
										</c:if>


										<c:if test="${stockTransferHeader.transId !=null}">
											<td style="padding: 0 1px;">
												<div class="input-group">

													<input class="form-control-trx" type="text" id="transNo"
														value="${stockTransferHeader.stockTransNo}" readonly>

												</div>
											</td>

											<td style="padding: 0 1px;">
												<div class="input-group">


													<fmt:parseDate
														value="${stockTransferHeader.stockTransDate}"
														var="parsedInvDate" pattern="MMM dd, yyyy" />
													<input class="form-control-trx" type="text" id="date"
														value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" />"
														readonly>
												</div>
											</td>

											<td style="padding: 0 1px;">
												<div class="input-group">
													<fmt:parseDate value="${stockTransferHeader.dueDate}"
														var="parsedDeuDate" pattern="MMM dd, yyyy" />
													<input class="form-control-trx" type="text" id="duedate"
														value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDeuDate}"/>"
														readonly>

												</div>
											</td>


											<c:if test="${sesloggedinUser.isAdmin !=1}">
												<td style="padding: 0 1px;">
													<div class="input-group">
														<select class="form-control-trx" id="fromTrans"
															disabled="true">
															<option value="${stockTransferHeader.fromStoreId}">${stockTransferHeader.fromStoreName}</option>
														</select>
													</div>
												</td>
												<td style="padding: 0 1px;">
													<div class="input-group">
														<select class="form-control-trx" id="toTrans"
															disabled="true">
															<option value="${stockTransferHeader.toStoreId}">${stockTransferHeader.toStoreName}</option>
														</select>
													</div>
												</td>
											</c:if>
											<c:if test="${sesloggedinUser.isAdmin == 1}">
												<td style="padding: 0 1px;">
													<div class="input-group">
														<select class="form-control-trx" id="fromTrans"
															disabled="true">

															<%-- <option value="${stockTransferHeader.toStoreId}">${stockTransferHeader.toStoreName}</option> --%>
															<option value="${stockTransferHeader.fromStoreId}">${stockTransferHeader.fromStoreName}</option>

														</select>
													</div>
												</td>
												<td style="padding: 0 1px;">
													<div class="input-group">
														<select class="form-control-trx" id="toTrans"
															disabled="true">
															<option value="${stockTransferHeader.toStoreId}">${stockTransferHeader.toStoreName}</option>
														</select>
													</div>
												</td>
											</c:if>



										</c:if>




									</tr>
									<td class="hide"><input class="form-control-trx"
										type="text" style="padding: 4px 1px;" id="retmemoFinyr"
										value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>

								</table>
							</div>
						</div>

					</div>
					<!-- // first filter div  -->


					<!--  item search div    -->
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter6">
						<div id="header_div_panel" class="panel-trx panel-default">
							<div class="panel-body-trx" id="header_div">

								<table>
									<tr align="center">
										<c:choose>
											<c:when
												test="${sessionScope.sesloggedinUser.primarySearchType == 1}">
												<td><spring:message code="purinvdet.jsp.itemname"
														text="Item Name" /></td>
												<td><spring:message code="cmn.jsp.barcode"
														text="Barcode" /></td>
											</c:when>
											<c:when
												test="${sessionScope.sesloggedinUser.primarySearchType == 2}">
												<td><spring:message code="cmn.jsp.barcode"
														text="Barcode" /></td>
												<td><spring:message code="purinvdet.jsp.itemname"
														text="Item Name" /></td>
												<%--  <td><spring:message code="item.jsp.code" text="Item Code" /></td> --%>
											</c:when>
											<c:otherwise>
												<td><spring:message code="item.jsp.code"
														text="Item Code" /></td>
												<td><spring:message code="cmn.jsp.barcode"
														text="Barcode" /></td>
												<td><spring:message code="purinvdet.jsp.itemname"
														text="Item Name" /></td>
											</c:otherwise>
										</c:choose>


										<td id="pqty_label"><spring:message
												code="purinvdet.jsp.pqty" text="P.Qty" /></td>
										<%-- <c:choose>
										  <c:when test="${sesloggedinStore.defaultPort=='--'}">
										  <td id="wmc_label" class="hide" >W.M/C</td>
										  </c:when>
										  <c:otherwise>
										  <td id="wmc_label" >W.M/C</td>
										  </c:otherwise>
										</c:choose> --%>

										<td id="lqty_label" class="hide"><spring:message
												code="purinvdet.jsp.lqty" text="L.Qty" /></td>
										<c:if test="${stockTransferHeader.transId==null}">
											<td><spring:message code="pos.jsp.stock" text="Stock" /></td>
										</c:if>
										<td id="sale_rate_label" class="hide">Rate</td>
										<td id="s_dis_label" class="hide">Sc Disc%</td>
										<td id="t_dis_label" class="hide">Tr Disc%</td>
										<td><spring:message code="pos.jsp.prate" text="Rate/Ls" /></td>
										<td><spring:message code="pos.jsp.salerate"
												text="Sale Rate" /></td>
										<td id="dis_label"><spring:message
												code="purinvdet.jsp.discprcnt" text="D%" /></td>
										<td id="batch_label" class="hide"><spring:message
												code="purinvdet.jsp.batch" text="Batch" /></td>
										<td id="exp_label" class="hide"><spring:message
												code="purinvdet.jsp.expdt" text="Exp" /></td>
										<td id="expdate_label" class="hide"><spring:message
												code="purinvdet.jsp.expddate" text="ExpDate" /></td>

										<td class="hide"><spring:message
												code="purinvdet.jsp.vatprcnt" text="Vat%" /></td>
										<td><spring:message code="purinvdet.jsp.total"
												text="Total" /></td>

									</tr>
									<tr>
										<!-- <td width="16%"><input class="form-control-trx" type="text" id="item_barcode" placeholder="Scan Barcode"></td>
										<td width="25%" style="padding: 0 5px;"><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"> <input type="hidden" id="item_id" value="0"></td>
										<td  width="10%" ><input class="form-control-trx" type="text" id="item_code" placeholder="Type 2 characters"></td> -->
										<c:choose>
											<c:when
												test="${sessionScope.sesloggedinUser.primarySearchType == 1}">
												<td width="25%" style="padding: 0 5px;"><input
													class="form-control-trx" type="text" id="item_name"
													placeholder="Please type atleast 2 characters"> <input
													type="hidden" id="item_id" value="0"> <input
													type="hidden" id="itemcodeid" value="0"></td>

												<td width="16%"><input class="form-control-trx"
													type="text" id="item_barcode" placeholder="Scan Barcode"></td>
												<!-- <td  width="10%"><input class="form-control-trx" type="text" id="item_code" placeholder="Type 2 characters"></td>	 -->
											</c:when>
											<c:when
												test="${sessionScope.sesloggedinUser.primarySearchType == 2}">
												<td width="16%"><input class="form-control-trx"
													type="text" id="item_barcode" placeholder="Scan Barcode"></td>
												<td width="25%" style="padding: 0 5px;"><input
													class="form-control-trx" type="text" id="item_name"
													placeholder="Please type atleast 2 characters"> <input
													type="hidden" id="item_id" value="0"></td>
												<!-- <td  width="10%"><input class="form-control-trx" type="text" id="item_code" placeholder="Type 2 characters"></td>	 -->
											</c:when>
											<c:otherwise>
												<td class="hide" width="10%"><input
													class="form-control-trx" type="text" id="item_code"
													placeholder="Type 2 characters"></td>
												<td width="16%"><input class="form-control-trx"
													type="text" id="item_barcode" placeholder="Scan Barcode"></td>
												<td width="25%" style="padding: 0 5px;"><input
													class="form-control-trx" type="text" id="item_name"
													placeholder="Please type atleast 2 characters"> <input
													type="hidden" id="item_id" value="0"></td>
											</c:otherwise>
										</c:choose>
										<td style="padding: 0 5px;" width="7%"><input
											class="form-control-trx" type="text" id="item_pqty" value="0"
											onkeydown="numcheck(event)"> <input type="hidden"
											id="item_packunitid" value="0"><input type="hidden"
											id="item_is_slno" value="0"><input type="hidden"
											id="item_taxtype" value="0"></td>
										<c:if test="${stockTransferHeader.transId==null}">

											<td width="7%" style="padding: 0 5px;"><input
												class="form-control-trx " type="text" id="current_stock"
												value="0" readonly></td>
										</c:if>
										<td style="display: none;"><input
											class="form-control-trx " onkeydown="numcheck(event)"
											type="hidden" id="item_free" value="0"><input
											class="form-control-trx hide" type="text" id="item_lqty"
											value="0"><input type="hidden" id="item_looseunitid"
											value="0"> <input type="hidden" id="item_stockedqty"
											value="0"><input type="hidden" id="item_ltadj"
											value="0"></td>
										<td style="padding: 0 5px;"><input
											class="form-control-trx" type="hidden" id="item_sale_rate"><input
											class="form-control-trx" type="text" id="item_purChase_rate"><input
											type="hidden" id="item_dualStockRequired_req" value="0" /></td>
										<td style="padding: 0 5px;" class="hide"><input
											class="form-control-trx" type="text" id="item_sc_dis"
											value="0.0000" onkeyup=""><input type="hidden"
											id="item_sc_dis_amt" value="0"></td>
										<td style="padding: 0 5px;" class="hide"><input
											class="form-control-trx" type="text" id="item_tr_dis"
											value="0.0000" onkeyup=""><input type="hidden"
											id="item_tr_dis_amt" value="0"></td>
										<td style="padding: 0 5px;" class="hide"><input
											class="form-control-trx" type="text" id="item_rate_ls"
											value="0" onkeydown="numcheck(event)"><input
											type="hidden" id="item_rate_ls_hid" value="0"></td>
										<td style="padding: 0 5px;"><input
											class="form-control-trx" type="text" id="item_sale_rate_ls"
											value="0"><input type="hidden" id="sale_rate_ls_hid"
											value="0"></td>
										<td width="9%" style="padding: 0 5px;" id="item_dis_td"><input
											class="form-control-trx" type="text" id="item_dis"
											onkeydown="numcheck(event)" value="0.0000"><input
											type="hidden" id="item_discamt" value="0"><input
											type="hidden" id="item_mrpRequired_req" value="0"><input
											type="hidden" id="item_priceListRequired_req" value="0">
											<input type="hidden" id="item_expDate" tabindex="-1" readonly></td>
										<td style="padding: 0 5px;" id="batch_no_td" class="hide"><input
											class="form-control-trx" type="text" id="item_batch"
											tabindex="-1" readonly></td>
										<td style="padding: 0 5px;" id="exp_td" class="hide"><input
											class="form-control-trx" type="text" id="item_exp"
											tabindex="-1" readonly></td>

										<td style="padding: 0 5px;" class="hide"><input
											class="form-control-trx " type="text" id="item_vat"
											value="${sessionScope.sesloggedinUser.vatPer}" readonly><input
											type="hidden" id="item_vatamt" value="0"><input
											class="form-control-trx " type="text" id="item_tax"
											value="${sessionScope.sesloggedinUser.taxPer}" readonly><input
											type="hidden" id="item_taxamt" value="0"></td>
										<td style="padding: 0 5px;"><input
											class="form-control-trx" type="text" id="item_tot" value="0"
											tabindex="-1" readonly></td>

									</tr>
									<tr align="center">

										<%-- <td class="${userinfo.isManufacturerSearch==1? '' :'hide'}"><spring:message code="pos.jsp.manu" text="Manufacturer" /></td> --%>
										<td colspan="2" class="hide"><spring:message
												code="itemmstr.jsp.content" text="Content" /></td>

										<td id="mrp_label" class="hide"><spring:message
												code="purinvdet.jsp.mrp" text="MRP" /></td>
										<td class="hide"><spring:message code="pos.jsp.mrpPack"
												text="MRP/Pack" /></td>
										<td class="hide"><spring:message
												code="itemmstr.jsp.schedule" text="Schedule" /></td>
										<td id="location_label" class="hide"><spring:message
												code="purinvdet.jsp.loc" text="Location" /></td>
										<td id="Sizelabel_td" class="hide"><spring:message
												code="purinvdet.jsp.size" text="Size" /></td>
										<td id="Colorlabel_td" class="hide"><spring:message
												code="purinvdet.jsp.color" text="Color" /></td>
										<td id="wtypelabel_td" class="hide"><spring:message
												code="purinvdet.jsp.warrantytype" text="Warranty Type" /></td>
										<td id="wmonthlabel_td" class="hide"><spring:message
												code="purinvdet.jsp.wmonth" text="W. Month" /></td>



										<td id="isfreeiai_td"></td>

										<td colspan="2"></td>
										<td colspan="2">.</td>



									</tr>
									<tr>

										<td colspan="2" style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" id="item_content" title="sdfgsdfg"
											type="text" tabindex="-1" readonly></td>

										<td style="padding: 0 1px;" id="mrp_td" class="hide"><input
											class="form-control-trx" type="text" id="item_mrp_pack"
											tabindex="-1" readonly></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_mrp"
											tabindex="-1" readonly></td>
										<!--  for salesman -->


										<td style="display: none;" id="ratio_val" class="hide"><input
											class="form-control-trx" type="text" id="item_conv"
											tabindex="-1" readonly></td>
										<!--  free item aginst  item  -->


										<td class="hide"><input type="hidden" id="item_sche">
											<input type="hidden" id="item_taxId"> <input
											type="hidden" id="item_taxPercentage"> <input
											type="hidden" id="item_isGroupTax"> <input
											type="hidden" id="item_CalcTaxAmt" value="0"> <input
											type="hidden" id="item_discount"> <input
											type="hidden" id="item_isDiscount"> <input
											type="hidden" id="item_maxDiscountLimit" value="0"> <input
											type="hidden" id="item_taxMode" value="0"> <input
											type="hidden" id="item_hsnCode" value="0"> <input
											type="hidden" id="item_purCost" value="0"> <input
											type="hidden" id="item_purCostperUnit" value="0"> <input
											type="hidden" id="item_dualstock" value="0"> <input
											type="hidden" id="item_saleorderid" value="0" /></td>




										<td style="padding: 0 1px;" id="wmonthval_td" class="hide"><input
											class="form-control-trx" type="text" value="0"
											id="warrantymonth"></td>



										<td colspan="2">
											<div
												style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;"
												id="alertMsg"></div>
										</td>

										<c:if test="${stockTransferHeader.dispatchStatus !='Y'}">
											<%--  <c:if test="${saleHeaderDTO.holdFlag ==0}">  --%>
											<td colspan="3" align="middle"><button
													class="btn btn-success btn-sm" title='Add into table '
													type="button" onclick="addOrUpdateItemToDetailsTable(0)"
													id="add_btn">
													<spring:message code="cmn.jsp.addcaps" text="ADD" />
												</button>
												<button class="btn btn-success btn-sm hide"
													style="text-transform: uppercase;"
													title='Update into table ' type="button"
													onclick="addOrUpdateItemToDetailsTable(1)" id="edit_btn">
													<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
												</button>
												<button class="btn btn-primary btn-sm" type="button"
													title='Clear content' onclick="clearHeaderDiv()">
													<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
												</button> <!-- Trigger the modal with a button --> <!-- <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#purModal">PURCHASE</button> -->
											</td>
											<%-- </c:if>   --%>
										</c:if>

									</tr>
								</table>
							</div>
						</div>
					</div>
					<!--//  item search div    -->

					<!-- Purchase History -->

					<!-- Purchase History -->


					<!-- item table for after add for sale -->
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div style="overflow: auto; height: 240px;" id="detail_table8">
							<table id="selitem" style="margin-bottom: 5px;"
								class="table table-bordered table-striped table-condensed-trx table-hover">
								<thead>
									<tr>
										<th><spring:message code="purinvdet.jsp.itemname"
												text="Item Name" /></th>
										<th><spring:message code="purinvdet.jsp.itembarcode"
												text="Barcode" /></th>
										<th><spring:message code="purinvdet.jsp.batch"
												text="Batch" /></th>
										<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
										<th class="hide"><spring:message
												code="purinvdet.jsp.expdate" text="ExpDate" /></th>
										<th class="${sesloggedinStore.isManufacturer==1?' ': 'hide'}"><spring:message
												code="purinvdet.jsp.mfg" text="Mfg" /></th>
										<th class="numeric"><spring:message
												code="pos.jsp.stocktransferjsp.sendqty" text="Send Qty" /></th>
										<%-- <th class="${sesloggedinStore.isFree==1?' ': 'hide'}"><spring:message code="purinvdet.jsp.free" text="Free" /></th> --%>
										<c:if test="${stockTransferHeader.dispatchStatus =='Y'}">
											<th><spring:message
													code="pos.jsp.stocktransferjsp.receiveqty"
													text="Received Qty" /></th>
											<th><spring:message
													code="pos.jsp.stocktransferjsp.transitqty"
													text="Transit Qty" /></th>
										</c:if>
										<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
											<th class="numeric"><spring:message
													code="pos.jsp.mrpPack" text="MRP/Pack" /></th>
											<th class="numeric"><spring:message
													code="purinvdet.jsp.mrp" text="MRP" /></th>
										</c:if>
										<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==0}">
											<th class="numeric hide"><spring:message
													code="pos.jsp.mrpPack" text="MRP/Pack" /></th>
											<th class="numeric hide"><spring:message
													code="purinvdet.jsp.mrp" text="MRP" /></th>
										</c:if>
										<th class="numeric">Amt</th>
										<th class="numeric"><spring:message
												code="pos.jsp.salerate" text="Sale Rate" /></th>
										<th class="numeric hide"><spring:message
												code="purinvdet.jsp.vatprcnt" text="VAT%" /></th>
										<th class="numeric"><spring:message
												code="purinvdet.jsp.taxprcnt" text="TAX%" /></th>
										<th class="numeric"><spring:message
												code="purinvdet.jsp.disc" text="Disc" />%</th>
										<!--saleman com-->
										<%-- <th class="numeric"><spring:message code="pos.jsp.salesmancomper" text="Com.Per" /></th> --%>
										<th class="numeric"><spring:message
												code="pos.jsp.netamnt" text="Net Amt" /></th>
										<c:if test="${stockTransferHeader.dispatchStatus !='Y'}">
											<th class="numeric"><spring:message
													code="purinvdet.jsp.dltbtn" text="Del." /></th>
										</c:if>

									</tr>
								</thead>
								<tbody id="saletabitemdetails">

									<c:if test="${!empty stockTransferDetails }">

										<c:forEach items="${stockTransferDetails}"
											var="stockTransferDetail">

											<tr id="${stockTransferDetail.itemUniqueKey}"
												class='schxfreeiai ' style='cursor: pointer;'
												onclick="javascript:itemHeaderDivView(this.id);">

												<td id="saletabid" class="hide">${stockTransferDetail.itemId}</td>
												<td id="saletabname">${stockTransferDetail.itemName}</td>
												<td id="saletabitembarcode">${stockTransferDetail.sku}</td>
												<!-- <td id="saletabiteidc"></td> -->
												<td id="saletabbat">${stockTransferDetail.batchNo}</td>
												<td id="saletabexpdt">${stockTransferDetail.expiryDateFormat}</td>
												<td
													class="${sesloggedinStore.isManufacturer==1?' ': 'hide'}"
													id="saletabmanname">${stockTransferDetail.manufacturerName}</td>

												<fmt:parseDate value="${stockTransferDetail.expiryDate}"
													var="formattedExpDate" pattern="MMM dd, yyyy" />
												<td class="hide" id="saletabexpdate"><fmt:formatDate
														pattern="yyyy-MM-dd" value="${formattedExpDate}" /></td>

												<fmt:parseDate value="${stockTransferDetail.mfdDate}"
													var="formattedExpDate" pattern="MMM dd, yyyy" />
												<td class="hide" id="saletabexpdate"><fmt:formatDate
														pattern="yyyy-MM-dd" value="${formattedExpDate}" /></td>

												<td class="numeric" id="saletabpqty">${stockTransferDetail.sendPackQty}</td>
												<td class="numeric hide" id="saletabcurrentstk">${stockTransferDetail.sendPackQty}</td>
												<c:if test="${stockTransferHeader.dispatchStatus =='Y'}">
													<c:if test="${stockTransferHeader.receiveStatus =='Y'}">
														<td class="numeric" id="saletabreceivepqty">${stockTransferDetail.recvdPackQty}</td>
													</c:if>
													<c:if test="${stockTransferHeader.receiveStatus =='N'}">
														<td class="numeric" id="saletabreceivepqty">0</td>
													</c:if>
													<c:if test="${stockTransferDetail.transitPackQty >0}">
														<td class="numeric" id="saletabtransitpqty">${stockTransferDetail.transitPackQty}
															<button type="button"
																onclick="setTransitItemToResock(${stockTransferDetail.transId},${stockTransferDetail.transDetailId},${stockTransferDetail.itemId},${stockTransferDetail.transitPackQty})"
																class="btn btn-primary btn-xs">Restock</button>
														</td>
													</c:if>
													<c:if test="${stockTransferDetail.transitPackQty==0}">
														<td class="numeric" id="saletabtransitpqty">${stockTransferDetail.transitPackQty}</td>
													</c:if>
												</c:if>

												<td class="hide" id="saletablqty">${stockTransferDetail.sendLooseQty}</td>



												<td class="hide" id="saletabconv">${stockTransferDetail.conversion}</td>

												<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==1}">
													<td class="numeric" id="saletabmrppack"><fmt:formatNumber
															type="number" maxFractionDigits="4" minFractionDigits="4"
															groupingUsed="false" value="${stockTransferDetail.mrp}" /></td>
													<td class="numeric" id="saletabmrp"><fmt:formatNumber
															type="number" maxFractionDigits="4" minFractionDigits="4"
															groupingUsed="false"
															value="${stockTransferDetail.mrpPerUnit}" /></td>
												</c:if>
												<c:if test="${sessionScope.sesloggedinUser.isMrpEnable==0}">
													<td class="numeric hide" id="saletabmrppack"><fmt:formatNumber
															type="number" maxFractionDigits="4" minFractionDigits="4"
															groupingUsed="false" value="${stockTransferDetail.mrp}" /></td>
													<td class="numeric hide" id="saletabmrp"><fmt:formatNumber
															type="number" maxFractionDigits="4" minFractionDigits="4"
															groupingUsed="false"
															value="${stockTransferDetail.mrpPerUnit}" /></td>
												</c:if>
												<%-- <td class="numeric" id="saletabamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${((saleDetail.packQty*saleDetail.conversion)+saleDetail.looseQty)*saleDetail.ratePerUnit}" /></td> --%>
												<td class="numeric" id="saletabamt"><fmt:formatNumber
														type="number" maxFractionDigits="4" minFractionDigits="4"
														groupingUsed="false"
														value="${(stockTransferDetail.sendPackQty*stockTransferDetail.ratePerUnit)}" /></td>

												<td class="numeric" id="saletabrateperunit"><fmt:formatNumber
														type="number" maxFractionDigits="4" minFractionDigits="4"
														groupingUsed="false"
														value="${stockTransferDetail.ratePerUnit}" /></td>
												<%-- <td class="numeric hide" id="saletabvatperc"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.vatPer}" /></td> --%>
												<td id="saletabtaxPercentage" class="numeric"><fmt:formatNumber
														type="number" maxFractionDigits="4" minFractionDigits="4"
														groupingUsed="false"
														value="${stockTransferDetail.taxPercentage}" /></td>
												<td class="numeric" id="saletabdiscperc"><fmt:formatNumber
														type="number" maxFractionDigits="4" minFractionDigits="4"
														groupingUsed="false"
														value="${stockTransferDetail.discPer}" /></td>
												<td class="numeric" id="saletabtotamt"><fmt:formatNumber
														type="number" maxFractionDigits="4" minFractionDigits="4"
														groupingUsed="false"
														value="${stockTransferDetail.sendItemNetAmount}" /></td>
												<c:if test="${stockTransferHeader.dispatchStatus =='N'}">
													<td>
														<%-- <c:if test="${saleHeaderDTO.holdFlag ==0}"> --%>
														<button class="btn btn-theme04 btn-xs" title='Delete item'
															id="${stockTransferDetail.itemUniqueKey}"
															onclick='javascript:showSelTabItemDelModal(this.id);'>
															<i class="fa fa-trash-o "></i>
														</button> <%-- </c:if> --%>
													</td>
												</c:if>

												<td id="saletabpunitid" class="hide">${stockTransferDetail.packUnitId}</td>
												<td id="saletablunitid" class="hide">${stockTransferDetail.looseUnitId}</td>
												<td id="saletabdisc" class="hide">${stockTransferDetail.sendItemDiscAmount}</td>
												<td id="saletabitemstkqty" class="hide">${stockTransferDetail.sendPackQty}</td>
												<td id="saletabmrpperunit" class="hide">${((stockTransferDetail.sendPackQty*stockTransferDetail.conversion)+stockTransferDetail.sendLooseQty)*stockTransferDetail.mrpPerUnit}</td>
												<td id="saletabrate" class="hide">${stockTransferDetail.rate}</td>
												<td id="saletabtaxperc" class="hide">${stockTransferDetail.taxPercentage}</td>
												<td id="saletabtax" class="hide">${stockTransferDetail.sendItemTaxAmount}</td>
												<td id="saletabtaxId" class="hide">${stockTransferDetail.taxId}</td>



												<td id="saletabdiscount" class="hide">${stockTransferDetail.sendItemDiscAmount}</td>
												<td id="saletabitemcalcgstamt" class="hide">${stockTransferDetail.sendItemTaxAmount}</td>
												<td id="saletabitemhsncode" class="hide">${stockTransferDetail.hsnCode}</td>
												<td id='saletabpurcost' class='hide'><fmt:formatNumber
														type="number" maxFractionDigits="4" minFractionDigits="4"
														groupingUsed="false"
														value="${((stockTransferDetail.sendPackQty*stockTransferDetail.conversion)+stockTransferDetail.sendLooseQty)*stockTransferDetail.purchaseRate}" /></td>
												<td id='saletabpurcostperunit' class='hide'>${stockTransferDetail.purchaseRate}</td>
												<td id='saletabitemsalerate' class='hide'>${stockTransferDetail.saleRate}</td>
												<td id='saletabitempurrate' class='hide'>${stockTransferDetail.purchaseRate}</td>

												<td class="hide"><input type="text"
													id="stocktransIdshow"
													value="${stockTransferHeader.transId}" /></td>
												<td class="hide"><input type="text"
													id="stocktransnoshow"
													value="${stockTransferHeader.stockTransNo}" /></td>

												<td id="saletabitemchkslnosid" class="numeric hide">${stockTransferDetail.purchaseRate}</td>
												<td id="saletabitemtaxtype" class="numeric hide">${stockTransferDetail.taxTypeId}</td>
												<td id="saletabcode" class="hide">${stockTransferDetail.itemCode}</td>

												<%-- <td id="saletabremarks" class="hide">${stockTransferDetail.remarks}</td> --%>
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
						<input type="hidden" id="dueties_and_tax_accf"
							value="<spring:message code="accgroup.jsp.duties_code" text="DT"/>">
						<input type="hidden" id="saleaccunt_group_codef"
							value="<spring:message code="accgroup.jsp.saleac_code" text="SA"/>">
						<input type="hidden" id="roundoff_group_codef"
							value="<spring:message code="accgroup.jsp.roun_code" text="ROD"/>">
						<input type="hidden" id="debitor_group_codef"
							value="<spring:message code="accgroup.jsp.deb_code" text="SDE"/>">
						<input type="hidden" id="dicount_codef"
							value="<spring:message code="accgroup.jsp.disc_code" text="DIS" />">
						<input type="hidden" id="cash_codef"
							value="<spring:message code="accgroup.jsp.cash_code" text="CIH" />">
						<input type="hidden" id="card_codef"
							value="<spring:message code="accgroup.jsp.card_code" text="CAB" />">
						<input type="hidden" id="duties_ledger_idf" value="0"> <input
							type="hidden" id="round_ledger_idf" value="0"> <input
							type="hidden" id="sales_ledger_idf" value="0"> <input
							type="hidden" id="debitor_ledger_idf" value="0"> <input
							type="hidden" id="discount_ledger_idf" value="0"> <input
							type="hidden" id="debitor_cahs_ledger_idf" value="0"> <input
							type="hidden" id="card_ledger_idf" value="0">


						<table id="final_table8">
							<tr>

								<td><spring:message code="purinvdet.jsp.itemcount"
										text="ItemCount" />:</td>
								<td width="2%"><span id="totitmcount"></span></td>
								<%-- <td class="font-bold"><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></td>
					           <td style="padding-right: 5px;"><input class="form-control-trx" id="remarks" type="text" value="${saleHeaderDTO.remarks}"></td> --%>
								<td><spring:message code="purinvdet.jsp.total"
										text="Total:" />:</td>
								<td style="padding-right: 5px;"><input
									class="form-control-trx" style="color: #000000;" type="text"
									id="totgrossamt" value="${stockTransferHeader.sendGrossAmount}"
									tabindex="-1" readonly> <input
									class="form-control-trx hide" type="text"
									id="hiddentotgrossamt"
									value="${stockTransferHeader.sendGrossAmount}" tabindex="-1"
									readonly></td>
								<td class="hide"><input type="text" id="stocktransIdshow"
									value="${stockTransferHeader.transId}" /> <input type="text"
									id="stocktransnoshow"
									value="${stockTransferHeader.stockTransNo}" /></td>
								<c:if test="${mrpEnableFlag==1}">
									<td>Tot.MRP:</td>
									<td style="padding-right: 5px;"><input
										class="form-control-trx" style="color: #000000;" type="text"
										id="totmrpamt" value="${stockTransferHeader.sendTotalMrp}"
										tabindex="-1" readonly></td>
								</c:if>
								<c:if test="${mrpEnableFlag==0}">
									<td class="hide">Tot.MRP:</td>
									<td class="hide" style="padding-right: 5px;"><input
										class="form-control-trx" style="color: #000000;" type="text"
										id="totmrpamt" value="${stockTransferHeader.sendTotalMrp}"
										tabindex="-1" readonly></td>
								</c:if>
								<td class="font-bold hide"><spring:message
										code="purinvdet.jsp.totvat" text="Tot.Vat:" /></td>
								<%-- <td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamt" value="${saleHeaderDTO.vatAmount}" tabindex="-1" readonly></td> --%>
								<td><spring:message code="pos.jsp.stax" text="Tot.Tax" /></td>
								<td style="padding-right: 5px;" class="hide"><input
									class="form-control-trx" type="text" id="tottaxamt"
									value="${stockTransferHeader.sendTaxAmount}" tabindex="-1"
									readonly></td>
								<!--  for total tax -->

								<td width="10%" style="padding-right: 5px;"><input
									class="form-control-trx" type="text" id="totgstamt"
									value="${stockTransferHeader.sendTaxAmount}" tabindex="-1"
									readonly></td>
								<!-- for discount  -->
								<td width="7%"><spring:message code="purinvdet.jsp.totdisc"
										text="Tot.Disc:" /></td>
								<td width="7%" style="padding-right: 5px;"><input
									class="form-control-trx" type="text" id="totdiscamt"
									value="${stockTransferHeader.sendDiscAmount}" tabindex="-1"
									readonly></td>
								<td class="hide">ProfitAmt:</td>
								<td style="padding-right: 5px;" class="hide"><input
									class="form-control-trx" type="text" id="profitperc" value=""
									readonly="readonly"></td>
								<!--  for round off  -->
								<td width="3%"><spring:message code="purinvdet.jsp.roff"
										text="R.Off:" /></td>
								<td width="7%" style="padding-right: 5px;"><input
									class="form-control-trx" type="text" id="roundoff"
									value="${stockTransferHeader.sendRoundoff}" tabindex="-1"
									readonly="readonly"></td>

								<!-- for other adjasment -->
								<%-- <td class="hide" id="otherAdjasment_td"><spring:message code="purinvdet.jsp.othadj" text="Oth.Adj" />:</td> --%>
								<%-- <td  class="hide"  width="7%" style="padding: 2px 5px 0 0" ><input  onkeydown="numcheck(event)" class="form-control-trx" type="text" id="otheradjasment" value="${saleHeaderDTO.othAdjAmount}" tabindex="-1" > --%>
								<!--  for net total  -->
								<td width="7%"><span
									style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message
											code="purinvdet.jsp.nettotal" text="NetTotal:" /></span></td>
								<td width="15%"><input class="form-control-trx"
									style="font-weight: bold; color: #000000; background-color: #ebccd1;"
									type="text" id="nettot"
									value="${stockTransferHeader.sendNetAmount}" tabindex="-1"
									readonly></td>
							</tr>

							<tr>

								<td id="remarkstext"><c:choose>
										<c:when test="${sessionScope.sesloggedinUser.companyId == 18}">
											<spring:message code="pos.jsp.interior" text="Interior" />: 
								    </c:when>
										<c:otherwise>
											<spring:message code="purinvdet.jsp.remarks" text="Remarks:" />
										</c:otherwise>
									</c:choose></td>

								<td colspan="3" style="padding: 3px 5px 0 0"><input
									class="form-control-trx" id="remarks" type="text"
									value="${stockTransferHeader.sendRemarks}"></td>


								

									<td colspan="5" style="padding-top: 4px; padding-right: 4px;">
										<button style="padding: 5px 8px;" class="btn btn-info btn-sm"
											title="New Sale Button " type="button"
											onclick="openStockTrnsferModule()">
											<spring:message code="cmn.jsp.new" text="NEW" />
										</button> <c:if test="${stockTransferHeader.sendIsPosted !=1}">
										<c:if test="${stockTransferHeader.transId==null}">

											<button class="btn btn-primary btn-sm"
												style="padding: 5px 30px;" title="Save Button "
												type="button" id="saveStockButtId"
												onclick="createOrUpdateStockTransfer()">
												<spring:message code="pos.jsp.stocktransferSave.btn"
													text="SAVE" />
											</button>
										</c:if>
								</c:if>

								<c:if test="${stockTransferHeader.transId !=null}">
									<c:if test="${stockTransferHeader.dispatchStatus =='N'}">
										<button style="padding: 5px 8px; text-transform: uppercase;"
											class="btn btn-success btn-sm" id="saveStockButtId"
											onclick="createOrUpdateStockTransfer()" type="button">
											<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
										</button>
										<button style="padding: 5px 8px;"
											class="btn btn-danger btn-sm" type="button"
											onclick="deleteStockTransfer(${stockTransferHeader.transId})">
											<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
										</button>
										<button class="btn btn-primary btn-sm" type="button"
											onclick="dispatchStockTransfer(${stockTransferHeader.transId})">
											<spring:message code="cmn.jsp.btn.dispatch" text="DISPATCH" />
										</button>
									</c:if>
									
										<c:if test="${stockTransferHeader.sendIsPosted==0 && stockTransferHeader.dispatchStatus=='Y'}">
										
											<button class="btn btn-warning btn-sm" type="button"
												onclick="closeStockTransferSend(${stockTransferHeader.transId})">
												<spring:message code="cmn.jsp.btn.close" text="CLOSE" />
											</button>
										
									</c:if>
								</c:if>
							</tr>
						</table>
					</div>
					<!--// all final  calculation from sale table   -->

				</div>
				<div class="tab-pane fade" role="tabpanel" id="profile"
					aria-labelledby="profile-tab">
					<p>
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter11">
						<input type="hidden" id="useridrsp"
							value="${sessionScope.sesloggedinUser.id}"> <input
							type="hidden" id="companyIdrsp"
							value="${sessionScope.sesloggedinUser.companyId}"> <input
							type="hidden" id="storeIdrsp"
							value="${sessionScope.sesloggedinUser.storeId}"> <input
							type="hidden" id="finyrIdrsp"
							value="${sessionScope.sesloggedinUser.finyrId}">
						<div id="toppanel2" class="panel-trx panel-default">

							<!-- Confirm Print Sale Return Modal Starts -->

							<div class="modal fade" id="confirmPrintSaleReturnModalrsp"
								style="text-align: center;" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true"
								data-backdrop="static" data-keyboard="false">
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
												<spring:message code="rtrnmemo.jsp.addsuccmsg"
													text="Sales Return Invoice successfully added!" />
												<br>
												<div class="col-sm-12 hide"
													style="text-align: center; font-weight: 700;">
													<spring:message code="rtrnmemo.jsp.printsalereturn"
														text="Print Sale Return" />
													: <input type="checkbox" id="printSaleReturn"
														name="printSaleReturn"
														style="zoom: 1.5; vertical-align: middle; margin: 0px;">
												</div>
											</div>

											<input type="hidden" id="confirmvalrspsamepage" value="0">
										</div>
										<div class="modal-footer" style="border-top: 0px;">
											<button type="button" onclick="targetURLrspsamepage()"
												data-dismiss="modal" class="btn btn-theme">
												<spring:message code="footer.jsp.btn.ok" text="OK" />
											</button>
										</div>
									</div>
								</div>
							</div>
							<!-- Confirm Print Purchase Modal ends -->



						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter22">
						<div id="header_div_panel2" class="panel-trx panel-default">
							<div class="panel-body-trx" id="header_div_rsp">
								<table>
									<tr align="center" style="font-weight: bold;">
										<td><spring:message code="purinvdet.jsp.itemname"
												text="Item Name" /></td>
										<td>Barcode</td>
										<td id="pqty_label"><spring:message
												code="purinvdet.jsp.pqty" text="P.Qty" /></td>
										<td id="lqty_label" class="hide"><spring:message
												code="purinvdet.jsp.lqty" text="L.Qty" /></td>

										<td class="hide"><spring:message
												code="purinvdet.jsp.batch" text="Batch" /></td>
										<td class="hide"><spring:message
												code="purinvdet.jsp.expdt" text="Exp" /></td>
										<td class="hide"><spring:message
												code="purinvdet.jsp.expdate" text="ExpDate" /></td>

										<td class="hide"><spring:message code="pos.jsp.mrpPack"
												text="MRP/Pack" /></td>
										<td class="hide"><spring:message code="purinvdet.jsp.mrp"
												text="MRP" /></td>
										<td class="hide"><spring:message
												code="rtrnmemo.jsp.srate" text="S.Rate" /></td>
										<td class="hide"><spring:message
												code="rtrnmemo.jsp.srate/ls" text="S.Rate/Ls" /></td>


										<td><spring:message code="purinvdet.jsp.total"
												text="Total" /></td>
									</tr>
									<tr>
										<!-- 						data-toggle="modal" data-target="#itemsaledetailModal" -->
										<td width="20%" style="padding: 0 1px;"><input
											class="form-control-trx" type="text" id="item_name_rsp"
											placeholder="Please type atleast 2 characters"> <input
											type="hidden" id="item_id_rsp" value="0"></td>
										<td width="12%"><input class="form-control-trx"
											type="text" placeholder="Scan Barcode" id="item_barcode_rsp"></td>
										<td style="padding: 0 1px;"><input
											class="form-control-trx" type="text" id="item_rpqty_rsp"
											value="0"></td>
										<td style="padding: 0 1px;" id="free_qty_rsp"
											class="${userinfo.isFree==1 ?'':'hide'}"><input
											class="form-control-trx" type="text"
											onkeydown="numcheck(event)" id="item_rfqty_rsp" value="0">
											<input class="form-control-trx" type="hidden"
											id="item_lotadjasment_rsp" value="0"></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_rlqty_rsp"
											value="0"></td>

										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_bat_rsp"
											readonly></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_exp_rsp"
											readonly></td>

										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_mrpperpack_rsp"
											readonly></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_mrp_rsp"
											readonly></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_rate_rsp"
											readonly></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text"
											id="item_rateperloose_rsp" readonly></td>

										<td style="padding: 0 1px;"><input
											class="form-control-trx" type="text" id="item_netamt_rsp"
											value="0" readonly></td>
										<td colspan="2">
											<div
												style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;"
												id="alertMsg_rsp"></div>
										</td>
										<c:if test="${stockTransferHeader.dispatchStatus =='N'}">
											<td><button class="btn btn-success btn-sm"
													title="add into table" type="button"
													onclick="addOrUpdateItemToDetailsTablersp(0)"
													id="add_btn_rsp">
													<spring:message code="cmn.jsp.addcaps" text="ADD" />
												</button>
												<button class="btn btn-success btn-sm hide"
													style="text-transform: uppercase;" type="button"
													title="add into table"
													onclick="addOrUpdateItemToDetailsTablersp(1)"
													id="edit_btn_rsp">
													<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
												</button></td>
											<td><button class="btn btn-primary btn-sm" type="button"
													onclick="clearHeaderDivrsp()">
													<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
												</button></td>
										</c:if>

									</tr>
									<tr align="center" style="font-weight: bold;">
										<td class="hide"><spring:message code="purinvdet.jsp.mfg"
												text="Mfg" /></td>
										<td colspan="3" class="hide"><spring:message
												code="itemmstr.jsp.content" text="Content" /></td>
										<td class="hide"><spring:message
												code="purinvdet.jsp.discprcnt" text="D%" /></td>
										<%-- <td class="hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td> --%>
										<td class="hide"><spring:message
												code="purretrn.jsp.bpqty" text="B.P.Qty" /></td>
										<td class="hide"><spring:message
												code="purretrn.jsp.blqty" text="B.L.Qty" /></td>
										<td class="hide"><spring:message
												code="purinvdet.jsp.pqty" text="P.Qty Hide" /></td>
										<td class="hide"><spring:message
												code="purinvdet.jsp.lqty" text="L.Qty Hide" /></td>
									</tr>
									<tr>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_mfg_rsp"
											readonly></td>
										<td colspan="3" style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" id="item_content_rsp" title=""
											type="text" readonly></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_disper_rsp"
											value="0" readonly></td>
										<td style="padding: 0 1px;" class="hide" id="ratio_val_rsp"><input
											class="form-control-trx" type="text" id="item_conv_rsp"
											readonly></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_pqty_rsp"
											value="0" readonly></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="text" id="item_lqty_rsp"
											value="0" readonly></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="hidden"
											id="item_rpqty_hide_rsp" value="0"></td>
										<td style="padding: 0 1px;" class="hide"><input
											class="form-control-trx" type="hidden"
											id="item_rlqty_hide_rsp" value="0"> <input
											class="form-control-trx" type="hidden"
											id="item_rfqty_hide_rsp" value="0"> <input
											type="hidden" id="item_taxId_rsp"> <input
											type="hidden" id="item_taxPercentage_rsp"> <input
											type="hidden" id="item_isGroupTax_rsp"> <input
											type="hidden" id="item_CalcTaxAmt_rsp" value="0"> <input
											type="hidden" id="item_discount_rsp"> <input
											type="hidden" id="item_isDiscount_rsp"> <input
											type="hidden" id="item_maxDiscountLimit_rsp" value="0">
											<input type="hidden" id="item_taxMode_rsp" value="0">
											<input type="hidden" id="item_hsnCode_rsp" value="0"></td>

									</tr>
								</table>
							</div>
						</div>
					</div>

					<div class="col-lg-12 col-md-12 col-sm-12">
						<p></p>
					</div>


				</div>
			</div>
		</div>


	</div>
</section>



<div class="modal fade" id="itemsByContentModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.itemnameforgeneric"
						text="Item name for " />
					<span id="modsearchedcontent"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="contentnotmatchdiv" class="hide">
					<input type="hidden" id="contentnotmatchid"> <span
						class="font-bold" id="itemnotmatchcontent"><spring:message
							code="pos.jsp.itemnotmatchcontent"
							text="No Item match with this content " />: <span
						id="contentnotfoundname"> </span> </span>
				</div>
				<div id="itemsearchtable">
					<table id="itemsbycontentidtbl"
						class="table table-bordered table-striped table-condensed-trx table-hover">
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



<div class="modal fade" id="itemsByManufactureModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.itemnameforgeneric"
						text="Item name for " />
					<span id="modsearchedmanufacture"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="manufacturenotmatchdiv" class="hide">
					<input type="hidden" id="manufacturenotmatchid"> <span
						class="font-bold" id="itemnotmatchmanufature"><spring:message
							code="pos.jsp.itemnotmatchmanufacturer"
							text="No Item match with this manufacturer " />: <span
						id="manufacturernotfoundname"> </span> </span>
				</div>
				<div id="itemsearchtablemanu">
					<table id="itemsbymanufactureidtbl"
						class="table table-bordered table-striped table-condensed-trx table-hover">
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

<!-- Purchase Order Detail by Order No Modal ends -->
<div class="modal fade" id="stkdetModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.stckdetailsfor"
						text="Stock Details for" />
					<span id="moditemname"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div>
					<span class="font-bold"><spring:message
							code="itemmstr.jsp.manufacturer" text="Manufacturer" /> :</span> <span
						id="modmanufname"></span>
				</div>
				<div class="hide">
					<span class="font-bold"><spring:message
							code="itemmstr.jsp.content" text="Content" /> :</span> <span
						id="modcontentname"> </span>
				</div>
				<div>
					<span class="font-bold"><spring:message
							code="itemmstr.jsp.group" text="Group" /> :</span> <span
						id="modgroupname"> </span>
				</div>
				<div>
					<span class="font-bold"><spring:message
							code="itemmstr.jsp.rack" text="Rack" /> :</span> <span id="modrackname">
					</span>
				</div>
				<div>
					<span class="font-bold">Note :</span> <span id="moditemnote">
					</span>
				</div>
				<div id="snditmtopodiv" class="">
					<input type="hidden" id="senditemtopoid">
					<!-- <button type="button" onclick="sendItemToPO(1)" class="btn btn-theme">Send Item To PO</button> -->
					&nbsp; &nbsp;<span id="snditmpo" class="alert alert-success hide"
						style="padding: 6px;"></span>
				</div>
				<div id="itemnotfounddiv" class="hide">
					<input type="hidden" id="itemnotfoundid"> <span
						class="font-bold" id="itemnotfound"><spring:message
							code="pos.jsp.nostockerror" text="No stock found for" /> <span
						id="itemnotfoundname"> </span>

						<button type="button" onclick="getGenericMed()"
							data-dismiss="modal" class="btn btn-theme hide">
							<spring:message code="footer.jsp.btn.getaltmed"
								text="Get Alternate Medicine" />
						</button></span>
				</div>
				<div
					style="max-height: 300px; height: 200px; overflow: auto; margin-top: 1%;"
					id="modtable">
					<table id="itemstockdetails"
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th class="hide"><spring:message
										code="purinvdet.jsp.expdate" text="ExpDate" /></th>
								<th><spring:message code="pos.jsp.currstock"
										text="Cur Stock" /></th>
								<th><spring:message code="pos.jsp.holdstock"
										text="Hold Stock" /></th>
								<th class="numeric"><spring:message
										code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th class="numeric hide"><spring:message
										code="pos.jsp.mrpPack" text="MRP/Pack" /></th>
								<%-- <th class="numeric hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th> --%>
								<th class="hide"><spring:message code="pos.jsp.packing"
										text="Packing" /></th>
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



<div class="modal fade" id="alternateMedModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.altrntmed"
						text="Alternate Medicine for" />
					<span id="alternateMeditemname"> Telma AM</span>
				</h4>
			</div>

			<div class="modal-body">
				<div id="altmeditemnotfounddiv" class="hide">
					<span class="font-bold" id="altmeditemnotfound"><spring:message
							code="pos.jsp.altrntmederror"
							text="No alternate medicine found for" /></span> <span
						id="altmeditemnotfoundname"> </span>
				</div>
				<div style="max-height: 350px; height: 300px; overflow: auto;"
					id="alternateMedtable">
					<table id="alternateMeddetails"
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="pos.jsp.name" text="Name" /></th>
								<th style="width: 30%;"><spring:message
										code="pos.jsp.mfgname" text="Mfg. Name" /></th>
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


<div class="modal fade" id="itemExistsModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="itemmstr.jsp.exist.error"
					text="Item with same batch and expiry already exist, please try other." />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="ExistsOk()" data-dismiss="modal"
					class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Item exists end -->

<!-- Confirm Modal Start -->

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
				<input type="hidden" id="confirmid"> <input type="hidden"
					id="confirmdetailsid"> <input type="hidden"
					id="confirmitemid"> <input type="hidden" id="confirmtrqty">
				<input type="hidden" id="confirmtype">
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


<!-- Confirm Modal end -->
<div class="modal fade" id="scheleXorH1Modal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold" style="color: red;">
				<spring:message code="pos.jsp.clctprescriptionmsg"
					text="Please collect the prescription and add Doctor & Customer name." />
				<input type="hidden" id="operationtype">
			</div>
			<div class="modal-footer">
				<button type="button"
					onclick="addItemtotable(document.getElementById('operationtype').value)"
					data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="currStkGraterModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<input type="hidden" id="curstkcheck" value="0" />
				<spring:message code="pos.jsp.totqtygrtcrntstckmsg"
					text="Total quantity is greater than current stock" />
				( <span id="currstkofitm"></span> ).


			</div>

			<div class="modal-footer">
				<button type="button" onclick="closeCurrStkModal()"
					data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Cust Last Bills Modal -->
</div>

<div class="modal fade" id="currStkGraterModal_rsp" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.inputgrtsellmsg"
					text="Input quantity is greater than sell/remaining quantity." />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="closeCurrStkModalrsp()"
					data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="sameItemInvModal_rsp" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.itemalrdyaddedmsg"
					text="Item already added for this invoice." />
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="sameUserModal_rsp" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.returndfrntmemomsg"
					text="You are trying to return different user cashmemo." />
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="confirmModalPos_rsp" tabindex="-1"
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
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmPosrsp()"
					data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal Ends -->
<div class="modal fade" id="noItemBarcodeModal_rsp" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				No item found against this(<span id="inputbarcode_rsp"></span>)
				barcode. Please check the input barcode.
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

<div class="modal fade" id="confirmModal_rsp" tabindex="-1"
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
				<input type="hidden" id="confirmId_rsp" value="">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmrsp()" data-dismiss="modal"
					class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>















<!-- Modal -->
<div id="purModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Modal Header</h4>
			</div>
			<div class="modal-body" style="height: 800px;">
				<p>Some text in the modal.</p>
				<iframe
					src="${pageContext.request.contextPath }/purchaseinvoice/loadpurchaseinvoice.htm"
					style="width: 100%; height: 100%;"></iframe>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>



<div class="modal fade" id="stkdetModal_so" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="so_myModalLabel">
					<spring:message code="pos.jsp.stckdetailsfor"
						text="Stock Details for" />
					<span id="moditemname"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div>
					<span class="font-bold"><spring:message
							code="itemmstr.jsp.manufacturer" text="Manufacturer" /> :</span> <span
						id="modmanufname_so"></span>
				</div>
				<div class="hide">
					<span class="font-bold"><spring:message
							code="itemmstr.jsp.content" text="Content" /> :</span> <span
						id="modcontentname_so"> </span>
				</div>
				<div>
					<span class="font-bold"><spring:message
							code="itemmstr.jsp.group" text="Group" /> :</span> <span
						id="modgroupname_so"> </span>
				</div>
				<div>
					<span class="font-bold"><spring:message
							code="itemmstr.jsp.rack" text="Rack" /> :</span> <span
						id="modrackname_so"> </span>
				</div>
				<div>
					<span class="font-bold">Note :</span> <span id="moditemnote_so">
					</span>
				</div>
				<!-- <div id="snditmtopodiv" class="">
					<input type="hidden" id="senditemtopoid">
					<button type="button" onclick="sendItemToPO(1)" class="btn btn-theme">Send Item To PO</button>
					&nbsp; &nbsp;<span id="snditmpo" class="alert alert-success hide" style="padding: 6px;"></span>
				</div> -->
				<div id="itemnotfounddiv_so" class="hide">
					<input type="hidden" id="itemnotfoundid_so"> <span
						class="font-bold" id="itemnotfound"><spring:message
							code="pos.jsp.nostockerror" text="No stock found for" /> <span
						id="itemnotfoundname_so"> </span>

						<button type="button" onclick="getGenericMed()"
							data-dismiss="modal" class="btn btn-theme hide">
							<spring:message code="footer.jsp.btn.getaltmed"
								text="Get Alternate Medicine" />
						</button></span>
				</div>
				<div
					style="max-height: 300px; height: 200px; overflow: auto; margin-top: 1%;"
					id="modtable_so">
					<table id="so_itemstockdetails"
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th class="hide"><spring:message
										code="purinvdet.jsp.expdate" text="ExpDate" /></th>
								<th><spring:message code="pos.jsp.currstock"
										text="Cur Stock" /></th>
								<th><spring:message code="pos.jsp.holdstock"
										text="Hold Stock" /></th>
								<th class="numeric"><spring:message
										code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th class="numeric hide"><spring:message
										code="pos.jsp.mrpPack" text="MRP/Pack" /></th>
								<%-- <th class="numeric hide"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th> --%>
								<th class="hide"><spring:message code="pos.jsp.packing"
										text="Packing" /></th>
								<th>P. Rate</th>
								<th id="salerateheader">S. Rate</th>
							</tr>
						</thead>
						<tbody id="itemdetails_so">

						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td class="font-bold" id="so_totalcurrstkitm">48/0 [480]</td>
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


<style>
.rowActive {
	background-color: #EDF4F9 !important;
}
</style>


<script
	src="${pageContext.request.contextPath }/assets/js/inventory/stock/stock_transfer.js"></script>
<script
	src="${pageContext.request.contextPath }/assets/js/glitter/jquery.gritter.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script
		src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/pos/customer/customer_en.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/inventory/doctor/doctor_en.js"></script>
	<script
		src="${pageContext.request.contextPath }/assets/js/pos/cashmemo/cashmemo_en.js"></script>
</c:if>
<script
	src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/returnsalememo.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<%-- 	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script> --%>
	<script
		src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/retcashmemo_en.js"></script>

</c:if>
<script
	src="${pageContext.request.contextPath }/assets/js/inventory/stock/stock_trans_en.js"></script>
<script type="text/javascript">
	/* var isWholesale=${isWholesale};
	var isOptical=${isOptical}; */
	
	var BASE_URL = "${pageContext.request.contextPath}";
	var dotMatrixPrint='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_PRINT)%>';
	var n2='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE)%>';
	var n1='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO)%>';
	/* var is_salemanflag=${userinfo.isSaleman}; */
	///var defaul_sales_man_per=${defaulSalesManPer};
	////var freechequee=${saleHeaderDTO.lotAdjAmount};
    var companyId = "${sessionScope.sesloggedinUser.companyId}";
    var primarySearchId = "${sessionScope.sesloggedinUser.primarySearchType}";
	
	/* 
	if (freechequee>0) {

		$("#flotid").removeClass("hide");
		$("#totltadj").removeClass("hide");
	}else {
		$("#flotid").addClass("hide");
		$("#totltadj").addClass("hide");
	} */

	//$("#add_cust_td").hide();
	function showConfirmModal() {
		$('#confirmMessageModal').modal('show');
	}
	/* $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		  var target = $(e.target).attr("href") // activated tab
		  alert(target);
		}); */
	$(document).ready(function() {
		   if(primarySearchId == 1){
			   $("#item_name").focus(); 
		   }else if(primarySearchId == 2){
			   $("#item_barcode").focus(); 
		   }else{
			   $("#item_code").focus(); 
		   }

		getvendorledger_sale($('#dueties_and_tax_accf').val(),0,0,0);// for duties and tax
		getvendorledger_sale($('#roundoff_group_codef').val(),0,0,1);// for round off
		getvendorledger_sale($('#saleaccunt_group_codef').val(),0,0,2);// for sale account
		getvendorledger_sale($('#dicount_codef').val(),0,0,4);// for discount acc
	    //// sale_id_present=${saleId};

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

