var BASE_URL = "${pageContext.request.contextPath}";
	var storeId = $("#storeIdrsp").val();
	var cmpnyId = $("#companyIdrsp").val();
	var createdBy = $("#useridrsp").val();
	var finyrId = $("#finyrIdrsp").val();
	function showConfirmModal() {
		$('#confirmMessageModal_rsp').modal('show');
	}


$( document ).ready(function() {
//	alert("dr="+$("#confirmvalrsp").val());
		var itemcount = 0;
		$('#salretitem tbody tr').each(function() {
			itemcount++;
		});
		$("#totitmcount_rsp").text(itemcount);
		/*$('#saleinvtable').DataTable({
			"lengthChange" : false,
		 "searching": false,
		"pageLength": 12
		});*/
		var currentDate = new Date();
		var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
		$('#stdate').datepicker({
			format : 'yyyy-mm-dd',
			startDate : startDateFrom,
			autoclose : true,
		});
		$('#enddate').datepicker({
			format : 'yyyy-mm-dd',
			endDate : currentDate,
			autoclose : true,
		});
		//  		$('.dataTables_filter input').attr("placeholder", getCashMemoText.dataTablePlaceHolder);

	$("#item_name_rsp").autocomplete({
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
			console.log(ui.item.items);
			/*$("#itemid").val(ui.item.itemCode);
			$("#modmanufname").text(ui.item.items.manufacturerName);
			$("#modcontentname").text(ui.item.items.contentName);
			$("#modrackname").text(ui.item.items.schdName);
			$("#modgroupname").text(ui.item.items.groupName);
			*/
			$("#itemsaledetailitemname_rsp").html(ui.item.label);
			// call new  ajax for item details
			getItemDetailsrsp(ui.item.itemCode);
			// call new ajax end

		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});

	$('#item_barcode_rsp').on('keydown', function(e) {
		if (e.which == 13) {
			e.preventDefault();
			var barcode = $('#item_barcode_rsp').val();
			var CommResultsetObj = {};
			CommResultsetObj.sku = barcode;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/item/getitembybarcode.htm", CommResultsetObj, function(response) {
				var itemdetobj = JSON.parse(response);
				if (itemdetobj.itemId==0) {
					$("#inputbarcode_rsp").text(barcode);
					$("#noItemBarcodeModal_rsp").modal("show");
				} else {
				getItemDetailsrsp(itemdetobj.itemId);
				}
			});
		}
	});

});

 /**
 *  May 22, 2018
 *   Name : getRetSaleDetbyInvId---enclosing_method_arguments
 *   @param :invno
 *   @param :retmemoFinyr
 *   @param :mulseries
 *  <h3> functionality </h3>
 *    it take invoice last integer number and  give all item details against the sale invoce
*/

function getRetSaleDetbyInvId(invno,retmemoFinyr,mulseries) {
	$("#searchmodtbody_rsp").html("");
	if (invno == '' || invno == 0) {

	} else {
		$('#pleasewaitModal').modal('show');
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.invoiceNo =mulseries+"/"+retmemoFinyr+$("#retmemoSlash").val()+ invno;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/getretrsalealldetbyinvno.htm", CommonRelsetmapperObj, function(response) {
			console.log("res=" + response);
			$('#pleasewaitModal').modal('hide');
			var saledetails = JSON.parse(response);
			if (saledetails.length == 0) {
				//$("#searchmodinvno_rsp").html(invno);
				$("#saledetnotfounddiv_rsp").removeClass("hide");
				$("#saledetmodtable_rsp").addClass("hide");
				$("#saledetfounddiv_rsp").addClass("hide");
				$("#nocashmemofoundokbut_rsp").hide();
			} else {
				$("#saledetnotfounddiv_rsp").addClass("hide");
				$("#saledetmodtable_rsp").removeClass("hide");
				$("#saledetfounddiv_rsp").removeClass("hide");
				$("#nocashmemofoundokbut_rsp").show();
				for ( var i = 0; i < saledetails.length; i++) {
					var saledetail = saledetails[i];
					$("#searchmodinvno_rsp").html(saledetail.saleInvNo);
					$("#searchmodinvdate_rsp").html(moment(saledetail.saleInvDate).format('YYYY-MM-DD'));
					$("#searchmodtotamt_rsp").html(saledetail.netAmount);
					$("#searchmodcustspcldisc_rsp").html(parseFloat(saledetail.specialDiscPer).toFixed(2));
					$("#searchmodcustcont_rsp").html(saledetail.customerPhone);
					$("#searchmodcustname_rsp").html(saledetail.customerName);
					$("#searchmoddocname_rsp").html(saledetail.doctorName);

					var salemansIdPerAmt=saledetail.salesmanId+"_"+saledetail.salesmanFactor+"_"+saledetail.salesmanAmount;
					var itemAgainstItem=saledetail.itemFreeAgainstItem;
					var isfree_color=itemAgainstItem==1? 'freeiai':'';

					$("#spcldiscperc_rsp").val(0);
					var strng_itemKey='"'+saledetail.itemUniqueKey+'"';
					var starttrline = "<tr class='"+isfree_color+"' id=" + saledetail.itemUniqueKey + " style='cursor: pointer;'> ";
					var chkbox = "<td><input id='" + saledetail.itemUniqueKey + "_modretcheck' class='chkboxcheked' type='checkbox' onchange='disablePLqtyrsp("+strng_itemKey+");' value='" + JSON.stringify(saledetail) + "' ></td>";
					var itmname = "<td>" + saledetail.itemName + "</td>";
					if (is_salemanflag==1) {
						var salemansname="<td  > <input type='hidden' id='saletabsalesmanIdComPer'  value='"+salemansIdPerAmt+"'> " + saledetail.salesmanName+ "</td>";
					}else {
						var salemansname="<td class='hide'> <input type='hidden' id='saletabsalesmanIdComPer'  value='"+salemansIdPerAmt+"'> " + saledetail.salesmanName + "</td>";
					}


					var batch = "<td>" + saledetail.batchNo + "</td>";
					var exp = "<td>" + saledetail.expiryDateFormat + "</td>";
					var packQty = "<td>" + saledetail.packQty + "</td>";
                    if ($("#is_free_rsp").val()==1) {
                    	var freeQty= "<td  class='' id='searchtabfree' >" + saledetail.freeQty + "</td>";
                    	var inputfreeQty = "<td><input type='text' value='0' readonly='readonly' id='" + saledetail.itemUniqueKey + "_irfqty' size='3' onkeydown='numcheck(event)'   onkeyup='calfQtyrsp(" + saledetail.conversion + "," + saledetail.ratePerUnit + ",this.value,&quot;" + saledetail.itemUniqueKey + "&quot;," + saledetail.packQty + "," + saledetail.looseQty + ","+saledetail.taxPercentage+","+saledetail.discPer+"," + JSON.stringify(saledetail) + ")'/><input type='hidden' value='" + saledetail.taxPercentage + "' id='" + saledetail.itemUniqueKey + "_irtaxpercentage'/><input type='hidden'  id='" + saledetail.itemUniqueKey + "_irslnolist'/><input type='hidden'  id='" + saledetail.itemUniqueKey + "_iritemlotadjasment'/></td>";
					}else {
					 	var freeQty= "<td  class='hide' id='searchtabfree' >" + saledetail.freeQty + "</td>";
					 	var inputfreeQty = "<td  class='hide' ><input type='text'  class='hide'  value='0' readonly='readonly' id='" + saledetail.itemUniqueKey + "_irfqty' size='3' onkeyup='calfQtyrsp(" + saledetail.conversion + "," + saledetail.ratePerUnit + ",this.value,&quot;" + saledetail.itemUniqueKey + "&quot;," + saledetail.packQty + "," + saledetail.looseQty + ","+saledetail.taxPercentage+","+saledetail.discPer+"," + JSON.stringify(saledetail) + ")'/><input type='hidden' value='" + saledetail.taxPercentage + "' id='" + saledetail.itemUniqueKey + "_irtaxpercentage'/><input type='hidden'  id='" + saledetail.itemUniqueKey + "_irslnolist'/></td>";
					}


					var looseQty = "<td class='hide'>" + saledetail.looseQty + "</td>";
					var prevReturnPackQty = "<td>" + saledetail.prevReturnPackQty + "</td>";
					var prevReturnLooseQty = "<td class='hide'>" + saledetail.prevReturnLooseQty + "</td>";
					var inputPackQty = "<td><input type='text' value='0' readonly='readonly' id='" + saledetail.itemUniqueKey + "_irpqty' size='3' onkeydown='numcheck(event)'  onkeyup='calPQtyrsp(" + saledetail.conversion + "," + saledetail.ratePerUnit + ",this.value,&quot;" + saledetail.itemUniqueKey + "&quot;," + saledetail.packQty + "," + saledetail.looseQty + ","+saledetail.taxPercentage+","+saledetail.discPer+"," + JSON.stringify(saledetail) + ")'/><input type='hidden' value='" + saledetail.taxPercentage + "' id='" + saledetail.itemUniqueKey + "_irtaxpercentage'/><input type='hidden'  id='" + saledetail.itemUniqueKey + "_irslnolist'/></td>";
					var inputLooseQty = "<td class='hide'><input type='text' value='0' readonly='readonly' id='" + saledetail.itemUniqueKey + "_irlqty' size='3' onkeyup='calLqtyrsp(" + saledetail.conversion + "," + saledetail.ratePerUnit + ",this.value,&quot;" + saledetail.itemUniqueKey + "&quot;," + saledetail.packQty + "," + saledetail.looseQty + ","+saledetail.taxPercentage+","+saledetail.discPer+")'/></td>";
					var mrp = "<td>" + parseFloat(saledetail.mrpPerUnit).toFixed(2) + "</td>";//
					var rate = "<td>" + parseFloat(saledetail.ratePerUnit).toFixed(2) + "</td>";
					var retamt = "<td id='" + saledetail.itemUniqueKey + "_retamt'>0.00</td>";
					var endtrline = "</tr>";
//					createdrowline = starttrline + chkbox + itmname + batch + exp + packQty + looseQty + prevReturnPackQty + prevReturnLooseQty + inputPackQty + inputLooseQty + mrp + rate + retamt + endtrline;
					var createdrowline = starttrline + chkbox + itmname+salemansname + batch + exp + packQty+freeQty + prevReturnPackQty  + inputPackQty +inputfreeQty+ mrp + rate + retamt + endtrline;
//					alert(createdrowline);
					$("#searchmodtbody_rsp").append(createdrowline);
				}
			}
		});
		$('#saledetailModal_rsp').modal('show');
	}

}
function calPQtyrsp(conv,
					rate,
					pqty,
					itemid,packqty,lsqty,taxperc,disc,saleretdetfores) {
	//console.log("saleretdetfores="+JSON.stringify(saleretdetfores));
	//console.log("serialNoRequired="+saleretdetfores.serialNoRequired);

	var item_dual_Stock=saleretdetfores.itemdualstock;
	var isexclu=saleretdetfores.taxTypeId;

	$("#clickeditem").val(JSON.stringify(saleretdetfores));
	var lqty =0;// $("#" + itemid + "_irlqty").val();
	var totselqty=((packqty * conv) + Number(lsqty));
	var totqty=((pqty * conv) + Number(lqty));
	 var item_free=$("#" + itemid + "_irfqty").val();
	 var discamt=0.0;
	 var temp_Total_qut=0.0;
	 var gstamt=0.0;


	 if (pqty>0) {

			//	console.log("lqty="+lqty);
			if(totqty>totselqty){
				$("#" + itemid + "_irpqty").val(0);
				$("#" + itemid + "_irlqty").val(0);
				$("#" + itemid + "_irfqty").val(0.0);

				//	console.log("retamt="+retamt);
				$("#" + itemid + "_retamt").text(parseFloat(0).toFixed(2));
			}else{
						if(saleretdetfores.serialNoRequired==1){
							for ( var i = 1; i <= 30; i++) {
								$("#rspcheckboxpopup"+i).attr('checked', false);
							}

						   for(var m=0;m<saleretdetfores.saleReturnDetailsSerialMapper.length;m++){
									//$("#rspcheckboxpopup"+(m+1)).prop("checked",true);
									$("#rsptextboxpopup"+(m+1)).val(saleretdetfores.saleReturnDetailsSerialMapper[m].uniqueIdentifierNo);
				//			$("#slrate"+(i+1)).val(itemserialnodet.saleRate);
							$("#TextBoxDivrsppopup_"+(m+1)).removeClass("hide");
							}

						    for ( var i = saleretdetfores.saleReturnDetailsSerialMapper.length; i < 30-saleretdetfores.saleReturnDetailsSerialMapper.length; i++) {
								$("#TextBoxDivrsppopup_"+(i+1)).addClass("hide");
							}
							$("#openSerialModalrsppopup").modal("show");
							//console.log("arr len="+saleretdetfores.saleReturnDetailsSerialMapper.length);
							var selchkboxlength= $("#selchkboxlength").val();
							//console.log("selchkboxlength="+selchkboxlength);


						}else{

							if (item_dual_Stock==1) {
								var retamt = (pqty * rate);
								temp_Total_qut=pqty;
							}else {
								var retamt = ((pqty * conv) + Number(lqty)) * rate;
								temp_Total_qut=((pqty * conv) + Number(lqty));
							}


							//console.log("retamt="+retamt);
						retamt=Number(retamt)-((Number(retamt)*Number(disc))/100);
						console.log("disc retamt="+retamt);
						//$("#" + itemid + "_retamt").text(parseFloat(retamt).toFixed(2));
						var gstamt = parseFloat(retamt * taxperc / 100).toFixed(4);
						//console.log(gstamt);
						$("#" + itemid + "_irtaxpercentage").val(parseFloat(gstamt).toFixed(4));
						$("#" + itemid + "_retamt").text(parseFloat(Number(retamt)).toFixed(2));
						//var isexclu = $("#isexclusiversp").val();
						if (isexclu == 2) {
							$("#" + itemid + "_retamt").text(parseFloat(Number(retamt) + Number(gstamt)).toFixed(2));
							}
						}
						/*
						 * item lot ajasment function call
						 *
						 */
						 itemlotAdjasmentCal(item_free,temp_Total_qut,
								   rate,disc,taxperc,
								   itemid + "_irtaxpercentage",itemid + "_retamt",
								   itemid + "_iritemlotadjasment",isexclu,0);
				}


	}

}

