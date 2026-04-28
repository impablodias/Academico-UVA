import java.util.ArrayList;
import java.util.Scanner;

class Aluno {
    String nome;
    int idade;
    String sexo;

    public Aluno(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo.toUpperCase();
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Idade: " + idade + " | Sexo: " + sexo;
    }
}

public class BackendAlunosUVA {
    static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    static Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        // Dados para iniciar o sistema
        listaAlunos.add(new Aluno("Jonathan", 25, "M"));
        listaAlunos.add(new Aluno("Augusto", 22, "M"));
        listaAlunos.add(new Aluno("Patrick", 24, "M"));
        listaAlunos.add(new Aluno("Pablo", 28, "M"));

        String opcao = "";
        while (!opcao.equals("0")) {
            System.out.println("\n--- SISTEMA ACADÊMICO (GRUPO: PABLO, JONATHAN, AUGUSTO, PATRICK) ---");
            System.out.println("1. Listar Alunos");
            System.out.println("2. Buscar por Matrícula");
            System.out.println("3. Cadastrar Aluno");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            
            opcao = leitor.nextLine().trim();

            if (opcao.equals("1")) executarListagem();
            else if (opcao.equals("2")) executarBusca();
            else if (opcao.equals("3")) executarCadastro();
        }
    }

    static void executarListagem() {
        System.out.println("\n--- LISTA DE MATRICULADOS ---");
        for (int i = 0; i < listaAlunos.size(); i++) {
            System.out.println("Matrícula " + i + " -> " + listaAlunos.get(i));
        }
    }

    static void executarBusca() {
        System.out.print("\nInforme a Matrícula: ");
        try {
            int m = Integer.parseInt(leitor.nextLine());
            System.out.println("Resultado: " + listaAlunos.get(m));
        } catch (Exception e) {
            System.out.println("Erro: Matrícula inválida ou inexistente.");
        }
    }

    static void executarCadastro() {
        System.out.println("\n--- NOVO CADASTRO ---");
        System.out.print("Nome: ");
        String nome = leitor.nextLine().trim();
        System.out.print("Idade: ");
        String idadeTexto = leitor.nextLine().trim();
        System.out.print("Sexo (M/F): ");
        String sexo = leitor.nextLine().trim().toUpperCase();

        try {
            int idade = Integer.parseInt(idadeTexto);

            // Validações Básicas
            if (nome.length() < 3 || idade < 18 || (!sexo.equals("M") && !sexo.equals("F"))) {
                System.out.println("Erro: Dados inconsistentes (Idade min 18 / Sexo M ou F).");
                return;
            }

            // Validação de Duplicidade
            for (Aluno a : listaAlunos) {
                if (a.nome.equalsIgnoreCase(nome) && a.idade == idade && a.sexo.equals(sexo)) {
                    System.out.println("Erro: Aluno já cadastrado no sistema.");
                    return;
                }
            }

            listaAlunos.add(new Aluno(nome, idade, sexo));
            System.out.println("Sucesso: Aluno matriculado!");

        } catch (Exception e) {
            System.out.println("Erro: A idade deve ser um número.");
        }
    }
}