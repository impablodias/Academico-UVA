import javax.swing.JOptionPane;

public class VerificaNumero {
    public static void main(String[] args) {
        int num = Integer.parseInt(JOptionPane.showInputDialog("Digite um número para verificar se é par ou ímpar:"));

        if (num > 0) {
            JOptionPane.showMessageDialog(null, "O número " + num + " é POSITIVO.");
        }
        else if (num < 0) {
            JOptionPane.showMessageDialog(null, "O número " + num + " é NEGATIVO.");
        }
        else {
            JOptionPane.showMessageDialog(null, "O número é ZERO.");}
        }
        }
