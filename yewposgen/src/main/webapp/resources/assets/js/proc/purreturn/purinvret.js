function chngeCmnReason()
{
	var cmnReasonSlctList = $("#slctCmnReason").val();
	if(cmnReasonSlctList != 0)
	{
		$('#purretitem tbody tr').each(function() {
			$(this).find("#tbl_reason").val(cmnReasonSlctList);
		});
	}
}
function itemDetailView(trId) {
	
	$("#purrettaxmode").val($("#purretitem tr#" + trId).find('#tbl_taxmode').text());
	$("#purRetTaxId").val($("#purretitem tr#" + trId).find('#tbl_taxid').text());
	$("#purretisgrptax").val($("#purretitem tr#" + trId).find('#tbl_taxisgrptax').text());
	$("#purHsnCode").val($("#purretitem tr#" + trId).find('#tbl_hsn').text());
	//$("#item_purCost").val(clickitmdet.purchaseCostPerUnit);
	//$("#item_purCostperUnit").val(clickitmdet.purchaseCostPerUnit);
	$("#is_slno").val($("#purretitem tr#" + trId).find('#tbl_itemisslno').text());
	/*$("#taxtype").val(clickitmdet.taxTypeId);
	var salerate = clickitmdet.saleRate;
	var isexclu = $("#isexclusive").val();tbl_iscurrentStk
	if (salerate == 0) {
		if (isexclu == 1) {
			$("#item_sale_rate").val(parseFloat(clickitmdet.mrp).toFixed(4));
			$("#item_rate_ls").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
			$("#item_rate_ls_hid").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
		} else {
			$("#item_sale_rate").val(parseFloat(clickitmdet.mrp).toFixed(4));
			$("#item_rate_ls").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
			$("#item_rate_ls_hid").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
		}

	} else {
		$("#item_sale_rate").val(parseFloat(clickitmdet.saleRate).toFixed(4));
		$("#item_rate_ls").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
		$("#item_rate_ls_hid").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
	}

	$("#item_purChase_rate").val(clickitmdet.purchaseRate);*/

	/*if(clickitmdet.serialNoRequired==0){
		$('#pqty').val(1);
		calculateDefaultvalue();
	}*/

	$("#item_id").val(trId);
	$('#item_name').prop('readonly', true);
	$("#barcode").attr("readonly",true);
	$("#item_name").val($("#purretitem tr#" + trId).find('#tbl_item_name').text());
	$("#batch_no").val($("#purretitem tr#" + trId).find('#tbl_batch_no').text());
	$("#exp").val($("#purretitem tr#" + trId).find('#tbl_exp').text());
	$("#crrntstk").val($("#purretitem tr#" + trId).find('#tbl_iscurrentStk').text());//new Added
	$("#pqty").val($("#purretitem tr#" + trId).find('#tbl_pqty').text());
	$("#lqty").val($("#purretitem tr#" + trId).find('#tbl_lqty').text());
	$("#ratio").val($("#purretitem tr#" + trId).find('#tbl_ratio').text());
	$("#free").val($("#purretitem tr#" + trId).find('#tbl_free').text());
	$("#prevfree").val($("#purretitem tr#" + trId).find('#tbl_prevfree').text());
	$("#mrp").val($("#purretitem tr#" + trId).find('#tbl_mrp').text());
	$("#rate").val($("#purretitem tr#" + trId).find('#tbl_rate').text());
	$("#ma").val($("#purretitem tr#" + trId).find('#tbl_ma').text());
	$("#grp").val($("#purretitem tr#" + trId).find('#tbl_grp').text());
	$("#sch").val($("#purretitem tr#" + trId).find('#tbl_sch').text());
	//	$("#grpid").val($("#tblrow_" + trId).find('#tbl_grp').text());
	//	$("#schid").val($("#tblrow_" + trId).find('#tbl_sch').text());
	$("#total").val($("#purretitem tr#" + trId).find('#tbl_amnt').text());
	$("#mfg").val($("#purretitem tr#" + trId).find('#tbl_mfg').text());
	//	$("#mfgid").val($("#tblrow_" + trId).find('#tbl_mfg').text());
	$("#edpercnt").val($("#purretitem tr#" + trId).find('#tbl_edprcnt').text());
	$("#ed").val($("#purretitem tr#" + trId).find('#tbl_ed').text());
	$("#taxprcnt").val($("#purretitem tr#" + trId).find('#tbl_taxprcnt').text());
	$("#tax").val($("#purretitem tr#" + trId).find('#tbl_tax').text());
	$("#vatprcnt").val($("#purretitem tr#" + trId).find('#tbl_vatprcnt').text());
	$("#vat").val($("#purretitem tr#" + trId).find('#tbl_vat').text());
	$("#dprcnt").val($("#purretitem tr#" + trId).find('#tbl_dprcnt').text());
	$("#disc").val($("#purretitem tr#" + trId).find('#tbl_disc').text());
	$("#id").val($("#purretitem tr#" + trId).find('#tbl_id').text());
	$("#itemid").val($("#purretitem tr#" + trId).find('#tbl_itemid').text());
	$("#tblrow_id").val(trId);
	$("#punitid").val($("#purretitem tr#" + trId).find('#tbl_punitid').text());
	$("#billpqty").val($("#purretitem tr#" + trId).find('#purrettabpqtyhide').text());
	$("#barcode").val($("#purretitem tr#" + trId).find('#tbl_sku').text());
	$("#purretexpdate").val($("#purretitem tr#" + trId).find('#tbl_exp_date').text());

	/*var isdis = $("#purretitem tr#" + trId).find('#tbl_isDiscount').text();
	if (isdis == 0) {
		$("#dprcnt").val(parseFloat(0).toFixed(4));
		$("#discprcnt_td").hide();
		$("#discamt_td").hide();
		$("#dis_label").hide();
		$("#disamt_label").hide();
		$("#disc").val(0);
	} else {
		$("#discamt_td").show();
		$("#discprcnt_td").show();
		$("#dis_label").show();
		$("#disamt_label").show();
		$("#dprcnt").val($("#purretitem tr#" + trId).find('#tbl_dprcnt').text());
		$("#disc").val($("#purretitem tr#" + trId).find('#tbl_disc').text());
	}*/
	var isDualStock = $("#purretitem tr#" + trId).find('#tbl_isDualStock').text();
	if(isDualStock==null||isDualStock==0){
		$("#dualStockRequired_req").val(0);
		$("#ratio_td").addClass('hide');
		$("#ratio_label").addClass('hide');
		//$("#ratio").val(itemdetval.conversion);
	}else if(isDualStock==1){
		$("#dualStockRequired_req").val(1);
		$("#ratio_td").removeClass('hide');
		$("#ratio_label").removeClass('hide');
	//	$("#ratio").val(itemdetval.conversion);
	}else{
		$("#dualStockRequired_req").val(0);
		$("#ratio_td").removeClass('hide');
		$("#ratio_label").removeClass('hide');
	//	$("#ratio").val(itemdetval.conversion);
	}

	var isBatch = $("#purretitem tr#" + trId).find('#tbl_isBatch').text();
	if(isBatch==null||isBatch==0){
		$("#batchWiseStock_req").val(0);
		$("#batch_no_td").addClass('hide');
		$("#batch_label").addClass('hide');
		//$("#batch_no").val(0);
	}else if(isBatch==1){
		$("#batchWiseStock_req").val(1);
		$("#batch_no_td").removeClass('hide');
		$("#batch_label").removeClass('hide');
		//$("#batch_no").val(0);
	}else{
		$("#batchWiseStock_req").val(0);
		$("#batch_no_td").removeClass('hide');
		$("#batch_label").removeClass('hide');
		//$("#batch_no").val(0);
	}

	var isExpDt = $("#purretitem tr#" + trId).find('#tbl_isExpDt').text();
	if(isExpDt==null||isExpDt==0){
		$("#expiryDateRequired_req").val(0);
		$("#exp_td").addClass('hide');
		$("#exp_label").addClass('hide');
		//$("#exp").val();
	}else if(isExpDt==1){
		$("#expiryDateRequired_req").val(1);
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
		//$("#exp").val();
	}else{
		$("#expiryDateRequired_req").val(0);
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
	//	$("#exp").val();
	}

	var isPriceList = $("#purretitem tr#" + trId).find('#tbl_isPriceList').text();
	if(isPriceList==null||isPriceList==0){
		$("#priceListRequired_req").val(0);
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
	}else if(isPriceList==1){
		$("#priceListRequired_req").val(1);
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
	}else{
		$("#priceListRequired_req").val(0);
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
	}

	var isMrp = $("#purretitem tr#" + trId).find('#tbl_isMrp').text();
	if(isMrp==null||isMrp==0){
		$("#mrpRequired_req").val(0);
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
	}else if(isMrp==1){
		$("#mrpRequired_req").val(1);
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
	}else{
		$("#mrpRequired_req").val(0);
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
	}

	$("#add_btn").addClass("hide");
	$("#edit_btn").removeClass("hide");
}

function clearHeaderDiv() {
	$("#header_div").find('input:text').val('');
	$("#header_div").find('input:hidden').val('');
	$('#item_name').prop('readonly', false);
	$('#barcode').prop('readonly', false);
	$("#add_btn").removeClass("hide");
	$("#edit_btn").addClass("hide");
	$("#item_id").val("0");
	$("#item_name").focus();

	$("#batch_label").addClass("hide");
	$("#exp_label").addClass("hide");
	$("#ratio_label").addClass("hide");
	$("#mrp_label").addClass("hide");
	//$("#dis_label").addClass("hide");
	//$("#disamt_label").addClass("hide");

	$("#batch_no_td").addClass("hide");
	$("#exp_td").addClass("hide");
	$("#ratio_td").addClass("hide");
	$("#mrp_td").addClass("hide");
	//$("#discprcnt_td").addClass("hide");
	//$("#discamt_td").addClass("hide");

	//	$("#item_dis").val(parseFloat($("#salediscount").val()).toFixed(4));
	//	document.getElementById('alertMsg').innerHTML = "";
};

function getvendordisval() {

	var rowCount = $('#purretitem >tbody >tr').length;
	if (rowCount > 0) {
		$("#confirmval").val(2);
		document.getElementById('confirmmessagecont').innerHTML = getPurInvRetText.vendorChangeError;
		showConfirmModal();
		$("#seldistributor").val($("#vendorchnge1").val());
	} else {
		var selvendor = $("#seldistributor").val();
		$("#vendorchnge1").val(selvendor);
		var disperc = selvendor.split("_")[1];
		$("#vendordis").val(parseFloat(disperc).toFixed(2));
		$("#dprcnt").val(parseFloat(disperc).toFixed(2));
		var qty = $("#pqty").val();
		var rate = $("#rate").val();
		if (rate == "") {
			rate = 0;
		}
		//discount calculation
		if (disperc == "" || qty == "") {

		} else {
			var disval = qty * rate * disperc / 100;
			$("#disc").val(parseFloat(disval).toFixed(2));
		}
	}
}

