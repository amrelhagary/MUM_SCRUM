'use strict';

angular
	.module('scrumApp.userstory', [
		'ngResource'
	])
	.config(['$routeProvider', function($routeProvider) {
		
		$routeProvider
			.when('/userstory', {
				controller   : 'UserstoryListCtrl',
				templateUrl  : 'userstory/views/userstory-list-view.html?' + Date.now(),
				title : 'List User Stories'  
			})
			.when('/product/:id/userstory/add', {
				controller   : 'UserstoryAddCtrl',
				templateUrl  : 'userstory/views/userstory-add-view.html?' + Date.now(),
				title: 'Add User Story'  
			})
			.when('/userstory/edit/:id',{
				controller: 'UserstoryEditCtrl',
				templateUrl: 'userstory/views/userstory-edit-view.html?'+ Date.now(),
				title : 'Edit User Story'
			})
			.when('/product/:id/userstory',{
				controller: 'UserstoryListCtrl',
				templateUrl: 'userstory/views/userstory-list-view.html?' + Date.now(),
				title : 'List User Stories'
			})
	}])
	.controller('UserstoryAddCtrl', ['$scope','UserstoryFactory','ProductFactory','$http','$location','$routeParams','toaster',
		function($scope,UserstoryFactory,ProductFactory,$http,$location,$routeParams,toaster){
			
			$scope.productId = $routeParams.id;
			$scope.addUserstory = function(isValid)
			{

				if(isValid)
				{
					//get product object from server side
					var product = ProductFactory.get({id: $scope.productId});

					// use promise concept for saving user story
					product.$promise.then(function(result){
						$scope.userstory.product = result.data;

						UserstoryFactory.save($scope.userstory,function(response){
							console.log($scope.userstory.product)
							console.log(response)
							if(response.status == 'ok')
							{
								$location.path('/product/'+$scope.productId+'/userstory');
								toaster.pop('success',"Add Userstory","Userstory Record Added Successfully");
							}else{
								toaster.pop('error',"Error",response.message);
							}
						});
					});
				}
			}
  	}])
	.controller('UserstoryListCtrl', ['$scope','UserstoryByProductId','UserstoryFactory','$routeParams','toaster',
		function($scope,UserstoryByProductId,UserstoryFactory,$routeParams,toaster){
			var productId = $routeParams.id;
			$scope.productId = productId;
	    	UserstoryByProductId.query({productId: productId },function(response){
	    		if(response.status == 'ok')
	    			$scope.userstories = response.data;
	    		else
	    			toaster.pop('error',"Error",response.message);
	    	});

	    $scope.deleteUserstory = function(userstoryId,index){
			UserstoryFactory.delete({id: userstoryId},function(response){
				if(response.status == 'ok')
				{
					$scope.userstories.splice(index,1);
					toaster.pop('success',"Delete Userstory","Userstory Record Deleted Successfully");
				}else{
					toaster.pop('error',"Error",response.message);
				}
			})
		}
  	}])
	.controller('UserstoryEditCtrl',['$scope','UserstoryFactory','$routeParams','$location','toaster',function($scope,UserstoryFactory,$routeParams,$location,toaster){
		var userstoryId = $routeParams.id;
		UserstoryFactory.get({id: userstoryId},function(response){
			$scope.userstory = response.data;
		})

		$scope.updateUserstory = function(isValid){

			if(isValid)
			{
				UserstoryFactory.update($scope.userstory,function(response){
					if(response.status == 'ok')
					{
						$location.path('/product/'+response.data.product.id+'/userstory');
						toaster.pop('success',"Update Userstory","Userstory Record Updated Successfully");
					}else{
						toaster.pop('error',"Error",response.message);
					}
				})
			}

		}
	}])
	.directive('userstoryForm',[function(){
		return {
			restrict: 'E',
			templateUrl: 'userstory/views/_form.html'
		}
	}])
	.directive('employeeList',['EmployeeFactory',function(EmployeeFactory){
		return {
			restrict: 'E',
			templateUrl: 'userstory/views/_employee_list.html',	
			link: function(scope,element,attrs){
				EmployeeFactory.get(function(response){
					scope.employeeList = response.data;
				})
			}
		}
	}])
	.directive('sprintList',['SprintFactory',function(SprintFactory){
		return {
			restrict: 'E',
			templateUrl: 'userstory/views/_sprint_list.html',	
			link: function(scope,element,attrs){
				SprintFactory.get(function(response){
					scope.sprintList = response.data;
				})
			}
		}
	}])
	.directive('releaseList',['ReleaseFactory',function(ReleaseFactory){
		return {
			restrict: 'E',
			templateUrl: 'userstory/views/_release_list.html',	
			link: function(scope,element,attrs){
				ReleaseFactory.get(function(response){
					scope.releaseList = response.data;
				})
			}
		}
	}])
