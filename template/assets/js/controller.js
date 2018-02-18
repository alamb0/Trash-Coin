var app = angular.module('myApp', []);

app.controller('myCtrl', function($scope, $http) {
    $scope.educatioxnBanner = 'http://pbsrg.asu.edu/fmp-online/wp-content/uploads/2014/06/asu-fulton-engineering-school.png';
    $scope.aboutBanner = '';
    $scope.projectBanner = ''
    $scope.username = '';

    $scope.getData = function(){
        $http({
            method: 'POST',
            url: 'https://d0c113ac.ngrok.io/user/create?username=thisthat&password=423'
        }).then(function successCallback(response) {
            $scope.username = response.data.username;
            console.log(response);
        }, function errorCallback(response) {
            console.log(1);
            console.log(response);
        }).catch(function(err){
            console.log(err);
        });
    }
})