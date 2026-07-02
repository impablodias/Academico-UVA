<?php 
if (session_status() === PHP_SESSION_NONE) { 
    session_start(); 
}

if (!isset($_SESSION['captcha_num1'])) {
    $_SESSION['captcha_num1'] = rand(1, 9);
    $_SESSION['captcha_num2'] = rand(1, 9);
}

$erroLogin = isset($_GET['erro']) ? "Usuário ou senha incorretos!" : "";
$erroCaptcha = isset($_GET['erro_captcha']) ? "Captcha incorreto!" : "";

include 'includes/header.php'; 
?>

<div class="container d-flex justify-content-center align-items-center" style="min-height: calc(100vh - 200px);">
    <div class="col-12 col-md-6 col-lg-4">
        <div class="card shadow border-0 rounded-4">
            <div class="card-body p-4"> 
                <div class="text-center mb-4">
                    <h3 class="fw-bold">Login</h3> 
                </div>
                
                <?php if (!empty($erroLogin)): ?>
                    <div class="alert alert-danger text-center py-2 small" role="alert">
                        <i class="bi bi-exclamation-circle-fill me-1"></i> <?php echo $erroLogin; ?>
                    </div>
                <?php endif; ?>

                <?php if (!empty($erroCaptcha)): ?>
                    <div class="alert alert-warning text-center py-2 small" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-1"></i> <?php echo $erroCaptcha; ?>
                    </div>
                <?php endif; ?>
                
                <form action="dashboard.php" method="POST" id="formLogin">
                    <div class="mb-3">
                        <label for="usuario" class="form-label fw-semibold small mb-1">Usuário</label>
                        <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Digite seu usuário" required autofocus>
                    </div>
                    
                    <div class="mb-3">
                        <label for="senha" class="form-label fw-semibold small mb-1">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite sua senha" required>
                    </div>

                    <div class="mb-4 p-3 bg-body-tertiary rounded border text-center">
                        <label class="form-label fw-bold small mb-2">Soma de Segurança</label>
                        <div class="d-flex align-items-center justify-content-center gap-2">
                            <span class="fw-bold fs-5 text-primary"><?php echo $_SESSION['captcha_num1']; ?></span>
                            <span class="text-muted">+</span>
                            <span class="fw-bold fs-5 text-primary"><?php echo $_SESSION['captcha_num2']; ?></span>
                            <span class="text-muted">=</span>
                            <input type="number" class="form-control text-center fw-bold w-25" name="captcha_resposta" placeholder="?" required>
                        </div>
                    </div>
                    
                    <button type="submit" class="btn btn-primary w-100 fw-bold py-2">
                        <i class="bi bi-box-arrow-in-right me-2"></i> Entrar
                    </button>
                </form>

                <hr class="my-4">
                <p class="text-center small text-muted mb-0">
                    <strong>Ambiente de Testes</strong> <br>Credenciais de demonstração disponíveis mediante autorização.</br>
                </p>
            </div>
        </div>
    </div>
</div>

<?php include 'includes/footer.php'; ?>