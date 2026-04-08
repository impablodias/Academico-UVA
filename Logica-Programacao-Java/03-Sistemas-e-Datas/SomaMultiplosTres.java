public class SomaMultiplosTres {
    public static void main(String[] args) {
        int i = 1;
        int somaTotal = 0;

        while (i <= 100) {
            if (i % 3 == 0) {
                somaTotal += i;
            }
            i++;
        }
        System.out.println("A soma dos múltiplos de 3 entre 1 e 100 é: " + somaTotal);
    }
    
}
