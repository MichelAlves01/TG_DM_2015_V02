(function(){
  var app = angular.module('Login-Pages' , []);
  app.directive('login', function(){
  	return{
  		restrict: 'A',
  		templateUrl: 'view-login/login.html'
  	};
  });

  app.directive('inicioCadastro', function(){
  	return{
  		restrict: 'A',
  		templateUrl: 'view-login/inicio-cadastro.html'
  	};
  });

  app.directive('banner', function(){
  	return{
  		restrict: 'A',
  		templateUrl: 'view-login/banner.html'
  	};
  });
})();