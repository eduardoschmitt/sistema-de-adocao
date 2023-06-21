package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Adocao;
import model.Animal;
import model.Cor;
import model.Doacao;
import model.Estado;
import model.Pessoa;

public class AdocaoDao {
	public static List<Adocao> get() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Adocao> adocoes = new ArrayList<>();
			st = connection.prepareStatement("select ad.id_adocao id, ad.data_adocao, "
					+ "an.id idAnimal, an.nome nomeAnimal, "
					+ "p.id idPessoa, p.nome nomePessoa  from adocoes ad "
					+ "left join animais an on ad.id_animal = an.ID "
					+ "left join pessoa p on ad.id_pessoa = p.id ");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Animal animal = new Animal(rs.getInt("idAnimal"), rs.getString("nomeAnimal"));
				Pessoa pessoa = new Pessoa(rs.getInt("idPessoa"), rs.getString("nomePessoa"));
				Adocao adocao = new Adocao(rs.getInt("id"), animal, pessoa, rs.getDate("data_adocao"));
				adocoes.add(adocao);
			}
			return adocoes;
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
			st = connection.prepareStatement("delete from adocoes where id_adocao=?");
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
	
	public static void insert(Adocao adocao) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into adocoes (id_animal, id_pessoa, data_adocao) values (?, ?, ?)");
			st.setInt(1, adocao.getAnimal().getId());
			st.setInt(2, adocao.getPessoa().getId());
			st.setDate(3, adocao.getDataAdocao());
			st.execute();
		}catch(Exception e) {
			System.out.println(e.toString());
			throw new Exception(e);
		}
	}
	
}
