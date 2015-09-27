(function(){

var app = angular.module("app", [
    "ngRoute",
    "app.controllers",
    "app.directives",
    "app.filters"
]);

app.config([
    "$routeProvider",
    "$httpProvider",
    function($routeProvider, $httpProvider){
        $httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = '*';
    }
])})();