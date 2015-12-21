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
        var clubType = res.clubType;
        if (currentItem.isAuthentic) {
          $('.true').removeClass('hidden');
          $('.true').append("Club Type: " + clubType);
          return currentItem;
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
