'use strict';

angular
	.module('scrumApp.auth',[])
	.config(['$routeProvider',function($routeProvider){
		$routeProvider
			.when('/login',{
				controller: 'loginCtrl',
				templateUrl: 'authentication/login.html'
			})
	}])
	.controller('loginCtrl',['$scope','AuthService','$location',function($scope,AuthService,$location){
		console.log('hello')
		$scope.login = function(){
			AuthService.login($scope.username,$scope.password,function(response){
				if(response.success)
				{
					AuthService.setCredentials(response.username,response.user_role);
					$location.path('/');
				}else{
					$scope.message = response.message;
				}
			});
		}
	}]);