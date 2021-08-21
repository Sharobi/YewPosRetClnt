function deletePurchaseInv(pinvid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(pinvid);
	$("#confirmtype").val(0); // for delete set as zero
	
}

function DoConfirmPur(){
	var pinvid=$("#confirmid").val();
	var type=$("#confirmtype").val();
	if(type==1){// for post
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purInvId = pinvid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/postpurinv.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataSucPost;
				showConfirmModal();
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataNotPost;
				showConfirmModal();
			}

		});
	}
	if(type==0){// for delete
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purInvId = pinvid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/deletepurinv.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataSucDelete;
				showConfirmModal();
			}
			else if (response == '-7') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataInUseErr;
				showConfirmModal();
			}
			else {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataNotDelete;
				showConfirmModal();
			}

		});
	}
}

function postPurchaseInv(pinvid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(pinvid);
	$("#confirmtype").val(1); // for post set as one
	
}

/* =============== Post All Functionality Starts =============== */

function postAllPI(){
	var purchase = {};
	var allpurchasedetails = [];
	/*$('#purinvtable > tbody  > tr input[type="checkbox"]').each(function() {
		if(this.checked){
			var purchasedetails = {};
			purchasedetails.purchaseId = this.id;
			allpurchasedetails.push(purchasedetails);
		}
	});*/
	if($("#example-select-all").prop("checked") == true){
		 var rows = $("#purinvtable").dataTable().fnGetNodes();
		 for(var i=0;i<rows.length;i++)
	        {      
	              var status=$.trim( $(rows[i]).find("td:eq(9)").html());
	              if(status == "Unposted"){
	            	var purchasedetails = {};
				    purchasedetails.purchaseId = rows[i].id;
				    allpurchasedetails.push(purchasedetails);
				}
	        }
	   }else{
		   $('#purinvtable > tbody  > tr input[type="checkbox"]').each(function() {
			if(this.checked){
				var purchasedetails = {};
				purchasedetails.purchaseId = this.id;
				allpurchasedetails.push(purchasedetails);
			}
		});
	   }
	purchase.purchaseDetails = allpurchasedetails;
	if($.isEmptyObject(purchase.purchaseDetails)){
		console.log("empty");
		document.getElementById('emptyalertmsg').innerHTML =  getFieldText.selectdata;
		$('#emptyAlertModal').modal('show');
	}else{
		console.log("checkedid="+JSON.stringify(purchase));
		$('#pleasewaitModal').modal('show');
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/postallselectedpurchaseinvoice.htm", purchase, function(response) {
			$('#pleasewaitModal').modal('hide');
			console.log(response);
			if (response == '0') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataNotPost;
				showConfirmModal();
				
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataSucPost;
				showConfirmModal();
			}

		});
	}
}

/* =============== Post All Functionality Ends =============== */


function targetURL(){
	location.href = BASE_URL + '/purinv/loadpurinvregister.htm';
}
function setChange(d){
	if (!d.checked) {
		$("#example-select-all").prop("checked", false);
		//$("#postall_td").addClass("hide");
  }
}

function printPurchaseInv(purchaseid){
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/purinv/printPurchaseChalan/" + purchaseid + ".htm", function(resp) {
		$("#printablediv").html(resp);
		localPrint();
	}, null);
}
function localPrint() {
    var divToPrint = document.getElementById('printablediv');
	//document.getElementById('removePrint80px').style.display = 'none';
	newWin = window.open("");
	newWin.document.write(divToPrint.outerHTML);
	newWin.document.close();
	newWin.focus();
	newWin.print();
	//document.getElementById('printablediv').style.display = 'block';

	newWin.close();

}