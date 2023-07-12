package consoles;
import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import excecoes.clienteexcecao.ClienteNaoEncontradoException;
import excecoes.locacaoexcecao.LocacaoInvalidaException;
import controladoras.GerenciadorClientes;
import controladoras.IVeiculos;
import modelos.Cliente;
import modelos.Locacao;
import modelos.Veiculo;
import excecoes.veiculosececao.VeiculoNaoEncontradoException;

public class ConsoleLocacoes {
    private GerenciadorClientes gerenciadorClientes;
    private IVeiculos veiculos;
    private List<Locacao> locacoes;

    public ConsoleLocacoes(GerenciadorClientes gerenciadorClientes, IVeiculos veiculos) {
        this.gerenciadorClientes = gerenciadorClientes;
        this.veiculos = veiculos;
        this.locacoes = new ArrayList<>();
    }

    public void exibeMenuLocacoes()  {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        
        while (opcao != 4) {
            exibirMenu();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    try {
                        incluirLocacao(scanner);
                    } catch (ClienteNaoEncontradoException | VeiculoNaoEncontradoException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                    }
                break;
            case 2:
                    try {
                        alterarLocacao(scanner);
                    } catch (LocacaoInvalidaException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                    }
                break;
            case 3:
                capturarDadosLocacao(scanner);
                break;
                case 4:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
/**
     * Exibe o menu do programa.
     */
    private void exibirMenu() {
        System.out.println("----- Menu -----");
        System.out.println("1 - Incluir locação");
        System.out.println("2 - Alterar locação");
        System.out.println("3 - Capturar dados de uma locação");
        System.out.println("4 - Sair");
    }

    /**
     * Realiza a inclusão de uma nova locação.
     *
     * @param scanner O objeto Scanner utilizado para obter os dados da locação.
     * @throws ClienteNaoEncontradoException
     * @throws VeiculoNaoEncontradoException
     */
    private void incluirLocacao(Scanner scanner) throws ClienteNaoEncontradoException, VeiculoNaoEncontradoException {
        System.out.println("===== Cadastrar locação =====");

        // Solicita e lê o CPF do cliente
        System.out.print("Digite o CPF do cliente: ");
        long cpf = scanner.nextLong();
        Cliente cliente = gerenciadorClientes.get(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Solicita e lê a placa do veículo
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.next();
        Veiculo veiculo = veiculos.get(placa);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        // Solicita e lê as datas de início e término da locação
        System.out.print("Digite a data de início da locação (yyyy-MM-dd): ");
        LocalDate inicio = LocalDate.parse(scanner.next());
        System.out.print("Digite a data de término da locação (yyyy-MM-dd): ");
        LocalDate termino = LocalDate.parse(scanner.next());

        // Cria um novo objeto Locacao
        Locacao locacao = new Locacao(0, cliente, veiculo, inicio, termino);

        // Adiciona a locação à lista de locações
        locacoes.add(locacao);

        System.out.println("Locação cadastrada com sucesso.");
    }

    /**
     * Realiza a alteração dos dados de uma locação.
     *
     * @param scanner O objeto Scanner utilizado para obter os dados da alteração.
     */
    public void alterarLocacao(Scanner scanner) throws LocacaoInvalidaException{
        System.out.println("Digite o código da locação a ser alterada: ");
        int codigo = scanner.nextInt();
        Locacao locacao = locacoes.get(codigo);
        if (locacao == null) {
        throw new LocacaoInvalidaException("Locação não encontrada.");
    }

        // Solicita e lê as novas datas de início e término da locação
        System.out.println("Informe a data de início da locação (yyyy-MM-dd):");
        LocalDate dataInicio = LocalDate.parse(scanner.next());
        System.out.println("Informe a data de fim da locação (yyyy-MM-dd):");
        LocalDate dataFim = LocalDate.parse(scanner.next());

        // Atualiza as datas da locação
        locacao.setDataInicial(dataInicio);
        locacao.setDataFinal(dataFim);

        System.out.println("Locação alterada com sucesso.");
    }

    /**
     * Captura e exibe os dados de uma locação.
     *
     * @param scanner O objeto Scanner utilizado para obter o código da locação.
     */ 
    public void capturarDadosLocacao(Scanner scanner) {
        System.out.println("Digite o código da locação desejada: ");
        int codigo = scanner.nextInt();
        Locacao locacao = locacoes.get(codigo);
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

}    
           

    

    


