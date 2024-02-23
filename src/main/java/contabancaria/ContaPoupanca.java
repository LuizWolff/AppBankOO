package contabancaria;

public class ContaPoupanca extends ContaBancaria {

    public ContaPoupanca(Cliente cliente, TipoConta tipoConta) {
        super(cliente, tipoConta);
    }

    public String dadosDaConta() {
        return "Cliente: " + getCliente().getNome() + "\nTipo de Conta: " + getTipoConta();
    }
}
