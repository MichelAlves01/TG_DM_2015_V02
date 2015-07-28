(function(){

	var app = angular.module('pedido', []);

	app.controller('pedidoCtrl' , function($scope,$http,empresa){

		$scope.cadastrarPedido = function(){
			alert("deu bom");
		}
	})
})();