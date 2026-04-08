import javax.swing.JOptionPane;

public class Questao14 {
    public static void main(String[] args) {
        String entrada1 = JOptionPane.showInputDialog("Digite o primeiro número:");
        double num1 = Double.parseDouble(entrada1);

        String entrada2 = JOptionPane.showInputDialog("Digite o segundo número (diferente do primeiro):");
        double num2 = Double.parseDouble(entrada2);

        if (num1 < num2) {
            JOptionPane.showMessageDialog(null, "O menor número é: " + num1);
        } else {

            JOptionPane.showMessageDialog(null, "O menor número é: " + num2);
        }
    }
}