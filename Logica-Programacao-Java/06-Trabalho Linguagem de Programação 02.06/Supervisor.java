// ===================== Supervisor.java =====================

public class Supervisor extends Usuario {
    private String turnoTrabalho;

    public Supervisor(int id, String nome, String turnoTrabalho) {
        super(id, nome);
        this.turnoTrabalho = turnoTrabalho;
    }

    public String getTurnoTrabalho() { return turnoTrabalho; }
    public void setTurnoTrabalho(String t) { this.turnoTrabalho = t; }

    public void gerenciarEscalaEquipe(EquipeDeOperacao equipe) {
        System.out.println("[SUPERVISOR] " + getNome() +
            " gerenciando equipe: " + equipe.getNomeEquipe());
    }

    public void autorizarDespacho(EquipeDeOperacao equipe, Ocorrencia ocorrencia)
            throws ViaturaIndisponivelException, AcessoNegadoException {
        System.out.println("[DESPACHO] " + getNome() + " autorizando despacho...");
        if (!equipe.isStatusDisponibilidade()) {
            throw new ViaturaIndisponivelException(
                "Equipe " + equipe.getNomeEquipe() + " indisponivel para despacho!"
            );
        }
        equipe.registrarDeslocamento(ocorrencia);
        ocorrencia.setStatus("EM ATENDIMENTO");
        System.out.println("[OK] Despacho autorizado para ocorrencia #" + ocorrencia.getId());
    }

    public void emitirRelatorioTurno(Ocorrencia[] ocorrencias) {
        System.out.println("\n[RELATORIO] TURNO - " + turnoTrabalho);
        int total = 0, encerrados = 0, emAndamento = 0, alertas = 0;
        for (Ocorrencia o : ocorrencias) {
            if (o != null) {
                total++;
                if (o.getStatus().equals("ENCERRADO"))      encerrados++;
                if (o.getStatus().equals("EM ATENDIMENTO")) emAndamento++;
                if (o.getTempoResposta() > 8)               alertas++;
                o.exibir();
            }
        }
        System.out.println("Total: "       + total);
        System.out.println("Encerrados: "  + encerrados);
        System.out.println("Em andamento: "+ emAndamento);
        System.out.println("Alertas SLA: " + alertas);
    }
}