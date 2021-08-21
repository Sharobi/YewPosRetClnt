function isEmpty(val) {

    return (val === undefined || val == null || val.length <= 0) ? true : false;
}


$(document).ready(function(){
	$("input:text").focus(function() { $(this).select(); } );
	$("#entrySelect").change(function(){
		if($("#entrySelect").val()==1)
		{
			$("#manual_div").removeClass("hide");
			$("#excel_div").addClass("hide");
			$("#entryDtDiv").removeClass("hide");
			$("#entrydate").val($("#todate").val());
		}
		else if($("#entrySelect").val()==2)
		{
			$("#excel_div").removeClass("hide");
			$("#manual_div").addClass("hide");
			$("#entryDtDiv").removeClass("hide");
			$("#entrydate").val($("#todate").val());
		}
		else
		{
			$("#manual_div").addClass("hide");
			$("#excel_div").addClass("hide");
			$("#entryDtDiv").addClass("hide");
			$("#entrydate").val($("#todate").val());
		}
	});

	$("#seldistributor").change(function(){
		if($("#seldistributor").val()==-1)
		{
			
			$("#headertext").text(getVendorText.headerTextAdd);
			$('#vendorAddEditModal').find('input:text').val('');
			$('#vendorAddEditModal').find('input:hidden').val('');
			$('#cntry').val(0.0);
			$('#state').val(0);
			$('#city').val(0);
			$('#addrs').val("");			
			$('#vendorAddEditModal').modal('show');
			$("#credt_limit").val(getVendorText.creditVndrAmt);
			$('#cntry').val(countryId+".0");
			getStateByCountry();setTimeout(function(){
				$('#state').val(stateId);
				getCityByState();
			},500);
		}
		else
		{}
	});

	var currentDate = new Date();
	$('#mfd').datepicker({
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
				console.log(obj[0].packUnitId);
				fillItemDetailsDiv(obj[0]);
			}, null);

			// call new ajax end
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

	var currentDate = new Date();

	$('#invdt').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
	});

	$("#vendorCloseBtn").click(function(){
		$("#seldistributor").val(0);
	});

	$("#cnfrm_cancel_btn").click(function() {
		$("#stock_add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
	});

	$(".close").click(function() {
		$("#stock_add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
	});

	$("#clear_btn").click(function() {
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#stock_add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		document.getElementById('alertMsg').innerHTML = "";
	});

	$('#purbarcode').on('keydown', function(e) {
		if (e.which == 13) {
			e.preventDefault();
			var barcode = $('#purbarcode').val();
			getItemDetailsByBarcode(barcode);
		}
	});

	$("#ratio").keyup(function() {
		var pqty = $("#pqty").val();
		var ratio = $(this).val();
		var lqty = $("#lqty").val();
		var rate = $("#rate").val();
		var taxprcnt = $("#taxprcnt").val();
		var totlqty = (pqty*ratio)+Number(lqty);
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
				//amt calculation
				if(ratio == "" || ratio == 0){}
				else
				{
					$("#total").val(parseFloat(totlqty * (rate/ratio)).toFixed(4));
				}

				//tax calculation
				if (taxprcnt == ""|| ratio == "" || ratio == 0) {

				} else {
					//var taxval = (Number(qty)) * mrp * taxprcnt / 100;
					//var taxval = (((totlqty*(mrp/ratio)))*taxprcnt)/100;
					var taxval = (((totlqty*(rate/ratio)))*taxprcnt)/100;
					$("#tax").val(parseFloat(taxval).toFixed(4));
				}

				//$("#lqty").val(pqty * ratio);
				if(ratio>1)
				{
					$("#lqty").attr("readonly",false);
				}
				else
				{
					$("#lqty").attr("readonly",true);
				}
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
	});

	// pqty change calculation
	$("#pqty").keyup(function() {
		var qty = $(this).val();
		var vatprcnt = $("#vatprcnt").val();
		var taxprcnt = $("#taxprcnt").val();
		var rate = $("#rate").val();
		var mrp = $("#mrp").val();
		var ratio = $("#ratio").val();
		if (mrp == "") {
			mrp = 0;
		}
		//$("#lqty").val(qty * ratio);
		var lqty = $("#lqty").val();
		var totlqty = (qty*ratio)+Number(lqty);
		//set total
		//$("#total").val((qty) * mrp);
		if(ratio == "" || ratio == 0){}
		else
		{
			$("#total").val(parseFloat(totlqty * (rate/ratio)).toFixed(4));
		}

		//vat calculation
		if (vatprcnt == ""|| rate == "") {

		} else {
			//var vatval = (Number(qty)) * mrp * vatprcnt / 100;
			//var vatval = (Number(qty)) * rate * vatprcnt / 100;
			var vatval = (Number((totlqty*(rate/ratio))*vatprcnt))/100;
			$("#vat").val(parseFloat(vatval).toFixed(4));
		}

		//amt calculation
		if (rate == "" || ratio == "" || ratio == 0) {

		} else {
			var amt = qty * rate;
			//$("#total").val(parseFloat(amt).toFixed(4));
			$("#total").val(parseFloat(totlqty * (rate/ratio)).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| ratio == "" || ratio == 0) {

		} else {
			//var taxval = (Number(qty)) * mrp * taxprcnt / 100;
			//var taxval = (((totlqty*(mrp/ratio)))*taxprcnt)/100;
			var taxval = (((totlqty*(rate/ratio)))*taxprcnt)/100;
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}
	});

	var pqty_field = $("#pqty_label").text();

	var lqty_field = $("#lqty_label").text();

	$("#pqty").keyup(function() {
		if(isNaN($("#pqty").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in P.Qty";
			$(this).focus();
			return false;
		}
		else
		{
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
		}
	});

	$("#lqty").keyup(function() {
		if(isNaN($("#lqty").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in L.Qty";
			$(this).focus();
			return false;
		}
		else
		{
			if($("#lqty").val()<0)
			{
				document.getElementById('alertMsg').innerHTML = lqty_field+" "+getFieldText.checkNegative;
				$(this).focus();
				return false;
			}
			else
			{
				document.getElementById('alertMsg').innerHTML = "";
				var pqty = $("#pqty").val();
				var ratio = $("#ratio").val();
				var lqty = $("#lqty").val();
				var rate = $("#rate").val();
				var vatprcnt = $("#vatprcnt").val();
				var mrp = $("#mrp").val();
				var taxprcnt = $("#taxprcnt").val();
				if(ratio=="")
				{
					ratio = 0;
				}
				if(mrp=="")
				{
					mrp = 0;
				}
				var totlqty = (pqty*ratio)+Number(lqty);
				if(ratio == "" || ratio == 0){}
				else
				{
					// Total calculation
					var total = totlqty*(rate/ratio);
					$("#total").val(parseFloat(total).toFixed(4));
				}

				// Vat calculation
				if(vatprcnt=="" || ratio=="" || ratio == 0)
				{

				}
				else
				{
					var vat = (Number((totlqty*(rate/ratio))*vatprcnt))/100;
					$("#vat").val(parseFloat(vat).toFixed(4));
				}

				// Tax Calculation
				if(taxprcnt=="" || ratio=="" || rate=="" || ratio == 0)
				{}
				else
				{
					var tax = (Number(((totlqty*(rate/ratio)))*taxprcnt))/100;
					$("#tax").val(parseFloat(tax).toFixed(4));
				}
			}
		}
	});
$("#mrp").keyup(function() {
	   document.getElementById('alertMsg').innerHTML = "";
		/*if(isNaN($("#mrp").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in MRP";
			$(this).focus();
			return false;
		}
		else
		{
			if(Number(parseFloat($("#mrp").val()).toFixed(2))>=Number($("#rate").val()))
			{
				document.getElementById('alertMsg').innerHTML = "";

			}
			else
			{
				document.getElementById('alertMsg').innerHTML = getStockInvText.mrpLessRateErr;
				$(this).focus();
				return false;
			}
       }*/

		var mrp = $(this).val();
		//var edpercnt = $("#edpercnt").val();
		var ed = $("#ed").val();
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
		var rate = 0.0;
		var sale_rate = 0.0;
		console.log($("#wholesalerProfitPrcnt").val());
		if (!(taxprcnt == ""|| mrp == "")) 
		{
			//rate calculation
			 

                /*if (isWholesale==1) // whole sale 
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
			if (isWholesale==0) // for Retail 
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
            else{// for Wholesale 
				var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
				var ptr = mrpWithoutTax*(1-(Number($("#retailerProfitPrcnt").val())/100));
				$("#sale_rate").val(parseFloat(ptr).toFixed(2));
				var pts = ptr*(1-(Number($("#wholesalerProfitPrcnt").val())/100));
				$("#rate").val(parseFloat(pts).toFixed(2));
			}
		}
	});


	//$("#mrp").keyup(function() {
	


			/*if($("#isExclusive").val()==0)
			{
				if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
				{
					document.getElementById('alertMsg').innerHTML = getStockInvText.mrpLessSRateErr;
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
					document.getElementById('alertMsg').innerHTML = getStockInvText.sRateGrtrMopErr;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
			}*/

	//	}
	//});

	// rate change calculation
	$("#rate").keyup(function() {
		var rate = $(this).val();
		var dprcnt = $("#dprcnt").val();
		var qty = $("#pqty").val();
		var ratio = $("#ratio").val();
		var lqty = $("#lqty").val();
		var taxprcnt = $("#taxprcnt").val();
		var totlqty = (qty*ratio)+Number(lqty);
		//amt calculation
		if (qty == "" || ratio == "" || ratio == 0) {

		} else {
			//var amt = qty * rate;

			var total = totlqty*(rate/ratio);
			$("#total").val(parseFloat(total).toFixed(4));
		}
		//tax calculation
		if (taxprcnt == ""|| ratio == "" || rate == "" || ratio == 0) {

		} else {
			//var taxval = (Number(qty)) * mrp * taxprcnt / 100;
			//var taxval = (((totlqty*(mrp/ratio)))*taxprcnt)/100;
			var taxval = (((totlqty*(rate/ratio)))*taxprcnt)/100;
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}
	});

	$("#rate").keyup(function() {
		if(isNaN($("#rate").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Rate";
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
				document.getElementById('alertMsg').innerHTML = getStockInvText.mrpLessRateErr;
				$(this).focus();
				return false;
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
			/*if($("#isExclusive").val()==0)
			{
				if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
				{
					document.getElementById('alertMsg').innerHTML = getStockInvText.mrpLessSRateErr;
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
					document.getElementById('alertMsg').innerHTML = getStockInvText.sRateGrtrMopErr;
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

	// taxpercent change calculation
	$("#taxprcnt").keyup(function() {
		var taxprcnt = $(this).val();
		var qty = $("#pqty").val();
		var lqty = $("#lqty").val();
		var ratio = $("#ratio").val();
		var rate = $("#rate").val();
		var mrp = $("#mrp").val();
		if (mrp == "") {
			mrp = 0;
		}
		var free = $("#free").val();
		if (free == "") {
			free = 0;
		}
		//tax calculation
		if(taxprcnt=="" || ratio=="" || rate=="" || ratio == 0) {

		} else {
			//var taxval = (Number(qty)) * mrp * taxprcnt / 100;
			var totlqty = (qty*ratio)+Number(lqty);
			var taxval = (((totlqty*(rate/ratio)))*taxprcnt)/100;
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

	});

	// vatpercent change calculation
	$("#vatprcnt").keyup(function() {
		var vatprcnt = $(this).val();
		var qty = $("#pqty").val();
		var ratio = $("#ratio").val();
		var lqty = $("#lqty").val();
		if (ratio == "") {
			ratio = 0;
		}

		var mrp = $("#mrp").val();
		if (mrp == "") {
			mrp = 0;
		}

		var rate = $("#rate").val();
		if (rate == "") {
			rate = 0;
		}

		var free = $("#free").val();
		if (free == "") {
			free = 0;
		}
		//vat calculation
		//if (qty == "" || mrp == "")
		if (qty == "" || ratio == ""){

		} else {
			//var vatval = (Number(qty)) * mrp * vatprcnt / 100;
			//var vatval = (Number(qty)) * rate * vatprcnt / 100;
			var totlqty = (qty*ratio)+Number(lqty);
			var vatval = (Number((totlqty*(rate/ratio))*vatprcnt))/100;
			$("#vat").val(parseFloat(vatval).toFixed(4));
		}

	});

	$("#taxprcnt").keyup(function() {
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

	$("#vatprcnt").keyup(function() {
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

		if(Validation()==1)
		{
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
		var itempresent = 0;
		var trId = $("#tblrow_id").val();
		$('#peitem > tbody  > tr').each(function() {
			if ((Number($(this).find('#tbl_itemid').text()) == Number($("#itemid").val())) && ($(this).find('#tbl_batch_no').text()==$("#batch_no").val())) {
				var rowId=(Number($(this).find('#tbl_itemid').text()))+"_"+$(this).find('#tbl_batch_no').text();
				if(rowId==trId)
				{}
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
			if(totalSlNo!=$("#pqty").val())
			{
				itempresent=-1;
			}
		}

		if(itempresent>=1)
		{
			$('#itemExistsModal').modal('show');
		}
		else if(itempresent==-1)
		{
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.slNoBlankErr;
			showConfirmModal();
			return false;
		}
		else
		{
			var amnt = Number($("#pqty").val() * $("#rate").val())+Number($("#tax").val());
			$("#tblrow_" + trId).find('#tbl_item_name').text($("#item_name").val());
			$("#tblrow_" + trId).find('#tbl_batch_no').text($("#batch_no").val());
			$("#tblrow_" + trId).find('#tbl_exp').text($("#exp").val());
			$("#tblrow_" + trId).find('#tbl_pqty').text($("#pqty").val());
			$("#tblrow_" + trId).find('#tbl_lqty').text($("#lqty").val());
			$("#tblrow_" + trId).find('#tbl_ratio').text($("#ratio").val());
			$("#tblrow_" + trId).find('#tbl_free').text($("#free").val());
			$("#tblrow_" + trId).find('#tbl_mrp').text($("#mrp").val());
			$("#tblrow_" + trId).find('#tbl_rate').text(rate);
			$("#tblrow_" + trId).find('#tbl_ma').text($("#ma").val());
			$("#tblrow_" + trId).find('#tbl_grp').text($("#grp").val());
			$("#tblrow_" + trId).find('#tbl_sch').text($("#sch").val());
			$("#tblrow_" + trId).find('#tbl_amnt').text(parseFloat(amnt).toFixed(4));
			$("#tblrow_" + trId).find('#tbl_grossAmt').text($("#total").val());
			$("#tblrow_" + trId).find('#tbl_mfg').text($("#mfg").val());
			$("#tblrow_" + trId).find('#tbl_edprcnt').text($("#edpercnt").val());
			$("#tblrow_" + trId).find('#tbl_ed').text($("#ed").val());
			$("#tblrow_" + trId).find('#tbl_taxprcnt').text($("#taxprcnt").val());
			$("#tblrow_" + trId).find('#tbl_tax').text($("#tax").val());
			$("#tblrow_" + trId).find('#tbl_vatprcnt').text($("#vatprcnt").val());
			$("#tblrow_" + trId).find('#tbl_vat').text($("#vat").val());
			$("#tblrow_" + trId).find('#tbl_dprcnt').text($("#dprcnt").val());
			$("#tblrow_" + trId).find('#tbl_disc').text($("#disc").val());
			$("#tblrow_" + trId).find('#tbl_id').text($("#id").val());
			$("#tblrow_" + trId).find('#tbl_itemid').text($("#itemid").val());
			$("#tblrow_" + trId).find('#tbl_punitid').text($("#punitid").val());
			$("#tblrow_" + trId).find('#tbl_saleRate').text(sale_rate);
			//common ret
			$("#tblrow_" + trId).find('#tbl_slnos').text($("#allVal").val());
			$("#tblrow_" + trId).find('#tbl_mfd').text($("#mfd").val());
			$("#tblrow_" + trId).find('#tbl_expmonth').text($("#expmonth").val());
			$("#tblrow_" + trId).find('#tbl_exp').text($("#exp").val());
			$("#tblrow_" + trId).find('#tbl_taxTypeId').text($("#item_taxTypeId").val());
		}
		clearHeaderDiv();
		/*$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#stock_add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		$("#seldistributor").val(0);
		$("#item_name").focus();*/
		calculateGrandTotal();
		calculateTotalMRP();
		calculateTotalED();
		calculateTotalVat();
		calculateTotalTax();
		calculateTotalDisc();
		calculateNetTotal();
	});


	$("#stock_add_btn").click(function() {

		if(Validation()==1)
		{
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
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


		var rowCount = $('#stockitems >tbody >tr').length;
		$("#itemcount").text(rowCount + 1);
		var itempresent = 0;
		$('#stockitems > tbody  > tr').each(function() {
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
			if(totalSlNo!=$("#pqty").val())
			{
				itempresent=-1;
			}
		}

		if (itempresent == 1) {
			$('#itemExistsModal').modal('show');
		}

		else if(itempresent==-1)
		{
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.slNoBlankErr;
			showConfirmModal();
			return false;
		}
		else {
			var strng_batch_no="'"+$("#batch_no").val()+"'";
			var strng_tbrow_id = "'"+$("#itemid").val()+"_"+$("#batch_no").val()+"'";
			var tr = '<tr id="tblrow_' + $("#itemid").val()+'_'+$("#batch_no").val() + '" style="cursor: pointer;" onclick="javascript:itemDetailView(' + $("#itemid").val() +','+strng_batch_no+ ');">';
			var totmrp = (Number($("#pqty").val()) + Number($("#free").val())) * $("#mrp").val();
			var row1 = '<td id="tbl_item_name">' + $("#item_name").val() + '</td>';
			var vendortext = '<td id="tbl_vendor_name">' + $("#seldistributor>option:selected").html() + '</td>';
			var row2 = '<td id="tbl_batch_no">' + $("#batch_no").val() + '</td>';
			//var row3 = '<td id="tbl_exp">' + $("#exp").val() + '</td>';
			var row3 = "";
			//if($("#item_expiryMonthRequired_req").val()==1){
			if($("#item_expiryDateRequired_req").val()==1){
				row3 = '<td id="tbl_exp">' + $("#exp").val() + '</td>';
			}else{
				row3 = '<td id="tbl_exp">NA</td>';
			}
			var row4 = '<td id="tbl_pqty" class="numeric">' + $("#pqty").val() + '</td>';
			var row5 = '<td id="tbl_lqty" class="numeric hide">' + $("#lqty").val() + '</td>';
			var row6 = '<td id="tbl_ratio" class="numeric hide">' + $("#ratio").val() + '</td>';
			//var row7 = '<td id="tbl_free" class="numeric hide">' + $("#free").val() + '</td>';
			var row8 = '<td id="tbl_mrp" class="numeric hide">' + $("#mrp").val() + '</td>';
			var row9 = '<td id="tbl_rate" class="numeric">' + rate + '</td>';
			//var row10 = '<td id="tbl_ed" class="numeric hide">' + $("#ed").val() + '</td>';
			var row11 = '<td id="tbl_tax" class="numeric">' + $("#tax").val() + '</td>';
			var taxTypeId = '<td id="tbl_taxTypeId" class="hide">' + $("#item_taxTypeId").val() + '</td>';
			var row12 = '<td id="tbl_vat" class="numeric hide">' + $("#vat").val() + '</td>';
			/*var row13 = '<td id="tbl_disc" class="numeric hide">' + $("#disc").val() + '</td>';*/
			var total = parseFloat(0).toFixed(4);
			var tot = $("#total").val();
			var vat = $("#vat").val();
			var tax = $("#tax").val();
			if($("#vat").val()>parseFloat(0).toFixed(4))
			{
				total = Number(tot)+Number(vat);
			}
			total = Number(tot)+Number(tax);
			var row14 = '<td id="tbl_amnt" class="numeric">' + parseFloat(total).toFixed(4) + '</td>';
			//var row15 = '<td id="tbl_totamnt" class="numeric hide">' + parseFloat(totmrp).toFixed(2) + '</td>';*/
			var row16 = '<td><button class="btn btn-theme04 btn-xs" onclick="javascript:showPurItemDelModal(' + strng_tbrow_id+ ');"><i class="fa fa-trash-o "></i></button></td>';
			/*var row17 = '<td id="tbl_ma" class="hide">' + $("#ma").val() + '</td>';
			var row18 = '<td id="tbl_grp" class="hide">' + $("#grpid").val() + '</td>';
			var row19 = '<td id="tbl_sch" class="hide">' + $("#schid").val() + '</td>';
			var row20 = '<td id="tbl_mfg" class="hide">' + $("#mfgid").val() + '</td>';
			var row21 = '<td id="tbl_edprcnt" class="hide">' + $("#edpercnt").val() + '</td>';*/
			var row22 = '<td id="tbl_taxprcnt" class="hide">' + $("#taxprcnt").val() + '</td>';
			var taxid = '<td id="tbl_taxid" class="hide">' + $("#purTaxId").val() + '</td>';
			var taxmode = '<td id="tbl_taxmode" class="hide">' + $("#purtaxmode").val() + '</td>';
			var isgrptax = '<td id="tbl_isgrptax" class="hide">' + $("#purisgrptax").val() + '</td>';
			var row23 = '<td id="tbl_vatprcnt" class="hide">' + $("#vatprcnt").val() + '</td>';
			//var row24 = '<td id="tbl_dprcnt" class="hide">' + $("#dprcnt").val() + '</td>';
			var row25 = '<td id="tbl_id" class="hide">' + $("#id").val() + '</td>';
			var row26 = '<td id="tbl_itemid" class="hide">' + $("#itemid").val() + '</td>';
			/*var row27 = '<td id="tbl_grpname" class="hide">' + $("#grp").val() + '</td>';
			var row28 = '<td id="tbl_schname" class="hide">' + $("#sch").val() + '</td>';
			var row29 = '<td id="tbl_mfgname" class="hide">' + $("#mfg").val() + '</td>';*/
			var packunitid = '<td id="tbl_punitid" class="hide">' + $("#punitid").val() + '</td>';
			var vendor = '<td id="tbl_vendor" class="hide">' + $("#seldistributor").val() + '</td>';
			var sku = '<td id="tbl_sku" class="hide">' + $("#purbarcode").val() + '</td>';
			var hsn = '<td id="tbl_hsn" class="hide">' + $("#purHsnCode").val() + '</td>';
			var tbl_sale_rate = '<td id="tbl_saleRate" class="numeric">' + sale_rate + '</td>';
			var grossAmt = '<td id="tbl_grossAmt" class="numeric">' + Number(tot) + '</td>';
			// for common retail
			var expmonth = '<td id="tbl_expmonth" class="hide">' + $("#expmonth").val() + '</td>';
			//var mfd = '<td id="tbl_mfd" class="hide">' + $("#mfd").val() + '</td>';
			if($("#item_expiryMonthRequired_req").val()==1){
		         var mfd = '<td id="tbl_mfd" class="hide">' + $("#mfd").val() + '</td>';
				}else{
				 var mfd = '<td id="tbl_mfd" class="hide">NA</td>';
				}
			var location = '<td id="tbl_location" class="hide">' + $("#location").val() + '</td>';

			var slnos = '<td id="tbl_slnos" class="numeric hide">' + $("#allVal").val() + '</td>';
			var slnoreq = '<td id="tbl_slnoreq" class="numeric hide">' + $("#serialnumberreq").val() + '</td>';
			var item_batchWiseStock_req = '<td id="tbl_item_batchWiseStock_req" class="numeric hide">' + $("#item_batchWiseStock_req").val() + '</td>';
			var item_expiryDateRequired_req = '<td id="tbl_item_expiryDateRequired_req" class="numeric hide">' + $("#item_expiryDateRequired_req").val() + '</td>';
			var item_expiryMonthRequired_req = '<td id="tbl_item_expiryMonthRequired_req" class="numeric hide">' + $("#item_expiryMonthRequired_req").val() + '</td>';
			var item_dualStockRequired_req = '<td id="tbl_item_dualStockRequired_req" class="numeric hide">' + $("#item_dualStockRequired_req").val() + '</td>';
			var item_mrpRequired_req = '<td id="tbl_item_mrpRequired_req" class="numeric hide">' + $("#item_mrpRequired_req").val() + '</td>';
			var item_locationRequired_req = '<td id="tbl_item_locationRequired_req" class="numeric hide">' + $("#item_locationRequired_req").val() + '</td>';
			var item_priceListRequired_req = '<td id="tbl_item_priceListRequired_req" class="numeric hide">' + $("#item_priceListRequired_req").val() + '</td>';

			//tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14 + row15 + row16 + row17 + row18 + row19 + row20 + row21 + row22 + row23 + row24 + row25 + row26 + row27 + row28 + row29 + packunitid + '</tr>';
			tr = tr + row1+vendortext + row2 + row3 + row4 + row5 + row6 + row8 + row9 + tbl_sale_rate + row11 +taxTypeId+ row12 + grossAmt + row14 + row16 + row22 + taxid + taxmode + isgrptax + row23 + row25 + row26 + packunitid +  vendor+ sku + hsn +expmonth+mfd+ location+slnos+slnoreq+item_batchWiseStock_req+item_expiryDateRequired_req+item_expiryMonthRequired_req+item_dualStockRequired_req+item_mrpRequired_req+item_locationRequired_req+item_priceListRequired_req+'</tr>';
			$("#stockitems tbody").append(tr);
			calculateGrandTotal();
			calculateTotalMRP();
			calculateTotalED();
			calculateTotalVat();
			calculateTotalTax();
			calculateTotalDisc();
			calculateNetTotal();
		}
		clearHeaderDiv();
		/*$("#header_div").find('input:text', 'input:hidden').val('');
		$("#itemid").val("");
		$("#seldistributor").val(0);
		$("#item_name").focus();*/

	});
});

function getItemDetailsByBarcode(barcode) {
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/purinv/getpurinvdetailsbysku/" + barcode + ".htm", function(resp) {
		console.log(resp);
		var obj = jQuery.parseJSON(resp);
		if(obj!="")
		{

			fillItemDetailsDiv(obj[0]);
			$("#editNewItemBtn").removeClass("hide");
			$("#addNewItemBtn").addClass("hide");
			$("#editItemLabel").removeClass("hide");
			$("#newItemLabel").addClass("hide");
			document.getElementById('alertMsg').innerHTML = "";
		}
		else
		{
			clearHeaderDiv();
			document.getElementById('alertMsg').innerHTML = getFieldText.wrongBarcode;
		}
	}, null);

}

function clearHeaderDiv() {
	$("#header_div").find('input:text').val('');
	$("#header_div").find('input:hidden').val('');
	$('#item_name').prop('readonly', false);
	$('#purbarcode').prop('readonly', false);
	$('#lqty').prop('readonly', false);
	$("#stock_add_btn").removeClass("hide");
	$("#edit_btn").addClass("hide");
	$("#item_id").val("0");
	$("#seldistributor").val(0);
	$("#item_name").focus();
	$("#editNewItemBtn").addClass("hide");
	$("#addNewItemBtn").removeClass("hide");
	$("#editItemLabel").addClass("hide");
	$("#newItemLabel").removeClass("hide");

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
	//$("#discprcnt_td").addClass("hide");
	//$("#discamt_td").addClass("hide");

	document.getElementById('alertMsg').innerHTML = "";
};

function addEditVendor() {
	document.getElementById('vendorAddAlertMsg').innerHTML = '';
	var vendor_id = $("#vendor_id").val();
	var pin = $('#pin').val();
	var name = $('#name').val();
	var addrs = $('#addrs').val();
	var city = $('#city').val();
	var state = $('#state').val();
	var cntry = $('#cntry').val();
	var phn1 = $('#phn1').val();
	var phn2 = $('#phn2').val();
	var fax = $('#fax').val();
	var email = $('#email').val();
	var cntct_person = $('#cntct_person').val();
	var rgstratn = $('#rgstratn').val();
	var opbalance = $('#opbalance').val();
	var credt_limit = $('#credt_limit').val();
	var licenceNo = $('#licenceNo').val();
	var discount = $('#discount').val();
    var cityId=$('#city').val();
    var dueDays=$('#dueDays').val();
    if (isEmpty(dueDays)) {
    	dueDays=0;
	}
	  var duePer=$('#duePer').val();
    if (isEmpty(duePer)) {
    	duePer=0;
	}
	if(isNaN($("#pin").val()))
	{
		document.getElementById('vendorAddAlertMsg').innerHTML = getFieldText.numberCheck+" in Pin";
		$(this).focus();
		return false;
	}
	else
	{
		document.getElementById('vendorAddAlertMsg').innerHTML = "";
	}

	if(discount=="" || discount==null)
	{
		discount = 0.00;
	}
	else
	{
		if(isNaN($("#discount").val()))
		{
			document.getElementById('vendorAddAlertMsg').innerHTML = getFieldText.numberCheck+" in Discount";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('vendorAddAlertMsg').innerHTML = "";
		}
	}

	if(opbalance=="" || opbalance==null)
	{
		opbalance = 0.00;
	}
	else
	{
		if(isNaN($("#opbalance").val()))
		{
			document.getElementById('vendorAddAlertMsg').innerHTML = getFieldText.numberCheck+" in Opening Balance";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('vendorAddAlertMsg').innerHTML = "";
		}
	}
	if(credt_limit=="" || credt_limit==null)
	{
		credt_limit = 0.00;
	}
	else
	{
		if(isNaN($("#credt_limit").val()))
		{
			document.getElementById('vendorAddAlertMsg').innerHTML = getFieldText.numberCheck+" in Credit Limit";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('vendorAddAlertMsg').innerHTML = "";
		}
	}
	var pin_label = $("#pin_label").text();
	var pin_field = pin_label.substring(0, pin_label.lastIndexOf(" "));

	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

	var addrs_label = $("#addrs_label").text();
	var addrs_field = addrs_label.substring(0, addrs_label.lastIndexOf(" "));

	var phn1_label = $("#phn1_label").text();
	var phn1_field = phn1_label.substring(0, phn1_label.lastIndexOf(" "));

	var rgstratn_label = $("#rgstratn_label").text();
	var rgstratn_field = rgstratn_label.substring(0, rgstratn_label.lastIndexOf(" "));

	var licenceNo_label = $("#licenceNo_label").text();
	var licenceNo_field = licenceNo_label.substring(0, licenceNo_label.lastIndexOf(" "));

	//var field_names = [["name",name_field],["addrs",addrs_field],["pin",pin_field],["phn1",phn1_field],["rgstratn",rgstratn_field],["licenceNo",licenceNo_field]];
	var field_names = [["name",name_field]];


	if(fieldValidationWithAlertDivId(field_names,"vendorAddAlertMsg")>0)
		{

		}
		else {
		$('#vendorAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
			var DistributorMasterObj = {};
			DistributorMasterObj.name = name;
			DistributorMasterObj.pin = pin;
			DistributorMasterObj.address = addrs;
			DistributorMasterObj.city = city;
			DistributorMasterObj.state = state;
			DistributorMasterObj.country = cntry;
			DistributorMasterObj.phoneNo1 = phn1;
			DistributorMasterObj.phoneNo2 = phn2;
			DistributorMasterObj.fax = fax;
			DistributorMasterObj.email = email;
			DistributorMasterObj.contactPerson = cntct_person;
			DistributorMasterObj.registrationNo = rgstratn;
			DistributorMasterObj.obBal = opbalance;
			DistributorMasterObj.creditLimit = credt_limit;
			DistributorMasterObj.licenceNo = licenceNo;
			DistributorMasterObj.discount = discount;
			DistributorMasterObj.cityId=cityId;
			DistributorMasterObj.dueDays=dueDays;
			DistributorMasterObj.duePer=duePer;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/addVendor.htm", DistributorMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				if (status.id > 0) {
					$("#seldistributor option:last").before($('<option>', {
					    value: status.id,
					    text: name
					}));
					$("#seldistributor").val(status.id);
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
				}else if (status.id == -10) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.mailsentsuccess;
					showConfirmModal();
					$("#confirmval").val(-1);
				}
				else {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
					showConfirmModal();
					$("#confirmval").val(-1);
				}

				/*if (response == 0) {
					document.getElementById('confirmmessagecont').innerHTML = getVendorText.dataNotAdd;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else {
					$("#seldistributor option:last").before($('<option>', {
					    value: response,
					    text: name
					}));
					$("#seldistributor").val(response);
					document.getElementById('confirmmessagecont').innerHTML = getVendorText.dataSucAdd;
					showConfirmModal();
					$("#confirmval").val(-1);
				}*/

			});
	}
}

function Validation()
{
	var counter = 0;

	var batch_field = $("#batch_label").text();

	var exp_field = $("#exp_label").text();

	var mrp_field = $("#mrp_label").text();

	var rate_field = $("#rate_label").text();

	var pqty_field = $("#pqty_label").text();

	var lqty_field = $("#lqty_label").text();

	var ratio_field = $("#ratio_label").text();

	//var field_names = [["batch_no",batch_field],["exp",exp_field],["ratio",ratio_field],["mrp",mrp_field],["rate",rate_field]];
	var field_names = [["rate",rate_field],["pqty",pqty_field]];
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

	if($("#seldistributor").val()==0)
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.chkVendorSelect;
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter =0;
		document.getElementById('alertMsg').innerHTML = "";
	}
	if(($("#pqty").val()=="" || $("#pqty").val()==0))
	{
		document.getElementById('alertMsg').innerHTML = pqty_field +getFieldText.fieldReq;
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter =0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	/*if(($("#pqty").val()=="" || $("#pqty").val()==0) && ($("#lqty").val()=="" || $("#lqty").val()==0))
	{
		document.getElementById('alertMsg').innerHTML = pqty_field +" / "+lqty_field+" "+getFieldText.fieldReq;
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		counter =0;
		document.getElementById('alertMsg').innerHTML = "";
	}*/

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
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in P.Qty";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
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
	}

	/*if(isNaN($("#lqty").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in L.Qty";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	}
	else
	{
		if($("#lqty").val()<0)
		{
			document.getElementById('alertMsg').innerHTML = lqty_field+" "+getFieldText.checkNegative;
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
		document.getElementById('alertMsg').innerHTML = getStockInvText.mrpLessRateErr;
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

/*	if($("#isExclusive").val()==0)
	{
		if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
		{
			document.getElementById('alertMsg').innerHTML = getPurOrderText.mrpLessSRateErr;
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
			document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
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
    	document.getElementById('alertMsg').innerHTML = getStockInvText.noTaxAddErr;
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

	if($("#exp").val())
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
		    };*/

	}
	return counter;
}

function showPurItemDelModal(trId) {
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
	$("#stock_add_btn").removeClass("hide");
	$("#edit_btn").addClass("hide");
	$("#header_div").find('input:text').val('');
	$("#header_div").find('input:hidden').val('');
	$("#tblrow_" + id).remove();
	$("#itemcount").text($("#itemcount").text() - 1);
	$("#item_name").attr("readonly",false);
	$("#purbarcode").attr("readonly",false);
	$("#itemid").val("");
	$("#item_name").focus();
	$("#addNewItemBtn").removeClass("hide");
	$("#editNewItemBtn").addClass("hide");
	$("#editItemLabel").addClass("hide");
	$("#newItemLabel").removeClass("hide");
	calculateGrandTotal();
	calculateTotalMRP();
	calculateTotalED();
	calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	calculateNetTotal();
	return false;
}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function fillItemDetailsDiv(itemdetval) {
	console.log(itemdetval.itemName);

	if(itemdetval.conversion<1)
	{
		$("#lqty").val(0);
		$("#lqty").attr("readonly",true);
	}
	else
	{
		$("#lqty").val(itemdetval.looseQty);
		$("#lqty").attr("readonly",false);
	}
	$("#item_name").val(itemdetval.itemName);
	$("#batch_no").val(0);
	$("#exp").val();
	$("#punitid").val(itemdetval.packUnitId);
	$("#pqty").val(itemdetval.packQty);
	$("#ratio").val(itemdetval.conversion);

	//$("#free").val(itemdetval.freeQty);
	$("#mrp").val(itemdetval.mrp);
	if(itemdetval.saleRate!=0 || itemdetval.saleRate!="")
	{
		$("#sale_rate").val(itemdetval.saleRate);
	}
	else
	{
		$("#sale_rate").val(0);
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
		$("#rate").val(0);
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
	$("#total").val(itemdetval.totAmount);
	$("#taxprcnt").val(itemdetval.taxPercentage);
	$("#purTaxId").val(itemdetval.taxId);
	$("#purtaxmode").val(itemdetval.taxMode);
	$("#purisgrptax").val(itemdetval.isGroupTax);
	 $("#item_taxTypeId").val(itemdetval.taxTypeId);
	if(itemdetval.tax==0)
	{
		$("#tax").val(itemdetval.tax);
	}
	else
	{
		var taxprcnt = $("#taxprcnt").val();
		var qty = $("#pqty").val();
		var mrp = $("#mrp").val();
		var rate = $("#rate").val();
		if (rate == "") {
			rate = 0;
		}
		//tax calculation
		if (rate == "" || qty == "") {

		} else {
			var taxval = Number(Number(itemdetval.rate) * Number((Number(itemdetval.packQty)))) * Number(itemdetval.taxPercentage) / 100;
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

		//total calculation
			var tot = Number($(tax).val()) + Number($("#total").val());
			$("#total").val(parseFloat(tot).toFixed(4));
	}
	/*$("#ma").val(0);
	$("#grp").val(itemdetval.grpName);
	$("#grpid").val(itemdetval.grpId);
	$("#sch").val(itemdetval.schdName);
	$("#schid").val(itemdetval.schdId);
	$("#mfg").val(itemdetval.manuName);
	$("#mfgid").val(itemdetval.manuId);
	$("#edpercnt").val(itemdetval.edPer);
	$("#ed").val(itemdetval.ed);
	$("#vatprcnt").val(itemdetval.vatPer);
	$("#dprcnt").val(itemdetval.discPer);
	$("#disc").val(itemdetval.disc);*/
	$("#id").val($("#tblrow_"+itemdetval.itemId).find('#tbl_id').text());
	$("#itemid").val(itemdetval.itemId);
	$("#purbarcode").val(itemdetval.sku);
	$("#purHsnCode").val(itemdetval.hsnCode);

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
		$("#item_batchWiseStock_req").val(0);
		$("#batch_no_td").removeClass('hide');
		$("#batch_label").removeClass('hide');
		$("#batch_no").val(0);
	}
	if(itemdetval.expiryDateRequired==null||itemdetval.expiryDateRequired==0){
		$("#item_expiryDateRequired_req").val(0);
		$("#exp_td").addClass('hide');
		$("#exp_label").addClass('hide');
		$("#exp_month_label").addClass('hide');
		$("#exp").val();
	}else if(itemdetval.expiryDateRequired==1){
		$("#item_expiryDateRequired_req").val(1);
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
		$("#exp_month_label").removeClass('hide');
		$("#exp").val();
	}else{
		$("#item_expiryDateRequired_req").val(0);
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
		$("#exp_month_label").removeClass('hide');
		$("#exp").val();
	}
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
		$("#item_expiryMonthRequired_req").val(0);
		$("#exp_month_label").removeClass('hide');
		$("#expmonth_val").removeClass('hide');
		$("#mfd_label").removeClass('hide');
		$("#mfd_val").removeClass('hide');
		$("#expmonth").val(0);
	}

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
		$("#item_dualStockRequired_req").val(0);
		$("#ratio_val").removeClass('hide');
		$("#ratio_label").removeClass('hide');
		$("#ratio").val(itemdetval.conversion);
	}

	$("#free").val(itemdetval.freeQty);
	$("#serialnumberreq").val(itemdetval.serialNoRequired);

	/*if(itemdetval.locationRequired==null||itemdetval.locationRequired==0){
		$("#item_locationRequired_req").val(0);
		var seslocid=$("#seslocationid").val();
		$("#location_label").addClass('hide');
		$("#locationval_td").addClass('hide');
		$("#location > [value=" + seslocid + "]").attr("selected", "true");
	}else if(itemdetval.locationRequired==1){
		$("#item_locationRequired_req").val(1);
		var seslocid=$("#seslocationid").val();
		$("#location_label").removeClass('hide');
		$("#locationval_td").removeClass('hide');
		$("#location > [value=" + seslocid + "]").attr("selected", "true");
	}else{
		$("#item_locationRequired_req").val(0);
		var seslocid=$("#seslocationid").val();
		$("#location_label").removeClass('hide');
		$("#locationval_td").removeClass('hide');
		$("#location > [value=" + seslocid + "]").removeAttr("selected");
	}*/


	var today = new Date();
	var yyyy = today.getFullYear().toString();
	var mm = (today.getMonth() + 1).toString(); // getMonth() is zero-based
	var dd = today.getDate().toString();
	$("#mfd").val(yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]));

	if(itemdetval.priceListRequired==null||itemdetval.priceListRequired==0){
		$("#item_priceListRequired_req").val(0);
		$("#item_mrpRequired_req").val(0);
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
		$("#sale_rate_val").addClass('hide');
		$("#salerate_label").addClass('hide');
		$("#mrp").val();
	}else if(itemdetval.priceListRequired==1){
		$("#item_priceListRequired_req").val(1);
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

		$("#sale_rate_val").removeClass('hide');
		$("#salerate_label").removeClass('hide');
		$("#mrp").val();
	}else{
		$("#item_priceListRequired_req").val(0);
		$("#item_mrpRequired_req").val(0);
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
		$("#sale_rate_val").removeClass('hide');
		$("#salerate_label").removeClass('hide');
		$("#mrp").val();
	}
//	alert("itemdetval.mrpRequired="+itemdetval.mrpRequired);
	if(itemdetval.mrpRequired==null||itemdetval.mrpRequired==0){
		$("#item_mrpRequired_req").val(0);
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
		$("#mrp").val();
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
		$("#mrp").val();
	}else{
		$("#item_mrpRequired_req").val(0);
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
		$("#mrp").val();
	}

}
function calculateGrandTotal() {
	console.log("call calculateGrandTotal");
	var grandtotal = 0.00;
	$('#peitem tbody tr').each(function() {
		var itmtotal = $(this).find("#tbl_amnt").html();
		grandtotal = grandtotal + Number(itmtotal);
	});
	$("#totgrosamnt").val(parseFloat(grandtotal).toFixed(2));
}
function calculateTotalMRP() {
	var grandtotalMRP = 0.00;
	$('#peitem tbody tr').each(function() {
		var itmmrp = $(this).find("#tbl_totamnt").html();
		grandtotalMRP = grandtotalMRP + Number(itmmrp);
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
		var itmtax = $(this).find("#tbl_tax").html();
		grandtotalTax = grandtotalTax + Number(itmtax);
	});
	$("#tottaxamnt").val(parseFloat(grandtotalTax).toFixed(4));
}
function calculateTotalDisc() {
	var grandtotalDisc = 0.00;
	$('#peitem tbody tr').each(function() {
		var itmdisc = $(this).find("#tbl_disc").html();
		grandtotalDisc = grandtotalDisc + Number(itmdisc);
	});
	$("#totdiscamnt").val(parseFloat(grandtotalDisc).toFixed(4));
}
function calculateNetTotal() {

	var spcldis=$("#spldisc").val();
	if(spcldis==""||spcldis==undefined){
		spcldis=0;
	}
	var spldisamt = Number($("#totgrosamnt").val()) * Number(spcldis) / 100;
	$("#spldiscamt").val(parseFloat(spldisamt).toFixed(2));
	var nettotal =( Number($("#totgrosamnt").val()) + Number($("#totvatamnt").val()) + Number($("#tottaxamnt").val())) - (Number($("#totdiscamnt").val())+Number(spldisamt));
//	var totnetamnt = $("#totnetamnt").val(parseFloat(nettotal).toFixed(2));
	//	$("#roundoff").val(parseFloat(Math.round(Number(nettotal))).toFixed(2));
	var roundednetamnt=Math.round(Number(nettotal));
	console.log("roundednetamnt="+parseFloat(roundednetamnt).toFixed(2));
	$("#totnetamnt").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff=Number(roundednetamnt)-Number(nettotal);
	console.log("roundoff="+parseFloat(roundoff).toFixed(2));
	$("#roundoff").val(parseFloat(roundoff).toFixed(2));
}

