package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Cidade;
import model.Estado;

public class CidadeDao {
	public static List<Cidade> get() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Cidade> cidades = new ArrayList<>();
			st = connection.prepareStatement("select cid.id, estado_id, est.sigla, est.nome nomeEstado, cid.nome nomeCidade from cidade cid "
					+ " left join estado est on est.id = cid.estado_id ");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Estado estado = new Estado(rs.getInt("estado_id"), rs.getString("sigla"), rs.getString("nomeEstado"));
				Cidade cidade = new Cidade(rs.getInt("id"), estado, rs.getString("nomeCidade"));
				cidades.add(cidade);
			}
			return cidades;
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
	
	public static List<Cidade> get(int idEstado) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Cidade> cidades = new ArrayList<>();
			st = connection.prepareStatement("select cid.id, estado_id, est.sigla, est.nome nomeEstado, cid.nome nomeCidade from cidade cid "
					+ " left join estado est on est.id = cid.estado_id "
					+ " where est.id=? ");
			st.setInt(1, idEstado);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Estado estado = new Estado(rs.getInt("estado_id"), rs.getString("sigla"), rs.getString("nomeEstado"));
				Cidade cidade = new Cidade(rs.getInt("id"), estado, rs.getString("nomeCidade"));
				cidades.add(cidade);
			}
			return cidades;
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
			st = connection.prepareStatement("delete from cidade where id=?");
			st.setInt(1, id);
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
	
	public static void insert(Cidade cidade) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into cidade (estado_id, nome) values (?,?)");
			st.setInt(1, cidade.getEstado().getId());
			st.setString(2, cidade.getNome());
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void update(int id, Cidade cidade) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("update cidade set estado_id=?, nome=? where id=?");
			st.setInt(1, cidade.getEstado().getId());
			st.setString(2, cidade.getNome());
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
