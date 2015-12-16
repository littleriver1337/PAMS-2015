(function() {
  "use strict";
  angular
    .module('pamsVerification')
    .factory('VerificationService', function($http, _, $location){

    var itemRoute = "/find-club/";

    var checkItem = function(item) {
      $http.get(itemRoute + item.serialNumber).success(function(res){
        console.log("Posted Item: ", item);
        console.log("Response: ", res);
        var currentItem = res;
        if (res.isAuthentic) {
          $location.path("/true/");
        } else {
          $location.path("/false/");
        }
      });
    };

      return {
        checkItem: checkItem
      };

    });
})();
