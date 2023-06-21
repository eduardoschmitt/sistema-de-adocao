package model;

import java.sql.Date;

public class Animal {
	private int id;
	private String nome;
	private String sexo;
	private String descricao;
	private Cor cor;
	private Racas raca;
	private boolean adotado;
	private Date nascimento;
	
	public Animal(int id, String nome, String sexo, String descricao, Cor cor, Racas raca, boolean adotado,
			Date nascimento) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.descricao = descricao;
		this.cor = cor;
		this.raca = raca;
		this.adotado = adotado;
		this.nascimento = nascimento;
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
	public boolean isAdotado() {
		return adotado;
	}
	public void setAdotado(boolean adotado) {
		this.adotado = adotado;
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
	
}
