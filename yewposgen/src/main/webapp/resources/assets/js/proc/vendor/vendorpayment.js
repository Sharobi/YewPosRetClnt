var mode="CIH";
$( document ).ready(function() {
    var currentDate = new Date();
	$('#checkdate').datepicker({
		format : 'yyyy-mm-dd',
		//endDate : currentDate,
		autoclose : true,
    });

	$('#paydt').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
    });
});
function getSelectedVendorPendingPayment(vendorid) {
	$("#vendorduepaymentbodylist").text("");
	var outs=$("#seldistributor").val().split("_")[1];

	$("#totselpayamt").val(parseFloat(0).toFixed(2));
	$("#totdueamt").val(parseFloat(0).toFixed(2));
	$("#outstandingamt").text(parseFloat(outs).toFixed(2));
	$("#paymenttype").val(1);
	$("#cardno").attr('readonly','readonly');
	$("#checkno").attr('readonly','readonly');
	$("#checkdate").attr('disabled','disabled');
	$("#bankname").attr('readonly','readonly');
	$("#remamt").val(parseFloat(0).toFixed(2));
	$("#payamt").val(parseFloat(0).toFixed(2));
	$("#pendingamt").val(parseFloat(0).toFixed(2));
	var commonResultMap = {};
	commonResultMap.distributorId = $("#seldistributor").val().split("_")[0];
	commonResultMap.paymentDate = $("#paydt").val();
	//console.log(JSON.stringify(commonResultMap));
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchvendorpendingpayment.htm", commonResultMap, function(response) {
		//console.log(response);
		var vendorduepaymentlist = JSON.parse(response);
		for ( var i = 0; i < vendorduepaymentlist.length; i++) {
			var vendorduepayment = vendorduepaymentlist[i];
			$("#outstandingamt").text(parseFloat(vendorduepayment.outstandingAmount).toFixed(2));
			var starttrline = "<tr id=" + vendorduepayment.invFromType + " >";
			var chkbox = "<td><input id='" + vendorduepayment.invFromType + "_modretcheck' class='chkboxcheked' type='checkbox' onclick='getCheckedVenPayDet(" + JSON.stringify(vendorduepayment) + ")' value='" + JSON.stringify(vendorduepayment) + "' ></td>";
			var invno = "<td>" + vendorduepayment.invNo + "</td>";
			var invdate = "<td>" + moment(vendorduepayment.invDate).format('YYYY-MM-DD') + "</td>";
			var netAmount = "<td id='vennetamt'>" + parseFloat(vendorduepayment.netAmount).toFixed(2) + "</td>";
			var advAmount = "<td id='venadjamt'>" + parseFloat(vendorduepayment.advAmount).toFixed(2) + "</td>";
			var otherAdjAmount = "<td id='venadjamt'>" + parseFloat(vendorduepayment.otherAdjAmount).toFixed(2) + "</td>";
			var dueAmount = "<td id='vendueamt'>" + parseFloat(vendorduepayment.dueAmount).toFixed(2) + "</td>";
			var billno = "<td>" + vendorduepayment.billNo + "</td>";
			var endtrline = "</tr>";
			createdrowline = starttrline + chkbox + invno + invdate + netAmount + advAmount+otherAdjAmount + dueAmount + billno + endtrline;
			$("#vendorduepaymentbodylist").append(createdrowline);
		}
		calculateTotalDueAmt(0);
	});


	  var trackname=vendorid.split("_");


	if (trackname[0]==0) {

	$('#dr_legder_id').val(0);

	}else
		{
		getvendorledger('SCR',0,vendorid,0);
		}

	mode="CIH";
	getvendorledger(mode,0,0,1);

}

function calculateTotalDueAmt(para) {
	var totalDueAmt = 0.00;
	$('#vendorduepaymenttable tbody tr').each(function() {
		var dueAmt = $(this).find("#vendueamt").html();
		totalDueAmt = totalDueAmt + Number(dueAmt);
	});
	var outstandingamt=$("#outstandingamt").text();
	$("#totdueamt").val(parseFloat(totalDueAmt+Number(outstandingamt)).toFixed(2));
	if (para==0) {// when vendor is selected
		$("#totdueamt").val(parseFloat(Number(outstandingamt)).toFixed(2));
	}
}

