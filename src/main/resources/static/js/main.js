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