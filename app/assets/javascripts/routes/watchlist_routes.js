angular.module('watchlist.routes', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/view/watchlist', {
               templateUrl: '/partials/watchlist/watchlist.html',
               controller: 'WatchlistController'
            })
    }]);


