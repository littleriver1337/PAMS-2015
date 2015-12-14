(function () {
  "use strict";

angular
  .module('pamsAdmin')
  .controller('AdminController', function ($scope, AdminService, $routeParams, _) {
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

    AdminService.getUsers().then(function(users){
      vm.allUsers = _.map(users, function(el, idx, arr) {
        console.log("Users: ", uesrs);
        return {
          username: el.username,
          companyName: el.companyName,
          email: el.email,
          id: el.id,
          address: el.address,
          city: el.city,
          state: el.state,
          zip: el.zip
        };
      });
    });

  });
})();
