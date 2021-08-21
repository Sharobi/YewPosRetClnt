
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		<section class="wrapper home-bg-img section-height-custom" id="wrapperContainer">
			<div class="row">
          		<div class="col-md-2 col-sm-2"></div>
          		<div class="col-md-8 col-sm-8">          		
          		<div class="${(sesloggedinUser.isWholesale==1)? 'ymed-user-wholesale-img' : (sesloggedinUser.isWholesale==2)? 'yp-user-wholesale-img' : 'yp-user-retail-img'}" id="logoimgId"></div><!-- Date: 25/1/2020 / Sayantan Mahanty  -->
          		<div class="custom-panel">
          			<h2 class="custom-header">${sesloggedinStore.companyMaster.name}</h2>
          			<div class="log-store-name">OUTLET : ${sesloggedinStore.name}</div>
          				<%-- License No. : ${sesloggedinStore.dlLicenceNo}<br> --%>
						TRN No. : ${sesloggedinStore.dlLicenceNo}<br>
          				Valid UpTo : <fmt:formatDate pattern="yyyy-MM-dd" value="${sesloggedinStore.dlExpiryDate}" /><br>
          				State License No. : ${sesloggedinStore.stateLicenceNo}<br>
          				Valid UpTo : <fmt:formatDate pattern="yyyy-MM-dd" value="${sesloggedinStore.stateExpiryDate}" /><br>
          				${sesloggedinStore.address}&nbsp;&nbsp;${sesloggedinStore.state}&nbsp;&nbsp;${sesloggedinStore.country}<br>
          				Pin:${sesloggedinStore.postcode}
          			<br>
          				Phone:${sesloggedinStore.phone}<br>
			           <%-- <span class="${isTRN==1?'':'hide'}">TRN:${sesloggedinStore.taxRegNo}</span><br> --%>


          			</div>
          		</div>
          		<div class="col-md-2 col-sm-2"></div>
          	</div>
         </section><!--/wrapper -->

<script src="${pageContext.request.contextPath }/assets/js/common/jquery.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/common/jquery.backstretch.min.js"></script>
<script type="text/javascript">
/* $("#wrapperContainer").backstretch(['${pageContext.request.contextPath}/assets/images/home/pharma_home-background.jpg']); */
</script>


<style>
.section-height-custom{
height: 707px;
}

.log-store-name
{
font-size: 18px;
}
</style>