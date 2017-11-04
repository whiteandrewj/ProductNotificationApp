/**
 * 
 */
app.controller('subscribeCtrl', function($scope, $http) {
	
	$scope.createNewSubscription = function() {
	
		/*var subscriptions = {Computer : $scope.Computer,
					Console : $scope.Console,
					Heater : $scope.Heater,
					Lawnmower : $scope.Lawnmower,
					Tool : $scope.Tool,
					Television : $scope.Television};
	*/
		var newSubscription = {	"firstName" : $scope.firstName,
								"lastName" : $scope.lastName,
								"emailAddress" : $scope.emailAddress,
								"subscriptions" : [
									{topic : "COMPUTER", isSubscribed : $scope.Computer, status : null},
									{topic : "CONSOLE", isSubscribed : $scope.Console, status : null},
									{topic : "HEATER", isSubscribed : $scope.Heater, status : null},
									{topic : "LAWNMOWER", isSubscribed : $scope.Lawnmower, status : null},
									{topic : "TOOL", isSubscribed : $scope.Tool, status : null},
									{topic : "TELEVISION", isSubscribed : $scope.Television, status : null}
								 	]
								};	
		
		for (i in newSubscription.subscriptions) {
				if (newSubscription.subscriptions[i].isSubscribed == undefined) {
					newSubscription.subscriptions[i].isSubscribed = false;
				}
		};
		
		var alertMessage = "";
		
		for (i in newSubscription.subscriptions) {
			alertMessage += (newSubscription.subscriptions[i].topic 
							+ ": " + newSubscription.subscriptions[i].isSubscribed 
							+ ", status " + newSubscription.subscriptions[i].status + "\n");
		}
			
		//having trouble with alerts, getting a "cannot read property 'Computer' of undefined"
		alert(	"First " + newSubscription.firstName + "\n" +
				"Last " + newSubscription.lastName + "\n" +
				"Email " + newSubscription.emailAddress + "\n" +
				alertMessage
				
				/*
				"Comp? " + newSubscription.subscriptions.Computer + "\n" +
				"Console? " + newSubscription.subscriptions.Console + "\n" +
				"Heater? " + newSubscription.subscriptions.Heater + "\n" +
				"Lawn? " + newSubscription.subscriptions.Lawnmower + "\n" +
				"Tool? " + newSubscription.subscriptions.Tool + "\n" +
				"TV?" + newSubscription.subscriptions.Television + "\n"
				*/
		);
		
		//convert value of 'undefined' properties to false
		/*
		for (pos in newSubscription) {
			for (pos2 in subscriptions) {
				if (newSubscription[pos] == undefined) {
					newSubscription[pos] = false;
				}
			}
		};
		*/
		/*
		alert(	"First " + newSubscription.firstName + "\n" +
				"Last " + newSubscription.lastName + "\n" +
				"Email " + newSubscription.emailAddress + "\n" +
				"Comp? " + newSubscription.Computer + "\n" +
				"Console? " + newSubscription.Console + "\n" +
				"Heater? " + newSubscription.Heater + "\n" +
				"Lawn? " + newSubscription.Lawnmower + "\n" +
				"Tool? " + newSubscription.Tool + "\n" +
				"TV?" + newSubscription.Television + "\n"
		);
		*/
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