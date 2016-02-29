angular.module('login.controllers', ['login.services', 'ngCookies'])
    .controller('LoginController', ['$scope', '$cookies', '$location', 'LoginService',
        function ($scope, $cookies, $location, LoginService) {

            LoginService.logged().then(
                function(response){
                    $location.path("/view/watchlist")
                },function(response){ });

            $scope.isAuthenticate = function(){
                if($cookies.get("XSRF-TOKEN")){
                    return true;
                }else{
                    return false;
                }
            }

            $scope.login = function(loginForm){
                if (loginForm.$valid){
                    LoginService.login($scope.loginData).then(function(response){
                        $location.path("/view/watchlist");
                    },
                    function(response){
                        $scope.alerts.push({type: 'warning', msg: response.data.msg});
                    });
                }
            };

            $scope.logout = function(){
                LoginService.logout().then(function(response){
                    $cookies.remove("XSRF-TOKEN");
                    $location.path("/");
                    $scope.alerts.push({type: 'success', msg: response.data.msg});
                },
                function(response){ });
          };

        }]);