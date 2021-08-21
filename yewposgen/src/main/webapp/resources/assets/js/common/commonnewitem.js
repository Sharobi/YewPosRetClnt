$(document).ready(function() {
	
	
	var rackid = $("#edited_rackId").val();
	console.log("rackid=" + rackid);
	if (rackid == '' || rackid == undefined) {
		$("#rackSelect").val(0);
	} else {
		$("#rackSelect").val($("#edited_rackId").val());
	}
	var mrp = $("#edited_isonmrpId").val();
	if (mrp == '' || mrp == undefined) {
		$("#isonmrp").val(0);
	} else {
		$("#isonmrp").val(mrp);
	}


	
	$("#searchBtn").click(function(event) {
		if ($(".cmn").not(".hide").val() == "" || $(".cmn").not(".hide").val() == 0) {
			event.preventDefault();
		} else {
			$("#searchForm").submit();
		}
	});

	if ($("#criteriaName").val() != "") {
		$("#searchBySelect").val($("#criteriaName").val());
		if ($("#searchBySelect").val() == "name") {
			$("#itemName1").removeClass("hide");
			$("#contentName1").addClass("hide");
			$("#manufacturerName1").addClass("hide");
			$("#contentName1").attr("value", "");
			$("#manufacturerName1").attr("value", "");
		} else if ($("#searchBySelect").val() == "content") {
			$("#contentName1").removeClass("hide");
			$("#itemName1").addClass("hide");
			$("#manufacturerName1").addClass("hide");
			$("#itemName1").attr("value", "");
			$("#manufacturerName1").attr("value", "");
		} else {
			$("#manufacturerName1").removeClass("hide");
			$("#contentName1").addClass("hide");
			$("#itemName1").addClass("hide");
			$("#contentName1").attr("value", "");
			$("#itemName1").attr("value", "");
		}
	} else {
	}
	$("#searchBySelect").change(function() {
		if ($(this).val() == "name") {
			$("#itemName1").removeClass("hide");
			$("#contentName1").addClass("hide");
			$("#manufacturerName1").addClass("hide");
			$("#contentName1").attr("value", "");
			$("#manufacturerName1").attr("value", "");
		} else if ($(this).val() == "content") {
			$("#contentName1").removeClass("hide");
			$("#itemName1").addClass("hide");
			$("#manufacturerName1").addClass("hide");
			$("#itemName1").attr("value", "");
			$("#manufacturerName1").attr("value", "");
		} else {
			$("#manufacturerName1").removeClass("hide");
			$("#contentName1").addClass("hide");
			$("#itemName1").addClass("hide");
			$("#contentName1").attr("value", "");
			$("#itemName1").attr("value", "");
		}
	});
	
	
	/*
	 * add on 08_11_20117
	 * */


});
function checkAddEditDeleteResult() {
	var result = $("#result").val();
	var add_edit_delete = $("#add_edit_delete").val();
	if (result != "") {
		if (result == "success") {
			if (add_edit_delete == "add") {
				document.getElementById('confirmmessagecont').innerHTML = getItemText.dataSucAdd;
				showConfirmModal();
			} else if (add_edit_delete == "edit") {
				document.getElementById('confirmmessagecont').innerHTML = getItemText.dataSucUpdate;
				showConfirmModal();
			} else if (add_edit_delete == "delete") {
				document.getElementById('confirmmessagecont').innerHTML = getItemText.dataSucDelete;
				showConfirmModal();
			} else {
			}
		} else if (result == "failure") {
			if (add_edit_delete == "add") {
				document.getElementById('confirmmessagecont').innerHTML = getItemText.dataNotAdd;
				showConfirmModal();
			} else if (add_edit_delete == "edit") {
				document.getElementById('confirmmessagecont').innerHTML = getItemText.dataNotUpdate;
				showConfirmModal();
			} else if (add_edit_delete == "delete") {
				document.getElementById('confirmmessagecont').innerHTML = getItemText.dataNotDelete;
				showConfirmModal();
			} else {
			}
		} else {
		}

	} else {
	}
}

function openAddEditModal(id) {
	document.getElementById('alertMsg').innerHTML = '';
	if (id == 0) {
		location.href = BASE_URL + '/item/loaditemmst/0.htm';
	} else {
		location.href = BASE_URL + '/item/loaditemmst/' + id + '.htm';
	}
}

function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/item/deleteItem/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == "1") {
			document.getElementById('confirmmessagecont').innerHTML = getItemText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getItemText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	/*window.location.replace(BASE_URL + '/pages/inv/item.jsp');
	location.href = BASE_URL + '/item/loaditem.htm';*/
}

function checkSameItem(	itemName,
						itemId) {
	console.log("itemName=" + itemName);
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/item/checkSameItemExists/" + itemName + "/" + itemId + ".htm", function(response) {
		if (response == 1) {
			$("#itmnameexistdiv").show();
			$("#itemaddbut").prop("disabled", true);
			$("#itemupdatebut").prop("disabled", true);
		} else {
			$("#itmnameexistdiv").hide();
			$("#itemaddbut").prop("disabled", false);
			$("#itemupdatebut").prop("disabled", false);
		}
	}, null);
}

function contentDetailsMod() {
	if ($("#content_id").val() != 0) {
		$('#contentDetailsModal').modal('show');
		$("#headertext").text("Details");
		$("#contentVal").text($("#content_Dets").val());
	}
}
/*
 *  FOR VALIDATION ONKEYUP
 * 
 * */
$("#itemvat").keyup(function() {
	if (isNaN($(this).val())) {
		document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
		$(this).focus();
	} else {
		document.getElementById('alertMsgItemMaster').innerHTML = "";
	}
});

$("#itemreorderlevel").keyup(function() {
	if (isNaN($(this).val())) {
		document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
		$(this).focus();
	} else {
		document.getElementById('alertMsgItemMaster').innerHTML = "";
	}
});

$("#itemPrice").keyup(function() {
	if (isNaN($(this).val())) {
		document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
		$(this).focus();
	} else {
		document.getElementById('alertMsgItemMaster').innerHTML = "";
	}
});

$("#itemconversion").keyup(function() {
	if (isNaN($(this).val())) {
		document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
		$(this).focus();
	} else {
		document.getElementById('alertMsgItemMaster').innerHTML = "";
	}
});

/*
$("#itemMarkup").keyup(function() {
	if (isNaN($(this).val())) {
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
		$(this).focus();
	} else {
		document.getElementById('alertMsg').innerHTML = "";
	}
});*/

$("#maxDiscountLimit").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{		
		if(Number($("#discountvalue").val())>Number($("#maxDiscountLimit").val()))
		{
			document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.discGrtMaxDiscErr;
			$(this).focus();
		}
		else
		{
			document.getElementById('alertMsgItemMaster').innerHTML = "";
		}
	}
});


$("#discountvalue").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{		
		if(Number($("#discountvalue").val())>Number($("#maxDiscountLimit").val()))
		{
			document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.discGrtMaxDiscErr;
			$(this).focus();
		}
		else
		{
			document.getElementById('alertMsgItemMaster').innerHTML = "";
		}
	}
});

/*
 * extra add 
 */
$("#sale_rate_value").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsgItemMaster').innerHTML = "";
	}
});


$("#purchaserate").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsgItemMaster').innerHTML = "";
	}
});

$("#orderquanity").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsgItemMaster').innerHTML = "";
	}
});


$("#maximumstock").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsgItemMaster').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsgItemMaster').innerHTML = "";
	}
});
/*
 *  on keyup validation end 
 */

var itemObj = null;

