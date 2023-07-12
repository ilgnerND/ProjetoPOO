package consoles;
import excecoes.clienteexcecao.ClienteNaoEncontradoException;
import excecoes.locacaoexcecao.LocacaoInvalidaException;
import excecoes.veiculosececao.VeiculoNaoEncontradoException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelos.Cliente;
import modelos.Locacao;
import modelos.Veiculo;
import controladoras.GerenciadorClientes;
import controladoras.IVeiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Uma classe GUI console para gerenciar operações de locação.
 */
public class GUIconsoleLocacao{
    private GerenciadorClientes gerenciadorClientes;
    private IVeiculos veiculos;
    private List<Locacao> locacoes;

    /**
     * Constrói um objeto GUIconsoleLocacao com o GerenciadorClientes e IVeiculos especificados.
     *
     * @param gerenciadorClientes O objeto GerenciadorClientes a ser usado para gerenciar os clientes.
     * @param veiculos            O objeto IVeiculos a ser usado para gerenciar os veículos.
     */
    public GUIconsoleLocacao(GerenciadorClientes gerenciadorClientes, IVeiculos veiculos) {
        this.gerenciadorClientes = gerenciadorClientes; // Substitua pela implementação real
        this.veiculos = veiculos; // Substitua pela implementação real
        this.locacoes = new ArrayList<>();
    }


    /**
     * Exibe o menu de locação.
     */
    public void exibeMenuLocacao() {
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Locações");

        // Layout principal
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setAlignment(Pos.CENTER);

        // Componentes do menu
        Label titleLabel = new Label("Sistema de Locações");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button incluirButton = new Button("Incluir Locação");
        Button alterarButton = new Button("Alterar Locação");
        Button capturarButton = new Button("Capturar Dados de Locação");
        Button voltarButton = new Button("Voltar");

        // Adiciona os componentes ao layout principal
        mainLayout.getChildren().addAll(titleLabel, incluirButton, alterarButton, capturarButton,voltarButton);

        // Event handlers
        incluirButton.setOnAction(e -> showIncluirLocacaoDialog());
        alterarButton.setOnAction(e -> showAlterarLocacaoDialog());
        capturarButton.setOnAction(e -> showCapturarDadosLocacaoDialog());
        voltarButton.setOnAction(e -> stage.close());

        // Cria a cena e exibe a janela
        Scene scene = new Scene(mainLayout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exibe um diálogo para incluir uma nova locação.
     */
    private void showIncluirLocacaoDialog() {
        // Cria uma nova janela
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Incluir Locação");

        // Layout do diálogo
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10));

        // Componentes do diálogo
        Label cpfLabel = new Label("CPF do Cliente:");
        TextField cpfTextField = new TextField();
        Label placaLabel = new Label("Placa do Veículo:");
        TextField placaTextField = new TextField();
        Label inicioLabel = new Label("Data de Início (yyyy-MM-dd):");
        TextField inicioTextField = new TextField();
        Label terminoLabel = new Label("Data de Término (yyyy-MM-dd):");
        TextField terminoTextField = new TextField();
        Button cadastrarButton = new Button("Cadastrar");

        // Adiciona os componentes ao layout
        layout.add(cpfLabel, 0, 0);
        layout.add(cpfTextField, 1, 0);
        layout.add(placaLabel, 0, 1);
        layout.add(placaTextField, 1, 1);
        layout.add(inicioLabel, 0, 2);
        layout.add(inicioTextField, 1, 2);
        layout.add(terminoLabel, 0, 3);
        layout.add(terminoTextField, 1, 3);
        layout.add(cadastrarButton, 1, 4);

        // Event handler do botão de cadastro
        cadastrarButton.setOnAction(e -> {
            try {
                incluirLocacao(
                        Long.parseLong(cpfTextField.getText()),
                        placaTextField.getText(),
                        LocalDate.parse(inicioTextField.getText()),
                        LocalDate.parse(terminoTextField.getText())
                );
                dialogStage.close();
            } catch (ClienteNaoEncontradoException | VeiculoNaoEncontradoException ex) {
                showErrorDialog("Erro", ex.getMessage());
            }
        });

        // Cria a cena do diálogo
        Scene dialogScene = new Scene(layout, 400, 200);
        dialogStage.setScene(dialogScene);
        
        dialogStage.show();
    }

