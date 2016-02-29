angular.module('users.services', []).factory('UserService', ['$http', function ($http) {
    return {
        save: function(user){
            return $http.post('/api/users', user);
        }
    }
}]);