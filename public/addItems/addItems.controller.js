(function () {
  "use strict";

angular
  .module('pamsAddItems')
  .controller('AddItemsController', function ($scope, AdminService, LoginService, VerificationService, CompanyService, RetailerService, AddItemsService, GuestService, $routeParams, _) {
    var vm = this;

    vm.addBag = function(item){
      console.log("SUBMITTED", item);
      AddItemsService.addBag(item);
    };

    vm.addClub = function(item){
      console.log("SUBMITTED", item);
      AddItemsService.addClub(item);
    };

    vm.addHat = function(item){
      console.log("SUBMITTED", item);
      AddItemsService.addHat(item);
    };

    vm.addShirt = function(item){
      console.log("SUBMITTED", item);
      AddItemsService.addShirt(item);
    };

    vm.addBall = function(item){
      console.log("SUBMITTED", item);
      AddItemsService.addBall(item);
    };

    vm.addPants = function(item){
      console.log("SUBMITTED", item);
      AddItemsService.addPants(item);
    };

    vm.addShoes = function(item){
      console.log("SUBMITTED", item);
      AddItemsService.addShoes(item);
    };

    vm.addUmbrella = function(item){
      console.log("SUBMITTED", item);
      AddItemsService.addUmbrella(item);
    };

  });
})();
