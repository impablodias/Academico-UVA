import javax.swing.JOptionPane;

public class Questao09 {
    public static void main(String[] args) {
        String entrada = JOptionPane.showInputDialog("Digite o valor do depósito (R$):");
        double valorDepositado = Double.parseDouble(entrada);

        double taxaJuros = 0.007;

        double rendimento = valorDepositado * taxaJuros;
        double valorFinal = valorDepositado + rendimento;

        String mensagem = String.format(
            "Resumo do Rendimento após 1 mês:\n" +
            "Valor Principal: R$ %.2f\n" +
            "Rendimento (0,70%%): R$ %.2f\n" +
            "Valor Total Atualizado: R$ %.2f", 
            valorDepositado, rendimento, valorFinal
        );

        JOptionPane.showMessageDialog(null, mensagem);
    }
}