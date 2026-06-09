// ===================== EquipeDeOperacao.java =====================

public class EquipeDeOperacao {
    private int idEquipe;
    private String nomeEquipe;
    private boolean statusDisponibilidade;
    private Viatura viatura;

    public EquipeDeOperacao(int idEquipe, String nomeEquipe, Viatura viatura) {
        this.idEquipe              = idEquipe;
        this.nomeEquipe            = nomeEquipe;
        this.viatura               = viatura;
        this.statusDisponibilidade = true;
    }

    public int getIdEquipe() { return idEquipe; }
    public void setIdEquipe(int id) { this.idEquipe = id; }

    public String getNomeEquipe() { return nomeEquipe; }
    public void setNomeEquipe(String n) { this.nomeEquipe = n; }

    public boolean isStatusDisponibilidade() { return statusDisponibilidade; }
    public void setStatusDisponibilidade(boolean s) { this.statusDisponibilidade = s; }

    public Viatura getViatura() { return viatura; }
    public void setViatura(Viatura v) { this.viatura = v; }

    public void registrarDeslocamento(Ocorrencia ocorrencia) {
        this.statusDisponibilidade = false;
        System.out.println("[DESLOCAMENTO] Equipe " + nomeEquipe +
            " deslocando para ocorrencia #" + ocorrencia.getId());
        System.out.println("Viatura: " + viatura.getPlaca());
    }

    public void atualizarStatusRotina(String novoStatus) {
        System.out.println("[STATUS] Equipe " + nomeEquipe +
            " atualizando status: " + novoStatus);
        if (novoStatus.equals("DISPONIVEL")) {
            this.statusDisponibilidade = true;
        }
    }

    public void exibir() {
        System.out.println("Equipe: " + nomeEquipe +
            " | Disponivel: " + (statusDisponibilidade ? "Sim" : "Nao") +
            " | Viatura: "    + viatura.getPlaca());
    }
}