(function () {
  "use strict";

angular
  .module('pamsRetailer')
  .controller('RetailerController', function ($scope, VerificationService, LoginService, AdminService, CompanyService, RetailerService, $routeParams) {
    var vm = this;

    vm.importFile = function(file) {
      console.log("SUBMITTED", file);
      CompanyService.importFile(file);
    };

  });
})();
