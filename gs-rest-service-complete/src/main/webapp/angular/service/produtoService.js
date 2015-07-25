(function(){
	
	var app = angular.module('produtoService' , []);

	var urlBase = 'http://localhost:8080';

	app.controller('produtoCtrl' , function($scope,$http,empresa){
		var toggleCadastro = true;
		var toggleUpdate = true;
		var validAll = true;
		var listItens = [];
		var itemId = null;
		var collapsed = false;
		

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

		/*
			###########################      funcção drag and drop ######################################### 
			este metodo é responsável por receber os itens, assim como validar e enviar para o servidor  

		*/
		$scope.droppableItens = function(id){
			console.log('id : ' + id);		    
			$( "#itens-produto").droppable({
					activeClass: "ui-state-default",
						hoverClass: "ui-state-hover",
						accept: ":not(.ui-sortable-helper)",
					drop: function(event, ui){
						var data = ui.draggable.context.innerText.split("	");
						itemId = data[0];
						console.log(data[0]);
						listItens.push(data);
					}
			}).sortable({
     			 items: "div:not(.placeholder)",
      				sort: function() {
        			// gets added unintentionally by droppable interacting with sortable
        			// using connectWithSortable fixes this, but doesn't allow you to customize active/hoverClass options
        			$( this ).removeClass( "ui-state-default" );
      			}
   			 });
		}


		function validItem(idItem , idProduto){
			if($scope.listItensProduto.length == 0){
				return true;
			} else {
				for(i=0 ; i < $scope.listItensProduto.length ; i++){
					if($scope.listItensProduto[i].item.id == idItem ){
						return false;
					}
				}
			}	
			itemId = idItem;
			return true;
		};

		$scope.getItensProduto = function(idProduto){
			if(idProduto != null && !collapsed){
					var data = $.param({idProduto: idProduto});
						$http.get(urlBase + '/getItensProdutoController?' + data).success(function(data,status){
						$scope.listItensProduto = data;
					});
					collapsed = true;		
				}
			collapsed = false;
			return $scope.listItensProduto;
		}

			 $scope.cadastrarItemProduto = function(idProduto){
				
				console.log(data);
				if(itemId != null && validItem(itemId ,idProduto)){
					console.log("id do produto : " + idProduto + "\nItem Id : " + itemId );
					var data = $.param({idItem: itemId , idProduto: idProduto});
					$http.post(urlBase + '/cadastrarItemProdutoController?' + data).success(function(data,status){
					$scope.itensProduto = data;
				});
					$scope.listItensProduto = $scope.getItensProduto(idProduto);
				}
				
				itemId = null;
			}
	});	

})();