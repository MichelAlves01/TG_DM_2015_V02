(function(){
	
	var app = angular.module('produtoService' , []);
	var toggleUpdate = true;
	var urlBase = 'http://localhost:8080';

	app.controller('produtoCtrl' , function($scope,$http, empresa){
		var toggleCadastro = true;
		var validAll = true;
		var listItens = [];
		var itemId = null;
		var collapsed = false;
		

		$scope.iniciarCadastroProduto = function(){
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

		$scope.mostrarCadastroProduto = function(){
			if(toggleCadastro == true){
				return false;
			} else {
				return true;
			}
		}

		$scope.validarPrecoProduto = function(){
			if($scope.precoProd != null){
				$scope.precoProd = "R$ " +  $scope.precoProd.replace(/[^0-9^()^]/g,'');;
				$scope.precoProd = $scope.precoProd.replace('R$ R$ ', 'R$ ');
				return true;
			} else {
				return false;
			}		  
		}

		$scope.validarDescProduto = function(){
			if($scope.descricaoProd != null){
				return true;
			} else {
				return false;
			}

		}

		$scope.isValidAllProduto = function(){
				return validAll;
		}

		$scope.mostrarCamposNulosProduto = function(descricao, preco){
			if(validaCamposProduto(descricao, preco)){
				validAll = true;	
			} else {
				validAll = false;
				
			}
		}

		$scope.cadastrarProdutoController = function(descricao, preco){
			if(validaCamposProduto(descricao, preco)){
				var preco = preco.replace('R$ ', '');
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


		function validaCamposProduto(descricao,preco){
			if(descricao != null &&
				preco != null){
				return true;	
			} else {
				return false;
				
			}
		}

		$scope.listProdutos = function(){
				var data = $.param({cpfCnpj: $scope.empresa.cpfCnpj}); 
				$http.get(urlBase + '/getProdutosController?' + data).success(function(data , status){
					$scope.produtos = data;
				})
			
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

		$scope.atualizarFormProduto = function(id){
			var element_accord = "#"+ id;
			var element_update = '#update-form-'+id;
			
			if(toggleUpdate){
				$(element_update).show();
    			toggleUpdate = false;
    		} else {
    			$(element_update).hide();
    			toggleUpdate = true
    		}
		} 

		$scope.cancelUpdateProduto = function(id){

		}

		$scope.mostrarUpdateFormProduto = function(){
			return toggleUpdate;
		}

		$scope.atualizarProdutoController = function(id , descricao, preco){
			if(validaCamposUpdateProduto(id,descricao,preco)){
				var cpfCnpj = $scope.empresa.cpfCnpj;
				var data = $.param({id: id , descricao: descricao , preco: preco , cpfCnpj: cpfCnpj });
				$http.get(urlBase + '/atualizarProdutoController?' + data).success(function(data,status){	
					$scope.listProdutos();
				});
				var element_update = '#update-form-'+id;
				$(element_update).hide();
    			toggleUpdate = true;
			}
		}

		function validaCamposUpdateProduto(id,descricao,preco){
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

	


		function validItem(idItem , idProduto, listItens){
			if(listItens.length == 0 && idItem != null && idProduto != null){
				return true;
			} else {
				for(i=0 ; i < listItens.length ; i++){
					if(listItens[i].item.id == idItem ){
						return false;
					}
				}
			}

			if(idItem != null && idProduto != null){
				itemId = idItem;
				return true;
			}

			return false;	
			
		};

		$scope.getItensProduto = function(idProduto, adicional){
			if(idProduto != null && !collapsed){	
							if(adicional == true){	
								adicionalProduto(idProduto, adicional);	
							} else if(adicional == false){
								itensProduto(idProduto, adicional);
									
							} else {
								adicional = false;
								itensProduto(idProduto, adicional);
								adicional = true;
								adicionalProduto(idProduto, adicional);
							}
					collapsed = true;		
				}
			collapsed = false;
		}

		function itensProduto(idProduto, adicional){
			var data = $.param({idProduto: idProduto , itemAdicional: adicional});
			setTimeout(function(){
			$http.get(urlBase + '/getItensProdutoController?' + data).success(function(data,status){
				$scope.listItensProduto = data;
			});
			}, 100);
		}

		function adicionalProduto(idProduto, adicional){
			var data = $.param({idProduto: idProduto , itemAdicional: adicional});
			setTimeout(function(){
				$http.get(urlBase + '/getItensProdutoController?' + data).success(function(data,status){
					$scope.listAdicionalProduto = data;	
				});
			}, 100);
		}

		$scope.cadastrarItemProduto = function(idProduto , adicional){
				var valid = null;
				console.log( "valid" + valid);
				if(adicional == true){
					valid = validItem(itemId ,idProduto, $scope.listAdicionalProduto);
				} else {
					valid = validItem(itemId ,idProduto, $scope.listItensProduto);
				}
				
				if(itemId != null && valid){
					console.log("id do produto : " + idProduto + "\nItem Id : " + itemId );
					var data = $.param({idItem: itemId , idProduto: idProduto, itemAdicional: adicional});
					$http.post(urlBase + '/cadastrarItemProdutoController?' + data).success(function(data,status){
						$scope.itensProduto = data;
					});
					$scope.getItensProduto(idProduto,adicional);
				}
				
				itemId = null;
		}


		$scope.excluirItemProduto = function(idProduto ,  idItem, adicional){
			console.log('excluindo ...');
			if(idProduto != null && idItem != null){
				var data = $.param({idProduto: idProduto , idItem: idItem});
				$http.get(urlBase + '/excluirItemProdutoController?' + data).success(function(data,status){
					$scope.getItensProduto(idProduto, adicional);
				});
				
			}
		}


		/* 
			####################################################################################################################
			###########################################   Itens Adicionais ######################################################
			####################################################################################################################
		*/

		$scope.droppableAdicionais = function(id){
			console.log('id : ' + id);		    
			$( "#adicionais-produto").droppable({
					activeClass: "ui-state-default",
						hoverClass: "ui-state-hover",
						accept: ":not(.ui-sortable-helper)",
					drop: function(event, ui){
						var data = ui.draggable.context.innerText.split("	");
						itemId = data[0];
						console.log(data[0]);
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


		
	});	

})();