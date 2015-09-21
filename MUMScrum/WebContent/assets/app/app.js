'use strict';

/* App Module */

var scrumApp = angular.module('scrumApp', [
  'ngRoute',
  'ngResource',
  'ngAnimate',
  'toaster',
  'scrumApp.auth',
  'scrumApp.employee',
  'scrumApp.product'
])
.config(['$routeProvider',function($routeProvider){
	// $routeProvider
	// 	.otherwise({
	// 		redirectTo: '/login'
	// 	})
}])
.run(['$rootScope', '$location',
    function ($rootScope, $location) {
        // keep user logged in after page refresh
        // if ($rootScope.globals.currentUser) {
        //     $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        // }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            //if ($location.path() !== '/login' && $rootScope.globals.currentUser) {
            // if ($location.path() !== '/login') {
            //     $location.path('/login');
            // }
        });
    }]);