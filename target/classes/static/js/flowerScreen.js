
document.addEventListener('DOMContentLoaded', function() {
 var enviarFlorBtn = document.getElementById('ver-flor-btn');

 enviarFlorBtn.addEventListener('click', function(){
 window.location.href = 'TelaFlores.html';
 });
});

//document.addEventListener('DOMContentLoaded', function() {
//  var exibirFlorBtn = document.getElementById('exibir-flor-btn');


  // Função para buscar as flores do backend e exibir na tela
  function getFlowers() {
    fetch('/floricultura/api/flowers')
      .then((response) => response.json())
      .then((data) => displayFlowers(data))
      .catch((error) => console.error('Erro ao buscar flores:', error));
  }

  // Função para exibir as flores na tela
  function displayFlowers(flowers) {
    const listaFloresDiv = document.getElementById('lista-flores');
    listaFloresDiv.innerHTML = ''; // Limpar a lista antes de exibir novamente

    flowers.forEach((flower) => {
      const flowerDiv = document.createElement('div');
      flowerDiv.innerHTML = `<h3>${flower.nome_flor}</h3>
                             <img src="${flower.imagem_flor}" alt="${flower.nome_flor}" width="150">`;
      listaFloresDiv.appendChild(flowerDiv);
    });
  }

  // Função para adicionar uma nova flor no backend
  function addFlower() {
    const form = document.getElementById('form-flor');
    const formData = new FormData(form);

    fetch('/floricultura/api/flowers', {
      method: 'POST',
      body: JSON.stringify({
        nome_flor: formData.get('nome_flor'),
        imagem_flor: formData.get('imagem_flor'),
      }),
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((response) => response.json())
      .then((data) => {
        // Atualiza a lista de flores na tela após adicionar a nova flor
        getFlowers();
      })
      .catch((error) => console.error('Erro ao adicionar a flor:', error));
  }

  // Chamada inicial para buscar e exibir as flores na tela
  getFlowers();

  // Atualiza a lista de flores a cada 10 segundos
  setInterval(getFlowers, 10000);