/**
 *  May 22, 2018
 *   Name :calfQtyrsp
 *   @param :conv
 *   @param :rate
 *   @param :pqty
 *   @param :itemid
 *   @param :packqty
 *   @param :lsqty
 *   @param :taxperc
 *   @param :disc
 *   @param :saleretdetfores
 *
 *  <h3> functionality </h3>
 *   this is sale return lot adjasment  calculation
 *
*/
function calfQtyrsp(conv,rate,fqty,itemid,packqty,lsqty,taxperc,disc,saleretdetfores)
{


	var item_dual_Stock=saleretdetfores.itemdualstock;

	$("#clickeditem").val(JSON.stringify(saleretdetfores));
	var lqty =0;// $("#" + itemid + "_irlqty").val();
	var totselqty=((packqty * conv) + Number(lsqty));
	var totqty=((pqty * conv) + Number(lqty));
	var totfqty=saleretdetfores.freeQty;
	 var item_free=fqty;
	 var discamt=0.0;
	 var temp_Total_qut=0.0;
	 var gstamt=0.0;
	 var isexclu = saleretdetfores.taxTypeId;

	var pqty=$("#" + itemid + "_irpqty").val();
	if (pqty>0) {
		//	console.log("lqty="+lqty);
			if(item_free>totfqty){
				$("#" + itemid + "_irfqty").val(0.0);
				//	console.log("retamt="+retamt);
				$("#" + itemid + "_retamt").text(parseFloat(0).toFixed(2));
			}else{
				if(saleretdetfores.serialNoRequired==1){
					for ( var i = 1; i <= 30; i++) {
						$("#rspcheckboxpopup"+i).attr('checked', false);
					}

				   for(var m=0;m<saleretdetfores.saleReturnDetailsSerialMapper.length;m++){
							//$("#rspcheckboxpopup"+(m+1)).prop("checked",true);
							$("#rsptextboxpopup"+(m+1)).val(saleretdetfores.saleReturnDetailsSerialMapper[m].uniqueIdentifierNo);
	//				$("#slrate"+(i+1)).val(itemserialnodet.saleRate);
					$("#TextBoxDivrsppopup_"+(m+1)).removeClass("hide");
					}

				    for ( var i = saleretdetfores.saleReturnDetailsSerialMapper.length; i < 30-saleretdetfores.saleReturnDetailsSerialMapper.length; i++) {
						$("#TextBoxDivrsppopup_"+(i+1)).addClass("hide");
					}
					$("#openSerialModalrsppopup").modal("show");
					console.log("arr len="+saleretdetfores.saleReturnDetailsSerialMapper.length);
					var selchkboxlength= $("#selchkboxlength").val();
					//console.log("selchkboxlength="+selchkboxlength);


				}else{

					if (item_dual_Stock==1) {
						var retamt = (pqty * rate);

						temp_Total_qut=pqty;
					}else {
						var retamt = ((pqty * conv) + Number(lqty)) * rate;
						temp_Total_qut=((pqty * conv) + Number(lqty));
					}


					    //console.log("retamt="+retamt);
					    retamt=Number(retamt)-((Number(retamt)*Number(disc))/100);
					//	console.log("disc retamt="+retamt);
						//$("#" + itemid + "_retamt").text(parseFloat(retamt).toFixed(2));
						  gstamt = parseFloat(retamt * taxperc / 100).toFixed(4);
						console.log(gstamt);
						$("#" + itemid + "_irtaxpercentage").val(parseFloat(gstamt).toFixed(4));
						$("#" + itemid + "_retamt").text(parseFloat(Number(retamt)).toFixed(2));


						if (isexclu == 2) {
							$("#" + itemid + "_retamt").text(parseFloat(Number(retamt) + Number(gstamt)).toFixed(2));
							}
						}


					/*
					 * lot adjasment  function call
		             *  itemlotAdjasmentCal(item_free,inputtotlqty,item_rate_ls,item_dis,item_taxPercentage_rsp,'item_CalcTaxAmt_rsp','item_netamt_rsp','item_lotadjasment_rsp','isexclusiversp');
					 */
						   itemlotAdjasmentCal(item_free,temp_Total_qut,
								   rate,disc,taxperc,
								   itemid + "_irtaxpercentage",itemid + "_retamt",
								   itemid + "_iritemlotadjasment",isexclu,0);

				}


	}// end if checking pack >0
	else {
		$("#" + itemid + "_irfqty").val(0.0);
	}



}

function okSlNorsppopup(){
	console.log("clickeditemval="+$("#clickeditem").val());
	var saleretdetfores=JSON.parse($("#clickeditem").val());
	console.log("serialNoRequiredpopup="+saleretdetfores.serialNoRequired);
	var pqty=$("input:checkbox[class=chkrsppopup]:checked").length ;
	//alert("length="+length);
	var arr=[];
	var arrchk=[];
	var srate=[];
    $("input:checkbox[class=chkrsppopup]:checked").each(function () {
//        alert("Id: " + $(this).attr("id") + " Value: " + $(this).val());
       // alert("textbox val="+$("#rsptextbox"+$(this).val()).val());
        arr.push($("#rsptextboxpopup"+$(this).val()).val());
        arrchk.push($(this).attr("id"));
//        srate.push($("#slrate"+$(this).val()).val());
    });

    var conv=saleretdetfores.conversion;
    var lqty=saleretdetfores.looseQty;
    var rate=saleretdetfores.ratePerUnit;
    var disc=saleretdetfores.discPer;
    var taxperc=saleretdetfores.taxPercentage;
    var itemid=saleretdetfores.itemUniqueKey;

    $("#allValrsppopup").val(arr);
    $("#allChkIDrsppopup").val(arrchk);
    $("#selchkboxlengthpopup").val(pqty);
    $("#"+itemid+"_irslnolist").val(arr);


    var retamt = ((pqty * conv) + Number(lqty)) * rate;
	console.log("retamt="+retamt);
		retamt=Number(retamt)-((Number(retamt)*Number(disc))/100);
		console.log("disc retamt="+retamt);
		//$("#" + itemid + "_retamt").text(parseFloat(retamt).toFixed(2));
		var gstamt = parseFloat(retamt * taxperc / 100).toFixed(4);
		console.log(gstamt);
		$("#" + itemid + "_irpqty").val(pqty);
		$("#" + itemid + "_irtaxpercentage").val(parseFloat(gstamt).toFixed(4));
		$("#" + itemid + "_retamt").text(parseFloat(Number(retamt)).toFixed(2));
		var isexclu = $("#isexclusiversp").val();
		if (isexclu == 1) {
			$("#" + itemid + "_retamt").text(parseFloat(Number(retamt) + Number(gstamt)).toFixed(2));
			}

    $("#openSerialModalrsppopup").modal("hide");
}
function calLqtyrsp(	conv,
					rate,
					lqty,
					itemid,packqty,lsqty,taxperc,disc) {
	var pqty = $("#" + itemid + "_irpqty").val();
	var totselqty=((packqty * conv) + Number(lsqty));
	var totqty=((pqty * conv) + Number(lqty));
	if(totqty>totselqty){
		$("#" + itemid + "_irpqty").val(0);
		$("#" + itemid + "_irlqty").val(0);
		//	console.log("retamt="+retamt);
		$("#" + itemid + "_retamt").text(parseFloat(0).toFixed(2));
	}else{
		var retamt = ((pqty * conv) + Number(lqty)) * rate;
			console.log("retamt="+retamt);
		retamt=Number(retamt)-((Number(retamt)*Number(disc))/100);
		console.log("disc retamt="+retamt);
		//$("#" + itemid + "_retamt").text(parseFloat(retamt).toFixed(2));
		var gstamt = parseFloat(retamt * taxperc / 100).toFixed(4);
		console.log(gstamt);
		$("#" + itemid + "_irtaxpercentage").val(parseFloat(gstamt).toFixed(4));
		$("#" + itemid + "_retamt").text(parseFloat(Number(retamt)).toFixed(2));
		var isexclu = $("#isexclusiversp").val();
		if (isexclu == 1) {
			$("#" + itemid + "_retamt").text(parseFloat(Number(retamt) + Number(gstamt)).toFixed(2));
		}
	}


}

function disablePLqtyrsp(itemKey)
{
	if ($("#" + itemKey + "_modretcheck").is(":checked")) {
		$("#" + itemKey + "_irpqty").attr("readonly",false);
		$("#" + itemKey + "_irlqty").attr("readonly",false);
		$("#" + itemKey + "_irfqty").attr("readonly",false);
	}
	else
	{
		$("#" + itemKey + "_irpqty").attr("readonly",true);
		$("#" + itemKey + "_irlqty").attr("readonly",true);
		$("#" + itemKey + "_irfqty").attr("readonly",true);
	}
}

