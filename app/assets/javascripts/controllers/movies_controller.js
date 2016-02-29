angular.module('movies.controllers', ['movies.services', 'auth.services','ngCookies'])
    .controller('MovieController', ['$scope', '$cookies', '$location', 'MovieService', 'AuthService',
        function ($scope, $cookies, $location, MovieService, AuthService) {

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
                MovieService.search(title).then(function (response) {
                        $scope.watchlist = response.data.movies;
                    }, function (response) {
                        console.log(response);
                    });
            }

            $scope.addMovie = function(movie){
                MovieService.addMovie(movie).then(function (response) {
                        //Add message
                        console.log(response);
                 }, function (response) {
                        console.log(response);
                 });
            }

        }]);