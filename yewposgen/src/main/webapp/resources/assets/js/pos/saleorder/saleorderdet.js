var charReg = /^\s*[a-zA-Z0-9\s]+\s*$/; // Restrict all special characters
var globalSaleHistDay = $("#saleHistoryDay").val();
var globalDayToPur = $("#dayToPurchase").val();

function getOrderByTypeAjax(order_type,distId,manfId)
{
	$('#pleasewaitModal').modal('show');
	var CommonRelsetmapperObj = {};
	//CommonRelsetmapperObj.invoiceNo = invno;
	CommonRelsetmapperObj.poGenType =order_type;
	CommonRelsetmapperObj.distributorId =distId;
	CommonRelsetmapperObj.manuId =manfId;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purorder/getpurorderbytype.htm", CommonRelsetmapperObj, function(response) {
	console.log("res=" + response);
	$('#pleasewaitModal').modal('hide');
	var purdetails = JSON.parse(response);
	if (purdetails==null) {
		$("#purordernotfounddiv").removeClass("hide");
		//$("#purordrbytypetable").addClass("hide");
		//$("#purordrbytypediv").addClass("hide");
		$("#purOrdrByTypeModal_okbtn").addClass('hide');
		$("#alertmessagecont").text("No Item Found");

	} else {
		$("#alertmessagecont").text("");
		$("#purordernotfounddiv").addClass("hide");
		$("#purordrbytypetable").removeClass("hide");
		$("#purordrbytypediv").removeClass("hide");
		$("#purOrdrByTypeModal_okbtn").removeClass('hide');
		$(':checkbox[name=selectAll]').prop("checked", false);
		for ( var i = 0; i < purdetails.length; i++) {
			var purdetail = purdetails[i];

			var starttrline = "<tr id='" + purdetail.itemId + "_orderTypeRow' style='cursor: pointer;'>";
			var chkbox = "<td><input id='" + purdetail.itemId + "_modretcheck' name='orderItemChk' class='chkboxcheked' type='checkbox' onchange='javascript:calQtyDayWise(" + purdetail.itemId + ");' value='" + JSON.stringify(purdetail) + "' ></td>";
			var itmname = "<td>" + purdetail.itemName + "</td>";
			var packQty = "<td>" + purdetail.packQty + "</td>";
			var recQty = "<td>" + purdetail.recPackQty + "</td>";
			var orderQty = "<td id='" + purdetail.itemId + "_orderQty'>" + purdetail.orderPackQty + "</td>";
			var mrp = "<td>" + parseFloat(purdetail.mrp).toFixed(2) + "</td>";//
			var rate = "<td>" + parseFloat(purdetail.rate).toFixed(2) + "</td>";
			var taxper = "<td>" + parseFloat(purdetail.taxPercentage).toFixed(2) + "</td>";
			var discper = "<td>" + parseFloat(purdetail.discPer).toFixed(2) + "</td>";
			var salehistday = "<td><input type='text' size='8' style='line-height: 14px;' value='"+globalSaleHistDay+"' onchange='javascript:calQtyDayWiseAjax("+purdetail.itemId+")' id='salehistday_" + purdetail.itemId + "'/></td>";
			var daytopur = "<td><input type='text' size='8' style='line-height: 14px;' value='"+globalDayToPur+"' onchange='javascript:calQtyDayWiseAjax("+purdetail.itemId+")' id='daytopur_" + purdetail.itemId + "'/></td>";
			var calculatedQty = "<td id='" + purdetail.itemId + "_calQtyDayWise'></td>"
			var setCalQty = "<td><button class='btn btn-theme btn-sm' id='setCalQtybtn_" + purdetail.itemId + "' onclick='javascript:setCalQty(" + purdetail.itemId + ");'>Set</button></td>";
			var showpurhist = "<td><button class='btn btn-theme btn-sm' id='purhistbtn_" + purdetail.itemId + "' onclick='javascript:showPurHist(" + purdetail.itemId + ");'>Pur Hist.</button></td>";
			var endtrline = "</tr>";
			createdrowline = starttrline + chkbox + itmname + packQty + recQty + orderQty + mrp + rate + taxper + discper + salehistday + daytopur + calculatedQty + setCalQty + showpurhist + endtrline;
			$("#purordrbytypetbody").append(createdrowline);
		}
	}

	});
}

function calQtyDayWise(itemid)
{
	if ($("#" + itemid + "_modretcheck").is(":checked"))
	{
		calQtyDayWiseAjax(itemid);
	}
	else
	{}
}

function setCalQty(itemid)
{
	$("#" + itemid + "_orderQty").text($("#" + itemid + "_calQtyDayWise").text());
}

function calQtyDayWiseAjax(itemid)
{
	var salehistday = $("#salehistday_" + itemid).val();
	var daytopur = $("#daytopur_" + itemid).val();

	var CommonRelsetmapperObj = {};
	var purordrqtydetails = "";
	//CommonRelsetmapperObj.invoiceNo = invno;
	CommonRelsetmapperObj.itemId =itemid;
	CommonRelsetmapperObj.lastSaleDays =salehistday;
	CommonRelsetmapperObj.comingPurchaseDays =daytopur;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purorder/getcalpurordrqty.htm", CommonRelsetmapperObj, function(response) {
	console.log("res=" + response);
	$('#pleasewaitModal').modal('hide');
	purordrqty = $.parseJSON(response).needRoundedPackQty;
	$("#" + itemid+"_calQtyDayWise").text(purordrqty)
	});
}

function showPurHist(itemid)
{
	$("#purHistDetailsDiv").removeClass("hide");
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/purinv/getpurhistoryofitem/" + itemid + "/0.htm", function(resp) {
		var itemPurHisDet = jQuery.parseJSON(resp);
		$("#purHistDetailsDiv").removeClass("hide");
		$("#purHistDetailsTbody").html("");
		$("#itemnameofpurHistDetails").text("");
		for ( var i = 0; i < itemPurHisDet.length; i++) {
			var itemPurHis = itemPurHisDet[i];
			$("#itemnameofpurHistDetails").text(itemPurHis.itemName);
			var starttrline = "<tr id=" + itemPurHis.invNo + " >";
			var invno = "<td>" + itemPurHis.invNo + "</td>";
			var invdate = "<td>" + moment(itemPurHis.invDate).format('YYYY-MM-DD') + "</td>";
			var vendorName = "<td>" +itemPurHis.distributorName + "</td>";
			var batchno = "<td>" +itemPurHis.batchNo + "</td>";
			var expdtfrmt = "<td>" +itemPurHis.expiryDateFormat + "</td>";
			var purqty = "<td>" +itemPurHis.packQty + "</td>";
			var freeqty = "<td>" +itemPurHis.freeQty + "</td>";
			var uom = "<td>" +itemPurHis.packUnitName + "</td>";
			var mrp = "<td>" +parseFloat(itemPurHis.mrp).toFixed(2) + "</td>";
			var rate = "<td>" +parseFloat(itemPurHis.rate).toFixed(2) + "</td>";
			var discper = "<td>" +parseFloat(itemPurHis.discPer).toFixed(2) + "</td>";
			var amt = "<td>" +parseFloat(itemPurHis.amount).toFixed(2) + "</td>";
			var endtrline = "</tr>";
			createdrowline = starttrline + invno + invdate + vendorName + batchno + expdtfrmt + purqty + freeqty + uom + mrp + rate + discper + amt + endtrline;
			$("#purHistDetailsTbody").append(createdrowline);
		}
	}, null);
}

function closePurHisDetForOrdrType(){
	$("#purHistDetailsDiv").addClass("hide");
}

function getAutoOrder()
{
	$("#purordrbytypetbody").html("");
	closePurHisDetForOrdrType();
	var order_type = $("#ordertype").val();
	var distId = $("#orderdistributor").val();
	var manfId = $("#manufacturerId").val();
	getOrderByTypeAjax(order_type,distId,manfId);
}

function getOrderByType()
{
	$("#alertmessagecont").text("");
	$("#orderdistributor").val(0);
	$("#itemManufacturer").val("")
	$("#manufacturerId").val(0);
	$("#purHistDetailsDiv").addClass("hide");
	$(':checkbox[name=selectAll]').prop("checked", false);
	var order_type = $("#ordertype").val();
	var saleretvendorid = $("#seldistributor").val().split("_")[0];
	if(order_type != 0)
	{
		//$("#slctVendorManfDiv").find('input:text').val('');
		//$("#slctVendorManfDiv").find('input:hidden').val('');
		$("#purordrbytypetbody").html("");
		if(order_type=="AUTO")
		{

			$("#orderdistributor").val($("#seldistributor").val());
			$("#orderdistributor").attr("disabled",true);
			$("#slctVendorManfDiv").removeClass("hide");
			//$("#purordrbytypediv").addClass("hide");
			//$("#footerDiv").addClass("hide");
		}
		else
		{
				getOrderByTypeAjax(order_type,saleretvendorid,0);
				$("#slctVendorManfDiv").addClass("hide");
				$("#purordrbytypediv").removeClass("hide");
				$("#footerDiv").removeClass("hide");
			}
			$("#purOrdrByTypeModal").modal("show");

	}
	else{}
}

function selctAllChkBox()
{
	if ($('input[name=selectAll]').is(":checked"))
	{
		$(':checkbox[name=orderItemChk]').prop("checked", true);
		$("#purordrbytypetable tbody tr").each(function(i,v){
			var rowId = $(this).attr("id");
			var itemId = rowId.split("_")[0];
			calQtyDayWiseAjax(itemId);
		});
	}
	else
	{
		$(':checkbox[name=orderItemChk]').prop("checked", false);
	}
}

