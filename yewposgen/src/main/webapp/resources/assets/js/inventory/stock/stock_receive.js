var sale_order_chain=0;
var sale_order_chain_qty = 0;
var sale_order_chain_ordid=0;
var sale_order_adv_arr = {};
var openPaymentModal = 0; // 29.5.2019

function isEmpty(val) {

    return (val === undefined || val == null || val.trim().length <= 0) ? true : false;
}

var remarks = $("#remarkstext").text();
var samepageret = 0;
var sale_id_present=0;
var autoadd_barcodeflag=0;
$(document).ready(function() {
	
	var seslocid=$("#seslocationid").val();
	$("#location").val(seslocid);
	var retpagesamesaleid = $("#retamtsamepagesaleid").val();

	$("#pcodeid").val($("#esiCodeId").val());
	$("#esitypeid").val($("#esiTypeId").val() == '' ? 0 : $("#esiTypeId").val());
	var etype = $("#esitypeid").val();

	if (etype == 1) {
		$("#codetextid").removeClass("hide");
		$("#pregtextid").removeClass("hide");
		$("#codevalid").removeClass("hide");
		$("#pregvalid").removeClass("hide");
	} else if (etype == 3) {
		$("#codetextid").addClass("hide");
		$("#pregtextid").addClass("hide");
		$("#codevalid").addClass("hide");
		$("#pregvalid").addClass("hide");
		$("#remarkstext").text("Place of Treatment");
		// alert(etype);
		$("#remarks").autocomplete({
			source : function(	request,
								response) {
				$.ajax({
					url : BASE_URL + "/pos/getplaceoftreatmentautocomplete.htm",
					type : "GET",
					data : {
						tagName : request.term
					},

					dataType : "json",
					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.remarks,
								items : v,
							};

						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});
			},
			position : {
				my : "left bottom",
				at : "left top",
			},
			select : function(	e,
								ui) {

				$("#remarks").val(ui.item.label);

				// $("#prebilltd").removeClass("hide");
				// $("#prebilltext").removeClass("hide");
			},

		});
	} else {
		$("#codetextid").addClass("hide");
		$("#pregtextid").addClass("hide");
		$("#codevalid").addClass("hide");
		$("#pregvalid").addClass("hide");
	}
	$("input:text").focus(function() {
		$(this).select();
	});
	$("#cardExpMod").keyup(function() {
		if ($(this).val().length == 2) {
			if ($(this).val() <= 12 && $(this).val() > 0) {
				$(this).val($(this).val() + "/");
			} else {
				$(this).val("");
			}

		}
	});

	 $('#item_barcode').on('keydown', function(e) {
		if (e.which == 13) {
			e.preventDefault();
			var barcode = $('#item_barcode').val();
			autoadd_barcodeflag=1;
			getItemDetailsByBarcode(barcode);
		}
	});
	$("#cardFourDigitMod").keyup(function() {
		if (isNaN($("#cardFourDigitMod").val())) {
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Four Digit.";
			$(this).focus();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}
	});
	
	var trId=$("#stocktransIdshow").val();
	if(trId==0){
	var currentDate = new Date();
	$('#duedate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});
	
	
	var today = new Date();
	today.setDate(today.getDate()+parseInt($("#noOfDue").val()));
	var yyyy = today.getFullYear().toString();
    var mm = (today.getMonth()+1).toString(); // getMonth() is zero-based
    var dd  = today.getDate().toString();
	
	
	
	
		$("#duedate").val(yyyy+"-"+(mm[1]?mm:"0"+mm[0])+"-"+(dd[1]?dd:"0"+dd[0]));
		}
	else
		{
		
		 $("#duedate").prop("readonly", true);
		}

	
	$('#pissuedt').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});
	var itemcount = 0;
	$('#selitem tbody tr').each(function() {
		itemcount++;
	});
	$("#totitmcount").text(itemcount);
	if (itemcount == 0) {
		$("#payButtId").addClass('disabled');
		$("#delButtId").addClass('disabled');
		$("#holdButtId").addClass('disabled');
	} else {
		$("#payButtId").removeClass("disabled");
		$("#delButtId").removeClass("disabled");
		$("#holdButtId").removeClass("disabled");
	}

	if ($('input[name=payOption]').is(":checked")) {
		// console.log($("input[name=payOption]:checked").val());
		var chkval = $("input[name=payOption]:checked").val();
		// console.log(chkval);
		if (chkval == 1 || chkval == 3) {
			$("#cardDiv").addClass("hide");
		}
	}
	if ($("#tenderamt").val() > 0) {
		// alert($("#payretadjamt").val());
		// $("#refundAmt").val(parseFloat($("#tenderamt").val()-$("#nettot").val()).toFixed(2));
		$("#refundAmt").val(parseFloat($("#tenderamt").val() - $("#cashAmt").val()).toFixed(2));
	} else {
		$("#refundAmt").val(parseFloat(0).toFixed(2));
	}

	$("#salediscount").keyup(function() {
		if (isNaN($("#salediscount").val())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in D%";
			$(this).focus();
			return false;
		} else {
			$("#item_dis").val(parseFloat($("#salediscount").val()).toFixed(4));
			document.getElementById('alertMsg').innerHTML = "";
		}
	});
	// item_packqty change calculation
	$("#item_pqty").blur(function() {
		var pqty = $(this).val();


		var item_is_slno = $("#item_is_slno").val();
		var itemid = $("#item_id").val();
		 if(itemid==""){
			itemid=$("#itemid").val();
		}

		if(item_is_slno==1){

			for ( var i = 1; i <= 30; i++) {
				$("#checkbox"+i).attr('checked', false);
			}
			/*
			 * var slnoschk=$('#selitem tr#' +
			 * itemid).find('#saletabitemchkslnosid').text();
			 * 
			 * var slnoschked=slnoschk.split(",");
			 * alert("slnoschked="+slnoschked); for(var i=0;i<slnoschked.length;i++){
			 * $("#"+slnoschked[i]).prop("checked",true); }
			 */

			// edit serial no.
				var slnos=$("#selitem tr#" + itemid).find("#saletabitemslnos").text();
			    var sno=slnos.split(",");
			//
			var CommResultsetObj = {};
			CommResultsetObj.itemId = itemid.split("_")[0];
			CommResultsetObj.saleId = $("#saleId").val();
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/stock/getserialnobyitemid.htm", CommResultsetObj, function(response) {
				// console.log("response=" + response);
				var itemserialnodets = JSON.parse(response);
				if($.isEmptyObject(itemserialnodets)){
					$("#currStkGraterModal").modal("show");
				}else{

					if(itemserialnodets.length>0){
						for ( var i = 0; i < itemserialnodets.length; i++) {
							var itemserialnodet = itemserialnodets[i];
							$("#textbox"+(i+1)).val(itemserialnodet.uniqueIdentifierNo);
							$("#slrate"+(i+1)).val(itemserialnodet.saleRate);
							for(var m=0;m<sno.length;m++){
								// alert("slnos="+sno[i]);
								if(sno[m]==itemserialnodet.uniqueIdentifierNo){
									$("#checkbox"+(i+1)).prop("checked",true);
								}

							}

							/*
							 * if(jQuery.inArray(itemserialnodet.uniqueIdentifierNo,
							 * slnos) !== -1){ console.log("is in array");
							 * $("#checkbox"+(i+1)).prop("checked",true); }
							 */
							$("#TextBoxDiv_"+(i+1)).removeClass("hide");
							// $("#checkbox"+(i+1)).attr('checked', true);
						}
						var itmdetlen=itemserialnodets.length;
						for ( var i = itmdetlen; i < 30-itmdetlen; i++) {
							$("#TextBoxDiv_"+(i+1)).addClass("hide");
						}
						$("#openSerialModal").modal("show");
					 }
				}

			});

		}else{
			calculateDefaultvalue();
			/*
			 * var lqty = $("#item_lqty").val(); var item_vat =
			 * $("#item_vat").val(); var item_tax = $("#item_tax").val(); var
			 * item_taxPercentage = $("#item_taxPercentage").val(); var item_dis =
			 * $("#item_dis").val(); // var item_rate_ls = $("#item_mrp").val();
			 * var item_rate_ls = $("#item_rate_ls").val(); var conv =
			 * $("#item_conv").val(); // for current stock cal var itemid =
			 * $("#item_id").val(); var itembatch = $("#item_batch").val(); var
			 * itemexp = $("#item_exp").val(); var itemmrppack =
			 * $("#item_mrp_pack").val(); var itemexpdate =
			 * ($("#item_expDate").val().trim() == "")? "NA" :
			 * $("#item_expDate").val(); var item_dual_stock=
			 * $("#item_dualStockRequired_req").val(); //console.log("Case#1
			 * itemexp=" + itemexp + " & itemexpdate=" + itemexpdate);
			 * 
			 * if (item_dual_stock==1) { // item_rate_ls = item_salerate_ls; var
			 * totlqty = parseFloat(pqty) ;//+ parseFloat(Number(lqty)); }else {
			 * item_rate_ls = item_rate_ls / conv; var totlqty = (pqty * conv) +
			 * Number(lqty); }
			 * 
			 * 
			 * 
			 * 
			 * var CommResultsetObj = {}; CommResultsetObj.itemId =
			 * itemid.split("_")[0]; CommResultsetObj.batchNo = itembatch;
			 * CommResultsetObj.expiryDateFormat = itemexp; CommResultsetObj.mrp =
			 * itemmrppack; CommResultsetObj.expDate = itemexpdate;
			 * CommResultsetObj.saleId = $("#saleId").val();
			 * //console.log("Case#2 itemexp=" + itemexp + " & itemexpdate=" +
			 * itemexpdate);
			 * 
			 * var ajaxCallObject = new CustomBrowserXMLObject();
			 * ajaxCallObject.callAjaxPost(BASE_URL +
			 * "/stock/getcurrstockbybatexpmrp.htm", CommResultsetObj,
			 * function(response) { //console.log("response=" + response); var
			 * itemcurstkdets = JSON.parse(response);
			 * //alert("itemcurstkdets="+itemcurstkdets);
			 * if($.isEmptyObject(itemcurstkdets)){
			 * $("#currStkGraterModal").modal("show");
			 * $("#currstkofitm").text(0); $("#curstkcheck").val(1); }else{ for (
			 * var i = 0; i < itemcurstkdets.length; i++) { var itemcurstkdet =
			 * itemcurstkdets[i]; //console.log("response=" +
			 * itemcurstkdet.calculateLooseQty); var calculateLooseQty =
			 * itemcurstkdet.calculateLooseQty; if (totlqty > calculateLooseQty) {
			 * $("#currStkGraterModal").modal("show");
			 * $("#currstkofitm").text(itemcurstkdet.stockQty);
			 * $("#curstkcheck").val(1); } } }
			 * 
			 * }); // var item_rate_ls = $("#item_rate_ls_hid").val();
			 * 
			 * var total = 0; var discamt = 0; var vatamt = 0; var taxamt = 0;
			 * var gstamt = 0;
			 * 
			 * discamt = item_rate_ls * item_dis / 100;
			 * $("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4));
			 * var rate = item_rate_ls - discamt;
			 * $("#item_rate_ls").val(parseFloat(rate).toFixed(4)); total =
			 * parseFloat(totlqty * rate).toFixed(4); total = parseFloat(totlqty *
			 * item_rate_ls).toFixed(4); discamt = total * item_dis / 100;
			 * $("#item_discamt").val(parseFloat(discamt).toFixed(4)); total =
			 * total - discamt;
			 * $("#item_tot").val(parseFloat(total).toFixed(4));
			 * $("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4));
			 * 
			 * vatamt = parseFloat(total * item_vat / 100).toFixed(4);
			 * $("#item_vatamt").val(parseFloat(vatamt).toFixed(4)); taxamt =
			 * parseFloat(total * item_tax / 100).toFixed(4);
			 * $("#item_taxamt").val(parseFloat(taxamt).toFixed(4)); gstamt =
			 * parseFloat(total * item_taxPercentage / 100).toFixed(4);
			 * $("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
			 * 
			 * for store var isexclu = $("#isexclusive").val();
			 * 
			 * var isexclu = $("#item_taxtype").val();
			 * 
			 * if (isexclu == 2) { $("#item_tot").val(parseFloat(Number(total) +
			 * Number(gstamt)).toFixed(4)); }
			 * 
			 * var item_free = $("#item_free").val();
			 * 
			 * 
			 * for floor value physical quantity
			 * 
			 * var temp_free=Math.floor(item_free); item_free = item_free -
			 * temp_free; // LtAdj calculation start if(((item_free % 1)!=0) ||
			 * item_free<1) {
			 * 
			 * var totalret = parseFloat(totlqty * item_rate_ls).toFixed(4); var
			 * modFree = item_free % 1; var pqtyPerFree = pqty/modFree; ltadj =
			 * totalret/pqtyPerFree;
			 * $("#item_ltadj").val(parseFloat(ltadj).toFixed(4)); // LtAdj
			 * calculation end discamt = (totalret-ltadj) * item_dis / 100;
			 * $("#item_discamt").val(parseFloat(discamt).toFixed(4));
			 * 
			 * gstamt = parseFloat((totalret-ltadj-discamt) * item_taxPercentage /
			 * 100).toFixed(4);
			 * $("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4)); }
			 */
		}

	});

	// item_looseqty change calculation
	$("#item_lqty").keyup(function() {
		var lqty = $(this).val();
		var pqty = $("#item_pqty").val();
		var item_vat = $("#item_vat").val();
		var item_tax = $("#item_tax").val();
		var item_taxPercentage = $("#item_taxPercentage").val();
		var item_dis = $("#item_dis").val();
		// var item_rate_ls = $("#item_rate_ls_hid").val();
		// var item_rate_ls = $("#item_mrp").val();
		var item_rate_ls = $("#item_rate_ls").val();
		var conv = $("#item_conv").val();
		var totlqty = (pqty * conv) + Number(lqty);
		// for current stock cal
		var itemid = $("#item_id").val();
		var itembatch = $("#item_batch").val();
		var itemexp = $("#item_exp").val();
		var itemmrppack = $("#item_mrp_pack").val();
		var itemexpdate = ($("#item_expDate").val().trim() == "")? "NA" : $("#item_expDate").val() ;

		var CommResultsetObj = {};
		CommResultsetObj.itemId = itemid.split("_")[0];
		CommResultsetObj.batchNo = itembatch;
		CommResultsetObj.expiryDateFormat = itemexp;
		CommResultsetObj.mrp = itemmrppack;
		CommResultsetObj.expDate = itemexpdate;
		CommResultsetObj.saleId = $("#saleId").val();

		/*
		 * var ajaxCallObject = new CustomBrowserXMLObject();
		 * ajaxCallObject.callAjaxPost(BASE_URL +
		 * "/stock/getcurrstockbybatexpmrp.htm", CommResultsetObj,
		 * function(response) { // console.log("response=" + response); var
		 * itemcurstkdets = JSON.parse(response); for ( var i = 0; i <
		 * itemcurstkdets.length; i++) { var itemcurstkdet = itemcurstkdets[i]; //
		 * console.log("response=" + itemcurstkdet.calculateLooseQty); var
		 * calculateLooseQty = itemcurstkdet.calculateLooseQty; if (totlqty >
		 * calculateLooseQty) { $("#currStkGraterModal").modal("show");
		 * $("#currstkofitm").text(itemcurstkdet.stockQty); } } });
		 */
		// for current stock cal end
		var total = 0;
		var discamt = 0;
		var vatamt = 0;
		var taxamt = 0;
		var gstamt = 0;

		/*
		 * discamt = item_rate_ls * item_dis / 100;
		 * $("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4)); var
		 * rate = item_rate_ls - discamt;
		 * $("#item_rate_ls").val(parseFloat(rate).toFixed(4)); total =
		 * parseFloat(totlqty * rate).toFixed(4);
		 */
		total = parseFloat(totlqty * item_rate_ls).toFixed(4);
		discamt = total * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt).toFixed(4));
		total = total - discamt;
		$("#item_tot").val(parseFloat(total).toFixed(4));
		$("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4));
		vatamt = parseFloat(total * item_vat / 100).toFixed(4);
		$("#item_vatamt").val(parseFloat(vatamt).toFixed(4));
		taxamt = parseFloat(total * item_tax / 100).toFixed(4);
		$("#item_taxamt").val(parseFloat(taxamt).toFixed(4));
		gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
		$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
		var isexclu = $("#isexclusive").val();
		if (isexclu == 1) {
			$("#item_tot").val(parseFloat(Number(total) + Number(gstamt)).toFixed(4));
		}
	});

	// item_discount change calculation
	$("#item_dis").keyup(function() {
		var item_dis = $(this).val();
		var item_maxdisper = $("#item_maxDiscountLimit").val();
		var item_taxPercentage = $("#item_taxPercentage").val();
		if (Number(item_dis) > Number(item_maxdisper)) {
			$("#itemmaxdisperspan").text(parseFloat(item_maxdisper).toFixed(2));
			$("#item_dis").val(parseFloat(0).toFixed(4));
			$("#itemMaxDisModal").modal("show");
		}
		calculateDefaultvalue();
	/*
	 * var pqty = $("#item_pqty").val(); var item_vat = $("#item_vat").val();
	 * var item_tax = $("#item_tax").val(); var lqty = $("#item_lqty").val(); //
	 * var item_rate_ls = $("#item_rate_ls_hid").val(); // var item_rate_ls =
	 * $("#item_mrp").val(); var item_rate_ls = $("#item_rate_ls").val(); var
	 * conv = $("#item_conv").val(); var item_dual_stock=
	 * $("#item_dualStockRequired_req").val(); var total = 0; var discamt = 0;
	 * var vatamt = 0; var taxamt = 0; var gstamt = 0;
	 * 
	 * 
	 * if (item_dual_stock==1) { //var item_rate_ls = item_salerate_ls; var
	 * totlqty = parseFloat(pqty) ;//+ parseFloat(Number(lqty)); }else {
	 * item_rate_ls = item_rate_ls / conv; var totlqty = (pqty * conv) +
	 * Number(lqty); }
	 * 
	 * discamt = item_rate_ls * item_dis / 100;
	 * $("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4)); var
	 * rate = item_rate_ls - discamt;
	 * $("#item_rate_ls").val(parseFloat(rate).toFixed(4)); total =
	 * parseFloat(totlqty * rate).toFixed(4); total = parseFloat(totlqty *
	 * item_rate_ls).toFixed(4); discamt = total * item_dis / 100;
	 * $("#item_discamt").val(parseFloat(discamt).toFixed(4)); total = total -
	 * discamt; $("#item_tot").val(parseFloat(total).toFixed(4));
	 * $("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4));
	 * 
	 * vatamt = parseFloat(total * item_vat / 100).toFixed(4);
	 * $("#item_vatamt").val(parseFloat(vatamt).toFixed(4)); taxamt =
	 * parseFloat(total * item_tax / 100).toFixed(4);
	 * $("#item_taxamt").val(parseFloat(taxamt).toFixed(4)); gstamt =
	 * parseFloat(total * item_taxPercentage / 100).toFixed(4);
	 * $("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4)); //var isexclu =
	 * $("#isexclusive").val(); var isexclu = $("#item_taxtype").val(); if
	 * (isexclu == 2) { $("#item_tot").val(parseFloat(Number(total) +
	 * Number(gstamt)).toFixed(4)); } var item_free = $("#item_free").val();
	 * 
	 * 
	 * for floor value physical quantity
	 * 
	 * var temp_free=Math.floor(item_free); item_free = item_free - temp_free; //
	 * LtAdj calculation start if(((item_free % 1)!=0) || item_free<1) { var
	 * totalret = parseFloat(totlqty * item_rate_ls).toFixed(4); var modFree =
	 * item_free % 1; var pqtyPerFree = pqty/modFree; ltadj =
	 * totalret/pqtyPerFree; $("#item_ltadj").val(parseFloat(ltadj).toFixed(4)); //
	 * LtAdj calculation end discamt = (totalret-ltadj) * item_dis / 100;
	 * $("#item_discamt").val(parseFloat(discamt).toFixed(4));
	 * 
	 * gstamt = parseFloat((totalret-ltadj-discamt) * item_taxPercentage /
	 * 100).toFixed(4);
	 * $("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4)); }
	 */
	});

	// item_scheme_discount change calculation
	$("#item_sc_dis").keyup(function() {
		var item_sc_dis = $(this).val();
		var item_taxPercentage = $("#item_taxPercentage").val();

		var pqty = $("#item_pqty").val();
		var item_vat = $("#item_vat").val();
		var item_tax = $("#item_tax").val();
		var lqty = $("#item_lqty").val();
		// var item_rate_ls = $("#item_rate_ls_hid").val();
		// var item_rate_ls = $("#item_mrp").val();
		var item_rate_ls = $("#item_rate_ls").val();
		var conv = $("#item_conv").val();
		var total = 0;
		var discamt = 0;
		var vatamt = 0;
		var taxamt = 0;
		var gstamt = 0;

		var totlqty = (pqty * conv) + Number(lqty);

		total = parseFloat(totlqty * item_rate_ls).toFixed(4);
		schemediscamt = total * item_sc_dis / 100;
		$("#item_sc_dis").val(parseFloat(schemediscamt).toFixed(4));
		total = total - schemediscamt;
		$("#item_tot").val(parseFloat(total).toFixed(4));
		$("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4));

		vatamt = parseFloat(total * item_vat / 100).toFixed(4);
		$("#item_vatamt").val(parseFloat(vatamt).toFixed(4));
		taxamt = parseFloat(total * item_tax / 100).toFixed(4);
		$("#item_taxamt").val(parseFloat(taxamt).toFixed(4));
		gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
		$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
		var isexclu = $("#isexclusive").val();
		if (isexclu == 1) {
			$("#item_tot").val(parseFloat(Number(total) + Number(gstamt)).toFixed(4));
		}
		var item_free = $("#item_free").val();

		/*
		 * for floor value physical quantity
		 */
		 var temp_free=Math.floor(item_free);
		 item_free = item_free - temp_free;
		// LtAdj calculation start
		if(((item_free % 1)!=0) || item_free<1)
		{
			var totalret = parseFloat(totlqty * item_rate_ls).toFixed(4);
			var modFree =  item_free % 1;
			var pqtyPerFree = pqty/modFree;
			ltadj = totalret/pqtyPerFree;
			$("#item_ltadj").val(parseFloat(ltadj).toFixed(4));
			// LtAdj calculation end
			discamt = (totalret-ltadj) * item_dis / 100;
			$("#item_discamt").val(parseFloat(discamt).toFixed(4));

			gstamt = parseFloat((totalret-ltadj-discamt) * item_taxPercentage / 100).toFixed(4);
			$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
		}
	});

	// item_sale_rate change calculation
	$("#item_sale_rate").keyup(function() {
		var item_salerate = $(this).val();
		var isexclu = $("#isexclusive").val();
		var itemmrppack = $("#item_mrp_pack").val();
		var item_taxPercentage = $("#item_taxPercentage").val();
		var item_free = $("#item_free").val();
	/*
	 * if (isexclu == 1) { var mop = (100 * itemmrppack) / (100 +
	 * Number(item_taxPercentage)); if (item_salerate > mop) {
	 * $("#item_sale_rate").val(parseFloat(mop).toFixed(4)); item_salerate =
	 * mop; } } else { if (item_salerate > itemmrppack) {
	 * $("#item_sale_rate").val(parseFloat(itemmrppack).toFixed(4));
	 * item_salerate = itemmrppack; } else { } }
	 */

		var item_dis = $("#item_dis").val();
		var pqty = $("#item_pqty").val();
		var item_vat = $("#item_vat").val();
		var item_tax = $("#item_tax").val();
		var lqty = $("#item_lqty").val();
		// var item_rate_ls = $("#item_rate_ls_hid").val();
		// var item_rate_ls = $("#item_mrp").val();
		var conv = $("#item_conv").val();
		var item_rate_ls = item_salerate / conv;
		var total = 0;
		var discamt = 0;
		var vatamt = 0;
		var taxamt = 0;
		var gstamt = 0;

		var totlqty = (pqty * conv) + Number(lqty);
		/*
		 * discamt = item_rate_ls * item_dis / 100;
		 * $("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4)); var
		 * rate = item_rate_ls - discamt;
		 * $("#item_rate_ls").val(parseFloat(rate).toFixed(4)); total =
		 * parseFloat(totlqty * rate).toFixed(4);
		 */
		total = parseFloat(totlqty * item_rate_ls).toFixed(4);
		discamt = total * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt).toFixed(4));
		total = total - discamt;
		$("#item_tot").val(parseFloat(total).toFixed(4));
		$("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4));
		vatamt = parseFloat(total * item_vat / 100).toFixed(4);
		$("#item_vatamt").val(parseFloat(vatamt).toFixed(4));
		taxamt = parseFloat(total * item_tax / 100).toFixed(4);
		$("#item_taxamt").val(parseFloat(taxamt).toFixed(4));
		gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
		$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));

		if (isexclu == 1) {
			$("#item_tot").val(parseFloat(Number(total) + Number(gstamt)).toFixed(4));
		}

		/*
		 * for floor value physical quantity
		 */
		 var temp_free=Math.floor(item_free);
		 item_free = item_free - temp_free;
		// LtAdj calculation start
		if(((item_free % 1)!=0) || item_free<1)
		{

			var totalret = parseFloat(totlqty * item_rate_ls).toFixed(4);
			var modFree =  item_free % 1;
			var pqtyPerFree = pqty/modFree;
			ltadj = totalret/pqtyPerFree;
			$("#item_ltadj").val(parseFloat(ltadj).toFixed(4));
			// LtAdj calculation end
			discamt = (totalret-ltadj) * item_dis / 100;
			$("#item_discamt").val(parseFloat(discamt).toFixed(4));

			gstamt = parseFloat((totalret-ltadj-discamt) * item_taxPercentage / 100).toFixed(4);
			$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
		}
	});
	// item_rate_ls change calculation
	// $("#item_rate_ls").keyup(function() {
	$("#item_rate_ls").blur(function() {
		calculateDefaultvalue();
		/*
		 * var item_salerate_ls = $(this).val(); //var isexclu =
		 * $("#isexclusive").val();
		 * 
		 * var isexclu = $("#item_taxtype").val(); var itemmrppack =
		 * $("#item_mrp_pack").val(); var item_taxPercentage =
		 * $("#item_taxPercentage").val(); var item_free =
		 * $("#item_free").val(); var item_dis = $("#item_dis").val(); var pqty =
		 * $("#item_pqty").val(); var item_vat = $("#item_vat").val(); var
		 * item_tax = $("#item_tax").val(); var lqty = $("#item_lqty").val();
		 * var item_dual_stock= $("#item_dualStockRequired_req").val(); // var
		 * item_rate_ls = $("#item_rate_ls_hid").val(); //var item_rate_ls =
		 * $("#item_mrp").val(); var conv = $("#item_conv").val(); var total =
		 * 0; var discamt = 0; var vatamt = 0; var taxamt = 0; var gstamt = 0;
		 * //for dual stock calculation if (item_dual_stock==1) { var
		 * item_rate_ls = item_salerate_ls; var totlqty = parseFloat(pqty);// +
		 * parseFloat(Number(lqty)); }else { var item_rate_ls = item_salerate_ls /
		 * conv; var totlqty = (pqty * conv) + Number(lqty); }
		 * 
		 * 
		 * 
		 * 
		 * discamt = item_rate_ls * item_dis / 100;
		 * $("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4)); var
		 * rate = item_rate_ls - discamt;
		 * $("#item_rate_ls").val(parseFloat(rate).toFixed(4)); total =
		 * parseFloat(totlqty * rate).toFixed(4); total = parseFloat(totlqty *
		 * item_rate_ls).toFixed(4); discamt = total * item_dis / 100;
		 * $("#item_discamt").val(parseFloat(discamt).toFixed(4)); total = total -
		 * discamt; $("#item_tot").val(parseFloat(total).toFixed(4));
		 * $("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4)); vatamt =
		 * parseFloat(total * item_vat / 100).toFixed(4);
		 * $("#item_vatamt").val(parseFloat(vatamt).toFixed(4)); taxamt =
		 * parseFloat(total * item_tax / 100).toFixed(4);
		 * $("#item_taxamt").val(parseFloat(taxamt).toFixed(4)); gstamt =
		 * parseFloat(total * item_taxPercentage / 100).toFixed(4);
		 * $("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
		 * 
		 * if (isexclu == 2) { $("#item_tot").val(parseFloat(Number(total) +
		 * Number(gstamt)).toFixed(4)); }
		 * 
		 * 
		 * for floor value physical quantity
		 * 
		 * var temp_free=Math.floor(item_free); item_free = item_free -
		 * temp_free; // LtAdj calculation start if(((item_free % 1)!=0) ||
		 * item_free<1) {
		 * 
		 * var totalret = parseFloat(totlqty * item_rate_ls).toFixed(4); var
		 * modFree = item_free % 1; var pqtyPerFree = pqty/modFree; ltadj =
		 * totalret/pqtyPerFree;
		 * $("#item_ltadj").val(parseFloat(ltadj).toFixed(4)); // LtAdj
		 * calculation end discamt = (totalret-ltadj) * item_dis / 100;
		 * $("#item_discamt").val(parseFloat(discamt).toFixed(4));
		 * 
		 * gstamt = parseFloat((totalret-ltadj-discamt) * item_taxPercentage /
		 * 100).toFixed(4);
		 * $("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4)); }
		 */
	});
	// item_free change calculation
	$("#item_free").keyup(function() {
		calculateDefaultvalue();
		/*
		 * var item_dual_stock= $("#item_dualStockRequired_req").val();
		 * $("#item_ltadj").val(0.0); var item_free = $(this).val(); var
		 * item_taxPercentage = $("#item_taxPercentage").val(); var itemmrppack =
		 * $("#item_mrp_pack").val(); //var item_sale_rate
		 * =$("#item_sale_rate").val(); var item_sale_rate
		 * =$("#item_rate_ls").val(); var item_dis = $("#item_dis").val(); var
		 * pqty = $("#item_pqty").val(); var conv = $("#item_conv").val(); var
		 * lqty = $("#item_lqty").val(); var disval = 0.0; var taxval = 0.0; var
		 * ltadj = 0.0; var total=0.0; var gstamt=0.0; if (item_sale_rate == "") {
		 * item_sale_rate = 0; } if (item_free == "") { item_free = 0; } //for
		 * dual stock calculation if (item_dual_stock==1) {
		 * 
		 * pqty = parseFloat(pqty);// + parseFloat(Number(lqty)); }else {
		 * item_sale_rate = item_sale_rate / conv; pqty = (pqty * conv) +
		 * Number(lqty); }
		 * 
		 * 
		 * total = parseFloat(pqty * item_sale_rate).toFixed(4); discamt = total *
		 * item_dis / 100;
		 * $("#item_discamt").val(parseFloat(discamt).toFixed(4));
		 * 
		 * gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
		 * $("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
		 * 
		 * 
		 * //var isexclu = $("#isexclusive").val(); var isexclu =
		 * $("#item_taxtype").val(); if (isexclu == 2) {
		 * $("#item_tot").val(parseFloat(Number(total) +
		 * Number(gstamt)).toFixed(4)); }
		 * 
		 * 
		 * for floor value physical quantity
		 * 
		 * var temp_free=Math.floor(item_free); item_free = item_free -
		 * temp_free;
		 * 
		 * 
		 * if(((item_free % 1)!=0) || item_free<1) { // LtAdj calculation start
		 * var totalret = parseFloat(pqty * item_sale_rate).toFixed(4); var
		 * modFree = item_free % 1; var pqtyPerFree = pqty/modFree; ltadj =
		 * totalret/pqtyPerFree;
		 * $("#item_ltadj").val(parseFloat(ltadj).toFixed(4)); // LtAdj
		 * calculation end discamt = (totalret-ltadj) * item_dis / 100;
		 * $("#item_discamt").val(parseFloat(discamt).toFixed(4));
		 * 
		 * gstamt = parseFloat((totalret-ltadj-discamt) * item_taxPercentage /
		 * 100).toFixed(4);
		 * $("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4)); }
		 */
	});

	var pqty_field = $("#pqty_label").text();

	var lqty_field = $("#lqty_label").text();

	$("#item_pqty").keyup(function() {
		if (isNaN($("#item_pqty").val())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in " + pqty_field;
			$(this).focus();
			return false;
		} else {
			if ($("#item_pqty").val() < 0) {
				document.getElementById('alertMsg').innerHTML = pqty_field + " " + getFieldText.checkNegative;
				$(this).focus();
				return false;
			} else {
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
	});

	$("#item_lqty").keyup(function() {
		if (isNaN($("#item_lqty").val())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in " + lqty_field;
			$(this).focus();
			return false;
		} else {
			if ($("#item_lqty").val() < 0) {
				document.getElementById('alertMsg').innerHTML = lqty_field + " " + getFieldText.checkNegative;
				$(this).focus();
				return false;
			} else {
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
	});
	
	$("#tenderamt").keyup(function(e) {
		AmtChange(e);
	});
	
	$("#cashAmt").keyup(function(e) {
		AmtChange(e);
	});
	
	$("#cardAmt").keyup(function(e) {
		AmtChange(e);
	});
	
	function AmtChange(e) {
		/*
		 * if (isNaN($("#"+e.target.id.toString()).val())) { //
		 * $("#confirmval").val(0);
		 * document.getElementById('alertmessagecont').innerHTML =
		 * getFieldText.numberCheck + " in Cash Amount"; // showConfirmModal();
		 * $(this).focus(); return false; } else {
		 */
			if ($("#"+e.target.id.toString()).val() == '') {
				$("#"+e.target.id.toString()).val(0);
			}
			document.getElementById('alertmessagecont').innerHTML = "";
			// changed for sale order advance
			var nettot = parseFloat($("#paymodnettot").val());
			// var nettot = $("#nettot").val();

			var cashamt = parseFloat($("#cashAmt").val());
			var tenderamt = parseFloat($("#tenderamt").val());
			var payretadjamt = parseFloat($("#payretadjamt").val());
			var cardAmt = parseFloat($("#cardAmt").val());
			var refundAmt = parseFloat($("#refundAmt").val());
			var creditAmt = parseFloat($("#creditAmt").val());
			
			nettot = nettot - Number(payretadjamt);
			
			if (cashamt == '') {
				cashamt = 0.00;
			}
			if (cardAmt == '') {
				cardAmt = 0.00;
			}
			if (tenderamt == '') {
				tenderamt = 0.00;
			}
			if (payretadjamt == '') {
				payretadjamt = 0.00;
			}
			
			if(e.target.id == "tenderamt") {
// alert("in tender amount");
				if (tenderamt > nettot) {
					cashamt = nettot;
					refundAmt = parseFloat(tenderamt - nettot);
					creditAmt = (parseFloat(0));
					cardAmt = (parseFloat(0));
				}
				else if(tenderamt < nettot) {
					cashamt = (parseFloat(tenderamt));
					refundAmt = (parseFloat(0));
					
					// here was credit customer
				} else if(tenderamt == nettot) {
					cashamt = tenderamt;
					refundAmt = (parseFloat(0));
					creditAmt = (parseFloat(0));
					cardAmt = (parseFloat(0));
				}
				
			}
			else if(e.target.id == "cashAmt") {
// alert("in cash amount");
				if(cashamt > tenderamt){
					tenderamt = cashamt;
					refundAmt = tenderamt - cashamt;
				} else if(cashamt<=tenderamt){
					refundAmt = tenderamt - cashamt;
				} 
				if(cashamt>nettot){
					tenderamt = cashamt;
					cashamt = nettot;
					refundAmt = tenderamt - cashamt;
					creditAmt = 0;
					cardAmt = 0;
				} else if(cashamt<nettot){
					if(cashamt>tenderamt){
						tenderamt = cashamt;
						refundAmt = tenderamt - cashamt;
					} else if(cashamt<=tenderamt){
						refundAmt = tenderamt - cashamt;
					}
				} else if(cashamt == nettot){
					cashamt = nettot;
					refundAmt = tenderamt - cashamt;
					creditAmt = 0;
					cardAmt = 0;
				}
			}
			else if(e.target.id == "cardAmt") {
// alert("in card amount");
				if (cardAmt < nettot) {
// var creditcustomer = $("#salecustid").val();
					var creditcustomer = $("#creditLimit").val();
					if(creditcustomer==0){
						// $("#carddiv").stop().show("slow");
						cashamt = (parseFloat(nettot - Number(cardAmt)));
						if(cashamt>tenderamt){
							tenderamt = cashamt;
						}
						refundAmt = tenderamt - cashamt;
						creditAmt = (parseFloat(0));
					}else{
						var totamt = cardAmt + cashamt;
						if(totamt < nettot) {
							creditAmt = (parseFloat(nettot - totamt));
						}
						else {
		// $("#carddiv").stop().hide("slow");
							creditAmt = (parseFloat(nettot - cardAmt));
							cashamt = (parseFloat(0));
							tenderamt = (parseFloat(0));
							refundAmt = (parseFloat(0));
						}
					}
				} else if (cardAmt >= nettot) {
					cardAmt = nettot;
					creditAmt = (parseFloat(0));
					cashamt = (parseFloat(0));
					tenderamt = (parseFloat(0));
					refundAmt = (parseFloat(0));
				}
			}

			if(e.target.id != "cardAmt") {
// var creditcustomer = $("#salecustid").val();
				var creditcustomer = $("#creditLimit").val();
				if(creditcustomer==0){
					// $("#carddiv").stop().show("slow");
					cardAmt = (parseFloat(nettot - Number(cashamt)));
					creditAmt = (parseFloat(0));
				}else{
					var totamt = cardAmt + cashamt;
					if(totamt < nettot) {
						creditAmt = (parseFloat(nettot - totamt));
					}
					else {
	// $("#carddiv").stop().hide("slow");
						creditAmt = (parseFloat(nettot - cashamt));
						cardAmt = (parseFloat(0));
					}
				}
			}
			
			if(e.target.id == "tenderamt") {
				tenderamt = Number(tenderamt);
			} else {
				tenderamt = parseFloat(tenderamt).toFixed(2);
			}
			if(e.target.id == "cashAmt") {
				cashamt = Number(cashamt);
			} else {
				cashamt = parseFloat(cashamt).toFixed(2);
			}
			if(e.target.id == "cardAmt") {
				cardAmt = Number(cardAmt);
			} else {
				cardAmt = parseFloat(cardAmt).toFixed(2);
			}
			$("#cashAmt").val(cashamt);
			$("#tenderamt").val(tenderamt);
			$("#cardAmt").val(cardAmt);
			$("#refundAmt").val(parseFloat(refundAmt).toFixed(2));
			$("#creditAmt").val(parseFloat(creditAmt).toFixed(2));
			
			if(cardAmt>0){
				$("#carddiv").stop().show("slow");
			} else {
				$("#carddiv").stop().hide("slow");
			}
// }
		
	}

	$("#salecustph").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/customer/getcustomerwithcreditlistautocomplete.htm",
					type : "GET",
					data : {
						tagName : request.term
					},

					dataType : "json",

					success : function(data) {
						if (!$.trim(data)) {
							$('#add_cust_td').removeClass("hide");
							$('.add_td').removeClass("hide");
							$('#blacktext_td').removeClass("hide");
							$('#black_td').removeClass("hide");
						} else {
							$('#add_cust_td').addClass("hide");
							$('.add_td').addClass("hide");
							$('#blacktext_td').addClass("hide");
							$('#black_td').addClass("hide");
						}
						response($.map(data, function(v) {
							return {
								label : v.phoneNo,
								itemCode : v.id,
								name : v.name,
								creditLimit : v.creditLimit,
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
			// console.log("items=" + ui.item.creditLimit);
			$("#salecustid").val(ui.item.itemCode);
			$("#salecustname").val(ui.item.name);
			$("#custCreditLimit").val(ui.item.creditLimit);
			$("#ecardno").val(ui.item.items.code);
			$("#custEmail").val(ui.item.items.email);// new added 10.5.2019
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				// e.target.value = "";
				$("#salecustid").val(0);
			// $("#salecustname").val("");
		},
	});

	$("#salecustname").autocomplete({
		source : function(	request,
							response) {
			// if (request.term.length >= 2) {
			$.ajax({
				url : BASE_URL + "/customer/getcustomerwithcreditlistautocompletebyname.htm",
				type : "GET",
				data : {
					tagName : request.term
				},

				dataType : "json",
				success : function(data) {
					if (!$.trim(data)) {
						$('#add_cust_td').removeClass("hide");
						$('.add_td').removeClass("hide");
						$('#blacktext_td').removeClass("hide");
						$('#black_td').removeClass("hide");
					} else {
						$('#add_cust_td').addClass("hide");
						$('.add_td').addClass("hide");
						$('#blacktext_td').addClass("hide");
						$('#black_td').addClass("hide");
					}
					response($.map(data, function(v) {
						return {
							label : v.name,
							itemCode : v.id,
							phno : v.phoneNo,
							creditLimit : v.creditLimit,
							items : v,
						};

					}));
				},
				error : function(error) {
					alert('error: ' + error);
				}
			});
			// }
		},
		select : function(	e,
							ui) {
            /* alert(JSON.stringify(ui)); */
			$("#salecustid").val(ui.item.itemCode);
			$("#salecustph").val(ui.item.phno);
			$("#custCreditLimit").val(ui.item.creditLimit);
			$("#ecardno").val(ui.item.items.code);
			$("#custEmail").val(ui.item.items.email);// new added 10.5.2019
			
			// $("#prebilltd").removeClass("hide");
			// $("#prebilltext").removeClass("hide");
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				// e.target.value = "";
				$("#salecustid").val(0);
			// $("#salecustph").val(0000000000);
		},
	});
	// Doctor autocomplete
	$("#saledocname").autocomplete({
		source : function(	request,
							response) {
			// if (request.term.length >= 2) {
			$.ajax({
				url : BASE_URL + "/invsetup/getdoctorlistautocompletebyname.htm",
				type : "GET",
				data : {
					tagName : request.term
				},

				dataType : "json",

				success : function(data) {
					if (!$.trim(data)) {
						$('#add_doc_td').removeClass("hide");
						// $('.add_td').removeClass("hide");
					} else {
						$('#add_doc_td').addClass("hide");
						// $('.add_td').addClass("hide");
					}
					response($.map(data, function(v) {
						return {
							label : v.name,
							itemCode : v.id,
							phno : v.phoneNo,
							items : v,
						};

					}));
				},
				error : function(error) {
					alert('error: ' + error);
				}
			});
			// }
		},
		select : function(	e,
							ui) {

			$("#saledocid").val(ui.item.itemCode);
			// $("#salecustph").val(ui.item.phno);
		},
		change : function(	e,
							ui) {
			if (!(ui.item)) {
				// e.target.value = "";
				$("#saledocid").val(0);
			}
		},
	});

	/*
	 * $("#item_name").autocomplete({ source : function( request, response) { if
	 * (request.term.length >= 2) {
	 * 
	 * $.ajax({ url : BASE_URL + "/item/autocompleteitem.htm", type : "GET",
	 * data : { tagName : request.term }, dataType : "json", success :
	 * function(data) { console.log(JSON.stringify(data)); response($.map(data,
	 * function(v) { return { label : v.itemName, itemCode : v.itemId, //
	 * tagCode : v.tagCode items : v, };
	 * 
	 * })); }, error : function(error) { alert('error: ' + error); } }); } },
	 * select : function( e, ui) {
	 *  // console.log(ui.item.itemCode); // console.log(ui.item.label); //
	 * console.log(ui.item.items); $("#itemid").val(ui.item.itemCode);
	 * $("#modmanufname").text(ui.item.items.manufacturerName);
	 * $("#modcontentname").text(ui.item.items.contentName);
	 * $("#modrackname").text(ui.item.items.schdName);
	 * $("#modgroupname").text(ui.item.items.groupName);
	 * $("#moditemnote").text(""); var seslocid=$("#seslocationid").val();
	 * 
	 * $("#location").val(seslocid); autoadd_barcodeflag=0;
	 *  // $("#salesman_id").val(0.0); // $("#salesman_per").val(0.0); //
	 * $("#salesman_amt").val(0.0);
	 *  // call new ajax for item details
	 * 
	 * 
	 * getItemDetails(ui.item.itemCode); // call new ajax end
	 *  }, change : function( e, ui) { if (!(ui.item)) e.target.value = ""; },
	 * });
	 */

	$("#itemContent").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 1) {
				$.ajax({
					url : BASE_URL + "/content/autocompleteitemcontent.htm",
					type : "GET",
					data : {
						tagName : request.term
					},

					dataType : "json",

					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.name,
								itemCode : v.id,
								// tagCode : v.tagCode
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
			// console.log(ui.item.itemCode)
			// console.log(ui.item.label)
			$("#content_id").val(ui.item.itemCode);
			$("#content_Dets").val(ui.item.label);

			getItemByContent(ui.item.itemCode);
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});

	$("#item_mfg").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/manufacturer/autocompleteitemmanufacturer.htm",
					type : "GET",
					data : {
						tagName : request.term
					},

					dataType : "json",

					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.name,
								itemCode : v.id,
								// tagCode : v.tagCode
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
			// console.log(ui.item.itemCode)
			// console.log(ui.item.label)
			$("#manu_id").val(ui.item.itemCode);
			// $("#manu_dets").val(ui.item.label);

			getItemByManufacture(ui.item.itemCode);
		},
		change : function(e,ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});
	
	/* New Added 7.5.2019 */
	$("#item_code").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/item/autocompleteitembycode.htm",
					type : "GET",
					data : {
						tagName : request.term
					},
					dataType : "json",
					success : function(data) {
						/* console.log(JSON.stringify(data)); */
						response($.map(data, function(v) {
							return {
								label : v.itemCode,
								itemCode : v.itemId,
								// tagCode : v.tagCode
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
			// console.log(ui.item.itemCode);
			// console.log(ui.item.label);
			// console.log(ui.item.items);
			// alert(ui.item.itemCode);
			$("#itemid").val(ui.item.itemCode);
			$("#modmanufname").text(ui.item.items.manufacturerName);
			$("#modcontentname").text(ui.item.items.contentName);
			$("#modrackname").text(ui.item.items.schdName);
			$("#modgroupname").text(ui.item.items.groupName);
			$("#moditemnote").text("");
			var seslocid=$("#seslocationid").val();

			$("#location").val(seslocid);
			autoadd_barcodeflag=0;

			// $("#salesman_id").val(0.0);
			 // $("#salesman_per").val(0.0);
			 // $("#salesman_amt").val(0.0);

			// call new ajax for item details
			getItemDetails(ui.item.itemCode);
			// call new ajax end

		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});
});

/*
 * ===================== end ready function ====================
 */
function getItemDetailsByBarcode(barcode) {
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/stock/getcurrstocksku/" + barcode + ".htm", function(resp) {
		// console.log(resp);
		var stkdet = JSON.parse(resp);
		if ($.isEmptyObject(stkdet)) {
			$("#inputbarcode").text(barcode);
			$("#noItemBarcodeModal").modal("show");
		} else {
			/*
			 * createItemStockDetails(resp, stkdet[0].itemId);
			 * $("#stkdetModal").modal("show");
			 */
			/*
			 * when no stock
			 */
			if(stkdet.length==1){
				for ( var i = 0; i < stkdet.length; i++) {
					var itmstkdet = stkdet[i];
					// console.log(JSON.stringify(itmstkdet));
				getClickeditmdet(itmstkdet);
				}
			}else{// when stock
				createItemStockDetails(resp,  stkdet[0].itemId);
				$("#stkdetModal").modal("show");
			}
		}

	}, null);
}
function getesitype() {
	var etype = $("#esitypeid").val();

	// alert(remarks);
	// alert(etype);
	if (etype == 1) {
		$("#codetextid").removeClass("hide");
		$("#pregtextid").removeClass("hide");
		$("#codevalid").removeClass("hide");
		$("#pregvalid").removeClass("hide");
		$("#remarkstext").text(remarks);
	} else if (etype == 3) {
		$("#codetextid").addClass("hide");
		$("#pregtextid").addClass("hide");
		$("#codevalid").addClass("hide");
		$("#pregvalid").addClass("hide");
		$("#remarkstext").text("Place of Treatment");
		$("#remarks").autocomplete({
			source : function(	request,
								response) {

				$.ajax({
					url : BASE_URL + "/pos/getplaceoftreatmentautocomplete.htm",
					type : "GET",
					cache : false,
					data : {
						tagName : request.term
					},

					dataType : "json",
					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.remarks,
								items : v,
							};
						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});

			},
			position : {
				my : "left bottom",
				at : "left top",
			},
			select : function(	e,
								ui) {

				$("#remarks").val(ui.item.label);

			},

		});
	} else {
		$("#codetextid").addClass("hide");
		$("#pregtextid").addClass("hide");
		$("#codevalid").addClass("hide");
		$("#pregvalid").addClass("hide");
		$("#remarkstext").text(remarks);
	}

}

$("#cardbut").click(function() {
	$("#carddiv").toggle("slow");
});
function closeCurrStkModal() {

	document.getElementById('snditmpo2').innerHTML=""; 
	$("#snditmpo2").addClass("hide");
	$("#item_pqty").val(0);
	$("#item_lqty").val(0);
	$("#item_tot").val(parseFloat(0).toFixed(2));
}
function calculateTotalMRP() {
	
	var grandtotalMRP = 0.00;
	var itemcount = 0;
	$('#selitem tbody tr').each(function() {
		var itmmrp = $(this).find("#saletabmrp").html();
			grandtotalMRP = grandtotalMRP + Number(itmmrp);
			itemcount++;
	});
	$("#totitmcount").text(itemcount);
	$("#totmrpamt").val(parseFloat(grandtotalMRP).toFixed(2));
	if (itemcount == 0) {
		$("#payButtId").addClass('disabled');
		$("#delButtId").addClass('disabled');
		$("#holdButtId").addClass('disabled');
	} else {
		$("#payButtId").removeClass("disabled");
		$("#delButtId").removeClass("disabled");
		$("#holdButtId").removeClass("disabled");
	}
}
function calculateTotalamt() {	
	var totsaleamt = 0.00;
	$('#selitem tbody tr').each(function() {
		var saleamt = $(this).find("#saletabamt").html();	
			totsaleamt = totsaleamt + Number(saleamt);						
	});
	$("#totgrossamt").val(parseFloat(totsaleamt).toFixed(2));
	$("#hiddentotgrossamt").val(parseFloat(totsaleamt).toFixed(2));
}


/*
 * function calculateTransition() { var result = 0; $('#selitem tbody
 * tr').each(function() { var rec = $(this).find("#saletabreceivepqty").html();
 * var sent = $(this).find("#saletabpqty").html(); result = rec - sent;
 * 
 * });
 * 
 * $("#saletabtransitpqty").val(result);
 * $("#hiddensaletabtransitpqty").val(parseFloat(result).toFixed(2)); }
 */
function calculateTotalamtAfterDiscount() { 
	
	var totsaleamt = 0.00;
	$('#selitem tbody tr').each(function() {
		var saleamt = $(this).find("#saletabamt").html();
		// if ($(this).find("#saletabisfreeitemaginstitem").html()==0) {// IAI
		// ignor when 0
			totsaleamt = totsaleamt + Number(saleamt);
		// }

	});
	$("#totgrossamt").val(parseFloat(totsaleamt).toFixed(2));
	
}
function calculateTotalltadj() {
	var totsaleltadj = 0.00;
	$('#selitem tbody tr').each(function() {
		var saleltadj = $(this).find("#saletabitemltadj").html();
		

		totsaleltadj = totsaleltadj + Number(saleltadj);
		
	});
	if (totsaleltadj>0) {

		$("#flotid").removeClass("hide");
		$("#totltadj").removeClass("hide");
	}else {
		$("#flotid").addClass("hide");
		$("#totltadj").addClass("hide");
	}
	$("#totltadj").val(parseFloat(totsaleltadj).toFixed(2));
}
function calculateTotalVat() {
	var grandtotalVat = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmvat = $(this).find("#saletabvat").html();

		
		grandtotalVat = grandtotalVat + Number(itmvat);
		



	});
	$("#totvatamt").val(parseFloat(grandtotalVat).toFixed(2));
}
function calculateTotalGSTamt() {
	var grandtotalGST = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmgst = $(this).find("#saletabitemcalcgstamt").html();
		
		grandtotalGST = grandtotalGST + Number(itmgst);
		
	});
	$("#totgstamt").val(parseFloat(grandtotalGST).toFixed(2));
}
function calculateTotalPurchaseamt() {
	var grandtotalPur = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmpur = $(this).find("#saletabpurcost").html();
		
		grandtotalPur = grandtotalPur + Number(itmpur);

		
	});
	var nettot = $("#nettot").val();
	$("#profitperc").val(parseFloat(Number(nettot) - Number(grandtotalPur)).toFixed(2));
}
function calculateTotalTax() {
	var grandtotalTax = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmtax = $(this).find("#saletabtax").html();
		
		grandtotalTax = grandtotalTax + Number(itmtax);
		
	});
	$("#tottaxamt").val(parseFloat(grandtotalTax).toFixed(2));
}
function calculateTotalDisc() {
	var grandtotalDisc = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmmrp = $(this).find("#saletabdisc").html();
		
		grandtotalDisc = grandtotalDisc + Number(itmmrp);
		
	});
	$("#totdiscamt").val(parseFloat(grandtotalDisc).toFixed(2));
	var disc = $("#spcldiscperc").val();
	if (disc != 0) {
		$("#spcldisc").val(parseFloat(grandtotalDisc).toFixed(2));
	}
}
function calculateNetTotal() {
	
	var grandNetTotal = 0.00;

	$('#selitem tbody tr').each(function() {
		var itmmrp = $(this).find("#saletabtotamt").html();
		
		grandNetTotal = grandNetTotal + Number(itmmrp);
		
		
	});
	if (grandNetTotal<=0) {
		$("#payButtId").addClass('disabled');
		$("#delButtId").addClass('disabled');
		$("#holdButtId").addClass('disabled');
	}else {
		$("#payButtId").removeClass("disabled");
		$("#delButtId").removeClass("disabled");
		$("#holdButtId").removeClass("disabled");
	}
	/*
	 * if (!isEmpty($("#otheradjasment").val())) { // other adjamsment
	 * grandNetTotal=parseFloat(grandNetTotal)+0.0; }
	 */
	// alert(grandNetTotal1);
	var roundednetamnt = Math.round(Number(grandNetTotal));
	// console.log("roundednetamnt=" + parseFloat(roundednetamnt).toFixed(2));
	$("#nettot").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff = Number(roundednetamnt) - Number(grandNetTotal);
	// console.log("roundoff=" + parseFloat(roundoff).toFixed(2));
	$("#roundoff").val(parseFloat(roundoff).toFixed(2));
	// value of tenderamt and cash amt
	// $("#tenderamt").val(parseFloat(roundednetamnt).toFixed(2));
	$("#tenderamt").val(parseFloat(0).toFixed(2));
	$("#refundAmt").val(parseFloat(0).toFixed(2));
	$("#creditAmt").val(parseFloat(Number(roundednetamnt)-Number($("#payretadjamt").val())).toFixed(2));
	$("#paymodnettot").val(parseFloat(roundednetamnt).toFixed(2));

}


