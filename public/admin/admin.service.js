(function() {
  "use strict";
  angular
    .module('pamsAdmin')
    .factory('AdminService', function($http, _, $location){

      // CREATE PRODUCT ROUTES
      var itemRoute = "/find-club/";
      var createClubRoute = "/create-club/";
      var createBagRoute = "/create-bag/";
      var createHatRoute = "/create-hat/";
      var createShirtRoute = "/create-shirt/";
      var createBallRoute = "/create-ball/";

      // USER MANAGEMENT ROUTES
      var userRoute = "/create-user/";
      var editUserRoute = "/edit-user/";
      var findUsersRoute = "/find-users/";
      var deleteUserRoute = "/delete-user/";

      // SEARCH ROUTES
      var makerSearchRoute = "/search-by-maker/";
      var clubTypeSearchRoute = "/search-by-clubType/";
      var yearSearchRoute = "/search-by-year/";
      var lieAngleSearchRoute = "/search-by-lie-angle/";

      var createAdmin = function(admin){
        admin.accessLevel = "ADMIN";
        $http.post(userRoute, admin).success(function(res){
          console.log("New admin posted: ", admin);
          $('input[name="username"]').val('');
          $('input[name="password"]').val('');
          $('input[name="passwordVerify"]').val('');
          $('input[name="email"]').val('');
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

      var makerSearch = function(maker){
        $http.get(makerSearchRoute + maker).success(function(res){
          var searchHTML = "";
          _.each(res, function(el, idx, arr){
            searchHTML += "<div class='authTxt'> " +
            "<p><b>Club Type: </b>" +
            el.clubType + "<br>" +
            "<b>Maker: </b>" +
            el.maker + "<br>" +
            "<b>Lie Angle: </b>" +
            el.lieAngle + "<br>" +
            "<b>Serial Number: </b>" +
            el.serialNumber + "<br>" +
            "<b>Year: </b>" +
            el.year +
            "<br></p></div>";
          });
          $('.searchRes').removeClass('hidden');
          $('.actualResults').html(searchHTML);
          console.log("Maker: ", maker);
          console.log("Response: ", res);
        });
      };

      var clubTypeSearch = function(type){
        $http.get(clubTypeSearchRoute + type).success(function(res){
          var searchHTML = "";
          _.each(res, function(el, idx, arr){
            searchHTML += "<div class='authTxt'> " +
            "<p><b>Club Type: </b>" +
            el.clubType + "<br>" +
            "<b>Maker: </b>" +
            el.maker + "<br>" +
            "<b>Lie Angle: </b>" +
            el.lieAngle + "<br>" +
            "<b>Serial Number: </b>" +
            el.serialNumber + "<br>" +
            "<b>Year: </b>" +
            el.year +
            "<br></p></div>";
          });
          $('.searchRes').removeClass('hidden');
          $('.actualResults').html(searchHTML);
          console.log("Club Type: ", type);
          console.log("Response: ", res);
        });
      };

      var yearSearch = function(year){
        $http.get(yearSearchRoute + year).success(function(res){
          var searchHTML = "";
          _.each(res, function(el, idx, arr){
            searchHTML += "<div class='authTxt'> " +
            "<p><b>Club Type: </b>" +
            el.clubType + "<br>" +
            "<b>Maker: </b>" +
            el.maker + "<br>" +
            "<b>Lie Angle: </b>" +
            el.lieAngle + "<br>" +
            "<b>Serial Number: </b>" +
            el.serialNumber + "<br>" +
            "<b>Year: </b>" +
            el.year +
            "<br></p></div>";
          });
          $('.searchRes').removeClass('hidden');
          $('.actualResults').html(searchHTML);
          console.log("Year: ", year);
          console.log("Response: ", res);
        });
      };

      var lieAngleSearch = function(angle){
        $http.get(lieAngleSearchRoute + angle).success(function(res){
          var searchHTML = "";
          _.each(res, function(el, idx, arr){
            searchHTML += "<div class='authTxt'> " +
            "<p><b>Club Type: </b>" +
            el.clubType + "<br>" +
            "<b>Maker: </b>" +
            el.maker + "<br>" +
            "<b>Lie Angle: </b>" +
            el.lieAngle + "<br>" +
            "<b>Serial Number: </b>" +
            el.serialNumber + "<br>" +
            "<b>Year: </b>" +
            el.year +
            "<br></p></div>";
          });
          $('.searchRes').removeClass('hidden');
          $('.actualResults').html(searchHTML);
          console.log("Lie Angle: ", angle);
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
        addBag: addBag,
        addClub: addClub,
        addHat: addHat,
        addShirt: addShirt,
        addBall: addBall,
        makerSearch: makerSearch,
        clubTypeSearch: clubTypeSearch,
        yearSearch: yearSearch,
        lieAngleSearch: lieAngleSearch
      };

    });
})();
