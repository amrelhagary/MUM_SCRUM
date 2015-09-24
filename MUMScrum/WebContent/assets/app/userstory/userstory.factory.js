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
	.factory('UserstoryBySprintId', ['$resource',
		  function($resource){
		    return $resource('http://localhost:8085/MUMScrum/API/UserStoryControllerWS/sprint/:sprintId/userstory', {}, {
		      'query': { method:'GET',isArray:false }
		    });
  }])
	.factory('NonSprintUserstory', ['$resource',
		  function($resource){
		    return $resource('http://localhost:8085/MUMScrum/API/UserStoryControllerWS/nonsprint/userstory', {}, {
		      'query': { method:'GET',isArray:false }
		    });
  }])