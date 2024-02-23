package contabancaria;

public abstract class Cliente {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Método abstrato para obter o identificador do cliente
    public abstract String getIdentificador();
}
