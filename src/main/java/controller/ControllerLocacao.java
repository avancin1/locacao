package controller;

import conexion.ConexaoBanco;
import model.Locacao;
import utils.Sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerLocacao {

    private static final String COLUNAS = "id_locacao, id_cliente, id_veiculo, data_retirada, "
            + "data_devolucao_prevista, data_devolucao, valor_total";

    private final ControllerCliente controllerCliente = new ControllerCliente();
    private final ControllerVeiculo controllerVeiculo = new ControllerVeiculo();

    public int contar() {
        String sql = "SELECT COUNT(1) total_locacoes FROM locacoes";
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total_locacoes") : 0;
        } catch (SQLException e) {
            System.out.println("Erro ao contar locações: " + e.getMessage());
            return 0;
        }
    }

    public boolean existe(int idLocacao) {
        String sql = "SELECT COUNT(1) total FROM locacoes WHERE id_locacao = " + idLocacao;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() && rs.getInt("total") > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao verificar locação: " + e.getMessage());
            return false;
        }
    }

    public boolean inserir(Locacao l) {
        String sql = "INSERT INTO locacoes "
                + "(id_cliente, id_veiculo, data_retirada, data_devolucao_prevista, data_devolucao, valor_total) "
                + "VALUES ("
                + l.getCliente().getIdCliente() + ", "
                + l.getVeiculo().getIdVeiculo() + ", "
                + Sql.data(l.getDataRetirada()) + ", "
                + Sql.data(l.getDataDevolucaoPrevista()) + ", "
                + Sql.data(l.getDataDevolucao()) + ", "
                + Sql.numero(l.getValorTotal()) + ")";
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Locação inserida com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir locação: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Locacao l) {
        String sql = "UPDATE locacoes SET "
                + "id_cliente = " + l.getCliente().getIdCliente() + ", "
                + "id_veiculo = " + l.getVeiculo().getIdVeiculo() + ", "
                + "data_retirada = " + Sql.data(l.getDataRetirada()) + ", "
                + "data_devolucao_prevista = " + Sql.data(l.getDataDevolucaoPrevista()) + ", "
                + "data_devolucao = " + Sql.data(l.getDataDevolucao()) + ", "
                + "valor_total = " + Sql.numero(l.getValorTotal()) + " "
                + "WHERE id_locacao = " + l.getIdLocacao();
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Locação atualizada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar locação: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int idLocacao) {
        String sql = "DELETE FROM locacoes WHERE id_locacao = " + idLocacao;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Locação removida com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover locação: " + e.getMessage());
            return false;
        }
    }

    public List<Locacao> listar() {
        List<Locacao> lista = new ArrayList<>();
        String sql = "SELECT " + COLUNAS + " FROM locacoes ORDER BY id_locacao";
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(montarLocacao(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar locações: " + e.getMessage());
        }
        return lista;
    }

    public Locacao buscarPorId(int idLocacao) {
        String sql = "SELECT " + COLUNAS + " FROM locacoes WHERE id_locacao = " + idLocacao;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return montarLocacao(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar locação: " + e.getMessage());
        }
        return null;
    }

    public int contarPorCliente(int idCliente) {
        String sql = "SELECT COUNT(1) total FROM locacoes WHERE id_cliente = " + idCliente;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) {
            System.out.println("Erro ao contar locações do cliente: " + e.getMessage());
            return 0;
        }
    }

    public int contarPorVeiculo(int idVeiculo) {
        String sql = "SELECT COUNT(1) total FROM locacoes WHERE id_veiculo = " + idVeiculo;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) {
            System.out.println("Erro ao contar locações do veículo: " + e.getMessage());
            return 0;
        }
    }

    public boolean removerPorCliente(int idCliente) {
        String sql = "DELETE FROM locacoes WHERE id_cliente = " + idCliente;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover locações do cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean removerPorVeiculo(int idVeiculo) {
        String sql = "DELETE FROM locacoes WHERE id_veiculo = " + idVeiculo;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover locações do veículo: " + e.getMessage());
            return false;
        }
    }

    private Locacao montarLocacao(ResultSet rs) throws SQLException {
        Locacao l = new Locacao();
        l.setIdLocacao(rs.getInt("id_locacao"));
        l.setCliente(controllerCliente.buscarPorId(rs.getInt("id_cliente")));
        l.setVeiculo(controllerVeiculo.buscarPorId(rs.getInt("id_veiculo")));
        l.setDataRetirada(rs.getDate("data_retirada").toLocalDate());
        l.setDataDevolucaoPrevista(rs.getDate("data_devolucao_prevista").toLocalDate());
        if (rs.getDate("data_devolucao") != null) {
            l.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
        }
        if (rs.getObject("valor_total") != null) {
            l.setValorTotal(rs.getDouble("valor_total"));
        }
        return l;
    }
}
