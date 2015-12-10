(function() {
  "use strict";
  angular
    .module('pamsLogin')
    .factory('LoginService', function($http, _){

    var loginRoute = "/login";

    var allUserInfo = "_____";

    var login = function(userInfo){
      $http.post(loginRoute, userInfo).success(function(res){
        console.log("Login info sent: ", userInfo);
      });
    };

    var loginRes = function(){
      $http.get(allUserInfo).then(function(){
        if (allUserInfo.accessLevel === 1) {
          console.log("Admin access");
        } else if (allUserInfo.accessLevel === 2){
          console.log("Company access");
        } else if (allUserInfo.accessLevel === 3) {
          console.log("AU access");
        } else {
          console.log("Not authorized");
        }
      });
    };

    var guest = function () {
      $http.post(___).success(function(res){
        console.log("Guest access");
      });
    };

      return {
        login: login,
        loginRes: loginRes,
        guest: guest
      };

    });
})();
