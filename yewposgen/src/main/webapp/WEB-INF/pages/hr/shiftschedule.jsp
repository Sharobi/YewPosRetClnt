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
    <%-- <script src="${pageContext.request.contextPath}/assets/js/bootstrap-datepicker.min.js"></script> --%>
    <%--  <script src="${pageContext.request.contextPath}/assets/js/bootstrap/bootstrap-datetimepicker.min.js"></script>  
    <link href="${pageContext.request.contextPath }/assets/css/bootstrap/bootstrap-datetimepicker.min.css" rel="stylesheet"> --%>
   
    
     <%--  <link href="${pageContext.request.contextPath}/assets/css/bootstrap/datepicker.css"	rel="stylesheet" />
      <script src="${pageContext.request.contextPath}/assets/js/bootstrap/bootstrap-datepicker.js"></script> --%>
    
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css"> -->
    
      <link href="${pageContext.request.contextPath}/assets/css/bootstrap/datepicker.css"	rel="stylesheet" />
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap/bootstrap-datepicker.js"></script>
    
   
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
					<!-- 	<span class="spanclass">Select Date For Employee Shift</span>&nbsp;&nbsp; -->

						      	<span class="">Select Date For Employee Shift</span>&nbsp;&nbsp;
								
								<span class="">From Date:</span>
								<input type="text" id="fromdate"  size="7" value="">
								
								<span class="">To Date:</span>
								<input type="text" id="todate"  size="7" value="">
								 <c:if test="${! empty dutyShiftList }">
								     
						             <select id="selectdutyshiftlist" class="hide "  >
                                 	 <option value=0> Please Select Shift</option>
                                 	<c:forEach items="${dutyShiftList}" var="dutyshift">
                                 	 <option value="${dutyshift.shiftingNo}"> ${dutyshift.shiftName}</option>
                                 	</c:forEach>
                                    </select>   
                                    	
                                </c:if>
                                
							    
                               <span class="spanclass">Select Slot Interval:</span>
                                <select id="slotinputselect" class=" customslotinputselect" >
                                <option selected="selected" >7</option>
                                <option>14</option>
                               <!--  <option>21</option>
                                <option>28</option> -->
                                </select>
								
							
							<button id="getallemployeeshiftinfo" class="btn btn-success"
							><spring:message
								code="cmn.jsp.btn.submit" text="Submit" /></button>

							<div id="content" align="center" style="margin-top:20px;">
							
							 <table class="table table-striped table-bordered table-hover hide" id="employeeshifttable">
                                    <thead  id="employeeshifttablethead">
                                            <tr id="theadrow" class="text-nowrap">
                                           
                                           </tr>
                                                                                  
                                   
                                    </thead>
                                    <tbody id="employeeshifttabletbody">
                                    	
                                    </tbody>
                                </table>
                                <div id="exportbuttondiv" class="hide">
                                <a  id="shiftscheduleexcelexport" class="btn btn-success customexportbutton"><spring:message code="hr.shiftschedule.export" text="EXPORT" /></a>
                                </div>
							
							</div>
							<!--EMPLOYEE SHIFT SCHEDULE MODAL START -->
           					<div class="modal fade" data-backdrop="static" id="employeeshiftmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog" >
                                    <div class="modal-content" >
                                        <div class="modal-header" >
                                            <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
                                            <h4 class="modal-title" id="myModalHeading" ><spring:message code="hr.shiftschedule.jsp.addshiftschedule" text="Add Shift Schedule" /></h4>
                                        </div>
                                        <div class="modal-body" >
                                        	<div >
                                            	<table>
                                            		
                                            		<tr>
                                            			<td><spring:message code="hr.shiftschedule.fromdate" text="FROM DATE" /></td>
                                            			<td>:</td>
                                            			<td id="fromdatetdtag" style="margin-bottom: 3px;"><input type="text" style="margin-bottom: 10px;" class="form-control-trx" placeholder="FromDate" id="fromdateinmodal" />
                                            			     </td>
                                            			<!-- <td id="errorstarttime" style="color:red;font-size:14px">  </td>    -->  
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.shiftschedule.todate" text="TO DATE" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;" id="todatetdtag"><input type="text" style="margin-bottom: 10px;" class="form-control-trx" placeholder="ToDate" id="todateinmodal"  /></td>
                                            			<!-- <td id="errorendtime" style="color:red;font-size:14px">  </td> -->
                                            		</tr>
                                            		<tr>
                                            			<td class="hide"><spring:message code="hr.shiftschedule.jsp.shiftscheduleid" text="SHIFT SCHEDULE ID" /></td>
                                            			<td class="hide">:</td>
                                            			<td style="margin-bottom: 3px;" id="shiftscheduletdtag" class="hide"><input type="text"  style="margin-bottom: 10px;" class="form-control-trx" placeholder="ShiftScheduleId" id="shiftscheduleidinmodal"/></td>
                                            			<!-- <td id="errorshiftno" style="color:red;font-size:14px">  </td> -->
                                            		</tr>
                                            		
                                            		<tr>
                                            			<td class="hide"><spring:message code="hr.empdetails.jsp.details" text="EMPLOYEE ID" /></td>
                                            			<td class="hide">:</td>
                                            			<td style="margin-bottom: 3px;" id="empidtdtag"  class="hide"><input type="text"  style="margin-bottom: 10px;" class="form-control-trx"  placeholder="EmployeeId" id="empid"/></td>
                                            			
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.dutyshift.jsp.shiftname" text="SHIFT NAME" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;">
                                            			<c:if test="${! empty dutyShiftList }">
						                             <select id="selectdutyshiftlistinmodal" style="margin-bottom: 10px;" class="form-control-trx" >
                                 	               <option value=0>Select Shift</option>
                                 	               <c:forEach items="${dutyShiftList}" var="dutyshift">
                                 	               <option value=${dutyshift.id}> ${dutyshift.shiftName}</option>
                                 	              </c:forEach>
                                                  </select>   
                                    	
                                                     </c:if>
                                            		</td>	
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.shiftschedule.fromtime" text="FROM TIME" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;" id="todatetdtag"><input type="text" class="form-control-trx" style="margin-bottom: 10px;" placeholder="HH:MM" id="empshiftfromtime"  /></td>
                                            			<!-- <td id="errorendtime" style="color:red;font-size:14px">  </td> -->
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.shiftschedule.totime" text="TO TIME" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;" id="todatetdtag"><input type="text" class="form-control-trx" style="margin-bottom: 10px;" placeholder="HH:MM" id="empshifttotime"  /></td>
                                            			<!-- <td id="errorendtime" style="color:red;font-size:14px">  </td> -->
                                            		</tr>
                                            		
                                            		
                                            	</table>
                                           		<div style="text-align: center;color: #F60;font-size: 12px;font-weight: bold;" id="addalertMsg"></div>
                                            </div>
                                        </div>
                                        <div class="modal-footer" id="employeeshiftmodalfooter">
                                            <button type="button" id="cancelshiftshedule"   class="btn btn-warning" data-dismiss="modal"><spring:message code="admin.menumgnt.jsp.cancel" text="CANCEL" /></button>
                                            <button type="button" id="addshiftshedule"  class="btn btn-theme"><spring:message code="admin.menumgnt.jsp.create" text="CREATE" /></button>
                                            <button type="button" id="closeshiftshedule"  class="btn btn-default"><spring:message code="cmn.jsp.btn.close" text="CLOSE" /></button>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                           <!--EMPLOYEE SHIFT SCHEDULE MODAL END -->
					

						  <div class="modal fade" data-backdrop="static" id="alertmessagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog" style="margin: 100px auto;">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                           
                                            <h4 class="modal-title"><spring:message code="footer.jsp.alert" text="Alert!" /></h4>
                                        </div>
                                        <div class="modal-body">
                                        	<div style="text-align: center;font-size: 20px;">
                                            <div id="alertmodalbody"></div>
                                            </div>
                                        </div>
                                        <div class="modal-footer" >
                                            <button type="button"  onclick="location.href='${pageContext.request.contextPath}/hr/loadshiftschedule.htm'" data-dismiss="modal" class="btn btn-success"><spring:message code="admin.menumgnt.jsp.ok" text="OK" /></button>
                                        </div>
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
   
   var companyId="${sessionScope.sesloggedinUser.id}"
   var storeID="${sessionScope.sesloggedinUser.storeId}"
   var dutyShiftList='<%=session.getAttribute("dutyShiftList")%>'
	   $("#employeeshifttabletbody").find("td:eq(1)").prop("disabled",true);
      
   //var dutyShiftList2='${sessionScope.dutyShiftList}';
   $("#getallemployeeshiftinfo").click(function(){
	   gettablebodyandheaddesign();
	   showallemployeesshiftschedulesintable();
   });
   $(document).on("click","#employeeshifttabletbody>tr>td.cursorpointer",function(){
	   var elem=this;
	   var empname=$(this).parent().find("td:eq(0)").html();
	   var index=$(this).index();
	   var chosenfromdate=$("#theadrow").find("th:eq("+index+")").html().split(" ")[0];
	   var headertextforaddempshiftschedule="Add "+empname+"'s"+" Shift Schedule On "+chosenfromdate;
	   //alert(headertextforaddempshiftschedule);
	   var headertextforupdateempshiftschedule="Update "+empname+"'s"+" Shift Schedule On "+chosenfromdate;
	   $("#employeeshiftmodalfooter").find("span").remove();
	   $("#empshiftfromtime").removeClass("customdanger");
	   $("#empshifttotime").removeClass("customdanger");
	   $("#fromdateinmodal").removeClass("customdanger");
	   $("#todateinmodal").removeClass("customdanger");
	   $("#selectdutyshiftlistinmodal").removeClass("customdanger");
	   $("#fromdateinmodal").val(chosenfromdate);
	   $("#todateinmodal").val(chosenfromdate);
	   
	   var dutyshift_id=$(this).attr("id");
	   var shiftscheduleid=$(elem).find("a").attr("id");
	    console.log("SHIFT SCHEDULE ID FROM DATABASE IS:"+shiftscheduleid);
	    $("#shiftscheduleidinmodal").val(shiftscheduleid);
	   $("#empshiftfromtime").val("");
	   $("#empshifttotime").val("");
	   var emp_id=$(this).parent().attr("id");
	   $("#empid").val(emp_id);
	  
	   if(shiftscheduleid>0){
		   $("#addshiftshedule").html("UPDATE");
		   $("#cancelshiftshedule").removeClass("hide");
		   $("#myModalHeading").html(headertextforupdateempshiftschedule);
		   $("#fromdateinmodal").prop("disabled",true);
		   $("#todateinmodal").prop("disabled",true);
	   }else{
		   $("#addshiftshedule").html("SAVE");
		   $("#cancelshiftshedule").addClass("hide");
		   $("#myModalHeading").html(headertextforaddempshiftschedule);
		   $("#fromdateinmodal").prop("disabled",true);
		   $("#todateinmodal").prop("disabled",false);
	   }
	  
	   var timerange=$(elem).find("a").html();
	   if(timerange!="" && timerange!=null && timerange!="-"){
		   var fromtime=timerange.split("-")[0];
		   var totime=timerange.split("-")[1];
		   
		   $("#empshiftfromtime").val(fromtime);
		   $("#empshifttotime").val(totime);
		   $("#selectdutyshiftlistinmodal").val(dutyshift_id);
		   
	   }else{
		   $("#selectdutyshiftlistinmodal").val(0);
	   }
	   $("#employeeshiftmodal").modal("show");
	  
	   
   });
	   
	   
  
 
   
   function showallemployeesshiftschedulesintable(){
	   var tdindexes=[];
	   var tdindex=0;
	   var allempshiftslist=getAllEmployeeShiftSchedule();
	   var ths=$("#theadrow").find("th");
	  // console.log("ALL EMPLOYEE SHIFT SCHEDULE IS:");
	  //console.log(allempshiftslist);
	  
	  if(allempshiftslist!=null && allempshiftslist!="" && allempshiftslist.length>0){
		  for(var i=0;i<allempshiftslist.length;i++){
			  var empshiftobj=allempshiftslist[i];
			  var empshiftname=null;
			  var empid=empshiftobj.employee.id;
			  var empshiftscheduleid=empshiftobj.id;
			  var start= empshiftobj.fromDate;
			  var end= empshiftobj.toDate;
			  start = new Date(start);
			  end = new Date(end);
			  var daysarr=generatedaterange(start,end);
			
			  var empshiftnumber=empshiftobj.shiftingNo;
			  var empdutyShiftId=empshiftobj.dutyShiftId;
			  var dutyshiftoptions=$("#selectdutyshiftlist>option");
			
			  var row="tr#"+empid;
			  var sel="#employeeshifttabletbody>tr#"+empid;
			  var selectedemp=$(sel).html();
			 
			  
			  var startdate=$("#fromdate").val();
			  var todate=$("#todate").val();
			   console.log("DAYS ARRAY2 FOR GETTING ASSIGNED SHIFT VALUES OF EMPLOYEE IS:");
			   console.log(daysarr);
			   
			   var fromtime=empshiftobj.fromTime;
			   var totime=empshiftobj.toTime;
			 
			   for(var j=0;j<ths.length;j++){
				   
				   var o=$(ths[j]).html().split(" ")[0];
				   for(var k=0;k<daysarr.length;k++){
					   var day=daysarr[k];
					   if(o==day){
						   var td=$(sel).find("td:eq("+j+")");
				    		$(td).find("i").remove();
				    		/* $(td).attr("id",empshiftnumber); */
				    		$(td).attr("id",empdutyShiftId);
				    		var anchor="<a class='text-nowrap customanchor' id='"+empshiftscheduleid+"'"+">"+fromtime+"-"+totime+"</a>";
							td.append(anchor);
					   }
					   
					   
				   }
				   
				   
			   }  
			 
		  }
		 
		  $("#exportbuttondiv").removeClass("hide");
	  }
	 
	 
	   
   }
   
   function getCalendarDays(weekday){
	   var day=null;
	    if(weekday==0){
		   day="Sunday"
	   }else if(weekday==1){
		   day="Monday"
	   }else if(weekday==2){
		   day="Tuesday"
	   }else if(weekday==3){
		   day="Wednesday"
	   }else if(weekday==4){
		   day="Thursday"
	   }else if(weekday==5){
		   day="Friday"
	   }else if(weekday==6){
		   day="Saturday"
	   } 
	   return day;
   }
   
   function gettablebodyandheaddesign(){
	   $("#employeeshifttabletbody").empty();
	   $("#theadrow").empty();
	   <%-- var dutyshiftlist='<%=session.getAttribute("dutyShiftList")%>'; --%>
	   
	   
	  var start=$("#fromdate").val();
	  var end=$("#todate").val();
	   
	   var daysarr=generatedaterange(start,end);
	   var emplistresponse=getAllEmployees();
	   var head="";
	   head=head+'<th>NAME</th>'; 
	   var body='';
	   for(var i=0;i<daysarr.length;i++){
		   var obj=daysarr[i];
		   var t= new Date(obj);
		   var x=moment(t).format("YYYY-MM-DD");
		   var weekday=moment(x).weekday();
		   
		   //console.log("WEEKDAY=="+weekday);
		   
		   var day=getCalendarDays(weekday);
		   
		   if(day!=null){
			   head=head+'<th>'
			   head=head+obj+" "+"<br>"+day+'</th>'
		   }else{
			   head=head+'<th>'
			   head=head+obj+'</th>'
		   } 
	   }
	   $("#theadrow").append(head);
	   if(emplistresponse!=null && emplistresponse!=""){
		   $("#employeeshifttable").removeClass("hide");
		    if(dutyshiftlist==null || dutyshiftlist.length<=0){
		    	jAlert('Please Add DutyShift First.',
				'Error!');
			   $("#popup_ok")
				.click(
						function() {
							location.href = BASE_URL+"/hr/loaddutyshift.htm";
						});
		    }else{
		   for(var i=0;i<emplistresponse.length;i++){
			  
			   var empobj=emplistresponse[i];
			   
			   var empid=empobj.id;
			   var empname=empobj.name;
			   var st_id=empobj.storeId;
			   body=body+"<tr  id="+empid+">"
			   body=body+"<td class='default'>"
			   body=body+empname
			   body=body+"</td>"
			   
			   
			   for(var k=0;k<daysarr.length;k++){
					  /* body=body+"<td class='cursorpointer'><a class='text-nowrap customanchor'>-</a></td>" */
					  body=body+"<td class='cursorpointer'> <i class='fa fa-plus' style='color:#32CD32;'></i></td>"
					 
				}
			   
			 
			   
			   body=body+"</tr>" 
			   
			   
		      }
		    }
		    
		   $("#employeeshifttabletbody").append(body);
		   
	   }else{
		   
		   
		   jAlert('Please Add Employees First.',
			'Error!');
		   $("#popup_ok")
			.click(
					function() {
						location.href = BASE_URL+"/hr/loademp.htm";
					});
	   }
   }
   
   
  
  function generatedaterange(start,end){
	  var arr=[];
	  var start =  moment(start,"YYYY-MM-DD").format("YYYY-MM-DD");
	  var end =  moment(end,"YYYY-MM-DD").format("YYYY-MM-DD");
	  for(var i=start;i<=end;){
		  var t=i;
		  arr.push(i);
		  i=moment(t, "YYYY-MM-DD").add('days',1).format("YYYY-MM-DD");
	  }
	  return arr;
	  
  } 
   
   
   function validate(){
	   var is_valid=true;
	   var start=$("#fromdate").val();
	   var end=$("#todate").val();
	   
	   if(start==null || start==""){
		   $("#fromdate").attr("style","font-color:red");
		   //$("#fromdate").append("<span class='error'>Start Date is Required</span>");
		   is_valid=false;
	   }else if(end==null || end==""){
		   $("#todate").append("<span class='error'>To Date is Required</span>");
		   is_valid=false;
	   }
	   
	   return is_valid;
   }
   
   
   function getAllEmployees(){
	   var companyId="${sessionScope.sesloggedinUser.id}"
		var storeID="${sessionScope.sesloggedinUser.storeId}"
		var res=''
		//alert("STOREID IN getAllEmployees() FUNCTION IN shiftschedule.js FILE IS:  "+storeID);
		$
		.ajax({
			url : BASE_URL + "/hr/getAllEmployees.htm",
			type : 'GET',
			headers: {"storeId": storeID},
			async:false,
			contentType : 'application/json;charset=utf-8',
			success : function(response) {
				   res=JSON.parse(response);
				
			},
			error:function(error){
				console.log("ERROR IN GETTING ALL EMPLOYEES:");
				console.log(error);
				//alert("ERROR IN GETTING ALL EMPLOYEES");
			}
		});
		return res;
	}
 
   
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

   var storeID="${sessionScope.sesloggedinUser.storeId}";

   var companyId="${sessionScope.sesloggedinUser.id}"
   var detmg="${pageContext.request.contextPath}/assets/images/admin/g/g_details.png";
   var deleteimg="${pageContext.request.contextPath}/assets/images/admin/g/g_delete.png";
   var editimg="${pageContext.request.contextPath}/assets/images/admin/g/g_edit.png";
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
  $("#fromdate").val(currentmonday);
 $(document).ready(function(){
  $('#empshiftfromtime').timepicker({
 	timeFormat:"HH:mm"
 }); 
 $("#empshifttotime").timepicker({
 	timeFormat:"HH:mm"
 }); 
 

  $("#fromdate").datepicker( {
 	format : "yyyy-mm-dd",
 	autoclose:true,
 	beforeShowDay: function(date){ 
 		  var day = date.getDay(); 
 		 if(day==1){
 			 return true;
 		 }else{
 			 return false;
 		 }
 		 
 		}
 		
	   });  
	   
	   
	  /*  $("#fromdate").datepicker({
		   format : "yyyy-mm-dd",
	        autoclose: true,
	        startDate: currentmonday,
	        daysOfWeekDisabled: "0,2,3,4,5,6"
	    }); */
	   
	  /*  $("#fromdate").datepicker( {
		 	format : "yyyy-mm-dd",
		 	autoclose:true
	    });  */
 
 $("#fromdateinmodal").datepicker( {
	 	format : "yyyy-mm-dd",
	 	autoclose:true
    }); 
 $("#todateinmodal").datepicker( {
	 	format : "yyyy-mm-dd",
	 	autoclose:true
 }); 
 //$("#fromdate").val(currentmonday);
 var slot=$("#slotinputselect").val();
 var new_date = moment(currentmonday, "YYYY-MM-DD").add('days', slot-1).format("YYYY-MM-DD");
 $("#todate").val(new_date);
 $("#todate").prop("disabled",true);
 
 $("#getallemployeeshiftinfo").trigger("click");
 
 $("#fromdate").on("change",function(){
	 var slot=$("#slotinputselect").val();
	 var startdate=$("#fromdate").val();
	 var new_date = moment(startdate, "YYYY-MM-DD").add('days', slot-1).format("YYYY-MM-DD");
	 $("#todate").val(new_date);
	 $("#getallemployeeshiftinfo").trigger("click");
 });
 
 
 $("#slotinputselect").on("change",function(){
	 var startdate=$("#fromdate").val();
	 var slot=$("#slotinputselect").val();
	 var new_date = moment(startdate, "YYYY-MM-DD").add('days', slot-1).format("YYYY-MM-DD");
	 $("#todate").val(new_date);
	 $("#getallemployeeshiftinfo").trigger("click");
 });
 
 });


	   
	
 
 
   </script>	
   
   
      

    
 <script src="${pageContext.request.contextPath}/assets/js/hr/shiftSchedule.js"></script>
   
                            
  

