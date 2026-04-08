import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Questao05 {
    public static void main(String[] args) {
        String inputNomeAluno = JOptionPane.showInputDialog("Diginte o nome do aluno:");
        String inputNota1 = JOptionPane.showInputDialog("Digite a primeira nota:");
        double nota1 = Double.parseDouble(inputNota1);

        String inputNota2 = JOptionPane.showInputDialog("Digite a segunda nota:");
        double nota2 = Double.parseDouble(inputNota2);

        String inputNota3 = JOptionPane.showInputDialog("Digite a terceira nota:");
        double nota3 = Double.parseDouble(inputNota3);

        double media = (nota1 + nota2 + nota3) / 3;

        DecimalFormat df = new DecimalFormat("0.00");
        String menssagem = "Nome do Aluno: " + inputNomeAluno + "\nMédia: " + df.format(media);

        JOptionPane.showMessageDialog(null, menssagem);
    }
}