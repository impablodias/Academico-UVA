import javax.swing.JOptionPane;

public class Tabuada {
    public static void main(String[] args) {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite um número para ver a tabuada:"));
        StringBuilder resultado = new StringBuilder("Tabuada do " + numero + ":\n");

        for (int i = 1; i <= 10; i++) {
            resultado.append(numero).append(" x ").append(i).append(" = ").append(numero * i).append("\n");
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }
    
}
