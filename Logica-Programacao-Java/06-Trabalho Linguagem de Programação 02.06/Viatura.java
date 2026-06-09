// ===================== Viatura.java =====================

public class Viatura {
    private int idViatura;
    private String placa;
    private String tipoVeiculo;
    private boolean emManutencao;

    public Viatura(int idViatura, String placa, String tipoVeiculo) {
        this.idViatura    = idViatura;
        this.placa        = placa;
        this.tipoVeiculo  = tipoVeiculo;
        this.emManutencao = false;
    }

    public int getIdViatura() { return idViatura; }
    public void setIdViatura(int id) { this.idViatura = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getTipoVeiculo() { return tipoVeiculo; }
    public void setTipoVeiculo(String t) { this.tipoVeiculo = t; }

    public boolean isEmManutencao() { return emManutencao; }
    public void setEmManutencao(boolean m) { this.emManutencao = m; }

    public void exibir() {
        System.out.println("Viatura: " + placa +
            " | Tipo: "       + tipoVeiculo +
            " | Manutencao: " + (emManutencao ? "Sim" : "Nao"));
    }
}