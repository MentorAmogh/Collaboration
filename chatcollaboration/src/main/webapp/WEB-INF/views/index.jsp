<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<head>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"> 
    <script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script> 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.3.1/angular-ui-router.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.3.1/angular-ui-router.min.js"></script>
<script>
    // Defining angularjs application.
    var postApp = angular.module('postApp', []);
    // Controller function and passing $http service and $scope var.
    postApp.controller('postController', function($scope, $http) {
      // create a blank object to handle form data.
        $scope.user = {};
      
      // calling our submit function.
    	$scope.register = function(){
    		  window.location = "registration.html";
    	} 
    	$scope.login = function(){
  		  window.location = "login.html";
  		}
    	$scope.chatPage = function(){
  		  window.location = "chatpage.html";
  		}
    	$scope.waindex = function(){
  		  window.location = "waindex.html";
  		}
    	$scope.allDetails = function(){
    		  window.location = "Alldata.html";
    	}  	
        
    });
</script>
<style>
h1 {
    text-shadow: 2px 2px 5px red;
}
</style>

</head>



<body ng-app="postApp" ng-controller="postController">
<div class="container">
<div class="col-sm-8 col-sm-offset-2">
    <div class="page-header"><h1>Friends Reunited</h1></div>
    
    <!-- working fine
    <div ng-init="myVar = 'http://cdn.tutorialzine.com/wp-content/uploads/2013/07/featured_4-100x100.jpg'">
	 -->
    
    <button type="button " ng-click="register()" >Registration form</button>
   
    <button type="button " ng-click="login()">Login</button>
  
    <button type="button " ng-click="chatPage()">Chat Page</button>
   
    <button type="button " ng-click="waindex()">Waindex</button>
      
    <button type="button " ng-click="allDetails()">All Details</button>
</div>
</div>
</body>
</html>