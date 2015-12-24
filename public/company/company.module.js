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
      'pamsAddItems',
      'pamsGuest'
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
        .when('/searchC', {
          templateUrl: 'company/views/searchC.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .otherwise({ redirectTo: '/404'});
    });

  angular
    .module('underscore', [])
    .factory('_', function($window){
      return $window._;
    });

})();
