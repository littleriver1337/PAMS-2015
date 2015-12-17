(function () {
  "use strict";
  angular
    .module('pamsRetailer', [
      'ngRoute',
      'underscore',
      'pamsLogin',
      'pamsAdmin',
      'pamsVerification'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/verify', {
          templateUrl: 'retailer/views/verify.html',
          controller: 'RetailerController as retailerCtrl',
        })
        .otherwise({ redirectTo: '/404'});
    });

  angular
    .module('underscore', [])
    .factory('_', function($window){
      return $window._;
    });

})();
