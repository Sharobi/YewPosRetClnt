<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/moment/moment.js"></script>
  
 <script src="${pageContext.request.contextPath}/assets/js/jquery/jquery.timepicker.min.js"></script> 
 <link href="${pageContext.request.contextPath }/assets/css/jquery.timepicker.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">  
  
   
 
     
   
    
 <style>
.ui-timepicker-standard{
z-index:9999!important
}
</style> 


<c:set var="today" value="<%=new java.util.Date()%>" />

<section class="wrapper">
 
	<div class="row">
		<div class="col-lg-12">

			<p>
				<%-- <spring:message code="customer.jsp.title" text="Customer..." /> --%>
			</p>
			<div class="pull-right">
			
				<c:if test="${menuByUserDTO.isAll==1}">
					<button class="btn btn-primary" type="button" onclick="showndutyshiftaddModal()">
						<i class="fa fa-plus"></i>
						<spring:message code="cmn.jsp.btn.add" text="Add" />
					</button>
				</c:if>
			</div>
           
			<table id="dutyshifttable" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
                                               <th class="hide"> 
                                                    <spring:message code="hr.department.jsp.id" text="ID" />
                                                </th>
                                            
                                            
                                            <th > 
                                                    <spring:message code="hr.dutyshift.jsp.starttime" text="START TIME" />
                                            </th>
                                            <th > 
                                                    <spring:message code="hr.dutyshift.jsp.endtime" text="END TIME" />
                                            </th>
                                            <th > 
                                                    <spring:message code="hr.dutyshift.jsp.shiftno" text="SHIFT NO" />
                                            </th>
                                            <th > 
                                                    <spring:message code="hr.dutyshift.jsp.shiftname" text="SHIFT NAME" />
                                            </th>
                                            <th > 
                                                     <spring:message code="hr.designation.jsp.edit" text="EDIT" />
                                            </th>
                                            <th> 
                                                     <spring:message code="hr.designation.jsp.delete" text="DELETE" />
                                            </th>
                                            <th >   
                                                    <spring:message code="hr.designation.jsp.details" text="DETAILS" />
                                            </th>         
                                            </tr>                                        
				</thead>
				<tbody>
					<c:if test="${!empty dutyShiftList && dutyShiftList!=null}">
						<c:forEach items="${dutyShiftList}" var="dutyshift">
							<tr>
								<td class="hide">${dutyshift.id}</td> 
								<td> ${dutyshift.fromTime} </td>
								<td>${dutyshift.toTime}   </td>
								<td>${dutyshift.shiftingNo} </td>
								<td>${dutyshift.shiftName}  </td>
								
								<td><c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button" style="text-transform: uppercase; font-size: 11px;" onclick="javascript:showndutyshifteditModal(${dutyshift.id},${dutyshift.storeId})">
											<i class="fa fa-pencil"></i>
											<spring:message code="cmn.jsp.btn.edit" text="Edit" />
										</button>
									</c:if>
									</td>
									<td>
									<c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" style="font-size: 11px;" type="button" onclick="showDutyshiftDeleteModal(${dutyshift.id},${dutyshift.storeId})">
											<i class="fa fa-trash-o "></i>
											<spring:message code="cmn.jsp.tblhdr.del" text="Del" />
										</button>
									</c:if>
									</td>
									
									<td>
                              <c:if test="${menuByUserDTO.isAll==1}">
                                   <a href="#"  onclick="javascript:showDetailDutyshiftModal(${dutyshift.id},${dutyshift.storeId})">
											<i class="glyphicon glyphicon-info-sign"></i>
											
										</a>
                                  </c:if>										
                                      </td>	
									
									
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</section>


  <!--DUTY SHIFT ADD MODAL START -->
           					<div class="modal fade" data-backdrop="static" id="dutyshiftAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog" >
                                    <div class="modal-content" >
                                        <div class="modal-header" >
                                            <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
                                            <h4 class="modal-title" ><spring:message code="hr.dutyshift.jsp.addDutyshift" text="Add DutyShift" /></h4>
                                        </div>
                                        <div class="modal-body" >
                                        	<div >
                                            	<table>
                                            		
                                            		<tr>
                                            			<td><spring:message code="hr.dutyshift.jsp.starttime" text="START TIME" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="text"  class="form-control-trx" style="margin-bottom: 3px;" placeholder="HH:MM" id="adddutyshiftStarttime" />
                                            			     </td>
                                            			<td id="errorstarttime" style="color:red;font-size:14px">  </td>     
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.dutyshift.jsp.endtime" text="END TIME" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="text" class="form-control-trx" style="margin-bottom: 3px;" placeholder="HH:MM"  id="adddutyshiftEndtime"  /></td>
                                            			<td id="errorendtime" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.dutyshift.jsp.shiftno" text="SHIFT NO" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="text" class="form-control-trx" style="margin-bottom: 3px;"  id="adddutyshiftNo"/></td>
                                            			<td id="errorshiftno" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.dutyshift.jsp.shiftname" text="SHIFT NAME" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="text" class="form-control-trx" style="margin-bottom: 3px;"  id="adddutyshiftName"/></td>
                                            			<td id="errorshiftname" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		
                                            		
                                            	</table>
                                           		<div style="text-align: center;color: #F60;font-size: 12px;font-weight: bold;" id="addalertMsg"></div>
                                            </div>
                                        </div>
                                        <div class="modal-footer" >
                                            <button type="button"   class="btn btn-warning" data-dismiss="modal"><spring:message code="admin.menumgnt.jsp.cancel" text="CANCEL" /></button>
                                            <button type="button" onclick="javascript:addDutyshift()"   class="btn btn-success"><spring:message code="admin.menumgnt.jsp.create" text="CREATE" /></button>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                           <!--DUTY SHIFT ADD MODAL END -->
                            
                            
               <!-- DUTY SHIFT EDIT MODAL START -->
                            
                            <div class="modal fade" data-backdrop="static" id="dutyShiftEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog" style="margin: 100px auto;">
                                    <div class="modal-content" >
                                        <div class="modal-header" >
                                            <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
                                            <h4 class="modal-title" style="font-size: 18px;font-weight: bold;" id="myModalLabel"><spring:message code="hr.dutyshift.jsp.editDutyshift" text="Edit DutyShift" /></h4>
                                        </div>
                                        <div class="modal-body" >
                                        	<div style="text-align: left;font-size: 20px;">
                                        	<input type="hidden" id="modeditdesignationid" value="">
                                            	<table>
                                            	     <tr style="display:none">
                                            			
                                            			<td style="margin-bottom: 3px;"><input type="text" id="editdutyshiftId" class="form-control-trx" style="margin-bottom: 3px;"  /></td>
                                            			
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.dutyshift.jsp.starttime" text="START TIME" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="text" id="editdutyshiftStarttime" class="form-control-trx" style="margin-bottom: 3px;"  /></td>
                                            			<td id="editerrorstarttime" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.dutyshift.jsp.endtime" text="END TIME" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="text"  id="editdutyshiftEndtime" class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			<td id="editerrorendtime" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.dutyshift.jsp.shiftno" text="SHIFT NO" /></td>
                                            			<td>:</td>
                                            			<td><input type="number"  id="editdutyshiftShiftno"  class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			<td id="editerrorshiftno" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="hr.dutyshift.jsp.shiftname" text="SHIFT NAME" /></td>
                                            			<td>:</td>
                                            			<td><input type="text"  id="editdutyshiftShiftname" class="form-control-trx" style="margin-bottom: 3px;"  /></td>
                                            			<td id="editerrorshiftname" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                       		
                                            	</table>
                                           		<div style="text-align: center;color: #F60;font-size: 12px;font-weight: bold;" id="editalertMsg"></div>
                                            </div>
                                        </div>
                                        <div class="modal-footer" >
                                            <button type="button" onclick="javascript:cancelEmployeetype()"  class="btn btn-warning" data-dismiss="modal"><spring:message code="admin.menumgnt.jsp.cancel" text="CANCEL" /></button>
                                            <button type="button" onclick="javascript:editDutyshift()"  class="btn btn-success"><spring:message code="admin.menumgnt.jsp.edit" text="EDIT" /></button>   
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <!-- DUTY SHIFT EDIT MODAL END -->
                            
                            







                            
                  <!-- DUTY SHIFT DETAIL MODAL START -->
                             <div class="modal fade" id="dutyshiftDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
                           
                                <div class="modal-dialog" style="width: 836px;">
                                    <div class="modal-content">
                                        <div class="modal-header" >
                                            <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
                                            <h4 class="modal-title" style="font-size: 18px;font-weight: bold;" id="myModalLabel"><spring:message code="hr.dutyshift.jsp.dutyshiftdetails" text="DutyShift Details" /></h4>
                                        </div>
                                        <div class="modal-body">
                                        	<div align="center" style="font-size: 20px;">
                                            	<table>
                                            		<tr class="hide">
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.id" text="ID" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="dutyshiftdetailid">  </td>
                                            		</tr>
                                            		<tr class="hide">
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.storeID" text="STORE ID" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="dutyshiftdetailstoreid"></td>
                                            		</tr>
                                            		
                                            		<tr>
                                            			<td style="padding: 5px 0px;"><spring:message code="hr.dutyshift.jsp.starttime" text="START TIME" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="dutyshiftdetailstarttime"></td>
                                            		</tr>
                                            		<tr>
                                            			<td style="padding: 5px 0px;"><spring:message code="hr.dutyshift.jsp.endtime" text="END TIME" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="dutyshiftdetailendtime"></td>
                                            		</tr>
                                            		<tr>
                                            			<td style="padding: 5px 0px;"><spring:message code="hr.dutyshift.jsp.shiftno" text="SHIFT NO" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="dutyshiftdetailshiftingNo"></td>
                                            		</tr>
                                            		<tr>
                                            			<td style="padding: 5px 0px;"><spring:message code="hr.dutyshift.jsp.shiftname" text="SHIFT NAME" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="dutyshiftdetailshiftName"></td>
                                            		</tr>
                                            		
                                            		
                                            	</table>
                                           		<div style="text-align: center;color: #F60;font-size: 12px;font-weight: bold;" id="addalertMsg"></div>
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
                            
                            
                       <!-- DUTY SHIFT DETAIL MODAL END -->           
                            
                            
                             <!-- Designation Delete Modal Start -->
                            
                            <div class="modal fade" data-backdrop="static" id="confirmdeleteDutyshiftModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog" >
                                    <div class="modal-content" >
                                        <div class="modal-header" >
                                            
                                            <h4 class="modal-title" style="font-size: 18px;font-weight: bold;" id="myModalLabel"><spring:message code="admin.menumgnt.jsp.confirmation" text="Confirmation!" /></h4>
                                        </div>
                                        <div class="modal-body">
                                        	<div style="text-align: center;font-size: 20px;">
                                            	<spring:message code="admin.menumgnt.jsp.areYouSure" text="Are you sure?" />
                                            	<input type="hidden" id="moddeleteconfirmdutyshiftid" value="">
                                            	<input type="hidden" id="moddeleteconfirmdutyshiftstoreid" value="">
                                            	<!-- <input type="hidden" id="moddeleteconfirmcatbgcolorContId" value=""> -->
                                            </div>
                                        </div>
                                        <div class="modal-footer" >
                                        <button type="button" class="btn btn-default" data-dismiss="modal">
					                    <spring:message code="admin.menumgnt.jsp.cancel" text="Cancel" />
				                        </button>
                                            
                                            <button type="button" onclick="javacsript:deleteDutyshift()"  data-dismiss="modal" class="btn btn-success"><spring:message code="admin.menumgnt.jsp.ok" text="OK" /></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                              <!-- Designation Delete Modal End -->  
         
                            