function Validation() {
	var counter = 0;

	/*var bill_field = $("#bill_label").text();

	var vendor_field = $("#vendor_label").text();

	var batch_field = $("#batch_label").text();

	var exp_field = $("#exp_label").text();

	var mrp_field = $("#mrp_label").text();*/

	var rate_field = $("#rate_label").text();

	var pqty_field = $("#pqty_label").text();

//	var field_names = [ [ "billno", bill_field ], [ "seldistributor", vendor_field ], [ "batch_no", batch_field ], [ "exp", exp_field ], [ "pqty", pqty_field ], [ "mrp", mrp_field ], [ "rate", rate_field ] ];
	var field_names = [  [ "pqty", pqty_field ], [ "rate", rate_field ] ];

	if (fieldValidation(field_names) > 0) {
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
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

	if (isNaN($("#rate").val())) {
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

function showPurRetItemDelModal(trId) {
	document.getElementById('confirmId').value = trId;
	$('#confirmModal').modal('show');
}

function ExistsOk() {
	$("#header_div").find('input:text').val('');
	$("#header_div").find('input:hidden').val('');
	$("#itemid").val("");
}

function DoConfirm() {
	var id = document.getElementById('confirmId').value;
	$("#" + id).remove();

	//$("#itemid").val("");
	calculateTotalLtAdj();
	calculateGrandTotal();
	calculateTotalMRP();
	calculateTotalED();
	calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	calculateNetTotal();
	clearHeaderDiv();
	return false;
}

function fillItemDetailsDiv(itemdetval) {
	console.log(itemdetval.itemName);
	$("#item_name").val(itemdetval.itemName);
	$("#batch_no").val(0);
	$("#exp").val();
	$("#punitid").val(itemdetval.packUnitId);
	$("#pqty").val(itemdetval.packQty);
	$("#lqty").val(itemdetval.looseQty);
	$("#ratio").val(itemdetval.conversion);
	$("#free").val(itemdetval.freeQty);
	$("#mrp").val(itemdetval.mrp);
	$("#rate").val(itemdetval.rate);
	$("#ma").val(0);
	$("#grp").val(itemdetval.grpName);
	$("#grpid").val(itemdetval.grpId);
	$("#sch").val(itemdetval.schdName);
	$("#schid").val(itemdetval.schdId);
	$("#total").val(0);
	$("#mfg").val(itemdetval.manuName);
	$("#mfgid").val(itemdetval.manuId);
	$("#edpercnt").val(itemdetval.edPer);
	$("#ed").val(itemdetval.ed);
	$("#taxprcnt").val(itemdetval.taxPer);
	$("#tax").val(itemdetval.tax);
	$("#vatprcnt").val(itemdetval.vatPer);
	$("#vat").val(itemdetval.vat);
	$("#dprcnt").val(itemdetval.discPer);
	$("#disc").val(itemdetval.disc);
	//		$("#id").val($("#tblrow_"+trId).find('#tbl_id').text());
	$("#item_id").val(itemdetval.itemId);

}

function calculateGrandTotal() {
	console.log("call calculateGrandTotal");
	var itemcount = 0;
	var grandtotal = 0.00;
	$('#purretitem tbody tr').each(function() {
		var itmtotal = $(this).find("#tbl_amnt").html();
		grandtotal = grandtotal + Number(itmtotal);
		itemcount++;
	});
	$("#totitmcount").text(itemcount);
	$("#totgrosamnt").val(parseFloat(grandtotal).toFixed(2));
}
function calculateTotalMRP() {
	var grandtotalMRP = 0.00;
	$('#purretitem tbody tr').each(function() {
		var itmmrp = $(this).find("#tbl_totamnt").html();
		grandtotalMRP = grandtotalMRP + Number(itmmrp);
	});
	$("#totgrosmrp").val(parseFloat(grandtotalMRP).toFixed(2));
}
function calculateTotalED() {
	var grandtotalED = 0.00;
	$('#purretitem tbody tr').each(function() {
		var itmed = $(this).find("#tbl_ed").html();
		grandtotalED = grandtotalED + Number(itmed);
	});
	$("#toted").val(parseFloat(grandtotalED).toFixed(4));
}
function calculateTotalVat() {
	var grandtotalVat = 0.00;
	$('#purretitem tbody tr').each(function() {
		var itmvat = $(this).find("#tbl_vat").html();
		grandtotalVat = grandtotalVat + Number(itmvat);
	});
	$("#totvatamnt").val(parseFloat(grandtotalVat).toFixed(4));
}
function calculateTotalTax() {
	var grandtotalTax = 0.00;
	$('#purretitem tbody tr').each(function() {
		var itmtax = $(this).find("#tbl_tax").html();
		grandtotalTax = grandtotalTax + Number(itmtax);
	});
	$("#tottaxamnt").val(parseFloat(grandtotalTax).toFixed(4));
}
function calculateTotalDisc() {
	var grandtotalDisc = 0.00;
	$('#purretitem tbody tr').each(function() {
		var itmdisc = $(this).find("#tbl_disc").html();
		grandtotalDisc = grandtotalDisc + Number(itmdisc);
	});
	$("#totdiscamnt").val(parseFloat(grandtotalDisc).toFixed(4));
}

function calculateTotalLtAdj() {
	var grandtotalLtAdj = 0.00;
	$('#purretitem tbody tr').each(function() {
		var itmltadj = $(this).find("#tbl_ltAdj").html();
		grandtotalLtAdj = grandtotalLtAdj + Number(itmltadj);
		//alert(grandtotalLtAdj);
	});
	$("#totltadj").val(parseFloat(grandtotalLtAdj).toFixed(4));
}

function calculateNetTotal() {
	var spcldis = $("#spldisc").val();
	if (spcldis == "" || spcldis == undefined) {
		spcldis = 0;
	}
	var spldisamt = Number($("#totgrosamnt").val()) * Number(spcldis) / 100;
	$("#spldiscamt").val(parseFloat(spldisamt).toFixed(2));
	//var nettotal = ((Number($("#totgrosamnt").val()) + Number($("#totvatamnt").val()) + Number($("#tottaxamnt").val())) - (Number($("#totdiscamnt").val()) + Number(spldisamt))) - Number($("#totltadj").val());
	var nettotal = ((Number($("#totgrosamnt").val()) + Number($("#tottaxamnt").val())) - (Number($("#totdiscamnt").val()) + Number(spldisamt))) - Number($("#totltadj").val());
	//var totnetamnt = $("#totnetamnt").val(parseFloat(nettotal).toFixed(2));
	var roundednetamnt = Math.round(Number(nettotal));
	console.log("roundednetamnt=" + parseFloat(roundednetamnt).toFixed(2));
	$("#totnetamnt").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff = Number(roundednetamnt) - Number(nettotal);
	console.log("roundoff=" + parseFloat(roundoff).toFixed(2));
	$("#roundoff").val(parseFloat(roundoff).toFixed(2));
	//	$("#roundoff").val(parseFloat(Math.round(Number(nettotal))).toFixed(2));
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
			var ltadj = 0.00;
			var disval = 0.00;
			var taxval = 0.00;

			if(((purdetail.freeQty % 1)!=0) || purdetail.freeQty<1)
			{
				// LtAdj calculation start
				var modFree =  purdetail.freeQty % 1;
				var tot = purdetail.packQty * purdetail.rate;
				var pqtyPerFree = purdetail.packQty/modFree;
				ltadj = tot/pqtyPerFree;
				// LtAdj calculation end
			}

			//discount calculation
			if (purdetail.discPer == "" || purdetail.rate == "") {

			} else {
				if(((purdetail.freeQty % 1)!=0) || purdetail.freeQty<1)
				{
					disval = ((purdetail.packQty * purdetail.rate)-ltadj) * purdetail.discPer / 100;
				}
				else
				{
					disval = purdetail.packQty * purdetail.rate * purdetail.discPer / 100;
				}
			}

			//tax calculation
			if (purdetail.taxPercentage == ""|| purdetail.rate == "") {

			}
			else
			{
				if(((purdetail.freeQty % 1)!=0) || purdetail.freeQty<1)
				{
					taxval = (((Number(purdetail.rate) * (Number(purdetail.packQty)))-Number(ltadj)) - Number(disval)) * Number(purdetail.taxPercentage) / 100;
				}
				else
				{
					taxval = ((Number(purdetail.rate) * (Number(purdetail.packQty))) - Number(disval)) * Number(purdetail.taxPercentage) / 100;
				}
			}

			var createdrowline="";
			$("#itempurdetailitemname").html(purdetail.itemName);
			var starttrline = "<tr id=" + purdetail.itemUniqueKey + " style='cursor: pointer;' onclick='insertModData(" + JSON.stringify(purdetail) + ")'>";
			var saleInvNo = "<td>" + purdetail.purchaseInvNo + "</td>";
			var saleInvDate = "<td>" + moment(purdetail.purchaseInvDate).format('YYYY-MM-DD') + "</td>";
			var vendorName = "<td>" + purdetail.distributorName + "</td>";
			var sku = "<td>" + purdetail.sku + "</td>";
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
			var tax = "<td id='" + purdetail.itemUniqueKey + "_taxamt'>" + parseFloat(taxval).toFixed(4) + "</td>";
			var vat = "<td id='" + purdetail.itemUniqueKey + "_vatamt'>" + parseFloat(purdetail.vat).toFixed(2) + "</td>";
			var disc = "<td id='" + purdetail.itemUniqueKey + "_discamt'>" + parseFloat(disval).toFixed(4) + "</td>";
			var retamt = "<td id='" + purdetail.itemUniqueKey + "_retamt'>" + parseFloat(purdetail.packQty * purdetail.rate).toFixed(2) + "</td>";
			var previousRetQty = "<td id='" + purdetail.itemUniqueKey + "_preretqty'>" + purdetail.prevReturnPackQty + "</td>";

			/*var isDiscount = "<td id='" + purdetail.itemUniqueKey + "_retisDiscount' class='hide'>" + purdetail.isDiscount + "</td>";
			var rettaxid = "<td id='" + purdetail.itemUniqueKey + "_rettaxid' class='hide'>" + purdetail.taxId + "</td>";
			var rettaxprcnt = "<td id='" + purdetail.itemUniqueKey + "_rettaxprcnt' class='hide'>" + parseFloat(purdetail.taxPercentage).toFixed(2) + "</td>";
			var rettaxmode = "<td id='" + purdetail.itemUniqueKey + "_rettaxmode' class='hide'>" + purdetail.taxMode + "</td>";
			var rettaxisgrptax = "<td id='" + purdetail.itemUniqueKey + "_rettaxisgrptax' class='hide'>" + parseFloat(purdetail.isGroupTax).toFixed(2) + "</td>";
			var retsku = "<td id='" + purdetail.itemUniqueKey + "_retsku' class='hide'>" + parseFloat(purdetail.sku).toFixed(2) + "</td>";
			var rethsn = "<td id='" + purdetail.itemUniqueKey + "_retamt' class='hide'>" + parseFloat(purdetail.hsnCode).toFixed(2) + "</td>";
			var isslnos = '<td id="' + purdetail.itemUniqueKey + '_retitemisslno" class="numeric hide">' + $("#item_is_slno").val() + '</td>';*/
			var endtrline = "</tr>";
			createdrowline = starttrline + saleInvNo + saleInvDate + vendorName + sku + batch + exp + packQty + freeQty + previousRetQty + stockQty + inputPackQty + inputfreeQty + mrp + rate + tax + disc + retamt + /*rettaxid + rettaxprcnt + rettaxmode + rettaxisgrptax + retsku + rethsn +*/ endtrline;
			$("#itemsearchmodtbody").append(createdrowline);
		}
	}
}

function getItemRetDetailsByBarcode(barcode) {
	var saleretvendorid = $("#slctedvendorid").val();
	//$('#itempurdetailModal').modal('show');

	$('#pleasewaitModal').modal('show');
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.sku = barcode;
	CommonRelsetmapperObj.distributorId = saleretvendorid;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purret/getpurinvdetbyskuforret.htm", CommonRelsetmapperObj, function(response) {
		console.log("res=" + response);
		$("#itemsearchmodtbody").text("");
		$('#pleasewaitModal').modal('hide');
		var purdetails = JSON.parse(response);
		fillItemRetDetailsDiv(purdetails);
	});
	$("#purRetMsgText").text("");
	$('#itempurdetailModal').modal('show');
}