function deletePurchaseInv(){
	var pinvid=$("#orderno").val();
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.purInvId = pinvid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/deletepurinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			$("#confirmval").val(1);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.dataSucDelete;
			showConfirmModal();
		} else {
			$("#confirmval").val(0);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.dataNotDelete;
			showConfirmModal();
		}

	});
}

function itemDetailView(trId,batchNo) {
	//alert($("#tblrow_" + trId).find('#tbl_vendor').text());
	$("#item_name").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_item_name').text());
	$("#item_name").attr("readonly",true);
	$("#purbarcode").attr("readonly",true);
	if($("#tblrow_" + trId+"_"+batchNo).find('#tbl_ratio').text()==1)
	{
		$("#lqty").attr("readonly",true);
	}
	else
	{
		$("#lqty").attr("readonly",false);
	}
	$("#seldistributor").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_vendor').text());
	$("#batch_no").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_batch_no').text());
	$("#exp").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_exp').text());
	$("#pqty").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_pqty').text());
	$("#lqty").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_lqty').text());
	$("#ratio").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_ratio').text());
	//$("#free").val($("#tblrow_" + trId).find('#tbl_free').text());
	$("#mrp").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_mrp').text());
	$("#rate").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_rate').text());
	$("#sale_rate").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_saleRate').text());
	$("#total").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_grossAmt').text());
	$("#vatprcnt").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_vatprcnt').text());
	$("#vat").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_vat').text());
	$("#taxprcnt").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_taxprcnt').text());
	$("#tax").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_tax').text());
	$("#purTaxId").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_taxid').text());
	$("#purtaxmode").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_taxmode').text());
	$("#purisgrptax").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_isgrptax').text());
	/*$("#ma").val($("#tblrow_" + trId).find('#tbl_ma').text());
	$("#grp").val($("#tblrow_" + trId).find('#tbl_grp').text());
	$("#sch").val($("#tblrow_" + trId).find('#tbl_sch').text());
	$("#mfg").val($("#tblrow_" + trId).find('#tbl_mfg').text());
	$("#edpercnt").val($("#tblrow_" + trId).find('#tbl_edprcnt').text());
	$("#ed").val($("#tblrow_" + trId).find('#tbl_ed').text());
	$("#dprcnt").val($("#tblrow_" + trId).find('#tbl_dprcnt').text());
	$("#disc").val($("#tblrow_" + trId).find('#tbl_disc').text());*/
	$("#id").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_id').text());
	$("#itemid").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_itemid').text());
	$("#tblrow_id").val(trId+"_"+batchNo);
	$("#punitid").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_punitid').text());
	$("#purbarcode").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_sku').text());
	$("#purHsnCode").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_hsn').text());
	// for common retail
	$("#expmonth").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_expmonth').text());
	$("#mfd").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_mfd').text());
	$("#location").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_location').text());
	$("#serialnumberreq").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_slnoreq').text());
	$("#item_taxTypeId").val($("#tblrow_" + trId+"_"+batchNo).find('#tbl_taxTypeId').text());
	var slnos=$("#tblrow_" + trId+"_"+batchNo).find('#tbl_slnos').text();
	var sno=slnos.split(",");
	for(var i=0;i<sno.length;i++){
		//alert("slnos="+sno[i]);
		$("#textbox"+(i+1)).val(sno[i]);
	}
	if($("#tblrow_" + trId+"_"+batchNo).find('#tbl_isFree').text()=="Y")
	{
		$("#rate").attr("readonly",true);
		$("#freeCheck").prop('checked', true);
	}
	else
	{
		$("#rate").attr("readonly",false);
		$("#freeCheck").prop("checked",false);
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


/*	if(tbl_item_locationRequired_req==0){
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
	}*/

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
	//alert("tbl_item_mrpRequired_req="+tbl_item_mrpRequired_req);
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
	$("#stock_add_btn").addClass("hide");
	$("#editNewItemBtn").removeClass("hide");
	$("#addNewItemBtn").addClass("hide");
	$("#editItemLabel").removeClass("hide");
	$("#newItemLabel").addClass("hide");
}

function targetURL(){
	var result=$("#confirmval").val();
	console.log("save pur inv id targetURL="+result);
	if(result==0){
		location.href = BASE_URL + '/stock/stockentry.htm';
	}
	else if(result==-1){
		location.href = "#";
	}
	else if($("#confirmval").val()=="itemAddOk")
	{
		fillItemDtlsDivFrmModal(itemObj);
		var mrp = $("#mrp").val();
		var qty = $("#pqty").val();
		var ratio = $("#ratio").val();
		var lqty = $("#lqty").val();
		var taxprcnt = $("#taxprcnt").val();
		var totlqty = (qty*ratio)+Number(lqty);

		//rate calculation
		if (taxprcnt == ""|| mrp == "") {

		}
		else
		{
			var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
			var ptr = mrpWithoutTax*(1-(Number($("#retailerProfitPrcnt").val())/100));
			$("#rate").val(parseFloat(ptr).toFixed(2));
		}

		//sale rate calculation
		if (taxprcnt == ""|| mrp == "") {

		}
		else
		{
			if($("#item_tax_type").val()==0)//isExclusive
			{
				$("#sale_rate").val(parseFloat(mrp).toFixed(2));
			}
			else
			{
				var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
				$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));
			}
			/*var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
			$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));*/
		}

		var rate = $("#rate").val();

		//amt calculation
		if (qty == "" || ratio == "" || ratio == 0) {

		} else {
			//var amt = qty * rate;

			var total = totlqty*(rate/ratio);
			$("#total").val(parseFloat(total).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| ratio == "" || rate == "" || ratio == 0) {

		} else {
			//var taxval = (Number(qty)) * mrp * taxprcnt / 100;
			//var taxval = (((totlqty*(mrp/ratio)))*taxprcnt)/100;
			var taxval = (((totlqty*(rate/ratio)))*taxprcnt)/100;
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
	else{
		location.href = BASE_URL + '/stock/stockentry.htm';
	}
}

function uploadStock()
{
	if($("#fileUpload").val()=="" || $("#fileUpload").val()==0)
	{
		$("#confirmval").val(-1);
		document.getElementById('confirmmessagecont').innerHTML = "Please choose any excel file.";
		showConfirmModal();
		return false;
	}
	else
	{
		$("#excelEntryDate").val($("#entrydate").val());
		$("#stock_upload_form").submit();

	}
}

function saveStockManual()
{
	if($("#itemid").val()==0 || $("#itemid").val()=="")
	{
		console.log("vendordis="+$("#vendordis").val());

		var StockObj = {};
		var StockDetailsObjArr = [];
		var stockItemCount = $('#stockitems >tbody >tr').length;
		$("#stockitems tr").not('thead tr').each(function(	i,
														v) {
			var StockDetailsObj = {};
			StockDetailsObj.batchNo = $(this).find('#tbl_batch_no').text();
			StockDetailsObj.expiryDateFormat = $(this).find('#tbl_exp').text();
			if($(this).find('#tbl_pqty').text() == "")
			{
				StockDetailsObj.packQty = 0;
			}
			else
			{
				StockDetailsObj.packQty = $(this).find('#tbl_pqty').text();
			}

			if($(this).find('#tbl_lqty').text() == "")
			{
				StockDetailsObj.looseQty = 0;
			}
			else
			{
				StockDetailsObj.looseQty = $(this).find('#tbl_lqty').text();
			}
			StockDetailsObj.conversion=$(this).find('#tbl_ratio').text();
			StockDetailsObj.itemId = $(this).find('#tbl_itemid').text();
			StockDetailsObj.id = 0;// $(this).find('#tbl_id').text();
			StockDetailsObj.mrp = $(this).find('#tbl_mrp').text();
			StockDetailsObj.rate = $(this).find('#tbl_rate').text();
			StockDetailsObj.lastDistributorId= $(this).find('#tbl_vendor').text();
			StockDetailsObj.packUnitId = $(this).find('#tbl_punitid').text();
			StockDetailsObj.asOnDate = $("#entrydate").val();
			StockDetailsObj.finyrId = finyrId;
			StockDetailsObj.storeId = storeId;
			StockDetailsObj.companyId = cmpnyId;
			StockDetailsObj.createdBy = createdBy;
			StockDetailsObj.taxId = $(this).find('#tbl_taxid').text();
			StockDetailsObj.taxAmount = $(this).find('#tbl_tax').text();
			StockDetailsObj.taxPercentage = $(this).find('#tbl_taxprcnt').text();
			StockDetailsObj.isGroupTax = $(this).find('#tbl_isgrptax').text();
			StockDetailsObj.taxMode = $(this).find('#tbl_taxmode').text();
			StockDetailsObj.saleRate = $(this).find('#tbl_saleRate').text();
			//new common ret
			StockDetailsObj.mfdDate=$(this).find('#tbl_mfd').text();
			StockDetailsObj.locationId=$(this).find('#tbl_location').text();
			StockDetailsObj.expiryMonth=$(this).find('#tbl_expmonth').text();
			StockDetailsObj.taxTypeId=$(this).find('#tbl_taxTypeId').text();
			
			// serialno add tbl_expmonth
			invOpeningStockDetailsSerialMapperObjArr=[];
			var slnos=$(this).find('#tbl_slnos').text();
			var slnoarr=slnos.split(",");
			StockDetailsObj.tmpMappingId=(i+1);
			for(var h=0;h<slnoarr.length;h++){
				var invOpeningStockDetailsSerialMapperObj={};
				invOpeningStockDetailsSerialMapperObj.itemId=$(this).find('#tbl_itemid').text();
				invOpeningStockDetailsSerialMapperObj.batchNo=$(this).find('#tbl_batch_no').text();
				invOpeningStockDetailsSerialMapperObj.uniqueIdentifierNo=slnoarr[h];
				invOpeningStockDetailsSerialMapperObj.qty=$(this).find('#tbl_pqty').text();
				invOpeningStockDetailsSerialMapperObj.tmpMappingId=(i+1);
				invOpeningStockDetailsSerialMapperObjArr.push(invOpeningStockDetailsSerialMapperObj);
			}
			StockDetailsObj.invOpeningStockDetails=invOpeningStockDetailsSerialMapperObjArr;
			StockDetailsObjArr[i] = StockDetailsObj;
		});
		StockObj.openingStockDetails = StockDetailsObjArr;
		console.log("StockObj json: " + JSON.stringify(StockObj));

		if(stockItemCount>0)
		{
			$('#pleasewaitModal').modal('show');
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/stock/createorupdateStock.htm", StockObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == '1') {
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.dataSucAdd;
					$("#confirmval").val(0);
					showConfirmModal();
				}
				else {
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.dataNotAdd;
					$("#confirmval").val(-1);
					showConfirmModal();
				}

			});
		}
	}
	else
	{
		$("#confirmval").val(-1);
		document.getElementById('confirmmessagecont').innerHTML = getStockInvText.addEditChckBefrSave;
		showConfirmModal();
	}

}

