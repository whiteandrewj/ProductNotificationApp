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
		
		$http.post("/ProductNotificationApp/rest/subscribe", newSubscription)
		.then(
				function success(response) {
					
					if (response.data == 1) {						
						alert("rows inserted: " + response.data + ", status: " + response.status);						
					} else {
						alert("error, return status: " + response.status);		
					}
				}					
		);
		
		
	};
	
} );