function getmodretcheckeditemlistrsp() {
	var len=$('.chkboxcheked:checked').length;
	var count = 0;
	$('#searchmodtable_rsp > tbody > tr').each(function() {
		var itemid = this.id;
		console.log("len="+len);
		if(len==0){
			$("#alertmessagecont_rsp").text("Please check at least one item.");
		}else{
			$("#alertmessagecont_rsp").text("");
			if ($("#" + itemid + "_modretcheck").is(":checked")) {
				var pqty = parseFloat(isEmpty( $(this).find("#" + itemid + "_irpqty").val())==true?0: $(this).find("#" + itemid + "_irpqty").val());
            	var lqty =parseFloat(isEmpty( $(this).find("#" + itemid + "_irlqty").val())==true?0: $(this).find("#" + itemid + "_irlqty").val());

				var checkedslno = $(this).find("#" + itemid + "_irslnolist").val();
				var fqty = $(this).find("#" + itemid + "_irfqty").val();
				var rt_item_lotadjament = $(this).find("#" + itemid + "_iritemlotadjasment").val();
				if (isEmpty(rt_item_lotadjament)) {
					rt_item_lotadjament=0;
				}

				if((pqty == "") && (lqty == ""))
				{
					count = count+1;
				}
				if ((pqty == 0) && (lqty == 0))
				{
					count = count+1;
				}
				else
				{

				}
				if(count==0)
				{
					$("#alertmessagecont_rsp").text("");
					var saledetail = $("#" + itemid + "_modretcheck").val();
					var itemdetail = JSON.parse(saledetail);
					insertModDatatoRetTable(itemdetail,checkedslno);
				}
				else
				{

				}
			}
		}
	});
	if(count>0)
	{
		$("#alertmessagecont_rsp").text("Ret P.Qty/Ret L.Qty "+getFieldText.fieldReq);
		$('#saledetailModal_rsp').modal('show');
		$("#salerettabitemdetails").empty();

		$("#saleretcustid").val("");
		$("#saleretcustph").val("");
		$("#saleretcustname").val("");
		$("#saleretdocname").val("");
		$("#saleretdocid").val("");
	}
	else
	{
		$('#saledetailModal_rsp').modal('hide');
	}
	/*if(len!=0){
		$('#saledetailModal_rsp').modal('hide');
	}*/
	calculateTotalVatrsp();
	calculateTotalTaxrsp();
	calculateTotalltadjrsp();
	calculateNetTotalrsp();
	calculateTotalGSTrsp();
	calculateSpclDiscrsp();
}

function insertModDatatoRetTable(itemdetail,checkedslno) {
	var saleretcustid = $("#saleretcustid").val();
	var isMrpEnableFlag = $("#isMrpEnableFlag").val();
		if (saleretcustid==0 || saleretcustid == itemdetail.customerId) {
			console.log("same user");
			var uniquechk=0;
//			var newunikey =itemdetail.itemId+itemdetail.saleInvNo;
			var newunikey =itemdetail.itemUniqueKey+itemdetail.saleInvNo;
			console.log("newunikey="+newunikey);
			$('#salretitem tbody tr').each(function() {
				var itemid=this.id;
				var saleinvno = $(this).find("#salerettabsaleinvno").html();
				var preunikey = itemid+saleinvno;
				console.log("preunikey="+preunikey);
				if(newunikey==preunikey){
					uniquechk=1;
				}
			});
			console.log("uniquechk="+uniquechk);
			if(uniquechk==1){
				$("#sameItemInvModal_rsp").modal("show");
			}else{
				var calretamt = $("#" + itemdetail.itemUniqueKey + "_retamt").text();
				var lqty = $("#" + itemdetail.itemUniqueKey + "_irlqty").val();
				var pqty = $("#" + itemdetail.itemUniqueKey + "_irpqty").val();
				var calctaxperc = $("#" + itemdetail.itemUniqueKey + "_irtaxpercentage").val();
				var fqty = $("#" + itemdetail.itemUniqueKey + "_irfqty").val();

				var rt_item_lotadjament = $("#" + itemdetail.itemUniqueKey + "_iritemlotadjasment").val();
				if (isEmpty(rt_item_lotadjament)) {
					rt_item_lotadjament=0;
				}

				$("#saleretcustid").val(itemdetail.customerId);
				$("#saleretcustph").val(itemdetail.customerPhone);
				$("#saleretcustname").val(itemdetail.customerName);
				$("#saleretdocname").val(itemdetail.doctorName);
				$("#saleretdocid").val(itemdetail.doctorId);

			 	var itemAgainstItem=itemdetail.itemFreeAgainstItem;
				var isfree_color=itemAgainstItem==1? 'freeiai':'';

				var starttrline = "<tr class='"+isfree_color+"' id=" + itemdetail.itemUniqueKey + " style='cursor: pointer;' onclick='javascript:itemHeaderDivViewrsp(this.id)'>";
				var itmname = "<td id='salerettabname'>" + itemdetail.itemName + "</td>";
				var batch = "<td id='salerettabbat'>" + itemdetail.batchNo + "</td>";
				var exp = "<td id='salerettabexpdt'>" + itemdetail.expiryDateFormat + "</td>";
//				var mfg = "<td id='salerettabmanname'>" + itemdetail.manufacturerName + "</td>";
				var packQty = "<td id='salerettabpqty'>" + pqty + "</td>";

				if ($("#is_free_rsp").val()==1) {
					var freeQty= "<td id='salerettabfqty'>" + fqty + "</td>";

				}else
					{
					var freeQty= "<td id='salerettabfqty' class='hide'>" + fqty + "</td>";
					}

				var back_fqty="<td id='salerettab_backfqty' class='hide'>" + itemdetail.freeQty + "</td>";
//				var looseQty = "<td id='salerettablqty'>" + lqty + "</td>";
				var conv = "<td id='salerettabconv'>" + itemdetail.conversion + "</td>";
				var mrpperpack = "<td id='salerettabmrppack'>" + parseFloat(itemdetail.mrp).toFixed(4) + "</td>";
				var mrp = "<td id='salerettabmrp' class='hide'>" + parseFloat(itemdetail.mrpPerUnit).toFixed(4) + "</td>";
				var rateperls = "<td id='salerettabrateperunit'>" + parseFloat(itemdetail.ratePerUnit).toFixed(4) + "</td>";
				var disc = "<td id='salerettabdiscperc'>" + parseFloat(itemdetail.discPer).toFixed(4) + "</td>";
				var retamt = "<td id='salerettabtotamt'>" + parseFloat(calretamt).toFixed(4) + "</td>";
				var rowdelete = "<td><button class='btn btn-theme04 btn-xs' id='" + itemdetail.itemUniqueKey + "' title='Delete item' onclick='javascript:showSelRetItemDelModal(this.id);'><i class='fa fa-trash-o '></i></button></td>";
				var salepty = "<td id='salepqty_rsp' class='hide'>" + itemdetail.packQty + "</td>";
				var salelqty_rsp = "<td id='salelqty_rsp' class='hide'>" + itemdetail.looseQty + "</td>";
				var punitid = "<td id='salerettabpunitid' class='hide'>" + itemdetail.packUnitId + "</td>";
				var salerettablunitid = "<td id='salerettablunitid' class='hide'>" + itemdetail.looseUnitId + "</td>";
				var salerettabcontent = "<td id='salerettabcontent' class='hide'>" + itemdetail.contentName + "</td>";
				var salerettabrate = "<td id='salerettabrate' class='hide'>" + parseFloat(itemdetail.rate).toFixed(4) + "</td>";
				var salerettabdisamt = "<td id='salerettabdisamt' class='hide'>" + itemdetail.disc + "</td>";
				var salerettabed = "<td id='salerettabed' class='hide'>" + itemdetail.edPer + "</td>";
				var salerettabedamt = "<td id='salerettabedamt' class='hide'>" + itemdetail.ed + "</td>";
				var taxPer = "<td id='salerettabtax' class='hide'>" + itemdetail.taxPer + "</td>";
				var salerettabtaxamt = "<td id='salerettabtaxamt' class='hide'>" + itemdetail.tax + "</td>";
				var vatPer = "<td id='salerettabvat' class='hide'>" + itemdetail.vatPer + "</td>";
				var salerettabvatamt = "<td id='salerettabvatamt' class='hide'>" + itemdetail.vat + "</td>";
				var salerettabsaleid = "<td id='salerettabsaleid' class='hide'>" + itemdetail.saleId + "</td>";
				var salerettabsaleinvno = "<td id='salerettabsaleinvno' class='hide'>" + itemdetail.saleInvNo + "</td>";
				var packQtyhide = "<td id='salerettabpqtyhide' class='hide'>" + itemdetail.hidePackQty + "</td>";
				var looseQtyhide = "<td id='salerettablqtyhide' class='hide'>" + itemdetail.hideLooseQty + "</td>";
				var salerettabsku = "<td id='salerettabsku' class='hide'>" + itemdetail.sku + "</td>";
				var salerettabtaxId = "<td id='salerettabtaxId' class='hide'>" + itemdetail.taxId + "</td>";
				var salerettabtaxName = "<td id='salerettabtaxName' class='hide'>" + itemdetail.taxName + "</td>";
				var salerettabtaxPercentage = "<td id='salerettabtaxPercentage' class='hide'>" + itemdetail.taxPercentage + "</td>";
				var salerettabitemTaxAmount = "<td id='salerettabitemTaxAmount' class='hide'>" + parseFloat(calctaxperc).toFixed(2) + "</td>";
				var salerettabisGroupTax = "<td id='salerettabisGroupTax' class='hide'>" + itemdetail.isGroupTax + "</td>";
				var salerettabtaxMode = "<td id='salerettabtaxMode' class='hide'>" + itemdetail.taxMode + "</td>";
				var salerettabhsnCode = "<td id='salerettabhsnCode' class='hide'>" + itemdetail.hsnCode + "</td>";

				var serialNoRequired = "<td id='salerettabslnoreq' class='hide'>" + itemdetail.serialNoRequired + "</td>";
				var batchWiseStockRequired = "<td id='salerettabitembatchWiseStockreq' class='hide'>" + itemdetail.batchWiseStock + "</td>";
				var expiryDateRequired = "<td id='salerettabitemexpiryDateRequiredreq' class='hide'>" + itemdetail.expiryDateRequired + "</td>";
				var expiryMonthRequired = "<td id='salerettabitemexpiryMonthRequiredreq' class='hide'>" + itemdetail.expiryMonthRequired + "</td>";
				var dualStockRequired = "<td id='salerettabitemdualStockRequiredreq' class='hide'>" + itemdetail.dualStockRequired + "</td>";
				var mrpRequired = "<td id='salerettabitemmrpRequiredreq' class='hide'>" + itemdetail.mrpRequired + "</td>";
				var locationRequired = "<td id='salerettabitemlocationRequiredreq' class='hide'>" + itemdetail.locationRequired + "</td>";
				var priceListRequired = "<td id='salerettabitempriceListRequiredreq' class='hide'>" + itemdetail.priceListRequired + "</td>";

				var salasManIsPerAmt = '<td id="salerettabsalesmanIdComPer" class="numeric hide">' + itemdetail.salesmanId+"_"+ itemdetail.salesmanFactor+"_"+itemdetail.salesmanAmount+ '</td>';

				var itemlotadjasmet = '<td id="salerettabitemlotadjasment" class="numeric hide">' + rt_item_lotadjament+'</td>';

				var itemAgainstItemtd = '<td id="salerettabitemagainstitem" class="numeric hide">' + itemAgainstItem+'</td>';
				var expdate = "<td id='salerettabexpdate' class='hide'>" + moment(itemdetail.expiryDate).format('YYYY-MM-DD') + "</td>";

				var slnoarr=[];
				if(itemdetail.saleReturnDetailsSerialMapper.length>0){
					for(var m=0;m<itemdetail.saleReturnDetailsSerialMapper.length;m++){
						slnoarr.push(itemdetail.saleReturnDetailsSerialMapper[m].uniqueIdentifierNo);
					}
				}
				var slnoarrList = "<td id='salerettabitemslnolist' class='hide'>" + slnoarr + "</td>";
				var checkedslnoList = "<td id='checkedsalerettabitemslnolist' class='hide'>" + checkedslno + "</td>";
				var itemTaxTypeid = "<td id='salerettabtaxTypeId' class='hide'>" + itemdetail.taxTypeId + "</td>";




				var endtrline = "</tr>";
				//createdrowline = starttrline + itmname + batch + exp + mfg + packQty + looseQty + conv + mrpperpack + mrp + rateperls + disc + retamt + rowdelete + salepty + salelqty_rsp + punitid + salerettablunitid + salerettabcontent + salerettabrate + salerettabdisamt + salerettabed + salerettabedamt + taxPer + salerettabtaxamt + vatPer + salerettabvatamt + salerettabsaleid + salerettabsaleinvno+packQtyhide+looseQtyhide+salerettabsku+salerettabtaxId+salerettabtaxName+salerettabtaxPercentage+salerettabitemTaxAmount+salerettabisGroupTax+salerettabtaxMode+salerettabhsnCode + endtrline;
				//createdrowline = starttrline + itmname + batch + exp + packQty  + conv + mrpperpack  + rateperls + disc + retamt + rowdelete + salepty + salelqty_rsp + punitid + salerettablunitid + salerettabcontent + salerettabrate + salerettabdisamt + salerettabed + salerettabedamt + taxPer + salerettabtaxamt + vatPer + salerettabvatamt + salerettabsaleid + salerettabsaleinvno+packQtyhide+looseQtyhide+salerettabsku+salerettabtaxId+salerettabtaxName+salerettabtaxPercentage+salerettabitemTaxAmount+salerettabisGroupTax+salerettabtaxMode+salerettabhsnCode+ mrp + endtrline;
				if(isMrpEnableFlag==1){
					createdrowline = starttrline + itmname + batch + exp + packQty + freeQty+back_fqty+ conv + mrpperpack  + rateperls + disc + retamt + rowdelete + salepty + salelqty_rsp + punitid + salerettablunitid + salerettabcontent + salerettabrate + salerettabdisamt + salerettabed + salerettabedamt + taxPer + salerettabtaxamt + vatPer + salerettabvatamt + salerettabsaleid + salerettabsaleinvno+packQtyhide+looseQtyhide+salerettabsku+salerettabtaxId+salerettabtaxName+salerettabtaxPercentage+salerettabitemTaxAmount+salerettabisGroupTax+salerettabtaxMode+salerettabhsnCode+ mrp+serialNoRequired+batchWiseStockRequired+expiryDateRequired+expiryMonthRequired+dualStockRequired+mrpRequired+locationRequired+priceListRequired+slnoarrList+expdate+checkedslnoList + salasManIsPerAmt+itemlotadjasmet +itemAgainstItemtd+itemTaxTypeid+ endtrline;
				}else{
					createdrowline = starttrline + itmname + batch + exp + packQty +freeQty+back_fqty + conv + rateperls + disc + retamt + rowdelete + salepty + salelqty_rsp + punitid + salerettablunitid + salerettabcontent + salerettabrate + salerettabdisamt + salerettabed + salerettabedamt + taxPer + salerettabtaxamt + vatPer + salerettabvatamt + salerettabsaleid + salerettabsaleinvno+packQtyhide+looseQtyhide+salerettabsku+salerettabtaxId+salerettabtaxName+salerettabtaxPercentage+salerettabitemTaxAmount+salerettabisGroupTax+salerettabtaxMode+salerettabhsnCode+ mrp+serialNoRequired+batchWiseStockRequired+expiryDateRequired+expiryMonthRequired+dualStockRequired+mrpRequired+locationRequired+priceListRequired+slnoarrList+expdate+checkedslnoList + salasManIsPerAmt +itemlotadjasmet+itemAgainstItemtd+itemTaxTypeid+ endtrline;
				}

				$("#salerettabitemdetails").append(createdrowline);


				getvendorledger($('#dueties_and_tax_acc').val(),0,0,0);// for duties and tax
				getvendorledger($('#roundoff_group_code').val(),0,0,1);// for round off
				getvendorledger($('#saleaccunt_group_code').val(),0,0,2);// for sale account
				getvendorledger($('#lot_adjas_group_code').val(),0,0,4); // for lot adjasment account
				if (itemdetail.customerId==0) {

					getvendorledger($('#cash_in_hand_group_code').val(),0,0,3);// for cash ledger credit

				}else {

							getvendorledger($('#debitor_group_code').val(),0,itemdetail.customerId,3);// for sunndry debitor credit
				}

			}

		} else {
			$("#sameUserModal_rsp").modal("show");
		}

}

