var App = angular.module('EventCalendar', []);

App.controller('EventsController', function($scope, $http) {
	
	$scope.greeting = "Witaj w kalendarzu wydarzeń!";
	
	$scope.events = '';
	
	$scope.event = '';
		
	$scope.getAllEvents = function() {
		$http.get('events').success(function(result){		
			$scope.events = result;	
		});
	}

	$scope.getEventById = function(id) {
		$http.get('events/' + id).success(function(result){		
			$scope.event = result;	
		});
	}
	
	$scope.getAllEvents();
	$scope.getEventById(5629499534213120);
	
});

