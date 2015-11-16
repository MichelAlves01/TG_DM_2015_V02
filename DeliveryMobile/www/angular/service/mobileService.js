(function(){
    var app = angular.module('mobService' , []);
    var ip = location.host;
    ip = ip.split(':');
     if(ip != null){
        var urlBase = 'http://'+ip[0]+':8080';
    } else {
        var urlBase = 'http://192.168.0.100:8080'
    }
    
      app.controller('CarrinhoCtrl' , ['$scope','$http' , '$log', function($scope, $http, $log){
        $scope.cadastrarPedido = function(data){
             $log.info(data);
            var res = $http.post(urlBase + '/cadastrarPedidoController?' + data).success(function(data, status){
                $log.info('Sucesso');
            });
        };
        
    }]);
    
})();