<?php
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

$usuarios_permitidos = [
    'pablo' => ['senha' => '1234', 'nome' => 'Pablo Dias'],
    'duda'  => ['senha' => '1234', 'nome' => 'Duda Caetana'],
    'edmeaDias' => ['senha' => '130620@', 'nome' => 'Edméa Vaz Dias']
];

if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['usuario'])) {
    $user_digitado = strtolower(trim($_POST['usuario']));
    $senha_digitada = trim($_POST['senha']);
    $captcha_resposta = isset($_POST['captcha_resposta']) ? intval($_POST['captcha_resposta']) : 0;
    $resposta_correta = $_SESSION['captcha_num1'] + $_SESSION['captcha_num2'];

    if ($captcha_resposta !== $resposta_correta) {
        unset($_SESSION['captcha_num1']);
        header("Location: login.php?erro_captcha=1");
        exit;
    }

    if (array_key_exists($user_digitado, $usuarios_permitidos) && $usuarios_permitidos[$user_digitado]['senha'] === $senha_digitada) {
        $_SESSION['logado'] = true;
        $_SESSION['usuario_nome'] = $usuarios_permitidos[$user_digitado]['nome'];
        unset($_SESSION['captcha_num1']);
    } else {
        unset($_SESSION['captcha_num1']);
        header("Location: login.php?erro=1");
        exit;
    }
}

if (!isset($_SESSION['logado']) || $_SESSION['logado'] !== true) {
    header("Location: login.php");
    exit;
}

if (isset($_GET['acao']) && $_GET['acao'] == 'sair') {
    session_destroy();
    header("Location: index.php");
    exit;
}

if (!isset($_SESSION['movimentacoes'])) {
    $_SESSION['movimentacoes'] = [];
}

if (isset($_GET['excluir'])) {
    $id = intval($_GET['excluir']);
    if (isset($_SESSION['movimentacoes'][$id])) {
        unset($_SESSION['movimentacoes'][$id]);
        $_SESSION['movimentacoes'] = array_values($_SESSION['movimentacoes']);
    }
    header("Location: dashboard.php");
    exit;
}

if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['acao_form'])) {
    $descricao = strip_tags(trim($_POST['descricao']));
    $categoria = $_POST['categoria'];
    $valor = floatval($_POST['valor']);
    $tipo = ($categoria === 'Receitas') ? 'entrada' : 'saida';

    if (!empty($descricao) && $valor > 0) {
        if ($_POST['acao_form'] === 'editar' && isset($_POST['id_edicao'])) {
            $id = intval($_POST['id_edicao']);
            $_SESSION['movimentacoes'][$id]['descricao'] = $descricao;
            $_SESSION['movimentacoes'][$id]['categoria'] = $categoria;
            $_SESSION['movimentacoes'][$id]['valor'] = $valor;
            $_SESSION['movimentacoes'][$id]['tipo'] = $tipo;
        } else {
            array_unshift($_SESSION['movimentacoes'], [
                'data' => date('d/m/Y'),
                'descricao' => $descricao,
                'categoria' => $categoria,
                'valor' => $valor,
                'tipo' => $tipo
            ]);
        }
    }
    header("Location: dashboard.php");
    exit;
}

$modoEdicao = false;
$idEdicao = -1;
$descAtual = ""; $catAtual = ""; $valAtual = "";

if (isset($_GET['editar'])) {
    $idEdicao = intval($_GET['editar']);
    if (isset($_SESSION['movimentacoes'][$idEdicao])) {
        $modoEdicao = true;
        $descAtual = $_SESSION['movimentacoes'][$idEdicao]['descricao'];
        $catAtual = $_SESSION['movimentacoes'][$idEdicao]['categoria'];
        $valAtual = $_SESSION['movimentacoes'][$idEdicao]['valor'];
    }
}

$totalEntradas = 0; $totalSaidas = 0;
foreach ($_SESSION['movimentacoes'] as $mov) {
    if ($mov['tipo'] == 'entrada') { $totalEntradas += $mov['valor']; } else { $totalSaidas += $mov['valor']; }
}
$saldoLiquido = $totalEntradas - $totalSaidas;

