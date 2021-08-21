$(document).ready(function() {
	var rackid=$("#edited_rackId").val();
	console.log("rackid="+rackid);
	if(rackid==''||rackid==undefined){
		$("#rackSelect").val(0);
	}else{
		$("#rackSelect").val($("#edited_rackId").val());
	}
	var mrp=$("#edited_isonmrpId").val();
	if(mrp==''||mrp==undefined){
		$("#isonmrp").val(0);
	}else{
		$("#isonmrp").val(mrp);
	}
	
	$("#searchBtn").click(function(event){
		/*if($(".cmn").not(".hide" ).val()=="" || $(".cmn").not(".hide" ).val()==0)	
			{
				event.preventDefault();
			}
		else
			{*/
				$("#searchForm").submit();
			/*}*/
	});
	
	if($("#criteriaName").val()!="")
	{
		$("#searchBySelect").val($("#criteriaName").val());
		if($("#searchBySelect").val()=="name")
			{
				$("#itemName1").removeClass("hide");
				$("#contentName1").addClass("hide");
				$("#manufacturerName1").addClass("hide");
				$("#contentName1").attr("value","");
				$("#manufacturerName1").attr("value","");
			}
		else if($("#searchBySelect").val()=="content")
			{
				$("#contentName1").removeClass("hide");
				$("#itemName1").addClass("hide");
				$("#manufacturerName1").addClass("hide");
				$("#itemName1").attr("value","");
				$("#manufacturerName1").attr("value","");
			}
		else
			{
				$("#manufacturerName1").removeClass("hide");
				$("#contentName1").addClass("hide");
				$("#itemName1").addClass("hide");
				$("#contentName1").attr("value","");
				$("#itemName1").attr("value","");
			}
		}
	else{}
	$("#searchBySelect").change(function(){
		if($(this).val()=="name")
			{				
				$("#itemName1").removeClass("hide");
				$("#contentName1").addClass("hide");
				$("#manufacturerName1").addClass("hide");
				$("#contentName1").attr("value","");
				$("#manufacturerName1").attr("value","");
			}
		else if($(this).val()=="content")
		{			
			$("#contentName1").removeClass("hide");
			$("#itemName1").addClass("hide");
			$("#manufacturerName1").addClass("hide");
			$("#itemName1").attr("value","");
			$("#manufacturerName1").attr("value","");
		}
		else
		{			
			$("#manufacturerName1").removeClass("hide");
			$("#contentName1").addClass("hide");
			$("#itemName1").addClass("hide");
			$("#contentName1").attr("value","");
			$("#itemName1").attr("value","");
		}
	});
		
});
function checkAddEditDeleteResult()
{
	var status = $("#result").val();
	var add_edit_delete = $("#add_edit_delete").val();
	if(status!="")
	{
		if (status > 0) {
			document.getElementById('confirmmessagecont').innerHTML = getFieldText.greaterthanzero;
			showConfirmModal();
		} else if (status == 0) {
			document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
			showConfirmModal();
		} else if (status == -1) {
			document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusone;
			showConfirmModal();
		} else if (status == -2) {
			document.getElementById('confirmmessagecont').innerHTML = getFieldText.minustwo;
			showConfirmModal();
		} else if (status == -3) {
			document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusthree;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
			showConfirmModal();
		}
		/*if(result.id > 0)
			{
				if(add_edit_delete=="add")
					{
					document.getElementById('confirmmessagecont').innerHTML = getItemText.dataSucAdd;
					showConfirmModal();
					}
				else if(add_edit_delete=="edit")
					{
					document.getElementById('confirmmessagecont').innerHTML = getItemText.dataSucUpdate;
					showConfirmModal();
					}
				else if(add_edit_delete=="delete")
					{
					document.getElementById('confirmmessagecont').innerHTML = getItemText.dataSucDelete;
					showConfirmModal();
					}
				else{}
			}
		else if(result=="failure")
		{
			if(add_edit_delete=="add")
				{
				document.getElementById('confirmmessagecont').innerHTML = getItemText.dataNotAdd;
				showConfirmModal();
				}
			else if(add_edit_delete=="edit")
				{
				document.getElementById('confirmmessagecont').innerHTML = getItemText.dataNotUpdate;
				showConfirmModal();
				}
			else if(add_edit_delete=="delete")
				{
				document.getElementById('confirmmessagecont').innerHTML = getItemText.dataNotDelete;
				showConfirmModal();
				}
			else{}
		}
		else{}*/
	
	}
	else{}
}


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
	
	var field_names = [["taxName",name_field],["taxPer",prcnt_field],["taxDesc",desc_field]];
	
	if(fieldValidationWithAlertDivId(field_names,"alertMsgTax")>0)
		{
			
		}
		else {		
		
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

function openAddEditModal(id) {
	document.getElementById('alertMsg').innerHTML = '';
	if(id==0){
		location.href = BASE_URL + '/item/loaditemmst/0.htm';
	}else{
		location.href = BASE_URL + '/item/loaditemmst/'+id+'.htm';
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
	$('#alertMsgmanu').text("");
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

function openBrandMod() {
	$('#brandName').val("");
	$('#alertMsgbrand').text("");
	$("#brandAddEditModal").modal("show");
}
function addEditBrand() {
	$('#alertMsgbrand').text("");
	var brandName = $('#brandName').val();
	var name_label = $("#name_label").text();
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


/*
 * this is for open unit model 
 * */
function openUnitMod(unittypeid){
	$('#unitTypeId').val(unittypeid);
	$('#alertMsgunit').text("");
	$("#unitCodeId").val("");
	$("#unitDescId").val("");
	$("#unitAddEditModal").modal("show");
}

function addEditUnit() {
	$('#alertMsgunit').text("");
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
	window.location.replace(BASE_URL + '/pages/inv/item.jsp');
	location.href = BASE_URL + '/item/loaditem.htm';
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
	}
	else if(rqstType == "Category")
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

function checkSameItem(itemName,itemId) {
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

function contentDetailsMod(){
	if($("#content_id").val()!=0){
	$('#contentDetailsModal').modal('show');
	$("#headertext").text("Details");
	$("#contentVal").text($("#content_Dets").val());
	}
}

$("#itemvat").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
});

$("#itemreorderlevel").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
});

$("#itemPrice").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
});

