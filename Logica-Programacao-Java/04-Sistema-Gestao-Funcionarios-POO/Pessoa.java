import java.sql.Date;

public class Pessoa {
    protected int idpessoas;
    protected String nome;
    protected String sexo;
    protected String endereco;
    protected Date nascimento;
    protected String nacionalidade;
    protected int telefone;
    protected int celular;
    protected String email;
    protected String foto;


    public int getIdPessoas(){
        return idpessoas;
    } 

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public int getTelefone() {
        return telefone;
    }

    public int getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getFoto() {
        return foto;    }

    public void setIdPessoas(int idpessoas) {
        this.idpessoas = idpessoas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setfoto( String foto) {
        this.foto = foto;
    }
}