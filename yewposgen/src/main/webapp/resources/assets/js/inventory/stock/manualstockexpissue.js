
function  setAllCheck(){
	$("#seldistributor").val(selectedDistributor);
	  if(selectedDistributor == "0" ){
		  $("#example-select-all").attr('checked', false);  
		 $("#example-select-all").attr("disabled", true);
	  }else if(selectedDistributor == "" ){
		  $("#example-select-all").attr('checked', false); 
		  $("#example-select-all").attr("disabled", true);
	  }else{
		  $("#example-select-all").attr("disabled", false);
	  }
	  
	  var rowCount = $('#expitem tr').length;
	  var i=1;
	  $('#expitem > tbody > tr').each(function() {
		  var itemid = this.id;
		  if ($("#" + itemid + "_modretcheck").is(":checked")) {
		     i++;	 
		  }
      });
	   if(rowCount == i && rowCount >1){
		  $("#example-select-all").attr('checked', true);  
	  }
	  
   } 

function getvendorledger_expire(group_code,acc_id,ref_id,para)
{
	var commonobj={};
	if (para==0) { // for party ledger 
		
		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=ref_id;
		commonobj.id=1;
		 
	 
	}
	
	
	if (para==1) { // for exp ledger
		
		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;
		 
	}
 
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchledgerusinggroup.htm", commonobj, function(response) {
		var status = JSON.parse(response);
		
		if (para==0) {// for duties and tax 
			console.log("para0");
			
			$.each(status, function(i) {
				 
				 $('#creditor_legder_id_val').val(""+status[i].name);
				 $('#creditor_legder_id').val(status[i].id);
			 
			});
		}	
		
		if (para==1) {// for round off 
			console.log("para1");
			$.each(status, function(i) {
				 
				 $('#exp_ledger_id_val').val(status[i].name);
				 $('#exp_ledger_id').val(status[i].id);
				 
			});
		}
 
	
});
}




$('#example-select-all').on('click', function(){
	if ($('#example-select-all').prop("checked") == true) {
	   	  $('#expitem > tbody > tr').each(function() {
		         var itemid = this.id;
		         $("#" + itemid + "_modretcheck").prop("checked", true);
		        
	         });
	     }else{
	   	   $('#expitem > tbody > tr').each(function() {
			       var itemid = this.id;
			       $("#" + itemid + "_modretcheck").prop('checked', false);  
			       
	              });
	     }
	    bulkSelectCalculation();
 
 });

function bulkSelectCalculation(){
	var len=$('.chkboxcheked:checked').length;
	$("#alertMsg").text("");
	var first=0;
	var i=0;
	var dif_vendor=0;
	var total_sale_rate=0;
    var id="";
    if (len!=0) {
	
	  $('#expitem > tbody > tr').each(function() {
		var itemid = this.id;
	     if ($("#" + itemid + "_modretcheck").is(":checked")) {
				 i++;
			 
			 if($("#" + itemid).find('#expdistributor').val()==0)
				{
					$(this).attr('checked', false);
					len=-1;
				
				}else {
					
					if (i==1) {// for vendor call first
						 
						first=$("#" + itemid).find('#expdistributor').val();
					}
					if (first==$("#" + itemid).find('#expdistributor').val()) {// checking same vendor and calulation 
					 
						
						 //total_sale_rate=parseFloat(total_sale_rate)+parseFloat($("#" + itemid).find('#tbl_rate').text());
						  // total_sale_rate=parseFloat(total_sale_rate)+parseFloat( $("#" + itemid).find('#tbl_pqty').text() * $("#" + itemid).find('#tbl_rate').text() );
						
						var itemPrice = (Number($("#" + itemid).find('#tbl_pqty').text() *  $("#" + itemid).find('#tbl_cnvrsn').text()) + Number($("#" + itemid).find('#tbl_lqty').text())) * ( parseFloat($("#" + itemid).find('#tbl_rate').text()) / $("#" + itemid).find('#tbl_cnvrsn').text());     
						total_sale_rate= parseFloat(total_sale_rate)+(parseFloat(itemPrice));
					
					
					}else {
				 
						dif_vendor=1;
						id=itemid;
						//$('this').prop('checked', false);
						$("#alertMsg").text("Please select all same vendor");
					}
					
				}
			 
			}
	 
	});
	  
	  
	  
	
	if (dif_vendor==1) {
		$("#" + id + "_modretcheck").prop('checked', false);
	}else {
		getvendorledger_expire($('#creditor_code1').val(),0,first,0);// for expe	
	}
	$('#dr_amount').val(total_sale_rate.toFixed(2));
	$('#cr_amount').val(total_sale_rate.toFixed(2));
}else{
	$('#dr_amount').val("");
	$('#cr_amount').val("");	
}
	
}
 
