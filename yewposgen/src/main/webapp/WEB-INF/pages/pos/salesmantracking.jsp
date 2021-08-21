<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
  <meta charset="utf-8">
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
		<section class="wrapper">
			<div class="row">
          		<div class="col-lg-12">
   				<p><%-- <spring:message code="subcat.jsp.title" text="SubCategory..." /> --%></p>
   				<div class="panel panel-default">
					<div class="panel-body">
                         <div class="col-lg-10 col-md-10 col-sm-12">
								 <div class="col-lg-2 col-md-2"><b><spring:message code="tourplan.jsp.tourDate" text="Tour Date" /></b></div>
								 <div class="col-lg-3 col-md-3"><b><spring:message code="salesman.jsp.salesman" text="Salesman" /></b></div>
								
						</div>
						<div class="col-lg-10 col-md-10 col-sm-12">
								 <div class="col-lg-2 col-md-2 form-group">
                                          <input type="text" class="form-control-trx" id="tourDate" name="tourdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
								</div>
								<div class="col-lg-3 col-md-3 form-group">
									<select class="form-control" id="saleseman" name="salesmanId" style="height:29px;width:100%;" >
										    <!-- <option value="0">All</option> -->
											<c:if test="${!empty allSalesmanMasterlist}">
												<c:forEach items="${allSalesmanMasterlist}" var="saleseman">
													<option value="${saleseman.id}">${saleseman.name}</option>
												</c:forEach>
											</c:if>
									</select>
									
								</div>
								<div class="col-lg-2 col-md-2 form-group">
								  <button class="btn btn-primary" type="button" onclick="javascript:getTourPlanLocationDetail()"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.search" text="Search" /></button>
						
								</div>
								<b><span id="distance"></span></b>
							
						</div>
					</div>
					<div class="panel-body"> 
                          <div class="col-lg-12 col-md-12 col-sm-12">
                           <div id="map" style="height: 500px; width: 1064px;"></div>
					               
						 </div>
					</div> 
					
				</div>
      	      </div>
          	</div>
       </section><!--/wrapper -->
<!-- <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA7kcAw3JkmSf-Ej0l_wX3h3aD71wc-G64&libraries=places"></script>  -->
<!-- <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>  -->
<!-- <script async defer
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA7kcAw3JkmSf-Ej0l_wX3h3aD71wc-G64&callback=initMap">
    </script> -->
     
<script src="${pageContext.request.contextPath }/assets/ajax/ajax.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/pos/tourplan/tourplantracking.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/admin/user/user_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<!-- <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBfYAQZp3wjpRaj0STJ8pNDH1W42wC7EjU&callback=initMap"
  type="text/javascript"></script> -->
 <script type="text/javascript" src=" http://maps.google.com/maps/api/js?sensor=true&libraries=geometry"></script>
 <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA7kcAw3JkmSf-Ej0l_wX3h3aD71wc-G64&callback=initMap"></script>  
  
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
var activeStoreId = "${sesloggedinUser.storeId}";
var infoWindow;
var polyline;
var lineSymbol;
var boundsListener;
var map;
/* function initMap() {
     map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: {lat: 22.5863785 , lng: 88.3968839},
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });
} */
    
   
   
$(document).ready(function() {
	
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getUserText.dataTablePlaceHolder);
    
    $('#tourDate').datepicker({
		format : 'yyyy-mm-dd',
		autoclose: true
	});
	
});
function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showUserDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
}


</script>