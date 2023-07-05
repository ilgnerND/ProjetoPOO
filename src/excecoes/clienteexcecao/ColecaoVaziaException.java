package excecoes.clienteexcecao;

public class ColecaoVaziaException extends Exception {
    public ColecaoVaziaException() {
        super();
    }

    public ColecaoVaziaException(String message) {
        super(message);
    }
}