package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao.Conexao;
import model.Animal;
import model.Cidade;
import model.Cor;
import model.Especies;
import model.Estado;
import model.Racas;

public class AnimalDao {
	public static List<Animal> get() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Animal> animais = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT a.id, a.nome nomeAnimal, a.sexo, a.descricao, a.nascimento, ");
			sb.append("c.id idCor, c.nome nomeCor, ");
			sb.append("r.id idRaca, r.nome nomeRaca, ");
			sb.append("(select coalesce(1,0) from adocoes adoc where adoc.id_animal=a.id) as adotado, ");
			sb.append("e.id idEspecie, e.nome_especie nomeEspecie ");
			sb.append("FROM animais a ");
			sb.append("left join cores c on c.id = a.id_cor ");
			sb.append("left join racas r on r.id = a.id_raca ");
			sb.append("left join especies e on e.id = r.id_especies ");
			st = connection.prepareStatement(sb.toString());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Especies especie = new Especies(rs.getInt("idEspecie"), rs.getString("nomeEspecie"));
				Racas raca = new Racas(rs.getInt("idRaca"), especie, rs.getString("nomeRaca"));
				Cor cor = new Cor(rs.getInt("idCor"), rs.getString("nomeCor"));
				Animal animal = new Animal(rs.getInt("id"), rs.getString("nomeAnimal"), rs.getString("sexo"), 
						rs.getString("descricao"), cor, raca, rs.getDate("nascimento"), rs.getBoolean("adotado"));
				animais.add(animal);
			}
			return animais;
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
	
	public static List<Animal> getNaoAdotados() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Animal> animais = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT a.id, a.nome nomeAnimal, a.sexo, a.descricao, a.nascimento, ");
			sb.append("c.id idCor, c.nome nomeCor, ");
			sb.append("r.id idRaca, r.nome nomeRaca, ");
			sb.append("e.id idEspecie, e.nome_especie nomeEspecie ");
			sb.append("FROM animais a ");
			sb.append("left join cores c on c.id = a.id_cor ");
			sb.append("left join racas r on r.id = a.id_raca ");
			sb.append("left join especies e on e.id = r.id_especies ");
			sb.append("where a.id not in (select id_animal from adocoes)");
			st = connection.prepareStatement(sb.toString());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Especies especie = new Especies(rs.getInt("idEspecie"), rs.getString("nomeEspecie"));
				Racas raca = new Racas(rs.getInt("idRaca"), especie, rs.getString("nomeRaca"));
				Cor cor = new Cor(rs.getInt("idCor"), rs.getString("nomeCor"));
				Animal animal = new Animal(rs.getInt("id"), rs.getString("nomeAnimal"), rs.getString("sexo"), 
						rs.getString("descricao"), cor, raca, rs.getDate("nascimento"), false);
				animais.add(animal);
			}
			return animais;
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
			st = connection.prepareStatement("delete from animais where id=?");
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
	
	public static void insert(Animal animal) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into animais (nome, sexo, descricao, id_cor, id_raca, nascimento) values (?,?,?,?,?,?)");
			st.setString(1, animal.getNome());
			st.setString(2, animal.getSexo());
			st.setString(3, animal.getDescricao());
			st.setInt(4, animal.getCor().getId());
			st.setInt(5, animal.getRaca().getId());
			st.setDate(6, animal.getNascimento());
			st.execute();
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void update(int id, Animal animal) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("update animais set nome=?, sexo=?, descricao=?, id_cor=?, id_raca=?, nascimento=? where id=?");
			st.setString(1, animal.getNome());
			st.setString(2, animal.getSexo());
			st.setString(3, animal.getDescricao());
			st.setInt(4, animal.getCor().getId());
			st.setInt(5, animal.getRaca().getId());
			st.setDate(6, animal.getNascimento());
			st.setInt(7, id);
			st.execute();
		}catch(Exception e) {
			e.printStackTrace();
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
