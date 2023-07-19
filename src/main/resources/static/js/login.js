window.addEventListener('DOMContentLoaded', (event) => {
    var errorMessage = document.getElementById('errorMessage').innerText;
    if (errorMessage) {
        showPopup(errorMessage);
    }
});

function showPopup(errorMessage) {
    var popup = document.createElement('div');
    popup.className = 'popup';
    popup.innerText = errorMessage;
    document.body.appendChild(popup);
    setTimeout(function() {
        popup.style.display = 'none';
    }, 3000);
}