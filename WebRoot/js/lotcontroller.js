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
    $scope.lottitle="Create New Lot";
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
	    	
	    	$scope.lots = response.data;
	    	$scope.lotscount = response.data.length;},
	    	
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
	    	    $scope.lottitle="Edit Lot";
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
	
	 
	  $scope.dateUpdate = function(lotId) {
		
	    var xsrf = $.param({startdate : $scope.startdate});
	   
		$http({
	        url: '/elokchits/lot/dateupdate/'+lotId,
	        method: "POST",
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
	
	  $scope.statusUpdate = function(lotId) {
		  var xsrf = $.param({'status':$scope.status});
		 $http({
			  url:'/elokchits/lot/statusUpdated/'+lotId,
			  method:"POST",
			  data:xsrf,
			  headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		  })
		   .then(function(response) {
		    	alert("Staus Update succesfully");
		    }, 
		    function(response) { // optional
		    	
		    });
	  };
	
    $("#memid").autocomplete({
   	 source: "/elokchits/member/searchname",
        minLength: 2,
        
        select: function( event, ui ) {
        	 $scope.memId=ui.item.memId;
        	 $scope.memName=ui.item.value;
             $scope.refId=ui.item.refId;
           	  
            }
   });
    
	  $scope.saveLotMember = function() {
	  alert("lot id is "+$scope.lotId);
	 
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
		$scope.lotView = function(){	
			
			var lotId=(location.search.split('lotid=')[1] || '').split('&')[0];
			
			$http({
		        url: '/elokchits/lot/lotEdit/'+lotId,
		        method: "GET",
		        
		    })
		    .then(function (response) {
		    	
		    	var lot = response.data;
		    	
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
		    	    $scope.status = lot.status;
		    }, 
		    function(response) { // optional
		    	alert("lot edited FAILED");
		    });
			
		
			$http({
		        url: '/elokchits/lotmember/allLotMembers/'+lotId,
		        method: "Get",
		        
		    })
		    .then(function(response) {
		    	$scope.lotmembers = response.data;
		    	$scope.memberTotalCount = response.data.length;
		    	var liftMember=0;
		    	var mem=response.data;
		    	for(var i = 0; i < data.length; i++){
		    	if(mem.paidDate!=null){
		    		$scope.liftMember=liftMember++;
					}}
		    }, 
		    function(response) { // optional
		    	alert("lot saved FAILED");
		    });	
		};
		
		$scope.deleteMembers=function(memrowid){
			
			$http({
		        url: '/elokchits/lot/deletemember/'+memrowid,
		        method: "GET",
		        
		    })
		    .then(function (response) {
		    	alert("Member deleted successfully");
		    	
		    	$scope.lots = response.datas.plice(m, 1);}, 
		    function(response) { // optional
		    	alert("Lot delete FAILED");
		    });
			
		};
		
});