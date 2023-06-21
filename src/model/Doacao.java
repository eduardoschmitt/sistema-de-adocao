package model;

import java.sql.Date;

public class Doacao {
	private int id;
	private double valor;
	private Date data;
	private Pessoa pessoa;
	
	public Doacao(int id, double valor, Date data, Pessoa pessoa) {
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.pessoa = pessoa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
