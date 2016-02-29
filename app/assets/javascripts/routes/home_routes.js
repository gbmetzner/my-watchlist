angular.module('home.routes', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/partials/home/home.html'
            })
            .when('/view/movies', {
                templateUrl: '/partials/home/home_logged.html',
                controller: 'HomeController'
             }).when('/view/watchlist', {
                               templateUrl: '/partials/watchlist/watchlist.html',
                               controller: 'WatchlistController'
                            })
    }]);