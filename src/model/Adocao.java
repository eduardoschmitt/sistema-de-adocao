package model;

import java.sql.Date;

public class Adocao {
	private int id;
	private Animal animal;
	private Pessoa pessoa;
	private Date dataAdocao;

	public Adocao(int id, Animal animal, Pessoa pessoa, Date dataAdocao) {
		this.id = id;
		this.animal = animal;
		this.pessoa = pessoa;
		this.dataAdocao = dataAdocao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Date getDataAdocao() {
		return dataAdocao;
	}
	public void setDataAdocao(Date dataAdocao) {
		this.dataAdocao = dataAdocao;
	}
	
	
}
