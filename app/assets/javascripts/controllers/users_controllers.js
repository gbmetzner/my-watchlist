angular.module('users.controllers', ['users.services'])
    .controller('NewUserController', ['$scope', '$location','UserService',
        function ($scope, $location, UserService) {

            $scope.user = {
                firstName: "",
                lastName: "",
                email: "",
                password: "",
                confirmPassword: ""
            };

            $scope.save = function (userForm) {
                if (userForm.$valid) {
                    UserService.save($scope.user).then(function (response) {
                        $scope.alerts.push({type: 'success', msg: response.data});
                        $location.path("/");
                    }, function (response) {
                        $scope.alerts.push({type: 'warning', msg: response.data});
                    });
                }
            };

        }]);