import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) {
        Connection connection = Conexao.getConexao();
        
        try {
            if (connection != null) {
            	String sql = "SELECT * FROM ANIMAIS";
                PreparedStatement statement = connection.prepareStatement(sql);
                
                // Executar a consulta e obter o resultado
                ResultSet resultSet = statement.executeQuery();
                
                // Processar os dados do resultado
                while (resultSet.next()) {
                    String nome = resultSet.getString("Nome");
                    // Extrair os outros campos conforme necessário
                    
                    System.out.println("Nome: " + nome);
                }
                
                // Fechar o ResultSet, Statement e Connection
                resultSet.close();
                statement.close();
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
        }
    }
    
    
}
