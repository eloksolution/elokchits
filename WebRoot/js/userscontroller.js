var app = angular.module('user', []);
app.controller('userCtrl', function($scope, $http) {
    $scope.firstname = "";
    $scope.lastname = "";
    $scope.phone = "";
    $scope.password = "";
    $scope.email = "";
    
    $scope.saveUser = function(){		
    	var xsrf = $.param({'firstname' :  $scope.firstname,lastname :$scope.lastname,phone : $scope.phonenumber,
    		address : $scope.address,email : $scope.email});
		
		$http({
	        url: '/elokchits/user/add',
	        method: "POST",
	        data: xsrf,
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	    })
	    .then(function(response) {
	    	alert("member saved successfully", {timeOut: 5000});
	    }, 
	    function(response) { // optional
	    	alert("member saved FAILED", {timeOut: 5000});
	    });
		
		
		
	};
	
});