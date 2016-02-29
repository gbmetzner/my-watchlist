angular.module('home.controllers', ['home.services', 'ngCookies'])
    .controller('HomeController', ['$scope', '$cookies', '$location', '$timeout', 'HomeService',
        function ($scope, $cookies, $location, $timeout, HomeService, ngDialog) {

            $scope.watchlist = [];

            $scope.$watch('movie', function (newVal) {
                console.log(newVal);
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

        }]);