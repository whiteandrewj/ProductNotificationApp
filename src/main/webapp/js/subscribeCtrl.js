/**
 * 
 */
app.controller('subscribeCtrl', function($scope, $http) {
	
	$scope.createNewSubscription = function() {
		
	
		var newSubscription = {	firstName : $scope.firstName,
								lastName : $scope.lastName,
								emailAddress : $scope.emailAddress,
								isComputerSub : $scope.isComputerSub,
								isConsoleSub : $scope.isConsoleSub,
								isHeaterSub : $scope.isHeaterSub,
								isLawnSub : $scope.isLawnSub,
								isToolSub : $scope.isToolSub,
								isTelevisionSub : $scope.isTelevisionSub
		};
		
		alert(	"First " + newSubscription.firstName + "\n" +
				"Last " + newSubscription.lastName + "\n" +
				"Email " + newSubscription.emailAddress + "\n" +
				"Comp? " + newSubscription.isComputerSub + "\n" +
				"Console? " + newSubscription.isConsoleSub + "\n" +
				"Heater? " + newSubscription.isHeaterSub + "\n" +
				"Lawn? " + newSubscription.isLawnSub + "\n" +
				"Tool? " + newSubscription.isToolSub + "\n" +
				"TV?" + newSubscription.isTelevisionSub + "\n"
		);
		
		//convert value of 'undefined' properties to false
		for (pos in newSubscription) {
			if (newSubscription[pos] == undefined) {
				newSubscription[pos] = false;
			}
		};
		
		alert(	"First " + newSubscription.firstName + "\n" +
				"Last " + newSubscription.lastName + "\n" +
				"Email " + newSubscription.emailAddress + "\n" +
				"Comp? " + newSubscription.isComputerSub + "\n" +
				"Console? " + newSubscription.isConsoleSub + "\n" +
				"Heater? " + newSubscription.isHeaterSub + "\n" +
				"Lawn? " + newSubscription.isLawnSub + "\n" +
				"Tool? " + newSubscription.isToolSub + "\n" +
				"TV?" + newSubscription.isTelevisionSub + "\n"
		);
		
		$http.post("/ProductNotificationApp/rest/notification/subscribe", newSubscription)
		.then(
				function success(response) {						
					alert("success! Response data: " + response.data + ", status: " + response.status);							 
				},
				function error(response) {
					alert("error, return status: " + response.status);	
				}
		);
		
		
	};
	
} );