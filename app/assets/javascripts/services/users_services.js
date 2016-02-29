angular.module('users.services', []).factory('UserService', ['$http', function ($http) {
    return {
        save: function(user){
            return $http.post('/api/users', user);
        }
    }
}]);

angular.module('users.autServices', []).factory('AuthService', ['$http', '$location',function ($http, $location) {
    return {
        isLogged: function(){
                   $http.get('/api/logged').then(function(response){

                        },function(response){
                            $location.path("/")
                        });
        }
    }
}]);