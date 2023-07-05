package controladoras;
import excecoes.clienteexcecao.ClienteExistenteException;
import excecoes.clienteexcecao.ClienteNaoEncontradoException;
import excecoes.clienteexcecao.ColecaoVaziaException;
import modelos.Cliente;


/**
 * Interface que permite a manipulação de um conjunto de clientes.
 * @author Aline
 */
public interface IClientes {
    
    /**
     * Adiciona um cliente na relação de clientes.
     * @param c Cliente a ser inserido.
     */
    public void add(Cliente c)throws ClienteExistenteException;
    
    /**
     * Captura o cliente com o CPF informado por parâmetro.
     * @param CPF CPF do cliente a ser capturado.
     * @return Cliente com o CPF informado ou null caso o CPF não for encontrado.
     * @throws ClienteNaoEncontradoException
     */
    public Cliente get(long CPF) throws ClienteNaoEncontradoException;
    
    /**
     * Captura uma String com as informaçoes do cliente com CPF informado por parâmetro.
     * @param CPF CPF do cliente a ser capturado.
     * @return String com as informaçoes do cliente com o CPF informado por parâmetro 
     * ou null caso o CPF não for encontrado.
     * @throws ClienteNaoEncontradoException
     */
    public String getInfo(long CPF) throws ClienteNaoEncontradoException;
    
    /**
     * Captura uma String com as informaçoes de todos os clientes.
     * @return String com as informaçoes de todos os clientes
     * ou null caso não exista nenhum cliente.
     * @throws ColecaoVaziaException
     */
    public String getInfo() throws ColecaoVaziaException;
    
    /**
     * Captura uma String com o CPF e o nome de todos os clientes.
     * @return String com o CPF e o nome de todos os clientes
     * ou null caso não exista nenhum cliente.
     * @throws ColecaoVaziaException
     */
    public String getResumoInfo() throws ColecaoVaziaException;
    
    /**
     * Modifica as informações do cliente com o CPF informado por parâmetro.
     * @param CPF CPD do cliente a ser modificado.
     * @param c Cliente com as modificações.
     * @return True se o cliente com o CPF informado por parâmetro for modificado ou
     * false caso não exista nenhum cliente com o CPF informado.
     * @throws ClienteNaoEncontradoException
     */
    public boolean set(long CPF, Cliente c) throws ClienteNaoEncontradoException;
    
    /**
     * Remove o cliente com o CPF igual ao informado por parâmetro.
     * @param CPF CPF do cliente a ser capturado.
     * @return True se o cliente com o CPF informado por parâmetro for removido ou
     * false caso não exista nenhum cliente com o CPF informado. 
     * @throws ClienteNaoEncontradoException
     */
    public boolean remove(long CPF) throws ClienteNaoEncontradoException;
    
    /**
     * Verifica se existe um cliente com o CPF informado por parâmetro.
     * @param CPF CPF do cliente a ser verificado.
     * @return True se um cliente com o CPF informado for encontrado ou 
     * false caso não exista nenhum cliente com o CPF informado.
     */
    public boolean existe(long CPF);
}