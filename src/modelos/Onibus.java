package modelos;

// Classe Onibus que herda de Veiculo
public class Onibus extends Veiculo {
    private int numeroPassageiros;
    private String categoria;

    // Construtor
    public Onibus(String placa, int ano, double valorDiaria, int numeroPassageiros, String categoria) {
        super(placa, ano, valorDiaria);
        this.numeroPassageiros = numeroPassageiros;
        this.categoria = categoria;
    }

    
    /** 
     * @return int
     */
    public int getNumeroPassageiros() {
        return numeroPassageiros;
    }

    public void setNumeroPassageiros(int numeroPassageiros) {
        this.numeroPassageiros = numeroPassageiros;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Onibus [placa= " + placa + ", ano= " + ano + ", valorDiaria= " + valorDiaria +" numeroPassageiros=" + numeroPassageiros + ", categoria=" + categoria + "]";
    }  

}