function newItemAdd() {
	
 
	if ( isNaN($("#itemreorderlevel").val()) || isNaN($("#itemconversion").val()) ||isNaN($("#sale_rate").val())||isNaN($("#purchaserate").val())||isNaN($("#maximumstock").val())) {
		$("#itemaddbut").attr("disable", true);
	} else {
		
	
		var grp_label = $("#grp_label").text();
		var grp_field = grp_label.substring(0, grp_label.lastIndexOf(" "));

		var name_label = $("#name_label").text();
		var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

		var code_label = $("#code_label").text();
		var code_field = code_label.substring(0, code_label.lastIndexOf(" "));

		var schdle_label = $("#schdle_label").text();
		var schdle_field = schdle_label.substring(0, schdle_label.lastIndexOf(" "));

		var brand_label = $("#brand_label").text();
		var brand_field = brand_label.substring(0, brand_label.lastIndexOf(" "));

		var vat_label = $("#vat_label").text();
		var vat_field = vat_label.substring(0, vat_label.lastIndexOf(" "));

		var pckUnit_label = $("#pckUnit_label").text();
		var pckUnit_field = pckUnit_label.substring(0, pckUnit_label.lastIndexOf(" "));

		var reOrder_label = $("#reOrder_label").text();
		var reOrder_field = reOrder_label.substring(0, reOrder_label.lastIndexOf(" "));

		var price_label = $("#price_label").text();
		var price_field = price_label.substring(0, price_label.lastIndexOf(" "));

		var rack_label = $("#rack_label").text();
		var rack_field = rack_label.substring(0, rack_label.lastIndexOf(" "));

		var cntnt_label = $("#cntnt_label").text();
		var cntnt_field = cntnt_label.substring(0, cntnt_label.lastIndexOf(" "));

		var cat_label = $("#cat_label").text();
		var cat_field = cat_label.substring(0, cat_label.lastIndexOf(" "));

		var subCat_label = $("#subCat_label").text();
		var subCat_field = subCat_label.substring(0, subCat_label.lastIndexOf(" "));

		var mnfctr_label = $("#mnfctr_label").text();
		var mnfctr_field = mnfctr_label.substring(0, mnfctr_label.lastIndexOf(" "));

		var cnvrsn_label = $("#cnvrsn_label").text();
		var cnvrsn_field = cnvrsn_label.substring(0, cnvrsn_label.lastIndexOf(" "));

		var lseUnit_label = $("#lseUnit_label").text();
		var lseUnit_field = lseUnit_label.substring(0, lseUnit_label.lastIndexOf(" "));

		var reOrderUnit_label = $("#reOrderUnit_label").text();
		var reOrderUnit_field = reOrderUnit_label.substring(0, reOrderUnit_label.lastIndexOf(" "));

		var netContent_label = $("#netcntnt_label").text();
		var netContent_field = netContent_label.substring(0, netContent_label.lastIndexOf(" "));

		var purchasetax_label = $("#purchasetax_label").text();
		var purchasetax_field = purchasetax_label.substring(0, purchasetax_label.lastIndexOf(" "));
		
		var saletax_label = $("#saletax_label").text();
		var saletax_field = saletax_label.substring(0, saletax_label.lastIndexOf(" "));
		
	    var marketer_by_label=$("#marketed_by").text();
		var marketer_by_field = marketer_by_label.substring(0, marketer_by_label.lastIndexOf(" "));
		
		var color_label = $("#color_label").text();
//		var color_field = color_label.substring(0, color_label.lastIndexOf(" "));
		var color_field = color_label;
		//color_field="Colour";
		var size_label = $("#size_label").text();
//		var size_field = size_label.substring(0, size_label.lastIndexOf(" "));
		var size_field = size_label;
		//size_field="Size";
		var colourWiseStockRequired=$("#colourWiseStockRequired").val();
		var sizeWiseStockRequired=$("#sizeWiseStockRequired").val();
		var field_names="";
		
		var hsn_label = $("#hsn_label").text();
		var hsn_field = hsn_label.substring(0, hsn_label.lastIndexOf(" "));
		
		/*if(colourWiseStockRequired==1 && sizeWiseStockRequired==1){
			 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemPrice",price_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["colour",color_field],["size",size_field]];
		}else if(colourWiseStockRequired==1){
			field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemPrice",price_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["colour",color_field]];
		}else if(sizeWiseStockRequired==1){
			field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemPrice",price_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["size",size_field]];
		}else{
			 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemPrice",price_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field]];
		}*/
		
		if(activeCompanyId == 18){
			field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["hsnCode",hsn_field]];
			
		}else{
			field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field]];
			
		}
		
		
	   // var alltaxtype=$("#alltaxtype_id").text();
		//var alltaxtype_field= alltaxtype.substring(0, alltaxtype.length-1);
		
		//var field_names = [ [ "itemName", name_field ], [ "itemPackingUnit", pckUnit_field ], [ "itemPrice", price_field ], [ "itemContent", cntnt_field ], [ "itemManufacturer", mnfctr_field ], [ "itemconversion", cnvrsn_field ],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["alltaxtypeexclusive",alltaxtype_field]];
	//	var field_names = [ [ "itemName", name_field ], [ "itemPackingUnit", pckUnit_field ], [ "itemPrice", price_field ],["purchaseTax",purchasetax_field],["saleTax",saletax_field]];
		if (fieldValidationWithAlertDivId(field_names, "alertMsgItem") > 0) {
			//  e.preventDefault();
		 return false;

		}else {
		/**
		 * for validation 
		 */
		if(sizeWiseStockRequired==1){
			if($("#sizecommitm").val()==null||$("#sizecommitm").val()==""){
				document.getElementById('alertMsgItem').innerHTML = size_field + " " + getFieldText.fieldReq;
				$("#sizecommitm").focus();
				return false;
			}else
			{
				document.getElementById('alertMsgItem').innerHTML = "";
			}
		}
		 if(colourWiseStockRequired==1){
			 if($("#colouritemchk").val()==null||$("#colouritemchk").val()==""){
			 document.getElementById('alertMsgItem').innerHTML = color_field + " " + getFieldText.fieldReq;
				$("#colouritemchk").focus();
				return false;
			 }else
				{
					document.getElementById('alertMsgItem').innerHTML = "";
				}
		 }
			if(isNaN($("#maxDiscountLimit").val()))
			{
				document.getElementById('alertMsgItem').innerHTML = getFieldText.numberCheck;
				$("#maxDiscountLimit").focus();
				return false;
			}
			else
			{		
				if(Number($("#discountvalue").val())>Number($("#maxDiscountLimit").val()))
				{
					document.getElementById('alertMsgItem').innerHTML = getFieldText.discGrtMaxDiscErr;
					$("#maxDiscountLimit").focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsgItem').innerHTML = "";
				}
			}
			
			if(isNaN($("#discountvalue").val()))
			{
				document.getElementById('alertMsgItem').innerHTML = getFieldText.numberCheck;
				$("#discountvalue").focus();
				return false;
			}
		else
		{		
			if(Number($("#discountvalue").val())>Number($("#maxDiscountLimit").val()))
			{
				document.getElementById('alertMsgItem').innerHTML = getFieldText.discGrtMaxDiscErr;
				$("#discountvalue").focus();
				return false;
			}
			else
			{
				document.getElementById('alertMsgItem').innerHTML = "";
			}
		}	
			var disc = 0.0;
			if($("#discountvalue").val() == "")
			{
				disc = 0.0
			}
			else
			{
				disc = $("#discountvalue").val();
			}
			
			var maxdisc = 0.0;
			if($("#maxDiscountLimit").val() == "" || $("#maxDiscountLimit").val() == 0 || $("#maxDiscountLimit").val() == 0.0)
			{
				maxdisc = 100;
			}
			else
			{
				maxdisc = $("#maxDiscountLimit").val();
			} 
			
			var reorderlevel = 0;
			if($("#itemreorderlevel").val() == "")
			{
				reorderlevel = 0;
			}
			else
			{
				reorderlevel = $("#itemreorderlevel").val();
			}
	
	 $('#pleasewaitModal').modal('show');
		var itemMasterObj = {};
		
		var itemId = $("#itemidparchase").val();
		if(itemId == 0 || itemId =="")
		{
			itemMasterObj.id = 0;								
		}
		else
		{
			itemMasterObj.id = $("#itemid").val();
		}
		
	
		
		var orderquanity = $("#orderquanity").val();
		if(orderquanity == 0 || orderquanity ==""|| orderquanity==undefined)
		{
			itemMasterObj.reorderLevelQty = 0.0;								
		}
		else
		{
			itemMasterObj.reorderLevelQty = $("#orderquanity").val();
		}
		
		if($("#maximumstock").val() ==" " || $("#maximumstock").val() ==0 || $("#maximumstock").val()==undefined)
		{
			itemMasterObj.maximumQty = 0;								
		}
		else
		{
			itemMasterObj.maximumQty = $("#maximumstock").val();
		}
		/*
		 * for sale rate 
		 */
		var sale_rate = $("#sale_rate_value").val();
		if(sale_rate == 0 || sale_rate =="" || sale_rate==undefined)
		{
			itemMasterObj.saleRate = 0.0;								
		}
		else
		{
			itemMasterObj.saleRate = $("#sale_rate_value").val();
		}
		
		var purchaserate = $("#purchaserate").val();
		if(purchaserate == 0 || purchaserate =="" || purchaserate==undefined)
		{
			itemMasterObj.purchaseRate = 0.0;								
		}
		else
		{
			itemMasterObj.purchaseRate = $("#purchaserate").val();
		}
		
			itemMasterObj.name = $("#itemName").val();
			itemMasterObj.sku = $("#sku").val();
			itemMasterObj.code = $("#itemCode").val();
			if($("#grpSelect").val()==null||$("#grpSelect").val()==""|| $("#grpSelect").val()==0)
			{
				itemMasterObj.groupId = 0;	
			}else{
				itemMasterObj.groupId = $("#grpSelect").val();	
			}
			//itemMasterObj.groupId = $("#grpSelect").val();
			itemMasterObj.categoryId = $("#catSelect").val();
			if($("#subCatSelect").val()==null||$("#subCatSelect").val()==""|| $("#subCatSelect").val()==0)
			{
				itemMasterObj.subCategoryId = 0;	
			}else{
				itemMasterObj.subCategoryId = $("#subCatSelect").val();	
			}
			//itemMasterObj.subCategoryId = $("#subCatSelect").val();
//			itemMasterObj.scheduleId = $("#scheSelect").val();
			
			if($("#scheSelect").val()==null||$("#scheSelect").val()==""|| $("#scheSelect").val()==0)
			{
				itemMasterObj.scheduleId = 0;	
			}else{
				itemMasterObj.scheduleId = $("#scheSelect").val();	
			}
			itemMasterObj.packUnitId = $("#packUnitId").val();
			itemMasterObj.reorderLevel = reorderlevel;
			itemMasterObj.taxTypeId = $("#alltaxtypeexclusive").val();
			itemMasterObj.purchaseTaxId = $("#purchaseTaxId").val();
			itemMasterObj.purchaseTaxPercentage = $("#purchaseTaxPerc").val();
			itemMasterObj.saleTaxId = $("#saleTaxId").val();
			itemMasterObj.saleTaxPercentage = $("#saleTaxPerc").val();
			var itemPrice = $("#itemPrice").val();
			if(itemPrice == 0 || itemPrice =="" || itemPrice==undefined)
			{
				itemMasterObj.mrp = 0.0;								
			}
			else
			{
				itemMasterObj.mrp = $("#itemPrice").val();
			}
			
			itemMasterObj.rackId = $("#rackSelect").val();
			itemMasterObj.hsnCode = $("#hsnCode").val();
			if($("#contentId").val()==""|| $("#contentId").val()==0)
			{
				itemMasterObj.contentId = 0;	
			}else{
				itemMasterObj.contentId = $("#contentId").val();	
			}
			itemMasterObj.brandId = 0;
			if($("#manufacturerId").val()==""|| $("#manufacturerId").val()==0)
			{
				itemMasterObj.manufacturerId = 0;
			}else{
				itemMasterObj.manufacturerId = $("#manufacturerId").val();
			}
			
			itemMasterObj.vat = 0;
			itemMasterObj.isOnMrp = $("#isonmrp").val();
			if($("#itemconversion").val()==""||$("#itemconversion").val()==0){
				itemMasterObj.conversion =1;
			}else{
				itemMasterObj.conversion = $("#itemconversion").val();
			}
			if($("#looseUnitId").val()==null||$("#looseUnitId").val()==""|| $("#looseUnitId").val()==0)
			{
				itemMasterObj.looseUnitId = 0;	
			}else{
				itemMasterObj.looseUnitId = $("#looseUnitId").val();
			}
			
			if($("#itemstrength").val()==""|| $("#itemstrength").val()==0)
			{
				itemMasterObj.strength =0;
			}else{
				itemMasterObj.strength = $("#itemstrength").val();
			}
			
			itemMasterObj.isDiscount = $("#isDiscount").val();
			itemMasterObj.maxDiscountLimit =maxdisc;
			itemMasterObj.discount =disc;
			itemMasterObj.launchDate = $("#launchdate").val();
			itemMasterObj.discontinueDate = $("#discontinuedate").val();
			itemMasterObj.netContent = $("#netcontent").val();
			itemMasterObj.note = $("#itemRemarks").val();
			if($("#marketed_by_id").val()==""|| $("#marketed_by_id").val()==0)
			{
				itemMasterObj.marketerId=0;
			}else{
				itemMasterObj.marketerId=$("#marketed_by_id").val();
			}
			if($("#reorderLevelUnitId").val()==null||$("#reorderLevelUnitId").val()==""|| $("#reorderLevelUnitId").val()==0)
			{
				itemMasterObj.reorderLevelUnitId = 0;	
			}else{
				itemMasterObj.reorderLevelUnitId = $("#reorderLevelUnitId").val();
			}
			
			itemMasterObj.description=$("#description").val();
			itemMasterObj.printName=$("#printname").val();
			itemMasterObj.imgUrl=$("#imgurl").val();
			if($("#maxlevel").val()==""){
				itemMasterObj.maxLevel=1;
			}else{
				itemMasterObj.maxLevel=$("#maxlevel").val();
			}
			if($("#minlevel").val()==""){
				itemMasterObj.minLevel=1;
			}else{
				itemMasterObj.minLevel=$("#minlevel").val();
			}
			
			itemMasterObj.isActive=$("#isactive").val();
			itemMasterObj.colour=$("#colouritemchk").val();
			itemMasterObj.sizeType=$("#sizetype").val();
			if($("#sizecommitm").val()==""){
				itemMasterObj.size=0;
			}else{
				itemMasterObj.size=$("#sizecommitm").val();
			}
			if($("#weight").val()==""){
				itemMasterObj.weight=0;
			}else{
				itemMasterObj.weight=$("#weight").val();
			}
			
			itemMasterObj.partNumber=0;
			itemMasterObj.authorName=$("#authorName").val();
			itemMasterObj.edition=$("#edition").val();
			if($("#listedMrp").val()==""){
				itemMasterObj.listedMrp=0;
			}else{
				itemMasterObj.listedMrp=$("#listedMrp").val();
			}
			if($("#wsp").val()==""){
				itemMasterObj.wsp=0;
			}else{
				itemMasterObj.wsp=$("#wsp").val();
			}
			
			itemMasterObj.stockRequired=$("#stockRequired").val();
			if($("#expiryMonthRequired").val()==null||$("#expiryMonthRequired").val()==""|| $("#expiryMonthRequired").val()==0)
			{
				itemMasterObj.expiryMonthRequired = 0;	
			}else{
				itemMasterObj.expiryMonthRequired = $("#expiryMonthRequired").val();	
			}
			//itemMasterObj.expiryMonthRequired=$("#expiryMonthRequired").val();
			if($("#batchWiseStock").val()==null||$("#batchWiseStock").val()==""|| $("#batchWiseStock").val()==0)
			{
				itemMasterObj.batchWiseStock = 0;	
			}else{
				itemMasterObj.batchWiseStock=$("#batchWiseStock").val();	
			}
			if($("#expiryDateRequired").val()==null||$("#expiryDateRequired").val()==""|| $("#expiryDateRequired").val()==0)
			{
				itemMasterObj.expiryDateRequired = 0;	
			}else{
				itemMasterObj.expiryDateRequired=$("#expiryDateRequired").val();
			}
			if($("#dualStockRequired").val()==null||$("#dualStockRequired").val()==""|| $("#dualStockRequired").val()==0)
			{
				itemMasterObj.dualStockRequired = 0;	
			}else{
				itemMasterObj.dualStockRequired=$("#dualStockRequired").val();
			}
			if($("#saleRateOn").val()==null||$("#saleRateOn").val()==""|| $("#saleRateOn").val()==0)
			{
				itemMasterObj.saleRateOn = 0;	
			}else{
				itemMasterObj.saleRateOn=$("#saleRateOn").val();
			}
			if($("#locationRequired").val()==null||$("#locationRequired").val()==""|| $("#locationRequired").val()==0)
			{
				itemMasterObj.locationRequired = 0;	
			}else{
				itemMasterObj.locationRequired=$("#locationRequired").val();
			}
			if($("#priceListRequired").val()==null||$("#priceListRequired").val()==""|| $("#priceListRequired").val()==0)
			{
				itemMasterObj.priceListRequired = 0;	
			}else{
				itemMasterObj.priceListRequired=$("#priceListRequired").val();
			}
			if($("#sizeWiseStockRequired").val()==null||$("#sizeWiseStockRequired").val()==""|| $("#sizeWiseStockRequired").val()==0)
			{
				itemMasterObj.sizeWiseStockRequired = 0;	
			}else{
				itemMasterObj.sizeWiseStockRequired=$("#sizeWiseStockRequired").val();
			}
			if($("#warrantyRequired").val()==null||$("#warrantyRequired").val()==""|| $("#warrantyRequired").val()==0)
			{
				itemMasterObj.warrantyRequired = 0;	
			}else{
				itemMasterObj.warrantyRequired=$("#warrantyRequired").val();
			}
			if($("#warrantyTypeOn").val()==null||$("#warrantyTypeOn").val()==""|| $("#warrantyTypeOn").val()==0)
			{
				itemMasterObj.warrantyTypeOn = 0;	
			}else{
				itemMasterObj.warrantyTypeOn=$("#warrantyTypeOn").val();
			}
			
			if($("#warrantyMonthcmnitm").val()==null||$("#warrantyMonthcmnitm").val()==""|| $("#warrantyMonthcmnitm").val()==0){
				itemMasterObj.warrantyMonth=0;
			}else{
				itemMasterObj.warrantyMonth=$("#warrantyMonthcmnitm").val();
			}
			if($("#mrpRequired").val()==null||$("#mrpRequired").val()==""|| $("#mrpRequired").val()==0)
			{
				itemMasterObj.mrpRequired = 0;	
			}else{
				itemMasterObj.mrpRequired=$("#mrpRequired").val();
			}
			if($("#serialNoRequired").val()==null||$("#serialNoRequired").val()==""|| $("#serialNoRequired").val()==0)
			{
				itemMasterObj.serialNoRequired = 0;	
			}else{
				itemMasterObj.serialNoRequired=$("#serialNoRequired").val();
			}
			if($("#serialNoSuffRequiredcmnitm").val()==null||$("#serialNoSuffRequiredcmnitm").val()==""|| $("#serialNoSuffRequiredcmnitm").val()==0)
			{
				itemMasterObj.serialNoSuffRequired = 0;	
			}else{
				itemMasterObj.serialNoSuffRequired=$("#serialNoSuffRequiredcmnitm").val();
			}
			//itemMasterObj.serialNoSuffRequired=0;//$("#serialNoSuffRequired").val();
			if($("#serialNoPrefRequiredcmnitm").val()==null||$("#serialNoPrefRequiredcmnitm").val()==""|| $("#serialNoPrefRequiredcmnitm").val()==0)
			{
				itemMasterObj.serialNoPrefRequired = 0;	
			}else{
				itemMasterObj.serialNoPrefRequired=$("#serialNoPrefRequiredcmnitm").val();
			}
			
			if($("#colourWiseStockRequired").val()==null||$("#colourWiseStockRequired").val()==""|| $("#colourWiseStockRequired").val()==0)
			{
				itemMasterObj.colourWiseStockRequired = 0;	
			}else{
				itemMasterObj.colourWiseStockRequired=$("#colourWiseStockRequired").val();	
			}
		//	itemMasterObj.serialNoPrefRequired=0;//$("#serialNoPrefRequired").val();
			itemMasterObj.serialNoType=$("#serialNoType").val();
			itemMasterObj.code = $("#itemCode").val(); // new added 7.5.2019
			
			
			
			console.log("itemmaster=" + JSON.stringify(itemMasterObj));

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/item/addnewitem.htm", itemMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				console.log(response);
				var status = JSON.parse(response);				
				if (status.id > 0) {
					itemObj = status.item;
					$("#confirmval").val("itemAddOk");
				}
				else
				{
					$("#confirmval").val("itemAddErr");
				}
				chngeResultStat(status);
				/*if (response == 'success') {					
					document.getElementById('confirmmessagecont').innerHTML = getcommonNewItemText.dataSucAdd;
					showConfirmModal();
					$("#confirmval").val("itemAdd");
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getcommonNewItemText.dataNotAdd;
					showConfirmModal();
					$("#confirmval").val("itemAdd");
				}*/

			});
		} // end else 

	} //end first if
}

