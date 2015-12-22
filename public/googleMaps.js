


// var myApp = angular.module("myApp", []);
//   myApp.directive("myMaps", function() {
//       return{
//         restrict: "E",
//         template: "<div></div>",
//         replace: true,
//         link: function(scope, element, attrs){
//           var myLatLng = new google.maps.LatLng(32.779991,-79.934250);
//           var mapOptions = {
//             center: myLatLng,
//             zoom: 16,
//             mapTypeId: google.maps.MapTypeId.ROADMAP
//           };
//           var map = new google.maps.Map(document.getElementById(attrs.id),
//             mapOptions);
//             var marker = new google.maps.Marker({
//               position: myLatLng,
//               map: map,
//               title: "PAMS"
//             });
//             marker.setMap(map);
//           }
//       };
//   });



// function initMap() {
//   var map = new google.maps.Map(document.getElementById('map'), {
//     zoom: 8,
//     center: {lat: 32.779991, lng: -79.934250}
//   });
//   var geocoder = new google.maps.Geocoder();
//
//   document.getElementById('submit').addEventListener('click', function() {
//     geocodeAddress(geocoder, map);
//   });
// }
//
// function geocodeAddress(geocoder, resultsMap) {
//   var address = document.getElementById('address').value;
//   geocoder.geocode({'address': address}, function(results, status) {
//     if (status === google.maps.GeocoderStatus.OK) {
//       resultsMap.setCenter(results[0].geometry.location);
//       var marker = new google.maps.Marker({
//         map: resultsMap,
//         position: results[0].geometry.location
//       });
//     } else {
//       alert('Geocode was not successful for the following reason: ' + status);
//     }
//   });
// }
