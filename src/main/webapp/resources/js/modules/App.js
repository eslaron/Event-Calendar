var App = angular.module('EventCalendar', ['restangular','mgcrea.ngStrap','ngTable'])


.config(['RestangularProvider', function(RestangularProvider){
	
	RestangularProvider.setRequestInterceptor(function(elem, operation) {
		  if (operation === "remove") {
		     return undefined;
		  } 
		  return elem;
	});
}])

App.controller('EventsController', ['$scope','$http','Restangular', '$filter', 'ngTableParams', function($scope, $http, Restangular, $filter, ngTableParams) {
	
	var data = [];
	
	$scope.event = '';
	
	$scope.currentUser = '';
	
	$scope.hideTable = false;
	$scope.hideAdd = true;
	$scope.hideEdit = true;
	
	$scope.showTable = function() {
		
		$scope.hideTable = false;
		$scope.hideAdd = true;
		$scope.hideEdit = true;		
	}
	
	$scope.showAdd = function() {
		
		$scope.hideTable = true;
		$scope.hideAdd = false;
		$scope.hideEdit = true;		
	}

	$scope.showEdit = function() {
	
		$scope.hideTable = true;
		$scope.hideAdd = true;
		$scope.hideEdit = false;		
	}
	
	$scope.getCurrentUser = function() {
		
		Restangular.one('currentUser').get().then(function(response){		
			$scope.currentUser = response;		
		}, function(error) {
			  $scope.error = error.data; 
			  
		});
	} 
	
	$scope.createEvent = function(event) {
		
		$scope.event.status = 'Niezakończone';
				
		Restangular.all('events').post($scope.event).then(function(response){
			$scope.tableParams.reload();
		}, function(error) {
			  $scope.error = error.data; 		  
		});
	}
		
	$scope.findAllEvents = function() {

		Restangular.one('events').getList().then(function(response){	
			
			for(var i=0;i<response.length;i++) {
				if(response[i].userNickname == $scope.currentUser.nickname)
					data.push(response[i]);
			}

			$scope.tableParams.reload();
		}, function(error) {
			  $scope.error = error.data; 
			  
		});		
	}

	$scope.findEventById = function(id) {
		
		Restangular.one('events', id).get().then(function(response){		
			$scope.event = response;		
		}, function(error) {
			  $scope.error = error.data; 
			  
		});
	}
		
	$scope.updateEvent = function(event) {
		
		var UpdateEvent = Restangular.one('events');

		UpdateEvent.id =  $scope.event.id;
		UpdateEvent.name = $scope.event.name;
		UpdateEvent.type = $scope.event.type;
		UpdateEvent.startDate = $scope.event.startDate;
		UpdateEvent.endDate = $scope.event.endDate;
		UpdateEvent.location = $scope.event.location;
		UpdateEvent.status = $scope.event.status;

		UpdateEvent.put().then(function(response) {
			$scope.tableParams.reload();	
		},function(error) {
			$scope.error = error.data; 					
	 	});	
	}
	
	$scope.deleteEventById = function(id) {

		var DeleteEvent = Restangular.one('events');
		
		DeleteEvent.id = id;
		
		DeleteEvent.remove().then(function(response) {
			$scope.tableParams.reload();	
		},function(error) {
			$scope.error = error.data; 					
	 	});
	}
	
	$scope.getCurrentUser();	
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

