(function () {
  "use strict";
  angular
    .module('pamsAdmin', [
      'ngRoute',
      'underscore',
      'pamsLogin'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/admin', {
          templateUrl: 'admin/views/list.html',
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
        .otherwise({ redirectTo: '/404'});
    });

  angular
    .module('underscore', [])
    .factory('_', function($window){
      return $window._;
    });

})();
