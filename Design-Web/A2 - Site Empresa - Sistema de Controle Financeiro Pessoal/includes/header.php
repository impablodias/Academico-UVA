<!DOCTYPE html>
<html lang="pt-br" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Controle Financeiro</title>
    <link rel="shortcut icon" href="imagens/favicon/favicon.ico" type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link href="css/estilo.css" rel="stylesheet">
    
    <script>
        const temaSalvo = localStorage.getItem('temaGestaoPratica');
        if (temaSalvo) {
            document.documentElement.setAttribute('data-bs-theme', temaSalvo);
        } else if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
            document.documentElement.setAttribute('data-bs-theme', 'dark');
        }
    </script>
</head>
<body class="d-flex flex-column min-vh-100">

    <nav class="navbar navbar-expand-lg navbar-dark shadow-sm" style="background-color: #111827;">
        <div class="container">
            <a class="navbar-brand fw-bold" href="index.php">
                <i class="bi bi-wallet2"></i> GestãoPrática
            </a>
            
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto align-items-lg-center gap-1">
                    <li class="nav-item">
                        <a class="nav-link" href="index.php">
                            <i class="bi bi-house"></i> Home
                        </a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="quem-somos.php">
                            <i class="bi bi-people"></i> Sobre Nós
                        </a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="servicos.php">
                            <i class="bi bi-star"></i> Serviços
                        </a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="contato.php">
                            <i class="bi bi-chat-dots"></i> Contato
                        </a>
                    </li>
                    
                    <li class="nav-item d-none d-lg-block ms-2">
                        <div style="width: 1px; height: 24px; background-color: rgba(255,255,255,0.2);"></div>
                    </li>
                    
                    <li class="nav-item ms-lg-2">
                        <button class="btn btn-outline-light btn-sm fw-bold" id="btnToggleTema">
                            <i class="bi bi-moon-stars"></i> Tema
                        </button>
                    </li>
                    
                    <li class="nav-item">
                        <a class="btn btn-light btn-sm ms-lg-2 fw-bold" style="color: #111827;" href="login.php">
                            <i class="bi bi-box-arrow-in-right"></i> Login
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <main class="flex-grow-1">