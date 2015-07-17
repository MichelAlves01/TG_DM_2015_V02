'use strict';
	
	var app = angular
		.module('delivery',['empresa'])
			.config(function($routeProvider){
				$routeProvider.when('/',{
					templateUrl: '../views/DeliveryApp.html',
					controller: 'empresa'
				});
	});