/*function lseSaleChkBox()
{
	if ($('input[name=isLooseSale]').is(":checked"))
	{
		$("#lseSaleStat").val(1);
		$("#lseUnitDiv").removeClass("hide");
	}
	else
	{
		$("#lseSaleStat").val(0);
		$("#lseUnitDiv").addClass("hide");
	}
}*/

function openGroupMod() {
	document.getElementById('alertMsgGroup').innerHTML = '';
	$("#grp_id").val(0);
	$('#grpName').val("");
	$('#grpDesc').val("");
	$('#groupAddEditModal').modal('show');
}

function addEditGroup() {
	document.getElementById('alertMsgGroup').innerHTML = '';
	var grp_id = $("#grp_id").val();
	var groupName = $('#grpName').val();
	var groupDesc = $('#grpDesc').val();

	var name_label = $("#grpname_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var desc_label = $("#grpdesc_label").text();
	var desc_field = desc_label.substring(0, desc_label.lastIndexOf(" "));
	
	var field_names = [["grpName",name_field],["grpDesc",desc_field]];
	if(fieldValidationWithAlertDivId(field_names,"alertMsgGroup")>0)
		{			
		}
	else {
		$('#groupAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		var groupMasterObj = {};
		groupMasterObj.name = groupName;
		groupMasterObj.description = groupDesc;	

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addgroup.htm", groupMasterObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			var status = JSON.parse(response);
			$("#rqstType").val("group");
			$("#objctId").val(status.id);
			$("#objctName").val(groupName);
			chngeResultStatForNewItem(status);

		});
	}
}

