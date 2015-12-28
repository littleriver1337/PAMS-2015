(function () {
  "use strict";
  angular
    .module('pamsAdmin', [
      'ngRoute',
      'underscore',
      'pamsLogin',
      'pamsVerification',
      'pamsCompany',
      'pamsRetailer',
      'pamsAddItems',
      'ui.bootstrap.tpls',
      'pamsGuest'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/admin', {
          templateUrl: 'admin/views/list.html',
          controller: 'VerificationController as verificationCtrl',
        })
        .when('/addUsers', {
          templateUrl: 'admin/views/addUsers.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/addAdmin', {
          templateUrl: 'admin/views/addAdmin.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/addCompany', {
          templateUrl: 'admin/views/addCompany.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/addRetailer', {
          templateUrl: 'admin/views/addRetailer.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/users', {
          templateUrl: 'admin/views/users.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/addItems', {
          templateUrl: 'admin/views/addItems.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/addBag', {
          templateUrl: 'admin/views/addBag.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addClub', {
          templateUrl: 'admin/views/addClub.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addHat', {
          templateUrl: 'admin/views/addHat.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addShirt', {
          templateUrl: 'admin/views/addShirt.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addBall', {
          templateUrl: 'admin/views/addBall.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addPants', {
          templateUrl: 'admin/views/addPants.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addShoes', {
          templateUrl: 'admin/views/addShoes.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/addUmbrella', {
          templateUrl: 'admin/views/addUmbrella.html',
          controller: 'AddItemsController as addItemsCtrl',
        })
        .when('/search', {
          templateUrl: 'admin/views/search.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/map', {
          templateUrl: 'geocode/views/map.html',
        })
        .when('/:id', {
          templateUrl: 'admin/views/edit.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/true', {
          templateUrl: 'admin/views/true.html',
          controller: 'VerificationController as verificationCtrl',
        })
        .when('/false', {
          templateUrl: 'admin/views/false.html',
          controller: 'VerificationController as verificationCtrl',
        })
        .otherwise({ redirectTo: '/404'});
    });

  angular
    .module('underscore', [])
    .factory('_', function($window){
      return $window._;
    });

})();