function getmodcheckedpoitemlist() {
	var len=$('.chkboxcheked:checked').length;
	$('#purordrbytypetable > tbody > tr').each(function() {
		var rowId = this.id;
		var itemid = rowId.split("_")[0];
		console.log("len="+len);
		if(len==0){
			$("#alertmessagecont").text("Please check at least one item.");
		}else{
			$("#alertmessagecont").text("");
			if ($("#" + itemid + "_modretcheck").is(":checked")) {
				$("#alertmessagecont").text("");
				var orderdetail = $("#" + itemid + "_modretcheck").val();
				var itemdetail = JSON.parse(orderdetail);
				insertModDatatoPOrderTable(itemdetail);
			}
		}
	});

	$("#ordertype").val(0);

	if(len!=0){
		$('#purOrdrByTypeModal').modal('hide');
	}
	calculateGrandTotal();
	calculateTotalMRP();
	calculateTotalTax();
	calculateTotalDisc();
	calculateNetTotal();
}

function insertModDatatoPOrderTable(itemdetail) {
	//$("#purrettabitemdetails").text("");
	var saleretvendorid = $("#seldistributor").val().split("_")[0];
	var ltadj = 0.0;
/*	if (saleretvendorid == 0 || saleretvendorid == itemdetail.distributorId || itemdetail.distributorId==0) {*/
	if (saleretvendorid >0 || itemdetail.distributorId>0) {
		console.log("same vendor");
		var uniquechk = 0;
		//var newunikey = itemdetail.itemId + itemdetail.purchaseInvNo;
		var newunikey = "tblrow_"+itemdetail.itemId;
		console.log("newunikey=" + newunikey);
		$('#peitem tbody tr').each(function() {
			var itemid = this.id;
			var preunikey = itemid;
			console.log("preunikey=" + preunikey);
			if (newunikey == preunikey) {
				uniquechk = 1;
			}
		});
		console.log("uniquechk=" + uniquechk);
		if (uniquechk == 1) {
			$("#itemExistsModal").modal("show");
		} else {
			$("#vendorchnge1").val($("#seldistributor").val());
			var orderPQty = 0;
			var itemDiscPer = 0.0;
			var rate = 0.0;
			var sale_rate = 0.0;
			if($("#ordertype").val()=="AUTO")
			{
				orderPQty = $("#"+itemdetail.itemId+"_orderQty").text();
			}
			else if($("#ordertype").val()=="SALE")
			{
				if($("#"+itemdetail.itemId+"_orderQty").text()==0)
				{
					orderPQty = $("#orderQtyFrmSale").val();
				}
				else
				{
					orderPQty = $("#"+itemdetail.itemId+"_orderQty").text();
				}
			}
			if(itemdetail.discPer==0 || itemdetail.discPer==0.0)
			{
				itemDiscPer = $("#distdiscprcnt").val();
			}
			else
			{
				itemDiscPer = itemdetail.discPer;
			}
			var discval = orderPQty * itemdetail.rate * itemDiscPer / 100;
			if(itemdetail.taxId==0 || orderPQty==0)
			{
				fillItemDetailsDiv(itemdetail);
				$("#pqty").val(orderPQty);
				$("#lqty").val(itemdetail.conversion * orderPQty);
				$("#grp").val(itemdetail.groupName);
				$("#grpid").val(itemdetail.groupId);
				$("#sch").val(itemdetail.scheduleName);
				$("#schid").val(itemdetail.scheduleId);
				$("#total").val(parseFloat(orderPQty * itemdetail.rate).toFixed(2));
				$("#mfg").val(itemdetail.manufacturerName);
				$("#mfgid").val(itemdetail.manufacturerId);
				var tax = Number(Number(Number(itemdetail.rate) * Number(orderPQty)) - Number(discval)) * Number(itemdetail.taxPercentage) / 100;
				$("#tax").val(tax);
				$("#id").val(itemdetail.purchaseOrderId);
				$("#dprcnt").val(itemDiscPer);
				$("#item_taxTypeId").val(itemdetail.taxTypeId);
				$("#editNewItemBtn").removeClass("hide");
				$("#addNewItemBtn").addClass("hide");
				$("#editItemLabel").removeClass("hide");
				$("#newItemLabel").addClass("hide");
			}
			else
			{
				if(itemdetail.saleRate!=0 || itemdetail.saleRate!="")
				{
					sale_rate = itemdetail.saleRate;
				}
				else
				{
					/*if($("#isExclusive").val()==0)
					{
						sale_rate = itemdetail.mrp;
					}
					else
					{
						var mop = (Number(itemdetail.mrp)*100)/(100+Number(itemdetail.taxPercentage));
						sale_rate = parseFloat(mop).toFixed(2);
					}*/

					/* start*/

						/*if (isWholesale==1) // whole sale
		            {
		            	var mop = (itemdetail.mrp*100)/(100+Number(itemdetail.taxPercentage));
						var ptr = mop*(1-(Number($("#retailerProfitPrcnt").val())/100));
						sale_rate=parseFloat(ptr).toFixed(2);

		            }
		            else{// for pharmachy

						var mop = (itemdetail.mrp*100)/(100+Number(itemdetail.taxPercentage));


						if (parseFloat(itemdetail.taxTypeId)==2) {
							sale_rate=parseFloat(mop).toFixed(2);

						}else {
						 sale_rate=parseFloat(itemdetail.mrp).toFixed(2);
						}
		            }*/
					
					if (isWholesale==0) // for  Retail
		            {
						var mop = (itemdetail.mrp*100)/(100+Number(itemdetail.taxPercentage));


						if (parseFloat(itemdetail.taxTypeId)==2) {
							sale_rate=parseFloat(mop).toFixed(2);

						}else {
						 sale_rate=parseFloat(itemdetail.mrp).toFixed(2);
						}

		            }
		            else
		            {// for wholesale												
						var mop = (itemdetail.mrp*100)/(100+Number(itemdetail.taxPercentage));
						var ptr = mop*(1-(Number($("#retailerProfitPrcnt").val())/100));
						sale_rate=parseFloat(ptr).toFixed(2);
		            }
            /* end */
				}

				if(itemdetail.rate!=0 || itemdetail.rate!="")
				{
					rate = itemdetail.rate;
				}
				else
				{
					/*if($("#isExclusive").val()==0)
					{
						rate = itemdetail.rate;
					}
					else
					{
						var mop = (Number(itemdetail.mrp)*100)/(100+Number(itemdetail.taxPercentage));
						var ptr = mop*(1-(Number($("#retailerProfitPrcnt").val())/100));
						rate = parseFloat(ptr).toFixed(2);
					}*/
					/* start*/

						if (isWholesale==0) // for Retail
		            {
							var mop = (itemdetail.mrp*100)/(100+Number(itemdetail.taxPercentage));
						    var ptr = mop*(1-(Number($("#retailerProfitPrcnt").val())/100));
						    rate=ptr;
		            }
		            else{// for whole sale											    
					    var mop = (itemdetail.mrp*100)/(100+Number(itemdetail.taxPercentage));
						var ptr = mop*(1-(Number($("#retailerProfitPrcnt").val())/100));
						var pts = ptr*(1-(Number($("#wholesalerProfitPrcnt").val())/100));
						rate=parseFloat(pts).toFixed(2);
		            }
            /* end */
				}

				var rowCount = $('#peitem >tbody >tr').length;
				$("#itemcount").text(rowCount + 1);

				var createdrowline="";
				var starttrline = "<tr id=tblrow_" + itemdetail.itemId + " style='cursor: pointer;' onclick='javascript:itemDetailView("+itemdetail.itemId+")'>";
				var itmname = "<td id='tbl_item_name'>" + itemdetail.itemName + "</td>";
				var batch = "<td id='tbl_batch_no'>" + itemdetail.batchNo + "</td>";
				var exp = "<td id='tbl_exp'>" + itemdetail.expiryDateFormat + "</td>";
				var packQty = "<td id='tbl_pqty'>" + orderPQty + "</td>";
				var looseQty = "<td class='hide' id='tbl_lqty'>" + (itemdetail.conversion * orderPQty) + "</td>";
				var conv = "<td id='tbl_ratio'>" + itemdetail.conversion + "</td>";
				var mrp = "<td id='tbl_mrp'>" + parseFloat(itemdetail.mrp).toFixed(2) + "</td>";
				var tabrate = "<td id='tbl_rate' >" + parseFloat(rate).toFixed(2) + "</td>";
				var salerate = "<td id='tbl_saleRate' class='hide' >" + parseFloat(sale_rate).toFixed(2) + "</td>";
				var tax = Number(Number(Number(rate) * Number(orderPQty)) - Number(discval)) * Number(itemdetail.taxPercentage) / 100;
				var taxamt = "<td id='tbl_tax'>" + parseFloat(tax).toFixed(2) + "</td>";
				var taxTypeId = "<td id='tbl_taxTypeID' class='hide'>" + itemdetail.taxTypeId + "</td>";
				var discamt = "<td id='tbl_disc'>" + parseFloat(discval).toFixed(2) + "</td>";
				var amnt = '<td id="tbl_amnt" class="numeric">' + parseFloat(orderPQty * rate).toFixed(2) + '</td>';
				var totmrp = "<td id='tbl_totamnt'>" + parseFloat(itemdetail.mrp * orderPQty).toFixed(2) + "</td>";
				var tempTaxamout=((Number(orderPQty * rate)-Number(discval))*itemdetail.taxPercentage)/100.0;
				var net_amount = (Number(orderPQty * rate) + Number(tempTaxamout)) - Number(discval);
				var netamt = '<td id="tbl_netamt" class="numeric">' + parseFloat(net_amount).toFixed(2) + '</td>';
				var rowdelete = '<td><button class="btn btn-theme04 btn-xs" onclick="javascript:showPurItemDelModal('+itemdetail.itemId+');"><i class="fa fa-trash-o "></i></button></td>';
				var markup = "<td id='tbl_ma' class='hide'>0.00</td>";
				var grpid = "<td id='tbl_grp' class='hide'>" + itemdetail.groupId + "</td>";
				var schid = "<td id='tbl_sch' class='hide'>" + itemdetail.scheduleId + "</td>";
				var mfgid = "<td id='tbl_mfg' class='hide'>" + itemdetail.manufacturerId + "</td>";
				var taxPer = "<td id='tbl_taxprcnt' class='hide'>" + itemdetail.taxPercentage + "</td>";
				var taxid = '<td id="tbl_taxid" class="hide">' + itemdetail.taxId + '</td>';
				var taxmode = '<td id="tbl_taxmode" class="hide">' + itemdetail.taxMode + '</td>';
				var isgrptax = '<td id="tbl_isgrptax" class="hide">' + itemdetail.isGroupTax + '</td>';
				var discper = "<td id='tbl_dprcnt' class='hide'>" + parseFloat(itemDiscPer).toFixed(4) + "</td>";
				var grpname = '<td id="tbl_grpname" class="hide">' + itemdetail.groupName + '</td>';
				var schdlename = '<td id="tbl_schname" class="hide">' + itemdetail.scheduleName + '</td>';
				var mfgname = '<td id="tbl_mfgname" class="hide">' + itemdetail.manufacturerName + '</td>';
				var punitid = "<td id='tbl_punitid' class='hide'>" + itemdetail.packUnitId + "</td>";
				var tblid = "<td id='tbl_id' class='hide'>"+itemdetail.purchaseOrderId+"</td>";
				var tblitemid = "<td id='tbl_itemid' class='hide'>" + itemdetail.itemId + "</td>";
				var sku = "<td id='tbl_sku' class='hide'>" + itemdetail.sku + "</td>";
				var hsn = "<td id='tbl_hsn' class='hide'></td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + itmname + batch + exp + packQty + looseQty + conv + mrp + tabrate + salerate + taxamt+taxTypeId + discamt + amnt + totmrp + netamt + rowdelete + markup + grpid + schid + mfgid + taxPer + taxid + taxmode + isgrptax + discper + tblid + tblitemid + grpname + schdlename + mfgname + punitid + sku + hsn + endtrline;
				$("#polisttbody").append(createdrowline);
				$("#dprcnt").val($("#distdiscprcnt").val());
			}

		}

	} else {
		$("#sameVendorModal").modal("show");
		return false;
	}

}


