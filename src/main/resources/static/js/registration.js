window.addEventListener('DOMContentLoaded', (event) => {
    var errorMessage = document.getElementById('errorMessage').innerText;
    if (errorMessage) {
        showPopup(errorMessage);
    }

    var successMessage = document.getElementById('successMessage').innerText;
    if (successMessage) {
        showSuccessMessage(successMessage);
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

function showSuccessMessage(successMessage) {
    var successMessageElement = document.createElement('div');
    successMessageElement.className = 'success-message';
    successMessageElement.innerText = successMessage;
    document.body.appendChild(successMessageElement);
    setTimeout(function() {
        successMessageElement.style.display = 'none';
    }, 3000);
}
