(function() {
  "use strict";
  angular
    .module('pamsLogin')
    .factory('LoginService', function($http, _, $location){

    var loginRoute = "/login/";

    var login = function(userInfo){
      $http.post(loginRoute, userInfo).success(function(res){
        console.log("Login info posted: ", userInfo);
        console.log("Response: ", res);
        var currentUser = res;
        var username = res.username;
        if (currentUser.accessLevel === "ADMIN") {
          console.log("User is an Admin");
          $location.path('/admin/');
          $('#tester').html(username);
        } else if (currentUser.accessLevel === "COMPANY_USER") {
          console.log("User is a Company");
          $location.path('/verifyC/');
        } else if (currentUser.accessLevel === "RETAILER_USER") {
          console.log("User is a Retailer");
          $location.path('/verifyR/');
        } else {
          console.log("User doesn't exist");
        }
      });
    };

    var guest = function () {
      $http.post(loginRoute).success(function(res){
        console.log("Guest access");
        $location.path('/verifyG/');
      });
    };

      return {
        login: login,
        guest: guest
      };

    });
})();
