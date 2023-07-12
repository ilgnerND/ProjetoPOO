package modelos;
public class Cliente {
    private String nome;
    private long cpf;
    private String numCarteiraMotorista;
    private String endereco;
    private String telefone;

     public Cliente(String nome, long cpf, String numCarteiraMotorista, String endereco, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.numCarteiraMotorista = numCarteiraMotorista;
        this.endereco = endereco;
        this.telefone = telefone;
    } 

    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNumCarteiraMotorista() {
        return numCarteiraMotorista;
    }

    public void setNumCarteiraMotorista(String numCarteiraMotorista) {
        this.numCarteiraMotorista = numCarteiraMotorista;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    @Override
    public String toString() {
        return "Cliente : \n" + //
                "nome=" + nome + ",\n" + //
                        " cpf=" + cpf + ",\n" + //
                                " numCarteiraMotorista=" + numCarteiraMotorista
                + ",\n" + //
                        " endereco=" + endereco + ",\n" + //
                                " telefone=" + telefone + "!";
    }

    
}
