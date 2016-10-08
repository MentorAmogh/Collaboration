<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"> 
    <script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script> 
</head>


<script>
    // Defining angularjs application.
    var postApp = angular.module('postApp', []);
    // Controller function and passing $http service and $scope var.
    postApp.controller('postController', function($scope, $http) {
      // create a blank object to handle form data.
        $scope.user = {};
      // calling our submit function.
        $scope.submitForm = function() {
        // Posting data to php file
        $http({
          method  : 'POST',
          url     : 'http://localhost:8085/collaboration/user',
          data    : $scope.user, //forms user object
          headers : {'Content-Type': 'application/json'} 
         })
          .success(function(data) {
            if (data.errors) {
              // Showing errors.
              $scope.errorName = data.errors.name;
              $scope.errorUserName = data.errors.username;
              $scope.errorEmail = data.errors.email;
              $scope.errorAddress = data.errors.email;
              $scope.errorPhone = data.errors.email;
              
            } else {
              $scope.message = data.message;
            }
          });
        };
    });
</script>
<body ng-app="postApp" ng-controller="postController">
<div class="container">
<div class="col-sm-8 col-sm-offset-2">
    <div class="page-header"><h1>Post data using angularJS</h1></div>
    <!-- FORM -->
    <form name="userForm" ng-submit="submitForm()">
    <div class="form-group">
        <label>Name</label>
        <input type="text" name="name" class="form-control" ng-model="user.fname">
        <span ng-show="errorName">{{errorName}}</span>
    </div>
    <div class="form-group">
        <label>Username</label>
        <input type="text" name="username" class="form-control" ng-model="user.username">
        <span ng-show="errorUserName">{{errorUserName}}</span>
    </div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" name="email" class="form-control" ng-model="user.email">
        <span ng-show="errorEmail">{{errorEmail}}</span>
    </div>
    <div class="form-group">
        <label>Phone</label>
        <input type="phone" name="Phone" class="form-control" ng-model="user.phno">
        <span ng-show="errorEmail">{{errorPhone}}</span>
    </div>
    <div class="form-group">
        <label>Address</label>
        <input type="text" name="Address" class="form-control" ng-model="user.address">
        <span ng-show="errorEmail">{{errorAddress}}</span>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</div>
</body>
</html>