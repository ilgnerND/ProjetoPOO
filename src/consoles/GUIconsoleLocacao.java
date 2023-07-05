package consoles;
import excecoes.clienteexcecao.ClienteNaoEncontradoException;
import excecoes.locacaoexcecao.LocacaoInvalidaException;
import excecoes.veiculosececao.VeiculoNaoEncontradoException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
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

public class GUIconsoleLocacao{
    private GerenciadorClientes gerenciadorClientes;
    private IVeiculos veiculos;
    private List<Locacao> locacoes;

    public GUIconsoleLocacao(GerenciadorClientes gerenciadorClientes, IVeiculos veiculos) {
        this.gerenciadorClientes = gerenciadorClientes; // Substitua pela implementação real
        this.veiculos = veiculos; // Substitua pela implementação real
        this.locacoes = new ArrayList<>();
    }


    
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
        mainLayout.getChildren().addAll(titleLabel, incluirButton, alterarButton, capturarButton);

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

        Locacao locacao = new Locacao(0, cliente, veiculo, inicio, termino);
        locacoes.add(locacao);
        System.out.println("Locação cadastrada com sucesso.");
    }

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
            alterarLocacao(Integer.parseInt(codigoTextField.getText()));
            dialogStage.close();
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
    private void alterarLocacao(int codigo) throws LocacaoInvalidaException {
    Locacao locacao = buscarLocacaoPorCodigo(codigo);
    if (locacao == null) {
        throw new LocacaoInvalidaException("Locação não encontrada.");
    }

    // TODO: Implemente a lógica para alterar a locação

    System.out.println("Locação alterada com sucesso.");
}

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
            capturarDadosLocacao(Integer.parseInt(codigoTextField.getText()));
            dialogStage.close();
        } catch (NumberFormatException ex) {
            showErrorDialog("Erro", "Código da locação inválido.");
        }
    });

    // Cria a cena do diálogo
    Scene dialogScene = new Scene(layout, 300, 150);
    dialogStage.setScene(dialogScene);
    dialogStage.show();
}
    private void capturarDadosLocacao(int codigo) {
    Locacao locacao = buscarLocacaoPorCodigo(codigo);
    if (locacao == null) {
        System.out.println("Locação não encontrada.");
        return;
    }

    System.out.println("Dados da locação:");
    System.out.println("Cliente: " + locacao.getCliente().getNome());
    System.out.println("Veículo: " + locacao.getVeiculo().toString());
    System.out.println("Data de início: " + locacao.getDataInicial());
    System.out.println("Data de fim: " + locacao.getDataFinal());
}

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Locacao buscarLocacaoPorCodigo(int codigo) {
    for (Locacao locacao : locacoes) {
        if (locacao.getCodigo() == codigo) {
            return locacao;
        }
    }
    return null;
}
}

