'use strict';

angular
	.module('scrumApp.sprint', [
		'ngResource'
	])
	.config(['$routeProvider', function($routeProvider) {
		
		$routeProvider
			.when('/sprint', {
				controller   : 'SprintListCtrl',
				templateUrl  : 'sprint/views/sprint-list-view.html?' + Date.now()  
			})
			.when('/sprint/add', {
				controller   : 'SprintAddCtrl',
				templateUrl  : 'sprint/views/sprint-add-view.html?' + Date.now()  
			})
			.when('/sprint/edit/:id',{
				controller: 'SprintEditCtrl',
				templateUrl: 'sprint/views/sprint-edit-view.html?'+ Date.now()
			})
	}])
	.controller('SprintAddCtrl', ['$scope','SprintFactory','$http','$location','toaster',
		function($scope,SprintFactory,$http,$location,toaster){
			$scope.addSprint = function(isValid)
			{
				if(isValid)
				{
					SprintFactory.save($scope.sprint,function(response){
						console.log(response)
						if(response.status == 'ok')
						{
							$location.path('/sprint');
							toaster.pop('success',"Add Sprint","Sprint Record Added Successfully");
						}else{
							toaster.pop('error',"Error",response.message);
						}
					});
				}
			}
  	}])
	.controller('SprintListCtrl', ['$scope','SprintFactory','$routeParams','toaster',
		function($scope,SprintFactory,$routeParams,toaster){
	    	SprintFactory.query(function(response){
	    		if(response.status == 'ok')
	    			$scope.sprintList = response.data;
	    		else
	    			toaster.pop('error',"Error",response.message);
	    	});

	    $scope.deleteSprint = function(sprintId,index){
	    	
			SprintFactory.delete({id: sprintId},function(response){
				if(response.status == 'ok')
				{
					$scope.sprintList.splice(index,1);
					toaster.pop('success',"Delete Sprint","Sprint Record Deleted Successfully");
				}
			})
		}
  	}])
	.controller('SprintEditCtrl',['$scope','SprintFactory','$routeParams','$location','toaster',function($scope,SprintFactory,$routeParams,$location,toaster){
		var sprintId = $routeParams.id;
		SprintFactory.get({id: sprintId},function(response){
			$scope.sprint = response.data;
		});

		$scope.updateSprint = function(){
			SprintFactory.update($scope.sprint,function(response){
				$location.path('/sprint');
				toaster.pop('success',"Update Sprint","Sprint Record Updated Successfully");
			})
		}
	}])
	.directive('sprintForm',[function(){
		return {
			restrict: 'E',
			templateUrl: 'sprint/views/_form.html'
		}
	}]);
