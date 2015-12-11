(function () {
  "use strict";

angular
  .module('pamsAdmin')
  .controller('AdminController', function ($scope, AdminService, $routeParams) {
    var vm = this;

    vm.addAdmin = function(admin){
      console.log("SUBMITTED", admin);
      AdminService.createAdmin(admin);
    };

    vm.addCompany = function(company){
      console.log("SUBMITTED", company);
      AdminService.createCompany(company);
    };

    vm.addRetailer = function(retailer){
      console.log("SUBMITTED", retailer);
      AdminService.createRetailer(retailer);
    };

  });
})();
