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
                    $('#emp' + empresasJson[i].cpfCnpj + 'tipo').hide();
                }
            }
            
            
        };
        
        $scope.getEmpresasProdutosController = function(){
                var deviceheight = window.orientation == 0 ? window.screen.height : window.screen.width;
            if (navigator.userAgent.indexOf('Android') >= 0 && window.devicePixelRatio) {
                deviceheight = deviceheight / window.devicePixelRatio;
            }
                var carrinho = deviceheight/5;
                $('#carrinho').css('height', carrinho);
                $('#containerPr').css('height', deviceheight);
                $('#containerPr').css('height', deviceheight);

            var onSuccess = function(position) {
                latitude = position.coords.latitude;
                longitude =  position.coords.longitude;
                console.log(latitude + ' ' + longitude);
                var data = $.param({latitude: -23.4534596 , longitude:  -47.4900411});
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
        
        function calcDistancia(latEmp, longEmp, latMob, longMob){
            latMobKm = latMob * 110.574;
            longMobKm = longMob * (111.320 * Math.cos(longEmp));
            latEmpKm = latEmp * 110.574;
            longEmpKm = longEmp * (111.320 * Math.cos(longEmp));
            latEmpKm = convertPositivo(latEmpKm);
            latMobKm = convertPositivo(latMobKm);
            var distLat = latEmpKm - latMobKm;
            var distLong = longEmpKm - longMobKm; 

            
            
            
            /*################################## Pitagoras ###############################################*/
                var catetoAdj = convertPositivo(distLong);
                var catetoOpt = convertPositivo(distLat);
                
                var hipotenusa = Math.pow(catetoAdj,2) + Math.pow(catetoOpt,2);
                hipotenusa = Math.sqrt(hipotenusa);

                                        /*##############################################################################################*/
            
            return hipotenusa;
        }
        
        function convertPositivo(valor){
            if(valor < 0){
                valor = valor * -1;
            }
            return valor;
        }
        
        $scope.comandaConteudo = function(){
            $('comanda-conteudo').animate({height: '80%'});
        };
    });
    

})();