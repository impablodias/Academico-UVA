import javax.swing.JOptionPane;

public class Questao01 {
    public static void main(String[] args) {
        String input1 = JOptionPane.showInputDialog("Digite o primeiro número:");
        String input2 = JOptionPane.showInputDialog("Digite o segundo número:");

        double num1 = Double.parseDouble(input1);
        double num2 = Double.parseDouble(input2);

        double soma = num1 + num2;

        String mensagem = "A soma dos números é:\n" + soma;

        JOptionPane.showMessageDialog(null,mensagem);

    }
}