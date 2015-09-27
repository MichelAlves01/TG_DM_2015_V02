(function(){

	var app = angular.module('pedido', []);
	var urlBase = 'http://localhost:8080';
	
	app.controller('pedidoCtrl' , function($scope,$http, empresa){

		$scope.cadastrarPedido = function(){
			alert("deu bom pedido");
		}

		
		/*
			pedido status = 0 -> pendente de atualização 
			pedido status = 1 -> pedido aceito 
			pedido status = 2 -> pedido rejeitado
			pedido status = 3 -> pedido finalizado
		*/

		$scope.atualizarStatusPedido = function(idPedido , status){
			var data = $.param({idPedido: idPedido, status: status, cpfCnpj: $scope.empresa.cpfCnpj});
			$http.post(urlBase + '/atualizarStatusPedidoController?' + data).success(function(data,status){
				$scope.pedidos = data;
			});
			$('.pedido-aceitar' + idPedido).hide();
			$('.pedido-rejeitar' + idPedido).hide();
		}


		$scope.calPrecoTotal = function(produtos){
			$scope.total = 0;
			for (var i=0 ; i<produtos.length ; i++) {
				$scope.total += produtos[i].produto.preco;
			};
		}

		$scope.splitValuesObs = function(values){
			values = values.split(",");
			$scope.observacao = values[0];
			$scope.pgtoTipo = values[1];
			$scope.pgtoTipoVal = values[2];
		}
	});
})();