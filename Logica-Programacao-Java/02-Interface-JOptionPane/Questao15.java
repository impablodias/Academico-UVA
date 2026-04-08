import javax.swing.JOptionPane;

public class Questao15 {
    public static void main(String[] args) {
        String entrada1 = JOptionPane.showInputDialog("Digite o primeiro número:");
        double num1 = Double.parseDouble(entrada1);

        String entrada2 = JOptionPane.showInputDialog("Digite o segundo número:");
        double num2 = Double.parseDouble(entrada2);

        if (num1 > num2) {
            JOptionPane.showMessageDialog(null, 
                "Ordem Decrescente: " + num1 + " e " + num2);
        } else {
            JOptionPane.showMessageDialog(null, 
                "Ordem Decrescente: " + num2 + " e " + num1);
        }
    }
}