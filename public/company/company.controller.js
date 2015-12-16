(function () {
  "use strict";

angular
  .module('pamsCompany')
  .controller('CompanyController', function ($scope, VerificationService, LoginService, AdminService, CompanyService, $routeParams) {
    var vm = this;

    vm.importFile = function(file) {
      console.log("SUBMITTED", file);
      CompanyService.importFile(file);
    };

  });
})();
