(function() {
  "use strict";
  angular
    .module('pamsCompany')
    .factory('CompanyService', function($http, _, $location){

    var fileRoute = "/import-file/";

    var importFile = function(file) {
      $http.post(fileRoute, file).success(function(res){
        console.log("POSTED FILE: ", file);
        console.log("Response: ", res);
      });
    };

      return {
        importFile: importFile
      };

    });
})();

// var upload = Upload.upload({
//   url: '/import-file/',
//   data: {key: file, otherInfo: uploadInfo},
//   method: 'POST',
//
// });
