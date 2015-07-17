(function(){
	var app = angular.module('cadastro-empresa-pages',[]);

	app.directive('cadastro', function(){
		return{
			restrict: 'A',
			templateUrl: 'view-cadastro/cadastro-empresa.html'
		};
	});

	app.directive('atualizar', function(){
		return{
			restrict: 'A',
			templateUrl: 'view-cadastro/atualizar-empresa.html'
		};
	});

	app.directive('preenchido', function(){
		return{
			restrict: 'A',
			templateUrl: 'view-cadastro/cadastro-empresa-preenchido.html'
		};
	});

	app.directive('mapa', function(){
		return{
			restrict: 'A',
			templateUrl: 'view-cadastro/mapa.html'
		};
	});
	
	app.directive('buttons', function(){
		return{
			restrict: 'A',
			templateUrl: 'view-cadastro/buttons.html'
		};
	});

	app.directive('ebuttons', function(){
		return{
			restrict: 'A',
			templateUrl: 'view-cadastro/Ebuttons.html'
		};
	});

		app.directive('dados', function(){
		return{
			restrict: 'A',
			templateUrl: 'view-cadastro/dados-empresa.html'
		};
	});

})();