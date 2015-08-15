(function(){
	var app = angular.module('principalMobile' , ['usuarioMobile','mobileDirectives']);
    var urlBase = 'http://192.168.0.11:8080';
    var empresasJson = null;
    var latitude = null;
    var longitude = null;
    app.controller('principalCtrl' , function($scope,$http){
        $scope.empresas = [];
        var produtos = [];
       $http.defaults.headers.post["Content-Type"] = "application/jsonp";
        $scope.showProdutos = function(produtoTipo){
            $("#containerPrincipal").hide();
            $("#containerUsuario").hide();
            $("#containerProdutos").show();
           
            if( produtoTipo == 'Alimentos'){
                for(var i=0 ; i < empresasJson.length ; i++){
                    if(empresasJson[i].tipo !== 'A'){
                       $('#emp' + empresasJson[i].cpfCnpj + 'tipo').hide();
                    }
                        
                }                
            } else  if( produtoTipo == 'servicos'){
                for(var i=0 ; i < empresasJson.length ; i++){
                    if(empresasJson[i].tipo !== 'S'){
                       $('#emp' + empresasJson[i].cpfCnpj + 'tipo').hide();
                    }
                        
                }                
            } else  if( produtoTipo == 'utilitarios'){
                for(var i=0 ; i < empresasJson.length ; i++){
                    if(empresasJson[i].tipo !== 'U'){
                        $('#emp' + empresasJson[i].cpfCnpj + 'tipo').hide();
                    }
                        
                }                
            }
            for(var i=0 ; i < empresasJson.length ; i++){
                
                var distancia = calcDistancia(empresasJson[i].latitude, empresasJson[i].longitude, latitude, longitude);
                if(distancia > empresasJson[i].raio){
                    empresasJson.splice(i,1);
                }
            }
            
            
        };
        
        $scope.getEmpresasProdutosController = function(){
            var onSuccess = function(position) {
                latitude = position.coords.latitude;
                longitude =  position.coords.longitude;
                console.log(latitude + ' ' + longitude);
                var data = $.param({latitude: position.coords.latitude , longitude:  position.coords.longitude});
                alert(data);
                setTimeout(function(){
                    $http.get(urlBase + '/getEmpresasPorLatLong?'+ data).success(function( data , status){
                        $scope.empresas = data;
                        empresasJson = data;
                    });
                },2000);
            }
            
            // onError Callback receives a PositionError object
            //
            function onError(error) {
                alert('code: '    + error.code    + '\n' +
                      'message: ' + error.message + '\n');
            }

            navigator.geolocation.getCurrentPosition(onSuccess, onError);
            
             
        }
        
        function getLatLongMobile(){
    
            
        }
        
        function calcDistancia(latMob, longMob, latEmp, longEmp){
            latMobKm = latMob / 110.574;
            longMobKm = longMob / (111.320 * Math.cos(longEmp));
            latEmpKm = latEmp / 110.574;
            longEmpKm = longEmp / (111.320 * Math.cos(longEmp));
            latEmpKm = convertPositivo(latEmpKm);
            latMobKm = convertPositivo(latMobKm);
            
            var distLong = latEmpKm - latMobKm;
            console.log(latEmpKm +" - "+ latMobKm);
            if(distLong < 0){
                distLong = distLong * -1;
            }
            console.log(distLong);
            return distLong;
        }
        
        function convertPositivo(valor){
            if(valor < 0){
                valor = valor * -1;
            }
            return valor;
        }
    });
})();