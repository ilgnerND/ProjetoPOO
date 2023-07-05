package controladoras;
import modelos.Locacao;

import excecoes.locacaoexcecao.ColecaoVaziaException;
import excecoes.locacaoexcecao.LocacaoExistenteException;
import excecoes.locacaoexcecao.LocacaoNaoEncontradaException;

/**
 * Interface que permite manipular um conjunto de locações.
 * @author Ilgner
 */
public interface ILocacoes {
    /**
     * Adiciona uma locação na relação de locações.
     * @param l Locação a ser inserido.
     * @throws LocacaoExistenteException
     */
    public void add(Locacao l) throws LocacaoExistenteException;
    
    /**
     * Captura a locação com o codigo informado por parâmetro.
     * @param codigo Codigo do locação a ser capturado.
     * @return Locação com o codigo informado ou null caso o codigo não for encontrado.
     * @throws LocacaoNaoEncontradaException
     */
        

    public Locacao get(int codigo) throws LocacaoNaoEncontradaException;
    
    /**
     * Captura uma String com as informaçoes da locação com o codigo informado por parâmetro.
     * @param codigo Codigo da locação a ser capturada.
     * @return String com as informaçoes da locação com o codigo informado por parâmetro 
     * ou null caso o codigo não for encontrado.
     * @throws LocacaoNaoEncontradaException
     */
    public String getInfo(int codigo) throws LocacaoNaoEncontradaException;
    
    /**
     * Captura uma String com as informaçoes de todas as locações.
     * @return String com as informaçoes de todas as locações
     * ou null caso não exista nenhuma locação.
     * @throws ColecaoVaziaException
     */
    public String getInfo() throws ColecaoVaziaException;
    
    /**
     * Modifica as informações da locação com o codigo informado por parâmetro.
     * @param codigo Codigo da locação a ser modificada.
     * @param l Locação com as modificações.
     * @return True se a locação com o codigo informado por parâmetro foi modificada ou
     * false caso não exista nenhuma locação com o codigo informado.
     * @throws ColecaoVaziaException
     * @throws LocacaoNaoEncontradaException
     */
    public boolean set(int codigo, Locacao l) throws ColecaoVaziaException, LocacaoNaoEncontradaException;
    
    /**
     * Remove a locação com o codigo igual ao informado por parâmetro.
     * @param codigo Codigo da locação a ser capturada.
     * @return True se a locação com o codigo informado por parâmetro foi removida ou
     * false caso não exista nenhuma locação com o codigo informado. 
     * @throws LocacaoNaoEncontradaException
     */
    public boolean remove(int codigo) throws LocacaoNaoEncontradaException;
    
    /**
     * Verifica se existe uma locação com o codigo informado por parâmetro.
     * @param codigo Codigo da locação a ser verificada.
     * @return True se uma locação com o codigo informado for encontrada ou 
     * false caso não exista nenhuma locação com o codigo informado.
     */
    public boolean existe(int codigo);    
}