function itemHeaderDivViewrsp(itemid) {
	$("#item_id_rsp").val(itemid);
	$('#item_name_rsp').prop('readonly', true);
	$('#item_barcode_rsp').prop('readonly', true);
	$("#item_name_rsp").val($("#salretitem tr#" + itemid).find('#salerettabname').text());
	$("#item_barcode_rsp").val($("#salretitem tr#" + itemid).find('#salerettabsku').text());
	$("#item_rpqty_rsp").val($("#salretitem tr#" + itemid).find('#salerettabpqty').text());
	$("#item_rlqty_rsp").val($("#salretitem tr#" + itemid).find('#salerettablqty').text());
	$("#item_disper_rsp").val($("#salretitem tr#" + itemid).find('#salerettabdiscperc').text());
	$("#item_bat_rsp").val($("#salretitem tr#" + itemid).find('#salerettabbat').text());
	$("#item_exp_rsp").val($("#salretitem tr#" + itemid).find('#salerettabexpdt').text());
	$("#item_mrpperpack_rsp").val($("#salretitem tr#" + itemid).find('#salerettabmrppack').text());
	$("#item_mrp_rsp").val($("#salretitem tr#" + itemid).find('#salerettabmrp').text());
	$("#item_rate_rsp").val($("#salretitem tr#" + itemid).find('#salerettabrate').text());
	$("#item_rateperloose_rsp").val($("#salretitem tr#" + itemid).find('#salerettabrateperunit').text());
	$("#item_netamt_rsp").val($("#salretitem tr#" + itemid).find('#salerettabtotamt').text());
	$("#item_mfg_rsp").val($("#salretitem tr#" + itemid).find('#salerettabmanname').text());
	$("#item_content_rsp").val($("#salretitem tr#" + itemid).find('#salerettabcontent').text());
	$("#item_conv_rsp").val($("#salretitem tr#" + itemid).find('#salerettabconv').text());
	$("#item_pqty_rsp").val($("#salretitem tr#" + itemid).find('#salepqty_rsp').text());
	$("#item_lqty_rsp").val($("#salretitem tr#" + itemid).find('#salelqty_rsp').text());
	$("#item_rpqty_hide_rsp").val($("#salretitem tr#" + itemid).find('#salerettabpqtyhide').text());
	$("#item_rlqty_hide_rsp").val($("#salretitem tr#" + itemid).find('#salerettablqtyhide').text());

	$("#item_taxId_rsp").val($("#salretitem tr#" + itemid).find('#salerettabtaxId').text());
	$("#item_taxPercentage_rsp").val($("#salretitem tr#" + itemid).find('#salerettabtaxPercentage').text());
	$("#item_isGroupTax_rsp").val($("#salretitem tr#" + itemid).find('#salerettabisGroupTax').text());
//	$("#item_discount_rsp").val($("#salretitem tr#" + itemid).find('#saletabdiscount').text());
//	$("#item_maxDiscountLimit_rsp").val($("#salretitem tr#" + itemid).find('#saletabmaxDiscountLimit').text());
	$("#item_CalcTaxAmt_rsp").val($("#salretitem tr#" + itemid).find('#salerettabitemTaxAmount').text());
	$("#item_taxMode_rsp").val($("#salretitem tr#" + itemid).find('#salerettabtaxMode').text());
	$("#item_hsnCode_rsp").val($("#salretitem tr#" + itemid).find('#salerettabhsnCode').text());

	$("#item_batchWiseStock_req_ret").val($("#salretitem tr#" + itemid).find('#salerettabitembatchWiseStockreq').text());
	$("#item_expiryDateRequired_req_ret").val($("#salretitem tr#" + itemid).find('#salerettabitemexpiryDateRequiredreq').text());
	$("#item_expiryMonthRequired_req_ret").val($("#salretitem tr#" + itemid).find('#salerettabitemexpiryMonthRequiredreq').text());
	$("#item_dualStockRequired_req_ret").val($("#salretitem tr#" + itemid).find('#salerettabitemdualStockRequiredreq').text());
	$("#item_mrpRequired_req_ret").val($("#salretitem tr#" + itemid).find('#salerettabitemmrpRequiredreq').text());
	$("#item_locationRequired_req_ret").val($("#salretitem tr#" + itemid).find('#salerettabitemlocationRequiredreq').text());
	$("#item_priceListRequired_req_ret").val($("#salretitem tr#" + itemid).find('#salerettabitempriceListRequiredreq').text());
	$("#item_serialNo_req_ret").val($("#salretitem tr#" + itemid).find('#salerettabslnoreq').text());
//	$("#item_serialNo_list_ret").val($("#salretitem tr#" + itemid).find('#salerettabitemslnolist').text());
	$("#item_serialNo_list_ret").val($("#salretitem tr#" + itemid).find('#checkedsalerettabitemslnolist').text());
	$("#item_expdate_rsp").val($("#salretitem tr#" + itemid).find('#salerettabexpdate').text());
	$("#item_rfqty_rsp").val($("#salretitem tr#" + itemid).find('#salerettabfqty').text());
	$("#item_rfqty_hide_rsp").val($("#salretitem tr#" + itemid).find('#salerettab_backfqty').text());

//	var slnos=$("#salretitem tr#" + itemid).find('#salerettabitemslnolist').text();
	var slnos=$("#salretitem tr#" + itemid).find('#checkedsalerettabitemslnolist').text();

  $("#item_taxTypeId_rsp").val($("#salretitem tr#" + itemid).find('#salerettabtaxTypeId').text());
   var item_dual_stock=$("#salretitem tr#" + itemid).find('#salerettabitemdualStockRequiredreq').text();


	 if ($("#is_free_rsp").val()==1) {
		  $("#free_label_rsp") .removeClass("hide");
		  $("#free_qty_rsp") .removeClass("hide");
	}else {
		 $("#free_label_rsp") .addClass("hide");
		 $("#free_qty_rsp") .addClass("hide");
	}

	if (item_dual_stock==1) {
     $("#ratio_val_rsp") .removeClass("hide");
	}else
		{
		  $("#ratio_val_rsp") .addClass("hide");
		}


	var sno=slnos.split(",");
	for(var i=0;i<sno.length;i++){

		$("#rsptextbox"+(i+1)).val(sno[i]);
	}

	$("#add_btn_rsp").addClass("hide");
	$("#edit_btn_rsp").removeClass("hide");
}
function clearHeaderDivrsp() {
	$("#header_div_rsp").find('input:text').val('');
	$("#header_div_rsp").find('input:hidden').val('');
	$('#item_name_rsp').prop('readonly', false);
	$("#add_btn_rsp").removeClass("hide");
	$("#edit_btn_rsp").addClass("hide");
	$("#item_id_rsp").val("0");
	$("#item_name_rsp").focus();
	document.getElementById('alertMsg_rsp').innerHTML = "";
	//	$("#item_dis").val(parseFloat($("#salediscount").val()).toFixed(4));
	//	document.getElementById('alertMsg_rsp').innerHTML = "";
};

