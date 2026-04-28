import java.sql.Date;

public class AtividadesFuncionarios extends Funcionario {
    private int idAtividadeFuncionario;
    private int idFuncionarios;
    private Date data;

    public int getIdAtividadeFuncionario() {
        return idAtividadeFuncionario;
    }

    public void setIdAtividadeFuncionario(int idAtividadeFuncionario) {
        this.idAtividadeFuncionario = idAtividadeFuncionario;
    }

    public int getIdFuncionarios() {
        return idFuncionarios;
    }
    
    public void setIdFuncionarios() {
        this.idFuncionarios = idFuncionarios;
    }

    public Date getData() {
        return data;
    }

    public Date setData(Date data) {
        this.data = data;
    }
}