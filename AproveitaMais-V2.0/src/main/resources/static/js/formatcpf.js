function formatCPF(input) {
    var value = input.value.replace(/\D/g, ''); // Remove caracteres não numéricos
    if (value.length > 0) {
      value = value.replace(/(\d{3})(\d)/, '$1.$2'); // Coloca o primeiro ponto
    }
    if (value.length > 5) {
      value = value.replace(/(\d{3})\.(\d{3})(\d)/, '$1.$2.$3'); // Coloca o segundo ponto
    }
    if (value.length > 9) {
      value = value.replace(/(\d{3})\.(\d{3})\.(\d{3})(\d)/, '$1.$2.$3-$4'); // Coloca o traço
    }
    input.value = value;
  }
  
  // Adicione um ouvinte de evento para chamar a função de formatação quando o usuário digitar
  var cpfInput = document.getElementById('cpf');
  cpfInput.addEventListener('input', function () {
    formatCPF(cpfInput);
  });
  