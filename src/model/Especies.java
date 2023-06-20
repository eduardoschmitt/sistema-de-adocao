package model;

public class Especies {
	private int id;
	private String nome_especie;
	
	private static final String[] colunas = {"ID","Nome_Especies"};
	
	public Especies(int id, String nome_especie) {
		this.id = id;
		this.nome_especie = nome_especie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome_especie() {
		return nome_especie;
	}

	public void setNome_especie(String nome_especie) {
		this.nome_especie = nome_especie;
	}
	
	public static String[] getColunas() {
		return colunas;
	}
	
	@Override
	public String toString() {
		return id + " - " + nome_especie;
	}
}
