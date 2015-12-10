(function () {
  "use strict";

angular
  .module('pamsLogin')
  .controller('LoginController', function ($scope, LoginService, $routeParams) {
    var vm = this;
    console.log("INSTATIATED");
    vm.login = function(userInfo){
      console.log("SUBMITTED", userInfo);
      LoginService.login(userInfo);
    };

    vm.loginAuth = function(accessLevel){
      LoginService.loginRes(accessLevel);
    };

  });
})();
