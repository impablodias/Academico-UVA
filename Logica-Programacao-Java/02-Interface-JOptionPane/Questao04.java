import javax.swing.JOptionPane;

public class Questao04 {
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite o nome do vendedor:");
        String inputSalarioFixo = JOptionPane.showInputDialog("Digite o salário fixo (Ex: 1.500,00)");
        String inputTotalVendas = JOptionPane.showInputDialog("Digite o total de vendas do mês (Ex: 5.000,00)");

        double salarioFixo = Double.parseDouble(inputSalarioFixo);
        double totalVendas = Double.parseDouble(inputTotalVendas);

        double comissao = totalVendas * 0.15;
        double salarioFinal = salarioFixo + comissao;

        String mensagem = "Vendedor: " + nome + "\n" +
                            "Salário Fixo: R$ " + salarioFixo + "\n" +
                            "Total de Vendas: R$ " + totalVendas + "\n" +
                            "Comissão (15%): R$ " + comissao + "\n" +
                            "Salário Final: R$ " + salarioFinal;

        JOptionPane.showMessageDialog(null, mensagem);
    }
}