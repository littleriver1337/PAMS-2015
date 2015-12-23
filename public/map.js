var map;
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 32.779991, lng: -79.934250},
    zoom: 8
  });
  var marker1 = new google.maps.Marker({
    position: {lat: 32.779991, lng: -79.934250},
    map: map,
    title: "PAMS"
  });
  marker1.setMap(map);

  var geocoder = new google.maps.Geocoder();

  document.getElementById('submit').addEventListener('click', function() {
    geocodeAddress(geocoder, map);
  });

  function geocodeAddress(geocoder, resultsMap) {
    var address = document.getElementById('address').value;
    geocoder.geocode({'address': address}, function(results, status) {
      if (status === google.maps.GeocoderStatus.OK) {
        resultsMap.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
          map: resultsMap,
          position: results[0].geometry.location
        });
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }

  function loadPins() {
    $.ajax({
      url: '/#/find-club/',
      method: 'GET',
      success: function(data){
        console.log("success: ", data);
      },
      failure: function(){
        console.log("failure");
      },
    });
  }
  loadPins();
}


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



// function loadPins() {
//   var pinArray = [];
//   $.ajax({
//     url: "/find-club/",
//     method: GET,
//     success: function(res){
//       console.log("success: ", res);
//       _.each(res, function(el, idx, arr){
//         var eachAddress = {
//           stAddress: el.user.address,
//           city: el.user.city,
//           state: el.user.state
//         };
//         pinArray.push(eachAddress);
//       });
//       _.each(pinArray, function(el, idx, arr){
//         var address = el.stAdress + ", " +  el.city + ", " + el.state;
//         geocoder.geocode(
//           { 'address': address }, function(results, status) {
//             if (status === google.maps.GeocoderStatus.OK) {
//               resultsMap.setCenter(results[0].geometry.location);
//               var marker = new google.maps.Marker({
//                 map: resultsMap,
//                 position: results[0].geometry.location
//               });
//             } else {
//               alert('Geocode was not successful for the following reason: ' + status);
//             }
//           });
//         )
//       });
//     },
//     failure: function(){
//       console.log("failure");
//     },
//   });
// }
