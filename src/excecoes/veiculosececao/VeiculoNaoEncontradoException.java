package excecoes.veiculosececao;

public class VeiculoNaoEncontradoException extends Exception {
    public VeiculoNaoEncontradoException() {
        super();
    }

    public VeiculoNaoEncontradoException(String message) {
        super(message);
    }
}