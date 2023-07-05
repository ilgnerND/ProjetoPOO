package controladoras;
import java.util.ArrayList;

import excecoes.locacaoexcecao.ColecaoVaziaException;
import excecoes.locacaoexcecao.LocacaoExistenteException;
import excecoes.locacaoexcecao.LocacaoNaoEncontradaException;
import modelos.Locacao;
/**
 * A classe Locacoes representa uma coleção de locações.
 */
public class Locacoes implements ILocacoes {

    private ArrayList<Locacao> listaLocacoes;

    /**
     * Cria uma instância de Locacoes.
     */
    public Locacoes() {
        listaLocacoes = new ArrayList<>();
    }

    /**
     * Adiciona uma locação à coleção de locações.
     *
     * @param l A locação a ser adicionada.
     * @throws LocacaoExistenteException
     */
    @Override
    public void add(Locacao l) throws LocacaoExistenteException {
        if (l == null) {
        throw new NullPointerException("A locação não pode ser nula.");
    }
    if (existe(l.getCodigo())) {
            throw new LocacaoExistenteException("A locação com o código fornecido já existe na coleção.");
        }
        listaLocacoes.add(l);
    }

    /**
     * Obtém uma locação com base no código fornecido.
     *
     * @param codigo O código da locação a ser obtida.
     * @return A locação com o código correspondente, ou null se não for encontrada.
     * @throws LocacaoNaoEncontradaException
     */
    @Override
    public Locacao get(int codigo) throws LocacaoNaoEncontradaException {
        if (codigo < 0) {
        throw new IllegalArgumentException("O código da locação não pode ser negativo.");
    }
        for (Locacao l : listaLocacoes) {
            if (l.getCodigo() == codigo) {
                return l;
            }
        }
        throw new LocacaoNaoEncontradaException("Locação não encontrado na coleção.");
    }

    /**
     * Obtém as informações de uma locação com base no código fornecido.
     *
     * @param codigo O código da locação.
     * @return Uma representação em string das informações da locação, ou null se a locação não for encontrada.
     */
    @Override
    public String getInfo(int codigo) throws LocacaoNaoEncontradaException{
        for (Locacao l : listaLocacoes) {
            if (l.getCodigo() == codigo) {
                return l.toString();
            }
        }
        throw new LocacaoNaoEncontradaException("Locação não encontrado na coleção.");
    }

    /**
     * Obtém as informações de todas as locações na coleção.
     *
     * @return Uma representação em string das informações de todas as locações na coleção, ou null se a coleção estiver vazia.
     * @throws ColecaoVaziaException
     */
    @Override
    public String getInfo() throws ColecaoVaziaException {
        
        if (listaLocacoes.isEmpty()) {
            throw new ColecaoVaziaException("A locação com o código fornecido não existe na coleção.");
        }
        StringBuilder sb = new StringBuilder();
        for (Locacao l : listaLocacoes) {
            sb.append(l.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Substitui uma locação existente com base no código fornecido.
     *
     * @param codigo O código da locação a ser substituída.
     * @param l      A locação a ser inserida.
     * @return true se a locação foi substituída com sucesso, false caso contrário.
     * @throws ColecaoVaziaException
     * @throws LocacaoNaoEncontradaException
     */
    @Override
    public boolean set(int codigo, Locacao l) throws ColecaoVaziaException, LocacaoNaoEncontradaException {
        if (!existe(codigo)) {
            throw new ColecaoVaziaException("A locação com o código fornecido não existe na coleção.");
        }

        for (int i = 0; i < listaLocacoes.size(); i++) {
            if (listaLocacoes.get(i).getCodigo() == codigo) {
                listaLocacoes.set(i, l);
                return true;
            }
        }
        throw new LocacaoNaoEncontradaException("Locação não encontrado na coleção.");
    }

    /**
     * Remove uma locação com base no código fornecido.
     *
     * @param codigo O código da locação a ser removida.
     * @return true se a locação foi removida com sucesso, false caso contrário.
     * @throws LocacaoNaoEncontradaException
     */
    @Override
    public boolean remove(int codigo) throws LocacaoNaoEncontradaException {
        for (Locacao l : listaLocacoes) {
            if (l.getCodigo() == codigo) {
                listaLocacoes.remove(l);
                return true;
            }
        }
        throw new LocacaoNaoEncontradaException("Locação não encontrado na coleção.");
    }

	/**
     * Verifica se uma locação com o código fornecido existe na coleção
	 *
	* @param codigo O código da locação a ser verificada.
	* @return true se a locação existe na coleção, false caso contrário.
	*/
    @Override
    public boolean existe(int codigo) {
        for (Locacao l : listaLocacoes) {
            if (l.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }
}