function openMailModal()
{
	var mailBody = getPurOrderText.mailRefer+"&nbsp;"+$("#salecustname").val()+",\n"+getPurOrderText.mailMsgSo+$("#invno").val();
	var mailSubjct = $("#store_name").val()+getPurOrderText.mailSubjct+$("#invno").val();
	/*$(".Editor-editor").html(mailBody);
	$("#mailMsgBody").Editor();*/
	$("#mailMsgBody").html(mailBody);
	$("#mailTo").val($("#cusEmail").val());
	$("#mailSub").val(mailSubjct);
	$("#mailPurchaseOrderModal").modal("show");
}

function sendMail()
{
	var emailBeanObj = {};
	emailBeanObj.toAddr = $("#mailTo").val();
	emailBeanObj.subject = $("#mailSub").val();
	emailBeanObj.messageBody = $("#mailMsgBody").val();
	emailBeanObj.transId = $("#orderno").val();
	emailBeanObj.transType = "SO";
	emailBeanObj.isAttachment = "Y";
	$('#pleasewaitModal').modal('show');
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/mail/sendmail.htm", emailBeanObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		console.log("send mail response:"+response);
		var status = JSON.parse(response);
		$("#confirmval").val($("#orderno").val());
		chngeResultStat(status);
	});
}

function itemDetailView(trId) {
	document.getElementById('alertMsg').innerHTML = "";
	$("#item_name").attr("readonly",true);
	$("#purbarcode").attr("readonly",true);
	$("#item_name").val($("#tblrow_" + trId).find('#tbl_item_name').text());
	$("#itmremarks").val($("#tblrow_" + trId).find('#tbl_itmremarks').text());// new added 7.5.2019
	$("#item_code").val($("#tblrow_" + trId).find('#tbl_saleitmcode').text());// new added 7.5.2019
	$("#batch_no").val($("#tblrow_" + trId).find('#tbl_batch_no').text());
	$("#exp").val($("#tblrow_" + trId).find('#tbl_exp').text());
	$("#pqty").val($("#tblrow_" + trId).find('#tbl_pqty').text());
	$("#lqty").val($("#tblrow_" + trId).find('#tbl_lqty').text());
	$("#ratio").val($("#tblrow_" + trId).find('#tbl_ratio').text());
	$("#mrp").val($("#tblrow_" + trId).find('#tbl_mrp').text());
	$("#rate").val($("#tblrow_" + trId).find('#tbl_rate').text());
	$("#sale_rate").val($("#tblrow_" + trId).find('#tbl_saleRate').text());
	$("#ma").val($("#tblrow_" + trId).find('#tbl_ma').text());
	$("#grp").val($("#tblrow_" + trId).find('#tbl_grp').text());
	$("#sch").val($("#tblrow_" + trId).find('#tbl_sch').text());
	$("#total").val($("#tblrow_" + trId).find('#tbl_amnt').text());
	$("#mfg").val($("#tblrow_" + trId).find('#tbl_mfg').text());
	$("#edpercnt").val($("#tblrow_" + trId).find('#tbl_edprcnt').text());
	$("#ed").val($("#tblrow_" + trId).find('#tbl_ed').text());
	$("#taxprcnt").val($("#tblrow_" + trId).find('#tbl_taxprcnt').text());
	$("#tax").val($("#tblrow_" + trId).find('#tbl_tax').text());
	$("#purTaxId").val($("#tblrow_" + trId).find('#tbl_taxid').text());
	$("#purtaxmode").val($("#tblrow_" + trId).find('#tbl_taxmode').text());
	$("#purisgrptax").val($("#tblrow_" + trId).find('#tbl_isgrptax').text());
	$("#vatprcnt").val($("#tblrow_" + trId).find('#tbl_vatprcnt').text());
	$("#vat").val($("#tblrow_" + trId).find('#tbl_vat').text());
	$("#dprcnt").val($("#tblrow_" + trId).find('#tbl_dprcnt').text());
	$("#disc").val($("#tblrow_" + trId).find('#tbl_disc').text());
	$("#id").val($("#tblrow_" + trId).find('#tbl_id').text());
	$("#itemid").val($("#tblrow_" + trId).find('#tbl_itemid').text());
	$("#punitid").val($("#tblrow_" + trId).find('#tbl_punitid').text());
	$("#tblrow_id").val(trId);
	$("#purbarcode").val($("#tblrow_" + trId).find('#tbl_sku').text());
	$("#purHsnCode").val($("#tblrow_" + trId).find('#tbl_hsn').text());
	$("#edit_btn").removeClass("hide");
	$("#add_btn").addClass("hide");
	$("#editNewItemBtn").removeClass("hide");
	$("#addNewItemBtn").addClass("hide");
	$("#editItemLabel").removeClass("hide");
	$("#newItemLabel").addClass("hide");

	$("#item_taxTypeId").val($("#tblrow_" + trId).find('#tbl_taxTypeID').text());
	$("#itemdualstock").val($("#tblrow_" + trId).find('#tbl_itemdualstock').text());
	$("#expiryDateFormat").val($("#tblrow_" + trId).find('#tbl_expiryDateFormat').text());
	$("#expDate").val($("#tblrow_" + trId).find('#tbl_expDate').text());
	$("#batchNo").val($("#tblrow_" + trId).find('#tbl_batchNo').text());

	if($("#tblrow_" + trId).find('#tbl_isfreeiai').text() == '1') {
		$( "#isfreeiai" ).prop( "checked", true );
	} else {
		$( "#isfreeiai" ).prop( "checked", false );
	}
}

function getvendordisval() {
	var selvendor = $("#seldistributor").val();
	var disperc = selvendor.split("_")[1];
	$("#distdiscprcnt").val(parseFloat(disperc).toFixed(2));
	$("#dprcnt").val(parseFloat(disperc).toFixed(2));
	var qty = $("#pqty").val();
	var free = 0;
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
		var taxval = ((Number(rate) * (Number(qty)+Number(free))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
		$("#tax").val(parseFloat(taxval).toFixed(4));
	}
}

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
	    };

    	showConfirmModal();
		document.getElementById('alertMsg').innerHTML = "";
}

function getpurhistoryofitem(itemId)
{
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/purinv/getpurhistoryofitem/" + itemId + "/0.htm", function(resp) {
		var obj = jQuery.parseJSON(resp);
		createItemPurHistoryDetails(obj);
	}, null);
}

