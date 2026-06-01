public class Base {
    private int id;
    private String nome;
    private Double preco;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <=0) {
            System.out.println("[Erro] ID deve ser um número positivo.");
            return;
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("[Erro] O nome não pode ser vazio.");
            return;
        }
        
        if (nome.trim().length() < 3) {
            System.out.println("[Erro] O Nome deve ter pelo menos 3 caracteres.");
            return;
        }
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        if (preco == null || preco <= 0) {
            System.out.println("[Erro] O preço deve ser maior que zero.");
            return;
        }
        this.preco = preco;
}

public String getDescricao() {
    return descricao;
}

public void setDescricao(String descricao) {
    this.descricao = descricao;
}


@Override
public String toString() {
return "ID: "+ id +" | Nome: " + nome + " | Preço: R$ " + preco;
}
}