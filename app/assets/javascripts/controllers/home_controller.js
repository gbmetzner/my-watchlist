angular.module('home.controllers', ['home.services', 'users.autServices','ngCookies'])
    .controller('HomeController', ['$scope', '$cookies', '$location', '$timeout', 'HomeService', 'AuthService',
        function ($scope, $cookies, $location, $timeout, HomeService, AuthService) {

            AuthService.isLogged();

            $scope.watchlist = [];

            $scope.$watch('movie', function (newVal) {
               if (newVal) {
                   if(newVal.length > 1){
                        search(newVal);
                   }
               }
           });

           var search = function(title){
                HomeService.search(title).then(function (response) {
                        $scope.watchlist = response.data.movies;
                    }, function (response) {
                        console.log(response);
                    });
            }

            $scope.addMovie = function(movie){
                HomeService.addMovie(movie).then(function (response) {
                        //Add message
                    }, function (response) {
                        console.log(response);
                });
            }

        }]).controller('WatchlistController', ['$scope', '$cookies', 'HomeService', 'AuthService',
                   function ($scope, $cookies, HomeService, AuthService) {

                       AuthService.isLogged();

                       $scope.watchlist = [];


                       HomeService.watchList().then(function (response) {
                             $scope.watchlist = response.data.movies;
                           }, function (response) {
                               console.log(response);
                       });


                   }]);