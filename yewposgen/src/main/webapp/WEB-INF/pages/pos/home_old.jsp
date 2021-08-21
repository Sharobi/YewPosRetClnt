
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		<section class="wrapper home-bg-img" id="wrapperContainer" style="height: 607px;">
			<div class="row">
          		<div class="col-md-4 col-sm-4"></div>
          		<div class="col-md-4 col-sm-4">
          		<div class="login-header-img" style="background-repeat: no-repeat;padding: 36px 0px;margin-top: 50px;"></div>
          		<div class="custom-panel">
          			<h2 class="custom-header">${sesloggedinStore.companyMaster.name}</h2>
          			<div style="font-size: 18px;">OUTLET : ${sesloggedinStore.name}</div>
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
          		<div class="col-md-4 col-sm-4"></div>
          	</div>
         </section><!--/wrapper -->

<script src="${pageContext.request.contextPath }/assets/js/common/jquery.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/common/jquery.backstretch.min.js"></script>
<script type="text/javascript">
/* $("#wrapperContainer").backstretch(['${pageContext.request.contextPath}/assets/images/home/pharma_home-background.jpg']); */
</script>