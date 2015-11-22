(function(){
	
	var app = angular.module('itemService' , []);

	var urlBase = 'http://localhost:8080';

	app.controller('itemCtrl' , function($scope,$http){
		var toggleCadastro = true;
		var toggleUpdate = true;
		var validAll = true;
		
		// expande ou contrai o formulario para cadastro de item
		$scope.iniciarCadastroItem = function(){
			if(toggleCadastro){
				$("#form-item-add").animate({
        			height: '150px',
        			border: '-bottom: 1px solid #04B404'
    			}, 'fast');
    			$('accordion-toggle').removeAttr('style');
    			toggleCadastro = false;
    			$scope.item.descricao = "";
    			$scope.item.preco = "";
    			return true;
			} else {
				$("#form-item-add").animate({
        			height: '0px'
    			});
    			toggleCadastro = true;
    			return false;
			}
			
		}

		// mostra ou esconde o formulario para cadastro.
		$scope.mostrarCadastroItem = function(){
			if(toggleCadastro == true){
				return false;
			} else {
				return true;
			}
		}

		$scope.validarPrecoItem = function(){
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

		$scope.mostrarCamposNulos = function(descricao,preco){
			if(validaCampos(descricao,preco)){
				validAll = true;	
			} else {
				validAll = false;
				
			}
		}

		// realiza cadastro de Item 
		$scope.cadastrarItemController = function(descricao,preco){
			if(validaCampos(descricao,preco)){
				var cpfCnpj = $scope.empresa.cpfCnpj;
				var data = $.param({descricao: descricao , preco: preco , cpfCnpj: cpfCnpj });
				$http.post(urlBase + '/cadastrarItemController?' + data).success(function(data,status){
						$scope.itens = data;
						$.growlUI('Cadastrado com sucesso', '', 'C');
				});


				$("#form-item-add").animate({
        			height: '0px'
    			});
    			toggleCadastro = true;
			} else {
				$.growlUI('Erro ao cadastrar' , 'verifique e tente novamente' , 'E');
			}

		}


		function validaCampos(descricao,preco){
			if(preco != null &&
				descricao != null && isNaN(preco) == false){
				return true;	
			} else {
				return false;
				
			}
		}

		// realiza uma busca no servidor e retorna todos os Itens
		$scope.listItens = function(cpfCnpj){
				var data = $.param({cpfCnpj: cpfCnpj}); 
				$http.get(urlBase + '/getItensController?' + data).success(function(data , status){
					$scope.itens = data;
				})
			
		}

		// exclui Item.
		$scope.removerItem = function(id){
			var cpfCnpj = $scope.empresa.cpfCnpj;
			var data = $.param({id: id, cpfCnpj: cpfCnpj});

			setTimeout(function(){ 
				$http.get(urlBase + '/excluirItemController?' + data).success(function(data,status){
					$scope.itens = data;
					$.growlUI('Excluido com sucesso' , '' , 'C');
				});
			}, 50);
		}

		function vericarItemEmUso(){

		}

		$scope.atualizarForm = function(id){
			var element_update = '#update-form-'+id;
			$(element_update).show();
		} 

		$scope.cancelUpdate = function(id){
			var element_update = '#update-form-'+id;
			$(element_update).hide();
		}

		// Recebe dados atualizados do produto e envia para o servidor.
		$scope.atualizarItemController = function(id , descricao, preco){
			if(validaCamposUpdate(id,descricao,preco)){
				var cpfCnpj = $scope.empresa.cpfCnpj;
				var data = $.param({id: id , descricao: descricao , preco: preco , cpfCnpj: cpfCnpj });
				$http.get(urlBase + '/atualizarItemController?' + data).success(function(data,status){	
						$scope.produto = data;
						$scope.produtos.push($scope.produto);
						$.growlUI('Atualizado com sucesso', '', 'C');
				});
				var element_update = '#update-form-'+id;
				$(element_update).hide();
    			toggleUpdate = true;
			} else {
				$.growlUI('Erro ao atualizar' ,'verifique e tente novamente' , 'E');
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

		// Registra funcção de arrastar para o item.
		$scope.draggableItem = function(){		
			$( ".accordion-group" ).draggable({ revert: "invalid",
												appendTo: "body",
      											helper: "clone",
      											cursor: "move", 
      											cursorAt: { top: 30, left: 30 },
      											drag: function(){
      												$( this ).find( "font" );
      											}});
		}
	
	});	

})();