function fieldValidation(field_names) {
	var error_counter = 0;
	var first_error_field = "";
	for ( var i = 0; i < field_names.length; i++) {
		var field_id = field_names[i][0];
		var field_name = field_names[i][1];
		var field_value = $("#" + field_id + "").val();
		if (field_value == 0 || field_value == "" || field_value == null) {
			if (error_counter == 0) {
				first_error_field = field_name;
			}
			if (error_counter > 0) {
				document.getElementById('alertMsg').innerHTML = first_error_field + " " + getFieldText.fieldReq;
			} else {
				document.getElementById('alertMsg').innerHTML = field_name + " " + getFieldText.fieldReq;
			}
			error_counter++;
		}

	}
	return error_counter;
}

function fieldValidationWithAlertDivId(	field_names,
										alertIdName) {
	var error_counter = 0;
	var first_error_field = "";
	for ( var i = 0; i < field_names.length; i++) {
		var field_id = field_names[i][0];
		var field_name = field_names[i][1];
		var field_value = $("#" + field_id + "").val();
		if (field_value == 0 || field_value == "" ||  field_value == null) {
			if (error_counter == 0) {
				first_error_field = field_name;
			}
			if (error_counter > 0) {
				document.getElementById(alertIdName).innerHTML = first_error_field + " " + getFieldText.fieldReq;
			} else {
				document.getElementById(alertIdName).innerHTML = field_name + " " + getFieldText.fieldReq;
			}
			error_counter++;
		}

	}
	return error_counter;
}

function strtDtGrtEndDt() {
	$("#search_btn").click(function() {
		if ((new Date($('#stdate').val()).getTime() > new Date($('#enddate').val()).getTime())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.frmdtGrtEnddt;
			e.preventDefault();
		} else {
			$("#searchForm").submit();
		}
	});
}

function chngeResultStat(status) {
	if (status.id > 0) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.greaterthanzero;
		showConfirmModal();
	} else if (status.id == 0) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
		showConfirmModal();
	} else if (status.id == -1) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusone;
		showConfirmModal();
	} else if (status.id == -2) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.minustwo;
		showConfirmModal();
	} else if (status.id == -3) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusthree;
		showConfirmModal();
	}else if (status.id == -10) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.mailsentsuccess;
		showConfirmModal();
	}else if (status.id == -11) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.dataPostSuccess;
		showConfirmModal();
	}else if (status.id == -12) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.dataCloseSuccess;
		showConfirmModal();
	}else if (status.id == -14) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.emailNotFoundError;
		showConfirmModal();
	}else {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
		showConfirmModal();
	}

}

function chngeResultStatForNewItem(status) {
	if (status.id > 0) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.greaterthanzero;
		showConfirmModalNewItem();
	} else if (status.id == 0) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.zero;
		showConfirmModalNewItem();
	} else if (status.id == -1) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.minusone;
		showConfirmModalNewItem();
	} else if (status.id == -2) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.minustwo;
		showConfirmModalNewItem();
	} else if (status.id == -3) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.minusthree;
		showConfirmModalNewItem();
	}else if (status.id == -10) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.mailsentsuccess;
		showConfirmModalNewItem();
	} 
	else if (status.id == -11) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.dataPostSuccess;
		showConfirmModalNewItem();
	}
	else if (status.id == -12) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.dataCloseSuccess;
		showConfirmModalNewItem();
	}else {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.zero;
		showConfirmModalNewItem();
	}

}



function empty(val) {



    return (val === undefined || val == null || val.trim().length <= 0) ? true : false;
}




// new added 23.4.2019
function changeChildStore(storeId){
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/home/changeStore/" + storeId + ".htm", function(resp) {
	console.log(resp);
	if(resp == "success")
		location.reload(true);
	}, null);
}