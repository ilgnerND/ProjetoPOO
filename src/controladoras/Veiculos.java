package controladoras;
import java.util.ArrayList;
import java.util.List;

import modelos.Veiculo;
import excecoes.veiculosececao.VeiculoExistenteException;
import excecoes.veiculosececao.VeiculoNaoEncontradoException;
import excecoes.veiculosececao.ColecaoVaziaException;
/**
 * A classe Veiculos representa uma coleção de veículos.
 */
public class Veiculos implements IVeiculos {

    private List<Veiculo> veiculos;

    /**
     * Construtor padrão da classe Veiculos.
     * Inicializa a lista de veículos.
     */
    public Veiculos() {
        veiculos = new ArrayList<>();
    }

    
    /** 
     * @param v
     * @throws VeiculoExistenteException
     */
    @Override
    public void add(Veiculo v) throws VeiculoExistenteException {
        if (existe(v.getPlaca())) {
            throw new VeiculoExistenteException("O veículo já existe na coleção.");
        }
        veiculos.add(v);
    }

    @Override
    public Veiculo get(String placa) throws VeiculoNaoEncontradoException {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        throw new VeiculoNaoEncontradoException("Veículo não encontrado na coleção.");
    }

    @Override
    public String getInfo(String placa) throws VeiculoNaoEncontradoException {
        Veiculo v = get(placa);
        return v.toString();
    }

    @Override
    public String getInfo() throws ColecaoVaziaException {
        if (veiculos.isEmpty()) {
            throw new ColecaoVaziaException("A coleção de veículos está vazia.");
        }
        StringBuilder sb = new StringBuilder();
        for (Veiculo v : veiculos) {
            sb.append(v.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String getResumoInfo() throws ColecaoVaziaException {
        if (veiculos.isEmpty()) {
            throw new ColecaoVaziaException("A coleção de veículos está vazia.");
        }
        StringBuilder sb = new StringBuilder();
        for (Veiculo v : veiculos) {
            sb.append(v.getPlaca());
            sb.append(", ");
            sb.append(v.getAno());
            sb.append(", ");
            sb.append(v.getValorDiaria());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean set(String placa, Veiculo v) throws VeiculoNaoEncontradoException {
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo current = veiculos.get(i);
            if (current.getPlaca().equals(placa)) {
                veiculos.set(i, v);
                return true;
            }
        }
        throw new VeiculoNaoEncontradoException("Veículo não encontrado na coleção.");
    }

    @Override
    public boolean remove(String placa) throws VeiculoNaoEncontradoException {
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo current = veiculos.get(i);
            if (current.getPlaca().equals(placa)) {
                veiculos.remove(i);
                return true;
            }
        }
        throw new VeiculoNaoEncontradoException("Veículo não encontrado na coleção.");
    }

    @Override
    public boolean existe(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }
}
