(function(){
	var app = angular.module('mobileDirectives' , ['mobService']);

	app.directive('principal' , function(){
		return{
			restrict: 'A',
			templateUrl: 'view/principal.html'
		};
	});
    
    app.directive('produtos', function(){
        return{
            restrict: 'A',
            templateUrl: 'view/empresasProdutos.html'
        };
    });

	app.directive('usuario', function(){
		return{
			restrict: 'A',
			templateUrl: 'view/usuario.html'
		};
	});
    
    app.directive('carrinho' , function(){
        return{
            restrict: 'A',
            templateUrl: 'view/comanda.html'
        };
    });
    
    app.directive('pedido' , function(){
        return{
            restrict: 'A',
            templateUrl: 'view/pedido.html'
        };     
    });
    
    
})();