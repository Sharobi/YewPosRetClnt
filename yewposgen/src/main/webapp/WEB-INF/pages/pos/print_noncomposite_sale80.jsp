<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bill</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> -->
  <link href="${pageContext.request.contextPath }/assets/css/bootstrap/bootstrap.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
  <script src="${pageContext.request.contextPath }/assets/js/common/bill_setup.js"></script> 
	<script type="text/javascript">
		var BASE_URL = "${pageContext.request.contextPath}";
	</script>
  <style>
   body {
	font-size: 12px;
     }

		table {
			border-collapse: collapse;
		}
		
		table tr th {
			font-weight: normal;
		}
    /*th {
       text-align: center;
       padding: 2px;
       border: 2px solid black;
       font-size:11px
    }
    td{
        padding: 2px;
        border-right: 2px solid black;
        font-size:11px
    }
   
    h4 {
       margin-bottom:0px;
       font-size:15px;
    }*/
   p {
      font-size:13px
   }
   
  /*  @page{
    margin-left:0mm;
    margin-right: 0mm;
    size:auto;
   }  */
 </style>
</head>
<body>

<!-- <div class="" style="margin-top:2px;"> -->
   <div style="width: 295px;" align="left">
    <table style="width:100%">
    	<tr style="border:1px solid black;border-bottom:2px solid black">
    		<td colspan="4" style="text-align: center;">
    			<h4 style="padding:0px"> <b> <u> ${sessionScope.sesloggedinStore.name} </u> </b> </h4>
	          		 <p> ${sessionScope.sesloggedinStore.address} <br>
	                        ${sessionScope.sesloggedinStore.postcode}<br>
	                        ${sessionScope.sesloggedinStore.email}
	                        ${sessionScope.sesloggedinStore.state}<br>
	                        ${sessionScope.sesloggedinStore.country}
	                  </p>
			          <p style="text-align:left"> 
			                GSTIN:${sessionScope.sesloggedinStore.taxRegNo} <br>
			                PHONE :${sessionScope.sesloggedinStore.phone}
			          </p>    
    		</td>
    	</tr>

      <tr style="border:1px solid black">
         <td colspan="4"> <h4> <b> RETAIL INVOICE </b> </h4> </td>
      </tr>

      <tr style="border:1px solid black;border-bottom:0px">
         <td colspan="4"> <p> 
               <c:choose>
					<c:when test="${saleHeaderDTO.printCount >1}">
						   Duplicate Copy
				    </c:when>
					<c:otherwise>
						   Original Copy
				    </c:otherwise>
				</c:choose>
			  </p> </td>
      </tr>
      <tr style="border:1px solid black;border-top:0px;border-bottom:0px">
         <td colspan="2" rowspan="3" style="text-align:left;border-bottom:1px solid black;"> <p> MEMO NO: </p>
                                      <h4> <b> ${saleHeaderDTO.invNo} </b> </h4> </td>
         <td colspan="1"> <p> Date </p> </td>
         <td colspan="1" style="text-align:right"> <p> 
         	<fmt:parseDate value="${saleHeaderDTO.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" /> 
            <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedInvDate}" />
          </p> </td>
      </tr>
      <tr style="border:1px solid black;border-top:0px;border-bottom:0px">
         <td colspan="1"> <p> Time </p> </td>
         <td colspan="1" style="text-align:right"> <p> ${saleHeaderDTO.invTime} </p> </td>
      </tr>
      <tr style="border:1px solid black;border-top:0px">
         <td colspan="1"> <p> User </p> </td>
         <td colspan="1" style="text-align:right"> <p> ${sessionScope.sesloggedinUser.userCode} </p> </td>
      </tr>
      <tr style="border:1px solid black;height:10px">
         <td colspan="4">  </td>
      </tr>
      <tr style="border:1px solid black;border-bottom:0px;font-size:12px;text-align:left">
         <td colspan="1" style="text-align:left"> <span style="float:left;">Sri</span> <span style="float:right;">Item Description</span> </td>
         <td colspan="1" style="text-align:right"> Quantity </td>
         <td colspan="1" style="text-align:right"> Rate </td>
         <td colspan="1" style="text-align:right"> Amount </td>
      </tr>
       <tr style="border:1px solid black;border-top:0px;font-size:12px;height: 30px;">
         <td colspan="1">  </td>
         <td colspan="1">  </td>
         <td colspan="1" style="text-align:right;"> Disc % </td>
        <!--  <td colspan="1" style="text-align:right" class="hide"> GST Token </td> -->
         <td colspan="1" style="text-align:right"> Sman </td>
      </tr>

   <c:set var="totQty" value="0"></c:set> 
   <c:set var="totLooseQty" value="0"></c:set>
   <c:set var="totMRP" value="0"></c:set>
   <c:if test="${!empty saleDetailsDTOs }">
   <c:set var="totCgst" value="0"></c:set> 
   <c:set var="totSgst" value="0"></c:set> 
   <c:set var="totIgst" value="0"></c:set> 
	  <c:set var="nooflinerem" value="${saleDetailsDTOs.size()}"></c:set>
			<c:forEach items="${saleDetailsDTOs}" var="saleDetail" varStatus="loop">
      <tr style="font-size:12px;border:1px solid black;border-bottom:0px;border-top:0px;">
         <td colspan="1" style="text-align:left"> <span style="float:left;">${loop.index+1}</span><span style="float:right;"> ${saleDetail.itemName}</span></td>
         <td colspan="1">${saleDetail.packQty} </td>
         <td colspan="1" style="text-align:right"> <fmt:formatNumber value="${saleDetail.rate}" currencySymbol="" type="currency" groupingUsed="false" /> </td>
         <td colspan="1" style="text-align:right"> <fmt:formatNumber value="${saleDetail.totAmount}" currencySymbol="" type="currency" groupingUsed="false" /> </td>
      </tr>
      <tr style="font-size:12px;border:1px solid black;border-bottom:0px;border-top:0px">
         <td colspan="1" style="text-align:left"> Hsn: ${saleDetail.hsnCode}</td>
         <td colspan="1">  </td>
         <td colspan="1">  </td>
         <td colspan="1">  </td>
      </tr>
      <tr style="font-size:12px;border:1px solid black;border-top:0px;border-bottom:0px;">
         <td colspan="1" style="text-align:left;font-size:11px"> BCD ${saleDetail.batchNo} </td>
         <td colspan="1">  </td>
         <td colspan="1" style="text-align:right;"> <fmt:formatNumber value="${saleDetail.disc}" currencySymbol="" type="currency" groupingUsed="false" /> </td>
         <%-- <td colspan="1" style="text-align:right" class="hide" > ${saleDetail.cgstPercentage + saleDetail.sgstPercentage} </td> --%>
         <td colspan="1" style="text-align:right">  </td>
      </tr>
      <c:set var="totQty" value="${totQty+saleDetail.packQty}"></c:set>
      <c:set var="totLooseQty" value="${totLooseQty+saleDetail.looseQty}"></c:set>
       <c:set var="totMRP" value="${totMRP+saleDetail.totAmount}"></c:set>
       <c:set var="totCgst" value="${totCgst+saleDetail.cgst}"></c:set>
	   <c:set var="totSgst" value="${totSgst+saleDetail.sgst}"></c:set>
	   <c:set var="totIgst" value="${totIgst+saleDetail.igst}"></c:set>
      
       </c:forEach>
   </c:if>




      <tr style="border:1px solid black">
        <td colspan="4" style="height:15px"> </td>
      </tr>

      <tr style="border:1px solid black;border-bottom:0px">
        <td colspan="1"> <span style="float:left"> Sales: </span> <span> ${totQty} </span> </td>
        <td colspan="1"> Loose: </td>
        <td colspan="1"> ${totLooseQty} </td>
        <td colspan="1" style="text-align:right">${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount} </td>
      </tr>
      <tr style="border:1px solid black;border-top:0px">
        <td colspan="1"> <span style="float:left"> RTN: </span> <span> 0  </span> </td>
        <td colspan="1"> Loose: </td>
        <td colspan="1"> 0 </td>
        <td colspan="1">  </td>
      </tr>

      <tr style="border:1px solid black;border-top:0px">
        <td colspan="2" style="text-align:left"> Total Records Sales </td>
        <td colspan="1"> ${totQty} </td>
        <td colspan="1"> <span style="float:left"> Return </span> <span style="float:right"> 0 </span> </td>
      </tr>

      <tr style="border:1px solid black;border-bottom:0px">
        <td colspan="4" style="text-align:left"> <b> <u> AMOUNT DETAILS: </u> </b> </td>
      </tr>
      <tr style="border:1px solid black;border-bottom:0px;border-top:0px">
        <td colspan="2" style="text-align:right"> Sales: </td>
        <td colspan="2" style="text-align:right">  ${totMRP+saleHeaderDTO.discAmount} </td>
      </tr>
      <tr style="border:1px solid black;border-bottom:0px;border-top:0px">
        <td colspan="2" style="text-align:right"> Less Item Discount: </td>
        <td colspan="2" style="text-align:right"> -${saleHeaderDTO.discAmount} </td>
      </tr>
      <tr style="border:1px solid black;border-top:0px">
        <td colspan="2" style="text-align:right"> Net Payable: </td>
        <td colspan="2" style="text-align:right"> ${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount} </td>
      </tr>

       <tr style="border:1px solid black">
        <td colspan="4" style="height:10px"> </td>
      </tr>

      <tr style="border:1px solid black;border-bottom:0px">
        <td colspan="4" style="text-align:left"> <b> <u> PAYMENT DETAILS: </u> </b> </td>
      </tr>
      <tr style="border:1px solid black;border-bottom:0px;border-top:0px">
        <td colspan="2" style="text-align:right"> ${saleHeaderDTO.invModeName}: </td>
        <td colspan="2" style="text-align:right">  ${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount}</td>
      </tr>
      <tr style="border:1px solid black;border-top:0px">
        <td colspan="2" style="text-align:right"> <h4> <b> TOTAL PAID: </b> </h4> </td>
        <td colspan="2" style="text-align:right"> <h4> <b> ${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount} </b> </h4> </td>
      </tr>

       <tr style="border:1px solid black;border-top:2px solid black">
        <td colspan="4" style="height:15px"> </td>
      </tr>

      <!-- <tr style="border:1px solid black">
        <td colspan="4"> 
            GST Breakup Details
        </td>
      </tr> -->

      <!-- <tr style="border:1px solid black;height:30px">
        <td colspan="2" style="text-align:left"> 
           <b> GST Details </b>
        </td>
        <td colspan="1"> 
            <b> Taxable </b>
        </td>
        <td colspan="1" style="text-align:right"> 
            <b> GST </b>
        </td>
      </tr> -->
      
      <%-- <c:if test="${!empty taxDetailsDTOs }">
		<c:forEach items="${taxDetailsDTOs}" var="taxDetail" varStatus="loop">
		      <tr style="border:1px solid black;border-bottom:0px;border-top:0px;">
		        <td colspan="2" style="text-align:left"> 
		           CGST ${taxDetail.cgstPercentage}
		        </td>
		        <td colspan="1"> 
		             ${taxDetail.taxableAmount}
		        </td>
		        <td colspan="1" style="text-align:right"> 
		             ${taxDetail.cgst}
		        </td>
		      </tr>
		      <tr style="border:1px solid black;border-top:0px;border-bottom:0px;">
		        <td colspan="2" style="text-align:left"> 
		           SGST ${taxDetail.sgstPercentage}
		        </td>
		        <td colspan="1"> 
		           ${taxDetail.taxableAmount}
		        </td>
		        <td colspan="1" style="text-align:right"> 
		            ${taxDetail.sgst}
		        </td>
		      </tr>
   </c:forEach>
  </c:if> --%>
      <%-- <tr style="border:1px solid black;height:30px">
        <td colspan="2" style="text-align:right"> 
           Total of GST Amount:
        </td>
        <td colspan="2" style="text-align:right"> 
             ${saleHeaderDTO.taxAmount}
        </td>
      </tr> --%>

      <tr style="border:1px solid black;height:5px">
        <td colspan="4"> 
        </td>
      </tr>

      <tr style="border:1px solid black;height:5px">
        <td colspan="4" style="text-align: center;"> 
          <h3> <b> Net Saved By You </b> </h3>
             <h3> <b> ${saleHeaderDTO.discAmount} </b> </h3>
        </td>
      </tr>

      <tr style="border:1px solid black;border-bottom:0px">
        <td colspan="2"> 
          <h5 style="margin-top:30px;text-align:left;font-size:17px"> <u> E.&O.E. </u> </h5>
        </td>
        <td colspan="2"> 
          <h5 style="margin-top:30px;text-align:right;font-size:17px"> P.T.O........T&C </h5>
        </td>
      </tr>

      <tr style="border:1px solid black;border-top:0px">
        <td colspan="4"> 
          <h5 style="margin-top:10px;font-size:15px"> 
              NO CASH REFUND NO EXCHANGE ON DISCOUNT ITEM. EXCHANGE WITHIN 7 DAYS FROM 10 TO 12 NOON WITH CASH MEMO & PRICE TAG. 
          </h5>
        </td>
      </tr>

    </table>
</div>
<!-- </div> -->
<input type="hidden" id="backUrl" value="${backUrl}" />
</body>
</html>
