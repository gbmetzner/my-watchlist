angular.module('messages.directives', []).directive('messages',['$timeout', function($timeout) {
  return {
    restrict: 'E',
    link: function (scope, element, attrs) {
        scope.alerts = [];

        var indexes = 0;

        scope.closeAlert = function (index) {
            scope.alerts.splice(index, 1);
        };
    },
    templateUrl: '/partials/templates/messages.html'
  };
}]);