"use strict";

console.log("this is working");

$(document).ready(function () {

    mapboxgl.accessToken = mapBoxToken;
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v9',
        center: [-21.9270884, 64.1436456],
        zoom: 13



    });

    // var marker = new mapboxgl.Marker({
    //     draggable: true,
    //     color: 'rgba(124, 165, 177, 0.78)'
    // })
    //     .setLngLat([-21.9270884, 64.1436456])
    //     .addTo(map);

});
