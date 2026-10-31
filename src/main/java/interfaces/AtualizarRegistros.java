package interfaces;

import controller.ControllerCliente;
import controller.ControllerLocacao;
import controller.ControllerVeiculo;
import model.Cliente;
import model.Locacao;
import model.Veiculo;
import utils.Entrada;
import utils.Formato;
import utils.Formularios;
import utils.Listagens;
import utils.Menus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AtualizarRegistros {
    private final ControllerCliente controllerCliente = new ControllerCliente();
    private final ControllerVeiculo controllerVeiculo = new ControllerVeiculo();
    private final ControllerLocacao controllerLocacao = new ControllerLocacao();

    public void menu() {
        while (true) {
            Menus.entidades("Atualizar");
            int entidade = Entrada.lerInt("Opção: ");

            boolean seguir;
            switch (entidade) {
                case 1 -> seguir = atualizarCliente();
                case 2 -> seguir = atualizarVeiculo();
                case 3 -> seguir = atualizarLocacao();
                case 0 -> { return; }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

           if (!seguir) {
                return;
            }
           if (!Entrada.lerSimNao("\nDeseja atualizar mais algum registro?")) {
                return;
            }
        }
    }

    private boolean atualizarCliente() {
        if (!Listagens.clientes()) return false;

        int id = Entrada.lerInt("ID do cliente a atualizar: ");
        Cliente atual = controllerCliente.buscarPorId(id);
        if (atual == null) {
            System.out.println("Cliente não encontrado.");
            return true;
        }

        System.out.println("Registro atual: " + atual);
        System.out.println("Informe os novos dados (o ID não pode ser alterado):");
        Cliente novo = Formularios.lerCliente(id);

        if (controllerCliente.atualizar(novo)) {
            System.out.println("Registro atualizado: " + controllerCliente.buscarPorId(id));
        }
        return true;
    }

    private boolean atualizarVeiculo() {
        if (!Listagens.veiculos()) return false;

        int id = Entrada.lerInt("ID do veículo a atualizar: ");
        Veiculo atual = controllerVeiculo.buscarPorId(id);
        if (atual == null) {
            System.out.println("Veículo não encontrado.");
            return true;
        }

        System.out.println("Registro atual: " + atual);
        System.out.println("Informe os novos dados (o ID não pode ser alterado):");
        Veiculo novo = Formularios.lerVeiculo(id);

        if (controllerVeiculo.atualizar(novo)) {
            System.out.println("Registro atualizado: " + controllerVeiculo.buscarPorId(id));
        }
        return true;
    }

    private boolean atualizarLocacao() {
        if (!Listagens.locacoes()) return false;

        int id = Entrada.lerInt("ID da locação a atualizar: ");
        Locacao locacao = controllerLocacao.buscarPorId(id);
        if (locacao == null) {
            System.out.println("Locação não encontrada.");
            return true;
        }

        System.out.println("Registro atual: " + locacao);
        System.out.println("Informe os novos dados (cliente e veículo da locação não mudam):");
        LocalDate retirada = Entrada.lerData("Nova data de retirada");
        LocalDate prevista = Entrada.lerData("Nova data de devolução prevista");
        if (prevista.isBefore(retirada)) {
            System.out.println("A devolução prevista não pode ser anterior à retirada. Nada foi alterado.");
            return true;
        }
        LocalDate devolucao = Entrada.lerDataOpcional("Data de devolução real");
        if (devolucao != null && devolucao.isBefore(retirada)) {
            System.out.println("A devolução não pode ser anterior à retirada. Nada foi alterado.");
            return true;
        }

        locacao.setDataRetirada(retirada);
        locacao.setDataDevolucaoPrevista(prevista);
        locacao.setDataDevolucao(devolucao);

        if (devolucao == null) {
            locacao.setValorTotal(null);
        } else {
            long dias = Math.max(1, ChronoUnit.DAYS.between(retirada, devolucao));
            locacao.setValorTotal(dias * locacao.getVeiculo().getValorDiaria());
            System.out.println("Valor total calculado: " + dias + " dia(s) x "
                    + Formato.dinheiro(locacao.getVeiculo().getValorDiaria())
                    + " = " + Formato.dinheiro(locacao.getValorTotal()));
        }

        if (controllerLocacao.atualizar(locacao)) {
            System.out.println("Registro atualizado: " + controllerLocacao.buscarPorId(id));
        }
        return true;
    }
}
