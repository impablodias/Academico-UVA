import java.util.ArrayList;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Base> listaProdutos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JOptionPane.showMessageDialog(null, "Cadastro do produto " + (i + 1));
            cadastrarProduto(listaProdutos);
        }

        while (true) {

            while (true) {
                int resposta = JOptionPane.showConfirmDialog(null,
                    "Deseja cadastrar mais um produto?",
                    "Cadastro",
                    JOptionPane.YES_NO_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {
                    cadastrarProduto(listaProdutos);
                } else {
                    break;
                }
            }
            while (true) {
                String idsDisponiveis = "IDs cadastrados:\n";
                for (Base b : listaProdutos) {
                    idsDisponiveis += "→ ID " + b.getId() + " | " + b.getNome() + "\n";
                }

                String input = JOptionPane.showInputDialog(
                    idsDisponiveis + "\nDigite o ID para consultar\n(ou cancele para sair):"
                );

                if (input == null) break;

                int idBusca = Integer.parseInt(input);
                boolean encontrou = false;

                for (Base b : listaProdutos) {
                    if (b.getId() == idBusca) {
                        JOptionPane.showMessageDialog(null,
                            "200 OK — Produto encontrado!\n\n" +
                            "ID: "        + b.getId()        + "\n" +
                            "Nome: "      + b.getNome()       + "\n" +
                            "Preco: R$"   + b.getPreco()      + "\n" +
                            "Descricao: " + b.getDescricao());
                        encontrou = true;
                        break;
                    }
                }
if (!encontrou) {
                    JOptionPane.showMessageDialog(null,
                        "404 Not Found\nProduto com ID " + idBusca + " não encontrado!");
                }
            }
            int continuar = JOptionPane.showConfirmDialog(null,
                "Deseja cadastrar novos produtos?",
                "Continuar",
                JOptionPane.YES_NO_OPTION);

            if (continuar == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Sistema encerrado!");
                break;
            }
        }
    }

    static void cadastrarProduto(ArrayList<Base> listaProdutos) throws Exception {
        Base base = new Base();

        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID: "));
        base.setId(id);

        String nome = JOptionPane.showInputDialog("Digite o nome do produto: ");
        base.setNome(nome);

        Double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco: "));
        base.setPreco(preco);

        String descricao = JOptionPane.showInputDialog("Digite a descricao: ");
        base.setDescricao(descricao);

        listaProdutos.add(base);

        JOptionPane.showMessageDialog(null,
            "Produto cadastrado!\n" +
            "ID: "        + base.getId()        + "\n" +
            "Nome: "      + base.getNome()       + "\n" +
            "Preco: R$"   + base.getPreco()      + "\n" +
            "Descricao: " + base.getDescricao());
    }
}