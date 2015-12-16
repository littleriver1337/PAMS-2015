(function() {
  "use strict";
  angular
    .module('pamsCompany')
    .factory('CompanyService', function($http, _, $location){

    var itemRoute = "/find-club/";

    var checkItem = function(item) {
      $http.get(itemRoute + item.serialNumber).success(function(res){
        console.log("Posted Item: ", item);
        console.log("Response: ", res);
        var currentItem = res;
        if (res !== null) {
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
