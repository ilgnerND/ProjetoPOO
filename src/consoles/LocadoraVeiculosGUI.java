package consoles;
import controladoras.GerenciadorClientes;
import controladoras.Locacoes;
import controladoras.Veiculos;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LocadoraVeiculosGUI extends Application {
    Veiculos veiculos = new Veiculos();
    GerenciadorClientes gcliente = new GerenciadorClientes();
    Locacoes locacoes = new Locacoes();

    GUIconsoleCliente consoleClientes = new GUIconsoleCliente(gcliente);
    GUIconsoleLocacao consoleLocacoes = new GUIconsoleLocacao(gcliente, veiculos);
    //ConsoleVeiculos consoleVeiculos = new ConsoleVeiculos(veiculos);
    GUIconsoleVeiculos consoleVeiculos = new GUIconsoleVeiculos(veiculos);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Locadora de Veículos");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Button btnVeiculos = new Button("Veículos");
        btnVeiculos.setOnAction(e -> consoleVeiculos.exibeMenuVeiculo());

        Button btnClientes = new Button("Clientes");
        btnClientes.setOnAction(e -> consoleClientes.exibeMenuCliente());

        Button btnLocacoes = new Button("Locação");
        btnLocacoes.setOnAction(e -> consoleLocacoes.exibeMenuLocacao());

        Button btnSair = new Button("Sair");
        btnSair.setOnAction(e -> primaryStage.close());

        vbox.getChildren().addAll(btnVeiculos, btnClientes, btnLocacoes, btnSair);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