function obterClasseCategoria($categoria) {
    $categorias = [
        'Receitas' => 'badge-receita',
        'Moradia' => 'badge-despesa',
        'Alimentação' => 'badge-despesa',
        'Transporte' => 'badge-despesa',
        'Educação' => 'badge-investimento',
        'Saúde' => 'badge-despesa',
        'Lazer' => 'badge-despesa',
        'Assinaturas' => 'badge-despesa',
        'Dívidas e Parcelas' => 'badge-cartao',
        'Investimentos e Reserva' => 'badge-reserva',
        'Impostos e Taxas' => 'badge-cartao',
        'Compras Pessoais' => 'badge-despesa',
        'Trabalho' => 'badge-receita',
        'Outros' => 'badge-outro'
    ];
    
    return $categorias[$categoria] ?? 'badge-outro';
}

include 'includes/header.php';
?>

<div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-4 pb-3 border-bottom">
        <div>
            <span class="text-muted small"><i class="bi bi-speedometer2"></i> Painel de Controle</span>
            <h1 class="h3 fw-bold text-dark mb-0">Olá, <?php echo htmlspecialchars($_SESSION['usuario_nome']); ?>!</h1>
        </div>
        <a href="dashboard.php?acao=sair" class="btn btn-outline-danger btn-sm fw-bold">
            <i class="bi bi-box-arrow-right"></i> Sair
        </a>
    </div>

    <div class="row g-3 mb-4">
        <div class="col-md-4">
            <div class="p-3 bg-body border rounded shadow-sm">
                <span class="text-secondary small fw-semibold"><i class="bi bi-arrow-up-circle text-success"></i> Total de Receitas (+)</span>
                <h2 class="text-success fw-bold my-1">R$ <?php echo number_format($totalEntradas, 2, ',', '.'); ?></h2>
            </div>
        </div>
        <div class="col-md-4">
            <div class="p-3 bg-body border rounded shadow-sm">
                <span class="text-secondary small fw-semibold"><i class="bi bi-arrow-down-circle text-danger"></i> Total de Despesas (-)</span>
                <h2 class="text-danger fw-bold my-1">R$ <?php echo number_format($totalSaidas, 2, ',', '.'); ?></h2>
            </div>
        </div>
        <div class="col-md-4">
            <div class="p-3 bg-body border rounded shadow-sm">
                <span class="text-secondary small fw-semibold"><i class="bi bi-cash-coin text-primary"></i> Saldo Líquido Real</span>
                <h2 class="text-primary fw-bold my-1" id="campoSaldo" data-valor="R$ <?php echo number_format($saldoLiquido, 2, ',', '.'); ?>">R$ <?php echo number_format($saldoLiquido, 2, ',', '.'); ?></h2>
                <button class="btn btn-link btn-sm p-0 text-decoration-none text-muted" id="btnOcultarSaldo"><i class="bi bi-eye-slash-fill"></i> Ocultar Saldo</button>
            </div>
        </div>
    </div>

    <div class="row g-4">
        <div class="col-lg-4">
            <div class="bg-body p-4 border rounded shadow-sm <?php echo $modoEdicao ? 'border-warning shadow' : ''; ?>">
                <h5 class="fw-bold text-dark mb-3">
                    <?php echo $modoEdicao ? '<i class="bi bi-pencil-square text-warning"></i> Editar Lançamento' : '<i class="bi bi-plus-circle-fill text-success"></i> Novo Lançamento'; ?>
                </h5>
                <form action="dashboard.php" method="POST" id="formLancamento">
                    <input type="hidden" name="acao_form" value="<?php echo $modoEdicao ? 'editar' : 'novo'; ?>">
                    <?php if($modoEdicao): ?><input type="hidden" name="id_edicao" value="<?php echo $idEdicao; ?>"><?php endif; ?>
                    
                    <div class="mb-3">
                        <label for="descricao" class="form-label fw-semibold text-secondary small">Descrição</label>
                        <input type="text" class="form-control" id="descricao" name="descricao" value="<?php echo htmlspecialchars($descAtual); ?>" required>
                    </div>

                    <div class="mb-3">
                        <label for="categoria" class="form-label fw-semibold text-secondary small">Categoria</label>
                        <select class="form-select" id="categoria" name="categoria" required>
                            <?php 
                            $opcoes = ["Receitas", "Moradia", "Alimentação", "Transporte", "Educação", "Saúde", "Lazer", "Assinaturas", "Dívidas e Parcelas", "Investimentos e Reserva", "Impostos e Taxas", "Compras Pessoais", "Trabalho", "Outros"];
                            foreach($opcoes as $op) {
                                $selecionado = ($catAtual === $op) ? "selected" : "";
                                echo "<option value='$op' $selecionado>$op</option>";
                            }
                            ?>
                        </select>
                    </div>

                    <div class="mb-4">
                        <label for="valor" class="form-label fw-semibold text-secondary small">Valor (R$)</label>
                        <input type="number" step="0.01" class="form-control" id="valor" name="valor" value="<?php echo $valAtual; ?>" required>
                    </div>

                    <?php if($modoEdicao): ?>
                        <button type="submit" class="btn btn-warning w-100 fw-bold py-2 mb-2"><i class="bi bi-check-circle"></i> Salvar Alteração</button>
                        <a href="dashboard.php" class="btn btn-outline-secondary w-100 fw-bold"><i class="bi bi-x-circle"></i> Cancelar Edição</a>
                    <?php else: ?>
                        <button type="submit" class="btn btn-success w-100 fw-bold py-2"><i class="bi bi-plus-circle"></i> Lançar no Fluxo</button>
                    <?php endif; ?>
                </form>
            </div>
        </div>

        <div class="col-lg-8">
            <div class="bg-body border rounded shadow-sm p-4">
                <h5 class="fw-bold mb-3 text-dark"><i class="bi bi-card-list"></i> Histórico de Movimentações</h5>
                <div class="table-responsive" style="max-height: 450px; overflow-y: auto;">
                    <table class="table table-hover table-bordered align-middle mb-0">
                        <thead class="table-light sticky-top">
                            <tr>
                                <th>Data</th>
                                <th>Descrição</th>
                                <th>Categoria</th>
                                <th class="text-end">Valor</th>
                                <th class="text-center">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php if (count($_SESSION['movimentacoes']) == 0): ?>
                                <tr><td colspan="5" class="text-center text-muted py-3">Nenhum registro encontrado.</td></tr>
                            <?php endif; ?>
                            
                            <?php foreach ($_SESSION['movimentacoes'] as $index => $mov): ?>
                                <tr>
                                    <td><?php echo htmlspecialchars($mov['data']); ?></td>
                                    <td><?php echo htmlspecialchars($mov['descricao']); ?></td>
                                    <td>
                                        <span class="badge <?php echo obterClasseCategoria($mov['categoria']); ?>">
                                            <?php echo htmlspecialchars($mov['categoria']); ?>
                                        </span>
                                    </td>
                                    <td class="text-end fw-semibold <?php echo ($mov['tipo'] == 'entrada') ? 'text-success' : 'text-danger'; ?>">
                                        <?php echo ($mov['tipo'] == 'entrada') ? '(+) ' : '(-) '; ?>R$ <?php echo number_format($mov['valor'], 2, ',', '.'); ?>
                                    </td>
                                    <td class="text-center">
                                        <a href="dashboard.php?editar=<?php echo $index; ?>" class="btn btn-sm btn-outline-primary" title="Editar Lançamento"><i class="bi bi-pencil-square"></i></a>
                                        <a href="dashboard.php?excluir=<?php echo $index; ?>" class="btn btn-sm btn-outline-danger" title="Excluir Lançamento" onclick="return confirm('Tem certeza que deseja apagar este registro?');"><i class="bi bi-trash-fill"></i></a>
                                    </td>
                                </tr>
                            <?php endforeach; ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<?php include 'includes/footer.php'; ?>