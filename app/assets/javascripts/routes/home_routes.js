angular.module('home.routes', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/partials/home/home.html'
            })
            .when('/home', {
                templateUrl: '/partials/home/home_logged.html',
                controller: 'HomeController'
             })
    }]);