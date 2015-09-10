'use strict';

/* Controllers */

var scrumAppControllers = angular.module('ScrumAppControllers', []);

scrumAppControllers.controller('HomeCtrl', ['$scope',
	function($scope){
    	$scope.name = "scrum tool";
  }]);

scrumAppControllers.controller('EmployeeListCtrl', ['$scope','Employees',
	function($scope,Employees){
    	$scope.name = "Employee List Ctrl";
    	$scope.employees = Employees.query();
  }]);
