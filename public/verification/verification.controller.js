(function () {
  "use strict";

angular
  .module('pamsVerification')
  .controller('VerificationController', function ($scope, VerificationService, LoginService, AdminService, CompanyService, RetailerService, GuestService, $routeParams) {
    var vm = this;

    vm.check = function(item) {
      console.log("SUBMITTED", item);
      VerificationService.checkItem(item);
    };


    //   .then(function(item){
    //     vm.item = item;
    //     if($routeParams.id) {
    //       var something = vm.items;
    //       var currItem;
    //       currItem = something.filter(function(el) {
    //         return el.id === +$routeParams.id;
    //       });
    //       vm.item = currItem[0];
    //     }
    //     $scope.currItem = {};
    //     $scope.currItem.clubType = vm.currItem.clubType;
    //     $scope.currItem.id = vm.currItem.id;
    //     $scope.currItem.isAuthentic = vm.currItem.isAuthentic;
    //     $scope.currItem.lieAngle = vm.currItem.lieAngle;
    //     $scope.currItem.maker = vm.currItem.maker;
    //     $scope.currItem.serialNumber = vm.currItem.serialNumber;
    //     $scope.currItem.time = vm.currItem.time;
    //     $scope.currItem.user = vm.currItem.user;
    //     $scope.currItem.year = vm.currItem.year;
    //     window.thing = vm.currItem;
    //     console.log("here: ", vm.currItem);
    //   });
    // };

  });
})();
