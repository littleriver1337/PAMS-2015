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

    vm.check = function(item) {
      console.log("SUBMITTED", item);
      AdminService.checkItem(item);
    };

    AdminService.getUsers().then(function(users){
      vm.users = users;
      if($routeParams.id) {
        var something = vm.users;
        var currUser;
        currUser = something.filter(function(el) {
          return el.id === +$routeParams.id;
        });
        vm.user = currUser[0];
      }
      $scope.user = {};
      $scope.user.accessLevel = vm.user.accessLevel;
      $scope.user.id = vm.user.id;
      $scope.user.companyName = vm.user.companyName;
      $scope.user.username = vm.user.username;
      $scope.user.password = vm.user.password;
      $scope.user.email = vm.user.email;
      $scope.user.address = vm.user.address;
      $scope.user.city = vm.user.city;
      $scope.user.state = vm.user.state;
      $scope.user.zip = vm.user.zip;
      window.thing = vm.user;
      console.log("user: ", vm.user);
    });

    vm.editUser = function(user){
      console.log("SUBMITTED ", user);
      AdminService.editUser(user);
    };

    vm.deleteUser = function(user){
      console.log("SUBMITTED ", user);
      AdminService.deleteUser(user);
    };

  });
})();
