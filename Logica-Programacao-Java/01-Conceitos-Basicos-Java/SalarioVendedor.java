import java.util.Scanner;

public class SalarioVendedor {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        System.out.print("Digite o salário fixo do vendedor: ");
        double salarioFixo = leitor.nextDouble();

        System.out.print("Digite o total de vendas realizadas pelo vendedor: ");
        double totalVendas = leitor.nextDouble();

        double comissao = totalVendas * 0.15; 
        double salarioFinal = salarioFixo + comissao;

        System.out.printf("O salário final do vendedor é: R$ %.2f%n", salarioFinal);

        leitor.close();
    }
}