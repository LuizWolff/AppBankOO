package contabancaria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class ContaBancaria {
    public static List<ContaBancaria> contas = new ArrayList<>();
    private double saldo;
    private Cliente cliente;
    private TipoConta tipoConta;

    private double totalInvestido;



    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    private static int gerarNumeroAgencia() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); // Garante que o número gerado tenha 4 dígitos
    }

    public ContaBancaria(Cliente cliente, TipoConta tipoConta) {
        this.cliente = cliente;
        this.tipoConta = tipoConta;
        this.saldo = 0.0; // Inicializa o saldo como zero
    }

    public String dadosDaConta() {
        return "Cliente: " + cliente.toString() + "\nTipo de Conta: " + tipoConta;
    }

    public void mostrarDadosConta(Cliente cliente, TipoConta tipoConta, int numeroAgencia, double saldoInicial) {
        DecimalFormat df = new DecimalFormat("#,##0.00"); // Define o formato desejado

        System.out.println("Conta Criada com sucesso!");
        System.out.println("Segue os dados da conta:\n");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println((cliente instanceof ClientePF ? "CPF" : "CNPJ") + ": " + cliente.getIdentificador());
        System.out.println("Tipo de Conta: " + tipoConta);
        System.out.println((cliente instanceof ClientePF ? "Pessoa Física" : "Pessoa Jurídica"));
        System.out.println("Número da Agência: " + numeroAgencia);
        System.out.println("Saldo Inicial: R$ " + df.format(saldoInicial)); // Formata o saldo inicial
    }

    //========================================================1 METODO - ABRIRCONTA========================================================
    public static void abrirConta(Scanner scanner, List<ContaBancaria> contas) {
        Cliente cliente = null;
        ContaBancaria conta = null; // Inicializa a variável conta como null

        System.out.println("Escolha o tipo de cliente:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        int tipoClienteOpcao = scanner.nextInt();

        // Limpar o buffer do scanner
        scanner.nextLine();

        switch (tipoClienteOpcao) {
            case 1:
                System.out.print("Digite o nome do cliente: ");
                String nomePF = scanner.nextLine();
                System.out.print("Digite o CPF do cliente: ");
                String cpf = scanner.nextLine();
                cliente = new ClientePF(nomePF, cpf);
                System.out.println("Escolha o tipo de conta:");
                System.out.println("1. Conta Poupança");
                System.out.println("2. Conta Investimento");
                System.out.println("3. Conta Corrente");
                break;
            case 2:
                System.out.print("Digite o nome da empresa: ");
                String nomePJ = scanner.nextLine();
                System.out.print("Digite o CNPJ da empresa: ");
                String cnpj = scanner.nextLine();
                cliente = new ClientePJ(nomePJ, cnpj);
                System.out.println("Escolha o tipo de conta:");
                System.out.println("1. Conta Poupança");
                System.out.println("2. Conta Investimento");
                System.out.println("3. Conta Corrente");
                break;
            default:
                System.out.println("Opção de tipo de cliente inválida.");
                return; // Retorna sem criar a conta se a opção for inválida
        }

        int tipoContaOpcao = scanner.nextInt();

        // Limpar o buffer do scanner
        scanner.nextLine();

        TipoConta tipoConta = null;
        switch (tipoContaOpcao) {
            case 1:
                tipoConta = TipoConta.POUPANCA;
                break;
            case 2:
                tipoConta = TipoConta.INVESTIMENTO;
                break;
            case 3:
                tipoConta = TipoConta.CORRENTE;
                break;
            default:
                System.out.println("Opção de tipo de conta inválida.");
                return; // Retorna sem criar a conta se a opção for inválida
        }

        // Solicitar o saldo inicial
        System.out.print("Digite o saldo inicial: ");
        double saldoInicial = scanner.nextDouble();

        // Criar a conta com saldo inicial
        switch (tipoConta) {
            case POUPANCA:
                conta = new ContaPoupanca(cliente, tipoConta);
                break;
            case INVESTIMENTO:
                conta = new ContaInvestimento(cliente, tipoConta);
                break;
            case CORRENTE:
                conta = new ContaCorrente(cliente, tipoConta);
                break;
            default:
                System.out.println("Tipo de conta inválido.");
                return;
        }

        // Depositar o saldo inicial na conta
        if (conta != null) {
            conta.depositar(saldoInicial);
            contas.add(conta); // Adiciona a conta ao ArrayList
            System.out.println("Conta Criada com sucesso!\nSegue os dados da conta:\n" + conta.dadosDaConta());
            conta.mostrarDadosConta(cliente, tipoConta, gerarNumeroAgencia(), saldoInicial); // Mostra os dados da conta
        }
    }
    //========================================================2 METODO - SACAR========================================================
    public void sacar(double valor) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        if (valor > 0 && valor <= saldo) {
            if (cliente instanceof ClientePJ) {
                // Se for cliente PJ, aplica a taxa de 0.5% sobre o saque
                double taxa = valor * 0.005;
                double valorTotal = valor + taxa;
                saldo -= valorTotal;
                if (valorTotal <= saldo) {
                    System.out.println("Taxa de 0.5% aplicada para clientes PJ: R$ " + df.format(taxa));
                    System.out.println("Saque realizado com sucesso. Novo saldo: R$ " + df.format(saldo));
                } else {
                    System.out.println("Saldo insuficiente para realizar o saque.");
                }
            } else if (cliente instanceof ClientePF) {
                // Se for cliente PF, não sera aplicado taxa sobre o saque
                saldo -= valor;
                if (valor > 0 && valor <= saldo) {
                    System.out.println("Saque realizado com sucesso. Novo saldo: R$ " + df.format(saldo));
                } else {
                    System.out.println("Saldo insuficiente para realizar o saque.");
                }
            }
        } else {
            System.out.println("Valor de saque inválido ou saldo insuficiente.");
        }
    }
    //========================================================3 METODO - DEPOSITAR========================================================
    public void depositar(double valor) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso. Novo saldo: R$ " + df.format(saldo));
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }
    //========================================================4 METODO - TRANSFERENCIA========================================================
    public void transferencia(double valor, int numeroAgenciaDestino) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        if (valor > 0 && valor <= saldo) {
            if (cliente instanceof ClientePJ) {
                // Se for cliente PJ, aplica a taxa de 0.5% sobre o saque
                double taxa = valor * 0.005;
                valor += taxa;
                System.out.println("Taxa de 0.5% aplicada para clientes PJ: R$ " + df.format(taxa));
                System.out.println("Transferência realizada com sucesso para a agência: " + numeroAgenciaDestino + ". Novo saldo: R$ " + df.format(saldo));
                saldo -= valor;
            } else if (cliente instanceof ClientePF) {
                saldo -= valor; // Se for cliente PF, não sera aplicado taxa sobre o saque
                System.out.println("Transferência com sucesso para a agência: " + numeroAgenciaDestino + "\nNovo saldo: R$ " + df.format(saldo));
            }
        } else {
            System.out.println("Saldo insuficiente para realizar a transferência ou valor de transferência inválido.");
        }
    }

    public void transferencia(int numeroContaDestino, double valorTransferencia, List<ContaBancaria> contas) {
        // Implementação do método de transferência entre contas
    }

    //========================================================5 METODO - INVESTIR========================================================
    public void investir(double valorInvestimento) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        if (valorInvestimento > 0 && valorInvestimento <= saldo) {
            System.out.println("Investimento realizado com sucesso. Valor investido: R$ " + df.format(valorInvestimento));
            saldo -= valorInvestimento;
            totalInvestido += valorInvestimento; // Adiciona o valor do novo investimento ao total investido
            System.out.println("Novo saldo: R$ " + df.format(saldo));
            System.out.println("Total investido: R$ " + df.format(totalInvestido));
        } else {
            System.out.println("Saldo insuficiente para realizar o investimento ou valor de investimento inválido.");
        }
    }
    //========================================================6 METODO - CONSULTAR SALDO========================================================
    public String getSaldoFormatado() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(this.saldo);
    }

}
