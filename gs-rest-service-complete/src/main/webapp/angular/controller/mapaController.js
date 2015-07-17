(function(){
	
	var app = angular.module('mapaController' , []);

	app.controller('getMarker' , function(){
		
		$scope.getLatitudeLongitude = function(){
					
				var cidade = document.getElementById('cidade').value;
				var bairro = document.getElementById('bairro').value;
				var rua = document.getElementById('rua').value;
				var numero = document.getElementById('numero').value;

				var address = cidade + ", " + bairro + ", " + rua + ", " + numero;

				  alert(address);

				  geocoder.geocode( { 'address': address}, function(results, status) {

				    if (status == google.maps.GeocoderStatus.OK) {
				      map.setCenter(results[0].geometry.location);
				   		
				   
				   		 mainMap(results[0].geometry.location)
				    } else {
				      alert('Geocode was not successful for the following reason: ' + status);
				    }
				  });
		}

				mainMap();
				

				function getZoom(raio){
					var zoom; 
					if(raio >= 4){
						minus = Math.round( (raio / 10)  + 2);
				  		zoom = 14 - minus; 		
				  	}

				  	if(raio > 50){
				  		zoom = 4;
				  	}
				  	return zoom;
				}

				function getRaio(){
					var raio = document.getElementById('raio').value;
					if(raio != null){
							return raio;
						} else {
							return 0;
						}	
							
						
				}

				function iniciaCirculo(latLong){
					
					 var citymap = {};
						citymap['Sorocaba'] = {
					  	center: new google.maps.LatLng(latLong),
					  	population: 2714856
						};
						return citymap ;
				}
					    
				var geocoder;
				var map;
				function initialize(citymap , latLong, raio, zoom) {
					
				  // Create the map.
				  geocoder = new google.maps.Geocoder();
				  var latlng = latLong;
				  var mapOptions = {
				    zoom: zoom,
				    center: latlng,
				    mapTypeId: google.maps.MapTypeId.TERRAIN
				  };

				  map = new google.maps.Map(document.getElementById('map-canvas'),
				      mapOptions);

				  // Construct the circle for each value in citymap.
				  // Note: We scale the area of the circle based on the population.
				  if(latLong != null){
				  for (var city in citymap) {
				    var populationOptions = {
				      strokeColor: '#FF0000',
				      strokeOpacity: 0.8,
				      strokeWeight: 2,
				      fillColor: '#FF0000',
				      fillOpacity: 0.35,
				      map: map,
				      center: latLong ,
				      radius: Math.sqrt(citymap[city].population) * raio
				    };
				    // Add the circle for this city to the map.
				    
				    	cityCircle = new google.maps.Circle(populationOptions);
				    }
				  }
				  if(latLong != null){
				  var marker = new google.maps.Marker({
				      map: map,
				      position: latLong
				      });
					}
				}

				function codeAddress() {

				  var cidade = document.getElementById('cidade').value;
				  var bairro = document.getElementById('bairro').value;
				  var rua = document.getElementById('rua').value;
				  var numero = document.getElementById('numero').value;

				  var address = cidade + ", " + bairro + ", " + rua + ", " + numero;

				  alert(address);

				  geocoder.geocode( { 'address': address}, function(results, status) {

				    if (status == google.maps.GeocoderStatus.OK) {
				      map.setCenter(results[0].geometry.location);
				   		
				   
				   		 mainMap(results[0].geometry.location)
				    } else {
				      alert('Geocode was not successful for the following reason: ' + status);
				    }
				  });
				 
				}

				function mainMap(latLong){

					var citymap = iniciaCirculo(latLong);
				
					var raio = getRaio();
					
					
				
					var zoom = 13;
					if(raio != null && raio >= 4){
						zoom = getZoom(raio);
					}
					alert(zoom);
					initialize(citymap , latLong, raio, zoom)
					

				}

				google.maps.event.addDomListener(window, 'load', initialize);
	});

	

				

})();