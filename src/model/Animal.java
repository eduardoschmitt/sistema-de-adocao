package model;

import java.sql.Date;

public class Animal {
	private int id;
	private String nome;
	private String sexo;
	private String descricao;
	private Cor cor;
	private Racas raca;
	private Date nascimento;
	private boolean adotado;
	
	public Animal(int id, String nome, String sexo, String descricao, Cor cor, Racas raca,
			Date nascimento, boolean adotado) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.descricao = descricao;
		this.cor = cor;
		this.raca = raca;
		this.nascimento = nascimento;
		this.setAdotado(adotado);
	}
	
	public Animal(int id, String nome) {
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
	}
	public Racas getRaca() {
		return raca;
	}
	public void setRaca(Racas raca) {
		this.raca = raca;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

	public boolean isAdotado() {
		return adotado;
	}

	public void setAdotado(boolean adotado) {
		this.adotado = adotado;
	}
	
}
