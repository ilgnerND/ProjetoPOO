package consoles;

import java.util.InputMismatchException;
import java.util.Scanner;

import excecoes.clienteexcecao.ClienteExistenteException;
import excecoes.clienteexcecao.ClienteNaoEncontradoException;
import modelos.Carro;
import modelos.Onibus;
import modelos.Veiculo;
import controladoras.IVeiculos;

import excecoes.veiculosececao.ColecaoVaziaException;
import excecoes.veiculosececao.VeiculoExistenteException;
import excecoes.veiculosececao.VeiculoNaoEncontradoException;

/**
 * A classe ConsoleVeiculos é responsável por exibir um menu de opções para
 * interagir com o sistema de cadastro de veículos.
 * Possui métodos para incluir, alterar, capturar e listar veículos.
 */
public class ConsoleVeiculos {
    private IVeiculos veiculos;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleVeiculos(IVeiculos veiculos) {
        this.veiculos = veiculos;
    }

    /**
     * Exibe o menu de opções para o sistema de cadastro de veículos e aguarda a
     * entrada do usuário para realizar as operações.
     * 
     * @throws VeiculoExistenteException
     * @throws VeiculoNaoEncontradoException
     * @throws ColecaoVaziaException
     * @throws ClienteExistenteException
     * @throws ClienteNaoEncontradoException
     */
    public void exibeMenuVeiculos() {
        int opcao = 0;

        do {
            System.out.println("Sistema de Cadastro de Veículos");
            System.out.println("1 - Incluir Veículo");
            System.out.println("2 - Alterar Veículo");
            System.out.println("3 - Capturar Veículo");
            System.out.println("4 - Listar Veículos");
            System.out.println("0 - Voltar Menu Anterior");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    try {
                        incluirVeiculo();
                    } catch (VeiculoExistenteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        alterarVeiculo();
                    } catch (VeiculoNaoEncontradoException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        capturarVeiculos();
                    } catch (VeiculoNaoEncontradoException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        listarVeiculos();
                    } catch (ColecaoVaziaException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:

                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * 
     * Realiza a inclusão de um veículo no sistema, solicitando ao usuário as
     * informações necessárias.
     * Após a inclusão, adiciona o veículo à lista de veículos da locadora.
     * 
     * @throws VeiculoExistenteException
     */
    private void incluirVeiculo() throws VeiculoExistenteException {
        System.out.println("Inclusão de Veículo");

        // Solicita informações do veículo ao usuário
        System.out.print("Placa: ");
        String placa = scanner.next();

        System.out.print("Ano: ");
        int ano; 
        try {
           ano = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Ano deve ser um número inteiro.");
            scanner.nextLine(); //consome a entrada inválida e retorna ao início da função
            return;
            // TODO: handle exception
        }
        
        System.out.print("Valor Diária: ");
        double valorDiaria; 
        try {
           valorDiaria = scanner.nextDouble(); 
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Valor Diária deve ser um número decimal.");
            scanner.nextLine(); //consome a entrada inválida e retorna ao início da função
            return;
            // TODO: handle exception
        }

        System.out.println("Tipo do Veículo");
        System.out.println("1 - Carro");
        System.out.println("2 - Ônibus");
        System.out.print("Escolha uma opção: ");
        int tipo = scanner.nextInt();

        Veiculo veiculo;

        // Cria o veículo com base no tipo selecionado
        if (tipo == 1) {
            System.out.print("Média de km por litros: ");
            double mediaKmPorLitros = scanner.nextDouble();

            System.out.print("Ar condicionado (S/N): ");
            String input = scanner.next().toUpperCase();
            String arCondicionado = input.equals("S") ? "Sim" : input.equals("N") ? "Não" : /* valor padrão */ "Sim";

            veiculo = new Carro(placa, ano, valorDiaria, mediaKmPorLitros, arCondicionado);
        } else if (tipo == 2) {
            System.out.print("Número de passageiros: ");
            int numPassageiros = scanner.nextInt();

            System.out.print("Categoria: ");
            String categoria = scanner.next();

            veiculo = new Onibus(placa, ano, valorDiaria, numPassageiros, categoria);
        } else {
            System.out.println("Tipo de veículo inválido!");
            return;
        }

        // Adiciona o veículo à lista de veículos da locadora e exibe uma mensagem de
        // sucesso.
        veiculos.add(veiculo);
        System.out.println("Veículo incluído com sucesso!");
    }

    /**
     * Realiza a alteração de um veículo no sistema, solicitando ao usuário a placa
     * do veículo a ser alterado
     * e as novas informações do veículo.
     * Verifica se o veículo existe na locadora e, caso exista, realiza a alteração
     * e exibe uma mensagem de sucesso.
     * 
     * @throws VeiculoNaoEncontradoException
     */
    private void alterarVeiculo() throws VeiculoNaoEncontradoException {
        System.out.println("Alteração de Veículo");

        // Solicita a placa do veículo a ser alterado
        System.out.print("Placa: ");
        String placa = scanner.next();

        // Verifica se o veículo existe
        if (!veiculos.existe(placa)) {
            System.out.println("Veículo não encontrado!");
            return;
        }

        Veiculo veiculo = veiculos.get(placa);

        // Solicita as novas informações do veículo
        System.out.print("Ano (" + veiculo.getAno() + "): ");
        int ano = scanner.nextInt();
        veiculo.setAno(ano);

        System.out.print("Valor Diária (" + veiculo.getValorDiaria() + "): ");
        double valorDiaria = scanner.nextDouble();
        veiculo.setValorDiaria(valorDiaria);

        if (veiculo instanceof Carro) {
            Carro carro = (Carro) veiculo;

            System.out.print("Média de km por litros (" + carro.getMediaKmPorLitro() + "): ");
            double mediaKmPorLitros = scanner.nextDouble();
            carro.setMediaKmPorLitro(mediaKmPorLitros);

            System.out.print("Ar Condicionado (" + carro.isArCondicionado() + "): ");
            String input = scanner.next().toUpperCase();

            String arCondicionado;
            try {
                arCondicionado = input.equals("S") || input.equals("SIM") ? "Sim"
                        : input.equals("N") || input.equals("NÃO") ? "Não"
                                : carro.isArCondicionado();
            } catch (Exception e) {
                System.out.println("Opção inválida. Mantendo a configuração atual.");
                arCondicionado = carro.isArCondicionado();
            }

            carro.setArCondicionado(arCondicionado);

        } else if (veiculo instanceof Onibus) {
            Onibus onibus = (Onibus) veiculo;

            System.out.print("Número de passageiros (" + onibus.getNumeroPassageiros() + "): ");
            int numPassageiros = scanner.nextInt();
            onibus.setNumeroPassageiros(numPassageiros);

            System.out.print("Categoria (" + onibus.getCategoria() + "): ");
            String categoria = scanner.next();
            onibus.setCategoria(categoria);
        }

        // Realiza a alteração do veículo na locadora
        boolean alterado = veiculos.set(placa, veiculo);
        if (alterado) {
            System.out.println("Veículo alterado com sucesso!");
        } else {
            System.out.println("Não foi possível alterar o veículo.");
        }
    }

    /**
     * Captura e exibe as informações de um ou mais veículos, informados pelo
     * usuário através de suas placas.
     * 
     * @throws VeiculoNaoEncontradoException
     */
    private void capturarVeiculos() throws VeiculoNaoEncontradoException {
        System.out.println("Captura de Veículos");

        System.out.print("Placas (separadas por vírgula): ");
        String placasStr = scanner.next();
        String[] placas = placasStr.split(",");

        for (String placa : placas) {
            if (veiculos.existe(placa)) {
                System.out.println(veiculos.getInfo(placa));
            } else {
                System.out.println("Veículo com placa " + placa + " não encontrado.");
            }
        }
    }

    /**
     * Lista todos os veículos cadastrados na locadora.
     * 
     * @throws ColecaoVaziaException
     */
    private void listarVeiculos() throws ColecaoVaziaException {
        System.out.println("Lista de Veículos");
        // Obtém as informações dos veículos da locadora
        String info = veiculos.getInfo();

        // Verifica se há veículos cadastrados
        if (info != null) {
            System.out.println(info);
        } else {
            System.out.println("Nenhum veículo cadastrado.");
        }
    }

}
