
  function consultarCEP(cep) {
    // Limpa campos de endereço
    document.getElementById('endereco').value = '';
    document.getElementById('cidade').value = '';
    document.getElementById('estado').value = '';

    // Verifica se o CEP é válido (8 dígitos numéricos)
    var cepRegExp = /^[0-9]{8}$/;
    if (cepRegExp.test(cep)) {
      // Monta a URL da consulta
      var url = 'https://viacep.com.br/ws/' + cep + '/json/';

      // Faz uma requisição HTTP para o ViaCEP
      fetch(url)
        .then(response => response.json())
        .then(data => {
          if (!data.erro) {
            document.getElementById('endereco').value = data.logradouro;
            document.getElementById('cidade').value = data.localidade;
            document.getElementById('estado').value = data.uf;
          } else {
            alert('CEP não encontrado. Verifique o CEP digitado.');
          }
        })
        .catch(error => {
          console.error('Erro na consulta de CEP:', error);
        });
    } else {
      alert('CEP inválido. Digite um CEP com 8 dígitos numéricos.');
    }
  }