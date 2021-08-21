
function searchExpiryById()
{
	if( (new Date($('#stdate').val()).getTime() > new Date($('#enddate').val()).getTime()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.frmdtGrtEnddt;
	}
	else
	{
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.startDate = $("#stdate").val();
		CommonRelsetmapperObj.endDate = $("#enddate").val();
		CommonRelsetmapperObj.distributorId = $("#seldistributor").val();
		CommonRelsetmapperObj.asOnDate = $("#invDt").val();

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/searchexpiryinvoicebyid.htm", CommonRelsetmapperObj, function(response) {
			//var table = $('#expitem').DataTable();
			//table.clear().draw();
			var rowNode= new Array();
			var expdetails = JSON.parse(response);
			for ( var i = 0; i < expdetails.length; i++) {
				var expdetail = expdetails[i];
				/*var j = -1;
	            var r = new Array();*/
	            var check_counter = 0;
	            $('#expitem > tbody  > tr').each(function() {
	            	var existing_unique_key = this.id;
	            	console.log("existing_unique_key :"+existing_unique_key+" ; itemUniqueKey :"+expdetail.itemUniqueKey);
	            	if(existing_unique_key==expdetail.itemUniqueKey)
	            	{
	            		check_counter = 1;
	            	}
	            });
	            console.log("check_counter: "+check_counter);
	            if(check_counter==0)
	            {
					$(".selectedVendor option[value='" + expdetail.distributorId + "']").attr("selected", "selected");	
					tr = "<tr id='"+expdetail.itemUniqueKey+"'><td><input id='" + expdetail.itemUniqueKey + "_modretcheck' class='chkboxcheked' type='checkbox'></td>";
					row1 = '<td id="tbl_item_name">' + expdetail.itemName + '</td>';
					row2 = '<td id="tbl_item_id" class="hide">' + expdetail.itemId + '</td>';
					row3 = '<td id="tbl_batch_no">' + expdetail.batchNo + '</td>';
					row4 = '<td id="tbl_exp">' + expdetail.expiryDateFormat + '</td>';
					row5 = '<td id="tbl_punit_name">' + expdetail.packUnitName + '</td>';
					row6 = '<td id="tbl_punit_id" class="hide">' + expdetail.packUnitId + '</td>';
					row7 = '<td id="tbl_rack_name">' + expdetail.rackName + '</td>';
					row8 = '<td id="tbl_rack_id" class="hide">' + expdetail.rackId + '</td>';
					row9 = '<td id="tbl_stock_qty">' + expdetail.stockQty + '</td>';
					row10 = '<td id="tbl_mrp">' + expdetail.mrp + '</td>';
					row11 = '<td id="tbl_rate">' + expdetail.rate + '</td>';
					row12 = '<td>'+$("#vendorListt").html()+'</td>';
					var dateString = expdetail.expiryDate;
					var currentTime = new Date(dateString );
					var month = currentTime.getMonth() + 1;
					var day = currentTime.getDate();
					var year = currentTime.getFullYear();
					var expDate = year + "-" + month + "-" + day;
					row13 = '<td id="tbl_expdt" class="hide">' + expDate + '</td>';
					row14 = '<td id="tbl_pqty" class="hide">' + expdetail.packQty + '</td>';
					row15 = '<td id="tbl_cnvrsn" class="hide">'+expdetail.conversion+'</td>';
					row16 = '<td id="tbl_lqty" class="hide">' + expdetail.looseQty + '</td>';
					row17 = '<td id="tbl_free" class="hide">' + expdetail.freeQty + '</td>';
					row18 = '<td id="tbl_amount" class="hide">' + expdetail.amount + '</td>';
					row19 = '<td id="tbl_netcontent" class="hide">' + expdetail.netContent + '</td>';
					row20 = '<td id="tbl_calclqty" class="hide">' + expdetail.calculateLooseQty + '</td>';
		
					tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14 + row15 + row16 + row17 + row18 + row19 + row20 +'</tr>';
					//rowNode =table.row.add(r);
					$("#expitem tbody").append(tr);
					$("#" + expdetail.itemUniqueKey).find('#vendorName').attr("id","expdistributor");
					$("#" + expdetail.itemUniqueKey).find('#expdistributor').attr("name","distributorId");
					$("#" + expdetail.itemUniqueKey).find('#expdistributor').attr("class","form-control-trx");
					$(".selectedVendor option[value='" + expdetail.distributorId + "']").removeAttr("selected");
	            }
	            else{};
			}
			//table.columns( [ 2,6,8,13,14,15,16,17,18,19,20 ] ).visible( false, false );
			//rowNode.draw().node();
		});
	}
	
}

function getcheckedexpiryitemlist() {
	var len=$('.chkboxcheked:checked').length;
	var count = 0;
	var ExpiryObj = {};
	var ExpiryDetailsObjArr = [];
	var i = 0;
	var expId = $("#expId").val();
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
				if($("#" + itemid).find('#expdistributor').val()==0)
				{
					$(this).attr('checked', false);
					len=-1;
				}
				else
				{
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
					ExpiryDetailsObj.mrp=$("#" + itemid).find('#tbl_mrp').text();
					ExpiryDetailsObj.rate=$("#" + itemid).find('#tbl_rate').text();
					ExpiryDetailsObj.distributorId=$("#" + itemid).find('#expdistributor').val();
					ExpiryDetailsObj.distributorName=$("#" + itemid).find('#expdistributor option:selected').text();
					ExpiryDetailsObj.expiryDate=$("#" + itemid).find('#tbl_expdt').text();
					ExpiryDetailsObj.packQty=$("#" + itemid).find('#tbl_pqty').text();
					ExpiryDetailsObj.conversion=$("#" + itemid).find('#tbl_cnvrsn').text();
					ExpiryDetailsObj.looseQty=$("#" + itemid).find('#tbl_lqty').text();
					ExpiryDetailsObj.freeQty=$("#" + itemid).find('#tbl_free').text();
					ExpiryDetailsObj.amount=$("#" + itemid).find('#tbl_amount').text();
					ExpiryDetailsObj.netContent=$("#" + itemid).find('#tbl_netcontent').text();
					ExpiryDetailsObj.calculateLooseQty=$("#" + itemid).find('#tbl_calclqty').text();
					ExpiryDetailsObjArr[i]=ExpiryDetailsObj;
					i=i+1;
				}
			}
		}
	});

	ExpiryObj.expiryDetails=ExpiryDetailsObjArr;
	ExpiryObj.id=expId;
	if (expId == 0) {// create

	} else {// edit
		ExpiryObj.invNo = $("#expiryInvoiceNo").val();
	}
	ExpiryObj.fromDate=$("#stdate").val();
	ExpiryObj.toDate=$("#enddate").val();
	ExpiryObj.invDate=$("#invDt").val();
	ExpiryObj.remarks=$("#remarks").val();
	console.log("ExpiryObj json: " + JSON.stringify(ExpiryObj));
	if(len>0){
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
	else{}
	
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

function newExpiryInv(){
	location.href = BASE_URL + '/stock/expissue.htm';
}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function targetURL(){
	var result=$("#confirmval").val();
	console.log("save expiry inv id targetURL="+result);
	if(result==0){
		location.href = BASE_URL + '/stock/expissue.htm';
	}
	else if(result==-1){
		location.href = "#";
	}
	else
	{
			location.href = BASE_URL + '/expiry/loadexpinvoicedet/'+result+'.htm';
	}
}
 













// new added 5.7.2019

/*
 * for legder search
 */


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
		//$('#pleasewaitModal').modal('hide');
	 
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
 

