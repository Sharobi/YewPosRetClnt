/**
 * 
 */

function showTourDetaillModal(tourPlanNo,saleTourPlanId,tourDate,salesmanName,remarks,isCancel,cusno){
	/*alert(tourPlanNo + " " + saleTourPlanId + " " + tourDate + " "+salesmanName+" "+ remarks + " "+isCancel);*/
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/salestour/getTourPlanDetails/" + saleTourPlanId + ".htm", function(resp) {
		var tourPlanDetails = jQuery.parseJSON(resp);
		$("#tourPlanDetails").html("");
		$("#tourPlanData").html("");
		for ( var i = 0; i <tourPlanDetails.length; i++) {
			var tourPlanData = tourPlanDetails[i];
			/*$("#tourPlanDetails").text(itemPurHis.itemName);*/
			var starttrline = "<tr id=" + tourPlanData.tourPlanId + " >";
			var firstTd = "<td>" + (i+1) + "</td>";
			/*var secondTd = "<td>" + moment(tourPlanData.tourDate).format('YYYY-MM-DD') + "</td>";*/
			var thirdTd = "<td>" +tourPlanData.customerName + "</td>";
			var fourthTd = "<td>" +tourPlanData.customerAddress + "</td>";
			var fifthTd = "<td>" +tourPlanData.cityName + "</td>";
			var sixthTd = "<td>" +tourPlanData.zoneName + "</td>";
			var seventhTd = "<td>" +tourPlanData.areaName + "</td>";
			var endtrline = "</tr>";
			createdrowline = starttrline + firstTd  + thirdTd + fourthTd + fifthTd + sixthTd  + seventhTd + endtrline;
			$("#tourPlanDetails").append(createdrowline);
		}
		
		var startHdtrline = "<tr>";
		var firstHdTd = "<td>" + tourPlanNo + "</td>";
		var secondHdTd = "<td>" + salesmanName + "</td>";
		var thirdHdTd = "<td>" + moment(tourDate).format('YYYY-MM-DD') + "</td>";
		var fourthHdTd = "<td>" + cusno + "</td>";
		var fifthHdTd = "<td>" + isCancel + "</td>";
		var sixthHdTd = "<td>" + remarks + "</td>";
		var endHdtrline = "</tr>";
		var headerLine = startHdtrline+firstHdTd+secondHdTd+thirdHdTd+fourthHdTd+fifthHdTd+sixthHdTd+endHdtrline;
		$("#tourPlanData").append(headerLine);
		$('#tourPlanDetailModal').modal('show');
	}, null);
	
	
	
}