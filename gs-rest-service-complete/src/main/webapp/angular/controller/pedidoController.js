(function(){

	var app = angular.module('pedido', []);
	var urlBase = 'http://192.168.0.11:8080';
	var repeat = 0;
	app.controller('pedidoCtrl' , function($scope,$http, empresa){

		$scope.cadastrarPedido = function(){
			alert("deu bom pedido");
		}

		$scope.getPedidosController = function(){
			
						if(repeat == 0){
							setTimeout(function(){
								var data = $.param({cpfCnpj: $scope.empresa.cpfCnpj});
								$http.get(urlBase + '/getPedidoController?' + data ).success(function(data,status){
									$scope.pedidos = data;
								});
							}, 100);
							repeat = 1;
						}
						setTimeout(function(){
							var data = $.param({cpfCnpj: $scope.empresa.cpfCnpj});
							$http.get(urlBase + '/getPedidoController?' + data ).success(function(data,status){
								$scope.pedidos = data;
							});
							$scope.getPedidosController();
						}, 10000);
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