import javax.swing.JOptionPane;

public class Questao10 {
    public static void main(String[] args) {
        String entrada = JOptionPane.showInputDialog("Digite o valor total da compra (R$):");
        
        double valorTotal = Double.parseDouble(entrada);

        double valorPrestacao = valorTotal / 5;

        String mensagem = String.format(
            "Resumo da Venda - Loja Mamão com Açúcar\n\n" +
            "Valor Total: R$ %.2f\n" +
            "Pagamento: 5x de R$ %.2f (Sem Juros)", 
            valorTotal, valorPrestacao
        );

        JOptionPane.showMessageDialog(null, mensagem);
    }
}