function openSchMod() {
	document.getElementById('alertMsgSche').innerHTML = '';
	$("#scheduleName").val("");
	$("#scheduleId").val(0);
	$("#scheheadertext").text("Add Schedule");
	$('#scheduleAddEditModal').modal('show');
}

function addEditSchedule()
{
	document.getElementById('alertMsgSche').innerHTML='';
	var scheduleName=$('#scheduleName').val();
	var id=$('#scheduleId').val();
	
	var name_label = $("#schname_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var field_names = [["scheduleName",name_field]];
	if(fieldValidationWithAlertDivId(field_names,"alertMsgSche")>0)
		{			
		}
	else
		{
		$('#scheduleAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		var d = new Date();
		var ScheduleMasterObj = {};
		ScheduleMasterObj.name = scheduleName;
		
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addSchedule.htm", ScheduleMasterObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			var status = JSON.parse(response);
			$("#rqstType").val("schedule");
			$("#objctId").val(status.id);
			$("#objctName").val(scheduleName);
			chngeResultStatForNewItem(status);
		});
	}
}

function openBrandMod() {
	$('#brandName').val("");
	$('#alertMsgbrand').text("");
	$("#brandAddEditModal").modal("show");
}
function addEditBrand() {
	document.getElementById('alertMsgbrand').innerHTML='';
	var brandName = $('#brandName').val();
	var name_label = $("#brname_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	var field_names = [ [ "brandName", name_field ] ];
	if (fieldValidationWithAlertDivId(field_names, "alertMsgbrand") > 0) {

	} else {
		$('#brandAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		// add brand
		var BrandMasterObj = {};
		BrandMasterObj.name = brandName;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/brand/addbrand.htm", BrandMasterObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == 'success') {
				$("#rqstType").val("brand");
				document.getElementById('confirmmessagecontNewItem').innerHTML = getcommonNewItemText.dataSucAdd;
				showConfirmModalNewItem();
			} else {
				$("#rqstType").val("brand");
				document.getElementById('confirmmessagecontNewItem').innerHTML = getcommonNewItemText.dataNotAdd;
				showConfirmModalNewItem();
			}

		});
	}
}
function openContentMod() {
	$('#content_name').val("");
	$('#alertMsgcontent').text("");
	$("#contentAddEditModal").modal("show");
}
function addEditContent() {
	document.getElementById('alertMsgcontent').innerHTML = '';
	var content_id = $("#content_id").val();
	//var content_code = $('#content_code').val();
	var content_name = $('#content_name').val();

	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

	var field_names = [ [ "content_name", name_field ] ];
	if (fieldValidationWithAlertDivId(field_names, "alertMsgcontent") > 0) {
	} else {
		$('#contentAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		// add Content
		var ContentMasterObj = {};
		//ContentMasterObj.code = content_code;
		ContentMasterObj.name = content_name;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/content/addContent.htm", ContentMasterObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			var status = JSON.parse(response);
			$("#rqstType").val("content");
			$("#objctId").val(status.id);
			$("#objctName").val(content_name);
			chngeResultStatForNewItem(status);

		});
	}
}

function openManufactMod(){
	$('#addrsmnuf').val("");
	$('#alertMsgmanu').text("");
	$("#manufacturerAddEditModal").find('input:text').val('');
	$("#manufacturerAddEditModal").find('input:hidden').val('');
	$("#manufacturerAddEditModal").modal("show");
}
function addEditManufacturer() {
	var manufctr_id = $("#manufctr_id").val();
	var manufctr_code = $('#manufctr_code').val();
	var manufctr_name = $('#manufctr_name').val();
	var addrs = $('#addrsmnuf').val();
	var phn = $('#phn').val();
	var fax = $('#fax').val();
	var email = $('#email').val();
	var url = $('#url').val();
	
	var code_label = $("#mfcode_label").text();
	var code_field = code_label.substring(0, code_label.lastIndexOf(" "));
	
	var name_label = $("#mfname_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var addrs_label = $("#mfaddrs_label").text();
	var addrs_field = addrs_label.substring(0, addrs_label.lastIndexOf(" "));
	var field_names = [["manufctr_code",code_field],["manufctr_name",name_field],["addrsmnuf",addrs_field]];
	if(fieldValidationWithAlertDivId(field_names,"alertMsgmanu")>0)
		{
			
		}
		else {
		$('#manufacturerAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		 // add Manufacturer
			var ManufacturerMasterObj = {};
			ManufacturerMasterObj.code = manufctr_code;
			ManufacturerMasterObj.name = manufctr_name;
			ManufacturerMasterObj.address = addrs;
			ManufacturerMasterObj.phone = phn;
			ManufacturerMasterObj.fax = fax;
			ManufacturerMasterObj.email = email;
			ManufacturerMasterObj.url = url;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/manufacturer/addManufacturer.htm", ManufacturerMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				$("#rqstType").val("manf");
				$("#objctId").val(status.id);
				$("#objctName").val(manufctr_name);
				chngeResultStatForNewItem(status);

			});
		}
}

function openTaxMod(taxType){
	document.getElementById('alertMsgTax').innerHTML = '';
	$("#taxAddEditModal").find('input:text').val('');
	$("#taxAddEditModal").find('input:hidden').val('');
	$("#singleTaxList").empty();
	$("#snglTaxDiv").addClass("hide");
	$("#taxIsGrp").val(0);
	
	$("#saveTaxBtn").removeClass("hide");
	$("#updateTaxBtn").addClass("hide");
	$("#taxheadertext").text(getTaxText.headerTextAdd);	
	$("#taxType").val(taxType);
	$("#taxAddEditModal").modal("show");
}

function makeGroupStat()
{
	var grpStat = $("#taxIsGrp").val();
	if(grpStat==1)
	{
		var taxId = $("#taxId").val();
		if (taxId != 0) {
			getTaxDetById(taxId);
		}
		else
		{
			$("#snglTaxDiv").removeClass("hide");
			var commonResultMap = {};
			commonResultMap.isGroup = 1;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/tax/getSingleTaxs.htm", commonResultMap, function(response) {
				//console.log(response);
				var singletaxlist = JSON.parse(response);
				for ( var i = 0; i < singletaxlist.length; i++) {
					var singletax = singletaxlist[i];
					var starttrline = "<tr id=" + singletax.taxId + " >";
					var chkbox = "<td><input id='" + singletax.taxId + "_modretcheck' class='chkboxcheked' type='checkbox' value='" + JSON.stringify(singletax) + "' ></td>";
					var taxname = "<td id='sngl_tax_name'>" + singletax.taxName + "</td>";
					var taxprcnt = "<td id='sngl_tax_prcnt'>" + singletax.percentage + "</td>";
					var taxdesc = "<td id='sngl_tax_desc' class='hide'>" + singletax.description + "</td>";
					var taxmode = "<td id='sngl_tax_mode' class='hide'>" + singletax.taxMode + "</td>";
					var endtrline = "</tr>";
					createdrowline = starttrline + chkbox + taxname + taxprcnt + taxdesc + taxmode + endtrline;
					$("#singleTaxList").append(createdrowline);
				}
			});
		}
	}
	else
	{
		$("#singleTaxList").empty();
		$("#snglTaxDiv").addClass("hide");
	}
}

