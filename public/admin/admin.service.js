(function() {
  "use strict";
  angular
    .module('pamsAdmin')
    .factory('AdminService', function($http, _, $location){

      var itemRoute = "/find-club/";
      var createClubRoute = "/create-club/";

      var createBagRoute = "/create-bag/";
      var userRoute = "/create-user/";

      var editUserRoute = "/edit-user/";
      var findUsersRoute = "/find-users/";
      var deleteUserRoute = "/delete-user/";

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

      var mapData = function (collection){
        return _.map(collection, function(el){
          return {
              accessLevel: el.accessLevel,
              username: el.username,
              password: el.password,
              companyName: el.companyName,
              email: el.email,
              id: el.id,
              address: el.address,
              city: el.city,
              state: el.state,
              zip: el.zip
          };
        });
      };

      var getUsers = function(){
        return $http.get(findUsersRoute).then(function(user){
          return mapData(user.data);
        });
      };

      var editUser = function(user){
        $http.put(editUserRoute, user).success(function(res){
          console.log("SUCCESSFUL PUT: ", user);
          console.log("Response: ", res);
        });
      };

      var deleteUser = function(user){
        $http.delete(deleteUserRoute + user.id).success(function(res){
          console.log("DELETED USER: ", user);
        });
      };

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

      var addBag = function(item){
        $http.post(createBagRoute, item).success(function(res){
          console.log("Bag Created: ", item);
          console.log("Response: ", res);
        });
      };

      return {
        createAdmin: createAdmin,
        createCompany: createCompany,
        createRetailer: createRetailer,
        getUsers: getUsers,
        editUser: editUser,
        deleteUser: deleteUser,
        checkItem: checkItem,
        addBag: addBag
      };

    });
})();