function calculateSpclDisc() {
	calSpclDisNew();
	/*
	 * var discper = $("#spcldiscperc").val(); var grandNetTotal = 0.00;
	 * $('#selitem tbody tr').each(function() { var itmmrp =
	 * $(this).find("#saletabtotamt").html(); grandNetTotal = grandNetTotal +
	 * Number(itmmrp); }); var discamt = grandNetTotal * discper / 100; var
	 * newnettot = grandNetTotal - discamt; var roundednetamnt =
	 * Math.round(Number(newnettot));
	 * $("#spcldisc").val(parseFloat(discamt).toFixed(2));
	 * $("#nettot").val(parseFloat(roundednetamnt).toFixed(2)); var roundoff =
	 * Number(roundednetamnt) - Number(newnettot);
	 * $("#roundoff").val(parseFloat(roundoff).toFixed(2)); // cal pur amt var
	 * grandtotalPur = 0.00; $('#selitem tbody tr').each(function() { var itmpur =
	 * $(this).find("#saletabpurcost").html(); grandtotalPur = grandtotalPur +
	 * Number(itmpur); }); var profitamt = Number(roundednetamnt) -
	 * Number(grandtotalPur);
	 * $("#profitperc").val(parseFloat(profitamt).toFixed(2)); // value of
	 * tenderamt and cash amt //
	 * $("#tenderamt").val(parseFloat(roundednetamnt).toFixed(2));
	 * $("#tenderamt").val(parseFloat(0).toFixed(2));
	 * $("#refundAmt").val(parseFloat(0).toFixed(2));
	 * $("#creditAmt").val(parseFloat(roundednetamnt).toFixed(2));
	 * $("#paymodnettot").val(parseFloat(roundednetamnt).toFixed(2));
	 */
}
function calSpclDisNew() {
	var discper = 0;
	if ($("#spcldiscperc").val() == "") {
		discper = 0;
	} else {
		discper = $("#spcldiscperc").val();
	}

	// var isexclu = $("#isexclusive").val();
	var grandNetTotal = 0.00;
	$('#selitem tbody tr').each(function() {
		var itemid = this.id;
		var total = 0;
		var discamt = 0;
		var gstamt = 0;
		var ltadj=0;
		var isexclu = $(this).find("#saletabitemtaxtype").html();
		var itmpqty = $(this).find("#saletabpqty").html();
		var itmlqty =0;// $(this).find("#saletablqty").html();
		var itmconv = $(this).find("#saletabconv").html();
		// //var item_free = $(this).find("#saletabfree").html();
		var salerateperunit = $(this).find("#saletabrateperunit").html();
		var saletabmaxDiscountLimit = $(this).find("#saletabmaxDiscountLimit").html();
		var item_taxPercentage = $(this).find("#saletabtaxPercentage").html();
		var saletabisDiscount = $(this).find("#saletabisDiscount").html();
		var item_dual_stock= $(this).find("#saletabdualstock").html();
		var itemfreeIAI=$(this).find("#saletabisfreeitemaginstitem").html();

		if (item_dual_stock==1) {
			var totlqty = Number(itmpqty) ;
		}else {
			var totlqty = Number(itmpqty) * Number(itmconv) + Number(itmlqty);
		}


		total = parseFloat(totlqty * salerateperunit).toFixed(4);
		/*
		 * for floor value physical quantity
		 */
		 var temp_free=Math.floor(item_free);
		 item_free = item_free - temp_free;

		// ltadj calculation
		if(((item_free % 1)!=0) || item_free<1)
		{
			// alert("inside item_free="+item_free);
			var modFree =  item_free % 1;
			var pqtyPerFree = itmpqty/modFree;
			ltadj = total/pqtyPerFree;
			$("#selitem tr#" + itemid).find('#saletabitemltadj').text(parseFloat(ltadj).toFixed(4));
			if (Number(saletabisDiscount) == 1) {
				if (Number(discper) > Number(saletabmaxDiscountLimit)) {
					discamt = (total-ltadj) * Number(saletabmaxDiscountLimit) / 100;
					$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(itemfreeIAI==0?saletabmaxDiscountLimit:0).toFixed(4));
				} else {
					discamt = (total-ltadj) * Number(discper) / 100;
					$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(itemfreeIAI==0?discper:0).toFixed(4));
				}
			}
			// alert(discamt);
			total = total - discamt - ltadj;
			// alert(item_taxPercentage);
			gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
			if (isexclu == 2) {
				total = (parseFloat(Number(total) + Number(gstamt)).toFixed(4));
			}
			$("#selitem tr#" + itemid).find('#saletabtotamt').text(parseFloat(itemfreeIAI==0?total:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabdisc').text(parseFloat(itemfreeIAI==0?discamt:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabitemcalcgstamt').text(parseFloat(itemfreeIAI==0?gstamt:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabitemltadj').text(parseFloat(itemfreeIAI==0?ltadj:0).toFixed(4));
		}else{
			// previous
			if (Number(saletabisDiscount) == 1) {
				if (Number(discper) > Number(saletabmaxDiscountLimit)) {
					discamt = total * Number(saletabmaxDiscountLimit) / 100;
					$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(itemfreeIAI==0?saletabmaxDiscountLimit:0).toFixed(4));
				} else {
					discamt = total * Number(discper) / 100;
					$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(itemfreeIAI==0?discper:0).toFixed(4));
				}
			}
			// alert(discamt);
			total = total - discamt;
			// alert(item_taxPercentage);
			gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
			if (isexclu == 2) {
				total = (parseFloat(Number(total) + Number(gstamt)).toFixed(4));
			}
			$("#selitem tr#" + itemid).find('#saletabtotamt').text(parseFloat(itemfreeIAI==0?total:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabdisc').text(parseFloat(itemfreeIAI==0?discamt:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabitemcalcgstamt').text(parseFloat(itemfreeIAI==0?gstamt:0).toFixed(4));
		}

	});
	calculateTotalMRP();
	// calculateTotalamt();
	calculateTotalamtAfterDiscount();
	calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	// calculateNetTotal();
	// calculateSpclDisc();
	calculateTotalGSTamt();
	calculateTotalPurchaseamt();
	calculateTotalltadj();
	calculateNetTotal();
	
	$("#spcldisc").val($("#totdiscamt").val());
}
function calculateSpclDiscPrcnt() {

	/*
	 * var discamt = $("#spcldisc").val(); var grandNetTotal = 0.00; $('#selitem
	 * tbody tr').each(function() { var itmmrp =
	 * $(this).find("#saletabtotamt").html(); grandNetTotal = grandNetTotal +
	 * Number(itmmrp); }); var discper = (discamt * 100) / grandNetTotal; var
	 * newnettot = grandNetTotal - discamt; var roundednetamnt =
	 * Math.round(Number(newnettot));
	 * $("#spcldiscperc").val(parseFloat(discper).toFixed(2));
	 * $("#nettot").val(parseFloat(roundednetamnt).toFixed(2)); var roundoff =
	 * Number(roundednetamnt) - Number(newnettot);
	 * $("#roundoff").val(parseFloat(roundoff).toFixed(2)); // value of
	 * tenderamt and cash amt //
	 * $("#tenderamt").val(parseFloat(roundednetamnt).toFixed(2));
	 * $("#tenderamt").val(parseFloat(0).toFixed(2));
	 * $("#refundAmt").val(parseFloat(0).toFixed(2));
	 * $("#creditAmt").val(parseFloat(roundednetamnt).toFixed(2));
	 * $("#paymodnettot").val(parseFloat(roundednetamnt).toFixed(2));
	 */
	
	
	/* alert($("#hidennettot").val()); */
	var totdiscamt = 0;
	if ($("#spcldisc").val() == "") {
		discper = 0;
	} else {
		totdiscamt = $("#spcldisc").val();
	}

	var discper = 0;
	var nettotamt = $("#hiddentotgrossamt").val();
	discper = parseFloat((Number(totdiscamt) * 100) / Number(nettotamt)).toFixed(4); ;
	
	// var isexclu = $("#isexclusive").val();
	var grandNetTotal = 0.00;
	$('#selitem tbody tr').each(function() {
		var itemid = this.id;
		var total = 0;
		var discamt = 0;
		var gstamt = 0;
		var ltadj=0;
		var isexclu = $(this).find("#saletabitemtaxtype").html();
		var itmpqty = $(this).find("#saletabpqty").html();
		var itmlqty =0;// $(this).find("#saletablqty").html();
		var itmconv = $(this).find("#saletabconv").html();
		// //var item_free = $(this).find("#saletabfree").html();
		var salerateperunit = $(this).find("#saletabrateperunit").html();
		var saletabmaxDiscountLimit = $(this).find("#saletabmaxDiscountLimit").html();
		var item_taxPercentage = $(this).find("#saletabtaxPercentage").html();
		var saletabisDiscount = $(this).find("#saletabisDiscount").html();
		var item_dual_stock= $(this).find("#saletabdualstock").html();
		var itemfreeIAI=$(this).find("#saletabisfreeitemaginstitem").html();

		if (item_dual_stock==1) {
			var totlqty = Number(itmpqty) ;
		}else {
			var totlqty = Number(itmpqty) * Number(itmconv) + Number(itmlqty);
		}


		total = parseFloat(totlqty * salerateperunit).toFixed(4);
		/*
		 * for floor value physical quantity
		 */
		 var temp_free=Math.floor(item_free);
		 item_free = item_free - temp_free;

		// ltadj calculation
		if(((item_free % 1)!=0) || item_free<1)
		{
			// alert("inside item_free="+item_free);
			var modFree =  item_free % 1;
			var pqtyPerFree = itmpqty/modFree;
			ltadj = total/pqtyPerFree;
			$("#selitem tr#" + itemid).find('#saletabitemltadj').text(parseFloat(ltadj).toFixed(4));
			if (Number(saletabisDiscount) == 1) {
				if (Number(discper) > Number(saletabmaxDiscountLimit)) {
					discamt = (total-ltadj) * Number(saletabmaxDiscountLimit) / 100;
					$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(itemfreeIAI==0?saletabmaxDiscountLimit:0).toFixed(4));
				} else {
					discamt = (total-ltadj) * Number(discper) / 100;
					$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(itemfreeIAI==0?discper:0).toFixed(4));
				}
			}
			// alert(discamt);
			total = total - discamt - ltadj;
			// alert(item_taxPercentage);
			gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
			if (isexclu == 2) {
				total = (parseFloat(Number(total) + Number(gstamt)).toFixed(4));
			}
			$("#selitem tr#" + itemid).find('#saletabtotamt').text(parseFloat(itemfreeIAI==0?total:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabdisc').text(parseFloat(itemfreeIAI==0?discamt:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabitemcalcgstamt').text(parseFloat(itemfreeIAI==0?gstamt:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabitemltadj').text(parseFloat(itemfreeIAI==0?ltadj:0).toFixed(4));
		}else{
			// previous
			if (Number(saletabisDiscount) == 1) {
				if (Number(discper) > Number(saletabmaxDiscountLimit)) {
					discamt = total * Number(saletabmaxDiscountLimit) / 100;
					$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(itemfreeIAI==0?saletabmaxDiscountLimit:0).toFixed(4));
				} else {
					discamt = total * Number(discper) / 100;
					$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(itemfreeIAI==0?discper:0).toFixed(4));
				}
			}
			// alert(discamt);
			total = total - discamt;
			// alert(item_taxPercentage);
			gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
			if (isexclu == 2) {
				total = (parseFloat(Number(total) + Number(gstamt)).toFixed(4));
			}
			$("#selitem tr#" + itemid).find('#saletabtotamt').text(parseFloat(itemfreeIAI==0?total:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabdisc').text(parseFloat(itemfreeIAI==0?discamt:0).toFixed(4));
			$("#selitem tr#" + itemid).find('#saletabitemcalcgstamt').text(parseFloat(itemfreeIAI==0?gstamt:0).toFixed(4));
		}

	});
	calculateTotalMRP();
	// calculateTotalamt();
	calculateTotalamtAfterDiscount();
	calculateTotalVat();
	calculateTotalTax();
	// calculateTotalDisc();
	calculateTotalGSTamt();
	calculateTotalPurchaseamt();
	calculateTotalltadj();
	calculateNetTotal();
    $("#spcldiscperc").val(parseFloat(discper).toFixed(2));
	$("#totdiscamt").val(parseFloat(totdiscamt).toFixed(2));
	
	
}

function clearHeaderDiv() {
	
	  $('#alertMsg').html('');
	$("#header_div").find('input:text').val('');

	$("#header_div").find('select').val(0);
	$("#header_div").find('input:hidden').val('');
	$('#item_name').prop('readonly', false);
	$('#item_barcode').prop('readonly', false);
	$("#add_btn").removeClass("hide");
	$("#edit_btn").addClass("hide");
	$("#item_id").val("0");
	$("#item_dis").val(parseFloat($("#salediscount").val()).toFixed(4));
	// $("#item_name").focus();
	$("#itemContent").prop('readonly', false);
	$("#content_Dets").val("");
	$("#content_id").val("0");
	$('#isfreeiai').prop('checked', false);
	$('#item_mfg').prop('readonly', false);
	 $("#ratio_val").addClass('hide');
	 $("#ratio_label").addClass('hide');
	/*
	 * sales man value setting zero
	 */
	 $("#salesman_id").val(0.0);
	 $("#salesman_per").val(0.0);
	 $("#salesman_amt").val(0.0);

	// document.getElementById('alertMsg').innerHTML = "";
	 if(primarySearchId == 1){
		   $("#item_name").focus(); 
	   }else if(primarySearchId == 2){
		   $("#item_barcode").focus(); 
	   }else{
		   $("#item_code").focus(); 
	   }
	$('#weival').removeClass("callweival");
};
function clearSaleInv() {
	// $("#saleInvDiv").find('input:text').val('');
	closeRetAdjMod();
	var nettot = $("#nettot").val();
	$("#creditAmt").val(parseFloat(nettot).toFixed(2));
	$("#paymodnettot").val(parseFloat(nettot).toFixed(2));
	$("#tenderamt").val(parseFloat(0).toFixed(2));
	$("#refundAmt").val(parseFloat(0).toFixed(2));
	$("#cashAmt").val(parseFloat(0).toFixed(2));
	$("#cardAmt").val(parseFloat(0).toFixed(2));
	$("#cardExpMod").val('');
	$("#cardFourDigitMod").val('');
	if ($("#retamtvalsamepage").val() != 0) {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		$("#payretadjamt").val(parseFloat(s).toFixed(2));
	}
	document.getElementById('alertmessagecont').innerHTML = "";

}
function ExistsOk() {
	clearHeaderDiv();
}
/*
 * function showSelItemDelModal(trId) { $("#confirmId").val(trId);
 * $('#confirmModal').modal('show'); } function DoConfirm() { var itmid =
 * $("#confirmId").val(); $('#saletabitemdetails tr#' + itmid).remove();
 * clearHeaderDiv(); calculateTotalMRP(); calculateTotalVat();
 * calculateTotalDisc(); calculateNetTotal(); }
 */

function getCustPreviousBill(custphno) {
	// console.log("custphno=" + custphno.length);
	if (custphno == '') {

	} else {
		$("#saleheaderdet").text("");
		$("#cusprebillphno").text(custphno + "/" + $('#salecustname').val());
		$('#pleasewaitModal').modal('show');
		var commonresultsetmap = {};
		commonresultsetmap.custPh = custphno;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/pos/getsaleheaderdetbycustomerphno.htm", commonresultsetmap, function(response) {
			$('#pleasewaitModal').modal('hide');
			// console.log("response=" + response);
			var headerdetails = JSON.parse(response);
			if (headerdetails.length == 0) {
				$("#prevsaledetnotfounddiv").removeClass("hide");
				$("#saledetmodtable").addClass("hide");
			} else {
				$("#prevsaledetnotfounddiv").addClass("hide");
				$("#saledetmodtable").removeClass("hide");
				for ( var i = 0; i < headerdetails.length; i++) {
					var headerdetail = headerdetails[i];
					var poststr = "";
					if (headerdetail.isPosted == 0) {
						poststr = "Unposted";
					} else {
						poststr = "Posted";
					}
					var starttrline = "<tr id=" + headerdetail.saleId + " style='cursor: pointer;' onclick='javascript:cusSelDet(" + headerdetail.saleId + ",&quot;" + headerdetail.invNo + "&quot;)'>";
					var invno = "<td>" + headerdetail.invNo + "</td>";
					var invdate = "<td>" + moment(headerdetail.invDate).format('YYYY-MM-DD') + " / " + headerdetail.invTime + "</td>";
					var invModeName = "<td>" + headerdetail.invModeName + "</td>";
					var grossAmount = "<td>" + parseFloat(headerdetail.grossAmount).toFixed(2) + "</td>";
					var discAmount = "<td>" + parseFloat(headerdetail.discAmount).toFixed(2) + "</td>";//
					var netAmount = "<td>" + parseFloat(headerdetail.netAmount).toFixed(2) + "</td>";
					var poststr1 = "<td>" + poststr + "</td>";
					var endtrline = "</tr>";
					createdrowline = starttrline + invno + invdate + invModeName + grossAmount + discAmount + netAmount + poststr1 + endtrline;
					$("#saleheaderdet").append(createdrowline);
				}
			}

		});
		$("#custLastBillModal").modal("show");
	}

}

