(function(){
    var app = angular.module('mobService' , []);
    
      app.controller('CarrinhoCtrl' , ['$scope','$http' , '$log', function($scope, $http, $log){
         var urlBase = 'http://192.168.0.11:8080';
        $scope.cadastrarPedido = function(data){
             $log.info(data);
            var res = $http.post(urlBase + '/cadastrarPedidoController?' + data).success(function(data, status){
                $log.info('Sucesso');
            });
        };
        
    }]);
    
})();