function addEditTax()
{
	document.getElementById('alertMsgTax').innerHTML = '';
	var taxId = $("#taxId").val();
	
	var name_label = $("#taxName_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var prct_label = $("#taxPer_label").text();
	var prcnt_field = prct_label.substring(0, name_label.lastIndexOf(" "));
	
	var desc_label = $("#taxDesc_label").text();
	var desc_field = desc_label.substring(0, name_label.lastIndexOf(" "));
	
	var field_names = [["taxName",name_field],["taxDesc",desc_field]];
	
	if(fieldValidationWithAlertDivId(field_names,"alertMsgTax")>0)
		{
			
		}
		else 
		{		
			if($("#taxPer").val()=="")
			{
				document.getElementById('alertMsgTax').innerHTML = prcnt_field + " " + getFieldText.fieldReq;
			}
			else
			{
				var TaxMasterObj = {};
				TaxMasterObj.name = $("#taxName").val();
				TaxMasterObj.percentage=$("#taxPer").val();
				TaxMasterObj.description=$("#taxDesc").val();
				TaxMasterObj.taxMode=$("#taxMode").val();	
				var taxType = $("#taxType").val();
				var grpStat = $("#taxIsGrp").val();
				if(grpStat==1)
				{
					var grpPrcnt = 0.0;
					var count = 0;
					TaxMasterObj.isGroup=1;
					var allTaxGrpdetails = [];
					$('#singleTaxListTbl > tbody > tr').each(function() {
						var taxid = this.id;
							if ($("#" + taxid + "_modretcheck").is(":checked")) {
								count = count + 1;
								var taxGrpDetails = {};
								taxGrpDetails.taxId=taxid;	
								grpPrcnt = Number(grpPrcnt) + Number($("#"+taxid).find("#sngl_tax_prcnt").text());
								taxGrpDetails.percentage=$("#"+taxid).find("#sngl_tax_prcnt").text();
								taxGrpDetails.description=$("#"+taxid).find("#sngl_tax_desc").text();
								taxGrpDetails.taxMode=$("#"+taxid).find("#sngl_tax_mode").text();
								allTaxGrpdetails.push(taxGrpDetails);
							}
					});
					TaxMasterObj.taxGrpDetailsMasters=allTaxGrpdetails;
					
					if(count<2)
					{
						document.getElementById('alertMsgTax').innerHTML = getTaxText.notSlctAnyTaxErr;
						return false;
					}
					else
					{
						document.getElementById('alertMsgTax').innerHTML = "";
					}
					
					if(Number($("#taxPer").val())!=Number(grpPrcnt))
					{
						document.getElementById('alertMsgTax').innerHTML = getTaxText.prcntGtGroupedPrcntErr;
						return false;
					}
					else
					{
						document.getElementById('alertMsgTax').innerHTML = "";
					}
				}
				else
				{
					TaxMasterObj.isGroup=0;
				}
				$('#taxAddEditModal').modal('hide');
				if (taxId == 0) { // add brand		 * 
				$('#pleasewaitModal').modal('show');
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/tax/addtax.htm", TaxMasterObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						$("#rqstType").val("tax");
						$("#objctId").val(status.id);
						$("#objctName").val($("#taxName").val());
						$("#objctType").val(taxType);
						$("#taxPrcnt").val($("#taxPer").val());
						chngeResultStatForNewItem(status);
					});
				} else {// edit brand
					$('#pleasewaitModal').modal('show');
					TaxMasterObj.id = taxId;
		 
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/tax/updatetax.htm", TaxMasterObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						$("#rqstType").val("tax");
						$("#objctId").val(status.id);
						$("#objctName").val($("#taxName").val());
						$("#objctType").val(taxType);
						$("#taxPrcnt").val($("#taxPer").val());
						chngeResultStatForNewItem(status);
		
					});
		
				}
		}
	}
}

function openUnitMod(unittypeid){
	$('#unitTypeId').val(unittypeid);
	$('#alertMsgunit').text("");
	$("#unitCodeId").val("");
	$("#unitDescId").val("");
	$("#unitAddEditModal").modal("show");
}

function openUnitMod(unittypeid){
	$('#unitTypeId').val(unittypeid);
	$('#alertMsgunit').text("");
	$("#unitCodeId").val("");
	$("#unitDescId").val("");
	$("#unitAddEditModal").modal("show");
}

function addEditUnit() {
	var unit_id = $("#unit_id").val();
	var unitCode = $('#unitCodeId').val();
	var unitDesc = $('#unitDescId').val();
	var unitTypeId = $('#unitTypeId').val();

	var code_label = $("#unitcode_label").text();
	var code_field = code_label.substring(0, code_label.lastIndexOf(" "));
	
	var desc_label = $("#unitdesc_label").text();
	var desc_field = desc_label.substring(0, desc_label.lastIndexOf(" "));
	
	var type_label = $("#unittype_label").text();
	var type_field = type_label.substring(0, type_label.lastIndexOf(" "));
	
	var field_names = [["unitCodeId",code_field],["unitDescId",desc_field],["unitTypeId",type_field]];
	if(fieldValidationWithAlertDivId(field_names,"alertMsgunit")>0)
		{			
		}
	else {
		$('#unitAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		 // add unit
			var UnitMasterObj = {};
			UnitMasterObj.code = unitCode;
			UnitMasterObj.description = unitDesc;
			UnitMasterObj.typeId = unitTypeId;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addunit.htm", UnitMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				$("#rqstType").val("unit");
				$("#objctId").val(status.id);
				$("#objctName").val(unitCode);
				$("#objctType").val(unitTypeId);
				chngeResultStatForNewItem(status);

			});
		}
	}

function targetAction()
{
	var rqstType = $("#rqstType").val();
	var objctId = $("#objctId").val();
	var objctName = $("#objctName").val();
	var objctType = $("#objctType").val();
	
	if(rqstType == "unit")
	{
		if(objctId > 0)
		{
			if(objctType == "1")
			{
				$("#looseUnitId").val(objctId);
				$("#itemLooseUnit").val(objctName);
			}
			else
			{
				$("#packUnitId").val(objctId);
				$("#itemPackingUnit").val(objctName);
				$("#looseUnitId").val(objctId);
				$("#itemLooseUnit").val(objctName);
				$("#reorderLevelUnitId").val(objctId);
				$("#itemreorderUnitId").val(objctName);
			}
		}
		else
		{}
	}
	else if(rqstType == "manf")
	{
		if(objctId > 0)
		{
			$("#manufacturerId").val(objctId);
			$("#itemManufacturer").val(objctName);
		}
		else
		{}	
	}
	else if(rqstType == "content")
	{
		if(objctId > 0)
		{
			$("#content_id").val(objctId);
			$("#contentId").val(objctId);
			$("#content_Dets").val(objctName);
			$("#itemContent").val(objctName);
		}
		else
		{}	
	}
	else if(rqstType == "brand")
	{}
	else if(rqstType == "group")
	{		
		if(objctId > 0)
		{
			$("#grpSelect").append($('<option>', {
			    value: objctId,
			    text: objctName
			}));
			$("#grpSelect").val(objctId);
		}
		else
		{}
	}
	else if(rqstType == "schedule")
	{		
		if(objctId > 0)
		{
			$("#scheSelect").append($('<option>', {
			    value: objctId,
			    text: objctName
			}));
			$("#scheSelect").val(objctId);
		}
		else
		{}
	}
	else if(rqstType == "tax")
	{		
		if(objctId > 0)
		{
			var taxPrcnt = $("#taxPrcnt").val();
			if(objctType == "P")
			{
				$("#purchaseTaxId").val(objctId);
				$("#purchaseTax").val(objctName);
				$("#purchaseTaxPerc").val(taxPrcnt);
			}
			else
			{
				$("#saleTaxId").val(objctId);
				$("#saleTax").val(objctName);
				$("#saleTaxPerc").val(taxPrcnt);
			}
		}
		else
		{}
	}else if(rqstType == "Category")
	{		
		if(objctId > 0)
		{
			$("#catSelect").append($('<option>', {
			    value: objctId,
			    text: objctName
			}));
			$("#catSelect").val(objctId);
		}
		else
		{}
	}else if(rqstType == "Subcategory")
	{		
		if(objctId > 0)
		{
			$("#subCatSelect").append($('<option>', {
			    value: objctId,
			    text: objctName
			}));
			$("#subCatSelect").val(objctId);
		}
		else
		{}
	}
}


/*
//=========================add new item start =======================//
*/
function addNewItem() {
	
//	$("#itemMasterModalBody").find('input:text').val('');
	$("#itemMasterModalBody").find('input:hidden').val('');
	$("#isLooseSale").prop("checked",false);
	$("#rackSelect").val(0);
	if($("#itemid").val()!=0)
	{
		$("#alertMsgItem").text("");
		$("#itmnameexistdiv").hide();
		$("#itemaddbut").prop("disabled", false);
		$("#itemupdatebut").prop("disabled", false);

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/item/getitemdetailsmod/" + $("#itemid").val() + ".htm", function(resp) {
			//console.log(resp);
			var obj = jQuery.parseJSON(resp);
			//console.log(obj[0].packUnitId);
			setTimeout(function()
			{
				fillItemDetailsModal(jQuery.parseJSON(resp));
			},200);
		}, null);
		//$("#grpSelect").attr("disabled",false);
		//$("#scheSelect").attr("disabled",true);
		$("#itemBrand").attr("readonly",true);
		//$("#itemPackingUnit").attr("readonly",true);
		$("#itemCode").attr("readonly",true);
		//$("#itemContent").attr("readonly",true);
		//$("#itemManufacturer").attr("readonly",true);
		//$("#itemLooseUnit").attr("readonly",true);
		$("#itemreorderUnitId").attr("readonly",true);
		$("#itemheadertext").text(getcommonNewItemText.headerTextUpdate);
		$("#itemupdatebut").removeClass("hide");
		$("#itemaddbut").addClass("hide");
		//$(".noneditablediv").addClass("hide");
		//$(".editablediv").removeClass("hide");
		
	}
	else
	{
		
		//$("#itemMasterModalBody").find('input:text').val('');
		$("#itemMasterModalBody").find('input:hidden').val('');
		$("#rackSelect").val(0);
		$("#subCatSelect").val(0);
		$("#isDiscount").val(0);
		/*Sayantan Mahanty added date-20/02/2020*/
		/*$("#stockRequired").val(0);*/
		$("#stockRequired").val(1);
		/*$("#mrpRequired").val(0);*/
		$("#mrpRequired").val(1);
		/*$("#priceListRequired").val(0);*/
		$("#priceListRequired").val(1);
		/*$("#batchWiseStock").val(0);*/
		$("#batchWiseStock").val(1);
		/*$("#expiryDateRequired").val(0);*/
		$("#expiryDateRequired").val(1);
		$("#expiryMonthRequired").val(0);
		$("#dualStockRequired").val(0);
		$("#grpSelect").attr("disabled",false);
		$("#scheSelect").attr("disabled",false);
		$("#itemBrand").attr("readonly",false);
		$("#itemPackingUnit").attr("readonly",false);
		$("#itemCode").attr("readonly",false);
		$("#itemContent").attr("readonly",false);
		$("#itemManufacturer").attr("readonly",false);
		$("#itemLooseUnit").attr("readonly",false);
		$("#itemreorderUnitId").attr("readonly",false);
		$("#itemheadertext").text(getcommonNewItemText.headerTextAdd);
		$("#ratiodiv").addClass("hide");
		$("#lseUnitDiv").addClass("hide");
		$("#rateondiv").addClass("hide");
		$("#itemaddbut").removeClass("hide");
		$("#itemupdatebut").addClass("hide");
		//$(".noneditablediv").removeClass("hide");
		//$(".editablediv").addClass("hide");
		 
		 $("#discontinuedate").datepicker().datepicker("setDate", moment(new Date()).format('YYYY-MM-DD'));
		 $("#launchdate").datepicker().datepicker("setDate", moment(new Date()).format('YYYY-MM-DD'));
		 $("#itemconversion").val(1);

	}
	
	
	$('#itemmasterModal').modal('show');

}
/*
=========================add new item end  =======================
*/


