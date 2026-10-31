package controller;

import conexion.ConexaoBanco;
import model.Cliente;
import utils.Sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerCliente {

    public int contar() {
        String sql = "SELECT COUNT(1) total_clientes FROM clientes";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            int total = 0;
            if (rs.next()) {
                total = rs.getInt("total_clientes");
            }
            rs.close();
            st.close();
            con.close();
            return total;
        } catch (SQLException e) {
            System.out.println("Erro ao contar clientes: " + e.getMessage());
            return 0;
        }
    }

    public boolean existe(int idCliente) {
        String sql = "SELECT COUNT(1) total FROM clientes WHERE id_cliente = " + idCliente;
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
            System.out.println("Erro ao verificar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, nome, cpf, telefone, cnh FROM clientes ORDER BY id_cliente";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("cnh")
                );
                lista.add(c);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }

    public Cliente buscarPorId(int idCliente) {
        String sql = "SELECT id_cliente, nome, cpf, telefone, cnh FROM clientes WHERE id_cliente = " + idCliente;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConexaoBanco.getConexao();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Cliente c = null;
            if (rs.next()) {
                c = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("cnh")
                );
            }
            rs.close();
            st.close();
            con.close();
            return c;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por ID: " + e.getMessage());
            return null;
        }
    }

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, cnh) VALUES ("
                + Sql.texto(cliente.getNome()) + ", "
                + Sql.texto(cliente.getCpf()) + ", "
                + Sql.texto(cliente.getTelefone()) + ", "
                + Sql.texto(cliente.getCnh()) + ")";

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
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET "
                + "nome = " + Sql.texto(cliente.getNome()) + ", "
                + "cpf = " + Sql.texto(cliente.getCpf()) + ", "
                + "telefone = " + Sql.texto(cliente.getTelefone()) + ", "
                + "cnh = " + Sql.texto(cliente.getCnh()) + " "
                + "WHERE id_cliente = " + cliente.getIdCliente();

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
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean temLocacoes(int idCliente) {
        String sql = "SELECT COUNT(1) total FROM locacoes WHERE id_cliente = " + idCliente;
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
            System.out.println("Erro ao verificar vinculos do cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = " + idCliente;
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
            System.out.println("Erro ao remover cliente: " + e.getMessage());
            return false;
        }
    }
}
