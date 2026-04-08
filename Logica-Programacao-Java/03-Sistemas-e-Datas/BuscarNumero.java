public class BuscarNumero {
    public static void main(String[] args) {
        int i = 1;

        while (i <= 100) {

            if (i % 5 == 0 && i % 7 == 0) {
                System.out.println("O Primeiro número é: " + i);

                break;
            }

            i++;
        }
    }
}