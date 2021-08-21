<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
.panel-green {
    border-color: #5cc16cc9;
}
.panel-green > .panel-heading {
    border-color: #5cc16cc9;
    color: white;
    background-color: #5cc16cc9;
}
 .panel-yellow {
    border-color: #75b1cc;
}
.panel-yellow > .panel-heading {
    border-color: #e2760cbd;
    color: white;
    background-color: #e2760cbd;
}


.panel-red {
    border-color: #5a7579;
}
.panel-red > .panel-heading {
    border-color: #5a7579;
    color: white;
    background-color: #5a7579;
}
.panel-primary {
    border-color: #486a88;
}
.panel-primary > .panel-heading {
    color: #fff;
    background-color: #486a88;
    border-color: #486a88;
    }


 .wrapperContent
 {
    padding-left: 12px;
  padding-right: 1px;
    margin-top: 68px;
 }

 .coldivider
 {
        margin-bottom: -2%;
    margin-right: -3%;
 }

 profit_and_loss{
  background-color: #ec1313;
    border-color: #ec1313;

}

 #main-content{
     margin-right: -4%;
 }

 canvas{
			-moz-user-select: none;
			-webkit-user-select: none;
			-ms-user-select: none;
		}


</style>

<c:set var="today" value="<%=new java.util.Date()%>" />
		<section class="wrapper wrapperContent" id="wrapperContainer">


            <!-- first row  start -->

            <div class="row">
		        <div class="col-lg-4 col-md-4 ">
		       	 <div class="form-group">
	    				<label for="startdate">From Date:</label>
	                       <input type="text" class="" id="startdate"  readonly   style="text-align: center;" value="${financialyear}">
	  				</div>
		        </div>
	         <div class="col-lg-4 col-md-4 ">
	         	<div class="form-group">
    				<label for="enddate">To Date :</label>
	         			 <input type="text" class="" id="enddate" onchange="getDataDetails()" style="text-align: center;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
	          	</div>
	          </div>
	 			<div class="col-lg-4 col-md-4 " >
					<div class="form-group">
	    				<label for="enddate">Store Name :</label>
	    				<select class=" " name="storelist"   onchange="getDataDetails()"  id="storelist"  style="height: 26px;">
			 			 <option value="0">Select...</option>
				 		   <c:forEach items="${allstorelist}" var="store">
				 		     <option value="${store.id}">${store.name }</option>
							</c:forEach>
		 				</select>

	 				</div>
	 			</div>
            </div>

        <%--    <div class="row">


         <!--  backup start here  -->

            <div class="col-lg-2 col-md-2 coldivider">
	                    <div class="panel panel-green">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                   <div><b>Inventory</b></div>
	                                </div>
	                            </div>
	                              <div class="row">
	                             	 <div class="col-xs-12 text-right">
										<div class="huge" id="inventory_stock">0</div>
                                      		 <span> ${cur}: </span>  <span id="inventory_valuation" class="count">0</span>
									 </div>
	                           	 </div>
	                           </div>
	                   <!--      <a href="#">
	                            <div class="panel-footer">
	                                <span class="pull-left">View Details</span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a> -->
	                    </div>
	                </div>

	             <div class="col-lg-2 col-md-2  coldivider" >
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                            		<img  style="width: 44px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/sale.png">
								</div>
                                <div class="col-xs-9 text-right">
                                    <div><b>Sale</b></div>
								</div>
                            </div>
							 <div class="row">
								<div class="col-xs-12 text-right">
								  <span> ${cur}: </span>  <span id="sale" class="count">0</span>
								</div>
                            </div>
                        </div>
                    <!--     <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>
                <div class="col-lg-2 col-md-2 coldivider">
	                    <div class="panel panel-red">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                               		<i class="fa fa-user fa-2x"" aria-hidden="true"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div><b>Purchase </b></div>
	                                  </div>
	                              </div>
	                              <div class="row">
									 <div class="col-xs-12 text-right">
										 <br>
									 	 <span> ${cur}: </span>  <span id="purchase" class="count">0</span>
									</div>
	                            </div>
	                        </div>
	                     <!--    <a href="#">
	                            <div class="panel-footer">
	                                <span class="pull-left">View Details</span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a> -->
	                    </div>
	                </div>
			  <div class="col-lg-2 col-md-2 coldivider">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
									 <i class="fa fa-bank fa-2x" aria-hidden="true" style="color:#ffffff"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div><b>Bank</b></div>
                                 </div>
                            </div>
                             <div class="row">
								 <div class="col-xs-12 text-right">
									<br>
                                  	<span> ${cur}: </span>  <span id="bank" class="count">0</span>
                                </div>
                            </div>
                        </div>
                     <!--    <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>

 	          <div class="col-lg-2 col-md-2 coldivider">
                    <div class="panel panel"  style="background-color: #ffa50069;">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                   <img  style="width: 44px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/cashinhand.png">
                                </div>
                                <div class="col-xs-9 text-right">
 									<div><b>Cash In Hand</b></div>
								 </div>
                            </div>

                              <div class="row">
								 <div class="col-xs-12 text-right">
									<span> ${cur}: </span>  <span id="cash_in_hand_dramt" class="count">0</span>
                                </div>
                            </div>
                        </div>
                    <!--     <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>
               	<div class="col-lg-2 col-md-2 coldivider">
                    <div class="panel panel" style="background-color: #ff000069;">
                        <div class="panel-heading ">
                            <div class="row">
                                <div class="col-xs-3">
                                       <i class="fa fa-shopping-cart fa-2x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
									 <div><b>Profit And Loss</b></div>
                                </div>
                            </div>
                             <div class="row">

                                <div class="col-xs-12 text-right">
                                  <br>
                                  <span> ${cur}: </span>  <span id="profit_loss" class="count">0</span>
                                </div>
                            </div>
                        </div>
                        <!-- <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>
           </div> --%>
 <!--./ first row  start -->
           <div class="row">
            <div class="panel panel-success" style="margin-bottom: 0;">
			       <div class="panel-heading"><center> Dharmatala Store</center></div>
			      			<div class="panel-body">

	          <div class="col-lg-4 "  style="margin-right: -2%;height: 214px;" >
	          	 <div class="panel panel-primary" style="width:96%;">
	          	 <canvas id="canvas" style="background-color: #31708f00;"></canvas>
	          	</div>
			 </div>
               <div class="col-lg-4 "  style="margin-right: 0%;height: 214px;">
                 <div class="panel panel-primary" style="width:96%;">
					<canvas id="paichart" style="background-color: #31708f00;"></canvas>
          		 </div>
                </div>

                 <div class="col-lg-4 " style="margin-left: -2%;">
						<div class="row">
					 <div class="col-lg-6 col-md-6  coldivider" >
					                    <div class="panel panel-yellow">
					                        <div class="panel-heading">
					                            <div class="row">
					                                <div class="col-xs-3">
					                            		<img  style="width: 44px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/sale.png">
													</div>
					                                <div class="col-xs-9 text-right">
					                                    <div><b>Sale</b></div>
													</div>
					                            </div>
												 <div class="row">
													<div class="col-xs-12 text-right">
													  <span> ${cur}: </span>  <span id="sale" class="count">0</span>
													</div>
					                            </div>
					                        </div>
					                    <!--     <a href="#">
					                            <div class="panel-footer">
					                                <span class="pull-left">View Details</span>
					                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					                                <div class="clearfix"></div>
					                            </div>
					                        </a> -->
					                    </div>
					                </div>

					          <div class="col-lg-6 col-md-6 coldivider">
	                    <div class="panel panel-red">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                               		<i class="fa fa-user fa-2x"" aria-hidden="true"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div><b>Purchase </b></div>
	                                  </div>
	                              </div>
	                              <div class="row">
									 <div class="col-xs-12 text-right">
										 <br>
									 	 <span> ${cur}: </span>  <span id="purchase" class="count">0</span>
									</div>
	                            </div>
	                        </div>
	                     <!--    <a href="#">
	                            <div class="panel-footer">
	                                <span class="pull-left">View Details</span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a> -->
	                    </div>
	                </div>

						</div>
						<div class="row">
						   <div class="col-lg-6 col-md-6 coldivider">
                    <div class="panel panel"  style="background-color: #ffa50069;">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                   <img  style="width: 44px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/cashinhand.png">
                                </div>
                                <div class="col-xs-9 text-right">
 									<div><b>Cash In Hand</b></div>
								 </div>
                            </div>

                              <div class="row">
								 <div class="col-xs-12 text-right">
									<span> ${cur}: </span>  <span id="cash_in_hand_dramt" class="count">805000</span>
                                </div>
                            </div>
                        </div>
                    <!--     <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>

                  <div class="col-lg-6 col-md-6 coldivider">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
									 <i class="fa fa-bank fa-2x" aria-hidden="true" style="color:#ffffff"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div><b>Bank</b></div>
                                 </div>
                            </div>
                             <div class="row">
								 <div class="col-xs-12 text-right">
									<br>
                                  	<span> ${cur}: </span>  <span id="bank" class="count">32423</span>
                                </div>
                            </div>
                        </div>
                     <!--    <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>

						</div>
                </div>

			      			</div>
			    </div>
                <!-- ./ first row  -->

           </div>
           <!--  for store 2 -->
			<div class="row">
	           <div class="panel panel-success">
		      <div class="panel-heading"><center>Park Street Store</center></div>
		      <div class="panel-body">
				<div class="col-lg-4 "  style="margin-right: -2%;height: 214px;" >
	          	 <div class="panel panel-primary" style="width:96%;">
	          	 <canvas id="canvas2" style="background-color: #31708f00;"></canvas>
	          	</div>
			 </div>
               <div class="col-lg-4 "  style="margin-right: 0%;height: 214px;">
                 <div class="panel panel-primary" style="width:96%;">
					<canvas id="paichart2" style="background-color: #31708f00;"></canvas>
          		 </div>
                </div>

                 <div class="col-lg-4 " style="margin-left: -2%;">
						<div class="row">
					 <div class="col-lg-6 col-md-6  coldivider" >
					                    <div class="panel panel-yellow">
					                        <div class="panel-heading">
					                            <div class="row">
					                                <div class="col-xs-3">
					                            		<img  style="width: 44px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/sale.png">
													</div>
					                                <div class="col-xs-9 text-right">
					                                    <div><b>Sale</b></div>
													</div>
					                            </div>
												 <div class="row">
													<div class="col-xs-12 text-right">
													  <span> ${cur}: </span>  <span id="sale2" class="count">242323</span>
													</div>
					                            </div>
					                        </div>
					                    <!--     <a href="#">
					                            <div class="panel-footer">
					                                <span class="pull-left">View Details</span>
					                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					                                <div class="clearfix"></div>
					                            </div>
					                        </a> -->
					                    </div>
					                </div>

					          <div class="col-lg-6 col-md-6 coldivider">
	                    <div class="panel panel-red">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                               		<i class="fa fa-user fa-2x"" aria-hidden="true"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div><b>Purchase </b></div>
	                                  </div>
	                              </div>
	                              <div class="row">
									 <div class="col-xs-12 text-right">
										 <br>
									 	 <span> ${cur}: </span>  <span id="purchase2" class="count">55555</span>
									</div>
	                            </div>
	                        </div>
	                     <!--    <a href="#">
	                            <div class="panel-footer">
	                                <span class="pull-left">View Details</span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a> -->
	                    </div>
	                </div>

						</div>
						<div class="row">
						   <div class="col-lg-6 col-md-6 coldivider">
                    <div class="panel panel"  style="background-color: #ffa50069;">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                   <img  style="width: 44px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/cashinhand.png">
                                </div>
                                <div class="col-xs-9 text-right">
 									<div><b>Cash In Hand</b></div>
								 </div>
                            </div>

                              <div class="row">
								 <div class="col-xs-12 text-right">
									<span> ${cur}: </span>  <span id="cash_in_hand_dramt2" class="count">690000</span>
                                </div>
                            </div>
                        </div>
                    <!--     <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>

                  <div class="col-lg-6 col-md-6 coldivider">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
									 <i class="fa fa-bank fa-2x" aria-hidden="true" style="color:#ffffff"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div><b>Bank</b></div>
                                 </div>
                            </div>
                             <div class="row">
								 <div class="col-xs-12 text-right">
									<br>
                                  	<span> ${cur}: </span>  <span id="bank2" class="count">50000</span>
                                </div>
                            </div>
                        </div>
                     <!--    <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>

						</div>
                </div>
		      </div>
		    </div>

           </div>

         </section><!--/wrapper -->

