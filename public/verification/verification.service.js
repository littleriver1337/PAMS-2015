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
        var maker = res.maker;
        var serialNumber = res.serialNumber;
        if (res.isAuthentic) {
          $('.false').addClass('hidden');
          $('.true').removeClass('hidden');
          $('.true').html(
            "<h3> AUTHENTIC </h3> " +
            "<p> Please check to ensure the following details match the product.</p>" +
            "<p> <b>Club Type</b>: " + clubType + "<br>" +
            "<b>Maker</b>: " + maker + "<br>" +
            "<b>Lie Angle</b>: " + lieAngle + "<br>" +
            "<b>Serial Number</b>: " + serialNumber + "<br>" +
            "<b>Year</b>: " + year + "<br></p>"
          );
          return currentItem;
        } else {
          $('.true').addClass('hidden');
          $('.false').removeClass('hidden');
        }
      });
    };

      return {
        checkItem: checkItem
      };

    });
})();
