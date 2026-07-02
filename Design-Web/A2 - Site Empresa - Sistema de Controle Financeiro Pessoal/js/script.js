$(document).ready(function() {
    
    function atualizarBotaoTema() {
        const temaAtual = document.documentElement.getAttribute('data-bs-theme');
        const $btnTema = $('#btnToggleTema');
        
        if (temaAtual === 'dark') {
            $btnTema
                .removeClass('btn-outline-light')
                .addClass('btn-warning')
                .html('<i class="bi bi-brightness-high-fill"></i> Modo Claro');
        } else {
            $btnTema
                .removeClass('btn-warning')
                .addClass('btn-outline-light')
                .html('<i class="bi bi-moon-stars"></i> Modo Escuro');
        }
    }

    atualizarBotaoTema();

    $('#btnToggleTema').on('click', function(e) {
        e.preventDefault();
        let temaAtual = document.documentElement.getAttribute('data-bs-theme') || 'light';
        let novoTema = (temaAtual === 'dark') ? 'light' : 'dark';
        document.documentElement.setAttribute('data-bs-theme', novoTema);
        localStorage.setItem('temaGestaoPratica', novoTema);
        
        atualizarBotaoTema();
    });
    
    let saldoVisivel = true;
    
    $('#btnOcultarSaldo').on('click', function(e) {
        e.preventDefault();
        
        let valorReal = $('#campoSaldo').attr('data-valor');

        if (saldoVisivel) {
            $('#campoSaldo').fadeOut(150, function() {
                $(this).text('R$ ••••••').fadeIn(150);
            });
            $('#btnOcultarSaldo').html('<i class="bi bi-eye-fill"></i> Mostrar Saldo');
            saldoVisivel = false;
        } else {
            $('#campoSaldo').fadeOut(150, function() {
                $(this).text(valorReal).fadeIn(150);
            });
            $('#btnOcultarSaldo').html('<i class="bi bi-eye-slash-fill"></i> Ocultar Saldo');
            saldoVisivel = true;
        }
    });

});