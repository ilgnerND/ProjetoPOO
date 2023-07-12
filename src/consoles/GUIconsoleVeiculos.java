package consoles;

//import excecoes.clienteexcecao.ClienteExistenteException;
//import excecoes.clienteexcecao.ClienteNaoEncontradoException;
import excecoes.veiculosececao.ColecaoVaziaException;
import excecoes.veiculosececao.VeiculoExistenteException;
import excecoes.veiculosececao.VeiculoNaoEncontradoException;
//import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
////import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelos.Carro;
import modelos.Onibus;
import modelos.Veiculo;
import controladoras.IVeiculos;

//import java.util.InputMismatchException;

public class GUIconsoleVeiculos {
    private IVeiculos veiculos;

    public GUIconsoleVeiculos(IVeiculos veiculos) {
        this.veiculos = veiculos;
    }

    
    public void exibeMenuVeiculo() {
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Veículos");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label labelMenu = new Label("Menu de Opções");
        GridPane.setConstraints(labelMenu, 0, 0);

        Button buttonIncluir = new Button("Incluir Veículo");
        GridPane.setConstraints(buttonIncluir, 0, 1);
        buttonIncluir.setOnAction(e -> showIncluirVeiculo());

        Button buttonAlterar = new Button("Alterar Veículo");
        GridPane.setConstraints(buttonAlterar, 0, 2);
        buttonAlterar.setOnAction(e -> showAlterarVeiculo());

        Button buttonCapturar = new Button("Capturar Veículo");
        GridPane.setConstraints(buttonCapturar, 0, 3);
        buttonCapturar.setOnAction(e -> showCapturarVeiculos());

        Button buttonListar = new Button("Listar Veículos");
        GridPane.setConstraints(buttonListar, 0, 4);
        buttonListar.setOnAction(e -> showListarVeiculos());

        Button buttonVoltar = new Button("Voltar");
    GridPane.setConstraints(buttonVoltar, 0, 5);
    buttonVoltar.setOnAction(e -> stage.close());

    grid.getChildren().addAll(labelMenu, buttonIncluir, buttonAlterar, buttonCapturar, buttonListar, buttonVoltar);

    Scene scene = new Scene(grid, 300, 200);
    stage.setScene(scene);
    stage.show();
    }

