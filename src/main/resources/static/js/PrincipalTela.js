document.addEventListener('DOMContentLoaded', function() {
 var enviarFlorBtn = document.getElementById('enviar-flor-btn');

 enviarFlorBtn.addEventListener('click', function(){
 window.location.href = 'TelaMensagem.html';
 });
});

var nome = window.prompt('ola, como se chamas?')
window.alert('prazer em te conhecer ' + nome,  ' fique avontade para explorar o site em desenvolvimento')
