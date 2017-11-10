/**
 * 
 */
app.controller('manageSubscriptionsCtrl', function($scope, $http) {
	
	
	$scope.getPersons = function() {
		$http.get("/ProductNotificationApp/rest/notification/subscribe").then(function(response) {
			$scope.allPersons = response.data;
			$scope.hidePersons = false;
		});
	}
	
	$scope.viewSubscriptions = function(person) {
		$scope.person = person;
		$scope.hideSubscriptions = false;
		$scope.hidePersons = true;
	}
	
	$scope.viewPersons = function() {
		$scope.hidePersons = false;
		$scope.hideSubscriptions = true;
	}
	
	$scope.hideSubscriptions = true;
	$scope.hidePersons = true;
});