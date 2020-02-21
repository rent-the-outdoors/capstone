"use strict";

// When the user scrolls the page, execute myFunction
window.onscroll = function() {myFunction()};

// Get the search
var hero = document.getElementById("search");

// Get the offset position of the search
var sticky = hero.offsetTop;

// Add the sticky class to the search when you reach its scroll position. Remove "sticky" when you leave the scroll position
function myFunction() {
    if (window.pageYOffset >= sticky) {
        hero.classList.add("sticky")
    } else {
        hero.classList.remove("sticky");
    }
}
//
// $('#closeModal').click(function () {
//     $('#bookModal').toggleClass("is-active")
// });
// mapboxgl.accessToken = mapBoxToken;
// const map = new mapboxgl.Map({
//     container: 'map',
//     style: 'mapbox://styles/mapbox/streets-v9',
//     zoom: 15,
//     center: [-98.4936, 29.4241]
// });
// const markerDetails = {
//     color: 'orange',
//     draggable: false
// };
// const marker = new mapboxgl.Marker(markerDetails)
//     .setLngLat([-98.4936, 29.4241])
//     .addTo(map);
//
// function geocode(search, token) {
//     const baseUrl = 'https://api.mapbox.com';
//     const endPoint = '/geocoding/v5/mapbox.places/';
//     return fetch(baseUrl + endPoint + encodeURIComponent(search) + '.json' + "?" + 'access_token=' + token)
//         .then(function (res) {
//             return res.json();
//             // to get all the data from the request, comment out the following three lines...
//         }).then(function (data) {
//             return data.features[0].center;
//         });
// }
//
// var address = $('#address').val();
//
// geocode(address, mapBoxToken)
//     .then(function (result) {
//         map.flyTo({center: result});
//         marker.setLngLat(result);
//     });
//
//
// $(document).ready(function () {
//     var calBookingsBucket = [];
//     var req = $.ajax({
//         url: '/bookings.json',
//         type: "GET",
//         contentType: "application/json; charset=utf-8",
//         cache: false,
//         async: false,
//         processData: false,
//     });
//     req.done(function (bookings) {
//         bookings.forEach(function (booking) {
//             calBookingsBucket.push({
//                 title: booking.title,
//                 start: new Date(booking.dateStart),
//                 end: new Date(booking.dateEnd),
//             })
//         });
//         console.log(calBookingsBucket);
//
//
//     }); //for each ends here
//     var calendarEl = document.getElementById('calendar');
//
//     var calendar = new FullCalendar.Calendar(calendarEl, {
//         plugins: ['dayGrid'],
//         header: {
//             left: 'prev,next,today',
//             center: 'title',
//             right: 'myCustomButton'
//
//         },
//
//
//         customButtons: {
//             myCustomButton: {
//                 text: "Book!",
//                 click: function () {
//                     $('#bookModal').toggleClass("is-active");
//
//                 }
//             }
//
//
//         },
//
//         events: calBookingsBucket,
//
//
//     });
//
//
//     calendar.render();
// }); //end of doc.ready
//
//
