$(document).ready(function(){
	var currentDate = new Date();
	//alert(currentDate);
	//$('#date').val(moment().format('YYYY-MM-DD'));
	$('#date').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});
	$("#opbal").keyup(function(){
		if(isNaN($("#opbal").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Opening Balance";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});

	$("#creditLimit").keyup(function(){
		if(isNaN($("#creditLimit").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Credit Limit";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});

	$("#pin").keyup(function(){
		if(isNaN($("#pin").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Pin";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});
});
function openAddEditModal(id) {
	document.getElementById('alertMsg').innerHTML = '';
	$('#customerAddEditModal').find('input:text').val('');
	$('#customerAddEditModal').find('input:hidden').val('');
	$('#country').val(0.0);
	$('#state').val(0);
	$('#city').val(0);
	$('#creditLimit').val(getCustomerText.creditCustAmt);
	if (id == 0) { // add
		$("#headertext").text(getCustomerText.headerTextAdd);
		$('#country').val(countryId+".0");
		getStateByCountry();
		setTimeout(function(){
			$('#state').val(stateId);
			getCityByState();
		},500);
	} else { // update
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/customer/getCustomerbyId/" + id + ".htm", function(response) {
			$("#cust_id").val($.parseJSON(response).id);
			$('#pin').val($.parseJSON(response).pin);
			$('#customerName').val($.parseJSON(response).name);
			$('#code').val($.parseJSON(response).code);
			$('#addrs').val($.parseJSON(response).address);

			$('#phn').val($.parseJSON(response).phoneNo);
			$('#fax').val($.parseJSON(response).fax);
			$('#opbal').val($.parseJSON(response).obBal);
			$('#creditLimit').val($.parseJSON(response).creditLimit);
			$("#aadharcard").val($.parseJSON(response).addharCardNo);
			$("#slgender").val($.parseJSON(response).gender);
			$('#custTypeId').val($.parseJSON(response).customerTypeId);
			$('#trnno_regno').val($.parseJSON(response).gstNo);
			$("#dlno").val($.parseJSON(response).dlNo);
			$("#panno").val($.parseJSON(response).panNo);

			$('#country').val($.parseJSON(response).countryId+".0");
			getStateByCountry();
			//$('#city').val($.parseJSON(response).city);
			setTimeout(function(){
				$('#state').val($.parseJSON(response).stateId);
				getCityByState();

			},500);

			setTimeout(function(){
				$('#city').val($.parseJSON(response).cityId);
			},1000);

		}, null);
		$("#headertext").text(getCustomerText.headerTextUpdate);
	}
	$('#customerAddEditModal').modal('show');
}

function addEditCustomer() {
	document.getElementById('alertMsg').innerHTML = '';
	var cust_id = $("#cust_id").val();
	var pin = $('#pin').val();
	var name = $('#customerName').val();
	var code = $('#code').val();
	var qualification = $('#qualification').val();
	var speciality = $('#speciality').val();
	var addrs = $('#addrs').val();
	var city = $('#city').val();
	var state = $('#state').val();
	var country = $('#country').val();
	var phn = $('#phn').val();
	var fax = $('#fax').val();
	var slgender = $('#slgender').val();
	var custtype = $('#custTypeId').val();
	var trnorReg= $("#trnno_regno").val();
	
	/*if($('#customerName').val() == ""){
		document.getElementById('alertMsg').innerHTML = getCustomerText.emptyName;
		$(this).focus();
		return false;
	}else{
		document.getElementById('alertMsg').innerHTML = "";
	}*/
	
	/*if (Number($("#phn").val().length)<10) {
		document.getElementById('alertMsg').innerHTML = "Mobile Number should be 10 digit.";
		$(this).focus();
		return false;
	} else {
		document.getElementById('alertMsg').innerHTML = "";
	}*/
	
	if(isNaN($("#pin").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Pin";
		$(this).focus();
		return false;
	}
	/*else if($("#pin").val() == ""){
		document.getElementById('alertMsg').innerHTML = getCustomerText.emptyPin;
		$(this).focus();
		return false;
	}*/else{
		document.getElementById('alertMsg').innerHTML = "";
	}

	if($('#opbal').val()==0 || $('#opbal').val()=="")
	{
		var opbal = 0;
	}
	else
	{
		if(isNaN($("#opbal").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Opening Balance";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			var opbal = $('#opbal').val();
		}
	}

	/*if(phn.length<10)
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.phnDigitLengthCheckErr;
		$("#phn").focus();
		return false;
	}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}*/

	if($('#creditLimit').val()==0 || $('#creditLimit').val()=="")
	{
		var creditLimit = 0.0;
	}
	else
	{
		if(isNaN($("#creditLimit").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Credit Limit";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			var creditLimit = $('#creditLimit').val();
		}
	}

	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

	var phn_label = $("#phn_label").text();
	var phn_field = phn_label.substring(0, phn_label.lastIndexOf(" "));
	
	var pin_label = $("#pin_label").text();
	var pin_field = pin_label.substring(0, pin_label.lastIndexOf(" "));
	
	/*var pin_label = $("#pin_label").text();
	var pin_field = pin_label.substring(0, pin_label.lastIndexOf(" "));*/
	
	var state_label = $("#states_label").text();
	var state_field = state_label.substring(0, state_label.lastIndexOf(" "));
	
	var city_label = $("#city_label").text();
	var city_field = city_label.substring(0, city_label.lastIndexOf(" "));
	

	/*var field_names = [["customerName",name_field],["phn",phn_field],["pin",pin_field],["state",state_field],["city",city_field]];*/
	 var field_names = [["customerName",name_field],["phn",phn_field]];

	if(fieldValidation(field_names)>0)
		{
		}
	else {
		$('#customerAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (cust_id == 0) { // add unit
			var CustomerMasterObj = {};
			CustomerMasterObj.code = code;
			CustomerMasterObj.name = name;
			CustomerMasterObj.address = addrs;
			CustomerMasterObj.pin = pin;
			CustomerMasterObj.city = city;
			CustomerMasterObj.state = state;
			CustomerMasterObj.country = country;
			CustomerMasterObj.phoneNo = phn;
			CustomerMasterObj.fax = fax;
			CustomerMasterObj.obBal = opbal;
			CustomerMasterObj.creditLimit = creditLimit;
			CustomerMasterObj.addharCardNo = $("#aadharcard").val();
			CustomerMasterObj.gender = slgender;
			CustomerMasterObj.customerTypeId=custtype;
			CustomerMasterObj.gstNo=trnorReg;
			CustomerMasterObj.dlNo=$("#dlno").val();
			CustomerMasterObj.panNo=$("#panno").val();
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/customer/addcustomer.htm", CustomerMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
				/*if (response == 0) {
					document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotAdd;
					showConfirmModal();
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucAdd;
					showConfirmModal();
				}*/

			});
		} else {// edit unit
			var CustomerMasterObj = {};
			CustomerMasterObj.id = cust_id;
			CustomerMasterObj.code = code;
			CustomerMasterObj.name = name;
			CustomerMasterObj.address = addrs;
			CustomerMasterObj.pin = pin;
			CustomerMasterObj.city = city;
			CustomerMasterObj.state = state;
			CustomerMasterObj.country = country;
			CustomerMasterObj.phoneNo = phn;
			CustomerMasterObj.fax = fax;
			CustomerMasterObj.obBal = opbal;
			CustomerMasterObj.creditLimit = creditLimit;
			CustomerMasterObj.addharCardNo = $("#aadharcard").val();
			CustomerMasterObj.gender = slgender;
			CustomerMasterObj.customerTypeId=custtype
			CustomerMasterObj.gstNo=trnorReg;
			CustomerMasterObj.dlNo=$("#dlno").val();
			CustomerMasterObj.panNo=$("#panno").val();

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/customer/editcustomer.htm", CustomerMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
				/*if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucUpdate;
					showConfirmModal();
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotUpdate;
					showConfirmModal();
				}*/

			});

		}
	}
}

function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;

	var CommonResultSetMapper = {};
	CommonResultSetMapper.custId = id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/customer/delcustomer.htm", CommonResultSetMapper, function(response) {
		$('#pleasewaitModal').modal('hide');
		var status = JSON.parse(response);
		chngeResultStat(status);
	});
	/*ajaxCallObject.callAjax(BASE_URL + "/customer/deleteCustomer/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotDelete;
			showConfirmModal();
		}
	}, null);*/
}

function targetURL() {
	location.href = BASE_URL + '/customer/loadcustomer.htm';
}

/*
 * for state list
 */

function getStateByCountry() {

    var country = $("#country").val();
    var commonResultObj = {};
    commonResultObj.countryId = country;

    var ajaxCallObject = new CustomBrowserXMLObject();
    ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getStateByCountry.htm",
        commonResultObj,
        function(response) {
            $('#pleasewaitModal').modal('hide');
            // console.log(response);
            var res = JSON.parse(response);
            var str = "<option value='0'>Select....</option>";
            $.each(res, function(i) {
                str = str + "<option value=" + res[i].id + ">" +
                    res[i].name + "</option>";
            });
            $("#state").html(str);
        });

}

function getCityByState() {
    var country = $("#country").val();
    var state = $("#state").val();
    var commonResultObj = {};
    commonResultObj.countryId = country;
    commonResultObj.stateId = state;

    var ajaxCallObject = new CustomBrowserXMLObject();
    ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getCityByState.htm",
        commonResultObj,
        function(response) {
            $('#pleasewaitModal').modal('hide');
            // console.log(response);
            var res = JSON.parse(response);
            var str = "<option value='0'>Select....</option>";
            $.each(res, function(i) {
                str = str + "<option value=" + res[i].id + ">" +
                    res[i].name + "</option>";
            });
            $("#city").html(str);
        });

}


/*
 * open city model here
 */
function openCityModel() {

    $("#headertextofcity").text(getCustomerText.headerTextAddCity);

    $('#cityAddEditModal').modal('show');

    if ($('#country').val()>0) {
    	  $('#countryidincity').val($('#country').val());



    		 var commonResultObj1 = {};

    		 // for state
    			commonResultObj1.countryId = $('#countryidincity').val();

    			var ajaxCallObject = new CustomBrowserXMLObject();
    			ajaxCallObject.callAjaxPost(BASE_URL+ "/invsetup/getStateByCountry.htm", commonResultObj1,
    					function(response) {
    						$('#pleasewaitModal').modal('hide');
    						// console.log(response);
    						var res = JSON.parse(response);
    						var str = "<option value='0'>Select....</option>";
    						$.each(res, function(i) {
    							str = str + "<option value='" + res[i].id + "'>"
    									+ res[i].name + "</option>";
    						});
    						$("#stateList").html(str);
    						$('#stateList').val($('#state').val());
    			});


	}else {


		 $('#countryidincity').val(0);
		 $('#stateList').val(0);
	}


}


function getStateByCountryinCity() {
    var country = $("#countryidincity").val();
    var commonResultObj = {};
    commonResultObj.countryId = country;

    var ajaxCallObject = new CustomBrowserXMLObject();
    ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getStateByCountry.htm",
        commonResultObj,
        function(response) {
            $('#pleasewaitModal').modal('hide');
            // console.log(response);
            var res = JSON.parse(response);
            var str = "<option value='0'>Select....</option>";
            $.each(res, function(i) {
                str = str + "<option value='" + res[i].id + "'>" +
                    res[i].name + "</option>";
            });
            $("#stateList").html(str);
        });

}

function addEditCity() {
    var country = $('#countryidincity').val();
    var state = $('#stateList').val();
    var cityName = $('#cityName').val();

    var name_label = $("#name_label_countryincity").text();
    var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

    var name_label_state = $("#name_label_stateincity").text();
    var name_field1 = name_label_state.substring(0, name_label_state
        .lastIndexOf(" "));

    var name_label_city = $("#name_label_cityincity").text();
    var name_field2 = name_label_city.substring(0, name_label_city
        .lastIndexOf(" "));

    var field_names = [
        ["countryidincity", name_field],
        ["stateList", name_field1],
        ["cityName", name_field2]
    ];

    if (fieldValidationWithAlertDivId(field_names, "alertMsgForcity") > 0) {

    } else {
        var commonresultsetObj = {};
        commonresultsetObj.countryId = country;
        commonresultsetObj.stateId = state;
        commonresultsetObj.name = cityName;

        var ajaxCallObject = new CustomBrowserXMLObject();
        ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addcity.htm",
            commonresultsetObj,
            function(response) {
                $('#pleasewaitModal').modal('hide');
                var status = JSON.parse(response);
                //chngeResultStatForNewItem(status);
                $('#cityAddEditModal').modal('hide');

                var newcity = "<option value='" + status.id + "'>" +
                    cityName + "</option>";
                $("#city").append(newcity);
                $("#city").val(status.id);
            });
    }

}

function numcheck(e) {
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
        // Allow: Ctrl+A, Command+A
        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
        // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
        // let it happen, don't do anything
        return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
}