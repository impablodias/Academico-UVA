#include <stdio.h>
#include <stdbool.h>

#define TAM 9

void imprimirTabuleiro(int matriz[TAM][TAM]) {
    printf("\n === SUDOKU ===\n");
    for (int linha = 0; linha < TAM; linha++) {
        if (linha % 3 == 0 && linha !=0) {
            printf ("--------------------\n");
        }
        for (int coluna =0; coluna < TAM; coluna++) {
            if (coluna % 3 == 0 && coluna != 0) {
                printf(" | ");
            }
            if (matriz[linha][coluna] == 0) {
                printf(". ");
            } else {
                printf("%d ", matriz[linha][coluna]);
            }
        }
        printf("\n");
    }
    printf("\n");
}

bool ehMovimentoValido(int matriz [TAM][TAM], int linha, int coluna, int numero) {
    for (int i = 0; i < TAM; i++) {
        if (matriz[linha][i] == numero) return false;
        if (matriz[i][coluna] == numero) return false;
    }

    int inicioLinha = linha - (linha % 3);
    int inicioColuna = coluna - (coluna % 3);

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (matriz[inicioLinha + i][inicioColuna + j] == numero) {
                return false;
            }
        }
    }
    return true;
}

int main() {
    int tabuleiro [TAM][TAM] = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    int linha, coluna, numero;
    int jogando = 1;

    while (jogando) {
        imprimirTabuleiro(tabuleiro);

        printf ("Digite a linha (0 a 8), a coluna (0 a 8) e o numero (1 a 9) separados pro espaco:\n");
        printf ("Exemplo: 0 2 4 (coloca o numero 4 na linha 0, coluna 2)\n");
        printf ("Sua jogada: ");
        scanf ("%d %d %d", &linha, &coluna, &numero);

        if (linha < 0 || linha > 8 || coluna < 0 || coluna > 8 || numero < 1 || numero > 9) {
            printf("\n[!] Entrada invalida. Use indices de 0 a 8 e numeros de 1 a 9.\n");
            continue;
        }

        if (tabuleiro[linha][coluna] != 0) {
            printf("\n[!] Essa posicao ja esta preenchida!\n");
            continue;
        }

        if (ehMovimentoValido(tabuleiro, linha, coluna, numero)) {
            tabuleiro[linha][coluna] = numero;
            printf("\n[+] Boa jogada\n");
        } else {
            printf("\n[-] Jogada invalida! Esse numero ja existe na linha, coluna ou quadrante.\n");
        }
    }
    return 0;
}