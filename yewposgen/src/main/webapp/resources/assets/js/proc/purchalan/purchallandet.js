var charReg = /^\s*[a-zA-Z0-9\s]+\s*$/; // Restrict all special characters
function clearHeaderDiv()
{
	$("#header_div").find('input:text').val('');
	$("#header_div").find('input:hidden').val('');
	$("#purHistoryDiv").addClass("hide");
	$("#add_btn").removeClass("hide");
	$("#edit_btn").addClass("hide");
	$("#itemid").val("");
	$("#dprcnt").val($("#vendordis").val());
	$("#item_name").attr("readonly",false);
	$("#item_name").focus();
	document.getElementById('alertMsg').innerHTML = "";

	$("#add_btn_ret").removeClass("hide");
	$("#edit_btn_ret").addClass("hide");
	$("#itemid_ret").val("");
	$("#dprcnt_ret").val($("#vendordis").val());
	$("#item_name_ret").attr("readonly",false);
	$("#item_name_ret").focus();

	$("#freeCheck").attr("checked",false);
	$("#addNewItemBtn").removeClass("hide");
	$("#editNewItemBtn").addClass("hide");
	$("#editItemLabel").addClass("hide");
	$("#newItemLabel").removeClass("hide");

	$("#batch_label").addClass("hide");
	$("#exp_label").addClass("hide");
	$("#ratio_label").addClass("hide");
	$("#mrp_label").addClass("hide");
	$("#salerate_label").addClass("hide");
	$("#location_label").addClass("hide");
	//$("#dis_label").addClass("hide");
	//$("#disamt_label").addClass("hide");

	$("#batch_no_td").addClass("hide");
	$("#exp_td").addClass("hide");
	$("#ratio_td").addClass("hide");
	$("#mrp_td").addClass("hide");
	$("#sale_rate_val").addClass("hide");
	$("#locationval_td").addClass("hide");
	$("#free").attr("readonly",false);
	//$("#discprcnt_td").addClass("hide");
	//$("#discamt_td").addClass("hide");
}
function itemDetailView(trId,batchNo) {
	document.getElementById('alertMsg').innerHTML = "";
	$("#item_name").attr("readonly",true);
	$("#purbarcode").attr("readonly",true);
	$("#item_name").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_item_name').text());
	$("#batch_no").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_batch_no').text());
	$("#exp").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_exp').text());
	$("#pqty").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_pqty').text());
	$("#lqty").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_lqty').text());
	$("#ratio").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_ratio').text());
	$("#free").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_free').text());
	$("#mrp").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_mrp').text());
	$("#rate").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_rate').text());
	$("#sale_rate").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_saleRate').text());
	$("#ma").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_ma').text());
	$("#grp").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_grp').text());
	$("#sch").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_sch').text());
	$("#total").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_amnt').text());
	$("#mfg").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_mfg').text());
	$("#edpercnt").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_edprcnt').text());
	$("#ed").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_ed').text());
	$("#taxprcnt").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_taxprcnt').text());
	$("#tax").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_tax').text());
	$("#purTaxId").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_taxid').text());
    $("#item_taxTypeId").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_taxTypeId').text());
	$("#purtaxmode").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_taxmode').text());
	$("#purisgrptax").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_isgrptax').text());
	$("#vatprcnt").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_vatprcnt').text());
	$("#vat").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_vat').text());
	$("#dprcnt").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_dprcnt').text());
	$("#disc").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_disc').text());
	$("#id").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_id').text());
	$("#itemid").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_itemid').text());
	$("#punitid").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_punitid').text());
	$("#tblrow_id").val(trId+"_"+batchNo);
	$("#purbarcode").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_sku').text());
	$("#purHsnCode").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_hsn').text());
	$("#purorderid").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_poid').text());
	$("#expmonth").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_expmonth').text());
	$("#mfd").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_mfd').text());
	$("#location").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_location').text());
	$("#serialnumberreq").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_slnoreq').text());
	/*var slnos=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_slnos').text();
	var sno=slnos.split(",");
	for(var i=0;i<sno.length;i++){
		//alert("slnos="+sno[i]);
		$("#textbox"+(i+1)).val(sno[i]);
	}*/
	if($("#tblrow_" + trId+"_"+batchNo).find('#tbl_isFree').text()=="Y")
	{
		$("#rate").attr("readonly",true);
		$("#freeCheck").prop('checked', true);
		$("#free").attr("readonly",true);
	}
	else
	{
		$("#rate").attr("readonly",false);
		$("#freeCheck").prop("checked",false);
		$("#free").attr("readonly",false);
	}
	// is required field check st
	var tbl_item_batchWiseStock_req=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_item_batchWiseStock_req').text();
	var tbl_item_expiryDateRequired_req=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_item_expiryDateRequired_req').text();
	var tbl_item_expiryMonthRequired_req=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_item_expiryMonthRequired_req').text();
	var tbl_item_dualStockRequired_req=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_item_dualStockRequired_req').text();
	var tbl_item_mrpRequired_req=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_item_mrpRequired_req').text();
	var tbl_item_locationRequired_req=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_item_locationRequired_req').text();
	var tbl_item_priceListRequired_req=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_item_priceListRequired_req').text();
	/*console.log("tbl_item_batchWiseStock_req="+tbl_item_batchWiseStock_req);
	console.log("tbl_item_expiryDateRequired_req="+tbl_item_expiryDateRequired_req);
	console.log("tbl_item_expiryMonthRequired_req="+tbl_item_expiryMonthRequired_req);
	console.log("tbl_item_dualStockRequired_req="+tbl_item_dualStockRequired_req);
	console.log("tbl_item_mrpRequired_req="+tbl_item_mrpRequired_req);
	console.log("tbl_item_locationRequired_req="+tbl_item_locationRequired_req);
	console.log("tbl_item_priceListRequired_req="+tbl_item_priceListRequired_req);*/
	if(tbl_item_batchWiseStock_req==0){
		$("#batch_no_td").addClass('hide');
		$("#batch_label").addClass('hide');
		//$("#batch_no").val(0);
	}else if(tbl_item_batchWiseStock_req==1){
		$("#batch_no_td").removeClass('hide');
		$("#batch_label").removeClass('hide');
		//$("#batch_no").val(0);
	}else{
		$("#batch_no_td").removeClass('hide');
		$("#batch_label").removeClass('hide');
		//$("#batch_no").val(0);
	}

	if(tbl_item_expiryDateRequired_req==0){
		$("#exp_td").addClass('hide');
		$("#exp_label").addClass('hide');
		$("#exp_month_label").addClass('hide');
		//$("#exp").val();
	}else if(tbl_item_expiryDateRequired_req==1){
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
		$("#exp_month_label").removeClass('hide');
		//$("#exp").val();
	}else{
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
		$("#exp_month_label").removeClass('hide');
	//	$("#exp").val();
	}

	if(tbl_item_expiryMonthRequired_req==0){
		$("#exp_month_label").addClass('hide');
		$("#expmonth_val").addClass('hide');
		$("#mfd_label").addClass('hide');
		$("#mfd_val").addClass('hide');
		//$("#expmonth").val(0);
	}else if(tbl_item_expiryMonthRequired_req==1){
		$("#exp_month_label").removeClass('hide');
		$("#expmonth_val").removeClass('hide');
		$("#mfd_label").removeClass('hide');
		$("#mfd_val").removeClass('hide');
		//$("#expmonth").val(0);
	}else{
		$("#exp_month_label").removeClass('hide');
		$("#expmonth_val").removeClass('hide');
		$("#mfd_label").removeClass('hide');
		$("#mfd_val").removeClass('hide');
		//$("#expmonth").val(0);
	}

	if(tbl_item_dualStockRequired_req==0){
		$("#ratio_val").addClass('hide');
		$("#ratio_label").addClass('hide');
		//$("#ratio").val(itemdetval.conversion);
	}else if(tbl_item_dualStockRequired_req==1){
		$("#ratio_val").removeClass('hide');
		$("#ratio_label").removeClass('hide');
		//$("#ratio").val(itemdetval.conversion);
	}else{
		$("#ratio_val").removeClass('hide');
		$("#ratio_label").removeClass('hide');
		//$("#ratio").val(itemdetval.conversion);
	}


	if(tbl_item_locationRequired_req==0){
//		var seslocid=$("#seslocationid").val();
		var seslocid=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_location').text();
//		alert(seslocid);
		$("#location_label").addClass('hide');
		$("#locationval_td").addClass('hide');
//		$("#location").val(seslocid);
		$("#location > [value=" + seslocid + "]").attr("selected", "true");
	}else if(tbl_item_locationRequired_req==1){
//		var seslocid=$("#seslocationid").val();
		var seslocid=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_location').text();
//		alert(seslocid);
		$("#location_label").removeClass('hide');
		$("#locationval_td").removeClass('hide');
//		$("#location").val(seslocid);
		$("#location > [value=" + seslocid + "]").attr("selected", "true");
	}else{
//		var seslocid=$("#seslocationid").val();
		var seslocid=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_location').text();
		$("#location_label").removeClass('hide');
		$("#locationval_td").removeClass('hide');
		$("#location > [value=" + seslocid + "]").removeAttr("selected");
	}

	if(tbl_item_priceListRequired_req==0){
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
		$("#sale_rate_val").addClass('hide');
		$("#salerate_label").addClass('hide');
		//$("#mrp").val();
	}else if(tbl_item_priceListRequired_req==1){
		if($("#isMrpEnableFlag").val()==1)
		{
			$("#mrp_td").removeClass('hide');
			$("#mrp_label").removeClass('hide');
		}
		else
		{
			$("#mrp_td").addClass('hide');
			$("#mrp_label").addClass('hide');
		}
		$("#sale_rate_val").removeClass('hide');
		$("#salerate_label").removeClass('hide');
		//$("#mrp").val();
	}else{
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
		$("#sale_rate_val").removeClass('hide');
		$("#salerate_label").removeClass('hide');
		//$("#mrp").val();
	}

	if(tbl_item_mrpRequired_req==0){
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
		//$("#mrp").val();
	}else if(tbl_item_mrpRequired_req==1){
		if($("#isMrpEnableFlag").val()==1)
		{
			$("#mrp_td").removeClass('hide');
			$("#mrp_label").removeClass('hide');
		}
		//$("#mrp").val();
	}else{
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
		//$("#mrp").val();
	}
	//is required field check end
	$("#edit_btn").removeClass("hide");
	$("#add_btn").addClass("hide");
	$("#editNewItemBtn").removeClass("hide");
	$("#addNewItemBtn").addClass("hide");
	$("#editItemLabel").removeClass("hide");
	$("#newItemLabel").addClass("hide");

	$("#header_pur_tbl").removeClass("hide");
	$("#header_ret_tbl").addClass("hide");


}

function getvendordisval() {
	var selvendor = $("#seldistributor").val();
	var disperc = selvendor.split("_")[1];
	$("#vendordis").val(parseFloat(disperc).toFixed(2));
	$("#dprcnt").val(parseFloat(disperc).toFixed(2));
	var qty = $("#pqty").val();
	var free = $("#free").val();
	var rate = $("#rate").val();
	var taxprcnt = $("#taxprcnt").val();
	if (rate == "") {
		rate = 0;
	}
	//discount calculation
	if (disperc == ""  || qty == "") {

	} else {
		var disval = qty * rate * disperc / 100;
		$("#disc").val(parseFloat(disval).toFixed(2));
	}

	//tax calculation
	if (taxprcnt == ""|| rate == "") {

	} else {
		//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
		var taxval = ((Number(rate) * (Number(qty))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
		$("#tax").val(parseFloat(taxval).toFixed(4));
	}
}

function expiryCalculation(expiry)
{
	/*function normalizeYear(year){
	    // Century fix
	    var YEARS_AHEAD = 10;
	    if (year<100){
	        var nowYear = new Date().getFullYear();
	        year += Math.floor(nowYear/100)*100;
	        if (year > nowYear + YEARS_AHEAD){
	            year -= 100;
	        } else if (year <= nowYear - 100 + YEARS_AHEAD) {
	            year += 100;
	        }
	    }
	    return year;
	}


	    var match=$('#exp').val().match(/^\s*(0?[1-9]|1[0-2])\/(\d\d|\d{4})\s*$/);
	    if (!match){
	       // alert('Input string isn\'t match the expiration date format or date fragments are invalid.')
	    	document.getElementById('confirmmessagecont').innerHTML = "exp date fragments are invalid.";
			$(this).focus();
			$("#confirmval").val(-2);
			$('#exp').val("");
			//return false;
	    }
	    var exp = new Date(normalizeYear(1*match[2]),1*match[1]-1,1).valueOf();
	    var now=new Date();
	    var currMonth = new Date(now.getFullYear(),now.getMonth(),1).valueOf();
	    if (exp<=currMonth){
	    	document.getElementById('confirmmessagecont').innerHTML = "Item Expired.";
			$(this).focus();
			$("#confirmval").val(-2);
			$('#exp').val("");
			//return false;
	    } else {
	    	if($("#expalertrequiremnt").val()==0)
	    	{
	    		$("#confirmval").val("no exp alert");
	    	}
	    	else
	    	{
	    		// alert('Valid');
		    	var currentTime = new Date()

		    	// returns the month (from 0 to 11)
		    	var currMonth = currentTime.getMonth() + 1

		    	// returns the year (four digits)
		    	var currYear = currentTime.getFullYear();

		    	var arr = expiry.split('/');
		    	expMonth = arr[0];
		    	expYear = 20+arr[1];

		    	numberOfMonths = (expYear - currYear) * 12 + (expMonth - currMonth);
		    	yr= Math.floor(numberOfMonths/12);
		    	month = numberOfMonths % 12;
		    	if(yr>0 && month>0)
		    	{
		    		document.getElementById('confirmmessagecont').innerHTML = "This item expires within "+yr+" years "+month+" months from the system date";
		    	}
		    	else if(yr==0)
		    	{
		    		document.getElementById('confirmmessagecont').innerHTML = "This item expires within "+month+" months from the system date";
		    	}
		    	else
		    	{
		    		document.getElementById('confirmmessagecont').innerHTML = "This item expires within "+yr+" years from the system date";
		    	}
			    $("#confirmval").val(-3);
		    	$("#confirmval").val(-1);
		    	showConfirmModal();
				document.getElementById('alertMsg').innerHTML = "";
	    	}

	    };
	    if($("#confirmval").val()=="no exp alert")
		{}
	    else
	    {
	    	showConfirmModal();
			document.getElementById('alertMsg').innerHTML = "";
	    }*/
}

function getpurhistoryofitem(itemId)
{
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/purinv/getpurhistoryofitem/" + itemId + "/0.htm", function(resp) {
		var obj = jQuery.parseJSON(resp);
		createItemPurHistoryDetails(obj);
	}, null);
}

