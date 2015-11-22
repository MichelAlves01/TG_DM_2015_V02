(function(){
	var app = angular.module('principalMobile' , ['usuarioMobile','mobileDirectives','mobService']);
    var ip = location.host;
    ip = ip.split(':');
    if(ip != null){
        var urlBase = 'http://'+ip[0]+':8080';
    } else {
        var urlBase = 'http://192.168.0.100:8080'
    }
    
    var empresasJson = null;
    var latitude = null;
    var longitude = null;
    var showCar = false;
    var deviceheight = 0;
    var carrinho = 0;
    var enderecoAlter = false;
    nivel = 1;

    app.run(function($locale){
    	$locale.NUMBER_FORMATS.GROUP_SEP = '.';
    	$locale.NUMBER_FORMATS.DECIMAL_SEP = ',';
	});
    
    app.controller('principalCtrl' , function($scope,$http){
        $http.defaults.headers.post["Content-Type"] = "application/jsonp";
        $scope.empresas = [];
        var produtos = [];
        $scope.enderecos = [];
        $scope.quantidade = 1;  
        
        $scope.showUsuario = function(){
			visibilityControle(5);
		}
        
        function visibilityControle( nivelOp  ){
        
            if(nivelOp == 1 || nivelOp == 4){
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
            } else if(nivelOp == 5){
                $("#containerPrincipal").hide();
                $("#containerUsuario").show();
                $("#produtosContainer").hide();
                $("#containerProdutos").hide();
                $("#containerPedido").hide();
                nivel = 5;
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
                $('#emp' + empresasJson[i].cpfCnpj + 'dist').text(distancia.toPrecision(4));
                var avaliacao = empresasJson[i].avaliacao.split(",");
                $('#emp' + empresasJson[i].cpfCnpj +'estrelas').raty({ score: avaliacao[0], readOnly : true,});
                $('#emp' + empresasJson[i].cpfCnpj + 'aval').text(avaliacao[0].substring(0,4));
            }
            
            
        };
        
        $scope.getEmpresasProdutosController = function(){
            $scope.nomeUsuario = window.localStorage.getItem('nome');
            deviceheight = window.orientation == 0 ? window.screen.height : window.screen.width;
            if (navigator.userAgent.indexOf('Android') >= 0 && window.devicePixelRatio) {
                deviceheight = deviceheight / window.devicePixelRatio;
            }
            
                carrinho = deviceheight/5;
                $('#carrinho').css('height', carrinho);
                $('#comanda').css('height', carrinho);
                $('#containerPr').css('height', deviceheight);
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
                var data = $.param({latitude: latitude , longitude:  longitude});
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
            latMobKm = latMob * 111.1;
            longMobKm = longMob * (111.1 * Math.cos(longEmp));
            latEmpKm = latEmp * 111.1;
            longEmpKm = longEmp * (111.1 * Math.cos(longEmp));
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
                    $('#containerPrincipal').animate({height: '79%'});                  
                } else if(nivelOp == 2) {
                    $("#produtosContainer").animate({height: '79%'});
                    $("#containerProdutos").show();
                } else if(nivelOp == 3) {
                    $("#containerPedido").animate({height: '79%'});
                }
            }
                
        }
        $scope.comandaConteudo = function(){
            if(!showCar){
                var heightComanda = deviceheight + 'px';
                $('#comandaConteudo').animate({height: heightComanda});
                $('#comandaConteudo').css('position','relative');
                $('#comandaConteudo').css('top', carrinho);
                visibilityCarrinho(nivel , showCar);
                 $('#comandaConteudo').show();
                $('#comandaConteudo').css('overflow' , 'auto');
                showCar = true;
            } else {
                $('#comandaConteudo').animate({height: '0px'});
                visibilityCarrinho(nivel , showCar);
                $('#comandaConteudo').hide();
                showCar = false;
            }
            
        };
        
       /* ######################## compras ############################## -->*/
         $scope.addProduto = function(empresa, produto, quantidade ){
            if(quantidade < 1){
                alert('Escolha quantidade valida');
                return;
            }
            if($scope.carrinho == null){
                $scope.carrinho = jQuery.extend({}, empresa);
                $scope.carrinho.produto = [];
            } else if($scope.carrinho.cpfCnpj != empresa.cpfCnpj) {
                $.blockUI({ message: $('#limparCarrinhoForm') }); 
            }
                produto.quantidade = quantidade;
                $scope.carrinho.produto.push(produto);
                if($scope.carrinho.produto.length > 0){
                    $scope.total = 0;
                    for(var i=0 ; i<$scope.carrinho.produto.length ; i++){
                        $scope.total += $scope.carrinho.produto[i].preco * quantidade; 
                    }
                }
        };
        
        $scope.limparCarrinho = function(){
            $scope.carrinho = null;
            $scope.total = 0;
            $.unblockUI();
        }
        
        $scope.avaliacao = function(){
            
        }
        
        $scope.excluirItemComanda = function(produto){
            for(var i=0 ; i<$scope.carrinho.produto.length ; i++ ){
                if($scope.carrinho.produto[i].id == produto.id){
                    $scope.carrinho.produto.splice(i,1);
                    $scope.total -= produto.preco * produto.quantidade;
                }
            }
        }
        
        $scope.cancelarBlockUIEvento = function(){
           $.unblockUI(); 
        }
        $scope.finalizarCompra = function(){
           
            if($scope.carrinho != null){
                visibilityControle( 3 );
                $scope.comandaConteudo();
                alert('verifique se seu endereço está correto');
            } else {
                alert('Escolha algum produto para finalizar a compra'); 
            }
               
        }
        
        $scope.enviarPedido = function(){
            
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
                                idUsuario: localStorage.getItem('email'),
                                produto: JSON.stringify($scope.carrinho.produto)});
           
            /*
                Criando a mensagem de confirmação
            */
            var msgConfirmação;
            if($scope.end != null){          
                msgConfirmação = "\nEndereço para entrega : " + $scope.end             
            } else {
                alert("Por favor insira um endereço para entrega");
                return;
            }
            if($scope.pgtoTipo != null){
                msgConfirmação = msgConfirmação + "\npagto : " + $scope.pgtoTipo
            }
            if($scope.troco != null){
                msgConfirmação = msgConfirmação + "\ntroco : " +$scope.troco
            }
            if($scope.observacao){
                msgConfirmação = msgConfirmação + "\nobservacao : " + $scope.observacao
            }
            if($scope.cartao != null){
                msgConfirmação = msgConfirmação + "\n$scope.cartao :  " + $scope.cartao
            }
            if(($scope.cartao == null && $scope.pgtoTipo) && ($scope.troco == null && $scope.pgtoTipo)){
                alert("Escolha uma forma de pagamento");
                return;
            }
            
             var r = confirm(msgConfirmação);
            if (r == true) {
                this.cadastrarPedido(data);
                    $scope.numero = endereco  = pgtoObs = observacao = "" ;
                    $scope.carrinho = null;
                    visibilityControle(2);
                    $scope.limparCarrinho();
            }
        }
        
        $scope.inicio = function(){
            visibilityControle(1);
        }
        
        $scope.voltar = function(){
            visibilityControle(nivel - 1);
            for(var i=0 ; i < empresasJson.length ; i++){
                        $('#emp' + empresasJson[i].cpfCnpj + 'tipo').show();
            }
        }
        
        $scope.endAdvertise = function(){
            if(!enderecoAlter){
                alert('Ao alterar a localidade de entrega o pedido pode ser rejeitado se estiver fora do raio de entrega da empresa!');
                enderecoAlter = true;
            }
            
            
        }
        
        
    });
    

})();