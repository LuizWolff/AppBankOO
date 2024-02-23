package contabancaria;

public abstract class Conta {

    protected Cliente cliente;
    protected TipoConta tipoConta;

    public Conta(Cliente cliente, TipoConta tipoConta) {
        this.cliente = cliente;
        this.tipoConta = tipoConta;
    }

    public abstract void abrirConta(Cliente cliente, TipoConta tipoconta);
    public abstract void sacar(double valor);

    public abstract void depositar(double valor);

    public abstract void transferencia(Conta destino, double valor);

    public abstract void investir(double valor);

    public abstract void consultarSaldo();
}

//Abrir conta
//Sacar
//Depositar
//Transferência
//Investir
//Consultar saldo