/*
 =======================edit new item start  =====================
*/

function fillItemDetailsModal(itemdetval) {
	console.log("itemdetval ",itemdetval);
	//console.log(JSON.stringify(itemdetval));
	$("#itemName").val(itemdetval.name);
	$("#itemidparchase").val(itemdetval.id);
	$("#sku").val(itemdetval.sku);
	$("#grpSelect").val(itemdetval.groupId);
	$("#scheSelect").val(itemdetval.scheduleId);
	
	//$("#brandId").val(itemdetval.brandId);
	//$("#itemBrand").val(itemdetval.brandMaster.name);
	$("#itemPackingUnit").val(itemdetval.packUnit.code);
	$("#packUnitId").val(itemdetval.packUnitId);
	
	
	
	if(itemdetval.purchaseTaxId!=0)
	{
		$("#purchaseTax").val(itemdetval.purchaseTax.name);
		$("#purchaseTaxPerc").val(itemdetval.purchaseTaxPercentage);
		$("#purchaseTaxId").val(itemdetval.purchaseTaxId);		
	}
	else
	{
		$("#purchaseTax").val("");
		$("#purchaseTaxPerc").val("");
		$("#purchaseTaxId").val(0);
	}
	if(itemdetval.saleTaxId!=0)
	{
		$("#saleTax").val(itemdetval.saleTax.name);
		$("#saleTaxPerc").val(itemdetval.saleTaxPercentage);
		$("#saleTaxId").val(itemdetval.saleTaxId);	
	}
	else
	{
		$("#saleTax").val("");
		$("#saleTaxPerc").val("");
		$("#saleTaxId").val(0);	
	}
	
	$("#itemconversion").val(itemdetval.conversion);
	$("#rackSelect").val(itemdetval.rackId);
	$("#itemCode").val(itemdetval.code);
	$("#content_id").val(itemdetval.contentId);
	/*$("#itemContent").val(itemdetval.contentMaster.name);
	$("#content_Dets").val(itemdetval.contentMaster.name);*/
	 if(itemdetval.manufacturerMaster===null){
		 
    }else{
	$("#itemManufacturer").val(itemdetval.manufacturerMaster.name);
	$("#manufacturerId").val(itemdetval.manufacturerMaster.id);
    }
	$("#itemLooseUnit").val(itemdetval.looseUnit.code);
    $("#looseUnitId").val(itemdetval.looseUnitId);
	$("#netcontent").val(itemdetval.netContent);
	$("#itemRemarks").val(itemdetval.note);
	$("#itemMarkup").val(itemdetval.markup);
	$("#itemstrength").val(itemdetval.strength);
	$("#isDiscount").val(itemdetval.isDiscount);
	$("#maxDiscountLimit").val(itemdetval.maxDiscountLimit);
	$("#discountvalue").val(itemdetval.discount);
	$("#itemreorderlevel").val(itemdetval.reorderLevel);
	$("#itemreorderUnitId").val(itemdetval.reorderLevelUnit.code);
	$("#reorderLevelUnitId").val(itemdetval.reorderLevelUnitId);
	// new add
	
	$("#alltaxtypeexclusive").val(itemdetval.taxTypeId);
	// for mrp
	$("#itemPrice").val(itemdetval.mrp);
	$("#sale_rate_value").val(itemdetval.saleRate);
	$("#purchaserate").val(itemdetval.purchaseRate);
	$("#orderquanity").val(itemdetval.reorderLevelQty);

	$("#maximumstock").val(itemdetval.maximumQty);
	$("#hsnCode").val(itemdetval.hsnCode);
	$("#marketed_by_id").val(itemdetval.marketerId);
	
	 if(itemdetval.discontinueDate===null)
		{
		
		 $("#discontinuedate").datepicker().datepicker("setDate", moment(new Date()).format('YYYY-MM-DD'));
		 
		}
	else
		{
		 $("#discontinuedate").datepicker().datepicker("setDate", moment(itemdetval.discontinueDate).format('YYYY-MM-DD'));
		} 
	 
	 if(itemdetval.launchDate===null)
		{
		 
		 $("#launchdate").datepicker().datepicker("setDate", moment(new Date()).format('YYYY-MM-DD'));
		}
	else
		{
		 $("#launchdate").datepicker().datepicker("setDate", moment(itemdetval.launchDate).format('YYYY-MM-DD'));
		}

	 
	//$("#marketed_by_name").val(itemdetval.marketerMaster.name);
	
	$("#itemPrice").val(itemdetval.mrp);
	$("#sale_rate_value").val(itemdetval.saleRate);
	$("#purchaserate").val(itemdetval.purchaseRate);
	$("#hsnCode").val(itemdetval.hsnCode);
	$("#description").val(itemdetval.description);
	$("#printname").val(itemdetval.printName);
	$("#imgurl").val(itemdetval.imgUrl);
	$("#maxlevel").val(itemdetval.maxLevel);
	$("#minlevel").val(itemdetval.minLevel);
	$("#colouritemchk").val(itemdetval.colour);
	$("#sizetype").val(itemdetval.sizeType);
	$("#sizecommitm").val(itemdetval.size);
	$("#weight").val(itemdetval.weight);
	$("#authorName").val(itemdetval.authorName);
	$("#edition").val(itemdetval.edition);
	$("#listedMrp").val(itemdetval.listedMrp);
	$("#wsp").val(itemdetval.wsp);
	$("#stockRequired").val(itemdetval.stockRequired);
	$("#mrpRequired").val(itemdetval.mrpRequired);
	$("#serialNoRequired").val(itemdetval.serialNoRequired);
	$("#serialNoSuffRequiredcmnitm").val(itemdetval.serialNoSuffRequired);
	$("#serialNoPrefRequiredcmnitm").val(itemdetval.serialNoPrefRequired);
	$("#catSelect").val(itemdetval.categoryId);
	$("#itemMarkup").val(itemdetval.markup);
	$("#itemstrength").val(itemdetval.strength);
	$("#isDiscount").val(itemdetval.isDiscount);
	$("#maxDiscountLimit").val(itemdetval.maxDiscountLimit);
	$("#discountvalue").val(itemdetval.discount);
	$("#orderquanity").val(itemdetval.serialNoRequired);
	$("#maximumstock").val(itemdetval.maximumQty);
	$("#netcontent").val(itemdetval.netContent);
	$("#itemRemarks").val(itemdetval.note);
	$("#batchWiseStock").val(itemdetval.batchWiseStock);
	$("#dualStockRequired").val(itemdetval.dualStockRequired);
	$("#locationRequired").val(itemdetval.locationRequired);
	$("#priceListRequired").val(itemdetval.priceListRequired);
	$("#sizeWiseStockRequired").val(itemdetval.sizeWiseStockRequired);
	$("#warrantyRequired").val(itemdetval.warrantyRequired);
	$("#colourWiseStockRequired").val(itemdetval.colourWiseStockRequired);
	$("#warrantyMonthcmnitm").val(itemdetval.warrantyMonth);
	$("#expiryDateRequired").val(itemdetval.expiryDateRequired);
	$("#expiryMonthRequired").val(itemdetval.expiryMonthRequired);
	//$("#lseSaleStat").val(itemdetval.isLooseSale);
	//***************
	$("#serialnumberreq").val(itemdetval.serialNoRequired);
	//*********
	// for is discount 
	if(itemdetval.isDiscount==1)
		{
		isDiscountWhenEdit(1);
		}
	else
		{
		isDiscountWhenEdit(0);
		}
	
	/*
	 * taking date 
	 * */

	
	/*
	 * for discontinue date 
	 */
 
	 
	// for loose sale 
	
	/*if(itemdetval.isLooseSale==1)
	{
		$("#isLooseSale").prop("checked",true);
		$("#lseUnitDiv").removeClass("hide");
	}
	else
	{
		$("#isLooseSale").prop("checked",false);
		$("#lseUnitDiv").addClass("hide");
	}*/
	
	dualStkReq();
	getBatWiseStk();
}

/*
 =======================edit new item end  =====================
*/


