package interfaces;

import controller.ControllerCliente;
import controller.ControllerLocacao;
import controller.ControllerVeiculo;
import model.Cliente;
import model.Locacao;
import model.Veiculo;
import utils.Entrada;
import utils.Formularios;
import utils.Listagens;
import utils.Menus;

import java.time.LocalDate;

public class InserirRegistros {
    private final ControllerCliente controllerCliente = new ControllerCliente();
    private final ControllerVeiculo controllerVeiculo = new ControllerVeiculo();
    private final ControllerLocacao controllerLocacao = new ControllerLocacao();

    public void menu() {
        while (true) {
            Menus.entidades("Inserir");
            int entidade = Entrada.lerInt("Opção: ");

            switch (entidade) {
                case 1 -> inserirCliente();
                case 2 -> inserirVeiculo();
                case 3 -> inserirLocacao();
                case 0 -> { return; }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

           if (!Entrada.lerSimNao("\nDeseja inserir mais algum registro?")) {
                return;
            }
        }
    }

    private void inserirCliente() {
        System.out.println("\n--- Novo cliente ---");
        Cliente cliente = Formularios.lerCliente(0);
        controllerCliente.inserir(cliente);
    }

    private void inserirVeiculo() {
        System.out.println("\n--- Novo veículo ---");
        Veiculo veiculo = Formularios.lerVeiculo(0);
        controllerVeiculo.inserir(veiculo);
	System.out.println("Cliente inserido com sucesso!");
    }

    private void inserirLocacao() {
        System.out.println("\n--- Nova locação ---");

        if (!Listagens.clientes()) {
            System.out.println("Cadastre um cliente antes de registrar uma locação.");
            return;
        }
        int idCliente = Entrada.lerInt("ID do cliente: ");
        Cliente cliente = controllerCliente.buscarPorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        if (!Listagens.veiculos()) {
            System.out.println("Cadastre um veículo antes de registrar uma locação.");
            return;
        }
        int idVeiculo = Entrada.lerInt("ID do veículo: ");
        Veiculo veiculo = controllerVeiculo.buscarPorId(idVeiculo);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        LocalDate retirada = Entrada.lerData("Data de retirada");
        LocalDate prevista = Entrada.lerData("Data de devolução prevista");
        if (prevista.isBefore(retirada)) {
            System.out.println("A devolução prevista não pode ser anterior à retirada. Locação não cadastrada.");
            return;
        }

        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setDataRetirada(retirada);
        locacao.setDataDevolucaoPrevista(prevista);
        controllerLocacao.inserir(locacao);
    }
}