function checkSelection(){
	 var selectedDistributors = [];
	$('#expitem > tbody > tr').each(function() {
		var itemid = this.id;
		var distributorid = $("#" + itemid).find('#expdistributor').val();
		selectedDistributors.push(distributorid);
		
	});
	var status = selectedDistributors.allValuesSame(); 
	if(status == true){
		 $("#example-select-all").attr("disabled", false);
	}else{
		 $("#example-select-all").prop('checked', false);  
		 $("#example-select-all").attr("disabled", true);
	}
	
}

Array.prototype.allValuesSame = function() {
   for(var i = 1; i < this.length; i++)
        {
       if(this[i] !== this[0])
           return false;
      }
    return true;
}



function call_vendor(id)
{
		var len=$('.chkboxcheked:checked').length;
		$("#alertMsg").text("");
		var first=0;
		var i=0;
		var dif_vendor=0;
		
		var total_sale_rate=0;

		
		 if ($("#" + id + "_modretcheck").prop("checked") == false) {
			 $("#example-select-all").attr('checked', false);
		 }
		
		
		
		
 
	if (len!=0) {
		
		
		$('#expitem > tbody > tr').each(function() {
			var itemid = this.id;
		     if ($("#" + itemid + "_modretcheck").is(":checked")) {
					 i++;
				 
				 if($("#" + itemid).find('#expdistributor').val()==0)
					{
						$(this).attr('checked', false);
						len=-1;
					
					}else {
						
						if (i==1) {// for vendor call first
							 
							first=$("#" + itemid).find('#expdistributor').val();
						}
						if (first==$("#" + itemid).find('#expdistributor').val()) {// checking same vendor and calulation 
						 
							
							// total_sale_rate=parseFloat(total_sale_rate)+parseFloat($("#" + itemid).find('#tbl_rate').text());
							  // total_sale_rate=parseFloat(total_sale_rate)+parseFloat( $("#" + itemid).find('#tbl_pqty').text() * $("#" + itemid).find('#tbl_rate').text() );

							var itemPrice = (Number($("#" + itemid).find('#tbl_pqty').text() *  $("#" + itemid).find('#tbl_cnvrsn').text()) + Number($("#" + itemid).find('#tbl_lqty').text())) * ( parseFloat($("#" + itemid).find('#tbl_rate').text()) / $("#" + itemid).find('#tbl_cnvrsn').text());     
							total_sale_rate= parseFloat(total_sale_rate)+(parseFloat(itemPrice));
							
						}else {
					 
							dif_vendor=1;
							//$('this').prop('checked', false);
							$("#alertMsg").text("Please select all same vendor");
						}
						
					}
				 
				}
		 
		});
		
		if (dif_vendor==1) {
			$("#" + id + "_modretcheck").prop('checked', false);
		}else {
			getvendorledger_expire($('#creditor_code1').val(),0,first,0);// for expe	
		}
		
	 
		$('#dr_amount').val(total_sale_rate.toFixed(2));
		$('#cr_amount').val(total_sale_rate.toFixed(2));
	}else{
		$('#dr_amount').val("");
		$('#cr_amount').val("");	
	}
	
	
	
			
}


