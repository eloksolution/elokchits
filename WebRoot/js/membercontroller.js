
var app = angular.module('member', []);
app.controller('memberCtrl', function($scope, $http) {
    $scope.firstname = "";
    $scope.lastname = "";
    $scope.phone = "";
    $scope.address = "";
    $scope.email = "";
    $scope.memId = "";
    $scope.dailogtitle="Create Member"; 
    $scope.refId = "";
    $scope.saveMember = function(){		
    	var xsrf = $.param({'firstname' :  $scope.firstname,lastname :$scope.lastname,phone : $scope.phone,reference :  $scope.reference,
    		address : $scope.address,email : $scope.email,memId : $scope.memId,refId:$scope.refId});
		
		$http({
	        url: '/elokchits/member/add',
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
	
	$scope.getMember = function(){	
		$http({
	        url: '/elokchits/member/searchall',
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.members = response.data;}, 
	    function(response) { // optional
	    	alert("member saved FAILED");
	    });
	};
	$scope.editMember = function(memId){	
		$http({
	        url: '/elokchits/member/memberEdit/'+memId,
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	
	    	var mem = response.data;
	    	alert("response is"+mem.firstName);
	    	 $scope.firstname=mem.firstName;
	    	 $scope.dailogtitle="Edit Member";
	    	 $scope.lastname=mem.lastName;
	    	 $scope.phone=mem.phone;
	    	 $scope.email=mem.email;
	    	 $scope.address=mem.address;
	    	 $scope.memId=mem.memId;
	    }, 
	    function(response) { // optional
	    	alert("member edited FAILED");
	    });
	};
	    
	$scope.deleteMember = function(memId){	
		$http({
	        url: '/elokchits/member/deletemember/'+memId,
	        method: "POST",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.members = response.data;}, 
	    function(response) { // optional
	    	alert("member delete FAILED");
	    });
	};
	$scope.memberview = function(memId){	
		
		$http({
	        url: '/elokchits/member/memberEdit/'+memId,
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	
	    	var mem = response.data;
	    	alert("response is"+mem.firstName);
	    	 $scope.firstname=mem.firstName;
	    	 $scope.dailogtitle="Edit Member";
	    	 $scope.lastname=mem.lastName;
	    	 $scope.phone=mem.phone;
	    	 $scope.email=mem.email;
	    	 $scope.address=mem.address;
	    	 $scope.memId=mem.memId;
	    }, 
	    function(response) { // optional
	    	alert("member edited FAILED");
	    });
	};
	$scope.getUser = function(){	
		$http({
	        url: '/elokchits/user/partners',
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.users = response.data;}, 
	    function(response) { // optional
	    	alert("member saved FAILED");
	    });
		
	};   
	$scope.searchName = function(){	
		$http({
	        url: '/elokchits/member/searchname',
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.users = response.data;}, 
	    function(response) { // optional
	    	alert("member saved FAILED");
	    });
		
	};   
});