function cusSelDet(	selid,
					saleinvno) {

	$("#custLastBillModal").modal("hide");
	$("#saletabitemdetails").text("");
	$("#searchmodtbody").text("");
	// $("#saledetailModal").modal("show");
	$("#searchmodinvno").text(saleinvno);
	var commonresultsetmap = {};
	commonresultsetmap.saleId = selid;
	var generatedHtml = "";
	var tablestart = '<div style="max-height: 175px;overflow: auto;"><table id="searchmodtable" class="table table-bordered table-condensed-trx" style="bgcolor:#000;margin-bottom: 1px;cursor: pointer;">';
	var tableend = '</table></div>';
	var headerrow = '<tr><td>Item Name</td><td>P.Qty</td><td>L.Qty</td></tr>';
	generatedHtml += tablestart + headerrow;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/pos/getsaledetbycustomerphno.htm", commonresultsetmap, function(response) {
		// console.log("response=" + response);
		var cashmemodetails = JSON.parse(response);
		for ( var i = 0; i < cashmemodetails.length; i++) {
			var cashmemodet = cashmemodetails[i];
			// var mrpamt = ((cashmemodet.packQty * cashmemodet.conversion) +
			// Number(cashmemodet.looseQty)) * cashmemodet.mrp;

			var starttrline = "<tr id='" + cashmemodet.itemUniqueKey + "' onclick='getCheckedPreBillDet(" + JSON.stringify(cashmemodet) + ")'>";
			var chkbox = "<td><input id='" + cashmemodet.itemUniqueKey + "_modretcheck' class='chkboxcheked' type='checkbox' onclick='getCheckedPreBillDet(" + JSON.stringify(cashmemodet) + ")' value='" + JSON.stringify(cashmemodet) + "'> </td>";
			var name = "<td id='presaletabname'>" + cashmemodet.itemName + "</td>";
			var batchno = "<td id='presaletabbat'>" + cashmemodet.batchNo + "</td>";
			var expiryDateFormat = "<td id='presaletabexpdt'>" + cashmemodet.expiryDateFormat + "</td>";
			// var manfacname = "<td id='saletabmanname'>" +
			// cashmemodet.manufacturerName + "</td>";
			var packQty = "<td id='presaletabpqty'>" + cashmemodet.packQty + "</td>";
			var looseQty = "<td id='presaletablqty'>" + cashmemodet.looseQty + "</td>";
			// var conversion = "<td id='saletabconv'>" + cashmemodet.conversion
			// + "</td>";
			var mrpperunit = "<td id='presaletabmrppack'>" + parseFloat(cashmemodet.mrpPerUnit).toFixed(4) + "</td>";
			// var mrp = "<td id='saletabmrp'>" +
			// parseFloat(cashmemodet.mrp).toFixed(4) + "</td>";
			var rateperunit = "<td id='presaletabrateperunit' >" + parseFloat(cashmemodet.ratePerUnit).toFixed(4) + "</td>";
			/*
			 * var vatperc = "<td id='saletabvatperc' class='hide'>" +
			 * parseFloat(cashmemodet.vatPer).toFixed(4) + "</td>"; var
			 * discperc = "<td id='saletabdiscperc'>" +
			 * parseFloat(cashmemodet.discPer).toFixed(4) + "</td>"; var
			 * totamt = "<td id='saletabtotamt'>" +
			 * parseFloat(cashmemodet.totAmount).toFixed(4) + "</td>"; var
			 * rowdelete = "<td><button class='btn btn-theme04 btn-xs' id='" +
			 * cashmemodet.itemUniqueKey + "'
			 * onclick='javascript:showSelTabItemDelModal(this.id);'><i
			 * class='fa fa-trash-o '></i></button></td>"; var punitid = "<td id='saletabpunitid' class='hide'>" +
			 * cashmemodet.packUnitId + "</td>"; var lunitid = "<td id='saletablunitid' class='hide'>" +
			 * cashmemodet.looseUnitId + "</td>"; var content = "<td id='saletabcontent' class='hide'>" +
			 * cashmemodet.contentName + "</td>"; var vatamt = "<td id='saletabvat' class='hide'>" +
			 * cashmemodet.vat + "</td>"; var discamt = "<td id='saletabdisc' class='hide'>" +
			 * cashmemodet.disc + "</td>"; var stockedqty = "<td id='saletabitemstkqty' class='hide'>" +
			 * cashmemodet.disc + "</td>"; var mrpperunit = "<td id='saletabmrpperunit' class='hide'>" +
			 * parseFloat(cashmemodet.mrpPerUnit).toFixed(4) + "</td>"; var
			 * rate = "<td id='saletabrate' class='hide'>" +
			 * cashmemodet.ratePerUnit + "</td>"; var schename = "<td id='saletabsche' class='hide'>" +
			 * cashmemodet.scheduleName + "</td>";
			 */
			var endtrline = "</tr>";

			// createdrowline = starttrline + name + batchno + expiryDateFormat
			// + manfacname + packQty + looseQty + conversion + mrppack + mrp +
			// rateperunit + vatperc + vatamt + discperc + discamt + totamt +
			// rowdelete + punitid + lunitid + content + stockedqty + mrpperunit
			// + rate + schename + endtrline;
			// $("#saletabitemdetails").append(createdrowline);
			createdrowline = starttrline + name + packQty + looseQty + endtrline;
			$("#searchmodtbody").append(createdrowline);
			generatedHtml += createdrowline;
		}
		generatedHtml += tableend;

		/*
		 * clearHeaderDiv(); calculateTotalMRP(); calculateTotalVat();
		 * calculateTotalDisc(); calculateNetTotal();
		 */

		var unique_id = $.gritter.add({
			// (string | mandatory) the heading of the notification
			title : "Sale Details for " + saleinvno + "",
			// (string | mandatory) the text inside the notification
			text : '' + generatedHtml + '',
			// (string | optional) the image to display on the left
			// image: 'assets/img/ui-sam.jpg',
			// (bool | optional) if you want it to fade out on its own or just
			// sit there
			sticky : true,
			// (int | optional) the time you want it to be alive for before
			// fading out
			time : '',
			// (string | optional) the class name you want to apply to that
			// specific message
			class_name : 'my-sticky-class'
		});
		var curid = unique_id - 1;
		if (curid > 0) {
			$.gritter.remove(curid, {});
		}

	});

}

function getCheckedPreBillDet(cashmemodet) {
	var itemid = (cashmemodet.itemUniqueKey).split("_")[0];
	// alert(itemid);
	$("#item_name").val(cashmemodet.itemName);
	getItemDetails(itemid);
}

function saveOrUpdateSaleInv() {

	if ($("#item_id").val() == 0 || $("#item_id").val() == "") {
		if (isNaN($("#tenderamt").val())) {
			// $("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Tender Amount";
			// showConfirmModal();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		if (isNaN($("#cashAmt").val())) {
			// $("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Cash Amount";
			// showConfirmModal();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		if (isNaN($("#cardExpMod").val())) {
			// $("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Expiry";
			// showConfirmModal();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		if (isNaN($("#cardFourDigitMod").val())) {
			// $("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Four Digit";
			// showConfirmModal();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		var saleid = $("#saleId").val();
		var sale = {};
		var allsaledetails = [];
		var allsalereturndetails = [];
		var selitemlength = $('#selitem >tbody >tr').length;
		/* alert(selitemlength); */
		$('#selitem > tbody  > tr').each(function(i) {
			var saledetails = {};
			var itemid = this.id;
			var saleitembatch = $(this).find("#saletabbat").text();
			if(saleitembatch == ""){saleitembatch=0;}// new added 13.6.2019
			var totlooseqty = (parseFloat($(this).find("#saletabpqty").text()) * parseFloat($(this).find("#saletabconv").text())) + parseFloat($(this).find("#saletablqty").text());
			saledetails.itemId = itemid.split("_")[0];
			saledetails.batchNo = $(this).find("#saletabbat").text();
			saledetails.batchNo = saleitembatch;// new added 13.6.2019
			saledetails.expiryDateFormat = $(this).find("#saletabexpdt").text();
			saledetails.expiryDate= $(this).find("#saletabexpdate").text();
			saledetails.packUnitId = $(this).find("#saletabpunitid").text();
			saledetails.packQty = $(this).find("#saletabpqty").text();
			saledetails.looseQty =0;// $(this).find("#saletablqty").text();
			saledetails.looseUnitId = $(this).find("#saletablunitid").text();
			saledetails.conversion = $(this).find("#saletabconv").text();
			saledetails.mrp = ($(this).find("#saletabmrppack").text()=="")?0:$(this).find("#saletabmrppack").text();
			saledetails.amount = $(this).find("#saletabamt").text();
			saledetails.itemTotalMrp = ($(this).find("#saletabmrp").text()=="")?0:$(this).find("#saletabmrp").text();
			saledetails.mrpPerUnit = $(this).find("#saletabmrpperunit").text();// parseFloat(parseFloat($(this).find("#saletabmrppack").text())/
																				// parseFloat($(this).find("#saletabconv").text())).toFixed(2);
			saledetails.ratePerUnit = $(this).find("#saletabrateperunit").text();
			saledetails.rate = $(this).find("#saletabitemsalerate").text();// $(this).find("#saletabrateperunit").text()
																			// *
																			// parseFloat($(this).find("#saletabconv").text());
			saledetails.vat = 0;// $(this).find("#saletabvat").text();
			saledetails.vatPer = $(this).find("#saletabvatperc").text();
			saledetails.tax = 0;// $(this).find("#saletabtax").text();
			saledetails.taxPer = $(this).find("#saletabtaxperc").text();
			saledetails.disc = $(this).find("#saletabdisc").text();
			saledetails.discPer = $(this).find("#saletabdiscperc").text();
			saledetails.totAmount = $(this).find("#saletabtotamt").text();
			saledetails.taxId = $(this).find("#saletabtaxId").text();
			saledetails.taxPercentage = $(this).find("#saletabtaxPercentage").text();
			saledetails.taxAmount = $(this).find("#saletabitemcalcgstamt").text();
			saledetails.isGroupTax = $(this).find("#saletabisGroupTax").text();
			saledetails.taxMode = $(this).find("#saletabitemtaxmode").text();
			saledetails.purchaseCostPerUnit = $(this).find("#saletabpurcostperunit").text();
			saledetails.saleRate = $(this).find("#saletabitemsalerate").text();
			saledetails.purchaseRate = $(this).find("#saletabitempurrate").text();
			saledetails.taxTypeId = $(this).find("#saletabitemtaxtype").text();
			saledetails.isExclusive = $(this).find("#saletabitemtaxtype").text();
			saledetails.retailTypeId=$("#retailtypeinfo").val();
			saledetails.itemdualstock=$(this).find("#saletabdualstock").text();
			saledetails.taxTypeId = $(this).find("#saletabitemtaxtype").text();
			// sales man
			saledetails.salesmanId=$(this).find("#saletabsalesmanIdComPer").text().split("_")[0];
			saledetails.salesmanFactor=$(this).find("#saletabsalesmanIdComPer").text().split("_")[1];
			saledetails.salesmanAmount=$(this).find("#saletabsalesmanIdComPer").text().split("_")[2];
			  // for free
			// // saledetails.freeQty=$(this).find("#saletabfree").text();
			 saledetails.itemLotAdjAmount=$(this).find("#saletabitemltadj").text();
			 // for item free aginst item
			 saledetails.itemFreeAgainstItem=$(this).find("#saletabisfreeitemaginstitem").text()
			 saledetails.saleOrderId=$(this).find("#saleorderid").text();
            // serialno add
			salesDetailsSerialMapperObjArr=[];
			var slnos=$(this).find('#saletabitemslnos').text();
			var slnoarr=slnos.split(",");
			var slnoschk=$(this).find('#saletabitemchkslnosid').text();
			var slnoschkarr=slnoschk.split(",");
			saledetails.tmpMappingId=(i);
			for(var h=0;h<slnoarr.length;h++){
				var salesDetailsSerialMapperObj={};
				salesDetailsSerialMapperObj.itemId=itemid.split("_")[0];
				salesDetailsSerialMapperObj.batchNo=$(this).find('#saletabbat').text();
				salesDetailsSerialMapperObj.uniqueIdentifierNo=slnoarr[h];
				salesDetailsSerialMapperObj.qty=$(this).find('#saletabpqty').text();
				salesDetailsSerialMapperObj.tmpMappingId=(i);
				salesDetailsSerialMapperObj.checkStatus=slnoschkarr[h];
				salesDetailsSerialMapperObjArr.push(salesDetailsSerialMapperObj);
			}
			saledetails.salesDetailsSerialMapper=salesDetailsSerialMapperObjArr;
			saledetails.remarks=$(this).find('#saletabremarks').text(); // new
																		// added
																		// 7.5.2019
			
			// slno end
			allsaledetails.push(saledetails);
		});
		sale.salesDetails = allsaledetails;
		sale.id = saleid;

		// for same page return
		var cval = $("#confirmvalrsp").val();
       // alert("cval="+cval);
		if (cval == 0) {

		} else {
			var cc = cval.split(",");
			for ( var i = 0; i < cc.length; i++) {
				console.log("ret id=" + cc[i]);
				// alert("ret id=" + cc[i]);
				var CommonResultSetMapper = {};
				CommonResultSetMapper.saleReturnId = cc[i];
				var ajaxCallObjectret = new CustomBrowserXMLObject();
				ajaxCallObjectret.callAjaxPost(BASE_URL + "/retunmemo/getsaleretdetbyid.htm", CommonResultSetMapper, function(res) {
				// alert("res=" + res);
					var retdetails = JSON.parse(res);
					var salereturndetailssamepage = {};
					salereturndetailssamepage.id = retdetails.saleReturnId;
					salereturndetailssamepage.invNo = retdetails.invNo;
					salereturndetailssamepage.invDate = moment(retdetails.invDate).format('YYYY-MM-DD');// invdate
					salereturndetailssamepage.adjAmount = retdetails.netAmount;// adjustment
																				// amt
					allsalereturndetails.push(salereturndetailssamepage);
				});
			}

		}

		if (saleid == 0) {// create

		} else {// edit
			sale.invNo = $("#saleinvno").val();
		}

		sale.invDate = $("#date").val();
		if ($("#salecustname").val() == "") {
			sale.customerId = 0;
		} else {
			sale.customerId = $("#salecustid").val();
			sale.customerName = $("#salecustname").val();
			if ($("#salecustph").val() == "") {
				sale.customerPhone = 0000000000;
			} else {
				sale.customerPhone = $("#salecustph").val();
			}

		}

		if ($("#saledocname").val() == "") {
			sale.doctorId = 0;
		} else {
			sale.doctorId = $("#saledocid").val();
			sale.doctorName = $("#saledocname").val();
		}
		sale.grossAmount = $("#totgrossamt").val();
		sale.totalMrp = ($("#totmrpamt").val()=="NaN")?0:$("#totmrpamt").val();
		sale.vatAmount = 0;// $("#totvatamt").val();
		sale.taxAmount = $("#totgstamt").val();// $("#tottaxamt").val();
		sale.discAmount = $("#totdiscamt").val();
		sale.roundoff = $("#roundoff").val();
		sale.netAmount = $("#nettot").val();
		sale.remarks = $("#remarks").val();
		sale.qs = $("#qs").val();
		sale.mulSeriesPrefix = $("#mulslid").val();
		sale.creditAmount = 0.00;
		  // when totltadj > 0 then it take original value else zero
        sale.lotAdjAmount=$("#totltadj").val()>0 ? $("#totltadj").val() : 0.0;
     // when otheradjsment > 0 then it take original value else zero
        sale.othAdjAmount=$("#otheradjasment").val()>0 ? $("#otheradjasment").val() : 0.0;

		if ($("#spcldiscperc").val() == "") {
			sale.specialDiscPer = 0.00;
		} else {
			sale.specialDiscPer = $("#spcldiscperc").val();
		}

		sale.specialDiscAmount = $("#spcldisc").val();
		if ($("#isesi").val() == 1) {
			sale.esiType = $("#esitypeid").val();
			sale.prescriptionIssueDate = $("#pissuedt").val();
			sale.slipNo = $("#reqslno").val();
			sale.esiCode = $("#pcodeid").val();
			sale.prescriptionRegNo = $("#prregno").val();
		}
		/*
		 * sale.tenderAmount = $("#tenderamt").val(); sale.cashAmount =
		 * $("#cashAmt").val(); sale.cardAmount = $("#cardAmt").val();
		 * sale.cardExpDate =$("#cardExpMod").val(); sale.cardFourDigit
		 * =$("#cardFourDigitMod").val();
		 */
		sale.holdFlag = 0;// 1 for pay 0 for hold
		if ($('input[name=payOption]').is(":checked")) {
			sale.invMode = $("#payOption").val();

		}


		if (selitemlength > 0) {
			$('#pleasewaitModal').modal('show');
			setTimeout(function() {
				sale.saleReturns = allsalereturndetails;
				// console.log("sale json: " + JSON.stringify(sale));
				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/pos/createorupdatesalesinvoice.htm", sale, function(response) {
					$('#pleasewaitModal').modal('hide');
					if (response == '0') {
						document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataNotAdd;
						$("#confirmval").val(0);
						showConfirmModal();
					} else {
						document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataSucAdd;
						$("#confirmval").val(response);
						showConfirmModal();
					}

				});
			}, 1000);
		}
	} else {
		$("#confirmval").val(-1);
		document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.addEditChckBefrSave;
		showConfirmModal();
	}
}
function saveCashmemo() {
	if ($("#salecustname").val() == "" ) {// || $("#saledocname").val() == ""
		$("#saveWithoutCustAndDocNameModal").modal("show");
	} else {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		if ($("#retamtsamepage").val() == 0) {
			if (Number($("#nettot").val()) > Number(s)) {
				saveOrUpdateSaleInv();
			} else {
				$("#samepageretvalgreater_rsp").modal("show");
			}
		} else {
			$("#samepageretnotsave_rsp").modal("show");
		}
	}
}
function paySaleInvModal1() {
	// alert("hi");
	$("#payModal").modal("show");
	$('#account_from').addClass("hide");
}
function paySaleInvModal() {
	
    openPaymentModal = 1; // 29.5.2019
	var actualnettot = $("#paymodnettot").val();
	var so_advamt = 0;
	for (var key in sale_order_adv_arr) {
	  console.log("key " + key + " has value " + sale_order_adv_arr[key]);
	  so_advamt += sale_order_adv_arr[key];
	}
	$("#so_adv_amt").val(parseFloat(so_advamt).toFixed(2));

	console.log("paynettot = " + parseFloat(actualnettot - so_advamt).toFixed(2));

	var nettotadvdeduct = parseFloat(actualnettot - so_advamt).toFixed(2);
	document.getElementById("paymodnettot").value = nettotadvdeduct;

	var s = $("#retamtvalsamepage").val();
	if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
		s = 0;
	}
	if ($("#retamtsamepage").val() == 0) {
		if (Number($("#nettot").val()) > Number(s)) {

			var creditcustomer = $("#salecustid").val();
			if (creditcustomer == 0) // check credit customer or not
			{
				document.getElementById("paymodnettot").value = nettotadvdeduct;
				$("#creditLimit").val(0.0);
				$('#notCreditCustDiv').removeClass("hide");
				$('#creditCustDiv').addClass("hide");
				var returnadjasment=$("#payretadjamt").val();
				
				var tenderamount=$("#tenderamt").val();
				var cashamount=$("#cashAmt").val();
                $("#refundAmt").val(tenderamount-cashamount);
				if (returnadjasment>0) {
					$("#cashAmt").val(parseFloat($("#paymodnettot").val()-returnadjasment).toFixed(2));
				}else
				{	
					$("#tenderamt").val(parseFloat($("#paymodnettot").val()).toFixed(2));
					$("#cashAmt").val(parseFloat($("#paymodnettot").val()).toFixed(2));
				}

				$("#creditAmt").val(0.0);
			} else {
				
				$("#cashAmt").val(parseFloat($("#paymodnettot").val()).toFixed(2));
				// /$("#creditAmt").val(0.0);
				calculateTotalMRP();
				calculateTotalamt();
				calculateTotalVat();
				calculateTotalTax();
				calculateTotalDisc();
				// calculateNetTotal();
				// calculateSpclDisc();
				calculateTotalGSTamt();
				calculateTotalPurchaseamt();
				calculateTotalltadj();
				calculateNetTotal();
				// $("#creditAmt").val($("#paymodnettot").val());
				document.getElementById("paymodnettot").value = nettotadvdeduct;
				$("#payretadjamt").val(parseFloat(s).toFixed(2));
				// /$("#creditAmt").val(parseFloat($("#paymodnettot").val()-s).toFixed(2));
				$("#creditAmt").val(parseFloat(0.0).toFixed(2));
				$("#creditLimit").val(parseFloat($("#custCreditLimit").val()).toFixed(2));
				$('#notCreditCustDiv').addClass("hide");
				$('#creditCustDiv').removeClass("hide");
				$("#tenderamt").val(parseFloat($("#paymodnettot").val()).toFixed(2));
			}
			$("#payModal").modal("show");
			if ($("#salecustname").val() == "" || $("#saledocname").val() == "") {
				$("#docorcus").text("without customer details");
			} else {
				$("#docorcus").text("");
			}
		} else {
			$("#samepageretvalgreater_rsp").modal("show");
		}
	} else {
		$("#samepageretnotsave_rsp").modal("show");
	}

	/*
	 * if (sale_id_present==0) {
	 * getvendorledger_sale($('#debitor_group_codef').val(),0,$('#salecustid').val(),3);//
	 * for sunndry debitor
	 * 
	 * if($('#salecustid').val()!=0) {
	 * getvendorledger_sale($('#cash_codef').val(),0,0,5);// for cash } } else {
	 * getvendorledger_sale($('#debitor_group_codef').val(),0,$('#salecustid').val(),3);//
	 * for sunndry debitor getvendorledger_sale($('#cash_codef').val(),0,0,5);//
	 * for cash }
	 */



	getvendorledger_sale($('#card_codef').val(),0,0,6);// for card

}
function paySaleInv() {
	
openPaymentModal = 0; // 29.5.2019
 document.getElementById('alertmessagecont').innerHTML = "";
	var notvallid = 1;
	var retadj=$("#payretadjamt").val();
	var totpayment=$("#paymodnettot").val();
	totpayment=(retadj>0)?(totpayment-retadj):totpayment;
	
	var compare=parseFloat(parseFloat($("#cashAmt").val())+parseFloat($("#cardAmt").val())+parseFloat($("#creditAmt").val()) );
	$("#tenderamt").val(totpayment);
	if(totpayment != compare) {
		document.getElementById('alertmessagecont').innerHTML = "Please pay the exact bill amount.";
		notvallid = 0;
		return false;
	}
	if ($("#item_id").val() == 0 || $("#item_id").val() == "") {
		if (isNaN($("#tenderamt").val())) {
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Tender Amount";
			
			notvallid = 0;
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		if (isNaN($("#cashAmt").val())) {
			// $("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Cash Amount";
			// showConfirmModal();
			notvallid = 0;
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}



		if (isNaN($("#cardFourDigitMod").val())) {
			// $("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Four Digit";
			// showConfirmModal();
			notvallid = 0;
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}
		var creditAmt = $("#creditAmt").val().trim();
		// //////alert("CREDIT AMOUNT IS:"+creditAmt);

		var creditcustomer = $("#salecustid").val();
		// if(creditcustomer==0) { // check credit customer or not
		if (creditAmt > 0) {
			if (creditcustomer == 0) // check credit customer or not
			{
				document.getElementById('alertmessagecont').innerHTML = getCashMemoText.billAmtLessCashCardError;
				notvallid = 0;
				return false;
			} else {
				var creditLimit= $("#creditLimit").val();
				if (Number(creditAmt) > Number(creditLimit)) {
					document.getElementById('alertmessagecont').innerHTML = getCashMemoText.custCreditAmtCheck;
					notvallid = 0;
					return false;
				} else {
					document.getElementById('alertmessagecont').innerHTML = "";

				}
			}

		} else {
			var creditLimit= $("#creditLimit").val();
			// document.getElementById('alertmessagecont').innerHTML = "";
			// previous
			if (Number(creditAmt) > Number(creditLimit)) {
				document.getElementById('alertmessagecont').innerHTML = getCashMemoText.custCreditAmtCheck;
				notvallid = 0;
				return false;
			} else {
				document.getElementById('alertmessagecont').innerHTML = "";

			}
		}
	
		var saleid = $("#saleId").val();
		var sale = {};
		var allsaledetails = [];
		var allsalereturndetails = [];
		var selitemlength = $('#selitem >tbody >tr').length;
		$('#selitem > tbody  > tr').each(function(i) {
			var saledetails = {};
			var itemid = this.id;
			var totlooseqty = (parseFloat($(this).find("#saletabpqty").text()) * parseFloat($(this).find("#saletabconv").text())) + parseFloat($(this).find("#saletablqty").text());
			saledetails.itemId = itemid.split("_")[0];
			var saleitembatch = $(this).find("#saletabbat").text();// new added
																	// 13.6.2019
			if(saleitembatch == ""){saleitembatch=0;}// new added 13.6.2019
			saledetails.batchNo = saleitembatch;// new updated 13.6.2019
			saledetails.expiryDateFormat = $(this).find("#saletabexpdt").text();
			saledetails.expiryDate= $(this).find("#saletabexpdate").text();
			saledetails.packUnitId = $(this).find("#saletabpunitid").text();
			saledetails.packQty = $(this).find("#saletabpqty").text();
			saledetails.looseQty = 0;// $(this).find("#saletablqty").text();
			saledetails.looseUnitId = $(this).find("#saletablunitid").text();
			saledetails.conversion = $(this).find("#saletabconv").text();
		/*
		 * saledetails.mrp =
		 * ($(this).find("#saletabmrppack").text()=="")?0:$(this).find("#saletabmrppack").text();
		 */ // changed
																														// 4.7.2019
			saledetails.mrp = ($(this).find("#saletabmrppack").text()=="")?0:$(this).find("#saletabmrpperunit").text() * parseFloat($(this).find("#saletabconv").text());
			
			saledetails.amount = $(this).find("#saletabamt").text();
			saledetails.itemTotalMrp = ($(this).find("#saletabmrp").text()=="")?0:$(this).find("#saletabmrp").text();
			saledetails.mrpPerUnit = $(this).find("#saletabmrpperunit").text();// parseFloat(parseFloat($(this).find("#saletabmrppack").text())/
																				// parseFloat($(this).find("#saletabconv").text())).toFixed(2);
			saledetails.ratePerUnit = $(this).find("#saletabrateperunit").text();
			saledetails.rate = $(this).find("#saletabrateperunit").text() * parseFloat($(this).find("#saletabconv").text());  // $(this).find("#saletabitemsalerate").text();
																																// //
																																// changed
																																// 4.7.2019
			saledetails.vat = 0;// $(this).find("#saletabvat").text();
			saledetails.vatPer = $(this).find("#saletabvatperc").text();
			saledetails.tax = 0;// $(this).find("#saletabtax").text();
			saledetails.taxPer = $(this).find("#saletabtaxperc").text();
			saledetails.disc = $(this).find("#saletabdisc").text();
			saledetails.discPer = $(this).find("#saletabdiscperc").text();
			saledetails.totAmount = $(this).find("#saletabtotamt").text();
			saledetails.taxId = $(this).find("#saletabtaxId").text();
			saledetails.taxPercentage = $(this).find("#saletabtaxPercentage").text();
			saledetails.taxAmount = $(this).find("#saletabitemcalcgstamt").text();
			saledetails.isGroupTax = $(this).find("#saletabisGroupTax").text();
			saledetails.taxMode = $(this).find("#saletabitemtaxmode").text();
			saledetails.purchaseCostPerUnit = $(this).find("#saletabpurcostperunit").text();
			saledetails.saleRate = $(this).find("#saletabrateperunit").text() * parseFloat($(this).find("#saletabconv").text()); // $(this).find("#saletabitemsalerate").text();
																																	// //
																																	// changed
																																	// 4.7.2019
			saledetails.purchaseRate = $(this).find("#saletabitempurrate").text();
			saledetails.taxTypeId = $(this).find("#saletabitemtaxtype").text();
			saledetails.isExclusive = $(this).find("#saletabitemtaxtype").text();
			saledetails.retailTypeId=$("#retailtypeinfo").val();
			saledetails.itemdualstock=$(this).find("#saletabdualstock").text();
			saledetails.taxTypeId = $(this).find("#saletabitemtaxtype").text();
			// sales man
		   saledetails.salesmanId=$(this).find("#saletabsalesmanIdComPer").text().split("_")[0];
	    	saledetails.salesmanFactor=$(this).find("#saletabsalesmanIdComPer").text().split("_")[1];
		   saledetails.salesmanAmount=$(this).find("#saletabsalesmanIdComPer").text().split("_")[2];
		   // for free
		  // // saledetails.freeQty=$(this).find("#saletabfree").text();

		   saledetails.itemLotAdjAmount=$(this).find("#saletabitemltadj").text();
		   saledetails.itemFreeAgainstItem=$(this).find("#saletabisfreeitemaginstitem").text();
		   saledetails.saleOrderId=$(this).find("#saleorderid").text();

			// serialno add

			salesDetailsSerialMapperObjArr=[];
			var slnos=$(this).find('#saletabitemslnos').text();
			var slnoarr=slnos.split(",");
			var slnoschk=$(this).find('#saletabitemchkslnosid').text();
			var slnoschkarr=slnoschk.split(",");
			saledetails.tmpMappingId=(i);
			for(var h=0;h<slnoarr.length;h++){
				var salesDetailsSerialMapperObj={};
				salesDetailsSerialMapperObj.itemId=itemid.split("_")[0];
				salesDetailsSerialMapperObj.batchNo=$(this).find('#saletabbat').text();
				salesDetailsSerialMapperObj.uniqueIdentifierNo=slnoarr[h];
				salesDetailsSerialMapperObj.qty=$(this).find('#saletabpqty').text();
				salesDetailsSerialMapperObj.tmpMappingId=(i);
				salesDetailsSerialMapperObj.checkStatus=slnoschkarr[h];
				salesDetailsSerialMapperObjArr.push(salesDetailsSerialMapperObj);
			}
			saledetails.salesDetailsSerialMapper=salesDetailsSerialMapperObjArr;
			saledetails.remarks =$(this).find("#saletabremarks").text(); // new
																			// added
																			// 7.5.2019
			

			// slno end
			allsaledetails.push(saledetails);
		});
		sale.salesDetails = allsaledetails;
		sale.id = saleid;
		if (saleid == 0) {// create

		} else {// edit
			sale.invNo = $("#saleinvno").val();
		}

		sale.invDate = $("#date").val();
		sale.customerDiscPer = $("#salediscount").val();
		if ($("#salecustname").val() == "") {
			sale.customerId = 0;
		} else {
			sale.customerId = $("#salecustid").val();
			sale.customerName = $("#salecustname").val();
			if ($("#salecustph").val() == "") {
				sale.customerPhone = 0000000000;
			} else {
				sale.customerPhone = $("#salecustph").val();
			}
		}
		if ($("#saledocname").val() == "") {
			sale.doctorId = 0;
		} else {
			sale.doctorId = $("#saledocid").val();
			sale.doctorName = $("#saledocname").val();
		}
		sale.grossAmount = $("#totgrossamt").val();
		// alert($("#totmrpamt").val());
		sale.totalMrp = ($("#totmrpamt").val()=='NaN')?0:$("#totmrpamt").val();
		sale.vatAmount = 0;// $("#totvatamt").val();
		sale.taxAmount = $("#totgstamt").val();// $("#tottaxamt").val();
		sale.discAmount = $("#totdiscamt").val();
		sale.roundoff = $("#roundoff").val();
		sale.netAmount = $("#nettot").val();
		sale.remarks = $("#remarks").val();
        // when totltadj > 0 then it take original value else zero
        sale.lotAdjAmount=$("#totltadj").val()>0 ? $("#totltadj").val() : 0.0;

        // when otheradjsment > 0 then it take original value else zero
        sale.othAdjAmount=$("#otheradjasment").val()>0 ? $("#otheradjasment").val() : 0.0;

		sale.qs = $("#qs").val();
		sale.mulSeriesPrefix = $("#mulslid").val();
		if ($("#spcldiscperc").val() == "") {
			sale.specialDiscPer = 0.00;
		} else {
			sale.specialDiscPer = $("#spcldiscperc").val();
		}
		sale.specialDiscAmount = $("#spcldisc").val();
		sale.tenderAmount = $("#tenderamt").val();

		// to add the sale order advanced
		var so_advamt = 0;
		for (var key in sale_order_adv_arr) {
		  console.log("key " + key + " has value " + sale_order_adv_arr[key]);
		  so_advamt += sale_order_adv_arr[key];
		}
		// alert("adv = "+so_advamt);
		sale.cashAmount = Number($("#cashAmt").val()) + Number(so_advamt);
		if(creditcustomer==0)
		sale.creditAmount = 0;
		else
		sale.creditAmount = $("#creditAmt").val();
		sale.cardAmount = $("#cardAmt").val();
		sale.cardExpDate = $("#cardExpMod").val();
		sale.cardFourDigit = $("#cardFourDigitMod").val();
		if ($("#isesi").val() == 1) {
			sale.esiType = $("#esitypeid").val();
			sale.prescriptionIssueDate = $("#pissuedt").val();
			sale.slipNo = $("#reqslno").val();
			sale.esiCode = $("#pcodeid").val();
			sale.prescriptionRegNo = $("#prregno").val();
		}
		sale.printCount = $("#printInv").is(":checked") ? 1 : 0;
		sale.holdFlag = 1;// 1 for pay 0 for hold
		if ($('input[name=payOption]').is(":checked")) {
			// console.log($("input[name=payOption]:checked").val());
			sale.invMode = $("#payOption").val();

		}
		// ret adj amt cal
		sale.adjAmount = $("#payretadjamt").val();

		/*
		 * add on 6_3_2018
		 */
		sale.debitor_ledger_id = $('#debitor_ledger_idf').val();
		sale.sale_ledger_id =  $('#sales_ledger_idf').val();
		sale.duties_ledger_id = $('#duties_ledger_idf').val();
		sale.round_ledger_id =  $('#round_ledger_idf').val();
		sale.discount_ledger_id =  $('#discount_ledger_idf').val();
		sale.debitor_cash_ledger_id= $('#debitor_cahs_ledger_idf').val();
		sale.card_ledger_id=$('#card_ledger_idf').val();

		// for same page return
		var cval = $("#confirmvalrsp").val();

		if (cval == 0) {

		} else {
			var cc = cval.split(",");
			for ( var i = 0; i < cc.length; i++) {
				// console.log("ret id=" + cc[i]);
				var CommonResultSetMapper = {};
				CommonResultSetMapper.saleReturnId = cc[i];
				var ajaxCallObjectret = new CustomBrowserXMLObject();
				ajaxCallObjectret.callAjaxPost(BASE_URL + "/retunmemo/getsaleretdetbyid.htm", CommonResultSetMapper, function(res) {
					var retdetails = JSON.parse(res);
					var salereturndetailssamepage = {};
					salereturndetailssamepage.id = retdetails.saleReturnId;
					salereturndetailssamepage.invNo = retdetails.invNo;
					salereturndetailssamepage.invDate = moment(retdetails.invDate).format('YYYY-MM-DD');// invdate
					salereturndetailssamepage.adjAmount = retdetails.netAmount;// adjustment
																				// amt
					allsalereturndetails.push(salereturndetailssamepage);
				});
			}
		}
		// var selretitemlength = $('#retadjtable >tbody >tr').length;
		$('#retadjtable > tbody  > tr').each(function() {
			var salereturndetails = {};
			var saleretid = this.id;
			var invno = $(this).find("td:eq(0)").text();
			salereturndetails.id = saleretid;
			salereturndetails.invNo = invno;
			salereturndetails.invDate = $(this).find("td:eq(1)").text();// invdate
			salereturndetails.adjAmount = $(this).find("td:eq(4)").text();// adjustment
																			// amt
			allsalereturndetails.push(salereturndetails);
		});

		sale.saleReturns = allsalereturndetails;

		/*
		 * setTimeout(function() { console.log("sale json: " +
		 * JSON.stringify(sale)); }, 200);
		 */

		if (selitemlength > 0 && notvallid > 0) {
			$("#payModal").modal("hide");
			$('#pleasewaitModal').modal('show');
			setTimeout(function() {
				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/pos/createorupdatesalesinvoice.htm", sale, function(response) {
					$('#pleasewaitModal').modal('hide');
					// console.log("save sale inv id=" + response);
					if (response == '0') {
						document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataNotPaid;
						$("#confirmval").val(0);
						showConfirmModal();
					} else {
						// alert(val);
						if ($('input[name=emailInv]').is(":checked")) { // new
																		// added
																		// 10.5.2019
							sendInvEmail(response);
						}
						
						if ($('input[name=printInv]').is(":checked")) {
							if (dotMatrixPrint == 1) {
								$('#pleasewaitModal').modal('show');
								var CommonResultSetMapper = {};
								CommonResultSetMapper.saleId = response;
								CommonResultSetMapper.noteLineOne = n2;
								CommonResultSetMapper.noteLineTwo = n1;
								CommonResultSetMapper.isRePrint = 0;
								var ajaxCallObject = new CustomBrowserXMLObject();
								ajaxCallObject.callAjaxPost(BASE_URL + "/pos/salebillprint.htm", CommonResultSetMapper, function(res) {
									$('#pleasewaitModal').modal('hide');
									location.href = BASE_URL + "/pos/cashmemo.htm";

								});

							} else {
								var val=$("#setprinter").val();
								if(val==1)
								location.href = BASE_URL + "/reprintmemo/cashmemo.htm?reprint=N&backUrl=pos/cashmemo&saleId=3465fg-trw73sxz-" + response + "-utew09-qdd55-4320jhhgrt";
								else
								location.href = BASE_URL + "/reprintmemo/cashmemo80mm.htm?reprint=N&backUrl=pos/cashmemo&saleId=3465fg-trw73sxz-" + response + "-utew09-qdd55-4320jhhgrt";
							}

						} else {
							document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataSucPaid;
							$("#confirmval").val(response);
							showConfirmModal();
						}
					}

				});
			}, 300);
		}
	} else {
		$("#payModal").modal("hide");
		$("#confirmval").val(-1);
		document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.addEditChckBefrSave;
		showConfirmModal();
	}
}

