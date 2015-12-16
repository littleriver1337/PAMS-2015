(function () {
  "use strict";

angular
  .module('pamsCompany')
  .controller('CompanyController', function ($scope, VerificationService, LoginService, AdminService, CompanyService, $routeParams) {
    var vm = this;

    vm.check = function(item) {
      console.log("SUBMITTED", item);
      VerificationService.checkItem(item);
    };

  });
})();
