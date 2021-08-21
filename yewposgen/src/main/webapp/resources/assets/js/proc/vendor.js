function isEmpty(val) {

    return (val === undefined || val == null || val.length <= 0) ? true : false;
}



$(document).ready(function(){
	$("#opbalance").keyup(function(){
		if(isNaN($("#opbalance").val()))
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

	$("#credt_limit").keyup(function(){
		if(isNaN($("#credt_limit").val()))
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

	$("#discount").keyup(function(){
		if(isNaN($("#discount").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Discount";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});

	$("#duePer").keyup(function(){


          if (parseFloat($("#duePer").val())>100) {
        	  $("#duePer").val(0);
		   }
	});

});

function openAddEditModal(id) {
	
	document.getElementById('alertMsg').innerHTML = '';
	if (id == 0) { // add
		$('#vendorAddEditModal').find('input:text').val('');
		$('#vendorAddEditModal').find('input:hidden').val('');
		$('#cntry').val(0.0);
		$('#state').val(0);
		$('#city').val(0);
		$('#addrs').val("");
		$("#headertext").text(getVendorText.headerTextAdd);
		$("#credt_limit").val(getVendorText.creditVndrAmt);
		$('#cntry').val(countryId+".0");
		getStateByCountry();setTimeout(function(){
			$('#state').val(stateId);
			getCityByState();
		},500);
	} else { // update
		$("#headertext").text(getVendorText.headerTextUpdate);
		var CommonResultSetMapper = {};
		CommonResultSetMapper.distributorId = id;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/getvendordetailsbyid.htm", CommonResultSetMapper, function(response) {
			var vendordet = JSON.parse(response);
			$("#vendor_id").val(vendordet.id);
			$('#name').val(vendordet.name);
			$('#addrs').val(vendordet.address);
			$('#pin').val(vendordet.pin);
			/*$('#city').val(vendordet.city);*/
			//$('#state').val(vendordet.state);
			//$('#cntry').val(vendordet.country);
			$('#phn1').val(vendordet.phoneNo1);
			$('#phn2').val(vendordet.phoneNo2);
			$('#fax').val(vendordet.fax);
			$('#email').val(vendordet.email);
			$('#cntct_person').val(vendordet.contactPerson);
			$('#rgstratn').val(vendordet.registrationNo);
			$('#opbalance').val(vendordet.obBal);
			$('#credt_limit').val(vendordet.creditLimit);
			$('#licenceNo').val(vendordet.licenceNo);
			$('#discount').val(vendordet.discount);
			$('#dueDays').val(vendordet.dueDays);
			$('#duePer').val(vendordet.duePer);

			/*
			 * New added
			 */


			$('#cntry').val($.parseJSON(response).countryId+".0");
			getStateByCountry();
			//$('#city').val($.parseJSON(response).city);
			setTimeout(function(){
				$('#state').val($.parseJSON(response).stateId);
				getCityByState();

			},500);

			setTimeout(function(){
				$('#city').val($.parseJSON(response).cityId);
			},1000);


		});
	}
	$('#vendorAddEditModal').modal('show');
}

function addEditVendor() {
	document.getElementById('alertMsg').innerHTML = '';
	var vendor_id = $("#vendor_id").val();
	var pin = $('#pin').val();
	var name = $('#name').val();
	var addrs1 = $('#addrs').val();
	var addrs = addrs1.replace(/\n/g, " ");
	var city = $('#city').val();
	var state = $('#state').val();
	var cntry = $('#cntry').val();
	var phn1 = $('#phn1').val();
	var phn2 = $('#phn2').val();
	var fax = $('#fax').val();
	var email = $('#email').val();
	var cntct_person = $('#cntct_person').val();
	var rgstratn = $('#rgstratn').val();
	var opbalance = $('#opbalance').val();
	var credt_limit = $('#credt_limit').val();
	var licenceNo = $('#licenceNo').val();
	var discount = $('#discount').val();
    var cityId=$('#city').val();
    var dueDays=$('#dueDays').val();

    if (isEmpty(dueDays)) {
    	dueDays=0;
	}

    var duePer=$('#duePer').val();
    if (isEmpty(duePer)) {
    	duePer=0;
	}

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

	if(discount=="" || discount==null)
	{
		discount = 0.00;
	}
	else
	{
		if(isNaN($("#discount").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Discount";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	}

	if(opbalance=="" || opbalance==null)
	{
		opbalance = 0.00;
	}
	else
	{
		if(isNaN($("#opbalance").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Opening Balance";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	}
	if(credt_limit=="" || credt_limit==null)
	{
		credt_limit = 0.00;
	}
	else
	{
		if(isNaN($("#credt_limit").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Credit Limit";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	}
	var pin_label = $("#pin_label").text();
	var pin_field = pin_label.substring(0, pin_label.lastIndexOf(" "));

	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

	var addrs_label = $("#addrs_label").text();
	var addrs_field = addrs_label.substring(0, addrs_label.lastIndexOf(" "));

	var phn1_label = $("#phn1_label").text();
	var phn1_field = phn1_label.substring(0, phn1_label.lastIndexOf(" "));

	var rgstratn_label = $("#rgstratn_label").text();
	var rgstratn_field = rgstratn_label.substring(0, rgstratn_label.lastIndexOf(" "));

	var licenceNo_label = $("#licenceNo_label").text();
	var licenceNo_field = licenceNo_label.substring(0, licenceNo_label.lastIndexOf(" "));

	//var field_names = [["name",name_field],["addrs",addrs_field],["pin",pin_field],["phn1",phn1_field],["rgstratn",rgstratn_field],["licenceNo",licenceNo_field]];
	var field_names = [["name",name_field]];


	if(fieldValidation(field_names)>0)
		{

		}
		else {
		$('#vendorAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (vendor_id == 0) { // add Vendor
			var DistributorMasterObj = {};
			DistributorMasterObj.name = name;
			DistributorMasterObj.pin = pin;
			DistributorMasterObj.address = addrs;
			DistributorMasterObj.city = city;
			DistributorMasterObj.state = state;
			DistributorMasterObj.country = cntry;
			DistributorMasterObj.phoneNo1 = phn1;
			DistributorMasterObj.phoneNo2 = phn2;
			DistributorMasterObj.fax = fax;
			DistributorMasterObj.email = email;
			DistributorMasterObj.contactPerson = cntct_person;
			DistributorMasterObj.registrationNo = rgstratn;
			DistributorMasterObj.obBal = opbalance;
			DistributorMasterObj.creditLimit = credt_limit;
			DistributorMasterObj.licenceNo = licenceNo;
			DistributorMasterObj.discount = discount;
			DistributorMasterObj.cityId=cityId;
			DistributorMasterObj.dueDays=dueDays;
			DistributorMasterObj.duePer=duePer;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/addVendor.htm", DistributorMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
				/*if (response == 0) {
					document.getElementById('confirmmessagecont').innerHTML = getVendorText.dataNotAdd;
					showConfirmModal();
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getVendorText.dataSucAdd;
					showConfirmModal();
				}*/

			});
		} else {// edit Manufacturer
			var DistributorMasterObj = {};
			DistributorMasterObj.id = vendor_id;
			DistributorMasterObj.name = name;
			DistributorMasterObj.pin = pin;
			DistributorMasterObj.address = addrs;
			DistributorMasterObj.city = city;
			DistributorMasterObj.state = state;
			DistributorMasterObj.country = cntry;
			DistributorMasterObj.phoneNo1 = phn1;
			DistributorMasterObj.phoneNo2 = phn2;
			DistributorMasterObj.fax = fax;
			DistributorMasterObj.email = email;
			DistributorMasterObj.contactPerson = cntct_person;
			DistributorMasterObj.registrationNo = rgstratn;
			DistributorMasterObj.obBal = opbalance;
			DistributorMasterObj.creditLimit = credt_limit;
			DistributorMasterObj.licenceNo = licenceNo;
			DistributorMasterObj.discount = discount;
			DistributorMasterObj.cityId=cityId;
			DistributorMasterObj.dueDays=dueDays;
			DistributorMasterObj.duePer=duePer;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/editVendor.htm", DistributorMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
				/*if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML = getVendorText.dataSucUpdate;
					showConfirmModal();
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getVendorText.dataNotUpdate;
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
	CommonResultSetMapper.distributorId = id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/delvendor.htm", CommonResultSetMapper, function(response) {
		$('#pleasewaitModal').modal('hide');
		var status = JSON.parse(response);
		chngeResultStat(status);
	});
	/*var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/vendor/deleteVendor/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getVendorText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getVendorText.dataNotDelete;
			showConfirmModal();
		}
	}, null);*/
}


function targetURL() {
	location.href = BASE_URL + '/vendor/loadvendor.htm';
}

/*
 * for state list
 */

function getStateByCountry() {

    var country = $("#cntry").val();
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
    var country = $("#cntry").val();
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

    $("#headertextofcity").text(getVendorText.headerTextAddCity);

    $('#cityAddEditModal').modal('show');

    if ($('#cntry').val()>0) {
    	  $('#countryidincity').val($('#cntry').val());



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


