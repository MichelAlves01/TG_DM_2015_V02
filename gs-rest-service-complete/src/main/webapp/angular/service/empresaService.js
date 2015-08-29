(function(){
	var app = angular.module('empresaService' , ['pedido',]);
	var urlBase = 'http://localhost:8080';
	var depoisCadastro = true;
	var cpfCnpj;
	var estados;
	var cepValue = null;
	var cidades;
	var msgErro = "";
	var isLogin = true;
	var isCadastro = false;
	var repeat = 0;
	/*
		Controller que contem todas funções 
		utilizadas na pagina de login e cadastro inicial
	*/
	app.controller('cadastroEmpresaInicio', function($scope, $http, $rootScope, empresa) {
		    $http.defaults.headers.post["Content-Type"] = "application/jsonp";
		    var statusCpfCnpj = false;
		    var longitude = null;
		    var latitude = null;
		    var latLongTest = null;
			var cpfExiste = false;
			$rootScope.tabLoaderControl = 'login';
			
			//Acessa o servidor e adiciona as informações iniciais da empresa 
		    $scope.iniciaCadastroEmpresa = function (){
		    	isLogin = false;
		    	isCadastro = true;
		    	var data = $.param({nome: $scope.nome , cpfCnpj: $scope.cpfCnpj})
		    	if(statusCpfCnpj && $scope.nome != null && $scope.tipo == null){
		    	//envia os primeiros parametros do cadastro(Nome e CPF/CNPJ)
		    	$http.post(urlBase + '/iniciaCadastroEmpresa?'+ data).
	        													success(function(data,status) {
	        														$scope.empresa = data;
	        														visibilityControl('cadastro' , true);
	        														$scope.getLatitudeLongitude();
	        													if($scope.empresa.status == 1){
																	$.growlUI('Erro ao enviar','CPF ou CNPJ ja esta cadastrado','E');
																	visibilityControl('login' , false);
																	$scope.cpfCnpj = "";
																	statusCpfCnpj = false;
																} else {
																		depoisCadastro = false;
														    			$scope.getEstados();
																	if($scope.empresa.tipo != null){
																		depoisCadastro = true;
																		latLong = latLong = new google.maps.LatLng($scope.empresa.latitude,$scope.empresa.longitude);
																		
																	}
																}	
	           													 
	           			});
	            } else {
	            	if(!statusCpfCnpj){
	            		$.growlUI('CPF ou CNPJ invalido', 'verifique e tente novamente', 'E'); 
	            	}
	        	}
		    }	



		    /*	Esta função recebe como parametro o cpf ou cnpj
		    	e verica se existe ou não 
		    	retornando um boolean 
		    */
		    $scope.validaCpfCnpj = function(){
		    	//verifica se o campo cpf não esta nulo 
		    	if($scope.cpfCnpj != null){
		    		//substitui letras ou caracteres por '' caso o usuario digite. 
		    		$scope.cpfCnpj = $scope.cpfCnpj.replace(/[^0-9]/g,'');
		    		console.log($scope.cpfCnpj);
		    	}
		    	//Se o tamanho for igual verifica se um cpf valido
		    	if($scope.cpfCnpj.length == 11){
		    		var strCPF = $scope.cpfCnpj;
		    		var Soma; 
		    		var Resto; 
		    		Soma = 0; 
		    		if (strCPF == "00000000000") return false;

		    		for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i); Resto = (Soma * 10) % 11; 
		    		
		    		if ((Resto == 10) || (Resto == 11)) Resto = 0; 
		    			if (Resto != parseInt(strCPF.substring(9, 10)) ) return false; 
		    			
		    			Soma = 0; 
		    			for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i); Resto = (Soma * 10) % 11; 
		    			
		    			if ((Resto == 10) || (Resto == 11)) Resto = 0; 
		    			if (Resto != parseInt(strCPF.substring(10, 11) ) ) return false; 
		    			statusCpfCnpj = true;	
		    			return true;
		    		
		    	} else  
		    	//caso o tamanho da string for igual verifica se é um CNPJ valido.
		    	if($scope.cpfCnpj.length == 14){

		    		var cnpj = $scope.cpfCnpj;
		    		cnpj = cnpj.replace(/[^\d]+/g,'');

				    if(cnpj == '') return false;

				    if (cnpj.length != 14)
				        return false;

				    // LINHA 10 - Elimina CNPJs invalidos conhecidos
				    if (cnpj == "00000000000000" || 
				        cnpj == "11111111111111" || 
				        cnpj == "22222222222222" || 
				        cnpj == "33333333333333" || 
				        cnpj == "44444444444444" || 
				        cnpj == "55555555555555" || 
				        cnpj == "66666666666666" || 
				        cnpj == "77777777777777" || 
				        cnpj == "88888888888888" || 
				        cnpj == "99999999999999")
				        return false; // LINHA 21

				    // Valida DVs LINHA 23 -
				    tamanho = cnpj.length - 2
				    numeros = cnpj.substring(0,tamanho);
				    digitos = cnpj.substring(tamanho);
				    soma = 0;
				    pos = tamanho - 7;
				    for (i = tamanho; i >= 1; i--) {
				      soma += numeros.charAt(tamanho - i) * pos--;
				      if (pos < 2)
				            pos = 9;
				    }
				    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
				    if (resultado != digitos.charAt(0))
				        return false;

				    tamanho = tamanho + 1;
				    numeros = cnpj.substring(0,tamanho);
				    soma = 0;
				    pos = tamanho - 7;
				    for (i = tamanho; i >= 1; i--) {
				      soma += numeros.charAt(tamanho - i) * pos--;
				      if (pos < 2)
				            pos = 9;
				    }
				    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
				    if (resultado != digitos.charAt(1))
				          return false; // LINHA 49
				    statusCpfCnpj = true;	
				    return true; // LINHA 51

				} else {
					statusCpfCnpj = false;
				}
		    	
		    }

		    /*
		    	retorna o valor boolean que foi incrementado 
		    	durante a verificação do CPF ou CNPJ no metodo acima.
		    */
		    $scope.isValidCpfCnpj = function(){
		    	return statusCpfCnpj;
		    }
	  });

		function visibilityControl(visible , cadastro){

				if(visible == 'login'){
					$('.login').show();
					$('.cadastro').hide();
					$('.principal').hide();
				} else if(visible == 'cadastro'){
					$('.login').hide();
					$('.cadastro').show();
					$('.principal').hide();
					visiblityCadastro(cadastro);
				} else if(visible == 'principal'){
					$('.login').hide();
					$('.cadastro').hide();
					$('.principal').show();
					$('#pedidoOp').toggleClass('active');
					$('#tab1').toggleClass('active');
				}
			}

		function visiblityCadastro(cadastro){
			if(cadastro){
				$("#cadastro").removeAttr("disabled");
				$("#atualizar").hide();
				$("#excluir").hide();
				$('#tab-update').removeClass( "active" );
				$('#tab-cadastro').toggleClass( "active" );
			} else {
				$("#cadastro").hide();
		    	$("#atualizar").toggleClass( "active" );
		    	$("#excluir").show();
		    	$('#tab-update').toggleClass( "active" );
			}
		}

		/*
			Controller que executa as funções finais para 
			o cadastro da empresa assim como vizualização  
			atualização e exclusão da conta. 
		*/
		app.controller('EmpresaCtrl', function($scope, $http , empresa) {
			 var fieldsValid = true;
			 var latLong = null;
			 var isLogin;


			 $scope.login = function(username, password){

			      var data = $.param({username: username , password: password});
			      $http.get(urlBase + '/login?' + data).success(function(data , status) {
			      			setTimeout(function(){
			      				$scope.empresa = data;
								cpfCnpj = data.cpfCnpj;
								if($scope.empresa != ""){
									visibilityControl('principal' , false);
									$scope.getPedidosController(cpfCnpj);
								} else {
									$.growlUI('Senha ou usuario incorreto', 'verifique e tente novamente', 'E'); 
								}
								
								/*
								 	se o tipo ainda estiver nulo ativas apenas aba de cadastro
									pois a empresa ainda não esta cadastrada senão vai para a tela 
									principal.
								*/
								if($scope.empresa.tipo == null){
									isLogin = false;
									depoisCadastro = false;
					    			$("#cadastro").removeAttr("disabled");
					    			$("#atualizar").hide();
					    			$("#excluir").hide();
					    			$('#tab-update').removeClass( "active" );
					    			$('#tab-cadastro').toggleClass( "active" );
					    		}


					    			depoisCadastro = true;
					    			$('#tab-cadastro').removeClass( "active" );
					    			latLong = latLong = new google.maps.LatLng($scope.empresa.latitude,$scope.empresa.longitude);
					    			
					    	
			     			
			      			}, 1000);	
			      		
			      });

			      
			      
			  }

			  $scope.getPedidosController = function(cpfCnpj){
			
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
			/*
				verifica quais abas devem estar ativas 
				durante o cadastro e recupera os dados iniciais do 
				cadastro informados anteriormente na pagina de login.
			*/
			

			$scope.tabLoaderControl = function(){

			}

			$scope.starting = function(){
					visibilityControl('login' , false);
					
					/*
					 	se o tipo ainda estiver nulo ativas apenas aba de cadastro
						pois a empresa ainda não esta cadastrada senão vai para a tela 
						principal.
					*/

						
	
		    			depoisCadastro = true;
		    			$('#tab-cadastro').removeClass( "active" );
		    			

					
				}



			 

			/*
				verifica se todos os campos estão validos e retorna 
				um boolean de verificação sendo true quando todos
				os campos foram preenchidos.
			*/
			function setValidFields() {
				if($scope.tipo != null &&
				$scope.cidade != null &&
				$scope.bairro != null && 
				$scope.endereco != null && 
				$scope.numero != null &&
				$scope.cep != null &&
				$scope.telFixo != null &&
				$scope.telMovel != null &&
				$scope.email != null &&
				$scope.senha != null){

					fieldsValid = true;
				} else {
					fieldsValid = false;
				}

				return fieldsValid;
				
			}

			/**
			* Envia todos os dados informados para a API(servidor) que
			* persistira na base de dados , caso houver campos nulos 
			* uma mensagem de erro será exibida.	
			*/
			$scope.executarCadastro = function(){
				if(setValidFields()){
					var tipo = $scope.tipo;
					var cidade = $scope.cidade.id;
					var endereco = $scope.bairro + "||" + $scope.endereco + "||" + $scope.numero;
					var cep = $scope.cep;
					var telFixo = $scope.telFixo;
					var telMovel = $scope.telMovel;
					var email = $scope.email;
					var senha = $scope.senha;		
					var data = $.param({tipo: tipo, 
										idCidade: cidade,
										endereco: endereco,
										email: email,
										telefoneFixo: telFixo,
										telefoneMovel: telMovel,
										cep: cep,
										latitude: latitude,
										longitude: longitude,
										senha: senha});
								
								console.log("enviando para o servidor");
								
									$http.post(urlBase + '/cadastrarEmpresaController?' + data).success(function(data,status){
										setTimeout(function(){
										$scope.empresa = data;
										$scope.nome = data.nome;
										$scope.cpfCnpj = data.cpfCnpj;
										$.growlUI('Cadastrado com sucesso' , '', 'C');
										$('#popOverMapa').popover('show')
									} , 100);
									});

									depoisCadastro = true;
									$('#pedidoOp').toggleClass('active');
									$('#tab3').removeClass('active');
									$('#tab4').removeClass('active');
									visibilityControl('principal' , false);
									$scope.getLatitudeLongitude();
								
								
				} else {
					console.log("Existem campos não preenchido");
					$.growlUI('Erro ao cadastrar','Preencha todos os campos', 'E');
					for(s=0 ; s<5;s++){
						loop(i = 0);
					}
					
				}	
			}

			
			function loop(i){
				i++;
				var iconAnimated = $('#animate' + i);
				console.log("deu bom" + i);
				iconAnimated.animate({top: '8px'},"slow");
				iconAnimated.animate({top: '0px'},"slow");
					
				if(i < 11){
					loop(i);	
				}
				
				}				

			/*
				Sera executado ao iniciar a pagina principal retornando 
				todas os estados e cidades do Brasil.
			*/
			$scope.getEstados = function(){
			if($scope.estados == null){
				$http.get('http://localhost:8080/getEstados').success(function(data){
					$scope.estados = data;
					estados = data;
				})
			} 
			
		}

		/*
			Verifica o valor definido no campo estado e adiciona no 
			scope cidades todas as cidades referente ao estado.
		*/
		$scope.getCidades = function(){
			if($scope.estado != null){			
				for(i=0;i<estados.length;i++){
					console.log($scope.estado.id +" == "+ estados[i].id);
					if($scope.estado.id == estados[i].id){
						$scope.cidades = $scope.estado.cidade;
					}
				}
			}
		}

		/*
			Obtem informações de latitude e longitude e atualiza o mapa,
			tambem mostra o mapa na tela com um marcador no endereço informado.  
		*/
		$scope.getLatitudeLongitude = function(){
			
					//inicia o mapa com uma localização padrão.
					if(latLong == null){
						latLong = new google.maps.LatLng(-23.5505199,-46.63330939999997);
					}

					var valorZoom;
					setTimeout(function() {
						//define o valor de zoom quando o endereço for o definido pelo usuario.
						if($scope.estado == null){
							valorZoom = 4;
						} else {
							valorZoom = 15;
						}
					
					//redireciona o mapa para o endereço inicial.
					var mapOptions = {
					    zoom: valorZoom,
					    center: latLong
					  }

					var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
					var map2 = new google.maps.Map(document.getElementById('map-canvas2'), mapOptions);
					var geocoder = new google.maps.Geocoder();

					//quando o estado for diferente de nulo redireciona o mapa,
					//para o endereço informado pelo usuario.
					if($scope.estado != null){
						var estado = $scope.estado.nome;
						var cidade = $scope.cidade.nome;
						var bairro = $scope.bairro;
						var endereco = $scope.endereco;
						var numero = $scope.numero;
						var address = estado + ", " + cidade + ", " + bairro + ", " + endereco;
					} else {
						var address = "Brasil , Brasilia";
					}

					/*
						As funções abaixo obtem a latitude e longitude ,
						do endereço informado. e adiciona o marcador no endereço.
					*/
				  	geocoder.geocode( { 'address': address}, function(results, status) {
				  	setTimeout(function(){
				  		if (status == google.maps.GeocoderStatus.OK) {
				      	map.setCenter(results[0].geometry.location);
				      	map2.setCenter(results[0].geometry.location);
				   		latLong = results[0].geometry.location;
				   		var resLatLong = latLong.toString().split(","); 
				   		latitude = resLatLong[0].replace("(", ""); 
				   		longitude = resLatLong[1].replace(")", "");

				   	if(address != "Brasil , Brasilia"){	
				   	var marker = new google.maps.Marker({
					     position: latLong,
					     map: map,
					     draggable:true,
    					animation: google.maps.Animation.DROP
					  });
					 }

				    

					

				    } else {
				      alert('Geocode was not successful for the following reason: ' + status);
				    }
				    } , 100);
				  });
				}, 100);
		}

		
		/*
			Validaçoes de todos campos no cadastro de empresas, se
			e quando for false mostra os icones e mensagem, mostrando 
			ao usuario os campos corretos e os que estão errados.
		*/
		$scope.isValidAll = function(){	
				return fieldsValid;
		}

		/*
			verifica se o campo tipo não esta nulo.
		*/
		$scope.isValidTipo = function(){			
			if($scope.tipo != null){
				return true;
			} else {
				return false;
			}
		}

		/*
			verifica se o campo cidade não esta nulo
		*/
		$scope.isValidCidade = function(){
			if($scope.cidade != null){
				return true;
			} else {
				return false;
			}
		}

		/*
			verifica se o campo endereço não esta nulo
		*/
		$scope.isValidEndereco = function(){
			if($scope.endereco != null){
				return true;
			} else {
				return false;
			}
		}

		/*
			verifica se o campo numero não esta nulo
		*/
		$scope.isValidNumero = function(){
			if($scope.numero != null){
				$scope.numero.replace(/[^0-9]/g,'');
				return true;
			} else {
				return false;
			}
		}

		/*
			envia o valor do campo cep como parametro para o 
			endereço < http://cep.correiocontrol.com.br/ > o qual
			retornará um JSON com os dados do CEP(nome cidade, sigla estado e nome da rua)
		*/
		$scope.verificarCep = function(cep){
				
				if(cepValue != null && cep != cepValue.cep){
					cepValue = null;
				}

				if(cep != null && cep.length == 8){
					$http.get('http://cep.correiocontrol.com.br/'+ cep +'.json').success(function(data,status){
						cepValue = data;
					});

						$scope.endereco = cepValue.logradouro;
						$scope.bairro = cepValue.bairro;			
			
						for(i=0 ; i<estados.length ; i++){
							if(estados[i].sigla == cepValue.uf){
								$scope.estado = estados[i];
								$scope.cidades = estados[i].cidade; 
								cidades = estados[i].cidade;
									for(k=0 ; k< cidades.length ; k++){
										if(cidades[k].nome == cepValue.localidade){
											console.log(cidades[k].nome);
											$scope.cidade = cidades[k]; 
										}
									}
							}
						}
				}
			
				
		}


		/*
			verifica se o campo bairro está nulo
		*/
		$scope.isValidBairro = function(){
			if($scope.bairro != null){		
					return true;
				} else {
					return false;
			}
		}

		/*
			verifica se o campo cep não está nulo
		*/
		$scope.isValidCep = function(){
			if($scope.cep != null){
				return true;
			} else {
				return false;
			}
		}

		/*
			verifica se o campo telefone fixo está nulo
		*/
		$scope.isValidTelFixo = function(){
			if($scope.telFixo != null){
				$scope.telFixo = $scope.telFixo.replace(/[^0-9^()^ ^-]/g,'');
				if(event.keyCode != 8 && event.keyCode != null){
					$scope.telFixo = $scope.telFixo.length == 1 && '(' + $scope.telFixo || $scope.telFixo;
					$scope.telFixo = $scope.telFixo.length == 3 && $scope.telFixo + ') ' || $scope.telFixo;
					$scope.telFixo = $scope.telFixo.length == 9 && $scope.telFixo + '-' || $scope.telFixo;
				}  
				return true;
			} else {
				return false;
			}
		}

		/*
			verifica se o campo telefone móvel está nulo
		*/
		$scope.isValidTelMovel = function(e){
			if($scope.telMovel != null){
				$scope.telMovel = $scope.telMovel.replace(/[^0-9^()^ ^-]/g,'');
				if(event.keyCode != 8 && event.keyCode != null){
					$scope.telMovel = $scope.telMovel.length == 1 && '(' + $scope.telMovel || $scope.telMovel;
					$scope.telMovel = $scope.telMovel.length == 3 && $scope.telMovel + ') ' || $scope.telMovel;
					$scope.telMovel = $scope.telMovel.length == 10 && $scope.telMovel + '-' || $scope.telMovel;
				}
				return true;
			} else {
				return false;
			}
		}

		/*
			verifica se o campo email está nulo
		*/
		$scope.isValidEmail = function(){
			if($scope.email != null){
				return true;
			} else {
				return false;
			}
		}

		/*
			verifica se o campo senha está nulo,
			também se as senhas são iguais.
		*/
		$scope.isValidSenha = function(){
			if($scope.senha != null && $scope.senha == $scope.confirmaSenha){
				return true;
			} else {
				return false;
			}
		}

		/*
			retorna o resultado da variavel de verificação boolen
			que retorna true quando o usuario ja possue um cadastro,
			esta verivel permite alternar entre mostrar tela principal e tela de 
			cadastro. 
		*/
		$scope.depoisCadastro = function(){
			return depoisCadastro;
		}
		
		/*
			Esta função permite recuperar os dados da empresa 
			de acordo com o cpf ou cnpj informado.
		*/
		$scope.getEmpresaController = function(cpfCnpj){
					depoisCadastro = false;
			 		visibilityControl('cadastro' , false);

				var data = $.param({cpfCnpj: cpfCnpj});
				$http.get(urlBase + '/getEmpresaController?' + data).success(function(data,status){
					$scope.nome = data.nome;
					$scope.cpfCnpj = data.cpfCnpj;
					$scope.tipo = data.tipo;
					$scope.estado = data.cidade.estado;
					$scope.cidade = data.cidade;
					var enderecoS = data.endereco.split("||");
					$scope.bairro = enderecoS[0];
					$scope.endereco = enderecoS[1];
					$scope.numero = enderecoS[2];
					$scope.cep = data.cep;
					$scope.telFixo = data.telefoneFixo;
					$scope.telMovel = data.telefoneMovel;
					$scope.email = data.email;
				});

			}

			/*
				Executa a atualização dos dados se todos os campos 
				estiverem preenchidos , senão mostra mensagem de erro ao usuario.
			*/
			$scope.executaAtualizacao = function(){
				if(setValidFields()){
					var nome = $scope.nome;
					var cpfCnpj = $scope.cpfCnpj;
					var tipo = $scope.tipo;
					var cidade = $scope.cidade.id;
					var endereco = $scope.bairro + "||" + $scope.endereco + "||" + $scope.numero;
					var cep = $scope.cep;
					var telFixo = $scope.telFixo;
					var telMovel = $scope.telMovel;
					var email = $scope.email;		
					var data = $.param({cpfCnpj: cpfCnpj,
										nome: nome,
										tipo: tipo, 
										idCidade: cidade,
										endereco: endereco,
										email: email,
										telefoneFixo: telFixo,
										telefoneMovel: telMovel,
										cep: cep,
										latitude: latitude,
										longitude: longitude});
								
								console.log("enviando para o servidor");
								$http.post(urlBase + '/atualizarEmpresaController?' + data).success(function(data,status){
									$scope.empresa = data;
								});

								depoisCadastro = true;
								$.growlUI('Atualizado com sucesso.', '', 'C');
				} else {
					console.log("Existem campos não preenchido");
					$.growlUI('Erro ao Atualizar','Existem campos não preenchido','E');
				}

			}

			//esta função verifica se todos os campos foram preenchidos corretamente.
			function setValidFields() {
				if($scope.tipo != null &&
				$scope.cidade != null &&
				$scope.bairro != null && 
				$scope.endereco != null && 
				$scope.numero != null &&
				$scope.cep != null &&
				$scope.telFixo != null &&
				$scope.telMovel != null &&
				$scope.email != null){

					fieldsValid = true;
				} else {
					fieldsValid = false;
				}

				return fieldsValid;
				
			}

			/*
				Esta função atualiza o status da empresa para 1, inativando o acesso do 
				usurio a conta e o redireciona para a tela de login. 
			*/
			$scope.excluirEmpresa = function(){
				var data = $.param({cpfCnpj: $scope.cpfCnpj});

				$http.post(urlBase + '/excluirEmpresaController?' + data).success(function(data,status){

				});
				location.href = "/";
			}
		
		});
	
	app.factory('empresa', function () {
    	return { cpfCnpj: '' };
	});

	app.controller('mapCtrl' , function($scope, $http){

	});


 


})();