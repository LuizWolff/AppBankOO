package contabancaria;

public class ContaInvestimento extends ContaBancaria {

    public ContaInvestimento(Cliente cliente, TipoConta tipoConta) {
        super(cliente, tipoConta);
    }

    public String dadosDaConta() {
        return "Cliente: " + getCliente().getNome() + "\nTipo de Conta: " + getTipoConta();
    }
}