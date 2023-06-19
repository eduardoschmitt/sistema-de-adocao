package model;

public class Racas {
	private int id;
	private Especies especies;
	private String nome;
	
	public Racas(int id, Especies especies, String nome) {
		this.id = id;
		this.especies = especies;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Especies getEspecies() {
		return especies;
	}

	public void setEspecies(Especies especies) {
		this.especies = especies;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
