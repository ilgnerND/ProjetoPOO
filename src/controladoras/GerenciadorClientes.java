package controladoras;
import java.util.ArrayList;
import java.util.List;

import excecoes.clienteexcecao.ClienteExistenteException;
import excecoes.clienteexcecao.ClienteNaoEncontradoException;
import excecoes.clienteexcecao.ColecaoVaziaException;
import modelos.Cliente;
/**
 * A classe GerenciadorClientes é responsável por gerenciar uma coleção de clientes.
 */
public class GerenciadorClientes implements IClientes {
    private List<Cliente> clientes;

    public GerenciadorClientes() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public void add(Cliente c) throws ClienteExistenteException {
        if (existe(c.getCpf())) {
            throw new ClienteExistenteException("O cliente já existe na coleção.");
        }
        clientes.add(c);
    }

    @Override
    public Cliente get(long CPF) throws ClienteNaoEncontradoException {
        for (Cliente c : clientes) {
            if (c.getCpf() == CPF) {
                return c;
            }
        }
        throw new ClienteNaoEncontradoException("Cliente não encontrado na coleção.");
    }

    @Override
    public String getInfo(long CPF) throws ClienteNaoEncontradoException {
        Cliente c = get(CPF);
        return c.toString();
    }

    @Override
    public String getInfo() throws ColecaoVaziaException {
        if (clientes.isEmpty()) {
            throw new ColecaoVaziaException("A coleção de clientes está vazia.");
        }
        StringBuilder sb = new StringBuilder();
        for (Cliente c : clientes) {
            sb.append(c.toString()).append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public String getResumoInfo() throws ColecaoVaziaException {
        if (clientes.isEmpty()) {
            throw new ColecaoVaziaException("A coleção de clientes está vazia.");
        }
        StringBuilder sb = new StringBuilder();
        for (Cliente c : clientes) {
            sb.append("CPF: ").append(c.getCpf()).append(" - Nome: ").append(c.getNome()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean set(long CPF, Cliente c) throws ClienteNaoEncontradoException {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf() == CPF) {
                clientes.set(i, c);
                return true;
            }
        }
        throw new ClienteNaoEncontradoException("Cliente não encontrado na coleção.");
    }

    @Override
    public boolean remove(long CPF) throws ClienteNaoEncontradoException {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf() == CPF) {
                clientes.remove(i);
                return true;
            }
        }
        throw new ClienteNaoEncontradoException("Cliente não encontrado na coleção.");
    }

    @Override
    public boolean existe(long CPF) {
        for (Cliente c : clientes) {
            if (c.getCpf() == CPF) {
                return true;
            }
        }
        return false;
    }
}    
