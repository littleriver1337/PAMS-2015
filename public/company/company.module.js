(function () {
  "use strict";
  angular
    .module('pamsCompany', [
      'ngRoute',
      'underscore',
      'pamsLogin',
      'pamsAdmin',
      'pamsVerification',
      'pamsRetailer'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/files', {
          templateUrl: 'company/views/files.html',
          controller: 'CompanyController as companyCtrl',
        })
        .when('/verify', {
          templateUrl: 'company/views/verify.html',
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
