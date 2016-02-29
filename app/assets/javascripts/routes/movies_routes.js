angular.module('movies.routes', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/view/movies', {
                templateUrl: '/partials/home/home_logged.html',
                controller: 'MovieController'
             })
    }]);