function ValidationForRet() {
	var counter = 0;
	var rate_field = $("#rate_ret_label").text();
	var pqty_field = $("#pqty_ret_label").text();
	var bill_field = $("#bill_label").text();

	var field_names = [ ["billno",bill_field], [ "pqty_ret", pqty_field ], [ "rate_ret", rate_field ] ];

	if (fieldValidation(field_names) > 0) {
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#pqty_ret").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in "+pqty_field;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		/*if(document.getElementById("pqty_ret").value.indexOf('.') != -1){
			document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.notDecimalChk;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{*/
			if($("#pqty_ret").val()<0)
			{
				document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.checkNegative;
				$(this).focus();
				counter = 1;
				return counter;
				return false;
			}
			else
			{
				counter = 0;
				document.getElementById('alertMsg').innerHTML = "";
			}
		//}
	}

	if (isNaN($("#rate_ret").val())) {
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in Rate";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	return counter;
}

function Validation()
{
	var counter = 0;

	var bill_field = $("#bill_label").text();

	var vendor_field = $("#vendor_label").text();

	var batch_field = $("#batch_label").text();

	var exp_field = $("#exp_label").text();

	var mrp_field = $("#mrp_label").text();

	var rate_field = $("#rate_label").text();

	var pqty_field = $("#pqty_label").text();

	var ratio_field = $("#ratio_label").text();

//	var field_names = [["billno",bill_field],["seldistributor",vendor_field],["batch_no",batch_field],["exp",exp_field],["pqty",pqty_field],["ratio",ratio_field],["mrp",mrp_field],["rate",rate_field]];
	var field_names = [["billno",bill_field],["seldistributor",vendor_field],["pqty",pqty_field],["rate",rate_field]];
	if(fieldValidation(field_names)>0)
		{
			counter = 1;
			return counter;
			return false;
		}
	else
		{
			counter =0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	if(isNaN($("#ratio").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in "+ratio_field;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		if($("#ratio").val()<0)
		{
			document.getElementById('alertMsg').innerHTML = ratio_field+" "+getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}

	if(isNaN($("#pqty").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in "+pqty_field;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		/*if(document.getElementById("pqty").value.indexOf('.') != -1){
			document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.notDecimalChk;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{*/
			if($("#pqty").val()<0)
			{
				document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.checkNegative;
				$(this).focus();
				counter = 1;
				return counter;
				return false;
			}
			else
			{
				counter = 0;
				document.getElementById('alertMsg').innerHTML = "";
			}
		//}
	}

	if(isNaN($("#free").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Free";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		if($("#free").val()<0)
		{
			document.getElementById('alertMsg').innerHTML = free_field+" "+getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}
	//alert($("#mrp").val());
	if(isNaN($("#mrp").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in MRP";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#rate").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Rate";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#sale_rate").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Sale Rate";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	/*if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#rate").val()))
	{
		document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessRateErr;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}
	if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
	{
		document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessSRateErr;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}*/
	/*if($("#isExclusive").val()==0)
	{
		if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
		{
			document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessSRateErr;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}
	else
	{
		var mop = (Number($("#mrp").val())*100)/(100+Number($("#taxprcnt").val()));
		if(Number(parseFloat(mop).toFixed(2))<Number($("#sale_rate").val()))
		{
			document.getElementById('alertMsg').innerHTML = getPurInvText.sRateGrtrMopErr;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}*/

	/*if(isNaN($("#ma").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Ma%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#edpercnt").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Ed%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#ed").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Ed";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}*/

	if(isNaN($("#taxprcnt").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Tax%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if($("#purTaxId").val()=="" || $("#purTaxId").val()==0)
    {
    	document.getElementById('alertMsg').innerHTML = getPurInvText.noTaxAddErr;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
    }
    else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#vatprcnt").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Vat%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#dprcnt").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in D%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	/*var inputVal = $("#batch_no").val();

    if (!charReg.test(inputVal))
    {
    	document.getElementById('alertMsg').innerHTML = getPurInvText.restrictSpecialCharacterErr;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}*/

	if($("#exp").val())
	{
	/*	function normalizeYear(year){
		    // Century fix
		    var YEARS_AHEAD = 10;
		    if (year<100){
		        var nowYear = new Date().getFullYear();
		        year += Math.floor(nowYear/100)*100;
		        if (year > nowYear + YEARS_AHEAD){
		            year -= 100;
		        } else if (year <= nowYear - 100 + YEARS_AHEAD) {
		            year += 100;
		        }
		    }
		    return year;
		}


		    var match=$('#exp').val().match(/^\s*(0?[1-9]|1[0-2])\/(\d\d|\d{4})\s*$/);
		    if (!match){
		       // alert('Input string isn\'t match the expiration date format or date fragments are invalid.')
		        document.getElementById('alertMsg').innerHTML = "exp date fragments are invalid.";
				$(this).focus();
				counter = 1;
				return counter;
				return false;
		    }
		    var exp = new Date(normalizeYear(1*match[2]),1*match[1]-1,1).valueOf();
		    var now=new Date();
		    var currMonth = new Date(now.getFullYear(),now.getMonth(),1).valueOf();
		    if (exp<=currMonth){
		        //alert('Expired');
		        document.getElementById('alertMsg').innerHTML = "Date Expired.";
				$(this).focus();
				counter = 1;
				return counter;
				return false;
		    } else {
		       // alert('Valid');
		    	counter = 0;
				document.getElementById('alertMsg').innerHTML = "";
		    };


		*/
	}
	return counter;
}

function ValidationForFree()
{
	var counter = 0;

	var bill_field = $("#bill_label").text();

	var vendor_field = $("#vendor_label").text();

	var batch_field = $("#batch_label").text();

	var exp_field = $("#exp_label").text();

	var mrp_field = $("#mrp_label").text();

	var rate_field = $("#rate_label").text();

	var pqty_field = $("#pqty_label").text();

	var ratio_field = $("#ratio_label").text();

	var free_field = $("#free_label").text();

//	var field_names = [["billno",bill_field],["seldistributor",vendor_field],["batch_no",batch_field],["exp",exp_field],["ratio",ratio_field],["free",free_field]];

	var field_names = [["billno",bill_field],["seldistributor",vendor_field]];

	if(fieldValidation(field_names)>0)
		{
			counter = 1;
			return counter;
			return false;
		}
	else
		{
			counter =0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	if(isNaN($("#ratio").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in "+ratio_field;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		if($("#ratio").val()<0)
		{
			document.getElementById('alertMsg').innerHTML = ratio_field+" "+getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}

	if(isNaN($("#pqty").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in "+pqty_field;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		/*if(document.getElementById("pqty").value.indexOf('.') != -1){
			document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.notDecimalChk;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{*/
			if($("#pqty").val()<0)
			{
				document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.checkNegative;
				$(this).focus();
				counter = 1;
				return counter;
				return false;
			}
			else
			{
				counter = 0;
				document.getElementById('alertMsg').innerHTML = "";
			}
		//}
	}

	if(isNaN($("#free").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Free";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		if($("#free").val()<0)
		{
			document.getElementById('alertMsg').innerHTML = free_field+" "+getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}

	if(isNaN($("#mrp").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in MRP";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#rate").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Rate";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#sale_rate").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Sale Rate";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	/*if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#rate").val()))
	{
		document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessRateErr;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
	{
		document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessSRateErr;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}*/

	/*if($("#isExclusive").val()==0)
	{
		if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
		{
			document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessSRateErr;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}
	else
	{
		var mop = (Number($("#mrp").val())*100)/(100+Number($("#taxprcnt").val()));
		if(Number(parseFloat(mop).toFixed(2))<Number($("#sale_rate").val()))
		{
			document.getElementById('alertMsg').innerHTML = getPurInvText.sRateGrtrMopErr;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}

	if(isNaN($("#ma").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Ma%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#edpercnt").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Ed%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#ed").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Ed";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}*/

	if(isNaN($("#taxprcnt").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Tax%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if($("#purTaxId").val()=="" || $("#purTaxId").val()==0)
    {
    	document.getElementById('alertMsg').innerHTML = getPurInvText.noTaxAddErr;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
    }
    else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#vatprcnt").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Vat%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if(isNaN($("#dprcnt").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in D%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

/*	var inputVal = $("#batch_no").val();

    if (!charReg.test(inputVal))
    {
    	document.getElementById('alertMsg').innerHTML = getPurInvText.restrictSpecialCharacterErr;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}*/

	/*if($("#exp").val())
	{
		function normalizeYear(year){
		    // Century fix
		    var YEARS_AHEAD = 10;
		    if (year<100){
		        var nowYear = new Date().getFullYear();
		        year += Math.floor(nowYear/100)*100;
		        if (year > nowYear + YEARS_AHEAD){
		            year -= 100;
		        } else if (year <= nowYear - 100 + YEARS_AHEAD) {
		            year += 100;
		        }
		    }
		    return year;
		}


		    var match=$('#exp').val().match(/^\s*(0?[1-9]|1[0-2])\/(\d\d|\d{4})\s*$/);
		    if (!match){
		       // alert('Input string isn\'t match the expiration date format or date fragments are invalid.')
		        document.getElementById('alertMsg').innerHTML = "exp date fragments are invalid.";
				$(this).focus();
				counter = 1;
				return counter;
				return false;
		    }
		    var exp = new Date(normalizeYear(1*match[2]),1*match[1]-1,1).valueOf();
		    var now=new Date();
		    var currMonth = new Date(now.getFullYear(),now.getMonth(),1).valueOf();
		    if (exp<=currMonth){
		        //alert('Expired');
		        document.getElementById('alertMsg').innerHTML = "Date Expired.";
				$(this).focus();
				counter = 1;
				return counter;
				return false;
		    } else {
		       // alert('Valid');
		    	counter = 0;
				document.getElementById('alertMsg').innerHTML = "";
		    };



	}*/

	return counter;
}

function toCashMemo()
{
	location.href = BASE_URL + '/purinv/loadpurchalan.htm';
}

function showPurItemDelModal(trId) {
	document.getElementById('confirmId').value = trId;
	$('#confirmModal').modal('show');
}

function ExistsOk() {
	$("#header_div").find('input:text').val('');
	$("#header_div").find('input:hidden').val('');
	$("#itemid").val("");
	$("#freeCheck").attr("checked",false);
}
function calculateSpclDisc()
{alert("hi");
	var spdprcnt = 0.0;
	var tbl_discprcnt = 0.0;
	if($("#spldisc").val()!=0.0 || $("#spldisc").val()!=0 || $("#spldisc").val()!=="")
	{
		spdprcnt = $("#spldisc").val();
	}
	else
	{
		spdprcnt = 0.0;
	}
	var grandNetTotal = 0.00;
	$('#peitem tbody tr').each(function() {
		var qty = $(this).find("#tbl_pqty").html();
		var rate = $(this).find("#tbl_rate").html();
		var taxprcnt = $(this).find("#tbl_taxprcnt").html();
		var amount = $(this).find("#tbl_amnt").html();
		tbl_discprcnt = $(this).find("#tbl_predprbfrspdp").html();
		if(spdprcnt==0.0)
		{
			spdprcnt = tbl_discprcnt;
		}
		var disval = 0.00;
		var taxval = 0.00;
		var ltadj = 0.00;
		var free = $("#tbl_free").html();
		if (free == "") {
			free = 0;
		}
		$(this).find("#tbl_dprcnt").html(parseFloat(spdprcnt).toFixed(4));
		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var pqtyPerFree = qty/modFree;
			ltadj = amount/pqtyPerFree;
			// LtAdj calculation end
		}

		//discount calculation
		if (spdprcnt == ""|| qty == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				disval = ((qty * rate)-ltadj) * spdprcnt / 100;
			}
			else
			{
				disval = parseFloat(qty * rate * spdprcnt / 100).toFixed(4);
			}
		}
		$(this).find("#tbl_disc").html(parseFloat(disval).toFixed(4));

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number(disval)) * Number(taxprcnt) / 100;
			}
			else
			{
				//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
				taxval = ((Number(rate) * (Number(qty))) - Number(disval)) * Number(taxprcnt) / 100;
			}
		}
		$(this).find("#tbl_tax").html(parseFloat(taxval).toFixed(4));

		//net amount calculation
		var net_amount = (Number(amount) + Number(taxval)) - Number(disval);
		$(this).find("#tbl_netamt").html(parseFloat(net_amount).toFixed(2));
		grandNetTotal = grandNetTotal + Number(net_amount);
	});
	calculateTotalTax();
	calculateTotalDisc();
	var totnetamnt = Number(grandNetTotal) - Number($("#totltadj").val());
	$("#totnetamnt").val(parseFloat(totnetamnt).toFixed(2));
	var roundednetamnt=Math.round(Number(totnetamnt));
	$("#totnetamnt").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff=Number(roundednetamnt)-Number(totnetamnt);
	$("#roundoff").val(parseFloat(roundoff).toFixed(2));
	calculateBillAmount();

}
function errSpclDiscOk()
{
	$("#spldisc").val(0.0);
	calculateSpclDisc();
}
function DoConfirm() {
	var id = document.getElementById('confirmId').value;
	$("#item_name").attr("readonly",false);
	$("#purbarcode").attr("readonly",false);
	$("#add_btn").removeClass("hide");
	$("#edit_btn").addClass("hide");
	$("#header_div").find('input:text').val('');
	$("#header_div").find('input:hidden').val('');

	if ($("#tblrow_" + id).find("#tbl_mode").text()=="R")
	{
		var deletedAdjAmt = $("#tblrow_" + id).find("#tbl_netamt").text();
		var retAdjAmt = Number($("#retadj").val()) - Number(deletedAdjAmt);
		$("#retadj").val(parseFloat(retAdjAmt).toFixed(2));
	}

	$("#tblrow_" + id).remove();
	var rowCount = 0;
	$('#peitem > tbody  > tr').each(function() {
		if($(this).find('#tbl_mode').text()=="P")
		{
			rowCount++;
		}
	});

	$("#itemcount").text(rowCount);
	$("#itemid").val("");
	$("#item_name").focus();
	$("#freeCheck").attr("checked",false);
	$("#addNewItemBtn").removeClass("hide");
	$("#editNewItemBtn").addClass("hide");
	$("#editItemLabel").addClass("hide");
	$("#newItemLabel").removeClass("hide");
	$("#prevItemCount").val(1);

	$("#add_btn_ret").removeClass("hide");
	$("#edit_btn_ret").addClass("hide");
	$("#itemid_ret").val("");
	$("#item_name_ret").attr("readonly",false);
	$("#barcode_ret").attr("readonly",false);
	chngeMode();

	var count = 0;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="R")
		{
			count=1;
		}
	});
	if(count==0)
	{
		$("#retAdj_btn").attr("disabled",false);
	}
	else
	{
		$("#retAdj_btn").attr("disabled",true);
	}

	calculateGrandTotal();
	calculateTotalMRP();
	//calculateTotalED();
	//calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	calculateTotalLtAdj();

	calculateTotalLtAdjForRet();
	calculateGrandTotalForRet();
	calculateTotalMRPForRet();
	calculateTotalTaxForRet();
	calculateTotalDiscForRet();
	calculateNetTotalForRet();

	calculateNetTotal();
	calculateBillAmount();
	return false;
}

function DoConfirmPurDel() {
	var pinvid = document.getElementById('confirmId').value;

	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.purInvId = pinvid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/deletepurinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			$("#confirmval").val("del1");
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataSucDelete;
			showConfirmModal();
		}
		else if (response == '-7') {
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataInUseErr+" deleted!";
			showConfirmModal();
		}
		else {
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataNotDelete;
			showConfirmModal();
		}

	});
}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function targetURL() {
	if($("#confirmval").val()==-1)
	{
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#dprcnt").val($("#distdiscprcnt").val());
		document.getElementById("alertMsg").innerHTML="";
		location.href = "#";
	}
	else if($("#confirmval").val()=="printBySku")
	{
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#dprcnt").val($("#distdiscprcnt").val());
		$("#item_name").attr("readonly",false);
		$("#purbarcode").attr("readonly",false);
		$("#item_name").focus();
		$("#add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		document.getElementById("alertMsg").innerHTML="";
		location.href = "#";
	}
	else if($("#confirmval").val()==-2){
		location.href = "#";
		$("#exp").focus();
	}
	else if($("#confirmval").val()==-3){
		location.href = "#";
		$("#pqty").focus();
	}
	else if($("#confirmval").val()=="notRedirect"){
		location.href = "#";
	}
	else if($("#confirmval").val()=="spclDiscErr"){
		location.href = "#";
		$("#spldisc").val(0.0);
		calculateSpclDisc();
	}
	else if($("#confirmval").val()=="itemAddOk")
	{
		fillItemDtlsDivFrmModal(itemObj);

		var mrp = $("#mrp").val();
		var qty = 0;
		if($("#pqty").val()=="")
		{
			qty = 0;
		}
		else
		{
			qty = $("#pqty").val();
		}
		var ratio = $("#ratio").val();
		var lqty = $("#lqty").val();
		var taxprcnt = $("#taxprcnt").val();
		var dprcnt = $("#dprcnt").val();
		var ltadj = 0.0;
		var disval = 0.0;
		var taxval = 0.0;
		var free = $("#free").val();
		if (free == "") {
			free = 0;
		}

		if ($('#freeCheck').is(":checked"))
		{
			$("#rate").val(parseFloat(0).toFixed(2));
		}
		else
		{
			//rate calculation
			if (taxprcnt == ""|| mrp == "") {

			}
			else
			{
				var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
				var ptr = mrpWithoutTax*(1-(Number($("#retailerProfitPrcnt").val())/100));
				$("#rate").val(parseFloat(ptr).toFixed(2));
			}
		}



		//sale rate calculation
		if (taxprcnt == ""|| mrp == "") {

		}
		else
		{
			if($("#isExclusive").val()==0)
			{
				$("#sale_rate").val(parseFloat(mrp).toFixed(2));
			}
			else
			{
			var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
			$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));
			}
		}

		var rate = 0.0;
		if($("#rate").val()=="")
		{
			rate = 0.0;
		}
		else
		{
			rate = $("#rate").val();
		}

		//amt calculation
		if (qty == "") {

		} else {
			var amt = qty * rate;
			$("#total").val(parseFloat(amt).toFixed(4));
		}

		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var tot = $("#total").val();
			var pqtyPerFree = qty/modFree;
			ltadj = tot/pqtyPerFree;
			// LtAdj calculation end
		}

		//discount calculation
		if (dprcnt == ""|| qty == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				disval = ((qty * rate)-ltadj) * dprcnt / 100;
			}
			else
			{
				disval = qty * rate * dprcnt / 100;
			}
			$("#disc").val(parseFloat(disval).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			else
			{
				//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
				taxval = ((Number(rate) * (Number(qty))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

		$('#itemmasterModal').modal('hide');
		$("#editNewItemBtn").removeClass("hide");
		$("#addNewItemBtn").addClass("hide");
		location.href = "#";
	}
	else if($("#confirmval").val()=="itemAddErr")
	{
		$('#itemmasterModal').modal('hide');
		location.href = "#";
	}
	else if($("#confirmval").val()=="post")
	{
		location.href = BASE_URL + "/purinv/loadpurinvregister.htm";
	}
	else if($("#confirmval").val()=="del1")
	{
		location.href = BASE_URL + "/purinv/loadpurchalan.htm";
	}
	else
	{
		if ($('input[name=printPurchase]').is(":checked"))
		{
			location.href = BASE_URL + "/purinv/printPurInvoice.htm?backUrl=loadpurchalan&pid=" + $("#confirmval").val();
		}
		else
		{
			location.href = BASE_URL + '/purinv/loadpurchalandet/'+$("#confirmval").val()+'.htm';
		}

	}
}

function fillItemDetailsDiv(itemdetval) {
	console.log(itemdetval.itemName);
	var today = new Date();
	var yyyy = today.getFullYear().toString();
	var mm = (today.getMonth() + 1).toString(); // getMonth() is zero-based
	var dd = today.getDate().toString();
	
	$("#freeCheck").attr("checked",false);
	$("#rate").attr("readonly",false);
	$("#item_name").val(itemdetval.itemName);
	if(itemdetval.batchWiseStock==null||itemdetval.batchWiseStock==0){
		$("#item_batchWiseStock_req").val(0);
		$("#batch_no_td").addClass('hide');
		$("#batch_label").addClass('hide');
		$("#batch_no").val(0);
	}else if(itemdetval.batchWiseStock==1){
		$("#item_batchWiseStock_req").val(1);
		$("#batch_no_td").removeClass('hide');
		$("#batch_label").removeClass('hide');
		$("#batch_no").val(0);
	}else{
		$("#batch_no_td").removeClass('hide');
		$("#batch_label").removeClass('hide');
		$("#batch_no").val(0);
	}

	/*if(itemdetval.expiryDateRequired==null||itemdetval.expiryDateRequired==0){*/
		if(itemdetval.expiryDateRequired==0){
		$("#item_expiryDateRequired_req").val(0);
		$("#exp_td").addClass('hide');
		$("#exp_label").addClass('hide');
		//$("#exp_month_label").addClass('hide');
		$("#exp").val("");
	}else/* if(itemdetval.expiryDateRequired==1)*/{
		$("#item_expiryDateRequired_req").val(1);
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
		//$("#exp_month_label").removeClass('hide');
		$("#exp").val();
		//$("#exp").val(yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]));
	}/*else{
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
		//$("#exp_month_label").removeClass('hide');
		$("#exp").val();
	}*/
	if(itemdetval.expiryMonthRequired==null||itemdetval.expiryMonthRequired==0){
		$("#item_expiryMonthRequired_req").val(0);
		$("#exp_month_label").addClass('hide');
		$("#expmonth_val").addClass('hide');
		$("#mfd_label").addClass('hide');
		$("#mfd_val").addClass('hide');
		$("#expmonth").val(0);
	}else if(itemdetval.expiryMonthRequired==1){
		$("#item_expiryMonthRequired_req").val(1);
		$("#exp_month_label").removeClass('hide');
		$("#expmonth_val").removeClass('hide');
		$("#mfd_label").removeClass('hide');
		$("#mfd_val").removeClass('hide');
		$("#expmonth").val(0);
	}else{
		$("#exp_month_label").removeClass('hide');
		$("#expmonth_val").removeClass('hide');
		$("#mfd_label").removeClass('hide');
		$("#mfd_val").removeClass('hide');
		$("#expmonth").val(0);
	}
	$("#punitid").val(itemdetval.packUnitId);
	$("#pqty").val(itemdetval.packQty);
	$("#lqty").val(itemdetval.looseQty);
	if(itemdetval.dualStockRequired==null||itemdetval.dualStockRequired==0){
		$("#item_dualStockRequired_req").val(0);
		$("#ratio_val").addClass('hide');
		$("#ratio_label").addClass('hide');
		$("#ratio").val(itemdetval.conversion);
	}else if(itemdetval.dualStockRequired==1){
		$("#item_dualStockRequired_req").val(1);
		$("#ratio_val").removeClass('hide');
		$("#ratio_label").removeClass('hide');
		$("#ratio").val(itemdetval.conversion);
	}else{
		$("#ratio_val").removeClass('hide');
		$("#ratio_label").removeClass('hide');
		$("#ratio").val(itemdetval.conversion);
	}
	$("#free").val(itemdetval.freeQty);
	if(itemdetval.mrpRequired==null||itemdetval.mrpRequired==0){
		$("#item_mrpRequired_req").val(0);
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
		$("#mrp").val(itemdetval.mrp);
	}else if(itemdetval.mrpRequired==1){
		$("#item_mrpRequired_req").val(1);
		if($("#isMrpEnableFlag").val()==1)
		{
			$("#mrp_td").removeClass('hide');
			$("#mrp_label").removeClass('hide');
		}
		else
		{
			$("#mrp_td").addClass('hide');
			$("#mrp_label").addClass('hide');
		}
		$("#mrp").val(itemdetval.mrp);
	}else{
		$("#item_mrpRequired_req").val(0);
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
		$("#mrp").val(itemdetval.mrp);
	}
	$("#serialnumberreq").val(itemdetval.serialNoRequired);
	//$("#mrp").val(itemdetval.mrp);
	if(itemdetval.locationRequired==null||itemdetval.locationRequired==0){
		$("#item_locationRequired_req").val(0);
		var seslocid=$("#seslocationid").val();
//		alert(seslocid);
		$("#location_label").addClass('hide');
		$("#locationval_td").addClass('hide');
//		$("#location").val(seslocid);
		$("#location > [value=" + seslocid + "]").attr("selected", "true");
	}else if(itemdetval.locationRequired==1){
		$("#item_locationRequired_req").val(1);
		var seslocid=$("#seslocationid").val();
//		alert(seslocid);
		$("#location_label").removeClass('hide');
		$("#locationval_td").removeClass('hide');
//		$("#location").val(seslocid);
		$("#location > [value=" + seslocid + "]").attr("selected", "true");
	}else{
		var seslocid=$("#seslocationid").val();
		$("#location_label").removeClass('hide');
		$("#locationval_td").removeClass('hide');
		$("#location > [value=" + seslocid + "]").removeAttr("selected");
	}

	/*if(itemdetval.sizeWiseStockRequired==null||itemdetval.sizeWiseStockRequired==0){
		$("#Sizelabel_td").addClass('hide');
		$("#size_td").addClass('hide');
		$("#size > [value=" + 0 + "]").attr("selected", "true");
	}else{
		$("#Sizelabel_td").removeClass('hide');
		$("#size_td").removeClass('hide');
		$("#size > [value=" + 0 + "]").removeAttr("selected");
	}

	if(itemdetval.colourWiseStockRequired==null||itemdetval.colourWiseStockRequired==0){
		$("#Colorlabel_td").addClass('hide');
		$("#color_td").addClass('hide');
//		$("#colour").val(0);
		$("#colour > [value=" + 0 + "]").attr("selected", "true");
	}else{
		$("#Colorlabel_td").removeClass('hide');
		$("#color_td").removeClass('hide');
//		$("#colour").val(0);
		$("#colour > [value=" + 0 + "]").removeAttr("selected");
	}*/

	
	$("#mfd").val(yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]));

	if(itemdetval.priceListRequired==null||itemdetval.priceListRequired==0){
		$("#item_priceListRequired_req").val(0);
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
		$("#sale_rate_val").addClass('hide');
		$("#salerate_label").addClass('hide');
		$("#mrp").val();
	}else if(itemdetval.priceListRequired==1){
		$("#item_priceListRequired_req").val(1);
		if($("#isMrpEnableFlag").val()==1)
		{
			$("#mrp_td").removeClass('hide');
			$("#mrp_label").removeClass('hide');
		}
		else
		{
			$("#mrp_td").addClass('hide');
			$("#mrp_label").addClass('hide');
		}
		$("#sale_rate_val").removeClass('hide');
		$("#salerate_label").removeClass('hide');
		$("#mrp").val();
	}else{
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
		$("#sale_rate_val").removeClass('hide');
		$("#salerate_label").removeClass('hide');
		$("#mrp").val();
	}
	if(itemdetval.saleRate!=0 || itemdetval.saleRate!="")
	{
		$("#sale_rate").val(itemdetval.saleRate);
	}
	else
	{
		$("#sale_rate").val(0.00);
		/*if($("#isExclusive").val()==0)
		{
			$("#sale_rate").val(itemdetval.mrp);
		}
		else
		{
			var mop = (Number(itemdetval.mrp)*100)/(100+Number(itemdetval.taxPercentage));
			$("#sale_rate").val(parseFloat(mop).toFixed(2));
		}*/
	}

	if(itemdetval.rate!=0 || itemdetval.rate!="")
	{
		$("#rate").val(itemdetval.rate);
	}
	else
	{
		$("#rate").val(0.00);
		/*if($("#isExclusive").val()==0)
		{
			$("#rate").val(itemdetval.rate);
		}
		else
		{
			var mop = (Number(itemdetval.mrp)*100)/(100+Number(itemdetval.taxPercentage));
			var ptr = mop*(1-(Number($("#retailerProfitPrcnt").val())/100));
			$("#rate").val(parseFloat(ptr).toFixed(2));
		}*/
	}
	$("#rateNonFree").val(itemdetval.rate);
	if(itemdetval.rate==0)
	{
		$("#ma").val(0);
	}
	else
	{
		$("#ma").val(((itemdetval.mrp-itemdetval.rate)/itemdetval.rate)*100);
	}
	$("#grp").val(itemdetval.grpName);
	$("#grpid").val(itemdetval.grpId);
	$("#sch").val(itemdetval.schdName);
	$("#schid").val(itemdetval.schdId);
	$("#total").val(0);
	$("#mfg").val(itemdetval.manuName);
	$("#mfgid").val(itemdetval.manuId);
	/*$("#edpercnt").val(itemdetval.edPer);
	$("#ed").val(itemdetval.ed);*/
	var tax = Number(Number(Number(itemdetval.rate) * Number((Number(itemdetval.packQty) + Number(itemdetval.freeQty)))) - Number(itemdetval.disc)) * Number(itemdetval.taxPercentage) / 100;
	$("#taxprcnt").val(itemdetval.taxPercentage);
	$("#tax").val(tax);
	$("#purTaxId").val(itemdetval.taxId);
	$("#purtaxmode").val(itemdetval.taxMode);
	$("#purisgrptax").val(itemdetval.isGroupTax);
	/*$("#vatprcnt").val(itemdetval.vatPer);
	$("#vat").val(itemdetval.vat);*/
	$("#dprcnt").val($("#distdiscprcnt").val());
	//$("#dprcnt").val(itemdetval.discPer);
	$("#disc").val(itemdetval.disc);
	$("#id").val($("#tblrow_"+itemdetval.itemId).find('#tbl_id').text());
	$("#itemid").val(itemdetval.itemId);
	$("#purbarcode").val(itemdetval.sku);
	$("#purHsnCode").val(itemdetval.hsnCode);
	 $("#item_taxTypeId").val(itemdetval.taxTypeId);

}

function adjOnMrpRate()
{
	var rateOrmrpValue = $("input[name='rateOrmrp']:checked").val();
	var rowCount = $('#showretadjDetailsTbl >tbody >tr').length;
	if(rowCount>0)
	{
		$('#showretadjDetailsTbl tbody tr').each(function() {
			var itemId = this.id;
			var mrp = $(this).find("#adjmrp_"+itemId).val();
			var rate = $(this).find("#adjrate_"+itemId).val();
			var clq = $(this).find("#adjclq_"+itemId).val();
			var conversion = $(this).find("#adjconversion_" + itemId).val();
			var totamt = 0.0;
			if(rateOrmrpValue=="rate")
			{
				totamt = (rate/conversion)*clq;
			}
			else
			{
				totamt = (mrp/conversion)*clq;
			}
			$(this).find("#adjexpamnt_" + itemId).val(parseFloat(totamt).toFixed(2));
		});
	}
	else
	{
		$("input[name=rateOrmrp][value='rate']").prop("checked",true);
		//$("input[name='rateOrmrp']:checked").val("rate");
	}
}

function openexpadjmod(){
	var rowCount = $('#peitem >tbody >tr').length;
	$("#billamtheaderExpAdj").text("");
	$("#noexpfound").text("");
	$("#retadjexpno").val("");
	if(rowCount>0)
	{
		$("#billamtheaderExpAdj").text(parseFloat($("#totnetamnt").val()).toFixed(2));
		$("#expAdjVendor").text($("#distname").val());
		$("#expVendorIdforAdj").val($("#seldistributor").val());
		$("#expAdjModal").modal("show");
	}
	else{}
}

function openretadjmod(){
	var rowCount = $('#peitem >tbody >tr').length;
	$("#billamtheaderAdj").text("");
	$("#nocashmemofound").text("");
	$("#retadjmemono").val("");
	if(rowCount>0)
	{
		$("#billamtheaderAdj").text(parseFloat($("#totnetamnt").val()).toFixed(2));
		$("#adjVendor").text($("#distname").val());
		$("#vendorIdforAdj").val($("#seldistributor").val());
		$("#retAdjModal").modal("show");
	}
	else{}
	getPrevMemoDetForAdj();
}

function checkAdjAmt(	inputval,
		retid) {
var netamt = $("#retnetamt_" + retid).text();
var preadjamt = $("#retpreadjamt_" + retid).text();
var totamt = Number(netamt) - Number(preadjamt);
console.log(totamt);
if (parseFloat(inputval) > parseFloat(totamt)) {
$("#adjamt_" + retid).val(parseFloat(totamt).toFixed(2));
}

}

function getPrevMemoDetForAdj() {
	$("#prevretadjtbody").text("");
	var CommonRelsetmapperObj = {};
	if ($("#orderno").val() == '') {
	} else {
		CommonRelsetmapperObj.purInvId =$("#orderno").val();

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purret/getprevretadjdetbypid.htm", CommonRelsetmapperObj, function(response) {
			console.log(response);
			var prevretadjlist = JSON.parse(response);
			console.log(prevretadjlist.length);
			if(prevretadjlist.length==0){
			}else{
				for ( var i = 0; i < prevretadjlist.length; i++) {
					var retadj = prevretadjlist[i];
					var starttrline = "<tr id=" + retadj.purchaseReturnId + " >";
					var invno = "<td>" + retadj.invNo + "</td>";
					var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
					var netAmount = "<td>" + parseFloat(retadj.netAmount).toFixed(2) + "</td>";
					var preAdjAmount = "<td>" + parseFloat(retadj.preAdjAmount).toFixed(2) + "</td>";
					var adjAmount = "<td id='prevadjamt_" + retadj.purchaseReturnId + "'>" + parseFloat(retadj.adjAmount).toFixed(2) + "</td>";
					var endtrline = "</tr>";
					createdrowline = starttrline + invno + invdate + netAmount + preAdjAmount + adjAmount + endtrline;
					$("#prevretadjtbody").append(createdrowline);
				}
			}

		});
	}

}

function getMemoDetForAdj() {
	$("#showretadjtbody").text("");

	var CommonRelsetmapperObj = {};
	if ($("#retadjmemono").val() == '') {
	} else {
		CommonRelsetmapperObj.invoiceNo =$("#retadjmemoDoc").val()+$("#retadjmemoFinyr").val()+$("#retadjmemoSlash").val()+ $("#retadjmemono").val();
	}
	CommonRelsetmapperObj.distributorId = $("#vendorIdforAdj").val();

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purret/getretdetforadj.htm", CommonRelsetmapperObj, function(response) {
		console.log(response);
		var retadjlist = JSON.parse(response);
		console.log(retadjlist.length);
		if(retadjlist.length==0){
			$("#nocashmemofound").text("No data found.");
		}else{
			$("#nocashmemofound").text("");
			for ( var i = 0; i < retadjlist.length; i++) {
				var retadj = retadjlist[i];
				var starttrline = "<tr id=" + retadj.purchaseReturnId + " >";
				var invno = "<td>" + retadj.invNo + "</td>";
				var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
				var netAmount = "<td id='retnetamt_" + retadj.purchaseReturnId + "'>" + parseFloat(retadj.netAmount).toFixed(2) + "</td>";
				var preAdjAmount = "<td id='retpreadjamt_" + retadj.purchaseReturnId + "'>" + parseFloat(retadj.preAdjAmount).toFixed(2) + "</td>";
				var adjAmount = "<td><input type='text' size='6' style='line-height: 14px;' id='adjamt_" + retadj.purchaseReturnId + "' value='" + parseFloat(retadj.netAmount - retadj.preAdjAmount).toFixed(2) + "' onkeyup='checkAdjAmt(this.value," + retadj.purchaseReturnId + ")' style'line-height: 14px;'> </td>";//
				var rowadd = "<td><button class='btn btn-success btn-xs' id='adjamtaddbut_" + retadj.purchaseReturnId + "' onclick='javascript:addItemforadj(" + JSON.stringify(retadj) + ");'><i class='fa fa-plus'></i></button></td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + invno + invdate + netAmount + preAdjAmount + adjAmount + rowadd + endtrline;
				$("#showretadjtbody").append(createdrowline);
			}
		}

	});

}

