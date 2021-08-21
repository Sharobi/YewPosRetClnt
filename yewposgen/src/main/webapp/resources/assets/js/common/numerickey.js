$(document).ready(function(){
                  //for retail calculator
                  var keyPad=null;
                  $('.form-control-trx').focus(function(){
                	  if(keyPad!=this)
                		  {
                		  keyPad=this;
                		  }
                	  });
                  $('.retnumero').click(function(){
                      if (!isNaN($(keyPad).val())) {
                         if (parseInt($(keyPad).val()) == 0) {
                           $(keyPad).val($(this).text());
                         } else {
                           $(keyPad).val($(keyPad).val() + $(this).text());
                         }
                         calcOfAll(keyPad);
                      }
                    });
                  $('.bigcurr').click(function(){
                      if (!isNaN($(keyPad).val())) {
                         if ($(keyPad).val() == '') {
                           $(keyPad).val($(this).text());
                         } else {
                           $(keyPad).val(parseInt(parseInt($(keyPad).val()) + parseInt($(this).text())));
                         }
                         calcOfAll(keyPad);
                      }
                    });
                  $('.retdel').click(function(){
                      $(keyPad).val($(keyPad).val().substring(0,$(keyPad).val().length - 1));
                      calcOfAll(keyPad);
                  });
                  $('.retclr').click(function(){
                      $(keyPad).val('');
                      calcOfAll(keyPad);
                  });
                  $('.retnumerozero').click(function(){
                    if (!isNaN($(keyPad).val())) {
                      if (parseInt($(keyPad).val()) != 0) {
                        $(keyPad).val($(keyPad).val() + $(this).text());
                      }
                      calcOfAll(keyPad);
                    }
                  });
                  $('.retnumerodzero').click(function(){
                      if (!isNaN($(keyPad).val())) {
                        if (parseInt($(keyPad).val()) != 0) {
                          $(keyPad).val($(keyPad).val() + $(this).text());
                        }
                        calcOfAll(keyPad);
                      }
                    });
                 
      });
function payExtAmt(){
	$("#tenderamt").val(parseFloat($("#nettot").val()).toFixed(2));
}