function calculateExpiry(){
	var mfd=$("#mfd").val();
	var expmonth=$("#expmonth").val();
	if(expmonth == ""){
		expmonth=0;
	}
	var today = moment(mfd).format('YYYY-MM-DD');
	var dateA = moment(today).add(expmonth, 'months');
	var today1 = moment(dateA).format('YYYY-MM-DD');
	console.log("d1="+today1);
	//$("#exp").val(today1);
	$("#exp").datepicker("setDate", today1);

}
function calExpMonth(){
	/*
	var exp = $("#exp").val();
	var mfd = $("#mfd").val();

	var mfdDate = moment(mfd).format('YYYY-MM-DD');
	var expDate = moment(exp).format('YYYY-MM-DD');

	var duration = moment.duration(expDate.diff(mfdDate));
	$("#expmonth").val(duration.asMonths());
	*/

	//alert($("#exp").val());
	var exp = moment($("#exp").val());
	console.log(exp);
	var month=exp.diff($("#mfd").val(), 'months');

	$("#expmonth").val(month);
}

function openSerialModal(qty){
//	alert("hi");
//	alert("qty="+qty);
	var serialnum=$("#serialnumberreq").val();
	//var oldpqty=$("#oldpqty").val();
	//alert("oldpqty="+oldpqty);
	//alert("serialnum="+serialnum);
	//var qtysl=$("#qtysl").val();
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
	var counter=$("#pqty").val();
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
	if(blank==1|| vreg==1){
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
		//*****************
		$("#allVal").val(arr);
		for(var i=1; i<=counter; i++){
		$('#textbox' + i).val("");
		}
	}
	$('#batch_no').focus();
	//$('#pqty').next().focus();
	//$("#TextBoxesGroup").remove();openSerialModal
	}
}
function closeSlNo(){
	$("#openSerialModal").modal("hide");
	$('#batch_no').focus();
	//$('#pqty').next().focus();
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



/*
 * for state list
 */

function getStateByCountry() {

    var country = $("#cntry").val();
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
    var country = $("#cntry").val();
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

    $("#headertextofcity").text(getVendorText.headerTextAddCity);

    $('#cityAddEditModal').modal('show');

    if ($('#cntry').val()>0) {
    	  $('#countryidincity').val($('#cntry').val());



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
                //chngeResultStatForNewItem(status);
                $('#cityAddEditModal').modal('hide');

                var newcity = "<option value='" + status.id + "'>" +
                    cityName + "</option>";
                $("#city").append(newcity);
                $("#city").val(status.id);
            });
    }

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
			//expiryCalculation($(this).val());
		}


		
	}
});
