package consoles;
import java.util.Scanner;

import excecoes.clienteexcecao.ClienteExistenteException;
import excecoes.clienteexcecao.ClienteNaoEncontradoException;

import controladoras.IClientes;
import modelos.Cliente;


public class ConsoleClientes {
     static Scanner scanner = new Scanner(System.in);
     static  IClientes gerenciadorClientes;

     public ConsoleClientes(IClientes gerenciadorClientes){
        this.gerenciadorClientes = gerenciadorClientes;
     }

    public  void exibeMenuCliente() {
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\nSistema de Cadastro de Clientes");
            System.out.println("1 - Incluir cliente");
            System.out.println("2 - Alterar cliente");
            System.out.println("3 - Consultar cliente");
            System.out.println("0 - Voltar menu anterior");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    try {
                        incluirCliente();
                    } catch (ClienteExistenteException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        alterarCliente();
                    } catch (ClienteNaoEncontradoException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        consultarCliente();
                    } catch (ClienteNaoEncontradoException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
/**
     * Realiza a inclusão de um novo cliente.
 * @throws ClienteExistenteException
     */
    private static void incluirCliente() throws ClienteExistenteException {
        System.out.println("\nInclusão de Cliente");

        // Solicita e lê os dados do cliente
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        while (!scanner.hasNextLong()) {
            System.out.println("Digite um valor válido para o CPF.");
            scanner.next(); // Descarta a entrada inválida
        }
        long cpf = Long.parseLong(scanner.nextLine());

        System.out.print("Número da Carteira de Motorista: ");
        String numCarteiraMotorista = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        // Cria um novo objeto Cliente
        Cliente cliente = new Cliente(nome, cpf, numCarteiraMotorista, endereco, telefone);

        // Adiciona o cliente ao gerenciador de clientes
        gerenciadorClientes.add(cliente);

        System.out.println("\nCliente incluído com sucesso!");
    }

    /**
     * Realiza a alteração dos dados de um cliente.
     * @throws ClienteNaoEncontradoException
     */
    private static void alterarCliente() throws ClienteNaoEncontradoException {
        System.out.println("\nAlteração de Cliente");

        // Solicita e lê o CPF do cliente
        System.out.print("CPF do cliente: ");
        long cpf = scanner.nextLong();

        // Obtém o cliente pelo CPF
        Cliente cliente = gerenciadorClientes.get(cpf);

        if (cliente == null) {
            System.out.println("\nCliente não encontrado!");
        } else {
            // Solicita e lê os novos dados do cliente
            System.out.print("Novo nome (atual: " + cliente.getNome() + "): ");
            String nome = scanner.next();

            System.out.print("Novo número da Carteira de Motorista (atual: " + cliente.getNumCarteiraMotorista() + "): ");
            String numCarteiraMotorista = scanner.next();

            System.out.print("Novo endereço (atual: " + cliente.getEndereco() + "): ");
            String endereco = scanner.next();

            System.out.print("Novo telefone (atual: " + cliente.getTelefone() + "): ");
            String telefone = scanner.next();

            // Cria um novo objeto Cliente com os dados atualizados
            Cliente novoCliente = new Cliente(nome, cpf, numCarteiraMotorista, endereco, telefone);

            // Realiza a atualização do cliente no gerenciador de clientes
            if (gerenciadorClientes.set(cpf, novoCliente)) {
                System.out.println("\nCliente alterado com sucesso!");
            } else {
                System.out.println("\nErro ao alterar o cliente!");
            }
        }
    }

    /**
     * Realiza a consulta de um cliente pelo CPF.
     * @throws ClienteNaoEncontradoException
     */
    private static void consultarCliente() throws ClienteNaoEncontradoException {
        System.out.println("\nConsulta de Cliente");

        // Solicita e lê o CPF do cliente
        System.out.print("CPF do cliente: ");
        long cpf = scanner.nextLong();

        // Obtém as informações do cliente pelo CPF
        String info = gerenciadorClientes.getInfo(cpf);

        if (info == null) {
            System.out.println("\nCliente não encontrado!");
        } else {
            // Exibe as informações do cliente
            System.out.println("\nInformações do cliente:");
            System.out.println(info);
        }
    }
}    


       

