import javax.swing.JOptionPane;

public class ResgatePRO {

    static long[] temposInicio = new long[10];

    public static void main(String[] args) {

        Ocorrencia[]       ocorrencias = new Ocorrencia[10];
        EquipeDeOperacao[] equipes     = new EquipeDeOperacao[3];
        Viatura[]          viaturas    = new Viatura[3];
        int totalOcorrencias = 0;

        viaturas[0] = new Viatura(1, "BOM-1234", "Auto Bomba");
        viaturas[1] = new Viatura(2, "BOM-5678", "Auto Escada");
        viaturas[2] = new Viatura(3, "BOM-9012", "Auto Resgate");

        equipes[0] = new EquipeDeOperacao(1, "Equipe Alpha", viaturas[0]);
        equipes[1] = new EquipeDeOperacao(2, "Equipe Beta",  viaturas[1]);
        equipes[2] = new EquipeDeOperacao(3, "Equipe Gama",  viaturas[2]);

        AtendenteCallcenter atendente = new AtendenteCallcenter(1, "Carlos Lima", 101);
        Supervisor supervisor = new Supervisor(1, "Ana Costa", "Manha");

        boolean rodando = true;

        while (rodando) {

            String[] menuOpcoes = {
                "1 - Registrar novo chamado",
                "2 - Listar ocorrencias",
                "3 - Despachar equipe",
                "4 - Encerrar atendimento",
                "5 - Gerar relatorio",
                "6 - Sair"
            };

            String escolha = (String) JOptionPane.showInputDialog(
                null,
                "RESGATEPRO - BOMBEIROS\nEscolha uma opcao:",
                "Menu Principal",
                JOptionPane.QUESTION_MESSAGE,
                null,
                menuOpcoes,
                menuOpcoes[0]
            );

            if (escolha == null || escolha.startsWith("6")) {
                rodando = false;
                break;
            }

            // ===================== REGISTRAR CHAMADO =====================
            if (escolha.startsWith("1")) {

                String nomeCliente = JOptionPane.showInputDialog(
                    null, "Nome do solicitante:", "Novo Chamado", JOptionPane.PLAIN_MESSAGE);
                if (nomeCliente == null) continue;

                String endereco = JOptionPane.showInputDialog(
                    null, "Endereco:", "Novo Chamado", JOptionPane.PLAIN_MESSAGE);
                if (endereco == null) continue;

                String numeroCasaStr = JOptionPane.showInputDialog(
                    null, "Numero da casa:", "Novo Chamado", JOptionPane.PLAIN_MESSAGE);
                if (numeroCasaStr == null) continue;
                int numeroCasa = Integer.parseInt(numeroCasaStr);

                String[] tipos = {
                    "Incendio",
                    "Acidente de transito",
                    "Vazamento de gas",
                    "Pessoa presa",
                    "Afogamento",
                    "Desabamento",
                    "Outro"
                };

                String tipoOcorrencia = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione o tipo de ocorrencia:",
                    "Tipo de Ocorrencia",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tipos,
                    tipos[0]
                );
                if (tipoOcorrencia == null) continue;

                if (tipoOcorrencia.equals("Outro")) {
                    tipoOcorrencia = JOptionPane.showInputDialog(
                        null, "Descreva a ocorrencia:", "Outro", JOptionPane.PLAIN_MESSAGE);
                    if (tipoOcorrencia == null) continue;
                }

                String[] urgencias = {"CRITICO", "ALTO", "MEDIO", "BAIXO"};

                String urgencia = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione o nivel de urgencia:",
                    "Urgencia",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    urgencias,
                    urgencias[0]
                );
                if (urgencia == null) continue;

                Cliente cliente = new Cliente(
                    totalOcorrencias + 1,
                    nomeCliente,
                    endereco,
                    numeroCasa
                );

                Ocorrencia novaOcorrencia = new Ocorrencia(
                    totalOcorrencias + 1,
                    tipoOcorrencia,
                    urgencia
                );

                temposInicio[totalOcorrencias] = System.currentTimeMillis();

                cliente.abrirChamado(novaOcorrencia);
                atendente.classificarEmergencia(novaOcorrencia, urgencia);

                ocorrencias[totalOcorrencias] = novaOcorrencia;
                totalOcorrencias++;

                JOptionPane.showMessageDialog(null,
                    "[OK] Chamado registrado!\n\n" +
                    "Solicitante: " + nomeCliente + "\n" +
                    "Tipo: "        + tipoOcorrencia + "\n" +
                    "Urgencia: "    + urgencia + "\n\n" +
                    "[CRONOMETRO INICIADO!]",
                    "Chamado Registrado", JOptionPane.INFORMATION_MESSAGE);
            }