function calculateTotalltadjrsp () {
	var totsaleretlotadj = 0.00;
	$('#salretitem tbody tr').each(function() {
		var saleltadj = $(this).find("#salerettabitemlotadjasment").html();
		totsaleretlotadj = totsaleretlotadj + Number(saleltadj);
	});
	 if (totsaleretlotadj>0) {

		$("#totltadj_rsp").removeClass("hide");
		$("#flotid_rsp").removeClass("hide");
	}else {
		$("#flotid_rsp").addClass("hide");
		$("#totltadj_rsp").addClass("hide");
	}

 $("#totlotadjasment_rsp").val(parseFloat(totsaleretlotadj).toFixed(2));
}
function calculateTotalVatrsp() {
	var grandtotalVat = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmvat = $(this).find("#salerettabvatamt").html();

		grandtotalVat = grandtotalVat + Number(itmvat);
	});
	$("#totvatamt_rsp").val(parseFloat(grandtotalVat).toFixed(2));
}

function calculateTotalTaxrsp() {
	var grandtotalTax = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmtax = $(this).find("#salerettabtaxamt").html();

		grandtotalTax = grandtotalTax + Number(itmtax);
	});
//	$("#tottaxamt_rsp").val(parseFloat(grandtotalTax).toFixed(2));
}

function calculateTotalGSTrsp() {
	var grandtotalGST = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmtax = $(this).find("#salerettabitemTaxAmount").html();

		grandtotalGST = grandtotalGST + Number(itmtax);
	});
	$("#tottaxamt_rsp").val(parseFloat(grandtotalGST).toFixed(2));
}


function calculateNetTotalrsp() {
	var itemcount = 0;
	var grandNetTotal = 0.00;
	var grandtotalGST = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmmrp = $(this).find("#salerettabtotamt").html();
		grandNetTotal = grandNetTotal + Number(itmmrp);
		itemcount++;


		var itmtax = $(this).find("#salerettabitemTaxAmount").html();

		grandtotalGST = grandtotalGST + Number(itmtax);
	});


	$("#totitmcount_rsp").text(itemcount);
	$("#totgrossamt_rsp").val(parseFloat(grandNetTotal).toFixed(2));
	var roundednetamnt = Math.round(Number(grandNetTotal));
	$("#totretamt_rsp").val(parseFloat(roundednetamnt).toFixed(2));
	 $("#retamtsamepage").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff_rsp = Number(roundednetamnt) - Number(grandNetTotal);
	console.log("roundoff_rsp="+roundoff_rsp);
	$("#roundoff_rsp").val(parseFloat(roundoff_rsp).toFixed(2));
	var temp_lot=0;
	if (parseFloat($("#totlotadjasment_rsp").val())=='NaN')
	{
		temp_lot=0;
	}
	var temp_sale=Math.round(parseFloat(grandNetTotal))+temp_lot;

	//var sale_acc=(parseFloat(roundednetamnt)-parseFloat(roundoff_rsp))- parseFloat(grandtotalGST);
	var sale_acc=(temp_sale)- parseFloat(grandtotalGST)-parseFloat(roundoff_rsp);

	$('#sale_account_rsp').val(sale_acc.toFixed(2));
	$('#debitor_amt_rsp').val(roundednetamnt.toFixed(2));
	 if (!isEmpty($("#totlotadjasment_rsp").val())) {
			// other adjamsment
		 grandNetTotal=parseFloat(grandNetTotal)+parseFloat($("#totlotadjasment_rsp").val());
		   }

}

function calculateSpclDiscrsp() {
	var discper=$("#spcldiscperc_rsp").val();
	var grandNetTotal = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmmrp = $(this).find("#salerettabtotamt").html();
		grandNetTotal = grandNetTotal + Number(itmmrp);
	});
	var discamt=grandNetTotal*discper/100;
	var newnettot=grandNetTotal-discamt;
	var roundednetamnt = Math.round(Number(newnettot));
	$("#totgrossamt_rsp").val(parseFloat(grandNetTotal).toFixed(2));
	$("#spcldisc_rsp").val(parseFloat(discamt).toFixed(2));
//	var roundednetamnt = Math.round(Number(grandNetTotal));
	$("#totretamt_rsp").val(parseFloat(roundednetamnt).toFixed(2));
	$('#debitor_amt_rsp').val(roundednetamnt.toFixed(2));
	 $("#retamtsamepage").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff_rsp = Number(roundednetamnt) - Number(newnettot);
	console.log("roundoff_rsp="+roundoff_rsp);
	$("#roundoff_rsp").val(parseFloat(roundoff_rsp).toFixed(2));
}

function showSelRetItemDelModal(trId) {
	$("#confirmId_rsp").val(trId);
	$('#confirmModal_rsp').modal('show');
}
function DoConfirmrsp() {
	var itmid = $("#confirmId_rsp").val();
	$('#salerettabitemdetails tr#' + itmid).remove();
	clearHeaderDivrsp();
	calculateTotalGSTrsp();
	calculateNetTotalrsp();
}
function Validationrsp() {
	var counter = 0;

	var pqty_field = $("#pqty_label").text();

	var lqty_field = $("#lqty_label").text();

	/*if (($("#item_rpqty_rsp").val() == "") || ($("#item_rlqty_rsp").val() == "")) {
		document.getElementById('alertMsg_rsp').innerHTML = pqty_field + " / " + lqty_field + " " + getFieldText.fieldempty;
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg_rsp').innerHTML = "";
	}

	if (($("#item_rpqty_rsp").val() == 0) && ($("#item_rlqty_rsp").val() == 0)) {
		document.getElementById('alertMsg_rsp').innerHTML = pqty_field + " / " + lqty_field + " " + getFieldText.fieldReq;
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg_rsp').innerHTML = "";
	}*/

	if (($("#item_rpqty_rsp").val() == "")) {
		document.getElementById('alertMsg_rsp').innerHTML = pqty_field + " " + getFieldText.fieldempty;
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg_rsp').innerHTML = "";
	}

	if (($("#item_rpqty_rsp").val() == 0)) {
		document.getElementById('alertMsg_rsp').innerHTML = pqty_field + " " + getFieldText.fieldReq;
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg_rsp').innerHTML = "";
	}

	if (isNaN($("#item_rpqty_rsp").val())) {
		document.getElementById('alertMsg_rsp').innerHTML = getFieldText.numberCheck + " in P.Qty";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		if($("#item_rpqty_rsp").val()<0)
		{
			document.getElementById('alertMsg_rsp').innerHTML = pqty_field+" "+getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}
	}

	/*if (isNaN($("#item_rlqty_rsp").val())) {
		document.getElementById('alertMsg_rsp').innerHTML = getFieldText.numberCheck + " in L.Qty";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		if($("#item_rlqty_rsp").val()<0)
		{
			document.getElementById('alertMsg_rsp').innerHTML = lqty_field+" "+getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}
	}*/

	return counter;
}

function addOrUpdateItemToDetailsTablersp(operation) {

		if (Validationrsp() == 1) {
			return false;
		} else {
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}
var item_dual_stock= $("#item_dualStockRequired_req_ret").val();
	console.log("call");
	//$("#saletabitemdetails").text("");
	var itemid = $("#item_id_rsp").val();

	if (Number(itemid) == 0) {
		return false;
	}
	console.log("itemid=" + itemid);
	if (item_dual_stock==1) {
		var retamt = ($('#item_rpqty_rsp').val()  * $('#item_rateperloose_rsp').val());
	}else {
		var retamt = (($('#item_rpqty_rsp').val() * $('#item_conv_rsp').val()) + Number($('#item_rlqty_rsp').val())) * $('#item_rateperloose_rsp').val();
	}


	var item_disper_rsp=$("#item_disper_rsp").val();
	var item_taxPercentage_rsp=$("#item_taxPercentage_rsp").val();
	retamt=Number(retamt)-((Number(retamt)*Number(item_disper_rsp))/100);
	console.log("disc retamt="+retamt);
	//$("#" + itemid + "_retamt").text(parseFloat(retamt).toFixed(2));
	console.log("item_taxPercentage_rsp="+item_taxPercentage_rsp);
	var gstamt = parseFloat(retamt * item_taxPercentage_rsp / 100).toFixed(4);
	console.log(gstamt);
	var isexclu = $("#isexclusiversp").val();
	if (isexclu == 1) {
		retamt=(parseFloat(Number(retamt) + Number(gstamt)).toFixed(4));
	}

	var itempresent = 0;
	if (operation == 1) { //edit
		$("#item_id_rsp").val(itemid);
		$("#salretitem tr#" + itemid).find('#salerettabpqty').text($("#item_rpqty_rsp").val());
		$("#salretitem tr#" + itemid).find('#salerettablqty').text($("#item_rlqty_rsp").val());
		$("#salretitem tr#" + itemid).find('#salerettabfqty').text($("#item_rfqty_rsp").val());
		//$("#salretitem tr#" + itemid).find('#salerettabitemTaxAmount').text(parseFloat(gstamt).toFixed(4));
		//$("#salretitem tr#" + itemid).find('#salerettabtotamt').text(parseFloat(retamt).toFixed(4));
	    $("#salretitem tr#" + itemid).find('#salerettabitemTaxAmount').text(parseFloat($("#item_CalcTaxAmt_rsp").val()).toFixed(4));
		$("#salretitem tr#" + itemid).find('#salerettabtotamt').text(parseFloat($("#item_netamt_rsp").val()).toFixed(4));

		$("#salretitem tr#" + itemid).find('#salerettabitemlotadjasment').text(parseFloat($("#item_lotadjasment_rsp").val()).toFixed(4));


		//$("#salretitem tr#" + itemid).find('#salerettabitemslnolist').text($("#allValrsp").val());
		$("#salretitem tr#" + itemid).find('#checkedsalerettabitemslnolist').text($("#allValrsp").val());
	} else { // add

		/*$('#selitem > tbody  > tr').each(function() {
			console.log("tbl_itemid=" + this.id);
			console.log("itemid=" + itemid);
			if (Number(this.id) == Number(itemid)) {
				itempresent = 1;
			}
		});
		}
		console.log("itempresent=" + itempresent);
		if (itempresent == 1) {
		$('#itemExistsModal').modal('show');
		} else {
		if ($("#item_sche").val() == 'H1' || $("#item_sche").val() == 'X') {
			$('#scheleXorH1Modal').modal('show');
			$('#operationtype').val(operation);
		} else {
			addItemtotable(operation);
		}

		}*/
	}
	clearHeaderDivrsp();
	calculateTotalltadjrsp();
	calculateNetTotalrsp();
	calculateTotalVatrsp();
	calculateTotalTaxrsp();
	calculateTotalGSTrsp();
	calculateSpclDiscrsp();
}
//item_looseqty change calculation
$("#item_rlqty_rsp").keyup(function() {
	var inputlqty = $(this).val();
	var inputpqty = $("#item_rpqty_rsp").val();
	var lqty = $("#item_rlqty_hide_rsp").val();
	var pqty = $("#item_rpqty_hide_rsp").val();
	var conv = $("#item_conv_rsp").val();
	var item_taxPercentage_rsp = $("#item_taxPercentage_rsp").val();
	var item_dis = $("#item_disper_rsp").val();
	var totlqty = (pqty * conv) + Number(lqty);
	var inputtotlqty = (inputpqty * conv) + Number(inputlqty);
	var billpqty = $("#item_pqty_rsp").val();
	var billlsqty = $("#item_lqty_rsp").val();
	var billtotlqty = (billpqty * conv) + Number(billlsqty);
	var totremqty=Number(billtotlqty)+Number(totlqty);
	console.log("inputtotlqty="+inputtotlqty);
	console.log("totremqty="+totremqty);
	/*if (totlqty > billtotlqty) {
		$("#currStkGraterModal_rsp").modal("show");
	}*/
	if (inputtotlqty > totremqty) {
		$("#currStkGraterModal_rsp").modal("show");
	}
	var item_rate_ls = $('#item_rateperloose_rsp').val();
	//var total = parseFloat(inputtotlqty * item_rate_ls).toFixed(4);
	var retamt = inputtotlqty * item_rate_ls;
	var discamt = retamt * item_dis / 100;
	console.log("discamt="+discamt);
	$("#item_discamt").val(parseFloat(discamt).toFixed(4));
	retamt = retamt - discamt;
	console.log("after disc retamt="+retamt);
	$("#item_netamt_rsp").val(parseFloat(retamt).toFixed(2));
	var gstamt = parseFloat(retamt * item_taxPercentage_rsp / 100).toFixed(4);
	console.log("gstamt="+gstamt);
	$("#item_CalcTaxAmt_rsp").val(parseFloat(gstamt).toFixed(4));
	var isexclu = $("#isexclusiversp").val();
	if (isexclu == 1) {
		$("#item_netamt_rsp").val(parseFloat(Number(retamt) + Number(gstamt)).toFixed(4));
	}

});

