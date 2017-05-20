var app = angular.module('auction', []);
app.controller('auctionCtrl', function($scope, $http) {
	
    $scope.saveLot = function(){		
    	var xsrf = $.param({'lotcode' : $scope.lotcode,commission :$scope.commission,startdate : $scope.startdate,sumamount : $scope.sumamount,
    		members : $scope.members,description : $scope.description,lottype :  $scope.lottype,beforeamount : $scope.beforeamount,afteramount : $scope.afteramount,lotId : $scope.lotId});
		
		$http({
	        url: '/elokchits/auction/save',
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
	$scope.getAuction = function(){	
		$http({
	        url: '/elokchits/auction/home',
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.auctions = response.data;}, 
	    function(response) { // optional
	    	alert("Auctions saved FAILED");
	    });
	};
	$scope.billGen = function(){	
		$http({
	        url: '/elokchits/auction/bill',
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.auctions = response.data;}, 
	    function(response) { // optional
	    	alert("Auctions saved FAILED");
	    });
	};
	
	
});