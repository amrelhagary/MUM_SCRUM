'use strict';

angular
	.module('scrumApp.sprint')
	.factory('SprintFactory', ['$resource',
		  function($resource){
		    return $resource('http://localhost:8085/MUMScrum/API/SprintControllerWS/sprint/:id', {}, {
		      'query': { method:'GET',isArray:false },
		      'update': { method: 'PUT' }
		    });
  }]);
