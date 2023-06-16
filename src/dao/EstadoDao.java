package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Estado;

public class EstadoDao {
	public static List<Estado> get() throws Exception {
		Connection connection = Conexao.getConexao();
		PreparedStatement st = null;
		try {
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
		}
	}
	
	public static void delete(int id) throws Exception {
		Connection connection = Conexao.getConexao();
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("delete from estado where id=?");
			st.setInt(1, id);
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void insert(Estado estado) throws Exception {
		Connection connection = Conexao.getConexao();
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("insert into estado (sigla, nome) values (?,?)");
			st.setString(1, estado.getSigla());
			st.setString(2, estado.getNome());
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void update(int id, Estado estado) throws Exception {
		Connection connection = Conexao.getConexao();
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("update estado set sigla=?, nome=? where id=?");
			st.setString(1, estado.getSigla());
			st.setString(2, estado.getNome());
			st.setInt(3, id);
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	
}
