angular
	.module('scrumApp.employee')
	.factory('EmployeeFactory', ['$resource',
		  function($resource){
		    return $resource('http://localhost:8181/MUMScrum/API/EmployeeControllerWS/employee/:id', {}, {
		      'query': { method:'GET',isArray:false },
		      'update': { method: 'PUT' }
		    });
  }])
	.factory('EmployeeRole',['$resource',function($resource){
		return $resource('http://localhost:8181/MUMScrum/API/RoleControllerWS/role',{},{
			'query': {method: 'GET', isArray: false }
		})
	}])
