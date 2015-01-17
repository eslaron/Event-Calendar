var App = angular.module('EventCalendar', ['restangular','mgcrea.ngStrap','ngTable']);

App.controller('IndexController', ['$scope','$http','Restangular', function($scope, $http, Restangular) {
		
}]);

App.controller('EventsController', ['$scope','$http','Restangular', '$filter', 'ngTableParams', function($scope, $http, Restangular, $filter, ngTableParams) {
	
	var data = '';
	
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
			data = response;	
			$scope.tableParams.reload();
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
	
	$scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10,          // count per page
        sorting: {
            name: 'asc'     // initial sorting
        }
    }, {
        total: data.length, // length of data
        getData: function($defer, params) {
        	        	
        	var orderedData = params.sorting() ? $filter('orderBy')(data, params.orderBy()) : data;
        	var filteredData = params.filter() ? $filter('filter')(orderedData, params.filter()) : orderedData; 
        	
        	params.total(filteredData.length); // set total for recalc pagination
        	
            $defer.resolve(filteredData.slice((params.page() - 1) * params.count(), params.page() * params.count()));          
        }
   });	
}]);

