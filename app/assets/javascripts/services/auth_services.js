angular.module('auth.services', []).factory('AuthService', ['$http', '$location',function ($http, $location) {
    return {
        isLogged: function(){
           $http.get('/api/logged').then(function(response){
                  console.log(response);
                },function(response){
                    $location.path("/")
                });
        }
    }
}]);