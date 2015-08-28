(function() {
  var app = angular.module('Login', ['empresaService' , 'Login-Pages']);
  var urlBase = 'http://localhost:8080';

  app.controller('loginCtrl' , function($scope, $http, empresa){
    alert('login');
   
    

  });

  app.controller('CadastrarEmpresaInicio', function ($scope){    
     $scope.redirect = function(){    	
    	  nome = $scope.nome;  	
     }

     $scope.inicioCadastro = function(){
     	alert("deu bom até aqui");
     	cadastroEmpresaParte1.iniciaCadastroEmpresa();
     }
     
  });



})();
