(function(){
	var app = angular.module('usuarioMobile' , []);
	var urlBase = 'http://192.168.0.11:8080';


	app.controller('usuarioCtrl', function($scope,$http){
		$http.defaults.headers.post["Content-Type"] = "application/jsonp";
		$scope.showUsuario = function(){
			$("#containerPrincipal").hide();
            $("#containerProdutos").hide();
            $("#containerUsuario").show();
		}

		$scope.cadastrarUsuarioMobile = function(){
			var nomeUsuario = nomeUsuario;
			var email = $scope.email;
			var senha = $scope.senha;
			var data = $.param({nomeUsuario: $scope.nomeUsuario,
								email: email,
								senha : senha});
			if($scope.validaUsuario()){
				$http.get(urlBase + '/cadastrarUsuarioMobileController?' + data).success(function(status , data) {
					$scope.usuario = data;
				});
			}
			alert($scope.nomeUsuario);
		}

		$scope.validaUsuario = function(){
			if($scope.nomeUsuario !== null &&
				$scope.email !== null &&
				$scope.senha !== null){
				return true;
			}else{
				return false;
			} 
		}
		
	});
})();