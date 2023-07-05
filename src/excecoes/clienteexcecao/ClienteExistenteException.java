package excecoes.clienteexcecao;

public class ClienteExistenteException extends Exception {
    public ClienteExistenteException() {
        super();
    }

    public ClienteExistenteException(String message) {
        super(message);
    }
}
