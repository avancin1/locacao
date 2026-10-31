package conexion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConexaoBanco {

    private static final Map<String, String> ENV = carregarEnv();

    private static final String URL = valor("DB_URL", "jdbc:postgresql://localhost:5432/locadora");
    private static final String USUARIO = valor("DB_USER", "postgres");
    private static final String SENHA = valor("DB_PASSWORD", null);

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static boolean testar() {
        try (Connection con = getConexao()) {
            return con.isValid(5);
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar ao banco de dados.");
            System.out.println("Motivo: " + e.getMessage());
            System.out.println();
            System.out.println("Confira se:");
            System.out.println(" - o PostgreSQL está em execução;");
            System.out.println(" - o banco 'locadora' existe e as tabelas foram criadas (sql/criacao_tabelas.sql);");
            System.out.println(" - o arquivo .env existe na raiz do projeto e tem DB_PASSWORD=sua_senha");
            System.out.println("   (copie o .env.example para .env e edite) e o programa foi executado a partir dessa pasta.");
            return false;
        }
    }

   private static String valor(String nome, String padrao) {
        String v = System.getenv(nome);
        if (v == null || v.isBlank()) v = ENV.get(nome);
        return (v == null || v.isBlank()) ? padrao : v;
    }

   private static Map<String, String> carregarEnv() {
        Map<String, String> mapa = new HashMap<>();
        Path arquivo = Path.of(".env");
        if (!Files.isRegularFile(arquivo)) return mapa;
        try {
            List<String> linhas = Files.readAllLines(arquivo);
            for (String linha : linhas) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) continue;
                if (linha.startsWith("export ")) linha = linha.substring(7).trim();
                int igual = linha.indexOf('=');
                if (igual <= 0) continue;
                String chave = linha.substring(0, igual).trim();
                String valor = linha.substring(igual + 1).trim();
                if (valor.length() >= 2
                        && ((valor.startsWith("\"") && valor.endsWith("\""))
                         || (valor.startsWith("'") && valor.endsWith("'")))) {
                    valor = valor.substring(1, valor.length() - 1);
                }
                mapa.put(chave, valor);
            }
        } catch (IOException e) {
            System.out.println("Aviso: não consegui ler o arquivo .env (" + e.getMessage() + ").");
        }
        return mapa;
    }
}
