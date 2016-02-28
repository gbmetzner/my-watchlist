angular.module('login.directives', []).directive('appHeader', function() {
  var bool = {
    'true': true,
    'false': false
  };

  return {
    restrict: 'E',
    link: function (scope, element, attrs) {
        console.log('Aqui');
      attrs.$observe('isauthenticated', function (newValue, oldValue) {
        if (bool[newValue]) { scope.headerUrl = '/assets/partials/login/logged.html'; }
        else { scope.headerUrl = '/assets/partials/login/not_logged.html'; }
      });
    },
    template: '<div data-ng-include="headerUrl"></div>'
  };
});