//item_packqty change calculation
$("#item_rpqty_rsp").keyup(function() {
	var inputpqty = $(this).val();
	if (inputpqty>0) {
		var itemid = $("#item_id_rsp").val();
		console.log("itemid="+itemid);
		var inputlqty = $("#item_rlqty_rsp").val();
		var lqty = $("#item_rlqty_hide_rsp").val();
		var pqty = $("#item_rpqty_hide_rsp").val();
		var slno = $("#item_serialNo_req_ret").val();
		var conv = $("#item_conv_rsp").val();
		var item_taxPercentage_rsp = $("#item_taxPercentage_rsp").val();
		var item_dis = $("#item_disper_rsp").val();
		var isexclu = $("#item_taxTypeId_rsp").val();
		var billpqty = $("#item_pqty_rsp").val();
		var billlsqty = $("#item_lqty_rsp").val();
		var item_free=$("#item_rfqty_rsp").val();
		var item_dual_stock= $("#item_dualStockRequired_req_ret").val();



	if (item_dual_stock==1) {
			var totlqty = parseFloat(pqty)+parseFloat(lqty);
			var inputtotlqty = parseFloat(inputpqty );
			var billtotlqty = parseFloat(billpqty );
	}else
	{
	var totlqty = (pqty * conv) + Number(lqty);
	var inputtotlqty = (inputpqty * conv) + Number(inputlqty);
	var billtotlqty = (billpqty * conv) + Number(billlsqty);

	}
		//var totlqty = (pqty * conv) + Number(lqty);

		var totremqty=Number(billtotlqty)+Number(totlqty);
		if (inputtotlqty > totremqty) {
			$("#currStkGraterModal_rsp").modal("show");
		}else{
		if(slno==1){
			for ( var i = 1; i <= 30; i++) {
				$("#rspcheckbox"+i).attr('checked', false);
			}
			var slnos=$("#salretitem tr#" + itemid).find('#salerettabitemslnolist').text();
		    var sno=slnos.split(",");
		    var sllen=sno.length;
		    var slnos1=$("#salretitem tr#" + itemid).find('#checkedsalerettabitemslnolist').text();
		    var sno1=slnos1.split(",");
		    for(var m=0;m<sno.length;m++){
		    	if(sno[m]!=""){
		    		for(var n=0;n<sno1.length;n++){
		    			if(sno[m]==sno1[n]){
					$("#rspcheckbox"+(m+1)).prop("checked",true);
					$("#rsptextbox"+(m+1)).val(sno1[n]);
					$("#TextBoxDivrsp_"+(m+1)).removeClass("hide");
					break;
		    		}else{
		    			$("#rspcheckbox"+(m+1)).prop("checked",false);
						$("#rsptextbox"+(m+1)).val(sno[m]);
						$("#TextBoxDivrsp_"+(m+1)).removeClass("hide");
		    		}
		    	}
		    		sllen=m;
		    	}else{
		    	}
			}
		    for ( var i = (sllen+1); i < 30 - (sllen+1); i++) {
				$("#TextBoxDivrsp_"+(i+1)).addClass("hide");
			}
			$("#openSerialModalrsp").modal("show");
		}
		}
		/*if (totlqty > billtotlqty) {
			$("#currStkGraterModal_rsp").modal("show");
		}*/

//		var retamt = totlqty * $('#item_rateperloose_rsp').val();
		var item_rate_ls = $('#item_rateperloose_rsp').val();
		//var total = parseFloat(inputtotlqty * item_rate_ls).toFixed(4);
		var retamt = inputtotlqty * item_rate_ls;
		console.log("retamt="+retamt);
		console.log("item_dis="+item_dis);
		var discamt = retamt * item_dis / 100;
		console.log("discamt="+discamt);
		$("#item_discamt").val(parseFloat(discamt).toFixed(4));
		retamt = retamt - discamt;
		console.log("after disc retamt="+retamt);
		$("#item_netamt_rsp").val(parseFloat(retamt).toFixed(2));
		var gstamt = parseFloat(retamt * item_taxPercentage_rsp / 100).toFixed(4);
		console.log(gstamt);
		$("#item_CalcTaxAmt_rsp").val(parseFloat(gstamt).toFixed(4));

		if (isexclu == 2) {
			$("#item_netamt_rsp").val(parseFloat(Number(retamt) + Number(gstamt)).toFixed(4));
		}

		/*
		 * for lot adjasment
		 */
		itemlotAdjasmentCal(item_free,inputtotlqty,item_rate_ls,item_dis,item_taxPercentage_rsp,'item_CalcTaxAmt_rsp','item_netamt_rsp','item_lotadjasment_rsp',isexclu,1);

	}

});


//item_packqty change calculation
$("#item_rfqty_rsp").keyup(function() {

	var  item_free= $(this).val();
	var pqty = $("#item_rpqty_rsp").val();
	if (pqty>0) {// when pack qty is geterthen zero

          if (item_free<= $("#item_rfqty_hide_rsp").val()) {
        	  var itemid = $("#item_id_rsp").val();
        	var item_dual_stock= $("#item_dualStockRequired_req_ret").val();
        	var conv = $("#item_conv_rsp").val();
        	var lqty = $("#item_rlqty_hide_rsp").val();
        	var item_taxPercentage_rsp = $("#item_taxPercentage_rsp").val();
        	var item_dis = $("#item_disper_rsp").val();
        	var item_rate_ls = $('#item_rateperloose_rsp').val();
        	var isexclu = $("#item_taxTypeId_rsp").val();
        			if (item_dual_stock==1) {
        		     var totlqty = (pqty);

        			 }else {
        		    var totlqty = (pqty * conv) + Number(lqty);

        			}
        	itemlotAdjasmentCal(item_free,totlqty,item_rate_ls,item_dis,item_taxPercentage_rsp,'item_CalcTaxAmt_rsp','item_netamt_rsp','item_lotadjasment_rsp',isexclu,1);


		  }else {// when fqty greater then sale fqty
			  $("#item_rfqty_rsp").val(0.0);
		}

	}else {// when no pack qty
		$("#item_rfqty_rsp").val(0.0);
	}


});


/*
 *  for lot adjasment
 */
function itemlotAdjasmentCal(item_free,totlqty,item_rate_ls,item_dis,item_taxPer,itemgstAmt_id,itemNetTot_id,item_lotadjs_amt_id,is_exclu,type)
{
		var discamt=0.0;
		var gstamt=0.0;
		var temp_free=Math.floor(item_free);
		item_free = item_free - temp_free;
		if(((item_free % 1)!=0) || item_free<1)
		{
			// LtAdj calculation start
			var totalret = parseFloat(totlqty * item_rate_ls).toFixed(4);
			var modFree =  item_free % 1;
			var pqtyPerFree = totlqty/modFree;
			ltadj = totalret/pqtyPerFree;
			$("#"+item_lotadjs_amt_id).val(parseFloat(ltadj).toFixed(4));
			// LtAdj calculation end
			discamt = (totalret-ltadj) * item_dis / 100;
			//$("#item_discamt").val(parseFloat(discamt).toFixed(4));
			var temp_retamt=(totalret-ltadj-discamt);
			gstamt = parseFloat((temp_retamt) * item_taxPer / 100).toFixed(4);
			$("#"+itemgstAmt_id).val(parseFloat(gstamt).toFixed(4));

			if (type==1) {// for  value set in field
				$("#"+itemNetTot_id).val(parseFloat(Number(temp_retamt)).toFixed(2));
				/*var isexclu =is_exclu;*/
				   if (is_exclu == 2) {
					$("#"+itemNetTot_id).val(parseFloat(Number(temp_retamt) + Number(gstamt)).toFixed(2));
					}
			}else {// for value set in text
				$("#"+itemNetTot_id).text(parseFloat(Number(temp_retamt)).toFixed(2));
				//var isexclu = $("#"+is_exclu).val();
				   if (is_exclu == 2) {
					$("#"+itemNetTot_id).text(parseFloat(Number(temp_retamt) + Number(gstamt)).toFixed(2));
					}
			}

		}
}

function okSlNorsp(){
	var length=$("input:checkbox[class=chkrsp]:checked").length ;
	//alert("length="+length);
	var arr=[];
	var arrchk=[];
	var srate=[];
    $("input:checkbox[class=chkrsp]:checked").each(function () {
//        alert("Id: " + $(this).attr("id") + " Value: " + $(this).val());
       // alert("textbox val="+$("#rsptextbox"+$(this).val()).val());
        arr.push($("#rsptextbox"+$(this).val()).val());
        arrchk.push($(this).attr("id"));
//        srate.push($("#slrate"+$(this).val()).val());
    });
    $("#allValrsp").val(arr);
    $("#allChkIDrsp").val(arrchk);
    $("#item_rpqty_rsp").val(length);
    /*var maxslrate=Math.max.apply(Math, srate);
   // alert("maxslrate="+maxslrate);
    if(maxslrate==-Infinity){
    	maxslrate=0.00;
    }
    $("#maxSlRate").val(maxslrate);*/

    var inputpqty = length;
	var itemid = $("#item_id_rsp").val();
	console.log("itemid="+itemid);
	var inputlqty =0;// $("#item_rlqty_rsp").val();
	var lqty = $("#item_rlqty_hide_rsp").val();
	var pqty = $("#item_rpqty_hide_rsp").val();
	var slno = $("#item_serialNo_req_ret").val();
	var conv = $("#item_conv_rsp").val();
	var item_taxPercentage_rsp = $("#item_taxPercentage_rsp").val();
	var item_dis = $("#item_disper_rsp").val();
	var totlqty = (pqty * conv) + Number(lqty);
	var inputtotlqty = (inputpqty * conv) + Number(inputlqty);
	var billpqty = $("#item_pqty_rsp").val();
	var billlsqty = 0;//$("#item_lqty_rsp").val();
	var billtotlqty = (billpqty * conv) + Number(billlsqty);
	console.log("billtotlqty="+billtotlqty);
	var totremqty=Number(billtotlqty)+Number(totlqty);

//	if (inputtotlqty > totremqty) {length
		if (Number(length) > Number(totremqty)) {
		$("#openSerialModalrsp").modal("hide");
		$("#currStkGraterModal_rsp").modal("show");
	}
//	var retamt = totlqty * $('#item_rateperloose_rsp').val();
	var item_rate_ls = $('#item_rateperloose_rsp').val();
	//var total = parseFloat(inputtotlqty * item_rate_ls).toFixed(4);
	var retamt = inputtotlqty * item_rate_ls;
	console.log("retamt="+retamt);
	console.log("item_dis="+item_dis);
	var discamt = retamt * item_dis / 100;
	console.log("discamt="+discamt);
	$("#item_discamt").val(parseFloat(discamt).toFixed(4));
	retamt = retamt - discamt;
	console.log("after disc retamt="+retamt);
	$("#item_netamt_rsp").val(parseFloat(retamt).toFixed(2));
	var gstamt = parseFloat(retamt * item_taxPercentage_rsp / 100).toFixed(4);
	console.log(gstamt);
	$("#item_CalcTaxAmt_rsp").val(parseFloat(gstamt).toFixed(4));
	var isexclu = $("#isexclusiversp").val();
	if (isexclu == 1) {
		$("#item_netamt_rsp").val(parseFloat(Number(retamt) + Number(gstamt)).toFixed(4));
	}

	$("#openSerialModalrsp").modal("hide");
}

