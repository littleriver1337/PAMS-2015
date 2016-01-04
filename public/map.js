var map;

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 32.779991, lng: -79.934250},
    zoom: 8
  });

// CUSTOM MARKER ICON
  var image = "assets/mapIcon.png";

// BOUNCE MARKER ICON ON CLICK
  function toggleBounce() {
  if (marker1.getAnimation() !== null) {
    marker1.setAnimation(null);
  } else {
    marker1.setAnimation(google.maps.Animation.BOUNCE);
  }
}

// MARKER 1 : PAMS HEADQUARTERS
  var marker1 = new google.maps.Marker({
    position: {lat: 32.779991, lng: -79.934250},
    map: map,
    title: "PAMS HEADQUARTERS",
    animation: google.maps.Animation.DROP,
    icon: image
  });
  //INFO WINDOW CONTENT
  var contentString1 = '<div class="infoWindowContent">' +
  '<h4>PAMS Headquarters</h4>' +
  '<h5>The Iron Yard</h5>' +
  '<ul>' +
  '<li>17 Priness Street</li>' +
  'Charleston, SC 29401' +
  '</ul> </div>';
  var infowindow1 = new google.maps.InfoWindow({
    content: contentString1
  });
  // EVENT LISTENERS - CLICK LOCATION MARKERS
  marker1.addListener('click', function() {
  infowindow1.open(map, marker1);
  });
  // marker1.addListener('click', toggleBounce);


// MARKER 2 : GOLFSMITH MYRTLE BEACH
  var marker2 = new google.maps.Marker({
    position: {lat: 33.808354, lng: -78.711687},
    map: map,
    title: "Golfsmith | North Myrtle Beach",
    icon: image
  });
  // marker2.setMap(map);
  //INFO WINDOW CONTENT
  var contentString2 = '<div class="infoWindowContent">' +
  '<h4>Golfsmith</h4>' +
  '<h5>North Myrtle Beach Branch</h5>' +
  '<ul>' +
  '<li>2301 Highway 17 S</li>' +
  'N. Myrtle Beach, SC 29582' +
  '</ul> </div>';
  var infowindow2 = new google.maps.InfoWindow({
    content: contentString2
  });
  // EVENT LISTENERS - CLICK LOCATION MARKERS
  marker2.addListener('click', function() {
  infowindow2.open(map, marker2);
  });
  // marker2.addListener('click', toggleBounce);


// MARKER 3 :
  var marker3 = new google.maps.Marker({
      position: {lat: 32.799501, lng: -80.027549},
      map: map,
      title: "Edwin Watts Golf | Charleston",
      animation: google.maps.Animation.DROP,
      icon: image
    });
    //INFO WINDOW CONTENT
    var contentString3 = '<div class="infoWindowContent">' +
    '<h4>Edwin Watts Golf</h4>' +
    '<h5>Charleston Branch</h5>' +
    '<ul>' +
    '<li>946 Orleans Road</li>' +
    'Charleston, SC 29407' +
    '</ul> </div>';
    var infowindow3 = new google.maps.InfoWindow({
      content: contentString3
    });
    // EVENT LISTENERS - CLICK LOCATION MARKERS
    marker3.addListener('click', function() {
    infowindow3.open(map, marker3);
    });
    // marker1.addListener('click', toggleBounce);

// MARKER 4 :
  var marker4 = new google.maps.Marker({
      position: {lat: 32.813933, lng: -79.843336},
      map: map,
      title: "David Ayres' Lowcountry Custom Golf",
      animation: google.maps.Animation.DROP,
      icon: image
    });
    //INFO WINDOW CONTENT
      var contentString4 = '<div class="infoWindowContent">' +
      "<h4>David Ayres'</h4>" +
      '<h5>Lowcountry Custom Golf</h5>' +
      '<ul>' +
      '<li>1485 Highway 17 N</li>' +
      'Mt. Pleasant, SC 29464' +
      '</ul> </div>';
      var infowindow4 = new google.maps.InfoWindow({
        content: contentString4
      });
      // EVENT LISTENERS - CLICK LOCATION MARKERS
      marker4.addListener('click', function() {
      infowindow4.open(map, marker4);
      });
      // marker1.addListener('click', toggleBounce);

  // MARKER 5 :
    var marker5 = new google.maps.Marker({
        position: {lat: 33.721873, lng: -78.880863},
        map: map,
        title: "PGA Tour Superstore",
        animation: google.maps.Animation.DROP,
        icon: image
      });
      //INFO WINDOW CONTENT
      var contentString5 = '<div class="infoWindowContent">' +
        "<h4>PGA Tour Superstore</h4>" +
        '<ul>' +
        '<li>1400 29th Avenue N</li>' +
        'Myrtle Beach, SC 29577' +
        '</ul> </div>';
      var infowindow5 = new google.maps.InfoWindow({
          content: contentString5
        });
        // EVENT LISTENERS - CLICK LOCATION MARKERS
        marker5.addListener('click', function() {
        infowindow5.open(map, marker5);
        });
        // marker1.addListener('click', toggleBounce);


// GEOCODING SERVICE
  // var geocoder = new google.maps.Geocoder();
  //
  // document.getElementById('submit').addEventListener('click', function() {
  //   geocodeAddress(geocoder, map);
  // });
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


}
