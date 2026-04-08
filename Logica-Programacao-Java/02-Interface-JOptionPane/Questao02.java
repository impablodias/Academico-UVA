import javax.swing.JOptionPane;

public class Questao02 {
    public static void main(String[] args) {
        String input1 = JOptionPane.showInputDialog("Digite o primeiro número:");
        String input2 = JOptionPane.showInputDialog("Digite o segundo número:");

        double num1 = Double.parseDouble(input1);
        double num2 = Double.parseDouble(input2);

        double soma = num1 + num2;
        double subtracao = num1 - num2;
        double multiplicacao = num1 * num2;
        double divisao = num1 / num2;

        String mensagem = "Resultados das operações:\n" +
                            "Soma: " + soma + "\n" +
                            "Subtração: " + subtracao + "\n" +
                            "Multiplicação: " + multiplicacao + "\n" +
                            "Divisão: " + divisao + "\n";

        JOptionPane.showMessageDialog(null, mensagem);

    }
}