<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="reprintcash.jsp.title" text="Cash Memo" /></title>
<style>
body {
	font-size: 9px;
}

table {
	border-collapse: collapse;
}

table tr th {
	font-weight: normal;
}
/* table  tr td {
    border: 1px solid;
} */

.arabichide{
      display: none ;
}
</style>
</head>
<body>



	<div id="printdiv" class="print ">
		<div style="margin: 0 auto; height: auto; width: 100%; float: center; font-family: verdana;">
			<div style="display:flex; width:100%">
			<div style="width:33%"><p id="dlcontId" style="text-align: center; margin: 0; padding: 0;">PH-${sessionScope.sesloggedinStore.phone}</p></div>
		    <div style="width:33%"><c:choose>
				<c:when test="${saleHeaderDTO.creditAmount gt 0.00}">
					<p style="text-align: center; margin: 0; padding: 0;">
						<spring:message code="reprintcredit.jsp.title1" text="CASH / CREDIT MEMO" />
						<%-- /
						<spring:message code="reprintcredit.jsp.prescrptnreg" text="Prescription Register" /> --%>
					</p>
				</c:when>
				<c:otherwise>
					<p style="text-align: center; margin: 0; padding: 0;">
						<spring:message code="reprintcash.jsp.title1" text="GST INVOICE" />
						<%-- /
						<spring:message code="reprintcredit.jsp.prescrptnreg" text="Prescription Register" /> --%>
					</p>
				</c:otherwise>
			</c:choose>
			</div>
			<div style="width:34%"><input type="hidden" value="${reprint}" id="reprint_stat" />
						<p id="reprint_p" class="hide" style="text-align: center; margin: 0; padding: 0;">
							<spring:message code="reprintcash.jsp.tag" text="Reprint" />
							: ${printCountRes}
						</p>
			</div><%-- 
			<p id="storecontId" style="text-align: center; margin: 0; padding: 0;">
				<b>${sessionScope.sesloggedinStore.name}</b>
			</p>
			<p id="taglinecontId" style="text-align: center; margin: 0; padding: 0;"></p>
			<p id="addcontId" style="text-align: center; margin: 0; padding: 0;"> ${sessionScope.sesloggedinStore.address}</p>
			<p id="dlcontId" style="text-align: center; margin: 0; padding: 0;">Email-${sessionScope.sesloggedinStore.email},PH-${sessionScope.sesloggedinStore.phone}</p>
 --%>
 </div>
			<div style="float: center; height: auto; width: 100%; display:flex; margin-bottom: 5px; border-bottom: 1px dashed; border-top: 1px dashed;">
				<div id="billinginfo_div" style="width:50%">
					<%-- <div>
       			<label style="width: 10%;font-weight: 600;"><b><spring:message code="reprintcash.jsp.billdetails" text="Bill Details" /></b>:</label>
   			</div> --%>
					<div id="etype">
						<label style="width: 10%;">Bill Type: ${saleHeaderDTO.esiType}</label>
					</div>
					<div id="ccode">
						<label style="width: 10%;">Card No: ${saleHeaderDTO.customerCode}</label>
					</div>
					<div>
						<b><%-- <label style="width: 10%;"><spring:message code="reprintcash.jsp.ptntname" text="Patient Name" />:</label> --%> <label id="patientName_label">${sessionScope.sesloggedinStore.name} <span id="genders">${saleHeaderDTO.gender}</span></label></b><br>
						 <%-- <label style="width: 10%;"><spring:message code="reprintcash.jsp.ptntname" text="Patient Name" />:</label> <label id="patientName_label">${saleHeaderDTO.customerName} <span id="genders">${saleHeaderDTO.gender}</span></label><br> --%>
						<%--  <label class="${isCur==2?'':'arabichide'}" style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.customername" text="اسم الزبون" /></label> --%>


					</div>
					<%-- <div>
						<label style="width: 10%;"><spring:message code="reprintcash.jsp.ptntphn" text="Patient Ph No." />:</label> <label id="patientName_label">${saleHeaderDTO.customerPhone}</label><br>
						<label class="${isCur==2?'':'arabichide'}"  style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.customerphnumber" text="رقم هاتف العميل" /></label>
					</div> --%>
					<div>
						<label style="width: 10%;"><spring:message code="reprintcash.jsp.address" text="Address" />:</label> <label id="address_label"> ${sessionScope.sesloggedinStore.address}</label><br>
		               <%-- <label style="width: 10%;"><spring:message code="reprintcash.jsp.ptntaddr" text="Patient Address" />:</label> <label id="patientName_label">${saleHeaderDTO.customerAddress}</label><br> --%>
		              <%--  <label class="${isCur==2?'':'arabichide'}"  style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.customeraddress" text="عنوان العميل" /></label> --%>

					</div>
					<div>
						<label style="width: 10%;"><spring:message code="cmn.print.jsp.trn" text="GST" />.:</label> <label id="">${sessionScope.sesloggedinStore.taxRegNo}</label>

					</div>
					<div>
						<label style="width: 10%;"><spring:message code="cmn.print.jsp.dlno" text="DL No" />.:</label> <label id="">${sessionScope.sesloggedinStore.dlLicenceNo}</label>

					</div>
					<%-- <div>
						<label style="width: 10%;"><spring:message code="cmn.print.jsp.panno" text="PAN No" />.:</label> <label id="">${saleHeaderDTO.panNo}</label>

					</div> --%>
					<%-- <div class="hide">
						<label style="width: 10%;"><spring:message code="dctr.jsp.name" text="Doctor's Name" />:</label> <label id="dctrName_label">${saleHeaderDTO.doctorName}</label>
					</div> --%>
					<c:if test="${saleHeaderDTO.esiType=='RSBY'}">
						<div id="placetreatment">
							<label style="width: 10%;">Place Of Treatment:</label> <label>${saleHeaderDTO.remarks}</label>
						</div>
					</c:if>
				</div>
				<div id="invinfo_div" style="width:50%">
					<c:if test="${saleHeaderDTO.esiType!=''}">
						<div id="slno">
							<label style="width: 10%;">SL No: ${saleHeaderDTO.slipNo}</label>
						</div>
						<div id="pid">
							<fmt:parseDate value="${saleHeaderDTO.prescriptionIssueDate}" var="pissuDate" pattern="MMM dd, yyyy" />
							<label style="width: 10%;">Pres. Issue Dt: <fmt:formatDate pattern="yyyy-MM-dd" value="${pissuDate}" /></label>
						</div>
					</c:if>
					<div>
					    <b><label style="width: 10%;"><spring:message code="reprintcash.jsp.to" text="To" />:</label> <label id="patientName_label">${saleHeaderDTO.customerName}</label></b><br>
						<label class="${isCur==2?'':'arabichide'}" style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.customername" text="اسم الزبون" /></label>
						<%-- <label style="width: 10%;"><spring:message code="purinvdet.jsp.invno" text="Invoice No" />. :</label> <label id="invNo_label">${saleHeaderDTO.invNo}</label><br>
						<label class="${isCur==2?'':'arabichide'}"   style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.invoiceno" text="رقم الفاتورة" /></label> --%>
					</div>
					<div>
					 <label style="width: 10%;"><spring:message code="reprintcash.jsp.address" text="Address" />:</label> <label id="patientName_label">${saleHeaderDTO.customerAddress}</label><br>
		             <label class="${isCur==2?'':'arabichide'}"  style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.customeraddress" text="عنوان العميل" /></label>
					</div>
					<div>
					 <label style="width: 10%;"><spring:message code="reprintcash.jsp.dlNo" text="DL No" />:</label> <label id="dlNo_label">${saleHeaderDTO.dlNo}</label><br>
		             <label class="${isCur==2?'':'arabichide'}"  style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.dlNo" text="عنوان العميل" /></label>
					</div>
					<div>
					 <label style="width: 10%;"><spring:message code="reprintcash.jsp.panNo" text="Pan No" />:</label> <label id="panNo_label">${saleHeaderDTO.panNo}</label><br>
		             <label class="${isCur==2?'':'arabichide'}"  style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.panNo" text="عنوان العميل" /></label>
					</div>
					 <label style="width: 10%;"><spring:message code="reprintcash.jsp.customerGstNo" text="GSTIN" />:</label> <label id="customerGstNo_label">${saleHeaderDTO.customerGstNo}</label><br>
		             <label class="${isCur==2?'':'arabichide'}"  style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.customerGstNo" text="عنوان العميل" /></label>
					</div><%-- 
					<div>
						<fmt:parseDate value="${saleHeaderDTO.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" />
						<label style="width: 10%;"><spring:message code="rtrnmemo.jsp.salereturnmodal.invdt" text="Invoice Date" />:</label> <label><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" /></label><br>
					    <label  class="${isCur==2?'':'arabichide'}" style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.invoicedate" text="تاريخ الفاتورة" /></label>
					</div>
					
					<div>
						<label style="width: 10%;"><spring:message code="cmn.print.jsp.trn" text="TRN" />.:</label> <label>${sessionScope.sesloggedinStore.taxRegNo}</label>
					</div> --%>
				</div>

			</div>
			<div style="display:flex;width:100%;border-bottom: 1px dashed">
			   <div style="width:50%"> <b>
				<label style="width: 10%;"><spring:message code="purinvdet.jsp.invno" text="Invoice No" />. :</label> <label id="invNo_label">${saleHeaderDTO.invNo}</label><br>
				<label class="${isCur==2?'':'arabichide'}"   style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.invoiceno" text="رقم الفاتورة" /></label>
			   </b> </div>
			   <div style="width:50%"> <b>
					<fmt:parseDate value="${saleHeaderDTO.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" />
					<label style="width: 10%;"><spring:message code="rtrnmemo.jsp.salereturnmodal.invdt" text="Invoice Date" />:</label> <label><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" /></label><br>
					<label  class="${isCur==2?'':'arabichide'}" style="width: 10%; font-size: 15px;"><spring:message code="arabic.pos.invoicedate" text="تاريخ الفاتورة" /></label>
			   </b> </div> 
			</div>
			<div style="float: center; margin-bottom: 5px; width: 100%;">
				<table id="billitemtable" style="text-align: center; border-collapse: collapse; width: 100%;">
					<thead style="border-bottom: 1px dashed;">
						<tr>
							<th><spring:message code="reprintcash.jsp.invno" text="SNo" /></th>
							<th colspan="4"><spring:message code="item.jsp.name" text="Item Name" /><br>
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.itemname" text="اسم العنصر" /></span>
							</th>
							<%-- <th width="17%;"><spring:message code="purinvdet.jsp.mfg" text="MFG" /></th> --%>
