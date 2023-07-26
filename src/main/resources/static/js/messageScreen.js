function hidePopup() {
            const successPopup = document.getElementById('successPopup');
            const errorPopup = document.getElementById('errorPopup');

            if (successPopup) {
                successPopup.style.opacity = 1;
                setTimeout(function() {
                    successPopup.style.opacity = 0;
                }, 2000);
            }

            if (errorPopup) {
                errorPopup.style.opacity = 1;
                setTimeout(function() {
                    errorPopup.style.opacity = 0;
                }, 2000);
            }
        }

        document.addEventListener("DOMContentLoaded", hidePopup);
