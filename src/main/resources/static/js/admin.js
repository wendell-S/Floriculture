var successPopup = document.getElementById("successPopup");
if (successPopup) {
    setTimeout(function() {
        successPopup.style.display = "none";
    }, 2000);
}

var errorPopup = document.getElementById("errorPopup");
if (errorPopup) {
    setTimeout(function() {
        errorPopup.style.display = "none";
    }, 2000);
}
