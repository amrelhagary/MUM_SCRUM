'use strict';

angular
	.module('scrumApp.userstory')
	.factory('UserstoryFactory', ['$resource',
		  function($resource){
		    return $resource('http://localhost:8085/MUMScrum/API/UserStoryControllerWS/userstory/:id', {}, {
		      'query': { method:'GET',isArray:false },
		      'update': { method: 'PUT' }
		    });
  }])
	.factory('UserstoryByProductId', ['$resource',
		  function($resource){
		    return $resource('http://localhost:8085/MUMScrum/API/UserStoryControllerWS/product/:productId/userstory', {}, {
		      'query': { method:'GET',isArray:false }
		    });
  }])
	.factory('GetAllUserStoryByUserId', ['$resource',
		  function($resource){
		    return $resource('http://localhost:8085/MUMScrum/API/UserStoryControllerWS/assignee/:UserId/userstory', {}, {
		      'query': { method:'GET',isArray:false }
		    });
  }])
	.factory('UserStoryGetById', ['$resource',
		  function($resource){
		    return $resource('http://localhost:8085/MUMScrum/API/UserStoryControllerWS/userstory/:id', {}, {
		      'query': { method:'GET',isArray:false },
		      'update': { method: 'PUT' }
		    });
  }]);


	