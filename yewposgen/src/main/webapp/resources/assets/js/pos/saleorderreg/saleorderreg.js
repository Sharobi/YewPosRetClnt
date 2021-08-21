function deletePurchaseOrder(poid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(poid);
	$("#confirmtype").val(0); // for delete set as zero
	
}

function DoConfirmPurOrder(){
	var poid=$("#confirmid").val();
	var type=$("#confirmtype").val();
	if(type==1){// for post
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purchaseOrderId = poid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/saleorder/postsaleorder.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			/*if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataSucPost;
				showConfirmModal();
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataNotPost;
				showConfirmModal();
			}*/
			
			var status = JSON.parse(response);
			chngeResultStat(status);

		});
	}
	if(type==0){// for delete
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purchaseOrderId = poid;
	
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/saleorder/deletesaleorder.htm", CommonRelsetmapperObj, function(response) {
			/*$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getPurOrderRegText.dataSucDelete;
				showConfirmModal();
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getPurOrderRegText.dataNotDelete;
				showConfirmModal();
			}*/
			var status = JSON.parse(response);
			chngeResultStat(status);
	
		});
	}
	if(type==2){// for close
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purchaseOrderId = poid;
	
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/saleorder/closesaleorder.htm", CommonRelsetmapperObj, function(response) {
			
			var status = JSON.parse(response);
			chngeResultStat(status);
	
		});
	}
}

function postPurchaseInv(pinvid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(pinvid);
	$("#confirmtype").val(1); // for post set as one
	
}

function closePurchaseOrder(pinvid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(pinvid);
	$("#confirmtype").val(2); // for close set as two
	
}

function targetURL(){
	location.href = BASE_URL + '/saleorder/loadsaleordregister.htm';
}



function sendMail(saleorderid,email){
	if(email=='' || email == 'NA'){
		var status ={};
	    status.id=-14;
		chngeResultStat(status);
	}else{
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/reprintmemo/saleorder.htm?reprint=N&backUrl=cashmemo&saleId=3465fg-trw73sxz-" + saleorderid + "-utew09-qdd55-4320jhhgrt", function(res) {
		res = res.replace(/<script/g,"<div class='hide'");
		res = res.replace(/script>/g,"div>");
		$("#printcontent").html(res);
		var mailBody = $("#printdiv").html();
		var mailSubjct = $("#allstorelists").find("option:selected").text()+' Sale Order Invoice';
		var emailBeanObj = {};
		emailBeanObj.toAddr = email;
		emailBeanObj.subject = mailSubjct;
		emailBeanObj.messageBody = mailBody;
		emailBeanObj.transType = "SO";
		emailBeanObj.isAttachment = "N";
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/mail/sendhtmlmail.htm", emailBeanObj, function(response) {
			console.log("send mail response:"+response);
			var responseData = JSON.parse(response);
			$("#printcontent").html("");
			chngeResultStat(responseData);
		});
	});	
   }
}


/*function getBill(id){
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/reprintmemo/saleorder.htm?reprint=N&backUrl=searchsaleorderregister&saleId=3465fg-trw73sxz-"+id+"-utew09-qdd55-4320jhhgrt'", function(res) {
		res = res.replace(/<script/g,"<div class='hide'");
		res = res.replace(/script>/g,"div>");
		$("#billdata").html(res);
		$('#billModal').modal('show');
	});	
}
function printPdf(){
	kendo.drawing
	.drawDOM("#printdiv", 
	{ 
	paperSize: "A4",
	margin: { top: "1cm", bottom: "1cm" },
	scale: 0.8,
	height: 500
	})
	.then(function(group){
	kendo.drawing.pdf.saveAs(group, "Sale Order.pdf")
	});
	
}
function clearDiv(){
	$("#billdata").html('');
}*/


function saveBill(saleorderid,billtype){
	var pdfline= save_sale_order_url+"?cmpnyId="+companyId+"&storeId="+storeId+"&saleOrderId="+saleorderid+"&isReprint="+1+"&type="+billtype;
	window.open(
			pdfline,
			  '_blank' // <- This is what makes it open in a new window.
			);
}