<%-- <script src="${pageContext.request.contextPath }/assets/js/common/jquery.js"></script> --%>
<script src="${pageContext.request.contextPath }/assets/js/common/jquery.backstretch.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/chart/chart_js.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/chart/util.js"></script>

<script>
var cur='${cur}';
$(document).ready(function(){
	var currentDate = new Date();
	/* $('#startdate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	}); */

	$('#enddate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});

});
function isEmpty(val) {
    return (val === undefined || val == null || val.length <= 0) ? true : false;
}

/*   for line chart
 */

	/* labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'], */
var config = {
type: 'bar',
data: {
	labels: ['April', 'May', 'June', 'July', "August", "September", "October", "November", "December",'January', 'February', 'March'],
	datasets: [{
		label: 'Sale',
		backgroundColor: window.chartColors.red,
		borderColor: window.chartColors.red,
	   /*  data: [0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0], */
	    data: [1050,1800, 1500, 0,0, 0, 0, 0,0, 0, 0, 0],

		fill: false,
	},
	{
		label: 'Sale Return',
		fill: false,
		backgroundColor: window.chartColors.blue,
		borderColor: window.chartColors.blue,
		 data: [130, 210, 145, 0,0, 0, 0, 0,0, 0, 0, 0],

	}]
},
options: {
	responsive: true,
	title: {
		display: true,
		text: 'Sale Details'
	},
	scales: {
		yAxes: [{
			/* ticks: {
				min: 1000,
				max: 5000000
			} */
		}]
	},
	chartArea: {
        backgroundColor: 'rgb(57, 129, 113)'
    }
}
};


var config3 = {
		type: 'bar',
		data: {
			labels: ['April', 'May', 'June', 'July', "August", "September", "October", "November", "December",'January', 'February', 'March'],
			datasets: [{
				label: 'Sale',
				backgroundColor: window.chartColors.red,
				borderColor: window.chartColors.red,
			   /*  data: [0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0], */
			    data: [3000,3200, 5800, 0,0, 0, 0, 0,0, 0, 0, 0],

				fill: false,
			},
			{
				label: 'Sale Return',
				fill: false,
				backgroundColor: window.chartColors.blue,
				borderColor: window.chartColors.blue,
				 data: [500, 300, 56, 0,0, 0, 0, 0,0, 0, 0, 0],

			}]
		},
		options: {
			responsive: true,
			title: {
				display: true,
				text: 'Sale Details'
			},
			scales: {
				yAxes: [{
					/* ticks: {
						min: 1000,
						max: 5000000
					} */
				}]
			},
			chartArea: {
		        backgroundColor: 'rgb(57, 129, 113)'
		    }
		}
		};


// end line chart

// start pai

var randomScalingFactor = function() {
return Math.round(Math.random() * 100);
};

var config2 = {
type: 'pie',
data: {
	datasets: [{
		data: [800,100],
		backgroundColor: [
			window.chartColors.orange,
			/* window.chartColors.yellow,
			window.chartColors.green, */
			window.chartColors.blue,
		],
		label: 'Dataset 1'
	}],
	labels: [
		'Purchase Invoice',
		/* 'Purchase Challan',
		'Purchase order', */
		'Purchase Return'
	]
},
options: {
	responsive: true
}
};

var config4 = {
type: 'pie',
data: {
	datasets: [{
		data: [900,50],
		backgroundColor: [
			window.chartColors.orange,
			/* window.chartColors.yellow,
			window.chartColors.green, */
			window.chartColors.blue,
		],
		label: 'Dataset 1'
	}],
	labels: [
		'Purchase Invoice',
		/* 'Purchase Challan',
		'Purchase order', */
		'Purchase Return'
	]
},
options: {
	responsive: true
}
};



function getAlltotal()
{
	var commonResultSetMapper={
							   startDate:document.getElementById("startdate").value,
							   endDate:document.getElementById("enddate").value,
							   id:document.getElementById("storelist").value
							  };


      	 var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/home/getalltotal.htm", commonResultSetMapper, function(response) {
            	 var res = JSON.parse(response);

		             $("#inventory_stock").html("Qty: "+res.stockqty);
		             $("#inventory_valuation").html(isEmpty(res.valuation)==true?0:res.valuation);
		             $("#sale").html(isEmpty(res.saleTotalAmount)==true?0:res.saleTotalAmount);
		             $("#purchase").html(isEmpty(res.purchaseTotalAmount)==true?0:res.purchaseTotalAmount);
		             var temp_cash=res.cashInHandDr-res.cashInHandCr;
		             $("#cash_in_hand_dramt").html(isEmpty(temp_cash)==true?0:temp_cash);
		             var tempbank=res.BankAmtDr-res.BankAmtCr;
		             $("#bank").html(isEmpty(tempbank)==true?0:tempbank);

		        	 $('.count').each(function () {
		        		    $(this).prop('Counter',0).animate({
		        		        Counter: $(this).text()
		        		    }, {
		        		        duration: 4000,
		        		        easing: 'swing',
		        		        step: function (now) {
		        		            $(this).text(Math.ceil(now).toLocaleString('en'));
		        		        }
		        		    });
		        		});



			});




}
/*
for line  chart ajax call
*/
function lineChartcall()
{

	var commonResultSetMapper={
			   startDate:document.getElementById("startdate").value,
			   endDate:document.getElementById("enddate").value,
			   id:document.getElementById("storelist").value
			 };
  	var ajaxCallObject2 = new CustomBrowserXMLObject();
    	ajaxCallObject2.callAjaxPost(BASE_URL + "/home/getlinechart.htm", commonResultSetMapper, function(response) {

			var res = JSON.parse(response);
			var salearr=res.sales.split(",");
			config.data.datasets[0].data[0]=parseInt(salearr[0]);
			config.data.datasets[0].data[1]=parseInt(salearr[1]);
			config.data.datasets[0].data[2]=parseInt(salearr[2]);
			config.data.datasets[0].data[3]=parseInt(salearr[3]);
			config.data.datasets[0].data[4]=parseInt(salearr[4]);
			config.data.datasets[0].data[5]=parseInt(salearr[5]);
			config.data.datasets[0].data[6]=parseInt(salearr[6]);
			config.data.datasets[0].data[7]=parseInt(salearr[7]);
			config.data.datasets[0].data[8]=parseInt(salearr[8]);
			config.data.datasets[0].data[9]=parseInt(salearr[9]);
			config.data.datasets[0].data[10]=parseInt(salearr[10]);
			config.data.datasets[0].data[11]=parseInt(salearr[11]);

			var salesReturnarr=res.salesReturn.split(",");
			config.data.datasets[1].data[0]=parseInt(salesReturnarr[0]);
			config.data.datasets[1].data[1]=parseInt(salesReturnarr[1]);
			config.data.datasets[1].data[2]=parseInt(salesReturnarr[2]);
			config.data.datasets[1].data[3]=parseInt(salesReturnarr[3]);
			config.data.datasets[1].data[4]=parseInt(salesReturnarr[4]);
			config.data.datasets[1].data[5]=parseInt(salesReturnarr[5]);
			config.data.datasets[1].data[6]=parseInt(salesReturnarr[6]);
			config.data.datasets[1].data[7]=parseInt(salesReturnarr[7]);
			config.data.datasets[1].data[8]=parseInt(salesReturnarr[8]);
			config.data.datasets[1].data[9]=parseInt(salesReturnarr[9]);
			config.data.datasets[1].data[10]=parseInt(salesReturnarr[10]);
			config.data.datasets[1].data[11]=parseInt(salesReturnarr[11]);

			/* config.data.datasets[0].data[0]=85; */

			window.myLine.update();

		});
    	/* config.data.datasets[0].data[0]=85; */

}