function getItemByContent(contentid) {
	var itemsbycontentidtbl = $('#itemsbycontentidtbl').DataTable();
	itemsbycontentidtbl.destroy();
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/item/getitemsbycontent/" + contentid + ".htm", function(resp) {

		$("#itemsbycontentid").text("");
		var itemsbycontent = JSON.parse(resp);
		// alert(itemdetail.length);
		var totstkitm = 0;
		var totlooseitm = 0;
		var conv = 0;
		var totlooseqty = 0;
		if (itemsbycontent.length == 0) {
			$("#contentnotmatchdiv").removeClass("hide");
			$("#contentnotfoundname").text($("#content_Dets").val());
			$("#modsearchedcontent").text($("#content_Dets").val());
			$("#contentnotmatchid").val(contentid);
			$("#itemsearchtable").addClass("hide");

		} else {
			$("#contentnotmatchdiv").addClass("hide");
			$("#itemsearchtable").removeClass("hide");
			$("#modsearchedcontent").text($("#content_Dets").val());
			for ( var i = 0; i < itemsbycontent.length; i++) {
				var genericitem = itemsbycontent[i];
				var starttrline = "<tr id='" + genericitem.itemId + "' style='cursor: pointer;' onclick='getGenericItems(" + JSON.stringify(genericitem) + ")'>";
				var itemname = "<td >" + genericitem.itemName + "</td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + itemname + endtrline;
				$("#itemsbycontentid").append(createdrowline);
			}

		}
		$("#itemsByContentModal").modal("show");

		$('#itemsbycontentidtbl').DataTable({
			"lengthChange" : false,
			"pagingType" : "full",
		});

	}, null);

}

function getItemByManufacture(manuid) {
	$('#itemsbymanufactureidtbl').DataTable().clear();
	$('#itemsbymanufactureidtbl').DataTable().destroy();
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/item/getitemsbymanufacturer/" + manuid + ".htm", function(resp) {

		$("#itemsbymanufacture").text("");
		var itemsbymanufacture = JSON.parse(resp);
		// alert(itemdetail.length);
		var totstkitm = 0;
		var totlooseitm = 0;
		var conv = 0;
		var totlooseqty = 0;
		if (itemsbymanufacture.length == 0) {
			$("#manufacturenotmatchdiv").removeClass("hide");
			$("#manufacturernotfoundname").text($("#item_mfg").val());
			$("#modsearchedmanufacture").text($("#item_mfg").val());
			$("#manufacturenotmatchid").val(manuid);
			$("#itemsearchtablemanu").addClass("hide");

		} else {
			$("#manufacturenotmatchdiv").addClass("hide");
			$("#itemsearchtablemanu").removeClass("hide");
			$("#modsearchedmanufacture").text($("#item_mfg").val());
			for ( var i = 0; i < itemsbymanufacture.length; i++) {
				var genericitem = itemsbymanufacture[i];
				var starttrline = "<tr id='" + genericitem.itemId + "' style='cursor: pointer;' onclick='getManufacturerItems(" + JSON.stringify(genericitem) + ")'>";
				var itemname = "<td >" + genericitem.itemName + "</td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + itemname + endtrline;
				$("#itemsbymanufactureid").append(createdrowline);
			}

		}
		$("#itemsByManufactureModal").modal("show");

		$('#itemsbymanufactureidtbl').DataTable({
			"lengthChange" : false,
			"pagingType" : "full",
		});

	}, null);

}
function getManufacturerItems(genericitem) {
	$('#itemsbymanufactureidtbl').DataTable().clear();
	$('#itemsbymanufactureidtbl').DataTable().destroy();
	$("#itemsByManufactureModal").modal("hide");
	$("#item_name").val(genericitem.itemName.split("::")[0]);
	getItemDetails(genericitem.itemId);
}



function getGenericItems(genericitem) {
	$("#itemsByContentModal").modal("hide");
	$("#item_name").val(genericitem.itemName.split("::")[0]);
	getItemDetails(genericitem.itemId);
}

/**
 * checking current stock by item id
 */
function getItemDetails(itemid) {
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/stock/getcurrstock/" + itemid + ".htm", function(resp) {
		/* console.log("respdata:"+resp); */
		/*
		 * console.log(resp); createItemStockDetails(resp, itemid);
		 * $("#stkdetModal").modal("show");
		 */
		var itemdetail = JSON.parse(resp);
	// console.log("___current_stock___resp="+resp);
		if(itemdetail.length==1){// when no stock
			for ( var i = 0; i < itemdetail.length; i++) {
				var itmstkdet = itemdetail[i];
				// console.log(JSON.stringify(itmstkdet));
			getClickeditmdet(itmstkdet);// for set the sale table value
			}
		}else{// when stock present
			createItemStockDetails(resp, itemid);// if batch present then it
													// will open modal
			$("#stkdetModal").modal("show");
		}


	}, null);
}

function createItemStockDetails(itemstkdetails,
								itemid) {
	// $("#snditmtopodiv").addClass("hide");
	$("#snditmpo").addClass("hide");
	// console.log("itemstkdetails="+itemstkdetails);
	$("#itemdetails").text("");
	// $("#moditemname").text($("#item_name").val());
	var itemdetail = JSON.parse(itemstkdetails);
	// alert(itemdetail.length);
	// console.log("itemdetail="+itemdetail);
	var totstkitm = 0;
	var totlooseitm = 0;
	var conv = 0;
	var totlooseqty = 0;
	var totloosereorderlevelqty = 0;
	$("#senditemtopoid").val(itemid);
	if (itemdetail.length == 0) {
		// $("#snditmtopodiv").removeClass("hide");
		// $("#snditmpo").addClass("hide");
		$("#itemnotfounddiv").removeClass("hide");
		$("#itemnotfoundname").text($("#item_name").val());
		$("#itemnotfoundid").val(itemid);
		$("#modtable").addClass("hide");

	} else {
		$("#itemnotfounddiv").addClass("hide");
		$("#modtable").removeClass("hide");
		for ( var i = 0; i < itemdetail.length; i++) {
			var itmstkdet = itemdetail[i];
			$("#moditemname").text(itmstkdet.itemName);
			// $("#item_name").text(itmstkdet.itemName);
			$("#modmanufname").text(itmstkdet.manufacturerName);
			$("#modcontentname").text(itmstkdet.contentName);
			$("#modrackname").text(itmstkdet.rackName);
			$("#modgroupname").text(itmstkdet.groupName);
			$("#moditemnote").text(itmstkdet.note);
			if (itmstkdet.stockQty == 0) {
				// $("#snditmpo").addClass("hide");
				// $("#snditmtopodiv").removeClass("hide");
				$("#itemnotfounddiv").removeClass("hide");
				$("#itemnotfoundname").text($("#item_name").val());
				$("#itemnotfoundid").val(itemid);
				$("#modtable").addClass("hide");
			} else {
				totstkitm = Number(totstkitm) + parseFloat(itmstkdet.stockQty);
				// console.log("looseQty=" + parseFloat(itmstkdet.looseQty));
				totlooseitm = Number(totlooseitm) + parseFloat(itmstkdet.looseQty);
				totlooseqty = Number(totlooseqty) + parseFloat(itmstkdet.conversion * itmstkdet.packQty + Number(itmstkdet.looseQty));
				conv = parseFloat(itmstkdet.conversion);
				totloosereorderlevelqty = Number(itmstkdet.claculateLooseReorderLevelQty);
				var starttrline = "";
				var expiryDateFormat = "";
				if (itmstkdet.expiryStatusMode == 1) {
					starttrline = "<tr id=" + itmstkdet.itemUniqueKey + " class='schx'>";
					if(itmstkdet.expiryDateFormat==""||itmstkdet.expiryDateFormat==undefined){
						expiryDateFormat = "<td>NA</td>";
					}else{
						expiryDateFormat = "<td>" + itmstkdet.expiryDateFormat + "(" + itmstkdet.expiryStatus + ")</td>";
					}

				} else {
					starttrline = "<tr id=" + itmstkdet.itemUniqueKey + " style='cursor: pointer;' onclick='javascript:getClickeditmdet(" + JSON.stringify(itmstkdet) + ")'>";
					if(itmstkdet.expiryDateFormat==""||itmstkdet.expiryDateFormat==undefined){
						expiryDateFormat = "<td>NA</td>";
					}else{
						expiryDateFormat = "<td>" + itmstkdet.expiryDateFormat + "</td>";
					}

				}
				var batchno = "<td>" + itmstkdet.batchNo + "</td>";

				var stockQty = "<td>" + itmstkdet.stockQty + "</td>";
				var holdQty = "<td>" + itmstkdet.holdQty + "</td>";
				var mrppack = "<td>" + parseFloat(itmstkdet.mrp).toFixed(2) + "</td>";
				var mrp = "<td>" + parseFloat(itmstkdet.mrp / itmstkdet.conversion).toFixed(2) + "</td>";
				var conversion = "<td>" + itmstkdet.conversion + "</td>";
				var packing = "<td>" + itmstkdet.netContent + "</td>";
				var purrate = "<td>" + itmstkdet.purchaseRate + "</td>";
				var srate =0;
				/*
				 * if(itmstkdet.priceListRequired==1){ srate = "<td>" +
				 * itmstkdet.saleRate + "</td>"; }else{ srate = "<td>0</td>"; }
				 */
				srate = "<td>" + itmstkdet.saleRate + "</td>";
				var endtrline = "</tr>";
// createdrowline = starttrline + batchno + expiryDateFormat + stockQty +
// holdQty + mrppack + mrp + conversion + packing+purrate+srate + endtrline;
				createdrowline = starttrline + batchno + expiryDateFormat + stockQty + holdQty +  mrppack + purrate+srate + endtrline;
				$("#itemdetails").append(createdrowline);
			}
			// console.log("totlooseqty="+totlooseqty);
			// $("#totalcurrstkitm").text(totstkitm + "/" + totlooseitm + " [ "
			// + (totstkitm * conv + totlooseitm) + " ]");
			$("#totalcurrstkitm").text(totstkitm + "/" + totlooseitm + " [ " + (totlooseqty) + " ]");

			$("#itemstockdetails>tbody>tr:first").addClass('rowActive');
		}
		/*
		 * if (totlooseqty <= totloosereorderlevelqty) {
		 * $("#snditmtopodiv").removeClass("hide");
		 * $("#itemnotfoundid").val(itemid); } else {
		 * $("#snditmtopodiv").addClass("hide");
		 * $("#snditmpo").addClass("hide"); }
		 */
	}

}

function selectRow(newRow) {
	// make sure the parameter is a jQuery object
	newRow = $(newRow);

	// exit early if we don't have a new row
	if (newRow.length == 0)
		return true;

	// unselect the old row
	var oldRow = $('.rowActive');
	oldRow.removeClass('rowActive');

	// select the new row
	newRow.addClass('rowActive');

	// keep track of the id
	// $('.pane h2').text(newRow.attr('id'));

	// we could use location.hash = '#' + newRow.attr('id'), but instead we will
	// do...

	// scrolling magic
	var rowTop = newRow.position().top;
	var rowBottom = rowTop + newRow.height();
	var $table = $('#itemstockdetails'); // store instead of calling twice
	var tableHeight = $table.height();
	var currentScroll = $table.scrollTop();

	if (rowTop < 0) {
		// scroll up
		$('#itemstockdetails').scrollTop(currentScroll + rowTop);
	} else if (rowBottom > tableHeight) {
		// scroll down
		var scrollAmount = rowBottom - tableHeight;
		$('#itemstockdetails').scrollTop(currentScroll + scrollAmount);
	}

	return false;
}

$('#stkdetModal').keydown(function(e) {
	var currentRow = $('.rowActive');
	if (e.keyCode == 40) {
		return selectRow(currentRow.next());
	}
	if (e.keyCode == 38) {
		return selectRow(currentRow.prev());
	}
});

$('#stkdetModal').on('shown.bs.modal', function() {
	// $("#itemstockdetails>tbody>tr:first").addClass('rowActive');
});


// Purchase History start
function getpurhistoryofitem(itemId,batchNo)
{
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/purinv/getpurhistoryofitem/" + itemId +"/"+ batchNo +"/0.htm", function(resp) {
	// ajaxCallObject.callAjax(BASE_URL + "/purinv/getpurhistoryofitem/" +
	// itemId + "/0.htm", function(resp) {
		var obj = jQuery.parseJSON(resp);
		createItemPurHistoryDetails(obj);
	}, null);
}

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
			// expdtfrmt = "<td>" +itemPurHis.expiryDateFormat + "</td>";
			expdtfrmt ="<td>NA</td>";
		}else{
			expdtfrmt = "<td>" +itemPurHis.expiryDateFormat + "</td>";
		}

		var purqty = "<td>" +itemPurHis.packQty + "</td>";
		var freeqty = "<td>" +itemPurHis.freeQty + "</td>";
		var lotAdjAmt = "<td>" +itemPurHis.itemLotAdjAmount + "</td>";
		var uom = "<td>" +itemPurHis.packUnitName + "</td>";
		var mrp = "";
		/*
		 * if($("#isMrpEnableFlag").val()==1) {
		 */
			mrp = "<td>" +parseFloat(itemPurHis.mrp).toFixed(2) + "</td>";
		/*
		 * } else { mrp = "<td class='hide'>"
		 * +parseFloat(itemPurHis.mrp).toFixed(2) + "</td>"; }
		 */
		var rate = "<td>" +parseFloat(itemPurHis.rate).toFixed(2) + "</td>";
		var discper = "<td>" +parseFloat(itemPurHis.discPer).toFixed(2) + "</td>";
		var amt = "<td>" +parseFloat(itemPurHis.amount).toFixed(2) + "</td>";
		var endtrline = "</tr>";
		createdrowline = starttrline + invno + invdate + vendorName + batchno + expdtfrmt + purqty + freeqty + lotAdjAmt + uom + mrp + rate + discper + amt + endtrline;
		$("#itmpurhistboby").append(createdrowline);
	}
}

function closePurHisDet(){
	$("#purHistoryDiv").addClass("hide");
}

// Purchase history end

/*
 * set the value for item search table and div
 */
