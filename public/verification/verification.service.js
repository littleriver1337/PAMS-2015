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
        var clubType = res.clubType;
        var lieAngle = res.lieAngle;
        var year = res.year;
        if (res.isAuthentic) {
          $('.true').removeClass('hidden');
          $('.true').append(
            "Club Type: " + clubType + "<br>" +
            "Lie Angle: " + lieAngle + "<br>" +
            "Year: " + year + "<br>"
          );
          return currentItem;
        } else {
          $location.path("/false/");
        }
      });
    };

    // counterfeit item --> user.address, user.city, user.state
  //  "/list-jacks/" POST
  //  "/find-club/" GET

      return {
        checkItem: checkItem
      };

    });
})();