    private void showIncluirVeiculo() {
     Stage stage = new Stage();
    stage.setTitle("Inclusão de Veículo");

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(5);
    grid.setHgap(5);

    Label labelPlaca = new Label("Placa:");
    GridPane.setConstraints(labelPlaca, 0, 0);

    TextField textFieldPlaca = new TextField();
    GridPane.setConstraints(textFieldPlaca, 1, 0);

    Label labelAno = new Label("Ano:");
    GridPane.setConstraints(labelAno, 0, 1);

    TextField textFieldAno = new TextField();
    GridPane.setConstraints(textFieldAno, 1, 1);

    Label labelValorDiaria = new Label("Valor Diária:");
    GridPane.setConstraints(labelValorDiaria, 0, 2);

    TextField textFieldValorDiaria = new TextField();
    GridPane.setConstraints(textFieldValorDiaria, 1, 2);

    Label labelTipoVeiculo = new Label("Tipo do Veículo:");
    GridPane.setConstraints(labelTipoVeiculo, 0, 3);

    ChoiceBox<String> choiceBoxTipoVeiculo = new ChoiceBox<>();
    choiceBoxTipoVeiculo.getItems().addAll("Carro", "Ônibus");
    GridPane.setConstraints(choiceBoxTipoVeiculo, 1, 3);

    // Carro-specific fields
    Label labelMediaKmPorLitros = new Label("Média de km por litros:");
    TextField textFieldMediaKmPorLitros = new TextField();

    Label labelArCondicionado = new Label("Ar condicionado (S/N):");
    ChoiceBox<String> choiceBoxArCondicionado = new ChoiceBox<>();
    choiceBoxArCondicionado.getItems().addAll("Sim", "Não");

    // Ônibus-specific fields
    Label labelNumPassageiros = new Label("Número de passageiros:");
    TextField textFieldNumPassageiros = new TextField();

    Label labelCategoria = new Label("Categoria:");
    TextField textFieldCategoria = new TextField();

    Button buttonIncluir = new Button("Incluir");
    GridPane.setConstraints(buttonIncluir, 0, 4);
    buttonIncluir.setOnAction(e -> {
        String placa = textFieldPlaca.getText();
        int ano = Integer.parseInt(textFieldAno.getText());
        double valorDiaria = Double.parseDouble(textFieldValorDiaria.getText());
        String tipoVeiculo = choiceBoxTipoVeiculo.getValue();

        try {
            if (tipoVeiculo.equals("Carro")) {
                double mediaKmPorLitros = Double.parseDouble(textFieldMediaKmPorLitros.getText());
                String arCondicionado = choiceBoxArCondicionado.getValue();

                Veiculo veiculo = new Carro(placa, ano, valorDiaria, mediaKmPorLitros, arCondicionado);
                veiculos.add(veiculo);
            } else if (tipoVeiculo.equals("Ônibus")) {
                int numPassageiros = Integer.parseInt(textFieldNumPassageiros.getText());
                String categoria = textFieldCategoria.getText();

                Veiculo veiculo = new Onibus(placa, ano, valorDiaria, numPassageiros, categoria);
                veiculos.add(veiculo);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Tipo de veículo inválido!");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Veículo incluído com sucesso!");
            alert.showAndWait();

            stage.close();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Entrada inválida. Verifique os campos numéricos!");
            alert.showAndWait();
        } catch (VeiculoExistenteException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Veículo já existe!");
            alert.showAndWait();
        }
    });

    // Set Carro-specific fields visible when "Carro" is selected
    choiceBoxTipoVeiculo.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
        if (newValue.equals("Carro")) {
            grid.getChildren().removeAll(labelNumPassageiros, textFieldNumPassageiros, labelCategoria, textFieldCategoria);
            grid.add(labelMediaKmPorLitros, 0, 5);
            grid.add(textFieldMediaKmPorLitros, 1, 5);
            grid.add(labelArCondicionado, 0, 6);
            grid.add(choiceBoxArCondicionado, 1, 6);
        } else {
            grid.getChildren().removeAll(labelMediaKmPorLitros, textFieldMediaKmPorLitros, labelArCondicionado, choiceBoxArCondicionado);
            grid.add(labelNumPassageiros, 0, 5);
            grid.add(textFieldNumPassageiros, 1, 5);
            grid.add(labelCategoria, 0, 6);
            grid.add(textFieldCategoria, 1, 6);
        }
    });

    grid.getChildren().addAll(labelPlaca, textFieldPlaca, labelAno, textFieldAno, labelValorDiaria, textFieldValorDiaria,
            labelTipoVeiculo, choiceBoxTipoVeiculo, buttonIncluir);

    Scene scene = new Scene(grid, 300, 250);
    stage.setScene(scene);
    stage.show();
}

