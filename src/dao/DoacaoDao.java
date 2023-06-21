package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Cor;
import model.Doacao;
import model.Estado;
import model.Pessoa;

public class DoacaoDao {
	public static List<Doacao> get() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Doacao> doacoes = new ArrayList<>();
			st = connection.prepareStatement("select d.id, valor, data, p.id idPessoa, p.nome from doacoes d "
					+ " left join pessoa p on d.id_pessoa = p.id");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Pessoa pessoa = new Pessoa(rs.getInt("idPessoa"), rs.getString("nome"));
				Doacao doacao = new Doacao(rs.getInt("id"), rs.getDouble("valor"), rs.getDate("data"), pessoa);
				doacoes.add(doacao);
			}
			return doacoes;
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
			st = connection.prepareStatement("delete from doacoes where id=?");
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
	
	public static void insert(Doacao doacao) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into doacoes (valor, data, id_pessoa) values (?, ?, ?)");
			st.setDouble(1, doacao.getValor());
			st.setDate(2, doacao.getData());
			st.setInt(3, doacao.getPessoa().getId());
			st.execute();
		}catch(Exception e) {
			System.out.println(e.toString());
			throw new Exception(e);
		}
	}
	
}
