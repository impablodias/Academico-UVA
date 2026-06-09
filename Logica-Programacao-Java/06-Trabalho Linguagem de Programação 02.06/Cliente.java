// ===================== Cliente.java =====================

public class Cliente extends Usuario {
    private String endereco;
    private int numeroCasa;

    public Cliente(int id, String nome, String endereco, int numeroCasa) {
        super(id, nome);
        this.endereco   = endereco;
        this.numeroCasa = numeroCasa;
    }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public int getNumeroCasa() { return numeroCasa; }
    public void setNumeroCasa(int numeroCasa) { this.numeroCasa = numeroCasa; }

    public void abrirChamado(Ocorrencia ocorrencia) {
        System.out.println("[CHAMADO] Cliente " + getNome() + " abrindo chamado...");
        ocorrencia.registrar();
    }

    public void informarEmergencia(Ocorrencia ocorrencia, String descricao) {
        System.out.println("[INFO] Informacao da emergencia: " + descricao);
        ocorrencia.setDescricao(descricao);
    }
}