function getExpDetForAdj() {
	$("#showretadjexptbody").text("");

	var CommonRelsetmapperObj = {};
	if ($("#retadjexpno").val() == '') {
	} else {
		CommonRelsetmapperObj.invoiceNo =$("#retadjexpDoc").val()+$("#retadjexpFinyr").val()+$("#retadjexpSlash").val()+ $("#retadjexpno").val();
	}
	CommonRelsetmapperObj.distributorId = $("#expVendorIdforAdj").val();

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purret/getexpdetforadj.htm", CommonRelsetmapperObj, function(response) {
		console.log(response);
		var retadjlist = JSON.parse(response);
		console.log(retadjlist.length);
		if(retadjlist.length==0){
			$("#noexpfound").text("No data found.");
		}else{
			$("#noexpfound").text("");
			for ( var i = 0; i < retadjlist.length; i++) {
				var retadj = retadjlist[i];
				var starttrline = "<tr id=" + retadj.expiryId + " >";
				var invno = "<td>" + retadj.invNo + "</td>";
				var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
				var itemName = "<td>" +retadj.itemName + "</td>";
				var totQty = "<td id='rettotqty_" + retadj.expiryId + "'>" + parseFloat(retadj.stockQty).toFixed(2) + "</td>";
				var mrp = "";
				if($("#isMrpEnableFlag").val()==1)
				{
					mrp = "<td><input type='text' size='6' style='line-height: 14px;' id='adjmrp_" + retadj.expiryId + "' value='" + parseFloat(retadj.mrp).toFixed(2) + "' onkeyup='calculateExpAdjAmt(this.value," + retadj.expiryId + ","+retadj.calculateLooseQty+")' style'line-height: 14px;'> </td>";
				}
				else
				{
					mrp = "<td class='hide'><input type='text' size='6' style='line-height: 14px;' id='adjmrp_" + retadj.expiryId + "' value='" + parseFloat(retadj.mrp).toFixed(2) + "' onkeyup='calculateExpAdjAmt(this.value," + retadj.expiryId + ","+retadj.calculateLooseQty+")' style'line-height: 14px;'> </td>";
				}
				var rate = "<td><input type='text' size='6' style='line-height: 14px;' id='adjrate_" + retadj.expiryId + "' value='" + parseFloat(retadj.rate).toFixed(2) + "' onkeyup='calculateExpAdjAmt(this.value," + retadj.expiryId + ","+retadj.calculateLooseQty+")' style'line-height: 14px;'> </td>";
				var amnt = (Number(parseFloat(retadj.rate).toFixed(2))/Number(parseFloat(retadj.conversion).toFixed(2))) * Number(parseFloat(retadj.calculateLooseQty).toFixed(2));
				var conversion = "<td class='hidden'><input type='hidden' id='adjconversion_" + retadj.expiryId + "' value='" + retadj.conversion + "' > </td>";
				var clq = "<td class='hidden'><input type='hidden' id='adjclq_" + retadj.expiryId + "' value='" + retadj.calculateLooseQty + "' > </td>";
				var pqty = "<td class='hidden'><input type='hidden' id='adjpqty_" + retadj.expiryId + "' value='" + retadj.packQty + "' > </td>";
				var lqty = "<td class='hidden'><input type='hidden' id='adjlqty_" + retadj.expiryId + "' value='" + retadj.looseQty + "' > </td>";
				var expiryDetailsId = "<td class='hidden'><input type='hidden' id='adjexpdetid_" + retadj.expiryId + "' value='" + retadj.expiryDetailsId + "' > </td>";
				var batchno = "<td class='hidden'><input type='hidden' id='adjbatchno_" + retadj.expiryId + "' value='" + retadj.batchNo + "' > </td>";
				var expdtfrmt = "<td class='hidden'><input type='hidden' id='adjexpdtfrmt_" + retadj.expiryId + "' value='" + retadj.expiryDateFormat + "' > </td>";
				var itemid = "<td class='hidden'><input type='hidden' id='adjitemid_" + retadj.expiryId + "' value='" + retadj.itemId + "' > </td>";
				var adjamnt = "<td><input type='text' size='6' style='line-height: 14px;' id='adjexpamnt_" + retadj.expiryId + "' value='" + parseFloat(amnt).toFixed(2) + "' onkeyup='checkExpAdjAmt(this.value," + retadj.expiryId + ","+retadj.calculateLooseQty+")' style'line-height: 14px;'> </td>";
				var rowadd = "<td><button class='btn btn-success btn-xs' id='adjexpamtaddbut_" + retadj.expiryId + "' onclick='javascript:addExpItemforadj(" + JSON.stringify(retadj) + ");'><i class='fa fa-plus'></i></button></td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + invno + invdate + itemName + totQty + mrp + rate + conversion + clq + pqty + lqty + expiryDetailsId + batchno + expdtfrmt + itemid + adjamnt + rowadd + endtrline;
				$("#showretadjexptbody").append(createdrowline);
			}
		}

	});

}

function checkExpAdjAmt(inputval,retid,clq) {
	var rateOrmrpValue = $("input[name='rateOrmrp']:checked").val();
	//var clq = $("#rettotqty_" + retid).text();
	var mrp = $("#adjmrp_" + retid).val();
	var rate = $("#adjrate_" + retid).val();
	var conversion = $("#adjconversion_" + retid).val();
	var totamt = 0.0;
	if(rateOrmrpValue=="rate")
	{
		totamt = (rate/conversion)*clq;
	}
	else
	{
		totamt = (mrp/conversion)*clq;
	}
	console.log(totamt);
	if (parseFloat(inputval) > parseFloat(totamt)) {
		$("#adjexpamnt_" + retid).val(parseFloat(totamt).toFixed(2));
	}

}

function calculateExpAdjAmt(inputval,retid,clq)
{
	var rateOrmrpValue = $("input[name='rateOrmrp']:checked").val();
	//var clq = $("#rettotqty_" + retid).text();
	var mrp = $("#adjmrp_" + retid).val();
	var rate = $("#adjrate_" + retid).val();
	var conversion = $("#adjconversion_" + retid).val();
	var totamt = 0.0;
	/*if(Number(mrp)<Number(rate))
	{
		document.getElementById('expgreaterbillamt').innerHTML = getPurInvText.mrpLessRateErr;
		$(this).focus();
		$("#adjexpamtaddbut_"+retid).attr("disabled",true);
		return false;
	}
	else
	{*/
		document.getElementById('expgreaterbillamt').innerHTML = "";
		$("#adjexpamtaddbut_"+retid).attr("disabled",false);
		if(rateOrmrpValue=="rate")
		{
			totamt = (rate/conversion)*clq;
		}
		else
		{
			totamt = (mrp/conversion)*clq;
		}
		console.log(totamt);
		$("#adjexpamnt_" + retid).val(parseFloat(totamt).toFixed(2));
//	}
}


function DoConfirmPos() {
	var itmid = $("#confirmIdret").val();
	var delType = $("#cnfrmType").val();
	if(delType==1) // 1 for purchase return
	{
		$('#retadjtbody tr#' + itmid).remove();
		$("#adjamtaddbut_" + itmid).show();
		$("#greaterbillamt").text("");
		$("#payretadjamt").val(parseFloat(0).toFixed(2));
		var cardAmt = $("#cardAmt").val();
		var cashamt = $("#cashAmt").val();
		var nettot = $("#nettot").val();
		var totamt = (Number(cashamt) + Number(cardAmt));
		$("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
		calculateAdjAmount();
		calculateRetpreAdjAmount();
		calculateRetTotnteamt();
	}
	else // 0 for expiry return
	{
		$('#retadjexptbody tr#' + itmid).remove();
		$("#adjexpamtaddbut_" + itmid).show();
		$("#expgreaterbillamt").text("");
		calculateAdjExpAmount();
	}
}

function showSelItemDelModal(trId,type) {
	$("#confirmIdret").val(trId);
	$("#cnfrmType").val(type);
	$('#confirmModalPos').modal('show');
}

function addItemforadj(retadj) {
	//$("#retadjtbody").text("");
	var retpresent = 0;
	var totretadjamt = $("#totretadjamt").text();
	var paymodnettot = $("#totnetamnt").val();

	$('#retadjtable > tbody  > tr').each(function() {
		var retid = this.id;
		if (retid == retadj.purchaseReturnId) {
			retpresent = 1;
		}
	});
	if (retpresent == 1) {

	} else {
		var adjamt = $("#adjamt_" + retadj.purchaseReturnId).val();
		totretadjamt = Number(totretadjamt) + Number(adjamt);
		if (parseFloat(totretadjamt) > parseFloat(paymodnettot)) {
			$("#greaterbillamt").text("Adjust return amt : (" + totretadjamt + ") is greater than bill amount :(" + paymodnettot + ")");
		} else {
			$("#greaterbillamt").text("");
			$("#adjamtaddbut_" + retadj.purchaseReturnId).hide();
			//$("#adjamt_"+retadj.saleReturnId).prop('readonly', true);
			var starttrline = "<tr id=" + retadj.purchaseReturnId + " >";
			var invno = "<td>" + retadj.invNo + "</td>";
			var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
			var netAmount = "<td id='retnetamt'>" + parseFloat(retadj.netAmount).toFixed(2) + "</td>";
			var preAdjAmount = "<td id='retpreadjamt'>" + parseFloat(retadj.preAdjAmount).toFixed(2) + "</td>";
			var adjAmount = "<td id='retadjamt'>" + parseFloat(adjamt).toFixed(2) + "</td>";//
			var rowadd = "<td><button class='btn btn-danger btn-xs'  onclick='javascript:showSelItemDelModal(" + retadj.purchaseReturnId + ",1);'><i class='fa fa-trash-o '></i></button></td>";
			var endtrline = "</tr>";
			createdrowline = starttrline + invno + invdate + netAmount + preAdjAmount + adjAmount + rowadd + endtrline;
			$("#retadjtbody").append(createdrowline);
			calculateAdjAmount();
			calculateRetTotnteamt();
			calculateRetpreAdjAmount();
		}

		//var retadj = JSON.parse(clickretadj);
		$("#payretadjamt").val(parseFloat(totretadjamt).toFixed(2));
		var paymodnettot = $("#paymodnettot").val();
		//		$("#paymodnettot").val(parseFloat(paymodnettot-parseFloat(totretadjamt)).toFixed(2));
		$("#creditAmt").val(parseFloat(paymodnettot - parseFloat(totretadjamt)).toFixed(2));
		//$("#nettot").val(parseFloat(paymodnettot-parseFloat(totretadjamt)).toFixed(2));
	}

}

function addExpItemforadj(retadj) {
	var retpresent = 0;
	var totretadjamt = $("#totretexpadjamt").text();
	var paymodnettot = $("#totnetamnt").val();

	$('#retadjexptbody > tbody  > tr').each(function() {
		var retid = this.id;
		if (retid == retadj.expiryId) {
			retpresent = 1;
		}
	});
	if (retpresent == 1) {

	} else {
		var adjamt = $("#adjexpamnt_" + retadj.expiryId).val();
		totretadjamt = Number(totretadjamt) + Number(adjamt);
		if (parseFloat(totretadjamt) > parseFloat(paymodnettot)) {
			$("#expgreaterbillamt").text("Adjust return amt : (" + totretadjamt + ") is greater than bill amount :(" + paymodnettot + ")");
		} else {
			$("#expgreaterbillamt").text("");
			$("#adjexpamtaddbut_" + retadj.expiryId).hide();
			var starttrline = "<tr id=" + retadj.expiryId + " >";
			var invno = "<td>" + retadj.invNo + "</td>";
			var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
			var itemName = "<td>" +retadj.itemName + "</td>";
			var totQty = "<td id='retexptotqty'>" + parseFloat(retadj.stockQty).toFixed(2) + "</td>";
			var mrp = "";
			if($("#isMrpEnableFlag").val()==1)
			{
				mrp = "<td id='retexpmrp'>"+ parseFloat($("#adjmrp_" + retadj.expiryId).val()).toFixed(2) + "</td>";
			}
			else
			{
				if($("#isMrpEnableFlag").val()==1)
				{
					mrp = "<td id='retexpmrp' class='hide'>"+ parseFloat($("#adjmrp_" + retadj.expiryId).val()).toFixed(2) + "</td>";
				}
			}
			var rate = "<td id='retexprate'>" + parseFloat($("#adjrate_" + retadj.expiryId).val()).toFixed(2) + "</td>";
			var conversion = "<td id='retexpconversion' class='hidden'>" + retadj.conversion + "</td>";
			var pqty = "<td id='retexppqty' class='hidden'>" + retadj.packQty + "</td>";
			var lqty = "<td id='retexplqty' class='hidden'>" + retadj.looseQty + "</td>";
			var expdetid = "<td id='retexpdetid' class='hidden'>" + retadj.expiryDetailsId + "</td>";
			var batchno = "<td id='retexpbatchno' class='hidden'>" + retadj.batchNo + "</td>";
			var expdtfrmt = "<td id='retexpdtfrmt' class='hidden'>" + retadj.expiryDateFormat + "</td>";
			var itemid = "<td id='retexpitemid' class='hidden'>" + retadj.itemId + "</td>";
			var adjamnt = "<td id='retexpadjamnt'>" + parseFloat(adjamt).toFixed(2) + "</td>";
			var rowadd = "<td><button class='btn btn-danger btn-xs'  onclick='javascript:showSelItemDelModal(" + retadj.expiryId + ",0);'><i class='fa fa-trash-o '></i></button></td>";
			var endtrline = "</tr>";
			createdrowline = starttrline + invno + invdate + itemName + totQty + mrp + rate + conversion + pqty + lqty + expdetid + batchno + expdtfrmt + itemid + adjamnt + rowadd + endtrline;
			$("#retadjexptbody").append(createdrowline);
			calculateAdjExpAmount();
		}
	}

}

function calculateAdjExpAmount() {
	var rettotadjAmount = 0.00;
	$('#retexpadjtable tbody tr').each(function() {
		var adjAmount = $(this).find("#retexpadjamnt").html();
		rettotadjAmount = rettotadjAmount + Number(adjAmount);
	});
	$("#totretexpadjamt").html(parseFloat(rettotadjAmount).toFixed(2));
}

function calculateAdjAmount() {
	var rettotadjAmount = 0.00;
	$('#retadjtable tbody tr').each(function() {
		var adjAmount = $(this).find("#retadjamt").html();
		rettotadjAmount = rettotadjAmount + Number(adjAmount);
	});
	$("#totretadjamt").html(parseFloat(rettotadjAmount).toFixed(2));
}

function calculateRetTotnteamt() {
	var rettotnteamt = 0.00;
	$('#retadjtable tbody tr').each(function() {
		var netAmount = $(this).find("#retnetamt").html();
		rettotnteamt = rettotnteamt + Number(netAmount);
	});
	$("#totretnetamt").html(parseFloat(rettotnteamt).toFixed(2));
}

function calculateRetpreAdjAmount() {
	var rettotpreAdjAmount = 0.00;
	$('#retadjtable tbody tr').each(function() {
		var preAdjAmount = $(this).find("#retpreadjamt").html();
		rettotpreAdjAmount = rettotpreAdjAmount + Number(preAdjAmount);
	});
	$("#totretpreadjamt").html(parseFloat(rettotpreAdjAmount).toFixed(2));
}

function okRetExpAdjMod() {
	$("#expgreaterbillamt").text("");
	var totretadjamt = $("#totretexpadjamt").text();
	$("#expadj").val(parseFloat(totretadjamt).toFixed(2));
	calculateBillAmount();
	//var nettot = $("#totnetamnt").val();
	//$("#totnetamnt").val(parseFloat(nettot - parseFloat(totretadjamt)).toFixed(2));
}

function okRetAdjMod() {
	$("#greaterbillamt").text("");
	var totretadjamt = $("#totretadjamt").text();
	if($("#prevrtrnadjamnt").val()=="")
	{
		$("#retadj").val(parseFloat(totretadjamt).toFixed(2));
	}
	else
	{
		$("#retadj").val(parseFloat(Number($("#prevrtrnadjamnt").val()) + Number(parseFloat(totretadjamt).toFixed(2))).toFixed(2));
	}
	calculateBillAmount();
	//var nettot = $("#totnetamnt").val();
	//$("#totnetamnt").val(parseFloat(nettot - parseFloat(totretadjamt)).toFixed(2));
}

function closeRetAdjMod() {
	$("#retadjtbody").text("");
	$("#showretadjtbody").text("");
	$("#totretnetamt").html(parseFloat(0).toFixed(2));
	$("#totretadjamt").html(parseFloat(0).toFixed(2));
	$("#totretpreadjamt").html(parseFloat(0).toFixed(2));
	var totretadjamt = $("#prevrtrnadjamnt").val();
	$("#retadj").val(parseFloat(totretadjamt).toFixed(2));
	calculateBillAmount();
}

function closeRetExpAdjMod() {
	$("#retadjexptbody").text("");
	$("#showretadjexptbody").text("");
	$("#totretexpadjamt").html(parseFloat(0).toFixed(2));
}


function calculateGrandTotal() {
	console.log("call calculateGrandTotal");
	var grandtotal = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="P")
		{
			var itmtotal = $(this).find("#tbl_amnt").html();
			grandtotal = grandtotal + Number(itmtotal);
		}
	});
	$("#totgrosamnt").val(parseFloat(grandtotal).toFixed(2));
}
function calculateTotalMRP() {
	var grandtotalMRP = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="P")
		{
			var itmmrp = $(this).find("#tbl_totamnt").html();
			grandtotalMRP = grandtotalMRP + Number(itmmrp);
		}
	});
	$("#totgrosmrp").val(parseFloat(grandtotalMRP).toFixed(2));
}
function calculateTotalED() {
	var grandtotalED = 0.00;
	$('#peitem tbody tr').each(function() {
		var itmed = $(this).find("#tbl_ed").html();
		grandtotalED = grandtotalED + Number(itmed);
	});
	$("#toted").val(parseFloat(grandtotalED).toFixed(4));
}
function calculateTotalVat() {
	var grandtotalVat = 0.00;
	$('#peitem tbody tr').each(function() {
		var itmvat = $(this).find("#tbl_vat").html();
		grandtotalVat = grandtotalVat + Number(itmvat);
	});
	$("#totvatamnt").val(parseFloat(grandtotalVat).toFixed(4));
}
function calculateTotalTax() {
	var grandtotalTax = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="P")
		{
			var itmtax = $(this).find("#tbl_tax").html();
			grandtotalTax = grandtotalTax + Number(itmtax);
		}
	});
	$("#tottaxamnt").val(parseFloat(grandtotalTax).toFixed(4));
}
function calculateTotalDisc() {
	var grandtotalDisc = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="P")
		{
			var itmdisc = $(this).find("#tbl_disc").html();
			grandtotalDisc = grandtotalDisc + Number(itmdisc);
		}
	});
	$("#totdiscamnt").val(parseFloat(grandtotalDisc).toFixed(4));
}

function calculateTotalLtAdj() {
	var grandtotalLtAdj = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="P")
		{
			var itmltadj = $(this).find("#tbl_ltAdj").html();
			grandtotalLtAdj = grandtotalLtAdj + Number(itmltadj);
		}
	});
	$("#totltadj").val(parseFloat(grandtotalLtAdj).toFixed(4));
}

function calculateNetTotal() {

	var spcldis=$("#spldisc").val();
	if(spcldis==""||spcldis==undefined){
		spcldis=0;
	}
	var spldisamt = Number($("#totgrosamnt").val()) * Number(spcldis) / 100;
	$("#spldiscamt").val(parseFloat(spldisamt).toFixed(2));
	//var nettotal =(( Number($("#totgrosamnt").val()) + Number($("#totvatamnt").val()) + Number($("#tottaxamnt").val())) - (Number($("#totdiscamnt").val())+Number(spldisamt))) - Number($("#totltadj").val());
	var nettotal =(( Number($("#totgrosamnt").val()) + Number($("#tottaxamnt").val())) - (Number($("#totdiscamnt").val()))) - Number($("#totltadj").val());
//	var totnetamnt = $("#totnetamnt").val(parseFloat(nettotal).toFixed(2));
	//	$("#roundoff").val(parseFloat(Math.round(Number(nettotal))).toFixed(2));
	var roundednetamnt=Math.round(Number(nettotal));
	console.log("roundednetamnt="+parseFloat(roundednetamnt).toFixed(2));
	$("#totnetamnt").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff=Number(roundednetamnt)-Number(nettotal);
	console.log("roundoff="+parseFloat(roundoff).toFixed(2));
	$("#roundoff").val(parseFloat(roundoff).toFixed(2));
}

function calculateBillAmount() {
	var billamnt =Number($("#totnetamnt").val())-(Number($("#expadj").val()) + Number($("#retadj").val()));
	var roundedbillamnt=Math.round(Number(billamnt));
	$("#billamt").val(parseFloat(roundedbillamnt).toFixed(2));
}

