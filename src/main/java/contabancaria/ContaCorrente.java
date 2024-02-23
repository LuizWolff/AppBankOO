package contabancaria;

public class ContaCorrente extends ContaBancaria {

    public ContaCorrente(Cliente cliente, TipoConta tipoConta) {
        super(cliente, tipoConta);
    }

    public String dadosDaConta() {
        return "Cliente: " + getCliente().getNome() + "\nTipo de Conta: " + getTipoConta();
    }
}