$(document).ready(function() {

	$('#purretitem >tbody >tr > td:nth-child(16)').click(function () {
        return false;
    });

	$('#barcode').on('keydown', function(e) {
		if (e.which == 13) {
			e.preventDefault();
			var barcode = $('#barcode').val();
			getItemRetDetailsByBarcode(barcode);
		}
	});

	$("#spldisc").keyup(function() {
		var spldisamt = Number($("#totgrosamnt").val()) * Number($(this).val()) / 100;
		$("#spldiscamt").val(parseFloat(spldisamt).toFixed(2));
		var newspldisamt = Number($("#totgrosamnt").val()) - Number(spldisamt);
		var nettotal = Number(newspldisamt) + Number($("#totvatamnt").val()) + Number($("#tottaxamnt").val()) - Number($("#totdiscamnt").val());
		//		$("#totnetamnt").val(parseFloat(nettotal).toFixed(2));
		var roundednetamnt = Math.round(Number(nettotal));
		console.log("roundednetamnt=" + parseFloat(roundednetamnt).toFixed(2));
		$("#totnetamnt").val(parseFloat(roundednetamnt).toFixed(2));
		var roundoff = Number(roundednetamnt) - Number(nettotal);
		console.log("roundoff=" + parseFloat(roundoff).toFixed(2));
		$("#roundoff").val(parseFloat(roundoff).toFixed(2));
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
		if (dprcnt == "" || dprcnt == 0 || qty == "" || qty == 0) {

		} else {
			var disval = qty * rate * dprcnt / 100;
			$("#disc").val(parseFloat(disval).toFixed(4));
		}
	});

	function calForPqtyChng(qty)
	{
		var edpercnt = $("#edpercnt").val();
		var dprcnt = $("#dprcnt").val();
		var taxprcnt = $("#taxprcnt").val();
		var vatprcnt = $("#vatprcnt").val();
		var rate = $("#rate").val();
		var mrp = $("#mrp").val();
		var ratio = $("#ratio").val();
		var free = $("#prevfree").val();
		var billpqty = $("#billpqty").val();
		var ltadj = 0.0;
		var disval = 0.0;
		var taxval = 0.0;
		var rtrnFree = 0.0;
		//alert(billpqty);
		if(Number(qty)>Number(billpqty)){
			$("#pqty").val(0);
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
				$("#total").val(parseFloat(amt).toFixed(2));
			}

			if(((free % 1)!=0) || free<1)
			{
				// LtAdj calculation start
				var amount = $("#total").val();
				var modFree =  free % 1;
				rtrnFree = (qty*modFree)/billpqty;
				var pqtyPerFree = qty/rtrnFree;
				ltadj = amount/pqtyPerFree;
				// LtAdj calculation end
			}
			else{rtrnFree = 0.0;}

			$("#free").val(parseFloat(rtrnFree).toFixed(2)); // set free

			free = rtrnFree;

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
					taxval = ((Number(rate) * Number(qty)) - Number($("#disc").val())) * Number(taxprcnt) / 100;
				}
				$("#tax").val(parseFloat(taxval).toFixed(4));
			}
		}
	}

	// pqty change calculation
	$("#pqty").keyup(function() {
		var qty = $(this).val();
		calForPqtyChng(qty);
	});

	//item_packqty change calculation
	$("#pqty").blur(function() {
		var pqty = $(this).val();
		var itemid = $("#item_id").val();
		var item_is_slno = $("#purrettabitemdetails tr#" + itemid).find("#tbl_itemisslno").text();
		if(item_is_slno==1){
			for ( var i = 1; i <= 30; i++) {
				$("#checkbox"+i).attr('checked', false);
			}
			var chkdsno=[];
			var sno=[];
			var chkdslnos=$("#purrettabitemdetails tr#" + itemid).find("#tbl_chkdSlNos").text();
		    var chkdsnoarr=chkdslnos.split(",");
		    for(var k=0;k<chkdsnoarr.length; k++)
		    {
		    	if(chkdsnoarr[k]!="")
		    	{
		    		chkdsno.push(chkdsnoarr[k]);
		    	}
		    }

		    var slnos=$("#purrettabitemdetails tr#" + itemid).find("#tbl_allSlNos").text();
		    var snoarr=slnos.split(",");
		    for(var j=0;j<snoarr.length; j++)
		    {
		    	if(snoarr[j]!="")
		    	{
		    		sno.push(snoarr[j]);
		    	}
		    }
			//alert("sno.length:"+sno.length+";pqty:"+pqty);
			if(sno.length<=0){
				$("#currStkGraterModal").modal("show");
				$("#currstkofitm").text(0);
				$("#curstkcheck").val(1);
			}
			else if(sno.length<pqty){
				$("#pqty").val(sno.length);
			}
			else{
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
				$("#openSerialModal").modal("show");
			}

		}else
		{
			var qty = $(this).val();
			calForPqtyChng(qty);
		}

	});


	$("#selslno").on("click", function () {
//		alert("length="+$("input:checkbox[class=chk]:checked").length);
		var modalCltype = $("#modalCltype").val();
		var length=$("input:checkbox[class=chk]:checked").length ;
		var arr=[];
		var arrchk=[];
	    $("input:checkbox[class=chk]:checked").each(function () {
//	        alert("Id: " + $(this).attr("id") + " Value: " + $(this).val());
//	        alert("textbox val="+$("#textbox"+$(this).val()).val());
	        arr.push($("#textbox"+$(this).val()).val());
	        arrchk.push($(this).attr("id"));
	    });
		if(modalCltype == 0)// when the modal is called during edit pqty
		{
			$("#allVal").val(arr);
		    $("#allChkID").val(arrchk);
		    $("#pqty").val(Number(length));
		    calForPqtyChng(Number(length));
		}
		if(modalCltype == 1) // when the modal is called by searching with purInvNo
		{
			var itemUniqueKey = $("#itemUniqueKey").val();
			var pqty = $("#"+itemUniqueKey+"_pqty").text();
			$("#"+itemUniqueKey+"_irpqty").val(Number(length));
			var freeqty = $("#"+itemUniqueKey+"_freeqty").text();
			var rate = $("#"+itemUniqueKey+"_rate").text();
			var edper = $("#"+itemUniqueKey+"_edper").text();
			var taxper = $("#"+itemUniqueKey+"_taxper").text();
			var vatper = $("#"+itemUniqueKey+"_vatper").text();
			var discper = $("#"+itemUniqueKey+"_discper").text();
			var mrp = $("#"+itemUniqueKey+"_mrp").text();
			var stckqty = $("#"+itemUniqueKey+"_stckqty").text();
			$("#"+itemUniqueKey+"_chkdSlNos").text(arr);
			calPQty(pqty,freeqty,rate,Number(length),itemUniqueKey,edper,taxper,vatper,discper,mrp,stckqty);
		}
	    $("#openSerialModal").modal("hide");
	    $("#modalCltype").val(0);
	    $("#itemUniqueKey").val(0);
	});

	// rate change calculation
	$("#rate").keyup(function() {
		var rate = $(this).val();
		var dprcnt = $("#dprcnt").val();
		var qty = $("#pqty").val();
		var taxprcnt = $("#taxprcnt").val();
		var free = $("#free").val();
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
				disval = parseFloat(((qty * rate)-ltadj) * dprcnt / 100).toFixed(4);
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
				taxval = ((Number(rate) * Number(qty)) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			}
			$("#tax").val(parseFloat(taxval).toFixed(4));
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
			$("#itempurdetailitemname").html(ui.item.label);
			var seslocid=$("#seslocationid").val();
			$("#location").val(seslocid);
			// call new  ajax for item details
			getItemDetails(ui.item.itemCode);
			// call new ajax end

		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});

	function getItemDetails(itemid) {
		var saleretvendorid = $("#slctedvendorid").val();
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
			$("#purRetMsgText").text("");
			$('#itempurdetailModal').modal('show');
			/*$('#pleasewaitModal').modal('hide');
			var itemdetail = JSON.parse(response);
			console.log("resp="+response);
			if(itemdetail.length==1){
				for ( var i = 0; i < itemdetail.length; i++) {
					var itmstkdet = itemdetail[i];
					console.log(JSON.stringify(itmstkdet));
				getClickeditmdet(itmstkdet);
				}
			}else{
				fillItemRetDetailsDiv(itemdetail);
				$('#itempurdetailModal').modal('show');
			}*/
		},null);
	}

	function getClickeditmdet(clickitmdet) {
		//	var clickeditemdetail = JSON.parse(clickitmdet);
		//	$("#item_id").val(clickitmdet.itemId);sizeWiseStockRequired
		//alert(clickitmdet.itemName);
		//console.log("clickitmdet="+JSON.Stringify(clickitmdet));
		if(clickitmdet.conversion==0){
			clickitmdet.conversion=1;
		}
		//alert(clickitmdet.itemUniqueKey);
		$("#item_id").val(clickitmdet.itemUniqueKey);
		$("#item_name").val(clickitmdet.itemName);
		$("#barcode").val(clickitmdet.sku);
		$("#batch_no").val(clickitmdet.batchNo);
		$("#exp").val(clickitmdet.expiryDateFormat);
		//$("#item_packunitid").val(clickitmdet.packUnitId);
		//$("#item_looseunitid").val(clickitmdet.packUnitId);
		$("#billpqty").val(clickitmdet.packQty);
		//$("#item_mrp_pack").val(parseFloat(clickitmdet.mrp).toFixed(4));
		//$("#mrp").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
		$("#mrp").val(parseFloat(clickitmdet.mrp).toFixed(4));
		//$("#item_rate_ls").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
		//$("#item_rate_ls_hid").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
		$("#rate").val(parseFloat(clickitmdet.rate).toFixed(4));
		$('#pqty').val(0);
		$('#lqty').val(0);
		var isdis = clickitmdet.isDiscount;
		if (isdis == 0) {
			$("#dprcnt").val(parseFloat(0).toFixed(4));
			$("#discprcnt_td").hide();
			$("#discamt_td").hide();
			$("#dis_label").hide();
			$("#disamt_label").hide();
			$("#disc").val(0);
		} else {
			$("#discamt_td").show();
			$("#discprcnt_td").show();
			$("#dis_label").show();
			$("#disamt_label").show();
			$("#dprcnt").val(clickitmdet.discount);
			$("#item_maxdisper").val(parseFloat(clickitmdet.maxDiscountLimit).toFixed(4));
		}
		//	$("#item_dis").val(parseFloat(0).toFixed(4));//clickitmdet.itemId
		$("#vatprcnt").val(parseFloat(clickitmdet.vatPer).toFixed(4));
		$("#taxprcnt").val(parseFloat(clickitmdet.taxPer).toFixed(4));
		$("#mfg").val(clickitmdet.manufacturerName);
		$('#mfg').prop('title', clickitmdet.manufacturerName);
		/*$("#item_content").val(clickitmdet.contentName);
		$("#itemContent").val(clickitmdet.contentName);
		$("#itemContent").val(clickitmdet.contentName);
		$("#content_Dets").val(clickitmdet.contentName);
		$('#item_content').prop('title', clickitmdet.contentName);*/
		$("#ratio").val(clickitmdet.conversion);
		$("#sch").val(clickitmdet.scheduleName);
		$("#purRetTaxId").val(clickitmdet.taxId);
		$("#taxprcnt").val(clickitmdet.taxPercentage);
		$("#purretisgrptax").val(clickitmdet.isGroupTax);
		//	$("#item_dis").val(parseFloat(clickitmdet.discount).toFixed(4));
		//	$("#item_discount").val(clickitmdet.discount);
		$("#isDiscount").val(clickitmdet.isDiscount);
		//$("#item_maxDiscountLimit").val(clickitmdet.maxDiscountLimit);
		$("#purrettaxmode").val(clickitmdet.taxMode);
		$("#purHsnCode").val(clickitmdet.hsnCode);
		//$("#item_purCost").val(clickitmdet.purchaseCostPerUnit);
		//$("#item_purCostperUnit").val(clickitmdet.purchaseCostPerUnit);
		$("#is_slno").val(clickitmdet.serialNoRequired);
		$("#taxtype").val(clickitmdet.taxTypeId);
		$("#purretexpdate").val(moment(clickitmdet.expiryDate).format('YYYY-MM-DD'));
		var salerate = clickitmdet.saleRate;
		var isexclu = $("#isexclusive").val();
		/*if (salerate == 0) {
			if (isexclu == 1) {
				$("#item_sale_rate").val(parseFloat(clickitmdet.mrp).toFixed(4));
				$("#item_rate_ls").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
				$("#item_rate_ls_hid").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
			} else {
				$("#item_sale_rate").val(parseFloat(clickitmdet.mrp).toFixed(4));
				$("#item_rate_ls").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
				$("#item_rate_ls_hid").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
			}

		} else {
			$("#item_sale_rate").val(parseFloat(clickitmdet.saleRate).toFixed(4));
			$("#item_rate_ls").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
			$("#item_rate_ls_hid").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
		}

		$("#item_purChase_rate").val(clickitmdet.purchaseRate);*/

		if(clickitmdet.dualStockRequired==null||clickitmdet.dualStockRequired==0){
			$("#dualStockRequired_req").val(0);
			$("#ratio").addClass('hide');
			$("#ratio_label").addClass('hide');
			//$("#ratio").val(itemdetval.conversion);
		}else if(clickitmdet.dualStockRequired==1){
			$("#dualStockRequired_req").val(1);
			$("#ratio").removeClass('hide');
			$("#ratio_label").removeClass('hide');
		//	$("#ratio").val(itemdetval.conversion);
		}else{
			$("#dualStockRequired_req").val(0);
			$("#ratio").removeClass('hide');
			$("#ratio_label").removeClass('hide');
		//	$("#ratio").val(itemdetval.conversion);
		}

		if(clickitmdet.batchWiseStock==null||clickitmdet.batchWiseStock==0){
			$("#batchWiseStock_req").val(0);
			$("#batch_no_td").addClass('hide');
			$("#batch_label").addClass('hide');
			//$("#batch_no").val(0);
		}else if(clickitmdet.batchWiseStock==1){
			$("#batchWiseStock_req").val(1);
			$("#batch_no_td").removeClass('hide');
			$("#batch_label").removeClass('hide');
			//$("#batch_no").val(0);
		}else{
			$("#batchWiseStock_req").val(0);
			$("#batch_no_td").removeClass('hide');
			$("#batch_label").removeClass('hide');
			//$("#batch_no").val(0);
		}

		if(clickitmdet.expiryDateRequired==null||clickitmdet.expiryDateRequired==0){
			$("#expiryDateRequired_req").val(0);
			$("#exp_td").addClass('hide');
			$("#exp_label").addClass('hide');
			//$("#exp").val();
		}else if(clickitmdet.expiryDateRequired==1){
			$("#expiryDateRequired_req").val(1);
			$("#exp_td").removeClass('hide');
			$("#exp_label").removeClass('hide');
			//$("#exp").val();
		}else{
			$("#expiryDateRequired_req").val(0);
			$("#exp_td").removeClass('hide');
			$("#exp_label").removeClass('hide');
		//	$("#exp").val();
		}

		if(clickitmdet.priceListRequired==null||clickitmdet.priceListRequired==0){
			$("#priceListRequired_req").val(0);
			$("#mrp_td").addClass('hide');
			$("#mrp_label").addClass('hide');
		}else if(clickitmdet.priceListRequired==1){
			$("#priceListRequired_req").val(1);
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
		}else{
			$("#priceListRequired_req").val(0);
			$("#mrp_td").removeClass('hide');
			$("#mrp_label").removeClass('hide');
		}
		if(clickitmdet.mrpRequired==null||clickitmdet.mrpRequired==0){
			$("#mrpRequired_req").val(0);
			$("#mrp_td").addClass('hide');
			$("#mrp_label").addClass('hide');
		}else if(clickitmdet.mrpRequired==1){
			$("#mrpRequired_req").val(1);
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
		}else{
			$("#mrpRequired_req").val(0);
			$("#mrp_td").removeClass('hide');
			$("#mrp_label").removeClass('hide');
		}
		if(clickitmdet.itemUniqueKey!=0){
			if(clickitmdet.serialNoRequired==0){
				$('#pqty').val(1);
				calculateDefaultvalue();
			}
		}

		$("#itempurdetailModal").modal("hide");
	}


	var currentDate = new Date();

	$('#invdt').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});

	$("#cnfrm_cancel_btn").click(function() {
		/*$("#add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');*/
		clearHeaderDiv();
	});

	$(".close").click(function() {
		/*$("#add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');*/
		clearHeaderDiv();
	});

	$("#clear_btn").click(function() {
		/*	$("#header_div").find('input:text').val('');
			$("#header_div").find('input:hidden').val('');
			$("#add_btn").removeClass("hide");
			$("#edit_btn").addClass("hide");
			$("#itemid").val("");*/
		clearHeaderDiv();
	});
	var pqty_field = $("#pqty_label").text();
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
			/*if(document.getElementById("pqty").value.indexOf('.') != -1){
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

	$("#rate").keyup(function() {
		if (isNaN($("#rate").val())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in Rate";
			$(this).focus();
			return false;
		} else {
			document.getElementById('alertMsg').innerHTML = "";
		}
	});

	$("#spldisc").keyup(function() {
		if (isNaN($("#spldisc").val())) {
			document.getElementById('alertMsgforSave').innerHTML = getFieldText.numberCheck + " in Spl.Disc%";
			$('#footerErrorModal').modal('show');
			$(this).focus();
			return false;
		} else {
			document.getElementById('alertMsgforSave').innerHTML = "";
		}
	});

	$("#edit_btn").click(function() {
		var currentstkdtail=parseInt($("#crrntstk").val());
		var pqtydtails=$("#pqty").val();
		if(pqtydtails>currentstkdtail)
		{
			$('#purRetMsg').modal('show');
			return false;
		}
		//alert("currentstkdtail "+currentstkdtail+"pqtydtails "+pqtydtails);
		//		var itemid = $("#item_id").val();
		var ltadj = 0.0;
		var rtrnFree = 0.0;
		if (Validation() == 1) {
			return false;
		} else {
			var free = $("#prevfree").val();
			var bpqty = $("#billpqty").val();
			var tot = $("#total").val();
			var pqty = $("#pqty").val();
			if(((free % 1)!=0) || free<1)
			{
				// LtAdj calculation start
				var modFree =  free % 1;
				rtrnFree = (pqty*modFree)/bpqty;
				var pqtyPerFree = pqty/rtrnFree;
				ltadj = tot/pqtyPerFree;
				// LtAdj calculation end
			}
			else
			{
				rtrnFree = 0.0;
			}
			document.getElementById('alertMsg').innerHTML = "";
		}

		var trId = $("#item_id").val();
		//var amnt = $("#pqty").val() * $("#rate").val();
//		var totmrp = (Number($("#pqty").val()) + Number($("#free").val())) * $("#mrp").val();
		var totmrp = (Number($("#pqty").val()) + Number(0)) * $("#mrp").val();
		$("#purretitem tr#" + trId).find('#tbl_pqty').text($("#pqty").val());
		$("#purretitem tr#" + trId).find('#tbl_lqty').text($("#lqty").val());
		$("#purretitem tr#" + trId).find('#tbl_rate').text($("#rate").val());
		$("#purretitem tr#" + trId).find('#tbl_free').text(parseFloat(rtrnFree).toFixed(2));
		$("#purretitem tr#" + trId).find('#tbl_ed').text($("#ed").val());
		$("#purretitem tr#" + trId).find('#tbl_tax').text($("#tax").val());
		$("#purretitem tr#" + trId).find('#tbl_vat').text($("#vat").val());
		$("#purretitem tr#" + trId).find('#tbl_disc').text($("#disc").val());
		$("#purretitem tr#" + trId).find('#tbl_amnt').text($("#total").val());
		$("#purretitem tr#" + trId).find('#tbl_totamnt').text(parseFloat(totmrp).toFixed(2));
		$("#purretitem tr#" + trId).find('#tbl_ltAdj').text(ltadj);
		$("#purretitem tr#" + trId).find('#tbl_chkdSlNos').text($("#allVal").val());
		clearHeaderDiv();
		calculateGrandTotal();
		calculateTotalMRP();
		//calculateTotalED();
		//calculateTotalVat();
		calculateTotalTax();
		calculateTotalDisc();
		calculateTotalLtAdj();
		calculateNetTotal();
	});

	$("#add_btn").click(function() {

		if (Validation() == 1) {
			return false;
		} else {
			document.getElementById('alertMsg').innerHTML = "";
		}

		var rowCount = $('#purretitem >tbody >tr').length;
		$("#itemcount").text(rowCount + 1);
		var itempresent = 0;
		$('#purretitem > tbody  > tr').each(function() {
			console.log("tbl_itemid=" + $(this).find('#tbl_itemid').text());
			console.log("itemid=" + $("#itemid").val());
			if (Number($(this).find('#tbl_itemid').text()) == Number($("#itemid").val())) {
				itempresent = 1;
			}
		});
		if (Number($("#itemid").val()) == 0) {
			return false;
		}
		if (itempresent == 1) {
			$('#itemExistsModal').modal('show');
		} else {
			var tr = '<tr id="tblrow_' + $("#itemid").val() + '" style="cursor: pointer;" onclick="javascript:itemDetailView(' + $("#itemid").val() + ');">';
			var totmrp = (Number($("#pqty").val()) + Number($("#free").val())) * $("#mrp").val();
			var row1 = '<td id="tbl_item_name">' + $("#item_name").val() + '</td>';
			var row2 = '<td id="tbl_batch_no">' + $("#batch_no").val() + '</td>';
			var row3 = '<td id="tbl_exp">' + $("#exp").val() + '</td>';
			var row4 = '<td id="tbl_pqty" class="numeric">' + $("#pqty").val() + '</td>';
			var row5 = '<td id="tbl_lqty" class="numeric">' + $("#lqty").val() + '</td>';
			var row6 = '<td id="tbl_ratio" class="numeric">' + $("#ratio").val() + '</td>';
			var row7 = '<td id="tbl_free" class="numeric">' + $("#free").val() + '</td>';
			var row8 = '<td id="tbl_mrp" class="numeric">' + $("#mrp").val() + '</td>';
			var row9 = '<td id="tbl_rate" class="numeric">' + $("#rate").val() + '</td>';
			var row10 = '<td id="tbl_ed" class="numeric">' + $("#ed").val() + '</td>';
			var row11 = '<td id="tbl_tax" class="numeric">' + $("#tax").val() + '</td>';
			var row12 = '<td id="tbl_vat" class="numeric">' + $("#vat").val() + '</td>';
			var row13 = '<td id="tbl_disc" class="numeric">' + $("#disc").val() + '</td>';
			var row14 = '<td id="tbl_amnt" class="numeric">' + $("#total").val() + '</td>';
			var row15 = '<td id="tbl_totamnt" class="numeric">' + parseFloat(totmrp).toFixed(2) + '</td>';
			var row16 = '<td><button class="btn btn-theme04 btn-xs" onclick="javascript:showPurRetItemDelModal(' + $("#itemid").val() + ');"><i class="fa fa-trash-o "></i></button></td>';
			var row17 = '<td id="tbl_ma" class="hide">' + $("#ma").val() + '</td>';
			var row18 = '<td id="tbl_grp" class="hide">' + $("#grpid").val() + '</td>';
			var row19 = '<td id="tbl_sch" class="hide">' + $("#schid").val() + '</td>';
			var row20 = '<td id="tbl_mfg" class="hide">' + $("#mfgid").val() + '</td>';
			var row21 = '<td id="tbl_edprcnt" class="hide">' + $("#edpercnt").val() + '</td>';
			var row22 = '<td id="tbl_taxprcnt" class="hide">' + $("#taxprcnt").val() + '</td>';
			var row23 = '<td id="tbl_vatprcnt" class="hide">' + $("#vatprcnt").val() + '</td>';
			var row24 = '<td id="tbl_dprcnt" class="hide">' + $("#dprcnt").val() + '</td>';
			var row25 = '<td id="tbl_id" class="hide">' + $("#id").val() + '</td>';
			var row26 = '<td id="tbl_itemid" class="hide">' + $("#itemid").val() + '</td>';
			var row27 = '<td id="tbl_grpname" class="hide">' + $("#grp").val() + '</td>';
			var row28 = '<td id="tbl_schname" class="hide">' + $("#sch").val() + '</td>';
			var row29 = '<td id="tbl_mfgname" class="hide">' + $("#mfg").val() + '</td>';
			var taxid = "<td id='tbl_taxid' class='hide'>" + $("#purRetTaxId").val() + "</td>";
			var taxmode = "<td id='tbl_taxmode' class='hide'>" +$("#purrettaxmode").val() + "</td>";
			var taxisgrptax = "<td id='tbl_taxisgrptax' class='hide'>" + $("#purretisgrptax").val() + "</td>";
			var sku = "<td id='tbl_sku' class='hide'>" + $("#barcode").val() + "</td>";
			var hsn = "<td id='tbl_hsn' class='hide'>" + $("#purHsnCode").val() + "</td>";
			var packunitid = '<td id="tbl_punitid" class="hide">' + $("#punitid").val() + '</td>';

			var isDiscount = "<td id='tbl_isDiscount' class='hide'>" + itemdetail.isDiscount + "</td>";
			var isslnos = '<td id="tbl_itemisslno" class="numeric hide">' + itemdetail.serialNoRequired + '</td>';
			var isdualStock = "<td id='tbl_isDiscount' class='hide'>" + itemdetail.dualStockRequired + "</td>";
			var isBatch = '<td id="tbl_itemisslno" class="numeric hide">' + itemdetail.batchWiseStock + '</td>';
			var isExpDt = '<td id="tbl_itemisslno" class="numeric hide">' + itemdetail.expiryDateRequired + '</td>';
			var isPriceList = "<td id='tbl_isDiscount' class='hide'>" + itemdetail.priceListRequired + "</td>";
			var isMrp = '<td id="tbl_itemisslno" class="numeric hide">' + itemdetail.mrpRequired + '</td>';
			var tbl_exp_date = '<td id="tbl_exp_date" class="numeric hide">' + moment(itemdetail.expiryDate).format('YYYY-MM-DD') + '</td>';

			tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14 + row15 + row16 + row17 + row18 + row19 + row20 + row21 + row22 + row23 + row24 + row25 + row26 + row27 + row28 + row29 + packunitid +tbl_exp_date+ '</tr>';
			$("#purretitem tbody").append(tr);

			calculateGrandTotal();
			calculateTotalMRP();
			//calculateTotalED();
			//calculateTotalVat();
			calculateTotalTax();
			calculateTotalDisc();
			calculateTotalLtAdj();
			calculateNetTotal();
			clearHeaderDiv();
		}

	});

	$("#ret_btn").click(function() {
		var puritemlength = $('#purretitem >tbody >tr').length;
		if (puritemlength > 0) {
			if ($("#item_id").val() == 0 || $("#item_id").val() == "") {
				/*console.log("vendordis=" + $("#vendordis").val());
				if (isNaN($("#spldisc").val()) || $("#spldisc").val() == "") {
					document.getElementById('alertMsgforSave').innerHTML = getFieldText.numberCheck + " in Spl.Disc%";
					$('#footerErrorModal').modal('show');
					return false;
				} else {
					document.getElementById('alertMsgforSave').innerHTML = "";
				}

				if ($("#invmode").is(':checked')) {
				} else {
					if ($("#cr_note").val() == "") {
						document.getElementById('alertMsgforSave').innerHTML = $("#cr_noteLabel").text() + " " + getFieldText.fieldReq;
						$('#footerErrorModal').modal('show');
						return false;
					}

				}*/

				var PurchaseReturnObj = {};
				var PurchaseReturnDetailsObjArr = [];

				$("#purretitem tr").not('thead tr').each(function(	i,
																	v) {
					var PurchaseRetDetailsObj = {};
					PurchaseRetDetailsObj.itemName = $(this).find('#tbl_item_name').text();
					PurchaseRetDetailsObj.batchNo = $(this).find('#tbl_batch_no').text();
					PurchaseRetDetailsObj.expiryDateFormat = $(this).find('#tbl_exp').text();
					PurchaseRetDetailsObj.expiryDate = $(this).find('#tbl_exp_date').text();
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
					PurchaseRetDetailsObj.isGroupTax = $(this).find('#tbl_taxisgrptax').text();
					PurchaseRetDetailsObj.totAmount = $(this).find('#tbl_totamnt').text();
					PurchaseRetDetailsObj.vat = 0.0; //$(this).find('#tbl_vat').text();
					PurchaseRetDetailsObj.vatPer = 0.0; //$(this).find('#tbl_vatprcnt').text();
					PurchaseRetDetailsObj.grpId = $(this).find('#tbl_grpid').text();
					PurchaseRetDetailsObj.schdId = $(this).find('#tbl_schid').text();
					PurchaseRetDetailsObj.manuId = $(this).find('#tbl_mfgid').text();
					PurchaseRetDetailsObj.packUnitId = $(this).find('#tbl_punitid').text();
					PurchaseRetDetailsObj.purchaseId = $(this).find("#tbl_invid").text();
					PurchaseRetDetailsObj.purchaseInvNo = $(this).find("#tbl_invno").text();
					PurchaseRetDetailsObj.itemLotAdjAmount = $(this).find("#tbl_ltAdj").text();
					PurchaseRetDetailsObj.sku = $(this).find('#tbl_sku').text();
					PurchaseRetDetailsObj.hsnCode = $(this).find('#tbl_hsn').text();
					PurchaseRetDetailsObj.storeId = storeId;
					PurchaseRetDetailsObj.finyrId = finyrId;
					PurchaseRetDetailsObj.companyId = cmpnyId;
					PurchaseRetDetailsObj.createdBy = createdBy;
					PurchaseRetDetailsObj.reasonId = $(this).find("#tbl_reason").val();

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

					PurchaseReturnDetailsObjArr[i] = PurchaseRetDetailsObj;
				});
				PurchaseReturnObj.purchaseReturnDetails = PurchaseReturnDetailsObjArr;
				PurchaseReturnObj.invDate = $("#retinvdt").val();
				//PurchaseObj.billNo = $("#billno").val();
				//
				PurchaseReturnObj.id =$("#purretid").val();
				if($("#purretid").val()!=0){
					PurchaseReturnObj.invNo =$("#purretinvno").val();
				}
				//PurchaseObj.distName = $("#distname").val();
				PurchaseReturnObj.distributorId = $("#slctedvendorid").val();
				//				PurchaseObj.distDiscPer = $("#vendordis").val();
				PurchaseReturnObj.edAmount = 0.0; //$("#toted").val();
				PurchaseReturnObj.grossAmount = $("#totgrosamnt").val();
				PurchaseReturnObj.vatAmount = 0.0; //$("#totvatamnt").val();
				PurchaseReturnObj.taxAmount = $("#tottaxamnt").val();
				PurchaseReturnObj.discAmount = $("#totdiscamnt").val();
				PurchaseReturnObj.roundoff = $("#roundoff").val();
				PurchaseReturnObj.netAmount = $("#totnetamnt").val();
				/*PurchaseObj.adjAmount = $("#cr_note").val();
				if ($("#invmode").is(':checked')) {
					PurchaseObj.invMode = 1;
				} else {
					PurchaseObj.invMode = 2;
				}*/
				PurchaseReturnObj.totalMrp = $("#totgrosmrp").val();
				PurchaseReturnObj.lotAdjAmount = $("#totltadj").val();
				/*PurchaseObj.specDiscPer = $("#spldisc").val();
				PurchaseObj.specDiscAmount = $("#spldiscamt").val();*/
				PurchaseReturnObj.remarks = $("#remarks").val();

				//PurchaseReturnObj.creditor_debit_amt
			/*	PurchaseReturnObj.duties_ledger_id= parseInt($('#duties_ledger_id').val());
				PurchaseReturnObj.round_ledger_id=parseInt($('#round_ledger_id').val());
				PurchaseReturnObj.purchase_ledger_id=parseInt($('#purchase_ledger_id').val());
				PurchaseReturnObj.discount_ledger_id= parseInt($('#discount_ledger_id').val());
				PurchaseReturnObj.lot_ledger_id= parseInt( $('#lot_ledger_id').val());
				PurchaseReturnObj.creditor_ledger_id= parseInt( $('#creditor_ledger_id').val());*/

				PurchaseReturnObj.duties_ledger_id= parseInt(isEmpty($('#duties_ledger_id').val())==true?0:$('#duties_ledger_id').val());
				PurchaseReturnObj.round_ledger_id=parseInt(isEmpty($('#round_ledger_id').val())==true?0:$('#round_ledger_id').val());
				PurchaseReturnObj.purchase_ledger_id=parseInt(isEmpty($('#purchase_ledger_id').val())==true?0:$('#purchase_ledger_id').val());
				PurchaseReturnObj.discount_ledger_id=parseInt(isEmpty($('#discount_ledger_id').val())==true?0:$('#discount_ledger_id').val());
				PurchaseReturnObj.lot_ledger_id=parseInt(isEmpty($('#lot_ledger_id').val())==true?0:$('#lot_ledger_id').val());
				PurchaseReturnObj.creditor_ledger_id=parseInt(isEmpty($('#creditor_ledger_id').val())==true?0:$('#creditor_ledger_id').val());



				console.log("PurchaseObj json: " + JSON.stringify(PurchaseReturnObj));

				$('#pleasewaitModal').modal('show');
				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/purret/createorupdatepurchasereturn.htm", PurchaseReturnObj, function(response) {
					$('#pleasewaitModal').modal('hide');
					console.log("save pur inv id=" + response);
					if (response == '0') {
						document.getElementById('confirmmessagecont').innerHTML = getPurInvRetText.dataNotAdd;
						$("#confirmval").val(0);
						showConfirmModal();
					} else {
						document.getElementById('confirmmessagecont').innerHTML = getPurInvRetText.dataSucAdd;
						$("#confirmval").val(response);
						showConfirmModal();

						/*$("#confirmval").val(response);
						$('#confirmPrintPurchaseModal').modal('show');*/
					}

				});

			} else {
				$("#confirmval").val(2);
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRetText.addEditChckBefrSave;
				showConfirmModal();
			}
		}
	});

});

