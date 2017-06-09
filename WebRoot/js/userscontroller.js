var app = angular.module('user', []);
app.controller('userCtrl', function($scope, $http) {
	 
    $scope.firstname = "";
    $scope.lastname = "";
    $scope.email = "";
    $scope.phone = "";
    $scope.password = "";
   
    
    $scope.saveUser = function(){		
    	var xsrf = $.param({'firstname' :  $scope.firstname,lastname :$scope.lastname,email : $scope.email,phone : $scope.phonenumber,
    		password : $scope.password});
		
		$http({
	        url: '/elokchits/user/add',
	        method: "POST",
	        data: xsrf,
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	    })
	    .then(function(response) {
	    	alert("member saved successfully");
	    }, 
	    function(response) { // optional
	    	alert("member saved FAILED");
	    });
		
		
		
	};
	$scope.logIn = function(){
		
		    var xsrf = $.param({password : $scope.password,email : $scope.email});
			
			$http({
		        url: '/elokchits/user/login',
		        method: "GET",
		        data: xsrf,
		        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		    })
		    .then(function(response) {
		    	var msg=response.data;
		    	if(msg=='success')
		    	alert("loin  successfully");
		    	else{
		    		alert("UserId or Password is invalid");
		    		$("#email").focus();
		    	}
		    		
		    }, 
		    function(response) { // optional
		    	alert("log in failed");
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