function Piechartcall()
{
	var commonResultSetMapper={
			   startDate:document.getElementById("startdate").value,
			   endDate:document.getElementById("enddate").value,
			   id:document.getElementById("storelist").value
			 };
  	var ajaxCallObject = new CustomBrowserXMLObject();
    	ajaxCallObject.callAjaxPost(BASE_URL + "/home/getalltotal.htm", commonResultSetMapper, function(response) {

			var res = JSON.parse(response);



		});
    	config2.data.datasets[0].data[0]=85;
    	window.myPie.update();
	}




	window.onload = function()
 {

	    var ctx = document.getElementById('canvas').getContext('2d');
		window.myLine = new Chart(ctx, config);

		var ctx = document.getElementById('paichart').getContext('2d');
		window.myPie = new Chart(ctx, config2);

		var ctx = document.getElementById('canvas2').getContext('2d');
		window.myLine = new Chart(ctx, config3);

		var ctx = document.getElementById('paichart2').getContext('2d');
		window.myPie = new Chart(ctx, config4);



	  /*   getAlltotal();
	    lineChartcall();
	    Piechartcall(); */

	/*     $('.count').each(function () {
		    $(this).prop('Counter',0).animate({
		        Counter: $(this).text()
		    }, {
		        duration: 4000,
		        easing: 'swing',
		        step: function (now) {
		            $(this).text(Math.ceil(now));
		        }
		    });
		}); */

 }

 function getDataDetails()
 {
	 getAlltotal();
	 lineChartcall();
	 Piechartcall();



  /* config.data.datasets[0].data[0]=85;
  config.data.datasets[0].data[1]=50;
  window.myLine.update();
  window.myPie.update(); */

 }

	</script>
<script type="text/javascript">
/* $("#wrapperContainer").backstretch(['${pageContext.request.contextPath}/assets/images/home/pharma_home-background.jpg']); */
</script>