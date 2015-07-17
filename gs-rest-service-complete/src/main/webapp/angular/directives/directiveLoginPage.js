(function(){
  var app = angular.module('Login-Pages' , []);
  app.directive('login', function(){
  	return{
  		restrict: 'A',
  		templateUrl: 'login.html'
  	};
  });

  app.directive('inicioCadastro', function(){
  	return{
  		restrict: 'A',
  		templateUrl: 'inicio-cadastro.html'
  	};
  });

  app.directive('banner', function(){
  	return{
  		restrict: 'A',
  		templateUrl: 'banner.html'
  	};
  });
})();