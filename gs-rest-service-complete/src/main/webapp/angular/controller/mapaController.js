(function(){
	
	var app = angular.module('mapCtrl' , []);

	app.controller('mapaRaio' , function($scope,$http,empresa){
		var latLong;
		var raio;
		var timeOut = true;
		var urlBase = 'http://localhost:8080';
		/*
			Obtem informações de latitude e longitude e atualiza o mapa,
			tambem mostra o mapa na tela com um marcador no endereço informado.  
		*/
		$scope.getMapalocalizacaoInicial = function(){
			setTimeout(function(){
					//inicia o mapa com uma localização padrão.
					latLong = new google.maps.LatLng($scope.empresa.latitude, $scope.empresa.longitude);
					if(raio == null){
						$('#master').val('Raio : ' + $scope.empresa.raio + 'Km');
				   		raio = $scope.empresa.raio;
				   	}
					 
					//define o valor de zoom quando o endereço for o definido pelo usuario.
					var valorZoom = (getZoom() != null)? getZoom() : 15;
					timeOut = true;
					console.log("desenhou");
					//redireciona o mapa para o endereço inicial.
					var mapOptions = {
					    zoom: valorZoom,
					    center: latLong
					  }

					var map = new google.maps.Map(document.getElementById('map-canvas3'), mapOptions);
					var geocoder = new google.maps.Geocoder();

					

					iniciaCirculo(map , latLong , $scope.empresa.cidade.nome);

				   	var marker = new google.maps.Marker({
					     position: latLong,
					     map: map,
					     draggable:true,
    					animation: google.maps.Animation.DROP
					  });


				   	$scope.definirRaio($scope.empresa.cpfCnpj , raio);
				   	$scope.slider();
			}, 100);

		}


		 function getZoom(){
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

		$scope.slider = function(){
			setTimeout(function(){
				$(function() {
				    $( "#slider" ).slider({
				      value: $scope.empresa.raio,
				      min: 0,
				      max: 50,
				      step: 1,
				      slide: function( event, ui ) {
				        $( "#master" ).val( "Raio : " + ui.value + 'Km');
				        raio = ui.value;
				        console.log(timeOut);
				        if(timeOut){
				        	timeOut = false;	
							setTimeout(function(){
									
									$scope.getMapalocalizacaoInicial();

							}, 1000);
						}				    				        
				      }
				    });
				    $( "#amount" ).val( "$" + $( "#slider" ).slider( "value" ) );
				  });
			}, 100);
		}

		function iniciaCirculo(map , latLong , cidade){
					 var citymap = {};
						citymap[cidade] = {
					  	center: new google.maps.LatLng(latLong),
					  	population: 2714856
						};

						for (var city in citymap) {
						    var populationOptions = {
						      strokeColor: '#FF0000',
						      strokeOpacity: 0.8,
						      strokeWeight: 2,
						      fillColor: '#FF0000',
						      fillOpacity: 0.05,
						      map: map,
						      center: latLong ,
						      radius: 1000 * raio
						    };
						    // Add the circle for this city to the map.
				    setTimeout(function() {
				    	cityCircle = new google.maps.Circle(populationOptions);
				   	}, 100);
				    }
		}
		$scope.definirRaio = function(cpfCnpj , raio){

			var data = $.param({cpfCnpj: cpfCnpj , raio: raio});
				$http.get(urlBase + '/definirRaioController?' + data).success(function(data,status){
					$scope.empresa = data;
				})
			console.log("foi");
		}
	});
})();