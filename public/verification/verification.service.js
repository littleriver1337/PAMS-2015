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
        var time = res.time;
        if (res.isAuthentic) {
          $('.false').addClass('hidden');
          $('.true').removeClass('hidden');
          $('.true').html(
            "<div class='authImg'>" +
            "<img src='../assets/authentic.png'>" +
            "</div> <div class='authTxt'>" +
            "<h3> AUTHENTIC </h3> " +
            "<p> Please check to ensure that the following details match the product:</p>" +
            "<p> <b>Club Type</b>: " + clubType + "<br>" +
            "<b>Maker</b>: " + maker + "<br>" +
            "<b>Lie Angle</b>: " + lieAngle + "<br>" +
            "<b>Serial Number</b>: " + serialNumber + "<br>" +
            "<b>Year</b>: " + year + "<br></p></div>"
          );
        } else {
          var user = res.user.username;
          $('.true').addClass('hidden');
          $('.false').removeClass('hidden');
          $('.false').html(
            "<div class='authImg'>" +
            "<img id='notAuth' src='../assets/notAuthentic.png'>" +
            "</div> <div class='authTxt'>" +
            "<h3> WARNING: NOT AUTHENTIC </h3> " +
            "<p> This item has automatically been reported to PAMS, and it was recorded in the counterfeit tracking system at: <br><b>" +
            moment(time).format("h:mm:ss A [on] dddd, MMMM D, YYYY") + "</b> <br><br>" +
            "Entered by: <b>" + user + "</b>." +
             "</p></div>"
          );
        }
      });
    };

      return {
        checkItem: checkItem
      };

    });
})();
