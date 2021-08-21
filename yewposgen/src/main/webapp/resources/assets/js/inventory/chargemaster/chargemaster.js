// this is for edit marketer
function openAddEditModal(id, name) {
	document.getElementById('alertMsg').innerHTML = '';
	if (id == 0) { // add
		$('#marketer_code').attr("disabled", false);
		$("#headertext").text(getMarketerText.headerTextAdd);
	} else { // update
		var CommonresultsetmapperObj = {};
		CommonresultsetmapperObj.id = id;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL
				+ "/invsetup/getChargeMasterId.htm", CommonresultsetmapperObj,
				function(response) {
					// $('#pleasewaitModal').modal('hide');
					var status = JSON.parse(response);
					for ( var i = 0; i < status.length; i++) {

						$('#chargemaster_id').val(status[i].id);
						$('#chargemaster_code').val(status[i].code);
						$('#chargemaster_name').val(status[i].name);
						$('#rate').val(status[i].rate);
						$('#factor').val(status[i].factor);
						$('#istaxable').val(status[i].isTaxable);
					}
					console.log(status);
				});

		$("#headertext").text(getMarketerText.headerTextUpdate);
	}
	$('#marketAddEditModal').modal('show');
}

// for add and edit operation of markter
function addEditChMaster() {

	document.getElementById('alertMsg').innerHTML = '';
	var chargemaster_id = $('#chargemaster_id').val();
	var chargemaster_code = $('#chargemaster_code').val();
	var chargemaster_name = $('#chargemaster_name').val();
	var rate = $('#rate').val();
	var factor = $('#factor').val();
	var IsTaxable = $('#istaxable').val();
	var wef = $('#date').val();

	// var code_label = $("#code_label").text();
	// var code_field = code_label.substring(0, code_label.lastIndexOf(" "));

	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	var rate_label = $("#rate_label").text();
	var rate_field = rate_label.substring(0, rate_label.lastIndexOf(" "));

	// var field_names =
	// [["marketer_code",code_field],["marketer_name",name_field]];
	var field_names = [ [ "chargemaster_name", name_field ],
			[ "rate", rate_field ] ];

	if (fieldValidation(field_names) > 0) {

	} else {
		if (chargemaster_id == 0) {
			var ChargeMasterObj = {};
			ChargeMasterObj.name = chargemaster_name;
			ChargeMasterObj.code = chargemaster_code;
			ChargeMasterObj.rate = rate;
			ChargeMasterObj.factor = factor;
			ChargeMasterObj.isTaxable = IsTaxable;
			ChargeMasterObj.wefDate = wef;

			// alert(JSON.stringify(MarketerMasterObj)+" nnn"+0);

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL
					+ "/invsetup/createChargeMaster.htm", ChargeMasterObj,
					function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						chngeResultStat(status);
					});
		} else {// for edit
			var ChargeMasterObj = {};
			ChargeMasterObj.id = chargemaster_id;
			ChargeMasterObj.name = chargemaster_name;
			ChargeMasterObj.code = chargemaster_code;
			ChargeMasterObj.rate = rate;
			ChargeMasterObj.factor = factor;
			ChargeMasterObj.isTaxable = IsTaxable;
			ChargeMasterObj.wefDate = wef;
			// alert(JSON.stringify(MarketerMasterObj)+"edit");

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL
					+ "/invsetup/editChargeMaster.htm", ChargeMasterObj,
					function(response) {
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
	var ChargeMasterObj = {};
	ChargeMasterObj.id = id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/deletechargemaster.htm",
			ChargeMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
			});

}

function targetURL() {
	location.href = BASE_URL + '/invsetup/loadchargemaster.htm';
}