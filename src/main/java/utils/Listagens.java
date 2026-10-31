package utils;

import controller.ControllerCliente;
import controller.ControllerLocacao;
import controller.ControllerVeiculo;
import model.Cliente;
import model.Locacao;
import model.Veiculo;

import java.util.List;

public class Listagens {

    private static final ControllerCliente controllerCliente =
            new ControllerCliente();

    private static final ControllerVeiculo controllerVeiculo =
            new ControllerVeiculo();

    private static final ControllerLocacao controllerLocacao =
            new ControllerLocacao();

    public static boolean clientes() {
        List<Cliente> lista = controllerCliente.listar();

        System.out.println("\n--- Clientes ---");

        if (lista.isEmpty()) {
            System.out.println("(nenhum cliente cadastrado)");
            return false;
        }

        for (Cliente c : lista) {
            System.out.println(
                c.getIdCliente() + " - " + c.getNome()
                + " (CPF " + c.getCpf() + ")"
            );
        }

        return true;
    }

    public static boolean veiculos() {
        List<Veiculo> lista = controllerVeiculo.listar();

        System.out.println("\n--- Veículos ---");

        if (lista.isEmpty()) {
            System.out.println("(nenhum veículo cadastrado)");
            return false;
        }

        for (Veiculo v : lista) {
            System.out.println(
                v.getIdVeiculo() + " - " + v.getPlaca()
                + " (" + v.getMarca() + " " + v.getModelo() + ")"
                + (v.isDisponivel() ? "" : " [indisponível]")
            );
        }

        return true;
    }

    public static boolean locacoes() {
        List<Locacao> lista = controllerLocacao.listar();

        System.out.println("\n--- Locações ---");

        if (lista.isEmpty()) {
            System.out.println("(nenhuma locação cadastrada)");
            return false;
        }

        for (Locacao l : lista) {
            System.out.println(
                l.getIdLocacao() + " - "
                + l.getCliente().getNome() + " / "
                + l.getVeiculo().getPlaca() + " / retirada em "
                + Formato.data(l.getDataRetirada())
                + (l.getDataDevolucao() == null
                   ? " [em aberto]" : "")
            );
        }

        return true;
    }
}