var pqty_field = $("#pqty_label").text();

var lqty_field = $("#lqty_label").text();

$("#item_rpqty_rsp").keyup(function() {
	if (isNaN($("#item_rpqty_rsp").val())) {
		document.getElementById('alertMsg_rsp').innerHTML = getFieldText.numberCheck + " in "+pqty_field;
		$(this).focus();
		return false;
	} else {
		if($("#item_rpqty_rsp").val()<0)
		{
			document.getElementById('alertMsg_rsp').innerHTML = pqty_field+" "+getFieldText.checkNegative;
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}
	}
});

$("#item_rlqty_rsp").keyup(function() {
	if (isNaN($("#item_rlqty_rsp").val())) {
		document.getElementById('alertMsg_rsp').innerHTML = getFieldText.numberCheck + " in "+lqty_field;
		$(this).focus();
		return false;
	} else {
		if($("#item_rlqty_rsp").val()<0)
		{
			document.getElementById('alertMsg_rsp').innerHTML = lqty_field+" "+getFieldText.checkNegative;
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}
	}
});

function closeCurrStkModalrsp() {
	$("#item_rpqty_rsp").val(0);
	$("#item_rlqty_rsp").val(0);
	$("#item_netamt_rsp").val(parseFloat(0).toFixed(2));
}



function getItemDetailsrsp(itemid) {
	$("#itemsearchmodtbody_rsp").text("");
	$('#pleasewaitModal').modal('show');
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.itemId = itemid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/getretrsalealldetbyitem.htm", CommonRelsetmapperObj, function(response) {
		console.log("res=" + response);
		$('#pleasewaitModal').modal('hide');
		var saledetails = JSON.parse(response);
		if (saledetails.length == 0) {
			$("#itemsaledetnotfounddiv_rsp").removeClass("hide");
			$("#itemsaledetmodtable_rsp").addClass("hide");
		} else {
			$("#itemsaledetnotfounddiv_rsp").addClass("hide");
			$("#itemsaledetmodtable_rsp").removeClass("hide");
			$("#searchmodtable_rsp").html('');

			for ( var i = 0; i < saledetails.length; i++) {
				var saledetail = saledetails[i];
				$("#itemsaledetailitemname_rsp").html(saledetail.itemName);
				var starttrline = "<tr id=" + saledetail.itemUniqueKey + " style='cursor: pointer;' onclick='insertModDatarsp(" + JSON.stringify(saledetail) + ")'>";
				var saleInvNo = "<td>" + saledetail.saleInvNo + "</td>";
				var saleInvDate = "<td>" + moment(saledetail.saleInvDate).format('YYYY-MM-DD') + "</td>";
				var customerPhone = "<td>" + saledetail.customerPhone + "</td>";
				var customerName = "<td>" + saledetail.customerName + "</td>";
                var tempSaleManCom=saledetail.salesmanId+"_"+saledetail.salesmanFactor+'_'+saledetail.salesmanAmount;
                var isexclu =saledetail.taxTypeId;
				if (is_salemanflag==1) {
					var salemansname="<td  > <input type='hidden' id='saletabsalesmanIdComPer'  value='"+tempSaleManCom+"'> " + saledetail.salesmanName + "</td>";
				}else {
					var salemansname="<td class='hide'> <input type='hidden' id='saletabsalesmanIdComPer'  value='"+tempSaleManCom+"'> " + saledetail.salesmanName + "</td>";
				}

//				var doctorName = "<td>" + saledetail.doctorName + "</td>";
				var batch = "<td>" + saledetail.batchNo + "</td>";
				var exp = "<td>" + saledetail.expiryDateFormat + "</td>";
				var packQty = "<td>" + saledetail.packQty + "</td>";
			    if ($("#is_free_rsp").val()==1) {
	             var freeQty= "<td  class='' id='searchtabfree' >" + saledetail.freeQty + "</td>";
	             }else {
					var freeQty= "<td  class='hide' id='searchtabfree' >" + saledetail.freeQty + "</td>";
					}
//				var looseQty = "<td>" + saledetail.looseQty + "</td>";
				var inputPackQty = "<td class='hide'><input type='hidden' value='" + saledetail.packQty + "' id='" + saledetail.itemUniqueKey + "_irpqty'/>" +
						"<input type='hidden' value='" + saledetail.itemTaxAmount + "' id='" + saledetail.itemUniqueKey + "_irtaxpercentage'/>" +
						"<input type='hidden' value='" + saledetail.freeQty + "' id='" + saledetail.itemUniqueKey + "_irfqty'/>" +
						"<input type='hidden' value='" + saledetail.itemLotAdjAmount + "' id='" + saledetail.itemUniqueKey + "_iritemlotadjasment'/></td>";
//				var inputLooseQty = "<td class='hide'><input type='hidden' value='" + saledetail.looseQty + "' id='" + saledetail.itemUniqueKey + "_irlqty'/></td>";
				var mrp = "<td>" + parseFloat(saledetail.mrp).toFixed(2) + "</td>";//
				var rate = "<td>" + parseFloat(saledetail.rate).toFixed(2) + "</td>";
				var calretamtrsp=0;
				var calprelseqty=(saledetail.prevReturnPackQty * saledetail.conversion)+saledetail.prevReturnLooseQty+saledetail.calculateLooseQty;
				if (isexclu == 2) {

					calretamtrsp=parseFloat(((saledetail.calculateLooseQty * saledetail.ratePerUnit)-saledetail.itemLotAdjAmount)+Number(saledetail.itemTaxAmount)-Number(saledetail.disc)).toFixed(2);
				}else{
					calretamtrsp=parseFloat(((saledetail.calculateLooseQty * saledetail.ratePerUnit)-saledetail.itemLotAdjAmount)-Number(saledetail.disc)).toFixed(2);
				}
				var retamt = "<td id='" + saledetail.itemUniqueKey + "_retamt'>" + parseFloat(calretamtrsp).toFixed(2) + "</td>";
//				var retamt = "<td id='" + saledetail.itemUniqueKey + "_retamt'>" + parseFloat(0).toFixed(2) + "</td>";
				var specialDiscPer = "<td style='background-color: yellow;'>" + parseFloat(saledetail.specialDiscPer).toFixed(2) + "</td>";
				var endtrline = "</tr>";
//				createdrowline = starttrline + saleInvNo + saleInvDate + customerPhone + customerName + doctorName + batch + exp + packQty + looseQty + inputPackQty + inputLooseQty + mrp + rate + retamt+specialDiscPer + endtrline;
				createdrowline = starttrline + saleInvNo + saleInvDate +salemansname+ customerPhone + customerName + batch + exp + packQty +freeQty + inputPackQty  + mrp + rate + retamt+specialDiscPer + endtrline;
				$("#itemsearchmodtbody_rsp").append(createdrowline);
			}
		}
	});
	$('#itemsaledetailModal_rsp').modal('show');
}

/*
 * for item wise search and insert
 */
function insertModDatarsp(saledetail){
	insertModDatatoRetTable(saledetail,0);
	$('#itemsaledetailModal_rsp').modal('hide');
	calculateTotalVatrsp();
	calculateTotalTaxrsp();
	calculateTotalGSTrsp();
	calculateTotalltadjrsp();
	calculateNetTotalrsp();
	calculateSpclDiscrsp();
	clearHeaderDivrsp();
}

