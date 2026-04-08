import javax.swing.JOptionPane;

public class Questao12 {
    public static void main(String[] args) {
        String entrada = JOptionPane.showInputDialog("Digite o valor do raio do círculo:");
        double raio = Double.parseDouble(entrada);

        double area = Math.PI * Math.pow(raio, 2);

        String mensagem = String.format(
            "Cálculo da Área do Círculo:\n" +
            "Raio informado: %.2f\n" +
            "Área total: %.4f", 
            raio, area
        );

        JOptionPane.showMessageDialog(null, mensagem);
    }
}