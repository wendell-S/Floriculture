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

window.addEventListener('DOMContentLoaded', (event) => {
    const successMessage = "[[${successMessage}]]";
    if (successMessage) {
        const successMessageElement = document.getElementById("successMessage");
        successMessageElement.innerText = successMessage;
        successMessageElement.style.display = "block";
        setTimeout(function() {
            successMessageElement.style.display = "none";
        }, 3000);
    }
});
