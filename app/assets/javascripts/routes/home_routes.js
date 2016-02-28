angular.module('home.routes', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/partials/home/home.html'
            })
    }]);