function Validation()
{
	var free=0;
	if ($('#isfreeiai').is(":checked")) {
		free = 1;
	} else {
		free = 0;
	}

	var counter = 0;

	var duedate_field = $("#duedate_label").text();

	var vendor_field = $("#vendor_label").text();

	var batch_field = $("#batch_label").text();

	var exp_field = $("#exp_label").text();

	var mrp_field = $("#mrp_label").text();

	var rate_field = $("#rate_label").text();

	var pqty_field = $("#pqty_label").text();

	var ratio_field = $("#ratio_label").text();

	var field_names = [["duedt",duedate_field],["pqty",pqty_field],["ratio",ratio_field],["mrp",mrp_field],["rate",rate_field]];


	if(free==1) {
		field_names = [["duedt",duedate_field],["seldistributor",vendor_field],["pqty",pqty_field],["ratio",ratio_field]];
	}

	//,["seldistributor",vendor_field]
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
		/*}*/
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

	if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#rate").val()))
	{
		document.getElementById('alertMsg').innerHTML = getPurOrderText.mrpLessRateErr;
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

	if($("#isExclusive").val()==0)
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
		/*if (isWholesale==1) {
					var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
				      if(Number(parseFloat(mrpWithoutTax).toFixed(2)) < Number($("#sale_rate").val()))
							{
								document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
								$(this).focus();
								return false;
							}
							else
							{
								document.getElementById('alertMsg').innerHTML = "";
							}
                 }else {
                 	var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
					if (parseFloat($("#item_taxTypeId").val())==2) {
						//$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));

 						 if(Number(parseFloat(mrpWithoutTax).toFixed(2)) < Number($("#sale_rate").val()))
							{
								document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
								$(this).focus();
								return false;
							}
							else
							{
								document.getElementById('alertMsg').innerHTML = "";
							}

					}else {
					// $("#sale_rate").val(parseFloat(mrp).toFixed(2));


				     if(Number(parseFloat($("#mrp").val()).toFixed(2)) < Number($("#sale_rate").val()))
							{
								document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
								$(this).focus();
								return false;
							}
							else
							{
								document.getElementById('alertMsg').innerHTML = "";
							}

					}
                 }*/
		
		
		if (isWholesale==0) { ////for retail
			var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
			if (parseFloat($("#item_taxTypeId").val())==2) {
				//$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));

					 if(Number(parseFloat(mrpWithoutTax).toFixed(2)) < Number($("#sale_rate").val()))
					{
						document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
						$(this).focus();
						return false;
					}
					else
					{
						document.getElementById('alertMsg').innerHTML = "";
					}

			}else {
			// $("#sale_rate").val(parseFloat(mrp).toFixed(2));


		     if(Number(parseFloat($("#mrp").val()).toFixed(2)) < Number($("#sale_rate").val()))
					{
						document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
						$(this).focus();
						return false;
					}
					else
					{
						document.getElementById('alertMsg').innerHTML = "";
					}

			}
         }else {       ////for wholesale  							
			var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
		      if(Number(parseFloat(mrpWithoutTax).toFixed(2)) < Number($("#sale_rate").val()))
					{
						document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
						$(this).focus();
						return false;
					}
					else
					{
						document.getElementById('alertMsg').innerHTML = "";
					}
         }
		/*var mop = (Number($("#mrp").val())*100)/(100+Number($("#taxprcnt").val()));
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
		}*/
	}


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
    	document.getElementById('alertMsg').innerHTML = getPurOrderText.noTaxAddErr;
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

	return counter;
}

