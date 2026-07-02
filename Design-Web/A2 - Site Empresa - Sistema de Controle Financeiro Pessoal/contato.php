<?php 
$mensagemSucesso = "";
$mensagemErro = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $nome = strip_tags(trim($_POST["nome"]));
    $email = filter_var(trim($_POST["email"]), FILTER_SANITIZE_EMAIL);
    $assunto = strip_tags(trim($_POST["assunto"]));
    $mensagem = strip_tags(trim($_POST["mensagem"]));
    $para = "pabl_dias@icloud.com";

    $tituloEmail = "GestãoPrática - Novo Contato: " . $assunto;
    $corpoEmail = "Você recebeu uma nova mensagem do site GestãoPrática:\n\n";
    $corpoEmail .= "Nome: " . $nome . "\n";
    $corpoEmail .= "E-mail: " . $email . "\n";
    $corpoEmail .= "Assunto: " . $assunto . "\n\n";
    $corpoEmail .= "Mensagem:\n" . $mensagem . "\n";

    $headers = "From: " . $email . "\r\n";
    $headers .= "Reply-To: " . $email . "\r\n";
    $headers .= "X-Mailer: PHP/" . phpversion();

    if (mail($para, $tituloEmail, $corpoEmail, $headers)) {
        $mensagemSucesso = "Sua mensagem foi enviada com sucesso! Logo entraremos em contato.";
    } else {
        $mensagemErro = "Houve um erro ao tentar enviar o e-mail. Caso esteja rodando no Localhost, certifique-se de configurar o arquivo php.ini.";
    }
}

include 'includes/header.php'; 
?>

<div class="container my-4">
    <div class="row justify-content-center">
        <div class="col-12 col-md-10 col-lg-8">
            <div class="bg-body p-4 p-md-5 rounded shadow-sm border">
                <h1 class="fw-bold text-dark mb-2">Fale Conosco</h1>
                <p class="text-muted mb-4">Tem alguma dúvida ou sugestão? Preencha o formulário abaixo e nossa equipe responderá o mais rápido possível.</p>

                <?php if (!empty($mensagemSucesso)): ?>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="bi bi-check-circle-fill me-2"></i> <?php echo $mensagemSucesso; ?>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                <?php endif; ?>

                <?php if (!empty($mensagemErro)): ?>
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong><i class="bi bi-exclamation-triangle-fill me-2"></i>Nota do Sistema:</strong> <?php echo $mensagemErro; ?>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                <?php endif; ?>

                <form action="contato.php" method="POST" id="formContato">
                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="nome" class="form-label fw-semibold text-secondary">Seu Nome</label>
                            <input type="text" class="form-control" id="nome" name="nome" placeholder="Ex: João Silva" required>
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="email" class="form-label fw-semibold text-secondary">Seu E-mail</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Ex: joao@email.com" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="assunto" class="form-label fw-semibold text-secondary">Assunto</label>
                        <input type="text" class="form-control" id="assunto" name="assunto" placeholder="Como podemos ajudar?" required>
                    </div>

                    <div class="mb-4">
                        <label for="mensagem" class="form-label fw-semibold text-secondary">Mensagem</label>
                        <textarea class="form-control" id="mensagem" name="mensagem" rows="5" placeholder="Digite sua mensagem detalhadamente..." required></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary px-4 py-2 fw-bold">
                        <i class="bi bi-send-fill me-2"></i> Enviar Mensagem
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<?php include 'includes/footer.php'; ?>