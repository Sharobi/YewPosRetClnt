$(document).ready(function(){
	$("#commition").keyup(function(){
		if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in Commition";
			$("#commition").focus();
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});
});
function getStateByCountry(){
	var country=$("#countryList").val();
	var commonResultObj = {};
	commonResultObj.countryId = country;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getStateByCountry.htm", commonResultObj, function(response) {
		$('#pleasewaitModal').modal('hide');
//		console.log(response);
		var res=JSON.parse(response);
		var str="<option value='0'>Select....</option>";
		$.each(res, function (i) {
			str=str+"<option value='"+res[i].id+"'>"+res[i].name+"</option>";
        });
		$("#stateList").html(str);
	});

}

function getCityByState(){
	var country=$("#countryList").val();
	var state=$("#stateList").val();
	var commonResultObj = {};
	commonResultObj.countryId = country;
	commonResultObj.stateId = state;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getCityByState.htm", commonResultObj, function(response) {
		$('#pleasewaitModal').modal('hide');
//		console.log(response);
		var res=JSON.parse(response);
		var str="<option value='0'>Select....</option>";
		$.each(res, function (i) {
			str=str+"<option value='"+res[i].id+"'>"+res[i].name+"</option>";
        });
		$("#cityList").html(str);
	});

}

// this is for opening modal during add/edit salesman
function openAddEditModal(id, name) {
	document.getElementById('alertMsg').innerHTML = '';
	$('#salesmanAddEditModal').find('input:text').val('');
	$('#salesmanAddEditModal').find('input:hidden').val('');
	$('#country').val(0.0);
	$('#state').val(0);
	$('#city').val(0);
	if (id == 0) { // add
		$('#salesman_code').attr("disabled", false);
		$("#headertext").text(getSalesmanText.headerTextAdd);
		$("#countryList").val(countryId);
		getStateByCountry();
		setTimeout(function() {
			$('#stateList').val(stateId);
			getCityByState();
		},500);
	} else { // update
		//$('#salesman_code').attr("disabled", true);
		//$("#headertext").text(getSalesmanText.headerTextUpdate);
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/salesman/getSalesmanbyId/" + id + ".htm", function(response) {
			$("#salesman_id").val(id);
			$('#salesman_code').val($.parseJSON(response).empId);
			$('#salesman_name').val(name);
			$('#addrs').val($.parseJSON(response).address);
			$('#phn').val($.parseJSON(response).phoneNo);
			$('#commition').val($.parseJSON(response).commissionPercentage);
			$('#pan').val($.parseJSON(response).panNo);
			$('#aadhar').val($.parseJSON(response).addharCardNo);
			$("#countryList").val($.parseJSON(response).countryId);
			getStateByCountry();
			setTimeout(function() {
				$('#stateList').val($.parseJSON(response).stateId);
				getCityByState();
			},500);

			setTimeout(function() {
				$('#cityList').val($.parseJSON(response).cityId);

			},1400);

		}, null);
		$("#headertext").text(getSalesmanText.headerTextUpdate);
	}
	$('#salesmanAddEditModal').modal('show');
}


// for add and edit operation of Salesman
function addEditsalesman() {
	document.getElementById('alertMsg').innerHTML = '';
	var salesman_id = $('#salesman_id').val();
	var salesman_code = $('#salesman_code').val();
	var salesman_name = $('#salesman_name').val();
	var addrs = $('#addrs').val();
	var phn = $('#phn').val();
	var commition = $('#commition').val();
    if(commition == ''){commition=0;}
	var countryId=$('#countryList').val();
	var stateId=$('#stateList').val();
	var cityId=$('#cityList').val();
	var pan = $('#pan').val();
	var aadhar = $('#aadhar').val();

	var country_label = $("#country_label").text();
	var country_field = country_label.substring(0, country_label.lastIndexOf(" "));

	var state_label = $("#state_label").text();
	var state_field = state_label.substring(0, state_label.lastIndexOf(" "));

	var city_label = $("#city_label").text();
	var city_field = city_label.substring(0, city_label.lastIndexOf(" "));

	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

/*	if(countryId==0)
	{
		document.getElementById('alertMsg').innerHTML=country_field+ " " + getFieldText.fieldReq;
		return false;
	}

	if(stateId==0)
	{
		document.getElementById('alertMsg').innerHTML=state_field+ " " + getFieldText.fieldReq;
		return false;
	}

	if(cityId==0)
	{
		document.getElementById('alertMsg').innerHTML=city_field+ " " + getFieldText.fieldReq;
		return false;
	}*/

	if(isNaN($("#commition").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in Commition";
		$("#commition").focus();
		return false;
	}

	var field_names = [["salesman_name",name_field]];



	if(fieldValidation(field_names)>0)
		{

		}
	else
		{
				if (salesman_id == 0) {
					var SalesmanMasterObj = {};
					SalesmanMasterObj.empId = salesman_code;
					SalesmanMasterObj.name = salesman_name;
					SalesmanMasterObj.address = addrs;
					SalesmanMasterObj.phoneNo = phn;
					SalesmanMasterObj.commissionPercentage = commition;
					SalesmanMasterObj.countryId = countryId;
					SalesmanMasterObj.stateId = stateId;
					SalesmanMasterObj.cityId = cityId;
					SalesmanMasterObj.panNo = pan;
					SalesmanMasterObj.addharCardNo = aadhar;

					//alert(JSON.stringify(SalesmanMasterObj)+" add");

					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/salesman/addSalesman.htm",SalesmanMasterObj, function(response) {
								$('#pleasewaitModal').modal('hide');
								var status = JSON.parse(response);
								chngeResultStat(status);
							});
				} else {// for edit
					var SalesmanMasterObj = {};
					SalesmanMasterObj.id = salesman_id;
					SalesmanMasterObj.empId = salesman_code;
					SalesmanMasterObj.name = salesman_name;
					SalesmanMasterObj.address = addrs;
					SalesmanMasterObj.phoneNo = phn;
					SalesmanMasterObj.commissionPercentage = commition;
					SalesmanMasterObj.countryId = countryId;
					SalesmanMasterObj.stateId = stateId;
					SalesmanMasterObj.cityId = cityId;
					SalesmanMasterObj.panNo = pan;
					SalesmanMasterObj.addharCardNo = aadhar;

					//alert(JSON.stringify(SalesmanMasterObj)+" edit");

					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/salesman/editSalesman.htm",SalesmanMasterObj, function(response) {
								$('#pleasewaitModal').modal('hide');
								var status = JSON.parse(response);
								chngeResultStat(status);
							});
				}
		}
}

function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject
			.callAjax(
					BASE_URL + "/salesman/deleteSalesman/" + id + ".htm",
					function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						chngeResultStat(status);
					}, null);
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/salesman.htm';
}

function isEmpty(val) {
    return (val === undefined || val == null || val.length <= 0) ? true : false;
}