function deletePurchaseInv(){
	var pinvid=$("#orderno").val();
   document.getElementById('confirmId').value=pinvid;
   $('#purDelConfirmModal').modal('show');
}
function postPurchaseInv(){
	if($("#prevItemCount").val()==1)
	{
		$("#confirmval").val(-1);
		document.getElementById('confirmmessagecont').innerHTML = getPurInvText.postBfrUpdate;
		showConfirmModal();
		return false;
	}
	else
	{
		var pinvid=$("#orderno").val();
		var puritemlength = $('#peitem >tbody >tr').length;
		if(puritemlength>0)
		{
			var CommonRelsetmapperObj = {};
			CommonRelsetmapperObj.purInvId = pinvid;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/postpurinv.htm", CommonRelsetmapperObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == '1') {
					$("#confirmval").val("post");
					document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataSucPost;
					showConfirmModal();
				} else {
					$("#confirmval").val(-1);
					document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataNotPost;
					showConfirmModal();
				}

			});
		}
		else
		{
			$("#confirmval").val(2);
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.noItemChckErr;
			showConfirmModal();
		}
	}

}
$(document).ready(function() {

	$("#invmode").change(function(){
		if ($('#invmode').is(":checked")) {
			$("#crnote").attr("readonly",true);
		}
		else
		{
			$("#crnote").attr("readonly",false);
		}
	});

	$("#freeCheck").change(function(){
		//var rate = $("#rateNonFree").val();
		if ($('#freeCheck').is(":checked")) {
			$("#rate").val(0);
			$("#total").val(0);
			$("#vat").val(0);
			$("#disc").val(0);
			$("#ma").val(0);
			$("#tax").val(0);
			$("#rate").attr("readonly",true);
			$("#free").val(0);
			$("#sale_rate").val(0);
			$("#free").attr("readonly",true);
		}
		else
		{
			//$("#rate").val(rate);
			$("#rate").attr("readonly",false);
			$("#free").attr("readonly",false);
		}
	});

	$("#spldisc").keyup(function() {
		/*var spldisamt = Number($("#totgrosamnt").val()) * Number($(this).val()) / 100;
		$("#spldiscamt").val(parseFloat(spldisamt).toFixed(2));
		var newspldisamt = Number($("#totgrosamnt").val()) - Number(spldisamt);
		var nettotal = Number(newspldisamt) + Number($("#totvatamnt").val()) + Number($("#tottaxamnt").val()) - Number($("#totdiscamnt").val());
//		$("#totnetamnt").val(parseFloat(nettotal).toFixed(2));
		var roundednetamnt=Math.round(Number(nettotal));
		console.log("roundednetamnt="+parseFloat(roundednetamnt).toFixed(2));
		$("#totnetamnt").val(parseFloat(roundednetamnt).toFixed(2));
		var roundoff=Number(roundednetamnt)-Number(nettotal);
		console.log("roundoff="+parseFloat(roundoff).toFixed(2));
		$("#roundoff").val(parseFloat(roundoff).toFixed(2));
		calculateBillAmount();*/

		var grandNetTotal = 0.00;
		var grandNetTotal = 0.00;
		$('#peitem tbody tr').each(function() {
			if($(this).find("#tbl_mode").html()=="P")
			{
				var spdprcnt = 0.0;
				var tbl_discprcnt = 0.0;
				if($("#spldisc").val()!=0.0 || $("#spldisc").val()!=0 || $("#spldisc").val()!=="")
				{
					spdprcnt = $("#spldisc").val();
				}
				else
				{
					spdprcnt = 0.0;
				}
				var qty = $(this).find("#tbl_pqty").html();
				var rate = $(this).find("#tbl_rate").html();
				var taxprcnt = $(this).find("#tbl_taxprcnt").html();
				var amount = $(this).find("#tbl_amnt").html();
				tbl_discprcnt = $(this).find("#tbl_predprbfrspdp").html();
				if(spdprcnt==0.0)
				{
					spdprcnt = tbl_discprcnt;
				}
				var disval = 0.00;
				var taxval = 0.00;
				var ltadj = 0.00;
				var free = $(this).find("#tbl_free").html();
				if (free == "") {
					free = 0;
				}
				$(this).find("#tbl_dprcnt").html(parseFloat(spdprcnt).toFixed(4));
				if(((free % 1)!=0) || free<1)
				{
					// LtAdj calculation start
					var modFree =  free % 1;
					var pqtyPerFree = qty/modFree;
					ltadj = amount/pqtyPerFree;
					// LtAdj calculation end
				}

				//discount calculation
				if (spdprcnt == ""|| qty == "") {

				} else {
					if(((free % 1)!=0) || free<1)
					{
						disval = ((qty * rate)-ltadj) * spdprcnt / 100;
					}
					else
					{
						disval = parseFloat(qty * rate * spdprcnt / 100).toFixed(4);
					}
				}
				$(this).find("#tbl_disc").html(parseFloat(disval).toFixed(4));

				//tax calculation
				if (taxprcnt == ""|| rate == "") {

				} else {
					if(((free % 1)!=0) || free<1)
					{
						taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number(disval)) * Number(taxprcnt) / 100;
					}
					else
					{
						//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
						taxval = ((Number(rate) * (Number(qty))) - Number(disval)) * Number(taxprcnt) / 100;
					}
				}
				$(this).find("#tbl_tax").html(parseFloat(taxval).toFixed(4));

				//net amount calculation
				var net_amount = (Number(amount) + Number(taxval)) - Number(disval);
				$(this).find("#tbl_netamt").html(parseFloat(net_amount).toFixed(2));
				grandNetTotal = grandNetTotal + Number(net_amount);
			}
		});
		calculateTotalTax();
		calculateTotalDisc();
		var totnetamnt = Number(grandNetTotal) - Number($("#totltadj").val());
		$("#totnetamnt").val(parseFloat(totnetamnt).toFixed(2));
		var roundednetamnt=Math.round(Number(totnetamnt));
		$("#totnetamnt").val(parseFloat(roundednetamnt).toFixed(2));
		var roundoff=Number(roundednetamnt)-Number(totnetamnt);
		$("#roundoff").val(parseFloat(roundoff).toFixed(2));
		calculateBillAmount();

	});
	//	$("#roundoff").keyup(function() {
	//		var netamt=$("#totnetamnt").val();
	////		console.log(Number($(this).val()));
	////		console.log(netamt);
	//		$("#totnetamnt").val(parseFloat(Number(netamt)-Number($(this).val())).toFixed(2));
	//	});
	$("#vendordis").keyup(function() {
		$("#dprcnt").val(parseFloat($(this).val()).toFixed(2));
		var dprcnt = $("#dprcnt").val();
		var qty = $("#pqty").val();
		var rate = $("#rate").val();
		if (rate == "") {
			rate = 0;
		}
		//discount calculation
		if (dprcnt == "" || qty == "") {

		} else {
			var disval = qty * rate * dprcnt / 100;
			$("#disc").val(parseFloat(disval).toFixed(4));
		}
	});

	/*=================== for Return On Pur Bill =========== */

	//pqty_ret change calculation
	$("#pqty_ret").blur(function() {
		var pqty = $(this).val();
		var itemid = $("#itemid_ret").val();
		var batch_no = $("#batch_no_ret_td").val();
		var item_is_slno = $("#serialnumberreq").val();
		if(item_is_slno==1){
			$(".retSlnoChk").removeClass("hide");
			for ( var i = 1; i <= 30; i++) {
				$("#checkbox"+i).attr('checked', false);
			}
			var chkdslnos=$("#allChkdSlNos_ret").val();
		    var chkdsno=chkdslnos.split(",");

		    var slnos=$("#allSlNos_ret").val();
		    var sno=slnos.split(",");

			if(sno.length<=0){
				$("#currStkGraterModal").modal("show");
				$("#currstkofitm").text(0);
				$("#curstkcheck").val(1);
			}else{
				for ( var i = 0; i < sno.length; i++) {
					$("#textbox"+(i+1)).val(sno[i]);
					for(var m=0;m<chkdsno.length;m++){
						//alert("slnos="+sno[i]);
						if(chkdsno[m]==sno[i]){
							$("#checkbox"+(i+1)).prop("checked",true);
						}

					}
					$("#TextBoxDiv_"+(i+1)).removeClass("hide");
				}
				var itmdetlen=sno.length;
				for ( var i = itmdetlen; i < 30-itmdetlen; i++) {
					$("#TextBoxDiv_"+(i+1)).addClass("hide");
				}
				$("#modalCltype").val(0);
				$("#openSerialModal").modal("show");
			}

		}else
		{
			var qty = $(this).val();
			calForPqtyChng(qty);
		}

	});

	$("#selslno").on("click", function () {
		var modalCltype = $("#modalCltype").val();
		if(modalCltype == 0)// when the modal is called for Return
		{
//			alert("length="+$("input:checkbox[class=chk]:checked").length);
			var length=$("input:checkbox[class=chk]:checked").length ;
			var arr=[];
			var blank=0;
			var vreg=0;
			var regex = new RegExp("^[a-zA-Z0-9]+$");
		    $("input:checkbox[class=chk]:checked").each(function () {
		        arr.push($("#textbox"+$(this).val()).val());
		        if($("#textbox"+$(this).val()).val()==""){
					blank=1;
				}
				if(!regex.test($("#textbox"+$(this).val()).val())){
					vreg=1;
				}
		    });
		    if(blank==1|| vreg==1){
				$("#blankslno").html("Serial number cannot be blank Or Serial number only contain number and alphabet.");
			}
		    else
		    {
			    $("#allChkdSlNos_ret").val(arr);
			    $("#pqty_ret").val(Number(length));
			    calForPqtyChng(Number(length));
				var counter = $("#pqty_ret").val();
				for(var i=1; i<=30; i++){
					$('#textbox' + i).val("");
				}
			    $("#openSerialModal").modal("hide");
			    $("#modalCltype").val(0);
		    }
		}
		if(modalCltype == 1) // when the modal is called for Purchase
		{
			openSlNo();
		}
	});

	function calForPqtyChng(qty)
	{
		var edpercnt = $("#edpercnt").val();
		var dprcnt = $("#dprcnt_ret").val();
		var taxprcnt = $("#taxprcnt_ret").val();
		var vatprcnt = $("#vatprcnt_ret").val();
		var rate = $("#rate_ret").val();
		var mrp = $("#mrp_ret").val();
		var ratio = $("#ratio_ret").val();
		var free = $("#prevfree_ret").val();
		var billpqty = $("#billpqty_ret").val();
		var ltadj = 0.0;
		var disval = 0.0;
		var taxval = 0.0;
		//alert(billpqty);
		if(Number(qty)>Number(billpqty)){
			$("#pqty_ret").val(0);
		}else{
			if (free == "") {
				free = 0;
			}
			if (mrp == "") {
				mrp = 0;
			}

			//amt calculation
			if (rate == "") {

			} else {
				var amt = qty * rate;
				$("#total_ret").val(parseFloat(amt).toFixed(2));
			}
			//set loose qty
			$("#lqty_ret").val(qty * ratio);

			if(((free % 1)!=0) || free<1)
			{
				// LtAdj calculation start
				var amount = $("#total_ret").val();
				var modFree =  free % 1;
				var pqtyPerFree = qty/modFree;
				ltadj = amount/pqtyPerFree;
				rtrnFree = (qty*modFree)/billpqty;
				// LtAdj calculation end
			}
			else{rtrnFree = 0.0;}

			$("#free_ret").val(parseFloat(rtrnFree).toFixed(2)); // set free

			free = rtrnFree;

			//discount calculation
			if (dprcnt == "" || rate == "") {

			} else {
				if(((free % 1)!=0) || free<1)
				{
					disval = parseFloat(((qty * rate)-ltadj) * dprcnt / 100).toFixed(4);
				}
				else
				{
					disval = qty * rate * dprcnt / 100;
				}
				$("#disc_ret").val(parseFloat(disval).toFixed(4));
			}
			//tax calculation
			if (taxprcnt == ""|| rate == "") {

			} else {
				if(((free % 1)!=0) || free<1)
				{
					taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#disc_ret").val())) * Number(taxprcnt) / 100;
				}
				else
				{
					//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
					taxval = ((Number(rate) * (Number(qty))) - Number($("#disc_ret").val())) * Number(taxprcnt) / 100;
				}
				$("#tax_ret").val(parseFloat(taxval).toFixed(4));
			}
		}
	}

	// pqty change calculation
	$("#pqty_ret").keyup(function() {
		var qty = $(this).val();
		calForPqtyChng(qty);
	});

	// rate change calculation
	$("#rate_ret").keyup(function() {
		var rate = $(this).val();
		var dprcnt = $("#dprcnt_ret").val();
		var qty = $("#pqty_ret").val();
		var free = $("#free_ret").val();
		var taxprcnt = $("#taxprcnt_ret").val();
		var ltadj = 0.0;
		var disval = 0.0;
		var taxval = 0.0;

		if (free == "") {
			free = 0;
		}

		//amt calculation
		if (qty == "") {

		} else {
			var amt = qty * rate;
			$("#total_ret").val(parseFloat(amt).toFixed(4));
		}

		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var tot = $("#total_ret").val();
			var pqtyPerFree = qty/modFree;
			ltadj = tot/pqtyPerFree;
			// LtAdj calculation end
		}

		//discount calculation
		if (dprcnt == "" || qty == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				disval = parseFloat(((qty * rate)-ltadj) * dprcnt / 100).toFixed(4);
			}
			else
			{
				disval = qty * rate * dprcnt / 100;
			}
			$("#disc_ret").val(parseFloat(disval).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#disc_ret").val())) * Number(taxprcnt) / 100;
			}
			else
			{
				//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
				taxval = ((Number(rate) * Number(qty)) - Number($("#disc_ret").val())) * Number(taxprcnt) / 100;
			}
			$("#tax_ret").val(parseFloat(taxval).toFixed(4));
		}

	});

	$("#pqty_ret").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#pqty_ret").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in "+pqty_field;
			$(this).focus();
			return false;
		}
		else
		{
			/*if(document.getElementById("pqty_ret").value.indexOf('.') != -1){
				document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.notDecimalChk;
				$(this).focus();
				return false;
			}
			else
			{*/
				if($("#pqty_ret").val()<0)
				{
					document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.checkNegative;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
			//}

		}
	});

	$("#rate_ret").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#rate_ret").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Rate";
			$(this).focus();
			return false;
		}
		else
		{
			/*if(Number(parseFloat($("#mrp_ret").val()).toFixed(2))>=Number($("#rate_ret").val()))
			{
				document.getElementById('alertMsg').innerHTML = "";

			}
			else
			{
				document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessRateErr;
				$(this).focus();
				return false;
			}*/
		}
	});

	/* ======================================================== */

	// pqty change calculation
	$("#pqty").keyup(function() {
		var qty = $(this).val();
		var edpercnt = $("#edpercnt").val();
		var dprcnt = $("#dprcnt").val();
		var taxprcnt = $("#taxprcnt").val();
		var vatprcnt = $("#vatprcnt").val();
		var rate = 0.0;
		var disval = 0.0;
		var taxval = 0.0;
		var ltadj = 0.0;
		if ($('#freeCheck').is(":checked"))
		{
			rate = 0.0;
		}
		else
		{
			rate = $("#rate").val();
		}
		var mrp = $("#mrp").val();
		var ratio = $("#ratio").val();
		var free = $("#free").val();
		if (free == "") {
			free = 0;
		}
		if (mrp == "") {
			mrp = 0;
		}

		//amt calculation
		if (rate == "") {

		} else {
			var amt = qty * rate;
			$("#total").val(parseFloat(amt).toFixed(2));
		}
		//set loose qty
		$("#lqty").val(qty * ratio);
		//ed calculation
		if (edpercnt == "" || mrp == "") {

		} else {
			var edval = qty * mrp * edpercnt / 100;
			$("#ed").val(parseFloat(edval).toFixed(4));
		}
		//vat calculation
		if (vatprcnt == "" || mrp == "") {

		} else {
			//var vatval = (Number(qty) + Number(free)) * mrp * vatprcnt / 100;
			var vatval = (Number(qty) + Number(free)) * rate * vatprcnt / 100;
			$("#vat").val(parseFloat(vatval).toFixed(4));
		}

		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var tot = $("#total").val();
			var pqtyPerFree = qty/modFree;
			ltadj = tot/pqtyPerFree;
			// LtAdj calculation end
		}

		//discount calculation
		if (dprcnt == "" || rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				disval = ((qty * rate)-ltadj) * dprcnt / 100;
			}
			else
			{
				disval = qty * rate * dprcnt / 100;
			}
			$("#disc").val(parseFloat(disval).toFixed(4));
		}
		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			else
			{
				//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
				taxval = ((Number(rate) * (Number(qty))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}
	});

	// mrp change calculation
	$("#mrp").keyup(function() {
		var mrp = $(this).val();
		var edpercnt = $("#edpercnt").val();
		var taxprcnt = $("#taxprcnt").val();
		var vatprcnt = $("#vatprcnt").val();
		var dprcnt = $("#dprcnt").val();
		var qty = $("#pqty").val();
		var free = $("#free").val();
		var disval = 0.0;
		var taxval = 0.0;
		var ltadj = 0.0;
		if (free == "") {
			free = 0;
		}
		var sale_rate = 0.0;
		console.log($("#wholesalerProfitPrcnt").val());
		if (!(taxprcnt == ""|| mrp == ""))
		{
			//rate calculation
			if (!(taxprcnt == ""|| mrp == "")) {

               /* if (isWholesale==1) // whole sale 
                {
                	var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
					var ptr = mrpWithoutTax*(1-(Number($("#retailerProfitPrcnt").val())/100));
					$("#sale_rate").val(parseFloat(ptr).toFixed(2));
					var pts = ptr*(1-(Number($("#wholesalerProfitPrcnt").val())/100));
					$("#rate").val(parseFloat(pts).toFixed(2));
                }
                else{// for pharmachy 

					var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
					var ptr = mrpWithoutTax*(1-(Number($("#retailerProfitPrcnt").val())/100));
					$("#rate").val(parseFloat(ptr).toFixed(2));
				
					if (parseFloat($("#item_taxTypeId").val())==2) {
 					$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));

					}else {
					 $("#sale_rate").val(parseFloat(mrp).toFixed(2));
					}
				


                }*/
				 if (isWholesale==0) //for retail
	                {
					 var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
						var ptr = mrpWithoutTax*(1-(Number($("#retailerProfitPrcnt").val())/100));
						$("#rate").val(parseFloat(ptr).toFixed(2));
					
						if (parseFloat($("#item_taxTypeId").val())==2) {
	 					$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));

						}else {
						 $("#sale_rate").val(parseFloat(mrp).toFixed(2));
						}
					
	                }
	                else{// for wholesale 						
						var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
						var ptr = mrpWithoutTax*(1-(Number($("#retailerProfitPrcnt").val())/100));
						$("#sale_rate").val(parseFloat(ptr).toFixed(2));
						var pts = ptr*(1-(Number($("#wholesalerProfitPrcnt").val())/100));
						$("#rate").val(parseFloat(pts).toFixed(2));

	                }
				

			}
		}
		rate = parseFloat($("#rate").val()).toFixed(2);
		//amt calculation
		if (qty == "") {

		} else {
			var amt = qty * rate;
			$("#total").val(parseFloat(amt).toFixed(4));
		}
