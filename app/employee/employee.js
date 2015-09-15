'use strict';

angular
	.module('scrumApp.employee', [
		'ngResource'
	])
	.config(['$routeProvider', function($routeProvider) {
		
		$routeProvider
			.when('/employee', {
				controller   : 'EmployeeListCtrl',
				templateUrl  : 'employee/employee-list-view.html?' + Date.now()  
			})
			.when('/employee/add', {
				controller   : 'EmployeeAddCtrl',
				templateUrl  : 'employee/employee-add-view.html?' + Date.now()  
			})
			.when('/employee/edit/:id',{
				controller: 'EmployeeEditCtrl',
				templateUrl: 'employee/employee-edit-view.html?'+ Date.now()
			})
	}])
	.controller('EmployeeAddCtrl', ['$scope','EmployeeFactory','$http','$location','toaster',
		function($scope,EmployeeFactory,$http,$location,toaster){
			$scope.addEmployee = function(isValid)
			{
				if(isValid)
				{
					EmployeeFactory.save($scope.employee,function(response){
						if(response.status == 'ok')
						{
							$location.path('/employee');
							toaster.pop('success',"Add Employee","Employee Record Added Successfully");
						}else{
							toaster.pop('error',"Error",response.message);
						}
					});
				}
			}
  	}])
	.controller('EmployeeListCtrl', ['$scope','EmployeeFactory','$routeParams','toaster',
		function($scope,EmployeeFactory,$routeParams,toaster){
	    	EmployeeFactory.query(function(response){
	    		if(response.status == 'ok')
	    			$scope.employees = response.data;
	    		else
	    			toaster.pop('error',"Error",response.message);
	    	});

	    $scope.deleteEmployee = function(employeeId,index){
	    	console.log(employeeId,index)

			EmployeeFactory.delete({id: employeeId},function(response){
				if(response.status == 'ok')
				{
					$scope.employees.splice(index);
					toaster.pop('success',"Delete Employee","Employee Record Deleted Successfully");
				}
			})
		}
  	}])
	.controller('EmployeeEditCtrl',['$scope','EmployeeFactory','$routeParams','$location','toaster',function($scope,EmployeeFactory,$routeParams,$location,toaster){
		var employeeId = $routeParams.id;
		console.log(employeeId)
		EmployeeFactory.get({id: employeeId},function(response){
			$scope.employee = response.data;
		})

		$scope.updateEmployee = function(){
			EmployeeFactory.update($scope.employee,function(response){
				console.log(response);
				$location.path('/employee');
				toaster.pop('success',"Update Employee","Employee Record Updated Successfully");
			})
		}
	}])
	.directive('employeeForm',[function(){
		return {
			restrict: 'E',
			templateUrl: 'employee/_form.html'
		}
	}])
	.directive('employeeRole',['EmployeeRole',function(EmployeeRole){
		return {
			restrict: 'E',
			templateUrl: 'employee/_employee_role.html',
			link: function($scope){
				EmployeeRole.get(function(response){
					console.log(response)
					$scope.roles = response.data;
				})
			}
		}
	}])
