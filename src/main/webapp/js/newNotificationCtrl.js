/**
 * 
 */
app.controller('newNotificationCtrl', function($scope, $http) {

	$scope.createNewNotification = function() {
		
		var newNotification = { productName : $scope.productName,
								price : $scope.price,
								description : $scope.description,
								topic : $scope.topic
		};
		
		alert(	"Product Name: " + newNotification.productName + "\n" +
				"Price: " + newNotification.price + "\n" +
				"Description: " + newNotification.description + "\n" +
				"Topic: " + newNotification.topic + "\n"
		);
		
		
		$http.post("/ProductNotificationApp/rest/notification/new", newNotification)
		.then(
				//the .then method takes two arguments, in this case two functions
				//the first argument is the code to be executed on 'success', the second on 'failure'
				function success(response) {
					
					if (response.data == 1) {						
						alert("rows inserted: " + response.data + ", status: " + response.status);						
					} else {
						alert("error, return status: " + response.status);	
					}
				},
				function error(response) {
					alert("error, return status: " + response.status);			
				}				
		);
		
	};
	
} );