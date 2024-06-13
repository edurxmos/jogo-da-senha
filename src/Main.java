import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] senhaComp = new int[4];
        int[] senhaUsu = new int[4];
        menu();
        gerarSenha(senhaComp);
        jogo(senhaUsu, senhaComp, sc);

    }

    public static void gerarSenha(int[] v) {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            v[i] = random.nextInt(6) + 1;
        }
    }

    public static void jogo(int[] senhaUsu, int[] senhaComp, Scanner sc) {
        int tentativas = 0;
        boolean venceu = false;

        while (tentativas < 10 && !venceu) {
            boolean[] corretos = {false, false, false, false};
            boolean[] verificacaoComp = {false, false, false, false};
            boolean[] verificacaoUsu = {false, false, false, false};

            usuario(senhaUsu, sc);
            tentativas++;

            int c = 0;
            for (int i = 0; i < 4; i++) {
                if (senhaUsu[i] == senhaComp[i]) {
                    corretos[i] = true;
                    verificacaoComp[i] = true;
                    c++;
                }
            }

            int deslocados = 0;
            for (int i = 0; i < 4; i++) {
                if (!corretos[i] && !verificacaoUsu[i]) {
                    for (int j = 0; j < 4; j++) {
                        if (i != j && senhaUsu[i] == senhaComp[j] && !verificacaoComp[j]) {
                            deslocados++;
                            verificacaoComp[j] = true;
                            verificacaoUsu[i] = true;
                        }
                    }
                }
            }

            if (c == 4) {
                venceu = true;
            } else if (tentativas < 10) {
                feedback(senhaUsu, c, tentativas, deslocados);
            }

            if (venceu) {
                System.out.println("PARABENS! Voce VENCEU depois de " + tentativas + " tentativas.");
            } else if (tentativas == 10) {
                System.out.println("Voce PERDEU! O computador ganhou.");
            }

        }

    }

    public static void usuario(int[] senhaUsu, Scanner sc) {
        for (int i = 0; i < 4; i++) {
            System.out.print("- ");
            int digito = sc.nextInt();
            while (digito < 1 || digito > 6) {
                System.out.println("[ERRO] Apenas numeros de 1 a 6");
                System.out.println("O digito nao foi computado.");
                System.out.print("- ");
                digito = sc.nextInt();
            }
            senhaUsu[i] = digito;
        }
    }

    public static void feedback(int[] senhaUsu, int c, int tentativas, int deslocados) {
        System.out.println("ERROU! Voce tem mais " + (10 - tentativas) + " tentativas.");
        System.out.print("Tentativa " + tentativas + ": ");

        for (int i = 0; i < 4; i++) {
            System.out.print(senhaUsu[i] + " ");
        }

        System.out.println();
        System.out.println("Digitos corretos: " + c);
        System.out.println("Digitos deslocados: " + deslocados);
    }

    public static void menu() {
        System.out.println("Trabalho Pratico Jogo da Senha");
        System.out.println("Disciplina: Logica de Programacao e Algoritmos");
        System.out.println("Prof: Christianne Orrico Dalforno");
        System.out.println();
        System.out.println("Integrantes da Equipe: Alanderson, Celso, Daniel, Eduardo");
        System.out.println();
        System.out.println("Jogo da Senha");
        System.out.println("Digite os numeros separados por espaco, exemplo: - 1 2 3 4");
        System.out.println("ou se preferir, digite um numero de cada vez.");
        System.out.println("===============================================================");
    }

}