function targetURL() {
	var result = $("#confirmval").val();
	console.log("save pur inv id targetURL=" + result);
	if (result == 0) {
		location.href = BASE_URL + '/purret/loadpurreturn.htm';
	} else if (result == 2) {
		location.href = "#";
	} else {
		if ($('input[name=printPurchase]').is(":checked")) {
			location.href = BASE_URL + "/purinv/printPurInvoice.htm?backUrl=loadpurchalan&pid=" + result;
		} else {
			location.href = BASE_URL + "/purret/loadpurreturn.htm?purRetId=ty678ui-90ewsd88-"+ result+"-71dglm35-jkas3try-dfvcbh231";
		}
	}
}

function newPurRetInv() {
	location.href = BASE_URL + '/purret/loadpurreturn.htm';
}

function veiwPurDetForRet(invno,retpurFinyr) {
	$("#searchmodtbody").html("");
	if (invno == '' || invno == 0) {

	} else {
		$('#pleasewaitModal').modal('show');
		var CommonRelsetmapperObj = {};
		//CommonRelsetmapperObj.invoiceNo = invno;
		CommonRelsetmapperObj.invoiceNo =$("#retpurDoc").val()+retpurFinyr+$("#retpurSlash").val()+ invno;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purret/getpurinvdetbyinvnoforret.htm", CommonRelsetmapperObj, function(response) {
			console.log("res=" + response);
			$('#pleasewaitModal').modal('hide');
			var purdetails = JSON.parse(response);
			if (purdetails.length == 0) {
				$("#purdetnotfounddiv").removeClass("hide");
				$("#purdetmodtable").addClass("hide");
				$("#purdetfounddiv").addClass("hide");
				$("#purdetailModal_okbtn").addClass("hide");
				$("#searchmodinvno").html($("#retpurDoc").val()+retpurFinyr+$("#retpurSlash").val()+ invno);
			} else {
				$("#purdetnotfounddiv").addClass("hide");
				$("#purdetmodtable").removeClass("hide");
				$("#purdetfounddiv").removeClass("hide");
				$("#purdetailModal_okbtn").removeClass("hide");
				for ( var i = 0; i < purdetails.length; i++) {
					var purdetail = purdetails[i];
					$("#searchmodinvno").html(purdetail.purchaseInvNo);
					$("#searchmodinvdate").html(moment(purdetail.purchaseInvDate).format('YYYY-MM-DD'));
					$("#searchmodtotamt").html(purdetail.netAmount);
					$("#searchmodvendorname").html(purdetail.distributorName);

					var starttrline = "<tr id=" + purdetail.itemUniqueKey + " style='cursor: pointer;'>";
					var chkbox ="";
					if(Number(purdetail.packQty) == Number(purdetail.prevReturnPackQty)){
					  chkbox = "<td><input id='" + purdetail.itemUniqueKey + "_modretcheck' class='chkboxcheked' type='checkbox' disabled value='" + JSON.stringify(purdetail) + "' ></td>";
					}else{
						 chkbox = "<td><input id='" + purdetail.itemUniqueKey + "_modretcheck' class='chkboxcheked' type='checkbox' value='" + JSON.stringify(purdetail) + "' ></td>";
							
					}
					var itmname = "<td>" + purdetail.itemName + "</td>";
					var itmsku = "<td>" + purdetail.sku + "</td>";
					var batch = "<td>" + purdetail.batchNo + "</td>";
					var exp = "<td>" + purdetail.expiryDateFormat + "</td>";
					var packQty = "<td id='" + purdetail.itemUniqueKey + "_pqty'>" + purdetail.packQty + "</td>";
//					var freeQty = "<td>" + purdetail.freeQty + "</td>";
					var freeQty = "<td id='" + purdetail.itemUniqueKey + "_freeqty'>"+purdetail.freeQty+"</td>";
					var stockQty = "<td id='" + purdetail.itemUniqueKey + "_stckqty'>"+purdetail.stockQty+"</td>";
					var prevReturnPackQty = "<td id='" + purdetail.itemUniqueKey + "_preretqty'>" + purdetail.prevReturnPackQty + "</td>";
					//var inputPackQty = "<td><input type='text' value='0' id='" + purdetail.itemUniqueKey + "_irpqty' size='3' onkeyup='calPQty(" + purdetail.packQty + "," + purdetail.freeQty + "," + purdetail.rate + ",this.value,&quot;" + purdetail.itemUniqueKey + "&quot;," + purdetail.edPer + "," + purdetail.taxPercentage + "," + purdetail.vatPer + "," + purdetail.discPer + "," + purdetail.mrp + ",&quot;" + purdetail.stockQty + "&quot;)'/></td>";
					var inputPackQty = "<td><input type='text' value='0' id='" + purdetail.itemUniqueKey + "_irpqty' size='3' onchange='getSlNoModal("+purdetail.serialNoRequired+","+JSON.stringify(purdetail.purchaseReturnDetailsSerialMapper)+",this.value,&quot;"+purdetail.itemUniqueKey+"&quot;)' onkeyup='calPQty(" + purdetail.packQty + "," + purdetail.freeQty + "," + purdetail.rate + ",this.value,&quot;" + purdetail.itemUniqueKey + "&quot;," + purdetail.edPer + "," + purdetail.taxPercentage + "," + purdetail.vatPer + "," + purdetail.discPer + "," + purdetail.mrp + ",&quot;" + purdetail.stockQty + "&quot;)' /></td>";
					var mrp = "<td id='" + purdetail.itemUniqueKey + "_mrp' class='hide'>" + parseFloat(purdetail.mrp).toFixed(2) + "</td>";//
					var rate = "<td id='" + purdetail.itemUniqueKey + "_rate'>" + parseFloat(purdetail.rate).toFixed(2) + "</td>";
					var edper = "<td class='hide' id='" + purdetail.itemUniqueKey + "_edper'>" + parseFloat(purdetail.edPer).toFixed(2) + "</td>";
					var taxper = "<td id='" + purdetail.itemUniqueKey + "_taxper'>" + parseFloat(purdetail.taxPercentage).toFixed(2) + "</td>";
					var vatper = "<td class='hide' id='" + purdetail.itemUniqueKey + "_vatper'>" + parseFloat(purdetail.vatPer).toFixed(2) + "</td>";
					var discper = "<td id='" + purdetail.itemUniqueKey + "_discper'>" + parseFloat(purdetail.discPer).toFixed(2) + "</td>";
					var retamt = "<td id='" + purdetail.itemUniqueKey + "_retamt'>0.00</td>";
					var edamt = "<td id='" + purdetail.itemUniqueKey + "_edamt' class='hide'>0.00</td>";
					var taxamt = "<td id='" + purdetail.itemUniqueKey + "_taxamt' class='hide'>0.0</td>";
					var vatamt = "<td id='" + purdetail.itemUniqueKey + "_vatamt' class='hide'>0.00</td>";
					var discamt = "<td id='" + purdetail.itemUniqueKey + "_discamt' class='hide'>0.00</td>";
					var allSlNos = "<td id='" + purdetail.itemUniqueKey + "_allSlNos' class='hide'>0</td>";
					var chkdSlNos = "<td id='" + purdetail.itemUniqueKey + "_chkdSlNos' class='hide'>0</td>";
					//var ltadjamt = "<td id='" + purdetail.itemUniqueKey + "_ltadjamt' class='hide'>"++"</td>";
					var endtrline = "</tr>";
					createdrowline = starttrline + chkbox + itmname + itmsku+ batch + exp + packQty + freeQty + stockQty + prevReturnPackQty + inputPackQty + mrp + rate + edper + taxper + vatper + discper + retamt + edamt + taxamt + vatamt + discamt + allSlNos + chkdSlNos + endtrline;
					$("#searchmodtbody").append(createdrowline);
				}
			}
		});
		$("#purRetMsgText").text("");
		$("#purdetailModal").modal("show");
	}
}

