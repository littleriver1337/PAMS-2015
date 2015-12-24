(function () {
  "use strict";
  angular
    .module('pamsGuest', [
      'ngRoute',
      'underscore',
      'pamsLogin',
      'pamsAdmin',
      'pamsVerification',
      'pamsRetailer'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/verifyG', {
          templateUrl: 'guest/views/guestVerify.html',
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
