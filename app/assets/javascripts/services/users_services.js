angular.module('users.services', []).factory('UserService', ['$http', function ($http) {
    return {
        save: function(user){
            return $http({
                        method: 'POST',
                        data: user,
                        headers: {'Content-Type': 'application/json'},
                        url: "/api/users"
                    });
        }
    }
}]);