function getClickeditmdet(clickitmdet) {
    console.log("clickitmdet:"+JSON.stringify(clickitmdet));
	if(clickitmdet.itemFreeAgainstItem==1) {
		$('#isfreeiai').prop("checked", true);
		itemAgainstItem();
	}

	
	// console.log("clickitmdet="+JSON.Stringify(clickitmdet));
	if(clickitmdet.conversion==0){
		clickitmdet.conversion=1;
	}
	/* alert(clickitmdet.itemUniqueKey); */
	$("#item_id").val(clickitmdet.itemUniqueKey);
	$("#item_name").val(clickitmdet.itemName);
	$("#item_code").val(clickitmdet.itemCode);// New Added 7.5.2019
	$("#item_barcode").val(clickitmdet.sku);
	$("#item_batch").val(clickitmdet.batchNo);
	$("#current_stock").val(parseInt(clickitmdet.stockQty));
	$("#item_sale_rate_ls").val(parseFloat(clickitmdet.saleRate));
	
	$("#item_exp").val(clickitmdet.expiryDateFormat);
	$("#item_expDate").val(moment(clickitmdet.expiryDate).format('YYYY-MM-DD'));
	$("#item_packunitid").val(clickitmdet.packUnitId);
	$("#item_looseunitid").val(clickitmdet.packUnitId);
	$("#item_stockedqty").val(clickitmdet.packQty);
	$("#item_mrp_pack").val(parseFloat(clickitmdet.mrp).toFixed(4));
	$("#item_mrp").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
	$("#item_rate_ls").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
	$("#item_rate_ls_hid").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
	$('#item_pqty').val(0);
	$('#item_lqty').val(0);
	$("#item_saleorderid").val(0);
	var isdis = clickitmdet.isDiscount;
	if (isdis == 0) {
		$("#item_dis").val(parseFloat(0).toFixed(4));
		$("#item_dis_td").hide();
		$("#dis_label").hide();
		$("#item_discount").val(0);
	} else {
		$("#item_dis_td").show();
		$("#dis_label").show();
		$("#item_dis").val(parseFloat(clickitmdet.discount).toFixed(4));
		$("#item_discount").val(clickitmdet.discount);
		$("#item_maxdisper").val(parseFloat(clickitmdet.maxDiscountLimit).toFixed(4));
	}
	// $("#item_dis").val(parseFloat(0).toFixed(4));//clickitmdet.itemId
	$("#item_vat").val(parseFloat(clickitmdet.vatPer).toFixed(4));
	$("#item_tax").val(parseFloat(clickitmdet.taxPer).toFixed(4));
	$("#item_mfg").val(clickitmdet.manufacturerName);
	$('#item_mfg').prop('title', clickitmdet.manufacturerName);
	$("#item_content").val(clickitmdet.contentName);
	$("#itemContent").val(clickitmdet.contentName);
	$("#itemContent").val(clickitmdet.contentName);
	$("#content_Dets").val(clickitmdet.contentName);
	$('#item_content').prop('title', clickitmdet.contentName);
	$("#item_conv").val(clickitmdet.conversion);
	$("#item_sche").val(clickitmdet.scheduleName);
	$("#item_taxId").val(clickitmdet.taxId);
	$("#item_taxPercentage").val(clickitmdet.taxPercentage);
	$("#item_isGroupTax").val(clickitmdet.isGroupTax);
	// $("#item_dis").val(parseFloat(clickitmdet.discount).toFixed(4));
	// $("#item_discount").val(clickitmdet.discount);
	$("#item_isDiscount").val(clickitmdet.isDiscount);
	$("#item_maxDiscountLimit").val(clickitmdet.maxDiscountLimit);
	$("#item_taxMode").val(clickitmdet.taxMode);
	$("#item_hsnCode").val(clickitmdet.hsnCode);
	$("#item_purCost").val(clickitmdet.purchaseCostPerUnit);
	$("#item_purCostperUnit").val(clickitmdet.purchaseCostPerUnit);
	$("#item_is_slno").val(clickitmdet.serialNoRequired);
	$("#item_taxtype").val(clickitmdet.taxTypeId);
	var salerate = clickitmdet.saleRate;
	// var isexclu = $("#isexclusive").val();
 	var isexclu = clickitmdet.taxTypeId;
	// alert(isexclu);
	if (salerate == 0) {
		if (isexclu == 2) {// for exclusive total =mrp + mrp*gst;
			/*
			 * var mop = (100 * clickitmdet.mrp) / (100 +
			 * clickitmdet.taxPercentage);
			 * $("#item_sale_rate").val(parseFloat(mop).toFixed(4));
			 * $("#item_rate_ls").val(parseFloat(mop /
			 * clickitmdet.conversion).toFixed(4));
			 * $("#item_rate_ls_hid").val(parseFloat(mop /
			 * clickitmdet.conversion).toFixed(4));
			 */
			$("#item_sale_rate").val(parseFloat(clickitmdet.mrp).toFixed(4));
			$("#item_rate_ls").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
			$("#item_rate_ls_hid").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
		} else {// for inclusive total = ptr ( in ptr gst is included
			$("#item_sale_rate").val(parseFloat(clickitmdet.mrp).toFixed(4));
			$("#item_rate_ls").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
			$("#item_rate_ls_hid").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
		}

	} else {
		$("#item_sale_rate").val(parseFloat(clickitmdet.saleRate).toFixed(4));
		$("#item_rate_ls").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
		$("#item_rate_ls_hid").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
	}

	$("#item_purChase_rate").val(clickitmdet.purchaseRate);


	/*
	 * for dual stock
	 */
	if(clickitmdet.dualStockRequired==null||clickitmdet.dualStockRequired==0){
		$("#item_dualStockRequired_req").val(0);
		$("#ratio_val").addClass('hide');
		$("#ratio_label").addClass('hide');
		// $("#ratio").val(itemdetval.conversion);
	}else if(clickitmdet.dualStockRequired==1){
		$("#item_dualStockRequired_req").val(1);
		$("#ratio_val").removeClass('hide');
		$("#ratio_label").removeClass('hide');
	// $("#ratio").val(itemdetval.conversion);
	}else{
		$("#item_dualStockRequired_req").val(0);
		$("#ratio_val").removeClass('hide');
		$("#ratio_label").removeClass('hide');
	// $("#ratio").val(itemdetval.conversion);
	}
	/*
	 * if(clickitmdet.locationRequired==null||clickitmdet.locationRequired==0){
	 * var seslocid=clickitmdet.locationId; //alert(seslocid);
	 * $("#location_label").addClass('hide');
	 * $("#locationval_td").addClass('hide'); // $("#location").val(seslocid);
	 * $("#location > [value=" + seslocid + "]").attr("selected", "true");
	 * $("#location").prop('disabled', false); }else
	 * if(clickitmdet.locationRequired==1){ var seslocid=clickitmdet.locationId;
	 * //alert(seslocid); $("#location_label").removeClass('hide');
	 * $("#locationval_td").removeClass('hide'); //
	 * $("#location").val(seslocid); $("#location > [value=" + seslocid +
	 * "]").attr("selected", "true"); $("#location").prop('disabled',
	 * 'disabled'); }else{ var seslocid=clickitmdet.locationId;
	 * $("#location_label").removeClass('hide');
	 * $("#locationval_td").removeClass('hide'); $("#location > [value=" +
	 * seslocid + "]").removeAttr("selected"); $("#location").prop('disabled',
	 * false); }
	 */
	/*
	 * 
	 * 
	 * if(clickitmdet.batchWiseStock==null||clickitmdet.batchWiseStock==0){
	 * $("#item_batchWiseStock_req").val(0); $("#batch_no_td").addClass('hide');
	 * $("#batch_label").addClass('hide'); //$("#batch_no").val(0); }else
	 * if(clickitmdet.batchWiseStock==1){ $("#item_batchWiseStock_req").val(1);
	 * $("#batch_no_td").removeClass('hide');
	 * $("#batch_label").removeClass('hide'); //$("#batch_no").val(0); }else{
	 * $("#item_batchWiseStock_req").val(0);
	 * $("#batch_no_td").removeClass('hide');
	 * $("#batch_label").removeClass('hide'); //$("#batch_no").val(0); }
	 * 
	 * if(clickitmdet.expiryDateRequired==null||clickitmdet.expiryDateRequired==0){
	 * $("#item_expiryDateRequired_req").val(0); $("#exp_td").addClass('hide');
	 * $("#exp_label").addClass('hide'); //$("#exp").val(); }else
	 * if(clickitmdet.expiryDateRequired==1){
	 * $("#item_expiryDateRequired_req").val(1);
	 * $("#exp_td").removeClass('hide'); $("#exp_label").removeClass('hide');
	 * //$("#exp").val(); }else{ $("#item_expiryDateRequired_req").val(0);
	 * $("#exp_td").removeClass('hide'); $("#exp_label").removeClass('hide'); //
	 * $("#exp").val(); }
	 * 
	 * if(clickitmdet.priceListRequired==null||clickitmdet.priceListRequired==0){
	 * $("#item_priceListRequired_req").val(0); $("#mrp_td").addClass('hide');
	 * $("#mrp_label").addClass('hide'); }else
	 * if(clickitmdet.priceListRequired==1){
	 * $("#item_priceListRequired_req").val(1);
	 * $("#mrp_td").removeClass('hide'); $("#mrp_label").removeClass('hide');
	 * }else{ $("#item_priceListRequired_req").val(0);
	 * $("#mrp_td").removeClass('hide'); $("#mrp_label").removeClass('hide'); }
	 * if(clickitmdet.mrpRequired==null||clickitmdet.mrpRequired==0){
	 * $("#item_mrpRequired_req").val(0); $("#mrp_td").addClass('hide');
	 * $("#mrp_label").addClass('hide'); }else if(clickitmdet.mrpRequired==1){
	 * $("#item_mrpRequired_req").val(1); $("#mrp_td").removeClass('hide');
	 * $("#mrp_label").removeClass('hide'); }else{
	 * $("#item_mrpRequired_req").val(0); $("#mrp_td").removeClass('hide');
	 * $("#mrp_label").removeClass('hide'); }
	 * if(clickitmdet.sizeWiseStockRequired==null||clickitmdet.sizeWiseStockRequired==0){
	 * $("#Sizelabel_td").addClass('hide'); $("#size_td").addClass('hide');
	 * $("#size > [value=" + clickitmdet.size + "]").attr("selected", "true");
	 * $("#size").prop('disabled', false); }else
	 * if(clickitmdet.sizeWiseStockRequired==1){
	 * $("#Sizelabel_td").removeClass('hide');
	 * $("#size_td").removeClass('hide'); $("#size > [value=" + clickitmdet.size +
	 * "]").attr("selected", "true"); $("#size").prop('disabled', 'disabled');
	 * }else{ $("#Sizelabel_td").removeClass('hide');
	 * $("#size_td").removeClass('hide'); $("#size > [value=" + clickitmdet.size +
	 * "]").removeAttr("selected"); $("#size").prop('disabled', false); }
	 * 
	 * if(clickitmdet.colourWiseStockRequired==null||clickitmdet.colourWiseStockRequired==0){
	 * $("#Colorlabel_td").addClass('hide'); $("#color_td").addClass('hide'); //
	 * $("#colour").val(0); $("#colour > [value=" + clickitmdet.colour +
	 * "]").attr("selected", "true"); $("#colour").prop('disabled', false);
	 * }else if(clickitmdet.colourWiseStockRequired==1){
	 * $("#Colorlabel_td").removeClass('hide');
	 * $("#color_td").removeClass('hide'); // $("#colour").val(0); $("#colour >
	 * [value=" + clickitmdet.colour + "]").attr("selected", "true");
	 * $("#colour").prop('disabled', 'disabled'); }else{
	 * $("#Colorlabel_td").removeClass('hide');
	 * $("#color_td").removeClass('hide'); // $("#colour").val(0); $("#colour >
	 * [value=" + clickitmdet.colour + "]").removeAttr("selected");
	 * $("#colour").prop('disabled', false); }
	 * if(clickitmdet.warrantyRequired==null||clickitmdet.warrantyRequired==0){
	 * $("#wtypeval_td").addClass('hide'); $("#wmonthval_td").addClass('hide');
	 * $("#wtypelabel_td").addClass('hide');
	 * $("#wmonthlabel_td").addClass('hide');
	 * $("#warrantymonth").val(clickitmdet.warrantyMonth); }else{
	 * $("#wtypeval_td").removeClass('hide');
	 * $("#wmonthval_td").removeClass('hide');
	 * $("#wtypelabel_td").removeClass('hide');
	 * $("#wmonthlabel_td").removeClass('hide');
	 * $("#warrantymonth").val(clickitmdet.warrantyMonth); }
	 */





	if(clickitmdet.itemUniqueKey!=0){
		if(clickitmdet.serialNoRequired==0){
			$('#item_pqty').val(1);
			calculateDefaultvalue();
		}
	}

	$("#stkdetModal").modal("hide");
	$('#weival').addClass("callweival");
	if (autoadd_barcodeflag==1) {
		addOrUpdateItemToDetailsTable(0);
	}

	if (sale_order_chain==1) {
		$("#item_pqty").val(sale_order_chain_qty);
		$("#item_saleorderid").val(sale_order_chain_ordid);
		addOrUpdateItemToDetailsTable(0);
		// closePurHisDet();
		$("#purHistoryDiv").addClass("hide");
		sale_order_chain=0;
		sale_order_chain_qty=0;
		sale_order_chain_ordid=0;
		// $("#item_saleorderid").val(sale_order_chain_ordid);
	}
}

function itemHeaderDivView(itemid) {
	$("#item_id").val(itemid);
	$('#item_name').prop('readonly', true);
	
	$('#item_barcode').prop('readonly', true);
	$('#itemContent').prop('readonly', true);
	$('#item_mfg').prop('readonly', true);
	$("#item_name").val($("#selitem tr#" + itemid).find('#saletabname').text());
	$("#item_barcode").val($("#selitem tr#" + itemid).find('#saletabitembarcode').text());
	$("#item_batch").val($("#selitem tr#" + itemid).find('#saletabbat').text());
	$("#item_exp").val($("#selitem tr#" + itemid).find('#saletabexpdt').text());
	$("#item_pqty").val($("#selitem tr#" + itemid).find('#saletabpqty').text());
	$("#item_sale_rate_ls").val($("#selitem tr#" + itemid).find('#saletabamt').text());
	$("#current_stock").val($("#selitem tr#" + itemid).find('#saletabcurrentStock').text());
	$("#item_receive_qty").val($("#selitem tr#" + itemid).find('#saletabreceivepqty').text());
	
	$("#item_looseunitid").val($("#selitem tr#" + itemid).find('#saletablunitid').text());
	$("#item_mrp_pack").val($("#selitem tr#" + itemid).find('#saletabmrppack').text());
	$("#item_mrp").val($("#selitem tr#" + itemid).find('#saletabmrpperunit').text());
	$("#item_rate_ls").val($("#selitem tr#" + itemid).find('#saletabrateperunit').text());
	$("#item_rate_ls_hid").val($("#selitem tr#" + itemid).find('#saletabrate').text());
	$("#item_dis").val($("#selitem tr#" + itemid).find('#saletabdiscperc').text());
	$("#item_discamt").val($("#selitem tr#" + itemid).find('#saletabdisc').text());
	$("#item_vat").val($("#selitem tr#" + itemid).find('#saletabvatperc').text());
	$("#item_vatamt").val($("#selitem tr#" + itemid).find('#saletabvat').text());
	$("#item_tax").val($("#selitem tr#" + itemid).find('#saletabtaxperc').text());
	$("#item_taxamt").val($("#selitem tr#" + itemid).find('#saletabtax').text());
	$("#item_tot").val($("#selitem tr#" + itemid).find('#saletabtotamt').text());
	$("#item_mfg").val($("#selitem tr#" + itemid).find('#saletabmanname').text());
	$("#item_content").val($("#selitem tr#" + itemid).find('#saletabcontent').text());
	


	
	$("#item_conv").val($("#selitem tr#" + itemid).find('#saletabconv').text());
	$("#content_Dets").val($("#selitem tr#" + itemid).find('#saletabcontent').text());
	$("#itemContent").val($("#selitem tr#" + itemid).find('#saletabcontent').text());
	$("#content_id").val($("#selitem tr#" + itemid).find('#saletabcontentid').text());
	$("#item_taxId").val($("#selitem tr#" + itemid).find('#saletabtaxId').text());
	$("#item_taxPercentage").val($("#selitem tr#" + itemid).find('#saletabtaxPercentage').text());
	$("#item_isGroupTax").val($("#selitem tr#" + itemid).find('#saletabisGroupTax').text());
	$("#item_discount").val($("#selitem tr#" + itemid).find('#saletabdiscount').text());
	$("#item_expDate").val($("#selitem tr#" + itemid).find('#saletabexpdate').text());
	$("#item_dualStockRequired_req").val($("#selitem tr#" + itemid).find('#saletabdualstock').text());
  var ss=$("#selitem tr#" + itemid).find('#saletabsalesmanIdComPer').text().split("_")[0]+"_"+$("#selitem tr#" + itemid).find('#saletabsalesmanIdComPer').text().split("_")[1];
  if ($("#selitem tr#" + itemid).find('#saletabsalesmanIdComPer').text().split("_")[0]==0) {
	  $("#salemanslist").val(0);
  }else {
	  $("#salemanslist").val($("#selitem tr#" + itemid).find('#saletabsalesmanIdComPer').text().split("_")[0]+"_"+$("#selitem tr#" + itemid).find('#saletabsalesmanIdComPer').text().split("_")[1]);
  }

	$("#salesman_id").val($("#selitem tr#" + itemid).find('#saletabsalesmanIdComPer').text().split("_")[0]);
	$("#salesman_per").val($("#selitem tr#" + itemid).find('#saletabsalesmanIdComPer').text().split("_")[1]);
	$("#salesman_amt").val($("#selitem tr#" + itemid).find('#saletabsalesmanIdComPer').text().split("_")[2]);

	$("#isfreeiai").prop('checked',$("#selitem tr#" + itemid).find('#saletabisfreeitemaginstitem').text()==1?true:false);


	if ($("#selitem tr#" + itemid).find('#saletabdualstock').text()==1) {
	     $("#ratio_val") .removeClass("hide");
	     $("#ratio_label").removeClass("hide");

		}else
			{
			  $("#ratio_val") .addClass("hide");
			  $("#ratio_label").addClass("hide");
			}

	var isDis = $("#selitem tr#" + itemid).find('#saletabisDiscount').text();
	if (isDis == 0) {
		$("#item_dis").val(parseFloat(0).toFixed(4));
		$("#item_dis_td").hide();
		$("#dis_label").hide();
	} else {
		$("#item_dis_td").show();
		$("#dis_label").show();
		$("#item_isDiscount").val($("#selitem tr#" + itemid).find('#saletabisDiscount').text());
	}

	$("#item_maxDiscountLimit").val($("#selitem tr#" + itemid).find('#saletabmaxDiscountLimit').text());
	$("#item_CalcTaxAmt").val($("#selitem tr#" + itemid).find('#saletabitemcalcgstamt').text());
	$("#item_taxMode").val($("#selitem tr#" + itemid).find('#saletabitemtaxmode').text());
	$("#item_hsnCode").val($("#selitem tr#" + itemid).find('#saletabitemhsncode').text());
	$("#item_purCost").val($("#selitem tr#" + itemid).find('#saletabpurcost').text());
	$("#item_purCostperUnit").val($("#selitem tr#" + itemid).find('#saletabpurcostperunit').text());
	$("#item_sale_rate").val($("#selitem tr#" + itemid).find('#saletabitemsalerate').text());
	$("#item_purChase_rate").val($("#selitem tr#" + itemid).find('#saletabitempurrate').text());
	$("#item_ltadj").val($("#selitem tr#" + itemid).find('#saletabitemltadj').text());
	$("#item_is_slno").val($("#selitem tr#" + itemid).find('#saletabitemisslno').text());
	$("#item_taxtype").val($("#selitem tr#" + itemid).find('#saletabitemtaxtype').text());
	$("#item_saleorderid").val($("#selitem tr#" + itemid).find('#saleorderid').text());
	
	$("#item_code").val($("#selitem tr#" + itemid).find('#saletabcode').text()); // new
																					// added
																					// 7.5.2019
	$("#itmremarks").val($("#selitem tr#" + itemid).find('#saletabremarks').text()); // new
																						// added
																						// 7.5.2019
	
	var slnos=$("#selitem tr#" + itemid).find('#saletabitemslnos').text();
// alert("slnos="+slnos);
	var sno=slnos.split(",");
	for(var i=0;i<sno.length;i++){
		// alert("slnos="+sno[i]);
		$("#textbox"+(i+1)).val(sno[i]);
	}

	$("#add_btn").addClass("hide");
	$("#edit_btn").removeClass("hide");
}

function Validation() {
	var counter = 0;

	var pqty_field = $("#pqty_label").text();

	var lqty_field = $("#lqty_label").text();

	var sale_rate_label = $("#sale_rate_label").text();

	/*
	 * var field_names = [ [ "item_pqty", pqty_field ] ];
	 * 
	 * if (fieldValidation(field_names) > 0) { counter = 1; return counter;
	 * return false; } else { counter = 0;
	 * document.getElementById('alertMsg').innerHTML = ""; }
	 */

	/*
	 * if (($("#item_pqty").val() == "") && ($("#item_lqty").val() == "")) {
	 * document.getElementById('alertMsg').innerHTML = pqty_field + " / " +
	 * lqty_field + " " + getFieldText.fieldempty; counter = 1; return counter;
	 * return false; } else { counter = 0;
	 * document.getElementById('alertMsg').innerHTML = ""; }
	 * 
	 * if (($("#item_pqty").val() == 0) && ($("#item_lqty").val() == 0)) {
	 * document.getElementById('alertMsg').innerHTML = pqty_field + " / " +
	 * lqty_field + " " + getFieldText.fieldReq; counter = 1; return counter;
	 * return false; } else { counter = 0;
	 * document.getElementById('alertMsg').innerHTML = ""; }
	 * 
	 * if (isNaN($("#item_pqty").val())) {
	 * document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + "
	 * in " + pqty_field; $(this).focus(); counter = 1; return counter; return
	 * false; } else { if ($("#item_pqty").val() < 0) {
	 * document.getElementById('alertMsg').innerHTML = pqty_field + " " +
	 * getFieldText.checkNegative; $(this).focus(); counter = 1; return counter;
	 * return false; } else { counter = 0;
	 * document.getElementById('alertMsg').innerHTML = ""; } }
	 * 
	 * if (isNaN($("#item_lqty").val())) {
	 * document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + "
	 * in " + lqty_field; $(this).focus(); counter = 1; return counter; return
	 * false; } else { if ($("#item_lqty").val() < 0) {
	 * document.getElementById('alertMsg').innerHTML = lqty_field + " " +
	 * getFieldText.checkNegative; $(this).focus(); counter = 1; return counter;
	 * return false; } else { counter = 0;
	 * document.getElementById('alertMsg').innerHTML = ""; } }
	 */

	if (isNaN($("#item_dis").val())) {
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in D%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	/*
	 * if ($("#item_sale_rate").val() == "" || $("#item_sale_rate").val() == 0 ||
	 * isNaN($("#item_sale_rate").val())) {
	 * document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + "
	 * in " + sale_rate_label; $(this).focus(); counter = 1; return counter;
	 * return false; } else { counter = 0;
	 * document.getElementById('alertMsg').innerHTML = ""; }
	 */

	return counter;
}

function addOrUpdateItemToDetailsTable(operation) {

	var fromTrans= $("#fromTrans").val();
	var toTrans= $("#toTrans").val();
	var reciveQty=$("#item_receive_qty").val();	
	var changeQty=$("#item_pqty").val();
	if(changeQty>reciveQty)
	{
		 $('#alertMsg').html('Quantity must be less then send quantity' );
		return false;
	}
	
	/*
	 * if(changeQty==0) { $('#alertMsg').html('Quantity must be greater then
	 * zero' ); return false; }
	 */
	if($("#item_name").val()=="")
	{
		 $('#alertMsg').html('Item is required' );
		return false;
	}
	
	if(fromTrans==toTrans)
	{
		 $('#alertMsg').html('From & to transfer are same ' );
		return false;
	}
	
$('#alertMsg').html('' );
var item_rate_ls =parseFloat(isEmpty($("#item_rate_ls").val())==true?0: $("#item_rate_ls").val());
var isfreeIAI= $('#isfreeiai').is(":checked")?1:0;
	if (isfreeIAI==0) {

		if (item_rate_ls<=0) {
			  $('#alertMsg').html('sale rate is required: ' );
			return;
		}	
	}
	

	if ($("#item_rate_ls").val()==0) {
		return;
	}

	if (Validation() == 1) {
		return false;
	} else {
		document.getElementById('alertMsg').innerHTML = "";
	}
	/*
	 * if (curstkcheck == 1) { return false; }
	 */
	// console.log("call");

	var itemid = $("#item_id").val();
	var batch = $('#item_batch').val();
	var preuniquekey = itemid + batch;
	if (Number(itemid) == 0) {
		return false;
	}
	// console.log("itemid=" + itemid);

	// console.log("preuniquekey=" + preuniquekey);
	var itempresent = 0;
	if (operation == 1) { // edit

	} else { // add
		$('#selitem > tbody  > tr').each(function() {
			/*
			 * console.log("tbl_itemid=" + this.id); console.log("itemid=" +
			 * itemid);
			 */
			var addbatch = $("#selitem tr#" + this.id).find('#saletabbat').text();
		// console.log("addbatch=" + addbatch);
			//
			var addeditmuniquekey = this.id + addbatch;
			// console.log("addeditmuniquekey=" + addeditmuniquekey);
			// if (Number(this.id) == Number(itemid)) {
			// itempresent = 1;
			// }
			if (preuniquekey == addeditmuniquekey) {
				itempresent = 1;
			}
		});
	}
	// console.log("itempresent=" + itempresent);
	if (itempresent == 1) {
		$('#itemExistsModal').modal('show');
	} else {
		if ($("#item_sche").val() == 'SCHEDULE H1' || $("#item_sche").val() == 'SCHEDULE X') {
			$('#scheleXorH1Modal').modal('show');
			$('#operationtype').val(operation);
		} else {
			addItemtotable(operation);
		}

	}

	$("#purHistoryDiv").addClass("hide");
}
/**
 * for add item to sale table
 */
function addItemtotable(operation) {
	
	
	var itemid = $("#item_id").val();
	// var itemCode = $("#item_code").val(); // new added 7.5.2019
	var itembarcode = $("#item_barcode").val(); // new added 20.6.2019
	var rqty = $('item_receive_qty').val();
	
	var tranqty = $('#transit_item').val();
	var isMrpEnableFlag = $("#isMrpEnableFlag").val();
	var isConversionFlag = $("#isConversionFlag").val();
	var isManufacturerFlag = $("#isManufacturerFlag").val();
	var isFreeFlag = $("#isFreeFlag").val();
	var packQty=$("#item_pqty").val();
	var curstockqty=0;
	var item_dual_stock= $("#item_dualStockRequired_req").val();

	var sales_mans_id= $("#salesman_id").val();
	var sales_mans_per= $("#salesman_per").val();
	var sales_mans_amt= $("#salesman_amt").val();

	var sale_order_id = $("#item_saleorderid").val() == '' ? 0 : $("#item_saleorderid").val();
	/**
	 * isfreeiai item free against item
	 */

    var isfreeIAI= 0;
	isfreeIAI = $('#isfreeiai').is(":checked") ? 1 : 0;
	var isfree_color=isfreeIAI==1? 'freeiai':'';

	    // for current stock cal
		// var itemid = $("#item_id").val();
		var itembatch = $("#item_batch").val().trim();
		var itemexp = $("#item_exp").val();
		var itemmrppack = $("#item_mrp_pack").val();
		var itemexpdate = ($("#item_expDate").val().trim() == "")? "NA" : $("#item_expDate").val() ;

		


					if (Number(itemid) == 0) {
						return false;
					}
					var mrpamt = (($('#item_pqty').val() * $('#item_conv').val()) + Number($('#item_lqty').val())) * $('#item_mrp').val();
					var starttrline = "";
					if ($("#item_sche").val() == 'H1' || $("#item_sche").val() == 'X') {
						starttrline = "<tr id='" + itemid + "' class='schx "+isfree_color+"' style='cursor: pointer;' onclick='javascript:itemHeaderDivView(this.id);' >";
					} else {
						starttrline = "<tr id='" + itemid + "' class='"+isfree_color+"' style='cursor: pointer;' onclick='javascript:itemHeaderDivView(this.id);' >";
					}
					var pqty = 0;
					var lqty = 0;
					
					var free=0.0;
					var disc = 0.0;
					if (($('#item_pqty').val() == "") || ($('#item_pqty').val() == null)) {
						pqty = 0;
					} else {
						pqty = $('#item_pqty').val();
					}
					if (($('#item_lqty').val() == "") || ($('#item_lqty').val() == null)) {
						lqty = 0;
					} else {
						lqty = $('#item_lqty').val();
					}
					if (($('#item_dis').val() == "") || ($('#item_dis').val() == null)) {
						disc = 0;
					} else {
						disc = $('#item_dis').val();
					}
					/*
					 * if (($('#saletabreceivepqty').val() == "") ||
					 * ($('#saletabreceivepqty').val() == null)) { free = 0; }
					 * else { free =
					 * isfreeIAI==0?$('#saletabreceivepqty').val():0; }
					 */

					var purcost = (($('#item_pqty').val() * $('#item_conv').val()) + Number($('#item_lqty').val())) * $('#item_purCostperUnit').val();

					if (item_dual_stock==0) {
						// var calamt = (($('#item_pqty').val()) +
						// Number($('#item_lqty').val())) *
						// $('#item_rate_ls').val();
						var calamt = ($('#item_pqty').val()) * $('#item_rate_ls').val();
					}else {
						var calamt = (($('#item_pqty').val() * $('#item_conv').val()) + Number($('#item_lqty').val())) * $('#item_rate_ls').val();
					}
					// var calamt = (($('#item_pqty').val() *
					// $('#item_conv').val()) + Number($('#item_lqty').val())) *
					// $('#item_rate_ls').val();

					/*
					 * var ltadj=0.0; if(((free % 1)!=0) || free<1) { // LtAdj
					 * calculation start var modFree = free % 1; var pqtyPerFree =
					 * pqty/modFree; ltadj = calamt/pqtyPerFree; // LtAdj
					 * calculation end }
					 */
					var manfacname,freetd,conversion,mrppack,mrp;
					var name = "<td id='saletabname'>" + $('#item_name').val() + "</td>";
					var batchno = "<td id='saletabbat'>" + $('#item_batch').val() + "</td>";
					var expiryDateFormat = "<td id='saletabexpdt'>" + $('#item_exp').val() + "</td>";
					if(isManufacturerFlag==1){
						 manfacname = "<td id='saletabmanname'>" + $('#item_mfg').val() + "</td>";
						}else{
						 manfacname = "<td id='saletabmanname' class='hide'>" + $('#item_mfg').val() + "</td>";
						}
					var packQty = "<td id='saletabpqty'> " + pqty + "</td>";
// var looseQty = "<td id='saletablqty'>" + lqty + "</td>";
					
						// /freetd = "<td id='saletabfree' class='hide>" + free
						// + "</td>";
					
						 conversion = "<td id='saletabconv' class='hide'>" + $('#item_conv').val() + "</td>";
						


					if (isMrpEnableFlag==1) {
						var mrp = "<td id='saletabmrp'>" + parseFloat(mrpamt).toFixed(4) + "</td>";
						var mrppack = "<td id='saletabmrppack'>" + $('#item_mrp_pack').val() + "</td>";
					}else
						{
						var mrp = "<td class='hide' id='saletabmrp'>" + parseFloat(mrpamt).toFixed(4) + "</td>";
						var mrppack = "<td  class='hide' id='saletabmrppack'>" + $('#item_mrp_pack').val() + "</td>";
						}



					var amt = "<td id='saletabamt'>" + parseFloat(isfreeIAI==0?calamt:0).toFixed(4) + "</td>";
					
				/*
				 * var salems_amt="<td id='saletabsalemanAmt'>" +
				 * parseFloat(sales_mans_amt).toFixed(4) + "</td>";
				 */
				   /*
					 * for sale man amt
					 */

					var rateperunit = "<td id='saletabrateperunit' >" + parseFloat(isfreeIAI==0?$('#item_rate_ls').val():0).toFixed(4) + "</td>";
					var vatperc = "<td id='saletabvatperc' class='hide'>" + $('#item_vat').val() + "</td>";
					var item_taxPercentage = "<td id='saletabtaxPercentage'>" + parseFloat(isfreeIAI==0?$('#item_taxPercentage').val():0).toFixed(4) + "</td>";
					var discperc = "<td id='saletabdiscperc'>" + parseFloat(isfreeIAI==0?disc:0).toFixed(4) + "</td>";
					var totamt = "<td id='saletabtotamt'>" +parseFloat(isfreeIAI==0? $('#item_tot').val():0) + "</td>";
					var rowdelete = "<td><button title='Delete Item' class='btn btn-theme04 btn-xs' id='" + itemid + "' onclick='javascript:showSelTabItemDelModal(this.id);'><i class='fa fa-trash-o '></i></button></td>";
					/*
					 * var itembarcode = "<td id='saletabitembarcode' class='hide'>" +
					 * $('#item_barcode').val() + "</td>";
					 */
					var itembarcode = "<td id='saletabitembarcode'>" + $('#item_barcode').val() + "</td>";
					var punitid = "<td id='saletabpunitid' class='hide'>" + $('#item_packunitid').val() + "</td>";
					var lunitid = "<td id='saletablunitid' class='hide'>" + $('#item_looseunitid').val() + "</td>";
					var content = "<td id='saletabcontent' class='hide'>" + $('#item_content').val() + "</td>";
					var vatamt = "<td id='saletabvat' class='hide'>" + $('#item_vatamt').val() + "</td>";
					var discamt = "<td id='saletabdisc' class='hide'>" + $('#item_discamt').val() + "</td>";
					var stockedqty = "<td id='saletabitemstkqty' class='hide'>" + $('#item_stockedqty').val() + "</td>";
					var mrpperunit = "<td id='saletabmrpperunit' class='hide'>" + $('#item_mrp').val() + "</td>";
					var rate = "<td id='saletabrate' class='hide'>" + $('#item_rate_ls').val() + "</td>";
					var schename = "<td id='saletabsche' class='hide'>" + $('#item_sche').val() + "</td>";
					var taxperc = "<td id='saletabtaxperc' class='hide'>" + $('#item_tax').val() + "</td>";
					var taxamt = "<td id='saletabtax' class='hide'>" + $('#item_taxamt').val() + "</td>";
					// var contentname = "<td id='saletabcontent' class='hide'>"
					// + $('#content_Dets').val() + "</td>"
					var contentid = "<td id='saletabcontentid' class='hide'>" + $('#content_id').val() + "</td>";
					var item_taxId = "<td id='saletabtaxId' class='hide'>" + $('#item_taxId').val() + "</td>";

					var item_isGroupTax = "<td id='saletabisGroupTax' class='hide'>" + $('#item_isGroupTax').val() + "</td>";
					var item_discount = "<td id='saletabdiscount' class='hide'>" + parseFloat(disc).toFixed(4) + "</td>";
					var item_isDiscount = "<td id='saletabisDiscount' class='hide'>" + $('#item_isDiscount').val() + "</td>";
					var item_maxDiscountLimit = "<td id='saletabmaxDiscountLimit' class='hide'>" + $('#item_maxDiscountLimit').val() + "</td>";
					var item_CalcTaxAmt = "<td id='saletabitemcalcgstamt' class='hide'>" + $('#item_CalcTaxAmt').val() + "</td>";
					var item_taxMode = "<td id='saletabitemtaxmode' class='hide'>" + $('#item_taxMode').val() + "</td>";
					var item_hsnCode = "<td id='saletabitemhsncode' class='hide'>" + $('#item_hsnCode').val() + "</td>";
					var item_purCost = "<td id='saletabpurcost' class='hide'>" + parseFloat(purcost).toFixed(4) + "</td>";
					var item_purCostperUnit = "<td id='saletabpurcostperunit' class='hide'>" + $('#item_purCostperUnit').val() + "</td>";
					var salerate = "<td id='saletabitemsalerate' class='hide'>" + parseFloat($('#item_sale_rate').val()).toFixed(4) + "</td>";
					var item_purChase_rate = "<td id='saletabitempurrate' class='hide'>" + $('#item_purChase_rate').val() + "</td>";
					var item_ltadj = "<td id='saletabitemltadj' class='hide'>" + $('#item_ltadj').val() + "</td>";
					var slnos = '<td id="saletabitemslnos" class="numeric hide">' + $("#allVal").val() + '</td>';
					var isslnos = '<td id="saletabitemisslno" class="numeric hide">' + $("#item_is_slno").val() + '</td>';
					var slnoschkid = '<td id="saletabitemchkslnosid" class="numeric hide">' + $("#allChkID").val() + '</td>';
					var item_taxtype = '<td id="saletabitemtaxtype" class="numeric hide">' + $("#item_taxtype").val() + '</td>';
					var saletabexpdate = '<td id="saletabexpdate" class="numeric hide">' + $("#item_expDate").val() + '</td>';
					var itemDualStock = '<td id="saletabdualstock" class="numeric hide">' + item_dual_stock + '</td>';
					var salasManIsPerAmt = '<td id="saletabsalesmanIdComPer" class="numeric hide">' + sales_mans_id+"_"+ sales_mans_per+"_"+sales_mans_amt+ '</td>';
					var isfreeItemagaistitem= '<td id="saletabisfreeitemaginstitem" class="numeric hide">' +isfreeIAI+ '</td>';
					var saleorderid= '<td id="saleorderid" class="numeric hide">' +sale_order_id+ '</td>';
                    
					var itemcode = "<td id='saletabcode' class='hide'>" + $('#item_code').val() + "</td>"; // new
																											// added
																											// 7.5.2019
					var itemremarks = "<td id='saletabremarks' class='hide'>" + $('#itmremarks').val() + "</td>"; // new
																													// added
																													// 7.5.2019
					  
					var endtrline = "</tr>";

					if (operation == 1) { // edit
						
						var disc = 0.0;
						if (($('#item_pqty').val() == "") || ($('#item_pqty').val() == null)) {
							pqty = 0;
						} else {
							pqty = $('#item_pqty').val();
						}
						if (($('#item_receive_qty').val() == "") || ($('#item_receive_qty').val() == null)) {
							rqty = 0;
						} else {
							rqty = $('item_receive_qty').val();
							
						}
						if (($('#item_lqty').val() == "") || ($('#item_lqty').val() == null)) {
							lqty = 0;
						} else {
							lqty = $('#item_lqty').val();
						}
						
						if (($('#transit_item').val() == "") || ($('#transit_item').val() == null)) {
							tranqty = 0;
						} else {
							tranqty = $('#transit_item').val();
						}
						
						if (($('#item_dis').val() == "") || ($('#item_dis').val() == null)) {
							disc = 0;
						} else {
							disc = isfreeIAI==0?$('#item_dis').val():0;
						}
						/*
						 * if (($('#item_free').val() == "") ||
						 * ($('#item_free').val() == null)) { free = 0; } else {
						 * free = isfreeIAI==0?$('#item_free').val():0; }
						 */
						$("#item_id").val(itemid);


						if (isfreeIAI==1) {
							$("#"+itemid).addClass('freeiai');
						}else {
							$("#"+itemid).removeClass('freeiai');
						}
						$("#selitem tr#" + itemid).find('#saletabname').text($('#item_name').val());
						$("#selitem tr#" + itemid).find('#saletabitembarcode').text($('#item_barcode').val());
						$("#selitem tr#" + itemid).find('#saletabbat').text($("#item_batch").val());
						$("#selitem tr#" + itemid).find('#saletabexpdt').text($("#item_exp").val());
						$("#selitem tr#" + itemid).find('#saletabpqty').text(pqty);
						$("#selitem tr#" + itemid).find('#saletabreceivepqty').text(rqty);
						
						$("#selitem tr#" + itemid).find('#saletabpunitid').text($("#item_packunitid").val());
// $("#selitem tr#" + itemid).find('#saletablqty').text(lqty);
						/*
						 * $("#selitem tr#" +
						 * itemid).find('#saletabfree').text(free);
						 */
						$("#selitem tr#" + itemid).find('#saletablunitid').text($("#item_looseunitid").val());
						$("#selitem tr#" + itemid).find('#saletabmrppack').text($("#item_mrp_pack").val());
						$("#selitem tr#" + itemid).find('#saletabmrp').text(parseFloat(isfreeIAI==0?mrpamt:0).toFixed(4));
						$("#selitem tr#" + itemid).find('#saletabamt').text(parseFloat(isfreeIAI==0?calamt:0).toFixed(4));
						$("#selitem tr#" + itemid).find('#saletabmrpperunit').text($("#item_mrp").val());
						$("#selitem tr#" + itemid).find('#saletabrateperunit').text(isfreeIAI==0?$("#item_rate_ls").val():0);
						$("#selitem tr#" + itemid).find('#saletabrate').text($("#item_rate_ls_hid").val());			
						$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(disc).toFixed(4));
						$("#selitem tr#" + itemid).find('#saletabdisc').text($("#item_discamt").val());
						$("#selitem tr#" + itemid).find('#saletabvatperc').text($("#item_vat").val());
						$("#selitem tr#" + itemid).find('#saletabvat').text($("#item_vatamt").val());
						$("#selitem tr#" + itemid).find('#saletabtotamt').text(isfreeIAI==0? $("#item_tot").val():0);
						$("#selitem tr#" + itemid).find('#saletabmanname').text($("#item_mfg").val());
						$("#selitem tr#" + itemid).find('#saletabcontent').text($("#item_content").val());
						$("#selitem tr#" + itemid).find('#saletabconv').text($("#item_conv").val());
						$("#selitem tr#" + itemid).find('#saletabtaxperc').text($("#item_tax").val());
						$("#selitem tr#" + itemid).find('#saletabtax').text($("#item_taxamt").val());
						$("#selitem tr#" + itemid).find('#saletabcontent').text($("#content_Dets").val());
						$("#selitem tr#" + itemid).find('#saletabcontentid').text($("#content_id").val());
						$("#selitem tr#" + itemid).find('#saletabtaxId').text($("#item_taxId").val());
						$("#selitem tr#" + itemid).find('#saletabtaxPercentage').text(isfreeIAI==0?$("#item_taxPercentage").val():0);
						$("#selitem tr#" + itemid).find('#saletabisGroupTax').text($("#item_isGroupTax").val());
						$("#selitem tr#" + itemid).find('#saletabdiscount').text(parseFloat(disc).toFixed(4));
						$("#selitem tr#" + itemid).find('#saletabisDiscount').text($("#item_isDiscount").val());
						$("#selitem tr#" + itemid).find('#saletabmaxDiscountLimit').text($("#item_maxDiscountLimit").val());
						$("#selitem tr#" + itemid).find('#saletabitemcalcgstamt').text($("#item_CalcTaxAmt").val());
						$("#selitem tr#" + itemid).find('#saletabitemtaxmode').text($("#item_taxMode").val());
						$("#selitem tr#" + itemid).find('#saletabitemhsncode').text($("#item_hsnCode").val());
						$("#selitem tr#" + itemid).find('#saletabpurcost').text(parseFloat(purcost).toFixed(4));
						$("#selitem tr#" + itemid).find('#saletabpurcostperunit').text($("#item_purCostperUnit").val());
						$("#selitem tr#" + itemid).find('#saletabitemsalerate').text($("#item_sale_rate").val());
						$("#selitem tr#" + itemid).find('#saletabitempurrate').text($("#item_purChase_rate").val());
						$("#selitem tr#" + itemid).find('#saletabitemltadj').text($("#item_ltadj").val());
						$("#selitem tr#" + itemid).find('#saletabitemslnos').text($("#allVal").val());
						$("#selitem tr#" + itemid).find('#saletabitemisslno').text($("#item_is_slno").val());
						$("#selitem tr#" + itemid).find('#saletabitemchkslnosid').text($("#allChkID").val());
						$("#selitem tr#" + itemid).find('#saletabitemtaxtype').text($("#item_taxtype").val());
						$("#selitem tr#" + itemid).find('#saletabexpdate').text($("#item_expDate").val());
						$("#selitem tr#" + itemid).find('#saletabsalesmanIdComPer').text($("#salesman_id").val()+"_"+$("#salesman_per").val()+"_"+$("#salesman_amt").val());
						$("#selitem tr#" + itemid).find('#saletabisfreeitemaginstitem').text(isfreeIAI);
						$("#selitem tr#" + itemid).find('#saleorderid').text(sale_order_id);
						
						$("#selitem tr#" + itemid).find('#saletabcode').text($('#item_code').val()); // new
																										// added
																										// 7.5.2019
						$("#selitem tr#" + itemid).find('#saletabremarks').text($('#itmremarks').val()); // new
																											// added
																											// 7.5.2019
						
						
						
					} else {
// createdrowline = starttrline + name + batchno + expiryDateFormat + manfacname
// + packQty + looseQty + conversion + mrppack + mrp + amt + rateperunit +
// vatperc + item_taxPercentage + vatamt + discperc + discamt + totamt +
// rowdelete + itembarcode + punitid + lunitid + content + stockedqty +
// mrpperunit + rate + schename + taxperc + taxamt + contentid + item_taxId +
// item_isGroupTax + item_discount + item_isDiscount + item_maxDiscountLimit +
// item_CalcTaxAmt + item_taxMode + item_hsnCode + item_purCost +
// item_purCostperUnit + salerate + item_purChase_rate+item_ltadj + endtrline;
						
							createdrowline = starttrline + name + itembarcode + batchno + expiryDateFormat + manfacname + packQty +
							conversion + mrppack + mrp + amt + rateperunit + vatperc + item_taxPercentage + vatamt + discperc +
							discamt +totamt + rowdelete  + punitid + lunitid + content + stockedqty + mrpperunit
							+ rate + schename + taxperc + taxamt + contentid + item_taxId + item_isGroupTax + item_discount
							+ item_isDiscount + item_maxDiscountLimit + item_CalcTaxAmt + item_taxMode + item_hsnCode + item_purCost
							+ item_purCostperUnit + salerate + item_purChase_rate+item_ltadj+slnos+isslnos+slnoschkid+item_taxtype+saletabexpdate
							+itemDualStock +salasManIsPerAmt+isfreeItemagaistitem + saleorderid +itemcode+ itemremarks+endtrline;


						$("#saletabitemdetails").prepend(createdrowline);
					}
					
					clearHeaderDiv();
					calculateTotalMRP();
					// //calculateTransition();
					calculateTotalamt();
					calculateTotalVat();
					calculateTotalTax();
					calculateTotalDisc();
					// /calculateNetTotal();
					// calculateSpclDisc();
					calculateTotalGSTamt();
					calculateTotalPurchaseamt();
					calculateTotalltadj();
					calculateNetTotal();
					
				
				
			}



	// //$('#weival').removeClass("callweival");