function getSelPaymentMode(paymodeid) {
	var text = $("#paymenttype").find("option:selected").text();
	
	if (text == "Card" || text == "CARD" || text == "card") {
		$("#checkno").val('');
		$("#checkdate").val('');
		$("#bankname").val('');
		$("#cardno").removeAttr('readonly');
		$("#checkno").attr('readonly','readonly');
		$("#checkdate").attr('disabled','disabled');
		$("#bankname").attr('readonly','readonly');
		mode='CAB';

	} else if (text == "Cheque" || text == "CHEQUE" || text == "cheque") {
		$("#cardno").val('');
		$("#cardno").attr('readonly','readonly');
		$("#checkno").removeAttr('readonly');
		$("#checkdate").removeAttr('disabled');
		$("#bankname").removeAttr('readonly');
		mode='CAB';
	} else {
		$("#cardno").val('');
		$("#checkno").val('');
		$("#checkdate").val('');
		$("#bankname").val('');
		$("#cardno").attr('readonly','readonly');
		$("#checkno").attr('readonly','readonly');
		$("#checkdate").attr('disabled','disabled');
		$("#bankname").attr('readonly','readonly');

		mode='CIH';
	}


	getvendorledger(mode,0,0,1);
}

function getCheckedVenPayDet(venpaydet){
	var totseldueamt=0;
	$('#vendorduepaymenttable > tbody > tr').each(function() {
		var invid = this.id;
			if ($("#" + invid + "_modretcheck").is(":checked")) {
				var vendorpaydetail = $("#" + invid + "_modretcheck").val();
				//console.log("vendorpaydetail="+vendorpaydetail);
				var vendorpaydet = JSON.parse(vendorpaydetail);
				totseldueamt=totseldueamt+Number(vendorpaydet.dueAmount);
				/*if(vendorpaydet.invFrom=='PURRTN'){
					totseldueamt=totseldueamt-Number(vendorpaydet.dueAmount);
				}else{
					totseldueamt=totseldueamt+Number(vendorpaydet.dueAmount);
				}*/

			}

	});
//	alert(parseFloat(totseldueamt).toFixed(2));
//	if(totseldueamt<0){
//		$("#totselpayamt").val(parseFloat(0).toFixed(2));
//	}else{
	var outstandingamt=$("#outstandingamt").text();
	$("#totdueamt").val(parseFloat(totseldueamt+Number(outstandingamt)).toFixed(2));
		$("#totselpayamt").val(parseFloat(totseldueamt).toFixed(2));
//	}
	//calculateTotalDueAmt();
}

