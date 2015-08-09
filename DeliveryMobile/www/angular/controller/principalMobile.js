(function(){
	var app = angular.module('principalMobile' , ['usuarioMobile','mobileDirectives']);
    var urlBase = 'http://192.168.0.11:8080';
    
    app.controller('principalCtrl' , function($scope,$http){
       $http.defaults.headers.post["Content-Type"] = "application/jsonp";
        $scope.showProdutos = function(produtoTipo){
            $("#containerPrincipal").hide();
            $("#containerUsuario").hide();
            $("#containerProdutos").show();
            if( produtoTipo == 'Alimentos'){
                 
            }
        };
        
        $scope.getEmpresasProdutosController = function(){
            var data = $.param({latitude: -23.4534596 , longitude: -47.4900411});
            $http.get(urlBase + '/getEmpresasPorLatLong?'+ data).success(function( data , status){
                $scope.empresas = data;
                alert( $scope.empresas[0].nome);
            });
        }
    });
})();