function DoConfirmCloseStkRec() {
	
	var trId=$("#confirmid").val();
	var type=$("#confirmtype").val();
	if(type==1){// for close
		
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.transferId = trId;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/stock/closeStockTransferReceive.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			document.getElementById('confirmmessagecont').innerHTML = getStockTransInvText.dataSucclose;
			showConfirmModal();
			
			
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getStockTransInvText.datastknotclose;
			showConfirmModal();
			
		}

	});
	}
}
		
	

/*
 * function deleteStockTransfer(pid) { $('#confirmModalStock').modal('show');
 * $("#confirmid").val(pid); $("#confirmtype").val(0); }
 */


function closeStockTransferReceive(pid) {
	
	$('#confirmModalStock').modal('show');
	$("#confirmid").val(pid);
	$("#confirmtype").val(1); // for post set as one
}





function sendItemToPO(para) {
	 if (para==2) {
		document.getElementById('snditmpo2').innerHTML="";
		   
	}
   if (para==1) {

		 document.getElementById('snditmpo2').innerHTML=" ";
	} 
     $("#snditmpo").innerHTML=" ";
	$("#snditmpo").addClass("hide");
	var itemnotfoundid = $("#senditemtopoid").val();
	var podate = $("#date").val();
	var CommonRelsetmapperObj = {};

		 

	CommonRelsetmapperObj.itemId = itemnotfoundid;
	CommonRelsetmapperObj.invDate = podate;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purorder/createtemppofromsale.htm", CommonRelsetmapperObj, function(response) {
		// console.log(response);
		var status = JSON.parse(response);
		if (status.length == 0) {

		} else {

			 if (para==1) {
			 	$("#snditmpo").removeClass("hide");
			if (status.id > 0) {
				document.getElementById('snditmpo').innerHTML = getFieldText.greaterthanzero;
			} else if (status.id == 0) {
				document.getElementById('snditmpo').innerHTML = getFieldText.zero;
			} else if (status.id == -1) {
				document.getElementById('snditmpo').innerHTML = getFieldText.minusone;
			} else if (status.id == -2) {
				document.getElementById('snditmpo').innerHTML = getFieldText.minustwo;
			} else if (status.id == -3) {
				document.getElementById('snditmpo').innerHTML = getFieldText.minusthree;
			} else {
				document.getElementById('snditmpo').innerHTML = getFieldText.zero;
			}
			 }
			 if (para==2) {

			 	$("#snditmpo2").removeClass("hide");
			if (status.id > 0) {
				document.getElementById('snditmpo2').innerHTML = getFieldText.greaterthanzero;
			} else if (status.id == 0) {
				document.getElementById('snditmpo2').innerHTML = getFieldText.zero;
			} else if (status.id == -1) {
				document.getElementById('snditmpo2').innerHTML = getFieldText.minusone;
			} else if (status.id == -2) {
				document.getElementById('snditmpo2').innerHTML = getFieldText.minustwo;
			} else if (status.id == -3) {
				document.getElementById('snditmpo2').innerHTML = getFieldText.minusthree;
			} else {
				document.getElementById('snditmpo2').innerHTML = getFieldText.zero;
			}
			 }
			
		}
	});
}
function getGenericMed() {
	$("#snditmpo").addClass("hide");
	$("#alternatemeddetails").text("");
	$("#alternateMeditemname").text($("#item_name").val());
	var itemnotfoundid = $("#itemnotfoundid").val();
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.itemId = itemnotfoundid;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/item/getalternatemedicine.htm", CommonRelsetmapperObj, function(response) {
		// console.log(response);
		var altmedlist = JSON.parse(response);
		if (altmedlist.length == 0) {
			$("#altmeditemnotfounddiv").removeClass("hide");
			$("#snditmpo").removeClass("hide");
			$("#alternateMedtable").addClass("hide");
			$("#altmeditemnotfoundname").text($("#item_name").val());
		} else {
			$("#altmeditemnotfounddiv").addClass("hide");
			$("#alternateMedtable").removeClass("hide");
			// $("#snditmpo").addClass("hide");
			for ( var i = 0; i < altmedlist.length; i++) {
				var altmed = altmedlist[i];
				// console.log(altmed.itemId);
				var starttrline = "<tr id=" + altmed.itemId + " style='cursor: pointer;' onclick='javascript:selAltMed(" + altmed.itemId + ",&quot;" + altmed.itemName + "&quot;)'>";
				var name = "<td>" + altmed.itemName + "</td>";
				var manufacturerName = "<td>" + altmed.manufacturerName + "</td>";
				var netContent = "<td>" + altmed.netContent + "</td>";
				var price = "<td>" + parseFloat(altmed.price).toFixed(2) + "</td>";
				var stockQty = "<td>" + altmed.stockQty + "</td>";//
				var endtrline = "</tr>";
				createdrowline = starttrline + name + manufacturerName + netContent + price + stockQty + endtrline;
				$("#alternatemeddetails").append(createdrowline);
			}
		}
		$('#alternateMedModal').modal('show');
	});
}
function selAltMed(	itemid,
					itemname) {
	// console.log("itemname" + itemname);
	// console.log("itemid" + itemid);
	$("#item_name").val(itemname);
	$('#alternateMedModal').modal('hide');
	getItemDetails(itemid);

}
function openCardModal() {
	$('#cardModal').modal('show');
	var crdamt = $("#cardAmt").val();
	if (crdamt == '') {
		crdamt = 0.00;
	}
	var cashAmt = $("#cashAmt").val();
	if (cashAmt == '') {
		cashAmt = 0.00;
	}
// var credittot = parseFloat($("#nettot").val() - cashAmt - crdamt).toFixed(2);
	var credittot = parseFloat($("#nettot").val() - cashAmt - crdamt-$("#payretadjamt").val()).toFixed(2);
	$('#cardAmtMod').val(credittot);
	$("#cardExpMod").val($("#cardExpMod").val());
	$("#cardFourDigitMod").val($("#cardFourDigitMod").val());
}
$("#cardAmtMod").keyup(function() {
	var crdamt = $(this).val();
	var cashAmt = $("#cashAmt").val();
	if (cashAmt == '') {
		cashAmt = 0.00;
	}
// var credittot = parseFloat($("#nettot").val() - cashAmt -
// $("#cardAmt").val()).toFixed(2);
	var credittot = parseFloat($("#nettot").val() - cashAmt - $("#cardAmt").val()-$("#payretadjamt").val()).toFixed(2);
	// $('#cardAmtMod').val(credittot);
});
function holdCardDetails() {
	var cardamt = Number($("#cardAmt").val()) + Number(parseFloat($('#cardAmtMod').val()).toFixed(2));
	$("#cardAmt").val(parseFloat(cardamt).toFixed(2));
	var cashAmt = $("#cashAmt").val();
	if (cashAmt == '') {
		cashAmt = 0.00;
	}
// var creditamt = parseFloat($("#nettot").val() - cashAmt -
// $("#cardAmt").val()).toFixed(2);//$("#creditAmt").val();
	var creditamt = parseFloat($("#nettot").val() - cashAmt - $("#cardAmt").val()-$("#payretadjamt").val()).toFixed(2);
// var totamt = Number(cardamt) + Number(cashAmt);
	var totamt = Number(cardamt) + Number(cashAmt)+Number($("#payretadjamt").val());
	// changed for sale order advance
	var nettot = $("#paymodnettot").val();
	// var nettot = $("#nettot").val();
	if (parseFloat(totamt).toFixed(2) < parseFloat(nettot).toFixed(2)) {
		// alert(parseFloat(nettot-totamt).toFixed(2));
		$("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
	} else {
		$("#creditAmt").val(parseFloat(0).toFixed(2));
	}

}
/*
 * function openNewCashMemo() { location.href = BASE_URL + '/pos/cashmemo.htm'; }
 */
function targetURL() {
	var type=$("#confirmtype").val();
	var res=$("#confirmid").val();
	if ($("#confirmval").val() == 0) {		
		location.href = BASE_URL + '/stock/stockreceivereg.htm';
	}	
	else if ($("#confirmval").val() == -1) {
		location.href = "#";
	} else if(type == 3) {		
		location.href = BASE_URL + '/stock/stockreceiveheader/'+res+'.htm';
	}
}
function openretadjmod() {
	$("#billamtheaderAdj").text(parseFloat($("#nettot").val()).toFixed(2));
	$("#retAdjModal").modal("show");
}
function getMemoDetForAdj() {
	$("#showretadjtbody").text("");

	if ($("#retamtvalsamepage").val() != 0) {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		$("#payretadjamt").val(parseFloat(s).toFixed(2));
	}

	var CommonRelsetmapperObj = {};
	if ($("#retadjmemono").val() == '') {
	} else {
		CommonRelsetmapperObj.invoiceNo = $("#retadjmemoDoc").val() + $("#retadjmemoFinyr").val() + $("#retadjmemoSlash").val() + $("#retadjmemono").val();
	}
	if ($("#retadjcustph").val() == '') {
	} else {
		CommonRelsetmapperObj.custPh = $("#retadjcustph").val();
	}

	CommonRelsetmapperObj.custId = 0;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/getretdetforadj.htm", CommonRelsetmapperObj, function(response) {
		// console.log(response);
		var retadjlist = JSON.parse(response);
		// console.log(retadjlist.length);
		if (retadjlist.length == 0) {
			$("#nocashmemofound").text("No data found.");
		} else {
			$("#nocashmemofound").text("");
			for ( var i = 0; i < retadjlist.length; i++) {
				var retadj = retadjlist[i];
				$("#retadjcustph").val(retadj.customerPhone);
				$("#retadjcustname").val(retadj.customerName);
				var starttrline = "<tr id=" + retadj.saleReturnId + " >";
				var invno = "<td>" + retadj.invNo + "</td>";
				var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
				var netAmount = "<td id='retnetamt_" + retadj.saleReturnId + "'>" + parseFloat(retadj.netAmount).toFixed(2) + "</td>";
				var preAdjAmount = "<td id='retpreadjamt_" + retadj.saleReturnId + "'>" + parseFloat(retadj.preAdjAmount).toFixed(2) + "</td>";
				var adjAmount = "<td><input type='text' size='6' style='line-height: 14px;' id='adjamt_" + retadj.saleReturnId + "' value='" + parseFloat(retadj.netAmount - retadj.preAdjAmount).toFixed(2) + "' onkeyup='checkAdjAmt(this.value," + retadj.saleReturnId + ")' style'line-height: 14px;'> </td>";//
				var rowadd = "<td><button class='btn btn-success btn-xs' id='adjamtaddbut_" + retadj.saleReturnId + "' onclick='javascript:addItemforadj(" + JSON.stringify(retadj) + ");'><i class='fa fa-plus'></i></button></td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + invno + invdate + netAmount + preAdjAmount + adjAmount + rowadd + endtrline;
				$("#showretadjtbody").append(createdrowline);
			}
		}

	});

}

function checkAdjAmt(	inputval,
						retid) {
	var netamt = $("#retnetamt_" + retid).text();
	var preadjamt = $("#retpreadjamt_" + retid).text();
	var totamt = Number(netamt) - Number(preadjamt);
	// console.log(totamt);
	if (parseFloat(inputval) > parseFloat(totamt)) {
		$("#adjamt_" + retid).val(parseFloat(totamt).toFixed(2));
	}

}

function addItemforadj(retadj) {
	// $("#retadjtbody").text("");
	var retpresent = 0;
	var totretadjamt = $("#totretadjamt").text();
	if ($("#payretadjamt").val() != 0) {
		if (Number($("#totretadjamt").text()) == 0) {
			samepageret = 1;
			totretadjamt = Number($("#totretadjamt").text()) + Number($("#payretadjamt").val());
		}
	}

	// var totretadjamt = Number($("#totretadjamt").text()) +
	// Number($("#payretadjamt").val());
	var paymodnettot = $("#paymodnettot").val();

	$('#retadjtable > tbody  > tr').each(function() {
		var retid = this.id;
		if (retid == retadj.saleReturnId) {
			retpresent = 1;
		}
	});
	if (retpresent == 1) {

	} else {
		var adjamt = $("#adjamt_" + retadj.saleReturnId).val();
		totretadjamt = Number(totretadjamt) + Number(adjamt);
		// totretadjamt = Number(totretadjamt) + Number(adjamt) +
		// Number($("#payretadjamt").val());
		if (parseFloat(totretadjamt) > parseFloat(paymodnettot)) {
			$("#greaterbillamt").text("Adjust return amt : (" + totretadjamt + ") is greater than bill amount :(" + paymodnettot + ")");
			totretadjamt = Number(totretadjamt) - Number(adjamt);
		} else {
			$("#greaterbillamt").text("");
			$("#adjamtaddbut_" + retadj.saleReturnId).hide();
			// $("#adjamt_"+retadj.saleReturnId).prop('readonly', true);
			var starttrline = "<tr id=" + retadj.saleReturnId + " >";
			var invno = "<td>" + retadj.invNo + "</td>";
			var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
			var netAmount = "<td id='retnetamt'>" + parseFloat(retadj.netAmount).toFixed(2) + "</td>";
			var preAdjAmount = "<td id='retpreadjamt'>" + parseFloat(retadj.preAdjAmount).toFixed(2) + "</td>";
			var adjAmount = "<td id='retadjamt'>" + parseFloat(adjamt).toFixed(2) + "</td>";//
			var rowadd = "<td><button class='btn btn-danger btn-xs'  onclick='javascript:showSelItemDelModal(" + retadj.saleReturnId + ");'><i class='fa fa-trash-o '></i></button></td>";
			var endtrline = "</tr>";
			createdrowline = starttrline + invno + invdate + netAmount + preAdjAmount + adjAmount + rowadd + endtrline;
			$("#retadjtbody").append(createdrowline);
			calculateAdjAmount();
			calculateRetTotnteamt();
			calculateRetpreAdjAmount();
		}

		// var retadj = JSON.parse(clickretadj);
		$("#payretadjamt").val(parseFloat(totretadjamt).toFixed(2));
		$("#totretadjamt").text(parseFloat(totretadjamt).toFixed(2));
		var paymodnettot = $("#paymodnettot").val();
		// $("#paymodnettot").val(parseFloat(paymodnettot-parseFloat(totretadjamt)).toFixed(2));
		$("#creditAmt").val(parseFloat(paymodnettot - parseFloat(totretadjamt)).toFixed(2));
		// $("#nettot").val(parseFloat(paymodnettot-parseFloat(totretadjamt)).toFixed(2));
	}

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

function calculateAdjAmount() {
	var rettotadjAmount = 0.00;
	$('#retadjtable tbody tr').each(function() {
		var adjAmount = $(this).find("#retadjamt").html();
		rettotadjAmount = rettotadjAmount + Number(adjAmount);
	});
	$("#totretadjamt").html(parseFloat(rettotadjAmount).toFixed(2));
}

function showSelItemDelModal(trId) {
	$("#confirmIdret").val(trId);
	$('#confirmModal').modal('show');
}
function showSelTabItemDelModal(trId) {
	$("#saletableitemdelid").val(trId);
	$('#saletableItemDelModal').modal('show');
}
function closeSaletableItemDel(trId) {
	$('#saletabitemdetails tr#' + trId).remove();
	clearHeaderDiv();
	calculateTotalMRP();
	
	calculateTotalamt();
	calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	// calculateNetTotal();
	// calculateSpclDisc();
	calculateTotalGSTamt();
	calculateTotalPurchaseamt();
	calculateTotalltadj();
	calculateNetTotal();
}
function DoConfirm() {
	var itmid = $("#confirmIdret").val();

	$('#retadjtbody tr#' + itmid).remove();
	$("#adjamtaddbut_" + itmid).show();
	$("#greaterbillamt").text("");
	$("#payretadjamt").val(parseFloat(0).toFixed(2));
	if ($("#retamtvalsamepage").val() != 0) {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		$("#payretadjamt").val(parseFloat(s).toFixed(2));
	}
	var cardAmt = $("#cardAmt").val();
	var cashamt = $("#cashAmt").val();
	var nettot = $("#nettot").val();
// var totamt = (Number(cashamt) + Number(cardAmt));
	var totamt = (Number(cashamt) + Number(cardAmt))+Number($("#payretadjamt").val());
	$("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
	calculateAdjAmount();
	calculateRetpreAdjAmount();
	calculateRetTotnteamt();
}
function closeRetAdjMod() {
	$("#retadjcustph").val("");
	$("#retadjcustname").val("");
	$("#retadjtbody").text("");
	$("#showretadjtbody").text("");
	$("#totretnetamt").html(parseFloat(0).toFixed(2));
	$("#totretadjamt").html(parseFloat(0).toFixed(2));
	$("#totretpreadjamt").html(parseFloat(0).toFixed(2));
	$("#payretadjamt").val(parseFloat(0).toFixed(2));
	if ($("#retamtvalsamepage").val() != 0) {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		$("#payretadjamt").val(parseFloat(s).toFixed(2));
	}
	var cardAmt = $("#cardAmt").val();
	var cashamt = $("#cashAmt").val();
	var nettot = $("#nettot").val();
// var totamt = (Number(cashamt) + Number(cardAmt));
	var totamt = (Number(cashamt) + Number(cardAmt))+Number($("#payretadjamt").val());
	// $("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
}
function okRetAdjMod() {
	$("#greaterbillamt").text("");
	var totretadjamt = $("#totretadjamt").text();
	// $("#payretadjamt").val(parseFloat(totretadjamt).toFixed(2));
	$("#payretadjamt").val(parseFloat(Number(totretadjamt)).toFixed(2));
	var cardAmt = $("#cardAmt").val();
	var cashamt = $("#cashAmt").val();
	var nettot = $("#nettot").val();
	totretadjamt = $("#payretadjamt").val();
	$("#totretadjamt").text(totretadjamt);	
	var tenderamt = parseFloat($("#tenderamt").val());
	
	
	var creditcustomer = $("#salecustid").val();
	if(creditcustomer==0){
		$("#creditAmt").val(0.0);
		$("#cashAmt").val(parseFloat(Number(nettot) - Number(totretadjamt)).toFixed(2));
		
	}else{
		$("#creditAmt").val(0.0);
		$("#cashAmt").val(parseFloat(Number(nettot) - Number(totretadjamt)).toFixed(2));
		$("#refundAmt").val(parseFloat(tenderamt - $("#cashAmt").val()));
		// /alert($("#refundAmt").val());
	}
	
	// /var tenderamount=$("#tenderamt").val();
	var cashamount=$("#cashAmt").val();
	// /alert("TENDER AMOUNT IS"+tenderamount);
    $("#refundAmt").val(tenderamount-cashamount);
    var paymodnettot=$("#paymodnettot").val();
    var payretadjamt=$("#payretadjamt").val();
    $("#cashAmt").val(paymodnettot-payretadjamt);
    $("#tenderamt").val(paymodnettot-payretadjamt);
	
	// $("#cashAmt").val(parseFloat(Number(nettot) -
	// Number(totretadjamt)).toFixed(2));
	// $("#creditAmt").val(parseFloat(Number(nettot) -
	// Number(totamt)).toFixed(2));
}

function viewretadjamt() {
	$("#viewretadjtbody").text("");
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.saleId = $("#saleId").val();
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/getretdetforadjbysaleid.htm", CommonRelsetmapperObj, function(response) {
		// console.log(response);
		var retadjlist = JSON.parse(response);
		for ( var i = 0; i < retadjlist.length; i++) {
			var retadj = retadjlist[i];
			var starttrline = "<tr id=" + retadj.saleReturnId + " >";
			var invno = "<td>" + retadj.invNo + "</td>";
			var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
			var netAmount = "<td id='retnetamt'>" + parseFloat(retadj.netAmount).toFixed(2) + "</td>";
			// var preAdjAmount = "<td id='retpreadjamt'>" +
			// parseFloat(retadj.preAdjAmount).toFixed(2) + "</td>";
			var adjAmount = "<td id='retadjamt'>" + parseFloat(retadj.adjAmount).toFixed(2) + "</td>";//
			var endtrline = "</tr>";
			createdrowline = starttrline + invno + invdate + netAmount + adjAmount + endtrline;
			$("#viewretadjtbody").append(createdrowline);
		}
	});

	$("#viewretAdjModal").modal("show");
}