function toPurchaseOrder()
{
	location.href = BASE_URL + '/saleorder/loadsaleorder.htm';
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

function DoConfirm() {
	var id = document.getElementById('confirmId').value;
	$("#item_name").attr("readonly",false);
	$("#purbarcode").attr("readonly",false);
	$("#add_btn").removeClass("hide");
	$("#edit_btn").addClass("hide");
	$("#header_div").find('input:text').val('');
	$("#header_div").find('input:hidden').val('');
	$("#tblrow_" + id).remove();
	$("#itemcount").text($("#itemcount").text() - 1);
	$("#prevItemCount").val(1);
	$("#itemid").val("");
	$("#item_name").focus();
	$("#addNewItemBtn").removeClass("hide");
	$("#editNewItemBtn").addClass("hide");
	$("#editItemLabel").addClass("hide");
	$("#newItemLabel").removeClass("hide");
	calculateGrandTotal();
	calculateTotalMRP();
	//calculateTotalED();
	//calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	calculateNetTotal();
	return false;
}

function DoConfirmPODel() {
	var poid = document.getElementById('confirmId').value;
	var confirmType = document.getElementById('confirmType').value;
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.purchaseOrderId = poid;
	if(confirmType=="delete")
	{
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/saleorder/deletesaleorder.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			/*if (response == '1') {
				$("#confirmval").val(1);
				document.getElementById('confirmmessagecont').innerHTML = getPurOrderText.dataSucDelete;
				showConfirmModal();
			} else {
				$("#confirmval").val(-1);
				document.getElementById('confirmmessagecont').innerHTML = getPurOrderText.dataNotDelete;
				showConfirmModal();
			}*/

			var status = JSON.parse(response);
			if(status.id==-1)
			{
				$("#confirmval").val("delSuccess");
			}
			else
			{
				$("#confirmval").val(status.id);
			}
			chngeResultStat(status);

		});
	}
	else if(confirmType=="close")
	{
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/saleorder/closesaleorder.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			var status = JSON.parse(response);
			if(status.id==-1)
			{
				$("#confirmval").val("closeSuccess");
			}
			else
			{
				$("#confirmval").val(status.id);
			}
			chngeResultStat(status);

		});
	}
}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function targetURL() {
	if($("#confirmval").val()==-1 || $("#confirmval").val()==0)
	{
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#dprcnt").val($("#distdiscprcnt").val());
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
	else if($("#confirmval").val()=="itemAddOk")
	{
		fillItemDtlsDivFrmModal(itemObj);

		var mrp = $("#mrp").val();
		var qty = $("#pqty").val();
		var ratio = $("#ratio").val();
		var lqty = $("#lqty").val();
		var taxprcnt = $("#taxprcnt").val();
		var dprcnt = $("#dprcnt").val();
		var free = 0;

		//rate calculation
		if (taxprcnt == ""|| mrp == "") {

		}
		else
		{
			var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
			var ptr = mrpWithoutTax*(1-(Number($("#retailerProfitPrcnt").val())/100));
			$("#rate").val(parseFloat(ptr).toFixed(4));
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

		//discount calculation
		if (dprcnt == ""|| qty == "") {

		} else {
			var disval = qty * rate * dprcnt / 100;
			$("#disc").val(parseFloat(disval).toFixed(4));
		}

		//amt calculation
		if (qty == "") {

		} else {
			var amt = qty * rate;
			$("#total").val(parseFloat(amt).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
			var taxval = ((Number(rate) * (Number(qty)+Number(free))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

		$('#itemmasterModal').modal('hide');
		location.href = "#";
	}
	else if($("#confirmval").val()=="itemAddErr")
	{
		$('#itemmasterModal').modal('hide');
		location.href = "#";
	}
	else if($("#confirmval").val()=="delSuccess")
	{
		location.href = BASE_URL + "/saleorder/loadsaleorder.htm";
	}
	else if($("#confirmval").val()=="closeSuccess")
	{
		location.href = BASE_URL + "/saleorder/loadsaleorder.htm";
	}
	else if($("#confirmval").val()=="post")
	{
		location.href = BASE_URL + "/saleorder/loadsaleorder.htm";
	}
	else
	{
		location.href = BASE_URL + '/saleorder/loadsaleorderdet/'+$("#confirmval").val()+'.htm';
	}
}

function fillItemDetailsDiv(itemdetval) {
	console.log(itemdetval.itemName);
	$("#rate").attr("readonly",false);
	$("#item_name").val(itemdetval.itemName);
	$("#batch_no").val(0);
	$("#exp").val();
	$("#punitid").val(itemdetval.packUnitId);
	$("#pqty").val(itemdetval.packQty);
	$("#lqty").val(itemdetval.looseQty);
	$("#ratio").val(itemdetval.conversion);
	$("#mrp").val(itemdetval.mrp);

	if(itemdetval.saleRate!=0 || itemdetval.saleRate!="")
	{
		$("#rate").val(itemdetval.saleRate);
	}
	/*else
	{
		if($("#isExclusive").val()==0)
		{
			$("#sale_rate").val(itemdetval.mrp);
		}
		else
		{
			var mop = (Number(itemdetval.mrp)*100)/(100+Number(itemdetval.taxPercentage));
			$("#sale_rate").val(parseFloat(mop).toFixed(2));
		}
	}

	if(itemdetval.rate!=0 || itemdetval.rate!="")
	{
		$("#rate").val(itemdetval.rate);
	}
	else
	{
		if($("#isExclusive").val()==0)
		{
			$("#rate").val(itemdetval.rate);
		}
		else
		{
			var mop = (Number(itemdetval.mrp)*100)/(100+Number(itemdetval.taxPercentage));
			var ptr = mop*(1-(Number($("#retailerProfitPrcnt").val())/100));
			$("#rate").val(parseFloat(ptr).toFixed(2));
		}
	}*/

	$("#grp").val(itemdetval.grpName);
	$("#grpid").val(itemdetval.grpId);
	$("#sch").val(itemdetval.schdName);
	$("#schid").val(itemdetval.schdId);
	$("#total").val(0);
	$("#mfg").val(itemdetval.manuName);
	$("#mfgid").val(itemdetval.manuId);
	/*$("#edpercnt").val(itemdetval.edPer);
	$("#ed").val(itemdetval.ed);*/
	var tax = Number(Number(Number(itemdetval.rate) * Number(itemdetval.packQty)) - Number(itemdetval.disc)) * Number(itemdetval.taxPercentage) / 100;
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
	$("#item_code").val(itemdetval.itemCode); // new added 7.5.2019

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

	var nettotal =( Number($("#totgrosamnt").val()) + Number($("#tottaxamnt").val())) - Number($("#totdiscamnt").val());
	var roundednetamnt=Math.round(Number(nettotal));
	console.log("roundednetamnt="+parseFloat(roundednetamnt).toFixed(2));
	$("#totnetamnt").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff=Number(roundednetamnt)-Number(nettotal);
	console.log("roundoff="+parseFloat(roundoff).toFixed(2));
	$("#roundoff").val(parseFloat(roundoff).toFixed(2));
}

function deletePurchaseOrder(){
	var poid=$("#orderno").val();
   document.getElementById('confirmId').value=poid;
   document.getElementById('confirmType').value="delete";
   $('#purDelConfirmModal').modal('show');
}
function postSaleOrder(){
	if($("#prevItemCount").val()==1)
	{
		$("#confirmval").val(0);
		document.getElementById('confirmmessagecont').innerHTML = getPurOrderText.postBfrUpdate;
		showConfirmModal();
		return false;
	}
	else
	{
		var poid=$("#orderno").val();
		var puritemlength = $('#peitem >tbody >tr').length;
		if(puritemlength>0)
		{
			var CommonRelsetmapperObj = {};
			CommonRelsetmapperObj.purchaseOrderId = poid;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/saleorder/postsaleorder.htm", CommonRelsetmapperObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				/*if (response == '1') {
					$("#confirmval").val("post");
					document.getElementById('confirmmessagecont').innerHTML = getPurOrderText.dataSucPost;
					showConfirmModal();
				} else {
					$("#confirmval").val(-1);
					document.getElementById('confirmmessagecont').innerHTML = getPurOrderText.dataNotPost;
					showConfirmModal();
				}*/

				var status = JSON.parse(response);
				if(status.id==-11)
				{
					$("#confirmval").val("post");
				}
				else
				{
					$("#confirmval").val(status.id);
				}
				chngeResultStat(status);

			});
		}
		else
		{
			$("#confirmval").val(2);
			document.getElementById('confirmmessagecont').innerHTML = getPurOrderText.noItemChckErr;
			showConfirmModal();
		}
	}
}
$(document).ready(function() {

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
	// pqty change calculation
	$("#pqty").keyup(function() {
		var qty = $(this).val();
		var edpercnt = $("#edpercnt").val();
		var dprcnt = $("#dprcnt").val();
		var taxprcnt = $("#taxprcnt").val();
		var vatprcnt = $("#vatprcnt").val();
		var rate = $("#rate").val();
		var mrp = $("#mrp").val();
		var ratio = $("#ratio").val();
		var free = 0;
		if (free == "") {
			free = 0;
		}
		if (mrp == "") {
			mrp = 0;
		}
		//set total
		//("#total").val((qty + free) * mrp);
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
			var disval = qty * rate * dprcnt / 100;
			$("#disc").val(parseFloat(disval).toFixed(4));
		}
		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
			var taxval = ((Number(rate) * (Number(qty)+Number(free))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}
		//amt calculation
		if (rate == "") {

		} else {
			var amt = qty * rate;
			$("#total").val(parseFloat(amt).toFixed(2));
		}
	});

	// mrp change calculation
	$("#mrp_notreq").keyup(function() {
		var mrp = $(this).val();
		var edpercnt = $("#edpercnt").val();
		var taxprcnt = $("#taxprcnt").val();
		var vatprcnt = $("#vatprcnt").val();
		var dprcnt = $("#dprcnt").val();
		var rate = $("#rate").val();
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
		//rate calculation
		if (!(taxprcnt == ""|| mrp == "")) {

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
			
			if (isWholesale==0) // for retail
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

		//amt calculation
		if (qty == "") {

		} else {
			var amt = qty * parseFloat($("#sale_rate").val());
			$("#total").val(parseFloat(amt).toFixed(4));
		}

		/*
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

		//discount calculation
		if (dprcnt == ""|| qty == "") {

		} else {
			var disval = qty * rate * dprcnt / 100;
			$("#disc").val(parseFloat(disval).toFixed(4));
		}

		//amt calculation
		if (qty == "") {

		} else {
			var amt = qty * rate;
			$("#total").val(parseFloat(amt).toFixed(4));
		}


		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
			var taxval = ((Number(rate) * (Number(qty)+Number(free))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

		//ma calculation
		if (mrp == ""|| rate == "" || rate == 0) {

		} else {
			var maprcnt = ((Number(mrp)-Number(rate)) / rate)*100;
			$("#ma").val(parseFloat(maprcnt).toFixed(4));
		}
		*/

	});

	// rate change calculation
	$("#rate").keyup(function() {
		var rate = $(this).val();
		var vatprcnt = $("#vatprcnt").val();
		var dprcnt = $("#dprcnt").val();
		var qty = $("#pqty").val();
		var free = 0;
		var taxprcnt = $("#taxprcnt").val();
		var mrp = $("#mrp").val();
		if (free == "") {
			free = 0;
		}

		//discount calculation
		if (dprcnt == "" || qty == "") {

		} else {
			var disval = qty * rate * dprcnt / 100;
			$("#disc").val(parseFloat(disval).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
			var taxval = ((Number(rate) * (Number(qty)+Number(free))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

		//amt calculation
		if (qty == "") {

		} else {
			var amt = qty * rate;
			$("#total").val(parseFloat(amt).toFixed(4));
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

	// taxpercent change calculation
	$("#taxprcnt").keyup(function() {
		var taxprcnt = $(this).val();
		var qty = $("#pqty").val();
		var rate = $("#rate").val();
		var mrp = $("#mrp").val();
		if (mrp == "") {
			mrp = 0;
		}
		var free = 0;
		if (free == "") {
			free = 0;
		}
		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
			var taxval = ((Number(rate) * (Number(qty)+Number(free))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
			$("#tax").val(parseFloat(taxval).toFixed(4));
		}

	});

	// discpercent change calculation
	$("#dprcnt").keyup(function() {
		var dprcnt = 0.0;
		var qty = $("#pqty").val();
		var rate = $("#rate").val();
		var free = 0;
		var taxprcnt = $("#taxprcnt").val();
		if (rate == "") {
			rate = 0;
		}

		if ($(this).val() == "") {
			dprcnt = 0;
		}
		else
		{
			dprcnt=$(this).val();
		}

		//discount calculation
		if (qty == "") {

		} else {
			var disval = qty * rate * dprcnt / 100;
			$("#disc").val(parseFloat(disval).toFixed(4));
		}

		//tax calculation
		if (taxprcnt == ""|| rate == "") {

		} else {
			//var taxval = (Number(qty) + Number(free)) * mrp * taxprcnt / 100;
			var taxval = ((Number(rate) * (Number(qty)+Number(free))) - Number($("#disc").val())) * Number(taxprcnt) / 100;
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
			$("#itemid").val(ui.item.itemCode);
			// call new  ajax for item details

			if(isOptical==0) {
				// call new  ajax for item details

				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjax(BASE_URL + "/purinv/getitemdetails/" + ui.item.itemCode + "/0.htm", function(resp) {
					//console.log(resp);
					var obj = jQuery.parseJSON(resp);
					//console.log(obj[0].packUnitId);
					fillItemDetailsDiv(obj[0]);
				}, null);
			} else {
				getItemDetails(ui.item.itemCode); //for batchwise stock checking
			}

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
	/* new added 7.5.2019*/
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
						response($.map(data, function(v) {
							return {
								label : v.itemCode,
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

			if(isOptical==0) {
				// call new  ajax for item details

				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjax(BASE_URL + "/purinv/getitemdetails/" + ui.item.itemCode + "/0.htm", function(resp) {
					//console.log(resp);
					var obj = jQuery.parseJSON(resp);
					//console.log(obj[0].packUnitId);
					fillItemDetailsDiv(obj[0]);
				}, null);
			} else {
				getItemDetails(ui.item.itemCode); //for batchwise stock checking
			}

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
/**
 * checking current stock by item id
 */
function getItemDetails(itemid) {
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/stock/getcurrstock/" + itemid + ".htm", function(resp) {
		/*console.log(resp);
		createItemStockDetails(resp, itemid);
		$("#stkdetModal").modal("show");*/
		var itemdetail = JSON.parse(resp);
	//	console.log("___current_stock___resp="+resp);
		if(itemdetail.length==1){// when no stock
			for ( var i = 0; i < itemdetail.length; i++) {
				var itmstkdet = itemdetail[i];
				//console.log(JSON.stringify(itmstkdet));
			//getClickeditmdet(itmstkdet);// for set the sale table value
			frmBtchfillItemDetails(itmstkdet);// for set the sale table value
			}
		}else{// when stock present
			createItemStockDetails(resp, itemid);// if batch present then it will open modal
			$("#stkdetModal").modal("show");
		}


	}, null);
}



//for batch details popup
function createItemStockDetails(itemstkdetails,
								itemid) {
	//	$("#snditmtopodiv").addClass("hide");
	$("#snditmpo").addClass("hide");
	//	console.log("itemstkdetails="+itemstkdetails);
	$("#itemdetails").text("");
	//$("#moditemname").text($("#item_name").val());
	var itemdetail = JSON.parse(itemstkdetails);
	//	alert(itemdetail.length);
	//console.log("itemdetail="+itemdetail);
	var totstkitm = 0;
	var totlooseitm = 0;
	var conv = 0;
	var totlooseqty = 0;
	var totloosereorderlevelqty = 0;
	$("#senditemtopoid").val(itemid);
	if (itemdetail.length == 0) {
		//		$("#snditmtopodiv").removeClass("hide");
		//		$("#snditmpo").addClass("hide");
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
				//				$("#snditmpo").addClass("hide");
				//				$("#snditmtopodiv").removeClass("hide");
				$("#itemnotfounddiv").removeClass("hide");
				$("#itemnotfoundname").text($("#item_name").val());
				$("#itemnotfoundid").val(itemid);
				$("#modtable").addClass("hide");
			} else {
				totstkitm = Number(totstkitm) + parseFloat(itmstkdet.stockQty);
				//			console.log("looseQty=" + parseFloat(itmstkdet.looseQty));
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
					starttrline = "<tr id=" + itmstkdet.itemUniqueKey + " style='cursor: pointer;' onclick='javascript:frmBtchfillItemDetails(" + JSON.stringify(itmstkdet) + ")'>";
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
				/*if(itmstkdet.priceListRequired==1){
					srate = "<td>" + itmstkdet.saleRate + "</td>";
				}else{
					srate = "<td>0</td>";
				}*/
				srate = "<td>" + itmstkdet.saleRate + "</td>";
				var endtrline = "</tr>";
//				createdrowline = starttrline + batchno + expiryDateFormat + stockQty + holdQty + mrppack + mrp + conversion + packing+purrate+srate + endtrline;
				createdrowline = starttrline + batchno + expiryDateFormat + stockQty + holdQty +  mrppack + purrate+srate + endtrline;
				$("#itemdetails").append(createdrowline);
			}
			//		console.log("totlooseqty="+totlooseqty);
			//		$("#totalcurrstkitm").text(totstkitm + "/" + totlooseitm + " [ " + (totstkitm * conv + totlooseitm) + " ]");
			$("#totalcurrstkitm").text(totstkitm + "/" + totlooseitm + " [ " + (totlooseqty) + " ]");

			$("#itemstockdetails>tbody>tr:first").addClass('rowActive');
		}
		/*if (totlooseqty <= totloosereorderlevelqty) {
			$("#snditmtopodiv").removeClass("hide");
			$("#itemnotfoundid").val(itemid);
		} else {
			$("#snditmtopodiv").addClass("hide");
			$("#snditmpo").addClass("hide");
		}*/
	}

}

	$("#cnfrm_cancel_btn").click(function() {
		$("#add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#itemid").val("");
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
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
			/*}*/
		}
	});

	$("#mrp").blur(function() {
		document.getElementById('alertMsg').innerHTML = "";
		if(isNaN($("#mrp").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in MRP";
			$(this).focus();
			return false;
		}
		else
		{
			if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#rate").val()))
			{
				document.getElementById('alertMsg').innerHTML = getPurOrderText.mrpLessRateErr;
				$(this).focus();
				return false;
			}
			else
			{
				document.getElementById('alertMsg').innerHTML = "";
			}

			if($("#isExclusive").val()==0)
			{
				if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
				{
					document.getElementById('alertMsg').innerHTML = getPurOrderText.mrpLessSRateErr;
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

				/*if (isWholesale==1) {
					var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
				      if(Number(parseFloat(mrpWithoutTax).toFixed(2)) < Number($("#sale_rate").val()))
							{
								document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
								$(this).focus();
								return false;
							}
							else
							{
								document.getElementById('alertMsg').innerHTML = "";
							}
                 }else {
                 	var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
					if (parseFloat($("#item_taxTypeId").val())==2) {
						//$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));

 						 if(Number(parseFloat(mrpWithoutTax).toFixed(2)) < Number($("#sale_rate").val()))
							{
								document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
								$(this).focus();
								return false;
							}
							else
							{
								document.getElementById('alertMsg').innerHTML = "";
							}

					}else {
					// $("#sale_rate").val(parseFloat(mrp).toFixed(2));


				     if(Number(parseFloat($("#mrp").val()).toFixed(2)) < Number($("#sale_rate").val()))
							{
								document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
								$(this).focus();
								return false;
							}
							else
							{
								document.getElementById('alertMsg').innerHTML = "";
							}

					}
                 }*/
				
				if (isWholesale==0) {/////for retail
					
					var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
					if (parseFloat($("#item_taxTypeId").val())==2) {
						//$("#sale_rate").val(parseFloat(mrpWithoutTax).toFixed(2));

 						 if(Number(parseFloat(mrpWithoutTax).toFixed(2)) < Number($("#sale_rate").val()))
							{
								document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
								$(this).focus();
								return false;
							}
							else
							{
								document.getElementById('alertMsg').innerHTML = "";
							}

					}else {
					// $("#sale_rate").val(parseFloat(mrp).toFixed(2));


				     if(Number(parseFloat($("#mrp").val()).toFixed(2)) < Number($("#sale_rate").val()))
							{
								document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
								$(this).focus();
								return false;
							}
							else
							{
								document.getElementById('alertMsg').innerHTML = "";
							}

					}
                 }else {/////for wholesale                 						
					var mrpWithoutTax = (mrp*100)/(100+Number(taxprcnt));
				      if(Number(parseFloat(mrpWithoutTax).toFixed(2)) < Number($("#sale_rate").val()))
							{
								document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
								$(this).focus();
								return false;
							}
							else
							{
								document.getElementById('alertMsg').innerHTML = "";
							}
                 }
				
				
				/*var mop = (Number($("#mrp").val())*100)/(100+Number($("#taxprcnt").val()));
				if(Number(parseFloat(mop).toFixed(2))<Number($("#sale_rate").val()))
				{
					document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}*/
			}
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
			if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#rate").val()))
			{
				document.getElementById('alertMsg').innerHTML = getPurOrderText.mrpLessRateErr;
				$(this).focus();
				return false;
			}
			else
			{
				document.getElementById('alertMsg').innerHTML = "";
			}
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
			if($("#isExclusive").val()==0)
			{
				if(Number(parseFloat($("#mrp").val()).toFixed(2))<Number($("#sale_rate").val()))
				{
					document.getElementById('alertMsg').innerHTML = getPurOrderText.mrpLessSRateErr;
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
					document.getElementById('alertMsg').innerHTML = getPurOrderText.sRateGrtrMopErr;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
			}

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

		var discPercnt = 0;
		if($("#dprcnt").val()=="")
		{
			discPercnt = 0;
		}
		else
		{
			discPercnt = $("#dprcnt").val();
		}
		var itempresent = 0;
		var trId = $("#tblrow_id").val();
		$('#peitem > tbody  > tr').each(function() {
			//alert("tbl_itemid=" + $(this).find('#tbl_itemid').text());
			//alert("itemid=" + $("#itemid").val());
			//alert("tbl_batch_no=" + $(this).find('#tbl_batch_no').text());
			if (Number($(this).find('#tbl_itemid').text()) == Number($("#itemid").val())) {
				var rowId=(Number($(this).find('#tbl_itemid').text()));
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
				var newtblrowId="tblrow_"+$("#itemid").val();
				var onclickFunction="itemDetailView("+$("#itemid").val()+");";
				$("#tblrow_" + trId).removeAttr('onclick');
				$("#tblrow_" + trId).attr('onclick',onclickFunction);
				$("#tblrow_" + trId).attr("id", newtblrowId);
				trId=$("#itemid").val();
			}
		});
		if(itempresent>0)
		{
			$('#itemExistsModal').modal('show');
		}
		else
		{
			//var amnt = $("#pqty").val() * $("#rate").val();
			var totmrp = Number($("#pqty").val()) * Number($("#mrp").val());
			$("#tblrow_" + trId).find('#tbl_item_name').text($("#item_name").val());
			$("#tblrow_" + trId).find('#tbl_batch_no').text($("#batch_no").val());
			$("#tblrow_" + trId).find('#tbl_exp').text($("#exp").val());
			$("#tblrow_" + trId).find('#tbl_pqty').text($("#pqty").val());
			$("#tblrow_" + trId).find('#tbl_lqty').text($("#lqty").val());
			$("#tblrow_" + trId).find('#tbl_ratio').text($("#ratio").val());
			$("#tblrow_" + trId).find('#tbl_mrp').text($("#mrp").val());
			$("#tblrow_" + trId).find('#tbl_rate').text(rate);
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
			$("#tblrow_" + trId).find('#tbl_dprcnt').text(discPercnt);
			$("#tblrow_" + trId).find('#tbl_disc').text($("#disc").val());
			$("#tblrow_" + trId).find('#tbl_id').text($("#id").val());
			$("#tblrow_" + trId).find('#tbl_itemid').text($("#itemid").val());
			$("#tblrow_" + trId).find('#tbl_punitid').text($("#punitid").val());
			$("#tblrow_" + trId).find('#tbl_isFree').text(0);
			$("#tblrow_" + trId).find('#tbl_ltAdj').text(0);
			$("#tblrow_" + trId).find('#tbl_saleRate').text($("#sale_rate").val());
			$("#tblrow_" + trId).find('#tbl_sku').text($("#purbarcode").val());
			$("#tblrow_" + trId).find('#tbl_hsn').text($("#purHsnCode").val());
			var net_amount = (Number($("#total").val()) + Number($("#tax").val())) - Number($("#disc").val());
			$("#tblrow_" + trId).find('#tbl_netamt').text(parseFloat(net_amount).toFixed(2));
			$("#tblrow_" + trId).find('#tbl_taxTypeID').text($("#item_taxTypeId").val());
			if($("#expiryDateFormat").val()!='' || $("#expiryDateFormat").val()!=undefined) {
				expirydateformat = $("#expiryDateFormat").val();
			}
			$("#tblrow_" + trId).find('#tbl_expiryDateFormat').text(expirydateformat);
			var isfreeiai = $('#isfreeiai').is(":checked") ? 1 : 0;
			$("#tblrow_" + trId).find('#tbl_isfreeiai').text(isfreeiai);
			if(isfreeiai==1) {
				$("#tblrow_" + trId).addClass("freeiai");
			} else {
				$("#tblrow_" + trId).removeClass("freeiai");
			}
		}
		$("#tblrow_" + trId).find('#tbl_itmremarks').text($("#itmremarks").val()); // new added 7.5.2019
		$("#tblrow_" + trId).find('#tbl_saleitmcode').text($("#item_code").val()); // new added 7.5.2019
		
		
		$("#prevItemCount").val(1);
		$("#header_div").find('input:text').val('');
		$("#header_div").find('input:hidden').val('');
		$("#dprcnt").val($("#distdiscprcnt").val());
		$("#add_btn").removeClass("hide");
		$("#edit_btn").addClass("hide");
		$("#item_name").attr("readonly",false);
		$("#item_name").focus();
		$("#itemid").val("");
		$("#addNewItemBtn").removeClass("hide");
		$("#editNewItemBtn").addClass("hide");
		$("#editItemLabel").addClass("hide");
		$("#newItemLabel").removeClass("hide");
		$( "#isfreeiai" ).prop( "checked", false );
		calculateGrandTotal();
		calculateTotalMRP();
		//calculateTotalED();
		//calculateTotalVat();
		calculateTotalTax();
		calculateTotalDisc();
		//calculateTotalLtAdj();
		calculateNetTotal();
	});

	$("#add_btn").click(function() {

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

		var ma = 0;
		if($("#ma").val()=="")
		{
			ma = 0;
		}
		else
		{
			ma = $("#ma").val();
		}

		var discPercnt = 0;
		if($("#dprcnt").val()=="")
		{
			discPercnt = 0;
		}
		else
		{
			discPercnt = $("#dprcnt").val();
		}
		var free=0;
		if ($('#isfreeiai').is(":checked")) {
			free = 1;
		} else {
			free = 0;
		}

		var rowCount = $('#peitem >tbody >tr').length;
		$("#itemcount").text(rowCount + 1);
		$("#prevItemCount").val(1);
		var itempresent = 0;
		$('#peitem > tbody  > tr').each(function() {
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
			$("#purHistoryDiv").addClass("hide");//open purchase history details popup
			var freestyle = "";
			if($("#isfreeiai").is(":checked")) {
				freestyle = ' class="freeiai" ';
			}
			var strng_tbrow_id = "'"+$("#itemid").val()+"'";
			var tr = '<tr id="tblrow_' + $("#itemid").val() + '" '+freestyle+' style="cursor: pointer;" onclick="javascript:itemDetailView(' + $("#itemid").val() +');">';
			var totmrp = Number($("#pqty").val()) * Number($("#mrp").val());
			var row1 = '<td id="tbl_item_name">' + $("#item_name").val() + '</td>';
			var row2 = '<td id="tbl_batch_no">' + $("#batch_no").val() + '</td>';
			var row3 = '<td id="tbl_exp">' + $("#exp").val() + '</td>';
			var row4 = '<td id="tbl_pqty" class="numeric">' + $("#pqty").val() + '</td>';
			var row5 = '<td id="tbl_lqty" class="numeric hide">' + $("#lqty").val() + '</td>';
			var row6 = '<td id="tbl_ratio" class="numeric">' + $("#ratio").val() + '</td>';
			var row8 = '<td id="tbl_mrp" class="numeric hide">' + (free==1 ? 0 : $("#mrp").val()) + '</td>';
			var row9 = '<td id="tbl_rate" class="numeric">' + (free==1 ? 0 : rate) + '</td>';
			var row10 = '<td id="tbl_ed" class="numeric">' + $("#ed").val() + '</td>';
			var row11 = '<td id="tbl_tax" class="numeric">' + (free==1 ? 0 : $("#tax").val()) + '</td>';
			var taxTypeId = "<td id='tbl_taxTypeID' class='hide'>" +  $("#item_taxTypeId").val()+ "</td>";
			var row12 = '<td id="tbl_vat" class="numeric">' + $("#vat").val() + '</td>';
			var row13 = '<td id="tbl_disc" class="numeric">' + (free==1 ? 0 : $("#disc").val()) + '</td>';
			var row14 = '<td id="tbl_amnt" class="numeric">' + (free==1 ? 0 : $("#total").val()) + '</td>';
			var row15 = '<td id="tbl_totamnt" class="numeric hide">' + (free==1 ? 0 : parseFloat(totmrp).toFixed(2)) + '</td>';
			var net_amount = (Number($("#total").val()) + Number($("#tax").val())) - Number($("#disc").val());
			var netamt = '<td id="tbl_netamt" class="numeric">' + (free==1 ? 0 : parseFloat(net_amount).toFixed(2)) + '</td>';
			var row16 = '<td><button class="btn btn-theme04 btn-xs" onclick="javascript:showPurItemDelModal(' + strng_tbrow_id + ');"><i class="fa fa-trash-o "></i></button></td>';
			var row17 = '<td id="tbl_ma" class="hide">' + ma + '</td>';
			var row18 = '<td id="tbl_grp" class="hide">' + $("#grp").val() + '</td>';
			var row19 = '<td id="tbl_sch" class="hide">' + $("#sch").val() + '</td>';
			var row20 = '<td id="tbl_mfg" class="hide">' + $("#mfg").val() + '</td>';
			var row21 = '<td id="tbl_edprcnt" class="hide">' + $("#edpercnt").val() + '</td>';
			var row22 = '<td id="tbl_taxprcnt" class="hide">' + $("#taxprcnt").val() + '</td>';
			var taxid = '<td id="tbl_taxid" class="hide">' + $("#purTaxId").val() + '</td>';
			var taxmode = '<td id="tbl_taxmode" class="hide">' + $("#purtaxmode").val() + '</td>';
			var isgrptax = '<td id="tbl_isgrptax" class="hide">' + $("#purisgrptax").val() + '</td>';
			var row23 = '<td id="tbl_vatprcnt" class="hide">' + $("#vatprcnt").val() + '</td>';
			var row24 = '<td id="tbl_dprcnt" class="hide">' + discPercnt + '</td>';
			/*var row25 = '<td id="tbl_id" class="hide">' + $("#id").val() + '</td>';*/
			var row25 = '<td id="tbl_id" class="hide">' + 0 + '</td>';
			var row26 = '<td id="tbl_itemid" class="hide">' + $("#itemid").val() + '</td>';
			var grpid = '<td id="tbl_grpid" class="hide">' + $("#grpid").val() + '</td>';
			var schid = '<td id="tbl_schid" class="hide">' + $("#schid").val() + '</td>';
			var mfgid = '<td id="tbl_mfgid" class="hide">' + $("#mfgid").val() + '</td>';
			var packunitid = '<td id="tbl_punitid" class="hide">' + $("#punitid").val() + '</td>';
			var isFreeRow = '<td id="tbl_isFree" class="hide">0</td>';
			var ltadjRow = '<td id="tbl_ltAdj" class="hide">0</td>';
			var sku = '<td id="tbl_sku" class="hide">' + $("#purbarcode").val() + '</td>';
			var hsn = '<td id="tbl_hsn" class="hide">' + $("#purHsnCode").val() + '</td>';
			var tbl_sale_rate = '<td id="tbl_saleRate" class="numeric hide">' + sale_rate + '</td>';
			var tbl_item_remarks = '<td id="tbl_itmremarks" class="hide">' + $("#itmremarks").val() + '</td>'; // new added 7.5.2019
			var tbl_item_code = '<td id="tbl_saleitmcode" class="hide">' + $("#item_code").val() + '</td>'; // new added 7.5.2019

			var batchno = 0;
			var expirydateformat='NA'
			if($("#batchNo").val()!='' || $("#batchNo").val()!=undefined) {
				batchno = $("#batchNo").val();
			}
			if($("#expiryDateFormat").val()!='' || $("#expiryDateFormat").val()!=undefined) {
				expirydateformat = $("#expiryDateFormat").val();
			}

			var item_batchno = '<td id="tbl_batchNo" class="numeric hide">' + batchno + '</td>';
			item_batchno += '<td id="tbl_expiryDateFormat" class="hide">' + expirydateformat + '</td>';
			var item_expdate = '<td id="tbl_expDate" class="hide">' + $("#expDate").val() + '</td>';

			var isfreeiai = $('#isfreeiai').is(":checked") ? 1 : 0;
			item_batchno += '<td id="tbl_isfreeiai" class="hide">' + isfreeiai + '</td>';	


			//tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14 + row15 + row16 + row17 + row18 + row19 + row20 + row21 + row22 + row23 + row24 + row25 + row26+grpid+schid+mfgid + packunitid + isFreeRow + ltadjRow +'</tr>';
			tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row8 + row9 + tbl_sale_rate + row11 +taxTypeId+ row13 + row14 + row15 + netamt + row16 + row17 + row18 + row19 + row20 + row22 + taxid + taxmode + isgrptax + row24 + row25 + row26 +grpid+schid+mfgid + packunitid + isFreeRow + ltadjRow + sku + hsn + item_batchno + item_expdate + tbl_item_code + tbl_item_remarks + '</tr>';
			$("#peitem tbody").append(tr);
			$("#item_name").focus();
			calculateGrandTotal();
			calculateTotalMRP();
			//calculateTotalED();
			//calculateTotalVat();
			calculateTotalTax();
			calculateTotalDisc();
			//calculateTotalLtAdj();
			calculateNetTotal();
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

	});

	$("#save_btn").click(function() {
		var puritemlength = $('#peitem >tbody >tr').length;
		if(puritemlength>0)
		{
		if($("#itemid").val()==0 || $("#itemid").val()=="")
		{
			var  customer_id=parseFloat($("#salecustid").val());

			if($("#salecustid").val()=="" && $("#salecustname").val()=="")
			{
				$("#confirmval").val(-1);
				document.getElementById('confirmmessagecont').innerHTML = "Please enter Customer details";//getPurOrderText.selectVendorErr;
				showConfirmModal();
			}
			else
			{
				//console.log("vendordis="+$("#vendordis").val());

				var SaleOrderObj = {};
				var SaleOrderDetailsObjArr = [];

				$("#peitem tr").not('thead tr').each(function(	i,
																v) {
					var SaleOrderDetailsObj = {};
					//SaleOrderDetailsObj.batchNo = $(this).find('#tbl_batch_no').text();
					//SaleOrderDetailsObj.expiryDateFormat = $(this).find('#tbl_exp').text();
					SaleOrderDetailsObj.packQty = $(this).find('#tbl_pqty').text();
					SaleOrderDetailsObj.looseQty = $(this).find('#tbl_lqty').text();
					SaleOrderDetailsObj.conversion=$(this).find('#tbl_ratio').text();
					SaleOrderDetailsObj.itemId = $(this).find('#tbl_itemid').text();
					SaleOrderDetailsObj.id = $(this).find('#tbl_id').text();
					SaleOrderDetailsObj.discPer = $(this).find('#tbl_dprcnt').text();
					SaleOrderDetailsObj.disc = $(this).find('#tbl_disc').text();
					SaleOrderDetailsObj.grossAmount = $(this).find('#tbl_amnt').text();
					SaleOrderDetailsObj.mrp = $(this).find('#tbl_mrp').text();
					SaleOrderDetailsObj.rate = $(this).find('#tbl_rate').text();
					SaleOrderDetailsObj.taxPercentage = $(this).find('#tbl_taxprcnt').text();
					SaleOrderDetailsObj.taxAmount = $(this).find('#tbl_tax').text();
					SaleOrderDetailsObj.taxId = $(this).find('#tbl_taxid').text();
					SaleOrderDetailsObj.taxMode = $(this).find('#tbl_taxmode').text();
					SaleOrderDetailsObj.isGroupTax = $(this).find('#tbl_isgrptax').text();
					SaleOrderDetailsObj.netAmount = $(this).find('#tbl_netamt').text();
					SaleOrderDetailsObj.itemTotalMrp = $(this).find('#tbl_totamnt').text();
					SaleOrderDetailsObj.packUnitId = $(this).find('#tbl_punitid').text();
					//SaleOrderDetailsObj.saleRate = $(this).find('#tbl_saleRate').text();
					SaleOrderDetailsObj.saleRate = $(this).find('#tbl_rate').text();
					SaleOrderDetailsObj.taxTypeId = $(this).find('#tbl_taxTypeID').text();
					SaleOrderDetailsObj.itemFreeAgainstItem = $(this).find('#tbl_isfreeiai').text();
					SaleOrderDetailsObj.remarks = $(this).find('#tbl_itmremarks').text(); // new added 7.5.2019

					//optical specific
					SaleOrderDetailsObj.batchNo = $(this).find('#tbl_batchNo').text();
					SaleOrderDetailsObj.expiryDateFormat = $(this).find('#tbl_expiryDateFormat').text();
					SaleOrderDetailsObj.expiryDate = moment($(this).find('#tbl_expDate').text()).format('YYYY-MM-DD');
					SaleOrderDetailsObj.mfdDate = "";

					SaleOrderDetailsObj.itemLotAdjAmount = 0;
					SaleOrderDetailsObjArr[i] = SaleOrderDetailsObj;
				});
				SaleOrderObj.saleOrderDetails = SaleOrderDetailsObjArr;
				SaleOrderObj.invDate = $("#invdt").val();
				SaleOrderObj.dueDate = $("#duedt").val();
				if($("#salecustid").val()!=0) {
					SaleOrderObj.customerId = $("#salecustid").val();
					SaleOrderObj.customerDiscPer = 0;//$("#salediscount").val();
				}
				else {
					SaleOrderObj.customerDiscPer = 0;
					SaleOrderObj.customerId = 0;
					SaleOrderObj.customerName = $("#salecustname").val();
					SaleOrderObj.customerAddress = $("#salecustaddr").val();
					SaleOrderObj.customerPhone = $("#salecustph").val();
				}
				if($("#saledocid").val()=="") {
					SaleOrderObj.doctorId = 0;
					SaleOrderObj.doctorName = "";
				}
				else {
					SaleOrderObj.doctorId = $("#saledocid").val();
					SaleOrderObj.doctorName = $("#saledocname").val();
				}

				//SaleOrderObj.distributorId = $("#seldistributor").val().split("_")[0];
				//SaleOrderObj.distributorDiscPer=$("#vendordis").val();
				if($("#saleAdvAmount").val()=="")
				{
					SaleOrderObj.advAmount = 0.0;
				} else {
					SaleOrderObj.advAmount = parseFloat($("#saleAdvAmount").val()).toFixed(2);
				}
				SaleOrderObj.invNo = $("#invno").val();
				SaleOrderObj.grossAmount = $("#totgrosamnt").val();
				SaleOrderObj.taxAmount = $("#tottaxamnt").val();
				SaleOrderObj.discAmount = $("#totdiscamnt").val();
				SaleOrderObj.roundoff = $("#roundoff").val();
				SaleOrderObj.totalMrp = $("#totgrosmrp").val();
				SaleOrderObj.netAmount = $("#totnetamnt").val();
				SaleOrderObj.remarks = $("#remarks").val();

				SaleOrderObj.id = $('#saleOrderid').text();

				if(isOptical==1) {
					SaleOrderObj.rDvSph = $("#r_dv_sph").val();
					SaleOrderObj.rDvCyl = $("#r_dv_cyl").val();
					SaleOrderObj.rDvAxis = $("#r_dv_axis").val();
					SaleOrderObj.lDvSph = $("#l_dv_sph").val();
					SaleOrderObj.lDvCyl = $("#l_dv_cyl").val();
					SaleOrderObj.lDvAxis = $("#l_dv_axis").val();
					SaleOrderObj.rNvSph = $("#r_nv_sph").val();
					SaleOrderObj.rNvCyl = $("#r_nv_cyl").val();
					SaleOrderObj.rNvAxis = $("#r_nv_axis").val();
					SaleOrderObj.lNvSph = $("#l_nv_sph").val();
					SaleOrderObj.lNvCyl = $("#l_nv_cyl").val();
					SaleOrderObj.lNvAxis = $("#l_nv_axis").val();
					SaleOrderObj.rAdd = $("#r_add").val();
					SaleOrderObj.lAdd = $("#l_add").val();
				} else {
					SaleOrderObj.rDvSph = 0;
					SaleOrderObj.rDvCyl = 0;
					SaleOrderObj.rDvAxis = 0;
					SaleOrderObj.lDvSph = 0;
					SaleOrderObj.lDvCyl = 0;
					SaleOrderObj.lDvAxis = 0;
					SaleOrderObj.rNvSph = 0;
					SaleOrderObj.rNvCyl = 0;
					SaleOrderObj.rNvAxis = 0;
					SaleOrderObj.lNvSph = 0;
					SaleOrderObj.lNvCyl = 0;
					SaleOrderObj.lNvAxis = 0;
					SaleOrderObj.rAdd = 0;
					SaleOrderObj.lAdd = 0;
				}

				console.log(SaleOrderObj);
					$('#pleasewaitModal').modal('show');
					var ajaxCallObject = new CustomBrowserXMLObject();
					console.log("SaleOrderObj = "+SaleOrderObj)
					ajaxCallObject.callAjaxPost(BASE_URL + "/saleorder/createorupdatesaleorder.htm", SaleOrderObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						console.log("save pur order id="+response);
						var status = JSON.parse(response);
						$("#confirmval").val(status.id);
						if(status.id>0) {
							// $("#confirmval").val();
							uploadPrescription();
						}
						chngeResultStat(status);
					});
				}
			}
			else
			{
				$("#confirmval").val(-1);
				document.getElementById('confirmmessagecont').innerHTML = getPurOrderText.addEditChckBefrSave;
				showConfirmModal();
			}
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
		var expdtfrmt = "<td>" +itemPurHis.expiryDateFormat + "</td>";
		var purqty = "<td>" +itemPurHis.packQty + "</td>";
		var freeqty = "<td>" +itemPurHis.freeQty + "</td>";
		var uom = "<td>" +itemPurHis.packUnitName + "</td>";
		var mrp = "<td>" +parseFloat(itemPurHis.mrp).toFixed(2) + "</td>";
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
			$("#editNewItemBtn").addClass("hide");
			$("#addNewItemBtn").removeClass("hide");
			$("#editItemLabel").addClass("hide");
			$("#newItemLabel").removeClass("hide");

			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getPurOrderText.wrongBarcode;
			showConfirmModal();
		}
	}, null);

}

function closeOrder()
{
   var poid=$("#orderno").val();
   document.getElementById('confirmId').value=poid;
   document.getElementById('confirmType').value="close";
   $('#purDelConfirmModal').modal('show');
}

function frmBtchfillItemDetails(itemdetval) {
	console.log(itemdetval.itemName);
	$("#rate").attr("readonly",false);
	$("#item_name").val(itemdetval.itemName);
	$("#batch_no").val(itemdetval.batchNo);
	$("#exp").val(itemdetval.expiryDateFormat);
	$("#punitid").val(itemdetval.packUnitId);
	/*$("#pqty").val(itemdetval.packQty);
	$("#lqty").val(itemdetval.looseQty);*/
	$("#pqty").val(0);
	$("#ratio").val(itemdetval.conversion);
	//$("#free").val(itemdetval.freeQty);
	$("#mrp").val(itemdetval.mrp);

	if(itemdetval.saleRate!=0 || itemdetval.saleRate!="")
	{
		//$("#sale_rate").val(itemdetval.saleRate);
		$("#rate").val(itemdetval.saleRate);
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

	
	if(itemdetval.disc == undefined){
		var itemdisc = 0;
	} else {
		var itemdisc = itemdetval.disc;
	}

	var tax = Number(Number(Number(itemdetval.saleRate) * Number($("#pqty").val())) - Number(itemdisc)) * Number(itemdetval.taxPercentage) / 100;
	
	$("#taxprcnt").val(itemdetval.taxPercentage);
	$("#tax").val(tax);
	$("#purTaxId").val(itemdetval.taxId);
	$("#purtaxmode").val(itemdetval.taxMode);
	$("#purisgrptax").val(itemdetval.isGroupTax);
	/*$("#vatprcnt").val(itemdetval.vatPer);
	$("#vat").val(itemdetval.vat);*/
	$("#dprcnt").val($("#vendordis").val());
	//$("#dprcnt").val(itemdetval.discPer);
	$("#disc").val(itemdisc);
	//		$("#id").val($("#tblrow_"+trId).find('#tbl_id').text());
	$("#itemid").val(itemdetval.itemId);
	$("#purbarcode").val(itemdetval.sku);
	$("#purHsnCode").val(itemdetval.hsnCode);

	$("#item_taxTypeId").val(itemdetval.taxTypeId);
	$("#itemdualstock").val(itemdetval.dualStockRequired);
	
	$("#batchNo").val(itemdetval.batchNo);
	$("#expiryDateFormat").val(itemdetval.expiryDateFormat);
	$("#expDate").val(moment(itemdetval.expiryDate).format('YYYY-MM-DD'));
	
	$("#item_code").val(itemdetval.itemCode); // new added 7.5.2019
	
	$('#stkdetModal').modal('hide');

}

$(".power-table input[type=number]").blur(function(){
	console.log("input changed");
	if($(this).val()=="")
		$(this).val(0);
});

$(".power-table input[type=number]").focus(function(){
	console.log("input focused");
	if($(this).val()==0)
		$(this).val("");
});

$(document).ready(function(){
	var link = window.location.origin+$("#prescriptionlink").attr("href");
	console.log(link);
	$.ajax({
	    url:link,
	    type:'HEAD',
	    error: function()
	    {
	        //file not exists
	        $("#prescriptionlink").hide();
	    },
	    success: function()
	    {
	        //file exists
	    }
	});
});

$("#prescriptionlink").on('click',function(){
	console.log("button clicked");
	$("#prescription_img").attr("src",$("#prescriptionlink").attr("href")+"?version="+Math.random());
	$("#prescriptionModal").modal("show");
})

$("#complaint_btn").on('click',function(){
	document.getElementById("complaintForm").reset();
	$("#saleOrderNum").text($(this).attr("data-orderno"));
	$("#complaintModal").modal("show");
});