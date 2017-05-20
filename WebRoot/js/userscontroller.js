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
	$scope.logIn = function(){
		 $scope.password = "";
		    $scope.email = "";
		    var xsrf = $.param({address : $scope.address,email : $scope.email});
			
			$http({
		        url: '/elokchits/user/login',
		        method: "GET",
		        data: xsrf,
		        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		    })
		    .then(function(response) {
		    	alert("loin  successfully", {timeOut: 5000});
		    }, 
		    function(response) { // optional
		    	alert("log in failed", {timeOut: 5000});
		    });
	};
	
	$scope.getMember = function(){	
		$http({
	        url: '/elokchits/user/partners',
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	
	    	$scope.members = response.data;}, 
	    function(response) { // optional
	    	
	    });
		
	};
	
});