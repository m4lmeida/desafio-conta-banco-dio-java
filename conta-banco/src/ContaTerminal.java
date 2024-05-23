import java.util.Locale;
import java.util.Scanner;

public class ContaTerminal {

    private static final String AGENCIA = "186-9";
    private static final int CONTA = 20528;
    private static double saldo = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in).useLocale(Locale.US)) {
            criarConta(scanner);
        }
    }

    private static void criarConta(Scanner scanner) {
        System.out.println("Bem-vindo ao banco! Por favor, siga as instruções para criar sua conta.");

        System.out.println("Digite o seu nome:");
        String nome = scanner.nextLine();

        System.out.println("Digite seu sobrenome:");
        String sobrenome = scanner.nextLine();

        String cpf;
        do {
            System.out.println("Digite o seu CPF (somente números):");
            cpf = scanner.nextLine();
            if (!cpf.matches("\\d{11}")) {
                System.out.println("CPF inválido. Por favor, insira um CPF válido (11 dígitos).");
            }
        } while (!cpf.matches("\\d{11}"));

        System.out.println("Qual o seu DDD + número de celular? (somente números):");
        String telefone = scanner.nextLine();
        while (!telefone.matches("\\d{10,11}")) {
            System.out.println(
                    "Número de telefone inválido. Por favor, insira um número de telefone válido (10 ou 11 dígitos):");
            telefone = scanner.nextLine();
        }

        System.out.println("Aguarde um momento....");
        System.out.println("Estamos quase lá....");

        System.out.println("Para concluir, qual será o valor do seu primeiro depósito?");
        saldo = Double.parseDouble(scanner.nextLine());

        confirmarDados(nome, sobrenome, cpf, telefone, saldo);
    }

    private static void confirmarDados(String nome, String sobrenome, String cpf, String telefone, double saldo) {
        System.out.println("\nPor favor, confirme os dados inseridos:");
        System.out.println("Nome: " + nome + " " + sobrenome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Valor do depósito: R$ " + String.format("%.2f", saldo));

        System.out.println("\nOs dados estão corretos? (s/n)");
        try (Scanner scanner = new Scanner(System.in)) {
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("s")) {
                System.out.println("Dados confirmados! Obrigado por criar uma conta em nosso banco. Sua agência é "
                        + AGENCIA + ", conta " + CONTA + ", seu saldo é de R$ "
                        + String.format("%.2f", saldo) + " e já está disponível para saque!");
                acessarConta();
            } else {
                System.out.println("Por favor, corrija os dados e tente novamente.");
            }
        }
    }

    private static void acessarConta() {
        System.out.println("\nAcesso à conta:");
        System.out.println("1. Ver saldo");
        System.out.println("2. Fazer depósito");
        System.out.println("3. Fazer saque");
        System.out.println("4. Sair");

        try (Scanner scanner = new Scanner(System.in).useLocale(Locale.US)) {
            boolean sair = false;
            while (!sair) {
                System.out.println("Escolha uma opção:");
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        verSaldo();
                        break;
                    case 2:
                        fazerDeposito(scanner);
                        break;
                    case 3:
                        fazerSaque(scanner);
                        break;
                    case 4:
                        sair = true;
                        System.out.println("Obrigado por utilizar nosso banco!");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            }
        }
    }

    private static void verSaldo() {
        System.out.println("Seu saldo atual é R$ " + String.format("%.2f", saldo));
    }

    private static void fazerDeposito(Scanner scanner) {
        System.out.println("Digite o valor que deseja depositar:");
        double valor = Double.parseDouble(scanner.nextLine());
        saldo += valor;
        System.out.println("Depósito realizado com sucesso! Seu novo saldo é R$ " + String.format("%.2f", saldo));
    }

    private static void fazerSaque(Scanner scanner) {
        System.out.println("Digite o valor que deseja sacar:");
        double valor = Double.parseDouble(scanner.nextLine());
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso! Seu novo saldo é R$ " + String.format("%.2f", saldo));
        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
        }
    }
}
