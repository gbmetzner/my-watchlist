angular.module('errors.routes', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/404', {
                templateUrl: '/partials/errors/404.html'
            })
            .otherwise('/');
    }]);