package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao.Conexao;
import model.Cor;
import model.Estado;

public class CorDao {
	public static List<Cor> get() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Cor> cores = new ArrayList<>();
			st = connection.prepareStatement("select id, nome from cores");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Cor cor = new Cor(rs.getInt("id"), rs.getString("nome"));
				cores.add(cor);
			}
			return cores;
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			if(connection!=null) {
				connection.close();
			}
			if(st!=null) {
				st.close();
			}
		}
	}
	
	public static void delete(int id) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("delete from cores where id=?");
			st.setInt(1, id);
			st.execute();
	    } catch (SQLException e) {
	        String mensagem = "";
	        if (e.getErrorCode() == 1451) {
	            mensagem = "Não foi possível excluir o registro porque está sendo referenciado por outra tabela.";
	        } else {
	            mensagem = "Ocorreu um erro durante a exclusão do registro.";
	        }
	        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	        throw new Exception(e);
	    } catch (Exception e) {
	        throw new Exception(e);
		}finally {
			if(connection!=null) {
				connection.close();
			}
			if(st!=null) {
				st.close();
			}
		}
	}
	
	public static void insert(Cor cor) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into cores (nome) values (?)");
			st.setString(1, cor.getNome());
			st.execute();
		}catch(Exception e) {
			System.out.println(e.toString());
			throw new Exception(e);
		}
	}
	
	public static void update(int id, Cor cor) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("update cores set nome=? where id=?");
			st.setString(1, cor.getNome());
			st.setInt(2, id);
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			if(connection!=null) {
				connection.close();
			}
			if(st!=null) {
				st.close();
			}
		}
	}
	
	
}
