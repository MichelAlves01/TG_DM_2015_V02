(function(){
	
	var app = angular.module('produtoService' , []);

	var urlBase = 'http://localhost:8080';

	app.controller('produtoCtrl' , function($scope,$http,empresa){
		var toggleCadastro = true;
		var toggleUpdate = true;
		var validAll = true;
		

		$scope.iniciarCadastro = function(){
			if(toggleCadastro == true){
				$("#form-produto-add").animate({
        			height: '150px',
        			border: '-bottom: 1px solid #04B404'
    			}, 'fast');
    			$('accordion-toggle').removeAttr('style');
    			toggleCadastro = false;
    			return true;
			} else {
				$("#form-produto-add").animate({
        			height: '0px'
    			});
    			toggleCadastro = true;
    			return false;
			}
			
		}

		$scope.mostrarCadastro = function(){
			if(toggleCadastro == true){
				return false;
			} else {
				return true;
			}
		}

		$scope.validarPreco = function(){
			if($scope.preco != null){
				$scope.preco = "R$ " +  $scope.preco.replace(/[^0-9^()^]/g,'');;
				$scope.preco = $scope.preco.replace('R$ R$ ', 'R$ ');
				return true;
			} else {
				return false;
			}		  
		}

		$scope.validarDesc = function(){
			if($scope.descricao != null){
				return true;
			} else {
				return false;
			}

		}

		$scope.isValidAll = function(){
				return validAll;
		}

		$scope.mostrarCamposNulos = function(){
			if(validaCampos()){
				validAll = true;	
			} else {
				validAll = false;
				
			}
		}

		$scope.cadastrarProdutoController = function(){
			if(validaCampos()){
				var descricao = $scope.descricao;
				var preco = $scope.preco.replace('R$ ', '');
				var cpfCnpj = $scope.empresa.cpfCnpj;
				var data = $.param({descricao: descricao , preco: preco , cpfCnpj: cpfCnpj });
				$http.post(urlBase + '/cadastrarProdutoController?' + data).success(function(data,status){	
						$scope.produto = data;
						$scope.produtos.push($scope.produto);
				});


				$("#form-produto-add").animate({
        			height: '0px'
    			});
    			toggleCadastro = true;
			}

		}


		function validaCampos(){
			if($scope.preco != null &&
				$scope.descricao != null){
				return true;	
			} else {
				return false;
				
			}
		}

		$scope.listProdutos = function(id){
			setTimeout(function(){
				var cpfCnpj = $scope.empresa.cpfCnpj;
				var data = $.param({cpfCnpj: cpfCnpj}); 
				$http.get(urlBase + '/getProdutosController?' + data).success(function(data , status){
					$scope.produtos = data;
				})
			}, 1000);
			
		}

		$scope.removerProduto = function(id){
			var cpfCnpj = $scope.empresa.cpfCnpj;
			var data = $.param({id: id, cpfCnpj: cpfCnpj});
			setTimeout(function(){ 
				$http.get(urlBase + '/excluirProdutoController?' + data).success(function(data,status){
					$scope.produtos = data;
				});
			}, 50);
		}

		$scope.atualizarForm = function(id){
			var element_accord = "#"+ id;
			$(element_accord).hide();
			var element_update = '#update-form-'+id;
			$(element_update).show();
			if(toggleUpdate == true){
    			toggleUpdate = false;
    		} 
		} 

		$scope.cancelUpdate = function(id){
			var element_accord = "#"+ id;
			$(element_accord).show();
			var element_update = '#update-form-'+id;
			$(element_update).hide();
    		toggleUpdate = true;
		}

		$scope.mostrarUpdateForm = function(){
			return toggleUpdate;
		}

		$scope.atualizarProdutoController = function(id , descricao, preco){
			if(validaCamposUpdate(id,descricao,preco)){
				var cpfCnpj = $scope.empresa.cpfCnpj;
				var data = $.param({id: id , descricao: descricao , preco: preco , cpfCnpj: cpfCnpj });
				$http.get(urlBase + '/atualizarProdutoController?' + data).success(function(data,status){	
						$scope.produto = data;
						$scope.produtos.push($scope.produto);
				});
				var element_update = '#update-form-'+id;
				$(element_update).hide();
    			toggleUpdate = true;
			}
		}

		function validaCamposUpdate(id,descricao,preco){
			if(	id != null && 
				descricao != null &&
				preco != null){
				return true;
			} else {
				return false;
			}
		}
	});	

})();