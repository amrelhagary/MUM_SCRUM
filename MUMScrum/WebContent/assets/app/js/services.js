'use strict';

/* Services */

var scrumAppServices = angular.module('ScrumAppServices', ['ngResource']);

scrumAppServices.factory('Employees', ['$resource',
  function($resource){
    return $resource('data/:userId.json', {}, {
      query: {method:'GET', params:{userId:'employee'}, isArray:true}
    });
  }]);
