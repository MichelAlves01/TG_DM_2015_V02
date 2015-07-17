(function(){
	
	var app = angular.module('produtoService' , []);

	app.controller('produtoCtrl' , function($scope,$http){
		var toggleCadastro = true;
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

		$scope.mostrarErros = function(){
			if($scope.preco != null &&
				$scope.descricao != null){
				validAll = true;
			} else {
				validAll = false;
				
			}
		}


	});	


})();