angular.module('login.controllers', ['login.services', 'ngCookies'])
    .controller('LoginController', ['$scope', '$cookies', '$location', 'LoginService',
        function ($scope, $cookies, $location, LoginService) {

            $scope.name = "";

                LoginService.logged()
                .then(function(response){
                    $location.path("/view/movies")
                },function(response){

                });

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
                        $scope.name = response.data.user.firstName;
                        $location.path("/view/watchlist");
                    },
                    function(response){
                        $scope.alerts.push({type: 'warning', msg: response.data.msg});
                    });
                }
              };

              $scope.logout = function(){
                $cookies.remove("XSRF-TOKEN");
                LoginService.logout().then(function(response){
                    $scope.name = "";
                    $location.path("/");
                    $scope.alerts.push({type: 'success', msg: response.data.msg});
                },
                function(response){
                });
              };

        }]);