$("#itemconversion").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
});

$("#itemMarkup").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
});

$("#maxDiscountLimit").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{		
		if(Number($("#discount").val())>Number($("#maxDiscountLimit").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.discGrtMaxDiscErr;
			$(this).focus();
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	}
});

$("#discount").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{		
		if(Number($("#discount").val())>Number($("#maxDiscountLimit").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.discGrtMaxDiscErr;
			$(this).focus();
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	}
});


/*
 * add on 3_11_2017 for number check
 * 
 * */

$("#sale_rate").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
});


$("#purchaserate").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
});

$("#orderquanity").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
});


$("#maximumstock").keyup(function(){
	if(isNaN($(this).val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
			$(this).focus();
		}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
});
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
			$("#sale_rate").val(parseFloat(actualAmt).toFixed(2));
				   if(isWholeSale == 0){
					     purchaseRate =Number(actualAmt) - ((Number(actualAmt)*Number(retailerProfitPrcnt))/100);
					    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
				   }else{
					    purchaseRate =Number(actualAmt) - ((Number(actualAmt)*Number(wholesalerProfitPrcnt))/100);
					    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
				   }
		 }else{/*inclusive*/
			  $("#sale_rate").val(parseFloat(itemPrice).toFixed(2));
				  if(isWholeSale == 0){
					     purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(retailerProfitPrcnt))/100);
					    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
				   }else{
					    purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(wholesalerProfitPrcnt))/100);
					    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
				   }
		 }
	}else{
		$("#sale_rate").val(parseFloat(itemPrice).toFixed(2));
		if(isWholeSale == 0){
		     purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(retailerProfitPrcnt))/100);
		    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
	    }else{
		    purchaseRate =Number(itemPrice) - ((Number(itemPrice)*Number(wholesalerProfitPrcnt))/100);
		    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
	   }
	}
	
}