/*
		if ($('#freeCheck').is(":checked"))
		{
			$("#rate").val(parseFloat(0).toFixed(2));
		}
		else
		{
			//rate calculation
			if (taxprcnt == ""|| mrp == "") {

			}
			else
			{
				var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
				var ptr = mrpWithoutTax*(1-(Number($("#retailerProfitPrcnt").val())/100));
				$("#rate").val(parseFloat(ptr).toFixed(2));
			}
		}

		//sale rate calculation
		if (taxprcnt == ""|| mrp == "") {

		}
		else
		{
			if($("#isExclusive").val()==0)
			{
				$("#sale_rate").val(parseFloat(mrp).toFixed(2));
			}
			else
			{
			var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
			$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));
			}
		}

		var rate = $("#rate").val();

		//amt calculation
		if (qty == "") {

		} else {
			var amt = qty * rate;
			$("#total").val(parseFloat(amt).toFixed(4));
		}

		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var tot = $("#total").val();
			var pqtyPerFree = qty/modFree;
			ltadj = tot/pqtyPerFree;
			// LtAdj calculation end
		}

		//discount calculation
		if (dprcnt == ""|| qty == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				disval = ((qty * rate)-ltadj) * dprcnt / 100;
			}
			else
			{
				disval = qty * rate * dprcnt / 100;
			}
			$("#disc").val(parseFloat(disval).toFixed(4));
		}

		//ed calculation
		if (edpercnt == "" || qty == "") {

		} else {
			var edval = qty * mrp * edpercnt / 100;
			$("#ed").val(parseFloat(edval).toFixed(4));
		}
		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			else
			{
				//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
				taxval = ((Number(rate) * (Number(qty))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}
		//vat calculation
		if (vatprcnt == "" || qty == "") {

		} else {
			//var vatval = (Number(qty) + Number(free)) * mrp * vatprcnt / 100;
			var vatval = (Number(qty) + Number(free)) * rate * vatprcnt / 100;
			$("#vat").val(parseFloat(vatval).toFixed(4));
		}

		//ma calculation
		if (mrp == ""|| rate == "" || rate == 0) {

		} else {
			var maprcnt = ((Number(mrp)-Number(rate)) / rate)*100;
			$("#ma").val(parseFloat(maprcnt).toFixed(4));
		}*/

	});

	// rate change calculation
	$("#rate").keyup(function() {
		var rate = $(this).val();
		var vatprcnt = $("#vatprcnt").val();
		var dprcnt = $("#dprcnt").val();
		var qty = $("#pqty").val();
		var free = $("#free").val();
		var taxprcnt = $("#taxprcnt").val();
		var mrp = $("#mrp").val();
		var disval = 0.0;
		var taxval = 0.0;
		var ltadj = 0.0;
		if (free == "") {
			free = 0;
		}

		//amt calculation
		if (qty == "") {

		} else {
			var amt = qty * rate;
			$("#total").val(parseFloat(amt).toFixed(4));
		}

		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var tot = $("#total").val();
			var pqtyPerFree = qty/modFree;
			ltadj = tot/pqtyPerFree;
			// LtAdj calculation end
		}

		//discount calculation
		if (dprcnt == "" || qty == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				disval = ((qty * rate)-ltadj) * dprcnt / 100;
			}
			else
			{
				disval = qty * rate * dprcnt / 100;
			}
			$("#disc").val(parseFloat(disval).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			else
			{
				//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
				taxval = ((Number(rate) * (Number(qty))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

		//vat calculation
		if (vatprcnt == ""|| qty == "") {

		} else {
			//var vatval = (Number(qty) + Number(free)) * mrp * vatprcnt / 100;
			var vatval = (Number(qty) + Number(free)) * rate * vatprcnt / 100;
			$("#vat").val(parseFloat(vatval).toFixed(4));
		}

		//ma calculation
		if (mrp == ""|| rate == "" || rate == 0) {

		} else {
			var maprcnt = ((Number(mrp)-Number(rate)) / rate)*100;
			$("#ma").val(parseFloat(maprcnt).toFixed(4));
		}
	});

	// free change calculation
	$("#free").keyup(function() {
		var free = $(this).val();
		var taxprcnt = $("#taxprcnt").val();
		var dprcnt = $("#dprcnt").val();
		var rate = $("#rate").val();
		var qty = $("#pqty").val();
		var mrp = $("#mrp").val();
		var disval = 0.0;
		var taxval = 0.0;
		var ltadj = 0.0;
		if (mrp == "") {
			mrp = 0;
		}

		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var tot = $("#total").val();
			var pqtyPerFree = qty/modFree;
			ltadj = tot/pqtyPerFree;
			// LtAdj calculation end
		}

		//discount calculation
		if (dprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				disval = ((qty * rate)-ltadj) * dprcnt / 100;
			}
			else
			{
				disval = qty * rate * dprcnt / 100;
			}
			$("#disc").val(parseFloat(disval).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			else
			{
				//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
				taxval = ((Number(rate) * (Number(qty))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

	});

	// edpercent change calculation
	$("#edpercnt").keyup(function() {
		var edpercnt = $(this).val();
		var qty = $("#pqty").val();
		var mrp = $("#mrp").val();
		if (mrp == "") {
			mrp = 0;
		}

		//ed calculation
		if (edpercnt == "" || qty == "") {

		} else {
			var edval = qty * mrp * edpercnt / 100;
			$("#ed").val(parseFloat(edval).toFixed(4));
		}

	});

	// ed change calculation
	$("#ed").keyup(function() {
		var ed = $(this).val();
		var qty = $("#pqty").val();
		var mrp = $("#mrp").val();
		if (mrp == "") {
			mrp = 0;
		}

		//edpercnt calculation
		if (ed == ""|| qty == "") {

		} else {
			var edpercntval = (ed*100)/(qty * mrp);
			$("#edpercnt").val(parseFloat(edpercntval).toFixed(4));
		}

	});

	// taxpercent change calculation
	$("#taxprcnt").keyup(function() {
		var taxprcnt = $(this).val();
		var qty = $("#pqty").val();
		var rate = $("#rate").val();
		var mrp = $("#mrp").val();
		var taxval = 0.0;
		var ltadj = 0.0;
		if (mrp == "") {
			mrp = 0;
		}
		var free = $("#free").val();
		if (free == "") {
			free = 0;
		}

		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var tot = $("#total").val();
			var pqtyPerFree = qty/modFree;
			ltadj = tot/pqtyPerFree;
			// LtAdj calculation end
		}

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			else
			{
				//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
				taxval = ((Number(rate) * (Number(qty))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

	});

	// vatpercent change calculation
	$("#vatprcnt").keyup(function() {
		var vatprcnt = $(this).val();
		var rate = $("#rate").val();
		var qty = $("#pqty").val();
		var mrp = $("#mrp").val();
		if (mrp == "") {
			mrp = 0;
		}
		var free = $("#free").val();
		if (free == "") {
			free = 0;
		}
		//vat calculation
		if (qty == "" || mrp == "") {

		} else {
			//var vatval = (Number(qty) + Number(free)) * mrp * vatprcnt / 100;
			var vatval = (Number(qty) + Number(free)) * rate * vatprcnt / 100;
			$("#vat").val(parseFloat(vatval).toFixed(4));
		}

	});

	// discpercent change calculation
	$("#dprcnt").keyup(function() {
		var dprcnt = 0.0;
		var qty = $("#pqty").val();
		var rate = $("#rate").val();
		var free = $("#free").val();
		var taxprcnt = $("#taxprcnt").val();
		var disval = 0.0;
		var taxval = 0.0;
		var ltadj = 0.0;
		if ($(this).val() == "") {
			dprcnt = 0;
		}
		else
		{
			dprcnt=$(this).val();
		}
		if (rate == "") {
			rate = 0;
		}
		if ($('#freeCheck').is(":checked"))
		{
			rate = 0.0;
		}
		else
		{
			rate = $("#rate").val();
		}

		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var tot = $("#total").val();
			var pqtyPerFree = qty/modFree;
			ltadj = tot/pqtyPerFree;
			// LtAdj calculation end
		}

		//discount calculation
		if (qty == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				disval = ((qty * rate)-ltadj) * dprcnt / 100;
			}
			else
			{
				disval = qty * rate * dprcnt / 100;
			}
			$("#disc").val(parseFloat(disval).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			if(((free % 1)!=0) || free<1)
			{
				taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			else
			{
				//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
				taxval = ((Number(rate) * (Number(qty))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

	});

	$("#batch_no").keyup(function()
	{
		var inputVal = $(this).val();

        if (!charReg.test(inputVal))
        {
        	document.getElementById('alertMsg').innerHTML = getPurInvText.restrictSpecialCharacterErr;
			$(this).focus();
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}

	});

	$("#item_name").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/item/autocompleteitem.htm",
					type : "GET",
					data : {
						tagName : request.term
					},
					dataType : "json",
					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.itemName,
								itemCode : v.itemId,
								//tagCode : v.tagCode
								items : v,
							};

						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});
			}
		},
		select : function(	e,
							ui) {
			console.log(ui.item.itemCode);
			console.log(ui.item.label);
			$("#itemid").val(ui.item.itemCode);
			// call new  ajax for item details

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjax(BASE_URL + "/purinv/getitemdetails/" + ui.item.itemCode + "/0.htm", function(resp) {
				console.log(resp);
				var obj = jQuery.parseJSON(resp);
				//console.log(obj[0].packUnitId);
				fillItemDetailsDiv(obj[0]);
			}, null);

			// call new ajax item details end

			// call new  ajax for item History details
			getpurhistoryofitem(ui.item.itemCode);
			// call new ajax item History details end

			$("#editNewItemBtn").removeClass("hide");
			$("#addNewItemBtn").addClass("hide");
			$("#editItemLabel").removeClass("hide");
			$("#newItemLabel").addClass("hide");
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});

	$("#item_name_ret").autocomplete({ // Autocomplete on item name for return
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/item/autocompleteitem.htm",
					type : "GET",
					data : {
						tagName : request.term
					},
					dataType : "json",
					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.itemName,
								itemCode : v.itemId,
								//tagCode : v.tagCode
								items : v,
							};

						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});
			}
		},
		select : function(	e,
							ui) {
			console.log(ui.item.itemCode);
			console.log(ui.item.label);
			$("#itempurdetailitemname").html(ui.item.label);
			// call new  ajax for item details
			getItemDetailsForRet(ui.item.itemCode);
			// call new ajax end

		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});

	var currentDate = new Date();

	$('#invdt').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose: true,
	});

	$('#mfd').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose: true,
	});

	$('#billDate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose: true,
	});
	if(isWholesale!=1)
	{
		$('#exp').datepicker({
			format : 'yyyy-mm-dd',
			//endDate : currentDate,
			autoclose: true,
		}).on('changeDate', function(ev) {
		    calExpMonth();
		});
	}

	$("#cnfrm_cancel_btn").click(function() {
		$("#add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#freeCheck").attr("checked",false);
		$("#addNewItemBtn").removeClass("hide");
		$("#editNewItemBtn").addClass("hide");
		$("#editItemLabel").addClass("hide");
		$("#newItemLabel").removeClass("hide");
	});

	$(".close").click(function() {
		$("#add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#freeCheck").attr("checked",false);
		$("#addNewItemBtn").removeClass("hide");
		$("#editNewItemBtn").addClass("hide");
		$("#editItemLabel").addClass("hide");
		$("#newItemLabel").removeClass("hide");
	});

	$("#clear_btn").click(function() {
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#purHistoryDiv").addClass("hide");
		$("#add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		$("#dprcnt").val($("#distdiscprcnt").val());
		$("#item_name").attr("readonly",false);
		$("#purbarcode").attr("readonly",false);
		$("#item_name").focus();
		document.getElementById('alertMsg').innerHTML = "";
		$("#freeCheck").attr("checked",false);
		$("#addNewItemBtn").removeClass("hide");
		$("#editNewItemBtn").addClass("hide");
		$("#editItemLabel").addClass("hide");
		$("#newItemLabel").removeClass("hide");
	});

	$("#ratio").keyup(function() {
		var pqty = $("#pqty").val();
		var ratio = $(this).val();
		var ratio_field = $("#ratio_label").text();
		if(isNaN($("#ratio").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in "+ratio_field;
			$(this).focus();
			return false;
		}
		else
		{
			if(ratio<0)
			{
				document.getElementById('alertMsg').innerHTML = ratio_field+" "+getFieldText.checkNegative;
				$(this).focus();
				return false;
			}
			else
			{
				$("#lqty").val(pqty * ratio);
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
	});

	var pqty_field = $("#pqty_label").text();

	var lqty_field = $("#lqty_label").text();

	var free_field = $("#free_label").text();

	var suppbillamt_field = $("#suppbillamt_label").text();

	$("#suppbillamt").keyup(function()
	{
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#suppbillamt").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in "+suppbillamt_field;
			$(this).focus();
			return false;
		}
		else
		{
			if($("#suppbillamt").val()<0)
			{
				document.getElementById('alertMsg').innerHTML = suppbillamt_field+" "+getFieldText.checkNegative;
				$(this).focus();
				return false;
			}
			else
			{
				if($("#suppbillamt").val()!=0 || $("#suppbillamt").val()!=0.0)
				{
					document.getElementById('alertMsg').innerHTML = "";
					var othadjamt = $("#totnetamnt").val() - $("#suppbillamt").val();
					$("#othadjamt").val(othadjamt);
				}
				else
				{
					$("#othadjamt").val(0);
				}
			}
		}

	});

	$("#pqty").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#pqty").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in "+pqty_field;
			$(this).focus();
			return false;
		}
		else
		{
			/*if(document.getElementById("pqty").value.indexOf('.') != -1)
			{
				document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.notDecimalChk;
				$(this).focus();
				return false;
			}
			else
			{*/
				if($("#pqty").val()<0)
				{
					document.getElementById('alertMsg').innerHTML = pqty_field+" "+getFieldText.checkNegative;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
			//}
		}
	});

	$("#free").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#free").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Free";
			$(this).focus();
			return false;
		}
		else
		{
			if($("#free").val()<0)
			{
				document.getElementById('alertMsg').innerHTML = free_field+" "+getFieldText.checkNegative;
				$(this).focus();
				return false;
			}
			else
			{
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
	});

	$("#mrp").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#mrp").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in MRP";
			$(this).focus();
			return false;
		}
		else
		{
			/*if(Number(parseFloat($("#mrp").val()).toFixed(2))>=Number($("#rate").val()))
			{
				document.getElementById('alertMsg').innerHTML = "";

			}
			else
			{
				document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessRateErr;
				$(this).focus();
				return false;
			}*/

			/*if($("#isExclusive").val()==0)
			{
				if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
				{
					document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessSRateErr;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
			}
			else
			{
				var mop = (Number($("#mrp").val())*100)/(100+Number($("#taxprcnt").val()));
				if(Number(parseFloat(mop).toFixed(2))<Number($("#sale_rate").val()))
				{
					document.getElementById('alertMsg').innerHTML = getPurInvText.sRateGrtrMopErr;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
			}*/
		}
	});

	$("#rate").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#rate").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Rate";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			/*if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#rate").val()))
			{
				document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessRateErr;
				$(this).focus();
				return false;
			}
			else
			{
				document.getElementById('alertMsg').innerHTML = "";
			}*/
		}
	});

	$("#sale_rate").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#sale_rate").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Sale Rate";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			/*if($("#isExclusive").val()==0)
			{
				if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
				{
					document.getElementById('alertMsg').innerHTML = getPurInvText.mrpLessSRateErr;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
			}
			else
			{
				var mop = (Number($("#mrp").val())*100)/(100+Number($("#taxprcnt").val()));
				if(Number(parseFloat(mop).toFixed(2))<Number($("#sale_rate").val()))
				{
					document.getElementById('alertMsg').innerHTML = getPurInvText.sRateGrtrMopErr;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
			}*/

		}
	});

	$("#ma").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#ma").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Ma%";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});

	$("#edpercnt").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#edpercnt").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Ed%";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});

	$("#taxprcnt").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#taxprcnt").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Tax%";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});

	$("#dprcnt").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#dprcnt").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in D%";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});

	$("#vatprcnt").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#vatprcnt").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Vat%";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});

	$("#spldisc").keyup(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#spldisc").val()))
		{
			$("#confirmval").val("spclDiscErr");
			document.getElementById('confirmmessagecont').innerHTML = getFieldText.numberCheck+" in Spl.Disc%";
			showConfirmModal();
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('confirmmessagecont').innerHTML = "";
		}
	});

	$("#toted").val(parseFloat($("#toted").val()).toFixed(4));
	$("#totvatamnt").val(parseFloat($("#totvatamnt").val()).toFixed(4));
	$("#tottaxamnt").val(parseFloat($("#tottaxamnt").val()).toFixed(4));
	$("#totdiscamnt").val(parseFloat($("#totdiscamnt").val()).toFixed(4));
	$("#totgrosmrp").val(parseFloat($("#totgrosmrp").val()).toFixed(2));
	$("#totgrosamnt").val(parseFloat($("#totgrosamnt").val()).toFixed(2));
	var rowCount = $('#peitem >tbody >tr').length;
	if(rowCount>=0)
	{
		$("#itemcount").text(rowCount);
		$('#peitem >tbody >tr').each(function(){
			$(this).find('#tbl_ed').text(parseFloat($(this).find('#tbl_ed').text()).toFixed(4));
			$(this).find('#tbl_tax').text(parseFloat($(this).find('#tbl_tax').text()).toFixed(4));
			$(this).find('#tbl_vat').text(parseFloat($(this).find('#tbl_vat').text()).toFixed(4));
			$(this).find('#tbl_disc').text(parseFloat($(this).find('#tbl_disc').text()).toFixed(4));
			$(this).find('#tbl_amnt').text(parseFloat($(this).find('#tbl_amnt').text()).toFixed(2));
		});
	}
	else
	{
		$("#itemcount").text(0);
	}

	$("#edit_btn").click(function() {

		var isFree = "";
		var ltadj = 0;
		var free = 0;

		if($("#free").val()=="" || $("#free").val() == 0)
		{
			free = 0;
		}
		else
		{
			free = $("#free").val();
		}

		if ($('#freeCheck').is(":checked")) {
			if(ValidationForFree()==1)
			{
				return false;
			}
			else
			{
				isFree = "Y";
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
		else
		{
			if(Validation()==1)
			{
				return false;
			}
			else
			{
				isFree = "N";
				if(((free % 1)!=0) || free<1)
				{
					// LtAdj calculation start
					var modFree =  free % 1;
					var pqty = $("#pqty").val();
					var tot = $("#total").val();
					var pqtyPerFree = pqty/modFree;
					ltadj = tot/pqtyPerFree;
					// LtAdj calculation end
				}
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
		var rate = 0;
		if($("#rate").val()=="")
		{
			rate = 0;
		}
		else
		{
			rate = $("#rate").val();
		}

		var sale_rate = 0;
		if($("#sale_rate").val()=="")
		{
			sale_rate = 0;
		}
		else
		{
			sale_rate = $("#sale_rate").val();
		}

		var ma = 0;
		if($("#ma").val()=="")
		{
			ma = 0;
		}
		else
		{
			ma = $("#ma").val();
		}

		var item_disc = 0.0;
		var item_dprcnt = 0.0;

		if(($("#disc").val() == 0) || ($("#disc").val() == ""))
		{
			item_disc = 0.0;
		}
		else
		{
			item_disc = $("#disc").val();
		}

		if(($("#dprcnt").val() == 0) || ($("#dprcnt").val() == ""))
		{
			item_dprcnt = 0.0;
		}
		else
		{
			item_dprcnt = $("#dprcnt").val();
		}

		var itempresent = 0;
		var trId = $("#tblrow_id").val();
		$('#peitem > tbody  > tr').each(function() {
			//alert("tbl_itemid=" + $(this).find('#tbl_itemid').text());
			//alert("itemid=" + $("#itemid").val());
			//alert("tbl_batch_no=" + $(this).find('#tbl_batch_no').text());
			if ((Number($(this).find('#tbl_itemid').text()) == Number($("#itemid").val())) && ($(this).find('#tbl_batch_no').text()==$("#batch_no").val())) {
				var rowId=(Number($(this).find('#tbl_itemid').text()))+"_"+$(this).find('#tbl_batch_no').text();
				if(rowId==trId)
				{
				}
				else
				{
					itempresent = itempresent+1;
				}
			}
			else
			{
				var newtblrowId="tblrow_"+$("#itemid").val()+"_"+$("#batch_no").val();
				var strng_batch_no="'"+$("#batch_no").val()+"'";
				var onclickFunction="itemDetailView("+$("#itemid").val()+","+strng_batch_no+");";
				$("#tblrow_" + trId).removeAttr('onclick');
				$("#tblrow_" + trId).attr('onclick',onclickFunction);
				$("#tblrow_" + trId).attr("id", newtblrowId);
				trId=$("#itemid").val()+"_"+$("#batch_no").val();
			}
		});

		var totalSlNo = 0;
		var allVal = $("#allVal").val();
		if(allVal=="")
		{
			totalSlNo = 0;
		}
		else
		{
			var sno=allVal.split(",");
			totalSlNo = sno.length;
		}
		if($("#tblrow_" + trId).find('#tbl_slnoreq').text()==1)
		{
			if(totalSlNo!=(Number($("#pqty").val())+Number(Math.floor(free))))
			{
				itempresent=-1;
			}
		}

		if(itempresent>0)
		{
			$('#itemExistsModal').modal('show');
		}
		else if(itempresent==-1)
		{
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.slNoBlankErr;
			showConfirmModal();
			return false;
		}
		else
		{
			var expDate = $("#exp").val();
			   if(expDate == ""){
				   expDate = "NA";
			   }
			//var amnt = $("#pqty").val() * $("#rate").val();
			var totmrp = (Number($("#pqty").val()) + Number(free)) * $("#mrp").val();
			$("#tblrow_" + trId).find('#tbl_item_name').text($("#item_name").val());
			$("#tblrow_" + trId).find('#tbl_batch_no').text($("#batch_no").val());
			$("#tblrow_" + trId).find('#tbl_exp').text(expDate);
			$("#tblrow_" + trId).find('#tbl_pqty').text($("#pqty").val());
			$("#tblrow_" + trId).find('#tbl_lqty').text($("#lqty").val());
			$("#tblrow_" + trId).find('#tbl_ratio').text($("#ratio").val());
			$("#tblrow_" + trId).find('#tbl_free').text(free);
			$("#tblrow_" + trId).find('#tbl_mrp').text($("#mrp").val());
			$("#tblrow_" + trId).find('#tbl_rate').text(rate);
			$("#tblrow_" + trId).find('#tbl_ma').text(ma);
			$("#tblrow_" + trId).find('#tbl_grp').text($("#grp").val());
			$("#tblrow_" + trId).find('#tbl_sch').text($("#sch").val());
			$("#tblrow_" + trId).find('#tbl_amnt').text(parseFloat($("#total").val()).toFixed(2));
			$("#tblrow_" + trId).find('#tbl_totamnt').text(parseFloat(totmrp).toFixed(2));
			$("#tblrow_" + trId).find('#tbl_mfg').text($("#mfg").val());
			//$("#tblrow_" + trId).find('#tbl_edprcnt').text($("#edpercnt").val());
			//$("#tblrow_" + trId).find('#tbl_ed').text($("#ed").val());
			$("#tblrow_" + trId).find('#tbl_taxprcnt').text($("#taxprcnt").val());
			$("#tblrow_" + trId).find('#tbl_tax').text($("#tax").val());
			$("#tblrow_" + trId).find('#tbl_taxid').text($("#purTaxId").val());
			$("#tblrow_" + trId).find('#tbl_taxmode').text($("#purtaxmode").val());
			$("#tblrow_" + trId).find('#tbl_isgrptax').text($("#purisgrptax").val());
			//$("#tblrow_" + trId).find('#tbl_vatprcnt').text($("#vatprcnt").val());
			//$("#tblrow_" + trId).find('#tbl_vat').text($("#vat").val());
			$("#tblrow_" + trId).find('#tbl_dprcnt').text(item_dprcnt);
			$("#tblrow_" + trId).find('#tbl_disc').text(item_disc);
			$("#tblrow_" + trId).find('#tbl_id').text($("#id").val());
			$("#tblrow_" + trId).find('#tbl_itemid').text($("#itemid").val());
			$("#tblrow_" + trId).find('#tbl_punitid').text($("#punitid").val());
			$("#tblrow_" + trId).find('#tbl_isFree').text(isFree);
			$("#tblrow_" + trId).find('#tbl_ltAdj').text(ltadj);
			$("#tblrow_" + trId).find('#tbl_sku').text($("#purbarcode").val());
			$("#tblrow_" + trId).find('#tbl_hsn').text($("#purHsnCode").val());
			$("#tblrow_" + trId).find('#tbl_saleRate').text($("#sale_rate").val());
			//$("#purorderid").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_poid').text());
			var net_amount = (Number($("#total").val()) + Number($("#tax").val())) - Number($("#disc").val());
			$("#tblrow_" + trId).find('#tbl_netamt').text(parseFloat(net_amount).toFixed(2));
			$("#tblrow_" + trId).find('#tbl_poid').text($("#purorderid").val());
			$("#tblrow_" + trId).find('#tbl_predprbfrspdp').text(item_dprcnt);
			$("#tblrow_" + trId).find('#tbl_slnos').text($("#allVal").val());
			$("#tblrow_" + trId).find('#tbl_mfd').text($("#mfd").val());
			$("#tblrow_" + trId).find('#tbl_expmonth').text($("#expmonth").val());
			/*$("#tblrow_" + trId).find('#tbl_exp').text($("#exp").val());*/
			$("#tblrow_" + trId).find('#tbl_taxTypeId').text($("#item_taxTypeId").val());
		}
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#dprcnt").val($("#distdiscprcnt").val());
		$("#add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#purbarcode").attr("readonly",false);
		$("#item_name").attr("readonly",false);
		$("#item_name").focus();
		$("#freeCheck").attr("checked",false);
		$("#itemid").val("");
		$("#addNewItemBtn").removeClass("hide");
		$("#editNewItemBtn").addClass("hide");
		$("#editItemLabel").addClass("hide");
		$("#newItemLabel").removeClass("hide");
		$("#prevItemCount").val(1);

		$("#batch_label").addClass("hide");
		$("#exp_label").addClass("hide");
		$("#ratio_label").addClass("hide");
		$("#mrp_label").addClass("hide");
		$("#salerate_label").addClass("hide");
		$("#location_label").addClass("hide");
		$("#mfd_label").addClass("hide");
		$("#exp_month_label").addClass("hide");
		//$("#dis_label").addClass("hide");
		//$("#disamt_label").addClass("hide");

		$("#batch_no_td").addClass("hide");
		$("#exp_td").addClass("hide");
		$("#ratio_td").addClass("hide");
		$("#mrp_td").addClass("hide");
		$("#sale_rate_val").addClass("hide");
		$("#locationval_td").addClass("hide");
		$("#mfd_val").addClass("hide");
		$("#expmonth_val").addClass("hide");
			$("#free").attr("readonly",false);
		//$("#discprcnt_td").addClass("hide");
		//$("#discamt_td").addClass("hide");

		calculateGrandTotal();
		calculateTotalMRP();
		//calculateTotalED();
		//calculateTotalVat();
		calculateTotalTax();
		calculateTotalDisc();
		calculateTotalLtAdj();
		calculateNetTotal();
		calculateBillAmount();
	});

	$("#add_btn").click(function() {

		var isFree = "";
		var ltadj = 0;
		var free = 0;
		if($("#mrp").val()=="" || $("#mrp").val() == 0)
		{
			$("#mrp").val(0);
		}
		if($("#free").val()=="" || $("#free").val() == 0)
		{
			free = 0;
		}
		else
		{
			free = $("#free").val();
		}
		var rate = 0;
		if($("#rate").val()=="")
		{
			rate = 0;
		}
		else
		{
			rate = $("#rate").val();
		}

		var sale_rate = 0;
		if($("#sale_rate").val()=="")
		{
			sale_rate = 0;
		}
		else
		{
			sale_rate = $("#sale_rate").val();
		}

		var ma = 0;
		if($("#ma").val()=="")
		{
			ma = 0;
		}
		else
		{
			ma = $("#ma").val();
		}

		var item_disc = 0.0;
		var item_dprcnt = 0.0;

		if(($("#disc").val() == 0) || ($("#disc").val() == ""))
		{
			item_disc = 0.0;
		}
		else
		{
			item_disc = $("#disc").val();
		}

		if(($("#dprcnt").val() == 0) || ($("#dprcnt").val() == ""))
		{
			item_dprcnt = 0.0;
		}
		else
		{
			item_dprcnt = $("#dprcnt").val();
		}
		if ($('#freeCheck').is(":checked")) {
			if(ValidationForFree()==1)
			{
				return false;
			}
			else
			{
				isFree = "Y";
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
		else
		{
			if(Validation()==1)
			{
				return false;
			}
			else
			{
				isFree = "N";
				if(((free % 1)!=0) || free<1)
				{
					// LtAdj calculation start
					var modFree =  free % 1;
					var pqty = $("#pqty").val();
					var tot = $("#total").val();
					var pqtyPerFree = pqty/modFree;
					ltadj = tot/pqtyPerFree;
					// LtAdj calculation end
				}
				document.getElementById('alertMsg').innerHTML = "";
			}
		}


		var rowCount = 0;
		$('#peitem > tbody  > tr').each(function() {
			if($(this).find('#tbl_mode').text()=="P")
			{
				rowCount++;
			}
		});

		$("#itemcount").text(rowCount + 1);
		$("#prevItemCount").val(1);
		var itempresent = 0;
		$('#peitem > tbody  > tr').each(function() {
			console.log("tbl_itemid=" + $(this).find('#tbl_itemid').text());
			console.log("itemid=" + $("#itemid").val());
			if ((Number($(this).find('#tbl_itemid').text()) == Number($("#itemid").val())) && ($(this).find('#tbl_batch_no').text()==$("#batch_no").val())) {
				itempresent = 1;
			}
		});
		if (Number($("#itemid").val()) == 0) {
			return false;
		}

		var totalSlNo = 0;
		var allVal = $("#allVal").val();
		if(allVal=="")
		{
			totalSlNo = 0;
		}
		else
		{
			var sno=allVal.split(",");
			totalSlNo = sno.length;
		}
		if($("#serialnumberreq").val()==1)
		{
			if(totalSlNo!=(Number($("#pqty").val())+Number(Math.floor(free))))
			{
				itempresent=2;
			}
		}

		if (itempresent == 1) {
			$('#itemExistsModal').modal('show');
		}
		else if(itempresent==2)
		{
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.slNoBlankErr;
			showConfirmModal();
			return false;
		}
		else {
			$("#purHistoryDiv").addClass("hide");//open purchase history details popup

			var strng_batch_no="'"+$("#batch_no").val()+"'";
			var strng_tbrow_id = "'"+$("#itemid").val()+"_"+$("#batch_no").val()+"'";
			var tr = '<tr id="tblrow_' + $("#itemid").val() +'_'+$("#batch_no").val() + '" style="cursor: pointer;" onclick="javascript:itemDetailView(' + $("#itemid").val() +','+strng_batch_no+ ');">';
			var totmrp = (Number($("#pqty").val()) + Number(free)) * $("#mrp").val();
			var row1 = '<td id="tbl_item_name">' + $("#item_name").val() + '</td>';
			var row2 = '<td id="tbl_batch_no">' + $("#batch_no").val() + '</td>';
			if($("#item_expiryDateRequired_req").val()==1){
				   var expDate = $("#exp").val();
				   if(expDate == ""){expDate = 'NA';}
				var row3 = '<td id="tbl_exp">' + expDate + '</td>';
			}else{
				var row3 = '<td id="tbl_exp">NA</td>';
			}
			//var row3 = '<td id="tbl_exp">' + $("#exp").val() + '</td>';
			var row4 = '<td id="tbl_pqty" class="numeric">' + $("#pqty").val() + '</td>';
			//var row5 = '<td id="tbl_lqty" class="numeric">' + $("#lqty").val() + '</td>';
		//	var row6 = '<td id="tbl_ratio" class="numeric">' + $("#ratio").val() + '</td>';
			var row7 = '<td id="tbl_free" class="numeric">' + free + '</td>';
			var previousFree = '<td id="tbl_prevfree" class="hide">0</td>';

			var row9 = '<td id="tbl_rate" class="numeric">' + rate + '</td>';
			var row10 = '<td id="tbl_ed" class="numeric">' + $("#ed").val() + '</td>';
			var row11 = '<td id="tbl_tax" class="numeric">' + $("#tax").val() + '</td>';
			var taxTypeId = '<td id="tbl_taxTypeId" class="hide">' + $("#item_taxTypeId").val() + '</td>';
			var row12 = '<td id="tbl_vat" class="numeric">' + $("#vat").val() + '</td>';
			var row13 = '<td id="tbl_disc" class="numeric">' + item_disc + '</td>';
			var row14 = '<td id="tbl_amnt" class="numeric">' + $("#total").val() + '</td>';
			var row15 = '<td id="tbl_totamnt" class="numeric hide">' + parseFloat(totmrp).toFixed(2) + '</td>';
			var net_amount = (Number($("#total").val()) + Number($("#tax").val())) - Number($("#disc").val());
			var netamt = '<td id="tbl_netamt" class="numeric">' + parseFloat(net_amount).toFixed(2) + '</td>';
			var row16 = '<td><button class="btn btn-theme04 btn-xs" onclick="javascript:showPurItemDelModal(' + strng_tbrow_id + ');"><i class="fa fa-trash-o "></i></button></td>';
			var row17 = '<td id="tbl_ma" class="hide">' + ma + '</td>';
			var row18 = '<td id="tbl_grp" class="hide">' + $("#grp").val() + '</td>';
			var row19 = '<td id="tbl_sch" class="hide">' + $("#sch").val() + '</td>';
			var row20 = '<td id="tbl_mfg" class="hide">' + $("#mfg").val() + '</td>';
			var row21 = '<td id="tbl_edprcnt" class="hide">' + $("#edpercnt").val() + '</td>';
			var row22 = '<td id="tbl_taxprcnt">' + $("#taxprcnt").val() + '</td>';
			var taxid = '<td id="tbl_taxid" class="hide">' + $("#purTaxId").val() + '</td>';
			var taxmode = '<td id="tbl_taxmode" class="hide">' + $("#purtaxmode").val() + '</td>';
			var isgrptax = '<td id="tbl_isgrptax" class="hide">' + $("#purisgrptax").val() + '</td>';
			var row23 = '<td id="tbl_vatprcnt" class="hide">' + $("#vatprcnt").val() + '</td>';
			var row24 = '<td id="tbl_dprcnt">' + item_dprcnt + '</td>';
			var row25 = '<td id="tbl_id" class="hide">' + $("#id").val() + '</td>';
			var row26 = '<td id="tbl_itemid" class="hide">' + $("#itemid").val() + '</td>';
			var grpid = '<td id="tbl_grpid" class="hide">' + $("#grpid").val() + '</td>';
			var schid = '<td id="tbl_schid" class="hide">' + $("#schid").val() + '</td>';
			var mfgid = '<td id="tbl_mfgid" class="hide">' + $("#mfgid").val() + '</td>';
			var packunitid = '<td id="tbl_punitid" class="hide">' + $("#punitid").val() + '</td>';
			var isFreeRow = '<td id="tbl_isFree" class="hide">' + isFree + '</td>';
			var ltadjRow = '<td id="tbl_ltAdj" class="hide">' + ltadj + '</td>';
			var sku = '<td id="tbl_sku" class="hide">' + $("#purbarcode").val() + '</td>';
			var hsn = '<td id="tbl_hsn" class="hide">' + $("#purHsnCode").val() + '</td>';
			var tbl_sale_rate = '<td id="tbl_saleRate" class="numeric">' + sale_rate + '</td>';
			var poId = '<td id="tbl_poid" class="hide">0</td>';
			var predprbfrspdp = '<td id="tbl_predprbfrspdp" class="hide">'+item_dprcnt+'</td>';
			var billpackqty = "<td class='hide' id='purrettabpqtyhide'>0</td>";
			var mode = "<td class='hide' id='tbl_mode'>P</td>";
			var returnReason = "<td class='hide' id='tbl_ret_reason'></td>";

			// for common retail
			var expmonth = '<td id="tbl_expmonth" class="hide">' + $("#expmonth").val() + '</td>';
			if($("#item_expiryMonthRequired_req").val()==1){
		         var mfd = '<td id="tbl_mfd" class="hide">' + $("#mfd").val() + '</td>';
				}else{
				 var mfd = '<td id="tbl_mfd" class="hide">NA</td>';
				}
			//var mfd = '<td id="tbl_mfd" class="hide">' + $("#mfd").val() + '</td>';
			var location = '<td id="tbl_location" class="hide">' + $("#location").val() + '</td>';

			var row5 = '<td id="tbl_lqty" class="numeric hide">' + $("#lqty").val() + '</td>';
			var row6 = '<td id="tbl_ratio" class="numeric hide">' + $("#ratio").val() + '</td>';
			var row8 = '<td id="tbl_mrp" class="numeric hide">' + $("#mrp").val() + '</td>';
			var row15 = '<td id="tbl_totamnt" class="numeric hide">' + parseFloat(totmrp).toFixed(2) + '</td>';
			var slnos = '<td id="tbl_slnos" class="numeric hide">' + $("#allVal").val() + '</td>';
			var slnoreq = '<td id="tbl_slnoreq" class="numeric hide">' + $("#serialnumberreq").val() + '</td>';
			var item_batchWiseStock_req = '<td id="tbl_item_batchWiseStock_req" class="numeric hide">' + $("#item_batchWiseStock_req").val() + '</td>';
			var item_expiryDateRequired_req = '<td id="tbl_item_expiryDateRequired_req" class="numeric hide">' + $("#item_expiryDateRequired_req").val() + '</td>';
			var item_expiryMonthRequired_req = '<td id="tbl_item_expiryMonthRequired_req" class="numeric hide">' + $("#item_expiryMonthRequired_req").val() + '</td>';
			var item_dualStockRequired_req = '<td id="tbl_item_dualStockRequired_req" class="numeric hide">' + $("#item_dualStockRequired_req").val() + '</td>';
			var item_mrpRequired_req = '<td id="tbl_item_mrpRequired_req" class="numeric hide">' + $("#item_mrpRequired_req").val() + '</td>';
			var item_locationRequired_req = '<td id="tbl_item_locationRequired_req" class="numeric hide">' + $("#item_locationRequired_req").val() + '</td>';
			var item_priceListRequired_req = '<td id="tbl_item_priceListRequired_req" class="numeric hide">' + $("#item_priceListRequired_req").val() + '</td>';
			var printAction = '<td></td>';
			//tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14 + row15 + row16 + row17 + row18 + row19 + row20 + row21 + row22 + row23 + row24 + row25 + row26+grpid+schid+mfgid + packunitid + isFreeRow + ltadjRow +'</tr>';
			//tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + previousFree + row8 + row9 + tbl_sale_rate + row22 + row11 + row24 + row13 + row14 + row15 + netamt + row16 + row17 + row18 + row19 + row20 + taxid + taxmode + isgrptax + row25 + row26 +grpid+schid+mfgid + packunitid + isFreeRow + ltadjRow + sku + hsn + poId + predprbfrspdp + billpackqty + mode + returnReason + '</tr>';
			tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + previousFree + row9 + tbl_sale_rate + row22 + row11 +taxTypeId+ row24 + row13 + row14  + netamt + row16 + printAction + row17 + row18 + row19 + row20 + taxid + taxmode + isgrptax + row25 + row26 +grpid+schid+mfgid + packunitid + isFreeRow + ltadjRow + sku + hsn + poId + predprbfrspdp + billpackqty + mode + returnReason + expmonth+mfd+location+ row8 + row15+slnos+slnoreq + item_batchWiseStock_req+item_expiryDateRequired_req+item_expiryMonthRequired_req+item_dualStockRequired_req+item_mrpRequired_req+item_locationRequired_req+item_priceListRequired_req+'</tr>';
			//tr = tr + row1 + row2 + row3 + row4 + row7 + previousFree + row9 + tbl_sale_rate + row22 + row11 + row24 + row13 + row14 + netamt + row16 + row17 + row18 + row19 + row20 + taxid + taxmode + isgrptax + row25 + row26 + row27 + row28 + row29 + packunitid + isFreeRow + ltadjRow + sku + hsn + poId + predprbfrspdp + billpackqty + mode + returnReason+expmonth+mfd+location+ row5 + row6+ row8 + row15+slnos+slnoreq + item_batchWiseStock_req+item_expiryDateRequired_req+item_expiryMonthRequired_req+item_dualStockRequired_req+item_mrpRequired_req+item_locationRequired_req+item_priceListRequired_req+'</tr>';
			$("#peitem tbody").prepend(tr);
			$("#item_name").focus();
			$("#printBySkuBtn").addClass("hide");
			calculateGrandTotal();
			calculateTotalMRP();
			//calculateTotalED();
			//calculateTotalVat();
			calculateTotalTax();
			calculateTotalDisc();
			calculateTotalLtAdj();
			calculateNetTotal();
			calculateBillAmount();
		}

		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#itemid").val("");
		$("#dprcnt").val($("#distdiscprcnt").val());
		$("#freeCheck").attr("checked",false);
		$("#addNewItemBtn").removeClass("hide");
		$("#editNewItemBtn").addClass("hide");
		$("#editItemLabel").addClass("hide");
		$("#newItemLabel").removeClass("hide");
		$("#prevItemCount").val(1);

		$("#batch_label").addClass("hide");
		$("#exp_label").addClass("hide");
		$("#ratio_label").addClass("hide");
		$("#mrp_label").addClass("hide");
		$("#salerate_label").addClass("hide");
		$("#location_label").addClass("hide");
		$("#mfd_label").addClass("hide");
		$("#exp_month_label").addClass("hide");
		//$("#dis_label").addClass("hide");
		//$("#disamt_label").addClass("hide");

		$("#batch_no_td").addClass("hide");
		$("#exp_td").addClass("hide");
		$("#ratio_td").addClass("hide");
		$("#mrp_td").addClass("hide");
		$("#sale_rate_val").addClass("hide");
		$("#locationval_td").addClass("hide");
		$("#mfd_val").addClass("hide");
		$("#expmonth_val").addClass("hide");
		$("#free").attr("readonly",false);

		//$("#discprcnt_td").addClass("hide");
		//$("#discamt_td").addClass("hide");

	});

	$("#save_btn").click(function() {
		var allpuchasereturndetails = [];
		var allexpiryreturndetails = [];
		//var rowCount = $('#peitem >tbody >tr').length;
		var rowCount = $('#itemcount').text();
		if(rowCount>0)
		{
		if($("#itemid").val()==0 || $("#itemid").val()=="")
		{
			if(isNaN($("#spldisc").val()))
			{
				$("#confirmval").val(-1);
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.numberCheck+" in Spl.Disc%";
				showConfirmModal();
				/*document.getElementById('alertMsgforSave').innerHTML = getFieldText.numberCheck+" in Spl.Disc%";
				$('#footerErrorModal').modal('show');*/
				return false;
			}
			else
			{
				document.getElementById('confirmmessagecont').innerHTML = "";
			}

			var spclDisc = 0.0;
			if($("#spldisc").val() == "" || $("#spldisc").val() == 0)
			{
				spclDisc = 0.0
			}
			else
			{
				spclDisc = $("#spldisc").val();
			}

			var suppbillamt_field = $("#suppbillamt_label").text();

			document.getElementById('alertMsg').innerHTML = "";

			if(isNaN($("#suppbillamt").val()))
			{
				$("#confirmval").val(-1);
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.numberCheck+" in "+suppbillamt_field;
				showConfirmModal();
				return false;
			}
			else
			{
				if($("#suppbillamt").val()<0)
				{
					$("#confirmval").val(-1);
					document.getElementById('confirmmessagecont').innerHTML = suppbillamt_field+" "+getFieldText.checkNegative;
					showConfirmModal();
					return false;
				}
				else
				{
					document.getElementById('confirmmessagecont').innerHTML = "";
				}
			}

			var suppbillamt = 0.0;
			if($("#suppbillamt").val() == "" || $("#suppbillamt").val() == 0)
			{
				suppbillamt = 0.0
			}
			else
			{
				suppbillamt = $("#suppbillamt").val();
			}

			var advamt = 0.0;
			if($("#crnote").val() == "" || $("#crnote").val() == 0)
			{
				advamt = 0.0
			}
			else
			{
				advamt = $("#crnote").val();
			}
			if($("#invmode").is(':checked'))
			{
			}
			else
			{
				if(isNaN($("#crnote").val()))
				{
					$("#confirmval").val(-1);
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.numberCheck+" in "+$("#cr_noteLabel").text();
					showConfirmModal();
					/*document.getElementById('alertMsgforSave').innerHTML = $("#cr_noteLabel").text()+" "+getFieldText.fieldReq;
					$('#footerErrorModal').modal('show');*/
					return false;
				}

			}

			var PurchaseObj = {};
			var PurchaseDetailsObjArr = [];
			var PurchaseReturnObj = {};
			var PurchaseReturnDetailsObjArr = [];
			var retOnBillStat = 0;
			var j = 0;
			var k = 0;
			var retId = 0;
			var retInvNo = "";
			var retInvDt = "";
			var retAdjAmnt = 0.0;

			$("#peitem tr").not('thead tr').each(function(	i,
															v) {
				// for individual item during return on pur bill
				if($(this).find('#tbl_mode').text()=="R")
				{
					var PurchaseRetDetailsObj = {};
					PurchaseRetDetailsObj.itemName = $(this).find('#tbl_item_name').text();
					PurchaseRetDetailsObj.batchNo = $(this).find('#tbl_batch_no').text();
					PurchaseRetDetailsObj.expiryDateFormat = $(this).find('#tbl_exp').text();
					PurchaseRetDetailsObj.packQty = $(this).find('#tbl_pqty').text();
					PurchaseRetDetailsObj.looseQty = 0;//set as zero for back end calculation of rajkumar $(this).find('#tbl_lqty').text();
					PurchaseRetDetailsObj.conversion = $(this).find('#tbl_ratio').text();
					PurchaseRetDetailsObj.itemId = $(this).find('#tbl_itemid').text();
					PurchaseRetDetailsObj.id = 0;// $(this).find('#tbl_id').text();
					PurchaseRetDetailsObj.freeQty = $(this).find('#tbl_free').text();
					PurchaseRetDetailsObj.edPer = 0.0; //$(this).find('#tbl_edprcnt').text();
					PurchaseRetDetailsObj.ed = 0.0; //$(this).find('#tbl_ed').text();
					PurchaseRetDetailsObj.discPer = $(this).find('#tbl_dprcnt').text();
					PurchaseRetDetailsObj.disc = $(this).find('#tbl_disc').text();
					PurchaseRetDetailsObj.amount = $(this).find('#tbl_amnt').text();
					PurchaseRetDetailsObj.mrp = $(this).find('#tbl_mrp').text();
					PurchaseRetDetailsObj.rate = $(this).find('#tbl_rate').text();
					PurchaseRetDetailsObj.tax = 0.0; //$(this).find('#tbl_tax').text();
					PurchaseRetDetailsObj.taxPer = 0.0; //$(this).find('#tbl_taxprcnt').text();
					PurchaseRetDetailsObj.taxPercentage = $(this).find('#tbl_taxprcnt').text();
					PurchaseRetDetailsObj.taxAmount = $(this).find('#tbl_tax').text();
					PurchaseRetDetailsObj.taxId = $(this).find('#tbl_taxid').text();
					PurchaseRetDetailsObj.taxMode = $(this).find('#tbl_taxmode').text();
					PurchaseRetDetailsObj.isGroupTax = $(this).find('#tbl_isgrptax').text();
					PurchaseRetDetailsObj.totAmount = $(this).find('#tbl_totamnt').text();
					PurchaseRetDetailsObj.vat = 0.0; //$(this).find('#tbl_vat').text();
					PurchaseRetDetailsObj.vatPer = 0.0; //$(this).find('#tbl_vatprcnt').text();
					PurchaseRetDetailsObj.grpId = $(this).find('#tbl_grp').text();
					PurchaseRetDetailsObj.schdId = $(this).find('#tbl_sch').text();
					PurchaseRetDetailsObj.manuId = $(this).find('#tbl_mfg').text();
					PurchaseRetDetailsObj.packUnitId = $(this).find('#tbl_punitid').text();
					//PurchaseRetDetailsObj.purchaseId = $(this).find("#tbl_invid").text();
					//PurchaseRetDetailsObj.purchaseInvNo = $(this).find("#tbl_invno").text();
					PurchaseRetDetailsObj.itemLotAdjAmount = $(this).find("#tbl_ltAdj").text();
					PurchaseRetDetailsObj.sku = $(this).find('#tbl_sku').text();
					PurchaseRetDetailsObj.hsnCode = $(this).find('#tbl_hsn').text();
					PurchaseRetDetailsObj.storeId = storeId;
					PurchaseRetDetailsObj.finyrId = finyrId;
					PurchaseRetDetailsObj.companyId = cmpnyId;
					PurchaseRetDetailsObj.createdBy = createdBy;
					PurchaseRetDetailsObj.reasonId = $(this).find("#tbl_ret_reason").text();

					// serialno add

					purDetailsSerialMapperObjArr=[];
					var slnos=$(this).find('#tbl_chkdSlNos').text();
					var slnoarr=slnos.split(",");
					PurchaseRetDetailsObj.tmpMappingId=(i);
					for(var h=0;h<slnoarr.length;h++){
						var purDetailsSerialMapperObj={};
						purDetailsSerialMapperObj.itemId=$(this).find('#tbl_itemid').text();
						purDetailsSerialMapperObj.batchNo=$(this).find('#tbl_batch_no').text();
						purDetailsSerialMapperObj.uniqueIdentifierNo=slnoarr[h];
						purDetailsSerialMapperObj.qty=$(this).find('#tbl_pqty').text();
						purDetailsSerialMapperObj.tmpMappingId=(i);
						if($("#purretid").val()!=0){
							purDetailsSerialMapperObj.purchaseReturnId =$("#purretid").val();
						}
						purDetailsSerialMapperObjArr.push(purDetailsSerialMapperObj);
					}
					PurchaseRetDetailsObj.purchaseReturnDetailsSerialMapper=purDetailsSerialMapperObjArr;

					// slno end

					PurchaseReturnDetailsObjArr[j] = PurchaseRetDetailsObj;
					j++;
					retOnBillStat = 1
				}
				//========================================

				if($(this).find('#tbl_mode').text()=="P")
				{

					var item_disc = 0.0;
					var item_dprcnt = 0.0;

					if(($(this).find('#tbl_disc').text() == 0) || ($(this).find('#tbl_disc').text() == ""))
					{
						item_disc = 0.0;
					}
					else
					{
						item_disc = $(this).find('#tbl_disc').text();
					}

					if(($(this).find('#tbl_dprcnt').text() == 0) || ($(this).find('#tbl_dprcnt').text() == ""))
					{
						item_dprcnt = 0.0;
					}
					else
					{
						item_dprcnt = $(this).find('#tbl_dprcnt').text();
					}

					var PurchaseDetailsObj = {};
					PurchaseDetailsObj.itemName = $(this).find('#tbl_item_name').text();
					PurchaseDetailsObj.batchNo = $(this).find('#tbl_batch_no').text();
					PurchaseDetailsObj.expiryDateFormat = $(this).find('#tbl_exp').text();
					PurchaseDetailsObj.packQty = $(this).find('#tbl_pqty').text();
					PurchaseDetailsObj.looseQty = 0;//set as zero for back end calculation of rajkumar $(this).find('#tbl_lqty').text();
					PurchaseDetailsObj.conversion=$(this).find('#tbl_ratio').text();
					PurchaseDetailsObj.itemId = $(this).find('#tbl_itemid').text();
					PurchaseDetailsObj.id = 0;// $(this).find('#tbl_id').text();
					PurchaseDetailsObj.freeQty = $(this).find('#tbl_free').text();
					PurchaseDetailsObj.edPer = 0.0; //$(this).find('#tbl_edprcnt').text();
					PurchaseDetailsObj.ed = 0.0; //$(this).find('#tbl_ed').text();
					PurchaseDetailsObj.discPer = $(this).find('#tbl_dprcnt').text();
					PurchaseDetailsObj.disc = $(this).find('#tbl_disc').text();
					PurchaseDetailsObj.amount = $(this).find('#tbl_amnt').text();
					PurchaseDetailsObj.mrp = $(this).find('#tbl_mrp').text();
					PurchaseDetailsObj.rate = $(this).find('#tbl_rate').text();
					PurchaseDetailsObj.tax = 0.0; //$(this).find('#tbl_tax').text();
					PurchaseDetailsObj.taxPer = 0.0; //$(this).find('#tbl_taxprcnt').text();
					PurchaseDetailsObj.taxPercentage = $(this).find('#tbl_taxprcnt').text();
					PurchaseDetailsObj.taxAmount = $(this).find('#tbl_tax').text();
					PurchaseDetailsObj.taxId = $(this).find('#tbl_taxid').text();
					PurchaseDetailsObj.taxMode = $(this).find('#tbl_taxmode').text();
					PurchaseDetailsObj.isGroupTax = $(this).find('#tbl_isgrptax').text();
					PurchaseDetailsObj.totAmount = $(this).find('#tbl_totamnt').text();
					PurchaseDetailsObj.totAmount = $(this).find('#tbl_totamnt').text();
					PurchaseDetailsObj.vat = 0.0; //$(this).find('#tbl_vat').text();
					PurchaseDetailsObj.vatPer = 0.0; //$(this).find('#tbl_vatprcnt').text();
					PurchaseDetailsObj.grpId = $(this).find('#tbl_grpid').text();
					PurchaseDetailsObj.schdId = $(this).find('#tbl_schid').text();
					PurchaseDetailsObj.manuId = $(this).find('#tbl_mfgid').text();
					PurchaseDetailsObj.packUnitId = $(this).find('#tbl_punitid').text();
					PurchaseDetailsObj.itemLotAdjAmount = $(this).find('#tbl_ltAdj').text();
					PurchaseDetailsObj.sku = $(this).find('#tbl_sku').text();
					PurchaseDetailsObj.hsnCode = $(this).find('#tbl_hsn').text();
					PurchaseDetailsObj.purchaseNetAmount = $(this).find('#tbl_netamt').text();
					PurchaseDetailsObj.saleRate = $(this).find('#tbl_saleRate').text();

					PurchaseDetailsObj.purchaseOrderId = $(this).find('#tbl_poid').text();
					PurchaseDetailsObj.mfdDate=$(this).find('#tbl_mfd').text();
					PurchaseDetailsObj.locationId=$(this).find('#tbl_location').text();
					if(($(this).find('#tbl_expmonth').text()).toLowerCase() === "nan") {
						PurchaseDetailsObj.expiryMonth=0;
					}
					else {
						PurchaseDetailsObj.expiryMonth=$(this).find('#tbl_expmonth').text();
					}
					PurchaseDetailsObj.taxTypeId=$(this).find('#tbl_taxTypeId').text();
					// serialno add tbl_expmonth
					purchaseDetailsSerialMapperObjArr=[];
					var slnos=$(this).find('#tbl_slnos').text();
					var slnoarr=slnos.split(",");
					PurchaseDetailsObj.tmpMappingId=(i+1);
					for(var h=0;h<slnoarr.length;h++){
						var purchaseDetailsSerialMapperObj={};
						purchaseDetailsSerialMapperObj.itemId=$(this).find('#tbl_itemid').text();
						purchaseDetailsSerialMapperObj.batchNo=$(this).find('#tbl_batch_no').text();
						purchaseDetailsSerialMapperObj.uniqueIdentifierNo=slnoarr[h];
						purchaseDetailsSerialMapperObj.qty=$(this).find('#tbl_pqty').text();
						purchaseDetailsSerialMapperObj.tmpMappingId=(i+1);
						purchaseDetailsSerialMapperObjArr.push(purchaseDetailsSerialMapperObj);
					}
					PurchaseDetailsObj.purchaseDetailsSerialMapper=purchaseDetailsSerialMapperObjArr;
					PurchaseDetailsObjArr[i] = PurchaseDetailsObj;
					k++;
				}
			});

			// for return on pur bill
			if(retOnBillStat==1)
			{
				var roundednetamnt = Math.round(Number($("#retadj").val()));
				console.log("roundednetamnt=" + parseFloat(roundednetamnt).toFixed(2));
				var roundoff = Number(roundednetamnt) - Number($("#retadj").val());
				console.log("roundoff=" + parseFloat(roundoff).toFixed(2));
				$("#roundoff_ret").val(parseFloat(roundoff).toFixed(2));

				//console.log("PurchaseReturnDetailsObjArr:"+PurchaseReturnDetailsObjArr);
				PurchaseReturnObj.purchaseReturnDetails = PurchaseReturnDetailsObjArr;
				PurchaseReturnObj.invDate = $("#invdt").val();

				PurchaseReturnObj.id =0;
				PurchaseReturnObj.distributorId = $("#seldistributor").val().split("_")[0];
				PurchaseReturnObj.edAmount = 0.0; //$("#toted").val();
				PurchaseReturnObj.grossAmount = $("#totgrosamnt_ret").val();
				PurchaseReturnObj.vatAmount = 0.0; //$("#totvatamnt").val();
				PurchaseReturnObj.taxAmount = $("#tottaxamnt_ret").val();
				PurchaseReturnObj.discAmount = $("#totdiscamnt_ret").val();
				PurchaseReturnObj.roundoff = $("#roundoff_ret").val();
				PurchaseReturnObj.netAmount = Number($("#retadj").val());
				PurchaseReturnObj.totalMrp = $("#totgrosmrp_ret").val();
				PurchaseReturnObj.lotAdjAmount = $("#totltadj_ret").val();
				PurchaseReturnObj.remarks = $("#remarks").val();

				/*
				 * add for account

				*/
				//PurchaseReturnObj.creditor_debit_amt
				PurchaseReturnObj.duties_ledger_id= parseInt($('#duties_ledger_id1').val());
				PurchaseReturnObj.round_ledger_id= parseInt($('#round_ledger_id1').val());
				PurchaseReturnObj.purchase_ledger_id= parseInt($('#purchase_ledger_id1').val());
				PurchaseReturnObj.discount_ledger_id= parseInt($('#discount_ledger_id1').val());
				PurchaseReturnObj.lot_ledger_id= parseInt($('#lotadj_ledger_id1').val());
				PurchaseReturnObj.creditor_ledger_id= parseInt($('#creditor_ledger_id1').val());

				console.log("PurchaseRetObj json: " + JSON.stringify(PurchaseReturnObj));
				 var purreturnerro=0;	// for pur return ledger checking

					if (is_acc==1) {// when account enable


						if (PurchaseReturnObj.duties_ledger_id>0 && PurchaseReturnObj.round_ledger_id>0 && PurchaseReturnObj.purchase_ledger_id>0&& PurchaseReturnObj.discount_ledger_id>0&& PurchaseReturnObj.creditor_ledger_id>0&& PurchaseReturnObj.lot_ledger_id>0) {
							purreturnerro=0;
						}else {
							purreturnerro=1;
							$("#confirmval").val(-1);
							document.getElementById('confirmmessagecont').innerHTML = getFieldText.LedgerAccountError;
							showConfirmModal();
							return false;

						}
					}

					if (purreturnerro==0) {
						$('#pleasewaitModal').modal('show');

						/* ================= For save return ============= */

						var ajaxCallObject = new CustomBrowserXMLObject();
						ajaxCallObject.callAjaxPost(BASE_URL + "/purret/createorupdatepurchasereturn.htm", PurchaseReturnObj, function(response) {
							$('#pleasewaitModal').modal('hide');
							console.log("save pur inv ret id=" + response);
							retId = response;
							var CommonRelsetmapperObj = {};
							CommonRelsetmapperObj.purchaseReturnId = response;

							/* ============== post return ============*/

							var ajaxCallObject = new CustomBrowserXMLObject();
							ajaxCallObject.callAjaxPost(BASE_URL + "/purret/postpurretinv.htm", CommonRelsetmapperObj, function(postResponse) {
								if (postResponse == '1') {

									/* ========== get return header by id ========== */

									var ajaxCallObject = new CustomBrowserXMLObject();
									ajaxCallObject.callAjaxPost(BASE_URL + "/purret/getretheaderbyidforpur.htm", CommonRelsetmapperObj, function(resp) {
										var obj = jQuery.parseJSON(resp);
										console.log("obj: "+obj);
										retInvNo = obj.invNo;
										retInvDt = obj.invDate;
										retAdjAmnt = Math.round(Number($("#retadj").val()));
									});
								}
							});
						});
					}

			}
			//========================================

			PurchaseObj.purchaseDetails = PurchaseDetailsObjArr;
			PurchaseObj.invDate = $("#invdt").val();
			PurchaseObj.dueDate = $("#duedt").val();
			PurchaseObj.billNo = $("#billno").val();
			PurchaseObj.billDate = $("#billDate").val();
			PurchaseObj.invNo = $("#invno").val();
			PurchaseObj.id =$("#orderno").val();
			//PurchaseObj.distName = $("#distname").val();
			PurchaseObj.distributorId = $("#seldistributor").val();
			PurchaseObj.edAmount = 0.0; //$("#toted").val();
			PurchaseObj.grossAmount = $("#totgrosamnt").val();
			PurchaseObj.vatAmount = 0.0; //$("#totvatamnt").val();
			PurchaseObj.taxAmount = $("#tottaxamnt").val();
			PurchaseObj.discAmount = $("#totdiscamnt").val();
			PurchaseObj.distDiscPer=$("#distdiscprcnt").val();
			PurchaseObj.roundoff =  $("#roundoff").val();
			PurchaseObj.netAmount = $("#totnetamnt").val();
			PurchaseObj.advAmount = advamt;
			PurchaseObj.adjAmount = $("#retadj").val();
			if ($("#invmode").is(':checked')) {
				PurchaseObj.invMode = 1;
			} else {
				PurchaseObj.invMode = 2;
			}
			PurchaseObj.totalMrp = $("#totgrosmrp").val();
			PurchaseObj.lotAdjAmount = $("#totltadj").val();
			PurchaseObj.specDiscPer = spclDisc;
			PurchaseObj.specDiscAmount = $("#spldiscamt").val();
			PurchaseObj.remarks = $("#remarks").val();
			PurchaseObj.distributorBillAmount = suppbillamt;
			PurchaseObj.otherAdjAmount = $("#othadjamt").val();
			PurchaseObj.expiryAdjAmount = $("#expadj").val();

			var rateOrmrpValue = $("input[name='rateOrmrp']:checked").val();
			var isMrp = 0;
			if(rateOrmrpValue=="mrp")
			{
				isMrp = 1;
			}
			else
			{
				isMrp = 0;
			}
			$('#retexpadjtable > tbody  > tr').each(function() {
				var expiryreturndetails = {};
				var expiryretid = this.id;
				var invno = $(this).find("td:eq(0)").text();
				expiryreturndetails.expiryId = expiryretid;
				expiryreturndetails.expiryDetailsId = $(this).find("#retexpdetid").text();
				expiryreturndetails.packQty = $(this).find("#retexppqty").text();
				expiryreturndetails.looseQty = $(this).find("#retexplqty").text();
				expiryreturndetails.expiryDateFormat = $(this).find("#retexpdtfrmt").text();
				expiryreturndetails.batchNo = $(this).find("#retexpbatchno").text();;
				expiryreturndetails.conversion = $(this).find("#retexpconversion").text();
				expiryreturndetails.mrp = $(this).find("#retexpmrp").text();
				expiryreturndetails.rate = $(this).find("#retexprate").text();
				expiryreturndetails.itemId = $(this).find("#retexpitemid").text();
				expiryreturndetails.isMrp = isMrp;
				expiryreturndetails.expiryInvNo = invno;
				expiryreturndetails.expiryInvDate = $(this).find("td:eq(1)").text();// invdate
				expiryreturndetails.adjAmount = $(this).find("#retexpadjamnt").text();// adjustment amt
				allexpiryreturndetails.push(expiryreturndetails);
			});
			PurchaseObj.expiryReturns = allexpiryreturndetails;

			setTimeout(function()
			{
				if(retId != 0)
				{
					var purchasereturndetails = {};
					purchasereturndetails.id = retId;
					purchasereturndetails.invNo = retInvNo;
					purchasereturndetails.invDate = retInvDt;// invdate
					purchasereturndetails.adjAmount = retAdjAmnt;// adjustment amt
					allpuchasereturndetails.push(purchasereturndetails);
				}

				$('#retadjtable > tbody  > tr').each(function() {
					var purchasereturndetails = {};
					var purchaseretid = this.id;
					var invno = $(this).find("td:eq(0)").text();
					purchasereturndetails.id = purchaseretid;
					purchasereturndetails.invNo = invno;
					purchasereturndetails.invDate = $(this).find("td:eq(1)").text();// invdate
					purchasereturndetails.adjAmount = $(this).find("td:eq(4)").text();// adjustment amt
					allpuchasereturndetails.push(purchasereturndetails);
				});
				PurchaseObj.purchaseReturns = allpuchasereturndetails;
				/*
				 * for account in purchase
				 */
				PurchaseObj.duties_ledger_id= $('#duties_ledger_id1').val();
				PurchaseObj.round_ledger_id= $('#round_ledger_id1').val();
				PurchaseObj.purchase_ledger_id= $('#purchase_ledger_id1').val();
				PurchaseObj.discount_ledger_id= $('#discount_ledger_id1').val();
				PurchaseObj.credior_ledger_id= $('#creditor_ledger_id1').val();
				PurchaseObj.lotadjas_ledger_id= $('#lotadj_ledger_id1').val();

				console.log("PurchaseObj json: " + JSON.stringify(PurchaseObj));
				 var error=0;// for account ledger checking flag
				if (is_acc==1) {// when account enable


					if (PurchaseObj.duties_ledger_id>=0 && PurchaseObj.round_ledger_id>0 && PurchaseObj.purchase_ledger_id>0&& PurchaseObj.discount_ledger_id>0&& PurchaseObj.credior_ledger_id>0&& PurchaseObj.lotadjas_ledger_id>0) {
						error=0;
					}else {
						error=1;
						$("#confirmval").val(-1);
						document.getElementById('confirmmessagecont').innerHTML = getFieldText.LedgerAccountError;
						showConfirmModal();
						return false;
					}
				}
				if (error==0) {



					$('#pleasewaitModal').modal('show');
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/createorupdatepurchaseinvoice.htm", PurchaseObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						if (response == '0') {
							$("#confirmval").val(-1);
							document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataNotUpdate;
							showConfirmModal();
						}
						else if (response == '-7') {
							$("#confirmval").val($("#orderno").val());
							document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataInUseErr+" updated!";
							showConfirmModal();
						}
						else if (response == '-8') {
							document.getElementById('confirmmessagecont').innerHTML = getPurInvText.sameBillForSameVendorErr;
							$("#confirmval").val(-1);
							showConfirmModal();
						}
						else {
							/*$("#confirmval").val(-1);
							document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataSucUpdate;
							showConfirmModal();*/
							$("#confirmval").val(response);
							$("#purAddEditMsg").text(getPurInvText.dataSucUpdate);
							$('#confirmPrintPurchaseModal').modal('show');
						}

					});

				}// end if
			},500);
		}
		else
		{
			$("#confirmval").val("notRedirect");
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.addEditChckBefrSave;
			showConfirmModal();
		}
		}
		else
		{
		$("#confirmval").val(-1);
		document.getElementById('confirmmessagecont').innerHTML = getPurInvText.saveWithoutPurItemErr;
		showConfirmModal();
		}
	});

});

function createItemPurHistoryDetails(itemPurHisDet){
	$("#purHistoryDiv").removeClass("hide");
	$("#itmpurhistboby").html("");
	$("#itemnameofpurhis").text("");
	for ( var i = 0; i < itemPurHisDet.length; i++) {
		var itemPurHis = itemPurHisDet[i];
		$("#itemnameofpurhis").text(itemPurHis.itemName);
		var starttrline = "<tr id=" + itemPurHis.invNo + " >";
		var invno = "<td>" + itemPurHis.invNo + "</td>";
		var invdate = "<td>" + moment(itemPurHis.invDate).format('YYYY-MM-DD') + "</td>";
		var vendorName = "<td>" +itemPurHis.distributorName + "</td>";
		var batchno = "<td>" +itemPurHis.batchNo + "</td>";
		var expdtfrmt ="NA";
		if(itemPurHis.expiryDateFormat==""||itemPurHis.expiryDateFormat==undefined){
			//expdtfrmt = "<td>" +itemPurHis.expiryDateFormat + "</td>";
			expdtfrmt ="<td>NA</td>";
		}else{
			expdtfrmt = "<td>" +itemPurHis.expiryDateFormat + "</td>";
		}
		var purqty = "<td>" +itemPurHis.packQty + "</td>";
		var freeqty = "<td>" +itemPurHis.freeQty + "</td>";
		var uom = "<td>" +itemPurHis.packUnitName + "</td>";
		var mrp = "";
		if($("#isMrpEnableFlag").val()==1)
		{
			mrp = "<td>" +parseFloat(itemPurHis.mrp).toFixed(2) + "</td>";
		}
		else
		{
			mrp = "<td class='hide'>" +parseFloat(itemPurHis.mrp).toFixed(2) + "</td>";
		}
		var rate = "<td>" +parseFloat(itemPurHis.rate).toFixed(2) + "</td>";
		var discper = "<td>" +parseFloat(itemPurHis.discPer).toFixed(2) + "</td>";
		var amt = "<td>" +parseFloat(itemPurHis.amount).toFixed(2) + "</td>";
		var endtrline = "</tr>";
		createdrowline = starttrline + invno + invdate + vendorName + batchno + expdtfrmt + purqty + freeqty + uom + mrp + rate + discper + amt + endtrline;
		$("#itmpurhistboby").append(createdrowline);
	}
}

function closePurHisDet(){
	$("#purHistoryDiv").addClass("hide");
}

$('#purbarcode').on('keydown', function(e) {
	if (e.which == 13) {
		e.preventDefault();
		var barcode = $('#purbarcode').val();
		getItemDetailsByBarcode(barcode);
	}
});

function getItemDetailsByBarcode(barcode) {
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/purinv/getpurinvdetailsbysku/" + barcode + ".htm", function(resp) {
		console.log(resp);
		var obj = jQuery.parseJSON(resp);
		if(obj!="")
		{
			fillItemDetailsDiv(obj[0]);
			// call new  ajax for item History details

			getpurhistoryofitem(obj[0].itemId);

			// call new ajax item History details end
			$("#editNewItemBtn").removeClass("hide");
			$("#addNewItemBtn").addClass("hide");
			$("#editItemLabel").removeClass("hide");
			$("#newItemLabel").addClass("hide");
		}
		else
		{
			$("#header_div").find('input:text').val('');
			$("#header_div").find('input:hidden').val('');
			$("#itemid").val("");
			$("#dprcnt").val($("#vendordis").val());
			$("#freeCheck").attr("checked",false);
			$("#editNewItemBtn").addClass("hide");
			$("#addNewItemBtn").removeClass("hide");
			$("#editItemLabel").addClass("hide");
			$("#newItemLabel").removeClass("hide");

			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.wrongBarcode;
			showConfirmModal();
		}
	}, null);

}

