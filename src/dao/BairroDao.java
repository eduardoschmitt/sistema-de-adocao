package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Bairro;
import model.Cidade;
import model.Estado;

public class BairroDao {
	public static List<Bairro> get() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Bairro> bairros = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			sb.append("select bai.id, bai.nome nomeBairro, cid.nome nomeCidade, est.sigla, est.nome nomeEstado, cid.estado_id, bai.id_cidade from bairro bai ");
			sb.append(" left join cidade cid on bai.id_cidade=cid.id ");
			sb.append("left join estado est on cid.Estado_id = est.id");
			st = connection.prepareStatement(sb.toString());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Estado estado = new Estado(rs.getInt("estado_id"), rs.getString("sigla"), rs.getString("nomeEstado"));
				Cidade cidade = new Cidade(rs.getInt("id_cidade"), estado, rs.getString("nomeCidade"));
				Bairro bairro = new Bairro(rs.getInt("id"), rs.getString("nomeBairro"), cidade, estado);
				
				bairros.add(bairro);
			}
			return bairros;
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
			st = connection.prepareStatement("delete from bairro where id=?");
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
	
	public static void insert(Bairro bairro) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into bairro (id_cidade, nome) values (?,?)");
			st.setInt(1, bairro.getCidade().getId());
			st.setString(2, bairro.getNome());
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void update(int id, Bairro bairro) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("update bairro set id_cidade=?, nome=? where id=?");
			st.setInt(1, bairro.getCidade().getId());
			st.setString(2, bairro.getNome());
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