function openAddModal(addSubjct) {
	document.getElementById('custAddAlertMsg').innerHTML = '';
	document.getElementById('docAddAlertMsg').innerHTML = '';
	if (addSubjct == "cust") { // Customer add
		$('#customerAddEditModal').find('input:text').val('');
		$('#customerAddEditModal').find('input:hidden').val('');
		$("#headertext").text(getCustomerText.headerTextAdd);
		$("#customerName").val($("#salecustname").val());
		$("#phn").val($("#salecustph").val());
		$('#customerAddEditModal').modal('show');
	} else if (addSubjct == "doc") { // Doctor add
		$('#doctorAddEditModal').find('input:text').val('');
		$('#doctorAddEditModal').find('input:hidden').val('');
		$("#headertextDoc").text(getDoctorText.headerTextAdd);
		$("#dctrName").val($("#saledocname").val());
		$('#doctorAddEditModal').modal('show');
	} else{ // Tailor Add
		$('#doctorAddEditModal').find('input:text').val('');
		$('#doctorAddEditModal').find('input:hidden').val('');
		$("#headertextDoc").text(getCashMemoText.addOrUpdateTailor);
		$("#dctrName").val($("#saledocname").val());
		$('#doctorAddEditModal').modal('show');
	}

}

function addEditCustomer() {
	document.getElementById('custAddAlertMsg').innerHTML = '';
	var pin = $('#pin').val();
	var name = $('#customerName').val();
	var code = $('#code').val();
	var qualification = $('#qualification').val();
	var speciality = $('#speciality').val();
	var addrs = $('#addrs').val();
	var city = $('#city').val();
	var state = $('#state').val();
	var country = $('#country').val();
	var phn = $('#phn').val();
	var fax = $('#fax').val();
	var slgender = $('#slgender').val();
	var custtype = $('#custTypeId').val();
	if (isNaN($("#pin").val())) {
		document.getElementById('custAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Pin";
		$(this).focus();
		return false;
	} else {
		document.getElementById('custAddAlertMsg').innerHTML = "";
	}
	/*
	 * if (Number($("#phn").val().length) < 10) {
	 * document.getElementById('custAddAlertMsg').innerHTML = "Mobile Number
	 * should be 10 digit."; $(this).focus(); return false; } else {
	 * document.getElementById('custAddAlertMsg').innerHTML = ""; }
	 */
	if ($('#opbal').val() == 0 || $('#opbal').val() == "") {
		var opbal = 0;
	} else {
		if (isNaN($("#opbal").val())) {
			document.getElementById('custAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Opening Balance";
			$(this).focus();
			return false;
		} else {
			document.getElementById('custAddAlertMsg').innerHTML = "";
			var opbal = $('#opbal').val();
		}
	}
	if ($('#creditLimitAdd').val() == 0 || $('#creditLimitAdd').val() == "") {
		var creditLimit = 0.0;
	} else {
		if (isNaN($("#creditLimitAdd").val())) {
			document.getElementById('custAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Credit Limit";
			$(this).focus();
			return false;
		} else {
			document.getElementById('custAddAlertMsg').innerHTML = "";
			var creditLimit = $('#creditLimitAdd').val();
		}
	}

	var name_label = $("#cust_name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

	var phn_label = $("#phn_label").text();
	var phn_field = phn_label.substring(0, phn_label.lastIndexOf(" "));
	
	var pin_label = $("#pin_label").text();
	var pin_field = pin_label.substring(0, pin_label.lastIndexOf(" "));
	
	var pin_label = $("#pin_label").text();
	var pin_field = pin_label.substring(0, pin_label.lastIndexOf(" "));
	
	var state_label = $("#states_label").text();
	var state_field = state_label.substring(0, state_label.lastIndexOf(" "));
	
	var city_label = $("#city_label").text();
	var city_field = city_label.substring(0, city_label.lastIndexOf(" "));
	

	/*
	 * var field_names =
	 * [["customerName",name_field],["phn",phn_field],["pin",pin_field],["state",state_field],["city",city_field]];
	 */
	  var field_names = [["customerName",name_field],["phn",phn_field]];

 
	// var field_names = [ [ "customerName", name_field ], [ "phn", phn_field ]
	// ];

	if (fieldValidationWithAlertDivId(field_names, "custAddAlertMsg") > 0) {
	} else {

		$('#customerAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		var CustomerMasterObj = {};
		CustomerMasterObj.code = code;
		CustomerMasterObj.name = name;
		CustomerMasterObj.address = addrs;
		CustomerMasterObj.pin = pin;
		CustomerMasterObj.city = city;
		CustomerMasterObj.state = state;
		CustomerMasterObj.country = country;
		CustomerMasterObj.phoneNo = phn;
		CustomerMasterObj.fax = fax;
		CustomerMasterObj.obBal = opbal;
		CustomerMasterObj.creditLimit = creditLimit;
		CustomerMasterObj.gender = slgender;
		CustomerMasterObj.addharCardNo = $("#aadharcard").val();
		CustomerMasterObj.customerTypeId=custtype;
		CustomerMasterObj.dlNo=$("#dlno").val();
		CustomerMasterObj.panNo=$("#panno").val();
		CustomerMasterObj.gstNo=$("#trnno_regno").val();
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/customer/addcustomer.htm", CustomerMasterObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			var status = JSON.parse(response);
			if (status.id > 0) {
				$("#salecustname").val(name);
				$("#salecustph").val(phn);
				$("#ecardno").val(code);
				$("#salecustid").val(status.id);
				$('#add_cust_td').addClass("hide");
				$("#custCreditLimit").val(Number(creditLimit) - Number(opbal));
				$('.add_td').addClass("hide");
				$('#blacktext_td').addClass("hide");
				$('#black_td').addClass("hide");
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.greaterthanzero;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == 0) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == -1) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusone;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == -2) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.minustwo;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == -3) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusthree;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == -10) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.mailsentsuccess;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
				showConfirmModal();
				$("#confirmval").val(-1);
			}

			/*
			 * if (response == 0) {
			 * document.getElementById('confirmmessagecont').innerHTML =
			 * getCustomerText.dataNotAdd; showConfirmModal();
			 * $("#confirmval").val(-1); } else { $("#salecustname").val(name);
			 * $("#salecustph").val(phn); $("#salecustid").val(response);
			 * $('#add_cust_td').addClass("hide");
			 * $("#custCreditLimit").val(creditLimit);
			 * $('.add_td').addClass("hide");
			 * document.getElementById('confirmmessagecont').innerHTML =
			 * getCustomerText.dataSucAdd; showConfirmModal();
			 * $("#confirmval").val(-1); }
			 */

		});
	}
}

function addEditDoctor() {
	document.getElementById('docAddAlertMsg').innerHTML = '';
	var pin = $('#pin').val();
	var name = $('#dctrName').val();
	var code = $('#code').val();
	var qualification = $('#qualification').val();
	var speciality = $('#speciality').val();
	var addrs = $('#addrs').val();
	var city = $('#city').val();
	var state = $('#state').val();
	var country = $('#country').val();
	var phn = $('#phn').val();
	var fax = $('#fax').val();
	if ($('#locked').val() == "on") {
		var locked = 1;
	} else {
		var locked = 0;
	}

	if (isNaN($("#pin").val())) {
		document.getElementById('docAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Pin";
		$(this).focus();
		return false;
	} else {
		document.getElementById('docAddAlertMsg').innerHTML = "";
	}

	if ($('#opbal').val() == 0 || $('#opbal').val() == "") {
		var opbal = 0;
	} else {
		if (isNaN($("#opbal").val())) {
			document.getElementById('docAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Opening Balance";
			$(this).focus();
			return false;
		} else {
			document.getElementById('docAddAlertMsg').innerHTML = "";
			var opbal = $('#opbal').val();
		}
	}
	if ($('#commPer').val() == 0 || $('#commPer').val() == "") {
		var commPer = 0;
	} else {
		if (isNaN($("#commPer").val())) {
			document.getElementById('docAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Commision Percentage";
			$(this).focus();
			return false;
		} else {
			document.getElementById('docAddAlertMsg').innerHTML = "";
			var commPer = $('#commPer').val();
		}
	}

	var name_label = $("#doc_name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

	var field_names = [ [ "dctrName", name_field ] ];
	if (fieldValidationWithAlertDivId(field_names, "docAddAlertMsg") > 0) {
	} else {
		$('#doctorAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		var DoctorMasterObj = {};
		DoctorMasterObj.code = code;
		DoctorMasterObj.name = name;
		DoctorMasterObj.qualification = qualification;
		DoctorMasterObj.speciality = speciality;
		DoctorMasterObj.address = addrs;
		DoctorMasterObj.pin = pin;
		DoctorMasterObj.city = city;
		DoctorMasterObj.state = state;
		DoctorMasterObj.country = country;
		DoctorMasterObj.phoneNo = phn;
		DoctorMasterObj.fax = fax;
		DoctorMasterObj.opBal = opbal;
		DoctorMasterObj.commPer = commPer;
		DoctorMasterObj.isLocked = locked;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/adddocctr.htm", DoctorMasterObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == 0) {
				document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataNotAdd;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else {
				$("#saledocname").val(name);
				$("#saledocid").val(response);
				$('#add_doc_td').addClass("hide");
				$('.add_td').addClass("hide");
				document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataSucAdd;
				showConfirmModal();
				$("#confirmval").val(-1);
			}

		});
	}
}

function getSaleItem() {
	var CommResultsetObj = {};
	var itemid = $("#senditemtopoid").val();
	// console.log("itemid=" + itemid);
	CommResultsetObj.itemId = itemid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/pos/saleitemdetforret.htm", CommResultsetObj, function(response) {
		console.log("response=" + response);
		var itemcurstkdets = JSON.parse(response);

	});
}

$("#selslno").on("click", function () {
// alert("length="+$("input:checkbox[class=chk]:checked").length);
	var length=$("input:checkbox[class=chk]:checked").length ;
	var arr=[];
	var arrchk=[];
	var srate=[];
    $("input:checkbox[class=chk]:checked").each(function () {

        arr.push($("#textbox"+$(this).val()).val());
        arrchk.push($(this).attr("id"));
        srate.push($("#slrate"+$(this).val()).val());
    });
    $("#allVal").val(arr);
    $("#allChkID").val(arrchk);
    var maxslrate=Math.max.apply(Math, srate);
   // alert("maxslrate="+maxslrate);
    if(maxslrate==-Infinity){
    	maxslrate=0.00;
    }
    $("#maxSlRate").val(maxslrate);
    $("#item_rate_ls").val(maxslrate);
    $("#item_pqty").val(length);
   /*
	 * alert("srate="+srate); alert("max="+Math.max.apply(Math, srate)); var
	 * lqty = $("#item_lqty").val(); var item_vat = $("#item_vat").val(); var
	 * item_tax = $("#item_tax").val(); var item_taxPercentage =
	 * $("#item_taxPercentage").val(); var item_dis = $("#item_dis").val(); //
	 * var item_rate_ls = $("#item_mrp").val(); var item_rate_ls =
	 * $("#item_rate_ls").val(); var conv = $("#item_conv").val(); //var totlqty =
	 * (pqty * conv) + Number(lqty);length var totlqty = Number(length);
	 * 
	 * var total = 0; var discamt = 0; var vatamt = 0; var taxamt = 0; var
	 * gstamt = 0; // total = parseFloat(totlqty * item_rate_ls).toFixed(4);
	 * prev total = parseFloat(totlqty * maxslrate).toFixed(4);
	 * 
	 * discamt = total * item_dis / 100;
	 * $("#item_discamt").val(parseFloat(discamt).toFixed(4)); total = total -
	 * discamt; $("#item_tot").val(parseFloat(total).toFixed(4)); //
	 * $("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4)); prev
	 * $("#item_rate_ls").val(parseFloat(maxslrate).toFixed(4));
	 * 
	 * vatamt = parseFloat(total * item_vat / 100).toFixed(4);
	 * $("#item_vatamt").val(parseFloat(vatamt).toFixed(4)); taxamt =
	 * parseFloat(total * item_tax / 100).toFixed(4);
	 * $("#item_taxamt").val(parseFloat(taxamt).toFixed(4)); gstamt =
	 * parseFloat(total * item_taxPercentage / 100).toFixed(4);
	 * $("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4)); var isexclu =
	 * $("#isexclusive").val(); if (isexclu == 1) {
	 * $("#item_tot").val(parseFloat(Number(total) +
	 * Number(gstamt)).toFixed(4)); }
	 */
    $("#item_pqty").val(Number(length));
    $("#openSerialModal").modal("hide");
});

function closeSlNo(){
	$("#openSerialModal").modal("hide");
	// $('#pqty').next().focus();
}
// ============================= after item search it will automatically
// calculate the total value
function calculateDefaultvalue(){
	
	 	var pqty = parseFloat(isEmpty($("#item_pqty").val())==true?0:$("#item_pqty").val());
	 	var item_is_slno = parseFloat(isEmpty($("#item_is_slno").val())==true?0:$("#item_is_slno").val());
        var lqty = parseFloat(isEmpty($("#item_lqty").val())==true?0:$("#item_lqty").val());
   		var item_vat =parseFloat( isEmpty($("#item_vat").val())==true?0:$("#item_vat").val());
   		var item_tax = parseFloat(isEmpty($("#item_tax").val())==true?0:$("#item_tax").val());
   		var item_taxPercentage =parseFloat(isEmpty($("#item_taxPercentage").val())==true?0: $("#item_taxPercentage").val());
   		var item_dis =parseFloat( isEmpty($("#item_dis").val())==true?0:$("#item_dis").val());
   		var item_rate_ls =parseFloat(isEmpty($("#item_rate_ls").val())==true?0: $("#item_rate_ls").val());
   		
   		if (item_rate_ls==0) {
   			autoadd_barcodeflag=0;
		}
   		var conv =parseFloat(isEmpty($("#item_conv").val())==true?0: $("#item_conv").val());

   		// for current stock cal
   		var itemid = $("#item_id").val();
   		var itembatch = $("#item_batch").val();
   		var itemexp = $("#item_exp").val();
   		var itemmrppack = parseFloat(isEmpty($("#item_mrp_pack").val())==true?0: $("#item_mrp_pack").val());
   		var itemexpdate = ($("#item_expDate").val().trim() == "")? "NA" : $("#item_expDate").val() ;
   		var item_dual_stock= parseFloat(isEmpty($("#item_dualStockRequired_req").val())==true?0: $("#item_dualStockRequired_req").val());
   		var sales_mans_id= $("#salesman_id").val();
   		var sales_mans_per= $("#salesman_per").val();


   		
   		if (item_dual_stock==0) {
   			 // item_rate_ls = item_salerate_ls;
   			var totlqty = parseFloat(pqty) ;// + parseFloat(Number(lqty));
   		}else {
   			if (item_rate_ls>0) {
				 item_rate_ls = item_rate_ls / conv;
				}
   			var totlqty = (pqty * conv) + Number(lqty);
   		}
        if(itembatch == ""){itembatch=0;}// new added 13.6.2019
   		var CommResultsetObj = {};
   		CommResultsetObj.itemId = itemid.split("_")[0];
   		CommResultsetObj.batchNo = itembatch;
   		CommResultsetObj.expiryDateFormat = itemexp;
   		CommResultsetObj.mrp = itemmrppack;
   		CommResultsetObj.expDate = itemexpdate;
   		CommResultsetObj.saleId = $("#saleId").val();
   		$("#senditemtopoid").val(itemid.split("_")[0]);

   		var ajaxCallObject = new CustomBrowserXMLObject();
   		ajaxCallObject.callAjaxPost(BASE_URL + "/stock/getcurrstockbybatexpmrp.htm", CommResultsetObj, function(response) {
   			// console.log("response=" + response);
   			var itemcurstkdets = JSON.parse(response);
   			for ( var i = 0; i < itemcurstkdets.length; i++) {
   				var itemcurstkdet = itemcurstkdets[i];
   				// console.log("response=" + itemcurstkdet.calculateLooseQty);
   				var calculateLooseQty = itemcurstkdet.calculateLooseQty;
   				if (totlqty > calculateLooseQty) {

   					$("#currStkGraterModal").modal("show");
   					$("#currstkofitm").text(itemcurstkdet.stockQty);
   				}
   			}
   		});

   		// //var item_rate_ls = item_rate_ls;

   		var total = 0;
   		var discamt = 0;
   		var vatamt = 0;
   		var taxamt = 0;
   		var gstamt = 0;
   		var ltadj=0;

   		/*
		 * for disccount cal
		 */
   		total = parseFloat(Number(totlqty) * Number(item_rate_ls)).toFixed(4);
   		discamt = Number(total) * Number(item_dis) / 100;
   		$("#item_discamt").val(parseFloat(discamt).toFixed(4));
   		total = total - discamt;
   		/*
		 * for sales man commission cal
		 */
   		if (sales_mans_id>0) {
   			$("#salesman_amt").val(parseFloat( ( Number(total)*Number(sales_mans_per) ) / 100 ).toFixed(4))

   		}
   		/*
		 * tax or vat cal
		 */
   		gstamt = parseFloat(Number(total) * Number(item_taxPercentage) / 100).toFixed(4);
   		$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));

   		 var item_free = $("#item_free").val();
   		 var temp_free=Math.floor(item_free);
   		 item_free = Number(item_free) - Number(temp_free);
   		// LtAdj calculation start
   		if(((item_free % 1)!=0) || item_free<1)
   		{

   			var totalret = parseFloat(Number(totlqty) * Number(item_rate_ls)).toFixed(4);
   			var modFree =  Number(item_free) % 1;
   			var pqtyPerFree=0;
   			if (pqty>0) {
				pqtyPerFree =Number(pqty)/Number(modFree);
			}

   			if (totalret>0) {
				ltadj = Number(totalret)/Number(pqtyPerFree);
			}
   			$("#item_ltadj").val(parseFloat(ltadj).toFixed(4));
   			// LtAdj calculation end
   			total=parseFloat(Number(totalret)-Number(ltadj)).toFixed(4);
   			discamt =(Number(total) * Number(item_dis))/ 100;

   			$("#item_discamt").val(parseFloat(discamt).toFixed(4));
   			total=parseFloat(Number(total)-Number(discamt)).toFixed(4);

   			gstamt = parseFloat( (Number(total) * Number(item_taxPercentage)) / 100).toFixed(4);
   			$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));

   		}


   		var isexclu = $("#item_taxtype").val();
   		if (isexclu == 2) {
   			$("#item_tot").val(parseFloat(Number(total) + Number(gstamt)).toFixed(4));
   		}else
   		{
   			$("#item_tot").val(parseFloat(Number(total)).toFixed(4));
   		}




}
function cTA(){
	var tenderamt=$("#tenderamt").val();
	var cashAmt=$("#cashAmt").val();
	if(Number(tenderamt)<=Number(cashAmt)){
		$("#cashAmt").val(parseFloat(tenderamt).toFixed(2));
	}
}
function changeSeries(){
	var CommResultsetObj = {};
	CommResultsetObj.qs = $("#qs").val();

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/pos/getinvoiceprefixbyqs.htm", CommResultsetObj, function(response) {
	// console.log("response=" + response);
		var commResultset = JSON.parse(response);
	// console.log("response=" + commResultset.mulSeriesPrefix);
		$("#seriesval").val(commResultset.mulSeriesPrefix);

	});
	$("#openModalForChangeSeries").modal("show");
}

function updateSeries(){
	var CommResultsetObj = {};
	CommResultsetObj.qs = $("#qs").val();
	CommResultsetObj.mulSeriesPrefix = $("#seriesval").val();

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/pos/updateInvoicePrefixbyQS.htm", CommResultsetObj, function(response) {
		console.log("response=" + response);
		/*
		 * var commResultset = JSON.parse(response); console.log("response=" +
		 * commResultset.mulSeriesPrefix);
		 */
		$("#seriesval").val(response);

	});
}
// =================================== Shortcut key functionality
// ================================================

/* ========= For "Prev Bill" Button (Using Ctrl+B) ========= */

$(document).bind("keyup keydown", function(e) {
	if (e.ctrlKey && e.which == 66) {
		getCustPreviousBill(document.getElementById('salecustph').value);
		return false;
	}
});

/* ============================ End ======================== */

/* ============ For "Add" Button (Using Ctrl+Shift+A) ============ */

$(document).bind("keyup", function(e) {
	if (e.ctrlKey && e.shiftKey && e.which == 65) {
// if (e.which == 13) {
		addOrUpdateItemToDetailsTable(0);
		return false;
	}
});

/* ============================ End ======================== */

/* ============ For "Update" Button (Using Alt+U) ============ */

$(document).bind("keyup", function(e) {
	if (e.altKey && e.which == 85) {
// if (e.which == 13) {
		addOrUpdateItemToDetailsTable(1);
		return false;
	}
});

/* ============================ End ======================== */

/* ============ For "Clear" Button (Using Ctrl+Shift+C) ============ */

$(document).bind("keyup", function(e) {
	if (e.ctrlKey && e.shiftKey && e.which == 67) {
		clearHeaderDiv();
		return false;
	}
});

/* ============================ End ======================== */

/* ========= For "New" Button (Using Ctrl+N) ========= */

$(document).bind("keyup", function(e) {
	if (e.ctrlKey && e.which == 78) {
		openNewCashMemo();
		return false;
	}
});

/*
 * add for account
 */



function getvendorledger_sale(group_code,acc_id,ref_id,para)
{
	 // var keyword=ref_id.toString();
	// var trackname=keyword.split("_");
	/*
	 * commonobj.id=1; is call another procedure
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

	if (para==2) { // for sale

		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;

	}

if (para==3) { // for debitor

		if (ref_id==0) { // when customer is not present
			commonobj.groupCode=$('#cash_codef').val();
			commonobj.accountID=0;
			commonobj.referenceID=0;
			commonobj.id=1;
			cash_sale=1;

		}else {
			cash_sale=0;// when customer present
			commonobj.groupCode=group_code;
			commonobj.accountID=0;
			commonobj.referenceID=ref_id;
			commonobj.id=1;
		}


	}

if (para==4) { // for discount

	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;

}
if (para==5) { // for cash

	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;

}

if (para==6) { // for card

	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;

}



// $('#pleasewaitModal').modal('show');
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchledgerusinggroup.htm", commonobj, function(response) {
		$('#pleasewaitModal').modal('hide');

		var status = JSON.parse(response);

		if (para==0) {// for duties and tax
			console.log("duties and tax ");

			$.each(status, function(i) {

				// $('#duties_html1').html("Cr-"+status[i].name);
				 $('#duties_ledger_idf').val(status[i].id);

			});
		}

		if (para==1) {// for round off
			console.log("for round off ");
			$.each(status, function(i) {

				 // $('#round_html1').html(status[i].name);
				 $('#round_ledger_idf').val(status[i].id);

			});
		}
	if (para==2) { // for sale

		console.log("for sale ");
				$.each(status, function(i) {

					// $('#sales_html1').html("Cr-"+status[i].name);
					 $('#sales_ledger_idf').val(status[i].id);

				});
			}

	if (para==3) {// for debitor

		console.log(" for debitor ");

				$.each(status, function(i) {

					// $('#debitor_html1').html("Dr-"+status[i].name );
					 $('#debitor_ledger_idf').val(status[i].id);

				});
		 }

	if (para==4) {// for discount
		console.log("for discount ");
		$.each(status, function(i) {

			// $('#discount_html1').html("Dr-"+status[i].name );
			 $('#discount_ledger_idf').val(status[i].id);

		});
		 }
	if (para==5) {// for cash
		console.log("for cash ");
		$.each(status, function(i) {
			// $('#cash_ledger_html1').html("Dr-"+status[i].name );
			 $('#debitor_cahs_ledger_idf').val(status[i].id);

		});
		 }
	if (para==6) {// for card
		console.log(" for card ");
		$.each(status, function(i) {



		// $('#card_html1').html("Dr-"+status[i].name );
			 $('#card_ledger_idf').val(status[i].id);

		});
		 }

		// chngeResultStat(status);
	});

}



function searchledger(id) {


var ids="#card_ledger_id"+id;

	  /*
		 * if (isEmpty($(ids).val())) {
		 * 
		 * $(ids).val(''); }
		 */

var type= 1;



    $("#card_ledger_id1").autocomplete({
        source: function(request,
            response) {


            if (request.term.length >= 2) {
            	$('#pleasewaitModal').modal('show');

                $.ajax({
                    url: BASE_URL + "/pos/searchledgerbytype.htm",
                    type: "POST",
                    data: {
                        tagName: request.term,
                        type:type

                    },
                    dataType: "json",
                    success: function(data) {
                        /* console.log("res_len"+data.length); */
                     	$('#pleasewaitModal').modal('hide');
                        response($.map(data, function(v) {



                            return {
                                label: "Dr-"+v.name,
                                itemCode: v.id,
                                // code : v.code
                                items: v
                            };

                        }));
                    },
                    error: function(error) {

                        $('#alertMsg').html('error: ' + error);
                    }
                });
            }
        },
        select: function(e,
            ui) {
            // console.log(ui.item.itemCode);
            // console.log(ui.item.label);// $('#dr_ledger').val(ui.item.label);
	 		        	 // $('#dr_legder_id').val(ui.item.itemCode);
			$('#card_ledger_id_final').val(ui.item.itemCode);
			$('#card_ledger_id1').val(ui.item.label);



        },
        change: function(e, ui) {
            if (!(ui.item))
                e.target.value = "";
        },
    });
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


function onblur_cash_amt() {


	if (cash_sale==0) { // when customer is present
		$('#cash_led').removeClass("hide");
		getvendorledger_sale($('#cash_code1').val(),0,0,5);// for sunndry
															// creditor

		$('#pos_debit').val($('#creditAmt').val());
		$('#pos_cash_debit').val($('#cashAmt').val());

		if ($('#creditAmt').val()==0) {
			$('#creditor_led').addClass("hide");
		}else {
			$('#creditor_led').removeClass("hide");
		}
	}

	if (cash_sale==1) {


		$('#pos_debit').val($('#cashAmt').val());
		var temp_reg=parseFloat($('#paymodnettot').val())-parseFloat($('#cashAmt').val());
		// $('#cardAmt').val(temp_reg);
		$('#card_debit_amt').val(temp_reg);


	}
}
// ///////////start weight machine integration/////////////////
// show display weight
// $('input[type="checkbox"]').click(function(){
	$('#weival').click(function(){

    if($(this).is(":checked")&&$("#item_id").val()>0){
// function showDisplayWeight(){
// setTimeout(function(){
    	$('#weival').addClass("callweival");
        	var ajaxCallObject = new CustomBrowserXMLObject();
        	ajaxCallObject
        			.callAjax(
        					BASE_URL + "/pos/viewdisplayweight.htm",
        					function(response) {
// console.log(response);
        						 $('#item_pqty').val(parseFloat(response).toFixed(3));
        						/*
								 * var responseObj = JSON.parse(response);
								 * console.log(responseObj);
								 */
        					}, null);
// }, 2000);
// }
    }
    else if($(this).is(":not(:checked)")){
    	$('#weival').removeClass("callweival");
    	$('#item_pqty').val(1);
    }
});
function showWeight(){
	if($("#weival").hasClass('callweival') && $("#weival").is(":checked")){

	  	var ajaxCallObject = new CustomBrowserXMLObject();
	  	ajaxCallObject
	  			.callAjax(
	  					BASE_URL + "/pos/viewdisplayweight.htm",
	  					function(response) {
	  						 console.log(response);
	  						 $('#item_pqty').val(parseFloat(response).toFixed(3));
	  					}, null);

	}
	setTimeout(showWeight, 1000);
}
// ///////////end weight machine integration/////////////////


function salemansCommissionCal()
{

	var sales_mans_id= $("#salemanslist").val().split("_")[0];
	var sales_mans_per= $("#salemanslist").val().split("_")[1];
	$("#salesman_id").val(sales_mans_id);
	$("#salesman_per").val(sales_mans_per);
	if ($("#item_pqty").val()>0) {
		calculateDefaultvalue();
	}


	// $("#salesman_amt").val(100);
}


function itemAgainstItem()
{
	var isfreeIAI= $('#isfreeiai').is(":checked")?1:0;
	$("#item_free").val(0);


}


$("#otheradjasment").keyup(function() {
	calculateNetTotal();
	// paySaleInvModal();
});

/*
 * for state list
 */

function getStateByCountry() {

    var country = $("#country").val();
    var commonResultObj = {};
    commonResultObj.countryId = country;

    var ajaxCallObject = new CustomBrowserXMLObject();
    ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getStateByCountry.htm",
        commonResultObj,
        function(response) {
            $('#pleasewaitModal').modal('hide');
            // console.log(response);
            var res = JSON.parse(response);
            var str = "<option value='0'>Select....</option>";
            $.each(res, function(i) {
                str = str + "<option value=" + res[i].id + ">" +
                    res[i].name + "</option>";
            });
            $("#state").html(str);
        });

}

function getCityByState() {
    var country = $("#country").val();
    var state = $("#state").val();
    var commonResultObj = {};
    commonResultObj.countryId = country;
    commonResultObj.stateId = state;

    var ajaxCallObject = new CustomBrowserXMLObject();
    ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getCityByState.htm",
        commonResultObj,
        function(response) {
            $('#pleasewaitModal').modal('hide');
            // console.log(response);
            var res = JSON.parse(response);
            var str = "<option value='0'>Select....</option>";
            $.each(res, function(i) {
                str = str + "<option value=" + res[i].id + ">" +
                    res[i].name + "</option>";
            });
            $("#city").html(str);
        });

}


/*
 * open city model here
 */
function openCityModel() {

    $("#headertextofcity").text(getCustomerText.headerTextAddCity);

    $('#cityAddEditModal').modal('show');

    if ($('#country').val()>0) {
    	  $('#countryidincity').val($('#country').val());



    		 var commonResultObj1 = {};

    		 // for state
    			commonResultObj1.countryId = $('#countryidincity').val();

    			var ajaxCallObject = new CustomBrowserXMLObject();
    			ajaxCallObject.callAjaxPost(BASE_URL+ "/invsetup/getStateByCountry.htm", commonResultObj1,
    					function(response) {
    						$('#pleasewaitModal').modal('hide');
    						// console.log(response);
    						var res = JSON.parse(response);
    						var str = "<option value='0'>Select....</option>";
    						$.each(res, function(i) {
    							str = str + "<option value='" + res[i].id + "'>"
    									+ res[i].name + "</option>";
    						});
    						$("#stateList").html(str);
    						$('#stateList').val($('#state').val());
    			});


	}else {


		 $('#countryidincity').val(0);
		 $('#stateList').val(0);
	}


}


function getStateByCountryinCity() {
    var country = $("#countryidincity").val();
    var commonResultObj = {};
    commonResultObj.countryId = country;

    var ajaxCallObject = new CustomBrowserXMLObject();
    ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getStateByCountry.htm",
        commonResultObj,
        function(response) {
            $('#pleasewaitModal').modal('hide');
            // console.log(response);
            var res = JSON.parse(response);
            var str = "<option value='0'>Select....</option>";
            $.each(res, function(i) {
                str = str + "<option value='" + res[i].id + "'>" +
                    res[i].name + "</option>";
            });
            $("#stateList").html(str);
        });

}