function saveOrUpdateSaleReturnInv() {

	if ($("#item_id_rsp").val() == 0 || $("#item_id_rsp").val() == "") {
		var saleretid = $("#saleretId").val();
		var salereturn = {};
		var allsaleretdetails = [];
		var selitemlength = $('#salretitem >tbody >tr').length;
		$('#salretitem > tbody  > tr').each(function(i) {
			var saleretdetails = {};
			var itemid = this.id;
			//var totlooseqty = (parseFloat($(this).find("#saletabpqty").text()) * parseFloat($(this).find("#saletabconv").text())) + parseFloat($(this).find("#saletablqty").text());
			saleretdetails.itemId = itemid.split("_")[0];
			saleretdetails.batchNo = $(this).find("#salerettabbat").text();
			saleretdetails.expiryDateFormat = $(this).find("#salerettabexpdt").text();
			saleretdetails.expiryDate = $(this).find("#salerettabexpdate").text();
			saleretdetails.packUnitId = $(this).find("#salerettabpunitid").text();
			saleretdetails.packQty = $(this).find("#salerettabpqty").text();
			saleretdetails.looseQty =0;// $(this).find("#salerettablqty").text();
			saleretdetails.looseUnitId = $(this).find("#salerettablunitid").text();
			saleretdetails.conversion = $(this).find("#salerettabconv").text();
			saleretdetails.mrp = ($(this).find("#salerettabmrppack").text()=="")?0:$(this).find("#salerettabmrppack").text();
			saleretdetails.mrpPerUnit = $(this).find("#salerettabmrp").text();// parseFloat(parseFloat($(this).find("#saletabmrppack").text())/ parseFloat($(this).find("#saletabconv").text())).toFixed(2);
			saleretdetails.ratePerUnit = $(this).find("#salerettabrateperunit").text();
			saleretdetails.rate = $(this).find("#salerettabrate").text();
			saleretdetails.vat = $(this).find("#salerettabvatamt").text();
			saleretdetails.vatPer = $(this).find("#salerettabvat").text();
			saleretdetails.disc = $(this).find("#salerettabdisamt").text();
			saleretdetails.discPer = $(this).find("#salerettabdiscperc").text();
			saleretdetails.totAmount = $(this).find("#salerettabtotamt").text();
			saleretdetails.saleId = $(this).find("#salerettabsaleid").text();
			saleretdetails.saleInvNo = $(this).find("#salerettabsaleinvno").text();
//			saleretdetails.sku = $(this).find("#salerettabsku").text();
			saleretdetails.taxId = $(this).find("#salerettabtaxId").text();
			saleretdetails.taxPercentage = $(this).find("#salerettabtaxPercentage").text();
			saleretdetails.taxAmount = $(this).find("#salerettabitemTaxAmount").text();
			saleretdetails.isGroupTax = $(this).find("#salerettabisGroupTax").text();
			saleretdetails.taxMode = $(this).find("#salerettabtaxMode").text();
			saleretdetails.reasonId = 0;
			saleretdetails.storeId = storeId;
			saleretdetails.finyrId = finyrId;
			saleretdetails.companyId = cmpnyId;
			saleretdetails.createdBy = createdBy;
			saleretdetails.itemdualstock =  $(this).find("#salerettabitemdualStockRequiredreq").text();
            saleretdetails.taxTypeId =  $(this).find("#salerettabtaxTypeId").text();
			//sales man
			saleretdetails.salesmanId=$(this).find("#salerettabsalesmanIdComPer").text().split("_")[0];
			saleretdetails.salesmanFactor=$(this).find("#salerettabsalesmanIdComPer").text().split("_")[1];
			saleretdetails.salesmanAmount=$(this).find("#salerettabsalesmanIdComPer").text().split("_")[2];
			saleretdetails.itemLotAdjAmount=$(this).find("#salerettabitemlotadjasment").text();
			saleretdetails.freeQty=$(this).find("#salerettabfqty").text();
			saleretdetails.itemFreeAgainstItem=$(this).find("#salerettabitemagainstitem").text();
			saleretdetails.amount = parseFloat(Number($(this).find("#salerettabpqty").text()) * Number($(this).find("#salerettabrateperunit").text())).toFixed(2); // new added 12.7.2109
			// serialno add

			salesRetDetailsSerialMapperObjArr=[];
//			var slnos=$(this).find('#salerettabitemslnolist').text();
			var slnos=$(this).find('#checkedsalerettabitemslnolist').text();
			var slnoarr=slnos.split(",");
			saleretdetails.tmpMappingId=(i);
			for(var h=0;h<slnoarr.length;h++){
				var salesRetDetailsSerialMapperObj={};
				salesRetDetailsSerialMapperObj.itemId=itemid.split("_")[0];
				salesRetDetailsSerialMapperObj.batchNo=$(this).find('#salerettabbat').text();
				salesRetDetailsSerialMapperObj.uniqueIdentifierNo=slnoarr[h];
				salesRetDetailsSerialMapperObj.qty=$(this).find('#salerettabpqty').text();
				salesRetDetailsSerialMapperObj.tmpMappingId=(i);
				salesRetDetailsSerialMapperObj.checkStatus=1;//slnoschkarr[h];
				salesRetDetailsSerialMapperObjArr.push(salesRetDetailsSerialMapperObj);
			}
			saleretdetails.saleReturnDetailsSerialMappers=salesRetDetailsSerialMapperObjArr;

			// slno end

			allsaleretdetails.push(saleretdetails);
		});
		salereturn.saleReturnDetails = allsaleretdetails;
		salereturn.id = saleretid;
		if (saleretid == 0) {// create

		} else {// edit
			salereturn.invNo = $("#saleretinvno").val();
		}

		salereturn.invDate = $("#datersp").val();
		if ($("#saleretcustname").val() == "") {
			salereturn.customerId = 0;
		} else {
			salereturn.customerId = $("#saleretcustid").val();
			salereturn.customerName = $("#saleretcustname").val();
			if ($("#saleretcustph").val() == "") {
				salereturn.customerPhone = 0000000000;
			} else {
				salereturn.customerPhone = $("#saleretcustph").val();
			}

		}

		if ($("#saleretdocname").val() == "") {
			salereturn.doctorId = 0;
		} else {
			salereturn.doctorId = $("#saleretdocid").val();
			salereturn.doctorName = $("#saleretdocname").val();
		}
		salereturn.grossAmount = $("#totgrossamt_rsp").val();
		salereturn.vatAmount =$("#totvatamt_rsp").val();
		salereturn.taxAmount =$("#tottaxamt_rsp").val();
		salereturn.discAmount =0;// $("#totdiscamt").val();
		salereturn.roundoff = $("#roundoff_rsp").val();
		salereturn.netAmount = $("#totretamt_rsp").val();
		salereturn.remarks = $("#remarks_rsp").val();
		salereturn.lotAdjAmount = $("#totlotadjasment_rsp").val();


		if($("#spcldiscperc_rsp").val()==""|| $("#spcldiscperc_rsp").val()==null){
			salereturn.specialDiscPer = 0;
		}else{
			salereturn.specialDiscPer = $("#spcldiscperc_rsp").val();
		}


		salereturn.specialDiscAmount = $("#spcldisc_rsp").val();
		/*salereturn.creditAmount = $("#creditAmt").val();
		sale.tenderAmount = $("#tenderamt").val();
		sale.cashAmount = $("#cashAmt").val();
		sale.cardAmount = $("#cardAmt").val();
		sale.cardExpDate =$("#cardExpMod").val();
		sale.cardFourDigit =$("#cardFourDigitMod").val();*/
		salereturn.isposted = 0;
//		salereturn.invMode = "Credit";
		salereturn.sale_account_credit_amt= $('#sale_account_rsp').val();
		salereturn.debitor_credit_amt= $('#debitor_amt_rsp').val();

	/*	salereturn.duties_ledger_id= $('#duties_ledger_idf').val();
		salereturn.round_ledger_id= $('#round_ledger_idf').val();
		salereturn.sales_ledger_id= $('#sales_ledger_idf').val();
		salereturn.debitor_ledger_id= $('#debitor_ledger_idf').val();*/
		salereturn.duties_ledger_id= parseInt(isEmpty($('#duties_ledger_idf').val())==true?0:$('#duties_ledger_idf').val());
		salereturn.round_ledger_id= parseInt(isEmpty($('#round_ledger_idf').val())==true?0:$('#round_ledger_idf').val());
		salereturn.sales_ledger_id= parseInt(isEmpty($('#sales_ledger_idf').val())==true?0:$('#sales_ledger_idf').val());
		salereturn.debitor_ledger_id= parseInt(isEmpty($('#debitor_ledger_idf').val())==true?0:$('#debitor_ledger_idf').val());
		salereturn.lotAdjAmountId= parseInt(isEmpty($('#lotAdjasment_ledger_idf').val())==true?0:$('#lotAdjasment_ledger_idf').val());




		console.log("sale json: " + JSON.stringify(salereturn));
		
		if (selitemlength > 0) {
			$('#pleasewaitModal').modal('show');
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/createorupdatesalesreturn.htm", salereturn, function(response) {
				$('#pleasewaitModal').modal('hide');
				//alert("save saleret inv id=" + response);
				if (response == '0') {
					document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.dataNotAdd;
					$("#confirmvalrsp").val(0);
					showConfirmModal();
				} else {
					/*document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.dataSucAdd;
					$("#confirmvalrsp").val(response);
					showConfirmModal();*/
				//	alert("cv="+$("#confirmvalrsp").val());
					if($("#confirmvalrsp").val()==0||$("#confirmvalrsp").val()==""){
						$("#confirmvalrsp").val(response);
					}else{

						$("#confirmvalrsp").val($("#confirmvalrsp").val()+","+response);
				//		alert("cv111="+$("#confirmvalrsp").val());
					}
					$("#retamtsamepage").val(0);
					$("#confirmvalrspsamepage").val(Number($("#totretamt_rsp").val()));
					$('#confirmPrintSaleReturnModalrsp').modal('show');
				}

			});
		}
	} else {
		$("#confirmvalrsp").val(-1);
		document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.addEditChckBefrSave;
		showConfirmModal();
	}
}
function openRetMemo(){
	location.href = BASE_URL + '/retunmemo/loadreturnmemo.htm';
}

function targetURLrsp() {
	console.log("con val=" + $("#confirmvalrsp").val());
	if ($("#confirmvalrsp").val() == 0) {
		clearHeaderDivrsp();
		location.href = BASE_URL + '/retunmemo/loadreturnmemo.htm';
	} else if ($("#confirmvalrsp").val() == -1) {
		location.href = "#";
	} else {

		if ($('input[name=printSaleReturn]').is(":checked"))
		{
			location.href = BASE_URL + "/retunmemo/printreturnmemo.htm?backUrl=loadreturnmemo&saleRetId=0954fg-hjk4565wer-"+$("#confirmvalrsp").val()+"-kjsa45-uyt6yu-9811fnczas";
		}
		else
		{
			location.href = BASE_URL + '/retunmemo/loadreturnmemo.htm?saleRetId=0954fg-hjk4565wer-' + $("#confirmvalrsp").val() + '-kjsa45-uyt6yu-9811fnczas';
		}
	}
}
function targetURLrspsamepage(){
	var cval=$("#confirmvalrsp").val();
	var cvalsp=$("#confirmvalrspsamepage").val();
	//alert("cval="+cval);
	//alert("$('#myTabs a:first')");
	//alert("1st val="+$("#retamtvalsamepage").val());
	//alert("cvalsp="+cvalsp);
	$("#retamtvalsamepage").val(parseFloat(Number($("#retamtvalsamepage").val())+Number(cvalsp)).toFixed(2));
	//alert("2nd val="+$("#retamtvalsamepage").val());
	$("#payretadjamt").val(Number($("#payretadjamt").val())+Number(cvalsp));
	clearAllRetMemo();
	$('#myTabs a:first').tab('show');
	//location.href = "#";
}

function deleteRetSalesInv() {
	var saleretId=$("#saleretId").val();
	if(saleretId!=0){
		$('#confirmModalPos_rsp').modal('show');
	}
}
function DoConfirmPosrsp() {
	$('#pleasewaitModal').modal('show');
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.saleReturnId = $("#saleretId").val();

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/deleteretsalesinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.dataNotDelete;
			showConfirmModal();
		}

	});
}

function clearAllRetMemo(){
	clearHeaderDivrsp();
	$("#salerettabitemdetails").empty();
	$("#saleretId").val(0);
	$("#invnorsp").val("");
	$("#saleretcustph").val("");
	$("#saleretcustid").val(0);
	$("#saleretcustname").val("");
	$("#saleretdocname").val("");
	$("#saleretdocid").val(0);
	$('#footer_detail1').find('input:text').val('');
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

	if (para==2) { // for sales

		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;
	}

if (para==3) { // for debitor

		if (ref_id==0) {
			commonobj.groupCode=group_code;
			commonobj.accountID=0;
			commonobj.referenceID=0;
			commonobj.id=1;
		}else {
			commonobj.groupCode=group_code;
			commonobj.accountID=0;
			commonobj.referenceID=ref_id;
			commonobj.id=1;
		}

	}

if (para==4) { // for lot adjasment

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
			console.log("for duties and tax ");

			$.each(status, function(i) {

				// $('#duties_html').html(status[i].name+" (Debit) ");
				 $('#duties_ledger_idf').val(status[i].id);

			});
		}

		if (para==1) {// for round off
			console.log(" round off ");
			$.each(status, function(i) {

			//	 $('#round_html').html(status[i].name);
				 $('#round_ledger_idf').val(status[i].id);

			});
		}
	if (para==2) { // for sales

		console.log("for sales ");
				$.each(status, function(i) {

				//	 $('#sales_html').html(status[i].name+" (Debit) ");
					 $('#sales_ledger_idf').val(status[i].id);

				});
			}

	if (para==3) {// for debitor
		console.log("for debitor ");
		$.each(status, function(i) {

			 //$('#debitor_html').html(status[i].name +" (Credit) ");
			 $('#debitor_ledger_idf').val(status[i].id);

		});
}

	if (para==4) {// for debitor
		console.log("for lot adjasment ");
		$.each(status, function(i) {

			 //$('#debitor_html').html(status[i].name +" (Credit) ");
			 $('#lotAdjasment_ledger_idf').val(status[i].id);

		});
}


		//chngeResultStat(status);
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
function isEmpty(val) {

    return (val === undefined || val == null || val.trim().length <= 0) ? true : false;
}

