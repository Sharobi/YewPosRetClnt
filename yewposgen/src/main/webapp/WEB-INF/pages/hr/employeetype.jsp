<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet"> 

<c:set var="today" value="<%=new java.util.Date()%>" />

<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">

			<p>
				<%-- <spring:message code="customer.jsp.title" text="Customer..." /> --%>
			</p>
			<div class="pull-right">
				<c:if test="${menuByUserDTO.isAll==1}">
					<button class="btn btn-primary" type="button" onclick="showEmployeetypeaddModal()()">
						<i class="fa fa-plus"></i>
						<spring:message code="cmn.jsp.btn.add" text="Add" />
					</button>
				</c:if>
			</div>

			<table id="employeetypetable" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
                                            <th class="hide"> 
                                                      <spring:message code="hr.department.jsp.id" text="ID" />
                                            </th>
                                            <th>
                                                    <spring:message code="admin.menumgnt.jsp.employeetypeType" text="TYPE" />
                                            </th>
                                           
                                            <th > 
                                                    <spring:message code="admin.menumgnt.jsp.employeetypeCasual" text="CASUAL LEAVE" />
                                            </th>
                                            <th > 
                                                    <spring:message code="admin.menumgnt.jsp.employeetypeSick" text="SICK LEAVE" />
                                            </th>
                                            <th > 
                                                    <spring:message code="admin.menumgnt.jsp.employeetypeMisc" text="MISC NAME" />
                                            </th>
                                            
                                            <th>
                                                     <spring:message code="hr.department.jsp.edit" text="EDIT" />
                                            </th>
                                            <th> 
                                                     <spring:message code="hr.department.jsp.delete" text="DELETE" />
                                            </th>
                                            <th>   
                                                    <spring:message code="hr.department.jsp.details" text="DETAILS" />
                                            </th>
                                            </tr>                                        
				</thead>
				<tbody>
					<c:if test="${!empty employeeTypeList && employeeTypeList!=null}">
						<c:forEach items="${employeeTypeList}" var="emptype">
							<tr>
								<td class="hide">${emptype.id}</td> 
								<td>${emptype.type} </td>
								<td>${emptype.casualLeave} </td>
								<td>${emptype.sickLeave} </td>
								<td>${emptype.miscLeave} </td>
								
								<td><c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button" style="text-transform: uppercase; font-size: 11px;" onclick="javascript:shownEmployeetypeeditModal(${emptype.id},&quot;${emptype.storeId}&quot;)">
											<i class="fa fa-pencil"></i>
											<spring:message code="cmn.jsp.btn.edit" text="Edit" />
										</button>
									</c:if>
									</td>
									<td>
									<c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" style="font-size: 11px;" type="button" onclick="showEmployeetypeDeleteModal(${emptype.id},&quot;${emptype.storeId}&quot;)">
											<i class="fa fa-trash-o "></i>
											<spring:message code="cmn.jsp.tblhdr.del" text="Del" />
										</button>
									</c:if>
									</td>
									
									<td>
                              <c:if test="${menuByUserDTO.isAll==1}">
                                   <a href="#"  onclick="javascript:showDetailEmployeetypeModal(${emptype.id},&quot;${emptype.storeId}&quot;)">
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


  <!--EMPLOYEE TYPE ADD MODAL START -->
           					<div class="modal fade" data-backdrop="static" id="employeetypeAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog" >
                                    <div class="modal-content" >
                                        <div class="modal-header" >
                                            <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
                                            <h4 class="modal-title" ><spring:message code="admin.menumgnt.jsp.addEmployeeType" text="Add EmployeeType" /></h4>
                                        </div>
                                        <div class="modal-body" >
                                        	<div>
                                            	<table>
                                            		
                                            		<tr>
                                            			<td><spring:message code="admin.menumgnt.jsp.employeetypeType" text="TYPE" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="text" id="addemployeetypeType" class="form-control-trx" style="margin-bottom: 3px;" onchange="expiryCalculation($(this).val(),$('#purorderid').val(),$('#itemid').val());" />
                                            			     </td>
                                            			<td id="empaddErrortype" style="color:red;font-size:14px">  </td>     
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="admin.menumgnt.jsp.employeetypeCasual" text="CASUAL LEAVE" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="number" step="0.5" id="addemployeetypeCasual" class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			<td id="empaddErrorcasual" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="admin.menumgnt.jsp.employeetypeSick" text="SICK LEAVE" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="number" step="0.5" id="addemployeetypeSick" class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			<td id="empaddErrorsick" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="admin.menumgnt.jsp.employeetypeMisc" text="MISC LEAVE" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="number" step="0.5" id="addemployeetypeMisc" class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			<td id="empaddErrormisc" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		
                                            	</table>
                                           		<div style="text-align: center;color: #F60;font-size: 12px;font-weight: bold;" id="addalertMsg"></div>
                                            </div>
                                        </div>
                                        <div class="modal-footer" >
                                            <button type="button" onclick="javascript:cancelEmployeetype1()"  class="btn btn-warning" data-dismiss="modal"><spring:message code="admin.menumgnt.jsp.cancel" text="CANCEL" /></button>
                                            <button type="button" onclick="javascript:addEmployeetype()"   class="btn btn-theme"><spring:message code="admin.menumgnt.jsp.create" text="CREATE" /></button>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <!--EMPLOYEE TYPE ADD MODAL END -->
                            
                            
               <!-- EMPLOYEE TYPE EDIT MODAL START -->
                            
                            <div class="modal fade" data-backdrop="static" id="employeetypeEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog" style="margin: 100px auto;">
                                    <div class="modal-content" >
                                        <div class="modal-header" >
                                            <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
                                            <h4 class="modal-title" style="font-size: 18px;font-weight: bold;" id="myModalLabel"><spring:message code="hr.designation.jsp.editemployeetype" text="Edit Employeetype" /></h4>
                                        </div>
                                        <div class="modal-body" >
                                        	<div >
                                        	<input type="hidden" id="modeditdesignationid" value="">
                                            	<table>
                                            	     <tr style="display:none">
                                            			
                                            			<td style="margin-bottom: 3px;"><input type="text" id="editemployeetypeId" class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="admin.menumgnt.jsp.employeetypeType" text="TYPE" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="text" id="editemployeetypeType" class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			<td id="editerrorType" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="admin.menumgnt.jsp.employeetypeCasual" text="CASUAL LEAVE" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="number" step="0.5" id="editemployeetypeCasual" class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			<td id="editerrorCasual" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="admin.menumgnt.jsp.employeetypeSick" text="SICK LEAVE" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="number" step="0.5" id="editemployeetypeSick" class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			<td id="editerrorSick" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                            		<tr>
                                            			<td><spring:message code="admin.menumgnt.jsp.employeetypeMisc" text="MISC LEAVE" /></td>
                                            			<td>:</td>
                                            			<td style="margin-bottom: 3px;"><input type="number" step="0.5" id="editemployeetypeMisc" class="form-control-trx" style="margin-bottom: 3px;" /></td>
                                            			<td id="editerrorMisc" style="color:red;font-size:14px">  </td>
                                            		</tr>
                                       		
                                            	</table>
                                           		<div style="text-align: center;color: #F60;font-size: 12px;font-weight: bold;" id="editalertMsg"></div>
                                            </div>
                                        </div>
                                        <div class="modal-footer" >
                                            <button type="button" onclick="javascript:cancelEmployeetype()"  class="btn btn-warning" data-dismiss="modal"><spring:message code="admin.menumgnt.jsp.cancel" text="CANCEL" /></button>
                                            <button type="button" onclick="javascript:editEmployeetype()"  class="btn btn-theme"><spring:message code="admin.menumgnt.jsp.edit" text="UPDATE" /></button>   
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <!-- EMPLOYEE TYPE EDIT MODAL END -->
                            
                            







                            
                  <!-- Employee Type Detail Modal Start -->
                             <div class="modal fade" id="employeetypeDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
                           
                                <div class="modal-dialog" style="width: 836px;">
                                    <div class="modal-content">
                                        <div class="modal-header" >
                                            <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
                                            <h4 class="modal-title" style="font-size: 18px;font-weight: bold;" id="myModalLabel"><spring:message code="admin.menumgnt.jsp.typedetails" text="EMPLOYEE TYPE" /></h4>
                                        </div>
                                        <div class="modal-body">
                                        	<div align="center" style="font-size: 20px;">
                                            	<table>
                                            		<tr class="hide">
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.id" text="ID" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="employeetypedetailid">  </td>
                                            		</tr>
                                            		<tr class="hide">
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.storeID" text="STORE ID" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="employeetypestoreid"></td>
                                            		</tr>
                                            		
                                            		<tr>
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.employeetypeType" text="TYPE" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="employeetypedetailtype"></td>
                                            		</tr>
                                            		<tr>
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.employeetypeCasual" text="CASUAL LEAVE" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="employeetypedetailcasual"></td>
                                            		</tr>
                                            		<tr>
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.employeetypeSick" text="SICK LEAVE" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="employeetypedetailsick"></td>
                                            		</tr>
                                            		<tr>
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.employeetypeMisc" text="MICS LEAVE" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="employeetypedetailmics"></td>
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
                            
                            
                       <!-- Employee Type Detail Modal End -->                  
                            
                            
                            
         
                            

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
				<button type="button"  onclick="location.href='${pageContext.request.contextPath}/hr/loademptype.htm'" data-dismiss="modal" class="btn btn-success"><spring:message code="admin.menumgnt.jsp.ok" text="OK" /></button>
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
/* function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function showCustomerDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} */
 $(document).ready(function() {
    $("#employeetypetable").DataTable({
    	"lengthChange": false,
    	
    });

} ); 
</script>
