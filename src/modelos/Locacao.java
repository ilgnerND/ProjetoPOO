package modelos;

import java.time.LocalDate;

public class Locacao {
    private int codigo;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private static int proximoCodigo = 1;

    public Locacao(int codigo, Cliente cliente, Veiculo veiculo, LocalDate dataInicial, LocalDate dataFinal) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate datinicio, LocalDate datfinal) {
    this.codigo = proximoCodigo++;
    this.cliente = cliente;
    this.veiculo = veiculo;
    this.dataInicial = datinicio;
    this.dataFinal = datfinal;
}

    
    /** 
     * @return int
     */
    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Override
    public String toString() {
        return "Locacao [codigo=" + codigo + ", cliente=" + cliente + ", veiculo=" + veiculo + ", dataInicial="
                + dataInicial + ", dataFinal=" + dataFinal + "]";
    }
}
