package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao.Conexao;
import model.Cidade;
import model.Especies;
import model.Racas;

public class RacasDao {
	public static List<Racas> get() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Racas> raca = new ArrayList<>();
			st = connection.prepareStatement("select ra.id, ra.nome, esp.nome_especie from racas ra "
					+ " left join especies esp on ra.id_especies = esp.id ");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Especies especies = new Especies(rs.getInt("ID"), rs.getString("Nome_Especie"));
				Racas racas = new Racas(rs.getInt("id"), especies, rs.getString("nome"));
				raca.add(racas);
			}
			return raca;
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
	
	public static List<Racas> get(int idEspecie) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Racas> raca = new ArrayList<>();
			st = connection.prepareStatement("select ra.id, ra.nome, esp.nome_especie, esp.id idEspecie from racas ra "
					+ " left join especies esp on ra.id_especies = esp.id "
					+ " where id_especies=?");
			st.setInt(1, idEspecie);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Especies especies = new Especies(rs.getInt("idEspecie"), rs.getString("Nome_Especie"));
				Racas racas = new Racas(rs.getInt("id"), especies, rs.getString("nome"));
				raca.add(racas);
			}
			return raca;
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
			st = connection.prepareStatement("delete from racas where id=?");
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
	
	public static void insert(Racas racas) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			System.out.println("Chegou aqui");
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into racas (nome, id_especies) values (?,?)");
			st.setString(1, racas.getNome());
			st.setInt(2, racas.getEspecies().getId());
			System.out.println("Espécie id: " + racas.getEspecies().getId());
			System.out.println("Nome Raça: " + racas.getNome());
			st.execute();
		}catch(Exception e) {
			System.err.println("Erro durante a inserção de raças: " + e.getMessage());
	        throw new Exception("Erro durante a inserção de raças", e);
		}
	}
	
	public static void update(int id, Racas racas) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("update racas set id_especies = ?, nome = ? where id = ?");
			st.setInt(1, racas.getEspecies().getId());
			st.setString(2, racas.getNome());
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
