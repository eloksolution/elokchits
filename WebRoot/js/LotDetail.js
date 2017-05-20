var app = angular.module('lotDetails', []);
app.controller('lotDetailsCtrl',function($scope, $http){
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
});