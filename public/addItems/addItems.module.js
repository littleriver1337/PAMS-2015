(function () {
  "use strict";
  angular
    .module('pamsAddItems', [
      'ngRoute',
      'underscore',
      'pamsLogin',
      'pamsVerification',
      'pamsCompany',
      'pamsRetailer',
      'pamsAdmin',
      'pamsGuest'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .otherwise({ redirectTo: '/404'});
    });

  angular
    .module('underscore', [])
    .factory('_', function($window){
      return $window._;
    });

})();
