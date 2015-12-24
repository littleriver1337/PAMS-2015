(function () {
  "use strict";
  angular
    .module('pamsRetailer', [
      'ngRoute',
      'underscore',
      'pamsLogin',
      'pamsAdmin',
      'pamsVerification',
      'pamsGuest'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/verifyR', {
          templateUrl: 'retailer/views/verify.html',
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