function getSlNoModal(item_is_slno,itemDetail,retPqty,itemUniqueKey)
{
	/*var pqty = itemDetail.packQty;
	var itemid = itemDetail.itemId;
	var item_is_slno = itemDetail.serialNoRequired;*/
	if(item_is_slno==1){
		for ( var i = 1; i <= 30; i++) {
			$("#checkbox"+i).attr('checked', false);
		}

		var sno=[];
		if($.isEmptyObject(itemDetail))
		{}
		else
		{
		for(var i=0;i<itemDetail.length;i++)
		{
			sno.push(itemDetail[i].uniqueIdentifierNo);
		}
		}
	   /* var slnos=$("#purrettabitemdetails tr#" + itemid).find("#tbl_allSlNos").text();
	    var sno=slnos.split(",");*/
		if(sno.length<=0){
			$("#currStkGraterModal").modal("show");
			$("#currstkofitm").text(0);
			$("#curstkcheck").val(1);
		}else{
			for ( var i = 0; i < sno.length; i++) {
				$("#textbox"+(i+1)).val(sno[i]);
				/*for(var m=0;m<chkdsno.length;m++){
					//alert("slnos="+sno[i]);
					if(chkdsno[m]==sno[i]){
						$("#checkbox"+(i+1)).prop("checked",true);
					}

				}*/
				$("#TextBoxDiv_"+(i+1)).removeClass("hide");
			}
			var itmdetlen=sno.length;
			for ( var i = itmdetlen; i < 30-itmdetlen; i++) {
				$("#TextBoxDiv_"+(i+1)).addClass("hide");
			}
			$("#"+itemUniqueKey+"_allSlNos").text(sno);
			$("#modalCltype").val(1);
			$("#itemUniqueKey").val(itemUniqueKey);
			$("#openSerialModal").modal("show");
		}

	}else
	{
		//calForPqtyChng(retPqty);
	}
}

