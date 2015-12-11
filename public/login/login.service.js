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
        if (currentUser.accessLevel === "ADMIN") {
          console.log("User is an Admin");
          $location.path('/admin/');
        } else if (currentUser.accessLevel === "COMPANY_USER") {
          console.log("User is a Company");
        } else if (currentUser.accessLevel === "RETAILER_USER") {
          console.log("User is a Retailer");
        } else {
          console.log("User doesn't exist");
        }
      });
    };

    var guest = function () {
      $http.post(loginRoute).success(function(res){
        console.log("Guest access");
      });
    };

      return {
        login: login,
        guest: guest
      };

    });
})();
