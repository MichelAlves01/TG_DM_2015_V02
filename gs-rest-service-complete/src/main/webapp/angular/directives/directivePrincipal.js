(function() {

  var app = angular.module('DirectivesPrincipal', []);

  app.directive('cabecalho' , function(){
  	return{
  		restrict: 'A',
  		templateUrl: 'view-principal/cabecalho.html'
  	};
  });

  app.directive('pedido' , function(){
  	
  	return{
  		restrict: 'A',
  		templateUrl: 'view-principal/pedido-tab.html'
  	};
  });

   app.directive('produto' , function(){
    
    return{
      restrict: 'A',
      templateUrl: 'view-principal/produto-tab.html'
    };
  });

   app.directive('mapatab', function(){
    return{
      restrict: 'A',
      templateUrl: 'view-principal/map-tab.html'
    };
   });
   

})();