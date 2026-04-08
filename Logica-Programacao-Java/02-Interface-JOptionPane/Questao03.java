import javax.swing.JOptionPane;

public class Questao03 {
    public static void main(String[] args) {
        String inputDistancia = JOptionPane.showInputDialog("Digite a distância pecorrida em km:");
        String InputCombustivel = JOptionPane.showInputDialog("Digite o total de combustível gastos em litros:");

        double distancia = Double.parseDouble(inputDistancia);
        double combustivel = Double.parseDouble(InputCombustivel);

        double consumoMedio = distancia / combustivel;

        String mensagem = "O consumo médio do veículo é: " + consumoMedio + "km/L";

        JOptionPane.showMessageDialog(null, mensagem);
    }
}