<%-- 							<th><spring:message code="cmn.print.jsp.hsn" text="HSN" /></th> --%>
							<th class="mfg"><spring:message code="purinvdet.jsp.mfg" text="MFG" /></th>
							<th class="grp"><spring:message code="purinvdet.jsp.grp" text="GRP" /></th>
							<th class="sch"><spring:message code="purinvdet.jsp.Sch" text="SCH" /></th>
							<th class="net">NET</th>
							<th class="hsn"><spring:message code="expiry.jsp.hsn" text="HSN" />	<br>
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.hsn" text="وحدة القياس" /></span>
							</th>
							<th><spring:message code="reprintcash.jsp.qty" text="Qty" /><br>
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.qty" text="كمية" /></span>


							</th>
 							<th><spring:message code="purinvdet.jsp.free" text="FREE" /></th>
 							<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
 							<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th> 
							<th class="unit"><spring:message code="expiry.jsp.unit" text="Unit" />	<br>
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.unit" text="وحدة القياس" /></span>
							</th>
							<th class="mrp"><spring:message code="expiry.jsp.mrp" text="MRP" />	<br>
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.mrp" text="وحدة القياس" /></span>
							</th>
							<th><spring:message code="purinvdet.jsp.rate" text="Rate" /><br>
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.price" text="معدل وحدة" /></span>
							</th>
							<%-- <th width="5%;"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
						<th width="5%;"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th> --%>
							<th class="smrp">&nbsp;<spring:message code="purinvdet.jsp.mrp" text="MRP" />&nbsp;</th>
							<%-- <th width="5%;">&nbsp;<spring:message code="purinvdet.jsp.vatprcnt" text="Vat%" />&nbsp;</th> --%>
							<%-- <th>&nbsp;<spring:message code="purinvdet.jsp.total" text="Total" />&nbsp;<br>
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.total" text="مجموع" /></span>
							</th> --%>
							<th>&nbsp;<spring:message code="purinvdet.jsp.discprcnt" text="Disc %" />&nbsp;<br>
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.discPer" text="خصم" /></span>
							</th>
							<%-- <th>&nbsp;<spring:message code="reprintcash.jsp.taxableamtnoncompo" text="Taxable Amt." />&nbsp;<br>
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.amount" text="مبلغ" /></span>
							</th> --%>
							<th>&nbsp;<spring:message code="reprintcash.jsp.cgst" text="CGST%" />&nbsp;
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.cgst" text="مبلغ" /></span>
							</th>
							<th>&nbsp;<spring:message code="reprintcash.jsp.sgst" text="SGST%" />&nbsp;
							<span  class="${isCur==2?'':'arabichide'}" style="font-size: 14px;"><spring:message code="arabic.pos.sgst" text="مبلغ" /></span>
							</th>
							<th class="cgst">&nbsp;<spring:message code="reprintcash.jsp.cgst" text="CGST%" />&nbsp;
							</th>
							<th class="sgst">&nbsp;<spring:message code="reprintcash.jsp.sgst" text="SGST%" />&nbsp;
							</th>
							<th>&nbsp;<spring:message code="" text="Net" />&nbsp;
							<th>&nbsp;<spring:message code="" text="Amount" />&nbsp;
							<%-- <th width="10%;">&nbsp;<spring:message code="reprintcash.jsp.igst" text="IGST(%)" />&nbsp;</th> --%>
						</tr>
					</thead>
					<tbody style="margin-bottom: 100px">
						<c:set var="noofline" value="10"></c:set>
						<c:set var="nooflinerem" value="0"></c:set>
						<c:if test="${!empty saleDetailsDTOs }">
							<c:set var="nooflinerem" value="${noofline-saleDetailsDTOs.size()}"></c:set>
							<c:forEach items="${saleDetailsDTOs}" var="saleDetail" varStatus="loop">
								<tr>
									<td>${loop.index+1}</td>
									<td colspan="4" align="left">${saleDetail.itemName}</td>
									<%-- <td width="17%;">${saleDetail.manufacturerCode}</td> --%>
