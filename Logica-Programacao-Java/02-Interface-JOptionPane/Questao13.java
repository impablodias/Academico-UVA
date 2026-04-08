import javax.swing.JOptionPane;

public class Questao13 {
    public static void main(String[] args) {
        String entrada = JOptionPane.showInputDialog("Digite um número:");
        double numero = Double.parseDouble(entrada);

        if (numero > 20) {
            JOptionPane.showMessageDialog(null, "O número digitado foi: " + numero);
        }
        
        System.out.println("Programa finalizado.");
    }
}