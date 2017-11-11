/**
 * 
 */
app.controller('manageSubscriptionsCtrl', function($scope, $http) {
	
	
	$scope.getPersons = function() {
		
		$http.get("/ProductNotificationApp/rest/notification/subscriptions")
		.then(
			function success(response) {
				$scope.allPersons = response.data;
			},
			function error(response) {
				alert("error: " + response.data + ", code: " + response.status)
			}
		);
	}
	
	
	$scope.updatePerson = function(person) {
		$http.put("/ProductNotificationApp/rest/notification/subscription", person).then function(response) {
			alert(res)
		});
	}
	
	$scope.viewSubscriptions = function(person) {
		$scope.person = person;
		$scope.hideGetPersonsBtn = true;
		$scope.hidePersonsTable = true;
		$scope.hideSubscriptionsTable = false;		
	}
	
	$scope.viewPersons = function() {
		$scope.hideGetPersonsBtn = false;
		$scope.hidePersonsTable = false;
		$scope.hideSubscriptionsTable = true;
	}
	
	$scope.hideGetPersonsBtn = false;
	$scope.hideSubscriptionsTable = true;
	$scope.hidePersonsTable = true;
});