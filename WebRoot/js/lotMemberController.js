
var app = angular.module('lotmember', []);
app.controller('lotmemberCtrl', ['$scope', '$http',function($scope, $http) {

	$scope.lotId = "";
    $scope.memId = "";
    $scope.memName = "";
    $scope.refId = "";
   
    
    
    $scope.saveLotMember = function(){		
    	var xsrf = $.param({'memId' : $scope.memId,memName :$scope.memName,refId : $scope.refId, lotid:$scope.lotId});
		
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
	$scope.mem=[];
	
	$scope.searchName = function(){	
		
		$http({
	        url: '/elokchits/member/searchname',
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.mem = response.data;}, 
	    function(response) { // optional
	    	alert("member saved FAILED");
	    });
		
	};   
	
}]);