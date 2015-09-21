'use strict';

angular
.module('scrumApp.authentication',[])
.config(['$routeProvider',function($routeProvider){
	$routeProvider
	.when('/login',{
		controller: 'LoginCtrl',
		templateUrl: 'authentication/login.html?'+ Date.now()
	})
}])
.controller('LoginCtrl',['$scope','AuthService','$location',function($scope,AuthService,$location){

	AuthService.ClearCredentials();
	$scope.login = function(){
		AuthService.login($scope.username,$scope.password,function(response){
			if(response.success)
			{
				console.log(response)
				AuthService.setCredentials(response.username,response.password,response.role);
				$location.path(response.route);
			}else{
				$scope.message = response.message;
			}
		});
	}
}]);