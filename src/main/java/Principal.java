import controller.ControllerCliente;
import controller.ControllerLocacao;
import controller.ControllerVeiculo;
import conexion.ConexaoBanco;
import interfaces.AtualizarRegistros;
import interfaces.InserirRegistros;
import interfaces.RemoverRegistros;
import reports.Relatorios;
import utils.Entrada;
import utils.Menus;
import utils.SplashScreen;

public class Principal {

    public static void main(String[] args) {
        if (!ConexaoBanco.testar()) {
            return;
        }

        SplashScreen.exibir(new ControllerCliente().contar(),
                            new ControllerVeiculo().contar(),
                            new ControllerLocacao().contar());

        Relatorios relatorios = new Relatorios();
        InserirRegistros inserir = new InserirRegistros();
        RemoverRegistros remover = new RemoverRegistros();
        AtualizarRegistros atualizar = new AtualizarRegistros();

        int opcao;
        do {
            Menus.principal();
            opcao = Entrada.lerInt("Opção: ");
            switch (opcao) {
                case 1 -> relatorios.menu();
                case 2 -> inserir.menu();
                case 3 -> remover.menu();
                case 4 -> atualizar.menu();
                case 5 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }
}