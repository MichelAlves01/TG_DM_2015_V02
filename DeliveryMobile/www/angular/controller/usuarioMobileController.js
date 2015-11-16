(function(){
	var app = angular.module('usuarioMobile' , []);
	var ip = location.host;
    ip = ip.split(':');
     if(ip != null){
        var urlBase = 'http://'+ip[0]+':8080';
    } else {
        var urlBase = 'http://192.168.0.100:8080'
    }


	app.controller('usuarioCtrl', function($scope,$http){
		$http.defaults.headers.post["Content-Type"] = "application/jsonp";
		

		$scope.cadastrarUsuarioMobile = function(){
			var nomeUsuario = $scope.nomeUsuario;
			var email = $scope.email;
			var senha = $scope.senha;
			var data = $.param({nomeUsuario: $scope.nomeUsuario,
								email: email,
								senha : senha});
            
            if( window.localStorage.length == 0 && validaUsuario()){
                     registrarCache();
				    $http.get(urlBase + '/cadastrarUsuarioMobileController?' + data).success(function(status , data) {
					   $scope.usuario = data;
				    });
            }  else {
                     registrarCache();
                    $http.get(urlBase + '/atualizarUsuarioMobileController?' + data).success(function(status , data) {
					   $scope.usuario = data;
				    });
            }
            
           
		}
        
         function registrarCache(){
                window.localStorage.setItem('nome', $scope.nomeUsuario);
                window.localStorage.setItem('email', $scope.email);
                window.localStorage.setItem('senha' , $scope.senha);
        }
        
		function validaUsuario(){
			if($scope.nomeUsuario !== null &&
				$scope.email !== null &&
				$scope.senha !== null){
				return true;
			}else{
				return false;
			} 
		}
        
        $scope.getUsuario = function(){
            if(window.localStorage != null){
                $scope.nomeUsuario = window.localStorage.getItem('nome');
                $scope.email = window.localStorage.getItem('email');
                if($scope.email != null){
                    $('#email').attr('disabled', 'true');
                }
                $scope.senha = window.localStorage.getItem('senha');
            }
        }
        
        $scope.getPedidosAnteriores = function(){
            var data = $.param({email: window.localStorage.getItem('email')});
            $http.get(urlBase + '/getPedidoPorUsuario?' + data).success(function(data,status){
                $scope.pedidos = data;
            });
        }
        
        $scope.avaliarEmpresa = function(idPedido){
             $.blockUI({ message: $('#avaliarEmpresa' + idPedido)}); 
        }
        
        $scope.cadastrarAvaliacao = function(cpfCnpj , nota){
            var data = $.param({cpfCnpj: cpfCnpj, nota: nota});
            $http.get(urlBase + '/avaliarEmpresaController?' + data).success(function(data,status){
                $.unblockUI();
            });
        }
        
     
		
	});
})();