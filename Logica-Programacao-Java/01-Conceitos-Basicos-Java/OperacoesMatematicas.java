import java.util.Scanner;

public class OperacoesMatematicas {

    public static void main(String[] args) {
        Scanner leitor = new Scanner (System.in);

        System.out.print ("Digite o primeiro número: ");
        double num1 = leitor.nextDouble();

        System.out.print ("Digite o segundo número: ");
        double num2 = leitor.nextDouble();

        double soma = num1 + num2;
        double subtracao = num1 - num2;
        double multiplicacao = num1 * num2;

        System.out.println("A soma dos dois números é: " + soma);
        System.out.println("A subtração dos dois números é: " + subtracao);
        System.out.println("A multiplicação dos dois números é: " + multiplicacao);

        if (num2 != 0) {
            double divisao = num1 / num2;
            System.out.println("A divisão dos dois números é: " + divisao);
        } else {
            System.out.println("Não é possível dividir por zero.");
        }

        leitor.close();
    }
}