function fillItemDtlsDivFrmModal(itemObject) {
	console.log("itemObject="+JSON.stringify(itemObject));
	//("#freeCheck").attr("checked",false);
	//$("#rate").attr("readonly",false);
	$("#item_name").val(itemObject.name);
	//$("#batch_no").val(0);
	//$("#exp").val();
	/*$("#pqty").val(0);
	$("#lqty").val(0);
	$("#free").val(0);
	$("#rate").val(0.0);
	$("#rateNonFree").val(0.0);*/
	
	var taxval = ((Number($("#rate").val()) * (Number($("#pqty").val())+Number($("#free").val()))) - Number($("#disc").val())) * Number(itemObject.purchaseTaxPercentage) / 100;
	$("#tax").val(parseFloat(taxval).toFixed(4));
	
	//$("#total").val(0);
	$("#edpercnt").val(0.0);
	$("#ed").val(0.0);
	$("#purbarcode").val(itemObject.sku);
	$("#vatprcnt").val(0.0);
	$("#vat").val(0.0);
	/*$("#dprcnt").val(0.0);
	$("#disc").val(0.0);*/
	$("#punitid").val(itemObject.packUnitId);
	$("#ratio").val(itemObject.conversion);
	$("#mrp").val(itemObject.price);
	$("#ma").val(itemObject.markup);
	$("#grp").val(itemObject.groupMaster.name);
	$("#grpid").val(itemObject.groupId);
	$("#sch").val(itemObject.scheduleMaster.name);
	$("#schid").val(itemObject.scheduleId);
	$("#mfg").val(itemObject.manufacturerMaster.name);
	$("#mfgid").val(itemObject.manufacturerId);
	$("#taxprcnt").val(itemObject.purchaseTaxPercentage);
	$("#purTaxId").val(itemObject.purchaseTaxId);
	$("#purtaxmode").val(itemObject.purchaseTax.taxMode);
	$("#purisgrptax").val(itemObject.purchaseTax.isGroup);
	$("#itemid").val(itemObject.id);
	$("#purHsnCode").val(itemObject.hsnCode);
	
	if(itemObject.batchWiseStock==null||itemObject.batchWiseStock==0){
		$("#batch_no_td").addClass('hide');
		$("#batch_label").addClass('hide');
		$("#batch_no").val(0);
	}else if(itemObject.batchWiseStock==1){
		$("#batch_no_td").removeClass('hide');
		$("#batch_label").removeClass('hide');
		$("#batch_no").val(0);
	}else{
		$("#batch_no_td").removeClass('hide');
		$("#batch_label").removeClass('hide');
		$("#batch_no").val(0);
	}
	if(itemObject.expiryDateRequired==null||itemObject.expiryDateRequired==0){
		$("#exp_td").addClass('hide');
		$("#exp_label").addClass('hide');
		$("#exp_month_label").addClass('hide');
		$("#exp").val();
	}else if(itemObject.expiryDateRequired==1){
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
		$("#exp_month_label").removeClass('hide');
		$("#exp").val();
	}else{
		$("#exp_td").removeClass('hide');
		$("#exp_label").removeClass('hide');
		$("#exp_month_label").removeClass('hide');
		$("#exp").val();
	}
	if(itemObject.expiryMonthRequired==null||itemObject.expiryMonthRequired==0){
		$("#exp_month_label").addClass('hide');
		$("#expmonth_val").addClass('hide');
		$("#expmonth").val(0);
		$("#mfd_label").addClass('hide');
		$("#mfd_val").addClass('hide');
	}else if(itemObject.expiryMonthRequired==1){
		$("#exp_month_label").removeClass('hide');
		$("#expmonth_val").removeClass('hide');
		$("#expmonth").val(0);
		$("#mfd_label").removeClass('hide');
		$("#mfd_val").removeClass('hide');
	}else{
		$("#exp_month_label").removeClass('hide');
		$("#expmonth_val").removeClass('hide');
		$("#expmonth").val(0);
		$("#mfd_label").removeClass('hide');
		$("#mfd_val").removeClass('hide');
	}
	
	if(itemObject.dualStockRequired==null||itemObject.dualStockRequired==0){
		$("#ratio_val").addClass('hide');
		$("#ratio_label").addClass('hide');
		$("#ratio").val(itemObject.conversion);
	}else if(itemObject.dualStockRequired==1){
		$("#ratio_val").removeClass('hide');
		$("#ratio_label").removeClass('hide');
		$("#ratio").val(itemObject.conversion);
	}else{
		$("#ratio_val").removeClass('hide');
		$("#ratio_label").removeClass('hide');
		$("#ratio").val(itemObject.conversion);
	}
	
	
	if(itemObject.locationRequired==null||itemObject.locationRequired==0){
		var seslocid=$("#seslocationid").val();
//		alert(seslocid);
		$("#location_label").addClass('hide');
		$("#locationval_td").addClass('hide');
//		$("#location").val(seslocid);
		$("#location > [value=" + seslocid + "]").attr("selected", "true");
	}else if(itemObject.locationRequired==1){
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
	
	if(itemObject.priceListRequired==null||itemObject.priceListRequired==0){
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
		$("#sale_rate_val").addClass('hide');
		$("#salerate_label").addClass('hide');
		$("#mrp").val();
	}else if(itemObject.priceListRequired==1){
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
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

	if(itemObject.mrpRequired==null||itemObject.mrpRequired==0){
		$("#mrp_td").addClass('hide');
		$("#mrp_label").addClass('hide');
		$("#mrp").val();
	}else if(itemObject.mrpRequired==1){
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
		$("#mrp").val();
	}else{
		$("#mrp_td").removeClass('hide');
		$("#mrp_label").removeClass('hide');
		$("#mrp").val();
	}
	
	var today = new Date();
	var yyyy = today.getFullYear().toString();
	var mm = (today.getMonth() + 1).toString(); // getMonth() is zero-based
	var dd = today.getDate().toString();
	$("#mfd").val(yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]));

	$("#serialnumberreq").val(itemObject.serialNoRequired);
	$("#item_expiryMonthRequired_req").val(itemObject.expiryMonthRequired);  
	$("#item_expiryDateRequired_req").val(itemObject.expiryDateRequired);   
	//alert("sl no="+$("#serialnumberreq").val());
}
	/*
	
open marketer modal 
	*/
	function openAddEditMarketer()
	{
		
		
		 
		$('#marketer_addrs').val("");
		$('#alertMsgMarketer').text("");
		$("#marketAddEditModal").find('input:text').val('');
		$("#marketAddEditModal").find('input:hidden').val('');
	 
		$("#headertextmarketer").text(getcommonNewItemText.MarketerheaderTextAdd);
		$("#marketAddEditModal").modal("show");
		
	 
	}
	/*
	 add /edit maketer code 
	*/
	// for add and edit operation of markter
	function addEditMarketer() {
		var marketer_id = $('#marketer_id').val();
		var marketer_code = $('#marketer_code').val();
		var marketer_name = $('#marketer_name').val();
		var addrs = $('#marketer_addrs').val();
		var phn = $('#marketer_phn').val();
		var fax = $('#marketer_fax').val();
		var email = $('#marketer_email').val();
		var url = $('#marketer_url').val();
		var code_label = $("#marketercode_label").text();
		var code_field = code_label.substring(0, code_label.lastIndexOf(" "));
		var name_label = $("#marketername_label").text();
		var name_field ="marketer_name";
		var field_names = [["marketer_code",'Marketer Code'],["marketer_name",'Marketer Name']];
		if(fieldValidationWithAlertDivId(field_names,"alertMsgMarketer")>0)
		 {
			
		 }
		else
			{
			 $('#marketAddEditModal').modal('hide');
			 $('#pleasewaitModal').modal('show');
						var MarketerMasterObj = {};
						MarketerMasterObj.code = marketer_code;
						MarketerMasterObj.name = marketer_name;
						MarketerMasterObj.address = addrs;
						MarketerMasterObj.phone = phn;
						MarketerMasterObj.fax = fax;
						MarketerMasterObj.email = email;
						MarketerMasterObj.url = url;
						// alert(JSON.stringify(MarketerMasterObj)+" nnn"+0);
						var ajaxCallObject = new CustomBrowserXMLObject();
						ajaxCallObject.callAjaxPost(BASE_URL + "/marketer/addMarketer.htm",MarketerMasterObj, function(response) {
									$('#pleasewaitModal').modal('hide');
								  var status = JSON.parse(response);
								 $('#marketed_by_id').val(status.id);
								  $('#marketed_by_name').val(marketer_name);
								 chngeResultStatForNewItem(status);
								});
					 
			}
	}
	
	function dualStkReq(){
		var dualStockReq=$("#dualStockRequired").val();
		if(dualStockReq==1){
			$("#lseUnitDiv").removeClass("hide");
			$("#rateondiv").removeClass("hide");
			$("#ratiodiv").removeClass("hide");
		}else{
				$("#itemconversion").val(1);
			$("#lseUnitDiv").addClass("hide");
			$("#rateondiv").addClass("hide");
			$("#ratiodiv").addClass("hide");
		}
	}

	function getMrp(){
		var itemPrice=$("#itemPrice").val();
		$("#listedMrp").val(itemPrice);
	}
	function getSelSerialNo(){
		var slno=$("#serialNoRequired").val();
		if(slno==1){
			$("#slnosuffreq").removeClass("hide");
			$("#slNoPrefReq").removeClass("hide");
			$("#slnotypeDiv").removeClass("hide");
		}else{
			$("#slnosuffreq").addClass("hide");
			$("#slNoPrefReq").addClass("hide");
			$("#slnotypeDiv").addClass("hide");
		}
	}
	
	function getWarrantyReq(){
		var warrno=$("#warrantyRequired").val();
		if(warrno==1){
			$("#warrantymonthondiv").removeClass("hide");
			$("#warrantytypeondiv").removeClass("hide");
		}else{
			$("#warrantymonthondiv").addClass("hide");
			$("#warrantytypeondiv").addClass("hide");
		}
	}
	
	function getBatWiseStk(){
		var batwistk=$("#batchWiseStock").val();
		if(batwistk==1){
			$("#expreqdiv").removeClass("hide");
			$("#expmonthreqdiv").removeClass("hide");
		}else{
			$("#expiryDateRequired").val(0);
			$("#expiryMonthRequired").val(0);
			$("#expreqdiv").addClass("hide");
			$("#expmonthreqdiv").addClass("hide");
		}
	}
	
	function getSelectedCat(){
		var selcatid=$("#catSelect").val();
		var commResultsetMapperObj = {};
		commResultsetMapperObj.catId = selcatid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getCatBySubcat.htm", commResultsetMapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			var res = JSON.parse(response);
			var str = "<option value='0'>Select....</option>";
			$.each(res, function(i) {
				str = str + "<option value='" + res[i].subCategoryId + "'>"
						+ res[i].subCategoryName + "</option>";
			});
			$("#subCatSelect").html(str);

		});
	}
	
	function getMrp(){
		/*alert("USED");*/
		var itemPrice=$("#itemPrice").val();
		$("#listedMrp").val(itemPrice);
		
		
		/*new added 3.7.2019*/
		if(itemPrice == ""){itemPrice = 0.0;}
		var taxType = $("#alltaxtypeexclusive").val();
		var purchaseTaxPerc = $("#purchaseTaxPerc").val();
		var retailerProfitPrcnt = $("#retailerProfitPrcnt").val();
		var wholesalerProfitPrcnt = $("#wholesalerProfitPrcnt").val();
		var purchaseRate="";
		if(purchaseTaxPerc !=""){
			if(taxType == 2){/*exclusive*/
				var taxAmt = (Number(itemPrice) * Number(purchaseTaxPerc))/100;
				var actualAmt = Number(itemPrice) - Number(taxAmt);
				$("#sale_rate_value").val(parseFloat(actualAmt).toFixed(2));
					   if(isWholeSale == 0){
						     purchaseRate =Number(actualAmt) - ((Number(actualAmt)*Number(retailerProfitPrcnt))/100);
						    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
					   }else{
						    purchaseRate =Number(actualAmt) - ((Number(actualAmt)*Number(wholesalerProfitPrcnt))/100);
						    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
					   }
			 }else{/*inclusive*/
				  $("#sale_rate_value").val(parseFloat(itemPrice).toFixed(2));
					  if(isWholeSale == 0){
						     purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(retailerProfitPrcnt))/100);
						    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
					   }else{
						    purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(wholesalerProfitPrcnt))/100);
						    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
					   }
			 }
		}else{
			$("#sale_rate_value").val(parseFloat(itemPrice).toFixed(2));
			if(isWholeSale == 0){
			     purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(retailerProfitPrcnt))/100);
			    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
		    }else{
			    purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(wholesalerProfitPrcnt))/100);
			    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
		   }
		}
	}
