angular.module('login.controllers', ['login.services', 'ngCookies'])
    .controller('LoginController', ['$scope', '$cookies', '$location', 'LoginService',
        function ($scope, $cookies, $location, LoginService, ngDialog) {

            var logged = false;

            var token = $cookies["XSRF-TOKEN"];

            if (token) {
//                LoginService.logged()
//                .then(
//                    function(response){
//                        $scope.name = response.data.user.name;
//                        $scope.logged = true;
//                },
//                    function(response){
//                        $scope.logged = true;
//                });
            }

            $scope.isAuthenticated = function(){
                return logged;
            }

              $scope.login = function(loginForm){
                if (loginForm.$valid){
                    LoginService.login($scope.loginData).then(function(response){
                        token = response.data.token;
                        $scope.name = response.data.user.name;
                        $scope.logged = true;
                        console.log(logged);
                        $location.path("/home")
                    },
                    function(response){
                        $scope.alerts.push({type: 'warning', msg: response.data.msg});
                        $scope.logged = false;
                    });
                }
              };

              $scope.logout = function(){
                LoginService.logout().then(function(response){
                    $scope.name = undefined;
                    $scope.logged = false;
                    $scope.alerts.push({type: 'success', msg: response.data.msg});
                },
                function(response){
                    $scope.logged = false;
                });
              };

        }]);