function calcOfAll(keyPad){
	// tenderamt calculation
    if(keyPad.id=='tenderamt')
   	 {
   	 if (isNaN($(keyPad).val())) {
			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Tender Amount";
			$(this).focus();
			return false;
		} else {
			var amt = Number($(keyPad).val());
			var payretadjamt = $("#payretadjamt").val();
			var nettot = $("#nettot").val();
			nettot = nettot - Number(payretadjamt);
			var cashamt = $("#cashAmt").val();
			if (cashamt == '') {
				console.log("cashamt="+cashamt);
				cashamt = 0.00;
			}
			/*if(Number(amt)<=Number(cashamt)){
				$("#cashAmt").val(parseFloat(amt).toFixed(2));
			}*/
			if (amt > nettot) {
				$("#refundAmt").val(parseFloat(amt - nettot).toFixed(2));
			} else {
				$("#refundAmt").val(parseFloat(0).toFixed(2));
			}
			if (nettot == 0) {
				$("#cashAmt").val(parseFloat(0).toFixed(2));
				$("#tenderamt").val(parseFloat(0).toFixed(2));
				$("#refundAmt").val(parseFloat(0).toFixed(2));
			}
			document.getElementById('alertmessagecont').innerHTML = "";
		}
    }
    //cashamt calculation
    if(keyPad.id=='cashAmt')
	 {
    if (isNaN($(keyPad).val())) {
		document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Cash Amount";
		$(this).focus();
		return false;
	} else {
		document.getElementById('alertmessagecont').innerHTML = "";
		//var cashamt = Number($(this).val());
		var cashamt =Number($(keyPad).val());
		
		var tenderamt = $("#tenderamt").val();
		if(cashamt>tenderamt){
			$("#cashAmt").val(parseFloat(tenderamt).toFixed(2));
			cashamt=tenderamt;
		}
		var payretadjamt = $("#payretadjamt").val();
		var nettot = $("#nettot").val();
		nettot = nettot - Number(payretadjamt);
		
		var cardAmt = $("#cardAmt").val();
		if (cardAmt == '') {
			cardAmt = 0.00;
		}
		if (cashamt > nettot) {
			$("#cashAmt").val(parseFloat(nettot).toFixed(2));
			cashamt = parseFloat(nettot).toFixed(2);
		}
		
		//$("#refundAmt").val(parseFloat(tenderamt - cashamt).toFixed(2));
		if (tenderamt>0) {
			$("#refundAmt").val(parseFloat(tenderamt - cashamt).toFixed(2));
		}else {
			$("#refundAmt").val(parseFloat(0).toFixed(2));
		}
		
		
		var totamt = (Number(cashamt) + Number(cardAmt));
		if (totamt >= nettot) {
			var creditcustomer = $("#salecustid").val();
			
			if(creditcustomer==0){
				
				if (totamt > nettot) {
					$("#cardAmt").val(parseFloat(nettot - Number(cashamt)).toFixed(2));
				}
				$("#creditAmt").val(parseFloat(0).toFixed(2));
			}else{
				//$("#creditAmt").val(parseFloat(nettot - Number(totamt)).toFixed(2));
				 if(totamt >= nettot)
					{
						if(cardAmt>cashamt)
						{
							  cashamt=parseFloat(nettot - cardAmt).toFixed(2);
							 $("#cashAmt").val(parseFloat(nettot - cardAmt).toFixed(2));
							
						}
						else {
							
							cardAmt=parseFloat(nettot - cashamt).toFixed(2);
							$("#cardAmt").val(parseFloat(nettot - cashamt).toFixed(2));
						}
						$("#creditAmt").val(parseFloat(0.0).toFixed(2));
					}else
					{
						$("#creditAmt").val(parseFloat(nettot - cardAmt).toFixed(2));
					} 
			}
			
		} else {
			$("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
		}
		document.getElementById('confirmmessagecont').innerHTML = "";
	}
}
    
    // cardamt calculation
    if(keyPad.id=='cardAmt')
	 {
   	 if (isNaN($(keyPad).val())) {
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Amount";
			$(this).focus();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
			var cardAmt = Number($(keyPad).val());
			var payretadjamt = $("#payretadjamt").val();
			var nettot = $("#nettot").val();
			nettot = nettot - Number(payretadjamt);
			var tenderamt = $("#tenderamt").val();
			if (cardAmt > nettot) {
				$("#cardAmt").val(parseFloat(nettot).toFixed(2));
				cardAmt = parseFloat(nettot).toFixed(2);
			}

			var cashamt = $("#cashAmt").val();
			if (cashamt == '') {
				cashamt = 0.00;
			}
			var totamt = (Number(cashamt) + Number(cardAmt));
			if (totamt >= nettot) {
				var creditcustomer = $("#salecustid").val();
				if(creditcustomer==0){
					
					if (totamt > nettot) {
						$("#cashAmt").val(parseFloat(nettot - Number(cardAmt)).toFixed(2));
						cashamt = parseFloat(nettot - Number(cardAmt)).toFixed(2);
					}
					$("#creditAmt").val(parseFloat(0).toFixed(2));
				}else{
					//$("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
					
					if(totamt >= nettot)
					{
						if(cardAmt>cashamt)
						{
							  cashamt=parseFloat(nettot - cardAmt).toFixed(2);
							 $("#cashAmt").val(parseFloat(nettot - cardAmt).toFixed(2));
							
						}
						else {
							
							cardAmt=parseFloat(nettot - cashamt).toFixed(2);
							$("#cardAmt").val(parseFloat(nettot - cashamt).toFixed(2));
						}
						$("#creditAmt").val(parseFloat(0.0).toFixed(2));
					}else
					{
						$("#creditAmt").val(parseFloat(nettot - cardAmt).toFixed(2));
					} 
				}
				
			} else {
				$("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
			}
			
		//	$("#refundAmt").val(parseFloat(tenderamt - cashamt).toFixed(2));
			if (tenderamt>0) {
				$("#refundAmt").val(parseFloat(tenderamt - cashamt).toFixed(2));
			}else {
				$("#refundAmt").val(parseFloat(0).toFixed(2));
			}
			
			document.getElementById('confirmmessagecont').innerHTML = "";
		}
	 }
        
    // spcl disc perc calculation
    if(keyPad.id=='spcldiscperc')
	 {
       calSpclDisNew();
	 }
    // item_dis calculation
    if(keyPad.id=='item_dis')
	 {
    	var item_dis = Number($(keyPad).val());
		var item_maxdisper = $("#item_maxDiscountLimit").val();
		var item_taxPercentage = $("#item_taxPercentage").val();
		if (Number(item_dis) > Number(item_maxdisper)) {
			$("#itemmaxdisperspan").text(parseFloat(item_maxdisper).toFixed(2));
			$("#item_dis").val(parseFloat(0).toFixed(4));
			$("#itemMaxDisModal").modal("show");
		}
		var pqty = $("#item_pqty").val();
		var item_vat = $("#item_vat").val();
		var item_tax = $("#item_tax").val();
		var lqty = $("#item_lqty").val();
		//	var item_rate_ls = $("#item_rate_ls_hid").val();
		//		var item_rate_ls = $("#item_mrp").val();
		var item_rate_ls = $("#item_rate_ls").val();
		var conv = $("#item_conv").val();
		var total = 0;
		var discamt = 0;
		var vatamt = 0;
		var taxamt = 0;
		var gstamt = 0;

		var totlqty = (pqty * conv) + Number(lqty);
		/*discamt = item_rate_ls * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4));
		var rate = item_rate_ls - discamt;
		$("#item_rate_ls").val(parseFloat(rate).toFixed(4));
		total = parseFloat(totlqty * rate).toFixed(4);*/
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
		var item_free = $("#item_free").val();
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
	 }
    // qty calculation
    if(keyPad.id=='item_pqty')
	 {
    	var pqty = Number($(keyPad).val());
		var item_is_slno = $("#item_is_slno").val();
		var itemid = $("#item_id").val();
		/*if(itemid==""){
			itemid=$("#itemid").val();
		}*/
		//alert("itemid="+itemid);
		if(item_is_slno==1){
			//console.log("item_is_slno="+item_is_slno);
			for ( var i = 1; i <= 30; i++) {
				$("#checkbox"+i).attr('checked', false);
			}
			/*var slnoschk=$('#selitem tr#' + itemid).find('#saletabitemchkslnosid').text();
			
			var slnoschked=slnoschk.split(",");
			alert("slnoschked="+slnoschked);
			for(var i=0;i<slnoschked.length;i++){
				$("#"+slnoschked[i]).prop("checked",true);	
			}*/
			
			// edit serial no.
				var slnos=$("#selitem tr#" + itemid).find("#saletabitemslnos").text();
			    var sno=slnos.split(",");
			//
			var CommResultsetObj = {};
			CommResultsetObj.itemId = itemid.split("_")[0];
			CommResultsetObj.saleId = $("#saleId").val();

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/stock/getserialnobyitemid.htm", CommResultsetObj, function(response) {
				console.log("response=" + response);
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
								//alert("slnos="+sno[i]);
								if(sno[m]==itemserialnodet.uniqueIdentifierNo){
									$("#checkbox"+(i+1)).prop("checked",true);	
								}
								
							}
							
							/*if(jQuery.inArray(itemserialnodet.uniqueIdentifierNo, slnos) !== -1){
							    console.log("is in array");
							    $("#checkbox"+(i+1)).prop("checked",true);	
							}*/
							$("#TextBoxDiv_"+(i+1)).removeClass("hide");
							//$("#checkbox"+(i+1)).attr('checked', true);
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
			var lqty = $("#item_lqty").val();
			var item_vat = $("#item_vat").val();
			var item_tax = $("#item_tax").val();
			var item_taxPercentage = $("#item_taxPercentage").val();
			var item_dis = $("#item_dis").val();
			//		var item_rate_ls = $("#item_mrp").val();
			var item_rate_ls = $("#item_rate_ls").val();
			var conv = $("#item_conv").val();
			var totlqty = (pqty * conv) + Number(lqty);
			// for current stock cal
			var itemid = $("#item_id").val();
			var itembatch = $("#item_batch").val();
			var itemexp = $("#item_exp").val();
			var itemmrppack = $("#item_mrp_pack").val();
			var itemexpdate = ($("#item_expDate").val()=="")? "NA" : $("#item_expDate").val() ;

			var CommResultsetObj = {};
			CommResultsetObj.itemId = itemid.split("_")[0];
			CommResultsetObj.batchNo = itembatch;
			CommResultsetObj.expiryDateFormat = itemexp;
			CommResultsetObj.mrp = itemmrppack;
			CommResultsetObj.expDate = itemexpdate;
			CommResultsetObj.saleId = $("#saleId").val();

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/stock/getcurrstockbybatexpmrp.htm", CommResultsetObj, function(response) {
				console.log("response=" + response);
				var itemcurstkdets = JSON.parse(response);
				//alert("itemcurstkdets="+itemcurstkdets);
				if($.isEmptyObject(itemcurstkdets)){
					$("#currStkGraterModal").modal("show");
					$("#currstkofitm").text(0);
					$("#curstkcheck").val(1);
					}else{
						for ( var i = 0; i < itemcurstkdets.length; i++) {
							var itemcurstkdet = itemcurstkdets[i];
							//console.log("response=" + itemcurstkdet.calculateLooseQty);
							var calculateLooseQty = itemcurstkdet.calculateLooseQty;
							if (totlqty > calculateLooseQty) {
								$("#currStkGraterModal").modal("show");
								$("#currstkofitm").text(itemcurstkdet.stockQty);
								$("#curstkcheck").val(1);
							}
						}
					}
				
			});

			//	var item_rate_ls = $("#item_rate_ls_hid").val();

			var total = 0;
			var discamt = 0;
			var vatamt = 0;
			var taxamt = 0;
			var gstamt = 0;

			/*discamt = item_rate_ls * item_dis / 100;
			$("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4));
			var rate = item_rate_ls - discamt;
			$("#item_rate_ls").val(parseFloat(rate).toFixed(4));
			total = parseFloat(totlqty * rate).toFixed(4);*/
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
			
			var item_free = $("#item_free").val();
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
		}
	 }
}