$("#payamt").keyup(function() {
	if (isNaN($("#payamt").val())) {
		//document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Four Digit.";
		$(this).focus();
		return false;
	} else {
		var payamt = $(this).val();
		var pendingamt=$("#totselpayamt").val()-payamt;
		var remaningamt=$("#totdueamt").val()-payamt;
		$("#pendingamt").val(parseFloat(pendingamt).toFixed(2));
		$("#remamt").val(parseFloat(remaningamt).toFixed(2));
		//document.getElementById('alertmessagecont').innerHTML = "";
	}
});
function saveVendorPayment(){
	var paymenttype = $("#paymenttype").val();
	var totselpayamt=$("#totselpayamt").val();
	var payamt=$("#payamt").val();
	var totdueamt=$("#totdueamt").val();

		if(payamt>0 && totdueamt>0){
		var distributorPayment = {};
		var allDistributorPaymentdetails = [];
		$('#vendorduepaymenttable > tbody > tr').each(function() {
			var invid = this.id;
				if ($("#" + invid + "_modretcheck").is(":checked")) {
					var distributorPaymentdetails = {};
					var vendorpaydetail = $("#" + invid + "_modretcheck").val();
					var vendorpaydet = JSON.parse(vendorpaydetail);
					distributorPaymentdetails.invFrom=vendorpaydet.invFrom;
					distributorPaymentdetails.invId=vendorpaydet.invId;
					allDistributorPaymentdetails.push(distributorPaymentdetails);
				}
		});
		distributorPayment.distributorPaymentDetails=allDistributorPaymentdetails;
		distributorPayment.invDate=$("#paydt").val();
		distributorPayment.invMode=$("#paymenttype").val();
		distributorPayment.distributorId=$("#seldistributor").val().split("_")[0];
		distributorPayment.payableAmount=$("#totselpayamt").val();
		distributorPayment.payAmount=$("#payamt").val();
		distributorPayment.netAmount=Number($("#outstandingamt").text())+Number($("#totselpayamt").val());
		distributorPayment.remainingAmount=(Number($("#outstandingamt").text())+Number($("#totselpayamt").val()))-$("#payamt").val();
		distributorPayment.remarks=$("#remarks").val();
		distributorPayment.cr_account_id = $("#cr_legder_id").val();
		distributorPayment.dr_account_id = $("#dr_legder_id").val();
 	    distributorPayment.cr_amount = $("#payamt").val();
		distributorPayment.dr_amount = $("#payamt").val();

		var error=0; // for ledger check

		if (is_acc==1) {

			if (distributorPayment.cr_account_id>0&& distributorPayment.dr_account_id>0) {
				  error=0;
			}else {
				 error=1;
					$("#confirmval").val(0);
					document.getElementById('confirmmessagecont').innerHTML = getVendorPaymentText.LedgerAccountError;
					showConfirmModal();
				 return false;
			}
		}

		if(paymenttype=="3")
		{
			if($("#cardno").val()=="")
			{

				//document.getElementById('alertMsg').innerHTML = "Please Enter Card No.";
				$("#confirmval").val(0);
				document.getElementById('confirmmessagecont').innerHTML = getVendorPaymentText.blankCardNo;
				showConfirmModal();
				return false;
			}
			else
			{
				//document.getElementById('alertMsg').innerHTML = "";
				distributorPayment.cardNo=$("#cardno").val();
			}
		}
		if(paymenttype=="4")
		{
			if(($("#checkno").val()=="") || ($("#checkno").val()==0))
			{
				//document.getElementById('alertMsg').innerHTML = "Please Enter Valid Cheque No.";
				$("#confirmval").val(0);
				document.getElementById('confirmmessagecont').innerHTML = getVendorPaymentText.blankChequeNo;
				showConfirmModal();
				return false;
			}
			if(isNaN($("#checkno").val()))
			{
				//document.getElementById('alertMsg').innerHTML = "Please Enter Valid Cheque No.";
				$("#confirmval").val(0);
				document.getElementById('confirmmessagecont').innerHTML = getVendorPaymentText.intChequeNo;
				showConfirmModal();
				return false;
			}
			else
			{
				document.getElementById('alertMsg').innerHTML = "";
				distributorPayment.chequeNo=$("#checkno").val();
				distributorPayment.chequeDate = $("#checkdate").val();

				if(($("#bankname").val()!="") || ($("#bankname").val()!=0))
				{
					distributorPayment.bankName = $("#bankname").val();
				}
				else{}

			}
		}
		//alert(JSON.stringify(distributorPayment));
		if (error==0) {
			$('#pleasewaitModal').modal('show');
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/createorupdatevendorpayment.htm", distributorPayment, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == '0') {
					$("#confirmval").val(0);
					document.getElementById('confirmmessagecont').innerHTML = getVendorPaymentText.paymentNotDone;
					showConfirmModal();
				} else {
					//sessionStorage.setItem("seldistributorId", $("#seldistributor").val());
					$("#confirmval").val($("#seldistributor").val().split("_")[0]);
					$('#confirmvalfrsaveupdate').val(response);
					document.getElementById('vendorPaymentconfirmmess').innerHTML = getVendorPaymentText.paymentDone;
					//showConfirmModal();
					$('#confirmPrintVendorModal').modal('show');

				}


			});
		}



	}else{
		$("#totpayamtnegModal").modal("show");
	}

}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function postVendorPayment(paymentId){
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.paymentId = paymentId;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/postvendorpayment.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			$("#confirmval").val("postpaymnt");
			document.getElementById('confirmmessagecont').innerHTML = getVendorPaymentText.dataSucPost;
			showConfirmModal();
		} else {
			$("#confirmval").val(0);
			document.getElementById('confirmmessagecont').innerHTML = getVendorPaymentText.dataNotPost;
			showConfirmModal();
		}

	});
}

