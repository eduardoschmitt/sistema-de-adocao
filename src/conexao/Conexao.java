package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConexao() {
        String url = "jdbc:mysql://localhost/sysadocao";
        String usuario = "root";
        String senha = "12345";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            System.out.println("Não foi possível realizar a conexão.");
        }

        return connection;
    }
	
}
