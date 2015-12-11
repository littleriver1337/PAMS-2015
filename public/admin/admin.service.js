(function() {
  "use strict";
  angular
    .module('pamsAdmin')
    .factory('AdminService', function($http, _){

      var itemRoute = "____";

      var userRoute = "/create-user/";

      var createAdmin = function(admin){
        $http.post(userRoute, admin).success(function(res){
          console.log("Admin created: ", res);
        });
      };

      var createCompany = function(company){
        $http.post(userRoute, company).success(function(res){
          console.log("Company created: ", res);
        });
      };

      var createRetailer = function(retailer){
        $http.post(userRoute, retailer).success(function(res){
          console.log("Retailer created: ", res);
        });
      };

      var checkItem = function(item) {
        $http.post(itemRoute, item).success(function(res){
          console.log("Response: ", res);
        });
      };

      return {
        createAdmin: createAdmin,
        createCompany: createCompany,
        createRetailer: createRetailer,
        checkItem: checkItem
      };

    });
})();