// category add
	
	function openAddCatModal() {
		document.getElementById('alertMsgCat').innerHTML='';
		$('#catAddEditModal').modal('show');
	}

	function addEditCat()
	{
		document.getElementById('alertMsgCat').innerHTML='';
		var catName=$('#CatName').val();
		var id=$('#CatId').val();
		var catname_label = $("#catname_label").text();
		var CatName_field = catname_label.substring(0, catname_label.lastIndexOf(" "));
		var field_names = [["CatName",CatName_field]];
		if(fieldValidationWithAlertDivId(field_names,"alertMsgCat")>0)
			{			
			}
		else
			{
			$('#catAddEditModal').modal('hide');
			$('#pleasewaitModal').modal('show');
				var d = new Date();
				var CategoryMasterObj = {};
				CategoryMasterObj.name = catName;
				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addcat.htm", CategoryMasterObj, function(response) {
					$('#pleasewaitModal').modal('hide');
					var status = JSON.parse(response);
					$("#rqstType").val("Category");
					$("#objctId").val(status.id);
					$("#objctName").val(catName);
					chngeResultStatForNewItem(status);
					/*if (response == 'success') {
						document.getElementById('confirmmessagecont').innerHTML=getCategoryText.dataSucAdd;
						showConfirmModal();
					}
					else{
						document.getElementById('confirmmessagecont').innerHTML=getCategoryText.dataNotAdd;
						showConfirmModal();
					}*/
				});
			
		}
	}
	
	// sub category add
	
	function openSubCatModal() {
		document.getElementById('alertMsgSubCat').innerHTML='';
		var CategoryMasterObj = {};
		CategoryMasterObj.categoryId = 1;
		
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getallcatjson.htm", CategoryMasterObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			console.log("response="+response);
			var status = JSON.parse(response);
			console.log("status="+status);
			var res = JSON.parse(response);
			var str = "<option value='0'>Select....</option>";
			$.each(res, function(i) {
				str = str + "<option value='" + res[i].id + "'>"
						+ res[i].name + "</option>";
			});
			$("#catNameList").html(str);
			/*$("#rqstType").val("Subcategory");
			$("#objctId").val(status.id);
			$("#objctName").val(subCatName);
			chngeResultStatForNewItem(status);*/
			
		});
		$('#subcatAddEditModal').modal('show');
	}

	function addEditSubCat()
	{
		document.getElementById('alertMsgSubCat').innerHTML='';
		var subCatName=$('#SubCatName').val();
		var subCatId=$('#SubCatId').val();
		var CatId=$('#catNameList').val();
		if(CatId==0)
		{
			document.getElementById('alertMsgSubCat').innerHTML=getFieldText.catBlankError;
		}
		else
			{
				var name_label = $("#subCatname_label").text();
				var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
			
				var field_names = [["SubCatName",name_field]];
				if(fieldValidationWithAlertDivId(field_names,"alertMsgSubCat")>0)
					{			
					}
				else
					{
					$('#subcatAddEditModal').modal('hide');
					$('#pleasewaitModal').modal('show');
						var d = new Date();
						//var CatId=$('#catNameList').val();
						var SubCategoryMasterObj = {};
						SubCategoryMasterObj.name = subCatName;
						SubCategoryMasterObj.categoryId = CatId;
						
						var ajaxCallObject = new CustomBrowserXMLObject();
						ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addsubcat.htm", SubCategoryMasterObj, function(response) {
							$('#pleasewaitModal').modal('hide');
							var status = JSON.parse(response);
							$("#rqstType").val("Subcategory");
							$("#objctId").val(status.id);
							$("#objctName").val(subCatName);
							chngeResultStatForNewItem(status);
							/*if (response == 'success') {
								document.getElementById('confirmmessagecont').innerHTML=getSubCategoryText.dataSucAdd;
								showConfirmModal();
							}
							else{
								document.getElementById('confirmmessagecont').innerHTML=getSubCategoryText.dataNotAdd;
								showConfirmModal();
							}*/
						});
					
					
				}
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
	
	
	// new added 3.7.2019
	
	function calculateRates(){/*new added 3.7.2019*/
		 var itemPrice=$("#itemPrice").val();
		 var saleRate=$("#sale_rate_value").val();
		 var taxType = $("#alltaxtypeexclusive").val();
		 var purchaseTaxPerc = $("#purchaseTaxPerc").val();
		 var retailerProfitPrcnt = $("#retailerProfitPrcnt").val();
		 var wholesalerProfitPrcnt = $("#wholesalerProfitPrcnt").val();
		 var purchaseRate="";
		 if(itemPrice == ""){itemPrice = 0.0;}
	     if(purchaseTaxPerc !=""){
					if(taxType == 2){/*exclusive*/
						var taxAmt = (Number(itemPrice) * Number(purchaseTaxPerc))/100;
						var actualAmt = Number(itemPrice) - Number(taxAmt);
						$("#sale_rate_value").val(parseFloat(actualAmt).toFixed(2));
						   
						   if(isWholeSale == 0){
							     purchaseRate =Number(actualAmt) - ((Number(actualAmt)*Number(retailerProfitPrcnt))/100);
							    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
						   }else{
							    purchaseRate =Number(actualAmt) - ((Number(actualAmt)*Number(wholesalerProfitPrcnt))/100);
							    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
						   }
					 }else{/*inclusive*/
						  $("#sale_rate_value").val(parseFloat(itemPrice).toFixed(2));
						  /*$("#purchaserate").val(purchaseRate);*/
						  if(isWholeSale == 0){
							     purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(retailerProfitPrcnt))/100);
							    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
						   }else{
							    purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(wholesalerProfitPrcnt))/100);
							    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
						   }
					 }
				}else{
					$("#sale_rate_value").val(parseFloat(itemPrice).toFixed(2));
					if(isWholeSale == 0){
					     purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(retailerProfitPrcnt))/100);
					    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
				    }else{
					    purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(wholesalerProfitPrcnt))/100);
					    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
				   }
				}
	 }

	function calculateSaleRate(){/*new added 3.7.2019*/
		
		 /*var itemPrice=$("#itemPrice").val();*/
		 var itemPrice=$("#sale_rate_value").val();
		 var taxType = $("#alltaxtypeexclusive").val();
		 var purchaseTaxPerc = $("#purchaseTaxPerc").val();
		 var retailerProfitPrcnt = $("#retailerProfitPrcnt").val();
		 var wholesalerProfitPrcnt = $("#wholesalerProfitPrcnt").val();
		 var purchaseRate="";
		 if(itemPrice == ""){itemPrice = 0.0;}
	    if(purchaseTaxPerc !=""){
					if(taxType == 2){/*exclusive*/
						var taxAmt = (Number(itemPrice) * Number(purchaseTaxPerc))/100;
						var actualAmt = Number(itemPrice) + Number(taxAmt);
						$("#itemPrice").val(parseFloat(actualAmt).toFixed(2));
						   
						   if(isWholeSale == 0){
							     purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(retailerProfitPrcnt))/100);
							    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
						   }else{
							    purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(wholesalerProfitPrcnt))/100);
							    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
						   }
					 }else{/*inclusive*/
						  $("#itemPrice").val(parseFloat(itemPrice).toFixed(2));
						  /*$("#purchaserate").val(purchaseRate);*/
						  if(isWholeSale == 0){
							     purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(retailerProfitPrcnt))/100);
							    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
						   }else{
							    purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(wholesalerProfitPrcnt))/100);
							    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
						   }
					 }
				}else{
					$("#itemPrice").val(parseFloat(itemPrice).toFixed(2));
					if(isWholeSale == 0){
					     purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(retailerProfitPrcnt))/100);
					    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
				    }else{
					    purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(wholesalerProfitPrcnt))/100);
					    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
				   }
				}
		
		
	}