private void showAlterarVeiculo() {
    Stage stage = new Stage();
    stage.setTitle("Alteração de Veículo");

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(5);
    grid.setHgap(5);

    Label labelPlaca = new Label("Placa:");
    GridPane.setConstraints(labelPlaca, 0, 0);

    TextField textFieldPlaca = new TextField();
    GridPane.setConstraints(textFieldPlaca, 1, 0);

    Button buttonAlterar = new Button("Alterar");
    GridPane.setConstraints(buttonAlterar, 0, 1);
    buttonAlterar.setOnAction(e -> {
        String placa = textFieldPlaca.getText();

        try {
            if (!veiculos.existe(placa)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Veículo não encontrado!");
                alert.showAndWait();
                return;
            }

            Veiculo veiculo = veiculos.get(placa);

            Stage alterarStage = new Stage();
            alterarStage.setTitle("Alterar Veículo");

            GridPane alterarGrid = new GridPane();
            alterarGrid.setPadding(new Insets(10, 10, 10, 10));
            alterarGrid.setVgap(5);
            alterarGrid.setHgap(5);

            Label labelAno = new Label("Ano:");
            GridPane.setConstraints(labelAno, 0, 0);

            TextField textFieldAno = new TextField(Integer.toString(veiculo.getAno()));
            GridPane.setConstraints(textFieldAno, 1, 0);

            Label labelValorDiaria = new Label("Valor Diária:");
            GridPane.setConstraints(labelValorDiaria, 0, 1);

            TextField textFieldValorDiaria = new TextField(Double.toString(veiculo.getValorDiaria()));
            GridPane.setConstraints(textFieldValorDiaria, 1, 1);

            Button buttonSalvar = new Button("Salvar");
            GridPane.setConstraints(buttonSalvar, 0, 2);
            buttonSalvar.setOnAction(event -> {
                int novoAno = Integer.parseInt(textFieldAno.getText());
                double novoValorDiaria = Double.parseDouble(textFieldValorDiaria.getText());

                veiculo.setAno(novoAno);
                veiculo.setValorDiaria(novoValorDiaria);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Veículo alterado com sucesso!");
                alert.showAndWait();

                alterarStage.close();
                stage.close();
            });

            alterarGrid.getChildren().addAll(labelAno, textFieldAno, labelValorDiaria, textFieldValorDiaria, buttonSalvar);

            Scene alterarScene = new Scene(alterarGrid, 300, 200);
            alterarStage.setScene(alterarScene);
            alterarStage.show();
        } catch (VeiculoNaoEncontradoException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Veículo não encontrado!");
            alert.showAndWait();
        }
    });

    grid.getChildren().addAll(labelPlaca, textFieldPlaca, buttonAlterar);

    Scene scene = new Scene(grid, 300, 200);
    stage.setScene(scene);
    stage.show();
}

private void showCapturarVeiculos() {
    Stage stage = new Stage();
    stage.setTitle("Captura de Veículos");

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(5);
    grid.setHgap(5);

    Label labelPlacas = new Label("Placas (separadas por vírgula):");
    GridPane.setConstraints(labelPlacas, 0, 0);

    TextField textFieldPlacas = new TextField();
    GridPane.setConstraints(textFieldPlacas, 1, 0);

    Button buttonCapturar = new Button("Capturar");
    GridPane.setConstraints(buttonCapturar, 0, 1);
    buttonCapturar.setOnAction(e -> {
        String placasStr = textFieldPlacas.getText();
        String[] placas = placasStr.split(",");

        StringBuilder resultado = new StringBuilder();

        for (String placa : placas) {
            try {
                if (veiculos.existe(placa)) {
                    resultado.append(veiculos.getInfo(placa)).append("\n");
                } else {
                    resultado.append("Veículo com placa ").append(placa).append(" não encontrado.\n");
                }
            } catch (VeiculoNaoEncontradoException ex) {
                resultado.append("Veículo com placa ").append(placa).append(" não encontrado.\n");
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado da Captura");
        alert.setHeaderText(null);
        alert.setContentText(resultado.toString());
        alert.showAndWait();
    });

    grid.getChildren().addAll(labelPlacas, textFieldPlacas, buttonCapturar);

    Scene scene = new Scene(grid, 300, 200);
    stage.setScene(scene);
    stage.show();
}

private void showListarVeiculos() {
    String info;
    try {
        info = veiculos.getInfo();
        if (info != null) {
        TextArea textArea = new TextArea(info);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);

        Stage stage = new Stage();
        stage.setTitle("Lista de Veículos");
        stage.setScene(new Scene(scrollPane, 400, 300));
        stage.show();

        } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Nenhum veículo cadastrado.");
        alert.showAndWait();
    }
        
    } catch (ColecaoVaziaException e) {
        // TODO Auto-generated catch block
        e.getMessage();
    }

    
    
}


}
