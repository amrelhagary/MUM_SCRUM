'use strict';

/* App Module */

var scrumApp = angular.module('scrumApp', [
  'ngRoute',
  'ScrumAppControllers',
  'ScrumAppServices'
]);

scrumApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
        templateUrl: 'partials/home.html',
        controller: 'HomeCtrl'
      }).
      when('/employees', {
        templateUrl: 'partials/employees.html',
        controller: 'EmployeeListCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);