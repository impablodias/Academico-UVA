// ===================== AtendenteCallcenter.java =====================

public class AtendenteCallcenter extends Usuario {
    private int ramal;

    public AtendenteCallcenter(int id, String nome, int ramal) {
        super(id, nome);
        this.ramal = ramal;
    }

    public int getRamal() { return ramal; }
    public void setRamal(int ramal) { this.ramal = ramal; }

    public void iniciarRegistroOcorrencia(Ocorrencia ocorrencia) {
        System.out.println("[ATENDENTE] " + getNome() + " registrando ocorrencia...");
        ocorrencia.registrar();
    }

    public void classificarEmergencia(Ocorrencia ocorrencia, String tipoUrgencia) {
        ocorrencia.setTipoUrgencia(tipoUrgencia);
        ocorrencia.priorizarPorRisco();
    }

    public void consultarInformacoes(Ocorrencia ocorrencia) {
        System.out.println("[CONSULTA] Ocorrencia #" + ocorrencia.getId());
        ocorrencia.exibir();
    }

    public void encerrarAtendimento(Ocorrencia ocorrencia) {
        ocorrencia.setStatus("ENCERRADO");
        System.out.println("[OK] Atendimento #" + ocorrencia.getId() + " encerrado!");
    }
}