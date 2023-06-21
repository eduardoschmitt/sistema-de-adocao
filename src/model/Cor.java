package model;

public class Cor {
	private int id;
	private String nome;
	
	private static final String[] colunas = {"Id", "Nome"};
	
	public Cor(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
