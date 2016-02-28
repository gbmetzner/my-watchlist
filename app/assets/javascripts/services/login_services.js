angular.module('login.services', []).factory('LoginService', ['$http', function ($http) {
    return {
        login: function (loginData) {
            console.log(loginData);
            return $http.post('/api/login', loginData);
        },
        logout: function () {
            return $http.put('/api/logout');
        },
        logged: function(){
            return $http.get('/api/logged');
        }
    };
}]);

