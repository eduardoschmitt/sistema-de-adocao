package model;

public class Bairro {
	private int id;
	private Cidade cidade;
	private Estado estado;
	private String nome;
	
	private static final String[] colunas = {"Id", "Nome", "Cidade", "Estado"};
	
	public Bairro(int id, String nome, Cidade cidade, Estado estado) {
		this.id = id;
		this.cidade = cidade;
		this.estado = estado;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public static String[] getColunas() {
		return colunas;
	}
	@Override
	public String toString() {
		return nome;
	}
	
}
