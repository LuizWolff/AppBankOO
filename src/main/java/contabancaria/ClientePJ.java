package contabancaria;

public class ClientePJ extends Cliente {
    private String cnpj;

    public ClientePJ(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getIdentificador() {
        return cnpj;
    }
}