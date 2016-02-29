angular.module('login.controllers', ['login.services', 'ngCookies'])
    .controller('LoginController', ['$scope', '$cookies', '$location', 'LoginService',
        function ($scope, $cookies, $location, LoginService, ngDialog) {

            $scope.logged = false;

            var token = $cookies.get("XSRF-TOKEN")

            if (token) {
                LoginService.logged()
                .then(function(response){
                    logged = true;
                },function(response){
                    logged = false;
                });
            }

              $scope.login = function(loginForm){
                if (loginForm.$valid){
                    LoginService.login($scope.loginData).then(function(response){
                        token = response.data.authToken;
                        $scope.name = response.data.user.firstName;
                        logged = true;
                        console.log(logged);
                        $location.path("/home")
                    },
                    function(response){
                        $scope.alerts.push({type: 'warning', msg: response.data.msg});
                        logged = false;
                    });
                }
              };

              $scope.logout = function(){
                LoginService.logout().then(function(response){
                    $scope.name = undefined;
                    logged = false;
                    $scope.alerts.push({type: 'success', msg: response.data.msg});
                },
                function(response){
                    logged = false;
                });
              };

        }]);