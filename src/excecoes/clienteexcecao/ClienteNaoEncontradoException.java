package excecoes.clienteexcecao;

public class ClienteNaoEncontradoException extends Exception {
    public ClienteNaoEncontradoException() {
        super();
    }

    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}