/*=============================== On Bill Purchase Return Start =====================*/

function chngeMode()
{
	var mode = $("#slctMode").val();
	if(mode == "R")
	{
		$("#header_ret_tbl").removeClass("hide");
		$("#header_pur_tbl").addClass("hide");
	}
	else
	{
		$("#header_pur_tbl").removeClass("hide");
		$("#header_ret_tbl").addClass("hide");
	}
}

function getItemDetailsForRet(itemid) {
	var saleretvendorid = $("#seldistributor").val();
	//$('#itempurdetailModal').modal('show');

	$('#pleasewaitModal').modal('show');
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.itemId = itemid;
	CommonRelsetmapperObj.distributorId = saleretvendorid;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purret/getpurinvdetbyitemidforret.htm", CommonRelsetmapperObj, function(response) {
		console.log("res=" + response);
		$("#itemsearchmodtbody").text("");
		$('#pleasewaitModal').modal('hide');
		var purdetails = JSON.parse(response);
		fillItemRetDetailsDiv(purdetails);
	});
	$('#itempurdetailModal').modal('show');
}

function fillItemRetDetailsDiv(purdetails)
{
	if (purdetails.length == 0) {
		$("#itempurdetnotfounddiv").removeClass("hide");
		$("#itempurdetmodtable").addClass("hide");
	} else {
		$("#itempurdetnotfounddiv").addClass("hide");
		$("#itempurdetmodtable").removeClass("hide");
		for ( var i = 0; i < purdetails.length; i++) {
			var purdetail = purdetails[i];
			var createdrowline="";
			$("#itempurdetailitemname").html(purdetail.itemName);
			var starttrline = "<tr id=" + purdetail.itemUniqueKey + " style='cursor: pointer;' onclick='insertModData(" + JSON.stringify(purdetail) + ")'>";
			var saleInvNo = "<td>" + purdetail.purchaseInvNo + "</td>";
			var saleInvDate = "<td>" + moment(purdetail.purchaseInvDate).format('YYYY-MM-DD') + "</td>";
			var vendorName = "<td>" + purdetail.distributorName + "</td>";
			var batch = "<td>" + purdetail.batchNo + "</td>";
			var exp = "<td>" + purdetail.expiryDateFormat + "</td>";
			var packQty = "<td>" + purdetail.packQty + "</td>";
			var freeQty = "<td>" + purdetail.freeQty + "</td>";
			var stockQty = "<td>" + purdetail.stockQty + "</td>"
			var inputPackQty = "<td class='hide'><input type='hidden' value='" + purdetail.packQty + "' id='" + purdetail.itemUniqueKey + "_irpqty'/></td>";
			var inputfreeQty = "<td class='hide'><input type='hidden' value='" + purdetail.freeQty + "' id='" + purdetail.itemUniqueKey + "_irfqty'/></td>";
			var mrp = "<td class='hide'>" + parseFloat(purdetail.mrp).toFixed(2) + "</td>";//
			var rate = "<td>" + parseFloat(purdetail.rate).toFixed(2) + "</td>";
			var ed = "<td id='" + purdetail.itemUniqueKey + "_edamt'>" + parseFloat(purdetail.ed).toFixed(2) + "</td>";
			var tax = "<td id='" + purdetail.itemUniqueKey + "_taxamt'>" + parseFloat(purdetail.itemTaxAmount).toFixed(2) + "</td>";
			var vat = "<td id='" + purdetail.itemUniqueKey + "_vatamt'>" + parseFloat(purdetail.vat).toFixed(2) + "</td>";
			var disc = "<td id='" + purdetail.itemUniqueKey + "_discamt'>" + parseFloat(purdetail.disc).toFixed(2) + "</td>";
			var retamt = "<td id='" + purdetail.itemUniqueKey + "_retamt'>" + parseFloat(purdetail.packQty * purdetail.rate).toFixed(2) + "</td>";
			var endtrline = "</tr>";
			createdrowline = starttrline + saleInvNo + saleInvDate + vendorName + batch + exp + packQty + freeQty + stockQty + inputPackQty + inputfreeQty + mrp + rate + tax + disc + retamt + /*rettaxid + rettaxprcnt + rettaxmode + rettaxisgrptax + retsku + rethsn +*/ endtrline;
			$("#itemsearchmodtbody").append(createdrowline);
		}
	}
}

