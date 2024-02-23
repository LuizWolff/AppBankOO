package contabancaria;

public class ClientePF extends Cliente {
    private String cpf;

    public ClientePF(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getIdentificador() {
        return cpf;
    }
}


