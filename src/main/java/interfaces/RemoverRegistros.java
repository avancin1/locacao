package interfaces;

import controller.ControllerCliente;
import controller.ControllerLocacao;
import controller.ControllerVeiculo;
import model.Cliente;
import model.Locacao;
import model.Veiculo;
import utils.Entrada;
import utils.Listagens;
import utils.Menus;

public class RemoverRegistros {
    private final ControllerCliente controllerCliente = new ControllerCliente();
    private final ControllerVeiculo controllerVeiculo = new ControllerVeiculo();
    private final ControllerLocacao controllerLocacao = new ControllerLocacao();

    public void menu() {
        while (true) {
            Menus.entidades("Remover");
            int entidade = Entrada.lerInt("Opção: ");

            boolean seguir;
            switch (entidade) {
                case 1 -> seguir = removerCliente();
                case 2 -> seguir = removerVeiculo();
                case 3 -> seguir = removerLocacao();
                case 0 -> { return; }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            if (!seguir) {
                return;
            }
            if (!Entrada.lerSimNao("\nDeseja remover mais algum registro?")) {
                return;
            }
        }
    }

    private boolean removerCliente() {
        if (!Listagens.clientes()) return false;

        int id = Entrada.lerInt("ID do cliente a remover: ");
        Cliente cliente = controllerCliente.buscarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return true;
        }
        if (!Entrada.lerSimNao("Deseja realmente remover o cliente " + id + " - " + cliente.getNome() + "?")) {
            return false;
        }

      int total = controllerLocacao.contarPorCliente(id);
        if (total > 0) {
            System.out.println("Este cliente não pode ser removido diretamente: tem " + total
                    + " locação(ões) vinculada(s).");
            if (!Entrada.lerSimNao("Deseja remover também as locações desse cliente?")) {
                System.out.println("Cliente não removido.");
                return true;
            }
            if (!controllerLocacao.removerPorCliente(id)) {
                System.out.println("Cliente não removido.");
                return true;
            }
        }
        controllerCliente.remover(id);
	System.out.println("Cliente removido com sucesso!");
        return true;
    }

    private boolean removerVeiculo() {
        if (!Listagens.veiculos()) return false;

        int id = Entrada.lerInt("ID do veículo a remover: ");
        Veiculo veiculo = controllerVeiculo.buscarPorId(id);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return true;
        }
        if (!Entrada.lerSimNao("Deseja realmente remover o veículo " + id + " - " + veiculo.getPlaca() + "?")) {
            return false;
        }

       int total = controllerLocacao.contarPorVeiculo(id);
        if (total > 0) {
            System.out.println("Este veículo não pode ser removido diretamente: tem " + total
                    + " locação(ões) vinculada(s).");
            if (!Entrada.lerSimNao("Deseja remover também as locações desse veículo?")) {
                System.out.println("Veículo não removido.");
                return true;
            }
            if (!controllerLocacao.removerPorVeiculo(id)) {
                System.out.println("Veículo não removido.");
                return true;
            }
        }
        controllerVeiculo.remover(id);
        return true;
    }

    private boolean removerLocacao() {
        if (!Listagens.locacoes()) return false;

        int id = Entrada.lerInt("ID da locação a remover: ");
        Locacao locacao = controllerLocacao.buscarPorId(id);
        if (locacao == null) {
            System.out.println("Locação não encontrada.");
            return true;
        }
        if (!Entrada.lerSimNao("Deseja realmente remover a locação " + id + "?")) {
            return false;
        }
       controllerLocacao.remover(id);
        return true;
    }
}