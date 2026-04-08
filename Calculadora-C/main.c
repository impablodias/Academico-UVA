#include <stdio.h>

double somar(double a, double b) {return a + b;}
double subtrair(double a, double b) {return a - b;}
double multiplicar(double a, double b) {return a * b;}
double dividir(double a, double b) {return a / b;}

int main() {
    char operacao;
    double num1, num2, resultado;

    printf("Digite a operação (+, -, *, /): ");
    scanf(" %c", &operacao);

    printf("Digite o primeiro número: ");
    scanf("%lf", &num1);

    printf("Digite o segundo número: ");
    scanf("%lf", &num2);
    
    switch (operacao) {
        case '+': resultado = somar(num1, num2);
        break;
        case '-': resultado = subtrair(num1, num2);
        break;
        case '*': resultado = multiplicar(num1, num2);
        break;
        case '/': if(num2 !=0) {
            resultado = dividir(num1, num2);}
        else {printf("Erro: Divisao por zero!\n"); return 1;}
        break;
        default: printf("Operador invalido!\n"); return 1;
    }

    printf("Resultado: %.2lf\n", resultado);
    return 0;
}