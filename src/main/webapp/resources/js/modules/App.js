var App = angular.module('EventCalendar', ['restangular','mgcrea.ngStrap']);


App.controller('EventsController', ['$scope','$http','Restangular', function($scope, $http, Restangular) {
	
	$scope.events = '';
	
	$scope.event = '';

	$scope.createEvent = function(event) {
		
		$scope.event.status = false;
				
		Restangular.all('events').post($scope.event).then(function(response){
					
		}, function(error) {
			  $scope.error = error.data; 		  
		});
	}
		
	$scope.findAllEvents = function() {

		Restangular.one('events').getList().then(function(response){			
			$scope.events = response;	
		}, function(error) {
			  $scope.error = error.data; 
			  
		});		
	}

	$scope.findEventById = function(id) {
		
		Restangular.one('events').get().then(function(response){		
			$scope.event = response;		
		}, function(error) {
			  $scope.error = error.data; 
			  
		});
	}
		
	$scope.updateEvent = function(event) {
	
	}
	
	$scope.deleteEventById = function(id) {
		
	}

	$scope.findAllEvents();	
}]);

