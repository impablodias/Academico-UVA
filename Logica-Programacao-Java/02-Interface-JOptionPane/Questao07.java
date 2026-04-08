import javax.swing.JOptionPane;

public class Questao07 {
    public static void main(String[] args) {
        String entrada = JOptionPane.showInputDialog("Digite a temperatura em graus Celsius (°C):");

        double celsius = Double.parseDouble(entrada);
        double fahrenheit = (9 * celsius + 160) / 5;

        JOptionPane.showMessageDialog(null, 
            celsius + "°C convertidos são: " + fahrenheit + "°F");
    }
}