function getcheckedexpiryitemlist() {
	var len=$('.chkboxcheked:checked').length;
	var count = 0;
	var ExpiryObj = {};
	var ExpiryDetailsObjArr = [];
	var i = 0;
	var j=0;
	var expId = $("#expId").val();
	var total_amt=0;
	var total_tax_amt=0;
	var total_net_amt=0;
	var total_mrp = 0;
	var first=0;
    var dif_vendor=0;
    $("#alertMsg").text(" ");
	// loop start
	$('#expitem > tbody > tr').each(function() {
		var ExpiryDetailsObj = {};
		var itemid = this.id;
		console.log("len="+len);
		
		if(len==0){
			$("#alertMsg").text("Please check at least one item.");
		}
		else if(len==-1){
			$("#alertMsg").text("Please check at least one item.");
			$("#alertMsg").text("Please select vendor of checked item.");
		}
		else{
			$("#alertMsg").text("");
			if ($("#" + itemid + "_modretcheck").is(":checked")) {
				 j++;
				if($("#" + itemid).find('#expdistributor').val()==0)
				{
					$(this).attr('checked', false);
					len=-1;
				}
				else
				{
					if (j==1) {// for vendor call first
						 
						first=$("#" + itemid).find('#expdistributor').val();
					}
					if (first==$("#" + itemid).find('#expdistributor').val()) {// checking same vendor and calulation 
					 
						var expiryItemDetails = JSON.stringify($("#" + itemid + "_modretcheck").val());
						//alert($.parseJSON(expiryItemDetails).itemName);
						ExpiryDetailsObj.id=0;
						ExpiryDetailsObj.itemId=$("#" + itemid).find('#tbl_item_id').text();
						ExpiryDetailsObj.itemName=$("#" + itemid).find('#tbl_item_name').text();
						ExpiryDetailsObj.itemUniqueKey=itemid;
						ExpiryDetailsObj.batchNo=$("#" + itemid).find('#tbl_batch_no').text();
						ExpiryDetailsObj.expiryDateFormat=$("#" + itemid).find('#tbl_exp').text();
						ExpiryDetailsObj.packUnitName=$("#" + itemid).find('#tbl_punit_name').text();
						ExpiryDetailsObj.packUnitId=$("#" + itemid).find('#tbl_punit_id').text();
						ExpiryDetailsObj.rackName=$("#" + itemid).find('#tbl_rack_name').text();
						ExpiryDetailsObj.rackId=$("#" + itemid).find('#tbl_rack_id').text();
						ExpiryDetailsObj.stockQty=$("#" + itemid).find('#tbl_stock_qty').text();
						ExpiryDetailsObj.mrp=parseFloat($("#" + itemid).find('#tbl_mrp').text()).toFixed(2);
						ExpiryDetailsObj.rate=$("#" + itemid).find('#tbl_rate').text();
						ExpiryDetailsObj.distributorId=$("#" + itemid).find('#expdistributor').val();
						ExpiryDetailsObj.distributorName=$("#" + itemid).find('#expdistributor option:selected').text();
						/*if($("#" + itemid).find('#tbl_expdt').text() == ""){
							ExpiryDetailsObj.expiryDate= $("#invDt").val();
						}else{*/
						   ExpiryDetailsObj.expiryDate=$("#" + itemid).find('#tbl_expdt').text();
						/*}*/
						ExpiryDetailsObj.packQty=parseFloat($("#" + itemid).find('#exp_qty').val()).toFixed(2);
						ExpiryDetailsObj.conversion=$("#" + itemid).find('#tbl_cnvrsn').text();
						ExpiryDetailsObj.looseQty==parseFloat($("#" + itemid).find('#tbl_lqty').text()).toFixed(2);
						ExpiryDetailsObj.freeQty=$("#" + itemid).find('#tbl_free').text();
						/*ExpiryDetailsObj.amount=$("#" + itemid).find('#tbl_amount').text();*/
						ExpiryDetailsObj.netContent=$("#" + itemid).find('#tbl_netcontent').text();
						ExpiryDetailsObj.calculateLooseQty=$("#" + itemid).find('#tbl_calclqty').text();
						
						ExpiryDetailsObj.discPer=$("#" + itemid).find('#tbl_discperc').text();
						ExpiryDetailsObj.disc=$("#" + itemid).find('#tbl_disc').text();
						ExpiryDetailsObj.taxPercentage=$("#" + itemid).find('#tbl_taxperc').text();
						/*ExpiryDetailsObj.taxAmount=$("#" + itemid).find('#tbl_taxamt').text();
						ExpiryDetailsObj.netAmount=$("#" + itemid).find('#tbl_netamt').text();
						ExpiryDetailsObj.totAmount=$("#" + itemid).find('#tbl_totamt').text();*/
						ExpiryDetailsObj.taxId=$("#" + itemid).find('#tbl_taxid').text();
						ExpiryDetailsObj.taxTypeId=$("#" + itemid).find('#tbl_taxtypeid').text();
						//total_amt= parseFloat(total_amt)+( $("#" + itemid).find('#tbl_pqty').text() * parseFloat($("#" + itemid).find('#tbl_rate').text()));
						var itemPrice = Number($("#" + itemid).find('#exp_qty').val()) *  parseFloat($("#" + itemid).find('#tbl_rate').text());     
						var totMrp = Number($("#" + itemid).find('#exp_qty').val()) *  parseFloat($("#" + itemid).find('#tbl_mrp').text());      
						total_amt= parseFloat(total_amt)+(parseFloat(itemPrice));				
						
						var taxAmt = 0.0;
						 if ($("#" + itemid + "_istaxable").is(":checked")) {
						    taxAmt = (Number(itemPrice) * Number($("#" + itemid).find('#tbl_taxperc').text()))/100;
						 }else{
							 taxAmt = 0.0;
						 }
						var netAmt = Number(itemPrice) + Number(taxAmt);
						total_tax_amt = Number(total_tax_amt) + Number(taxAmt);
						total_net_amt = Number(total_net_amt) + Number(netAmt);
						total_mrp = Number(total_mrp) + Number(totMrp);
						
						ExpiryDetailsObj.amount=parseFloat(itemPrice).toFixed(2);
						ExpiryDetailsObj.taxAmount=parseFloat(taxAmt).toFixed(2);
						ExpiryDetailsObj.netAmount=parseFloat(netAmt).toFixed(2);
						ExpiryDetailsObj.totAmount=parseFloat(totMrp).toFixed(2);
						
						ExpiryDetailsObjArr[i]=ExpiryDetailsObj;
						i=i+1;
					}else {
				 
						dif_vendor=1;
						
					}
					
					
					
				
				}// else
			}
		}// else
	});// loop end 

	ExpiryObj.expiryDetails=ExpiryDetailsObjArr;
	ExpiryObj.id=expId;
	if (expId == 0) {// create

	} else {// edit
		ExpiryObj.invNo = $("#expiryInvoiceNo").val();
	}
	ExpiryObj.fromDate=formDate;
	ExpiryObj.toDate=$("#invDt").val();
	ExpiryObj.invDate=$("#invDt").val();
	ExpiryObj.remarks=$("#remarks").val();
	

	ExpiryObj.cr_amount= parseFloat(total_amt).toFixed(2);
	ExpiryObj.dr_amount= parseFloat(total_amt).toFixed(2);
	ExpiryObj.cr_account_id=$('#exp_ledger_id').val();
	ExpiryObj.dr_account_id= $('#creditor_legder_id').val();
	ExpiryObj.expMode = "MANUAL";
	ExpiryObj.grossAmount=parseFloat(total_amt).toFixed(2);
	ExpiryObj.discAmount='0.0';	
	ExpiryObj.taxAmount=parseFloat(total_tax_amt).toFixed(2);
	ExpiryObj.netAmount=parseFloat(total_net_amt).toFixed(2);
	ExpiryObj.totalMrp=parseFloat(total_mrp).toFixed(2);
	var roundedNetAmt = Math.round(total_net_amt);
	var roundOffAmt = Number(total_net_amt) -  Number(roundedNetAmt);
	ExpiryObj.roundoff = parseFloat(roundOffAmt).toFixed(2);
	
	console.log("ExpiryObj json: " + JSON.stringify(ExpiryObj));
	if(len>0 && dif_vendor==0){
		
		
		$('#pleasewaitModal').modal('show');
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/createorupdateexpiryinvoice.htm", ExpiryObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			console.log("save pur inv id="+response);
			if (response == '0') {
				if (expId == 0)
				{
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotAdd;
				}
				else
				{
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotUpdate;
				}
				$("#confirmval").val(0);
				showConfirmModal();
			} else {
				if (expId == 0)
				{
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucAdd;
				}
				else
				{
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucUpdate;
				}
				$("#confirmval").val(response);
				showConfirmModal();
			}
	
		});
	}
	else{
		$("#alertMsg").text("Please select all same vendor");
	}
	
}


