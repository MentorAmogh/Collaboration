<html>
<head>
<title>My Chat Application</title>
<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-resource.min.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script	src="/WEB-INF/user_service.js"></script>
    
    
</head>
<body>
<script>
angular.module('mainApp',['ngResource']); //mainApp is our main module
angular.module('myApp.services').factory('Entry', function($resource) {
	  return $resource('/api/entries/:id'); // Note the full endpoint address
	});
angular.module('myApp.controllers',[]);

angular.module('myApp.controllers').controller('ResourceController',function($scope, Entry) {
  var entry = Entry.get({ id: $scope.id }, function() {
    console.log(entry);
  }); // get() returns a single entry

  var entries = Entry.query(function() {
    console.log(entries);
  }); //query() returns all the entries

  $scope.entry = new Entry(); //You can instantiate resource class

  $scope.entry.data = 'some data';

  Entry.save($scope.entry, function() {
    //data saved. do something here.
  }); //saves an entry. Assuming $scope.entry is the Entry object  
});
angular.module('myApp.services').factory('Entry', function($resource) {
	  return $resource('/api/entries/:id', { id: '@_id' }, {
	    update: {
	      method: 'PUT' // this method issues a PUT request
	    }
	  });
	});
</script>

</body>
</html>