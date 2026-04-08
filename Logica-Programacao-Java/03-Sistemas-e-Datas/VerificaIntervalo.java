import javax.swing.JOptionPane;

public class VerificaIntervalo {
    public static void main(String[] args) {
        int num = Integer.parseInt(JOptionPane.showInputDialog("Digite um número:"));

        if (num >= 10 && num <= 100) {
            JOptionPane.showMessageDialog(null, "O número " + num + " está dentro do intervalo de 10 a 100.");
        }
        else {
            JOptionPane.showMessageDialog(null, "O número " + num + " está fora do intervalo de 10 a 100.");
        }
    }
    
}
