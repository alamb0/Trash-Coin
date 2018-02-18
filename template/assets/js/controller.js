var app = angular.module('myApp', []);

app.controller('myCtrl', function($scope, $http, $interval, $rootScope) {
    $scope.educatioxnBanner = 'http://pbsrg.asu.edu/fmp-online/wp-content/uploads/2014/06/asu-fulton-engineering-school.png';
    $scope.aboutBanner = '';
    $scope.projectBanner = ''
    $scope.username = '';
    $scope.id='';
    $rootScope.points = 0;

    $scope.getData = function(){
        $http({
            method: 'POST',
            url: 'https://d0c113ac.ngrok.io/user/create?username=thisthat&password=423'
        }).then(function successCallback(response) {
            $scope.id = response.data.id;
            $scope.username = response.data.username;
            updateUserOnInterval(1.1,1.2);
            console.log(response);
        }, function errorCallback(response) {
            console.log(1);
            console.log(response);
        }).catch(function(err){
            console.log(err);
        });
    }

    function updateUser(lat, long){
        $http({
            method: 'POST',
            url: 'https://d0c113ac.ngrok.io/user/' + $scope.id + '/update?x=' + lat + '&y=' + long
        }).then(function successCallback(response) {
            $rootScope.points += response.data.points + 5;
            console.log(response);
        }, function errorCallback(response) {
            console.log(1);
            console.log(response);
        }).catch(function(err){
            console.log(err);
        });
    }

    function updateUserOnInterval(lat, long){
        $interval(function(){
            updateUser(lat, long)
            console.log($scope.points);
        }, 5 * 1000);
    }

})