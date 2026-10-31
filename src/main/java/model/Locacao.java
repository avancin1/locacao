package model;

import java.time.LocalDate;
import utils.Formato;

public class Locacao {

    private int idLocacao;

   
    private Cliente cliente;
    private Veiculo veiculo;

    private LocalDate dataRetirada;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucao;
    private Double valorTotal;

    public Locacao() {
    }

    public Locacao(int idLocacao, Cliente cliente, Veiculo veiculo,
                   LocalDate dataRetirada,
                   LocalDate dataDevolucaoPrevista,
                   LocalDate dataDevolucao, Double valorTotal) {
        this.idLocacao = idLocacao;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucao = dataDevolucao;
        this.valorTotal = valorTotal;
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate data) {
        this.dataDevolucaoPrevista = data;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Locacao [id=" + idLocacao
                + ", cliente="
                + (cliente != null ? cliente.getNome() : null)
                + ", veiculo="
                + (veiculo != null ? veiculo.getPlaca() : null)
                + ", retirada=" + Formato.data(dataRetirada)
                + ", prevista=" + Formato.data(dataDevolucaoPrevista)
                + ", devolucao=" + Formato.data(dataDevolucao)
                + ", total=" + Formato.dinheiro(valorTotal)
                + "]";
    }
}