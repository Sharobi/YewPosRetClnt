// this is for edit marketer
function openAddEditModal(id, name) {
	document.getElementById('alertMsg').innerHTML = '';
	//$("#marketer_id").val(id);
	 //$("#marketer_id").val();
	/*
	 * $('#marketer_code').val(code); $('#marketer_name').val(name);
	 * $('#addrs').val(address); $('#phn').val(phn); $('#fax').val(fax);
	 * $('#email').val(email); $('#url').val(url);
	 */
	if (id == 0) { // add
		$('#marketer_code').attr("disabled", false);
		$("#headertext").text(getMarketerText.headerTextAdd);
	} else { // update
		//$('#marketer_code').attr("disabled", true);
		//$("#headertext").text(getMarketerText.headerTextUpdate);
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/marketer/getMarketerbyId/" + id + ".htm", function(response) {
			$("#marketer_id").val($.parseJSON(response).id);
			$('#marketer_code').val($.parseJSON(response).code);
			$('#marketer_name').val($.parseJSON(response).name);
			$('#addrs').val($.parseJSON(response).address);
			$('#phn').val($.parseJSON(response).phone);
			$('#fax').val($.parseJSON(response).fax);
			$('#email').val($.parseJSON(response).email);
			$('#url').val($.parseJSON(response).url);
		}, null);
		$("#headertext").text(getMarketerText.headerTextUpdate);
	}
	$('#marketAddEditModal').modal('show');
}


// for add and edit operation of markter
function addEditMarketer() {
	
	document.getElementById('alertMsg').innerHTML = '';
	var marketer_id = $('#marketer_id').val();
	var marketer_code = $('#marketer_code').val();
	var marketer_name = $('#marketer_name').val();
	var addrs = $('#addrs').val();
	var phn = $('#phn').val();
	var fax = $('#fax').val();
	var email = $('#email').val();
	var url = $('#url').val();
	
	
	var code_label = $("#code_label").text();
	var code_field = code_label.substring(0, code_label.lastIndexOf(" "));
	
	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
 
	
	var field_names = [["marketer_code",code_field],["marketer_name",name_field]];
	
	
	
	if(fieldValidation(field_names)>0)
		{
			
		}
	else
		{
				if (marketer_id == 0) {
					var MarketerMasterObj = {};
					MarketerMasterObj.code = marketer_code;
					MarketerMasterObj.name = marketer_name;
					MarketerMasterObj.address = addrs;
					MarketerMasterObj.phone = phn;
					MarketerMasterObj.fax = fax;
					MarketerMasterObj.email = email;
					MarketerMasterObj.url = url;
			
					//alert(JSON.stringify(MarketerMasterObj)+" nnn"+0);
			
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/marketer/addMarketer.htm",MarketerMasterObj, function(response) {
								$('#pleasewaitModal').modal('hide');
								var status = JSON.parse(response);
								chngeResultStat(status);
							});
				} else {// for edit
					var MarketerMasterObj = {};
					MarketerMasterObj.id = marketer_id;
					MarketerMasterObj.code = marketer_code;
					MarketerMasterObj.name = marketer_name;
					MarketerMasterObj.address = addrs;
					MarketerMasterObj.phone = phn;
					MarketerMasterObj.fax = fax;
					MarketerMasterObj.email = email;
					MarketerMasterObj.url = url;
					
					//alert(JSON.stringify(MarketerMasterObj)+"edit");
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/marketer/editMarketer.htm",MarketerMasterObj, function(response) {
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
					BASE_URL + "/marketer/deleteMarketer/" + id + ".htm",
					function(response) {
						$('#pleasewaitModal').modal('hide');
						if (response == 'success') {
							document.getElementById('confirmmessagecont').innerHTML = getMarketerText.dataSucDelete;
							showConfirmModal();
						} else {
							document.getElementById('confirmmessagecont').innerHTML = getMarketerText.dataNotDelete;
							showConfirmModal();
						}
					}, null);
}

function targetURL() {
	location.href = BASE_URL + '/marketer/loadmarketer.htm';
}