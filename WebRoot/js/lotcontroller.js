
var app = angular.module('lot', []);
app.controller('lotCtrl', function($scope, $http) {
    $scope.lotcode = "";
    $scope.commission = "";
    $scope.sumamount = "";
    $scope.members = "";
    $scope.description = "";
    $scope.startdate = "";
    $scope.lottype = "";
    $scope.beforeamount = "";
    $scope.afteramount = "";
    
    $scope.saveLot = function(){		
    	var xsrf = $.param({'lotcode' : $scope.lotcode,commission :$scope.commission,startdate : $scope.startdate,sumamount : $scope.sumamount,
    		members : $scope.members,description : $scope.description,lottype :  $scope.lottype,beforeamount : $scope.beforeamount,afteramount : $scope.afteramount});
		
		$http({
	        url: '/elokchits/lot/add',
	        method: "POST",
	        data: xsrf,
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	    })
	    .then(function(response) {
	    	alert("lot saved succesfully");
	    }, 
	    function(response) { // optional
	    	alert("lot saved FAILED");
	    });
			
	};
   
	$scope.geLot = function(){	
		$http({
	        url: '/elokchits/lot/searchall',
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.lots = response.data;}, 
	    function(response) { // optional
	    	alert("lot saved FAILED");
	    });
	};
});


