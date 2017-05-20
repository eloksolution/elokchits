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
    $scope.lotId = "";
    $scope.memId = "";
    $scope.saveLot = function(){		
    	var xsrf = $.param({'lotcode' : $scope.lotcode,commission :$scope.commission,startdate : $scope.startdate,sumamount : $scope.sumamount,
    		members : $scope.members,description : $scope.description,lottype :  $scope.lottype,beforeamount : $scope.beforeamount,afteramount : $scope.afteramount,lotId : $scope.lotId});
		
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
	$scope.getLot = function(){	
		$http({
	        url: '/elokchits/lot/searchall',
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.lots = response.data;}, 
	    function(response) { // optional
	    	alert("member saved FAILED");
	    });
	};
	$scope.editLot = function(lotId){	
		$http({
	        url: '/elokchits/lot/lotEdit/'+lotId,
	        method: "GET",
	        
	    })
	    .then(function (response) {
	    	
	    	var lot = response.data;
	    	alert("response is"+lotId);
	    	    $scope.lotcode=lot.code;
	    	    $scope.commission = lot.commisionper;
	    	    $scope.sumamount = lot.sumAmount;
	    	    $scope.members = lot.noOfMonths;
	    	    $scope.description = lot.description;
	    	    $scope.startdate = lot.startDate;
	    	    $scope.lottype = lot.type;
	    	    $scope.beforeamount = lot.beforeLiftAmount;
	    	    $scope.afteramount = lot.afterLiftAmount;
	    	    $scope.lotId = lot.lotId;
	    	    $scope.status = lot.statusName;
	    }, 
	    function(response) { // optional
	    	alert("lot edited FAILED");
	    });
	};
	$scope.deleteLot = function(lotId){	
		
    		
		$http({
	        url: '/elokchits/lot/deletlot/'+lotId,
	        method: "POST",
	        
	    })
	    .then(function (response) {
	    	alert("response is"+response);
	    	$scope.lots = response.data;}, 
	    function(response) { // optional
	    	alert("Lot delete FAILED");
	    });
	};
	
	  $scope.startdate = ""; 
	  $scope.dateUpdate = function(lotId) {
	  
	    var xsrf = $.param({'startdate' : $scope.startdate});
		
		$http({
	        url: '/elokchits/lot/dateupdate/'+lotId,
	        method: "GET",
	        data: xsrf,
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	    })
	    .then(function(response) {
	    	alert("Date Update succesfully");
	    }, 
	    function(response) { // optional
	    	alert("Date Update FAILED");
	    });
			
	    
	  };
	  $scope.status="";
	  $scope.statusUpdate = function(lotId) {
		  var xsrf = $.param({'status':$scope.status});
		 $http({
			  url:'/elokchits/lot/statusUpdated/'+lotId,
			  method:"GET",
			  data:xsrf,
			  headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		  })
		   .then(function(response) {
		    	alert("Staus Update succesfully");
		    }, 
		    function(response) { // optional
		    	alert("Status Update FAILED");
		    });
	  };
	
    $("#memid").autocomplete({
   	 source: "/elokchits/member/searchname",
        minLength: 2,
        
  
   });
    
	  $scope.lotId = "";
	    $scope.memId = "";
	    $scope.memName = "";
	    $scope.refId = "";
	  $scope.saveLotMember = function() {
	  
	    var xsrf = $.param({'lotId' : $scope.lotId,memId :$scope.memId,memName : $scope.memName,refId : $scope.refId
    		});
		
		$http({
	        url: '/elokchits/lotmember/add',
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
		$scope.lotView = function(lotId){	
			var lotId=(location.search.split(name + '=')[1] || '').split('&')[0];
			alert("nre  lotId "+lotId);
			$http({
		        url: '/elokchits/lot/lotEdit/'+lotId,
		        method: "GET",
		        
		    })
		    .then(function (response) {
		    	
		    	var lot = response.data;
		    	alert("response is"+lotId);
		    	    $scope.lotcode=lot.code;
		    	    $scope.commission = lot.commisionper;
		    	    $scope.sumamount = lot.sumAmount;
		    	    $scope.members = lot.noOfMonths;
		    	    $scope.description = lot.description;
		    	    $scope.startdate = lot.startDate;
		    	    $scope.lottype = lot.type;
		    	    $scope.beforeamount = lot.beforeLiftAmount;
		    	    $scope.afteramount = lot.afterLiftAmount;
		    	    $scope.lotId = lot.lotId;
		    	    $scope.status = lot.statusName;
		    }, 
		    function(response) { // optional
		    	alert("lot edited FAILED");
		    });
		};
});