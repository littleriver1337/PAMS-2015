(function() {
  "use strict";
  angular
    .module('pamsAddItems')
    .factory('AddItemsService', function($http, _, $location){

      var createClubRoute = "/create-club/";

      var createBagRoute = "/create-bag/";

      var createHatRoute = "/create-hat/";

      var createShirtRoute = "/create-shirt/";

      var createBallRoute = "/create-ball/";

      var createPantsRoute = "/create-pant/";

      var createShoesRoute = "/create-shoe/";

      var createUmbrellaRoute = "/create-umbrella/";

      var addBag = function(item){
        $http.post(createBagRoute, item).success(function(res){
          console.log("Bag Created: ", item);
          console.log("Response: ", res);
        });
      };

      var addClub = function(item){
        $http.post(createClubRoute, item).success(function(res){
          console.log("Club Created: ", item);
          console.log("Response: ", res);
        });
      };

      var addHat = function(item){
        $http.post(createHatRoute, item).success(function(res){
          console.log("Hat Created: ", item);
          console.log("Response: ", res);
        });
      };

      var addShirt = function(item){
        $http.post(createShirtRoute, item).success(function(res){
          console.log("Shirt Created: ", item);
          console.log("Response: ", res);
        });
      };

      var addBall = function(item){
        $http.post(createBallRoute, item).success(function(res){
          console.log("Ball Created: ", item);
          console.log("Response: ", res);
        });
      };

      var addPants = function(item){
        $http.post(createPantsRoute, item).success(function(res){
          console.log("Pants Created: ", item);
          console.log("Response: ", res);
        });
      };

      var addShoes = function(item){
        $http.post(createShoesRoute, item).success(function(res){
          console.log("Shoes Created: ", item);
          console.log("Response: ", res);
        });
      };

      var addUmbrella = function(item){
        $http.post(createUmbrellaRoute, item).success(function(res){
          console.log("Umbrella Created: ", item);
          console.log("Response: ", res);
        });
      };

      return {
        addBag: addBag,
        addClub: addClub,
        addHat: addHat,
        addShirt: addShirt,
        addBall: addBall,
        addPants: addPants,
        addShoes: addShoes,
        addUmbrella: addUmbrella
      };

    });
})();
