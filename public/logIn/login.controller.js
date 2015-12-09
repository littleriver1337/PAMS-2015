(function () {
  "use strict";

angular
  .module('pamsLogin')
  .controller('LoginController', function ($scope, LoginService, $routeParams) {
    var vm = this;

    vm.login = function(userInfo){
      LoginService.login(userInfo);
    };

    vm.loginAuth = function(accessLevel){
      LoginService.loginRes(accessLevel);
    };

  });
})();
