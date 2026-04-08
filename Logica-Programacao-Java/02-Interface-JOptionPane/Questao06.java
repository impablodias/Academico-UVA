import javax.swing.JOptionPane;

public class Questao06 {
    public static void main(String[] args) {
        String valorA = JOptionPane.showInputDialog("Digite o valor da variável A:");
        String valorB = JOptionPane.showInputDialog("Digite o valor da variável B:");

        JOptionPane.showMessageDialog(null, "Antes da troca:\nA = " + valorA + "\nB = " + valorB);

        String auxiliar = valorA;
        valorA = valorB;
        valorB = auxiliar;

        JOptionPane.showMessageDialog(null, "Valores trocados com sucesso:\nA = " + valorA + "\nB = " + valorB);
    }
}