(function() {
  "use strict";
  angular
    .module('pamsAdmin')
    .factory('AdminService', function($http, _){

      var itemRoute = "/find-club/";

      var userRoute = "/create-user/";

      var findUsersRoute = "/find-user/";

      var createAdmin = function(admin){
        admin.accessLevel = "ADMIN";
        $http.post(userRoute, admin).success(function(res){
          console.log("New admin posted: ", admin);
          console.log("Response: ", res);
        });
      };

      var createCompany = function(company){
        company.accessLevel = "COMPANY_USER";
        $http.post(userRoute, company).success(function(res){
          console.log("New company posted: ", company);
          console.log("Response: ", res);
        });
      };

      var createRetailer = function(retailer){
        retailer.accessLevel = "RETAILER_USER";
        $http.post(userRoute, retailer).success(function(res){
          console.log("New retailer posted: ", retailer);
          console.log("Response: ", res);
        });
      };

      var checkItem = function(item) {
        $http.get(itemRoute + item.serialNumber).success(function(res){
          console.log("Posted Item: ", item);
          console.log("Response: ", res);
          var currentItem = res;
        });
      };

      var getUsers = function(){
        return $http.get(findUsersRoute);
      };

      // var getID = function(user){
      //   return user.id;
      // };
      //
      // var removeUser = function(user){
      //   var userID = getID(user);
      //   $http.delete(userRoute + "/" + userID);
      // };

      return {
        createAdmin: createAdmin,
        createCompany: createCompany,
        createRetailer: createRetailer,
        checkItem: checkItem,
        getUsers: getUsers
        // getID: getID,
        // removeUser: removeUser
      };

    });
})();
