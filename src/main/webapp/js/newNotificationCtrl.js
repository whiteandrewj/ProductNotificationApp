/**
 * 
 */
app.controller('newNotificationCtrl', function($scope, $http) {

	$scope.createNewNotification = function() {
		
		var newNotification = { name : $scope.name,
								price : $scope.price,
								description : $scope.description,
								type : $scope.type
		};
		
		alert(	"Product Name: " + newNotification.name + "\n" +
				"Price: " + newNotification.price + "\n" +
				"Description: " + newNotification.description + "\n" +
				"Type: " + newNotification.type + "\n"
		);
		
		
	};
	
} );