function addEditCity() {
    var country = $('#countryidincity').val();
    var state = $('#stateList').val();
    var cityName = $('#cityName').val();

    var name_label = $("#name_label_countryincity").text();
    var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

    var name_label_state = $("#name_label_stateincity").text();
    var name_field1 = name_label_state.substring(0, name_label_state
        .lastIndexOf(" "));

    var name_label_city = $("#name_label_cityincity").text();
    var name_field2 = name_label_city.substring(0, name_label_city
        .lastIndexOf(" "));

    var field_names = [
        ["countryidincity", name_field],
        ["stateList", name_field1],
        ["cityName", name_field2]
    ];

    if (fieldValidationWithAlertDivId(field_names, "alertMsgForcity") > 0) {

    } else {
        var commonresultsetObj = {};
        commonresultsetObj.countryId = country;
        commonresultsetObj.stateId = state;
        commonresultsetObj.name = cityName;

        var ajaxCallObject = new CustomBrowserXMLObject();
        ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addcity.htm",
            commonresultsetObj,
            function(response) {
                $('#pleasewaitModal').modal('hide');
                var status = JSON.parse(response);
                // chngeResultStatForNewItem(status);
                $('#cityAddEditModal').modal('hide');

                var newcity = "<option value='" + status.id + "'>" +
                    cityName + "</option>";
                $("#city").append(newcity);
                $("#city").val(status.id);
            });
    }

}
/* ============================ End ======================== */
// ============================================= end
// =============================================================

function veiwPurOrderDet(orderno,purOrderFinyr) {
	
	document.getElementById('alertmessagecont').innerHTML = "";
	if (orderno == '' || orderno == 0) {
		$('#pleasewaitModal').modal('show');
	} else {
		$('#pleasewaitModal').modal('show');
		var CommonRelsetmapperObj = {};
		// CommonRelsetmapperObj.invoiceNo = invno;
		CommonRelsetmapperObj.invoiceNo =$("#purOrderDoc").val()+purOrderFinyr+$("#purOrderSlash").val()+ orderno;
		// CommonRelsetmapperObj.distributorId =
		// $("#seldistributor").val().split("_")[0];
		CommonRelsetmapperObj.custId = $("#salecustid").val();
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/saleorder/getsaleorderdetbyordernoforpi.htm", CommonRelsetmapperObj, function(response) {
			console.log("res=" + response);
			$('#pleasewaitModal').modal('hide');
			var purdetails = JSON.parse(response);
			console.log(purdetails);
			$("#searchmodorderno").html(orderno);
			if (purdetails.length == 0 || $("#billno").val()=="") {
				$("#purorderdetnotfounddiv").removeClass("hide");
				$("#purorderdetmodtable").addClass("hide");
				$("#purorderdetfounddiv").addClass("hide");
				$("#purorderdetailModal_okbtn").addClass('hide');
			} else {
				$("#purorderdetnotfounddiv").addClass("hide");
				$("#purorderdetmodtable").removeClass("hide");
				$("#purorderdetfounddiv").removeClass("hide");
				$("#purorderdetailModal_okbtn").removeClass('hide');
				$("#searchordermodvendorname").text($("#seldistributor option:selected").text());
				for ( var i = 0; i < purdetails.length; i++) {
					var purdetail = purdetails[i];
					// $("#searchmodorderdate").html(moment(purdetail.purchaseInvDate).format('YYYY-MM-DD'));
					// $("#searchordermodtotamt").html(purdetail.netAmount);
					$("#searchordermodvendorname").html(purdetail.distributorName);

					var starttrline = "<tr id=" + purdetail.itemId + " style='cursor: pointer;'>";
					var chkbox = "<td class='hide'><input id='" + purdetail.itemId + "_modretcheck' class='chkboxcheked' type='checkbox' value='" + JSON.stringify(purdetail) + "' ></td>";
					var itmname = "<td>" + purdetail.itemName + "</td>";
					if(purdetail.batchWiseStock==1) {
						if(isOptical==0) {
							var batch = "<td><input type='text' id='" + purdetail.itemId + "_batch' onkeyup='checkBatchOfOrderItem(this.value,this.id)' value='0'/></td>";
						}
						else if(isOptical==1) {
							var batch = "<td><input type='text' id='" + purdetail.itemId + "_batch' value='"+purdetail.batchNo+"'/></td>";
						}
					} else {
						var batch = "<td><input class='hide' type='text' id='" + purdetail.itemId + "_batch' value='0'/></td>";
					}

					if(purdetail.expiryDateRequired==1) {
						if (isWholesale==1) {
							var exp = "<td><input type='text' id='" + purdetail.itemId + "_exp' class='itemexpiry' value='"+purdetail.expiryDateFormat+"' placeholder='MM/YY' maxlength='5' onchange='expiryCalculation(this.value,"+purdetail.purchaseOrderId+","+purdetail.itemId+");'/></td>";
						}
						else {
							var exp = "<td><input type='text' id='" + purdetail.itemId + "_exp' value='"+purdetail.expiryDateFormat+"' placeholder='YYYY/MM/DD'/></td>";
						}
					} else {
						var exp = "<td><input class='hide' type='text' id='" + purdetail.itemId + "_exp' value='NA'/></td>";
					}
					var packQty = "<td class='pndpckqty'>" + purdetail.pendingPackQty + "</td>";
					var mrp = "";
					if($("#isMrpEnableFlag").val()==1)
					{
						mrp = "<td>" + parseFloat(purdetail.mrp).toFixed(2) + "</td>";
					}
					else
					{
						mrp = "<td class='hide'>" + parseFloat(purdetail.mrp).toFixed(2) + "</td>";
					}
					var rate = "<td>" + parseFloat(purdetail.rate).toFixed(2) + "</td>";
					var taxper = "<td>" + parseFloat(purdetail.taxPercentage).toFixed(2) + "</td>";
					var discper = "<td>" + parseFloat(purdetail.discPer).toFixed(2) + "</td>";
					var addbtn = "<td><button attr_val='"+JSON.stringify(purdetail)+"' attr_id='"+purdetail.itemId+"' class='addso btn btn-success btn-sm'>Add</button></td>";
					var slnos = "<td class='tbl_slnos hide'></td>"

					var allremaining = "<td class='hide'><input type='hidden' class='hide item_id'/><input type='hidden' class='hide item_name'/><input type='hidden' class='hide item_barcode'/><input type='hidden' class='hide item_batch'/><input type='hidden' class='hide item_exp'/><input type='hidden' class='hide item_expDate'/><input type='hidden' class='hide item_packunitid'/><input type='hidden' class='hide item_looseunitid'/><input type='hidden' class='hide item_stockedqty'/><input type='hidden' class='hide item_mrp_pack'/><input type='hidden' class='hide item_mrp'/><input type='hidden' class='hide item_rate_ls'/><input type='hidden' class='hide item_rate_ls_hid'/><input type='hidden' class='hide item_pqty'/><input type='hidden' class='hide item_lqty'/><input type='hidden' class='hide item_dis_td'/>.show();<input type='hidden' class='hide dis_label'/>.show();<input type='hidden' class='hide item_dis'/><input type='hidden' class='hide item_discount'/><input type='hidden' class='hide item_maxdisper'/><input type='hidden' class='hide item_vat'/><input type='hidden' class='hide item_tax'/><input type='hidden' class='hide item_mfg'/><input type='hidden' class='hide item_mfg'/><input type='hidden' class='hide item_content'/><input type='hidden' class='hide itemContent'/><input type='hidden' class='hide itemContent'/><input type='hidden' class='hide content_Dets'/><input type='hidden' class='hide item_content'/><input type='hidden' class='hide item_conv'/><input type='hidden' class='hide item_sche'/><input type='hidden' class='hide item_taxId'/><input type='hidden' class='hide item_taxPercentage'/><input type='hidden' class='hide item_isGroupTax'/><input type='hidden' class='hide item_vat'/><input type='hidden' class='hide item_tax'/><input type='hidden' class='hide item_mfg'/><input type='hidden' class='hide item_mfg'/><input type='hidden' class='hide item_content'/><input type='hidden' class='hide itemContent'/><input type='hidden' class='hide itemContent'/><input type='hidden' class='hide content_Dets'/><input type='hidden' class='hide item_content'/><input type='hidden' class='hide item_conv'/><input type='hidden' class='hide item_sche'/><input type='hidden' class='hide item_taxId'/><input type='hidden' class='hide item_taxPercentage'/><input type='hidden' class='hide item_isGroupTax'/><input type='hidden' class='hide item_sale_rate'/><input type='hidden' class='hide item_rate_ls'/><input type='hidden' class='hide item_rate_ls_hid'/><input type='hidden' class='hide item_purChase_rate'/><input type='hidden' class='hide item_dualStockRequired_req'/><input type='hidden' class='hide item_pqty'/><input type='hidden' class='hide stkdetModal'/><input type='hidden' class='hide weival'/></td>";

					var endtrline = "</tr>";
					createdrowline = starttrline + chkbox + itmname + batch + exp + packQty + mrp + rate + taxper + discper + addbtn + slnos + allremaining + endtrline;
					$("#searchordermodtbody").append(createdrowline);
				}
			}

			$(".itemexpiry").keyup(function() {
				if ($(this).val().length == 2) {
					if ($(this).val() <= 12 && $(this).val() > 0) {
						$(this).val($(this).val() + "/");
					} else {
						$(this).val("");
					}

				}
			});
		});
		$("#purorderdetailModal").modal("show");
	}
}

$('body').on('click',".addso",function(){
	console.log("clicked id = "+$(this).attr("id"));
	$("#ordTextBoxesGroup").html("");
	var itemdetails = JSON.parse($(this).attr("attr_val"));
	sale_order_chain = 1;
	sale_order_chain_qty = $("#"+itemdetails.itemId).find(".pndpckqty").html();
	sale_order_chain_ordid = itemdetails.saleOrderId;

	sale_order_adv_arr[itemdetails.saleOrderId] = itemdetails.advAmount;

	/* itemdetails.discount = itemdetails.disc; */
	console.log("sale_order_chain_qty = "+sale_order_chain_qty);
	if(isOptical==1) {
		getClickeditmdet(itemdetails);
	} else {
		   /* getItemDetails(itemdetails.itemId); */
		   /* console.log("second data"+JSON.stringify(itemdetails)); */
		     getItemDetailsFromSo(itemdetails);// new added 13.6.2019
	}

	closePurHisDet();
	// $("#purHistoryDiv").addClass("hide");
	/*
	 * sale_order_chain = 0; sale_order_chain_qty = 0;
	 */

	/*
	 * var txttmp = $("#ordsltmplt").html(); var tmptot = "";
	 * if($(this).is(":checked")) { getItemDetails_so(itemdetails.itemId)
	 * console.log("checked itemdetails.serialNoRequired =
	 * "+itemdetails.serialNoRequired); console.log("checked
	 * itemdetails.packQty+itemdetails.freeQty =
	 * "+(itemdetails.packQty+itemdetails.freeQty));
	 * 
	 * if(itemdetails.serialNoRequired == 1) {
	 * 
	 * for (var i = 0; i < (itemdetails.packQty+itemdetails.freeQty); i++) {
	 * //tmptot = tmptot + txttmp;
	 * 
	 * $("#ordTextBoxesGroup").append(txttmp.replace("#NO",i+1)); }
	 * //console.log("tmptot = "+tmptot);
	 * //$("#ordTextBoxesGroup").html(tmptot);
	 * $("#ordtxtno").val(itemdetails.itemId);
	 * $("#ord_openSerialModal").modal("show"); } } else {
	 * $("tr#"+itemdetails.itemId).find(".tbl_slnos").html(""); }
	 */
})



/**
 * checking current stock by item id
 */
function getItemDetails_so(itemid) {
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/stock/getcurrstock/" + itemid + ".htm", function(resp) {
		/*
		 * console.log(resp); createItemStockDetails(resp, itemid);
		 * $("#stkdetModal").modal("show");
		 */
		var itemdetail = JSON.parse(resp);
	// console.log("___current_stock___resp="+resp);
		if(itemdetail.length==1){// when no stock
			for ( var i = 0; i < itemdetail.length; i++) {
				var itmstkdet = itemdetail[i];
				// console.log(JSON.stringify(itmstkdet));
			getClickeditmdet(itmstkdet);// for set the sale table value
			}
		}else{// when stock present
			createItemStockDetails_so(resp, itemid);// if batch present then it
													// will open modal
			$("#stkdetModal_so").modal("show");
		}


	}, null);
}

function createItemStockDetails_so(itemstkdetails,
								itemid) {
	// $("#snditmtopodiv").addClass("hide");
	$("#snditmpo").addClass("hide");
	// console.log("itemstkdetails="+itemstkdetails);
	$("#itemdetails_so").text("");
	// $("#moditemname").text($("#item_name").val());
	var itemdetail = JSON.parse(itemstkdetails);
	// alert(itemdetail.length);
	// console.log("itemdetail="+itemdetail);
	var totstkitm = 0;
	var totlooseitm = 0;
	var conv = 0;
	var totlooseqty = 0;
	var totloosereorderlevelqty = 0;
	// $("#senditemtopoid").val(itemid);
	if (itemdetail.length == 0) {
		// $("#snditmtopodiv").removeClass("hide");
		// $("#snditmpo").addClass("hide");
		$("#itemnotfounddiv_so").removeClass("hide");
		$("#itemnotfoundname_so").text($("#item_name").val());
		$("#itemnotfoundid_so").val(itemid);
		$("#modtable_so").addClass("hide");

	} else {
		$("#itemnotfounddiv_so").addClass("hide");
		$("#modtable_so").removeClass("hide");
		for ( var i = 0; i < itemdetail.length; i++) {
			var itmstkdet = itemdetail[i];
			$("#moditemname_so").text(itmstkdet.itemName);
			// $("#item_name").text(itmstkdet.itemName);
			$("#modmanufname_so").text(itmstkdet.manufacturerName);
			$("#modcontentname_so").text(itmstkdet.contentName);
			$("#modrackname_so").text(itmstkdet.rackName);
			$("#modgroupname_so").text(itmstkdet.groupName);
			$("#moditemnote_so").text(itmstkdet.note);
			if (itmstkdet.stockQty == 0) {
				// $("#snditmpo").addClass("hide");
				// $("#snditmtopodiv").removeClass("hide");
				$("#itemnotfounddiv_so").removeClass("hide");
				$("#itemnotfoundname_so").text($("#item_name").val());
				$("#itemnotfoundid_so").val(itemid);
				$("#modtable_so").addClass("hide");
			} else {
				totstkitm = Number(totstkitm) + parseFloat(itmstkdet.stockQty);
				// console.log("looseQty=" + parseFloat(itmstkdet.looseQty));
				totlooseitm = Number(totlooseitm) + parseFloat(itmstkdet.looseQty);
				totlooseqty = Number(totlooseqty) + parseFloat(itmstkdet.conversion * itmstkdet.packQty + Number(itmstkdet.looseQty));
				conv = parseFloat(itmstkdet.conversion);
				totloosereorderlevelqty = Number(itmstkdet.claculateLooseReorderLevelQty);
				var starttrline = "";
				var expiryDateFormat = "";
				if (itmstkdet.expiryStatusMode == 1) {
					starttrline = "<tr id=" + itmstkdet.itemUniqueKey + " class='schx'>";
					if(itmstkdet.expiryDateFormat==""||itmstkdet.expiryDateFormat==undefined){
						expiryDateFormat = "<td>NA</td>";
					}else{
						expiryDateFormat = "<td>" + itmstkdet.expiryDateFormat + "(" + itmstkdet.expiryStatus + ")</td>";
					}

				} else {
					starttrline = "<tr id=" + itmstkdet.itemUniqueKey + " style='cursor: pointer;' onclick='javascript:getClickeditmdet_so(" + JSON.stringify(itmstkdet) + ")'>";
					if(itmstkdet.expiryDateFormat==""||itmstkdet.expiryDateFormat==undefined){
						expiryDateFormat = "<td>NA</td>";
					}else{
						expiryDateFormat = "<td>" + itmstkdet.expiryDateFormat + "</td>";
					}

				}
				var batchno = "<td>" + itmstkdet.batchNo + "</td>";

				var stockQty = "<td>" + itmstkdet.stockQty + "</td>";
				var holdQty = "<td>" + itmstkdet.holdQty + "</td>";
				var mrppack = "<td>" + parseFloat(itmstkdet.mrp).toFixed(2) + "</td>";
				var mrp = "<td>" + parseFloat(itmstkdet.mrp / itmstkdet.conversion).toFixed(2) + "</td>";
				var conversion = "<td>" + itmstkdet.conversion + "</td>";
				var packing = "<td>" + itmstkdet.netContent + "</td>";
				var purrate = "<td>" + itmstkdet.purchaseRate + "</td>";
				var srate =0;
				/*
				 * if(itmstkdet.priceListRequired==1){ srate = "<td>" +
				 * itmstkdet.saleRate + "</td>"; }else{ srate = "<td>0</td>"; }
				 */
				srate = "<td>" + itmstkdet.saleRate + "</td>";
				var endtrline = "</tr>";
// createdrowline = starttrline + batchno + expiryDateFormat + stockQty +
// holdQty + mrppack + mrp + conversion + packing+purrate+srate + endtrline;
				createdrowline = starttrline + batchno + expiryDateFormat + stockQty + holdQty +  mrppack + purrate+srate + endtrline;
				$("#itemdetails_so").append(createdrowline);
			}
			// console.log("totlooseqty="+totlooseqty);
			// $("#totalcurrstkitm").text(totstkitm + "/" + totlooseitm + " [ "
			// + (totstkitm * conv + totlooseitm) + " ]");
			$("#totalcurrstkitm").text(totstkitm + "/" + totlooseitm + " [ " + (totlooseqty) + " ]");

			$("#itemstockdetails>tbody>tr:first").addClass('rowActive');
		}
		/*
		 * if (totlooseqty <= totloosereorderlevelqty) {
		 * $("#snditmtopodiv").removeClass("hide");
		 * $("#itemnotfoundid").val(itemid); } else {
		 * $("#snditmtopodiv").addClass("hide");
		 * $("#snditmpo").addClass("hide"); }
		 */
	}

}

$(document).on('click',".chkboxcheked",function(){
	console.log("clicked id = "+$(this).attr("id"));
	$("#ordTextBoxesGroup").html("");
	var itemdetails = JSON.parse($(this).val());
	var txttmp = $("#ordsltmplt").html();
	var tmptot = "";
	if($(this).is(":checked"))
	{
		getItemDetails_so(itemdetails.itemId)
		console.log("checked itemdetails.serialNoRequired = "+itemdetails.serialNoRequired);
		console.log("checked itemdetails.packQty+itemdetails.freeQty = "+(itemdetails.packQty+itemdetails.freeQty));

		if(itemdetails.serialNoRequired == 1)
		{
			
			for (var i = 0; i < (itemdetails.packQty+itemdetails.freeQty); i++) {
				// tmptot = tmptot + txttmp;

				$("#ordTextBoxesGroup").append(txttmp.replace("#NO",i+1));
			}
			// console.log("tmptot = "+tmptot);
			// $("#ordTextBoxesGroup").html(tmptot);
			$("#ordtxtno").val(itemdetails.itemId);
			$("#ord_openSerialModal").modal("show");
		}
	}
	else {
		$("tr#"+itemdetails.itemId).find(".tbl_slnos").html("");
	}
})

$(document).on("click","#saletabitemdetails>tr>td",function(){
	
	var temp=$(this).html();
	// /alert(temp);
	
	
	
	
});

// new added 10.5.2019
function sendInvEmail(id){
	/* alert($("#allstorelists").find("option:selected").text()); */
	var mailBody = getCashMemoText.mailRefer+"&nbsp;"+$("#salecustname").val()+",\n"+getCashMemoText.mailMsgSo;
	var mailSubjct =  $("#allstorelists").find("option:selected").text()+getCashMemoText.mailSubjct;
	var emailBeanObj = {};
	emailBeanObj.toAddr = $("#custEmail").val();
	emailBeanObj.subject = mailSubjct;
	emailBeanObj.messageBody = mailBody;
	emailBeanObj.transId = id;
	emailBeanObj.transType = "SL";
	emailBeanObj.isAttachment = "Y";
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/mail/sendmail.htm", emailBeanObj, function(response) {
		console.log("send mail response:"+response);
	});
	
	
	
}

document.onkeydown = function(e) {
	// alert("dfdfdf");
	console.log(openPaymentModal);
	if(e.keyCode==13){ // pay order by enter;
	    if(openPaymentModal == 1){
			paySaleInv();
		 }
  }
};

function createOrUpdateStockTransfer(){
	
	
	if($("#item_name").val())
	{ $('#alertMsg').html('Please Update this Item ' );
		return false; }
	
	
	var id=$("#stocktransIdshow").val();
	$("#confirmtype").val(3);
	$("#confirmid").val(id);
	
	var tablerows=$("#saletabitemdetails").find("tr");
	var StockTransferDetailsArr = [];
	var StockTransferObj = {};
	var totalgross=$("#totgrossamt").val();
	
	
	StockTransferObj.id=$("#stocktransIdshow").val();
	StockTransferObj.stockTransNo=$("#stocktransnoshow").val();
	StockTransferObj.stockTransDate=$("#tdate").val();
	StockTransferObj.sendGrossAmount=$("#sndtotgrossamt").val();
	StockTransferObj.recvdGrossAmount=$("#totgrossamt").val();
	StockTransferObj.transitGrossAmount=(parseFloat($("#sndtotgrossamt").val()).toFixed(2) - parseFloat($("#totgrossamt").val()).toFixed(2));
	StockTransferObj.sendDiscAmount=$("#sndtotdiscamt").val();
	StockTransferObj.recvdDiscAmount=$("#totdiscamt").val();
	StockTransferObj.transitDiscAmount=(parseFloat($("#sndtotdiscamt").val()).toFixed(2) - parseFloat($("#totdiscamt").val()).toFixed(2));
	StockTransferObj.sendTaxAmount=$("#sndtotgstamt").val();
	StockTransferObj.recvdTaxAmount=$("#totgstamt").val();
	StockTransferObj.transitTaxAmount=(parseFloat($("#sndtotgstamt").val()).toFixed(2) - parseFloat($("#totgstamt").val()).toFixed(2));
	StockTransferObj.sendNetAmount=$("#sndnettot").val();
	StockTransferObj.recvdNetAmount=$("#nettot").val();
	StockTransferObj.transitNetAmount=(parseFloat($("#sndnettot").val()).toFixed(2) - parseFloat($("#nettot").val()).toFixed(2));
	StockTransferObj.sendRoundoff=$("#sndroundoff").val();
	StockTransferObj.recvdRoundoff=$("#roundoff").val();
	StockTransferObj.transitRoundof=(parseFloat($("#sndroundoff").val()).toFixed(2) - parseFloat($("#roundoff").val()).toFixed(2));
	StockTransferObj.sendTotalMrp=$("#sendtotmrpamt").val();
	StockTransferObj.recvdTotalMrp=$("#totmrpamt").val();
	StockTransferObj.transitTotalMrp=(parseFloat($("#sendtotmrpamt").val()).toFixed(2) - parseFloat($("#totmrpamt").val()).toFixed(2));
	StockTransferObj.isDeleted=0;
	StockTransferObj.dueDate=$("#duedate").val();
	StockTransferObj.receiveRemarks=$("#remarks").val();
	StockTransferObj.receiveStatus="Y";
	StockTransferObj.dispatchStatus="Y";
	StockTransferObj.receivedDate=$("#date").val();
	StockTransferObj.receivedBy=$("#useridrsp").val();
	StockTransferObj.companyId=$("#companyIdrsp").val();
	StockTransferObj.fromStoreId=$("#fromTrans").val();
	StockTransferObj.fromFinyrId=$("#finyrIdrsp").val();
	StockTransferObj.toStoreId=$("#toTrans").val();
	StockTransferObj.toFinyrId=$("#finyrIdrsp").val();
	StockTransferObj.createdBy=$("#useridrsp").val();
	StockTransferObj.createdDate=$("#date").val();
	StockTransferObj.updatedBy=0;
	StockTransferObj.updatedDate="null";
	StockTransferObj.finyrCode=$("#retmemoFinyr").val();
	StockTransferObj.qs=$("#qs").val();
	
	var stocktransArray=new Array();
	$(tablerows).filter(function(){
		var StockTransferDetailsObj = {};
		var batchno=$(this).find("td#saletabitembarcode").html();		
		var tds=$(this).find("td");
		var item_id=$(this).attr("id");
		// var itemname=$(tds[0]).html();
		// //var item_id=$(tds[10]).html();
		// /var expd=$(tds[3]).html();
		// //alert(item_id.split("_")[0]);
		// alert(itemname);
		// /alert(itemname);fromTrans
		// /StockTransferDetailsObj.batchNo=$(tds[2]).html();
		// StockTransferDetailsObj.stockTransId=1;
		// /StockTransferDetailsObj.stockTransNo="TR123";
		var snd=parseFloat($(this).find("td#saletabreceivepqty").html());
		var rec=parseFloat($(this).find("td#saletabpqty").html());
		var transit=snd-rec;
		StockTransferDetailsObj.id=$(this).find("td#saletabstkrecdetaisid").html();
		StockTransferDetailsObj.stockTransDate=$("#date").val();
		StockTransferDetailsObj.itemId=item_id.split("_")[0];
		StockTransferDetailsObj.batchNo=$(this).find("td#saletabbat").html();
		StockTransferDetailsObj.mfdDate=$("#date").val();
		StockTransferDetailsObj.expiryDate=$(this).find("td#saletabexpdt").html();
		StockTransferDetailsObj.expiryDateFormat=$(this).find("td#saletabexpdt").html();
		StockTransferDetailsObj.packUnitId=$(this).find("td#saletabpunitid").html();
		StockTransferDetailsObj.sendPackQty=$(this).find("td#saletabreceivepqty").html();
		StockTransferDetailsObj.recvdPackQty=$(this).find("td#saletabpqty").html();
		StockTransferDetailsObj.transitPackQty=transit;
		StockTransferDetailsObj.conversion=$(this).find("td#saletabconv").html();
		StockTransferDetailsObj.looseUnitId=$(this).find("td#saletablunitid").html();;// //
		StockTransferDetailsObj.sendLooseQty=0;
		StockTransferDetailsObj.recvdLooseQty=0;
		StockTransferDetailsObj.transitLooseQty=0;
		StockTransferDetailsObj.mrp=($(this).find("td#saletabmrppack").html()=="")?0:$(this).find("td#saletabmrpperunit").html() * parseFloat($(this).find("td#saletabconv").html());
		StockTransferDetailsObj.rate=$(this).find("td#saletabitempurrate").html();
		StockTransferDetailsObj.saleRate=$(this).find("td#saletabitemsalerate").html();
		StockTransferDetailsObj.purchaseRate=$(this).find("td#saletabitempurrate").html();
		StockTransferDetailsObj.mrpPerUnit=$(this).find("td#saletabmrpperunit").html();
		StockTransferDetailsObj.ratePerUnit=$(this).find("td#saletabrateperunit").html();
		StockTransferDetailsObj.sendItemGrossAmount=$(this).find("td#sndsaletabamt").html();
		StockTransferDetailsObj.recvdItemGrossAmount=$(this).find("td#saletabamt").html();
		StockTransferDetailsObj.transitItemGrossAmount=(parseFloat($(this).find("td#sndsaletabamt").html()) - parseFloat($(this).find("td#saletabamt").html()));
		StockTransferDetailsObj.discPer=$(this).find("td#saletabdiscperc").html();
		StockTransferDetailsObj.sendItemDiscAmount=$(this).find("td#sndsaletabdiscount").html();
		StockTransferDetailsObj.recvdItemDiscAmount=$(this).find("td#saletabdiscount").html();
		StockTransferDetailsObj.transitItemDiscAmount=(parseFloat($(this).find("td#sndsaletabdiscount").html()) - parseFloat($(this).find("td#saletabdiscount").html()));
		StockTransferDetailsObj.taxId=$(this).find("td#saletabtaxId").html();
		StockTransferDetailsObj.taxTypeId=$(this).find("td#saletabitemtaxtype").html();
		StockTransferDetailsObj.taxPercentage=$(this).find("td#saletabtaxPercentage").html();
		StockTransferDetailsObj.sendItemTaxAmount=$(this).find("td#sndsaletabitemcalcgstamt").html();
		StockTransferDetailsObj.recvdItemTaxAmount=$(this).find("td#saletabitemcalcgstamt").html();
		StockTransferDetailsObj.transitItemTaxAmount=(parseFloat($(this).find("td#sndsaletabitemcalcgstamt").html()) - parseFloat($(this).find("td#saletabitemcalcgstamt").html()));
		StockTransferDetailsObj.sendItemNetAmount=$(this).find("td#sndsaletabtotamt").html();
		StockTransferDetailsObj.recvdItemNetAmount=$(this).find("td#saletabtotamt").html();
		StockTransferDetailsObj.transitItemNetAmount=(parseFloat($(this).find("td#sndsaletabtotamt").html()) - parseFloat($(this).find("td#saletabtotamt").html()));
		StockTransferDetailsObj.actSendPackQty=$(this).find("td#saletabpqty").html();
		StockTransferDetailsObj.actSendLooseQty=$(this).find("td#salerettablqty").html();// //salerettablqty
		StockTransferDetailsObj.isDeleted=0;
		StockTransferDetailsObj.companyId=$("#companyIdrsp").val();
		StockTransferDetailsObj.fromStoreId=$("#fromTrans").val();
		StockTransferDetailsObj.fromFinyrId=41;
		StockTransferDetailsObj.toStoreId=$("#toTrans").val();
		StockTransferDetailsObj.toFinyrId=$("#finyrIdrsp").val();
		// /StockTransferDetailsObj.createdBy=$("#useridrsp").val();
		StockTransferDetailsObj.createdDate='null';
		StockTransferDetailsObj.updatedBy=0;
		StockTransferDetailsObj.updatedDate=$("#date").val();		
		stocktransArray.push(StockTransferDetailsObj);
		
		
	});
	console.log("StockReceiveObj >>>>>>>>>>>>>>>>>>> ",StockTransferObj);
	StockTransferObj.stockTransferDetails=stocktransArray;
	
	// //console.log("StockTransferObj ",StockTransferObj);
		$('#pleasewaitModal').modal('show');
		setTimeout(function() {			
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/stock/createOrUpdateStockTransferReceive.htm", StockTransferObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == '0') {
					document.getElementById('confirmmessagecont').innerHTML = getStockTransInvText.dataNotAddRec;
					$("#confirmval").val(0);
					showConfirmModal();
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getStockTransInvText.dataSucAddRec;
					$("#confirmval").val(response);
					showConfirmModal();
					
				}
			});
		}, 1000);
		
	
}
/* set item detail in sale table from so */ // new added 13.6.2019
function getItemDetailsFromSo(itemdetails) {
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/stock/getcurrstock/" + itemdetails.itemId + ".htm", function(resp) {
		console.log(resp);
		createItemStockDetails(resp, itemid);
		$("#stkdetModal").modal("show");
		var itemdetail = JSON.parse(resp);
	// console.log("___current_stock___resp="+resp);
		if(itemdetail.length==1){// when no stock
			for ( var i = 0; i < itemdetail.length; i++) {
				var itmstkdet = itemdetail[i];
				console.log("new:"+ JSON.stringify(itemdetails));
				console.log("old data:"+JSON.stringify(itmstkdet));
			    getClickeditmdet(itemdetails);// for set the sale table value
			}
		}else{// when stock present
			createItemStockDetails(resp, itemdetails.itemId);// if batch
																// present then
																// it will open
																// modal
			$("#stkdetModal").modal("show");
		}
	}, null);
}

function openStockTrnsferModule() {
	location.href = BASE_URL + '/stock/stocktransfer.htm';
}
function openStockTrnsferRegisterModule() {
	location.href = BASE_URL + '/stock/stocktransferreg.htm';
}

