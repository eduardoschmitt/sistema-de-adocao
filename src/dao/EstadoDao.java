package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao.Conexao;
import model.Estado;

public class EstadoDao {
	public static List<Estado> get() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Estado> estados = new ArrayList<>();
			st = connection.prepareStatement("select id, sigla, nome from estado");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Estado estado = new Estado(rs.getInt("id"), rs.getString("sigla"), rs.getString("nome"));
				estados.add(estado);
			}
			return estados;
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
			st = connection.prepareStatement("delete from estado where id=?");
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
	
	public static void insert(Estado estado) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into estado (sigla, nome) values (?,?)");
			st.setString(1, estado.getSigla());
			st.setString(2, estado.getNome());
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void update(int id, Estado estado) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("update estado set sigla=?, nome=? where id=?");
			st.setString(1, estado.getSigla());
			st.setString(2, estado.getNome());
			st.setInt(3, id);
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
