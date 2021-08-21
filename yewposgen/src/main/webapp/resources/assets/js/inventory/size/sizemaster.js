// this is for edit marketer
function openAddEditModal(id, name) {
	document.getElementById('alertMsg').innerHTML = '';
	if (id == 0) { // add
		$('#marketAddEditModal').find('input:text').val('');
		$('#marketAddEditModal').find('input:hidden').val('');
		$('#variantTypeId').attr("disabled", false);
		$("#headertext").text(getMarketerText.headerTextAdd);
	} else { // update
		$('#variantTypeId').attr("disabled", true);
		$('#marketer_id').val(id);
		var CommonresultsetmapperObj = {};
		CommonresultsetmapperObj.id = id;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL
				+ "/invsetup/getVariantMasterId.htm", CommonresultsetmapperObj,
				function(response) {
					// $('#pleasewaitModal').modal('hide');
					var status = JSON.parse(response);
					$('#value').val(status.value);
					$('#variantTypeId').val(status.variantTypeId);
					
					console.log(status);
				});
		$("#headertext").text(getMarketerText.headerTextUpdate);
	}
	$('#marketAddEditModal').modal('show');
}


// for add and edit operation of markter
function addEditMarketer() {
	
	document.getElementById('alertMsg').innerHTML = '';
	var marketer_id = $('#marketer_id').val();
	var variantTypeId = $('#variantTypeId').val();
	var value = $('#value').val();
	
	var code_label = $("#code_label").text();
	var code_field = code_label.substring(0, code_label.lastIndexOf(" "));
	
	var value_label = $("#value_label").text();
	var value_labelt = value_label.substring(0, value_label.lastIndexOf(" "));
	var field_names = [["value","Value"]];
	if(fieldValidation(field_names)>0)
		{
			
		}
	else
		{
		$('#marketAddEditModal').modal('hide');
				if (marketer_id == 0) {
					var VariantMasterObj = {};
					VariantMasterObj.variantTypeId = variantTypeId;
					VariantMasterObj.value = value;
					//alert(JSON.stringify(VariantMasterObj)+" nnn"+0);
			
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/createVarientMaster.htm",VariantMasterObj, function(response) {
								$('#pleasewaitModal').modal('hide');
								var status = JSON.parse(response);
								chngeResultStat(status);
							});
				} else {// for edit
					var VariantMasterObj = {};
					VariantMasterObj.id=marketer_id;
					VariantMasterObj.variantTypeId = variantTypeId;
					VariantMasterObj.value = value;
					//alert(JSON.stringify(MarketerMasterObj)+"edit");
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editVariantMaster.htm",VariantMasterObj, function(response) {
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
	var VariantMasterObj = {};
	VariantMasterObj.id = id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/deletevariantmaster.htm",
			VariantMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
			});
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/loadvariantmaster.htm';
}