(function(){
	var app = angular.module('usuarioMobile' , []);

	app.controller('usuarioCtrl', function($scope,$http){

		$scope.showUsuario = function(){
			$("#container").hide();
		}

		$scope.cadastrarUsuarioMobile = function(){
			alert('deu bom');
		}
	})
})();