function insertModData(itemdetail) {
	var calretamt = $("#" + itemdetail.itemUniqueKey + "_retamt").text();
	var caledamt = $("#" + itemdetail.itemUniqueKey + "_edamt").text();
	var caltaxamt = $("#" + itemdetail.itemUniqueKey + "_taxamt").text();
	var calvatamt = $("#" + itemdetail.itemUniqueKey + "_vatamt").text();
	var caldiscamt = $("#" + itemdetail.itemUniqueKey + "_discamt").text();
	//var lqty = $("#" + itemdetail.itemId + "_irlqty").val();
	var pqty = $("#" + itemdetail.itemUniqueKey + "_irpqty").val();

	var free = itemdetail.freeQty;
	var bpqty = itemdetail.packQty;
	var tot = parseFloat(calretamt).toFixed(4);

	//$("#item_id").val(trId);
	$('#item_name_ret').prop('readonly', true);
	$("#barcode_ret").attr("readonly",true);
	$("#item_name_ret").val(itemdetail.itemName);
	$("#batch_no_ret").val(itemdetail.batchNo);
	$("#exp_ret").val(itemdetail.expiryDateFormat);
	$("#pqty_ret").val(pqty);
	$("#lqty_ret").val(itemdetail.conversion * pqty);
	$("#ratio_ret").val(itemdetail.conversion);
	$("#free_ret").val(free);
	$("#prevfree_ret").val(free);
	$("#mrp_ret").val(parseFloat(itemdetail.mrp).toFixed(4));
	$("#rate_ret").val(parseFloat(itemdetail.rate).toFixed(4));
	$("#ma_ret").val(0);
	$("#grp_ret").val(itemdetail.grpName);
	$("#sch_ret").val(itemdetail.scheduleName);
	$("#grpid_ret").val(itemdetail.grpId);
	$("#schid_ret").val(itemdetail.scheduleId);
	$("#total_ret").val(parseFloat(calretamt).toFixed(4));
	$("#mfg_ret").val(itemdetail.manufacturerName);
	$("#mfgid_ret").val(itemdetail.manufacturerId);
	$("#taxprcnt_ret").val(itemdetail.taxPercentage);
	$("#tax_ret").val(parseFloat(caltaxamt).toFixed(4));
	$("#purTaxId_ret").val(itemdetail.taxId);
	$("#purtaxmode_ret").val(itemdetail.taxMode);
	$("#purisgrptax_ret").val(itemdetail.isGroupTax);
	$("#dprcnt_ret").val(parseFloat(itemdetail.discPer).toFixed(4));
	$("#disc_ret").val(parseFloat(caldiscamt).toFixed(4));
	$("#itemid_ret").val(itemdetail.itemId);
	$("#tblrow_id_ret").val(itemdetail.itemId+"_"+itemdetail.batchNo);
	$("#punitid_ret").val(itemdetail.packUnitId);
	$("#billpqty_ret").val(itemdetail.packQty);
	$("#barcode_ret").val(itemdetail.sku);
	$("#purHsnCode_ret").val(itemdetail.hsnCode);
	$("#serialnumberreq").val(itemdetail.serialNoRequired);
	if(itemdetail.serialNoRequired==1)
	{
		var arr=[];
		for(var i=0;i<itemdetail.purchaseReturnDetailsSerialMapper.length;i++)
		{
			arr.push(itemdetail.purchaseReturnDetailsSerialMapper[i].uniqueIdentifierNo);
		}
		$("#allSlNos_ret").val(arr);
		$("#allChkdSlNos_ret").val(arr);
	}
	else
	{
		$("#allSlNos_ret").val(0);
		$("#allChkdSlNos_ret").val(0);
	}

	if(itemdetail.dualStockRequired==null||itemdetail.dualStockRequired==0){
		$("#item_dualStockRequired_req").val(0);
		$("#ratio_ret_td").addClass('hide');
		$("#ratio_ret_label").addClass('hide');
		//$("#ratio").val(itemdetval.conversion);
	}else if(itemdetail.dualStockRequired==1){
		$("#item_dualStockRequired_req").val(1);
		$("#ratio_ret_td").removeClass('hide');
		$("#ratio_ret_label").removeClass('hide');
	//	$("#ratio").val(itemdetval.conversion);
	}else{
		$("#item_dualStockRequired_req").val(0);
		$("#ratio_ret_td").removeClass('hide');
		$("#ratio_ret_label").removeClass('hide');
	//	$("#ratio").val(itemdetval.conversion);
	}

	if(itemdetail.batchWiseStock==null||itemdetail.batchWiseStock==0){
		$("#item_batchWiseStock_req").val(0);
		$("#batch_no_ret_td").addClass('hide');
		$("#batch_ret_label").addClass('hide');
		//$("#batch_no").val(0);
	}else if(isBatch==1){
		$("#item_batchWiseStock_req").val(1);
		$("#batch_no_ret_td").removeClass('hide');
		$("#batch_ret_label").removeClass('hide');
		//$("#batch_no").val(0);
	}else{
		$("#item_batchWiseStock_req").val(0);
		$("#batch_no_ret_td").removeClass('hide');
		$("#batch_ret_label").removeClass('hide');
		//$("#batch_no").val(0);
	}

	if(itemdetail.expiryDateRequired==null||itemdetail.expiryDateRequired==0){
		$("#item_expiryDateRequired_req").val(0);
		$("#exp_ret_td").addClass('hide');
		$("#exp_ret_label").addClass('hide');
		//$("#exp").val();
	}else if(itemdetail.expiryDateRequired==1){
		$("#item_expiryDateRequired_req").val(1);
		$("#exp_ret_td").removeClass('hide');
		$("#exp_ret_label").removeClass('hide');
		//$("#exp").val();
	}else{
		$("#item_expiryDateRequired_req").val(0);
		$("#exp_ret_td").removeClass('hide');
		$("#exp_ret_label").removeClass('hide');
	//	$("#exp").val();
	}

	if(itemdetail.priceListRequired==null||itemdetail.priceListRequired==0){
		$("#item_priceListRequired_req").val(0);
		$("#mrp_ret_td").addClass('hide');
		$("#mrp_ret_label").addClass('hide');
	}else if(itemdetail.priceListRequired==1){
		$("#item_priceListRequired_req").val(1);
		if($("#isMrpEnableFlag").val()==1)
		{
			$("#mrp_ret_td").removeClass('hide');
			$("#mrp_ret_label").removeClass('hide');
		}
		else
		{
			$("#mrp_ret_td").addClass('hide');
			$("#mrp_ret_label").addClass('hide');
		}
	}else{
		$("#item_priceListRequired_req").val(0);
		$("#mrp_ret_td").removeClass('hide');
		$("#mrp_ret_label").removeClass('hide');
	}

	if(itemdetail.mrpRequired==null||itemdetail.mrpRequired==0){
		$("#item_mrpRequired_req").val(0);
		$("#mrp_ret_td").addClass('hide');
		$("#mrp_ret_label").addClass('hide');
	}else if(itemdetail.mrpRequired==1){
		$("#item_mrpRequired_req").val(1);
		if($("#isMrpEnableFlag").val()==1)
		{
			$("#mrp_ret_td").removeClass('hide');
			$("#mrp_ret_label").removeClass('hide');
		}
		else
		{
			$("#mrp_ret_td").addClass('hide');
			$("#mrp_ret_label").addClass('hide');
		}
	}else{
		$("#item_mrpRequired_req").val(0);
		$("#mrp_ret_td").removeClass('hide');
		$("#mrp_ret_label").removeClass('hide');
	}
	$("#edit_btn_ret").addClass("hide");
	$("#add_btn_ret").removeClass("hide");
	$('#itempurdetailModal').modal('hide');



	/*insertModDatatoRetTable(purdetail);

	calculateGrandTotal();
	calculateTotalMRP();
	calculateTotalTax();
	calculateTotalDisc();
	calculateTotalLtAdj();
	calculateNetTotal();
	clearHeaderDiv();*/
}

function addForRet() {
	//$("#purrettabitemdetails").text("");
	var saleretvendorid = $("#seldistributor").val();
	var ltadj = 0.0;
	var rate = 0;
	var rtrnFree = 0.0;
	var free = 	$("#prevfree_ret").val();
	if($("#rate_ret").val()=="")
	{
		rate = 0;
	}
	else
	{
		rate = $("#rate_ret").val();
	}

	if(ValidationForRet()==1)
	{
		return false;
	}

	var itempresent = 0;
	$('#peitem > tbody  > tr').each(function() {
		console.log("tbl_itemid=" + $(this).find('#tbl_itemid').text());
		console.log("itemid=" + $("#itemid_ret").val());
		if ((Number($(this).find('#tbl_itemid').text()) == Number($("#itemid_ret").val())) && ($(this).find('#tbl_batch_no').text()==$("#batch_no_ret").val()) && ($(this).find('#tbl_mode').text()==$("#slctMode").val())) {
			itempresent = 1;
		}
	});
	if (Number($("#itemid_ret").val()) == 0) {
		return false;
	}
	if (itempresent == 1) {
		$('#itemExistsModal').modal('show');
	} else {

		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var pqty = $("#pqty_ret").val();
			var modFree =  free % 1;
			var pqtyPerFree = pqty/modFree;
			var tot = $("#total_ret").val();
			var bpqty = $("#billpqty_ret").val();
			ltadj = tot/pqtyPerFree;
			rtrnFree = (pqty*modFree)/bpqty;
			// LtAdj calculation end
		}
		else
		{
			rtrnFree = 0;
		}

		var strng_batch_no="'"+$("#batch_no_ret").val()+"'";
		var strng_tbrow_id = "'R_"+$("#itemid_ret").val()+"_"+$("#batch_no_ret").val()+"'";
		var tr = '<tr class="danger" id="tblrow_R_' + $("#itemid_ret").val()+'_'+$("#batch_no_ret").val() + '" style="cursor: pointer;" onclick="javascript:itemDetailViewRet(' + $("#itemid_ret").val() +','+strng_batch_no+ ');">';
		var totmrp = (Number($("#pqty_ret").val()) + Number($("#free_ret").val())) * $("#mrp_ret").val();
		var row1 = '<td id="tbl_item_name">' + $("#item_name_ret").val() + '</td>';
		var row2 = '<td id="tbl_batch_no">' + $("#batch_no_ret").val() + '</td>';
		if($("#item_expiryMonthRequired_req").val()==1){
			var row3 = '<td id="tbl_exp">' + $("#exp_ret").val() + '</td>';
		}else{
			var row3 = '<td id="tbl_exp">NA</td>';
		}
		//var row3 = '<td id="tbl_exp">' + $("#exp_ret").val() + '</td>';
		var row4 = '<td id="tbl_pqty" class="numeric">' + $("#pqty_ret").val() + '</td>';
		var row5 = '<td id="tbl_lqty" class="numeric hide">' + $("#lqty_ret").val() + '</td>';
		var row6 = '<td id="tbl_ratio" class="numeric hide">' + $("#ratio_ret").val() + '</td>';
		var row7 = '<td id="tbl_free" class="numeric">'+parseFloat(rtrnFree).toFixed(2)+'</td>';
		var previousFree = '<td id="tbl_prevfree" class="hide">' + parseFloat($("#prevfree_ret").val()).toFixed(2) + '</td>';
		var row8 = '<td id="tbl_mrp" class="numeric hide">' + $("#mrp_ret").val() + '</td>';
		var row9 = '<td id="tbl_rate" class="numeric">' + rate + '</td>';
		var row10 = '<td id="tbl_ed" class="numeric">0</td>';
		var row11 = '<td id="tbl_tax" class="numeric">' + $("#tax_ret").val() + '</td>';
		var row12 = '<td id="tbl_vat" class="numeric">0</td>';
		var row13 = '<td id="tbl_disc" class="numeric">' + $("#disc_ret").val() + '</td>';
		var row14 = '<td id="tbl_amnt" class="numeric">' + $("#total_ret").val() + '</td>';
		var row15 = '<td id="tbl_totamnt" class="numeric hide">' + parseFloat(totmrp).toFixed(2) + '</td>';
		var net_amount = (Number($("#total_ret").val()) + Number($("#tax_ret").val())) - Number($("#disc_ret").val());
		var netamt = '<td id="tbl_netamt" class="numeric">' + parseFloat(net_amount).toFixed(2) + '</td>';
		var row16 = '<td><button class="btn btn-theme04 btn-xs" onclick="javascript:showPurItemDelModal('+strng_tbrow_id+');"><i class="fa fa-trash-o "></i></button></td>';
		var row17 = '<td id="tbl_ma" class="hide">0</td>';
		var row18 = '<td id="tbl_grp" class="hide">' + $("#grpid_ret").val() + '</td>';
		var row19 = '<td id="tbl_sch" class="hide">' + $("#schid_ret").val() + '</td>';
		var row20 = '<td id="tbl_mfg" class="hide">' + $("#mfgid_ret").val() + '</td>';
		var row21 = '<td id="tbl_edprcnt" class="hide">0</td>';
		var row22 = '<td id="tbl_taxprcnt">' + $("#taxprcnt_ret").val() + '</td>';
		var taxid = '<td id="tbl_taxid" class="hide">' + $("#purTaxId_ret").val() + '</td>';
		var taxmode = '<td id="tbl_taxmode" class="hide">' + $("#purtaxmode_ret").val() + '</td>';
		var isgrptax = '<td id="tbl_isgrptax" class="hide">' + $("#purisgrptax_ret").val() + '</td>';
		var row23 = '<td id="tbl_vatprcnt" class="hide">0</td>';
		var row24 = '<td id="tbl_dprcnt">' + $("#dprcnt_ret").val() + '</td>';
		var row25 = '<td id="tbl_id" class="hide">' + $("#id_ret").val() + '</td>';
		var row26 = '<td id="tbl_itemid" class="hide">' + $("#itemid_ret").val() + '</td>';
		var row27 = '<td id="tbl_grpname" class="hide">' + $("#grp_ret").val() + '</td>';
		var row28 = '<td id="tbl_schname" class="hide">' + $("#sch_ret").val() + '</td>';
		var row29 = '<td id="tbl_mfgname" class="hide">' + $("#mfg_ret").val() + '</td>';
		var packunitid = '<td id="tbl_punitid" class="hide">' + $("#punitid_ret").val() + '</td>';
		var isFreeRow = '<td id="tbl_isFree" class="hide">N</td>';
		var ltadjRow = '<td id="tbl_ltAdj" class="hide">'+ltadj+'</td>';
		var sku = '<td id="tbl_sku" class="hide">' + $("#barcode_ret").val() + '</td>';
		var hsn = '<td id="tbl_hsn" class="hide">' + $("#purHsnCode_ret").val() + '</td>';
		var tbl_sale_rate = '<td id="tbl_saleRate" class="numeric">0.00</td>';
		var poId = '<td id="tbl_poid" class="hide">0</td>';
		var predprbfrspdp = '<td id="tbl_predprbfrspdp" class="hide">'+$("#dprcnt_ret").val()+'</td>';
		var billpackqty = "<td class='hide' id='purrettabpqtyhide'>"+$("#billpqty_ret").val()+"</td>";
		var mode = "<td class='hide' id='tbl_mode'>"+$("#slctMode").val()+"</td>";
		var returnReason = "<td class='hide' id='tbl_ret_reason'>"+$("#slctRetReason").val()+"</td>";
		var printAction = '<td></td>';

		// for common retail
		var expmonth = '<td id="tbl_expmonth" class="hide"></td>';
		var mfd = '<td id="tbl_mfd" class="hide">NA</td>';
		var slnos = '<td id="tbl_slnos" class="numeric hide">' + $("#allSlNos_ret").val() + '</td>';
		var slnoreq = '<td id="tbl_slnoreq" class="numeric hide">' + $("#serialnumberreq").val() + '</td>';
		var item_batchWiseStock_req = '<td id="tbl_item_batchWiseStock_req" class="numeric hide">' + $("#item_batchWiseStock_req").val() + '</td>';
		var item_expiryDateRequired_req = '<td id="tbl_item_expiryDateRequired_req" class="numeric hide">' + $("#item_expiryDateRequired_req").val() + '</td>';
		var item_expiryMonthRequired_req = '<td id="tbl_item_expiryMonthRequired_req" class="numeric hide">' + $("#item_expiryMonthRequired_req").val() + '</td>';
		var item_dualStockRequired_req = '<td id="tbl_item_dualStockRequired_req" class="numeric hide">' + $("#item_dualStockRequired_req").val() + '</td>';
		var item_mrpRequired_req = '<td id="tbl_item_mrpRequired_req" class="numeric hide">' + $("#item_mrpRequired_req").val() + '</td>';
		var item_locationRequired_req = '<td id="tbl_item_locationRequired_req" class="numeric hide">' + $("#item_locationRequired_req").val() + '</td>';
		var item_priceListRequired_req = '<td id="tbl_item_priceListRequired_req" class="numeric hide">' + $("#item_priceListRequired_req").val() + '</td>';
		var chkdSlNos = '<td id="tbl_chkdSlNos" class="hide">' + $("#allChkdSlNos_ret").val() + '</td>';

		//tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14 + row15 + row16 + row17 + row18 + row19 + row20 + row21 + row22 + row23 + row24 + row25 + row26 + row27 + row28 + row29 + packunitid + isFreeRow + ltadjRow +'</tr>';
		//tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + previousFree + row8 + row9 + tbl_sale_rate + row22 + row11 + row24 + row13 + row14 + row15 + netamt + row16 + row17 + row18 + row19 + row20 + taxid + taxmode + isgrptax + row25 + row26 + row27 + row28 + row29 + packunitid + isFreeRow + ltadjRow + sku + hsn + poId + predprbfrspdp + billpackqty + mode + returnReason + '</tr>';
		tr = tr + row1 + row2 + row3 + row4 + row5 + row6+ row7 + previousFree + row9 + tbl_sale_rate + row22 + row11 + row24 + row13 + row14 + netamt + row16 + printAction + row17 + row18 + row19 + row20 + taxid + taxmode + isgrptax + row25 + row26 + row27 + row28 + row29 + packunitid + isFreeRow + ltadjRow + sku + hsn + poId + predprbfrspdp + billpackqty + mode + returnReason+expmonth+mfd+location+ row8 + row15+slnos+slnoreq + item_batchWiseStock_req+item_expiryDateRequired_req+item_expiryMonthRequired_req+item_dualStockRequired_req+item_mrpRequired_req+item_locationRequired_req+item_priceListRequired_req+chkdSlNos+'</tr>';
		$("#peitem tbody").prepend(tr);

		$("#retAdj_btn").attr("disabled",true);
		calculateTotalLtAdjForRet();
		calculateGrandTotalForRet();
		calculateTotalMRPForRet();
		calculateTotalTaxForRet();
		calculateTotalDiscForRet();
		calculateNetTotalForRet();

		//calculateRetAdj();
		calculateBillAmount();
	}
	clearHeaderDiv();
}

