import javax.swing.JOptionPane;

public class ParOuImpar {
    public static void main(String[] args) {
    int num = Integer.parseInt(JOptionPane.showInputDialog("Digite um número inteiro:"));

    if ( num % 2 == 0) {
        JOptionPane.showMessageDialog(null, "O número " + num + " é PAR.");
    }
    else {
        JOptionPane.showMessageDialog(null, "O número " + num + " é ÍMPAR.");
        }
    }
}
