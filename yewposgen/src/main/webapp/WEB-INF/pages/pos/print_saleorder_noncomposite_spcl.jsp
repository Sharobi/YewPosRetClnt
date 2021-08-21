<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<!DOCTYPE html>
<html lang="en">
   <head>
      <title>Sale Order Bill</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
      <style>
         /*.row {
         margin-left:0px;
         margin-right:0px;
         display:flex
         }*/
         th {
         text-align: center;
         padding: 2px;
         border: 1px solid black;
         }
         td{
         text-align: center;
         padding: 2px;
         border-right: 1px solid black;
         font-size:12px;
         }
         /*table {
         border-top: 1px solid black;
         border-right: 1px solid black;
         border-left: 1px solid black
         }*/
         h4 {
         margin-bottom:0px;
         font-size:12px;
         margin-top:2px;
         }
         /*.col-ms-1 {
         width:8%;
         }
         .col-ms-10 {
         width:84%;
         }*/
         /*.col-ms-3 {
         width:25%;
         }
         .col-ms-6 {
         width:50%;
         }*/
      </style>
   </head>
   <body>
      <div id="printdiv"  style="width:100%;display:flex">
         <!-- <div style="width:1%"> </div> -->
         <div style="width:100%">
            <div style="border-right: 1px solid black;border-left: 1px solid black;border-top: 1px solid black">
               <div style="width:100%;display:flex;padding:10px 0px 30px 0px">
                  <div style="width:25%;text-align:center"> 
                  <!-- /*Sayantan Mahanty added date-21/02/2020*/ -->
                     <%-- <img src="${pageContext.request.contextPath }/assets/images/logo/logo_new.png" alt="" style="width:80%;margin-top:30px"> --%>
                  </div>
                  <div style="width:50%;text-align:center">
                     <h3 style="font-size:18px;margine-top:2px;"> <b> SALES QUOTATION </b> </h3>
                     <h4 style="margin-top:10px"> <b><b>${sessionScope.sesloggedinStore.name}</b></b> </h4>
                     <h4>${sessionScope.sesloggedinStore.address}</h4>
                     <h4>${sessionScope.sesloggedinStore.postcode} (${sessionScope.sesloggedinStore.state}) ${sessionScope.sesloggedinStore.country} </h4>
                     <h4>${sessionScope.sesloggedinStore.email} ${sessionScope.sesloggedinStore.phone} </h4>
                  </div>
                  <div style="text-align:right;width:25%">
                     <h4> <b>
                    <%--  <c:choose>
						  <c:when test="${saleHeaderDTO.printCount >1}">
						   Duplicate Copy
						  </c:when>
						  <c:otherwise>
						   Original Copy
						  </c:otherwise>
						</c:choose> --%>
                         <br>
                        <br>
                        ${sessionScope.sesloggedinUser.userCode}
                        </b> </h4>
                  </div>
               </div>
               <!-- .....................part 2.............................. -->
               <div style="border-top:1px solid black;display:flex;width:100%">
                  <div style="border-right:1px solid black;padding-bottom:0px;width:50%">
                     <div style="display:flex;width:100%">
                        <div style="width:50%">
                           <h4> <b> GST NUMBER </b> </h4>
                        </div>
                        <div style="width:50%">
                           <h4> ${sessionScope.sesloggedinStore.taxRegNo} </h4>
                        </div>
                     </div>
                     <div style="display:flex;width:100%">
                        <div style="width:50%">
                           <h4> <b> Quotation Number </b> </h4>
                        </div>
                        <div style="width:50%">
                           <h4> ${saleHeaderDTO.invNo} </h4>
                        </div>
                     </div>
                     <div style="display:flex;width:100%">
                        <div style="width:50%">
                           <h4> <b> Quotation Date </b> </h4>
                        </div>
                        <div style="width:50%">
                           <h4>
                              <fmt:parseDate value="${saleHeaderDTO.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" /> 
                              <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedInvDate}" />
                           </h4>
                        </div>
                     </div>
                     <div style="display:flex;width:100%">
                        <div style="width:50%">
                           <h4> <b> Salesperson Name </b> </h4>
                        </div>
                        <div style="width:50%">
                           <%-- <h4> ${saleDetailsDTOs[0].salesmanName} </h4> --%>
                           <h4> NA</h4>
                        </div>
                     </div>
                  </div>
                  <div style="width:50%">
                     <h4> <b> Transportation Mode:(Apply For Supply of Goods Only) </b> </h4>
                     <div style="display:flex;width:100%">
                        <div style="width:50%">
                           <h4> <b> Veh. No. </b> </h4>
                        </div>
                        <div style="width:50%">
                           <h4> </h4>
                        </div>
                     </div>
                     <div style="display:flex;width:100%">
                        <div style="width:50%">
                           <h4> <b> Date & Time of Supply </b> </h4>
                        </div>
                        <div style="width:50%">
                           <h4>
                              <fmt:parseDate value="${saleHeaderDTO.dueDate}" var="parsedDueDate" pattern="yyyy-MM-dd" /> 
                              <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedDueDate}" />
                          </h4>
                        </div>
                     </div>
                     <div style="display:flex;width:100%">
                        <div style="width:50%">
                           <h4> <b> Place of Supply </b> </h4>
                        </div>
                        <div style="width:50%">
                           <h4> Bill-to-Address </h4>
                        </div>
                     </div>
                     <c:if test="${sessionScope.sesloggedinUser.companyId == 18}">
                     <div style="display:flex;width:100%">
                        <div style="width:50%">
                           <h4> <b> Tailor </b> </h4>
                        </div>
                        <div style="width:50%">
                           <h4>  </h4>
                        </div>
                     </div>
                     <div style="display:flex;width:100%">
                        <div style="width:50%">
                           <h4> <b> Interior </b> </h4>
                        </div>
                        <div style="width:50%">
                           <h4>  </h4>
                        </div>
                     </div>
                     </c:if>
                  </div>
               </div>
               <!-- ............................3rd part....................................... -->
               <div style="border-top:1px solid black;display:flex;width:100%">
                  <div style="border-right:1px solid black;width:50%">
                     <h4> <b> Details of Receiver (Billed to) </b> </h4>
                  </div>
                  <div style="width:50%">
                     <h4> <b> Details of Consignee (Shipped to) </b> </h4>
                  </div>
               </div>
               <!-- ..............................4th part .....................................   --> 
               <div style="border-top:1px solid black;display:flex;width:100%">
                  <div style="border-right:1px solid black;padding-top:0px;padding-bottom:0px;width:50%">
                     <h4> <b> Name </b> ${saleHeaderDTO.customerName} </h4>
                     <h4> <b> Address </b> ${saleHeaderDTO.customerAddress} </h4>
                     <h4> <b> Phone </b> ${saleHeaderDTO.customerPhone}  </h4>
                     <h4> <b> GSTIN/UNIQUE ID </b>${saleHeaderDTO.customerGstNo} </h4>
                  </div>
                  <div style="padding-top:0px;width:50%">
                     <h4> <b> Name </b> ${saleHeaderDTO.customerName} </h4>
                     <h4> <b> Address </b> ${saleHeaderDTO.customerAddress} </h4>
                     <h4> <b> GSTIN/UNIQUE ID </b>${saleHeaderDTO.customerGstNo}</h4>
                  </div>
               </div>
            </div>
            <!-- ....................................5th part...................................  -->   
            <div >
               <div style="border-bottom:1px solid black">
                  <table style="width:100%;border-top: 1px solid black;border-right: 1px solid black;border-left: 1px solid black">
                     <tr>
                        <th rowspan="2">Sr. <br> No</th>
                        <th rowspan="2">Barcode No.</th>
                        <th rowspan="2">Description</th>
                        <th rowspan="2">HSN/SAC</th>
                        <!-- 
                           <th rowspan="2">GSt Group <br> Type</th> -->
                        <th rowspan="2">Qty.</th>
                        <th rowspan="2">UOM</th>
                        <th rowspan="2">Unit Price</th>
                        <th rowspan="2" colspan="2">Amt</th>
                        <th rowspan="2">Discount</th>
                        <th rowspan="2">Taxable <br> Value</th>
                        <th colspan="2">CGST</th>
                        <th colspan="2">SGST</th>
                        <th colspan="2">IGST</th>
                     </tr>
                     <tr>
                        <th>Rate</th>
                        <th>Amt</th>
                        <th>Rate</th>
                        <th>Amt</th>
                        <th>Rate</th>
                        <th>Amt</th>
                     </tr>
                     <c:set var="noofline" value="10"></c:set>
                     <c:set var="nooflinerem" value="0"></c:set>
                     <c:set var="totunitprice" value="0"></c:set>
                     <c:set var="totamnt" value="0"></c:set>
                     <c:set var="totdisc" value="0"></c:set>
                     <c:set var="totTexableValue" value="0"></c:set>
                     <c:set var="totCgst" value="0"></c:set>
                     <c:set var="totSgst" value="0"></c:set>
                     <c:set var="totIgst" value="0"></c:set>
                     <c:if test="${!empty saleDetailsDTOs }">
                        <c:set var="nooflinerem" value="${saleDetailsDTOs.size()}"></c:set>
                        <c:forEach items="${saleDetailsDTOs}" var="saleDetail" varStatus="loop">
                           <tr>
                              <td >${loop.index+1}</td>
                              <td>${saleDetail.sku}</td>
                              <td> 
                              <c:choose>
								  <c:when test="${saleDetail.itemRemarks !=''}">
								   ${saleDetail.itemRemarks}
								  </c:when>
								  <c:otherwise>
								    ${saleDetail.itemName}
								  </c:otherwise>
								</c:choose>
								</td>
                              <td>${saleDetail.hsnCode}</td>
                              <%-- 
                                 <td>${saleDetail.hsnCode}</td> --%>
                              <td>${saleDetail.packQty}</td>
                              <td>${saleDetail.packUnitName}</td>
                              <td>
                                 <fmt:formatNumber value="${saleDetail.rate}" currencySymbol="" type="currency" groupingUsed="false" />
                              </td>
                              <td colspan="2">
                                 <fmt:formatNumber value="${saleDetail.grossAmount}" currencySymbol="" type="currency" groupingUsed="false" />
                              </td>
                              <td>
                                 <fmt:formatNumber value="${saleDetail.disc}" currencySymbol="" type="currency" groupingUsed="false" />
                              </td>
                              <td>
                                 <fmt:formatNumber value="${saleDetail.taxableAmount}" currencySymbol="" type="currency" groupingUsed="false" />
                              </td>
                              <td>${saleDetail.cgstPercentage}</td>
                              <td>
                                 <fmt:formatNumber value="${saleDetail.cgst}" currencySymbol="" type="currency" groupingUsed="false" />
                              </td>
                              <td>${saleDetail.sgstPercentage}</td>
                              <td>
                                 <fmt:formatNumber value="${saleDetail.sgst}" currencySymbol="" type="currency" groupingUsed="false" />
                              </td>
                              <td>${saleDetail.igstPercentage}</td>
                              <td>
                                 <fmt:formatNumber value="${saleDetail.igst}" currencySymbol="" type="currency" groupingUsed="false" />
                              </td>
                           </tr>
                           <c:set var="totunitprice" value="${totunitprice+saleDetail.rate}"></c:set>
                           <c:set var="totamnt" value="${totamnt+saleDetail.grossAmount}"></c:set>
                           <c:set var="totdisc" value="${totdisc+saleDetail.disc}"></c:set>
                           <c:set var="totTexableValue" value="${totTexableValue+saleDetail.taxableAmount}"></c:set>
                           <c:set var="totCgst" value="${totCgst+saleDetail.cgst}"></c:set>
                           <c:set var="totSgst" value="${totSgst+saleDetail.sgst}"></c:set>
                           <c:set var="totIgst" value="${totIgst+saleDetail.igst}"></c:set>
                        </c:forEach>
                     </c:if>
                     <c:if test="${nooflinerem lt 10 }">
                        <c:forEach var="i" begin="1" end="${10-nooflinerem}">
                           <tr height="10px;">
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td colspan=2></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                           </tr>
                        </c:forEach>
                     </c:if>
                     <tr style="border-top:1px solid black">
                        <td colspan="2"></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td colspan=2>
                           <b>
                              <fmt:formatNumber value="${totunitprice}" currencySymbol="" type="currency" groupingUsed="false" />
                           </b>
                        </td>
                        <td>
                           <b>
                              <fmt:formatNumber value="${totamnt}" currencySymbol="" type="currency" groupingUsed="false" />
                           </b>
                        </td>
                        <td>
                           <b>
                              <fmt:formatNumber value="${totdisc}" currencySymbol="" type="currency" groupingUsed="false" />
                           </b>
                        </td>
                        <td>
                           <b>
                              <fmt:formatNumber value="${totTexableValue}" currencySymbol="" type="currency" groupingUsed="false" />
                           </b>
                        </td>
                        <td></td>
                        <td>
                           <b>
                              <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${totCgst}" />
                           </b>
                        </td>
                        <td></td>
                        <td>
                           <b>
                              <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${totSgst}" />
                           </b>
                        </td>
                        <td></td>
                        <td>
                           <b>
                              <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${totIgst}" />
                           </b>
                        </td>
                     </tr>
                     <tr>
                        <th colspan="11"><b> Invoice Value(In Words) </b></th>
                        <th colspan="4" style="text-align:right"> Total </th>
                        <th colspan="2" style="text-align:right" id="totInvValue1">
                           <fmt:formatNumber value="${totTexableValue}" currencySymbol="" type="currency" groupingUsed="false" />
                        </th>
                     </tr>
                     <tr>
                        <th colspan="11"><span id="totInvValueWord" ></span></th>
                        <th colspan="4" style="text-align:right"> Total GST Amount </th>
                        <th colspan="2" style="text-align:right">
                           <fmt:formatNumber value="${saleHeaderDTO.taxAmount}"/>
                        </th>
                     </tr>
                     <tr>
                        <th colspan="11"> </th>
                        <th colspan="4" style="text-align:right"> Freight Charges </th>
                        <th colspan="2" style="text-align:right"> 0.00 </th>
                     </tr>
                     <tr>
                        <th colspan="11"> </th>
                        <th colspan="4" style="text-align:right"><b> Loading And Packing Charges </b></th>
                        <th colspan="2" style="text-align:right"><b> 0.00 </b></th>
                     </tr>
                     <tr>
                        <th colspan="5" rowspan="3" style="text-align:left"> Acoount Name: <span> <c:if test="${sessionScope.sesloggedinUser.companyId == 18}">Sanctum Designs Private Limited</c:if> </span> <br> 
                           IFSC CODE: <span> <b><c:if test="${sessionScope.sesloggedinUser.companyId == 18}">UBIN0530123</c:if></b> </span> <br> 
                           Branch: <span> <b><c:if test="${sessionScope.sesloggedinUser.companyId == 18}">Ballygunge</c:if></b> </span>
                           
                        </th>
                        <th colspan="6" rowspan="3" style="text-align:left"> Account No: <span> <b><c:if test="${sessionScope.sesloggedinUser.companyId == 18}">301201010914808</c:if></b> </span> <br> 
                           Bank Name: <span> <b><c:if test="${sessionScope.sesloggedinUser.companyId == 18}">Union Bank Of India</c:if></b> </span>
                        </th>
                        <th colspan="4" style="text-align:right"> Insuraces Charges </th>
                        <th colspan="2" style="text-align:right"> 0.00 </th>
                     </tr>
                     <tr>
                        <th colspan="4" style="text-align:right"><b> Other Charges </b></th>
                        <th colspan="2" style="text-align:right"><b> 0.00 </b></th>
                     </tr>
                     <tr>
                        <th colspan="4" style="text-align:right"><b> Invoice Total </b></th>
                        <th colspan="2" style="text-align:right" id="totInvValue"><b> ${saleHeaderDTO.netAmount} </b></th>
                     </tr>
                     <tr>
                        <th colspan="8"> Certified that the particulars given above are true and correct </th>
                        <th colspan="9"> Electronic Reference No: </th>
                     </tr>
                     <tr>
                        <th colspan="17" style="height:20px"></th>
                     </tr>
                     <tr>
                        <th colspan="8"> TERMS AND CONDITIONS OF SALE </th>
                        <th colspan="9"> ${sessionScope.sesloggedinStore.name} </th>
                     </tr>
                     <tr>
                        <th colspan="8" rowspan="4" style="text-align:left;border-bottom:0px"> * No charges can be entertained after confirmation <br> 
                           * Bills Details must be confirmed at the time of confirmation of Sales Quotation <br> 
                           * Payments - 50% advance and balance before delivery
                        </th>
                        <th colspan="9" style="text-align:left;"> Signature: </th>
                     </tr>
                     <tr>
                        <th colspan="9"> Authorised Signatory: </th>
                     </tr>
                     <tr>
                        <th colspan="9" rowspan="2" style="text-align:left;border:1px solid black;border-bottom:0px">
                           Name: 
                           <sapn> ${sessionScope.sesloggedinUser.userCode} </sapn>
                           <br>
                           Designation: 
                           <sapn>  </sapn>
                        </th>
                     </tr>
                  </table>
               </div>
            </div>
         
         </div>
         <!-- <div style="width:1%"> </div> -->
          <input type="hidden" id="backUrl" value="${backUrl}" />
      </div>
      <script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.8.3.min.js"></script>
       <script src="${pageContext.request.contextPath }/assets/js/common/bill_setup.js"></script>
      <script type="text/javascript">
         var BASE_URL = "${pageContext.request.contextPath}";
         $(document).ready(function(){
         	$("#totInvValueWord").text(number2text($("#totInvValue").text()));
         	/* ============== Convert number to Word in currency ========================*/
         
         	function number2text(value) {
         		var fraction = Math.round(frac(value) * 100);
         		var f_text = "";
         
         		if (fraction > 0) {
         			f_text = "AND " + convert_number(fraction) + " PAISE";
         		}
         
         		return convert_number(value) + " RUPEES " + f_text + " ONLY";
         	}
         
         function frac(f) {
         	return f % 1;
         }
         
         function convert_number(number) {
         	if ((number < 0) || (number > 999999999)) {
         		return "NUMBER OUT OF RANGE!";
         	}
         	var Gn = Math.floor(number / 10000000); /* Crore */
         	number -= Gn * 10000000;
         	var kn = Math.floor(number / 100000); /* lakhs */
         	number -= kn * 100000;
         	var Hn = Math.floor(number / 1000); /* thousand */
         	number -= Hn * 1000;
         	var Dn = Math.floor(number / 100); /* Tens (deca) */
         	number = number % 100; /* Ones */
         	var tn = Math.floor(number / 10);
         	var one = Math.floor(number % 10);
         	var res = "";
         
         	if (Gn > 0) {
         		res += (convert_number(Gn) + " CRORE");
         	}
         	if (kn > 0) {
         		res += (((res == "") ? "" : " ") + convert_number(kn) + " LAKH");
         	}
         	if (Hn > 0) {
         		res += (((res == "") ? "" : " ") + convert_number(Hn) + " THOUSAND");
         	}
         
         	if (Dn) {
         		res += (((res == "") ? "" : " ") + convert_number(Dn) + " HUNDRED");
         	}
         
         	var ones = Array("", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN");
         	var tens = Array("", "", "TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY");
         
         	if (tn > 0 || one > 0) {
         		if (!(res == "")) {
         			res += " AND ";
         		}
         		if (tn < 2) {
         			res += ones[tn * 10 + one];
         		} else {
         
         			res += tens[tn];
         			if (one > 0) {
         				res += (ones[one]);
         			}
         		}
         	}
         
         	if (res == "") {
         		res = "zero";
         	}
         	return res;
         }
         
         });
      </script>
      <script>
     
      
      
      </script>
   </body>
</html>