package modelos;
public class Carro extends Veiculo {
    private double mediaKmPorLitro;
    private String arCondicionado;

    // Construtor
    public Carro(String placa, int ano, double valorDiaria, double mediaKmPorLitro, String arCondicionado) {
        super(placa, ano, valorDiaria);
        this.mediaKmPorLitro = mediaKmPorLitro;
        this.arCondicionado = arCondicionado;
    }

    public double getMediaKmPorLitro() {
        return mediaKmPorLitro;
    }

    public void setMediaKmPorLitro(double mediaKmPorLitro) {
        this.mediaKmPorLitro = mediaKmPorLitro;
    }

    public String isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(String arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    @Override
    public String toString() {
        return "Carro [placa= " + placa + ", ano= " + ano + ", valorDiaria= " + valorDiaria +" mediaKmPorLitro=" + mediaKmPorLitro + ", arCondicionado=" + arCondicionado + "]";
    }

    
}
