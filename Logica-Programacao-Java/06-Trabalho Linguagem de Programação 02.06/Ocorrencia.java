import java.util.Date;

public class Ocorrencia {
    private int id;
    private String descricao;
    private String tipoUrgencia;
    private Date dataAbertura;
    private int tempoResposta;
    private String status;

    private static final int META_PRAZO = 8;

    public Ocorrencia(int id, String descricao, String tipoUrgencia) {
        this.id            = id;
        this.descricao     = descricao;
        this.tipoUrgencia  = tipoUrgencia;
        this.dataAbertura  = new Date();
        this.tempoResposta = 0;
        this.status        = "ABERTO";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipoUrgencia() { return tipoUrgencia; }
    public void setTipoUrgencia(String t) { this.tipoUrgencia = t; }

    public Date getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(Date d) { this.dataAbertura = d; }

    public int getTempoResposta() { return tempoResposta; }
    public void setTempoResposta(int t) { this.tempoResposta = t; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void registrar() {
        System.out.println("[REGISTRO] Ocorrencia #" + id + " registrada!");
        System.out.println("Descricao: "  + descricao);
        System.out.println("Urgencia: "   + tipoUrgencia);
        System.out.println("Status: "     + status);
    }

    public void priorizarPorRisco() {
        System.out.println("[PRIORIDADE] Ocorrencia #" + id +
            " | Urgencia: " + tipoUrgencia);
    }

    public void verificarAlertaPrazo() throws MetaTempoUltrapassadaException {
        if (tempoResposta > META_PRAZO) {
            throw new MetaTempoUltrapassadaException(
                "[ALERTA PRAZO] Ocorrencia #" + id +
                " ultrapassou " + META_PRAZO + " minutos!" +
                " Tempo atual: " + tempoResposta + " min"
            );
        }
    }

    public void exibir() {
        System.out.println("Ocorrencia #" + id +
            " | "           + descricao +
            " | Urgencia: " + tipoUrgencia +
            " | Status: "   + status +
            " | Tempo: "    + tempoResposta + " min");
    }
}