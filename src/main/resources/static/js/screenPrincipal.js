document.addEventListener('DOMContentLoaded', function() {
    new Vue({
        el: '#app',
        data: {
            isMenuOpen: false
        },
        methods: {
            toggleMenu() {
                this.isMenuOpen = !this.isMenuOpen;
            },
            redirectTo(route) {
                const currentPath = window.location.origin;
                window.location.href = currentPath + route;
            }
        }
    });

    var enviarFlorBtn = document.getElementById('enviar-flor-btn');

    enviarFlorBtn.addEventListener('click', function() {
        window.location.href = 'TelaMensagem.html';
    });
});

