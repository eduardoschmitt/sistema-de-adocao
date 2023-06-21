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
import model.Pessoa;

public class PessoaDao {
	public static List<Pessoa> get() throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			List<Pessoa> pessoas = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT p.id, p.nome, p.numero_telefone, p.email, ");
			sb.append("e.id idEndereco, e.logradouro, e.Complemento, ");
			sb.append("b.id idBairro, b.nome nomeBairro, ");
			sb.append("c.id idCidade, c.nome nomeCidade,");
			sb.append("est.id idEstado, est.nome nomeEstado, est.sigla ");
			sb.append("FROM pessoa p ");
			sb.append("LEFT JOIN endereco e on p.ID_Endereco=e.ID ");
			sb.append("LEFT JOIN bairro b on e.ID_Bairro=b.id ");
			sb.append("LEFT JOIN cidade c on b.id_cidade=c.id ");
			sb.append("LEFT JOIN estado est on c.estado_id=est.id");
			st = connection.prepareStatement(sb.toString());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Estado estado = new Estado(rs.getInt("idEstado"), rs.getString("sigla"), rs.getString("nomeEstado"));
				Cidade cidade = new Cidade(rs.getInt("idCidade"), estado, rs.getString("nomeCidade"));
				Bairro bairro = new Bairro(rs.getInt("idBairro"), rs.getString("nomeBairro"), cidade, estado);
				Pessoa pessoa = new Pessoa(rs.getInt("id"),rs.getString("nome"),rs.getString("numero_telefone"),rs.getString("email"),rs.getString("logradouro"),rs.getString("complemento"),bairro);
				
				pessoas.add(pessoa);
			}
			return pessoas;
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
			st = connection.prepareStatement("delete from pessoa where id=?");
			st.setInt(1, id);
			st.execute();
			st.close();
			st = connection.prepareStatement("delete from endereco where id in (select id_endereco from pessoa p where p.id=?)");
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
	
	public static void insert(Pessoa pessoa) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("insert into endereco(logradouro, complemento, id_bairro) values (?,?,?)");
			st.setString(1, pessoa.getLogradouro());
			st.setString(2, pessoa.getComplemento());
			st.setInt(3, pessoa.getBairro().getId());
			st.execute();
			st.close();
			
			st = connection.prepareStatement("insert into pessoa (nome, numero_telefone, email, id_endereco) values (?,?,?,(select max(id) from endereco))");
			st.setString(1, pessoa.getNome());
			st.setString(2, pessoa.getTelefone());
			st.setString(3, pessoa.getEmail());
			st.execute();

			
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void update(int id, Pessoa pessoa) throws Exception {
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = Conexao.getConexao();
			st = connection.prepareStatement("update endereco set logradouro=?, complemento=?, id_bairro=? where id=(select id_endereco from pessoa p where p.id=?)");
			st.setString(1, pessoa.getLogradouro());
			st.setString(2, pessoa.getComplemento());
			st.setInt(3, pessoa.getBairro().getId());
			st.setInt(4, id);
			st.execute();
			st.close();
			
			st = connection.prepareStatement("update pessoa set nome=?, numero_telefone=?, email=? where id=?");
			st.setString(1, pessoa.getNome());
			st.setString(2, pessoa.getTelefone());
			st.setString(3, pessoa.getEmail());
			st.setInt(4, id);
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
