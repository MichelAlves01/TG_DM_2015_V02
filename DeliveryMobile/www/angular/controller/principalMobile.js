(function(){
	var app = angular.module('principalMobile' , ['usuarioMobile','mobileDirectives']);
    
    
    app.controller('principalCtrl' , function($scope,$http){
       
        $scope.showProdutos = function(produtoTipo){
            $("#containerPrincipal").hide();
            $("#containerUsuario").hide();
            $("#containerProdutos").show();
            if( produtoTipo == 'Alimentos'){
                 
            }
        };
    });
})();