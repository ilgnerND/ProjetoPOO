package modelos;


/**
 * Classe base Veiculo.
 * @author Ilgner
 */
public abstract class Veiculo {
    protected String placa;
    protected int ano;
    protected double valorDiaria;
    


    // Construtor
    /**
     * @param placa
     * @param ano
     * @param valorDiaria
   
     */
    public Veiculo(String placa, int ano, double valorDiaria) {
        this.placa = placa;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
        

    }

    /**
     * @return
     */
    public String getPlaca() {
        return placa;
    }

    
    /** 
     * @param placa
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

   



    @Override
    public String toString() {
        return "Veiculo [placa= " + placa + ", ano= " + ano + ", valorDiaria= " + valorDiaria +"]";
    }

    

    
    
}