function calPQty(	pqty,
					freeqty,
					rate,
					inputpqty,
					itemid,
					edper,
					taxper,
					vatper,
					discper,
					mrp,
					stockqty) {
	var stckpqty = pqty;
	var qty = inputpqty;
	var edpercnt = edper;
	var dprcnt = discper;
	var taxprcnt = taxper;
	var vatprcnt = vatper;
	//alert("stockqty:"+stockqty);
	var arr = stockqty.split('[');
	//alert("arr:"+arr);
	var a = arr[1];
	//alert("a:"+stockqty);
	var arr2 = a.split(']');
	var totStockQty = arr2[0];
	var ltadj = 0.0;
	var taxval = 0.0;
	var disval = 0.0;
	//alert("qty: "+qty+";totStockQty: "+totStockQty);
	//var rate = rate;
	//var mrp = $("#mrp").val();
	//var ratio = $("#ratio").val();
	var free = freeqty;
	if (free == "") {
		free = 0;
	}
	if (mrp == "") {
		mrp = 0;
	}
	/*alert(qty);
	if(qty.indexOf('.') != -1){
		document.getElementById('alertmessagecont').innerHTML = "Ret P.Qty "+getFieldText.notDecimalChk;
		$("#" + itemid + "_irpqty").focus();
		$("#" + itemid + "_irpqty").val(0);
		return false;
	}else
	{}*/

	if(isNaN(qty))
	{
		document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck+" in Ret P.Qty";
		$("#" + itemid + "_irpqty").focus();
		$("#" + itemid + "_irpqty").val(0);
		return false;
	}else{}

	//amt calculation
	if (Number(qty) > Number(stckpqty) || Number(qty) < 0) {
		$("#" + itemid + "_irpqty").val(0);
		$("#" + itemid + "_retamt").text(parseFloat(0).toFixed(2));
	} else {
		if (rate == "") {

		} else {
			var amt = qty * rate;
			$("#" + itemid + "_retamt").text(parseFloat(amt).toFixed(2));
		}
	}

	if(((free % 1)!=0) || free<1)
	{
		// LtAdj calculation start
		var modFree =  free % 1;
		var tot = $("#" + itemid + "_retamt").text();
		var pqtyPerFree = qty/modFree;
		ltadj = tot/pqtyPerFree;
		// LtAdj calculation end
	}


	//ed calculation
	if (edpercnt == "" || mrp == "") {

	} else {
		var edval = qty * mrp * edpercnt / 100;
		$("#" + itemid + "_edamt").text(parseFloat(edval).toFixed(4));
	}
	//vat calculation
	if (vatprcnt == "" || mrp == "") {

	} else {
		//var vatval = (Number(qty) + Number(free)) * mrp * vatprcnt / 100;
		var vatval = (Number(qty) + Number(free)) * rate * vatprcnt / 100;
		$("#" + itemid + "_vatamt").text(parseFloat(vatval).toFixed(4));
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
		$("#" + itemid + "_discamt").text(parseFloat(disval).toFixed(4));
	}

	//tax calculation
	if (taxprcnt == ""|| rate == "") {

	}
	else
	{
		if(((free % 1)!=0) || free<1)
		{
			taxval = (((Number(rate) * (Number(qty)))-Number(ltadj)) - Number($("#" + itemid + "_discamt").text())) * Number(taxprcnt) / 100;
		}
		else
		{
			//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
			taxval = ((Number(rate) * (Number(qty))) - Number($("#" + itemid + "_discamt").text())) * Number(taxprcnt) / 100;
		}
		$("#" + itemid + "_taxamt").text(parseFloat(taxval).toFixed(4));
	}

}

function getmodretcheckeditemlist() {
	var len = $('.chkboxcheked:checked').length;
	var count = 0 ;
	var flag = 0;
	$('#searchmodtable > tbody > tr').each(function() {
		var itemid = this.id;
		console.log("len=" + len);
		if (len == 0) {
			$("#alertmessagecont").text("Please check at least one item.");
		} else {
			$("#alertmessagecont").text("");
			
			if ($("#" + itemid + "_modretcheck").is(":checked")) {
				var qty = $(this).find("#" + itemid + "_pqty").text();
				var pqty = $(this).find("#" + itemid + "_irpqty").val();
				var stock = $(this).find("#" + itemid + "_stckqty").text().toString().split(".")[0];
				var preRetQty = $(this).find("#" + itemid + "_preretqty").text();
				
				var totqty = Number(pqty) - Number(preRetQty);
				
				
				/*alert("totqty: "+totqty+" , stock: "+stock);*/
				if((pqty == "") || (pqty == 0))
				{
					count = count+1;
				}
				else if(Number(totqty) > Number(stock)){
					flag = flag+1;
					$("#" + itemid + "_modretcheck").attr('checked', false);
					
				}
				else if(Number(qty) == Number(preRetQty)){
					$("#" + itemid + "_modretcheck").attr('checked', false);
				}
				else
				{

				}
				/*if(count==0)
				{
					alert("enter");
					var purdetail = $("#" + itemid + "_modretcheck").val();
					var itemdetail = JSON.parse(purdetail);
					insertModDatatoRetTable(itemdetail);
				}else{}*/
			}
		}
	});
	if (len == 0) {
		$("#purRetMsgText").text("");
		$('#purdetailModal').modal('show');
	}
	else
	{
		if(count!= 0)
		{
			$("#alertmessagecont").text("Ret P.Qty "+getFieldText.fieldReq);
			$("#purRetMsgText").text("");
			$('#purdetailModal').modal('show');
			//$("#purrettabitemdetails").empty();
		}/*else if(flag != 0){
			$('#purRetMsg').modal('show');
		}*/
		else
		{	
			if(flag != 0){
				$('#purRetMsg').modal('show');
			}
			insertModDatatoRetTable();
			closePurdetailModal();
			$('#purdetailModal').modal('hide');
		}
	}

	//closePurdetailModal();
	calculateGrandTotal();
	calculateTotalMRP();
	calculateTotalED();
	calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	calculateTotalLtAdj();
	calculateNetTotal();
	clearHeaderDiv();
}

