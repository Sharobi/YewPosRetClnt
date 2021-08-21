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
					<button class="btn btn-primary" type="button" onclick="showdepartmentaddModal()">
						<i class="fa fa-plus"></i>
						<spring:message code="cmn.jsp.btn.add" text="Add" />
					</button>
				</c:if>
			</div>

			<table id="departmenttable" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
                                            <th class="hide"> 
                                                      <spring:message code="hr.department.jsp.id" text="ID" />
                                            </th>
                                            <th>
                                                    <spring:message code="hr.department.jsp.name" text="NAME" />
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
					<c:if test="${!empty departmentList && departmentList!=null}">
						<c:forEach items="${departmentList}" var="dept">
							<tr>
								<td class="hide">${dept.id}</td> 
								<td>${dept.name} </td>
								
								<td><c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button" style="text-transform: uppercase; font-size: 11px;" onclick="javascript:showdepartmenteditModal(${dept.id},&quot;${dept.name}&quot;)">
											<i class="fa fa-pencil"></i>
											<spring:message code="cmn.jsp.btn.edit" text="Edit" />
										</button>
									</c:if>
									</td>
									<td>
									<c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" style="font-size: 11px;" type="button" onclick="showConfirmdeleteDeptModal(${dept.id},&quot;${dept.name}&quot;)">
											<i class="fa fa-trash-o "></i>
											<spring:message code="cmn.jsp.tblhdr.del" text="Del" />
										</button>
									</c:if>
									</td>
									
									<td>
                              <c:if test="${menuByUserDTO.isAll==1}">
                                   <a href="#"  onclick="javascript:showDetailDeptModal(${dept.id},&quot;${dept.name}&quot;)">
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

<!-- Department Add Modal Start -->
<div class="modal fade" id="departmentAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 836px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="adddepartmentheader">
					<span id="adddepartmentheaderspan"><spring:message code="admin.menumgnt.jsp.addDepartment" text="Add Department" /></span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="admin.menumgnt.jsp.departmentName" text="Department Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="adddepartmentname" class="form-control">
							</div>
						</div>
					
					</div>



					<div class="col-sm-6 col-sm-6 ">

						

								
						
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="addalertMsg"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:adddepartment()" class="btn btn-theme">

					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
</div>
</div>

<!-- Department Add Modal End -->

 <!-- Department Edit Modal Start -->
                            
                           <div class="modal fade" id="departmentEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 836px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="editdepartmentheader">
					<span id="editdepartmentheaderspan"><spring:message code="admin.menumgnt.jsp.editDepartment" text="Edit Department" /></span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="admin.menumgnt.jsp.departmentName" text="Department Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
							<input type="hidden" id="departmenteditid" value="">
								<input type="text" id="editdepartmentname" class="form-control">
							</div>
						</div>
						
						
					
					
						

							
						
					</div>



					<div class="col-sm-6 col-sm-6 ">

						

								
						
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="editalertMsg"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:editDepartment()" class="btn btn-theme">

					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
</div>
</div>
                            
                            <!-- Department Edit Modal End -->
                            
                  <!-- Department Detail Modal Start -->
                             <div class="modal fade" id="departmentDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
                           
                                <div class="modal-dialog" style="width: 836px;">
                                    <div class="modal-content">
                                        <div class="modal-header" >
                                            <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
                                            <h4 class="modal-title" style="font-size: 18px;font-weight: bold;" id="myModalLabel"><spring:message code="admin.menumgnt.jsp.deptDetails" text="Department Details" /></h4>
                                        </div>
                                        <div class="modal-body">
                                        	<div align="center" style="font-size: 20px;">
                                            	<table>
                                            		<tr class="hide">
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.id" text="ID" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="moddetaildepartmentid"></td>
                                            		</tr>
                                            		<tr>
                                            			<td style="padding: 5px 0px;"><spring:message code="admin.menumgnt.jsp.departmentName" text="DEPARTMENT NAME" /></td>
                                            			<td width="5%">:</td>
                                            			<td id="moddetaildepartmentname"></td>
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
                            
                            
                      <!-- Department Detail Modal End -->                    
                            
                            
                            
         <!-- Department Delete Modal Start -->
                            
                            <div class="modal fade" data-backdrop="static" id="confirmdeleteDeptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog" >
                                    <div class="modal-content" >
                                        <div class="modal-header" >
                                            
                                            <h4 class="modal-title" style="font-size: 18px;font-weight: bold;" id="myModalLabel"><spring:message code="admin.menumgnt.jsp.confirmation" text="Confirmation!" /></h4>
                                        </div>
                                        <div class="modal-body">
                                        	<div style="text-align: center;font-size: 20px;">
                                            	<spring:message code="admin.menumgnt.jsp.areYouSure" text="Are you sure?" />
                                            	<input type="hidden" id="moddeleteconfirmcatidDeptId" value="">
                                            	<input type="hidden" id="moddeleteconfirmcatnameDeptId" value="">
                                            	<!-- <input type="hidden" id="moddeleteconfirmcatbgcolorContId" value=""> -->
                                            </div>
                                        </div>
                                        <div class="modal-footer" >
                                        <button type="button" class="btn btn-default" data-dismiss="modal">
					                    <spring:message code="admin.menumgnt.jsp.cancel" text="Cancel" />
				                        </button>
                                            
                                            <button type="button" onclick="javacsript:deleteDepartment()"  data-dismiss="modal" class="btn btn-success"><spring:message code="admin.menumgnt.jsp.ok" text="OK" /></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                              <!-- Department Delete Modal End -->                   
                            
                            
                            
                            
                            
                            
                            
                            

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
				<button type="button"  onclick="location.href='${pageContext.request.contextPath}/hr/loaddept.htm'" data-dismiss="modal" class="btn btn-success"><spring:message code="admin.menumgnt.jsp.ok" text="OK" /></button>
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
    $('#departmenttable').DataTable({
    	"lengthChange": false,
    	
    });
    //$('.dataTables_filter input').attr("placeholder", getCustomerText.dataTablePlaceHolder);
} );
</script>
