<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.sharobi.yewpos.hr.model.DutyShift"%>
<%@page import="java.util.List" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.google.gson.Gson"%>
<%@page import=" com.sharobi.yewpos.login.model.LoginInfoByUserDTO"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



   
  
    <!-- BOOTSTRAP CORE STYLE  -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME ICONS  -->
    <link href="${pageContext.request.contextPath}/assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE  -->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/customstyle.css" rel="stylesheet" />
    <link rel="icon" id="favicon" href="${pageContext.request.contextPath}/assets/images/title/fb.png">
    
 <%--  <link href="${pageContext.request.contextPath}/assets/css/bootstrap/datepicker.css" rel="stylesheet" /> --%> 


<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.1.min.js"></script>
    
    
    <!-- Core files For Using Jalert -->
	<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery.alerts.js"></script>
	<link href="${pageContext.request.contextPath}/assets/css/jquery.alerts.css"	rel="stylesheet" />
    
    
    
   <%--  <script src="${pageContext.request.contextPath}/assets/js/jAlert-functions.js"></script> --%>
  <script src="${pageContext.request.contextPath}/assets/js/moment/moment.js"></script>
  
  <script src="${pageContext.request.contextPath}/assets/js/jquery/jquery.timepicker.min.js"></script> 
 <link href="${pageContext.request.contextPath }/assets/css/jquery.timepicker.min.css" rel="stylesheet"> 
 <link href="${pageContext.request.contextPath}/assets/css/bootstrap/datepicker.css"	rel="stylesheet" />
 <script src="${pageContext.request.contextPath}/assets/js/bootstrap/bootstrap-datepicker.js"></script>
        
    <%-- <script src="${pageContext.request.contextPath}/assets/js/bootstrap-datepicker.min.js"></script> --%>
    
   <%--  <link href="${pageContext.request.contextPath }/assets/css/bootstrap/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap/bootstrap-datetimepicker.min.js"></script>   --%>
      
     <%--  <link href="${pageContext.request.contextPath}/assets/css/bootstrap/datepicker.css"	rel="stylesheet" />
      <script src="${pageContext.request.contextPath}/assets/js/bootstrap/bootstrap-datepicker.js"></script> --%>
    
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css"> -->
    
    
    
   
<!-- <style>
.ui-timepicker-standard{
z-index:9999!important
}
</style> -->
 
        

  
    <%-- <jsp:include page="/pages/common/header.jsp"></jsp:include> --%>
    
        <div class="row panel-trx panel-default">
           <%-- <jsp:include page="/pages/admin/admleftpanel.jsp"></jsp:include>   --%>
           
           <%-- <input type="text" value="${loggedinUser.id}"/> --%>
           <section class="wrapper">
         <div class="col-md-12 col-sm-12 ">
					<div class="col-md-12 col-sm-12" align="center">
						
							<div id="content" align="center">
							
							 <table class="table table-striped table-bordered table-hover hide  " id="employeeleavedetailstable">
                                    <thead  id="employeeleavedetailstablethead">
                                            <tr id="theadrow" class="text-nowrap">
                                            <th>Employee Name</th>
                                            <th>January</th>
                                            <th>February</th>
                                            <th>March</th>
                                            <th>April</th>
                                            <th>May</th>
                                            <th>June</th>
                                            <th>July</th>
                                            <th>August</th>
                                            <th>September</th>
                                            <th>October</th>
                                            <th>November</th>
                                            <th>December</th>
                                            <th>Total<br>Leave</th>
                                            <th>Alloted<br>Leave</th>
                                            <th>Leave<br>Balance</th>
                                            
                                            
                                            
                                           </tr>
                                                                                  
                                   
                                    </thead>
                                    <tbody id="employeeleavedetailstabletbody">
                                    	
                                    </tbody>
                                </table>
							 <div id="exportbuttondiv" class="hide">
                                <a  id="employeeleavedetailsexport" class="btn btn-success customexportbutton"><spring:message code="invitemstock.jsp.export" text="EXPORT" /></a>
                                </div>
							</div>
							
                                    </div>
                                

						 
				</div>
                	
            </div>
          
    </div>
   </section>
    <!-- CONTENT-WRAPPER SECTION END-->
    
    <!-- FOOTER SECTION END-->
    <!-- JAVASCRIPT AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
   <%--  <script src="${pageContext.request.contextPath}/assets/js/adminScript.js"></script> --%>
    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/ajax/ajax.js"></script>
    <!-- CORE JQUERY SCRIPTS -->
  <%--   <script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-1.9.1.min.js"></script> --%>
    <!-- BOOTSTRAP SCRIPTS  -->
   <script src="${pageContext.request.contextPath}/assets/js/bootstrap/bootstrap.min.js"></script>
   <%-- <script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-ui.js"></script> --%>
   <%-- <script src="${pageContext.request.contextPath}/assets/js/bootstrap/bootstrap-datepicker.js"></script> --%>
 <%--  <script src="${pageContext.request.contextPath}/assets/js/jquery/jquery.timepicker.min.js"></script> 
  <link href="${pageContext.request.contextPath}/assets/css/jquery.timepicker.min.css" rel="stylesheet"> --%>
   <script src="${pageContext.request.contextPath}/assets/js/table2excel/table2excel.js"></script>
  
  
  
    
   <script>
   
 

   </script>
   
   
   

   <script type="text/javascript">
   
   	
   
   var BASE_URL="${pageContext.request.contextPath}";

   var language = '<%=session.getAttribute("language") %>';
   
     
	  <%  
	  String responseString ="";
      Gson gson =new Gson();
      LoginInfoByUserDTO obj=(LoginInfoByUserDTO)session.getAttribute("sesloggedinUser"); 
    responseString =gson.toJson(obj);
	  String today ="";
	  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      today = formatter.format(new Date());
    %>
	   
	   var customerstring ='<%=responseString%>'
	   var customer=JSON.parse(customerstring);
	   var todaydate='<%=today%>'
	   //console.log("TODAY DATE IN BODY SCRIPT TAG IS:");
	   //console.log(todaydate);
	   console.log("CUSTOMER IN BODY SCRIPT TAG IS:");
	   console.log(customerstring);
	   console.log("ID IS:"+customer.id); 
  
	   <%  
		  String responseString2 ="";
	      Gson gson2 =new Gson();
	      List<DutyShift> obj2=(List<DutyShift>)session.getAttribute("dutyShiftList"); 
	    responseString2 =gson2.toJson(obj2);
	    %>
	    
	   var dutyshiftlistString='<%=responseString2%>'
	   var dutyshiftlist=JSON.parse(dutyshiftlistString); 
	   var companyId="${sessionScope.sesloggedinUser.id}"
		   var storeID="${sessionScope.sesloggedinUser.storeId}"
		   var dutyShiftList='<%=session.getAttribute("dutyShiftList")%>'   

   
  // var detmg="${pageContext.request.contextPath}/assets/images/admin/g/g_details.png";
   //var deleteimg="${pageContext.request.contextPath}/assets/images/admin/g/g_delete.png";
   //var editimg="${pageContext.request.contextPath}/assets/images/admin/g/g_edit.png";
   var contextpath="${pageContext.request.contextPath}";
  var dutyShiftList='<%=session.getAttribute("dutyShiftList")%>'
  var dutyShiftList2="${sessionScope.dutyShiftList}";
  var now = new Date();
  
  var weekStart = moment().clone().startOf('week');
  var currentmonday=moment(weekStart, "YYYY-MM-DD").add('days',1).format("YYYY-MM-DD");
  //console.log("WEEKSTART ARRAY IN SHIFT SCHEDULE JSP IS:");
  //console.log(weekStart);
  console.log("CURRENT WEEK MONDAY IN SHIFT SCHEDULE JSP IS:");
  console.log(currentmonday);
    
 $(document).ready(function(){
 
 

  
	   
	   
	 
 
 });


	   
	
 
 
   </script>	
   
   
      

    
 <script src="${pageContext.request.contextPath}/assets/js/hr/employeeleavedetails.js"></script>
   
                            
  

