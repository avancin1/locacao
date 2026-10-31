package controller;

import conexion.ConexaoBanco;
import model.Veiculo;
import utils.Sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerVeiculo {

    public int contar() {
        String sql = "SELECT COUNT(1) total_veiculos FROM veiculos";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            int total = 0;
            if (rs.next()) {
                total = rs.getInt("total_veiculos");
            }
            rs.close();
            st.close();
            con.close();
            return total;
        } catch (SQLException e) {
            System.out.println("Erro ao contar veiculos: " + e.getMessage());
            return 0;
        }
    }

    public boolean existe(int idVeiculo) {
        String sql = "SELECT COUNT(1) total FROM veiculos WHERE id_veiculo = " + idVeiculo;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            boolean achou = false;
            if (rs.next()) {
                achou = rs.getInt("total") > 0;
            }
            rs.close();
            st.close();
            con.close();
            return achou;
        } catch (SQLException e) {
            System.out.println("Erro ao verificar veiculo: " + e.getMessage());
            return false;
        }
    }

    public List<Veiculo> listar() {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT id_veiculo, placa, modelo, marca, ano, valor_diaria, disponivel FROM veiculos ORDER BY id_veiculo";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Veiculo v = new Veiculo(
                    rs.getInt("id_veiculo"),
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getInt("ano"),
                    rs.getDouble("valor_diaria"),
                    "S".equalsIgnoreCase(rs.getString("disponivel"))
                );
                lista.add(v);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar veiculos: " + e.getMessage());
        }
        return lista;
    }

    public Veiculo buscarPorId(int idVeiculo) {
        String sql = "SELECT id_veiculo, placa, modelo, marca, ano, valor_diaria, disponivel FROM veiculos WHERE id_veiculo = " + idVeiculo;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Veiculo v = null;
            if (rs.next()) {
                v = new Veiculo(
                    rs.getInt("id_veiculo"),
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getInt("ano"),
                    rs.getDouble("valor_diaria"),
                    "S".equalsIgnoreCase(rs.getString("disponivel"))
                );
            }
            rs.close();
            st.close();
            con.close();
            return v;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veiculo por ID: " + e.getMessage());
            return null;
        }
    }

    public boolean inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculos (placa, modelo, marca, ano, valor_diaria, disponivel) VALUES ("
                + Sql.texto(veiculo.getPlaca()) + ", "
                + Sql.texto(veiculo.getModelo()) + ", "
                + Sql.texto(veiculo.getMarca()) + ", "
                + veiculo.getAno() + ", "
                + veiculo.getValorDiaria() + ", "
                + Sql.texto(veiculo.isDisponivel() ? "S" : "N") + ")";

        Connection con = null;
        Statement st = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            int res = st.executeUpdate(sql);
            st.close();
            con.close();
            return res > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir veiculo: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Veiculo veiculo) {
        String sql = "UPDATE veiculos SET "
                + "placa = " + Sql.texto(veiculo.getPlaca()) + ", "
                + "modelo = " + Sql.texto(veiculo.getModelo()) + ", "
                + "marca = " + Sql.texto(veiculo.getMarca()) + ", "
                + "ano = " + veiculo.getAno() + ", "
                + "valor_diaria = " + veiculo.getValorDiaria() + ", "
                + "disponivel = " + Sql.texto(veiculo.isDisponivel() ? "S" : "N") + " "
                + "WHERE id_veiculo = " + veiculo.getIdVeiculo();

        Connection con = null;
        Statement st = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            int res = st.executeUpdate(sql);
            st.close();
            con.close();
            return res > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veiculo: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarDisponibilidade(int idVeiculo, boolean disponivel) {
        String sql = "UPDATE veiculos SET disponivel = "
                + Sql.texto(disponivel ? "S" : "N")
                + " WHERE id_veiculo = " + idVeiculo;

        Connection con = null;
        Statement st = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            int res = st.executeUpdate(sql);
            st.close();
            con.close();
            return res > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar disponibilidade: " + e.getMessage());
            return false;
        }
    }

    public boolean temLocacoes(int idVeiculo) {
        String sql = "SELECT COUNT(1) total FROM locacoes WHERE id_veiculo = " + idVeiculo;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            boolean tem = false;
            if (rs.next()) {
                tem = rs.getInt("total") > 0;
            }
            rs.close();
            st.close();
            con.close();
            return tem;
        } catch (SQLException e) {
            System.out.println("Erro ao verificar locacoes do veiculo: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int idVeiculo) {
        String sql = "DELETE FROM veiculos WHERE id_veiculo = " + idVeiculo;
        Connection con = null;
        Statement st = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            int res = st.executeUpdate(sql);
            st.close();
            con.close();
            return res > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover veiculo: " + e.getMessage());
            return false;
        }
    }
}