function insertModDatatoRetTable() {
	//$("#purrettabitemdetails").text("");
	var saleretvendorid = $("#slctedvendorid").val();
	var ltadj = 0.0;
	var rtrnFree = 0.0;
	$('#searchmodtable tbody tr').each(function() {
		var itemid = this.id;
		if ($("#" + itemid + "_modretcheck").is(":checked")) {
		var itemdetail = JSON.parse($("#"+itemid+"_modretcheck").val());

		if (saleretvendorid == 0 || saleretvendorid == itemdetail.distributorId) {
			console.log("same vendor");
			var uniquechk = 0;
			//var newunikey = itemdetail.itemId + itemdetail.purchaseInvNo;
			var newunikey = itemdetail.itemUniqueKey + itemdetail.purchaseInvNo;
			console.log("newunikey=" + newunikey);
			$('#purretitem tbody tr').each(function() {
				var itemid = this.id;
				var purinvno = $(this).find("#tbl_invno").html();
				var preunikey = itemid + purinvno;
				console.log("preunikey=" + preunikey);
				if (newunikey == preunikey) {
					uniquechk = 1;
				}
			});
			console.log("uniquechk=" + uniquechk);
			if (uniquechk == 1) {
				$("#sameItemInvModal").modal("show");
			} else {
				var calretamt = $("#" + itemdetail.itemUniqueKey + "_retamt").text();
				var caledamt = $("#" + itemdetail.itemUniqueKey + "_edamt").text();
				var caltaxamt = $("#" + itemdetail.itemUniqueKey + "_taxamt").text();
				var calvatamt = $("#" + itemdetail.itemUniqueKey + "_vatamt").text();
				var caldiscamt = $("#" + itemdetail.itemUniqueKey + "_discamt").text();
				var allSlNos = $("#" + itemdetail.itemUniqueKey + "_allSlNos").text();
				var chkdSlNos = $("#" + itemdetail.itemUniqueKey + "_chkdSlNos").text();
				//var lqty = $("#" + itemdetail.itemId + "_irlqty").val();
				var pqty = $("#" + itemdetail.itemUniqueKey + "_irpqty").val();
				var free = itemdetail.freeQty;
				var bpqty = itemdetail.packQty;
				var tot = parseFloat(calretamt).toFixed(4);
				if(((free % 1)!=0) || free<1)
				{
					// LtAdj calculation start
					var modFree =  free % 1;
					rtrnFree = (pqty*modFree)/bpqty;
					var pqtyPerFree = pqty/rtrnFree;
					ltadj = tot/pqtyPerFree;
					//alert("modFree:"+modFree+"; pqty:"+pqty+"; pqtyPerFree:"+pqtyPerFree+";tot:"+tot+"; ltadj:"+ltadj);

					// LtAdj calculation end
				}
				else
				{
					rtrnFree = 0;
				}
				$("#slctedvendorid").val(itemdetail.distributorId);
				$("#slctedvendorid").attr('disabled', true);
				var freevalue = 0.0;
				if(itemdetail.freeQty==0)
				{
					freevalue = 0;
				}
				else
				{
					freevalue = itemdetail.freeQty;
				}
				var barcode = "";
				if(itemdetail.sku==undefined)
				{
					barcode = "";
				}
				else
				{
					barcode = itemdetail.sku;
				}
				var createdrowline="";
				var starttrline = "<tr id=" + itemdetail.itemUniqueKey + " style='cursor: pointer;' onclick='javascript:itemDetailView(this.id)'>";
				var itmname = "<td id='tbl_item_name'>" + itemdetail.itemName + "</td>";
				var itmsku = "<td id='tbl_item_sku'>" + itemdetail.sku + "</td>";
				var batch = "<td id='tbl_batch_no'>" + itemdetail.batchNo + "</td>";
				var exp = "<td id='tbl_exp'>" + itemdetail.expiryDateFormat + "</td>";
				var packQty = "<td id='tbl_pqty'>" + pqty + "</td>";
				var looseQty = "<td id='tbl_lqty'>" + (itemdetail.conversion * pqty) + "</td>";
				var conv = "<td id='tbl_ratio'>" + itemdetail.conversion + "</td>";
				var free = "<td id='tbl_free'>" + parseFloat(rtrnFree).toFixed(2) + "</td>";
				var previousFree = '<td id="tbl_prevfree" class="hide">' + parseFloat(freevalue).toFixed(2) + '</td>';
				//var free = "<td id='tbl_free'>0</td>";
				var mrp = "<td id='tbl_mrp' class='hide'>" + parseFloat(itemdetail.mrp).toFixed(4) + "</td>";
				var tabrate = "<td id='tbl_rate' >" + parseFloat(itemdetail.rate).toFixed(4) + "</td>";
				var edamt = "<td id='tbl_ed' class='hide'>" + parseFloat(caledamt).toFixed(4) + "</td>";
				var taxamt = "<td id='tbl_tax'>" + parseFloat(caltaxamt).toFixed(4) + "</td>";
				var vatamt = "<td id='tbl_vat' class='hide'>" + parseFloat(calvatamt).toFixed(4) + "</td>";
				var discamt = "<td id='tbl_disc'>" + parseFloat(caldiscamt).toFixed(4) + "</td>";
				var retamt = "<td id='tbl_amnt'>" + parseFloat(calretamt).toFixed(4) + "</td>";
				var totmrp = "<td id='tbl_totamnt' class='hide'>" + parseFloat(itemdetail.mrp * pqty).toFixed(4) + "</td>";
				var rowdelete = "<td><button class='btn btn-theme04 btn-xs' id='" + itemdetail.itemUniqueKey + "' onclick='javascript:showPurRetItemDelModal(this.id);'><i class='fa fa-trash-o '></i></button></td>";
				var markup = "<td id='tbl_ma' class='hide'>0.00</td>";
				var grpname = "<td id='tbl_grp' class='hide'>" + itemdetail.grpName + "</td>";
				var schname = "<td id='tbl_sch' class='hide'>" + itemdetail.scheduleName + "</td>";
				var mfg = "<td id='tbl_mfg' class='hide'>" + itemdetail.manufacturerName + "</td>";
				var edper = "<td id='tbl_edprcnt' class='hide'>" + itemdetail.edPer + "</td>";
				var taxPer = "<td id='tbl_taxprcnt' class='hide'>" + itemdetail.taxPercentage + "</td>";
				var vatPer = "<td id='tbl_vatprcnt' class='hide'>" + itemdetail.vatPer + "</td>";
				var discper = "<td id='tbl_dprcnt' class='hide'>" + parseFloat(itemdetail.discPer).toFixed(4) + "</td>";
				var grpid = "<td id='tbl_grpid' class='hide'>" + itemdetail.grpId + "</td>";
				var schid = "<td id='tbl_schid' class='hide'>" + itemdetail.scheduleId + "</td>";
				var mfgid = "<td id='tbl_mfgid' class='hide'>" + itemdetail.manufacturerId + "</td>";
				var punitid = "<td id='tbl_punitid' class='hide'>" + itemdetail.packUnitId + "</td>";
				var tblid = "<td id='tbl_id' class='hide'>" + itemdetail.itemId + "</td>";
				var tblitemid = "<td id='tbl_itemid' class='hide'>" + itemdetail.itemId + "</td>";
				var purchaseInvNo = "<td id='tbl_invno' class='hide'>" + itemdetail.purchaseInvNo + "</td>";
				var purchaseInvId = "<td id='tbl_invid' class='hide'>" + itemdetail.purchaseId + "</td>";
				var billpackqty = "<td class='hide' id='purrettabpqtyhide'>"+itemdetail.packQty+"</td>";
				var ltadjRow = "<td class='hide' id='tbl_ltAdj'>" + ltadj + "</td>";
				//var ltadjRow = "<td class='hide' id='tbl_ltAdj'>" + parseFloat(0).toFixed(4) + "</td>";
				var salepty = "<td id='salepqty' class='hide'>" + itemdetail.packQty + "</td>";
				var salelqty = "<td id='salelqty' class='hide'>" + itemdetail.looseQty + "</td>";
				var salerettablunitid = "<td id='salerettablunitid' class='hide'>" + itemdetail.looseUnitId + "</td>";
				var salerettabcontent = "<td id='salerettabcontent' class='hide'>" + itemdetail.contentName + "</td>";
				var salerettabsaleid = "<td id='salerettabsaleid' class='hide'>" + itemdetail.saleId + "</td>";
				var salerettabsaleinvno = "<td id='salerettabsaleinvno' class='hide'>" + itemdetail.saleInvNo + "</td>";
				var packQtyhide = "<td id='salerettabpqtyhide' class='hide'>" + itemdetail.hidePackQty + "</td>";
				var looseQtyhide = "<td id='salerettablqtyhide' class='hide'>" + itemdetail.hideLooseQty + "</td>";
				var taxid = "<td id='tbl_taxid' class='hide'>" + itemdetail.taxId + "</td>";
				var taxmode = "<td id='tbl_taxmode' class='hide'>" + itemdetail.taxMode + "</td>";
				var taxisgrptax = "<td id='tbl_taxisgrptax' class='hide'>" + itemdetail.isGroupTax + "</td>";
				var sku = "<td id='tbl_sku' class='hide'>" + barcode + "</td>";
				var hsn = "<td id='tbl_hsn' class='hide'>" + itemdetail.hsnCode + "</td>";
				var returnReason = '<td>'+$("#reasonTypeDiv").html()+'</td>';

				var isDiscount = "<td id='tbl_isDiscount' class='hide'>" + itemdetail.isDiscount + "</td>";
				var isslnos = '<td id="tbl_itemisslno" class="numeric hide">' + itemdetail.serialNoRequired + '</td>';
				var isdualStock = "<td id='tbl_isDualStock' class='hide'>" + itemdetail.dualStockRequired + "</td>";
				var isBatch = '<td id="tbl_isBatch" class="numeric hide">' + itemdetail.batchWiseStock + '</td>';
				var isExpDt = '<td id="tbl_isExpDt" class="numeric hide">' + itemdetail.expiryDateRequired + '</td>';
				var isPriceList = "<td id='tbl_isPriceList' class='hide'>" + itemdetail.priceListRequired + "</td>";
				var isMrp = '<td id="tbl_isMrp" class="numeric hide">' + itemdetail.mrpRequired + '</td>';
				var allSlNosTd = '<td id="tbl_allSlNos" class="hide">' + allSlNos + '</td>';
				var chkdSlNosTd = '<td id="tbl_chkdSlNos" class="hide">' + chkdSlNos + '</td>';
				var tbl_exp_date = '<td id="tbl_exp_date" class="numeric hide">' + moment(itemdetail.expiryDate).format('YYYY-MM-DD') + '</td>';
				var endtrline = "</tr>";
				createdrowline = starttrline + itmname + itmsku + batch + exp + packQty + looseQty + conv + free + previousFree + mrp + tabrate + edamt + taxamt + vatamt + discamt + retamt + totmrp + returnReason + rowdelete + markup + grpname + schname + mfg + edper + taxPer + vatPer + discper + grpid + schid + mfgid + punitid + tblid + tblitemid + purchaseInvNo + purchaseInvId+billpackqty + ltadjRow + taxid + taxmode + taxisgrptax + sku + hsn + isDiscount + isslnos + isdualStock + isBatch + isExpDt + isPriceList + isMrp + allSlNosTd + chkdSlNosTd+tbl_exp_date + endtrline;
				$("#purrettabitemdetails").append(createdrowline);
				$("#" + itemdetail.itemUniqueKey).find('#returnReasonTypeList').attr("id","tbl_reason");
				$("#" + itemdetail.itemUniqueKey).find('#tbl_reason').attr("class","form-control-trx");
				$(".selectedReason").val(0);
				if ($("#slctCmnReason").val() != 0)
				{
					$("#" + itemdetail.itemUniqueKey).find('#tbl_reason').val($("#slctCmnReason").val());
				}





				getvendorledger($('#dueties_and_tax_acc').val(),0,0,0);// for duties and tax
				getvendorledger($('#roundoff_group_code').val(),0,0,1);// for round off
				getvendorledger($('#pursaccunt_group_code').val(),0,0,2);// for sale account
				getvendorledger($('#crditor_group_code').val(),0,$('#slctedvendorid').val(),3);// for sunndry creditor
				getvendorledger($('#dicount_group_code').val(),0,0,4);// for sunndry creditor
				getvendorledger($('#lot_group_code').val(),0,0,5);// for lot adjasment


			}

			$('#purretitem >tbody >tr > td:nth-child(16)').click(function () {
		        return false;
		    });
		} else {
			$("#sameVendorModal").modal("show");
		}
		}
	});
}

