angular.module('users.routes', ['users.controllers'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/views/users', {
                templateUrl: '/partials/users/users.html',
                controller: 'NewUserController'
            })
            .when('/views/login', {
                templateUrl: '/partials/login/login.html',
                controller: 'LoginController'
            }).when('/', {
                templateUrl: '/partials/home/home.html'
            });
    }]);