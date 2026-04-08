import javax.swing.JOptionPane;

public class Questao11 {
    public static void main(String[] args) {
        String entradaCusto = JOptionPane.showInputDialog("Digite o preço de custo do produto (R$):");
        double precoCusto = Double.parseDouble(entradaCusto);

        String entradaPercentual = JOptionPane.showInputDialog("Digite o percentual de acréscimo (%):");
        double percentual = Double.parseDouble(entradaPercentual);

        double valorAcrescimo = precoCusto * (percentual / 100);
        double precoVenda = precoCusto + valorAcrescimo;

        String mensagem = String.format(
            "Cálculo de Preço de Venda:\n" +
            "Preço de Custo: R$ %.2f\n" +
            "Margem de Lucro: R$ %.2f\n" +
            "Valor Final de Venda: R$ %.2f",
            precoCusto, percentual, valorAcrescimo, precoVenda
        );

        JOptionPane.showMessageDialog(null, mensagem);
    }
}