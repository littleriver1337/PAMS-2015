(function () {
  "use strict";
  angular
    .module('pamsAdmin', [
      'ngRoute',
      'underscore',
      'pamsLogin',
      'pamsVerification',
      'pamsCompany',
      'pamsRetailer'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/admin', {
          templateUrl: 'admin/views/list.html',
          controller: 'VerificationController as verificationCtrl',
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
        .when('/addBag', {
          templateUrl: 'admin/views/addBag.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/addClub', {
          templateUrl: 'admin/views/addClub.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/addHat', {
          templateUrl: 'admin/views/addHat.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/addShirt', {
          templateUrl: 'admin/views/addShirt.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/addBall', {
          templateUrl: 'admin/views/addBall.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/search', {
          templateUrl: 'admin/views/search.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/:id', {
          templateUrl: 'admin/views/edit.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/true', {
          templateUrl: 'verification/views/true.html',
          controller: 'AdminController as adminCtrl',
        })
        .when('/false', {
          templateUrl: 'verification/views/false.html',
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