    /**
     * Inclui uma nova locação com os detalhes especificados.
     *
     * @param cpf     O CPF do cliente.
     * @param placa   A placa do veículo.
     * @param inicio  A data de início da locação.
     * @param termino A data de término da locação.
     * @throws ClienteNaoEncontradoException   Se o cliente não for encontrado.
     * @throws VeiculoNaoEncontradoException  Se o veículo não for encontrado.
     */
    private void incluirLocacao(long cpf, String placa, LocalDate inicio, LocalDate termino)
            throws ClienteNaoEncontradoException, VeiculoNaoEncontradoException {
        Cliente cliente = gerenciadorClientes.get(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado.");
        }

        Veiculo veiculo = veiculos.get(placa);
        if (veiculo == null) {
            throw new VeiculoNaoEncontradoException("Veículo não encontrado.");
        }

        Locacao locacao = new Locacao(cliente, veiculo, inicio, termino);
        locacoes.add(locacao);
        System.out.println("Locação cadastrada com sucesso. Código Locação: "+ locacao.getCodigo());
    }

/**
     * Exibe um diálogo para alterar uma locação existente.
     */
private void showAlterarLocacaoDialog() {
    // Cria uma nova janela
    Stage dialogStage = new Stage();
    dialogStage.setTitle("Alterar Locação");

    // Layout do diálogo
    GridPane layout = new GridPane();
    layout.setAlignment(Pos.CENTER);
    layout.setHgap(10);
    layout.setVgap(10);
    layout.setPadding(new Insets(10));

    // Componentes do diálogo
    Label codigoLabel = new Label("Código da Locação:");
    TextField codigoTextField = new TextField();
    Button alterarButton = new Button("Alterar");

    // Adiciona os componentes ao layout
    layout.add(codigoLabel, 0, 0);
    layout.add(codigoTextField, 1, 0);
    layout.add(alterarButton, 1, 1);

    // Event handler do botão de alteração
    alterarButton.setOnAction(e -> {
        try {
            int codigo = Integer.parseInt(codigoTextField.getText());
            alterarLocacao(codigo, dialogStage);
        } catch (NumberFormatException ex) {
            showErrorDialog("Erro", "Código da locação inválido.");
        } catch (LocacaoInvalidaException ex) {
            showErrorDialog("Erro", ex.getMessage());
        }
    });

    // Cria a cena do diálogo
    Scene dialogScene = new Scene(layout, 300, 150);
    dialogStage.setScene(dialogScene);
    dialogStage.show();
}

/**
     * Altera a locação com o código especificado.
     *
     * @param codigo        O código da locação a ser alterada.
     * @param dialogStage   O estágio do diálogo.
     * @throws LocacaoInvalidaException    Se a locação for inválida.
     */
private void alterarLocacao(int codigo, Stage dialogStage) throws LocacaoInvalidaException {
    Locacao locacao = buscarLocacaoPorCodigo(codigo);
    if (locacao == null) {
        showErrorDialog("Erro", "Locação não encontrada.");
        return;
    }

    // Cria um layout para exibir os componentes de alteração
    VBox alterarLayout = new VBox(10);
    alterarLayout.setAlignment(Pos.CENTER);
    alterarLayout.setPadding(new Insets(10));

    // Componentes para alterar as datas de início e término
    Label clienteLabel = new Label("Cliente: " + locacao.getCliente().getNome());
    Label veiculoLabel = new Label("Veículo: " + locacao.getVeiculo().toString());
    Label inicioLabel = new Label("Data de início atual: " + locacao.getDataInicial());
    Label terminoLabel = new Label("Data de término atual: " + locacao.getDataFinal());
    Label novaInicioLabel = new Label("Nova data de início (yyyy-MM-dd):");
    DatePicker novaInicioPicker = new DatePicker();
    Label novaTerminoLabel = new Label("Nova data de término (yyyy-MM-dd):");
    DatePicker novaTerminoPicker = new DatePicker();
    Button confirmarButton = new Button("Confirmar");

    // Adiciona os componentes ao layout
    alterarLayout.getChildren().addAll(clienteLabel, veiculoLabel, inicioLabel, terminoLabel,
            novaInicioLabel, novaInicioPicker, novaTerminoLabel, novaTerminoPicker, confirmarButton);

    // Event handler do botão de confirmar
    confirmarButton.setOnAction(e -> {
        LocalDate novaDataInicio = novaInicioPicker.getValue();
        LocalDate novaDataTermino = novaTerminoPicker.getValue();
        if (novaDataInicio == null || novaDataTermino == null) {
            showErrorDialog("Erro", "Selecione as novas datas de início e término.");
        } else {
            locacao.setDataInicial(novaDataInicio);
            locacao.setDataFinal(novaDataTermino);
            dialogStage.close();
            showSuccessDialog("Sucesso", "Locação alterada com sucesso.");
        }
    });

    // Cria a cena com o layout de alteração
    Scene alterarScene = new Scene(alterarLayout, 400, 300);
    dialogStage.setScene(alterarScene);
}

/**
     * Exibe um diálogo para capturar os dados de uma locação existente.
     */
    private void showCapturarDadosLocacaoDialog() {
        // Cria uma nova janela
    Stage dialogStage = new Stage();
    dialogStage.setTitle("Capturar Dados de Locação");

    // Layout do diálogo
    GridPane layout = new GridPane();
    layout.setAlignment(Pos.CENTER);
    layout.setHgap(10);
    layout.setVgap(10);
    layout.setPadding(new Insets(10));

    // Componentes do diálogo
    Label codigoLabel = new Label("Código da Locação:");
    TextField codigoTextField = new TextField();
    Button capturarButton = new Button("Capturar Dados");

    // Adiciona os componentes ao layout
    layout.add(codigoLabel, 0, 0);
    layout.add(codigoTextField, 1, 0);
    layout.add(capturarButton, 1, 1);

    // Event handler do botão de captura
    capturarButton.setOnAction(e -> {
    try {
        capturarDadosLocacao(Integer.parseInt(codigoTextField.getText()), dialogStage);
    } catch (NumberFormatException ex) {
        showErrorDialog("Erro", "Código da locação inválido.");
    }
});

    // Cria a cena do diálogo
    Scene dialogScene = new Scene(layout, 300, 150);
    dialogStage.setScene(dialogScene);
    dialogStage.show();
}

/**
     * Captura e exibe os dados da locação com o código especificado.
     *
     * @param codigo        O código da locação para capturar os dados.
     * @param dialogStage   O estágio do diálogo.
     */
private void capturarDadosLocacao(int codigo, Stage dialogStage) {
    Locacao locacao = buscarLocacaoPorCodigo(codigo);
    if (locacao == null) {
        showErrorDialog("Erro", "Locação não encontrada.");
        return;
    }

    // Cria um layout para exibir as informações
    VBox infoLayout = new VBox(10);
    infoLayout.setAlignment(Pos.CENTER);
    infoLayout.setPadding(new Insets(10));

    // Componentes para exibir as informações
    Label clienteLabel = new Label("Cliente: " + locacao.getCliente().getNome());
    Label veiculoLabel = new Label("Veículo: " + locacao.getVeiculo().toString());
    Label inicioLabel = new Label("Data de início: " + locacao.getDataInicial());
    Label terminoLabel = new Label("Data de término: " + locacao.getDataFinal());
    Button closeButton = new Button("Fechar");

    // Adiciona os componentes ao layout
    infoLayout.getChildren().addAll(clienteLabel, veiculoLabel, inicioLabel, terminoLabel, closeButton);

    // Event handler do botão Fechar
    closeButton.setOnAction(e -> dialogStage.close());

    // Cria a cena com o layout das informações
    Scene infoScene = new Scene(infoLayout, 500, 350);
    dialogStage.setScene(infoScene);
}


/**
     * Exibe um diálogo de erro com o título e a mensagem especificados.
     *
     * @param title   O título do diálogo de erro.
     * @param message A mensagem a ser exibida no diálogo de erro.
     */
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Exibe um diálogo de sucesso com o título e a mensagem especificados.
     *
     * @param title   O título do diálogo de sucesso.
     * @param message A mensagem a ser exibida no diálogo de sucesso.
     */
    private void showSuccessDialog(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    /**
     * Procura uma locação com o código especificado.
     *
     * @param codigo O código da locação a ser pesquisada.
     * @return O objeto Locacao com o código especificado, ou null se não encontrado.
     */
    private Locacao buscarLocacaoPorCodigo(int codigo) {
    for (Locacao locacao : locacoes) {
        if (locacao.getCodigo() == codigo) {
            return locacao;
        }
    }
    return null;
}
}

