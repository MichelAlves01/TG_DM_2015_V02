(function(){
	var app = angular.module('principalMobile' , ['usuarioMobile','mobileDirectives','mobService']);
    
    var urlBase = 'http://192.168.0.11:8080';
    var empresasJson = null;
    var latitude = null;
    var longitude = null;
    var showCar = false;
    nivel = 1;
    
    
    app.controller('principalCtrl' , function($scope,$http){
        $http.defaults.headers.post["Content-Type"] = "application/jsonp";
        $scope.empresas = [];
        var produtos = [];
        $scope.enderecos = [];
        
        
        function visibilityControle( nivelOp  ){
        
            if(nivelOp == 1){
                $('#containerPrincipal').animate({height: '0px'});
                $("#containerPrincipal").show();
                $("#containerUsuario").hide();
                $("#produtosContainer").hide();
                $("#containerProdutos").hide();               
                $("#containerPedido").hide();
                nivel = 1;
                visibilityCarrinho(nivel , true);
            } else if(nivelOp == 2){
                 $("#containerPrincipal").hide();
                $("#containerUsuario").hide();
                $("#produtosContainer").show();
                $("#containerProdutos").show();
                $("#containerPedido").hide();
                nivel = 2;
                visibilityCarrinho(nivel , true);
            } 
            else if(nivelOp == 3){
                $("#containerPrincipal").hide();
                $("#containerUsuario").hide();
                $("#produtosContainer").hide();
                $("#containerProdutos").hide();
                $("#containerPedido").show();
                nivel = 3;
                visibilityCarrinho(nivel , true);
            }
        }
        
       
        $scope.showProdutos = function(produtoTipo){
            visibilityControle( 2 );
            
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

            var onSuccess = function(position) {
                latitude = position.coords.latitude;
                longitude =  position.coords.longitude;
                
                var latlng = new google.maps.LatLng(latitude, longitude);
                var geocoder = geocoder = new google.maps.Geocoder();
                geocoder.geocode({ 'latLng': latlng }, function (results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        
                        var endereco = results[0].formatted_address.split(",");
                        
                        $scope.end = "";
                        for(var i=0 ; i<endereco.length ; i++){
                            if(i == 1){
                                var bairro = endereco[i].split("-");
                                $scope.end = $scope.end + "," + bairro[1];
                            } else {
                                $scope.end = $scope.end + "," + endereco[i]; 
                            }
                        }
                        
                        $scope.end = $scope.end.substring(1,$scope.end.length);
                        console.log($scope.end);
                        for(var i=0 ; i<results.length ; i++){
                            console.log("enderecos : " + results[i].formatted_address);
                        }
                    }
                });
                
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
        
        function visibilityCarrinho( nivelOp , showCar){
            if(!showCar){
                if(nivelOp == 1){
                    $('#containerPrincipal').animate({height: '0px'});
                } else if(nivelOp == 2) {
                    $("#produtosContainer").animate({height: '0px'});
                    $("#containerProdutos").hide(); 
                } else if(nivelOp == 3) {
                    $("#containerPedido").animate({height: '0px'});
                }
            } else {
                if(nivelOp == 1){
                    $('#containerPrincipal').animate({height: '80%'});                  
                } else if(nivelOp == 2) {
                    $("#produtosContainer").animate({height: '80%'});
                    $("#containerProdutos").show();
                } else if(nivelOp == 3) {
                    $("#containerPedido").animate({height: '80%'});
                }
            }
            
        }
        $scope.comandaConteudo = function(){
            if(!showCar){
                $('#comandaConteudo').animate({height: '80%'});
                visibilityCarrinho(nivel , showCar);
                 $('#comandaConteudo').show();
                showCar = true;
            } else {
                $('#comandaConteudo').animate({height: '0px'});
                visibilityCarrinho(nivel , showCar);
                $('#comandaConteudo').hide();
                showCar = false;
            }
            
        };
        
       
         $scope.addProduto = function(empresa, produto ){
            
            if($scope.carrinho == null){
                $scope.carrinho = jQuery.extend({}, empresa);
                $scope.carrinho.produto = [];
            }
             
                $scope.carrinho.produto.push(produto);
                if($scope.carrinho.produto.length > 0){
                    $scope.total = 0;
                    for(var i=0 ; i<$scope.carrinho.produto.length ; i++){
                        $scope.total += $scope.carrinho.produto[i].preco; 
                    }
                }
        };
        
        $scope.limparCarrinho = function(){
            $scope.carrinho = null;
            $scope.total = 0;
        }
        
        $scope.finalizarCompra = function(){
           visibilityControle( 3 );
            
        }
        
        $scope.enviarPedido = function(){
       
            alert("numero : " + $scope.numero +"\npagto : " + $scope.pgtoTipo + "\ntroco : " +$scope.troco 
                 + "observacao : " + $scope.observacao +"$scope.cartao :  "+ $scope.cartao);
            var endereco = $scope.end.split(",");
            $scope.end = "";
            for(var i=0 ; i<endereco.length ; i++){
                if(i == 0){
                    $scope.end = $scope.end + "," + endereco[i] + ", nº" + $scope.numero; 
                } else {
                    $scope.end = $scope.end + "," + endereco[i];
                }
            }
           
             $scope.end = $scope.end.substring(1,$scope.end.length);
            if($scope.troco != null){
                var pgtoObs = $scope.troco;
            } else {
                var pgtoObs = $scope.cartao;
            }
            
            var data = $.param({cpfCnpj: $scope.carrinho.cpfCnpj,
                                endereco: $scope.end,
                                pgtoTipo: $scope.pgtoTipo,
                                pgtoObs: pgtoObs,
                                observacao: $scope.observacao,
                                produto: JSON.stringify($scope.carrinho.produto)});
           
            this.cadastrarPedido(data);
            $scope.numero = "";
        }
        
        $scope.inicio = function(){
            visibilityControle(1);
        }
        
        $scope.voltar = function(){
            visibilityControle(nivel - 1);
        }
        
        
    });
    

})();