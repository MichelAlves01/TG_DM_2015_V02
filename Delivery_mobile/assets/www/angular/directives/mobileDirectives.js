(function(){
	var app = angular.module('mobileDirectives' , []);

	app.directive('principal' , function(){
		return{
			restrict: 'A',
			templateUrl: 'view/principal.html'
		};
	});

	app.directive('usuario', function(){
		return{
			restrict: 'A',
			templateUrl: 'view/usuario.html'
		};
	});
})();