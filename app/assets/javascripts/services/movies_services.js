angular.module('movies.services', []).factory('MovieService', ['$http', function ($http) {
    return {
        search: function (title) {
            return $http.get('/api/omdb?title=' + title);
        },
        watchList: function () {
            return $http.get('/api/watchlist');
        },
        addMovie: function (movie) {
            return $http.post('/api/watchlist', movie);
        }
    };
}]);

