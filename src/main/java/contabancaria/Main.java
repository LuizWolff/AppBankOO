package contabancaria;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContaBancaria conta = null;
        System.out.println("Bem-vindo ao AppBank");

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===================MENU APP BANK===================");
            System.out.println("1. Abrir conta");
            System.out.println("2. Sacar");
            System.out.println("3. Depositar");
            System.out.println("4. Transferência");
            System.out.println("5. Investir");
            System.out.println("6. Consultar saldo");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    if (conta == null) {
                        ContaBancaria.abrirConta(scanner, ContaBancaria.contas);
                        if (!ContaBancaria.contas.isEmpty()) {
                            conta = ContaBancaria.contas.get(ContaBancaria.contas.size() - 1);
                        }
                    } else {
                        System.out.println("Você já possui uma conta aberta.");
                    }
                    break;
                case 2:
                    if (conta != null) {
                        try {
                            System.out.print("Digite o valor que deseja sacar: ");
                            double valorSaque = scanner.nextDouble();
                            conta.sacar(valorSaque);
                        } catch (InputMismatchException e) {
                            System.out.println("Valor inválido. Certifique-se de inserir um número válido.");
                            scanner.nextLine(); // Limpar o buffer do Scanner
                        }
                    } else {
                        System.out.println("Você não possui uma conta aberta para realizar o saque.");
                    }
                    break;
                case 3:
                    if (conta != null) {
                        try {
                            System.out.print("Digite o valor a ser depositado: ");
                            double valorDeposito = scanner.nextDouble();
                            conta.depositar(valorDeposito);
                        } catch (InputMismatchException e) {
                            System.out.println("Valor inválido. Certifique-se de inserir um número válido.");
                            scanner.nextLine(); // Limpar o buffer do Scanner
                        }
                    } else {
                        System.out.println("Você não possui uma conta aberta para realizar o depósito.");
                    }
                    break;
                case 4:
                    if (conta != null) {
                        System.out.print("Digite o número da agência de destino: ");
                        int numeroAgenciaDestino;
                        try {
                            numeroAgenciaDestino = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Número de agência inválido. Certifique-se de inserir um número válido.");
                            scanner.nextLine(); // Limpar o buffer do Scanner
                            break;
                        }
                        System.out.print("Digite o valor para transferência: ");
                        double valorTransferencia;
                        try {
                            valorTransferencia = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("Valor de transferência inválido. Certifique-se de inserir um número válido.");
                            scanner.nextLine(); // Limpar o buffer do Scanner
                            break;
                        }
                        conta.transferencia(valorTransferencia, numeroAgenciaDestino);
                    } else {
                        System.out.println("Você não possui uma conta aberta para realizar a transferência.");
                    }
                    break;
                case 5:
                    if (conta != null) {
                        System.out.print("Digite o valor a ser investido: ");
                        double valorInvestimento;
                        try {
                            valorInvestimento = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("Valor de investimento inválido. Certifique-se de inserir um número válido.");
                            scanner.nextLine(); // Limpar o buffer do Scanner
                            break;
                        }
                        conta.investir(valorInvestimento);
                    } else {
                        System.out.println("Você não possui uma conta aberta para realizar o investimento.");
                    }
                    break;
                case 6:
                    if (conta != null) {
                        System.out.println("Saldo atual: R$ " + conta.getSaldoFormatado());
                    } else {
                        System.out.println("Você não possui uma conta aberta para consultar o saldo.");
                    }
                    break;
                case 0:
                    System.out.println("Obrigado por utilizar o AppBank. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
        scanner.close();
    }
}