function targetURL(){
	var result=$("#confirmval").val();
	console.log("save expiry inv id targetURL="+result);
	if(result==0){
		location.href = BASE_URL + '/stock/expissuemanual.htm';
	}
	else if(result==-1){
		location.href = "#";
	}
	else
	{
	   location.href = BASE_URL + '/manualexpiry/loadmanualexpinvoicedet/'+result+'.htm';
	}
}


function CheckExpQty(itemUniqueKey,packQty){
	var total_sale_rate = 0.0;
	$('#expitem > tbody > tr').each(function() {
		var itemid = this.id;
		if ($("#" + itemid + "_modretcheck").is(":checked")) {
		   var expQty = $("#" + itemid).find('#exp_qty').val();
		  
		   if(Number(expQty) > Number(packQty)){
			   $("#" + itemid).find('#exp_qty').val(packQty);
		   }

			var itemPrice = Number($("#" + itemid).find('#exp_qty').val()) *  parseFloat($("#" + itemid).find('#tbl_rate').text());     
			total_sale_rate= parseFloat(total_sale_rate)+(parseFloat(itemPrice));
		}else{
			
		}
	});// end loop
	$('#dr_amount').val(total_sale_rate.toFixed(2));
	$('#cr_amount').val(total_sale_rate.toFixed(2));
	
	
}


function postExpiryInv(){
	var expId = $("#expId").val();
	var len=$('.chkboxcheked:checked').length;
	if(len>0)
	{
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.expiryId = expId;
	
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/postexpiryinv.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				$("#confirmval").val(0);
				document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucPost;
				showConfirmModal();
			} else {
				$("#confirmval").val(0);
				document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotPost;
				showConfirmModal();
			}
	
		});
	}
	else
	{
		$("#confirmval").val(2);
		document.getElementById('confirmmessagecont').innerHTML = getStockInvText.noItemChckErr;
		showConfirmModal();
	}
}

function deleteExpiryInv(){
	var expId = $("#expId").val();
   document.getElementById('confirmId').value=expId;
   $('#confirmModal').modal('show');   
}

function DoConfirm() {
	var expid = document.getElementById('confirmId').value;

	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.expiryId = expid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/deleteexpinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			$("#confirmval").val(0);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucDelete;
			showConfirmModal();
		} else {
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotDelete;
			showConfirmModal();
		}

	});
}