function insertModDatatoRetTableWithParam(itemdetail) {
	//$("#purrettabitemdetails").text("");
	var crntstkqty=itemdetail.stockQty;
	var saleretvendorid = $("#slctedvendorid").val();
	var ltadj = 0.0;
	var rtrnFree = 0.0;

	if (saleretvendorid == 0 || saleretvendorid == itemdetail.distributorId) {
		console.log("same vendor");
		var uniquechk = 0;
		//var newunikey = itemdetail.itemId + itemdetail.purchaseInvNo;
		var newunikey = itemdetail.itemUniqueKey + itemdetail.purchaseInvNo;
		console.log("newunikey=" + newunikey);
		$('#purretitem tbody tr').each(function() {
			var itemid = this.id;
			var purinvno = $(this).find("#tbl_invno").html();
			var preunikey = itemid + purinvno;
			console.log("preunikey=" + preunikey);
			if (newunikey == preunikey) {
				uniquechk = 1;
			}
		});
		console.log("uniquechk=" + uniquechk);
		if (uniquechk == 1) {
			$("#sameItemInvModal").modal("show");
		} else {
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
			if(((free % 1)!=0) || free<1)
			{
				// LtAdj calculation start
				var modFree =  free % 1;
				rtrnFree = (pqty*modFree)/bpqty;
				var pqtyPerFree = pqty/rtrnFree;
				ltadj = tot/pqtyPerFree;
				// LtAdj calculation end
			}
			else
			{
				rtrnFree = 0;
			}
			$("#slctedvendorid").val(itemdetail.distributorId);
			$("#slctedvendorid").attr('disabled', true);
			var freevalue = 0.0;
			if(itemdetail.freeQty==0)
			{
				freevalue = 0;
			}
			else
			{
				freevalue = itemdetail.freeQty;
			}
			var barcode = "";
			if(itemdetail.sku==undefined)
			{
				barcode = "";
			}
			else
			{
				barcode = itemdetail.sku;
			}
			var createdrowline="";
			var starttrline = "<tr id=" + itemdetail.itemUniqueKey + " style='cursor: pointer;' onclick='javascript:itemDetailView(this.id)'>";
			var itmname = "<td id='tbl_item_name'>" + itemdetail.itemName + "</td>";
			var itmsku = "<td id='tbl_item_sku'>" + itemdetail.sku + "</td>";
			var batch = "<td id='tbl_batch_no'>" + itemdetail.batchNo + "</td>";
			var exp = "<td id='tbl_exp'>" + itemdetail.expiryDateFormat + "</td>";
			/*var packQty = "<td id='tbl_pqty'>" + pqty + "</td>";*/
			var packQty = "<td id='tbl_pqty'>" + parseInt(crntstkqty) + "</td>";///Sayantan Mahanty added date-26/02/2020
			/*var looseQty = "<td id='tbl_lqty'>" + (itemdetail.conversion * pqty) + "</td>";*/
			var looseQty = "<td id='tbl_lqty'>" + (itemdetail.conversion * parseInt(crntstkqty)) + "</td>";
			var conv = "<td id='tbl_ratio'>" + itemdetail.conversion + "</td>";
			var free = "<td id='tbl_free'>" + parseFloat(rtrnFree).toFixed(2) + "</td>";
			var previousFree = '<td id="tbl_prevfree" class="hide">' + parseFloat(freevalue).toFixed(2) + '</td>';
			//var free = "<td id='tbl_free'>0</td>";
			var mrp = "<td id='tbl_mrp' class='hide'>" + parseFloat(itemdetail.mrp).toFixed(4) + "</td>";
			var tabrate = "<td id='tbl_rate' >" + parseFloat(itemdetail.rate).toFixed(4) + "</td>";
			var edamt = "<td id='tbl_ed' class='hide'>" + parseFloat(caledamt).toFixed(4) + "</td>";
			var taxamt = "<td id='tbl_tax'>" + parseFloat(caltaxamt).toFixed(4) + "</td>";
			var vatamt = "<td id='tbl_vat' class='hide'>" + parseFloat(calvatamt).toFixed(4) + "</td>";
			var discamt = "<td id='tbl_disc'>" + parseFloat(caldiscamt).toFixed(4) + "</td>";
			var retamt = "<td id='tbl_amnt'>" + parseFloat(calretamt).toFixed(4) + "</td>";
			var totmrp = "<td id='tbl_totamnt' class='hide'>" + parseFloat(itemdetail.mrp * pqty).toFixed(4) + "</td>";
			var rowdelete = "<td><button class='btn btn-theme04 btn-xs' id='" + itemdetail.itemUniqueKey + "' onclick='javascript:showPurRetItemDelModal(this.id);'><i class='fa fa-trash-o '></i></button></td>";
			var markup = "<td id='tbl_ma' class='hide'>0.00</td>";
			var grpname = "<td id='tbl_grp' class='hide'>" + itemdetail.grpName + "</td>";
			var schname = "<td id='tbl_sch' class='hide'>" + itemdetail.scheduleName + "</td>";
			var mfg = "<td id='tbl_mfg' class='hide'>" + itemdetail.manufacturerName + "</td>";
			var edper = "<td id='tbl_edprcnt' class='hide'>" + itemdetail.edPer + "</td>";
			var taxPer = "<td id='tbl_taxprcnt' class='hide'>" + itemdetail.taxPercentage + "</td>";
			var vatPer = "<td id='tbl_vatprcnt' class='hide'>" + itemdetail.vatPer + "</td>";
			var discper = "<td id='tbl_dprcnt' class='hide'>" + parseFloat(itemdetail.discPer).toFixed(4) + "</td>";
			var grpid = "<td id='tbl_grpid' class='hide'>" + itemdetail.grpId + "</td>";
			var schid = "<td id='tbl_schid' class='hide'>" + itemdetail.scheduleId + "</td>";
			var mfgid = "<td id='tbl_mfgid' class='hide'>" + itemdetail.manufacturerId + "</td>";
			var punitid = "<td id='tbl_punitid' class='hide'>" + itemdetail.packUnitId + "</td>";
			var tblid = "<td id='tbl_id' class='hide'>" + itemdetail.itemId + "</td>";
			var tblitemid = "<td id='tbl_itemid' class='hide'>" + itemdetail.itemId + "</td>";
			var purchaseInvNo = "<td id='tbl_invno' class='hide'>" + itemdetail.purchaseInvNo + "</td>";
			var purchaseInvId = "<td id='tbl_invid' class='hide'>" + itemdetail.purchaseId + "</td>";
			var billpackqty = "<td class='hide' id='purrettabpqtyhide'>"+itemdetail.packQty+"</td>";
			var ltadjRow = "<td class='hide' id='tbl_ltAdj'>" + ltadj + "</td>";
			//var ltadjRow = "<td class='hide' id='tbl_ltAdj'>" + parseFloat(0).toFixed(4) + "</td>";
			var salepty = "<td id='salepqty' class='hide'>" + itemdetail.packQty + "</td>";
			var salelqty = "<td id='salelqty' class='hide'>" + itemdetail.looseQty + "</td>";
			var salerettablunitid = "<td id='salerettablunitid' class='hide'>" + itemdetail.looseUnitId + "</td>";
			var salerettabcontent = "<td id='salerettabcontent' class='hide'>" + itemdetail.contentName + "</td>";
			var salerettabsaleid = "<td id='salerettabsaleid' class='hide'>" + itemdetail.saleId + "</td>";
			var salerettabsaleinvno = "<td id='salerettabsaleinvno' class='hide'>" + itemdetail.saleInvNo + "</td>";
			var packQtyhide = "<td id='salerettabpqtyhide' class='hide'>" + itemdetail.hidePackQty + "</td>";
			var looseQtyhide = "<td id='salerettablqtyhide' class='hide'>" + itemdetail.hideLooseQty + "</td>";
			var taxid = "<td id='tbl_taxid' class='hide'>" + itemdetail.taxId + "</td>";
			var taxmode = "<td id='tbl_taxmode' class='hide'>" + itemdetail.taxMode + "</td>";
			var taxisgrptax = "<td id='tbl_taxisgrptax' class='hide'>" + itemdetail.isGroupTax + "</td>";
			var sku = "<td id='tbl_sku' class='hide'>" + barcode + "</td>";
			var hsn = "<td id='tbl_hsn' class='hide'>" + itemdetail.hsnCode + "</td>";
			var returnReason = '<td>'+$("#reasonTypeDiv").html()+'</td>';

			var isDiscount = "<td id='tbl_isDiscount' class='hide'>" + itemdetail.isDiscount + "</td>";
			var isslnos = '<td id="tbl_itemisslno" class="numeric hide">' + itemdetail.serialNoRequired + '</td>';
			var isdualStock = "<td id='tbl_isDualStock' class='hide'>" + itemdetail.dualStockRequired + "</td>";
			var isBatch = '<td id="tbl_isBatch" class="numeric hide">' + itemdetail.batchWiseStock + '</td>';
			var isExpDt = '<td id="tbl_isExpDt" class="numeric hide">' + itemdetail.expiryDateRequired + '</td>';
			var isPriceList = "<td id='tbl_isPriceList' class='hide'>" + itemdetail.priceListRequired + "</td>";
			var isMrp = '<td id="tbl_isMrp" class="numeric hide">' + itemdetail.mrpRequired + '</td>';
			var iscurrentStk = '<td id="tbl_iscurrentStk" class="hide">' + itemdetail.stockQty + '</td>';
			var tbl_exp_date = '<td id="tbl_exp_date" class="numeric hide">' + moment(itemdetail.expiryDate).format('YYYY-MM-DD') + '</td>';
			
			//console.log("itemdetail: "+JSON.stringify(itemdetail));
			var allSlNos = "";
			var chkdSlNos = "";

			if(itemdetail.serialNoRequired==1)
			{
				var arr=[];
				for(var i=0;i<itemdetail.purchaseReturnDetailsSerialMapper.length;i++)
				{
					arr.push(itemdetail.purchaseReturnDetailsSerialMapper[i].uniqueIdentifierNo);
				}
				allSlNos = '<td id="tbl_allSlNos" class="hide">' + arr + '</td>';
				chkdSlNos = '<td id="tbl_chkdSlNos" class="hide">' + arr + '</td>';
			}
			else
			{

				allSlNos = '<td id="tbl_allSlNos" class="hide">' + 0 + '</td>';
				chkdSlNos = '<td id="tbl_chkdSlNos" class="hide">' + 0 + '</td>';
			}
			var endtrline = "</tr>";
			createdrowline = starttrline + itmname + itmsku + batch + exp + packQty + looseQty + conv + free + previousFree + mrp + tabrate + edamt + taxamt + vatamt + discamt + retamt + totmrp + returnReason + rowdelete + markup + grpname + schname + mfg + edper + taxPer + vatPer + discper + grpid + schid + mfgid + punitid + tblid + tblitemid + purchaseInvNo + purchaseInvId+billpackqty + ltadjRow + taxid + taxmode + taxisgrptax + sku + hsn + isDiscount + isslnos + isdualStock + isBatch + isExpDt + isPriceList + isMrp + iscurrentStk + allSlNos + chkdSlNos + tbl_exp_date + endtrline;
			$("#purrettabitemdetails").append(createdrowline);
			$("#" + itemdetail.itemUniqueKey).find('#returnReasonTypeList').attr("id","tbl_reason");
			$("#" + itemdetail.itemUniqueKey).find('#tbl_reason').attr("class","form-control-trx");
			$(".selectedReason").val(0);
			if ($("#slctCmnReason").val() != 0)
			{
				$("#" + itemdetail.itemUniqueKey).find('#tbl_reason').val($("#slctCmnReason").val());
			}
		}

		$('#purretitem >tbody >tr > td:nth-child(16)').click(function () {
	        return false;
	    });
	} else {
		$("#sameVendorModal").modal("show");
	}
}

function insertModData(purdetail) {
	console.log(JSON.stringify(purdetail));
	var packQty = purdetail.packQty;
	var preReturnPackQty = purdetail.prevReturnPackQty;
	var totQty = Number(packQty) - Number(preReturnPackQty);
	var stockQty = purdetail.stockQty.toString().split(".")[0];
	/*alert(totQty + " "+stockQty);*/
	if(Number(packQty) == Number(preReturnPackQty)){
		$("#purRetMsgText").text("Already Returned.");
		
	}
	/*Sayantan Mahanty added date-26/02/2020*/
	/*else if(Number(totQty) > Number(stockQty)){
		$("#purRetMsgText").text("");
		$('#purRetMsg').modal('show');
    }*/
	else{
	$("#purRetMsgText").text("");
	insertModDatatoRetTableWithParam(purdetail);
	$('#itempurdetailModal').modal('hide');

	closePurdetailModal();
	calculateGrandTotal();
	calculateTotalMRP();
	//calculateTotalED();
	//calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	calculateTotalLtAdj();
	calculateNetTotal();
	clearHeaderDiv();
	}
}

function closePurdetailModal()
{
	$("#searchmodtbody").empty();
}



function getvendorledger(group_code,acc_id,ref_id,para)
{
	 //var keyword=ref_id.toString();
	//  var trackname=keyword.split("_");
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

	if (para==2) { // for purchase

		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;
	}

if (para==3) { // for creditor

		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=ref_id;
		commonobj.id=1;
	}

if (para==4) { // for discount

	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;
}


if (para==5) { // for lotadjasment

	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;
}


$('#pleasewaitModal').modal('show');
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchledgerusinggroup.htm", commonobj, function(response) {
		$('#pleasewaitModal').modal('hide');

		var status = JSON.parse(response);

		if (para==0) {// for duties and tax
			console.log("para0");

			$.each(status, function(i) {


				 $('#duties_ledger_id').val(status[i].id);

			});
		}

		if (para==1) {// for round off
			console.log("para1");
			$.each(status, function(i) {


				 $('#round_ledger_id').val(status[i].id);

			});
		}
	if (para==2) { // for purchase

		console.log("para2");
				$.each(status, function(i) {


					 $('#purchase_ledger_id').val(status[i].id);

				});
			}

	if (para==3) {// for creditor
		console.log("para3");
		$.each(status, function(i) {


			 $('#creditor_ledger_id').val(status[i].id);

		});
		 }

	if (para==4) {// for discount
		console.log("para4");
		$.each(status, function(i) {


			 $('#discount_ledger_id').val(status[i].id);

		});
		 }


	if (para==5) {// for lot adjasment
		console.log("para5");
		$.each(status, function(i) {


			 $('#lot_ledger_id').val(status[i].id);

		});
		 }

		//chngeResultStat(status);
	});

}

function onchangeDistributor()
{


	getvendorledger($('#dueties_and_tax_acc').val(),0,0,0);// for duties and tax
	getvendorledger($('#roundoff_group_code').val(),0,0,1);// for round off
	getvendorledger($('#pursaccunt_group_code').val(),0,0,2);// for sale account
	if ($('#slctedvendorid').val()==0) {

		$('#creditor_ledger_id').val(0);

	}else {
		getvendorledger($('#crditor_group_code').val(),0, $('#slctedvendorid').val(),3);// for sunndry creditor
	}

	getvendorledger($('#dicount_group_code').val(),0,0,4);// for sunndry creditor
	getvendorledger($('#lot_group_code').val(),0,0,5);// for lot adjasment

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
function isEmpty(val) {
    return (val === undefined || val == null || val.trim().length <= 0) ? true : false;
}
