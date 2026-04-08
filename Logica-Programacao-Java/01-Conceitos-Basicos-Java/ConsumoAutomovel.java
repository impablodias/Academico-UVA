import java.util.Scanner;

public class ConsumoAutomovel {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        System.out.print("Digite a distância total percorrida (km): ");
        double distancia = leitor.nextDouble();

        System.out.print("Digite o total de combustível gasto (litros): ");
        double combustivel = leitor.nextDouble();

        if (combustivel > 0) {
            double consumoMedio = distancia / combustivel;
            
            System.out.println("--- Relatório de Consumo ---");
            System.out.printf("O consumo médio do automóvel é de: %.2f km/l%n", consumoMedio);
        } else {
            System.out.println("Erro: O combustível gasto deve ser maior que zero.");
        }

        leitor.close();
    }
}