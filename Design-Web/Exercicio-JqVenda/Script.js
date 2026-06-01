$(document).ready(function() {
    $('#buttonCalcular').click(function() {

        let dataInput = $('#dataVenda').val();
        let produtoInput = $('#nomeDoProduto').val();
        let precoInput = parseFloat($('#precoUnitario').val());
        let qtdInput = parseInt($('#quantidadeVendida').val());

        if (!dataInput) {
            $('#dialogErroData').dialog();
            return;
        }
        if (!produtoInput) {
            alert("Preencha o nome do produto!");
            return;
        }
        let totalCalculado = precoInput * qtdInput;

        $('#resData').text(dataInput);
        $('#resProduto').text(produtoInput);
        $('#resPrecoUnitario').text(precoInput.toFixed(2))
        $('#resQuantidadeVendida').text(qtdInput);
        $('#resTotalDaVenda').text(totalCalculado.toFixed(2))

        $('#resultado').show();
    });
});