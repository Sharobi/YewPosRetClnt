/**
 * 
 */

var lastLat;
var lastLongt;
var places = [];
var polyline;
var lineSymbol;
var boundsListener;
var marker;
/*var centarlat=22.5863785;
var centarlng=88.3968839;*/
function getTourPlanLocationDetail(){
	var lastLat;
	var lastLongt;
	var lat;
	var longt;
	var siteId
	var mobTime;
	var type;
	var marker;
	var places = [];
	var tourDate = $("#tourDate").val();
	var salesManId = $("#saleseman").val();
	
	var responseObj;
	$('#distance').text('');
	$('#map').html('');
	    var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/salesmantrack/getTourPlanLocations/" + tourDate + "/" + salesManId + ".htm", function(resp) {
		        responseObj = JSON.parse(resp);
				if(responseObj.length>0){
					    var centarlat = responseObj[parseInt(Number(responseObj.length)/2)].latitude;
						var centarlng = responseObj[parseInt(Number(responseObj.length)/2)].longitude;
						
						var options = {
								zoom : 15,
								center : new google.maps.LatLng(centarlat,centarlng),
								mapTypeId : google.maps.MapTypeId.ROADMAP
							};

							// Creating the map
							var map = new google.maps.Map(document.getElementById('map'),options);
						
						
						
						
						lastLat = responseObj[responseObj.length-1].latitude; 
						lastLongt = responseObj[responseObj.length-1].longitude; 
						var distance = 0.0;
						for (var j = 0; j < responseObj.length; j++) {
							
							lat = responseObj[j].latitude;
							longt = responseObj[j].longitude;
							
						
							if (lat > 0 && longt > 0) {
								
								var from = new google.maps.LatLng(responseObj[j].latitude, responseObj[j].longitude);
								 if(j+1 < responseObj.length){
									var to   = new google.maps.LatLng(responseObj[j+1].latitude, responseObj[j+1].longitude);
									var dist = google.maps.geometry.spherical.computeDistanceBetween(from, to);
									distance = Number(distance) + Number(dist);
								 }
								
                       
								places.push(new google.maps.LatLng(
										responseObj[j].latitude,
										responseObj[j].longitude));

								for (var i = 0; i < places.length; i++) {
									if (responseObj[i].tourType == "TOUR") {
											marker = new google.maps.Marker(
													{
														position : places[i],
														map : map,
														title : 'TOUR',
														icon : BASE_URL
																+ '/assets/images/map/circle-blue.png',

													});
										} else if (responseObj[i].tourType == "CHECK IN") {
											marker = new google.maps.Marker(
													{
														position : places[i],
														map : map,
														title : 'CHECK IN',
														icon : BASE_URL
																+ '/assets/images/map/green-dot.png',

													});
									   } else /*if (responseObj[i].tourType == "CHECK OUT")*/ {
										   
											marker = new google.maps.Marker(
													{
														position : places[i],
														map : map,
														title : 'CHECK OUT',
														icon : BASE_URL
																+ '/assets/images/map/red-dot.png',

													});
									   }

									(function(i, marker) {

										google.maps.event
												.addListener(
														marker,
														'click',
														function() {

															var infowindow = new google.maps.InfoWindow(
																	{
																		content : 'Type:'
																				+ (responseObj[i].tourType)
																				+ " , "
																				+ ' Time : '
																				+ (responseObj[i].tourTime)

																	});

															infowindow
																	.open(map,
																			marker);

														});

									})(i, marker);

								}
								
								

							}
						}
                      
                        $('#distance').text("Total Distance Covered: "+parseFloat(Number(distance)/1000).toFixed(2) + "KM");
						// Zoom Level
						var boundsListener = google.maps.event.addListener(
								(map), 'bounds_changed', function(event) {
									this.setZoom(13);
									google.maps.event
											.removeListener(boundsListener);
								});

						

						var lineSymbol = {
							// path: 'M 0,-1 0,2',
							path : google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
							strokeOpacity : 0.8,
							scale : 1,
							strokeWeight : 2,
							strokeColor : "#00008B",
						};

						var polyline = new google.maps.Polyline({
							path : places,
							strokeOpacity : 0,
							icons : [ {
								icon : lineSymbol,
								offset : '0',
								repeat : '20px'

							} ],
							map : map
						});

						// Adding the polyline to the map
						polyline.setMap(map);

					
		   }	// end resp.length>0 if	
			else{
				$('#distance').text('NO DATA FOUND');
			}
		}, null);
					
}




