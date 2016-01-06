(function () {
  "use strict";
  angular
    .module('pamsCompany', [
      'ngRoute',
      'underscore',
      'pamsLogin',
      'pamsAdmin',
      'pamsVerification',
      'pamsRetailer',
      'pamsAddItems'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/addItemsC', {
          templateUrl: 'company/views/addItemsC.html',
          controller: 'CompanyController as companyCtrl',
        })
        .when('/verifyC', {
          templateUrl: 'company/views/verify.html',
          controller: 'VerificationController as verificationCtrl',
        })
        .when('/addBagC', {
          templateUrl: 'company/views/addBag.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addClubC', {
          templateUrl: 'company/views/addClub.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addHatC', {
          templateUrl: 'company/views/addHat.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addShirtC', {
          templateUrl: 'company/views/addShirt.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addBallC', {
          templateUrl: 'company/views/addBall.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addPantsC', {
          templateUrl: 'company/views/addPants.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addShoesC', {
          templateUrl: 'company/views/addShoes.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addUmbrellaC', {
          templateUrl: 'company/views/addUmbrella.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/searchC', {
          templateUrl: 'company/views/searchC.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/makerC', {
          templateUrl: 'company/views/maker.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/lieAngleC', {
          templateUrl: 'company/views/lieAngle.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/yearC', {
          templateUrl: 'company/views/year.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/clubTypeC', {
          templateUrl: 'company/views/clubType.html',
          controller: 'AdminController as adminCtrl',
        })
        .otherwise({ redirectTo: '/404'});
    });

  angular
    .module('underscore', [])
    .factory('_', function($window){
      return $window._;
    });

})();
