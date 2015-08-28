(function(){

	var app = angular.module('pedido', []);
	var urlBase = 'http://192.168.0.11:8080';
	
	app.controller('pedidoCtrl' , function($scope,$http, empresa){

		$scope.cadastrarPedido = function(){
			alert("deu bom pedido");
		}

		

		$scope.aceitarPedido = function(idPedido){
			var data = $.param({idPedido: idPedido, status: 1, cpfCnpj: $scope.empresa.cpfCnpj});
			$http.post(urlBase + '/atualizarStatusPedidoController?' + data).success(function(data,status){
				$scope.pedidos = data;
			});
			$('.pedido-aceitar').hide();
			$('.pedido-rejeitar').hide();
		}

		$scope.rejeitarPedido = function(idPedido){
			var data = $.param({idPedido: idPedido, status: 2 , cpfCnpj: $scope.empresa.cpfCnpj});
			$http.post(urlBase + '/atualizarStatusPedidoController?' + data).success(function(data,status){
				$scope.pedidos = data;
			});
			$('.pedido-aceitar').hide();
			$('.pedido-rejeitar').hide();
		}
	});
})();