<!-- ALERTCATDATAOP MODAL BEGINS HERE -->
<div class="modal fade" id="alertcatdataopModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 836px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					Alert!
				</h4>
			</div>
			<div class="modal-body">
				
             <div id="catdataopmassagecont">
             </div>		
				
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button"  onclick="location.href='${pageContext.request.contextPath}/hr/loaddutyshift.htm'" data-dismiss="modal" class="btn btn-success"><spring:message code="admin.menumgnt.jsp.ok" text="OK" /></button>
			</div>
		</div>
	</div>
</div>
<!-- ALERTCATDATAOP MODAL ENDS HERE -->
			
 			
<!--/wrapper -->
<%--  <script src="${pageContext.request.contextPath }/assets/js/pos/customer/customer.js"></script>  --%>
 <script src="${pageContext.request.contextPath }/assets/js/hr/hradminScript.js"></script> 
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/pos/customer/customer_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
 
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}"
var countryId = "${sessionScope.sesloggedinUser.countryId}";
var stateId = "${sessionScope.sesloggedinUser.stateId}";
var companyId="${sessionScope.sesloggedinUser.id}"
var storeID="${sessionScope.sesloggedinUser.storeId}"
 function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function showCustomerDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 
$(document).ready(function() {
    $('#dutyshifttable').DataTable({
    	"lengthChange": false
    	
    });
    
   
       $("#adddutyshiftStarttime").timepicker({
    	
    	timeFormat:"HH:mm"
    }); 
     $("#adddutyshiftEndtime").timepicker({
    	timeFormat:"HH:mm"
    });   
     
     $("#editdutyshiftStarttime").timepicker({
     	
     	timeFormat:"HH:mm"
     }); 
      $("#editdutyshiftEndtime").timepicker({
     	timeFormat:"HH:mm"
     });   
   
    
    

}); 




</script>
