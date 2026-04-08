import javax.swing.JOptionPane;

public class Questao08 {
    public static void main(String[] args) {
        String entradaCotacao = JOptionPane.showInputDialog("Informe a cotação do dólar hoje (R$):");
        double cotacao = Double.parseDouble(entradaCotacao);

        String entradaDolares = JOptionPane.showInputDialog("Quantos dólares (US$) você deseja converter?");
        double quantidadeDolares = Double.parseDouble(entradaDolares);

        double valorReal = quantidadeDolares * cotacao;

        JOptionPane.showMessageDialog(null, 
            "Com US$ " + quantidadeDolares + " e a cotação de R$ " + cotacao + 
            ", você tem o total de: R$ " + String.format("%.2f", valorReal));
    }
}