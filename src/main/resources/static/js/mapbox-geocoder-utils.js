<<<<<<< HEAD
// "use strict";
// $(document).ready(function(){
//
//
//
//     function geocode(search, token) {
//         var baseUrl = 'https://api.mapbox.com';
//         var endPoint = '/geocoding/v5/mapbox.places/';
//         return fetch(baseUrl + endPoint + encodeURIComponent(search) + '.json' + "?" + 'access_token=' + token)
//             .then(function(res) {
//                 return res.json();
//                 // to get all the data from the request, comment out the following three lines...
//             }).then(function(data) {
//                 return data.features[0].center;
//             });
//     }
//
//     function reverseGeocode(coordinates, token) {
//         var baseUrl = 'https://api.mapbox.com';
//         var endPoint = '/geocoding/v5/mapbox.places/';
//         return fetch(baseUrl + endPoint + coordinates.lng + "," + coordinates.lat + '.json' + "?" + 'access_token=' + token)
//             .then(function(res) {
//                 return res.json();
//             })
//             // to get all the data from the request, comment out the following three lines...
//             .then(function(data) {
//                 return data.features[0].place_name;
//             });
//
//     }
//
//
//
//
//
// });
=======
"use strict";
$(document).ready(function(){



    function geocode(search, token) {
        const baseUrl = 'https://api.mapbox.com';
        const endPoint = '/geocoding/v5/mapbox.places/';
        return fetch(baseUrl + endPoint + encodeURIComponent(search) + '.json' + "?" + 'access_token=' + token)
            .then(function(res) {
                return res.json();
                // to get all the data from the request, comment out the following three lines...
            }).then(function(data) {
                return data.features[0].center;
            });
    }

    function reverseGeocode(coordinates, token) {
        const baseUrl = 'https://api.mapbox.com';
        const endPoint = '/geocoding/v5/mapbox.places/';
        return fetch(baseUrl + endPoint + coordinates.lng + "," + coordinates.lat + '.json' + "?" + 'access_token=' + token)
            .then(function(res) {
                return res.json();
            })
            // to get all the data from the request, comment out the following three lines...
            .then(function(data) {
                return data.features[0].place_name;
            });

    }





});
>>>>>>> 78222493c8be4916ae3c866ed74a52fcb2059eea

