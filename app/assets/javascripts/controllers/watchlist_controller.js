angular.module('watchlist.controllers', ['movies.services', 'auth.services','ngCookies'])
    .controller('WatchlistController', ['$scope', '$cookies', 'MovieService', 'AuthService',
        function ($scope, $cookies, MovieService, AuthService) {

        AuthService.isLogged();

        $scope.watchlist = [];

        MovieService.watchList().then(function (response) {
             $scope.watchlist = response.data.movies;
           }, function (response) {
                console.log(response);
           });
    }]);