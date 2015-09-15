'use strict';
	angular
		.module('scrumApp.auth',[])
		.factory('AuthService',['$http','$rootScope',function($http,$scope){
			var service = {};
			service.login = function(username,password,callback){
				$http
					.post('/login',{username: username,password: password})
					.success(function(response){
						callback(response);
					})
			};

			service.setCredentials = function(username,user_role){
				$rootScope.globals = {
					current_user: {
						username: username,
						user_role: user_role
					}
				};
			}

			
			return service;
		}]);