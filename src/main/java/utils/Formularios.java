package utils;

import model.Cliente;
import model.Veiculo;

public class Formularios {

    public static Cliente lerCliente(int idAtual) {
        String nome = Entrada.lerTextoObrigatorio("Nome: ", 100);
        String cpf = Entrada.lerDigitos("CPF (11 dígitos): ", 11);
        String telefone = Entrada.lerTextoOpcional("Telefone (Enter para deixar vazio): ", 15);
        String cnh = Entrada.lerDigitos("CNH (11 dígitos): ", 11);
        return new Cliente(idAtual, nome, cpf, telefone, cnh);
    }

    public static Veiculo lerVeiculo(int idAtual) {
        String placa = Entrada.lerPlaca("Placa: ");
        String modelo = Entrada.lerTextoObrigatorio("Modelo: ", 50);
        String marca = Entrada.lerTextoObrigatorio("Marca: ", 50);
        int anoMaximo = java.time.Year.now().getValue() + 1;
        int ano = Entrada.lerIntEntre("Ano (1990 a " + anoMaximo + "): ", 1990, anoMaximo);
        double valorDiaria = Entrada.lerValorPositivo("Valor da diária (R$): ");
        boolean disponivel = Entrada.lerSimNao("O veículo está disponível?");
        return new Veiculo(idAtual, placa, modelo, marca, ano, valorDiaria, disponivel);
    }
}