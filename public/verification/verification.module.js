(function () {
  "use strict";
  angular
    .module('pamsVerification', [
      'ngRoute',
      'underscore',
      'pamsLogin',
      'pamsAdmin',
      'pamsCompany',
      'pamsRetailer'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/true', {
          templateUrl: 'verification/views/true.html',
          controller: 'VerificationController as verificationCtrl',
        })
        .when('/false', {
          templateUrl: 'verification/views/false.html',
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
