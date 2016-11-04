var noteApp = angular.module('noteApp',[]);

noteApp.controller('noteController', ['$scope', function($scope) {
        $scope.url = "ws://localhost:8080/ca2/note";
        $scope.notes = [];
        
        $scope.category = {};
        $scope.category.type = {
            ALL: 'All',
            SOCIAL: 'Social',
            FOR_SALE: 'ForSale',
            JOBS: 'Jobs',
            TUTION: 'Tuition'
        }
        $scope.category.value = $scope.category.type.ALL;
        
        var socket = new WebSocket($scope.url);
        
        socket.onopen = function() {
            socket.send("Connected successfully, load init data");
        }
        
        socket.onmessage = function(evt) {
		// {category: " " , content: "", datetime: "", title: "", user: "" }
		var msg = JSON.parse(evt.data);
                $scope.notes.push(msg);
                $scope.$apply();
	}
        
}])


