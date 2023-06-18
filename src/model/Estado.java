package model;

public class Estado {
	private int id;
	private String sigla;
	private String nome;
	
	private static final String[] colunas = {"Id","Sigla", "Nome"};
	
	public Estado(int id, String sigla, String nome) {
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
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
		return sigla + " - " + nome;
	}
	
}