function calculateRates(){/*new added 3.7.2019*/
	 var itemPrice=$("#itemPrice").val();
	 var saleRate=$("#sale_rate").val();
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
					$("#sale_rate").val(parseFloat(actualAmt).toFixed(2));
					   
					   if(isWholeSale == 0){
						     purchaseRate =Number(actualAmt) - ((Number(actualAmt)*Number(retailerProfitPrcnt))/100);
						    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
					   }else{
						    purchaseRate =Number(actualAmt) - ((Number(actualAmt)*Number(wholesalerProfitPrcnt))/100);
						    $("#purchaserate").val(parseFloat(purchaseRate).toFixed(2));
					   }
				 }else{/*inclusive*/
					  $("#sale_rate").val(parseFloat(itemPrice).toFixed(2));
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
				$("#sale_rate").val(parseFloat(itemPrice).toFixed(2));
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
	 var itemPrice=$("#sale_rate").val();
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

/*function lseSaleChkBox()
{
	if ($('input[name=lseSaleChk]').is(":checked"))
	{
		$("#isLooseSale").val(1);
		$("#lseUnitDiv").removeClass("hide");
	}
	else
	{
		$("#isLooseSale").val(0);
		$("#lseUnitDiv").addClass("hide");
	}
}*/
/*
 * 
 *  for add new item setup 
 * */
function clicksub()
{	
	
	if(isNaN($("#itemreorderlevel").val())  || isNaN($("#itemconversion").val()) ||isNaN($("#sale_rate").val())||isNaN($("#purchaserate").val())||isNaN($("#maximumstock").val()))
	{
		$("#itemaddbut").attr("disable",true);	
		 
	}
	 else
	{
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
		var color_field = color_label.substring(0, color_label.lastIndexOf(" "));
		color_field="Colour";
		var size_label = $("#size_label").text();
		var size_field = size_label.substring(0, size_label.lastIndexOf(" "));
		size_field="Size";
		
	    var hsn_label = $("#hsn_label").text();
		var hsn_field = hsn_label.substring(0, hsn_label.lastIndexOf(" "));
		
		
		var isDiscount=$("#isDiscount").val();
		if(isDiscount==0)
			{
			$("#maxDiscountLimit").val(100);
			$("#discount").val(0);
			}
		var colourWiseStockRequired=$("#colourWiseStockRequired").val();
		var sizeWiseStockRequired=$("#sizeWiseStockRequired").val();
		var field_names="";
		if(companyId == 18){
			if(colourWiseStockRequired==1 && sizeWiseStockRequired==1){
				 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["colour",color_field],["size",size_field],["hsnCode",hsn_field]];
			}else if(colourWiseStockRequired==1){
				 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["colour",color_field],["hsnCode",hsn_field]];
			}else if(sizeWiseStockRequired==1){
				 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["size",size_field],["hsnCode",hsn_field]];
			}else{
				 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["hsnCode",hsn_field]];
			}
			
		}else{
			if(colourWiseStockRequired==1 && sizeWiseStockRequired==1){
				 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["colour",color_field],["size",size_field]];
			}else if(colourWiseStockRequired==1){
				 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["colour",color_field]];
			}else if(sizeWiseStockRequired==1){
				 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["size",size_field]];
			}else{
				 field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field]];
			}
		}
		
//		var field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemPrice",price_field],["itemContent",cntnt_field],["itemManufacturer",mnfctr_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field],["marketed_by_name",marketer_by_field]];
		
		//var field_names = [["itemName",name_field],["itemPackingUnit",pckUnit_field],["itemPrice",price_field],["itemconversion",cnvrsn_field],["purchaseTax",purchasetax_field],["saleTax",saletax_field]];
		//$("#item_form").submit();
		if(fieldValidation(field_names)>0)
		{	
		 
			
		}
		else
		{
			if(Number($("#discount").val())>Number($("#maxDiscountLimit").val()))
			{
				document.getElementById('alertMsg').innerHTML = getFieldText.discGrtMaxDiscErr;
				$(this).focus();
				return false;
			}else{
				if($("#maxDiscountLimit").val()==0 || $("#maxDiscountLimit").val()==0.0)
				{
					$("#maxDiscountLimit").val(100);
				}
				document.getElementById('alertMsg').innerHTML = "";
				$("#item_form").submit();
			}
			
			
			
			
			
			/*if(isNaN($("#maxDiscountLimit").val()))
				{
					document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
					$(this).focus();
					return false;
				}
			else
			{		
				if(Number($("#discount").val())>Number($("#maxDiscountLimit").val()))
				{
					document.getElementById('alertMsg').innerHTML = getFieldText.discGrtMaxDiscErr;
					$(this).focus();
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
					$("#item_form").submit();
				}
			}

		
			if(isNaN($("#discount").val()))
				{
					document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck;
					$(this).focus();
					return false;
				}
			else
			{		
				if(Number($("#discount").val())>Number($("#maxDiscountLimit").val()))
				{
					document.getElementById('alertMsg').innerHTML = getFieldText.discGrtMaxDiscErr;
					$(this).focus();
					return false;
				}
				else
				{
					if($("#maxDiscountLimit").val()==0 || $("#maxDiscountLimit").val()==0.0)
					{
						$("#maxDiscountLimit").val(100);
					}
					document.getElementById('alertMsg').innerHTML = "";
					$("#item_form").submit();
				}
			}*/		
			
		}
  }
}


/*

add and edit for marketer 
*/

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
			$("#expreqdiv").addClass("hide");
			$("#expmonthreqdiv").addClass("hide");
		}
	}
	// category add
	
	function openAddCatModal() {
		document.getElementById('alertMsgCat').innerHTML='';
		$('#CatId').val(0);
		$('#CatName').val("");
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
		$('#SubCatName').val("");
		$('#SubCatId').val(0);
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
				var name_label = $("#name_label").text();
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
