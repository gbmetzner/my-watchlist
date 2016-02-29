angular.module('home.controllers', ['home.services', 'ngCookies'])
    .controller('HomeController', ['$scope', '$cookies', '$location', '$timeout', 'ngDialog','HomeService',
        function ($scope, $cookies, $location, $timeout, ngDialog, HomeService) {

            $scope.watchlist = [];

            $scope.alerts = [];

            $scope.$watch('movie', function (newVal) {
               if (newVal) {
                   if (timeout) $timeout.cancel(timeout);
                   timeout = $timeout(function () {
                       if(newVal.length > 1){
                            search(newVal);
                       }
                   }, 350);
               }
           });

           var timeout;

           var search = function(title){
                HomeService.search(title).then(function (response) {
                        $scope.watchlist = response.data.movies;
                    }, function (response) {
                        console.log(response);
                    });
            }

            $scope.addMovie = function(movie){
                HomeService.addMovie(movie).then(function (response) {
                        $scope.alerts.push({type: 'success', msg: response.data.msg});
                    }, function (response) {
                        console.log(response);
                });
            }

        }]);