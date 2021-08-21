<html lang="en">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<style>
    p {font-size:17px;letter-spacing:1px}
    table {
	  font-family: arial, sans-serif;
	  border-collapse: collapse;
	  width: 100%;
    }
    td, th {
	 

    }
    th {
    	text-align:center;font-size:19px; border: 1px solid black; 
	  padding: 8px;
    }
    td{
    	border-bottom:none;
    	text-align: left;font-size:17px; border-right: 1px solid black; 
	  padding: 8px;
    }
</style>

</head>
<!-- <body style="background:#a1c3e8"> -->
<body>


	<div style="width:100%;margin-top:50px;display:flex" id="printdiv" class="print hide">
        <div style="width:5%"> </div>
        <div style="width:90%">
		     <div style="width:100%;display:flex">
		     	 <div style="width:42%"> <img src="${pageContext.request.contextPath }/assets/images/logo/Jeevan-Suraksha-Logo.png" style="width:87%;height:185px"> </div>
		     	 <div style="width:58%">  
		     	 	   <h3 style="margin-left:20%">${sessionScope.sesloggedinStore.name}</h3> <br> 
		              <p> ${sessionScope.sesloggedinStore.address} </p>
		              <!-- <p> Kolkata - 700102, Contact : 033-25710321 / 9836895226 </p> -->
		              <p> Email-${sessionScope.sesloggedinStore.email}</p>
		              <p> PH-${sessionScope.sesloggedinStore.phone} </p>
		     	 </div>
		     </div>

             <!--  .................................2nd part.................................... -->

             <div style="width:100%;display:flex;margin-top:30px;border:1px solid black">
             	  <div style="width:18%;padding:10px 0px 10px 7px;border-right:1px solid black"> 
             	      <p> Order No: </p>
                      <p> Buyer's Name: </p>
                      <p> Contact No: </p>
                     
             	  </div>
             	  <div style="width:46%;padding:10px 0px 10px 7px;border-right:1px solid black"> 
             	      <p> <b> ${saleHeaderDTO.saleId} </b> </p>
             	  	  <p style="text-transform:uppercase"> ${saleHeaderDTO.customerName} </p>
                      <p> ${saleHeaderDTO.customerPhone} </p>
                     
             	  </div>
             	  <div style="width:17%;padding:10px 0px 10px 7px;border-right:1px solid black">  
             	  	  <p> Invoice No: </p>
                      <p> Invoice Date: </p>
                      <p style="text-transform:uppercase"> gstin: </p>
             	  </div>
             	  <div style="width:38%;padding:10px 0px 10px 7px"> 
                         <p style="text-transform:uppercase;font-size:19px"> <b> ${saleHeaderDTO.invNo} </b> </p>
                         <fmt:parseDate value="${saleHeaderDTO.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" />
                         <p><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" /> </p>
                         <p> <b> ${sessionScope.sesloggedinStore.taxRegNo} </b> </p>
             	  </div>
             </div>


             <!-- ........................................3rd part................................... -->

            <table style="margin-top:10px;border:1px solid black">
				  <tr>
				    <th>Sl <br>No</th>
				    <th>Item description</th>
				    <th>HSN / SAC</th>
				    <th>Qnty</th>
				    <th>Rate</th>
				    <th>Gross</th>
				    <th>Disc%</th>
				    <th>Net</th>
				  </tr>
		    <c:set var="noofline" value="10"></c:set>
			<c:set var="nooflinerem" value="0"></c:set>
			<c:if test="${!empty saleDetailsDTOs }">
				<c:set var="nooflinerem" value="${noofline-saleDetailsDTOs.size()}"></c:set>
				 <c:forEach items="${saleDetailsDTOs}" var="saleDetail" varStatus="loop">
					  <tr style="border:0px white">
					  	<td style="text-align:center"> ${loop.index+1} </td>
					    <td>${saleDetail.itemName} </td>
					    <td style="text-align:center"> <b> ${saleDetail.hsnCode} </b> </td>
					    <td style="text-align:center">${saleDetail.packQty}  </td>
					    <td style="text-align:right"> <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${saleDetail.rate}" /> </td>
					    <td style="text-align:right"> <fmt:formatNumber value="${saleDetail.mrp}" currencySymbol="" type="currency" /> </td>
					    <td style="text-align:right"> <fmt:formatNumber value="${saleDetail.discPer}" currencySymbol="" type="currency" /> </td>
					    <td style="text-align:right"> <fmt:formatNumber value="${saleDetail.taxableAmount}" currencySymbol="" type="currency" /> </td>
					  </tr>
				  </c:forEach>
				 </c:if> 
				 <!-- <tr style="border:0px white">
				  	<td style="text-align:center"> 1 </td>
				    <td>HYBRID - M 1002 </td>
				    <td style="text-align:center"> <b> 9003 </b> </td>
				    <td style="text-align:center"> 1 </td>
				    <td style="text-align:right"> 260.00 </td>
				    <td style="text-align:right"> 260.00 </td>
				    <td style="text-align:right"> 0 </td>
				    <td style="text-align:right"> 260.00 </td>
				  </tr> -->
				  <tr>
				  	<th colspan="4" rowspan="2" style="text-align:left;padding: 12px 5px;font-size:17px" valign="top"> Rupees In Word : <b style="font-weight:500"> 
				  	  <span id="totInvValueWord" style="text-align: left; white-space: nowrap; font-weight: normal;"> </span>
				  	  </b> </th>
				    <th style="font-size:17px;font-weight:700;border-right:0px;text-align:left;padding-left:30px">Gross: </th>
				    <th style="font-size:17px;font-weight:700;border-left:0px;text-align:right"> <b> ${saleHeaderDTO.grossAmount} </b> </th>
				    <th style="font-size:17px;font-weight:700;border-right:0px;;text-align:left;padding-left:30px"> Disc. </th>
				    <th style="font-size:17px;font-weight:700;border-left:0px;text-align:right"> ${saleHeaderDTO.discAmount} </th>
				  </tr>
				  <tr>
				    <th style="font-size:17px;font-weight:700;border-right:0px;text-align:left;padding-left:30px">Adjustment: </th>
				    <th style="font-size:17px;font-weight:700;border-left:0px;text-align:right"> <b> ${saleHeaderDTO.roundoff}</b> </th>
				    <th style="font-size:17px;font-weight:700;border-right:0px;;text-align:left;padding-left:30px"> Net: </th>
				    <th style="font-size:17px;font-weight:700;border-left:0px;text-align:right">  <span id="totInvValue" style="text-align: left; font-weight: bold;"> <fmt:formatNumber value="${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount}" currencySymbol="" type="currency" groupingUsed="false" />
						</span></th>
				  </tr>
            </table>


            <!-- ............................last part.............................. -->

            <div style="width:100%;display:flex;margin-top:20px">
            	 <div style="width:60%">
            	 	 <h5> <b> Terms & Conditions : </b> </h5>
            	 	 <div style="margin-left:10px;font-size:15px">
            	 	 	 <p> @ Company is not liable if not taken within 1 month </p>
            	 	 	 <p> @ Metal frames are not guaranted </p>
            	 	 	 <p> @ Working hrs : 10.00 A.M to 9.30 P.M, <b> Sunday Closed </b> </p>
            	 	 </div>
            	 </div>
            	 <div style="width:10%"> </div>
            	 <div style="width:30%"> 
            	 	<h5> <b> For Jeevan Suraksha </b> </h5>

            	 	<h5 style="margin-top:80px"> <b> Authorised Signatory </b> </h5>
            	 </div>
            </div>
             

      </div>
     <div style="width:5%"> </div>
     <input type="hidden" id="backUrl" value="${backUrl}" />
    </div>
   <script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.8.3.min.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/bill_setup.js"></script>
	<script type="text/javascript">
		var BASE_URL = "${pageContext.request.contextPath}";
	</script>
</body>
</html>
