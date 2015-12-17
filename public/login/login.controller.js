(function () {
  "use strict";

angular
  .module('pamsLogin')
  .controller('LoginController', function ($scope, LoginService, AdminService, VerificationService, CompanyService, RetailerService, $routeParams) {
    var vm = this;

    console.log("INSTATIATED");

    vm.login = function(userInfo){
      console.log("SUBMITTED", userInfo);
      LoginService.login(userInfo);
    };

  });
})();
