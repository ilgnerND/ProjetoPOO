package consoles;
import controladoras.GerenciadorClientes;
import excecoes.clienteexcecao.ClienteExistenteException;
import excecoes.clienteexcecao.ClienteNaoEncontradoException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelos.Cliente;

/**
 * Classe que representa a interface gráfica do console para gerenciamento de clientes.
 */

public class GUIconsoleCliente {
    private GerenciadorClientes gerenciadorClientes;


    /**
     * Construtor da classe GUIconsoleCliente.
     * 
     * @param gerenciadorClientes o gerenciador de clientes a ser utilizado
     */

    public GUIconsoleCliente(GerenciadorClientes gerenciadorClientes) {
        this.gerenciadorClientes = gerenciadorClientes;
    }

    /**
     * Exibe o menu de opções para o gerenciamento de clientes.
     */
    public void exibeMenuCliente() {
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Clientes");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Button btnIncluir = new Button("Incluir Cliente");
        btnIncluir.setOnAction(e -> exibeTelaIncluirCliente());

        Button btnAlterar = new Button("Alterar Cliente");
        btnAlterar.setOnAction(e -> exibeTelaAlterarCliente());

        Button btnConsultar = new Button("Consultar Cliente");
        btnConsultar.setOnAction(e -> exibeTelaConsultarCliente());

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> stage.close());

        vbox.getChildren().addAll(btnIncluir, btnAlterar, btnConsultar, btnVoltar);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exibe a tela para inclusão de um novo cliente.
     */
    private void exibeTelaIncluirCliente() {
        
        Stage stage = new Stage();
        stage.setTitle("Incluir Cliente");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();

        Label lblCPF = new Label("CPF:");
        TextField txtCPF = new TextField();

        Label lblNumCarteira = new Label("Número da Carteira de Motorista:");
        TextField txtNumCarteira = new TextField();

        Label lblEndereco = new Label("Endereço:");
        TextField txtEndereco = new TextField();

        Label lblTelefone = new Label("Telefone:");
        TextField txtTelefone = new TextField();

        Button btnIncluir = new Button("Incluir");
        btnIncluir.setOnAction(e -> {
            try {
                String nome = txtNome.getText();
                long cpf = Long.parseLong(txtCPF.getText());
                String numCarteiraMotorista = txtNumCarteira.getText();
                String endereco = txtEndereco.getText();
                String telefone = txtTelefone.getText();
                Cliente cliente = new Cliente(nome, cpf, numCarteiraMotorista, endereco, telefone);
                gerenciadorClientes.add(cliente);

                System.out.println("Cliente incluído com sucesso!");
            exibirMensagem("Sucesso", "Cliente incluído com sucesso!");

            stage.close();
        } catch (NumberFormatException ex) {
            System.out.println("CPF inválido! Digite apenas números.");
            exibirMensagem("Erro", "CPF inválido! Digite apenas números.");
        } catch (ClienteExistenteException ex) {
            System.out.println(ex.getMessage());
            exibirMensagem("Erro", ex.getMessage());
        }
    });

        vbox.getChildren().addAll(lblNome, txtNome, lblCPF, txtCPF, lblNumCarteira, txtNumCarteira, lblEndereco,
                txtEndereco, lblTelefone, txtTelefone, btnIncluir);

        Scene scene = new Scene(vbox, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exibe a tela para alteração de um cliente existente.
     */
    private void exibeTelaAlterarCliente() {
        Stage stage = new Stage();
        stage.setTitle("Alterar Cliente");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Label lblCPF = new Label("CPF do cliente:");
        TextField txtCPF = new TextField();

        Button btnConsultar = new Button("Consultar");
        btnConsultar.setOnAction(e -> {
            try {
                long cpf = Long.parseLong(txtCPF.getText());
                exibeTelaAlteracaoCliente(cpf);
            stage.close();
        } catch (NumberFormatException ex) {
            System.out.println("CPF inválido! Digite apenas números.");
            exibirMensagem("Erro", "CPF inválido! Digite apenas números.");
        } catch (ClienteNaoEncontradoException ex) {
            System.out.println(ex.getMessage());
            exibirMensagem("Erro", ex.getMessage());
        }
    });

        vbox.getChildren().addAll(lblCPF, txtCPF, btnConsultar);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    private void exibeTelaAlteracaoCliente(long cpf) throws ClienteNaoEncontradoException {
        Stage stage = new Stage();
        stage.setTitle("Alteração de Cliente");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Label lblNome = new Label("Novo nome:");
        TextField txtNome = new TextField();

        Label lblNumCarteira = new Label("Novo número da Carteira de Motorista:");
        TextField txtNumCarteira = new TextField();

        Label lblEndereco = new Label("Novo endereço:");
        TextField txtEndereco = new TextField();

        Label lblTelefone = new Label("Novo telefone:");
        TextField txtTelefone = new TextField();

        Button btnAlterar = new Button("Alterar");
        btnAlterar.setOnAction(e -> {
            try {
                String nome = txtNome.getText();
                String numCarteiraMotorista = txtNumCarteira.getText();
                String endereco = txtEndereco.getText();
                String telefone = txtTelefone.getText();
                Cliente novoCliente = new Cliente(nome, cpf,numCarteiraMotorista, endereco, telefone);

                gerenciadorClientes.set(cpf,novoCliente);

                System.out.println("Cliente alterado com sucesso!");

                stage.close();
            } catch (ClienteNaoEncontradoException ex) {
                System.out.println(ex.getMessage());
            }
        });

        vbox.getChildren().addAll(lblNome, txtNome, lblNumCarteira, txtNumCarteira, lblEndereco, txtEndereco,
                lblTelefone, txtTelefone, btnAlterar);

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.show();
    }


   /**
     * Exibe a tela para consultar as informações de um cliente.
     */ 
    private void exibeTelaConsultarCliente() {
        Stage stage = new Stage();
        stage.setTitle("Consultar Cliente");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Label lblCPF = new Label("CPF do cliente:");
        TextField txtCPF = new TextField();

        Button btnConsultar = new Button("Consultar");
        btnConsultar.setOnAction(e -> {
            try {
                
                long cpf = Long.parseLong(txtCPF.getText());
                String info = gerenciadorClientes.getInfo(cpf);
            if (info == null) {
                System.out.println("Cliente não encontrado!");
                exibirMensagem("Erro", "Cliente não encontrado!");
            } else {
                System.out.println("Informações do cliente:\n" + info);
                exibirMensagem("Informações do Cliente", "Informações do cliente:\n" + info);
            }
            stage.close();
        } catch (NumberFormatException ex) {
            System.out.println("CPF inválido! Digite apenas números.");
            exibirMensagem("Erro", "CPF inválido! Digite apenas números.");
        } catch (ClienteNaoEncontradoException e1) {
            e1.getMessage();
            exibirMensagem("Erro", e1.getMessage());
        }
    });

        vbox.getChildren().addAll(lblCPF, txtCPF, btnConsultar);

        Scene scene = new Scene(vbox, 500, 200);
        stage.setScene(scene);
        stage.show();
    }

     /**
     * Exibe uma mensagem de diálogo.
     *
     * @param titulo   o título da mensagem
     * @param mensagem a mensagem a ser exibida
     */
    private void exibirMensagem(String titulo, String mensagem) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensagem);
    alert.showAndWait();
}
}
