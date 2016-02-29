angular.module('home.services', []).factory('HomeService', ['$http', function ($http) {
    return {
        search: function (title) {
            return $http.get('/api/omdb?title=' + title);
        },
        watchList: function () {
            return $http.get('/api/mywatchlist');
        }
    };
}]);