function getPayDetView(pid,vendorname){
	$("#vendorpaymentbodylist").html("");
	$("#vendorname").html(vendorname);
	var CommonResultSetMapper = {};
	CommonResultSetMapper.paymentId = pid;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/getvendorsinglepaymentdetails.htm", CommonResultSetMapper, function(response) {
		//$('#pleasewaitModal').modal('hide');
		console.log("r="+response);
		var paydet = JSON.parse(response);
		for ( var i = 0; i < paydet.length; i++) {
			var vpdet = paydet[i];
			var starttrline = "<tr id='" + vpdet.invNo + "' >";
			var invNo = "<td >" + vpdet.invNo + "</td>";
			var invdate = "<td >" + moment(vpdet.invDate).format('YYYY-MM-DD') + "</td>";
			var netAmount = "<td >" + vpdet.netAmount + "</td>";
			var advAmount = "<td >" + vpdet.advAmount + "</td>";
			var otherAdjAmount = "<td >" + vpdet.otherAdjAmount + "</td>";
			var dueAmount = "<td >" + vpdet.dueAmount + "</td>";
			var billNo=0;
			if(vpdet.billNo==undefined){
				billNo = "<td >" + 0 + "</td>";
			}else{
				billNo = "<td >" + vpdet.billNo + "</td>";
			}

			var endtrline = "</tr>";
			createdrowline = starttrline+invNo + invdate+netAmount+advAmount+otherAdjAmount+dueAmount+ billNo+ endtrline;
			$("#vendorpaymentbodylist").append(createdrowline);
		}
		$("#paydetailModal").modal('show');
	});
}
function targetURL(){
	if($("#confirmval").val()==0)
	{
		location.href = '#';
	}
	else if($("#confirmval").val()=="postpaymnt")
	{
		location.href = BASE_URL + '/vendor/loadvendorpayreg.htm';
	}
	else
	{
		location.href = BASE_URL + '/vendor/loadvendorpay/'+$("#confirmval").val()+'.htm';
	}
}


/*
 * add for result
 */


function getvendorledger(group_code,acc_id,ref_id,para)
{
	 var keyword=ref_id.toString();
	  var trackname=keyword.split("_");

	var commonobj={};
	if (para==0) { // for creditor

		commonobj.groupCode=group_code;
		commonobj.accountID=acc_id;
		commonobj.referenceID=trackname[0];
	}


	if (para==1) { // for cash/bank

		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
	}



	//$('#pleasewaitModal').modal('show');
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchledgerusinggroup.htm", commonobj, function(response) {
		//$('#pleasewaitModal').modal('hide');

		var status = JSON.parse(response);

		if (para==0) {// sunndy creditor

			if (status.length>0) {
				$.each(status, function(i) {
					// $('#dr_ledger').val(status[i].name);
					 $('#dr_legder_id').val(status[i].id);
					// $("#dr_ledger").attr("disabled",true);
				});
			}else {
				 $('#dr_legder_id').val(0);
			}


		}

		if (para==1) {

			$.each(status, function(i) {

				//$('#cr_ledger').val(status[i].name);
				$('#cr_legder_id').val(status[i].id);


			});
		}


		//chngeResultStat(status);
	});

}
/*
 * for vendor payment print
 */
function printVendorPayment()
{
	if ($('input[name=vendor_payment]').is(":checked"))
	{

		location.href= BASE_URL + '/vendor/vendorpaymentprint/'+$("#confirmvalfrsaveupdate").val()+'.htm';

		//location.href = BASE_URL + '/customer/loadcustomer.htm';

	}
	else
	{
		location.href = BASE_URL + '/vendor/loadvendorpayreg.htm';
	}
}
function targetActionPur(){
	 var result=$("#confirmvalfrsaveupdate").val();
 	console.log("vendorpayment statu="+result);
	if(result==0){
		location.href = "#";
	}

	else {
		location.href = BASE_URL + '/vendor/loadvendorpayreg.htm';

	}
}
