package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Cidade;
import model.Especies;

public class EspeciesDao {
	public static List<Especies> get() throws Exception {
		Connection connection = Conexao.getConexao();
		PreparedStatement st = null;
		try {
			List<Especies> especies = new ArrayList<>();
			st = connection.prepareStatement("select ID, Nome_Especie from especies");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Especies especie = new Especies(rs.getInt("ID"), rs.getString("Nome_Especie"));
				especies.add(especie);
			}
			return especies;
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void insert(Especies especies) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			System.out.println("Deu certo.");
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into especies (nome_especie) values (?)");
			st.setString(1, especies.getNome_especie());
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void update(int id, Especies especies) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("update especies set nome_especie=? where id=?");
			st.setString(1, especies.getNome_especie());
			st.setInt(2, id);
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			if(connection!=null) {
				connection.close();
			}
			if(st!=null) {
				System.out.println("Registro atualizado");
				st.close();
			}
		}
	}
	
	public static void delete(int id) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("delete from especies where id=?");
			st.setInt(1, id);
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			if(connection!=null) {
				connection.close();
			}
			if(st!=null) {
				System.out.println("Dado deletado.");
				st.close();
			}
		}
	}
	
}
