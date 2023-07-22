
document.addEventListener('DOMContentLoaded', function() {
 var enviarFlorBtn = document.getElementById('ver-flor-btn');

 enviarFlorBtn.addEventListener('click', function(){
 window.location.href = 'TelaFlores.html';
 });
});

document.addEventListener('DOMContentLoaded', function() {
  var exibirFlorBtn = document.getElementById('exibir-flor-btn');

  fetch('/flores/florN/1')
    .then(response => response.text())
    .then(nomeFlor => {
      fetch('/flores/florQ/1')
        .then(response => response.text())
        .then(Florq => {
          exibirFlorBtn.textContent = 'nome: ' + nomeFlor  + '\n' +
                                      'quant: ' + Florq;
        })
    })


    .catch(error => {
      console.log('Ocorreu um erro ao obter o nome da flor: ', error);
    });
});