            // ===================== LISTAR OCORRENCIAS =====================
            else if (escolha.startsWith("2")) {
                if (totalOcorrencias == 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma ocorrencia registrada!");
                } else {
                    StringBuilder lista = new StringBuilder("====== OCORRENCIAS ======\n\n");
                    for (int i = 0; i < totalOcorrencias; i++) {
                        if (ocorrencias[i] != null) {
                            long tempoAtual = 0;
                            if (!ocorrencias[i].getStatus().equals("ENCERRADO")) {
                                tempoAtual = (System.currentTimeMillis() -
                                    temposInicio[i]) / 60000;
                            } else {
                                tempoAtual = ocorrencias[i].getTempoResposta();
                            }
                            lista.append("#").append(ocorrencias[i].getId())
                                 .append(" | ").append(ocorrencias[i].getDescricao())
                                 .append(" | Urgencia: ").append(ocorrencias[i].getTipoUrgencia())
                                 .append(" | Status: ").append(ocorrencias[i].getStatus())
                                 .append(" | Tempo: ").append(tempoAtual)
                                 .append(" min\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null, lista.toString());
                }
            }

            // ===================== DESPACHAR EQUIPE =====================
            else if (escolha.startsWith("3")) {
                if (totalOcorrencias == 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma ocorrencia registrada!");
                    continue;
                }

                String[] ocorrenciasAbertas = new String[totalOcorrencias];
                int count = 0;
                for (int i = 0; i < totalOcorrencias; i++) {
                    if (ocorrencias[i] != null &&
                        ocorrencias[i].getStatus().equals("ABERTO")) {
                        ocorrenciasAbertas[count++] = "#" + ocorrencias[i].getId() +
                            " - " + ocorrencias[i].getDescricao() +
                            " | " + ocorrencias[i].getTipoUrgencia();
                    }
                }

                if (count == 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma ocorrencia aberta!");
                    continue;
                }

                String ocorrenciaEscolhida = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione a ocorrencia:",
                    "Despachar Equipe",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    ocorrenciasAbertas,
                    ocorrenciasAbertas[0]
                );
                if (ocorrenciaEscolhida == null) continue;

                int idxOcorrencia = 0;
                for (int i = 0; i < totalOcorrencias; i++) {
                    if (ocorrenciasAbertas[i] != null &&
                        ocorrenciasAbertas[i].equals(ocorrenciaEscolhida)) {
                        idxOcorrencia = i;
                    }
                }

                String[] equipesDisponiveis = new String[equipes.length];
                for (int i = 0; i < equipes.length; i++) {
                    equipesDisponiveis[i] = equipes[i].getNomeEquipe() +
                        " | " + equipes[i].getViatura().getPlaca() +
                        " | " + (equipes[i].isStatusDisponibilidade() ?
                            "Disponivel" : "Indisponivel");
                }

                String equipeEscolhida = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione a equipe:",
                    "Escolher Equipe",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    equipesDisponiveis,
                    equipesDisponiveis[0]
                );
                if (equipeEscolhida == null) continue;

                int idxEquipe = 0;
                for (int i = 0; i < equipes.length; i++) {
                    if (equipesDisponiveis[i].equals(equipeEscolhida)) {
                        idxEquipe = i;
                    }
                }

                try {
                    supervisor.autorizarDespacho(
                        equipes[idxEquipe],
                        ocorrencias[idxOcorrencia]
                    );
                    JOptionPane.showMessageDialog(null,
                        "[OK] Despacho autorizado!\n\n" +
                        "Equipe: "     + equipes[idxEquipe].getNomeEquipe() + "\n" +
                        "Viatura: "    + equipes[idxEquipe].getViatura().getPlaca() + "\n" +
                        "Ocorrencia: " + ocorrencias[idxOcorrencia].getDescricao() + "\n\n" +
                        "[CRONOMETRO CONTINUA RODANDO...]");
                } catch (ViaturaIndisponivelException e) {
                    JOptionPane.showMessageDialog(null,
                        "[ERRO] " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (AcessoNegadoException e) {
                    JOptionPane.showMessageDialog(null,
                        "[ACESSO NEGADO] " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            // ===================== ENCERRAR ATENDIMENTO =====================
            else if (escolha.startsWith("4")) {
                if (totalOcorrencias == 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma ocorrencia registrada!");
                    continue;
                }

                String[] emAtendimento = new String[totalOcorrencias];
                int countAt = 0;
                for (int i = 0; i < totalOcorrencias; i++) {
                    if (ocorrencias[i] != null &&
                        ocorrencias[i].getStatus().equals("EM ATENDIMENTO")) {
                        emAtendimento[countAt++] = "#" + ocorrencias[i].getId() +
                            " - " + ocorrencias[i].getDescricao();
                    }
                }

                if (countAt == 0) {
                    JOptionPane.showMessageDialog(null,
                        "Nenhuma ocorrencia em atendimento!");
                    continue;
                }

                String ocorrenciaEncerrar = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione a ocorrencia para encerrar:",
                    "Encerrar Atendimento",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    emAtendimento,
                    emAtendimento[0]
                );
                if (ocorrenciaEncerrar == null) continue;

                int idxEncerrar = 0;
                for (int i = 0; i < totalOcorrencias; i++) {
                    if (emAtendimento[i] != null &&
                        emAtendimento[i].equals(ocorrenciaEncerrar)) {
                        idxEncerrar = i;
                    }
                }

                long tempoFinal = System.currentTimeMillis() - temposInicio[idxEncerrar];
                int minutos = (int)(tempoFinal / 60000);

                ocorrencias[idxEncerrar].setTempoResposta(minutos);
                atendente.encerrarAtendimento(ocorrencias[idxEncerrar]);

                for (EquipeDeOperacao e : equipes) {
                    if (!e.isStatusDisponibilidade()) {
                        e.atualizarStatusRotina("DISPONIVEL");
                        break;
                    }
                }

                try {
                    ocorrencias[idxEncerrar].verificarAlertaPrazo();
                    JOptionPane.showMessageDialog(null,
                        "[OK] Atendimento encerrado!\n\n" +
                        "Ocorrencia: " + ocorrencias[idxEncerrar].getDescricao() + "\n" +
                        "Tempo total: " + minutos + " minutos\n\n" +
                        "Dentro do prazo de 8 minutos!",
                        "Atendimento Encerrado", JOptionPane.INFORMATION_MESSAGE);
                } catch (MetaTempoUltrapassadaException e) {
                    JOptionPane.showMessageDialog(null,
                        "[OK] Atendimento encerrado!\n\n" +
                        "Ocorrencia: " + ocorrencias[idxEncerrar].getDescricao() + "\n" +
                        "Tempo total: " + minutos + " minutos\n\n" +
                        "[ALERTA] " + e.getMessage(),
                        "Alerta! Prazo Ultrapassado!", JOptionPane.WARNING_MESSAGE);
                }
            }

            // ===================== RELATORIO =====================
            else if (escolha.startsWith("5")) {
                StringBuilder relatorio = new StringBuilder(
                    "RELATORIO DO TURNO - " + supervisor.getTurnoTrabalho() + "\n\n"
                );

                int total = 0, encerrados = 0, emAndamento = 0, alertas = 0;

                for (int i = 0; i < totalOcorrencias; i++) {
                    if (ocorrencias[i] != null) {
                        total++;
                        if (ocorrencias[i].getStatus().equals("ENCERRADO"))      encerrados++;
                        if (ocorrencias[i].getStatus().equals("EM ATENDIMENTO")) emAndamento++;
                        if (ocorrencias[i].getTempoResposta() > 8)               alertas++;
                        relatorio.append("#").append(ocorrencias[i].getId())
                                 .append(" | ").append(ocorrencias[i].getDescricao())
                                 .append(" | ").append(ocorrencias[i].getTipoUrgencia())
                                 .append(" | ").append(ocorrencias[i].getStatus())
                                 .append(" | ").append(ocorrencias[i].getTempoResposta())
                                 .append(" min\n");
                    }
                }

                relatorio.append("\nTotal de ocorrencias: ").append(total)
                         .append("\nEncerradas: ").append(encerrados)
                         .append("\nEm andamento: ").append(emAndamento)
                         .append("\nAlertas de prazo: ").append(alertas);

                relatorio.append("\n\nEQUIPES:\n");
                for (EquipeDeOperacao e : equipes) {
                    if (e != null) {
                        relatorio.append(e.getNomeEquipe())
                                 .append(" | ").append(e.getViatura().getPlaca())
                                 .append(" | ").append(e.isStatusDisponibilidade() ?
                                     "Disponivel" : "Indisponivel")
                                 .append("\n");
                    }
                }

                JOptionPane.showMessageDialog(null, relatorio.toString(),
                    "Relatorio do Turno", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null, "Sistema ResgatePRO encerrado!");
    }
}