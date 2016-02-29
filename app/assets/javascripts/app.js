var myWatchlist = angular.module('myWatchlist',
    ['ngRoute',
     'ngCookies',
        'ui.bootstrap',
        'myWatchlist.tpl',
        'akoenig.deckgrid',
        'login.controllers',
        'home.controllers',
        'users.controllers',
        'users.routes',
        'home.routes',
        'login.directives',
        'messages.directives',
        'commons.directives']).config(function ($provide, $httpProvider, $locationProvider) {

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
     });

  $provide.factory('HttpInterceptor', function ($q, $location) {
    return {

      request: function (config) {
        return config || $q.when(config);
      },

      requestError: function (rejection) {
        return $q.reject(rejection);
      },

      response: function (response) {
        return response || $q.when(response);
      },

      responseError: function (rejection) {
          switch (rejection.status) {
              case 403:
                $location.path('/home');
                break;
              case 404:
                $location.path('/404');
                break;
          }
        return $q.reject(rejection);
      }
    };
  });

  $httpProvider.interceptors.push('HttpInterceptor');

});