<%-- 									<td>${saleDetail.hsnCode}</td> --%>
									<td class="mfg">${saleDetail.manufacturerCode}</td>
									<td class="grp">${fn:substring(saleDetail.groupName, 0, 3)}</td>
									<td class="sch">${fn:replace(saleDetail.scheduleName, 'SCHEDULE', '')}</td>
									<td class="net">${saleDetail.netContent}</td>
									<td>${saleDetail.hsnCode}</td>
									<td><%-- ${saleDetail.packQty * saleDetail.conversion + saleDetail.looseQty} --%>
									${saleDetail.itemdualstock==1 ?(saleDetail.packQty):(saleDetail.packQty * saleDetail.conversion + saleDetail.looseQty)}
									</td>
									<td>${saleDetail.freeQty}</td>

 									<td>${saleDetail.batchNo}</td>
 									<td>${saleDetail.expiryDateFormat}</td>
									<td class="unit">${saleDetail.looseUnitName}</td>
 									<td align="right">${saleDetail.mrp}</td>
									<%-- <td width="5%;">${saleDetail.packQty}</td>
							<td width="5%;">${saleDetail.looseQty}</td> --%>
									<td width="5%;" align="right"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${saleDetail.ratePerUnit}" /></td>
									<td class="smrp"><fmt:formatNumber value="${saleDetail.mrp}" currencySymbol="" type="currency"/></td>
									<%-- <td width="5%;"><fmt:formatNumber value="${saleDetail.vatPer}" currencySymbol="" type="currency"/></td> --%>
									<%-- <td ><fmt:formatNumber value="${saleDetail.totalAmount}" currencySymbol="" type="currency" /></td> --%>
									<td ><fmt:formatNumber value="${saleDetail.discPer}" currencySymbol="" type="currency" /></td>
									<%-- <td ><fmt:formatNumber value="${saleDetail.taxableAmount}" currencySymbol="" type="currency" /></td> --%>
									<%-- <td width="5%;" style="text-align: right;"><fmt:formatNumber value="${saleDetail.cgstPercentage}" currencySymbol="" type="currency"/></td> --%>
									<%-- <td class="cgst" style="text-align: right;"><fmt:formatNumber value="${saleDetail.cgst}" currencySymbol="" type="currency" />(${saleDetail.cgstPercentage}%)</td> --%>
									<td ><%-- <fmt:formatNumber value="${saleDetail.cgst}" currencySymbol="" type="currency" /> --%>${saleDetail.cgstPercentage}</td>
									<%-- <td width="5%;" style="text-align: right;"><fmt:formatNumber value="${saleDetail.sgstPercentage}" currencySymbol="" type="currency"/></td> --%>
									<%-- <td class="sgst" style="text-align: right;"><fmt:formatNumber value="${saleDetail.sgst}" currencySymbol="" type="currency" />(${saleDetail.sgstPercentage}%)</td> --%>
									<td ><%-- <fmt:formatNumber value="${saleDetail.sgst}" currencySymbol="" type="currency" /> --%>${saleDetail.sgstPercentage}</td>
									<%-- <td width="5%;" style="text-align: right;"><fmt:formatNumber value="${saleDetail.igstPercentage}" currencySymbol="" type="currency"/></td> --%>
									<%-- <td width="10%;" style="text-align: right;"><fmt:formatNumber value="${saleDetail.igst}" currencySymbol="" type="currency"/>(${saleDetail.igstPercentage}%)</td> --%>
									<td align="right"><fmt:formatNumber value="${saleDetail.totAmount/saleDetail.packQty}" currencySymbol="" type="currency" /></td>
									<td align="right"><fmt:formatNumber value="${saleDetail.totAmount}" currencySymbol="" type="currency" /></td>
								</tr>

							</c:forEach>
						</c:if>
						<c:if test="${nooflinerem gt 0 }">
							<c:forEach var="i" begin="0" end="${nooflinerem}">
								<tr class="extraRow" height="10px;"></tr>
							</c:forEach>
						</c:if>
					</tbody>
					<%-- <tfoot>
						<tr>
							<td></td>
							<td>Sale Ret. Amt.:</td>
							<td><fmt:formatNumber value="${saleHeaderDTO.adjAmount}" currencySymbol="" type="currency" /></td>
							<td class="mfg"></td>
							<td class="grp"></td>
							<td class="sch"></td>
							<td class="net"></td>
							<td></td>
							<td class="smrp"></td>
							<td><spring:message code="reprintcash.jsp.r.off" text="RoundOff" />:</td>
							<td><fmt:formatNumber value="${saleHeaderDTO.roundoff}" currencySymbol="" type="currency" /></td>
							<td class="unit"> </td>
							<td><spring:message code="purinvdet.jsp.total" text="Total" />:</td>
							<td style="text-align: right;"><fmt:formatNumber value="${saleHeaderDTO.totalAmount}" currencySymbol="" type="currency" /></td>
							<td style="text-align: right;"><fmt:formatNumber value="${saleHeaderDTO.discAmount}" currencySymbol="" type="currency" /></td>
							<td style="text-align: right;"><fmt:formatNumber value="${saleHeaderDTO.taxableAmount}" currencySymbol="" type="currency" /></td>

							<td class="cgst"><fmt:formatNumber value="${saleHeaderDTO.cgst}" currencySymbol="" type="currency" /></td>

							<td class="sgst"><fmt:formatNumber value="${saleHeaderDTO.sgst}" currencySymbol="" type="currency" /></td>

							<td ><fmt:formatNumber value="${saleHeaderDTO.igst}" currencySymbol="" type="currency"/></td>
						</tr>
					</tfoot> --%>
				</table>
			</div>
			<br>

							<%-- ${saleHeaderDTO.lotAdjAmount}--%>
                              <%--   ${saleHeaderDTO.othAdjAmount}--%>
                              
                              
			<div id="taxdetailscont" style="margin-bottom: 1px; margin-top: 50px; width: 100%; display:flex; border-top: 1px dashed;">
				<!-- tax details table --><div style="width:80%">
				<table id="taxdetailtable" style="width: 100%;">
					<thead>
						<tr style="text-align: right;">
							<th style="text-align: center;">GST Summary</th>
							<th>0% GST</th>
							<!-- <th>VatAmount</th> -->
							<th>5% GST</th>
							<th>12% GST</th>
							<th>18% GST</th>
							<th>28% GST</th>
							<th>Total</th>
							<!-- <th>IGST%</th>
					             <th>IGST AMT</th> -->
						</tr>
					</thead>
					<tbody>
					<c:set var="gstvalues" value="${fn:split('0,5,12,18,28', ',')}" scope="application" />
					
						<c:if test="${!empty taxDetailsDTOs }">
													<tr style="text-align: right;">
								<td style="text-align: center;">Taxable</td>
								<c:set var="count" value="0" scope="page" />
								<c:forEach items="${gstvalues}" var="gstval">
									<c:set var="found" value="0"/>
									<c:forEach items="${taxDetailsDTOs}" var="taxDetail" varStatus="loop">
										<c:if test="${taxDetail.taxPercentage == gstval}">
											<td>${taxDetail.taxableAmount}</td>
											<c:set var="found" value="1"/>
										</c:if>
									</c:forEach>
									<c:if test="${found == 0}">
										<td></td>
									</c:if>
								</c:forEach>
								<td>${saleHeaderDTO.taxableAmount}</td>
							</tr>	
							</c:if>
						<c:if test="${!empty taxDetailsDTOs }">
													<tr style="text-align: right;">
								<td style="text-align: center;">Tax</td>
								<c:set var="count" value="0" scope="page" />
								<c:forEach items="${gstvalues}" var="gstval">
									<c:set var="found" value="0"/>
									<c:forEach items="${taxDetailsDTOs}" var="taxDetail" varStatus="loop">
										<c:if test="${taxDetail.taxPercentage == gstval}">
											<td>${taxDetail.taxAmount}</td>
											<c:set var="found" value="1"/>
										</c:if>
									</c:forEach>
									<c:if test="${found == 0}">
										<td></td>
									</c:if>
								</c:forEach>
								<td>${saleHeaderDTO.taxAmount}</td>
							</tr>	
							</c:if>
						
						<%-- <c:if test="${!empty taxDetailsDTOs }">
							<c:forEach items="${taxDetailsDTOs}" var="taxDetail" varStatus="loop">
								<tr style="text-align: center;">
									<td>${taxDetail.taxPercentage}</td>
									<td><fmt:formatNumber value="${taxDetail.taxableAmount}" currencySymbol="" type="currency" /></td>
									<td><fmt:formatNumber value="${taxDetail.taxAmount}" currencySymbol="" type="currency" /></td>
 									<td>${taxDetail.cgstPercentage}</td>
									<td><fmt:formatNumber value="${taxDetail.cgst}" currencySymbol="" type="currency" /></td>
 									<td>${taxDetail.sgstPercentage}</td>
									<td><fmt:formatNumber value="${taxDetail.sgst}" currencySymbol="" type="currency" /></td>
									<td>${taxDetail.igstPercentage}</td>
							<td>${taxDetail.igst}</td>
								</tr>
							</c:forEach>
						</c:if> --%>
					</tbody>
				</table>
				<p style="display: none"><spring:message code="reprintcash.jsp.totinvfigr" text="Total Invoice Value (In Figures)" />: <span id="totInvValue" style="text-align: left;"> <fmt:formatNumber value="${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount}" currencySymbol="" type="currency" groupingUsed="false" /></span></p>
				<p style="margin-bottom:0px"><b><spring:message code="reprintcash.jsp.totinvword1" text="Net Amt (In Words)" />: <span id="totInvValueWord" style="text-align: left; white-space: nowrap;"> </span></b></p>
				<p style="margin-top:0px"><spring:message code="reprintcash.jsp.note" text="Note" />: <span id="noteline1"></span><br><span id="noteline2"></span></p>
				</div>
				
				<div style="width:20%">
				<table align="right">
				<tr><td colspan="4"><span style="float:left">Gross Total</span> <span style="float:right"> : </span></td>
							<td colspan="4" style="text-align:right">${saleHeaderDTO.grossAmount}</td>
				</tr>
				<tr>
				    <td colspan="4"><span style="float:left">Disc</span> <span style="float:right"> : </span></td>
							<td colspan="4" style="text-align:right">${saleHeaderDTO.discAmount}</td>
					
								</tr>
								<tr>
							<%-- <td colspan="6"><p style="display: none"><spring:message code="reprintcash.jsp.totinvfigr" text="Total Invoice Value (In Figures)" />: <span id="totInvValue" style="text-align: left;"> <fmt:formatNumber value="${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount}" currencySymbol="" type="currency" groupingUsed="false" /></span></p></td> --%>
							<td colspan="4"><span style="float:left">Lot Adj Amt</span> <span style="float:right"> : </span></td>
							<td colspan="4" style="text-align:right">${saleHeaderDTO.lotAdjAmount}</td>
								</tr>
								<tr>
							<%-- <td colspan="6"><b><spring:message code="reprintcash.jsp.totinvword1" text="Net Amt (In Words)" />: <span id="totInvValueWord" style="text-align: left; white-space: nowrap;"> </span></b></td> --%>
							<td colspan="4"><span style="float:left">GST</span> <span style="float:right"> : </span></td>
							<td colspan="4" style="text-align:right">${saleHeaderDTO.taxAmount}</td>
								</tr>
								<tr>
							<%-- <td colspan="6"><spring:message code="reprintcash.jsp.note" text="Note" />: <span id="noteline1"></span><br><span id="noteline2"></span></td> --%>
							<td colspan="4"><span style="float:left">Sale Return Amt</span> <span style="float:right"> : </span></td>
							<td colspan="4" style="text-align:right"><fmt:formatNumber value="${saleHeaderDTO.totalReturnAmount}" currencySymbol="" type="currency" /></td>
								</tr>
								<tr>
							<td colspan="4"><span style="float:left">R.Off</span> <span style="float:right"> : </span></td>
							<td colspan="4" style="text-align:right">${saleHeaderDTO.roundoff}</td>
								</tr>
								<tr>
								  <td colspan="4" style="border-top: 1px solid black;"><b><span style="float:left">Bill Amount</span><span style="text-align:right;">:</span></b></td>
								  <td colspan="4" style="border-top: 1px solid black; text-align:right"><b>${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount}</b></td>
								</tr>
								</table>
				</div>
				<!-- end tax details table -->
			</div>
					<p style="text-align:center;">
					   E & O.E
					</p>
					<p style="text-align:center;">
					   Signature - For ${sessionScope.sesloggedinStore.name}
					</p>
			<%-- 
			<div style="margin-bottom: 1px;">
				<table style="width: 100%;">
					<tr style="display: none">
						<td style=""><spring:message code="reprintcash.jsp.totinvfigr" text="Total Invoice Value (In Figures)" />: <span id="totInvValue" style="text-align: left;"> <fmt:formatNumber value="${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount}" currencySymbol="" type="currency" groupingUsed="false" />
						   </span>
						</td>
					</tr>
					<tr>
						<td style=""><spring:message code="reprintcash.jsp.totinvword1" text="Net Amt (In Words)" />: <span id="totInvValueWord" style="text-align: left; white-space: nowrap; font-weight: normal;"> </span></td>
						<!-- <td id="totInvValueWord" style="text-align: left;white-space:nowrap;"></td> -->
					</tr>
					<tr id="reversetaxRow">
						<td style=""><spring:message code="reprintcash.jsp.taxamtrspctrvrschrg" text="Amt of Tax subject to Rev. Chrgs" />: <span style=""> CGST = <fmt:formatNumber value="${saleHeaderDTO.cgst}" currencySymbol="" type="currency" /> SGST = <fmt:formatNumber value="${saleHeaderDTO.sgst}" currencySymbol="" type="currency" />
						</span></td>
						<td style="font-weight: bold;">
					<span style="padding-left: 20%"><fmt:formatNumber value="${saleHeaderDTO.cgst}" currencySymbol="" type="currency"/></span>
					<span  style="padding-left: 10%"><fmt:formatNumber value="${saleHeaderDTO.sgst}" currencySymbol="" type="currency"/></span>
					<span  style="padding-left: 12%"><fmt:formatNumber value="${saleHeaderDTO.igst}" currencySymbol="" type="currency"/></span>
				</td>
					</tr>
					<tr>
					<td ><spring:message code="reprintcash.jsp.note" text="Note" />: <span id="noteline1"></span><br><span id="noteline2"></span></td>
					</tr>
					<tr>
					   <td colspan="10" style="text-align:center;">Signature - For ${sessionScope.sesloggedinStore.name} </td>
					</tr>
				</table>
			</div>
 --%>
			<div>
				<table style="width: 100%;">

					<tr>
						
						<td></td>
						<td></td>
					</tr><%-- 
					<tr>
						<td><span id="noteline2"></span></td>
						<td width="25%" style="white-space: nowrap;"><span id="salesmansig"><spring:message code="reprintcash.jsp.sigsalesman" text="Sig. of Salesman" /></span></td>
						<td width="25%" style="white-space: nowrap;"><span id="pharmacistsig"><spring:message code="reprintcash.jsp.sigpharma" text="Sig. of Pharmasist" /></span></td>
					</tr> --%>
				</table>
			</div>
			<div style="float: left;">
				<input type="hidden" id="backUrl" value="${backUrl}" />
			</div>
		</div>
	</div>


	<script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.8.3.min.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/bill_setup.js"></script>
	<script type="text/javascript">
		var BASE_URL = "${pageContext.request.contextPath}";
	</script>

</body>
</html>