function itemDetailViewRet(trId,batchNo) {
	document.getElementById('alertMsg').innerHTML = "";
	$("#item_name_ret").attr("readonly",true);
	$("#barcode_ret").attr("readonly",true);
	$("#item_name_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_item_name').text());
	$("#batch_no_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_batch_no').text());
	$("#exp_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_exp').text());
	$("#pqty_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_pqty').text());
	$("#lqty_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_lqty').text());
	$("#ratio_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_ratio').text());
	$("#free_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_free').text());
	$("#prevfree_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_prevfree').text());
	$("#mrp_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_mrp').text());
	$("#rate_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_rate').text());
	//$("#sale_rate_ret").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_saleRate').text());
	$("#ma_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_ma').text());
	$("#grp_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_grpname').text());
	$("#sch_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_schname').text());
	$("#grpid_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_grp').text());
	$("#schid_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_sch').text());
	$("#total_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_amnt').text());
	$("#mfg_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_mfgname').text());
	$("#mfgid_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_mfg').text());
	$("#taxprcnt_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_taxprcnt').text());
	$("#tax_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_tax').text());
	$("#purTaxId_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_taxid').text());
	$("#purtaxmode_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_taxmode').text());
	$("#purisgrptax_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_isgrptax').text());
	$("#billpqty_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#purrettabpqtyhide').text());
	$("#dprcnt_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_dprcnt').text());
	$("#disc_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_disc').text());
	$("#id_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_id').text());
	$("#itemid_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_itemid').text());
	$("#tblrow_id_ret").val(trId+"_"+batchNo);
	$("#punitid_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_punitid').text());
	$("#barcode_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_sku').text());
	$("#purHsnCode_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_hsn').text());
	$("#slctRetReason").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_ret_reason').text());

	$("#allSlNos_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_slnos').text());
	$("#allChkdSlNos_ret").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_chkdSlNos').text());
	$("#serialnumberreq").val($("#tblrow_R_" + trId+"_"+batchNo).find('#tbl_slnoreq').text());

	$("#edit_btn_ret").removeClass("hide");
	$("#add_btn_ret").addClass("hide");

	$("#header_ret_tbl").removeClass("hide");
	$("#header_pur_tbl").addClass("hide");

}

function editForRet()
{
	var ltadj = 0.0;
	var rtrnFree = 0.0;
	if (ValidationForRet() == 1) {
		return false;
	} else {
		var free = $("#prevfree_ret").val();
		var bpqty = $("#billpqty_ret").val();
		var tot = $("#total_ret").val();
		var pqty = $("#pqty_ret").val();
		if(((free % 1)!=0) || free<1)
		{
			// LtAdj calculation start
			var modFree =  free % 1;
			var pqtyPerFree = pqty/modFree;
			ltadj = tot/pqtyPerFree;
			rtrnFree = (pqty*modFree)/bpqty;
			// LtAdj calculation end
		}
		document.getElementById('alertMsg').innerHTML = "";
	}

	var trId = $("#tblrow_id_ret").val();
	//var amnt = $("#pqty").val() * $("#rate").val();
//		var totmrp = (Number($("#pqty").val()) + Number($("#free").val())) * $("#mrp").val();
	var totmrp = (Number($("#pqty_ret").val()) + Number(0)) * $("#mrp_ret").val();
	$("#tblrow_R_" + trId).find('#tbl_pqty').text($("#pqty_ret").val());
	$("#tblrow_R_" + trId).find('#tbl_lqty').text($("#lqty_ret").val());
	$("#tblrow_R_" + trId).find('#tbl_rate').text($("#rate_ret").val());
	$("#tblrow_R_" + trId).find('#tbl_tax').text($("#tax_ret").val());
	$("#tblrow_R_" + trId).find('#tbl_disc').text($("#disc_ret").val());
	$("#tblrow_R_" + trId).find('#tbl_amnt').text($("#total_ret").val());
	$("#tblrow_R_" + trId).find('#tbl_free').text(parseFloat(rtrnFree).toFixed(2));
	$("#tblrow_R_" + trId).find('#tbl_totamnt').text(parseFloat(totmrp).toFixed(2));
	var net_amount = (Number($("#total_ret").val()) + Number($("#tax_ret").val())) - Number($("#disc_ret").val());
	$("#tblrow_R_" + trId).find('#tbl_netamt').text(parseFloat(net_amount).toFixed(2));
	$("#tblrow_R_" + trId).find('#tbl_ret_reason').text($("#slctRetReason").val());
	$("#tblrow_R_" + trId).find('#tbl_ltAdj').text(ltadj);
	$("#tblrow_R_" + trId).find('#tbl_chkdSlNos').text($("#allChkdSlNos_ret").val());
	clearHeaderDiv();
	calculateTotalLtAdjForRet();
	calculateGrandTotalForRet();
	calculateTotalMRPForRet();
	calculateTotalTaxForRet();
	calculateTotalDiscForRet();
	calculateNetTotalForRet();

	//calculateRetAdj();
	calculateBillAmount();
	$("#edit_btn_ret").addClass("hide");
	$("#add_btn_ret").removeClass("hide");
}

function calculateRetAdj() {
	var grandtotalRetAdj = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="R")
		{
			var itmretadj = $(this).find("#tbl_netamt").html();
			grandtotalRetAdj = grandtotalRetAdj + Number(itmretadj);
		}
	});
	$("#retadj").val(Number(parseFloat($("#retadj").val()).toFixed(4)) + Number(parseFloat(grandtotalRetAdj).toFixed(4)));
}

function calculateTotalLtAdjForRet() {
	var grandtotalLtAdj = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="R")
		{
			var itmltadj = $(this).find("#tbl_ltAdj").html();
			grandtotalLtAdj = grandtotalLtAdj + Number(itmltadj);
		}
	});
	$("#totltadj_ret").val(parseFloat(grandtotalLtAdj).toFixed(2));
}

function calculateGrandTotalForRet() {
	console.log("call calculateGrandTotalForRet");
	var itemcount = 0;
	var grandtotal = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="R")
		{
			var itmtotal = $(this).find("#tbl_amnt").html();
			grandtotal = grandtotal + Number(itmtotal);
			itemcount++;
		}
	});
	//$("#totitmcount").text(itemcount);
	$("#totgrosamnt_ret").val(parseFloat(grandtotal).toFixed(2));
}
function calculateTotalMRPForRet() {
	var grandtotalMRP = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="R")
		{
		var itmmrp = $(this).find("#tbl_totamnt").html();
		grandtotalMRP = grandtotalMRP + Number(itmmrp);
		}
	});
	$("#totgrosmrp_ret").val(parseFloat(grandtotalMRP).toFixed(2));
}
function calculateTotalTaxForRet() {
	var grandtotalTax = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="R")
		{
			var itmtax = $(this).find("#tbl_tax").html();
			grandtotalTax = grandtotalTax + Number(itmtax);
		}
	});
	$("#tottaxamnt_ret").val(parseFloat(grandtotalTax).toFixed(4));
}
function calculateTotalDiscForRet() {
	var grandtotalDisc = 0.00;
	$('#peitem tbody tr').each(function() {
		if($(this).find("#tbl_mode").html()=="R")
		{
			var itmdisc = $(this).find("#tbl_disc").html();
			grandtotalDisc = grandtotalDisc + Number(itmdisc);
		}
	});
	$("#totdiscamnt_ret").val(parseFloat(grandtotalDisc).toFixed(4));
}

function calculateNetTotalForRet() {
	var nettotal =(( Number($("#totgrosamnt_ret").val()) + Number($("#tottaxamnt_ret").val())) - (Number($("#totdiscamnt_ret").val()))) - Number($("#totltadj_ret").val());
	var roundednetamnt = Math.round(Number(nettotal));
	console.log("roundednetamnt=" + parseFloat(roundednetamnt).toFixed(2));
	//$("#totnetamnt_ret").val(parseFloat(roundednetamnt).toFixed(2));
	$("#retadj").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff = Number(roundednetamnt) - Number(nettotal);
	console.log("roundoff=" + parseFloat(roundoff).toFixed(2));
	$("#roundoff_ret").val(parseFloat(roundoff).toFixed(2));
}

function openFreeSlNo(freeQty)
{
	if(freeQty<1)
	{}
	else
	{
		var wholeFree = Math.floor(freeQty);
		var pqty = $("#pqty").val();

		var slnos=$("#allVal").val();
		var sno=slnos.split(",");
		for(var i=0;i<sno.length;i++){
			//alert("slnos="+sno[i]);
			$("#textbox"+(i+1)).val(sno[i]);
		}


		openSerialModal(Number(wholeFree)+Number(pqty));
	}
}

function openSerialModal(qty){
	$("#modalCltype").val(1);
//	alert("hi");
//	alert("qty="+qty);
	var serialnum=$("#serialnumberreq").val();
	var oldpqty=$("#oldpqty").val();
	//alert("oldpqty="+oldpqty);
//	alert("serialnum="+serialnum);
	var qtysl=$("#qtysl").val();
	/*if(qtysl!=qty){

		if(serialnum==1){
			//alert("qty="+qty);

			for(var i=2; i<=qty; i++){

			var newTextBoxDiv = $(document.createElement('div'))
		     .attr("id", 'TextBoxDiv' + qty);

		newTextBoxDiv.after().html('<label>SL No #'+ i + ' : </label>' +
		      '<input type="text" onblur="getSLValue($(this).val())"  name="textbox' + i +
		      '" id="textbox' +i + '" value=""  >');

		newTextBoxDiv.appendTo("#TextBoxesGroup");
		$("#clk").val(1);
		$("#qtysl").val(qty);
			}
		}

	}else{

		var clk=$("#clk").val();
		if(clk==0){
		if(serialnum==1){
			//alert("qty="+qty);
			for(var i=2; i<=qty; i++){

			var newTextBoxDiv = $(document.createElement('div'))
		     .attr("id", 'TextBoxDiv' + qty);

		newTextBoxDiv.after().html('<label>SL No #'+ i + ' : </label>' +
		      '<input type="text" onblur="getSLValue($(this).val())"  name="textbox' + i +
		      '" id="textbox' +i + '" value=""  >');

		newTextBoxDiv.appendTo("#TextBoxesGroup");
		$("#clk").val(1);
		$("#qtysl").val(qty);
			}
		}

		}
	}*/
	if(serialnum==1){
			for(var i=1; i<=30; i++){
				if(i<=qty){
					//$("#textbox"+i).val("");
					var slnos=$("#tblrow_" + $("#itemid").val()+"_"+$("#batch_no").val()).find('#tbl_slnos').text();
					var sno=slnos.split(",");
					for(var j=0;j<sno.length;j++){
						//alert("slnos="+sno[i]);
						$("#textbox"+(j+1)).val(sno[j]);
					}
					$("#TextBoxDiv_"+i).removeClass("hide");
					/*if(oldpqty==0){
						$("#TextBoxDiv_"+i).removeClass("hide");
					}else if(oldpqty > qty){
						$("#TextBoxDiv_"+i).removeClass("hide");
						alert("tno="+(Number(qty)+Number(i)));
						$('#textbox' + (Number(qty)+Number(i))).val("");
					}else if(qty>oldpqty){
						alert("else tno="+(Number(qty)+Number(i)));
						$("#TextBoxDiv_"+i).removeClass("hide");
						$('#textbox' + (Number(qty)+Number(i))).val("");
					}else{
						$("#TextBoxDiv_"+i).removeClass("hide");
					}*/

				}else{
					//$("#textbox"+i).val("");
					$("#TextBoxDiv_"+i).addClass("hide");
				}

				}
//

		$("#openSerialModal").modal("show");

	}

}

function openSlNo(){
	$("#slnoduptext").addClass("hide");
	$("#blankslno").html("");
	$("#slnodupval").html("");
	var counter=Number($("#pqty").val())+Number(Math.floor($("#free").val()));
	var arr=[];
	var blank=0;
	var vreg=0;
	var regex = new RegExp("^[a-zA-Z0-9]+$");
	for(var i=1; i<=counter; i++){
		//alert("len="+arr.length);
		arr.push($('#textbox' + i).val());
		if($('#textbox' + i).val()==""){
			blank=1;
		}
		if(!regex.test($('#textbox' + i).val())){
			vreg=1;
		}
		/*var val=$('#textbox' + i).val();
		var res=jQuery.inArray( val, arr );
		console.log(res);
		if(res == -1){

		}else{
			arr.push(val);
			console.log("present");
		}*/

	}

	/*jQuery.each( arr, function( i, val ) {
		console.log("v="+val);
		  // Will stop running after "three"
		});*/
	if(blank==1){
		$("#blankslno").html("Serial number cannot be blank Or Serial number only contain number and alphabet.");
	}else{
		$("#blankslno").html("");
		var sorted_arr = arr.sort();
		var results = [];
		for (var i = 0; i < arr.length - 1; i++) {
	        if (sorted_arr[i + 1] == sorted_arr[i]) {
	            results.push(sorted_arr[i]);
	        }
	    }
		console.log("results="+results);
		if(results.length>0){
			$("#slnoduptext").removeClass("hide");
			$("#slnodupval").html(results);
		}else{
			$("#slnoduptext").addClass("hide");
			$("#slnodupval").html("");
			$("#openSerialModal").modal("hide");
			$("#modalCltype").val(0);
			//**************
			$("#allVal").val(arr);
			for(var i=1; i<=30; i++){
			$('#textbox' + i).val("");
			}
		}
		$('#batch_no').focus();
		//$("#TextBoxesGroup").remove();openSerialModal
	}

}
function closeSlNo(){
	$("#openSerialModal").modal("hide");
	$('#batch_no').focus();
}
function getSLValue(val){
	//alert(val);
	var allv=$("#allVal").val();
	console.log("allv="+allv)	;
	jQuery.each( allv, function( i, val ) {
		console.log("v="+val)	;
		  // Will stop running after "three"
		});
	var res=jQuery.inArray( val, allv );
	console.log(res);
	if(res == -1){

	}else{
		console.log("present");
	}
}

function calculateExpiry(){
	var mfd=$("#mfd").val();
	var expmonth=$("#expmonth").val();
	if(expmonth==""){
		expmonth=0;
	}
	var today = moment(mfd).format('YYYY-MM-DD');
	var dateA = moment(today).add('months', expmonth);
	var today1 = moment(dateA).format('YYYY-MM-DD');
	console.log("d1="+today1);
	//$("#exp").val(today1);
	$("#exp").datepicker("setDate", today1);

}
function calExpMonth(){
	//alert($("#exp").val());
	var exp = moment($("#exp").val());
	console.log(exp);
	var month=exp.diff($("#mfd").val(), 'months');

	$("#expmonth").val(month);
}

function printByBarcode(itemid,itemname,sku,mrp,salerate,batch,exp,qty,storeName,category,subcategory,color,size,grp)
{
	var BarcodePrintParamObj = {};
	BarcodePrintParamObj.itemId = itemid;
	BarcodePrintParamObj.itemName = itemname;
	BarcodePrintParamObj.sku = sku;
	BarcodePrintParamObj.mrp = mrp;
	BarcodePrintParamObj.saleRate = salerate;
	BarcodePrintParamObj.batch = batch;
	BarcodePrintParamObj.noOfCopies = Math.floor(qty);
	BarcodePrintParamObj.expiryDate = exp;
	BarcodePrintParamObj.storeName = storeName;
	BarcodePrintParamObj.storeCurrency = "INR";
	BarcodePrintParamObj.itemCategory = category;
	BarcodePrintParamObj.itemSubCategory = subcategory;
	BarcodePrintParamObj.grpName =grp;
	BarcodePrintParamObj.size = size;
	BarcodePrintParamObj.colour = color;
	/*console.log("BARCODE PRINT DATA:"+JSON.stringify(BarcodePrintParamObj));*/

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/printbysku.htm", BarcodePrintParamObj, function(response) {
		if(response=="success")
		{
			$("#confirmval").val("printBySku");
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.printByBarcodeSucc;
			showConfirmModal();
		}
		else
		{
			$("#confirmval").val("printBySku");
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.printByBarcodeFailure;
			showConfirmModal();
		}
	});
}

function printByBarcodeAll(storeName)
{
	var BarcodePrintParamList = {};
	var ParamList = [];
	$("#peitem tr").not('thead tr').each(function(	i,v) {
		var BarcodePrintParamObj = {};
		BarcodePrintParamObj.itemId = $(this).find('#tbl_itemid').text();
		BarcodePrintParamObj.itemName = $(this).find('#tbl_item_name').text();
		BarcodePrintParamObj.sku = $(this).find('#tbl_sku').text();
		BarcodePrintParamObj.mrp = $(this).find('#tbl_mrp').text();
		BarcodePrintParamObj.saleRate = $(this).find('#tbl_saleRate').text();
		BarcodePrintParamObj.batch = $(this).find('#tbl_batch_no').text();
		var noOfCopy = Number($(this).find('#tbl_pqty').text())+Number($(this).find('#tbl_free').text());
		BarcodePrintParamObj.noOfCopies = Math.floor(noOfCopy);
		BarcodePrintParamObj.expiryDate = $(this).find('#tbl_exp').text();
		BarcodePrintParamObj.storeName = storeName;
		BarcodePrintParamObj.storeCurrency = "INR";
		BarcodePrintParamObj.itemCategory = $(this).find('#tbl_category').text();
		BarcodePrintParamObj.itemSubCategory = $(this).find('#tbl_subcategory').text();
		BarcodePrintParamObj.grpName =$(this).find('#tbl_grp').text();
		BarcodePrintParamObj.size = $(this).find('#tbl_size').text();
		BarcodePrintParamObj.colour = $(this).find('#tbl_color').text();
		ParamList.push(BarcodePrintParamObj);
	});
	BarcodePrintParamList.paramList=ParamList;
	/*console.log(JSON.stringify(BarcodePrintParamList));*/
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/printbyskuall.htm", BarcodePrintParamList, function(response) {
		if(response=="success")
		{
			$("#confirmval").val("printBySku");
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.printByBarcodeSucc;
			showConfirmModal();
		}
		else
		{
			$("#confirmval").val("printBySku");
			document.getElementById('confirmmessagecont').innerHTML = getPurInvText.printByBarcodeFailure;
			showConfirmModal();
		}
	});
}


function getvendorledger_pur(group_code,acc_id,ref_id,para)
{
	   var keyword=ref_id.toString();
	   var trackname=keyword.split("_");


	/*
	 * 	commonobj.id=1; is call another procedure
	 */


	var commonobj={};
	if (para==0) { // for duties and tax

		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;


	}


	if (para==1) { // for round off

		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;

	}

	if (para==2) { // for pur

		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;

	}

if (para==3) { // for credior


		if (cash_ledger==1) {
			commonobj.groupCode=group_code;
			commonobj.accountID=0;
			commonobj.referenceID=0;
			commonobj.id=1;
		}
	else
		{
		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=trackname[0];
		commonobj.id=1;
		}




	}

if (para==4) { // for discount

	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;

}
 if (para==5) { // for lot jas ment

	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;

}




//$('#pleasewaitModal').modal('show');
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchledgerusinggroup.htm", commonobj, function(response) {


		var status = JSON.parse(response);

		if (para==0) {// for duties and tax
			console.log("para0");

			$.each(status, function(i) {


				 $('#duties_ledger_id1').val(status[i].id);

			});
		}

		if (para==1) {// for round off
			console.log("para1");
			$.each(status, function(i) {


				 $('#round_ledger_id1').val(status[i].id);

			});
		}
	if (para==2) { // for purchase

		console.log("para2");
				$.each(status, function(i) {


					 $('#purchase_ledger_id1').val(status[i].id);

				});
			}

	if (para==3) {// for creditor
		console.log("para3");
		$.each(status, function(i) {


			 $('#creditor_ledger_id1').val(status[i].id);

		});
		if ($('#seldistributor').val()==0) {

			$('#creditor_ledger_id1').val(0);
			$('#creditor_html1').html("");
		}

		 }

	if (para==4) {// for discount
		console.log("para4");
		$.each(status, function(i) {


			 $('#discount_ledger_id1').val(status[i].id);

		});
		 }
	 if (para==5) {// for lot adjasment
		console.log("para5");
		$.each(status, function(i) {


			 $('#lotadj_ledger_id1').val(status[i].id);

		});
		 }

		//$('#pleasewaitModal').modal('hide');
		//chngeResultStat(status);
	});

}


var cash_ledger=0;
 function call_cash_ledger()
 {


	 if(document.getElementById("invmode").checked==true)
		 {
		    cash_ledger=1;
			getvendorledger_pur($('#cash_code').val(),0, 0,3);// for creditor account
		 }

	 if(document.getElementById("invmode").checked==false)
		 {
		   cash_ledger=0;
			getvendorledger_pur($('#creditor_code1').val(),0, $('#seldistributor').val(),3);// for creditor account

		 }



 }

	/*
	 * add here new

	$("#crnote").keyup(function() {

	var backup_net_tota= parseFloat( $('#temp_net_total_amt_backup').val() );


	var tem_net_total=$('#totnetamnt').val();
	var temp_supp_bil_amt=$('#temp_supplier_amt_backup').val();

	var temp_advance_amt= $('#crnote').val();

	if (isEmpty(temp_advance_amt)) {// when advance is empty

		if (temp_supp_bil_amt>0) {
			$('#creditor_amt').val(parseFloat(temp_supp_bil_amt).toFixed(2) );
			$('#suppbillamt').val(parseFloat(temp_supp_bil_amt).toFixed(2) );
		}
		else {
			$('#creditor_amt').val(parseFloat(backup_net_tota).toFixed(2) );
		}


	}
	else {// non empty

			if (temp_advance_amt<=backup_net_tota) {


				if (temp_supp_bil_amt>0) {// supply bill amt present

					//$('#suppbillamt').val( ( parseFloat( temp_supp_bil_amt ) - parseFloat( temp_advance_amt ) ).toFixed(2) );
					$('#creditor_amt').val(( parseFloat( temp_supp_bil_amt ) - parseFloat( temp_advance_amt ) ) );

				}else {// for net total

					$('#creditor_amt').val( (parseFloat(backup_net_tota) - parseFloat(temp_advance_amt) ) .toFixed(2));
				}
			}
			else {// when advance amt greater then nettoatla

				if (temp_supp_bil_amt>0) {
					$('#creditor_amt').val(parseFloat(temp_supp_bil_amt).toFixed(2) );
				}
				else {
					$('#creditor_amt').val(parseFloat(backup_net_tota).toFixed(2) );
				}

				$('#crnote').val(0.0);
			}
	}



	});*/

 function isEmpty(val) {
	    return (val === undefined || val == null || val.length <= 0) ? true : false;
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

	$("#exp").keyup(function() {
	if (isWholesale==1) // whole sale
	{
		if ($(this).val().length == 2) {
			if ($(this).val() <= 12 && $(this).val() > 0) {
				$(this).val($(this).val() + "/");
			} else {
				$(this).val("");
			}
		}
		if($(this).val().length >= 5) {
			$(this).val(($(this).val()).substring(0,5));
			expiryCalculation($(this).val());
		}


		
	}
});

function expiryCalculation(expiry)
{
	function normalizeYear(year){
	    // Century fix
	    var YEARS_AHEAD = 10;
	    if (year<100){
	        var nowYear = new Date().getFullYear();
	        year += Math.floor(nowYear/100)*100;
	        if (year > nowYear + YEARS_AHEAD){
	            year -= 100;
	        } else if (year <= nowYear - 100 + YEARS_AHEAD) {
	            year += 100;
	        }
	    }
	    return year;
	}

	
	    var match="";
	    /*if(purorderid!=0)
	    {
	    	match=$('#'+itemid+'_exp').val().match(/^\s*(0?[1-9]|1[0-2])\/(\d\d|\d{4})\s*$/);
	    }
	    else
	    {*/
	    	match=$('#exp').val().match(/^\s*(0?[1-9]|1[0-2])\/(\d\d|\d{4})\s*$/);
	    //}
	    if (!match){
	       // alert('Input string isn\'t match the expiration date format or date fragments are invalid.')
	    	
			/*if(purorderid!=0)
			{
				document.getElementById('alertmessagecont').innerHTML = "exp date fragments are invalid.";
				$('#'+itemid+'_exp').focus();
				$('#'+itemid+'_exp').val("");
			}
			else
			{*/
				document.getElementById('confirmmessagecont').innerHTML = "exp date fragments are invalid.";
				$(this).focus();
				$("#confirmval").val(-2);
				$('#exp').val("");
			//}
			//return false;
	    }
	    var exp = new Date(normalizeYear(1*match[2]),1*match[1]-1,1).valueOf();
	    var now=new Date();
	    var currMonth = new Date(now.getFullYear(),now.getMonth(),1).valueOf();
	    if (exp<=currMonth){
	    	
			/*if(purorderid!=0)
			{
				document.getElementById('alertmessagecont').innerHTML = "Item Expired.";
				$('#'+itemid+'_exp').focus();
				$('#'+itemid+'_exp').val("");
			}
			else
			{*/
				document.getElementById('confirmmessagecont').innerHTML = "Item Expired.";
				$(this).focus();
				$("#confirmval").val(-2);
				$('#exp').val("");
			//}
			//return false;
	    } else {
	    	if($("#expalertrequiremnt").val()==0)
	    	{
	    		$("#confirmval").val("no exp alert");
	    	}
	    	else
	    	{
	    		// alert('Valid');
		    	var currentTime = new Date()

		    	// returns the month (from 0 to 11)
		    	var currMonth = currentTime.getMonth() + 1

		    	// returns the year (four digits)
		    	var currYear = currentTime.getFullYear();
		    	
		    	var arr = expiry.split('/');
		    	expMonth = arr[0];
		    	expYear = 20+arr[1];
		    	
		    	numberOfMonths = (expYear - currYear) * 12 + (expMonth - currMonth);
		    	yr= Math.floor(numberOfMonths/12);
		    	month = numberOfMonths % 12;
		    	if(yr>0 && month>0)
		    	{
		    		document.getElementById('confirmmessagecont').innerHTML = "This item expires within "+yr+" years "+month+" months from the system date";
		    	}
		    	else if(yr==0)
		    	{
		    		document.getElementById('confirmmessagecont').innerHTML = "This item expires within "+month+" months from the system date";
		    	}
		    	else
		    	{
		    		document.getElementById('confirmmessagecont').innerHTML = "This item expires within "+yr+" years from the system date";
		    	}
			    $("#confirmval").val(-3);
		    	/*$("#confirmval").val(-1);
		    	showConfirmModal();
				document.getElementById('alertMsg').innerHTML = "";*/
	    	}
	       
	    };
	
	    if($("#confirmval").val()=="no exp alert")
		{}
	    else
	    {
	    	showConfirmModal();
			document.getElementById('alertMsg').innerHTML = "";
	    }
    	
}